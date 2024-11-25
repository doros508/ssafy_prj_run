<template>
  <div class="container">
    <h1 class="logo">Community</h1>
    <div class="content">
      <div class="board-info">
        <div class="board-info-left">
          <h3>제목</h3>
          <p>작성자</p>
          <p>작성일자</p>
        </div>
        <div class="board-info-right">
          <h3>{{ boardStore.board.boardTitle }}</h3>
          <p>{{ boardStore.board.boardNo }}</p>
          <p>{{ boardStore.board.boardRegDate }}</p>
        </div>
      </div>
      <hr>
      <div class="board-content">
        <!-- Carousel (슬라이드 쇼) -->
        <div v-if="boardStore.board.boardFileList && boardStore.board.boardFileList.length > 0" id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
          <div class="carousel-indicators">
            <!-- 슬라이드 버튼 생성 -->
            <button 
              v-for="(file, index) in boardStore.board.boardFileList" 
              :key="index" 
              type="button" 
              :data-bs-target="'#carouselExampleIndicators'" 
              :data-bs-slide-to="index" 
              :class="{'active': index === 0}" 
              :aria-label="'Slide ' + (index + 1)">
            </button>
          </div>
          <div class="carousel-inner">
            <!-- 이미지 또는 동영상 슬라이드 항목 생성 -->
            <div 
              v-for="(file, index) in boardStore.board.boardFileList" 
              :key="index" 
              :class="['carousel-item', { 'active': index === 0 }]">
              <!-- 파일이 mp4 형식인 경우 video 태그 사용 -->
              <template v-if="file.systemName.endsWith('.mp4')">
                <video class="d-block w-100" :src="file.imageUrl" controls>
                  Your browser does not support the video tag.
                </video>
              </template>
              <!-- 이미지 파일인 경우 img 태그 사용 -->
              <template v-else>
                <img :src="file.imageUrl" class="d-block w-100" :alt="'게시글 이미지 ' + (index + 1)">
              </template>
            </div>
          </div>
          <!-- 이전/다음 버튼 -->
          <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
        <p v-else>첨부된 이미지나 동영상이 없습니다.</p>
        <!-- 게시글 내용 -->
        <p>{{ boardStore.board.boardContent }}</p>
      </div>
      <div class="btn-box" v-if="isAuthor">
        <button class="btn btn-primary" type="submit" @click="updateBoard">수정</button>
        <button class="btn btn-primary" type="submit" @click="deleteBoard">삭제</button>
      </div>
      <div class="board-comment">
        <!-- 여기에 댓글 -->
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { useBoardStore } from "@/stores/board";
import { onMounted, computed } from "vue";
import { useRoute, useRouter } from "vue-router";

const userStore = useUserStore();
const boardStore = useBoardStore();
const route = useRoute();
const router = useRouter();

// store를 boardStore로 변경
const isAuthor = computed(() => {

  console.log("boardDetail test isLoggedIn:", userStore.isLoggedIn);
  console.log("userNo:", userStore.user?.userNo);
  console.log("boardUserNo:", boardStore.board?.userNo);

  return userStore.isLoggedIn && 
         userStore.user && 
         boardStore.board && 
         boardStore.board.userNo === userStore.user.userNo;
});

onMounted(async () => {
  await boardStore.getBoard(route.params.boardNo);
  if (userStore.isLoggedIn) {
    userStore.initializeFromLocalStorage();
  }
});

const updateBoard = function() {
  if (!userStore.isLoggedIn) {
    alert("로그인이 필요한 서비스입니다.");
    router.push({ name: "login" });
    return;
  }
  if (boardStore.board.userNo !== userStore.user.userNo) {
    alert("자신이 작성한 글만 수정할 수 있습니다.");
    return;
  }

  router.push({ 
    name: 'boardUpdate',
    params: { boardNo: route.params.boardNo }
  });
};

const deleteBoard = function() {
  if (!userStore.isLoggedIn) {
    alert("로그인이 필요한 서비스입니다.");
    router.push({ name: "login" });
    return;
  }
  if (boardStore.board.userNo !== userStore.user.userNo) {
    alert("자신이 작성한 글만 삭제할 수 있습니다.");
    return;
  }
  
  if (confirm("정말 삭제하시겠습니까?")) {
    // store를 boardStore로 변경
    boardStore.deleteBoard(route.params.boardNo);
  }
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

.board-info {
  background-color: black;
  display: flex;
  border-radius: 10px;
  padding: 10px;
}
.board-info-left {
  width: 20%;
}
.board-info-right {
  width: 100%;
}
.board-content {
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
