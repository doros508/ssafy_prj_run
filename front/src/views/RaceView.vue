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
        <MainAside />
      </aside>
    </div>
    <footer></footer>
  </div>
</template>

<script setup>
import TheHeader from "@/components/common/TheHeader.vue";
import MainAside from "@/components/main/aside/MainAside.vue";
import RaceList from "@/components/race/RaceList.vue";
</script>

<style scoped>
* {
  margin: 0px;
  box-sizing: border-box;
}
.body {
  height: 95vh; /* 여기 */
  background: black;
  font-family: sans-serif;
  color: white;
  font-size: 24px;
  display: flex;
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
</style>
