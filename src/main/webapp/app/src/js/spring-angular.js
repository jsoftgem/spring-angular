/**
 * Created by Jerico on 14/10/2015.
 */
(function () {
    'use strict';
    angular.module("spApp", ["spTemplates", "oc.lazyLoad", "sp.core", "spConfig", "spAuthentication", "spSession", "ui.router", "ngCookies", "ActiveResource"])
        .config(["$stateProvider", "$urlRouterProvider", function (sp, urp) {
            urp.otherwise("/");
            sp.state("spa", {
                url: "/",
                templateProvider: function ($templateCache) {
                    return $templateCache.get("main/webapp/app/src/templates/app/home.html");
                },
                controller: "sessionCtrl"
            });
        }]);
})();

