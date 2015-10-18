/**
 * Created by Jerico on 18/10/2015.
 */

'use strict';

describe("sp.model.base", function () {
    describe("Inheritance", function () {

        beforeEach(function () {
            User.inherits(SomeBaseClass);

            function SomeBaseClass(attributes) {
                var _constructor = this;
                var _prototype = _constructor.prototype;

                _constructor.new = function (attributes) {
                    var instance = new _constructor(attributes);
                    return instance;
                };

                _prototype.$setUrl = angular.noop;
            };
        });


        it("adds method to the child class", function () {
            expect(User.new).toBeDefined();
        });

        it("adds method to the instance", function () {
            var user = User.new({});
            expect(user.$setUrl).toBeDefined();
        });


    });
});


describe("ModelCache", function () {

    it("adds a cache to the model", function () {
        expect(User.cached).toEqual({});
    });

    it("adds new instances to the cache when they are created", function () {
        var user = User.new({id: 1});
        expect(User.cached[1]).toEqual(user);
    });

    it("is empty if it contains no instances", function () {
        expect(User.cached.isEmpty()).toEqual(true);
    });

    it("is not empty if it contains instances", function () {
        var user = User.new({id: 1});
        expect(User.cached.isEmpty()).toEqual(false);
    });

});