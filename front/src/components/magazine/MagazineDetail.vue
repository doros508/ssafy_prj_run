<template>
  <div class="container">
    <h1 class="logo">Magazine</h1>
    <div class="content">
      <div class="magazine-info">
        <div class="magazine-info-left">
          <h3>제목</h3>
          <p>작성자</p>
          <p>작성일자</p>
        </div>
        <div class="magazine-info-right">
          <h3>{{ store.magazine.magazineTitle }}</h3>
          <p>{{ store.magazine.magazineNo }}</p>
          <p>{{ store.magazine.magazineRegDate }}</p>
        </div>
      </div>
      <hr />
      <div class="magazine-content">
        <!-- Carousel (슬라이드 쇼) -->
        <div
          v-if="
            store.magazine.magazineFileList &&
            store.magazine.magazineFileList.length > 0
          "
          id="carouselExampleIndicators"
          class="carousel slide"
          data-bs-ride="carousel"
        >
          <div class="carousel-indicators">
            <!-- 슬라이드 버튼 생성 -->
            <button
              v-for="(file, index) in store.magazine.magazineFileList"
              :key="index"
              type="button"
              :data-bs-target="'#carouselExampleIndicators'"
              :data-bs-slide-to="index"
              :class="{ active: index === 0 }"
              :aria-label="'Slide ' + (index + 1)"
            ></button>
          </div>
          <div class="carousel-inner">
            <!-- 이미지 또는 동영상 슬라이드 항목 생성 -->
            <div
              v-for="(file, index) in store.magazine.magazineFileList"
              :key="index"
              :class="['carousel-item', { active: index === 0 }]"
            >
              <!-- 파일이 mp4 형식인 경우 video 태그 사용 -->
              <template v-if="file.systemName.endsWith('.mp4')">
                <video class="d-block w-100" :src="file.imageUrl" controls>
                  Your browser does not support the video tag.
                </video>
              </template>
              <!-- 이미지 파일인 경우 img 태그 사용 -->
              <template v-else>
                <img
                  :src="file.imageUrl"
                  class="d-block w-100"
                  :alt="'게시글 이미지 ' + (index + 1)"
                />
              </template>
            </div>
          </div>
          <!-- 이전/다음 버튼 -->
          <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide="prev"
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide="next"
          >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
        <p v-else>첨부된 이미지나 동영상이 없습니다.</p>
        <!-- 게시글 내용 -->
        <p>{{ store.magazine.magazineContent }}</p>
      </div>
      <div class="btn-box" v-if="isAdmin">
        <button class="btn btn-primary" type="submit" @click="updateMagazine">
          수정
        </button>
        <button class="btn btn-primary" type="submit" @click="deleteMagazine">
          삭제
        </button>
      </div>
      <div class="magazine-comment">
        <!-- 여기에 댓글ㅎ -->
      </div>
    </div>
  </div>
</template>

<script setup>
import { useMagazineStore } from "@/stores/magazine";
import { onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useUserStore } from "@/stores/userStore";
import { computed } from "vue";

const userStore = useUserStore();
const isAdmin = computed(() => {
  return userStore.user && userStore.user.userId === "admin";
});
const store = useMagazineStore();

const route = useRoute();
const router = useRouter();

onMounted(() => {
  store.getMagazine(route.params.magazineNo);
});

// 게시글 수정
const updateMagazine = function (magazineNo) {
  router.push({ name: "magazineUpdate" });
};

// 게시글 삭제
const deleteMagazine = function () {
  console.log(route.params.magazineNo);
  store.deleteMagazine(route.params.magazineNo);
};
</script>

<style scoped>
.logo {
  text-align: center;
  margin: 15px 0;
  color: #fff;
  font-weight: bold;
}
.content {
  background-color: rgba(0, 0, 0, 0.8);
  border-radius: 10px;
  padding: 20px;
  color: white;
}

.magazine-info {
  background-color: black;
  display: flex;
  border-radius: 10px;
  padding: 10px;
}
.magazine-info-left {
  width: 20%;
}
.magazine-info-right {
  width: 100%;
}
.magazine-content {
  padding: 10px;
  background-color: black;
  border-radius: 10px;
}
p {
  margin: 0px;
}

hr {
  color: white;
}

.btn-box {
  display: flex;
  justify-content: flex-end; /* 버튼을 오른쪽으로 정렬 */
  margin-top: 10px;
}

.btn-primary {
  background-color: #ff5722;
  border-color: #ff5722;
  margin: 3px;
}

.btn-primary:hover {
  background-color: #e64a19;
  border-color: #e64a19;
}

/* Carousel 이미지 및 비디오 조정 */
.carousel-inner img,
.carousel-inner video {
  max-height: 500px; /* 최대 높이 설정 */
  object-fit: contain; /* 이미지나 비디오가 잘리지 않도록 비율 유지하면서 공간에 맞추기 */
  margin: 0 auto; /* 이미지나 비디오가 중앙에 오도록 여백 추가 */
}
</style>
