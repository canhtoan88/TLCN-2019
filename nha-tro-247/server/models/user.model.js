const mongoose = require("mongoose");
const Schema = mongoose.Schema;

var options = {
    autoCreate: true
};

const userSchema = new Schema(
    {
        fullname: String,
        gender: Boolean,
        age: Number
    },
    options
);

const User = mongoose.model("User", userSchema);
module.exports = User;
