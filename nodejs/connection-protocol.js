// The file, in which secure APIs are written, (main.js) has not been included in the repository due to security considerations.

const net = require("net"); // In order to handle the Suizim clients' or any other client's connections
require("dotenv").config();

const server = net.createServer((socket) => socket.write(Buffer.from("121", "utf-8")));
server.listen(process.env.CUSTOM_PROTOCOL_PORT, process.env.CLOUD_SERVER_HOST, () => console.log("TCP server running..."));
