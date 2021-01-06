CREATE TABLE category (
	id SERIAL,
	name VARCHAR NOT NULL
);
ALTER TABLE category ADD CONSTRAINT category_pk PRIMARY KEY (id);

INSERT INTO category (name) VALUES('Lazer');
INSERT INTO category (name) VALUES('Alimentação');
INSERT INTO category (name) VALUES('Supermercado');
INSERT INTO category (name) VALUES('Farmácia');
INSERT INTO category (name) VALUES('Outros'); 