CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE transactions (
    id                   UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    account_sender_id    UUID,
    account_receiver_id  UUID,           -- nullable теперь
    date                 TIMESTAMP   NOT NULL,
    currency             VARCHAR(10) NOT NULL,
    amount               NUMERIC(19,2) NOT NULL,
    status               VARCHAR(10) NOT NULL,
    description          TEXT
);