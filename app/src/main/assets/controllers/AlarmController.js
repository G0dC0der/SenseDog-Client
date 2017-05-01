function AlarmController($scope, $route, StorageService, AlarmService, DeviceService, ServerService, CloudService) {
    var alarmToken = StorageService.get('alarm-auth-token');
    var androidServiceRunning = AlarmService.isRunning();

    $scope.loaded = false;
    $scope.pinCode;
    $scope.alarmServiceRunning;

    if (alarmToken) {
        ServerService.getStatus(alarmToken).then(function(status){
            if (!AlarmService.isRunning()) {
                AlarmService.start();
            }
            $scope.alarmServiceRunning = true;
        }).then(null, function(err){
            console.error("No service is running on server.", err);
            StorageService.remove('alarm-auth-token');
        }).then(function(){
            $scope.loaded = true;
        });
    } else {
        $scope.alarmServiceRunning = false;
        $scope.loaded = true;
    }

    if (!alarmToken && androidServiceRunning) {
        AlarmService.stop();
    }

    $scope.start = function() {
        $scope.loaded = false;
        AlarmService.start();

        if (!alarmToken) {
            ServerService.createService({
                cloudToken: CloudService.getToken(),
                deviceModel: DeviceService.deviceModel(),
                osVersion: DeviceService.osVersion(),
                appVersion: DeviceService.appVersion(),
                carrier: DeviceService.carrier(),
                battery: DeviceService.battery()
            }).then(function(resp) {
                StorageService.put('alarm-auth-token', resp.alarmAuthToken);
                $scope.alarmServiceRunning = true;
                $scope.pinCode = resp.pinCode;
            }).then(null, function(err){
                console.error('Could not create alarm service.', err);
                $scope.alarmServiceRunning = false;
                $scope.pinCode = "-";
                AlarmService.stop();
            }).then(function(){
                $scope.loaded = true;
            });
        } else {
            ServerService.startService(alarmToken).then(function(){
                $scope.alarmServiceRunning = true;
            }).then(null, function(err) {
                console.error('Could not start alarm service.', err);
                $scope.alarmServiceRunning = false;
                $scope.pinCode = "-";
                AlarmService.stop();
            }).then(function(){
                $scope.loaded = false;
            });
        }
    };

    $scope.destroy = function() {
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
    'CloudService',
    AlarmController]);