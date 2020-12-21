INSERT INTO reimbursement_status (status)
VALUES (NULL),(NULL),(NULL),(NULL),(NULL),(NULL);

INSERT INTO reimbursement_type (reimb_type)
VALUES ('lodging'),('food'),('other'),('travel'),('other'),('food');

INSERT INTO user_roles (us_role)
VALUES ('Employee'),('Employee'),('Manager'),('Manager'),('Employee'),('Manager');

INSERT INTO users (username, us_password, first_name, last_name, email, role_id)
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

INSERT INTO reimbursements (amount, submitted, resolved, description, user_id, status_id, type_id)
VALUES (250.25, '2020-12-12 20:25:11', null, 'hotel expenses for story in Gotham', 1, 1 , 1),
(402.50, '2020-12-12 20:30:11', null, 'food for Bobby Bigmouth', 2, 2, 2),
(1000.99, '2020-12-12 20:40:11', null, 'Elvis Museum Gift Shop', 3, 3, 3),
(1155.99, '2020-12-12 21:50:11', null, 'I am a manager', 4, 4, 4),
(122.95, '2020-12-12 21:05:11', null, 'I have my reasons', 5, 5, 5),
(259.45, '2020-12-12 21:15:11', null, 'I was hungry, dont judge me', 6, 6, 6);

SELECT max(status_id) FROM reimbursement_status;

SELECT now();--current time
