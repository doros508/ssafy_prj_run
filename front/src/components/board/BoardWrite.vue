<template>
  <div class="container">
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
          @change="uploadFile"
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
        <button class="btn btn-primary" @click="createBoard">등록</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useBoardStore } from "@/stores/board";
import { useUserStore } from "@/stores/userStore";
import { useRouter } from "vue-router";

const userStore = useUserStore();
const boardStore = useBoardStore();
const router = useRouter();

const board = ref({
  boardTitle: "",
  boardContent: "",
  userNo: null
});

const files = ref([]);

onMounted(() => {
  if (!userStore.isLoggedIn || !userStore.user) {
    alert("로그인이 필요한 서비스입니다.");
    router.push("/login");
    return;
  }
  // Set userNo from logged in user
  board.value.userNo = userStore.user.userNo;
});

const uploadFile = function(event) {
  files.value = Array.from(event.target.files);
};

const createBoard = async function() {
  try {
    if (!board.value.boardTitle || !board.value.boardContent) {
      alert("제목과 내용을 모두 입력해주세요.");
      return;
    }

    const formData = new FormData();
    const boardData = {
      boardTitle: board.value.boardTitle,
      boardContent: board.value.boardContent,
      userNo: userStore.user.userNo
    };

    formData.append(
      "board",
      new Blob([JSON.stringify(boardData)], { type: "application/json" })
    );

    if (files.value.length > 0) {
      files.value.forEach((file) => {
        formData.append("files", file);
      });
    }

    await boardStore.createBoard(formData);
    router.push({ name: "boardList" });
  } catch (error) {
    console.error("게시글 작성 실패:", error);
    alert("게시글 작성에 실패했습니다.");
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
