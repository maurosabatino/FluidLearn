create table percorsoDA (
	idpercorsoDA serial primary key not null,
	iduda1 integer not null references uda(iduda) on delete cascade,
	iduda2 integer not null references uda(iduda) on delete cascade
);