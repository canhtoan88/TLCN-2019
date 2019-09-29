const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const options = {
    autoCreate: true
};

const imageSchema = new Schema(
    {
        house: { type: Schema.Types.ObjectId, ref: "House" },
        url: { type: String, required: true, maxlength: 250, trim: true }
    },
    options
);

module.exports = mongoose.model("Image", imageSchema);
