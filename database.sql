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
-- фільтр что би смотрел если current quest num > this quest num - redirect to you.are.cheater.jsp

CREATE TABLE quest
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    quest_photo BYTEA        NOT NULL,
    difficulty  difficulty   NOT NULL,
    author      VARCHAR(128) NOT NULL,
    UNIQUE (name, author)
);

CREATE TABLE question
(
    id                        BIGSERIAL PRIMARY KEY,
    number_of_question        INT                          NOT NULL,
    quest_id                  BIGINT REFERENCES quest (id) NOT NULL,
    text                      TEXT                         NOT NULL,
    background_question_photo BYTEA                        NOT NULL,
    last_question             BOOLEAN                      NOT NULL
);

CREATE TABLE answers
(
    id          SERIAL PRIMARY KEY,
    question_id INTEGER REFERENCES question (id) NOT NULL,
    text        TEXT                             NOT NULL,
    is_correct  BOOLEAN                          NOT NULL
);

-- INSERT INTO question(number_of_question, quest_id, text, background_question_photo, last_question) VALUES (1,1,'ok',);
-- INSERT INTO answers(question_id, text, is_correct) VALUES (1,'hello',true)