CREATE TABLE dna_bank (
	id numeric PRIMARY KEY,
	dna text NOT null,
	simian BOOL NOT null default false,
	creation_date TIMESTAMP NOT NULL default now()
);

CREATE SEQUENCE sq_human_dna;

CREATE SEQUENCE sq_mutant_dna;