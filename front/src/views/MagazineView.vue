<template>
  
  <div>
    <header>
      <TheHeader />
    </header>
    <!-- <img
      class="background-img"
      src="../assets/user/sign_up/login_picture.png"
    /> -->
    <!-- <img
      class="background-img"
      src="../assets/magazine/list/background-tmp.png"
    /> -->
    <div class="body">
      <div class="main">
        <main>
          <RouterView />
        </main>
        <aside class="right">
          <MainAside />
        </aside>
      </div>
    </div>
  </div>
</template>

<script setup>
import TheHeader from "@/components/common/TheHeader.vue";
import MainAside from "@/components/main/aside/MainAside.vue";
</script>

<style scoped>
* {
  margin: 0px;
  box-sizing: border-box;
}



/* - 글씨 작은 버전 
.body {
  display: flex;
  justify-content: center;
  height: 100vh;
  background-color: #181818;
} */

.body {
  height: 100%;
  background: #181818;
  font-family: sans-serif;
  color: white;
  font-size: 24px;
  display: flex;
  flex-direction: column;
}


.main {
  width: 100%;
  display: flex;
  justify-content: center;
  height: 100vh;
}


main {
  width: 70%;
  display: flex;
  justify-content: center;
}

.right {
  margin: 20px;
}

/* .background-img {
  position: absolute;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: -1;
} */

</style>

