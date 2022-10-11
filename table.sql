DROP TABLE Center;
DROP TYPE STATE_CENTER;
CREATE TYPE STATE_CENTER AS ENUM ('Open', 'Closed');
CREATE TABLE CENTER (
	center_id bigint PRIMARY KEY,
	location varchar(50) NOT NULL,
	name varchar(50) NOT NULL,
	state STATE_CENTER
);