const Model = require("../models/user.model");

module.exports = {
    find: () => {
        return new Promise((resolve, reject) => {
            Model.find({})
                .populate("authorization")
                .exec((err, results) => {
                    if (err) return reject(null);
                    return resolve(results);
                });
        }).catch(() => null);
    },
    findById: id => {
        return new Promise((resolve, reject) => {
            Model.findById(id)
                .populate("authorization")
                .exec((err, result) => {
                    if (err) return reject(null);
                    return resolve(result);
                });
        }).catch(() => null);
    },
    findByName: name => {
        return new Promise((resolve, reject) => {
            Model.find({ name: name })
                .populate("authorization")
                .exec((err, result) => {
                    if (err) return reject(null);
                    return resolve(result);
                });
        }).catch(() => null);
    },
    findOneByEmail: email => {
        return new Promise((resolve, reject) => {
            Model.find({ email: email })
                .populate("authorization")
                .exec((err, result) => {
                    if (err) return reject(null);
                    return resolve(result);
                });
        }).catch(() => null);
    },
    findOneByPhone: phone => {
        return new Promise((resolve, reject) => {
            Model.find({ phone: phone })
                .populate("authorization")
                .exec((err, result) => {
                    if (err) return reject(null);
                    return resolve(result);
                });
        }).catch(() => null);
    },
    save: model => {
        return new Promise((resolve, reject) => {
            model.save((err, result) => {
                if (err) return reject(null);
                return resolve(result);
            });
        }).catch(() => null);
    },
    update: model => {
        return new Promise((resolve, reject) => {
            Model.findByIdAndUpdate(
                model._id,
                {

                },
                { new: true },
                (err, result) => {
                    if (err) return reject(null);
                    return resolve(result);
                }
            );
        }).catch(() => null);
    },
    addAvatar: (user, avatarUrl) => {
        let avatarUrls = user.avatars;
        avatarUrls.push(avatarUrl);

        return new Promise((resolve, reject) => {
            Model.findByIdAndUpdate(
                user._id,
                {
                    avatars: avatarUrls
                },
                { new: true },
                (err, result) => {
                    if (err) return reject(null);
                    return resolve(result);
                }
            );
        }).catch(() => null);
    },
    delete: model => {
        return new Promise((resolve, reject) => {
            Model.findByIdAndDelete(model.id, err => {
                if (err) return reject(false);
                return resolve(true);
            });
        }).catch(() => false);
    }
};
