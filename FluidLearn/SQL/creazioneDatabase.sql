-- creazione FluidLearn --
-- creazione tabelle corso --
create table corso (
	idcorso serial primary key not null,
	nome varchar(50),
	descrizione text
);

create table UDA (
	idUDA serial primary key not null,
	idcorso integer not null references corso(idcorso),
	nome varchar(50),
	descrizione text,
	dataattivazione timestamp
);

create table nodo (
	idnodo serial primary key not null,
	iduda integer not null references uda(iduda),
	nome varchar(50),
	descrizione text,
	iscomposite boolean
);
