angular.module("SenseDog").factory('ConfigurationService', ['$q', '$http', function($q, $http) {
    var cache;

    return {
        get: function () {
            if (cache) {
                return $q.resolve(cache);
            }

            return $http.get('config.json').then(function(resp){
                cache = resp.data;
                return cache;
            });
        }
    };
}]);