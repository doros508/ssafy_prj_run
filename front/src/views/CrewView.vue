<template>
  <div>
    <TheHeader />
    <div class="crew-picture">
      <CrewMain @filter-crews="updateFilteredCrews" />
      <div class="spacing"></div>
      <KakaoView :crews="filteredCrews" />
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import TheHeader from "@/components/common/TheHeader.vue";
import CrewMain from "@/components/crew/CrewMain.vue";
import KakaoView from "@/components/crew/kakaoView.vue";

// 필터링된 크루 데이터를 부모에서 관리
const filteredCrews = ref([]);

// CrewMain에서 발생한 검색 이벤트를 처리하는 함수
const updateFilteredCrews = (crews) => {
  filteredCrews.value = crews;
};
</script>

<style scoped>
/* 배경 이미지 설정 */
.crew-picture {
  width: 100%;
  position: relative;
  /* background-image: url("src/assets/common/backgroundSample/원본 7.png"); */
  background-image: url("src/assets/common/backgroundSample/원본 4.png");
  background-size: cover;
  background-position: center;
  padding: 20px; /* 내부 여백 추가 */
}

/* crewMain과 kakaoView 간격 설정 */
.spacing {
  height: 20px; /* 원하는 간격 크기 */
}

/* Vue 컴포넌트의 공통 스타일 */
.crew-picture > * {
  width: 100%; /* 가로 너비 통일 */
  max-width: 1200px; /* 원하는 최대 너비 */
  margin: 0 auto; /* 가운데 정렬 */
  box-sizing: border-box;
}
</style>
