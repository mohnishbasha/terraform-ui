define([
  'underscore',
  'backbone'
], function(_, Backbone){
	var ResourceModel = Backbone.Model.extend({
		defaults : {
			name : "",
			isFile: ""
		}
	});
  return ResourceModel;
}); 