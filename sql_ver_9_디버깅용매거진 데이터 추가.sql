-- 1. 데이터베이스 생성 및 선택
DROP DATABASE IF EXISTS run_prj;
CREATE DATABASE IF NOT EXISTS run_prj;
USE run_prj;

-- 2. 테이블 삭제 (이미 존재하는 경우)
DROP TABLE IF EXISTS `files`;
DROP TABLE IF EXISTS `community_comment`;
DROP TABLE IF EXISTS `magazine_comment`;
DROP TABLE IF EXISTS `magazine`;
DROP TABLE IF EXISTS `crew`;
DROP TABLE IF EXISTS `board`;
DROP TABLE IF EXISTS `race`;
DROP TABLE IF EXISTS `district`;
DROP TABLE IF EXISTS `city`;
DROP TABLE IF EXISTS `user`;

-- 3. User 테이블 생성
CREATE TABLE `user` (
    `user_no` INT NOT NULL AUTO_INCREMENT,
    `user_id` VARCHAR(200) NOT NULL,
    `user_password` VARCHAR(500) NOT NULL,
    `user_email` VARCHAR(50),
    `user_name` VARCHAR(50),
    `user_birthday` date,
    `user_gender` CHAR(3),
    `user_address` VARCHAR(50),
    `user_detail_address` VARCHAR(200),
    `user_zip_code` INT,
    `user_phone_number` VARCHAR(15),
    `user_age` INT,
    `user_nickname` VARCHAR(50),
    `user_reg_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `user_login_date` DATETIME,
    `user_photo` VARCHAR(500),
    `user_role` VARCHAR(10),
    `user_iat` VARCHAR(50),
    `user_exp` VARCHAR(255),
    PRIMARY KEY (`user_no`)
);

-- 4. City 테이블 생성
CREATE TABLE `city` (
    `city_no` INT NOT NULL AUTO_INCREMENT,
    `city_name` VARCHAR(100),
    PRIMARY KEY (`city_no`)
);

-- 5. District 테이블 생성
CREATE TABLE `district` (
    `district_no` INT NOT NULL AUTO_INCREMENT,
    `city_no` INT NOT NULL,
    `district_name` VARCHAR(100),
    PRIMARY KEY (`district_no`),
    FOREIGN KEY (`city_no`) REFERENCES `city` (`city_no`) ON DELETE CASCADE
);

-- 6. Race 테이블 생성
CREATE TABLE `race` (
    `race_no` INT NOT NULL AUTO_INCREMENT,
    `race_name` VARCHAR(300),
    `race_date` DATE,
    `race_place` VARCHAR(300),
    `race_distance` VARCHAR(300),
    `race_url` VARCHAR(2048),
    `user_no` INT NOT NULL,
    `race_reg_date` DATE,
    `city_no` INT NOT NULL,
    PRIMARY KEY (`race_no`),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`) ON DELETE CASCADE,
    FOREIGN KEY (`city_no`) REFERENCES `city` (`city_no`) ON DELETE CASCADE
);

-- 7. Board 테이블 생성
CREATE TABLE `board` (
    `board_no` INT NOT NULL AUTO_INCREMENT,
    `board_file_no` VARCHAR(200),
    `board_title` VARCHAR(1000),
    `board_content` VARCHAR(5000),
    `board_reg_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `board_view_cnt` INT DEFAULT 0,
    `user_no` INT NOT NULL,
    PRIMARY KEY (`board_no`)
    -- FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`) ON DELETE CASCADE
);

-- 8. Files 테이블 생성
CREATE TABLE `files` (
    `file_no` VARCHAR(40) NOT NULL,
    `file_name` VARCHAR(40),
    `file_path` VARCHAR(100),
    `board_no` INT NOT NULL,
    `file_system_name` VARCHAR(100),
    PRIMARY KEY (`file_no`),
    FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE CASCADE
);

-- 9. Community_comment 테이블 생성
CREATE TABLE `community_comment` (
    `c_comment_no` INT NOT NULL AUTO_INCREMENT,
    `c_comment_content` VARCHAR(300) NOT NULL,
    `c_comment_reg_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `user_no` INT NOT NULL,
    `board_no` INT NOT NULL,
    PRIMARY KEY (`c_comment_no`),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`) ON DELETE CASCADE,
    FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE CASCADE
);

-- 10. Crew 테이블 생성
CREATE TABLE `crew` (
    `crew_no` INT NOT NULL AUTO_INCREMENT,
    `crew_name` VARCHAR(100) NOT NULL,
    `crew_size` INT NOT NULL,
    `district_no` INT NOT NULL,
    `city_no` INT NOT NULL,
    `crew_content` VARCHAR(5000) NOT NULL,
    `crew_url` VARCHAR(2048),
    `crew_day` VARCHAR(255),
    PRIMARY KEY (`crew_no`),
    FOREIGN KEY (`district_no`) REFERENCES `district` (`district_no`) ON DELETE CASCADE,
    FOREIGN KEY (`city_no`) REFERENCES `city` (`city_no`) ON DELETE CASCADE
);

-- 11. Magazine 테이블 생성
CREATE TABLE `magazine` (
    `magazine_no` INT NOT NULL AUTO_INCREMENT,
    `magazine_title` VARCHAR(400),
    `magazine_writer` VARCHAR(20),
    `magazine_content` VARCHAR(5000),
    `magazine_reg_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `magazine_theme` VARCHAR(200),
    `magazine_view_cnt` INT DEFAULT 0,
    `user_no` INT NOT NULL,
    `magazine_thumbnail` VARCHAR(1000),
    PRIMARY KEY (`magazine_no`)
    -- FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`) ON DELETE CASCADE 
);

-- 12. Magazine_comment 테이블 생성
CREATE TABLE `magazine_comment` (
    `m_comment_no` INT NOT NULL AUTO_INCREMENT,
    `m_comment_content` VARCHAR(300) NOT NULL,
    `m_comment_reg_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `user_no` INT NOT NULL,
    `magazine_no` INT NOT NULL,
    PRIMARY KEY (`m_comment_no`),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`) ON DELETE CASCADE,
    FOREIGN KEY (`magazine_no`) REFERENCES `magazine` (`magazine_no`) ON DELETE CASCADE
);

-- 13. 데이터 삽입
INSERT INTO `user` (`user_id`, `user_password`, `user_email`, `user_name`, `user_birthday`, `user_gender`, `user_address`, `user_detail_address`, `user_zip_code`, `user_phone_number`, `user_age`, `user_nickname`, `user_photo`, `user_role`, `user_iat`, `user_exp`)
VALUES 
('user01', 'password123', 'user01@example.com', 'John Doe', '19900101', 'M', 'Seoul', 'Gangnam', 12345, '010-1234-5678', 30, 'johnny', 'path/to/photo.jpg', 'USER', 'some_iat', 'some_exp'),
('user02', 'password456', 'user02@example.com', 'Jane Smith', '19920304', 'F', 'Busan', 'Haeundae', 67890, '010-2345-6789', 28, 'janey', 'path/to/photo2.jpg', 'USER', 'some_iat', 'some_exp'),
('qwe', '123', 'user02@example.com', 'Jane Smith', '19920304', 'F', 'Busan', 'Haeundae', 67890, '010-2345-6789', 28, 'janey', 'path/to/photo2.jpg', 'USER', 'some_iat', 'some_exp');

INSERT INTO `city` (`city_name`) VALUES ('Seoul'), ('Busan');

INSERT INTO `district` (`city_no`, `district_name`) VALUES 
(1, 'Gangnam'), (1, 'Hongdae'), (2, 'Haeundae'), (2, 'Seomyeon');

INSERT INTO `race` (`race_name`, `race_date`, `race_place`, `race_distance`, `race_url`, `user_no`, `race_reg_date`, `city_no`)
VALUES 
('Seoul Marathon', '2024-05-01', 'Seoul', '42.195 km', 'http://seoulmarathon.com', 1, '2024-01-01', 1),
('Busan Half Marathon', '2024-06-01', 'Busan', '21.097 km', 'http://busanhalfmarathon.com', 2, '2024-02-01', 2);

INSERT INTO `board` (`board_file_no`, `board_title`, `board_content`, `board_view_cnt`, `user_no`)
VALUES 
('file001', 'Spring Marathon Tips', 'Here are some tips for spring marathon preparation.', 100, 1),
('file002', 'Running Shoes Review', 'A detailed review of the best running shoes for 2024.', 150, 2);

INSERT INTO `crew` (`crew_name`, `crew_size`, `district_no`, `city_no`, `crew_content`, `crew_url`, `crew_day`)
VALUES 
('Seoul Runners', 10, 1, 1, 'A fun crew for daily runs in Seoul.', 'http://seoulrunners.com', 'Every weekend'),
('Busan Runners', 15, 3, 2, 'Join our crew for group runs in Busan.', 'http://busanrunners.com', 'Every weekend');

select * from user;

SELECT * FROM magazine;

INSERT INTO `magazine` (
    `magazine_title`, `magazine_writer`, `magazine_content`, 
    `magazine_reg_date`, `magazine_theme`, `magazine_view_cnt`, 
    `user_no`, `magazine_thumbnail`
) VALUES
('초보자를 위한 달리기 가이드', '홍길동', '달리기를 처음 시작하는 사람들을 위한 기본적인 팁과 준비물을 설명합니다.', NOW(), '런닝', 150, 1, ''),
('마라톤 훈련 계획', '김철수', '마라톤을 준비하는 사람들을 위한 훈련 계획과 전략을 제공합니다.', NOW(), '런닝', 200, 2, ''),
('효과적인 러닝 자세', '이순신', '달리기 자세와 발 착지 기술을 개선하여 부상을 방지하는 방법에 대해 다룹니다.', NOW(), '런닝', 180, 3, ''),
('달리기 전 후 스트레칭', '박영희', '달리기 전후에 필요한 스트레칭 방법과 운동 후 회복을 돕는 팁을 소개합니다.', NOW(), '런닝', 220, 4, ''),
('다이어트를 위한 러닝', '최진호', '달리기를 통한 효과적인 체중 감량 방법과 운동 루틴을 제안합니다.', NOW(), '런닝', 250, 5, ''),
('달리기 속도 향상시키는 방법', '정은영', '달리기 속도를 개선하기 위한 훈련 방법과 테크닉을 설명합니다.', NOW(), '런닝', 130, 6, ''),
('겨울철 러닝 팁', '김지민', '겨울철 달리기를 위한 옷차림과 안전한 운동을 위한 팁을 제공합니다.', NOW(), '런닝', 160, 7, ''),
('러닝과 심박수', '박민수', '달리기 중 심박수를 관리하는 방법과 그 중요성에 대해 설명합니다.', NOW(), '런닝', 140, 8, ''),
('하프 마라톤 준비하기', '오세훈', '하프 마라톤을 준비하는 사람들을 위한 훈련법과 전략을 소개합니다.', NOW(), '런닝', 170, 9, ''),
('달리기와 정신 건강', '이하늘', '달리기가 정신 건강에 미치는 긍정적인 영향과 이를 통해 얻을 수 있는 장점에 대해 설명합니다.', NOW(), '런닝', 200, 10, '');

