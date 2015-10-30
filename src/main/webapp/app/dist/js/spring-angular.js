/**
 * Created by Jerico on 22/10/2015.
 */
(function () {

    'use strict';


    angular.module("user.controller", [])
        .controller("userCtrl", UserController);


    function UserController($scope) {

    }


})();;/**
 * Created by Jerico on 15/10/2015.
 */
(function () {
    'use strict';

    angular.module('commons.authentication', [])
        .factory('AuthenticationService', ['Base64', '$http', '$cookieStore', '$rootScope', '$timeout', "spHelper", "LOGIN_URL",
            function (Base64, $http, $cookieStore, $rootScope, $timeout, spH, LURL) {
                var service = {};

                service.Login = function (username, password, callback) {

                    $http.post(spH.withContext(LURL), {username: username, password: password})
                        .success(function (response) {
                            callback(response);
                        });

                };

                service.SetCredentials = function (username, password) {
                    var authdata = Base64.encode(username + ':' + password);

                    $rootScope.globals = {
                        currentUser: {
                            username: username,
                            authdata: authdata
                        }
                    };

                    $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
                    $cookieStore.put('globals', $rootScope.globals);
                };

                service.ClearCredentials = function () {
                    $rootScope.globals = {};
                    $cookieStore.remove('globals');
                    $http.defaults.headers.common.Authorization = 'Basic ';
                };


                return service;
            }])
        .factory('Base64', function () {
            /* jshint ignore:start */

            var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';

            return {
                encode: function (input) {
                    var output = "";
                    var chr1, chr2, chr3 = "";
                    var enc1, enc2, enc3, enc4 = "";
                    var i = 0;

                    do {
                        chr1 = input.charCodeAt(i++);
                        chr2 = input.charCodeAt(i++);
                        chr3 = input.charCodeAt(i++);

                        enc1 = chr1 >> 2;
                        enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                        enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                        enc4 = chr3 & 63;

                        if (isNaN(chr2)) {
                            enc3 = enc4 = 64;
                        } else if (isNaN(chr3)) {
                            enc4 = 64;
                        }

                        output = output +
                            keyStr.charAt(enc1) +
                            keyStr.charAt(enc2) +
                            keyStr.charAt(enc3) +
                            keyStr.charAt(enc4);
                        chr1 = chr2 = chr3 = "";
                        enc1 = enc2 = enc3 = enc4 = "";
                    } while (i < input.length);

                    return output;
                },

                decode: function (input) {
                    var output = "";
                    var chr1, chr2, chr3 = "";
                    var enc1, enc2, enc3, enc4 = "";
                    var i = 0;

                    // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
                    var base64test = /[^A-Za-z0-9\+\/\=]/g;
                    if (base64test.exec(input)) {
                        window.alert("There were invalid base64 characters in the input text.\n" +
                            "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                            "Expect errors in decoding.");
                    }
                    input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

                    do {
                        enc1 = keyStr.indexOf(input.charAt(i++));
                        enc2 = keyStr.indexOf(input.charAt(i++));
                        enc3 = keyStr.indexOf(input.charAt(i++));
                        enc4 = keyStr.indexOf(input.charAt(i++));

                        chr1 = (enc1 << 2) | (enc2 >> 4);
                        chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                        chr3 = ((enc3 & 3) << 6) | enc4;

                        output = output + String.fromCharCode(chr1);

                        if (enc3 != 64) {
                            output = output + String.fromCharCode(chr2);
                        }
                        if (enc4 != 64) {
                            output = output + String.fromCharCode(chr3);
                        }

                        chr1 = chr2 = chr3 = "";
                        enc1 = enc2 = enc3 = enc4 = "";

                    } while (i < input.length);

                    return output;
                }
            };

            /* jshint ignore:end */
        });
})();;(function () {
    'use strict';
    angular.module("commons.config", [])
        .constant("BASE_CONTEXT", "")
        .constant("LOGIN_URL", "api/login")
        .service("spHelper", ["BASE_CONTEXT", function (BC) {
            this.withContext = function (url) {
                if (url && url.charAt(0) === '/') {
                    url = url.substring(1, url.length - 1);
                }
                return BC + url;
            };

            return this;
        }]);
})();;(function () {
    'use strict';
    angular.module('commons.models', ['models.user']);
})
();;(function () {
    'use strict';
    angular.module('commons.session', [])
        .controller('sessionCtrl', ['$scope', 'AuthenticationService', '$ocLazyLoad', '$state', function ($s, authService, $ocLazyLoad, $state) {
            $s.login = function (username, password) {
                authService.Login(username, password, function (response) {
                    authService.SetCredentials(username, password);
                    $state.go('main');
                });
            };

        }]);
})();;/**
 * Created by Jerico on 22/10/2015.
 */
(function () {
    "use strict";

    angular.module("models.user", [])
        .factory("User", UserModel);

    function UserModel(ActiveResource) {
        function User(attr) {
        }

        User.api.set("services/user");
        User.inherits(ActiveResource);
        return User;
    }

})();;(function () {
    'use strict';

    angular.module('sp.commons', ['commons.config', 'commons.authentication', 'commons.session', 'commons.models']);

})();;/**
 * Created by Jerico on 29/10/2015.
 */
(function () {
    angular.module("sp.core", []);
})();;/**
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

;angular.module('spTemplates', ['main/webapp/app/src/templates/app/home.html']);

angular.module("main/webapp/app/src/templates/app/home.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("main/webapp/app/src/templates/app/home.html",
    "<md-content layout=\"column\" layout-align=\"center center\" layout-wrap><h1>Welcome to Spring-angular application</h1><form ng-submit=\"login(username,password)\"><md-input-container><label for=\"username\">Username</label><input ng-model=\"username\" id=\"username\" type=\"text\"></md-input-container><md-input-container><label for=\"password\">Password</label><input ng-model=\"password\" id=\"password\" type=\"password\"></md-input-container><md-button class=\"md-raised\">Login</md-button></form></md-content>");
}]);
