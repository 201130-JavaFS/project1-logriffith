--create database ers;

CREATE TABLE reimbursement_status(
	status_id serial PRIMARY KEY,
	status varchar(10)
);

CREATE TABLE reimbursement_type(
	type_id serial PRIMARY KEY,
	reimb_type varchar(10)
);

CREATE TABLE user_roles(
	role_id serial PRIMARY KEY,
	us_role varchar(10)
);

CREATE TABLE users(
	user_id serial PRIMARY KEY,
	username varchar(50) NOT null,
	us_password varchar(200) NOT null,
	first_name varchar(100) NOT null,
	last_name varchar(100) NOT null,
	email varchar(150) UNIQUE,
	role_id integer,
	UNIQUE (username,us_password),
	CONSTRAINT user_roles_fk
		FOREIGN KEY(role_id)
			REFERENCES user_roles(role_id)
			ON DELETE CASCADE 
);

CREATE TABLE reimbursements(
	reimb_id serial PRIMARY KEY,
	amount numeric(7,2) NOT NULL CHECK(amount>0),
	submitted timestamptz NOT null,
	resolved timestamptz,
	description varchar(250),
	user_id integer,
	status_id integer,
	type_id integer, 
	FOREIGN KEY(status_id)
		REFERENCES reimbursement_status(status_id)
		ON DELETE CASCADE,
	FOREIGN key(type_id)
		REFERENCES reimbursement_type(type_id)
		ON DELETE CASCADE,
	FOREIGN KEY(user_id)
		REFERENCES users(user_id)
		ON DELETE CASCADE 
);