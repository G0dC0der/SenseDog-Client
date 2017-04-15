function RouteStartController($scope, $location, StorageService) {
    //Also check if a service is already running

    var role = StorageService.get("role");

    if (!role) {
        $location.path('choose')
    } else if (role === 'master') {
        $location.path('master')
    } else if (role === 'alarm') {
        $location.path('alarm')
    }
}

angular.module('SenseDog').controller('RouteStartController', [
    '$scope',
    '$location',
    'StorageService',
    RouteStartController]);