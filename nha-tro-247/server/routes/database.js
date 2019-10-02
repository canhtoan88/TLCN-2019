const express = require("express");
const router = express.Router();

const citiesCollection = require("../test/defaultDatabase/cityCollection");
const districtsCollection = require("../test/defaultDatabase/districtCollection");
const authorizationCollection = require("../test/defaultDatabase/authorizationCollection");
const userCollection = require("../test/defaultDatabase/userCollection");
const hostelCollection = require("../test/defaultDatabase/hostelCollection");

/* GET home page. */
router.get("/", function(req, res, next) {

    citiesCollection.createDefaultCollection();
    districtsCollection.createDefaultCollection();
    authorizationCollection.createDefaultCollection();
    userCollection.createDefaultCollection();
    hostelCollection.createDefaultCollection();

    res.send("Check server console.log");
});

module.exports = router;
