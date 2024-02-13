CREATE TABLE IF NOT EXISTS post (
    id serial primary key,
    name text,
    description text,
    link text UNIQUE,
    created TIMESTAMP
);