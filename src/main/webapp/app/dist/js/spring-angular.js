/**
 * Created by Jerico on 15/10/2015.
 */
angular.module('spAuthentication',[])
    .factory('AuthenticationService', ['Base64', '$http', '$cookieStore', '$rootScope', '$timeout', "spHelper", "LOGIN_URL",
        function (Base64, $http, $cookieStore, $rootScope, $timeout, spH, LURL) {
            var service = {};

            service.Login = function (username, password, callback) {

                /* Dummy authentication for testing, uses $timeout to simulate api call
                 �������������----------------------------------------------
                 $timeout(function () {
                 var response = {success: username === 'test' && password === 'test'};
                 if (!response.success) {
                 response.message = 'Username or password is incorrect';
                 }
                 callback(response);
                 }, 1000);
                 */

                /* Use this for real authentication
                 �������������----------------------------------------------*/
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
    });/**
 * Created by Jerico on 15/10/2015.
 */
angular.module("spConfig", [])
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
    }]);;/**
 * Created by Jerico on 15/10/2015.
 */
angular.module("spSession", [])
    .controller("sessionCtrl", ["$scope", "AuthenticationService", function ($s, authService) {

        $s.login = function (username, password) {
            authService.Login(username, password, function (response) {
                alert("login successful!");
                authService.SetCredentials(username, password);
            });
        }
    }]);;/**
 * Created by Jerico on 14/10/2015.
 */
angular.module("spApp", ["spTemplates", "fluid.webComponents", "spConfig", "spAuthentication", "spSession", "ui.router", "ngCookies"])
    .config(["$stateProvider", "$urlRouterProvider", function (sp, urp) {
        urp.otherwise("/");
        sp.state("home", {
            url: "/",
            templateUrl: "templates/home.html",
            controller: "sessionCtrl"
        });
    }])
    .controller("mainCtrl", ["$scope", function ($s) {

    }]);;angular.module('spTemplates', ['templates/home.html']);

angular.module("templates/home.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("templates/home.html",
    "<div class=\"container center-in\"><div class=\"jumbotron\"><h1>Welcome to Spring-angular project</h1><h5>Version 1.0</h5><h5>Concept by Jerico de Guzman</h5><form role=\"form\" class=\"login-form\" ng-submit=\"login(username,password)\"><h2 class=\"text-primary\">Login</h2><div class=\"col-lg-6 form-group form-group-lg\"><label class=\"control-label\" for=\"usernameField\">Username</label><input class=\"form-control\" id=\"usernameField\" ng-model=\"username\"></div><div class=\"col-lg-6 form-group form-group-lg\"><label class=\"control-label\" for=\"passwordField\">Password</label><input type=\"password\" class=\"form-control\" id=\"passwordField\" ng-model=\"password\"></div><button class=\"btn btn-info\" type=\"submit\">Submit</button></form></div></div>");
}]);
