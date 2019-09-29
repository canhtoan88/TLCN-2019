var createError = require("http-errors");
var express = require("express");
var path = require("path");
var cookieParser = require("cookie-parser");
var logger = require("morgan");
const mongoose = require("mongoose");
var bodyParser = require("body-parser");

var indexRouter = require("./server/routes/index");
var databaseRouter = require("./server/routes/database");
var citiesApi = require("./server/api/cities.api");
var districtsApi = require("./server/api/districts.api");
var authorizationsApi = require("./server/api/authorizations.api");

var app = express();

// Connect to MongoDB
mongoose.connect("mongodb://localhost:27017/db247hostel", {
    useNewUrlParser: true,
    useUnifiedTopology: true
});
var db = mongoose.connection;
db.on("error", console.error.bind(console, "connection error:"));
db.once("open", function() {
    // we're connected!
    console.log("Database connected");
});

// view engine setup
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "ejs");

app.use(logger("dev"));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, "public")));

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }));

// parse application/json
app.use(bodyParser.json());

app.use("/", indexRouter);
app.use("/database", databaseRouter);
app.use("/api/cities", citiesApi);
app.use("/api/districts", districtsApi);
app.use("/api/authorizations", authorizationsApi);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
    next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get("env") === "development" ? err : {};

    // render the error page
    res.status(err.status || 500);
    res.render("error");
});

app.listen(5000, () => {
    console.log("Server starting..");
});
