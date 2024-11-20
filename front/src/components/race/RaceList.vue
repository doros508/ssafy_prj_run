<template>
    <div class="container">
      <!-- 커뮤니티 텍스트 -->
      <h1 class="community">Community</h1>
  
      <div class="content">
        <!-- 대회 카드 리스트 -->
        <div class="card" style="width: 18rem;" v-for="race in store.raceList" :key="board.boardNo">
            <img src="../../assets/race/image.png" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title">춘천봄내마라톤대회</h5>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">일시</li>
                <li class="list-group-item">장소</li>
                <li class="list-group-item">종목</li>
            </ul>
            <!-- <div class="card-body">
                <a href="여기에 디테일 또는 공식 홈페이지" class="card-link"></a>
            </div> -->
            <div class="d-grid gap-2">
                <button class="btn btn-primary" type="button">Button</button>
            </div>
            </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { useBoardStore } from "@/stores/board";
  import { onMounted } from "vue";
  
  const store = useBoardStore();
  onMounted(() => {
    store.getBoardList();
  });
  </script>
  
  <style scoped>
  * {
    text-decoration: none;
  }
  a {
    color: grey;
  }
  h1 {
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
  </style>
  