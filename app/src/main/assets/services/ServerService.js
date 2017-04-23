angular.module("SenseDog").factory("ServerService", ["$q", "$http", "ConfigurationService", function($q, $http, ConfigurationService) {
    return {
        createService: function (serviceData) {
            return ConfigurationService.get().then(function(config){
                return $http({
                    "method": "POST",
                    "url": config.baseUrl + "/alarm/create",
                    "data": JSON.stringify(serviceData)
                }).then(function(resp){
                    return resp.data;
                });
            });
        },
        stopService: function (alarmAuthToken) {
            return ConfigurationService.get().then(function(config){
                return $http({
                    "method": "POST",
                    "url": config.baseUrl + "alarm/stop",
                    "headers": {
                        "alarm-auth-token": alarmAuthToken
                    }
                }).then(function(resp){
                });
            });
        },
        startService: function (alarmAuthToken) {
            return ConfigurationService.get().then(function(config){
                return $http({
                    "method": "POST",
                    "url": config.baseUrl + "/alarm/start",
                    "headers": {
                        "alarm-auth-token": alarmAuthToken
                    }
                }).then(function(resp){
                });
            });
        },
        getStatus: function(alarmAuthToken) {
            return ConfigurationService.get().then(function(config){
                return $http({
                    "method": "GET",
                    "url": config.baseUrl + "/status",
                    "headers": {
                        "alarm-auth-token": alarmAuthToken
                    }
                }).then(function(resp){
                    return resp.data;
                });
            });
        }
    };
}]);