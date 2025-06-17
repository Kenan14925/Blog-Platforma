
-- Drop sve postojeće tabele (redosled zbog FK veza)
DROP TABLE IF EXISTS komentar, post, kategorija, korisnik_uloga, uloga, korisnik, tag, post_tag CASCADE;

-- Tabela korisnik
CREATE TABLE korisnik (
    id SERIAL PRIMARY KEY,
    ime VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

-- Tabela uloga
CREATE TABLE uloga (
    id SERIAL PRIMARY KEY,
    naziv VARCHAR(100) NOT NULL UNIQUE
);

-- Povezivanje korisnika i uloge (many-to-many)
CREATE TABLE korisnik_uloga (
    korisnik_id INT REFERENCES korisnik(id) ON DELETE CASCADE,
    uloga_id INT REFERENCES uloga(id) ON DELETE CASCADE,
    PRIMARY KEY (korisnik_id, uloga_id)
);

-- Tabela kategorija
CREATE TABLE kategorija (
    id SERIAL PRIMARY KEY,
    naziv VARCHAR(100) NOT NULL UNIQUE
);

-- Tabela post
CREATE TABLE post (
    id SERIAL PRIMARY KEY,
    naslov VARCHAR(255) NOT NULL,
    sadrzaj TEXT NOT NULL,
    korisnik_id INT REFERENCES korisnik(id) ON DELETE SET NULL,
    kategorija_id INT REFERENCES kategorija(id) ON DELETE SET NULL,
    datum_kreiranja TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela komentar
CREATE TABLE komentar (
    id SERIAL PRIMARY KEY,
    tekst TEXT NOT NULL,
    korisnik_id INT REFERENCES korisnik(id) ON DELETE CASCADE,
    post_id INT REFERENCES post(id) ON DELETE CASCADE,
    datum_kreiranja TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela tag
CREATE TABLE tag (
    id SERIAL PRIMARY KEY,
    naziv VARCHAR(50) NOT NULL UNIQUE
);

-- Povezivanje postova i tagova (many-to-many)
CREATE TABLE post_tag (
    post_id INT REFERENCES post(id) ON DELETE CASCADE,
    tag_id INT REFERENCES tag(id) ON DELETE CASCADE,
    PRIMARY KEY (post_id, tag_id)
);

-- Ubacivanje demo podataka
INSERT INTO korisnik (ime, email) VALUES 
('Ana', 'ana@example.com'),
('Marko', 'marko@example.com'),
('Ivana', 'ivana@example.com');

INSERT INTO uloga (naziv) VALUES ('Admin'), ('Autor'), ('Čitalac');
INSERT INTO korisnik_uloga (korisnik_id, uloga_id) VALUES (1,1), (2,2), (3,3);

INSERT INTO kategorija (naziv) VALUES ('Tehnologija'), ('Putovanja'), ('Zdravlje');

INSERT INTO post (naslov, sadrzaj, korisnik_id, kategorija_id) VALUES 
('Quarkus REST API', 'Uvod u Quarkus...', 2, 1),
('Letovanje u Grčkoj', 'Saveti za letovanje...', 2, 2);

INSERT INTO komentar (tekst, korisnik_id, post_id) VALUES 
('Super tekst!', 1, 1),
('Odlično objašnjeno!', 3, 1),
('Jedva čekam da idem!', 1, 2);

INSERT INTO tag (naziv) VALUES ('Java'), ('Quarkus'), ('Putovanje'), ('Saveti');
INSERT INTO post_tag (post_id, tag_id) VALUES 
(1,1), (1,2), -- Post 1: Java, Quarkus
(2,3), (2,4); -- Post 2: Putovanje, Saveti
