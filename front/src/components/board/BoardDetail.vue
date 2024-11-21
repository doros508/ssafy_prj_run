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
          <h3>{{ store.board.boardTitle }}</h3>
          <p>{{ store.board.boardNo}}</p>
          <p>{{ store.board.boardRegDate }}</p>
        </div>
      </div>
      <hr>
      <div class="board-content">
        <!-- <h5>내용</h5> -->
        <p>{{ store.board.boardContent }}</p>
      </div>
      <div class="btn-box">
        <button class="btn btn-primary" type="submit" @click="updateBoard">수정</button>
        <button class="btn btn-primary" type="submit" @click="deleteBoard">삭제</button>
      </div>
      <div class="board-comment">
        <!-- 여기에 댓글ㅎ -->
      </div>
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

// 게시글 삭제
const deleteBoard = function() {
        console.log(route.params.boardNo) 
        store.deleteBoard(route.params.boardNo)
    }
</script>

<style scoped>
.logo {
  text-align: center;
  margin: 15px 0;
  color: #fff;
  font-weight: bold;
}
.content{
  background-color: rgba(0, 0, 0, 0.8);
  border-radius: 10px;
  padding: 20px;
  color: white;
}

.board-info{
  background-color: black;
  display: flex;
  border-radius: 10px;
  padding: 10px;
}
.board-info-left{
  width: 20%;
}
.board-info-right{
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

</style>