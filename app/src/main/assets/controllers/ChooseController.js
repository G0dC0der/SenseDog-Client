function ChooseController($scope, $location, StorageService) {
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
}

angular.module('SenseDog').controller('ChooseController', [
    '$scope',
    '$location',
    'StorageService',
    ChooseController]);