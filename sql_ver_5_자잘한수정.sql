-- 테이블 생성
CREATE DATABASE run_prj;
use run_prj;

drop table User;

CREATE TABLE `user` (
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
    `user_reg_date` DATETIME NULL,
    `user_login_date` DATETIME NULL,
    `user_photo` VARCHAR(500) NULL,
    `user_role` VARCHAR(10) NULL,
    `user_iat` VARCHAR(50) NULL,
    `user_exp` VARCHAR(255) NULL,
    PRIMARY KEY (`user_no`)
);

-- City 테이블
CREATE TABLE `city` (
    `city_no` INT NOT NULL AUTO_INCREMENT,
    `city_name` VARCHAR(100) NULL,
    PRIMARY KEY (`city_no`)
);

-- District 테이블
CREATE TABLE `district` (
    `district_no` INT NOT NULL AUTO_INCREMENT,
    `city_no` INT NOT NULL,
    `district_name` VARCHAR(100) NULL,
    PRIMARY KEY (`district_no`, `city_no`),
    FOREIGN KEY (`city_no`) REFERENCES `City` (`city_no`)
);

-- Race 테이블
CREATE TABLE `race` (
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
CREATE TABLE `community_board` (
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
CREATE TABLE `files` (
    `file_no` VARCHAR(40) NOT NULL,
    `file_name` VARCHAR(40) NULL,
    `file_path` VARCHAR(100) NULL,
    `board_no` INT NOT NULL,
    `file_system_name` VARCHAR(100) NULL,
    PRIMARY KEY (`file_no`),
    FOREIGN KEY (`board_no`) REFERENCES `Community_board` (`c_board_no`)
);

-- Community_comment 테이블
CREATE TABLE `community_comment` (
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
CREATE TABLE `crew` (
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
CREATE TABLE `magazine_board` (
    `m_board_no` INT NOT NULL AUTO_INCREMENT,
    `m_board_title` VARCHAR(400) NULL,
    `m_board_writer` VARCHAR(20) NULL,
    `m_board_content` VARCHAR(5000) NULL,
    `m_board_reg_date` DATETIME NULL,
    `m_board_theme` VARCHAR(200) NULL,
    `magazin_thumbnail` VARCHAR(1024) NULL,
    `m_board_view_cnt` INT NULL,
    `user_no` INT NOT NULL,
    PRIMARY KEY (`m_board_no`),
    FOREIGN KEY (`user_no`) REFERENCES `User` (`user_no`)
);

ALTER TABLE `magazine_board` 
add column m_board_thumbnail varchar(1000) not null;

-- Magazine_comment 테이블
CREATE TABLE `magazine_comment` (
    `m_comment_no` INT NOT NULL AUTO_INCREMENT,
    `m_comment_content` VARCHAR(300) NOT NULL,
    `m_comment_reg_date` DATETIME NULL,
    `user_no` INT NOT NULL,
    `m_board_no` INT NOT NULL,
    PRIMARY KEY (`m_comment_no`),
    FOREIGN KEY (`user_no`) REFERENCES `User` (`user_no`),
    FOREIGN KEY (`m_board_no`) REFERENCES `Magazine_board` (`m_board_no`)
);
INSERT INTO `user` (`user_id`, `user_password`, `user_email`, `user_name`, `user_birthday`, `user_gender`, `user_address`, `user_detail_address`, `user_zip_code`, `user_phone_number`, `user_age`, `user_nickname`, `user_reg_date`, `user_login_date`, `user_photo`, `user_role`, `user_iat`, `user_exp`)
VALUES 
('user01', 'password123', 'user01@example.com', 'John Doe', '19900101', 'M', 'Seoul', 'Gangnam', 12345, '010-1234-5678', 30, 'johnny', NOW(), NOW(), 'path/to/photo.jpg', 'USER', 'some_iat', 'some_exp'),
('user02', 'password456', 'user02@example.com', 'Jane Smith', '19920304', 'F', 'Busan', 'Haeundae', 67890, '010-2345-6789', 28, 'janey', NOW(), NOW(), 'path/to/photo2.jpg', 'USER', 'some_iat', 'some_exp');

INSERT INTO `city` (`city_name`)
VALUES 
('Seoul'),
('Busan');

INSERT INTO `district` (`city_no`, `district_name`)
VALUES 
(1, 'Gangnam'),
(1, 'Hongdae'),
(2, 'Haeundae'),
(2, 'Seomyeon');

INSERT INTO `race` (`race_name`, `race_date`, `race_place`, `race_distance`, `race_url`, `user_no`, `race_reg_date`, `city_no`)
VALUES 
('Seoul Marathon', '2024-05-01', 'Seoul', '42.195 km', 'http://seoulmarathon.com', 1, '2024-01-01', 1),
('Busan Half Marathon', '2024-06-01', 'Busan', '21.097 km', 'http://busanhalfmarathon.com', 2, '2024-02-01', 2);

INSERT INTO `community_board` (`c_board_file_no`, `c_board_title`, `c_board_content`, `c_board_reg_date`, `c_board_view_cnt`, `board_user_no`)
VALUES 
('file001', 'Spring Marathon Tips', 'Here are some tips for spring marathon preparation.', NOW(), 100, 1),
('file002', 'Running Shoes Review', 'A detailed review of the best running shoes for 2024.', NOW(), 150, 2);

INSERT INTO `crew` (`crew_name`, `crew_size`, `district_no`, `city_no`, `crew_content`, `crew_url`, `crew_day`)
VALUES 
('Seoul Runners', 10, 1, 1, 'A fun crew for daily runs in Seoul.', 'http://seoulrunners.com', 'Every weekend'),
('Busan Runners', 15, 3, 2, 'Join our crew for group runs in Busan.', 'http://busanrunners.com', 'Every Sunday');

INSERT INTO `magazine_board` (`m_board_title`, `m_board_writer`, `m_board_content`, `m_board_reg_date`, `m_board_theme`, `m_board_view_cnt`, `user_no`, `m_board_thumbnail`)
VALUES 
('Running in the Summer', 'John Doe', 'Here’s how to prepare for summer races.', NOW(), 'Running', 200, 1, 'path/to/thumbnail1.jpg'),
('Best Running Routes in Seoul', 'Jane Smith', 'Explore the best running routes in Seoul.', NOW(), 'Running', 180, 2, 'path/to/thumbnail2.jpg');

INSERT INTO `magazine_comment` (`m_comment_content`, `m_comment_reg_date`, `user_no`, `m_board_no`)
VALUES 
('Great article, I loved the routes!', NOW(), 2, 1),
('Thanks for the summer tips, I’ll definitely try them.', NOW(), 1, 2);

select * from user