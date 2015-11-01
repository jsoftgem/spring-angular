(function () {
    'use strict';

    angular.module('login.controller', [])
        .controller('loginCtrl', LoginCtrl);

    LoginCtrl.$inject = ['$scope', 'AuthenticationService'];

    function LoginCtrl($scope, AuthenticationService) {
        $scope.login = function (username, password) {
            AuthenticationService.Login(username, password, function (response) {
                AuthenticationService.SetCredentials(username, password);
            });
        };
    }

})();