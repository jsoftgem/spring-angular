(function () {
    'use strict';
    angular.module('home.controller', []).controller('homeCtrl', HomeCtrl).controller('homeSidebarCtrl', HomeSidebarCtrl).controller('homeMenuBarCtrl', HomeMenuBarCtrl);
    HomeCtrl.$inject = ['$scope'];
    HomeSidebarCtrl.$inject = ['$scope', 'sidebarMenu'];
    HomeMenuBarCtrl.$inject = ['$scope', '$state', '$cookieStore'];

    function HomeCtrl($scope) {
    }

    function HomeSidebarCtrl($scope, sidebarMenu) {
        $scope.sidebarMenu = sidebarMenu;
    }

    function HomeMenuBarCtrl($scope, $state, $cookieStore) {
        $scope.logout = function () {
            $cookieStore.remove('globals');
            $state.go('main');
        };
    }

})();