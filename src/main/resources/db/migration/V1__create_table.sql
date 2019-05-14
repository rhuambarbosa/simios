CREATE TABLE dna_bank (
	id numeric PRIMARY KEY,
	dna_idx text NOT null,
	dna text NOT null,
	simian BOOL NOT null default false,
	creation_date TIMESTAMP NOT NULL default now()
);
CREATE index idx_dna_idx ON dna_bank ((upper(dna_idx)));

CREATE SEQUENCE sq_human_dna;

CREATE SEQUENCE sq_mutant_dna;