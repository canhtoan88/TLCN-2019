// Define dependences
const http = require("http");
const app = require("./app");

// Define PORT
const port = 5000;

// Create a server
const server = http.createServer(app);

// Listen a port
server.listen(port, () => {
    console.log(`Server listened port ${port}`);
});
