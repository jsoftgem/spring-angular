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
            });

            sp.state('main', {
                url: '/main',
                controller: 'mainCtrl',
                templateUrl: 'app/app/Main/main.html',
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load('app/app/Main/main.controller.js');
                    }]
                }
            }).state('main.home', {
                controller: 'homeCtrl',
                views: {
                    'sideMenu': {
                        templateUrl: 'app/app/Main/Home/home.sidemenu.html', controller: 'mainCtrl'
                    },
                    'content': {
                        templateUrl: 'app/app/Main/Home/home.content.html', controller: 'mainCtrl'
                    }
                },
                resolve: {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load('app/app/Main/Home/home.controller.js');
                    }]
                }
            });


        }]);
})();

