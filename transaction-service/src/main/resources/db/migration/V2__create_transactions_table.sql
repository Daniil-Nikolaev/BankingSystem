ALTER TABLE transactions
    ALTER COLUMN account_sender_id DROP NOT NULL,
ALTER COLUMN account_receiver_id DROP NOT NULL;