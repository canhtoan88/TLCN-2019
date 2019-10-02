const Authorization = require("../../models/authorization.model");
const AuthorizationDao = require("../../dao/authorization.dao");

const authorizations = [
    { name: "Admin" },
    { name: "Employee" },
    { name: "User" },
    { name: "Guest" }
];

module.exports.createDefaultCollection = async () => {
    const authorizationsArray = await AuthorizationDao.find();
    if (authorizationsArray.length <= 0) {
        console.log("Authorization collection is empty.");
        authorizations.map(authorization => {
            const authorizationNew = new Authorization(authorization);
            AuthorizationDao.save(authorizationNew);
        });
        console.log("Default Authorization collection created.");
    } else {
        console.log("Authorization collection existed.");
    }
};
