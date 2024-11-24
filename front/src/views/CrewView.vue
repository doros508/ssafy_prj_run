<template>
  <div>
    <TheHeader />
    <div class="crew-picture">
      <CrewMain />
      <KakaoView />
    </div>
  </div>
</template>

<script setup>
import TheHeader from "@/components/common/TheHeader.vue";
import CrewMain from "@/components/crew/CrewMain.vue";
import KakaoView from "@/components/crew/kakaoView.vue";
</script>

<style scoped>
/* 배경 이미지 설정 */
.crew-picture {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background-image: url("../../assets/common/backgroundSample/원본 8.png");
  background-size: cover;
  background-position: center;
}
</style>
