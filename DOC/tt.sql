CREATE DATABASE tt;
USE tt;
CREATE TABLE address
(
	idAddreess           INTEGER NOT NULL,
	intNumber            INTEGER NULL,
	extNumber            INTEGER NULL,
	description          VARCHAR(20) NULL,
	idCP                 INTEGER NOT NULL
);



ALTER TABLE address
ADD PRIMARY KEY (idAddreess,idCP);



CREATE TABLE folio
(
	idFolio              INTEGER NOT NULL,
	numberFolio          VARCHAR(20) NULL
);



ALTER TABLE folio
ADD PRIMARY KEY (idFolio);



CREATE TABLE folioStatus
(
	location             VARCHAR(20) NULL,
	idFolio              INTEGER NOT NULL,
	idStatus             INTEGER NOT NULL
);



ALTER TABLE folioStatus
ADD PRIMARY KEY (idFolio,idStatus);



CREATE TABLE permition
(
	idPermition          INTEGER NOT NULL,
	description          VARCHAR(20) NULL
);



ALTER TABLE permition
ADD PRIMARY KEY (idPermition);



CREATE TABLE persona
(
	idPerson             INTEGER NOT NULL,
	NAME                 VARCHAR(20) NULL,
	lastNameM            VARCHAR(20) NULL,
	lastNameF            VARCHAR(20) NULL,
	birthDay             DATE NULL,
	idPhone              INTEGER NOT NULL,
	email                VARCHAR(20) NULL,
	idAddreess           INTEGER NOT NULL,
	idCP                 INTEGER NOT NULL
);



ALTER TABLE persona
ADD PRIMARY KEY (idPerson,idPhone,idAddreess,idCP);



CREATE TABLE phone
(
	idPhone              INTEGER NOT NULL,
	lada                 INTEGER NULL,
	phone                INTEGER NULL
);



ALTER TABLE phone
ADD PRIMARY KEY (idPhone);



CREATE TABLE postalcode
(
	idCP                 INTEGER NOT NULL,
	number               INTEGER NULL,
	city                 INTEGER NULL,
	town                 VARCHAR(20) NULL,
	country              CHAR(18) NULL
);



ALTER TABLE postalcode
ADD PRIMARY KEY (idCP);



CREATE TABLE role
(
	idRole               INTEGER NOT NULL,
	description          VARCHAR(20) NULL
);



ALTER TABLE role
ADD PRIMARY KEY (idRole);



CREATE TABLE STATUS
(
	idStatus             INTEGER NOT NULL,
	description          VARCHAR(20) NULL,
	active               BOOLEAN NULL
);



ALTER TABLE STATUS
ADD PRIMARY KEY (idStatus);



CREATE TABLE USER
(
	idUser               INTEGER NOT NULL,
	NAME                 VARCHAR(20) NULL,
	lastNameM            VARCHAR(20) NULL,
	lastNameF            VARCHAR(20) NULL,
	birthDay             DATE NULL,
	userName             VARCHAR(20) NULL,
	PASSWORD             VARCHAR(20) NULL,
	email                VARCHAR(20) NULL,
	idPhone              INTEGER NOT NULL,
	idPerson             INTEGER NOT NULL,
	idAddreess           INTEGER NOT NULL,
	idCP                 INTEGER NOT NULL,
	idFolio              INTEGER NOT NULL
);



ALTER TABLE USER
ADD PRIMARY KEY (idUser,idPhone,idPerson,idAddreess,idCP,idFolio);



ALTER TABLE address
ADD FOREIGN KEY R_10 (idCP) REFERENCES postalcode (idCP);



ALTER TABLE folioStatus
ADD FOREIGN KEY R_13 (idFolio) REFERENCES folio (idFolio);



ALTER TABLE folioStatus
ADD FOREIGN KEY R_14 (idStatus) REFERENCES STATUS (idStatus);



ALTER TABLE persona
ADD FOREIGN KEY R_7 (idPhone) REFERENCES phone (idPhone);



ALTER TABLE persona
ADD FOREIGN KEY R_8 (idAddreess, idCP) REFERENCES address (idAddreess, idCP);



ALTER TABLE USER
ADD FOREIGN KEY R_5 (idPerson, idPhone, idAddreess, idCP) REFERENCES persona (idPerson, idPhone, idAddreess, idCP);



ALTER TABLE USER
ADD FOREIGN KEY R_15 (idFolio) REFERENCES folio (idFolio);