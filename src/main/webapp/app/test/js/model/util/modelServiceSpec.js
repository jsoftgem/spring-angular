/**
 * Created by Jerico on 18/10/2015.
 */

describe("ModelHelper", function () {

    it("is defined", function () {
        expect(modelHelper).toBeDefined();
    });

    it("combines 'http:/localhost:8080/sample/' context to the url 'hello-service'", function () {

        modelHelper.setContext("http:/localhost:8080/sample/");

        var result = modelHelper.withHost("hello-service");

        expect(modelHelper.withHost("hello-service")).toEqual("http:/localhost:8080/sample/hello-service");
    });

});


