const express = require("express");
const router = express.Router();

const CityDao = require("../dao/city.dao");

router.route("/").get(async (req, res, next) => {
    const cities = await CityDao.find();
    if (!cities) {
        res.json({});
    } else {
        res.json(cities);
    }
});

router.route("/:id").get(async (req, res, next) => {
    const id = req.params.id;
    const city = await CityDao.findById(id);
    if (!city) {
        res.json({});
    } else {
        res.json(city);
    }
});

module.exports = router;
