/**
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

})();