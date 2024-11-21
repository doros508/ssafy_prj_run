<template>
    <div class="container">
      <h1 class="logo">Magazine</h1>
      <div class="content">
        <div class="magazine-info">
          <div class="magazine-info-left">
            <h3>제목</h3>
            <p>작성자</p>
            <p>작성일자</p>
          </div>
          <div class="magazine-info-right">
            <h3>{{ store.magazine.magazineTitle }}</h3>
            <p>{{ store.magazine.magazineNo}}</p>
            <p>{{ store.magazine.magazineRegDate }}</p>
          </div>
        </div>
        <hr>
        <div class="magazine-content">
          <!-- <h5>내용</h5> -->
          <p>{{ store.magazine.magazineContent }}</p>
        </div>
        <div class="btn-box">
          <button class="btn btn-primary" type="submit" @click="updateMagazine">수정</button>
          <button class="btn btn-primary" type="submit" @click="deleteMagazine">삭제</button>
        </div>
        <div class="magazine-comment">
          <!-- 여기에 댓글ㅎ -->
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { useMagazineStore } from "@/stores/magazine";
  import { onMounted } from "vue";
  import { useRoute, useRouter } from "vue-router";
  
  const store = useMagazineStore();
  
  const route = useRoute();
  const router = useRouter();
  
  onMounted(() => {
    store.getMagazine(route.params.magazineNo);
  });
  
  // 게시글 수정
  const updateMagazine = function (magazineNo) {
    router.push({ name: 'magazineUpdate'})
  }
  
  // 게시글 삭제
  const deleteMagazine = function() {
          console.log(route.params.magazineNo) 
          store.deleteMagazine(route.params.magazineNo)
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
  
  .magazine-info{
    background-color: black;
    display: flex;
    border-radius: 10px;
    padding: 10px;
  }
  .magazine-info-left{
    width: 20%;
  }
  .magazine-info-right{
    width: 100%;
  }
  .magazine-content {
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