CREATE TABLE transaction_logs (
    id SERIAL PRIMARY KEY,
    account_id UUID NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);
