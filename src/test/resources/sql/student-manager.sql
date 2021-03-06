--CREATE DATABASE IF NOT EXISTS `student-manager` DEFAULT CHARACTER SET utf8;
--
--USE `student-manager`;

CREATE TABLE IF NOT EXISTS `cls_meta` (
    `cls_id` BIGINT PRIMARY KEY,
    `cls_name` VARCHAR(50) NOT NULL,
    `cls_create_time` BIGINT NOT NULL,
    `cls_manager` VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS `st_meta` (
    `st_id` BIGINT PRIMARY KEY,
    `st_name` VARCHAR(50) NOT NULL,
    `st_start_time` BIGINT NOT NULL,
    `cls_id`  BIGINT NULL,
    `st_city` VARCHAR(50) NOT NULL,
    `st_sex` VARCHAR(10) NOT NULL,
    `st_political` VARCHAR(20) NOT NULL
);

--CREATE UNIQUE INDEX `cls_meta_pri_index` ON cls-meta('cls-meta');
--CREATE UNIQUE INDEX `st_meta_pri_index` ON st-meta('st_id');
--
--CREATE INDEX st_cls_index ON st-meta('cls_id');