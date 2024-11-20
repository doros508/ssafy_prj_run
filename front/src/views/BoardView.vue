<template>
  <div class="body">
    <header>
      <TheHeader />
    </header>
    <div class="main">
      <main>
        <RouterView />
      </main>
      <aside class="right">
        <MainAside />
        <!-- <MainAsideWeather />
              <MainAsideRace /> -->
      </aside>
    </div>
    <footer>
    </footer>
  </div>
</template>

<script setup>
import TheHeader from "@/components/common/TheHeader.vue";
import MainAside from "@/components/main/aside/MainAside.vue";
import MainAsideWeather from "@/components/main/aside/MainAsideWeather.vue";
import MainAsideRace from "@/components/main/aside/MainAsideRace.vue";
import TheFooter from "@/components/common/TheFooter.vue";
</script>

<style scoped>
* {
  margin: 0px;
  box-sizing: border-box;
}
.body {
  height: 100%; /* 여기 */
  background: black;
  font-family: sans-serif;
  color: grey;
  
  /* color: white; */
  font-size: 20px; /* 전체 기본값, 디테일마다 크기 조정할 것 */
  display: felx;
  flex-direction: column;
}
/* .header {

  } */
.main {
  display: flex;
  flex: 1; /* 여기 */
}

main {
  display: flex;
  flex: 5 5 150px;
  justify-content: center;
}

.right {
  /* background: green; */
  padding: 3em 0 3em 0; /* 여기 */
  flex: 1 1 100px;
}

@media all and (max-width: 550px) {
  .main {
    flex-direction: column;
  }
  main {
    padding: 5em 0 5em 0;
  }
}

footer {
  padding: 5em 0 5em 0;
}
</style>
