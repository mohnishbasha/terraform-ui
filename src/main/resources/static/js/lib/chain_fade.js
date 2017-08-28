/*!
* jQuery ChainFade; version: 1.0
* 2014 - Gonzalo Castillo
*/
(function($){

    $.fn.chainFade = function(options, callback){

        // Default options
        var defaults = {
            // Time in miliseconds before the animation starts
            startAt: 0,

            // Time in miliseconds between each animation
            interval: 300,

            // Speed of the animation in miliseconds
            speed: 700,

            // The type of animation. For the moment is just 'fade' :3
            fx: 'fade',

            // Initial distance of each elements
            distance: 50,

            // Direction of the animation: 'forward' or 'backward'
            direction: 'forward',

            // Where the element should go: toThe 'left', 'right', 'bottom' or 'top'
            toThe: 'top',

            // To apply the same height based on the taller element
            fixedHeight: false,

            // If you want to see the selected options in the console
            messages: false,

            // Change to false if you want to animate all the elements at once if you don't have a container for those elements
            queue: true,

            // Easing, MUST INCLUDE JQUERY UI FOR ANOTHER!!!
            ease: 'swing',

            // Callback function
            after: function(){}
        },

        // Pass the defaults
        settings = $.extend( defaults , options);
        
        // Console Messages
        if( settings.messages === true){
            console.log('--- Chain fade start ---');
            console.log('Selected options:');
            for(var opt in settings){
                console.log('>'+opt+': '+settings[opt]);
            }
        }

        //Fix Height
        if( settings.fixedHeight === true ){
            var max_h = 0;
            this.each(function(){
                if( $(this).height() > max_h){
                    max_h = $(this).height();
                }
            });

            // Applies max height (max_h) to every element
            $(this).height(max_h);
        }

        var getCss = function($elem, prop) {
            var wasVisible = $elem.css('display') !== 'none';
            try {
                return $elem.hide().css(prop);
            } finally {
                if (wasVisible) $elem.show();
            }
        };
        var arr_pos = ['top', 'bottom', 'left', 'right'],
            pos = {
                toThe: null,
                value: 0
            };

        // Different animations effects (there's only one at the moment)
        switch(settings.fx){

            // Regular
            case 'fade':
                // Count the elements to trigger the callback after all elements were animated
                var items = this.length,
                    count = 0;

                return this.each(function(i, el){

                    // Get the actual value and starting position
                    for (var i = 0; i < arr_pos.length; i++) {
                        var v = getCss( $(this), arr_pos[i] );
                        if( v !== 'auto' ){
                            pos['toThe'] = arr_pos[i];
                            pos['value'] = v.slice(0,-2);
                            break;
                        }
                    };

                    // Check if the element goes somewhere especific
                    if( pos['toThe'] == null ){
                        pos['toThe'] = settings.toThe;
                    }

                    // Get the 'position' property from the element
                    // Don't know why but elements that doesn't have 'position' sepecified
                    // return 'static' as 'position'
                    var p = getCss( $(this), 'position' );
                    if( p === 'static'){
                        p = 'relative';
                    }

                    var that=$(this);
                    // Build an object with the options for the css() method
                    var css_options = {};
                        css_options['position'] = p;
                        css_options['display'] = 'block';
                        if( settings.direction === 'backward' ){
//                        	css_options['opacity'] = 0;
                            css_options[pos.toThe] = ( parseInt(pos['value'])+parseInt(settings.distance) ) + 'px';
                        }
                    // Pass the css options
                    $(this).css(css_options);
                    
                    // Build an object with the options for the animate() method
                    var anim_options = {};
                        anim_options['opacity'] = 1;
                        anim_options[pos.toThe] = pos['value']+'px';
                        if( settings.direction === 'forward' ){
                        	anim_options[pos.toThe] = ( parseInt(pos['value'])-parseInt(settings.distance) ) + 'px';
	                      }

                        // The animation
                    $(this).stop(true, false).delay(settings.startAt).animate(
                        anim_options, 
                        {
                            duration: settings.speed, 
                            easing: settings.ease,
                            queue: settings.queue, 
                            complete: function(){
                                count+=1;
                                if(count === items){
                                	css_options[pos.toThe] = parseInt(pos['value'])+'px';
                                	that.css(css_options);
                                    settings.after();
                                }
                            }
                        });

                    // Increase the delay for the next element based 
                    // on the startAt option and the interval set in the options
                    settings.startAt += settings.interval;
                });
                break;
        }

    };
}(jQuery));