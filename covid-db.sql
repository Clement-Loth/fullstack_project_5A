DROP TABLE IF EXISTS CENTERS CASCADE;
DROP TYPE IF EXISTS STATE_CENTER CASCADE;
CREATE TYPE STATE_CENTER as ENUM('Open','Closed');
CREATE TABLE CENTERS(
	center_id bigint PRIMARY KEY,
	name varchar(50) not NULL,
	location varchar(150) not NULL,
	state STATE_CENTER not NULL,
	city varchar(50) not NULL
);
DROP TABLE IF EXISTS ADMINS;
CREATE TABLE ADMINS(
	admin_id bigint PRIMARY KEY,
	firstName varchar(30) not NULL,
	lastName varchar(30)not NULL,
	center_id bigint,
	FOREIGN KEY (center_id) REFERENCES CENTERS(center_id)
);
DROP TABLE IF EXISTS DOCTORS;
CREATE TABLE DOCTORS(
	doctor_id bigint PRIMARY KEY,
	firstName varchar(30) not NULL,
	lastName varchar(30) not NULL,
	center_id bigint,
	FOREIGN KEY (center_id) REFERENCES CENTERS(center_id)
);
DROP TYPE IF EXISTS STATE_CLIENT CASCADE;
CREATE TYPE STATE_CLIENT as ENUM('Treated','Waiting');
DROP TABLE IF EXISTS APPOINTMENTS;
CREATE TABLE APPOINTMENTS (
	appointment_id bigint PRIMARY KEY,
	fisrtName varchar(30) not NULL,
	lastName varchar(30) not NULL,
	phone varchar(15) not NULL,
	email varchar(50) not NULL,
	state STATE_CLIENT not NULL,
	date timestamp not NULL,
	center_id bigint,
	FOREIGN KEY(center_id) REFERENCES CENTERS(center_id)
);
DROP TABLE IF EXISTS SUPERADMINS;
CREATE TABLE SUPERADMINS(
	superAdmin_id bigint PRIMARY KEY,
	firstName varchar(30),
	lastName varchar(30)
);
select * from CENTERS;