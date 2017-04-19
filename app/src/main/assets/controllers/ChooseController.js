function ChooseController($scope, $location, StorageService, AlarmService) {
    $scope.chooseMaster = function() {
        $location.path("master");
    };

    $scope.chooseAlarm = function() {
        $location.path("alarm");
    };

    if (StorageService.get("master-auth-token")) {
        $location.path('master');
    } else if (StorageService.get("alarm-auth-token")) {
        $location.path('alarm');
    }
}

angular.module('SenseDog').controller('ChooseController', [
    '$scope',
    '$location',
    'StorageService',
    'AlarmService',
    ChooseController]);