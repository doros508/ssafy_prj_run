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
}

.main-slogan {
color: #ffffff;
text-align: left;
font-family: "Inter-BlackItalic", sans-serif;
font-size: 170px;
line-height: 160px;
font-weight: 900;
font-style: italic;
position: absolute;
right: 5%; /* 오른쪽 여백을 설정 */
left: 5%; /* 왼쪽 여백을 설정 */
bottom: 10%; /* 하단 여백을 설정 */
width: auto;
max-width: 90%; /* 너비가 너무 커지지 않도록 최대 너비 설정 */
height: auto;
}
</style>
