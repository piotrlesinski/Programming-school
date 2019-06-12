CREATE DATABASE IF NOT EXISTS Szkola_programowania;

CREATE TABLE IF NOT EXISTS exercise (
    id INT AUTO_INCREMENT,
    title VARCHAR(255),
    description TEXT,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS user_group (
    id INT AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS users(
    id INT AUTO_INCREMENT, 
    userName VARCHAR(256),
    email VARCHAR(255) UNIQUE, 
    password CHAR(60),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS solution(
    id INT AUTO_INCREMENT,
    created DATETIME,
    updated DATETIME,
    description TEXT,
    exercise_id INT,
    user_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(exercise_id) REFERENCES exercise(id)
);
