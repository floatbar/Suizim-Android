// The file, in which secure APIs are written, (main.js) has not been included in the repository due to security considerations.

const net = require("net");
require("dotenv").config();

const server = net.createServer((socket) => {});
server.listen(process.env.PROTOCOL_PORT, process.env.HOST, () => console.log("TCP server running..."));
