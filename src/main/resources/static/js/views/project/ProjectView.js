define([
  'jquery',
  'underscore',
  'backbone',
  'bootstrap',
  'dnd',
  'util',
  'collections/project/ResourceCollection',
  'text!/templates/resourceTemplate.html',
  'text!/templates/aws/aws_provider.html'
], function($, _, Backbone,bootstrap, dnd, Util, ResourceCollection, projectTemplate,defaultProvider){
  var projectView = Backbone.View.extend({
    el: $('#container-content'),
    events : {
		'click .resource-class' : 'loadResources',
		'click .add-file' : 'showFileResource',
		'click .add-directory' : 'showDirectoryResource',
		'click .modal-save' : 'saveResource'
	},
    initialize: function (type, id) {
		 this.cloudType=type;
		 this.projectId=id;
		 this.collection = new ResourceCollection({ 'projectId': id});
    },
    render: function(){
    	var that = this;
    	this.collection.fetch({
			success:function(model){
				var data = {"resources": model.toJSON()};
				data['path']=that.collection.path.split('/');
				that.compiledTemplate = _.template(projectTemplate)(data);
				that.$el.html(that.compiledTemplate);
			},
			error:function(response){
				console.log("Error is occured while fetching the data:"+response);
			}
		});	
    },  
    onClose: function(){
		this.stopListening();
		this.collection.unbind("change", this.render);
	 },
	 loadResources: function(e){
		 var element=$(e.target);
		 var that=this;
		 if(element.attr('data-file') == "true"){
			 $.ajax({ 
				  type: "GET", 
				  url: "/project/"+that.projectId+"/resource/"+that.collection.path+"/"+element.text(),
				  async : false, 
				  success: function (response) {
					  require([ 'text!/templates/fileContent.html' ], function(fileTemplate) {
				         var template = _.template(fileTemplate);
				         $('.modal-body').html(template);
				         $('#fileName').val(element.text());
						 $('#fileContent').val(response);
				         $('.modal').modal('show');
						});
				  }, 
				  error: function(response) { 
					  return false; 
				  },
				  complete: function() {
					  $('.modal').modal('hide'); 
					  $('body').removeClass('modal-open');
					  $('.modal-backdrop').remove();
					  parent.render();
				  } 
			  });
		 } else {
		  this.collection.appendURL(element.text());
		  this.render();
		 }
     },
     showFileResource: function() {
    	require([ 'text!/templates/fileContent.html' ], function(fileTemplate) {
       	 var template = _.template(fileTemplate);
         $('.modal-body').html(template);
         $('.modal').modal('show');
		});
     },
     showDirectoryResource: function() {
    	 require([ 'text!/templates/directoryContent.html' ], function(fileTemplate) {
        	 var template = _.template(fileTemplate);
             $('.modal-body').html(template);
             $('.modal').modal('show');
    		});
     },
     saveResource: function(){
    	 var fileResource= {};
		  var parent = this;
		  fileResource["resource_name"] = $('#fileName').val();
		  fileResource["resource_type"] = $('#resourceType').val();
		  fileResource["file_content"] = $('#fileContent').val();
    	 $.ajax({ 
			  type: "POST", 
			  url: this.collection.url(),
			  contentType: "application/json",
			  async : false,
			  data:JSON.stringify(fileResource),
			  success: function (response) { 
				 
			  }, 
			  error: function(response) { 
				  return false; 
			  },
			  complete: function() {
				  $('.modal').modal('hide'); 
				  $('body').removeClass('modal-open');
				  $('.modal-backdrop').remove();
				  parent.render();
			  } 
		  });
     }
  });
  return projectView;
});