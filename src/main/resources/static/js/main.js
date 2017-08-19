require.config({
	baseUrl : 'js',
	paths : {
		jquery :       'lib/jquery-min',
		underscore :   'lib/underscore-min',
		backbone :     'lib/backbone-min',
        text:          'lib/text',
        waitMe:        'lib/waitMe',
        util:          'lib/util',
        bootstrap:     'lib/bootstrap.min',
        datatable:     'lib/jquery.dataTables.min',
        databoot:      'lib/dataTables.bootstrap.min',
        fader:         'lib/chain_fade',
        dnd:           'lib/dnd',
        scroll:        'lib/jQuery.scrollText'
	},
	shim : {
		underscore: {
			exports: "_"
		},
		backbone: {
			deps: ['underscore', 'jquery'],
			exports: 'Backbone'
		},
		waitMe:{
			deps: ['jquery']
		},
		datatable:{
			deps: ['jquery']
		},
		fader:{
			deps: ['jquery']
		},
		databoot:{
			deps: ['jquery','datatable','bootstrap']
		},
		dnd:{
			deps: ['jquery']
		},
		scroll:{
			deps: ['jquery']
		}
	}
	
});

require(['app'], function(app){
	app.initialize();
});

