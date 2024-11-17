<script setup>
import { ref } from "vue";

// 선택된 성별 저장
const selectedGender = ref("");

// 성별 선택 함수
function selectGender(gender) {
  selectedGender.value = gender;
}
</script>


<template>
  <div class="signup-container">
    <img class="background-img" src="../../assets/user/sign_up/login_picture.png" />
    <div class="signup-form">
      <h1 class="logo">MRT</h1>
      <h2 class="title">Sign up</h2>
      
      <div class="form-group">
        <input type="text" placeholder="아이디" />
      </div>

      <div class="form-group">
        <input type="password" placeholder="비밀번호" />
      </div>

      <div class="form-group">
        <p class="message">이미 사용중인 별명입니다 or 별명을 입력해주세요.</p>
      </div>

      <div class="form-group">
        <input type="text" placeholder="이메일" />
        <button class="send-code-btn">인증코드 전송</button>
      </div>

      <div class="form-group">
        <input type="text" placeholder="이름" />
      </div>

      <div class="form-group">
        <input type="date" />
      </div>

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

      <div class="form-group">
        <input type="text" placeholder="주소 검색" />
      </div>

      <div class="form-group">
        <input type="tel" placeholder="전화번호" />
      </div>

      <div class="form-group">
        <input type="text" placeholder="인증번호 입력" />
      </div>

      <button class="submit-btn">회원가입</button>
    </div>
  </div>
</template>

<style scoped>
/* 기본 스타일 */
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
  margin-bottom: 15px;
}

.icon {
  width: 20px;
  margin-right: 10px;
}

input[type="text"],
input[type="password"],
input[type="date"],
input[type="tel"] {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 5px;
  outline: none;
  font-size: 16px;
}

button {
  padding: 10px;
  width: 100%;
  background-color: #ff7b54;
  border: none;
  border-radius: 5px;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
}

button:hover {
  background-color: #ff5722;
}

.send-code-btn {
  margin-left: 10px;
  padding: 10px;
  background-color: #5a9;
}

.gender {
  display: flex;
  justify-content: space-between;
}

.gender label {
  cursor: pointer;
}

.submit-btn {
  margin-top: 20px;
}

/*  */

.gender-btn {
  width: 48%;
  padding: 10px 0;
  font-size: 16px;
  font-weight: bold;
  border: 2px solid #ddd;
  border-radius: 10px;
  background-color: #fff;
  color: #333;
  cursor: pointer;
  transition: all 0.2s;
}

.gender-btn.active {
  background-color: #ff7b54;
  color: #fff;
  border-color: #ff7b54;
}

.gender-btn:hover {
  background-color: #ffe0d6;
  color: #333;
}

</style>
