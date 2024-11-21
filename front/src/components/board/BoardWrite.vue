<template>
  <div class="container">
    <!-- 커뮤니티 텍스트 -->
    <h1 class="logo">Community</h1>
    <div class="writeform-box">
      <div class="mb-3">
        <label for="formGroupExampleInput" class="form-label">제목</label>
        <input
          type="text"
          class="form-control"
          id="formGroupExampleInput"
          placeholder="제목을 입력하세요"
          v-model="board.boardTitle"
        />
      </div>
      <div class="mb-3">
        <label for="formFileMultiple" class="form-label">파일 첨부</label>
        <input
          class="form-control"
          type="file"
          id="formFileMultiple"
          multiple
        />
      </div>
      <div class="mb-3">
        <label for="formGroupExampleInput2" class="form-label">내용</label>
        <textarea
          type="text"
          class="form-control"
          id="board-content"
          placeholder="내용을 입력하세요"
          v-model="board.boardContent"
        ></textarea>
      </div>

      <div class="d-flex justify-content-end">
        <button class="btn btn-primary" @click="createBoard">
          등록
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useBoardStore } from "@/stores/board";

const board = ref({
  title: "",
  writer: "",
  content: "",
});

const store = useBoardStore();

const createBoard = function () {
  store.createBoard(board.value);
};
</script>

<style scoped>
.logo {
  text-align: center;
  margin: 15px 0;
  color: #fff;
  font-weight: bold;
}
#board-content {
  height: 50vb;
}
.community {
  color: #ffffff;
  font-size: 2em;
  font-weight: bold;
  margin-bottom: 0px;
}
.writeform-box {
  background-color: rgba(0, 0, 0, 0.7);
  border-radius: 10px;
  padding: 20px;
}

.btn-primary {
  background-color: #ff5722;
  border-color: #ff5722;
}

.btn-primary:hover {
  background-color: #e64a19;
  border-color: #e64a19;
}
</style>
