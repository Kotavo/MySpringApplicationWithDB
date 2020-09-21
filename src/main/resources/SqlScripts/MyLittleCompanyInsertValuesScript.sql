INSERT INTO departments (is_deleted, name,location) VALUES
(false, 'Department 1', 'Location 1'),
(false, 'Department 2', 'Location 1'),
(false, 'Department 3', 'Location 2'),
(false, 'Department 4', 'Location 2'),
(false, 'Department 5', 'Location 3'),
(false, 'Department 6', 'Location 4');

INSERT INTO employees (is_deleted, name,surname,position,mail,department) VALUES
(false, 'EmployeeName 1', 'EmployeeSurname 1', 'Position 1', 'EmployeeMail 1', 1),
(false, 'EmployeeName 2', 'EmployeeSurname 2', 'Position 1', 'EmployeeMail 2', 2),
(false, 'EmployeeName 3', 'EmployeeSurname 3', 'Position 2', 'EmployeeMail 3', 3),
(false, 'EmployeeName 4', 'EmployeeSurname 4', 'Position 2', 'EmployeeMail 4', 4),
(false, 'EmployeeName 5', 'EmployeeSurname 5', 'Position 2', 'EmployeeMail 5', 6),
(false, 'EmployeeName 6', 'EmployeeSurname 6', 'Position 3', 'EmployeeMail 6', 3),
(false, 'EmployeeName 7', 'EmployeeSurname 7', 'Position 3', 'EmployeeMail 7', 4),
(false, 'EmployeeName 8', 'EmployeeSurname 8', 'Position 3', 'EmployeeMail 8', 5);


INSERT INTO workprocesses (is_deleted, description, employee) VALUES
(false, 'Some tasks solved by the 1 WorkProcess', 1),
(false, 'Some tasks solved by the 2 WorkProcess', 2),
(false, 'Some tasks solved by the 3 WorkProcess', 3),
(false, 'Some tasks solved by the 4 WorkProcess', 4),
(false, 'Some tasks solved by the 5 WorkProcess', 5),
(false, 'Some tasks solved by the 6 WorkProcess', 6),
(false, 'Some tasks solved by the 7 WorkProcess', 7),
(false, 'Some tasks solved by the 8 WorkProcess', 8);


