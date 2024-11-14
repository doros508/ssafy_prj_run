<template>
        <!-- <nav>
            <RouterLink :to="{ name: 'main' }">Main</RouterLink> |
            <RouterLink :to="{ name: 'magazine' }">Magazine</RouterLink> | 
            <RouterLink :to="{ name: 'board' }">Community</RouterLink> |
            <RouterLink :to="{ name: 'boardCreate' }">Crew</RouterLink> | 
            <RouterLink :to="{ name: 'login' }">Race</RouterLink>
        </nav> -->
        <div class="nav-bar">
            <RouterLink class="mrt-logo" :to="{name: 'main'}">MRT</RouterLink>
            <RouterLink class="magazine" :to="{name: 'magazine'}">Magazine</RouterLink>
            <RouterLink class="community" :to="{name: 'board'}">Community</RouterLink>
            <RouterLink class="crew" :to="{name: 'crew'}">Crew</RouterLink>
            <RouterLink class="race" :to="{name: 'race'}">Race</RouterLink>

            <div class="sign-up-in"></div>
            <div class="sign-up-in2">Sign up/in</div>
            <div class="div"></div>
        </div>
</template>

<script setup>
import { RouterLink } from 'vue-router';


</script>

<style scoped>
.nav-bar {
  width: 100%; /* 화면에 꽉 차도록 설정 */
  height: 74px;
  position: sticky;
  top: 0; /* 상단에 고정 */
  z-index: 1000; /* 다른 요소 위에 위치하도록 */
  background-color: #000; /* 배경색 추가 (필요 시) */
}

.mrt-logo {
  color: #ffffff;
  text-align: left;
  font-family: "Notable-Regular", sans-serif;
  font-size: 61px;
  font-weight: 400;
  position: absolute;
  right: 88.85%;
  left: 2.08%;
  width: 9.06%;
  bottom: 94.04%;
  top: 1.46%;
  height: 4.5%;
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
  position: absolute;
  bottom: 94.77%;
  top: 3.22%;
  height: 2.01%;
}
.magazine {
  right: 53.59%;
  left: 38.28%;
  width: 8.12%;
}
.community {
  right: 37.92%;
  left: 52.4%;
  width: 9.69%;
}
.crew {
  right: 27.5%;
  left: 68.07%;
  width: 4.43%;
}
.race {
  right: 17.29%;
  left: 78.44%;
  width: 4.27%;
}
.sign-up-in {
  width: 176px;
  height: 50px;
  position: static;
}
.sign-up-in2 {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-SemiBold", sans-serif;
  font-size: 30px;
  font-weight: 600;
  position: absolute;
  right: 2.4%;
  left: 89.38%;
  width: 8.23%;
  bottom: 94.71%;
  top: 3.28%;
  height: 2.01%;
}
</style>