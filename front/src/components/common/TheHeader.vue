<template>
  <div class="nav-bar">
      <RouterLink class="mrt-logo" :to="{name: 'main'}">MRT</RouterLink>
      <div class="center-links">
          <RouterLink class="magazine" :to="{name: 'magazine'}">Magazine</RouterLink>
          <RouterLink class="community" :to="{name: 'board'}">Community</RouterLink>
          <RouterLink class="crew" :to="{name: 'crew'}">Crew</RouterLink>
          <RouterLink class="race" :to="{name: 'race'}">Race</RouterLink>
      </div>
      <RouterLink class="sign-up-in2" :to="{name: 'login'}">Sign up/in</RouterLink>
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router';
</script>

<style scoped>
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
padding: 0 20px ; /* 양쪽에 여백을 추가하여 여유 공간 확보 */

}

.mrt-logo {
color: #ffffff;
font-family: "Notable-Regular", sans-serif;
font-size: 61px;
font-weight: 400;
white-space: nowrap;
}

.center-links {
display: flex;
justify-content: center; /* 가운데 정렬 */
gap: 20px; /* 링크들 사이 간격 */
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
}

.sign-up-in2 {
color: #ffffff;
font-family: "Inter-SemiBold", sans-serif;
font-size: 30px;
font-weight: 600;
white-space: nowrap;
}
</style>
