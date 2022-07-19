DROP DATABASE IF EXISTS `student_schema`;
CREATE DATABASE `student_schema`;
USE `student_schema`;

CREATE TABLE `course` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `standard` int NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `student` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `FKdfypyqt0stgfc0aij9kcxm99s` (`course_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `address_type` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `flat_no` int NOT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `pin` varchar(255) NOT NULL,
  `road` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `FKg6qcywqtp08ksr5r9lpv16g1k` (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `subject` (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) DEFAULT NULL,
   `total_marks` int NOT NULL,
   `passing_marks` int NOT NULL,
  `marks_obtained` int NOT NULL,
  `course_id` int DEFAULT NULL,
  PRIMARY KEY (`subject_id`),
  KEY `FKnxhd764cm1ie783v26t3jsdlx` (`course_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



