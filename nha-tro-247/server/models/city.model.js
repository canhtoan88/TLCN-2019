const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const options = {
    autoCreate: true
};

const citySchema = new Schema(
    {
        name: { type: String, required: true, maxlength: 30, trim: true }
    },
    options
);

module.exports = mongoose.model("City", citySchema);
