const express = require("express");
const router = express.Router();

const citiesCollection = require("../test/defaultDatabase/defaultCityCollection");
const districtsCollection = require("../test/defaultDatabase/defaultDistrictCollection");
const authorizationCollection = require("../test/defaultDatabase/defaultAuthorizationCollection");
const userCollection = require("../test/defaultDatabase/defaultUserCollection");
const avatarCollection = require("../test/defaultDatabase/defaultAvatarCollection");

/* GET home page. */
router.get("/", function(req, res, next) {

    citiesCollection.createDefaultCollection();
    districtsCollection.createDefaultCollection();
    authorizationCollection.createDefaultCollection();
    userCollection.createDefaultCollection();
    avatarCollection.createDefaultCollection();

    res.send("Check server console.log");
});

module.exports = router;
