angular.module("SenseDog").factory('AlarmService', ['$q', function($q) {
    return {
        start: function() {
            if (window.AndroidServiceBinding.isAlarmServiceRunning()) {
                throw new Exception("Alarm service already running.");
            }
            window.AndroidServiceBinding.startAlarmService();
        },
        stop: function() {
            if (!window.AndroidServiceBinding.isAlarmServiceRunning()) {
                throw new Exception("Alarm service not even running.");
            }
            window.AndroidServiceBinding.stopAlarmService();
        },
        isRunning: function() {
            return window.AndroidServiceBinding.isAlarmServiceRunning();
        }
    };
}]);