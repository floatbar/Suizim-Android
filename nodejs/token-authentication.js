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

const crypto = require("crypto");

function generateToken(secret, userName, userSurname, expirationTimeSeconds) {
    const expirationTime = Math.floor(Date.now() / 1000) + expirationTimeSeconds;
    const iv = crypto.randomBytes(16);
    const user = userName + "-" + userSurname + "-" + expirationTime.toString();
    const cipher = crypto.createCipheriv("aes-256-cbc", secret, iv, { authTagLength: 12 });

    let encryptedData = cipher.update(user, "utf-8", "hex");
    encryptedData += cipher.final("hex");

    const token = `${iv.toString("hex")}.${encryptedData}`;

    return token;
}

function verifyToken(secret, userName, userSurname, token) {
    const [iv, encryptedData] = token.split(".");

    const decipher = crypto.createDecipheriv("aes-256-cbc", secret, Buffer.from(iv, "hex"), 
    { authTagLength: 12 });

    let decryptedData = decipher.update(encryptedData, "hex", "utf-8");
    decryptedData += decipher.final("utf-8");

    const [nameOfUser, surnameOfUser, stringValueOfExpirationTime] = decryptedData.split("-");

    return nameOfUser === userName && surnameOfUser === userSurname && parseInt(stringValueOfExpirationTime) > Math.floor(Date.now() / 1000);
}

module.exports = { generateToken,verifyToken };
