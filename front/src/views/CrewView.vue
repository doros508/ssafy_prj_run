<template>
  <div>
    <TheHeader />
    <div class="crew-picture">
      <CrewMain @filter-crews="updateFilteredCrews" />
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
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background-image: url("../../assets/common/backgroundSample/원본 8.png");
  background-size: cover;
  background-position: center;
}
</style>
