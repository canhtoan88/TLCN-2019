const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const options = {
    autoCreate: true
};

const avatarSchema = new Schema(
    {
        user: { type: Schema.Types.ObjectId, ref: 'User' },
        url: { type: String, required: true, maxlength: 300, trim: true }
    },
    options
);

module.exports = mongoose.model("Avatar", avatarSchema);
