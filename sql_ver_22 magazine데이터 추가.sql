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
    `user_nickname` VARCHAR(100),
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
    `crew_lat` varchar(20),
    `crew_lng` varchar(20),
   `crew_location` varchar(500),
    
    
    PRIMARY KEY (`crew_no`),
    FOREIGN KEY (`district_no`) REFERENCES `district` (`district_no`) ON DELETE CASCADE,
    FOREIGN KEY (`city_no`) REFERENCES `city` (`city_no`) ON DELETE CASCADE
);

CREATE TABLE `magazine_theme` (
   `theme_no` INT PRIMARY KEY AUTO_INCREMENT,
    `theme_name` VARCHAR(20) NOT NULL
);

CREATE TABLE `magazine` (
    `magazine_no` INT NOT NULL AUTO_INCREMENT,
    `magazine_title` VARCHAR(400),
    `magazine_writer` VARCHAR(20),
    `magazine_content` VARCHAR(5000),
    `magazine_reg_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `theme_no` INT,
    `magazine_view_cnt` INT DEFAULT 0,
    `user_no` INT NOT NULL,
    `magazine_thumbnail` VARCHAR(1000),
    PRIMARY KEY (`magazine_no`),
    FOREIGN KEY (`theme_no`) REFERENCES `magazine_theme` (`theme_no`)
    -- FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`) ON DELETE CASCADE 
);



INSERT INTO `magazine_theme` (`theme_name`) VALUES
('뉴스'),
('러닝 팁'),
('건강');

INSERT INTO `magazine` (
    `magazine_title`, `magazine_writer`, `magazine_content`, 
    `magazine_reg_date`, `theme_no`, `magazine_view_cnt`, 
    `user_no`, `magazine_thumbnail`
) VALUES
('러닝화는 언제 교체 해야 될까?', '조상래', '러닝화는 단순한 신발이 아니라, 러너의 발을 보호하고 운동 성능을 향상시키는 필수 장비다. 시작 단계에서는 기존 신발을 활용하더라도 꾸준히 이어가려면 적절한 러닝화 선택을 하는 것이 운동의 질을 결정짓고 부상 위험을 낮추는 요소다.

비전문적인 신발 착용은 발에 부담을 줄 수 있다. 일상용 신발이나 오래된 스포츠 신발로 달리기를 지속한다면 발의 불편함과 부상 위험이 커지게 된다. 전문적인 러닝화는 개개인의 달리기 스타일에 맞는 지지력과 충격 완화 기능을 제공하여 안전하게 운동할 수 있는 환경을 조성한다.

많은 이들이 놓치기 쉬운 한 가지 중요한 요소가 바로 러닝화의 관리와 교체 시기다. 아무리 애착이 가고 편안한 러닝화라 할지라도, 지속적인 사용은 필연적으로 마모를 초래하며, 결국에는 발목, 무릎, 고관절 등 중요한 관절 부위에 부상을 입힐 수 있는 위험성이 커진다.

일반적으로 로드 러닝화는 대략 500~800km, 경량 러닝화는 약 400~480km 범위에서 교체가 권장된다. 주당 32km를 달린다고 가정할 때, 이는 대략 4~6개월마다 신발을 교체해야 한다는 계산이 나온다.

하지만 신발의 수명은 단순히 거리에 의해서만 결정되는 것이 아니다. 달리기 환경, 개인의 달리기 스타일 및 체형 등 여러 요소가 영향을 준다.

첫째로, 도로나 트레일과 같이 다양한 지형에서 달릴 때 특정 지형에 맞춰 설계된 신발을 선택하는 것이 중요하다. 예를 들어, 로드용 신발로 비포장 도로를 달릴 경우 신발의 마모가 가속될 수 있다.

둘째로, 자신만의 독특한 달리기 스타일 역시 신발 선택에 있어 고려해야 할 요소 중 하나다. 발의 착지 방식은 신발 마모 패턴에 큰 영향을 미친다. 예를 들어, 앞발 착지 형태를 보이는 이들은 신발 앞부분과 외곽 부분의 마모가 가속되며 이로 인해 새 신발 구매 시 고려해야 할 사항들이 있다.

셋째로, 체중과 키가 ‘평균적인’ 범주에서 벗어난 경우에도 신발 마모 속도와 패턴이 달라질 수 있으며 이 경우 좀더 자주 교체할 필요성이 있다.

신발 교체 시점을 감지하는 몇 가지 징조로는 설명하기 어려운 새로운 통증 발생, 마모된 밑창 트레드, 딱딱해진 중창 등이 있다. 기존 제품과 비교하여 확연한 차이를 보여주면 그것은 분명 교체 시점임을 의미한다.

정강이나 발에 통증이 발생한다면, 이는 신발 교체 시점일 수 있다. 쿠셔닝 성능 저하로 인한 충격 흡수력 감소는 관절과 근육에 과도한 스트레스를 주며, 이러한 경고 신호를 간과해서는 안 될 것이다.

밑창의 마모 및 찢어짐 역시 교체 필요성을 알리는 명확한 징조다. 밑창은 운동 시 안정성을 제공하는 핵심 구성요소이며, 마모된 상태에서의 사용은 접지력 저하와 함께 전반적인 신발 구조에 악영향을 끼친다.

반복되는 물집 형성도 신발 변경이 필요함을 의미한다. 올바른 핏의 부재와 마찰 유발은 잦은 물집으로 이어지며, 발 모양과 달리기 방식에 맞지 않는 신발 선택은 추가 문제를 초래할 수 있다.

쿠셔닝 기능 저하는 충격 완화 기능 감소와 연관되어 있으며, 이 역시 교체해야 할 중요한 시점임을 나타낸다. 쿠셔닝 손실은 피로도 상승 및 부상 위험 증가로 직결된다.

러닝화를 오래 사용하기 위해서는 몇 가지 관리 방법을 따르는 것도 유효하다. 예를 들면 운동 목적 외 사용 자제, 정확한 방법으로 신발끈 조정 및 결속 등이 그것이다.

정기적으로 러닝화 상태를 점검하고 필요 시 적극적으로 교체하는 것은 부상 예방과 개선효과를 가져오며, 더욱 안전하고 만족스러운 달리기 경험을 가능하게 한다.', NOW(), 1, 150, 1, ''),
('국가대표가 러닝 꿀팁 알려드려요', '온종림 기자', '[대학저널 온종림 기자] 건국대학교가 21일 교내 대운동장에서 ‘건국인과 함께하는 러닝클래스’ 행사를 개최했다.


이번 행사는 전국적으로 러닝 열풍이 뜨거운 가운데, 건국대 체육부가 교내 구성원에게 전문적인 러닝 팁을 배우는 기회를 제공함으로써 건강한 생활 습관을 장려하고자 기획됐다.

이날 행사에는 건국대 학생·교직원 등 100여 명이 참석했다. 클래스의 일일 강사로는 체육부 엄효석(전 국가대표, 대한민국 하프마라톤 기록 보유) 육상코치, 백승호(고양시청, 현 5,000m 한국기록 보유) 동문, 이동진(대구시청, 2024 대구마라톤 우승) 동문, 신용민(서귀포시청) 동문과 건국대 육상부 재학생 선수들이 활약했다.

행사에 앞선 축사에서 건국대 원종필 총장은 “이번 행사가 참석 인원 모집을 시작하자마자 마감이 될 만큼 큰 인기였다고 들었다”며, “러닝에 높은 관심이 쏠리는 지금, 전국 최고의 건국대 육상팀의 개인 지도를 받으며 즐거운 시간을 보냈으면 한다”고 말했다.

강사진의 체조 시범을 따라 해보는 ‘워밍업’으로 시작된 이번 프로그램은, 반복적인 동작을 수행하는 훈련을 통해 근력과 민첩성, 유연성을 기르는 ‘드릴운동’을 거쳐 ‘조별 러닝과 자세 교정’ 시간으로 이어진 뒤 ‘쿨다운 스트레칭’으로 마무리됐다. 참가자들은 전문가들의 코칭 하에 러닝의 기본기를 배울 수 있었던 이번 행사에 높은 만족감을 표했다.

출처 : 대학저널(https://dhnews.co.kr)', NOW(), 2, 200, 2, ''),
('마라톤 식단은 어떻게 짜야할까?', '기봉이', '여러 스포츠 영양학 연구 결과에 따르면, 마라톤 선수들의 성과 향상과 회복을 위해 체계적인 식단 계획이 매우 중요한 것으로 나타났다. 본 가이드라인은 최신 연구를 바탕으로 마라톤 선수들을 위한 종합적인 영양 전략을 제시한다.

1. 에너지 요구량 평가

마라톤 선수의 에너지 요구량은 훈련 강도와 개인의 특성에 따라 다르다5. 일반적으로 마라톤 선수들은 하루에 체중 1kg당 5-7g의 탄수화물을 섭취해야 한다5. 이는 총 에너지 섭취량의 55-65%에 해당한다.

2. 탄수화물 중심의 식단 구성

탄수화물은 마라톤 선수의 주요 에너지원이다. 국제 스포츠 영양학회(ISSN)의 최신 보고서에 따르면, 마라톤 선수들의 식단에서 탄수화물이 차지하는 비중이 결정적인 것으로 나타났다.

엘리트 마라톤 선수들의 식단 연구에 따르면, 탄수화물 섭취량은 총 에너지 섭취량의 64.3 ± 2.6%를 차지하며, 이는 체중 1kg당 9.7 ± 0.9g에 해당한다12. 이는 대부분의 지구력 선수들을 위한 권장사항을 충족시키는 수준이다.

이 수준의 탄수화물 섭취는 마라톤 선수들의 글리코겐 저장량을 최적화하여 지구력 향상에 직접적인 영향을 미치는 것으로 분석되었다.

3. 단백질 섭취 최적화

단백질 섭취는 근육 회복과 유지에 중요하다. 엘리트 선수들의 단백질 섭취량은 총 에너지 섭취량의 12.4 ± 0.6%로, 체중 1kg당 1.8 ± 0.2g이었다12. 이는 일반적으로 권장되는 범위 내에 있다.

4. 적절한 지방 섭취

지방 섭취는 총 에너지 섭취량의 23.3 ± 2.1%를 차지했다12. 이는 필수 지방산 공급과 지용성 비타민 흡수를 위해 중요하다.

5. 수분 섭취 전략 수립

수분 섭취는 마라톤 선수들의 성과에 큰 영향을 미친다. 엘리트 마라톤 선수들은 레이스 중 평균적으로 시간당 0.55L의 수분을 섭취하는 것으로 나타났다. 이는 미국 스포츠 의학회(ACSM)의 권장 섭취량인 시간당 0.4-0.8L와 일치하는 수준이다.

훈련 중 수분 섭취율은 시간당 0.4 – 0.8L가 권장된다. 개인의 땀 배출량과 환경 조건에 따라 조절이 필요하다.

6. 식사 타이밍 조정

훈련 전: 훈련 2-3시간 전에 주요 식사를 섭취한다.
훈련 중: 장시간 훈련 시 60-90분마다 30-60g의 탄수화물을 섭취한다.
훈련 후: 훈련 종료 후 30분 이내에 회복 식사나 간식을 섭취한다.

이 ‘골든 타임’을 활용하면 근육 회복과 글리코겐 재합성을 효과적으로 촉진할 수 있다는 것이 연구를 통해 밝혀졌다.

7. 개인화된 영양 전략 개발

각 선수의 개별적인 요구사항, 선호도, 그리고 소화 능력에 따라 식단을 조정해야 한다5. 이는 트레이닝 적응과 경기 성과 최적화에 중요하다.

8. 영양 보충제 고려

일부 영양 보충제는 마라톤 선수들의 성과를 향상시킬 수 있다:

카페인: 운동 성능을 향상시킬 수 있다.
비타민과 미네랄: 전반적인 건강과 면역 기능을 지원한다.
전해질 보충제: 장시간 훈련 시 전해질 균형 유지에 도움이 될 수 있다.

9. 주기화된 영양 접근법

훈련 주기에 맞춰 영양 섭취를 조절하는 것이 중요하다:

고강도 훈련 기간: 탄수화물 섭취량을 증가시킨다.
테이퍼링 기간: 에너지 섭취를 조절하여 체중 관리에 주의한다.
경기 당일: 개인에게 맞는 경기 전 식사 전략을 수립한다.

10. 지속적인 모니터링 및 조정

정기적으로 체중, 체성분, 에너지 수준, 그리고 성과를 모니터링하고, 필요에 따라 식단을 조정한다5. 이는 최적의 훈련 적응과 경기 성과를 위해 중요하다.

마치며

마라톤 훈련에 적합한 식단 계획은 과학적 원칙을 기반으로 하되, 개인의 특성과 목표에 맞게 조정되어야 한다5. 탄수화물 중심의 식단, 적절한 단백질과 지방 섭취, 그리고 개인화된 수분 섭취 전략이 핵심이다.

또한, 영양사나 스포츠 영양 전문가와의 상담을 통해 더욱 정교한 식단 계획을 수립할 수 있다. 지속적인 모니터링과 조정을 통해 최적의 훈련 효과와 경기 성과를 달성할 수 있다.

이번 연구 결과는 마라톤 선수들의 영양 섭취에 대한 새로운 지침을 제시하고 있으며, 향후 선수들의 경기력 향상에 큰 도움이 될 것으로 기대된다.

[참조]

-Stellingwerff, T. (2015). Carbohydrate Dominate Fueling Strategies Employed By Three World-class Ultra-marathon Runners: A Case-study. Medicine & Science in Sports & Exercise. 

-Beis, L. (2012). An evidence-based approach to the application of the science of sports and exercise nutrition to optimising sporting performance.', NOW(), 3, 180, 3, ''),
('초보 러너를 위한 최적의 러닝화 선택 가이드', '조상래', '러닝은 전문 신발 착용이 필수적인 스포츠로, 일반 운동화와는 차별화된 특수 설계가 적용된 러닝화가 요구된다. 러닝화는 주행 시 발생하는 충격을 흡수하고 발의 움직임을 지지하는 기능을 갖추고 있어, 특히 초보 러너들에게 적합한 신발 선택이 중요하다.

전문가들은 초보자들이 러닝화를 구매할 때 편안함을 최우선으로 고려해야 한다고 조언한다. “발에 잘 맞고 착용감이 좋은 신발을 선택하면 부상 위험을 줄이고 러닝을 더 오래 즐길 수 있습니다,” 라고 한 러닝 코치는 말한다. 또한 초보자들에게는 과도한 쿠션이나 지지력보다는 중간 정도의 쿠션과 지지력을 갖춘 신발이 권장된다.

러닝화 선택 시 고려해야 할 주요 요소로는 발 모양, 러닝 스타일, 주로 달리는 지형, 그리고 가격대 등이 있다. 전문 매장에서 발 분석을 받아보는 것도 도움이 될 수 있으며, 도로 주행용과 트레일 주행용 신발의 특성이 다르므로 주로 달리는 환경에 맞는 신발을 선택하는 것이 중요하다.

가격 측면에서는 초보자들에게 중간 가격대의 신발이 추천된다. 이는 러닝에 대한 적응과 개인의 선호도를 파악하는 데 도움이 된다. 구매 시에는 여러 브랜드와 모델을 비교해보고, 가능하다면 실제로 신어보는 것이 좋다. 온라인 구매 시에는 반품 정책을 꼭 확인해야 한다.

기술적 측면에서 러닝화의 주요 구성 요소인 어퍼, 미드솔, 힐컵, 힐드롭에 대한 이해가 필요하다. 통기성이 우수한 메쉬나 니트 소재의 어퍼, 충분한 두께와 탄성을 가진 미드솔, 단단한 소재의 힐컵이 권장된다. 힐드롭은 개인의 신체 상태에 따라 선택해야 하며, 초보자에게는 8-10mm의 중간 정도 힐드롭이 적합하다.

사이즈 선택에 있어서는 일반 신발보다 약간 크게 신는 것이 좋다. 러닝 전문가들은 “달리는 동안 발이 부어오르기 때문에 발가락 앞쪽에 손톱 길이만큼의 여유 공간을 두는 것이 이상적입니다,”라고 조언한다. 발볼 또한 중요한 고려 사항으로, 일반적으로 남성은 D나 2E, 여성은 B나 D 사이즈가 적합하다고 알려져 있다.

결론적으로, 러닝화 선택은 개인의 발 구조와 주행 스타일에 따라 달라질 수 있다. 따라서 전문가의 조언을 참고하되, 실제로 신어보고 자신에게 가장 편안한 모델을 선택하는 것이 중요하다. 적절한 러닝화 선택은 부상 예방과 러닝 성과 향상에 크게 기여할 수 있다.', NOW(), 1, 220, 4, '');







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
(10, '갑천 러너스', 17, 4, 2, '매주 2회 갑천뛰는 대전 대표 러닝크루입니다. ', 'https://open.kakao.com/o/gsF6WN1g', '월,수', 36.3654, 127.3645, '갑천 내부 (별도공지)'),
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


INSERT INTO race (race_name, race_date, race_place, race_distance, race_url, race_reg_date, city_no) VALUES 
-- 서울 (city_no: 1)
('Seoul Autumn Marathon', '2024-09-15', 'Seoul Olympic Stadium', 'FULL, HALF, 10km', 'https://www.seoul-autumn-marathon.com', '2024-08-30', 1),
('Namsan Hill Climb Race', '2024-10-05', 'Seoul Namsan Park', '10km, 5km', 'https://www.namsan-hillrace.kr', '2024-09-20', 1),
('Seoul Han River Marathon', '2024-10-25', 'Seoul Hangang Park', 'FULL, 10km', 'https://www.hangang-marathon.com', '2024-10-01', 1),

-- 대전 (city_no: 2)
('Daejeon Autumn Classic', '2024-09-10', 'Daejeon Expo Park', 'HALF, 10km', 'https://www.daejeon-autumn-classic.kr', '2024-08-25', 2),
('Hanbat Trail Marathon', '2024-10-18', 'Daejeon Hanbat Arboretum', 'FULL, HALF', 'https://www.hanbat-trail-run.kr', '2024-10-01', 2),
('Daejeon Science Park Race', '2024-10-30', 'Daejeon Science Park', '10km, 5km', 'https://www.daejeon-science-run.com', '2024-10-10', 2),

-- 부산 (city_no: 3)
('Haeundae Beach Night Run', '2024-09-20', 'Busan Haeundae Beach', 'HALF, 10km', 'https://www.haeundae-night-run.com', '2024-09-01', 3),
('Busan Autumn Half Marathon', '2024-10-12', 'Busan Asiad Main Stadium', 'HALF, 10km', 'https://www.busan-autumn-half.com', '2024-09-25', 3),
('Gwangan Bridge Marathon', '2024-10-28', 'Busan Gwangan Bridge', 'FULL, HALF, 10km', 'https://www.gwangan-bridge-marathon.com', '2024-10-10', 3),

-- 추가 서울 (city_no: 1)
('Gwanak Mountain Trail Race', '2024-11-12', 'Seoul Gwanak Mountain', 'HALF, 10km', 'https://www.gwanak-trail-run.kr', '2024-10-20', 1),
('Seoul Night Lights Run', '2024-09-25', 'Seoul City Center', '10km, 5km', 'https://www.seoul-night-run.kr', '2024-09-10', 1),

-- 추가 대전 (city_no: 2)
('Daejeon Urban Run', '2024-09-30', 'Daejeon Downtown', 'HALF, 10km', 'https://www.daejeon-urban-run.com', '2024-09-15', 2),
('Daejeon Autumn Festival Run', '2024-11-05', 'Daejeon Expo Plaza', 'FULL, 10km', 'https://www.autumn-festival-run.kr', '2024-10-15', 2),

-- 추가 부산 (city_no: 3)
('Busan Coastal Trail Race', '2024-09-18', 'Busan Songjeong Beach', 'FULL, HALF', 'https://www.busan-coastal-trail.kr', '2024-09-01', 3),
('Busan Lighthouse Run', '2024-11-15', 'Busan Taejongdae Park', '10km, 5km', 'https://www.busan-lighthouse-run.com', '2024-10-20', 3);

INSERT INTO race (race_name, race_date, race_place, race_distance, race_url, race_reg_date, city_no) VALUES 
-- 서울 (city_no: 1)
('서울 국제 마라톤', '2024-11-02', '서울 잠실종합운동장', 'FULL, HALF', 'https://www.seoul-international-marathon.kr', '2024-10-15', 1),
('한강 러닝 페스티벌', '2024-11-04', '서울 여의도 한강공원', 'HALF, 10km, 5km', 'https://www.hangang-running-festival.kr', '2024-10-18', 1),
('서울 산책 마라톤', '2024-11-06', '서울 남산공원', '10km, 5km', 'https://www.seoul-walkathon.kr', '2024-10-20', 1),
('서울 올림픽 마라톤', '2024-11-08', '서울 올림픽공원', 'FULL, HALF, 10km', 'https://www.olympic-marathon.kr', '2024-10-22', 1),
('서울 야간 러닝', '2024-11-14', '서울 시청광장', '10km, 5km', 'https://www.seoul-night-run.kr', '2024-10-25', 1),
('서울 역사 러닝', '2024-11-18', '서울 종로구 광화문광장', 'HALF, 10km', 'https://www.seoul-history-run.kr', '2024-10-28', 1),

-- 대전 (city_no: 2)
('대전 국제 마라톤', '2024-11-01', '대전 한밭종합운동장', 'FULL, HALF', 'https://www.daejeon-international-marathon.kr', '2024-10-12', 2),
('대전 한빛 러닝', '2024-11-07', '대전 한빛탑 광장', 'HALF, 10km, 5km', 'https://www.daejeon-hanbit-run.kr', '2024-10-15', 2),
('대전 가을 러닝 대회', '2024-11-09', '대전 엑스포과학공원', 'FULL, 10km', 'https://www.daejeon-autumn-run.kr', '2024-10-18', 2),
('대전 둔산 마라톤', '2024-11-13', '대전 둔산공원', 'HALF, 10km, 5km', 'https://www.dunsan-marathon.kr', '2024-10-21', 2),
('대전 도심 야간 러닝', '2024-11-20', '대전 은행동 스카이로드', '10km, 5km', 'https://www.daejeon-night-run.kr', '2024-10-25', 2),
('대전 가을 클래식 마라톤', '2024-11-28', '대전 갑천변', 'FULL, HALF, 10km', 'https://www.daejeon-classic-run.kr', '2024-10-30', 2);


INSERT INTO `board` (`board_file_no`, `board_title`, `board_content`, `user_no`) VALUES
('file001', '공지사항', '그동안 다들 고생 하셨습니다 감사합니다!', 1),
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

INSERT INTO `user` (
    `user_id`, `user_password`, `user_email`, `user_name`, 
    `user_birthday`, `user_gender`, `user_address`, `user_detail_address`, 
    `user_zip_code`, `user_phone_number`, `user_age`, `user_nickname`, 
    `user_photo`, `user_role`, `user_iat`, `user_exp`
) VALUES
('admin', 'admin', 'user01@example.com', '홍길동', 
'1990-01-01', 'M', '서울시 강남구', '역삼동 123-45', 
12345, '010-1234-5678', 34, '러너홍',
'', 'USER', 'token123', 'exp123'),

('qwe', '123', 'user02@example.com', '김철수', 
'1992-05-15', 'M', '대전시 유성구', '궁동 456-78', 
34567, '010-2345-6789', 32, '달리는철수',
'', 'USER', 'token456', 'exp456'),

('user03', 'password3', 'user03@example.com', '이영희', 
'1995-08-20', 'F', '부산시 해운대구', '우동 789-10', 
45678, '010-3456-7890', 29, '달림이영희',
'', 'USER', 'token789', 'exp789'),

('user', '123', 'qwe@example.com', '테스트', 
'1994-12-25', 'M', '서울시 마포구', '상암동 123-45', 
23456, '010-9876-5432', 30, '테스트러너',
'', 'USER', 'tokentest', 'exptest');

select * from user;

SELECT * FROM magazine;


select * from user;
select * from city;
select * from crew;
select * from race;
SELECT * FROM board;
SELECT * FROM files;
select * FROM magazine;
