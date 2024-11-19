<template>
  <div class="board-container">
    <!-- 배경 이미지 -->
    <img
      class="board-background"
      src="../../assets/board/list/board_background.png"
    />

    <!-- 게시판 콘텐츠 영역 -->
    <div class="board-content">
      <!-- 커뮤니티 텍스트 -->
      <br>
      <h1 class="community">Community</h1>

      <!-- 게시판 목록 테이블 -->
      <table class="table table-dark table-striped">
        <thead>
          <tr>
            <th scope="col">글 번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="board in store.boardList" :key="board.boardNo">
            <td>{{ board.boardNo }}</td>
            <td><RouterLink :to="`/board/${board.boardNo}`">{{ board.boardTitle }}</RouterLink></td>
            <td>board.userNo</td>
            <td>{{ board.boardViewCnt }}</td>
            <td>{{ board.boardRegDate }}</td>
          </tr>
        </tbody>
      </table>
      <!-- 글쓰기 버튼 -->
      <div class="write-box" @click="navigateToWrite">
        <span class="write-text"><RouterLink :to="{ name: 'boardWrite' }"><i class="bi bi-pencil-square"></i> 글쓰기</RouterLink></span>
      </div>

      <!-- 페이지네이션 -->
      <nav aria-label="Page navigation example">
        <ul class="pagination">
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          <li class="page-item"><a class="page-link" href="#">1</a></li>
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>

      <!-- 검색 -->
      <BoardSearchInput />
    </div>
  </div>
</template>

<script setup>
import { useBoardStore } from "@/stores/board";
import { onMounted } from "vue";
import BoardSearchInput from "./BoardSearchInput.vue";

const store = useBoardStore();
onMounted(() => {
  store.getBoardList();
});
</script>

<style scoped>
.board-container {
  position: relative;
  width: 100%;
  height: 100vh;
  overflow: hidden;
}

.board-background {
  width: 100%;
  height: 100%;
  position: absolute;
  right: 0%;
  left: 0%;
  bottom: 0%;
  top: 0%;
  object-fit: cover;
}

.board-content {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  z-index: 1;
}

.community {
  color: #ffffff;
  font-size: 2em;
  font-weight: bold;
  margin-bottom: 20px;
}

.table {
  width: 90%;
  max-width: 1200px;
  background-color: rgba(0, 0, 0, 0.6);
  border-radius: 10px;
  overflow: hidden;
}

.table-header,
.table-row {
  display: flex;
  justify-content: space-between;
  padding: 10px;
  color: #ffffff;
  font-weight: bold;
}

.table-header {
  background-color: rgba(255, 255, 255, 0.2);
}

.table-row {
  border-bottom: 1px solid rgba(255, 255, 255, 0.2);
}

.table-cell {
  flex: 1;
  text-align: center;
}

.paging {
  display: flex;
  gap: 10px;
  margin-top: 20px;
  color: #ffffff;
}

button {
  padding: 10px;
  cursor: pointer;
  background-color: #f0f0f0;
  border: none;
  border-radius: 5px;
}

.write-box {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #ffffff;
  border-radius: 5px;
  cursor: pointer;
}

.write-text {
  font-weight: bold;
}
</style>
