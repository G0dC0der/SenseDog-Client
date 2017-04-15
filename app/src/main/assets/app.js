var sensedog = {};
sensedog.server = {};
sensedog.server.BASE_URL = "http://10.0.75.1:8080/sensedog"; //TOOD: Even the BASE_URL should be defined in the java code and referenced here through bindings

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