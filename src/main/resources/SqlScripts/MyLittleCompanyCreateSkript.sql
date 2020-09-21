CREATE DATABASE "MyLittleCompany"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE departments (
	id serial CONSTRAINT id PRIMARY KEY,
	name varchar(40),
	location varchar(40)
);

CREATE TABLE employees (
	id serial CONSTRAINT id PRIMARY KEY,
	name varchar(30),
	surname varchar(30),
	position varchar(30),
	mail varchar(30),
	department int NOT NULL,
	FOREIGN KEY (department) REFERENCES departments (id) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE workprocesses(
	id serial CONSTRAINT id PRIMARY KEY,
	description varchar(80),
	employee int NOT NULL,
	FOREIGN KEY (employee) REFERENCES employees (id) ON DELETE NO ACTION ON UPDATE CASCADE
);