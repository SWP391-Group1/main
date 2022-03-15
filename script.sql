CREATE SCHEMA IF NOT EXISTS `clinic_system`;
USE `clinic_system`;

CREATE TABLE IF NOT EXISTS `user` (
	`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`role_id` BIGINT NOT NULL,
    `card_id` VARCHAR(50) NOT NULL,
    `first_name` NVARCHAR(50) NOT NULL,
    `last_name` NVARCHAR(50) NOT NULL,
    `gender` BOOLEAN NOT NULL,
    `avatar` NVARCHAR(255)  NULL,
    `dob` DATETIME NOT NULL,
    `phone_number` VARCHAR(10) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `username` VARCHAR(50) NOT NULL,
	`password` VARCHAR(50) NOT NULL,
    `status` BOOLEAN NOT NULL,
    `created_stamp` DATETIME,
    `modified_stamp` DATETIME
);

CREATE TABLE IF NOT EXISTS `role` (
	`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR (30) NOT NULL
);

CREATE TABLE IF NOT EXISTS `medical_schedule` (
	`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`created_id` BIGINT NOT NULL,
    `description` NVARCHAR(255) NOT NULL,
    `type` VARCHAR(50) NOT NULL,
    `schedule` DATETIME NOT NULL,
    `status` VARCHAR(50) NOT NULL,
    `created_stamp` DATETIME,
    `modified_stamp` DATETIME
);

CREATE TABLE IF NOT EXISTS `medical_result` (
	`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`schedule_id` BIGINT NOT NULL,
	`patient_id` BIGINT NOT NULL,
	`doctor_id` BIGINT NOT NULL,
     `name` NVARCHAR(255) NOT NULL,
    `diagnosis` NVARCHAR(255) NOT NULL,
    `conclude` NVARCHAR(255) NOT NULL,
    `status` BOOLEAN NOT NULL,
    `created_stamp` DATETIME,
    `modified_stamp` DATETIME
);

CREATE TABLE IF NOT EXISTS `medical_method` (
	`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `result_id` BIGINT NOT NULL,
    `name` NVARCHAR(255) NOT NULL,
    `image` NVARCHAR(255) NULL,
    `diagnosis` NVARCHAR(255) NOT NULL,
    `conclude` NVARCHAR(255) NOT NULL,
    `created_stamp` DATETIME,
    `modified_stamp` DATETIME
);

CREATE TABLE IF NOT EXISTS `health_news` (
	`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`created_id` BIGINT NOT NULL,
    `title` TEXT NOT NULL,
    `thumbnail` NVARCHAR(255) NOT NULL,
    `short_description` TEXT NOT NULL,
    `content` TEXT NOT NULL,
    `created_stamp` DATETIME,
    `modified_stamp` DATETIME
);

ALTER TABLE `user`
    ADD CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES `role` (id); 
ALTER TABLE `medical_schedule`
    ADD CONSTRAINT fk_medical_schedule_user FOREIGN KEY (created_id) REFERENCES `user` (id);
ALTER TABLE `medical_result`
    ADD CONSTRAINT fk_medical_result_patient FOREIGN KEY (patient_id) REFERENCES `user` (id);
ALTER TABLE `medical_result`
    ADD CONSTRAINT fk_medical_result_doctor FOREIGN KEY (doctor_id) REFERENCES `user` (id);
 ALTER TABLE `health_news`
    ADD CONSTRAINT fk_health_news_user FOREIGN KEY (created_id) REFERENCES `user` (id);
ALTER TABLE `medical_method`
    ADD CONSTRAINT fk_medical_method_result FOREIGN KEY (result_id) REFERENCES `medical_result` (id);
ALTER TABLE `medical_result`
    ADD CONSTRAINT fk_medical_result_schedule FOREIGN KEY (schedule_id) REFERENCES `medical_schedule` (id);
    
INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('DOCTOR'); 
INSERT INTO role (name) VALUES ('RECEPTIONIST'); 