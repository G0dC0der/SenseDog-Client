angular.module("SenseDog").factory('StorageService', ['$q', function($q) {
    if (!localStorage) {
        throw new Exception("Local Storage is undefined!");
    }

    return {
        put: function (key, value) {
            localStorage.setItem(key, value);
        },
        get: function (key) {
            return localStorage.getItem(key);
        },
        remove: function (key) {
            localStorage.removeItem(key);
        }
    };
}]);