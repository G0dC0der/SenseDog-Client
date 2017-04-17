angular.module("SenseDog").factory('AlarmService', ['$q', function($q) {
    return {
        start: function() {
            if (window.AndroidServiceBindings.isAlarmServiceRunning()) {
                throw new Exception("Alarm service already running.");
            }
            window.AndroidServiceBindings.startAlarmService();
        },
        stop: function() {
            if (!window.AndroidServiceBindings.isAlarmServiceRunning()) {
                throw new Exception("Alarm service not even running.");
            }
            window.AndroidServiceBindings.stopAlarmService();
        },
        isRunning: function() {
            return window.AndroidServiceBindings.isAlarmServiceRunning();
        }
    };
}]);