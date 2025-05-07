CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS transactions_log(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    account_id UUID NOT NULL,
    date TIMESTAMP NOT NULL,
    amount numeric(10,2) NOT NULL,
    type varchar(255) NOT NULL
);