(function () {
    'use strict';

    angular.module('login.route', [])
        .config(LoginRoute);

    LoginRoute.$inject = ['$stateProvider'];

    function LoginRoute($stateProvider) {

        $stateProvider.state('main.login',
            {
                url: 'login',
                views: {
                    'content': {
                        controller: 'loginCtrl',
                        templateProvider: ['$templateCache', function ($templateCache) {
                            return $templateCache.get('login/login.content.html');
                        }]
                    }
                }
            }
        );

    }

})();
