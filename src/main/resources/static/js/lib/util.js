define([
  'jquery',
  'underscore',
  'backbone',
  'waitMe',
  'fader'
], function($, _, Backbone, waitMe, fader){
	showBlockUI = function () {
		$('#container').waitMe({
			effect:'roundBounce',
			bg: 'rgba(214,212,212,0.7)'
	 });
	},
	hideBlockUI = function () {
		$('#container').waitMe('hide');
	},
	showView = function(view) {
	    if (this.currentView){
	      this.currentView.close();
	    }
	    this.currentView = view;
	    this.currentView.render();
	},
	swap = function (fadeContainer,showContainer) {
		var hide =function() {
			fadeContainer.hide();
			showup();
		};
		fadeContainer.chainFade({
			  toThe: 'bottom',
			  speed: 500,
			  distance: 30,
			  after: hide
		});
		var showup=function(){
			showContainer.chainFade({
				toThe: 'top',
				speed: 700,
				direction:'backward',
				distance: 30
			});
		}
	},
	templateByName = function (templateName) {
		var that=this;
		if(templateName === 'aws_instance'){
			require([ 'views/project/aws/AWSInstanceView' ], function(AWSInstanceView) {
				var aWSInstanceView = new AWSInstanceView();
				aWSInstanceView.render();
			}); 
		} else if(templateName === 'aws_key_pair'){
			require([ 'views/project/aws/AWSKeyPairView' ], function(AWSKeyPairView) {
				var aWSKeyPairView= new AWSKeyPairView();
				aWSKeyPairView.render();
			}); 
		} else if(templateName === 'aws_security_group'){
			require([ 'views/project/aws/AWSSecurityGroupView' ], function(AWSSecurityGroupView) {
				var aWSSecurityGroupView= new AWSSecurityGroupView();
				aWSSecurityGroupView.render();
			}); 
		} 
		
	}
	
	return {
		block: showBlockUI,
		hide: hideBlockUI,
		showView:showView,
		swap:swap,
		templateByName:templateByName
	}
}); 