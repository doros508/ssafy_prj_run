<template>
  <aside>
    <MainAsideWeather />
    <MainAsideRace />
  </aside>
</template>

<script setup>
import MainAsideWeather from "./MainAsideWeather.vue";
import MainAsideRace from "./MainAsideRace.vue";
</script>

<style scoped>
aside {
  position: sticky;
  top: 60px;
  background-color: rgba(0, 0, 0, 0.7);
  padding: 10px;
  border-radius: 10px;
}
</style>
