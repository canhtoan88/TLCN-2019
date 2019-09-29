const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const options = {
    autoCreate: true
};

const employeeSchema = new Schema(
    {
        user: { type: Schema.Types.ObjectId, ref: "User" },
        salary: { type: Number, required: true, min: 0, default: 0 },
        timeStart: { type: Date, required: true, default: Date.now }
    },
    options
);

module.exports = mongoose.model("Employee", employeeSchema);
