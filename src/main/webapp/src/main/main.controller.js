(function () {
    'use strict';

    angular.module('main.controller', [])
        .controller('mainCtrl', MainCtrl)
        .controller('mainMenuCtrl', MainMenuCtrl);

    MainCtrl.$inject = ['$scope', '$state', '$cookieStore'];

    MainMenuCtrl.$inject = ['$scope', '$state', '$cookieStore'];

    function MainCtrl($scope, $state, $cookieStore) {

        if (angular.isUndefined($cookieStore.get('globals'))) {
            $state.go('main.login');
        } else {
            $state.go('main.home');
        }

    }

    function MainMenuCtrl($scope, $state, $cookieStore) {

        $scope.logout = function () {
            $cookieStore.remove('globals');
            $state.go('main.login');
        };
    }

})();
