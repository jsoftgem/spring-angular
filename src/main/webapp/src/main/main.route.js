(function () {

    'use strict';

    angular.module('main.route', [])
        .config(['$stateProvider', '$urlRouterProvider', MainConfig]);

    function MainConfig($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/');

        $stateProvider.state('main', {
            url: '/',
            templateProvider: ['$templateCache', function ($templateCache) {
                return $templateCache.get('main/main.html');
            }],
            controller: 'mainCtrl'
        });

    }

})();