(function () {
    'use strict';
    angular.module('home.controller', []).controller('homeCtrl', HomeCtrl).controller('homeSidebarCtrl', HomeSidebarCtrl);
    HomeCtrl.$inject = ['$scope'];
    function HomeCtrl($scope) {
    }
    HomeSidebarCtrl.$inject = ['$scope', 'sidebarMenu'];
    function HomeSidebarCtrl($scope, sidebarMenu) {
        $scope.sidebarMenu = sidebarMenu;
    }
})();