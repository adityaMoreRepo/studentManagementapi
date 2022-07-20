USE `student_schema`;

insert into `course` values(1, 9);
insert into `course` values(2, 10);
insert into `course` values(3, 9);
insert into `course` values(4, 10);

INSERT INTO `student` VALUES (1,'Vikas','Sharma', 'Male', 1);
INSERT INTO `student` VALUES (2,'Sham','Mishra', 'Male', 2);
INSERT INTO `student` VALUES (3,'Tom','Robin', 'Male', 3);
INSERT INTO `student` VALUES (4,'Rahi','Gaikwad', 'Female', 4);

INSERT INTO `address` VALUES(1, "Permanent", 'Pune', 101, '9458296938', '414041', 'Main road', 'Maharashtra', 1);
INSERT INTO `address` VALUES(2, "permanent", 'Pune', 201, '9155934936', '304110', 'Gandhi road', 'Maharashtra', 2);
INSERT INTO `address` VALUES(3, "Temporary", 'Nasik', 106, '9158295435', '402051', 'Karve road', 'Maharashtra', 3);
INSERT INTO `address` VALUES(4,  "Permanent", 'Mumbai', 401,'9058396941', '306220', 'Laxmi road', 'Maharashtra', 3);

insert into `subject` values(1, 'Math', 150, 50, 70, 1);
insert into `subject` values(2, 'Science', 70, 30, 40, 1);
insert into `subject` values(3, 'History', 50, 25, 45, 1);
insert into `subject` values(4, 'English', 70, 30, 47, 1);
insert into `subject` values(5, 'Geography', 50, 25, 29, 1);
insert into `subject` values(6, 'Math', 150, 50, 90, 2);
insert into `subject` values(7, 'Science', 70, 30, 60, 2);
insert into `subject` values(8, 'History', 50, 25, 36, 2);
insert into `subject` values(9, 'English', 70, 30, 60, 2);
insert into `subject` values(10, 'Geography', 50, 25, 49, 2);

