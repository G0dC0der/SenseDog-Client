angular.module("SenseDog").factory("ServerService", ["$q", "$http", "ConfigurationService", function($q, $http, ConfigurationService) {
    var baseUrl = "";

    ConfigurationService.get().then(function(config){
        baseUrl = config.baseUrl;
    });

    return {
        createService: function (serviceData) {
            return $http({
                "method": "POST",
                "url": baseUrl + "/alarm/create",
                "data": JSON.stringify(serviceData)
            }).then(function(resp){
                return resp.data;
            });
        },
        stopService: function (alarmAuthToken) {
            return $http({
                "method": "POST",
                "url": baseUrl + "alarm/stop",
                "headers": {
                    "alarm-auth-token": alarmAuthToken
                }
            }).then(function(resp){
                return $q.resolve();
            });
        },
        startService: function (alarmAuthToken) {
            return $http({
                "method": "POST",
                "url": baseUrl + "/alarm/start",
                "headers": {
                    "alarm-auth-token": alarmAuthToken
                }
            }).then(function(resp){
                return $q.resolve();
            });
        },
        status: function(alarmAuthToken) {
            return $http({
                "method": "GET",
                "url": baseUrl + "/status",
                "headers": {
                    "alarm-auth-token": alarmAuthToken
                }
            }).then(function(resp){
                return resp.data;
            });
        }
    };
}]);