(function () {
    'use strict';

    angular.module('login.controller', [])
        .controller('loginCtrl', LoginCtrl);

    LoginCtrl.$inject = ['$scope', 'AuthenticationService', '$state'];

    function LoginCtrl($scope, AuthenticationService, state) {
        $scope.login = function (username, password) {
            AuthenticationService.Login(username, password, function (response) {
                AuthenticationService.SetCredentials(username, password);
                state.go('main.home');
            });
        };
    }

})();