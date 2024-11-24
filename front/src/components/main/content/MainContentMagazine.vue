<template>
  <div class="main-container">
    <!-- Magazine Section -->
    <div class="magazine-section">
      <h2 class="section-title">Magazine</h2>
      <div class="magazine-container">
        <div
          class="magazine-item"
          v-for="magazine in store.magazineList.slice(0, 8)"
          :key="magazine.magazineNo"
        >
          <img
            class="magazine-image"
            src="../../../assets/magazine/list/img2.png"
            alt="magazine image"
          />
          <h3 class="magazine-title">{{ magazine.magazineTitle }}</h3>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useMagazineStore } from '@/stores/magazine';
import { onMounted } from 'vue';

const store = useMagazineStore();

onMounted(() => {
  store.getMagazineList()
})
</script>

<style scoped>
/* 전체 레이아웃 */
.main-container {
  margin: 20px;
}

/* 매거진 섹션 스타일 */
.magazine-section {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.magazine-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr); /* 한 줄에 2개 */
  gap: 20px;
  width: 90%;
  max-width: 90%; /* 매거진 섹션의 최대 너비 */
}

.magazine-item {
  display: flex;
  flex-direction: column;
  background: #222;
  border-radius: 12px;
  overflow: hidden;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.magazine-image {
  width: 100%;
  height: 350px; /* 높이를 늘려 더 넓게 설정 */
  object-fit: cover;
}

.magazine-title {
  font-size: 18px;
  margin: 15px 0;
  color: #fff;
  font-weight: bold;
}

/* 제목 스타일 */
.section-title {
  font-size: 35px;
  font-weight: 900;
  color: white;
  text-align: center;
  margin-bottom: 30px;
  margin-bottom: 20px;
}

/* 반응형 디자인 */
@media screen and (max-width: 768px) {
  .magazine-container {
    grid-template-columns: 1fr; /* 작은 화면에서는 한 줄에 1개 */
  }
}
</style>
