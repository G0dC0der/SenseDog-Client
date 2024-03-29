angular.module("SenseDog").factory('AlarmService', ['$q', function($q) {
    return {
        start: function() {
            if (this.isRunning()) {
                throw "Alarm service already running.";
            }
            window.AndroidServiceBindings.startAlarmService();
        },
        stop: function() {
            if (!this.isRunning()) {
                throw "Alarm service not even running.";
            }
            window.AndroidServiceBindings.stopAlarmService();
        },
        isRunning: function() {
            return window.AndroidServiceBindings.isAlarmServiceRunning();
        }
    };
}]);