define([
  'jquery',
  'underscore',
  'backbone',
  'collections/dashboard/DashboardCollection',
  'text!/templates/dashboardContent.html'
], function($, _, Backbone, DashboardCollection, dashboardTemplate){
  var dashboardView = Backbone.View.extend({
    el: $('#container-content'),
    render: function(){
    	$(".sidebar").find(".active").toggleClass("active");
    	$("#dashboard_link").addClass("active");
    	 var that = this;
    	this.collection = new DashboardCollection();
		var compiledTemplate = _.template(dashboardTemplate);
		that.$el.html(compiledTemplate);
    },
    onClose: function(){
		this.stopListening();
	    this.collection.unbind("change", this.render);
	  }
  });
  return dashboardView;
});