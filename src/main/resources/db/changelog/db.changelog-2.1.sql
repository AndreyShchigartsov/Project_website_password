--liquibase formatted sql

--changeset andrey:1
CREATE TABLE IF NOT EXISTS revision
(
    id SERIAL PRIMARY KEY ,
    timestamp BIGINT NOT NULL
);

--changeset andrey:2
CREATE TABLE IF NOT EXISTS users_aud
(
    id BIGINT,
    rev INT REFERENCES revision (id),
    revtype SMALLINT ,
    login VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL,
    password VARCHAR(128) NOT NULL,
    birth_day DATE,
    role VARCHAR(20) NOT NULL
);

--changeset andrey:3
CREATE TABLE IF NOT EXISTS storage_aud
(
    id BIGINT,
    rev INT REFERENCES revision (id),
    revtype SMALLINT ,
    userId BIGSERIAL NOT NULL ,
    password VARCHAR(128) NOT NULL ,
    website VARCHAR(128) NOT NULL ,
    comment VARCHAR(255) NOT NULL
);

--changeset andrey:4
CREATE TABLE IF NOT EXISTS sms_aud
(
    id BIGINT,
    rev INT REFERENCES revision (id),
    revtype SMALLINT ,
    chatId BIGSERIAL NOT NULL ,
    sms VARCHAR(255),
    time DATE NOT NULL ,
    login_sender VARCHAR(255) NOT NULL
);