function ChooseController($scope, $location, StorageService, AlarmService) {
    $scope.chooseMaster = function() {
        choose('master');
    };

    $scope.chooseAlarm = function() {
        choose('alarm');
    };

    var choose = function(role) {
        StorageService.put("role", role);
        $location.path(role);
    };

    /*
     * Init
     */
    if (AlarmService.isRunning()) {
        $location.path('alarm');
    } /*else if (MasterService.isRunning()) { //TODO: Don't forget
        $location.path('master');
    } */else {
        var role = StorageService.get("role");

        if (role) {
            if (role === 'master') {
                $location.path('master');
            } else if (role === 'alarm') {
                $location.path('alarm');
            }
        }
    }
}

angular.module('SenseDog').controller('ChooseController', [
    '$scope',
    '$location',
    'StorageService',
    'AlarmService',
    ChooseController]);