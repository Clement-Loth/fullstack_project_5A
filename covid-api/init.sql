DROP TABLE IF EXISTS CENTERS CASCADE;
DROP TYPE IF EXISTS STATE_CENTER CASCADE;
CREATE TYPE STATE_CENTER as ENUM('Open','Closed');
CREATE TABLE CENTERS(
	id bigserial PRIMARY KEY,
	name varchar(50) not NULL,
	location varchar(150) not NULL,
	state STATE_CENTER not NULL,
	city varchar(50) not NULL,
	disabled boolean
);
INSERT INTO CENTERS (name, location, state, city, disabled) VALUES
('Centre hospitalier de Moulins-Yzeure', '10, av. du Général de Gaulle', 'Open','Moulins-Yzeure',false),
('Centre de vaccination de Metz', 'Boulevard Saint-Symphorien', 'Open','Longeville-lès-Metz',false),
('Pharmacie de Serre', '20 Rue de Serre', 'Open','Pagny-sur-Moselle',false);

DROP TYPE IF EXISTS ROLE CASCADE;
CREATE TYPE ROLE as ENUM('Administrator','Doctor','SuperAdministrator');
DROP TABLE IF EXISTS USERS CASCADE;
CREATE TABLE USERS(
	id bigserial PRIMARY KEY,
	first_name varchar(30) not NULL,
	last_name varchar(30)not NULL,
	password varchar(72) not NULL,
	email varchar(50) not NULL UNIQUE,
	phone varchar(20),
	disabled boolean,
	role ROLE not NULL,
	center_id bigint,
	FOREIGN KEY(center_id) REFERENCES CENTERS(id)	
);

DROP TYPE IF EXISTS STATE_CLIENT CASCADE;
CREATE TYPE STATE_CLIENT as ENUM('Treated','Waiting','Cancelled');
DROP TABLE IF EXISTS APPOINTMENTS;
CREATE TABLE APPOINTMENTS (
	id bigserial PRIMARY KEY,
	first_name varchar(30) not NULL,
	last_name varchar(30) not NULL,
	phone varchar(15) not NULL,
	email varchar(50) not NULL,
	state STATE_CLIENT not NULL,
	date timestamp not NULL,
	disabled boolean,
	center_id bigserial,
	FOREIGN KEY(center_id) REFERENCES CENTERS(id),
	doctor_id bigserial,
	FOREIGN KEY(doctor_id) REFERENCES USERS(id)
);