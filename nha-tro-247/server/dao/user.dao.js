const Model = require("../models/user.model");

module.exports = {
    find: () => {
        return new Promise((resolve, reject) => {
            Model.find({}).exec((err, results) => {
                if (err) return reject(null);
                return resolve(results);
            });
        });
    },
    findById: id => {
        return new Promise((resolve, reject) => {
            Model.findById(id).exec((err, result) => {
                if (err) return reject(null);
                return resolve(result);
            });
        });
    },
    save: model => {
        return new Promise((resolve, reject) => {
            model.save((err, result) => {
                if (err) return reject(null);
                return resolve(result);
            });
        });
    },
    update: model => {
        return new Promise((resolve, reject) => {
            Model.findByIdAndUpdate(
                model._id,
                {
                    fullname: model.fullname,
                    gender: model.gender,
                    age: model.age
                },
                { new: true },
                (err, result) => {
                    if (err) return reject(null);
                    return resolve(result);
                }
            );
        });
    },
    delete: model => {
        return new Promise((resolve, reject) => {
            Model.findByIdAndDelete(model.id, err => {
                if (err) return reject(false);
                return resolve(true);
            });
        });
    }
};
