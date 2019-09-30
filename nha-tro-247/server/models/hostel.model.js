const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const options = {
    autoCreate: true
};

const hostelSchema = new Schema(
    {
        user: { type: Schema.Types.ObjectId, ref: "User" },
        city: { type: Schema.Types.ObjectId, ref: "City" },
        district: { type: Schema.Types.ObjectId, ref: "District" },
        street: { type: String, required: true, maxlength: 50, trim: true },
        location: {
            type: {
                type: String,
                required: true,
                trim: true,
                default: "Point"
            },
            coordinates: []
        },
        houseNumber: {
            type: String,
            required: true,
            maxlength: 20,
            trim: true
        },
        price: { type: Number, required: true, min: 0 },
        space: { type: Number, required: true, min: 0 },
        title: { type: String, required: true, maxlength: 100, trim: true },
        description: {
            type: String,
            required: true,
            maxlength: 2500,
            trim: true
        },
        isRented: { type: Boolean, required: true, default: false },
        isCensored: { type: Number, required: true, default: 0 },
        timeUp: { type: Date, required: true, default: Date.now }
    },
    options
);

module.exports = mongoose.model("Hostel", hostelSchema);
