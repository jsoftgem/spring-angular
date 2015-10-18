/**
 * Created by Jerico on 18/10/2015.
 */

angular.module("sp.model.user", [])
    .factory("User", ["BaseClass", function (BaseClass) {
        var user = function User(attr) {
        };

        user.inherits(BaseClass);
        user.$setUrl("user");
        return user;
    }]);