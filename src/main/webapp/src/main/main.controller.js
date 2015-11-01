(function () {
    'use strict';

    angular.module('main.controller', [])
        .controller('mainCtrl', MainCtrl);

    MainCtrl.$inject = ['$scope', '$state', '$cookieStore'];

    function MainCtrl($scope, $state, $cookieStore) {
        $state.go('main.home');
    }
})();
