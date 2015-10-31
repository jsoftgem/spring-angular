(function () {
    'use strict';

    angular.module('main.controller', [])
        .controller('mainCtrl', ['$scope', '$state', MainCtrl]);


    function MainCtrl($scope, $state) {
        $scope.login = function () {
            $state.go('login');
        };
    }
})();
