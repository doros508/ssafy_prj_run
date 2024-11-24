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
-- 8. Files 테이블 생성
CREATE TABLE `files` (
    `file_no` VARCHAR(40) NOT NULL,
    `file_name` VARCHAR(40),
    `file_path` VARCHAR(100),
    `board_no` INT,
    `magazine_no` INT,
    `file_system_name` VARCHAR(100),
    PRIMARY KEY (`file_no`),
    FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE CASCADE,
    FOREIGN KEY (`magazine_no`) REFERENCES `magazine` (`magazine_no`) ON DELETE CASCADE
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

-- 13. 데이터 추가 

-- 도시 데이터
INSERT INTO city (city_no, city_name) VALUES 
(1, '서울'),
(2, '대전'),
(3, '부산');

-- 지역(구) 데이터
INSERT INTO district (district_no, district_name, city_no) VALUES 
-- 서울
(1, '강남구', 1),
(2, '마포구', 1),
(3, '종로구', 1),
-- 대전
(4, '유성구', 2),
(5, '서구', 2),
(6, '중구', 2),
-- 부산
(7, '해운대구', 3),
(8, '부산진구', 3),
(9, '동래구', 3);

-- 크루 데이터
INSERT INTO crew VALUES 
-- 서울 크루
(1, '러닝하이', 12, 1, 1, '강남구 역삼동을 기반으로 활동하는 러닝 크루입니다.', 'http://runninghigh.kr', '월,수', 37.4979, 127.0276, '역삼동 707'),
(2, '달림이들', 15, 1, 1, '강남 테헤란로를 달리는 직장인 러닝 크루입니다.', 'http://dalrimee.co.kr', '화,목', 37.5065, 127.0224, '테헤란로 152'),
(3, '런앤런', 18, 1, 1, '선릉공원 중심의 러닝크루입니다.', 'http://runnrun.kr', '토,일', 37.5094, 127.0476, '선릉공원'),

(4, '상암러너스', 14, 2, 1, '상암동 월드컵공원에서 정기적으로 모이는 러닝크루입니다.', 'http://sangamrunners.co.kr', '화,목', 37.5531, 126.8872, '상암동 1676'),
(5, '홍대달리기', 20, 2, 1, '홍대입구역 주변을 달리는 젊은 러닝크루입니다.', 'http://hongrun.com', '수,금', 37.5571, 126.9252, '홍대입구역'),
(6, '마포질주단', 16, 2, 1, '망원한강공원 중심의 한강러닝크루입니다.', 'http://maporun.kr', '토', 37.5526, 126.9012, '망원한강공원'),

(7, '종로러닝클럽', 13, 3, 1, '종로 광화문 일대를 달리는 러닝크루입니다.', 'http://jongnorun.com', '토', 37.5704, 126.9813, '관철동 12-1'),
(8, '청계러너스', 17, 3, 1, '청계천을 따라 달리는 도심 러닝크루입니다.', 'http://streamrun.kr', '일', 37.5689, 126.9786, '청계천로'),
(9, '광화문달리기', 19, 3, 1, '경복궁 돌담길을 달리는 역사탐방 러닝크루입니다.', 'http://gwanghwamun.run', '토,일', 37.5766, 126.9773, '경복궁역'),

-- 대전 크루
(10, '충대러너스', 17, 4, 2, '충남대 정문에서 출발하는 대전 대표 러닝크루입니다.', 'http://cnu-runners.kr', '월,수', 36.3654, 127.3645, '충남대학교 정문'),
(11, 'KAIST런', 15, 4, 2, 'KAIST 캠퍼스를 달리는 러닝크루입니다.', 'http://kaistrun.com', '화,목', 36.3712, 127.3597, 'KAIST 정문'),
(12, '유성달림이', 12, 4, 2, '유성온천역 중심의 러닝크루입니다.', 'http://yuseongrun.kr', '토', 36.3551, 127.3479, '유성온천역'),

(13, '둔산러너스', 16, 5, 2, '둔산대공원을 중심으로 활동하는 러닝크루입니다.', 'http://dunsanrun.kr', '화,목', 36.3513, 127.3769, '둔산대공원'),
(14, '월평러닝', 14, 5, 2, '월평공원을 달리는 러닝크루입니다.', 'http://wolpyeongrun.com', '수,금', 36.3542, 127.3642, '월평공원'),
(15, '서구달리미', 18, 5, 2, '갈마공원 중심의 러닝크루입니다.', 'http://seogurun.kr', '토,일', 36.3474, 127.3778, '갈마공원'),

(16, '으능정이런', 15, 6, 2, '으능정이거리를 달리는 도심 러닝크루입니다.', 'http://eunrun.kr', '월,수', 36.3292, 127.4251, '으능정이거리'),
(17, '중앙로달리기', 19, 6, 2, '중앙로 일대를 달리는 러닝크루입니다.', 'http://jungang-run.com', '화,목', 36.3277, 127.4214, '중앙로역'),
(18, '대전역런', 13, 6, 2, '대전역 주변을 달리는 러닝크루입니다.', 'http://djstationrun.kr', '토', 36.3321, 127.4332, '대전역'),

-- 부산 크루
(19, '해변러너스', 16, 7, 3, '해운대 해수욕장을 달리는 부산의 대표 러닝크루입니다.', 'http://haeundaerun.kr', '수,일', 35.1587, 129.1583, '해운대해수욕장'),
(20, '마린시티런', 14, 7, 3, '마린시티를 달리는 야경 러닝크루입니다.', 'http://marinecityrun.com', '화,목', 35.1545, 129.1486, '마린시티'),
(21, '달맞이런', 18, 7, 3, '달맞이고개를 달리는 러닝크루입니다.', 'http://dalmaji.run', '토', 35.1623, 129.1738, '달맞이고개'),

(22, '서면러너스', 20, 8, 3, '서면 중심가를 달리는 도심 러닝크루입니다.', 'http://seomyeonrun.com', '화,목', 35.1595, 129.0591, '서면역'),
(23, '부산진달리기', 15, 8, 3, '부산진구청 일대를 달리는 러닝크루입니다.', 'http://busanjin-run.kr', '월,수', 35.1621, 129.0555, '부산진구청'),
(24, '범내골런', 17, 8, 3, '범내골 문화거리를 달리는 러닝크루입니다.', 'http://beomrun.com', '토,일', 35.1668, 129.0644, '범내골역'),

(25, '온천러너스', 15, 9, 3, '동래 온천장 일대를 달리는 러닝크루입니다.', 'http://oncheonrun.kr', '월,수', 35.2025, 129.0879, '온천장역'),
(26, '동래달림이', 12, 9, 3, '동래시장 주변을 달리는 러닝크루입니다.', 'http://dongrae-run.com', '화,목', 35.2051, 129.0943, '동래시장'),
(27, '명장런', 16, 9, 3, '명장공원을 중심으로 달리는 러닝크루입니다.', 'http://myeongjang.run', '토', 35.2007, 129.1027, '명장공원');


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
SELECT * FROM board;
SELECT * FROM files;
select * FROM magazine;
