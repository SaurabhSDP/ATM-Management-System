-- ATM system database setup
CREATE DATABASE IF NOT EXISTS atm_system;
USE atm_system;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    card_number VARCHAR(20) UNIQUE,
    pin VARCHAR(10),
    name VARCHAR(50),
    balance DOUBLE DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    type VARCHAR(20),
    amount DOUBLE,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Sample user
INSERT INTO users (card_number, pin, name, balance)
VALUES ('12345', '1234', 'Demo User', 5000.00)
ON DUPLICATE KEY UPDATE name = VALUES(name);
