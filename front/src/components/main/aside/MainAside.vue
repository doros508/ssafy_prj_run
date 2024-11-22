<template>
  <aside>
    <MainAsideWeather />
    <!-- <Weather_test/> -->
    <MainAsideRace />
  </aside>
</template>

<script setup>
import MainAsideWeather from "./MainAsideWeather.vue";
import MainAsideRace from "./MainAsideRace.vue";
import Weather_test from "./weather_test.vue";
</script>

<style scoped>
aside {
  position: sticky;
  top: 80px;
  background-color: rgba(0, 0, 0, 0.7);
  padding: 10px;
  border-radius: 10px;
}
</style>
