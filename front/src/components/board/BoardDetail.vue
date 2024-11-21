<template>
  <div class="container">
    <h1 class="community">Community</h1>
    <div class="content">
      <div class="board-info">
        <div class="board-info-left">
          <h2>제목</h2>
          <p>작성자</p>
          <p>작성일자</p>
        </div>
        <div class="board-info-right">
          <h2>{{ store.board.boardTitle }}</h2>
          <p>{{ store.board.boardNo}}</p>
          <p>{{ store.board.boardRegDate }}</p>
        </div>
      </div>
      <hr>
      <div class="board-content">
        <h2>내용</h2>
        <h5>{{ store.board.boardContent }}</h5>
      </div>
      <div class="btn-box">
        <button class="btn btn-primary" type="submit" @click="updateBoard">수정</button>
        <button class="btn btn-primary" type="submit">삭제</button>
      </div>
      <div class="board-comment"></div>
    </div>
  </div>
</template>

<script setup>
import { useBoardStore } from "@/stores/board";
import { onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";

const store = useBoardStore();

const route = useRoute();
const router = useRouter();

onMounted(() => {
  store.getBoard(route.params.boardNo);
});

// 게시글 수정
const updateBoard = function (boardNo) {
  router.push({ name: 'boardUpdate'})
}
</script>

<style scoped>

.content{
  background-color: rgba(0, 0, 0, 0.7);
  border-radius: 10px;
  padding: 20px;
  color: white;
}

.board-info{
  background-color: black; /** 확인용 */
  display: flex;
  border-radius: 10px;

}
.board-info-left{
  width: 20%;
}
.board-info-right{
  width: 100%;
}

p {
  margin: 0px;
}

hr {
  color: white;
}

.board-content{
  background-color: blue; /** 확인용 */

}
.board-comment{
  background-color: black; /** 확인용 */

}
.btn-box {
  display: flex;
  justify-content: flex-end; /* 버튼을 오른쪽으로 정렬 */
  margin-top: 10px;
}

.btn {
  margin: 2px;
}

</style>