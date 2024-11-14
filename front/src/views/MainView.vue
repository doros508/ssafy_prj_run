<template>
  <div class="main-page">
    <img class="main-photo" src="C:\SSAFY\ssafy_prj_run\front\src\assets\main_photo.png" />
    <div class="main-slogan">
      RUNNING
      <br />
      TOGETHER
    </div>
    <!-- 헤더 영역 -->
    <!-- <Theheader/> -->
  </div>
</template>

<script setup>
import Theheader from "@/components/common/TheHeader.vue";
import { RouterView } from "vue-router";
</script>

<style scoped>
.main-page,
.main-page * {
  box-sizing: border-box;
}
.main-page {
  height: 1644px;
  position: relative;
}
.main-photo {
  width: 100%;
  height: 100%;
  position: absolute;
  right: 0%;
  left: 0%;
  bottom: 0%;
  top: 0%;
  object-fit: cover;
}
.main-slogan {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-BlackItalic", sans-serif;
  font-size: 170px;
  line-height: 160px;
  font-weight: 900;
  font-style: italic;
  position: absolute;
  right: 35.73%;
  left: 3.54%;
  width: 60.73%;
  bottom: 6.69%;
  top: 73.11%;
  height: 20.19%;
}
.nav-bar {
  width: 1838px;
  height: 74px;
  position: static;
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
.div {
  background: rgba(255, 255, 255, 0.15);
  border-radius: 25px;
  border-style: solid;
  border-color: rgba(255, 255, 255, 0.4);
  border-width: 1px;
  width: 9.17%;
  height: 3.04%;
  position: absolute;
  right: 2.19%;
  left: 88.65%;
  bottom: 94.04%;
  top: 2.92%;
}
</style>
