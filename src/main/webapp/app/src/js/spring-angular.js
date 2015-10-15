/**
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

    }]);