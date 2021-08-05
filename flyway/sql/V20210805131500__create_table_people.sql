create table if not exists people (
    id BIGSERIAL NOT NULL CONSTRAINT user_pk PRIMARY KEY,
    last_name text,
    first_name text,
    age int
);