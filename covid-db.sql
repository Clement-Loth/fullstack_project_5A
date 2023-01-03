DROP TABLE IF EXISTS CENTERS CASCADE;
DROP TYPE IF EXISTS STATE_CENTER CASCADE;
CREATE TYPE STATE_CENTER as ENUM('Open','Closed','Disable');
CREATE TABLE CENTERS(
	center_id bigserial PRIMARY KEY,
	name varchar(50) not NULL,
	location varchar(150) not NULL,
	state STATE_CENTER not NULL,
	city varchar(50) not NULL
);
INSERT INTO CENTERS (name, location, state, city)
VALUES ('Centre hospitalier de Moulins-Yzeure', '10, av. du Général de Gaulle', 'Open','Moulins-Yzeure');
INSERT INTO CENTERS (name, location, state, city)
VALUES ('Centre de vaccination de Metz', 'Boulevard Saint-Symphorien', 'Open','Longeville-lès-Metz');
INSERT INTO CENTERS (name, location, state, city)
VALUES ('Pharmacie de Serre', '20 Rue de Serre', 'Open','Pagny-sur-Moselle');

DROP TABLE IF EXISTS USERS CASCADE;
CREATE TABLE USERS(
	user_id bigserial PRIMARY KEY,
	firstName varchar(30) not NULL,
	lastName varchar(30)not NULL,
	email varchar(50),
	phone varchar(20)
);
INSERT INTO USERS (firstName, lastName, email, phone)
VALUES ('Carl', 'JUAN', 'aaa@bbb.fr', '01 01 01 01 01'); 
INSERT INTO USERS (firstName, lastName, email, phone)
VALUES ('Stéphanie', 'MONAK', 'bbb@ccc.com', '23 23 23 23 23');
INSERT INTO USERS (firstName, lastName, email, phone)
VALUES ('Goefrey', 'SERNOUS', 'ccc@ddd.com', '34 34 34 34 34');

DROP TABLE IF EXISTS ROLES CASCADE;
CREATE TABLE ROLES(
	role_id bigserial PRIMARY KEY,
	name varchar(30) not NULL
);

DROP TYPE IF EXISTS STATE_CLIENT CASCADE;
CREATE TYPE STATE_CLIENT as ENUM('Treated','Waiting','Cancelled');
DROP TABLE IF EXISTS APPOINTMENTS;
CREATE TABLE APPOINTMENTS (
	appointment_id bigserial PRIMARY KEY,
	firstName varchar(30) not NULL,
	lastName varchar(30) not NULL,
	phone varchar(15) not NULL,
	email varchar(50) not NULL,
	state STATE_CLIENT not NULL,
	date timestamp not NULL,
	center_id bigserial,
	FOREIGN KEY(center_id) REFERENCES CENTERS(center_id),
	doctor_id bigserial,
	FOREIGN KEY(doctor_id) REFERENCES USERS(user_id)
);
INSERT INTO APPOINTMENTS (firstName, lastName, phone, email, state, date, center_id, doctor_id)
VALUES ('Axel', 'LOTH', '00 00 00 00 00', 'actual.email@gmail.com', 'Treated', '2018-10-21 20:31:40', 1, 2);
INSERT INTO APPOINTMENTS (firstName, lastName, phone, email, state, date, center_id, doctor_id)
VALUES ('Gérard', 'BOUCHARD', '11 11 11 11 11', 'true-actual.email@gmail.com', 'Waiting', '2023-09-11 07:54:37', 3, 3);
INSERT INTO APPOINTMENTS (firstName, lastName, phone, email, state, date, center_id, doctor_id)
VALUES ('Antoine', 'DANIEL', '22 22 22 22 22', 'not-a-true-email@hotmail', 'Cancelled', '2019-08-12 17:07:22', 1, 1);
