define([
  'jquery',
  'underscore',
  'backbone',
  'text!/templates/aws/aws_instance.html'
], function($, _, Backbone, awsTemplate){
  var awsView = Backbone.View.extend({
    el: $('#drag-zone'),
    render: function(){
    	 var that = this;
		var compiledTemplate = _.template(awsTemplate);
		that.$el.append(compiledTemplate);
    },
    onClose: function(){
		this.stopListening();
	  }
  });
  return awsView;
});