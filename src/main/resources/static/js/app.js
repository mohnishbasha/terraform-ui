define([
  'jquery',
  'underscore',
  'backbone',
  'routes/router',
], function($, _, Backbone, router){
  

	var initialize = function() {
		Backbone.View.prototype.close = function(){
			  this.undelegateEvents();
			  this.unbind();
			  if (this.onClose){
			    this.onClose();
			  }
			};
		router.initialize();
	}

	return {
		initialize : initialize
	};
});