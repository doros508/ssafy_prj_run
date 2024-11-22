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
    `race_reg_date` DATE,
    `city_no` INT NOT NULL,
    PRIMARY KEY (`race_no`)
    -- FOREIGN KEY (`city_no`) REFERENCES `city` (`city_no`) ON DELETE CASCADE 
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
    `crew_lat` DECIMAL(10,8),
    `crew_lng` DECIMAL(11,8),
	`crew_location` varchar(500),
    
    
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
INSERT INTO `city` (`city_no`, `city_name`) VALUES
(1, '서울'),
(2, '대전'),
(3, '부산');

INSERT INTO `district` (`district_no`, `district_name`, `city_no`) VALUES
-- 서울의 구
(1, '강남구', 1),
(2, '강북구', 1),
(3, '서초구', 1),

-- 대전의 구
(4, '유성구', 2),
(5, '서구', 2),
(6, '중구', 2),

-- 부산의 구
(7, '해운대구', 3),
(8, '동래구', 3),
(9, '부산진구', 3);



INSERT INTO `district` (`city_no`, `district_name`) VALUES 
(1, 'Gangnam'), (1, 'Hongdae'), (2, 'Haeundae'), (2, 'Seomyeon');

INSERT INTO `user` (`user_id`, `user_password`, `user_email`, `user_name`, `user_birthday`, `user_gender`, `user_address`, `user_detail_address`, `user_zip_code`, `user_phone_number`, `user_age`, `user_nickname`, `user_photo`, `user_role`, `user_iat`, `user_exp`)
VALUES 
('user01', 'password123', 'user01@example.com', 'John Doe', '19900101', 'M', 'Seoul', 'Gangnam', 12345, '010-1234-5678', 30, 'johnny', 'path/to/photo.jpg', 'USER', 'some_iat', 'some_exp'),
('user02', 'password456', 'user02@example.com', 'Jane Smith', '19920304', 'F', 'Busan', 'Haeundae', 67890, '010-2345-6789', 28, 'janey', 'path/to/photo2.jpg', 'USER', 'some_iat', 'some_exp'),
('qwe', '123', 'user02@example.com', 'Jane Smith', '19920304', 'F', 'Busan', 'Haeundae', 67890, '010-2345-6789', 28, 'janey', 'path/to/photo2.jpg', 'USER', 'some_iat', 'some_exp');

INSERT INTO `crew` 
(`crew_name`, `crew_size`, `district_no`, `city_no`, `crew_content`, `crew_url`, `crew_day`, `crew_lat`, `crew_lng`, `crew_location`) 
VALUES
('서울 Innovators', 8, 1, 1, '기술과 개발 혁신을 목표로 하는 팀입니다.', 'https://example.com/seoul-innovators', '월, 수, 금', 37.5665, 126.9780, '서울, 대한민국'),
('대전 Pioneers', 6, 3, 2, '새로운 도전과 혁신을 추구하는 크루입니다.', 'https://example.com/daejeon-pioneers', '화, 목, 토', 36.3508, 127.3845, '대전, 대한민국'),
('부산 Waves', 10, 4, 3, '활기찬 활동을 즐기는 크루입니다.', 'https://example.com/busan-waves', '월, 수, 일', 35.1796, 129.0756, '부산, 대한민국');

INSERT INTO `crew` 
(`crew_name`, `crew_size`, `district_no`, `city_no`, `crew_content`, `crew_url`, `crew_day`, `crew_lat`, `crew_lng`, `crew_location`) 
VALUES
-- 서울 크루 추가
('강남 러너스', 12, 1, 1, '강남 지역에서 함께 달리기를 즐기는 크루입니다.', 'https://example.com/gangnam-runners', '월, 수, 금', 37.4979, 127.0276, '서울 강남구'),
('강북 에너자이저', 10, 2, 1, '강북에서 활기차게 활동하는 러닝 크루입니다.', 'https://example.com/gangbuk-energizers', '화, 목, 토', 37.6396, 127.0257, '서울 강북구'),
('서초 스트라이더스', 8, 3, 1, '서초구를 기반으로 하는 달리기 동호회입니다.', 'https://example.com/seocho-striders', '화, 금, 일', 37.4835, 127.0323, '서울 서초구'),
('홍대 나이트 러너', 15, 1, 1, '밤에 달리는 것을 좋아하는 홍대 크루입니다.', 'https://example.com/hongdae-night-runners', '월, 목, 토', 37.5502, 126.9205, '서울 마포구'),
('잠실 스프린터스', 20, 1, 1, '잠실 종합운동장에서 훈련하는 스프린터 크루입니다.', 'https://example.com/jamsil-sprinters', '수, 금, 일', 37.5133, 127.1020, '서울 송파구'),

-- 대전 크루 추가
('유성 피닉스', 10, 4, 2, '유성구에서 활발히 활동하는 러닝 크루입니다.', 'https://example.com/yuseong-phoenix', '월, 수, 토', 36.3623, 127.3561, '대전 유성구'),
('서구 익스트림 러너스', 8, 5, 2, '서구에서 모험적인 러닝을 즐기는 팀입니다.', 'https://example.com/seogu-extreme', '화, 금, 일', 36.3511, 127.3780, '대전 서구'),
('중구 마라토너스', 12, 6, 2, '중구에서 마라톤을 준비하는 크루입니다.', 'https://example.com/junggu-marathoners', '화, 목, 토', 36.3193, 127.4168, '대전 중구'),
('대전 새벽 러너스', 6, 4, 2, '새벽에 달리기를 즐기는 대전 크루입니다.', 'https://example.com/daejeon-dawn-runners', '월, 수, 금', 36.3552, 127.3860, '대전 유성구'),
('대전 밤 러너스', 8, 5, 2, '밤에 대전을 달리는 크루입니다.', 'https://example.com/daejeon-night-runners', '월, 목, 토', 36.3516, 127.3794, '대전 서구');



INSERT INTO `race` (`race_name`, `race_date`, `race_place`, `race_distance`, `race_url`, `race_reg_date`, `city_no`) VALUES
('서울 마라톤 대회', '2024-11-03', '서울 잠실종합운동장', '풀코스, 하프코스, 10km', 'http://seoulmarathon.kr', '2024-10-20', 101),
('부산 국제 마라톤', '2024-11-05', '부산 아시아드 경기장', '풀코스, 10km', 'http://busanmarathon.com', '2024-10-20', 102),
('제주 에코 마라톤', '2024-11-10', '제주 월드컵경기장', '10km, 5km', 'http://jejumarathon.co.kr', '2024-10-22', 103),
('대전 시민 마라톤 대회', '2024-11-12', '대전 한밭종합운동장', '하프코스, 10km', 'http://daejeonmarathon.kr', '2024-10-22', 104),
('강릉 평화 마라톤', '2024-11-15', '강릉 강문해변', '10km, 5km', 'http://gangneungmarathon.com', '2024-10-23', 105),
('무안 생태길 마라톤', '2024-11-18', '무안 청계천 생태길', '10km, 5km', 'http://muanecopathmarathon.kr', '2024-10-24', 106),
('인천 바다길 마라톤', '2024-11-20', '인천 소래포구', '풀코스, 하프코스', 'http://incheonmarathon.com', '2024-10-25', 107),
('춘천 호수 마라톤', '2024-11-22', '춘천 의암호', '하프코스, 10km', 'http://chuncheonmarathon.kr', '2024-10-25', 108),
('전주 한옥길 마라톤', '2024-11-24', '전주 한옥마을', '10km, 5km', 'http://jeonjuhanokmarathon.kr', '2024-10-26', 109),
('울산 태화강 마라톤', '2024-11-26', '울산 태화강 국가정원', '풀코스, 10km', 'http://ulsanmarathon.kr', '2024-10-27', 110),
('포항 해안도로 마라톤', '2024-11-28', '포항 영일만 해안도로', '10km, 5km', 'http://pohangmarathon.kr', '2024-10-28', 111),
('광주 무등산 마라톤', '2024-11-30', '광주 무등산', '하프코스, 10km', 'http://gwangjumarathon.kr', '2024-10-29', 112),
('수원 화성길 마라톤', '2024-12-01', '수원 화성행궁', '10km, 5km', 'http://suwonmarathon.kr', '2024-10-30', 113),
('평창 대관령 마라톤', '2024-12-03', '평창 대관령', '풀코스, 하프코스', 'http://pyeongchangmarathon.kr', '2024-10-31', 114),
('여수 바다빛 마라톤', '2024-12-05', '여수 오동도', '10km, 5km', 'http://yeosumarathon.kr', '2024-11-01', 115),
('성남 탄천길 마라톤', '2024-12-07', '성남 탄천', '10km, 5km', 'http://seongnammarathon.kr', '2024-11-02', 116),
('안산 갈대밭 마라톤', '2024-12-09', '안산 갈대습지공원', '10km, 5km', 'http://ansanmarathon.kr', '2024-11-03', 117),
('김해 봉하마을 마라톤', '2024-12-11', '김해 봉하마을', '하프코스, 10km', 'http://gimhaemarathon.kr', '2024-11-04', 118),
('대구 달구벌 마라톤', '2024-12-13', '대구 달성공원', '풀코스, 10km', 'http://daegumarathon.kr', '2024-11-05', 119),
('양산 황산공원 마라톤', '2024-12-15', '양산 황산공원', '10km, 5km', 'http://yangsanmarathon.kr', '2024-11-06', 120);


INSERT INTO `board` (`board_file_no`, `board_title`, `board_content`, `user_no`) VALUES
('file001', '마라톤 참가 꿀팁 공유', '처음 마라톤에 참가하시는 분들께 준비 운동과 수분 보충의 중요성을 알려드리고자 합니다.', 1),
('file002', '서울 마라톤 후기', '2024 서울 마라톤에 참가한 후기를 공유합니다. 많은 분들과 함께 뛰며 좋은 추억을 만들었습니다.', 2),
('file003', '제주 에코 마라톤 정보', '제주의 아름다운 자연을 배경으로 달리는 에코 마라톤을 소개합니다. 참가 팁도 함께 알려드려요.', 3),
('file004', '대전 마라톤 참가 준비', '대전 마라톤 참가를 위한 체력 관리 및 훈련 방법을 공유합니다.', 4),
('file005', '강릉 마라톤 완주 성공!', '강릉 마라톤에서 처음으로 풀코스를 완주했습니다. 느낀 점을 나누고 싶어요.', 5),
('file006', '마라톤 초보자를 위한 준비물 리스트', '마라톤에 처음 참가하시는 분들을 위해 준비해야 할 필수 아이템 리스트를 작성했습니다.', 6),
('file007', '인천 마라톤 풍경 사진', '인천 마라톤에서 촬영한 멋진 풍경 사진을 공유합니다.', 7),
('file008', '춘천 호수 마라톤 경험담', '춘천 호수 마라톤에 참가하며 겪었던 일과 느낀 점을 나눕니다.', 8),
('file009', '전주 한옥길 마라톤 일정', '전주 한옥길 마라톤 일정과 참가 정보를 알려드립니다.', 9),
('file010', '울산 태화강 마라톤 후기', '태화강의 멋진 풍경을 달리며 느꼈던 감정을 공유합니다.', 10),
('file011', '포항 해안도로 마라톤 소개', '포항 해안도로에서 열리는 마라톤의 매력을 소개합니다.', 11),
('file012', '광주 무등산 마라톤 훈련 계획', '무등산 마라톤을 대비한 훈련 계획을 작성해 보았습니다.', 12),
('file013', '수원 화성길 마라톤 경로 분석', '수원 화성길 마라톤 코스를 분석하여 완주 전략을 공유합니다.', 13),
('file014', '평창 대관령 마라톤 준비 과정', '대관령 마라톤 준비를 위해 했던 체력 관리와 연습 기록을 공유합니다.', 14),
('file015', '여수 바다빛 마라톤 참가 모집', '여수 마라톤 참가를 독려하기 위해 홍보 글을 작성했습니다.', 15),
('file016', '성남 탄천길 마라톤 후기', '성남 탄천길에서 열린 마라톤에 참가하며 느낀 점을 나눕니다.', 16),
('file017', '안산 갈대밭 마라톤 예상 날씨', '안산 갈대밭 마라톤 당일 예상 날씨와 준비물 체크리스트를 공유합니다.', 17),
('file018', '김해 봉하마을 마라톤 개최 소식', '김해에서 열리는 봉하마을 마라톤 소식을 공유합니다.', 18),
('file019', '대구 달구벌 마라톤 참가 후기', '대구 마라톤에서의 도전과 성공 스토리를 공유합니다.', 19),
('file020', '양산 황산공원 마라톤 코스 리뷰', '양산 마라톤 코스를 미리 뛰어보며 느낀 점을 정리해 보았습니다.', 20);



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

select * from city;
select * from crew;
