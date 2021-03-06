CREATE TABLE if NOT EXISTS people_to_csv
(
    id BIGSERIAL NOT NULL CONSTRAINT people_to_csv_pk PRIMARY KEY,
    last_name text,
    first_name text,
    age int
);

INSERT INTO people_to_csv (last_name, first_name, age)
VALUES ('Teszt', 'Elek', 30),
       ('Nyomo', 'Réka', 21),
       ('Para', 'Zita', 32),
       ('Patta', 'Nóra', 43),
       ('Pár', 'Zoltán', 51),
       ('Pop', 'Simon', 12),
       ('Remek', 'Elek', 35),
       ('Beviz', 'Elek', 8),
       ('Szalmon', 'Ella', 11),
       ('Techno', 'Kolos', 83),
       ('Trab', 'Antal', 92),
       ('Ultra', 'Viola', 43),
       ('Am', 'Erika', 12),
       ('Bac', 'Ilus', 23),
       ('Citad', 'Ella', 31),
       ('Dil', 'Emma', 72),
       ('Eszte', 'Lenke', 81),
       ('Feles', 'Elek', 68),
       ('Füty', 'Imre', 34),
       ('Git', 'Áron', 99),
       ('Har', 'Mónika', 1),
       ('Heu', 'Réka', 52),
       ('Hü', 'Jenő', 44),
       ('Kukor', 'Ica', 22),
       ('Kala', 'Pál', 59),
       ('Külö', 'Nóra', 79),
       ('Körm', 'Ödön', 33),
       ('Kér', 'Ede', 54),
       ('Major', 'Anna', 82),
       ('Meg', 'Győző', 26);
