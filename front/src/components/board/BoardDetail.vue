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
      <div class="board-contnent">
        <h2>내용</h2>
        <h5>{{ store.board.boardContent }}</h5>
      </div>
      <div class="board-comment"></div>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { onMounted } from "vue";
import { useBoardStore } from "@/stores/board";

const route = useRoute();
const store = useBoardStore();

onMounted(() => {
  console.log("route.params.boardNo: ", route.params.boardNo);
  store.getBoard(route.params.boardNo);
});
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

</style>