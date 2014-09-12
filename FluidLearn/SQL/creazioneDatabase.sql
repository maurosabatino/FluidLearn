-- creazione FluidLearn --
-- creazione tabelle corso --

DROP TABLE IF EXISTS corso cascade;
create table corso (
	idcorso serial primary key not null,
	nome varchar(50),
	descrizione text
);

DROP TABLE IF EXISTS UDA cascade;
create table UDA (
	idUDA serial primary key not null,
	idcorso integer not null references corso(idcorso) on delete cascade,
	nome varchar(50),
	descrizione text,
	dataattivazione timestamp
);

DROP TABLE IF EXISTS nodo cascade;
create table nodo (
	idnodo serial primary key not null,
	iduda integer not null references uda(iduda) on delete cascade,
	nome varchar(50),
	descrizione text,
	iscomposite boolean
);

DROP TABLE IF EXISTS nodoComposto cascade;
create table nodoComposto(
	idNodoComposto serial primary key not null,
	idNodoPadre integer references nodo(idNodo) on delete cascade,
	idNodoFiglio integer references nodo(idNodo) on delete cascade
);

-- creazione tabelle attori --
DROP TABLE IF EXISTS utente cascade;
create table utente (
	idUtente serial primary key not null,
	username varchar(16) not null,
	password varchar(32) not null
);

DROP TABLE IF EXISTS Partecipante cascade;
create table Partecipante (
	idPartecipante serial primary key not null,
	idUtente integer references utente(idUtente) on delete cascade,
	idcorso integer references corso(idcorso) on delete cascade,
	ruolo varchar(45)
);

-- creazione tabelle contributo --

DROP TABLE IF EXISTS plugin cascade;
create table plugin(
	idPlugin serial primary key not null,
	nome varchar(45)
);

DROP TABLE IF EXISTS Post cascade;
create table Post( 
	idPost serial primary key not null,
	idPartecipante integer references Partecipante(idPartecipante) on delete cascade,
	idUDA integer references UDA(idUDA) on delete cascade,
	idNodo integer references nodo(idNodo) on delete cascade,
	visibility integer,
	testo text,
	data timestamp,
	deadline timestamp,
	idPlugin integer references plugin(idplugin) on delete cascade
);

DROP TABLE IF EXISTS Commento cascade;
create table Commento(
	idCommento serial primary key not null,
	idPost integer references post(idPost) on delete cascade,
	idPartecipante integer references Partecipante(idPartecipante) on delete cascade,
	testo text,
	data timestamp,
	idPlugin integer references plugin(idPlugin)on delete cascade
);

DROP TABLE IF EXISTS allegatiPost cascade;
create table allegatiPost(
	idAllegatiPost serial primary key not null,
	idPost integer references post(idPost) on delete cascade,
	url text,
	nome text
);

DROP TABLE IF EXISTS allegatiCommento cascade;
create table allegatiCommento(
	idAllegatiCommento serial primary key not null,
	idCommento integer references commento(idCommento) on delete cascade,
	url text,
	nome text
);