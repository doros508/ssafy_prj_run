<template>
  <!-- 헤더 -->
  <TheHeader />
  <!-- 상단 Welcome -->
  <Welcome />

  <!-- 메인 레이아웃 -->
  <div class="main-layout">
    <!-- Magazine 영역 -->
    <section class="magazine-zone">
      <MagazineZone />
    </section>

    <!-- Weather 영역 -->
    <section class="weather-widget">
      <div>날씨 위젯</div>
    </section>

    <!-- Community 영역 -->
    <section class="community-zone">
      <CommunityZone />
    </section>

    <!-- Race 영역 -->
    <section class="race-widget">
      <div>최신 대회 정보</div>
    </section>
  </div>
</template>

<script setup>




import TheHeader from "@/components/common/TheHeader.vue";
import Welcome from "@/components/main/welcome.vue";
import MagazineZone from "@/components/main/MagazineZone.vue";
import CommunityZone from "@/components/main/CommunityZone.vue";
</script>

<style scoped>
.main-layout {
  display: grid;
  grid-template-columns: 3fr 1fr; /* 첫 번째 열: 두 번째 열 = 3:1 */
  grid-template-rows: auto auto; /* 두 행 */
  gap: 16px; /* 각 영역 간 간격 */
  padding: 16px;
  background-color:black
}

.magazine-zone {
  grid-column: 1 / 2; /* 첫 번째 열 */
  grid-row: 1 / 2;    /* 첫 번째 행 */
}

.weather-widget {
  grid-column: 2 / 3; /* 두 번째 열 */
  grid-row: 1 / 2;    /* 첫 번째 행 */
}

.community-zone {
  grid-column: 1 / 2; /* 첫 번째 열 */
  grid-row: 2 / 3;    /* 두 번째 행 */
}

.race-widget {
  grid-column: 2 / 3; /* 두 번째 열 */
  grid-row: 2 / 3;    /* 두 번째 행 */
}

/* 공통 스타일 */
section {
  background:rgba(255, 255, 255, 0.1);
  color: #fff;
  border-radius: 8px;
  padding: 16px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
</style>
