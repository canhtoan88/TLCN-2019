const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const options = {
    autoCreate: true
};

const videoSchema = new Schema(
    {
        house: { type: Schema.Types.ObjectId, ref: "House" },
        url: { type: String, required: true, maxlength: 250, trim: true }
    },
    options
);

module.exports = mongoose.model("Video", videoSchema);
