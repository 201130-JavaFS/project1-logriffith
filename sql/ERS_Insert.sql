INSERT INTO reimbursement_status (status)
VALUES ('approved'),(NULL),('denied'),(NULL),(NULL),(NULL);

INSERT INTO reimbursement_type (reimb_type)
VALUES ('LODGING'),('FOOD'),('OTHER'),('TRAVEL'),('OTHER'),('FOOD');

INSERT INTO user_roles (us_role)
VALUES ('employee'),('employee'),('manager'),('manager'),('employee'),('manager');

INSERT INTO users (username, us_password, first_name, last_name, email, role_id)
VALUES
--password is kansasboy
('smallville','���������', 'Clark', 'Kent', 'farmboy@yahoo.com', 1),
--
--password is mybyline
('reporter', '��������', 'Lois', 'Lane', 'acereporter@hotmail.com', 2),
--
--password is elvislover
('chief', '����������', 'Perry', 'White', 'caesarsghost@yahoo.com', 3),
--
--password is manager
('testmanager', '�������', 'Tom', 'Smith', 'manager@yahoo.com', 4),
--
--password is employee
('testemployee', '��������', 'John', 'Smith', 'mystery@yahoo.com', 5),
--
--password is ronny
('ronman', '�����', 'Ron', 'Troupe', 'theron@yahoo.com', 6);

INSERT INTO reimbursements (amount, submitted, resolved, description, user_id, status_id, type_id)
VALUES (250.25, '2020-12-12 20:25:11', '2020-12-12 20:35:11', 'hotel expenses for story in Gotham', 1, 1 , 1),
(402.50, '2020-12-12 20:30:11', '2020-12-12 20:40:11', 'food for Bobby Bigmouth', 2, 2, 2),
(1000.99, '2020-12-12 20:40:11', '2020-12-12 20:50:11', 'Elvis Museum Gift Shop', 3, 3, 3),
(1155.99, '2020-12-12 21:50:11', '2020-12-12 21:00:11', 'I am a manager', 4, 4, 4),
(122.95, '2020-12-12 21:05:11', '2020-12-12 21:10:11', 'I have my reasons', 5, 5, 5),
(259.45, '2020-12-12 21:15:11', '2020-12-12 21:45:11', 'I was hungry, dont judge me', 6, 6, 6);

SELECT max(status_id) FROM reimbursement_status;

SELECT now();--current time
