(function () {
    'use strict';

    angular.module('login.controller', [])
        .controller('loginCtrl', ['$scope', 'AuthenticationService', LoginCtrl]);

    function LoginCtrl($scope, AuthenticationService) {
        var auth = new AuthenticationService();
        $scope.login = function (username, password) {
            auth.login(username, password, function (response) {
                auth.SetCredentials(username, password);
            });
        };
    }

})();