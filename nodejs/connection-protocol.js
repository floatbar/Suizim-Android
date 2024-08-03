// The file, in which secure APIs are written, (main.js) has not been included in the repository due to security considerations.

const net = require("net"); // TCP server creator for handling Suizim's or any other client's connections
require("dotenv").config();

const server = net.createServer(socket);
server.listen(process.env.CUSTOM_PROTOCOL_PORT, process.env.CLOUD_SERVER_HOST, () => console.log("TCP server running..."));
