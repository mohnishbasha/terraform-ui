define([ 'backbone','util'], function(Backbone,Util) {
	var Router = Backbone.Router.extend({
		routes : {
		    '':'home',	
			'show/aws' : 'showAWSCloudView',
			'show/azure' : 'showAzureCloudView',
			':cloud/project/:id' : 'showProjectView'
		}
	});
	var initialize = function() {
		var app_router = new Router;
		app_router.on('route:home', function() {
			require([ 'views/dashboard/DashboardView' ], function(DashboardView) {
				Util.block();
				var dashboardView = new DashboardView();
				Util.showView(dashboardView);
				Util.hide();
			}); 
		});
		app_router.on('route:showAWSCloudView', function() {
			require([ 'views/cloud/AWSCloudProjectView' ], function(AWSCloudProjectView) {
				Util.block();
				var awsCloudProjectView = new AWSCloudProjectView();
				Util.showView(awsCloudProjectView);
				Util.hide();
			}); 
		});
		app_router.on('route:showAzureCloudView', function() {
			require([ 'views/cloud/AzureCloudProjectView' ], function(AzureCloudProjectView) {
				Util.block();
				var azureCloudProjectView = new AzureCloudProjectView();
				Util.showView(azureCloudProjectView);
				Util.hide();
			}); 
		});
		app_router.on('route:showProjectView', function(cloud,id) {
			require([ 'views/project/ProjectView' ], function(ProjectView) {
				Util.block();
				var projectView = new ProjectView(cloud,id);
				Util.showView(projectView);
				Util.hide();
			}); 
		});
		Backbone.history.start();
	};
	
	return {
	    initialize: initialize
	};
});
