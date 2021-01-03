SET SCHEMA 'algamoney';

CREATE TABLE person (
	id SERIAL,
	name VARCHAR NOT NULL,
	address VARCHAR,
	address_number VARCHAR,
	complement VARCHAR,
	district VARCHAR,
	zipcode VARCHAR,
	city VARCHAR,
	state VARCHAR,
	active boolean DEFAULT TRUE
);

INSERT INTO person (name, address, address_number,complement, district, zipcode, city, state, active) VALUES
 				('Emerson', 'Rua 600', '104', 'Bloco F', 'Santa maria','70583400', 'Brasília', 'DF', true ),
 				('Lu�sa', 'Rua 600', '104', 'Bloco F', 'Santa maria', 'Brasília','70583400', 'DF', false ),
 				('Lucas Oliveira', 'Rua 605', '204', 'Bloco H', 'Gama', 'Brasília','70583450', 'DF', false );