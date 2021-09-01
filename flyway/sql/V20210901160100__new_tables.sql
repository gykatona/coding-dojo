create table if not exists marketplace (
    id BIGSERIAL NOT NULL CONSTRAINT marketplace_pk PRIMARY KEY,
    marketplace text
);

create table if not exists listing_status (
   id BIGSERIAL NOT NULL CONSTRAINT listing_status_pk PRIMARY KEY,
   status text
);