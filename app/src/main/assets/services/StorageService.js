angular.module("SenseDog").factory('StorageService', ['$q', function($q) {
    return {
        put: function (key, value) {
            window.AndroidStorageBindings.put(key, value);
        },
        get: function (key) {
            return window.AndroidStorageBindings.get(key);
        },
        remove: function (key) {
            window.AndroidStorageBindings.remove(key);
        }
    };
}]);