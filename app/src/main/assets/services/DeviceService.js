angular.module("SenseDog").factory('DeviceService', ['$q', function($q) {
    return {
        osVersion: function() {
            return window.AndroidInformationBindings.getOsVersion();
        },
        deviceModel: function() {
            return window.AndroidInformationBindings.getDeviceModel();
        },
        appVersion: function() {
            return window.AndroidInformationBindings.getAppVersion();
        },
        carrier: function() {
            return window.AndroidInformationBindings.getCarrier();
        },
        battery: function() {
            return window.AndroidInformationBindings.getBatteryLevel();
        }
    };
}]);