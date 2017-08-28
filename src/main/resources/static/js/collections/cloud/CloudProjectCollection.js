define([
  'underscore',
  'backbone',
  'models/cloud/CloudProjectModel'
], function(_, Backbone, cloudProjectModel){
	  var CloudProjectCollection = Backbone.Collection.extend({
		model : cloudProjectModel,
		url : function() {
			return "/cloud/" + this.cloudType + "/projects";
		},
		initialize : function(data) {
			this.cloudType = data.cloudType;
		}
	});
  return CloudProjectCollection;
});