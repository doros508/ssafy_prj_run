<template>
  <div class="container">
    <h1 class="logo">Community</h1>
    <div class="content">
      <table class="table table-dark table-striped table-hover">
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
          <tr v-for="board in boardStore.boardList" :key="board.boardNo">
            <td>{{ board.boardNo }}</td>
            <td>
              <RouterLink :to="`/board/${board.boardNo}`">{{
                board.boardTitle
              }}</RouterLink>
            </td>
            <td>{{ board.userNo }}</td>
            <td>{{ board.boardViewCnt }}</td>
            <td>{{ board.boardRegDate }}</td>
          </tr>
        </tbody>
      </table>

      <button
        type="button"
        id="writeform-btn"
        class="btn btn-primary"
        @click="writeBoard"
      >
        <i class="bi bi-pencil-square"></i> 글쓰기
      </button>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore";
import { useBoardStore } from "@/stores/board";
import { onMounted } from "vue";
import { useRouter } from "vue-router";

const userStore = useUserStore();
const boardStore = useBoardStore();
const router = useRouter();

onMounted(() => {
  boardStore.getBoardList();
});

const writeBoard = function() {
  if (!userStore.isLoggedIn) {
    alert("로그인이 필요한 서비스입니다.");
    router.push({ name: "login" });
    return;
  }
  router.push({ name: "boardWrite" });
};
</script>

<style scoped>
* {
  text-decoration: none;
}

a {
  color: white;
}
.logo {
  text-align: center;
  margin: 15px 0;
  color: #fff;
  font-weight: bold;
}

.table {
  text-align: center;
  border-radius: 15px;
  overflow: hidden;
}

.content {
  position: relative;
  width: 100%;
  padding: 20px;
  background-color: rgba(0, 0, 0, 0.7);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#writeform-btn {
  align-self: flex-end; /* 버튼을 오른쪽 끝에 배치 */
  margin-top: 0; /* 버튼과 다른 요소들 간 간격 조정 */
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
