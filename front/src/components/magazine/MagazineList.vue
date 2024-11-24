<template>
  <div class="magazine-container">
    <h1 class="magazine-logo">Magazine</h1>
    <div class="content">
      <!-- 네비게이션 탭 -->
      <!-- <div class="magazine-list-box"> -->
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
      <!-- </div> -->
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
.magazine-container {
  width: 100%;
}

.magazine-logo {
  text-align: center;
  margin: 15px 0;
  color: #fff;
  font-weight: bold;
}

.content {
  /* position: relative;
  width: 100%;
  padding: 10px;
  background-color: rgba(0, 0, 0, 0.7);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: auto; */
  background-color: #181818;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  justify-content: center;
}

.magazine-card {
  /* background-color: yellow; */
  cursor: pointer;
  width: 380px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
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

</style>
