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
    <div>
        <RouterLink
          v-if="!isLoggedIn"
          class="sign-up-in2"
          :to="{ name: 'login' }"
        >
          Sign up/in
        </RouterLink>
        <button v-else class="sign-up-in2" @click="handleLogout">
          Logout
        </button>
      </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { useRouter } from "vue-router";
import axios from "axios";
import { storeToRefs } from "pinia";
import { onMounted } from "vue";
import { computed } from "vue";

// userStore 선언을 한 번만 하도록 수정
const userStore = useUserStore();
const router = useRouter();


const isLoggedIn = computed(() => userStore.isLoggedIn);

onMounted(() => {
  // userStore.clearUser();
  userStore.initializeFromLocalStorage();
});

async function handleLogout() {
  try {
    await axios.post("/maratalk/logout");
    userStore.clearUser();
    router.push("/");
  } catch (error) {
    console.error("로그아웃 실패:", error);
    // userStore.clearUser(); // 에러가 발생해도 로컬 상태는 초기화
  }
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Notable&display=swap");

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
  font-family: "Notable", sans-serif;
  color: #ffffff;
  text-align: left;
  /* font-family: "Notable-Regular", sans-serif; */
  font-size: 61px;
  font-weight: 400;
  position: relative;
  width: 174px;
  height: 108px;
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
