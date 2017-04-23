angular.module("SenseDog").factory('CloudService', ['$q', function($q) {
    return {
        getToken: function () {
            return window.AndroidCloudBindings.getCloudToken();
        }
    };
}]);