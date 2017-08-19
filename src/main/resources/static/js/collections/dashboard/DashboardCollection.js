define([
  'underscore',
  'backbone',
  'models/dashboard/DashboardModel'
], function(_, Backbone, dashboardModel){
  var DashboardCollection = Backbone.Collection.extend({
    model: dashboardModel,
    url:"/clouds"
  });
  return DashboardCollection;
});