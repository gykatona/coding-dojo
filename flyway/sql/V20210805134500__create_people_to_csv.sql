create table if not exists people_to_csv (
    id BIGSERIAL NOT NULL CONSTRAINT people_to_csv_pk PRIMARY KEY,
    last_name text,
    first_name text,
    age int
);
