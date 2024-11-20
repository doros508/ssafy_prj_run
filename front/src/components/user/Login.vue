<template>
  <div class="login">
    <!-- 배경 이미지 -->
    <div class="login-picture"></div>

    <!-- 로그인 폼 -->
    <div class="sign-in">
      <!-- 투명 배경 박스 -->
      <div class="sign-in-box">
        <div class="sign-in-logo">Sign in</div>

        <form @submit.prevent="login">
          <!-- 아이디 입력 필드 -->
          <input
            type="text"
            placeholder="아이디"
            class="input-field"
            v-model="userId"
            required
          />

          <!-- 비밀번호 입력 필드 -->
          <input
            type="password"
            placeholder="비밀번호"
            class="input-field"
            v-model="userPassword"
            required
          />

          <!-- 비밀번호 찾기 -->
          <div class="forgot-password">비밀번호를 잊어버리셨나요?</div>

          <!-- 로그인 버튼 -->
          <button class="login-btn">로그인</button>
        </form>

        <!-- 회원가입 버튼 -->
        <button class="signup-btn" @click="goToSignUp">회원가입</button>

        <!-- 소셜 로그인 버튼 -->
        <div class="social-login">
          <img
            class="kakao"
            src="../../assets/user/login/kakao.png"
            alt="Kakao"
          />
          <img
            class="naver"
            src="../../assets/user/login/naver.png"
            alt="Naver"
          />
          <img
            class="google"
            src="../../assets/user/login/google.png"
            alt="Google"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import axios from "axios";
import { useUserStore } from "@/stores/userStore";

const router = useRouter();
const route = useRoute();
const userStore = useUserStore(); //Pinia 스토어 사용.

const userId = ref("");
const userPassword = ref("");

// 페이지 로드 시 로그인 상태 확인
onMounted(() => {
  const savedUser = localStorage.getItem('user');
  if (savedUser) {
    const user = JSON.parse(savedUser);
    userStore.setUser(user);
    userStore.setIsLoggedIn(true);
  }
});
async function login() {
  try {
    if (!userId.value || !userPassword.value) {
      alert("아이디와 비밀번호를 입력해주세요.");
      return;
    }

    const response = await axios.post("/api-user/login", {
      userId: userId.value,
      userPassword: userPassword.value,
    });
    
    if (response.data) {
      const userData = {
        userId: userId.value,
        // 필요한 다른 사용자 정보도 포함
      };
      userStore.setUser(userData);
      alert("로그인 성공");
      router.push({ name: "main" });
    }
  } catch (error) {
    alert(error.response?.data?.message || "로그인 실패");
    userStore.clearUser();
  }
}


function goToSignUp() {
  router.push({
    path: "/signup",
    name: "signUp",
  });
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
