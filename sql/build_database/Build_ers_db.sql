DROP TABLE IF EXISTS public.reimbursements CASCADE;
DROP TABLE IF EXISTS public.users CASCADE;
DROP TABLE IF EXISTS public.user_roles CASCADE;
DROP TABLE IF EXISTS public.reimbursement_status CASCADE;
DROP TABLE IF EXISTS public.reimbursement_type CASCADE;

CREATE TABLE public.reimbursement_status(
	status_id serial PRIMARY KEY,
	status varchar(10)
);

CREATE TABLE public.reimbursement_type(
	type_id serial PRIMARY KEY,
	reimb_type varchar(10)
);

CREATE TABLE public.user_roles(
	role_id serial PRIMARY KEY,
	us_role varchar(10)
);

CREATE TABLE public.users(
	user_id serial PRIMARY KEY,
	username varchar(50) NOT null,
	us_password varchar(200) NOT null,
	first_name varchar(100) NOT null,
	last_name varchar(100) NOT null,
	email varchar(150) UNIQUE,
	role_id integer,
	UNIQUE (username,us_password)
);

CREATE TABLE public.reimbursements(
	reimb_id serial PRIMARY KEY,
	amount numeric(7,2) NOT NULL CHECK(amount>0),
	submitted timestamptz NOT null,
	resolved timestamptz,
	description varchar(250),
	user_id integer,
	status_id integer,
	type_id integer
);

INSERT INTO public.reimbursement_status (status)
VALUES (NULL),(NULL),(NULL),(NULL),(NULL),(NULL);

INSERT INTO public.reimbursement_type (reimb_type)
VALUES ('lodging'),('food'),('other'),('travel'),('other'),('food');

INSERT INTO public.user_roles (us_role)
VALUES ('Employee'),('Employee'),('Manager'),('Manager'),('Employee'),('Manager');

INSERT INTO public.users (username, us_password, first_name, last_name, email, role_id)
VALUES
--password is kansasboy
('smallville','кантатбош', 'Clark', 'Kent', 'farmboy@yahoo.com', 1),
--
--password is mybyline
('reporter', 'мшбшлинд', 'Lois', 'Lane', 'acereporter@hotmail.com', 2),
--
--password is elvislover
('chief', 'длхитлохдс', 'Perry', 'White', 'caesarsghost@yahoo.com', 3),
--
--password is manager
('testmanager', 'манаждс', 'Tom', 'Smith', 'manager@yahoo.com', 4),
--
--password is employee
('testemployee', 'дмплошдд', 'John', 'Smith', 'mystery@yahoo.com', 5),
--
--password is ronny
('ronman', 'соннш', 'Ron', 'Troupe', 'theron@yahoo.com', 6);

INSERT INTO public.reimbursements (amount, submitted, resolved, description, user_id, status_id, type_id)
VALUES 
(250.25, current_timestamp(2), null, 'hotel expenses for story in Gotham', 1, 1 , 1),
(402.50, current_timestamp(2), null, 'food for Bobby Bigmouth', 2, 2, 2),
(1000.99, current_timestamp(2), null, 'Elvis Museum Gift Shop', 3, 3, 3),
(1155.99, current_timestamp(2), null, 'I am a manager', 4, 4, 4),
(122.95, current_timestamp(2), null, 'I have my reasons', 5, 5, 5),
(259.45, current_timestamp(2), null, 'I was hungry, dont judge me', 6, 6, 6);


ALTER TABLE public.users ADD CONSTRAINT roles_fk
FOREIGN KEY(role_id) REFERENCES user_roles(role_id) ON DELETE CASCADE; 

ALTER TABLE public.reimbursements ADD CONSTRAINT status_fk
FOREIGN KEY(status_id) REFERENCES reimbursement_status(status_id) ON DELETE CASCADE;

ALTER TABLE public.reimbursements ADD CONSTRAINT type_fk
FOREIGN key(type_id) REFERENCES reimbursement_type(type_id) ON DELETE CASCADE;

ALTER TABLE public.reimbursements ADD CONSTRAINT user_fk
FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE; 
