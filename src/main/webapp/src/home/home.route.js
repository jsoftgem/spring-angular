(function () {

    'use strict';

    angular.module('home.route', [])
        .config(['$stateProvider', '$urlRouterProvider', MainConfig]);

    function MainConfig($stateProvider, $urlRouterProvider) {
        $stateProvider.state('main.home', {
            url: 'home',
            views: {
                'menuBar': {
                    templateProvider: ['$templateCache', function ($templateCache) {
                        return $templateCache.get('main/partials/main.menubar.html');
                    }],
                    controller: 'homeMenuBarCtrl'
                },
                'sideBar': {
                    resolve: {
                        sidebarMenu: [function () {
                            var sidebar = [
                                {name: "settings", label: "Settings", ref: "menu.settings"}
                            ];
                            return sidebar;
                        }]
                    },
                    templateProvider: ['$templateCache', function ($templateCache) {
                        return $templateCache.get('main/partials/main.sidebar.html');
                    }],
                    controller: 'homeSidebarCtrl'
                },
                'content': {
                    templateProvider: ['$templateCache', function ($templateCache) {
                        return $templateCache.get('main/partials/main.content.html');
                    }],
                    controller: 'homeCtrl'
                }
            }
        });
    }

})();