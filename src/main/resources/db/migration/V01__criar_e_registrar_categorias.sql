SET SCHEMA 'algamoney';

CREATE TABLE category (
	id SERIAL,
	name VARCHAR NOT NULL
);

INSERT INTO category (name) VALUES('Lazer');
INSERT INTO category (name) VALUES('Alimentação');
INSERT INTO category (name) VALUES('Supermercado');
INSERT INTO category (name) VALUES('Farmácia');
INSERT INTO category (name) VALUES('Outros'); 