CREATE SCHEMA IF NOT EXISTS `clinic_system`;
USE `clinic_system`;

CREATE TABLE IF NOT EXISTS `user` (
	`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
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

CREATE TABLE IF NOT EXISTS `user_roles` (
	`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS `medical_schedule` (
	`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`created_id` BIGINT NOT NULL,
    `description` NVARCHAR(255) NOT NULL,
    `type` VARCHAR(50) NOT NULL,
    `schedule` DATETIME NOT NULL,
    `created_stamp` DATETIME,
    `modified_stamp` DATETIME
);

CREATE TABLE IF NOT EXISTS `medical_result` (
	`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` NVARCHAR(255) NOT NULL,
	`patient_id` BIGINT NOT NULL,
	`doctor_id` BIGINT NOT NULL,
    `diagnosis` NVARCHAR(255) NOT NULL,
    `conclude` NVARCHAR(255) NOT NULL,
    `created_stamp` DATETIME,
    `modified_stamp` DATETIME
);

CREATE TABLE IF NOT EXISTS `health_news` (
	`id` BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`created_id` BIGINT NOT NULL,
    `title` NVARCHAR(255) NOT NULL,
    `description` NVARCHAR(255) NOT NULL,
    `image` NVARCHAR(255) NOT NULL,
    `created_stamp` DATETIME,
    `modified_stamp` DATETIME
);

ALTER TABLE `user_roles`
    ADD CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES `user` (id); 
ALTER TABLE `user_roles`
    ADD CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES `role` (id); 
ALTER TABLE `medical_schedule`
    ADD CONSTRAINT fk_medical_schedule_user FOREIGN KEY (created_id) REFERENCES `user` (id);
ALTER TABLE `medical_result`
    ADD CONSTRAINT fk_medical_result_patient FOREIGN KEY (patient_id) REFERENCES `user` (id);
ALTER TABLE `medical_result`
    ADD CONSTRAINT fk_medical_result_doctor FOREIGN KEY (doctor_id) REFERENCES `user` (id);
 ALTER TABLE `health_news`
    ADD CONSTRAINT fk_health_news_user FOREIGN KEY (created_id) REFERENCES `user` (id);