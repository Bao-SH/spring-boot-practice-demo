ALTER TABLE books
ADD COLUMN status VARCHAR(50) DEFAULT 'AVAILABLE';
ALTER TABLE books
ADD COLUMN user_id VARCHAR(50);
