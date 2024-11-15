<template>
    <div class="magazine">
      <div class="magazine2">Magazine</div>
      <!-- <img class="rectangle-32" src="rectangle-320.svg" /> -->
      <div class="_24" v-for="(magazine, index) in magazines" :key="index">
        <img class="_25" :src="magazine.image" />
        <div class="_14">{{ magazine.title }}</div>
      </div>
      <div class="div10">+</div>
      <div class="div11">더보기</div>
    </div>
  </template>
  
  
  <script>
  export default {
    data() {             
      return {
        magazines: [
          { image: require("../../assets/main/magazineZone/img1.png"), title: "매거진 제목1" },
          { image: require("../../assets/main/magazineZone/img2.png"), title: "매거진 제목2" },
          { image: require("../../assets/main/magazineZone/img3.png"), title: "매거진 제목3" },
        ]
      }
    }
  }
  </script>
  
  
  <style scoped>
  .magazine2 {
    color: #ffffff;
    text-align: left;
    font-family: "Inter-Black", sans-serif;
    font-size: 55px;
    font-weight: 900;
  }
  </style>