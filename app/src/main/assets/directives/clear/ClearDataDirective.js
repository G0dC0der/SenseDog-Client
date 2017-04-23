angular.module("SenseDog").directive('appReset', ['$location', 'StorageService', 'AlarmService', function($location, StorageService, AlarmService) {
    return {
        templateUrl: "directives/clear/template.html",
        controller: function($scope){
            $scope.clear = function(){
                StorageService.remove('alarm-auth-token');
                StorageService.remove('master-auth-token');

                if (AlarmService.isRunning()) {
                    AlarmService.stop();
                }

                $location.path('');
            }
        },
        link: function(scope, element, attrs) {

        }
    };
}]);