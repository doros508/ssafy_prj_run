<template>
  <div class="magazine-container">
    <h1 class="magazine-logo">Magazine</h1>
    <div class="content">
      <div class="btn-box">
        <button type="button" id="writeform-btn" class="btn btn-primary" @click="writeMagazine">
          <i class="bi bi-pencil-square"></i> 글쓰기
        </button>
      </div>

      <!-- 네비게이션 탭 -->
      <div class="magazine-list-box">
        <div 
          class="magazine-card" 
          v-for="magazine in store.magazineList" 
          :key="magazine.magazineNo"
          @click="detailMagazine(magazine.magazineNo)"
        >
          <div class="card text-bg-dark">
            <!-- 썸네일 이미지 처리 -->
            <img :src="getThumbnail(magazine)" class="card-img" alt="썸네일 이미지" />
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
import { computed, onMounted } from "vue";
import { useRouter } from "vue-router";

const store = useMagazineStore();
const router = useRouter();

onMounted(() => {
  store.getMagazineList();
});

const detailMagazine = (magazineNo) => {
  console.log(magazineNo + "번 매거진 클릭");
  router.push({ name: 'magazineDetail', params: { magazineNo } });
};

const writeMagazine = () => {
  console.log("글쓰기 버튼 클릭!");
  router.push({ name: 'magazineWrite' });
};

// 썸네일 이미지 결정 함수 수정
const getThumbnail = (magazine) => {
  const files = magazine.magazineFileList || [];

  // 1. 파일이 아예 없을 경우
  if (files.length === 0) {
    return "http://localhost:8080/uploads/default/thumbnail.png";  // 기본 이미지
  }

  // 2. 비디오 파일만 존재하는 경우
  const videoFiles = files.filter(file => file.systemName.endsWith('.mp4'));
  if (videoFiles.length === files.length) {
    return "http://localhost:8080/uploads/default/thumbnail.png";  // 비디오만 있을 경우 기본 이미지
  }

  // 3. 비디오 파일과 이미지 파일이 함께 있을 경우
  const imageFiles = files.filter(file => !file.systemName.endsWith('.mp4'));
  if (imageFiles.length > 0) {
    // 3-1. 첫 번째 이미지 파일을 썸네일로 사용
    return `http://localhost:8080/uploads/magazine${imageFiles[0].path}/${imageFiles[0].systemName}`;
  }

  // 4. 사진 파일만 있을 경우
  if (imageFiles.length > 0) {
    // 4-1. 첫 번째 이미지 파일을 썸네일로 사용
    return `http://localhost:8080/uploads/magazine${imageFiles[0].path}/${imageFiles[0].systemName}`;
  }

  // 기본 반환 값 (기본 이미지)
  return "http://localhost:8080/uploads/default/thumbnail.png";
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
  background-color: #181818;
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
}

.magazine-list-box {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
}

.magazine-card {
  cursor: pointer;
  width: calc(33.33% - 20px); /* 한 줄에 3개씩 보이도록 */
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  height: 280px; /* 카드 높이 고정 */
}

.magazine-card:hover {
  transform: scale(1.05);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.card-img {
  width: 100%;
  height: 200px; /* 이미지의 높이 고정 */
  object-fit: cover; /* 이미지가 박스를 채우도록 비율 맞추기 */
}

.card-title {
  font-weight: bold;
  position: absolute;
  bottom: 0;
  margin-bottom: 10px;
}

.btn-box {
  width: 100%;
  display: flex;
  justify-content: flex-end;
}

#writeform-btn {
  margin-top: 0;
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
</style>

