const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const options = {
    autoCreate: true
};

const rateSchema = new Schema(
    {
        house: { type: Schema.Types.ObjectId, ref: "House" },
        user: { type: Schema.Types.ObjectId, ref: "User" },
        stars: { type: Number, required: true, min: 0, max: 5, default: 0 },
        timeUp: { type: Date, required: true, default: Date.now }
    },
    options
);

module.exports = mongoose.model("Rate", rateSchema);
