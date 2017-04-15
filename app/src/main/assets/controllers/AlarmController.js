function AlarmController($scope, $route, StorageService, AlarmService, DeviceService) {
    $scope.alarmServiceRunning = AlarmService.isRunning();

    $scope.start = function() {
        $route.reload();
    };

    $scope.stop = function() {
        $route.reload();
    };
}

angular.module('SenseDog').controller('AlarmController', [
    '$scope',
    '$route',
    'StorageService',
    'AlarmService',
    'DeviceService',
    AlarmController]);