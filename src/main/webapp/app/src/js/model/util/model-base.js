/**
 * Created by Jerico on 18/10/2015.
 */


angular.module("sp.model.base", [])
    .provider("BaseClass", function () {
        this.context = "";
        this.setContext = function (context) {
            this.content = context ? context : "";
        };
        this.$get = function (modelHelper, ModelCache) {

            modelHelper.setContext(this.context);

            function BaseClass(attributes) {

                var _constructor = this;
                var _prototype = _constructor.prototype;

                privateVariable(_constructor, "primaryKey", "id");

                function cache(instance) {
                    _constructor.cached.cache(instance, _constructor.primaryKey);
                }

                function setUrl(url) {
                    _prototype.url = modelHelper.withHost(url);
                }

                _constructor.new = function (attributes) {
                    var instance = new _constructor(attributes);
                    cache(instance);
                    return instance;
                };

                _constructor.cached = new ModelCache();

                _constructor.$setUrl = setUrl;
            };


            return BaseClass;
        };
    })
    .factory("ModelCache", [function () {

        function ModelCache() {

            privateVariable(this, 'cache', function (instance, primaryKey) {
                if (instance && instance[primaryKey] !== undefined) {
                    this[instance[primaryKey]] = instance;
                }
            });

            privateVariable(this, 'isEmpty', function () {
                return !!(!Object.keys(this).length);
            });

        };

        return ModelCache;
    }]);