const express = require("express");
const router = express.Router();

const User = require("../models/user.model");
const UserDao = require("../dao/user.dao");

router
    .route("/")
    .get(async (req, res, next) => {
        let users = await UserDao.find();
        res.json(users);
    });
    // .post(async (req, res, next) => {
    //     let user = new User();
    //     user.fullname = req.body.fullname;
    //     user.gender = req.body.gender;
    //     user.age = req.body.age;

    //     let userSave = await UserDao.save(user);
    //     res.json(userSave);
    // });

router
    .route("/:id")
    .get(async (req, res, next) => {
        let id = req.params.id;
        let user = await UserDao.findById(id);
        res.json(user);
    });
    // .put(async (req, res, next) => {
    //     let id = req.params.id;
    //     let user = await UserDao.findById(id);
    //     user.fullname = req.body.fullname;
    //     user.gender = req.body.gender;
    //     user.age = req.body.age;

    //     let userUpdate = await UserDao.update(user);
    //     res.json(userUpdate);
    // })
    // .delete(async (req, res, next) => {
    //     let id = req.params.id;
    //     let user = await UserDao.findById(id);
    //     let b = await UserDao.delete(user);
    //     res.json({ message: b });
    // });

module.exports = router;
