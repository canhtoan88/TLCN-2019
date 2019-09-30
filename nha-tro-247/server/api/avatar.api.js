const express = require("express");
const router = express.Router();

const AvatarDao = require("../dao/avatar.dao");
const UserDao = require("../dao/user.dao");

router.route("/").get(async (req, res, next) => {
    const avatars = await AvatarDao.find();
    if (!avatars) {
        res.json({});
    } else {
        res.json(avatars);
    }
});

router.route("/:id").get(async (req, res, next) => {
    const id = req.params.id;
    const avatar = await AvatarDao.findById(id);
    if (!avatar) {
        res.json({});
    } else {
        res.json(avatar);
    }
});

router.route("/users/:id").get(async (req, res, next) => {
    const id = req.params.id;
    const user = await UserDao.findById(id);
    if (!user) {
        res.json({});
    } else {
        const avatars = await AvatarDao.findByUser(user);
        if (!avatars) {
            res.json({});
        } else {
            res.json(avatars);
        }
    }
});

module.exports = router;
