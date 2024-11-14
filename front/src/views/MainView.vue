<template>
  <div class="main-page">
    <!-- 헤더 영역 -->
    <TheHeader />
    <!-- <img class="main-photo" src="C:\SSAFY\ssafy_prj_run\front\src\assets\main_photo.png " /> -->
    <div class="main-slogan">
      RUNNING
      <br />
      TOGETHER
    </div>
  </div>
</template>

<script setup>
import TheHeader from "@/components/common/TheHeader.vue";
import { RouterView } from "vue-router";
</script>

<style scoped>

.main-page,
.main-page * {
  box-sizing: border-box;
}

.main-page {
  height: 100vh; /* 화면의 전체 높이를 차지하도록 설정 */
  position: relative;
  background-image: url("@/assets/main_photo.png"); /* 배경 이미지 설정 */
  background-size: cover; /* 이미지가 컨테이너를 덮도록 설정 */
  background-position: center; /* 이미지가 중앙에 오도록 설정 */
}

/* .main-page {
  height: 1644px;
  position: relative;
} */
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
