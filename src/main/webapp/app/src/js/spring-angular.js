/**
 * Created by Jerico on 14/10/2015.
 */
(function () {
    'use strict';
    angular.module('spApp', ['spTemplates', 'oc.lazyLoad', 'sp.core', 'sp.commons', 'ui.router', 'ngCookies', 'ActiveResource', 'ngMaterial'])
        .config(['$stateProvider', '$urlRouterProvider', function (sp, urp) {
            urp.otherwise('/');

            sp.state('spa', {
                url: '/',
                templateProvider: function ($templateCache) {
                    return $templateCache.get('main/webapp/app/src/templates/app/home.html');
                },
                controller: 'sessionCtrl'
            })
                .state('spa.main', {
                    url: 'main',
                    controller: 'mainCtrl',
                    templateUrl: 'app/app/Main/main.html',
                    resolve: {
                        loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                            return $ocLazyLoad.load('app/app/Main/main.controller.js');
                        }]
                    }
                });
        }]);
})();

