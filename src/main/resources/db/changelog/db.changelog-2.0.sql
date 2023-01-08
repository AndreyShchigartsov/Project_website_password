--liquibase formatted sql

--changeset andrey:1
ALTER TABLE users
    ADD COLUMN created_at TIMESTAMP;

ALTER TABLE users
    ADD COLUMN modified_at TIMESTAMP;

ALTER TABLE users
    ADD COLUMN created_by VARCHAR(128);

ALTER TABLE users
    ADD COLUMN modified_by VARCHAR(128);

ALTER TABLE chat
    ADD COLUMN created_at TIMESTAMP;

ALTER TABLE chat
    ADD COLUMN modified_at TIMESTAMP;

ALTER TABLE chat
    ADD COLUMN created_by VARCHAR(128);

ALTER TABLE chat
    ADD COLUMN modified_by VARCHAR(128);

ALTER TABLE sms
    ADD COLUMN created_at TIMESTAMP;

ALTER TABLE sms
    ADD COLUMN modified_at TIMESTAMP;

ALTER TABLE sms
    ADD COLUMN created_by VARCHAR(128);

ALTER TABLE sms
    ADD COLUMN modified_by VARCHAR(128);

ALTER TABLE storage
    ADD COLUMN created_at TIMESTAMP;

ALTER TABLE storage
    ADD COLUMN modified_at TIMESTAMP;

ALTER TABLE storage
    ADD COLUMN created_by VARCHAR(128);

ALTER TABLE storage
    ADD COLUMN modified_by VARCHAR(128);