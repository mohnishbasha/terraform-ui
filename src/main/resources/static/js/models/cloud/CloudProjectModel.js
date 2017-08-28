define([
  'underscore',
  'backbone'
], function(_, Backbone){
	var CloudProjectModel = Backbone.Model.extend({
		defaults : {
			name : "",
			cloudType:""
		},
		idAttribute: "id"
	});
  return CloudProjectModel;
}); 