<template>
  <div class="magazine-container">
    <h1 class="magazine-logo">Magazine</h1>
    <div class="content">
      <!-- 네비게이션 탭 -->
      <div class="magazine-list-box">
        <div
          class="magazine-card"
          v-for="magazine in store.magazineList"
          :key="magazine.magazineNo"
          @click="detailMagazine(magazine.magazineNo)"
        >
          <div class="card text-bg-dark">
            <img
              src="../../assets/magazine/list/img2.png"
              class="card-img"
              alt=""
            />
            <div class="card-img-overlay">
              <h5 class="card-title">{{ magazine.magazineTitle }}</h5>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useMagazineStore } from "@/stores/magazine";
import { onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

const store = useMagazineStore();
const route = useRoute(); // 이거 안쓰나?
const router = useRouter();

onMounted(() => {
  store.getMagazineList();
});

const detailMagazine = function (magazineNo) {
  console.log(magazineNo + "번 매거진 클릭");
  store.getMagazine(magazineNo);
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.magazine-logo {
  text-align: center;
  margin: 15px 0;
  color: #fff;
  font-weight: bold;
}
.content {
  position: relative;
  width: 100%;
  padding: 10px;
  background-color: rgba(0, 0, 0, 0.7);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: auto;
}

.magazine-list-box {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  border-radius: 10px;
  gap: 15px;
}

.magazine-card {
  /* background-color: yellow; */
  cursor: pointer;
  width: 380px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  /* margin: 10px; */
}

.magazine-card:hover {
  transform: scale(1.05);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.card-img {
  width: 100%;
}

.card-title {
  position: absolute;
  bottom: 0;
  margin-bottom: 20px;
}

/* .magazine-content-list {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2rem;
  position: relative;
  z-index: 1;
}

.magazine-item {
  position: relative;
  border-radius: 20px;
  overflow: hidden;
  aspect-ratio: 16/9;
}

.magazine-list-background-img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: 0;
} */

/* .search-bar {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 2rem;
  position: relative;
  z-index: 1;
} */

/* .search-box {
  background: rgba(255, 255, 255, 0.1);
  border-radius: 25px;
  padding: 0.5rem 1rem;
  display: flex;
  align-items: center;
  width: 300px;
} */

/* .search-icon {
  width: 30px;
  height: 30px;
  margin-right: 10px;
} */

/* .search-text {
  color: #fff;
} */

/* .magazine-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 20px;
} */

/* .magazine-box {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 50%;
  background: linear-gradient(to bottom, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.7));
  border-radius: 20px;
  z-index: 1;
} */

/* .magazine-title {
  position: absolute;
  bottom: 1.5rem;
  left: 1.5rem;
  color: #fff;
  font-size: 1.5rem;
  font-weight: bold;
  z-index: 2;
  max-width: 90%;
  word-wrap: break-word;
  overflow-wrap: break-word;
} */
</style>
