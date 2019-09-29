const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const options = {
    autoCreate: true
};

const authorizationSchema = new Schema(
    {
        name: { type: String, required: true, maxlength: 20, trim: true }
    },
    options
);

module.exports = mongoose.model("Authorization", authorizationSchema);
