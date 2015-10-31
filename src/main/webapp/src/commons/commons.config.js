(function () {
    'use strict';

    angular.module('commons.config', [])
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

})();