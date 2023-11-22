CREATE DATABASE epic_quest_db;

CREATE SCHEMA adventure_quest_schema;

CREATE TABLE "user"
(
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255)        NOT NULL,
    email    VARCHAR(100) UNIQUE NOT NULL
);

INSERT INTO "user"(username, password, email)
VALUES ('Admin', '7777', 'admin@gmail.com');

CREATE TABLE "quest-info"
(
    id BIGSERIAL PRIMARY KEY ,
    name_of_quest VARCHAR(50) UNIQUE NOT NULL ,
    difficult VARCHAR(20) NOT NULL,
    choices quest UNIQUE NOT NULL
);

CREATE TABLE "quest"
(
    id BIGINT NOT NULL ,
    level INT NOT NULL
)