CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS transactions(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    account_sender_id UUID NOT NULL,
    account_receiver_id UUID NOT NULL,
    date TIMESTAMP NOT NULL,
    currency varchar(30) NOT NULL,
    amount numeric(10,2) NOT NULL,
    status varchar(255) NOT NULL,
    description varchar(255)
);