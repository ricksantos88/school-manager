-- Add email and password columns to users table
ALTER TABLE users ADD COLUMN email VARCHAR(255) NOT NULL;
ALTER TABLE users ADD COLUMN password VARCHAR(255) NOT NULL;
