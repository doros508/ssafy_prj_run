<template>
  <div class="container">
    <!-- 커뮤니티 텍스트 -->
    <h1 class="logo">Magazine</h1>
    <div class="writeform-box">
      <div class="mb-3">
        <label for="formGroupExampleInput" class="form-label">제목</label>
        <input
          type="text"
          class="form-control"
          id="formGroupExampleInput"
          placeholder="제목을 입력하세요"
          v-model="magazine.magazineTitle"
        />
      </div>
      <!-- <div class="mb-3">
        <label for="formFileMultiple" class="form-label">썸네일 첨부</label>
        <input
          class="form-control"
          type="file"
          id="formFileMultiple"
          @change="uploadFile"
        />
      </div> -->
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
          id="magazine-content"
          placeholder="내용을 입력하세요"
          v-model="magazine.magazineContent"
        ></textarea>
      </div>
  
      <div class="d-flex justify-content-end">
        <button class="btn btn-primary" @click="createMagazine">
          등록
        </button>
      </div>
    </div>
  </div>
</template>
  
<script setup>
import { ref } from "vue";
import { useMagazineStore } from "@/stores/magazine";
  
const magazine = ref({
  magazineTitle: "", // 제목
  magazineContent: "", // 내용
});

const files = ref([]); // 첨부된 파일 리스트

const store = useMagazineStore();

const uploadFile = function(event) {
  console.log("파일 들어왔다 :)");
  files.value = Array.from(event.target.files); // 첨부된 파일 저장
}

const createMagazine = async function () {
  // 제목과 내용이 비어있는지 확인
  if (!magazine.value.magazineTitle || !magazine.value.magazineContent) {
    alert("제목과 내용을 모두 입력해주세요.");
    return; // 제목과 내용이 비어 있으면 등록을 하지 않음
  }

  const formData = new FormData();
  formData.append("magazine", new Blob([JSON.stringify(magazine.value)], { type: "application/json" }));
  files.value.forEach((file) => {
    formData.append("files", file);
  });

  await store.createMagazine(formData); // 게시글 등록
};
</script>
  
<style scoped>
.logo {
  text-align: center;
  margin: 15px 0;
  color: #fff;
  font-weight: bold;
}
#magazine-content {
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
