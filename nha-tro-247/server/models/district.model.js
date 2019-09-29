const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const options = {
    autoCreate: true
};

const districtSchema = new Schema(
    {
        city: { type: Schema.Types.ObjectId, ref: "City" },
        name: { type: String, required: true, maxlength: 30, trim: true }
    },
    options
);

module.exports = mongoose.model("District", districtSchema);
