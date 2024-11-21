<template>
  <div class="body">
    <header>
      <TheHeader />
    </header>
    <div class="main">
      <main>
        <RaceList />
      </main>
      <aside class="right">
        <MainAside/>
      </aside>
    </div>
    <footer></footer>
  </div>
</template>

<script setup>
import TheHeader from "@/components/common/TheHeader.vue";
import MainAside from "@/components/main/aside/MainAside.vue";
// import MainAside from "@/components/main/aside/MainAside.vue";
import MainAsideWeather from "@/components/main/aside/MainAsideWeather.vue";
import RaceList from "@/components/race/RaceList.vue";
</script>

<style scoped>
* {
  margin: 0px;
  box-sizing: border-box;
}
.body {
  height: 100vh; /* 여기 */
  background: #181818;
  font-family: sans-serif;
  color: white;
  font-size: 24px;
  display: flex;
  flex-direction: column;
  
}
/* .header {

    } */
/* .main {
  display: flex;
  flex: 1;
}

main {
  display: flex;
  flex: 5 5 150px;
  justify-content: center;
  
}

.right {
  padding: 3em 0 3em 0;
  flex: 1 1 100px;
} */

.main {
  width: 100%;
  display: flex;
  justify-content: center;
  height: 100%;
}


main {
  width: 70%;
  display: flex;
  justify-content: center;
}

.right {
  margin: 20px;
}

@media all and (max-width: 550px) {
  .main {
    flex-direction: column;
  }
  main {
    padding: 5em 0 5em 0;
  }
}
</style>
