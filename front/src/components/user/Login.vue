<template>
  <div class="login">
    <div class="login-picture"></div>
    <div class="sign-in">
      <div class="sign-in-box">
        <div class="sign-in-logo">Sign in</div>
        <form @submit.prevent="login">
          <input
            type="text"
            placeholder="아이디"
            class="input-field"
            v-model="userId"
            required
          />
          <input
            type="password"
            placeholder="비밀번호"
            class="input-field"
            v-model="userPassword"
            required
          />
          <div class="forgot-password">비밀번호를 잊어버리셨나요?</div>
          <button type="submit" class="login-btn">로그인</button>
        </form>
        <button class="signup-btn" @click="goToSignUp">회원가입</button>
        <div class="social-login">
          <img src="@/assets/user/login/kakao.png" alt="Kakao" />
          <img src="@/assets/user/login/naver.png" alt="Naver" />
          <img src="@/assets/user/login/google.png" alt="Google" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/userStore";

const router = useRouter();
const userStore = useUserStore();
const userId = ref("");
const userPassword = ref("");

async function login() {
  try {
    if (!userId.value || !userPassword.value) {
      alert("아이디와 비밀번호를 입력해주세요.");
      return;
    }

    const success = await userStore.login(userId.value, userPassword.value);

    if (!success) {
      alert("아이디 또는 비밀번호가 올바르지 않습니다.");
    }
  } catch (error) {
    console.error("로그인 오류:", error);
    alert("로그인 중 오류가 발생했습니다.");
  }
}

function goToSignUp() {
  router.push({ name: "signUp" });
}
</script>

<style scoped>
/* 기본 설정 */
.login,
.login * {
  box-sizing: border-box;
  font-family: "Inter", sans-serif;
}

/* 전체 화면 설정 */
.login {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
}

/* 배경 이미지 설정 */
.login-picture {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background-image: url("../../assets/user/login/login_picture.png");
  background-size: cover;
  background-position: center;
}

/* 로그인 폼 박스 */
.sign-in {
  width: 400px;
  padding: 40px;
  background: rgba(0, 0, 0, 0.7);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  z-index: 10;
}

/* Sign in 텍스트 */
.sign-in-logo {
  font-size: 36px;
  font-weight: bold;
  color: #ffffff;
  margin-bottom: 30px;
}

/* 입력 필드 스타일 */
.input-field {
  width: 100%;
  padding: 10px 15px;
  margin: 10px 0;
  border: none;
  border-radius: 20px;
  font-size: 16px;
}

/* 입력 필드 색상 */
.input-field::placeholder {
  color: #999;
}

/* 버튼 스타일 */
.login-btn,
.signup-btn {
  width: 100%;
  padding: 10px 0;
  margin: 10px 0;
  border: none;
  border-radius: 20px;
  background-color: #fff;
  color: #000;
  font-size: 18px;
  cursor: pointer;
}

.signup-btn {
  background-color: #f0f0f0;
}

/* 비밀번호 찾기 링크 */
.forgot-password {
  color: #fff;
  font-size: 14px;
  margin-bottom: 20px;
  cursor: pointer;
}

/* 소셜 로그인 버튼 */
.social-login {
  display: flex;
  gap: 10px;
  justify-content: center;
}

.social-login img {
  width: 40px;
  height: 40px;
  cursor: pointer;
}
</style>
