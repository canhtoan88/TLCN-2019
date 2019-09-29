const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const options = {
    autoCreate: true
};

const commentSchema = new Schema(
    {
        house: { type: Schema.Types.ObjectId, ref: "House" },
        user: { type: Schema.Types.ObjectId, ref: "User" },
        content: { type: String, required: true, maxlength: 250, trim: true },
        timeUp: { type: Date, required: true, default: Date.now },
        replyComments: [
            {
                content: {
                    type: String,
                    required: true,
                    maxlength: 250,
                    trim: true
                },
                user: { type: Schema.Types.ObjectId, ref: "User" },
                timeUp: { type: Date, required: true, default: Date.now }
            }
        ]
    },
    options
);

module.exports = mongoose.model("Comment", commentSchema);
