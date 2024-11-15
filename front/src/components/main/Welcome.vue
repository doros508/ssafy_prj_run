<template>
    <div class="main-page">
        <img class="main-photo" src="../../assets/main/main_photo.png" alt="welcome 배경"/>
        <div class="main-slogan">
            RUNNING
            <br />
            TOGETHER
        </div>
    </div>
</template>

<script setup>

</script>

<style>
.main-page,
.main-page * {
  box-sizing: border-box;
}

.main-photo {
  width: 100%;
  height: 100%;
  position: absolute;
  right: 0%;
  left: 0%;
  bottom: 0%;
  top: 0%;
  object-fit: cover;
}

.main-page {
  height: 100vh; /* 화면의 전체 높이를 차지하도록 설정 */
  position: relative;
  background-image: url("../assets/main/main_photo.png"); /*배경 이미지 설정*/
  background-size: cover; /* 이미지가 컨테이너를 덮도록 설정 */
  background-position: center; /* 이미지가 중앙에 오도록 설정 */
}

.main-slogan {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-BlackItalic", sans-serif;
  font-size: 170px;
  line-height: 160px;
  font-weight: 900;
  font-style: italic;
  position: relative;
  right: 35.73%;
  left: 3.54%;
  width: 60.73%;
  bottom: 6.69%;
  top: 73.11%;
  height: 20.19%;
}
</style>