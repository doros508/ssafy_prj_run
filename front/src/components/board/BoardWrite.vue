<template>
    <div class="board-write">
      <img class="background-img" src="../../assets/board/detail/detail-background.png" alt="background" />
      <div class="content-box">본문 내용을 입력하세요.</div>
      <div class="div">
        <div class="div">
          <div class="box"></div>
          <div class="text">확인</div>
          <img class="div2" src="../../assets/board/write/확인.png" alt="icon" />
        </div>
      </div>
      <div class="title">
        <div class="div3">제목</div>
        <div class="div4">
          <span>
            <span class="div-4-span">제목을 입력하세요.</span>
            <span class="div-4-span2"></span>
          </span>
        </div>
      </div>
      <div class="file-attach">
        <div class="attach-buttton">
          <div class="attach-button-box"></div>
          <div class="attach-button-text">내 pc</div>
        </div>
        <div class="div5">파일 첨부</div>
        <img class="vector-6" src="../../assets/board/write/파일첨부.png" alt="vector" />
      </div>
      <!-- <img class="write-form-box" src="write-form-box0.svg" alt="write form" /> -->
      <div class="magazine-search">
        <div class="search-box"></div>
        <img class="search-icon" src="../../assets/board/write/write-search.png" alt="search icon" />
        <div class="search-text">커뮤니티 검색</div>
      </div>
      <div class="community-logo">Community</div>
    </div>
  </template>
  
  <script>
  export default {
    name: "BoardWrite",
  };
  </script>
  
  <style scoped>
  .board-write,
  .board-write * {
    box-sizing: border-box;
  }
  .board-write {
    height: 1644px;
    position: relative;
  }
  .background-img {
    width: 100%;
    height: 100%;
    position: absolute;
    right: 0%;
    left: 0%;
    bottom: 0%;
    top: 0%;
    object-fit: cover;
  }
  .content-box {
    color: #9a9a9a;
    text-align: left;
    font-family: "Inter-Medium", sans-serif;
    font-size: 24px;
    font-weight: 500;
    position: absolute;
    left: 84px;
    top: 527px;
    width: 323px;
    height: 43px;
  }
  .div {
    width: 101px;
    height: 43px;
    position: static;
  }
  .box {
    background: #205df8;
    border-style: solid;
    border-color: #002bd9;
    border-width: 1px;
    width: 101px;
    height: 43px;
    position: absolute;
    left: 1750px;
    top: 383px;
  }
  .text {
    color: #ffffff;
    text-align: left;
    font-family: "Inter-Bold", sans-serif;
    font-size: 18px;
    font-weight: 700;
    position: absolute;
    left: 1764px;
    top: 394px;
    width: 59px;
    height: 20px;
  }
  .div2 {
    width: 18px;
    height: 23px;
    position: absolute;
    left: 1817px;
    top: 393px;
    object-fit: cover;
  }
  .title {
    width: 1569px;
    height: 33px;
    position: static;
  }
  .div3 {
    color: #ffffff;
    text-align: left;
    font-family: "Inter-Bold", sans-serif;
    font-size: 24px;
    font-weight: 700;
    position: absolute;
    left: 86px;
    top: 393px;
    width: 105px;
    height: 20px;
  }
  .div4 {
    text-align: left;
    font-family: "Inter-Medium", sans-serif;
    font-size: 18px;
    font-weight: 500;
    position: absolute;
    left: 226px;
    top: 398px;
    width: 1429px;
    height: 28px;
    -webkit-text-stroke: 1px #000000;
  }
  .div-4-span {
    color: #a3a3a3;
    font-family: "Inter-Medium", sans-serif;
    font-size: 18px;
    font-weight: 500;
  }
  .div-4-span2 {
    color: #ffffff;
    font-family: "Inter-Medium", sans-serif;
    font-size: 18px;
    font-weight: 500;
  }
  .file-attach {
    width: 193px;
    height: 30px;
    position: static;
  }
  .attach-buttton {
    width: 61px;
    height: 28px;
    position: static;
  }
  .attach-button-box {
    background: #373737;
    border-radius: 4px;
    border-style: solid;
    border-color: #8f8f8f;
    border-width: 2px;
    width: 61px;
    height: 28px;
    position: absolute;
    left: 218px;
    top: 456px;
  }
  .attach-button-text {
    color: #a6a6a6;
    text-align: left;
    font-family: "Inter-Medium", sans-serif;
    font-size: 16px;
    font-weight: 500;
    position: absolute;
    left: 228px;
    top: 461px;
    width: 48px;
    height: 20px;
  }
  .div5 {
    color: #ffffff;
    text-align: left;
    font-family: "Inter-Bold", sans-serif;
    font-size: 24px;
    font-weight: 700;
    position: absolute;
    left: 86px;
    top: 454px;
    width: 148px;
    height: 24px;
  }
  .vector-6 {
    width: 6px;
    height: 12px;
    position: absolute;
    left: 190px;
    top: 464px;
    overflow: visible;
  }
  .write-form-box {
    width: 1793px;
    height: 1231px;
    position: absolute;
    left: calc(50% - 890.5px);
    top: 368px;
    overflow: visible;
  }
  .magazine-search {
    width: 407px;
    height: 50px;
    position: static;
  }
  .search-box {
    background: rgba(255, 255, 255, 0.15);
    border-radius: 25px;
    border-style: solid;
    border-color: rgba(255, 255, 255, 0.4);
    border-width: 1px;
    width: 21.19%;
    height: 3.04%;
    position: absolute;
    right: 3.64%;
    left: 75.17%;
    bottom: 83.03%;
    top: 13.93%;
  }
  .search-icon {
    width: 1.46%;
    height: 1.64%;
    position: absolute;
    right: 22.12%;
    left: 76.42%;
    bottom: 83.7%;
    top: 14.66%;
    object-fit: cover;
  }
  .search-text {
    color: #ffffff;
    text-align: left;
    font-family: "Inter-Medium", sans-serif;
    font-size: 20px;
    font-weight: 500;
    position: absolute;
    right: 15.3%;
    left: 78.66%;
    width: 6.04%;
    bottom: 83.7%;
    top: 14.84%;
    height: 1.46%;
  }
  .community-logo {
    color: #ffffff;
    text-align: left;
    font-family: "Inter-Black", sans-serif;
    font-size: 55px;
    font-weight: 900;
    position: absolute;
    right: 78.08%;
    left: 3.64%;
    width: 18.27%;
    bottom: 83.03%;
    top: 13.26%;
    height: 3.71%;
  }
  </style>
  