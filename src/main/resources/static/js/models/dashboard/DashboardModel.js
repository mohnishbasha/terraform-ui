define([
  'underscore',
  'backbone'
], function(_, Backbone){
	var DashboardModel = Backbone.Model.extend({
		defaults : {
			id : "",
			name : ""
		}
	});
  return DashboardModel;
}); 