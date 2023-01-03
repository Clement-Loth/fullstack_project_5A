DROP TABLE IF EXISTS CENTERS CASCADE;
DROP TYPE IF EXISTS STATE_CENTER CASCADE;
CREATE TYPE STATE_CENTER as ENUM('Open','Closed','Disable');
CREATE TABLE CENTERS(
	center_id bigint PRIMARY KEY,
	name varchar(50) not NULL,
	location varchar(150) not NULL,
	state STATE_CENTER not NULL,
	city varchar(50) not NULL
);
INSERT INTO CENTERS (center_id,name, location, state, city)
VALUES (1,'Centre hospitalier de Moulins-Yzeure', '10, av. du Général de Gaulle', 'Open','Moulins-Yzeure');
INSERT INTO CENTERS (center_id,name, location, state, city)
VALUES (2,'Centre de vaccination de Metz', 'Boulevard Saint-Symphorien', 'Open','Longeville-lès-Metz');
INSERT INTO CENTERS (center_id,name, location, state, city)
VALUES (3,'Pharmacie de Serre', '20 Rue de Serre', 'Open','Pagny-sur-Moselle');
DROP TYPE IF EXISTS STATE_ADMIN CASCADE;
CREATE TYPE STATE_ADMIN as ENUM('Active','Disable');
DROP TABLE IF EXISTS ADMINS;
CREATE TABLE ADMINS(
	admin_id bigint PRIMARY KEY,
	firstName varchar(30) not NULL,
	lastName varchar(30)not NULL,
	state STATE_ADMIN not NULL,
	center_id bigint,
	FOREIGN KEY (center_id) REFERENCES CENTERS(center_id)
);
INSERT INTO ADMINS (admin_id, firstName, lastName, state, center_id)
VALUES (1,'Carl', 'JUAN', 'Active', 1);
INSERT INTO ADMINS (admin_id, firstName, lastName, state, center_id)
VALUES (2,'Stéphanie', 'MONAK', 'Disable', 1);
INSERT INTO ADMINS (admin_id, firstName, lastName, state, center_id)
VALUES (3,'Goefrey', 'SERNOUS', 'Active', 3);
DROP TABLE IF EXISTS DOCTORS;
CREATE TABLE DOCTORS(
	doctor_id bigint PRIMARY KEY,
	firstName varchar(30) not NULL,
	lastName varchar(30) not NULL,
	state STATE_ADMIN not NULL,
	center_id bigint,
	FOREIGN KEY (center_id) REFERENCES CENTERS(center_id)
);
INSERT INTO DOCTORS (doctor_id, firstName, lastName, state, center_id)
VALUES (1,'François', 'GOTINI', 'Active', 2);
INSERT INTO DOCTORS (doctor_id, firstName, lastName, state, center_id)
VALUES (2,'Laurent', 'JEKILL', 'Disable', 3);
INSERT INTO DOCTORS (doctor_id, firstName, lastName, state, center_id)
VALUES (3,'Clément', 'JACQUE', 'Active', 1);
DROP TYPE IF EXISTS STATE_CLIENT CASCADE;
CREATE TYPE STATE_CLIENT as ENUM('Treated','Waiting','Cancelled','Disable');
DROP TABLE IF EXISTS APPOINTMENTS;
CREATE TABLE APPOINTMENTS (
	appointment_id bigint PRIMARY KEY,
	firstName varchar(30) not NULL,
	lastName varchar(30) not NULL,
	phone varchar(15) not NULL,
	email varchar(50) not NULL,
	state STATE_CLIENT not NULL,
	date timestamp not NULL,
	center_id bigint,
	FOREIGN KEY(center_id) REFERENCES CENTERS(center_id)
);
INSERT INTO APPOINTMENTS (appointment_id, firstName, lastName, phone, email, state, date, center_id)
VALUES (1,'Axel', 'LOTH', '00 00 00 00 00', 'actual.email@gmail.com', 'Treated', '2018-10-21 20:31:40', 1);
INSERT INTO APPOINTMENTS (appointment_id, firstName, lastName, phone, email, state, date, center_id)
VALUES (2,'Gérard', 'BOUCHARD', '11 11 11 11 11', 'true-actual.email@gmail.com', 'Waiting', '2023-09-11 07:54:37', 3);
INSERT INTO APPOINTMENTS (appointment_id, firstName, lastName, phone, email, state, date, center_id)
VALUES (3,'Antoine', 'DANIEL', '22 22 22 22 22', 'not-a-true-email@hotmail', 'Cancelled', '2019-08-12 17:07:22', 1);
DROP TABLE IF EXISTS SUPERADMINS;
CREATE TABLE SUPERADMINS(
	superAdmin_id bigint PRIMARY KEY,
	firstName varchar(30),
	lastName varchar(30),
	state STATE_ADMIN not NULL
);
INSERT INTO SUPERADMINS (superAdmin_id, firstName, lastName, state)
VALUES (1,'Axel', 'JACQUE', 'Active');
INSERT INTO SUPERADMINS (superAdmin_id, firstName, lastName, state)
VALUES (2,'Clément', 'LOTH', 'Active');
INSERT INTO SUPERADMINS (superAdmin_id, firstName, lastName, state)
VALUES (3,'Jimmy', 'BERNABE', 'Active');

select admins.firstName from ADMINS inner join CENTERS on admins.center_id = centers.center_id where centers.name = 'Centre hospitalier de Moulins-Yzeure';
select * from SUPERADMINS;
