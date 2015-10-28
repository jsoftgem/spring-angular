(function () {
    'use strict';
    angular.module("spSession", [])
        .controller("sessionCtrl", ["$scope", "AuthenticationService", function ($s, authService) {

            $s.login = function (username, password) {
                authService.Login(username, password, function (response) {
                    alert("login successful!");
                    authService.SetCredentials(username, password);
                });
            };

        }]);
})();