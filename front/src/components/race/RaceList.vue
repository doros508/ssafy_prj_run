<template>
    <div class="container">
      <!-- 커뮤니티 로고 -->
      <h1 class="logo">Race</h1>
  
      <div class="content">
        <!-- 대회 카드 리스트 -->

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
  