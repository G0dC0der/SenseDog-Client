function RegisterController($scope, $route, StorageService, AlarmService, DeviceService, ServerService, CloudService) {

    $scope.login = function() {
        ServerService.login({ email: $scope.loginEmail}).then(function(){
            StorageService.put("master-email", $scope.email);
        }).then(null, function(err){
            console.log("Failed to login.", err);
        });
    };

    $scope.register = function() {
        ServerService.register({
            name: $scope.name,
            email: $scope.email,
            phone: $scope.phone,
            cloudToken: CloudService.getToken()
        }).then(function(){
            StorageService.put("master-email", $scope.email);
        }).then(null, function(err){
            console.log("Failed to register account.", err);
        });
    };
}

angular.module('SenseDog').controller('RegisterController', [
    '$scope',
    '$route',
    'StorageService',
    'AlarmService',
    'DeviceService',
    'ServerService',
    'CloudService',
    RegisterController]);