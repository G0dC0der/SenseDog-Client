angular.module('SenseDog', ['ngRoute']).config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "pages/choose.html"
    })
    .when("/alarm", {
        templateUrl : "pages/alarm.html"
    })
    .when("/master", {
        templateUrl : "pages/master.html"
    });
});