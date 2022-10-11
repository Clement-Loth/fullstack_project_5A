CREATE TYPE STATE_CENTER AS ENUM ('Open', 'Closed');

CREATE TABLE CENTERS (
	center_id bigint PRIMARY KEY,
	location varchar(50) NOT NULL,
	name varchar(50) NOT NULL,
	state STATE_CENTER
);