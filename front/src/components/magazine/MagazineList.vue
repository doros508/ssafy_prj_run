<template>
  <div class="magazine-list">
    <img
      class="magazine-list-background-img"
      src="../../assets/common/background1.png"
    />

    <div class="magazine-content-list">
      <div
        v-for="(magazine, index) in magazines"
        :key="index"
        :class="`magazine-` + (index + 1)"
      >
        <div :class="`magazine-box-` + (index + 1)"></div>
        <img
          :class="`magazine-img-` + (index + 1)"
          :src="magazine.img"
          :art="magazine.title"
        />
        <div :class="`magazine - title - 1` + (index + 1)">
          {{ magazine.title }}
          <br />
        </div>
      </div>
      <!-- <div class="magazine-2">
        <img class="magazine-img-2" src="magazine-img-22.png" />
        <div class="magazine-box-2"></div>
        <div class="magazine-title-2">
          이번주 한강에서 열리는
          <br />
          가을 마라톤
        </div>
      </div>
      <div class="magazine-3">
        <img class="magazine-img-3" src="magazine-img-32.png" />
        <div class="magazine-box-3"></div>
        <div class="magazine-title-3">
          이번주 한강에서 열리는
          <br />
          가을 마라톤
        </div>
      </div>
      <div class="magazine-4">
        <img class="magazine-img-4" src="magazine-img-40.png" />
        <div class="magazine-box-4"></div>
        <div class="magazine-title-4">
          발 볼이 넓은 사람을 위한
          <br />
          러닝화 선택 방법
        </div>
      </div>
      <div class="magazine-5">
        <img class="magazine-img-5" src="magazine-img-21.png" />
        <div class="magazine-box-5"></div>
        <div class="magazine-title-5">
          이번주 한강에서 열리는
          <br />
          가을 마라톤
        </div>
      </div>
      <div class="magazine-6">
        <img class="magazine-img-6" src="magazine-img-31.png" />
        <div class="magazine-box-6"></div>
        <div class="magazine-title-6">
          이번주 한강에서 열리는
          <br />
          가을 마라톤
        </div>
      </div>
      <div class="magazine-7">
        <div class="magazine-box-7"></div>
        <img class="magazine-img-7" src="magazine-img-10.png" />
        <div class="magazine-title-7">
          매거진1
          <br />
        </div>
      </div>
      <div class="magazine-8">
        <img class="magazine-img-8" src="magazine-img-20.png" />
        <div class="magazine-box-8"></div>
        <div class="magazine-title-8">
          이번주 한강에서 열리는
          <br />
          가을 마라톤
        </div>
      </div>
      <div class="magazine-9">
        <img class="magazine-img-9" src="magazine-img-30.png" />
        <div class="magazine-box-9"></div>
        <div class="magazine-title-9">
          이번주 한강에서 열리는
          <br />
          가을 마라톤
        </div>
      </div> -->
    </div>
    <div class="search-bar">
      <div class="search-box"></div>
      <img class="search-icon" :src="searchIcon" />
      <div class="search-text">전체 검색</div>
    </div>
    <div class="magazine-logo">Magazine</div>
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
.magazine-list,
.magazine-list * {
  box-sizing: border-box;
}
.magazine-list {
  height: 1644px;
  position: relative;
}
.magazine-list-background-img {
  width: 100%;
  height: 100%;
  position: absolute;
  right: 0%;
  left: 0%;
  bottom: 0%;
  top: 0%;
  object-fit: cover;
}
.magazine-content-list {
  width: 1799px;
  height: 999px;
  position: static;
}
.magazine-1,
.magazine-2,
.magazine-3,
.magazine-4,
.magazine-5,
.magazine-6,
.magazine-7,
.magazine-8,
.magazine-9 {
  width: 551px;
  height: 282px;
  position: static;
}
.magazine-img-9,
.magazine-img-8,
.magazine-img-7,
.magazine-img-6,
.magazine-img-5,
.magazine-img-4,
.magazine-img-3,
.magazine-img-2,
.magazine-img-1 {
  border-radius: 20px;
  width: 551px;
  height: 280px;
  position: absolute;
  object-fit: cover;
}
.magazine-box-9,
.magazine-box-8,
.magazine-box-7,
.magazine-box-6,
.magazine-box-5,
.magazine-box-4,
.magazine-box-3,
.magazine-box-2,
.magazine-box-1 {
  background: linear-gradient(
    180deg,
    rgba(0, 0, 0, 0) 0%,
    rgba(0, 0, 0, 0.4) 100%
  );
  border-radius: 20px;
  width: 28.68%;
  height: 16.97%;
  position: absolute;
  box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
}
.magazine-title-9,
.magazine-title-8,
.magazine-title-7,
.magazine-title-6,
.magazine-title-5,
.magazine-title-4,
.magazine-title-3,
.magazine-title-2,
.magazine-title-1 {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Black", sans-serif;
  font-size: 32px;
  font-weight: 900;
  position: absolute;
  width: 17.07%;
  bottom: 14.9%;
  height: 4.62%;
}
.search-bar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
}
.search-box {
  width: 50%;
  padding: 10px;
  border: 1px solid #ccc;
}
.search-icon {
  width: 20px;
  height: 20px;
}
.search-text {
  font-size: 16px;
  color: #000;
  margin-left: 10px;
}
.magazine-logo {
  font-size: 24px;
  text-align: center;
  color: #000;
}
</style>
