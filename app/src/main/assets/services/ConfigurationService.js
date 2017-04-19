angular.module("SenseDog").factory('ConfigurationService', ['$q', '$http', function($q, $http) {
    var cache = null;

    return {
        get: function () {
            if (cache != null) {
                return $q.resolve(cache);
            }

            return $http.get('config.json').then(function(config){
                cache = config;
                return config;
            });
        }
    };
}]);