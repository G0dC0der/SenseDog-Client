function AlarmController($scope, $route, StorageService, AlarmService, DeviceService, ServerService) {
    var alarmToken = StorageService.get('alarm-auth-token');
    var androidServiceRunning = AlarmService.isRunning();

    $scope.loaded = false;
    $scope.pinCode;
    $scope.alarmServiceRunning;

    if (alarmToken) {
        ServerService.status(alarmToken).then(function(status){
            $scope.alarmServiceRunning = status.systemStatus === 'ACTIVE';

            if ($scope.alarmServiceRunning && !AlarmService.isRunning()) {
                AlarmService.start();
            } else if (!$scope.alarmServiceRunning && AlarmService.isRunning()) {
                AlarmService.stop();
            }

            $scope.loaded = true;
        });
    }

    if (!alarmToken && androidServiceRunning) {
        AlarmService.stop();
    }

    $scope.start = function() {
        AlarmService.start();

        if (!alarmToken) {
            ServerService.createService({
                cloudToken: null,//TODO:
                deviceModel: DeviceService.deviceModel(),
                osVersion: DeviceService.osVersion(),
                appVersion: DeviceService.appVersion(),
                carrier: DeviceService.carrier(),
                battery: DeviceService.battery()
            }).then(function(resp) {
                StorageService.put('alarm-auth-token', resp.alarmAuthToken);
                $scope.alarmServiceRunning = true;
                $scope.pinCode = resp.pinCode;
            }).then(null, function(){
                $scope.alarmServiceRunning = null;
                $scope.pinCode = "-";
                AlarmService.stop();
            });
        } else {
            ServerService.resume(alarmToken).then(function(){
                $scope.alarmServiceRunning = true;
            }).then(null, function() {
                $scope.alarmServiceRunning = null;
                $scope.pinCode = "-";
                AlarmService.stop();
            });
        }
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
    'ServerService',
    AlarmController]);