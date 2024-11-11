-- 테이블 생성


CREATE DATABASE run_prj;
use run_prj;


-- User 테이블
CREATE TABLE `User` (
    `user_no` INT NOT NULL AUTO_INCREMENT,
    `user_id` VARCHAR(200) NOT NULL,
    `user_password` VARCHAR(500) NOT NULL,
    `user_email` VARCHAR(50) NULL,
    `user_name` VARCHAR(50) NULL,
    `user_birthday` CHAR(8) NULL,
    `user_gender` CHAR(3) NULL,
    `user_address` VARCHAR(50) NULL,
    `user_detail_address` VARCHAR(200) NULL,
    `user_zip_code` INT NULL,
    `user_phone_number` VARCHAR(15) NULL,
    `user_age` INT NULL,
    `user_nickname` VARCHAR(50) NULL,
    `user_reg_date` DATE NULL,
    `user_login_date` DATE NULL,
    `user_photo` VARCHAR(500) NULL,
    `user_role` VARCHAR(10) NULL,
    `user_iat` VARCHAR(50) NULL,
    `user_exp` VARCHAR(255) NULL,
    PRIMARY KEY (`user_no`)
);

-- City 테이블
CREATE TABLE `City` (
    `city_no` INT NOT NULL AUTO_INCREMENT,
    `city_name` VARCHAR(100) NULL,
    PRIMARY KEY (`city_no`)
);

-- District 테이블
CREATE TABLE `District` (
    `district_no` INT NOT NULL AUTO_INCREMENT,
    `city_no` INT NOT NULL,
    `district_name` VARCHAR(100) NULL,
    PRIMARY KEY (`district_no`, `city_no`),
    FOREIGN KEY (`city_no`) REFERENCES `City` (`city_no`)
);

-- Race 테이블
CREATE TABLE `Race` (
    `race_no` INT NOT NULL AUTO_INCREMENT,
    `race_name` VARCHAR(300) NULL,
    `race_date` DATE NULL,
    `race_place` VARCHAR(300) NULL,
    `race_distance` VARCHAR(300) NULL,
    `race_url` VARCHAR(2048) NULL,
    `user_no` INT NOT NULL,
    `race_reg_date` DATE NULL,
    `city_no` INT NOT NULL,
    PRIMARY KEY (`race_no`),
    FOREIGN KEY (`user_no`) REFERENCES `User` (`user_no`),
    FOREIGN KEY (`city_no`) REFERENCES `City` (`city_no`)
);

-- Community_board 테이블
CREATE TABLE `Community_board` (
    `c_board_no` INT NOT NULL AUTO_INCREMENT,
    `c_board_file_no` VARCHAR(200) NULL,
    `c_board_title` VARCHAR(1000) NULL,
    `c_board_content` VARCHAR(5000) NULL,
    `c_board_reg_date` DATETIME NULL,
    `c_board_view_cnt` INT NULL,
    `board_user_no` INT NOT NULL,
    PRIMARY KEY (`c_board_no`),
    FOREIGN KEY (`board_user_no`) REFERENCES `User` (`user_no`)
);

-- Files 테이블
CREATE TABLE `Files` (
    `file_no` VARCHAR(40) NOT NULL,
    `file_name` VARCHAR(40) NULL,
    `file_path` VARCHAR(100) NULL,
    `board_no` INT NOT NULL,
    `file_system_name` VARCHAR(100) NULL,
    PRIMARY KEY (`file_no`),
    FOREIGN KEY (`board_no`) REFERENCES `Community_board` (`c_board_no`)
);

-- Community_comment 테이블
CREATE TABLE `Community_comment` (
    `c_comment_no` INT NOT NULL AUTO_INCREMENT,
    `c_comment_content` VARCHAR(300) NOT NULL,
    `c_comment_reg_date` DATETIME NULL,
    `user_no` INT NOT NULL,
    `c_board_no` INT NOT NULL,
    PRIMARY KEY (`c_comment_no`),
    FOREIGN KEY (`user_no`) REFERENCES `User` (`user_no`),
    FOREIGN KEY (`c_board_no`) REFERENCES `Community_board` (`c_board_no`)
);

-- Crew 테이블
CREATE TABLE `Crew` (
    `crew_no` INT NOT NULL AUTO_INCREMENT,
    `crew_name` VARCHAR(100) NOT NULL,
    `crew_size` INT NOT NULL,
    `district_no` INT NOT NULL,
    `city_no` INT NOT NULL,
    `crew_content` VARCHAR(5000) NOT NULL,
    `crew_url` VARCHAR(2048) NOT NULL,
    `crew_day` VARCHAR(255) NULL,
    PRIMARY KEY (`crew_no`),
    FOREIGN KEY (`district_no`, `city_no`) REFERENCES `District` (`district_no`, `city_no`)
);

-- Magazine_board 테이블
CREATE TABLE `Magazine_board` (
    `m_board_no` INT NOT NULL AUTO_INCREMENT,
    `m_board_title` VARCHAR(400) NULL,
    `m_board_writer` VARCHAR(20) NULL,
    `m_board_content` VARCHAR(5000) NULL,
    `m_board_reg_date` DATETIME NULL,
    `m_board_theme` VARCHAR(200) NULL,
    `m_board_view_cnt` INT NULL,
    `user_no` INT NOT NULL,
    PRIMARY KEY (`m_board_no`),
    FOREIGN KEY (`user_no`) REFERENCES `User` (`user_no`)
);

-- Magazine_comment 테이블
CREATE TABLE `Magazine_comment` (
    `m_comment_no` INT NOT NULL AUTO_INCREMENT,
    `m_comment_content` VARCHAR(300) NOT NULL,
    `m_comment_reg_date` DATETIME NULL,
    `user_no` INT NOT NULL,
    `m_board_no` INT NOT NULL,
    PRIMARY KEY (`m_comment_no`),
    FOREIGN KEY (`user_no`) REFERENCES `User` (`user_no`),
    FOREIGN KEY (`m_board_no`) REFERENCES `Magazine_board` (`m_board_no`)
);