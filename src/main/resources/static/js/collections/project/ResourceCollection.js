define([
  'underscore',
  'backbone',
  'models/project/ResourceModel'
], function(_, Backbone, resourceModel){
	  var ResourceCollection = Backbone.Collection.extend({
		model : resourceModel,
		url : function() {
			return "project/" + this.projectId+"/resources/"+this.path;
		},
		initialize : function(data) {
			this.projectId = data.projectId;
			this.path="";
		},
		appendURL: function(name){
			if(this.path == ""){
				this.path =name;
			} else {
				this.path=this.path+"/"+name;
			}	
		},
		path: function(){
			return this.path;
		}
	});
  return ResourceCollection;
});