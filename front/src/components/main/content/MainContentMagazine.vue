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
          @click="detailMagazine(magazine.magazineNo)"
        >
          <img
            :src="getThumbnail(magazine)"
            class="magazine-image"
            alt="썸네일 이미지"
          />
          <h3 class="magazine-title">{{ magazine.magazineTitle }}</h3>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useMagazineStore } from "@/stores/magazine";
import { onMounted } from "vue";
import { useRouter } from "vue-router";

const store = useMagazineStore();
const router = useRouter();

onMounted(() => {
  store.getMagazineList();
});

// 매거진 상세 페이지로 이동하는 함수
const detailMagazine = (magazineNo) => {
  console.log(magazineNo + "번 매거진 클릭");
  router.push({ name: "magazineDetail", params: { magazineNo } });
};

// 글쓰기 페이지로 이동하는 함수
const writeMagazine = () => {
  console.log("글쓰기 버튼 클릭!");
  router.push({ name: "magazineWrite" });
};

// 썸네일 이미지 결정 함수
const getThumbnail = (magazine) => {
  const files = magazine.magazineFileList || [];

  // 1. 파일이 아예 없을 경우
  if (files.length === 0) {
    return "http://localhost:8080/uploads/default/thumbnail.png"; // 기본 이미지 로컬
    // return "http://192.168.210.53:8080/uploads/default/thumbnail.png"; // 건우
    // return "http://192.168.210.63:8080/uploads/default/thumbnail.png"; // 종수
  }

  // 2. 비디오 파일만 존재하는 경우
  const videoFiles = files.filter((file) => file.systemName.endsWith(".mp4"));
  if (videoFiles.length === files.length) {
    return "http://localhost:8080/uploads/default/thumbnail.png"; // 비디오만 있을 경우 기본 이미지 로컬
   // return "http://192.168.210.53:8080/uploads/default/thumbnail.png"; // 건우
  //  return "http://192.168.210.63:8080/uploads/default/thumbnail.png"; // 종수
  }

  // 3. 비디오 파일과 이미지 파일이 함께 있을 경우
  const imageFiles = files.filter((file) => !file.systemName.endsWith(".mp4"));
  if (imageFiles.length > 0) {
    // 3-1. 첫 번째 이미지 파일을 썸네일로 사용
    return `http://localhost:8080/uploads/magazine${imageFiles[0].path}/${imageFiles[0].systemName}`; // 로컬
    // return `http://192.168.210.53:8080/uploads/magazine${imageFiles[0].path}/${imageFiles[0].systemName}`; // 건우
    // return `http://192.168.210.63:8080/uploads/magazine${imageFiles[0].path}/${imageFiles[0].systemName}`; // 종수
  
  }

  // 4. 사진 파일만 있을 경우
  if (imageFiles.length > 0) {
    // 4-1. 첫 번째 이미지 파일을 썸네일로 사용
    return `http://localhost:8080/uploads/magazine${imageFiles[0].path}/${imageFiles[0].systemName}`; // 로컬
    // return `http://192.168.210.53:8080/uploads/magazine${imageFiles[0].path}/${imageFiles[0].systemName}`; // 건우
    // return `http://192.168.210.63:8080/uploads/magazine${imageFiles[0].path}/${imageFiles[0].systemName}`; // 종수
  }

  // 기본 반환 값 (기본 이미지)
  return "http://localhost:8080/uploads/default/thumbnail.png"; // 로컬
  // return "http://192.168.210.53:8080/uploads/default/thumbnail.png"; // 건우
  // return "http://192.168.210.63:8080/uploads/default/thumbnail.png"; // 종수
};
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
  background-color: rgba(0, 0, 0, 0); /* 투명한 검정 배경 */
  gap: 20px;
  width: 90%;
  max-width: 90%; /* 매거진 섹션의 최대 너비 */
}

.magazine-item {
  display: flex;
  flex-direction: column;
  background: #222; /*카드배경*/
  border-radius: 12px;
  overflow: hidden;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  cursor: pointer;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  height: 280px;
}

.magazine-item:hover {
  transform: scale(1.05);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.magazine-image {
  width: 100%;
  height: 200px; /* 이미지의 높이 고정 */
  object-fit: cover; /* 이미지가 박스를 채우도록 비율 맞추기 */
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
