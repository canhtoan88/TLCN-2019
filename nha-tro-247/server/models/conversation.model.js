const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const options = {
    autoCreate: true
};

const conversationSchema = new Schema(
    {
        user1: { type: Schema.Types.ObjectId, ref: "User" },
        user2: { type: Schema.Types.ObjectId, ref: "User" },
        messages: [
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

module.exports = mongoose.model("Conversation", conversationSchema);
