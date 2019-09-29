const express = require("express");
const router = express.Router();

const AuthorizationDao = require("../dao/authorization.dao");

router.route("/").get(async (req, res, next) => {
    const authorizations = await AuthorizationDao.find();
    if (!authorizations) {
        res.json({});
    } else {
        res.json(authorizations);
    }
});

router.route("/:id").get(async (req, res, next) => {
    const id = req.params.id;
    const authorization = await AuthorizationDao.findById(id);
    if (!authorization) {
        res.json({});
    } else {
        res.json(authorization);
    }
});

module.exports = router;
