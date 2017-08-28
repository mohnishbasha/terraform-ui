define([
  'jquery',
  'underscore',
  'backbone',
  'text!/templates/aws/aws_key_pair.html'
], function($, _, Backbone, awsTemplate){
  var awsView = Backbone.View.extend({
    el: $('#drag-zone'),
    render: function(){
		var that = this;
		var compiledTemplate = _.template(awsTemplate)({'prefix':'aws_key'});
		that.$el.append(compiledTemplate);
		that.$el.find('#aws_key_pair_box').on('click', function() {
	       var content = $(this).parent().parent().parent().find(".content-body");
	       if (content.hasClass("collapsed")) {
	           content.removeClass("collapsed").slideDown(500);
	           $(this).removeClass("fa-chevron-up").addClass("fa-chevron-down");
	       } else {
	           content.addClass("collapsed").slideUp(500);
	           $(this).removeClass("fa-chevron-down").addClass("fa-chevron-up");
	       }

	   });  
    },
    onClose: function(){
		this.stopListening();
	  }
  });
  return awsView;
});