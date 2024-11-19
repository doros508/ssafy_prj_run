<template>
  <div class="nav-bar">
    <RouterLink class="mrt-logo" :to="{ name: 'main' }">MRT</RouterLink>
    <div class="center-links">
      <RouterLink class="magazine" :to="{ name: 'magazine' }"
        >Magazine</RouterLink
      >
      <RouterLink class="community" :to="{ name: 'board' }"
        >Community</RouterLink
      >
      <RouterLink class="crew" :to="{ name: 'crew' }">Crew</RouterLink>
      <RouterLink class="race" :to="{ name: 'race' }">Race</RouterLink>
    </div>
    <!--조건부 렌더링 -->
    <div v-if="!isLoggedIn">
      <RouterLink class="sign-up-in2" :to="{ name: 'login' }"
        >Sign up/in</RouterLink
      >
    </div>
    <div v-else>
      <button class="sign-up-in2" @click="handleLogout">Logout</button>
    </div>
  </div>
</template>

<script setup>
import { RouterLink } from "vue-router";
import { useUserStore } from "@/stores/userStore";
import { useRouter } from "vue-router";
import axios from "axios";
import { computed } from "vue";
const userStore = useUserStore();
const router = useRouter();

// 로그인 상태 계산된 속성
const isLoggedIn = computed(() => userStore.getIsLoggedIn);

// 로그아웃 핸들러
async function handleLogout() {
  try {
    // 서버에 로그아웃 요청 (세션 무효화)
    await axios.post("/api-user/logout");

    // Pinia 스토어 로그아웃 액션 호출
    userStore.logout();

    // 메인 페이지로 리다이렉트
    router.push({ name: "main" });
  } catch (error) {
    console.error("로그아웃 실패", error);
    // 에러 처리 (선택적)
    alert("로그아웃 중 오류가 발생했습니다.");
  }
}
</script>

<style scoped>
a {
  text-decoration: none; /* 기본적으로 밑줄 제거 */
}

.nav-bar {
  width: 100%;
  height: 74px;
  position: sticky;
  top: 0;
  z-index: 1000;
  background-color: #000;
  display: flex;
  justify-content: space-between; /* 양 끝에 배치 */
  align-items: center;
  padding: 0 20px; /* 양쪽에 여백을 추가하여 여유 공간 확보 */
  box-sizing: border-box;
}

.mrt-logo {
  color: #ffffff;
  text-align: left;
  font-family: "Notable-Regular", sans-serif;
  font-size: 61px;
  font-weight: 400;
  position: relative;
  width: 174px;
  height: 74px;
}

.center-links {
  display: flex;
  justify-content: center; /* 가운데 정렬 */
  /* gap: 100    px; 링크들 사이 간격 */
  flex-grow: 1; /* 가운데 링크들이 중앙에 배치되도록 공간 확장 */
}

.magazine,
.community,
.crew,
.race {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-SemiBold", sans-serif;
  font-size: 32px;
  font-weight: 600;
  white-space: nowrap;
  margin-left: 5%; /* 왼쪽 간격을 비율로 설정 */
  margin-right: 5%; /* 오른쪽 간격을 비율로 설정 */
}

.sign-up-in2 {
  /* color: #ffffff;
  font-family: "Inter-SemiBold", sans-serif;
  font-size: 30px;
  font-weight: 600;
  white-space: nowrap; */

  background: none;
  border: none;
  cursor: pointer;
  color: #ffffff;
  font-family: "Inter-SemiBold", sans-serif;
  font-size: 30px;
  font-weight: 600;
  white-space: nowrap;
}
</style>
