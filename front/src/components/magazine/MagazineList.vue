<template>
  <div class="magazine-list">
    <img
      class="magazine-list-background-img"
      src="../../assets/common/background1.png"
    />
    <div class="magazine-logo">Magazine</div>

    <div class="search-bar">
      <span class="search-box">
        <img class="search-icon" :src="searchIcon" />
        <span class="search-text">전체 검색</span>
      </span>
    </div>

    <div class="magazine-content-list">
      <div
        v-for="(magazine, index) in magazines"
        :key="index"
        :class="`magazine-item`"
      >
        <div :class="`magazine-box`"></div>
        <img
          :class="`magazine-img`"
          :src="magazine.image"
          :art="magazine.title"
        />
        <div :class="`magazine-title`">
          {{ magazine.title }}
          <br />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import img1 from "../../assets/magazine/list/img1.png";
import img2 from "../../assets/magazine/list/img2.png";
import img3 from "../../assets/magazine/list/img3.png";
import searchIcon from "../../assets/common/search.png";

export default {
  name: "MagazineList",
  data() {
    return {
      magazines: [
        { image: img1, title: "매거진 제목1" },
        { image: img2, title: "매거진 제목2" },
        { image: img3, title: "매거진 제목3" },
        { image: img1, title: "매거진 제목4" }, //사진 파일 수정하기.
        { image: img2, title: "매거진 제목5" },
        { image: img3, title: "매거진 제목6" },
        { image: img1, title: "매거진 제목7" },
        { image: img2, title: "매거진 제목8" },
        { image: img3, title: "매거진 제목9" },
      ],
    };
  },
};
</script>

<style scoped>
.magazine-list {
  min-height: 100vh;
  background-color: #000;
  padding: 2rem;
  position: relative;
}

.magazine-list-background-img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: 0;
}

.magazine-logo {
  color: #fff;
  font-size: 2.5rem;
  font-weight: bold;
  margin-bottom: 2rem;
  position: relative;
  z-index: 1;
}

.search-bar {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 2rem;
  position: relative;
  z-index: 1;
}

.search-box {
  background: rgba(255, 255, 255, 0.1);
  border-radius: 25px;
  padding: 0.5rem 1rem;
  display: flex;
  align-items: center;
  width: 300px;
}

.search-icon {
  width: 30px;
  height: 30px;
  margin-right: 10px;
}

.search-text {
  color: #fff;
}

.magazine-content-list {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2rem;
  position: relative;
  z-index: 1;
}

.magazine-item {
  position: relative;
  border-radius: 20px;
  overflow: hidden;
  aspect-ratio: 16/9;
}

.magazine-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 20px;
}

.magazine-box {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 50%;
  background: linear-gradient(to bottom, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.7));
  border-radius: 20px;
  z-index: 1;
}

.magazine-title {
  position: absolute;
  bottom: 1.5rem;
  left: 1.5rem;
  color: #fff;
  font-size: 1.5rem;
  font-weight: bold;
  z-index: 2;
  /* 제목 최대 너비 제한 */
  max-width: 90%;
  /* 자동 줄바꿈 */
  word-wrap: break-word;
  overflow-wrap: break-word;
}
</style>
