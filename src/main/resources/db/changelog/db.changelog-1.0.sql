--liquibase formatted sql

--changeset andrey:1
CREATE TABLE users
(
    id BIGSERIAL primary key ,
    login VARCHAR(128) NOT NULL UNIQUE ,
    email VARCHAR(128) NOT NULL ,
    password VARCHAR(128),
    birth_day DATE,
    role VARCHAR(20) NOT NULL
);

--changeset andrey:2
CREATE TABLE storage
(
    id BIGSERIAL primary key ,
    userId BIGSERIAL NOT NULL ,
    password VARCHAR(128) NOT NULL ,
    website VARCHAR(128) NOT NULL ,
    comment VARCHAR(255) NOT NULL,
    FOREIGN KEY (userId) REFERENCES users (id)
);

--changeset andrey:3
CREATE TABLE chat
(
    id BIGSERIAL primary key ,
    title VARCHAR(128) NOT NULL UNIQUE ,
    time_creating DATE NOT NULL ,
    login_creating VARCHAR(128) NOT NULL ,
    type VARCHAR(20) NOT NULL
);

--changeset andrey:4
CREATE TABLE sms
(
    id BIGSERIAL primary key ,
    chatId BIGSERIAL NOT NULL ,
    sms VARCHAR(255),
    time DATE NOT NULL ,
    login_sender VARCHAR(128) NOT NULL,
    FOREIGN KEY (chatId) REFERENCES chat (id)
);

--changeset andrey:5
CREATE TABLE user_chat
(
    id BIGSERIAL PRIMARY KEY ,
    user_id BIGSERIAL REFERENCES users (id),
    chat_id BIGSERIAL REFERENCES chat (id),
    created_at timestamp
);