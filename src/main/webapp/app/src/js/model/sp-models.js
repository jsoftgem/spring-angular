/**
 * Created by Jerico on 18/10/2015.
 */

'use strict';

Function.prototype.inherits = function (baseClass) {
    var _constructor = this;
    _constructor = baseClass.apply(_constructor);
};


function privateVariable(object, name, value) {
    var val;

    Object.defineProperty(object, name, {
        enumerable: false,
        configurable: false,
        get: function () {
            return val;
        },
        set: function (newValue) {
            val = newValue;
        }
    });

    if (value !== undefined) object[name] = value;

};

angular.module("sp.models", ["sp.model.base", "sp.model.user"])
    .config(["BaseClassProvider", function (BaseClassProvider) {
        BaseClassProvider.setContext("http://localhost:8080/spring-angular/");
    }])
    .service("modelHelper", [function () {

        this.setContext = function (context) {
            this.context = context;
        };

        this.withHost = function (url) {
            if (this.context) {
                if (url && url.charAt(0) === '/') {
                    url = url.substring(1, url.length - 1);
                }
                return this.context + url;
            } else {
                return url;
            }
        };

        return this;
    }]);
