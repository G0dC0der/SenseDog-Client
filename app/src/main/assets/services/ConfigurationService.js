angular.module("SenseDog").factory('ConfigurationService', ['$q', function($q) {
    var cache = null;

    return {
        get: function () {
            if (cache != null) {
                return $q.resolve(cache);
            }

            var deferred = $q.defer();

            $.ajax({
                type: "GET",
                url: 'config.json',
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (config) {
                    cache = config;
                    deferred.resolve(config);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    deferred.reject(JSON.parse(jqXHR.responseText));
                }
            });

            return deferred.promise;
        }
    };
}]);