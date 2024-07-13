// The file, in which secure APIs are written, (main.js) has not been included in the repository due to security considerations.

const crypto = require("crypto");

function generate2FAPassword(secret, password) { 
    return crypto.createHmac("sha256", secret).update(password).digest("hex");
}

function verify2FAPassword(secret, password, twoFAPassword) {
    return twoFAPassword === crypto.createHmac("sha256", secret).update(password).digest("hex");
}

module.exports = {
    generate2FAPassword,
    verify2FAPassword
};
