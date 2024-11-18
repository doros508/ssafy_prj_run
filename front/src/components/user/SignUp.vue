<script setup>
import { ref } from "vue";

// 선택된 성별 저장
const selectedGender = ref("");
// 프로필 사진 저장
const profileImage = ref(null);
// 비밀번호와 비밀번호 확인 저장
const password = ref("");
const confirmPassword = ref("");
// 아이디 중복 검사 상태
const isUsernameAvailable = ref(true);
// 별명 중복 검사 상태
const isNicknameAvailable = ref(true);

// 성별 선택 함수
function selectGender(gender) {
  selectedGender.value = gender;
}

// 프로필 사진 업로드 핸들러
function handleProfileImageUpload(event) {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = () => {
      profileImage.value = reader.result; // 이미지 데이터를 base64로 저장
    };
    reader.readAsDataURL(file); // 이미지 파일을 base64로 읽기
  }
}

// 아이디 중복 검사 함수 (임시 구현)
function checkUsernameAvailability() {
  const username = document.querySelector('input[placeholder="아이디"]').value;
  if (username === "takenUsername") {
    isUsernameAvailable.value = false; // 이미 존재하는 아이디
  } else {
    isUsernameAvailable.value = true; // 사용 가능한 아이디
  }
}

// 별명 중복 검사 함수 (아이디 중복검사와 동일한 방식)
function checkNicknameAvailability() {
  const nickname = document.querySelector('input[placeholder="별명"]').value;
  // 서버로 별명 중복 체크 요청 (실제 구현 시 API 호출)
  if (nickname === "takenNickname") {
    isNicknameAvailable.value = false; // 이미 존재하는 별명
  } else {
    isNicknameAvailable.value = true; // 사용 가능한 별명
  }
}

// 인증번호 확인 함수 (임시 구현)
function checkVerificationCode() {
  console.log('인증번호 확인');
}
</script>

<template>
  <div class="signup-container">
    <img class="background-img" src="../../assets/user/sign_up/login_picture.png" />
    <div class="signup-form">
      <h1 class="logo">Sign up</h1>
      <div class="spacing"></div>
      
      
      <!-- 프로필 사진 업로드 -->
      <div class="form-group">
        <label for="profile-image" class="profile-image-label">
          <span v-if="!profileImage" class="upload-text">프로필 사진</span>
          <img v-if="profileImage" :src="profileImage" alt="Profile Image" class="profile-image-preview" />
        </label>
        <input
          type="file"
          id="profile-image"
          accept="image/*"
          class="profile-image-input"
          @change="handleProfileImageUpload"
        />
      </div>

      <!-- 아이디 입력 및 중복검사 -->
      <div class="form-group">
        <input type="text" placeholder="아이디" />
        <button class="check-username-btn" @click="checkUsernameAvailability">아이디 중복 검사</button>
        <p v-if="!isUsernameAvailable" class="error-message">이미 존재하는 아이디입니다.</p>
      </div>

      <!-- 별명 입력 및 중복검사 -->
      <div class="form-group">
        <input type="text" placeholder="별명" />
        <button class="check-nickname-btn" @click="checkNicknameAvailability">별명 중복 검사</button>
        <p v-if="!isNicknameAvailable" class="error-message">이미 존재하는 별명입니다.</p>
      </div>

      <!-- 비밀번호 입력 -->
      <div class="form-group">
        <input type="password" placeholder="비밀번호" v-model="password" />
      </div>

      <!-- 비밀번호 확인 입력 -->
      <div class="form-group">
        <input type="password" placeholder="비밀번호 확인" v-model="confirmPassword" />
        <p v-if="password && confirmPassword && password !== confirmPassword" class="error-message">비밀번호가 일치하지 않습니다.</p>
      </div>

      <!-- 이메일 입력 -->
      <div class="form-group">
        <input type="text" placeholder="이메일" />
        <button class="send-code-btn">인증코드 전송</button>
      </div>

      <!-- 이름 입력 -->
      <div class="form-group">
        <input type="text" placeholder="이름" />
      </div>

      <!-- 생일 입력 -->
      <div class="form-group">
        <input type="date" />
      </div>

      <!-- 성별 선택 -->
      <div class="form-group gender">
        <button
          class="gender-btn"
          :class="{ active: selectedGender === '남자' }"
          @click="selectGender('남자')">
          남자
        </button>
        <button
          class="gender-btn"
          :class="{ active: selectedGender === '여자' }"
          @click="selectGender('여자')">
          여자
        </button>
      </div>

      <!-- 주소 검색 -->
      <div class="form-group">
        <input type="text" placeholder="주소 검색" />
      </div>

      <!-- 전화번호 입력 -->
      <div class="form-group">
        <input type="tel" placeholder="전화번호" />
      </div>

      <!-- 인증번호 확인 버튼 -->
      <div class="form-group">
        <input type="text" placeholder="인증번호 입력" />
        <button class="check-code-btn" @click="checkVerificationCode">확인</button>
      </div>

      <!-- 회원가입 버튼 -->
      <button class="submit-btn">회원가입</button>
    </div>
  </div>
</template>


<style scoped>
.spacing {
  height: 20px; /* 간격을 설정 */
}

/* 기존 스타일 */

.signup-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: rgba(0, 0, 0, 0.5);
  position: relative;
}

.background-img {
  position: absolute;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: -1;
}

.signup-form {
  width: 400px;
  padding: 20px;
  background: rgba(0, 0, 0, 0.7);
  border-radius: 10px;
  color: #fff;
}

.logo {
  font-size: 32px;
  text-align: center;
  font-weight: bold;
}

.title {
  font-size: 24px;
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  display: flex;
  align-items: center;
  justify-content: space-between; /* 버튼과 입력란의 간격 유지 */
  margin-bottom: 15px;
  gap: 10px; /* 입력란과 버튼 사이 간격 */
}

input[type="text"],
input[type="password"],
input[type="date"],
input[type="tel"] {
  flex: 1; /* 입력란이 버튼보다 넓게 차지 */
  padding: 10px;
  border: none;
  border-radius: 5px;
  outline: none;
  font-size: 16px;
}

button {
  width: 40%; /* 버튼 가로폭을 줄임 */
  padding: 10px;
  background-color: #ff5722;
  color: #fff;
  border: none;
  border-radius: 5px;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #e64a19;
}

/* 회원가입 버튼 */
.submit-btn {
  width: 100%; /* 전체 폭 */
  font-size: 18px;
  font-weight: bold;
  padding: 15px; /* 버튼 크기 증가 */
  margin-top: 20px; /* 간격 추가 */
  background-color: #ff5722;
  text-align: center;
  display: block;
}

.submit-btn:hover {
  background-color: #e64a19;
}

/* 오류 메시지 */
.error-message {
  color: red;
  font-size: 14px;
  margin-top: 5px;
}

/* 성별 버튼 스타일 */
.gender-btn {
  width: 48%; /* 가로폭을 조정 */
  padding: 8px 0; /* 성별 버튼 크기 */
  font-size: 16px;
  font-weight: bold;
  border: 2px solid #ddd;
  border-radius: 10px;
  background-color: #fff; /* 기본 흰색 */
  color: #333;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
}

.gender-btn.active,
.gender-btn:hover {
  background-color: #ff5722; /* 선택 및 호버 시 */
  color: #fff;
  border-color: #ff5722;
}

.gender {
  display: flex;
  justify-content: space-between;
  gap: 10px; /* 버튼 간격 추가 */
}

/* 프로필 사진 스타일 */
.profile-image-label {
  display: block;
  width: 120px; /* 너비와 높이를 지정해서 원형을 만들기 */
  height: 120px;
  background-color: #ddd;
  border-radius: 50%; /* 원형으로 만들기 */
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  cursor: pointer;
  position: relative;
  margin: 0 auto; /* 중앙 정렬 */
}

.upload-text {
  font-size: 16px;
  color: #555;
}

.profile-image-preview {
  width: 100%;
  height: 100%;
  object-fit: cover; /* 이미지가 원형 안에 잘 맞게 조정 */
}

.profile-image-input {
  display: none;
}
</style>
