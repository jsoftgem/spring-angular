/**
 * Created by Jerico on 18/10/2015.
 */

var BaseClass, User, modelHelper;

beforeEach(module("sp.models"));

beforeEach(inject(function (_BaseClass_, _User_, _modelHelper_) {
    BaseClass = _BaseClass_;
    User = _User_;
    modelHelper = _modelHelper_;
}));
