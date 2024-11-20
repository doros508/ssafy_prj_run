<template>
  <div class="board-container">
    <!-- 배경 이미지 -->

    <!-- 게시판 콘텐츠 영역 -->
    <div class="board-content-box">
      <!-- 커뮤니티 텍스트 -->
      <h1 class="community">Community</h1>

      <div class="content">
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
            <td>
              <RouterLink :to="`/board/${board.boardNo}`">{{
                board.boardTitle
              }}</RouterLink>
            </td>
            <td>board.userNo</td>
            <td>{{ board.boardViewCnt }}</td>
            <td>{{ board.boardRegDate }}</td>
          </tr>
        </tbody>
      </table>

      <!-- 글쓰기 버튼 -->
      <button
        type="button"
        id="writeform-btn"
        class="btn btn-outline-dark"
        @click="navigateToWrite"
      >
        <RouterLink :to="{ name: 'boardWrite' }"
          ><i class="bi bi-pencil-square"></i> 글쓰기</RouterLink
        >
      </button>

      <!-- ---------------------------------------------------------------------------- -->
      <!-- 페이지네이션 -->
      <nav aria-label="Page navigation example">
        <ul class="pagination">
          <li class="page-item"><a class="page-link" href="#">Previous</a></li>
          <li class="page-item"><a class="page-link" href="#">1</a></li>
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
      </nav>
      <!-- ---------------------------------------------------------------------------- -->
    </div>

      

      <!-- ---------------------------------------------------------------------------- -->
      <!-- 검색 -->
      <!-- <BoardSearchInput /> -->
      <!-- ---------------------------------------------------------------------------- -->
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


.board-content-box {
  width: 100%;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.content {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#writeform-btn {
  align-self: flex-end;
}

a {
  text-decoration: none;
  color: inherit;
}

.community {
  color: #ffffff;
  font-size: 2em;
  font-weight: bold;
  margin-bottom: 0px;
}
.table {
  width: 90%;
  max-width: 1200px;
  border-radius: 10px;
  overflow: hidden;
  margin: 30px;
}
</style>
