DROP DATABASE IF EXISTS epic_quest_db;

CREATE DATABASE epic_quest_db;

CREATE SCHEMA adventure_quest_schema;

CREATE TABLE "user"
(
    id           BIGSERIAL PRIMARY KEY,
    username     VARCHAR(50) UNIQUE,
    password     VARCHAR(255)        NOT NULL,
    email        VARCHAR(100) UNIQUE NOT NULL,
    photo        BYTEA               NOT NULL,
    games_played BIGINT              NOT NULL
);

CREATE TYPE difficulty AS ENUM ('EASY', 'MEDIUM', 'HARD');

CREATE TABLE quest
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(35)  NOT NULL,
    description TEXT         NOT NULL,
    quest_photo BYTEA        NOT NULL,
    difficulty  difficulty   NOT NULL,
    author      VARCHAR(128) NOT NULL,
    UNIQUE (name, author)
);

CREATE TABLE question
(
    id                        BIGSERIAL PRIMARY KEY,
    number_of_question        INT                                            NOT NULL,
    quest_id                  BIGINT REFERENCES quest (id) ON DELETE CASCADE NOT NULL,
    text                      TEXT                                           NOT NULL,
    background_question_photo BYTEA                                          NOT NULL,
    is_last_question          BOOLEAN                                        NOT NULL,
    UNIQUE (number_of_question, quest_id)
);

CREATE TABLE answer
(
    id          BIGSERIAL PRIMARY KEY,
    question_id INTEGER REFERENCES question (id) ON DELETE CASCADE NOT NULL,
    text        TEXT                                               NOT NULL,
    is_correct  BOOLEAN                                            NOT NULL,
    UNIQUE (question_id, text)
);