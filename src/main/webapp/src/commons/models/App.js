(function () {
    'use strict';
    angular.module('model.app', [])
        .factory('App', AppFactory);
    AppFactory.$inject = ['ActiveResource'];
    function AppFactory(ActiveResource) {
        function App(arg) {

        }
        App.inherits(ActiveResource.Base);
        App.api.set('api/services');
        return App;
    }


})();