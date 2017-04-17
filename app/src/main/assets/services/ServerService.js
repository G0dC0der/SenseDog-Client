angular.module("SenseDog").factory('ServerService', ['$q', 'ConfigurationService', function($q, ConfigurationService) {
    var baseUrl;

    ConfigurationService.get().then(function(config){
        baseUrl = config.baseUrl;
    });

    return {
        createService: function (serviceData) {
            var deferred = $q.defer();

            $.ajax({
                type: "POST",
                url: baseUrl + '/alarm/create',
                data: JSON.stringify(serviceData),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (token) {
                    deferred.resolve(token);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    deferred.reject(JSON.parse(jqXHR.responseText));
                }
            });

            return deferred.promise;
        },
        stop: function (alarmAuthToken) {
            var deferred = $q.defer();

            $.ajax({
                type: "POST",
                url: baseUrl + '/alarm/stop',
                beforeSend: function(request) {
                    request.setRequestHeader("alarm-auth-token", alarmAuthToken);
                },
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function () {
                    deferred.resolve();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    deferred.reject(JSON.parse(jqXHR.responseText));
                }
            });

            return deferred.promise;
        },
        resume: function (alarmAuthToken) {
            var deferred = $q.defer();

            $.ajax({
                type: "POST",
                url: baseUrl + '/alarm/resume',
                beforeSend: function(request) {
                    request.setRequestHeader("alarm-auth-token", alarmAuthToken);
                },
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function () {
                    deferred.resolve();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    deferred.reject(JSON.parse(jqXHR.responseText));
                }
            });

            return deferred.promise;
        },
        status: function(alarmAuthToken) {
            var deferred = $q.defer();

            $.ajax({
                type: "GET",
                url: baseUrl + '/status',
                beforeSend: function(request) {
                    request.setRequestHeader("alarm-auth-token", alarmAuthToken);
                },
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (status) {
                    deferred.resolve(status);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    deferred.reject(JSON.parse(jqXHR.responseText));
                }
            });

            return deferred.promise;
        }
    };
}]);