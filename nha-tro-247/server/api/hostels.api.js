const express = require("express");
const router = express.Router();

const Hostel = require("../models/hostel.model");
const HostelDao = require("../dao/hostel.dao");

router
    .route("/")
    .get(async (req, res, next) => {
        let hostels = await HostelDao.find();
        res.json(hostels);
    });

router
    .route("/:id")
    .get(async (req, res, next) => {
        let id = req.params.id;
        let hostel = await HostelDao.findById(id);
        res.json(hostel);
    });

module.exports = router;
