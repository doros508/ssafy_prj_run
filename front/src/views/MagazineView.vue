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



.body {
  display: flex;
  justify-content: center;
  height: 100%;
  background-color: black;
}

.main {
  width: 80%;
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

