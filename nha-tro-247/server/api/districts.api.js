const express = require("express");
const router = express.Router();

const DistrictDao = require("../dao/district.dao");
const CityDao = require("../dao/city.dao");

router.route("/").get(async (req, res, next) => {
    const districts = await DistrictDao.find();
    if (!districts) {
        res.json({});
    } else {
        res.json(districts);
    }
});

router.route("/:id").get(async (req, res, next) => {
    const id = req.params.id;
    const district = await DistrictDao.findById(id);
    if (!district) {
        res.json({});
    } else {
        res.json(district);
    }
});

router.route("/cities/:id").get(async (req, res, next) => {
    const id = req.params.id;
    const city = await CityDao.findById(id);
    if (!city) {
        res.json({});
    } else {
        const districts = await DistrictDao.findByCity(city);
        if (!districts) {
            res.json({});
        } else {
            res.json(districts);
        }
    }
});

module.exports = router;
