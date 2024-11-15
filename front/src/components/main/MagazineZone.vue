<template>
  <div class="main-container">
    <!-- Magazine Section -->
    <div class="magazine-section">
      <h2 class="section-title">Magazine</h2>
      <div class="magazine-container">
        <div class="magazine-item" v-for="(magazine, index) in magazines" :key="index">
          <img class="magazine-image" :src="magazine.image" alt="magazine image" />
          <h3 class="magazine-title">{{ magazine.title }}</h3>
        </div>
      </div>
    </div>

 
 
  </div>
</template>

    
    
    <script>
    import img1 from "../../assets/main/magazineZone/img1.png";
    import img2 from "../../assets/main/magazineZone/img2.png";
    import img3 from "../../assets/main/magazineZone/img3.png";

    export default {
      data() {             
        return {

          magazines: [
            { image: img1, title: "매거진 제목1" },
            { image: img2, title: "매거진 제목2" },
            { image: img3, title: "매거진 제목3" },
          ]
        }
      }
    }
    </script>
    
    
    <style scoped>
/* 전체 레이아웃 */
.main-container {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 20px;
  margin: 20px;
}

/* 매거진 섹션 스타일 */
.magazine-section {
  display: flex;
  flex-direction: column;
}

.magazine-container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 15px;
}

.magazine-item {
  display: flex;
  flex-direction: column;
  background: #222;
  border-radius: 8px;
  overflow: hidden;
  text-align: center;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
}

.magazine-image {
  width: 100%;
  height: 150px;
  object-fit: cover;
}

.magazine-title {
  font-size: 16px;
  margin: 10px 0;
  color: #fff;
  font-weight: bold;
}

/* Weather 및 Race 섹션 스타일 */
.section-title {
  font-size: 20px;
  margin-bottom: 10px;
  text-align: center;
}

/* 반응형 디자인 */
@media screen and (max-width: 768px) {
  .main-container {
    grid-template-columns: 1fr;
  }


}
</style>
