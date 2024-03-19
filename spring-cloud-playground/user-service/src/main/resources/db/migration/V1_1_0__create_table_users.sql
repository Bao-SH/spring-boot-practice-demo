CREATE TABLE IF NOT EXISTS users (
    id uuid NOT NULL PRIMARY KEY,
    name varchar(20),
    email varchar(50)
    );