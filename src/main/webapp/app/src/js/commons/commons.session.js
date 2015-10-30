(function () {
    'use strict';
    angular.module('commons.session', [])
        .controller('sessionCtrl', ['$scope', 'AuthenticationService', '$ocLazyLoad', '$state', function ($s, authService, $ocLazyLoad, $state) {
            $s.login = function (username, password) {
                authService.Login(username, password, function (response) {
                    authService.SetCredentials(username, password);
                    $state.go('main');
                });
            };

        }]);
})();