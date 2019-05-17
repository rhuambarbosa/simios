CREATE TABLE dna_bank (
	id SERIAL PRIMARY KEY,
	dna_idx text NOT null,
	dna text UNIQUE NOT null,
	simian BOOL NOT null default false,
	creation_date TIMESTAMP NOT NULL default now()
);
CREATE index idx_dna_idx ON dna_bank ((upper(dna_idx)));

CREATE SEQUENCE sq_human_dna;

CREATE SEQUENCE sq_mutant_dna;