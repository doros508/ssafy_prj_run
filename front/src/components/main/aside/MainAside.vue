<template>
    <aside class="main-aside">
      <MainAsideWeather />
      <MainAsideRace />
    </aside>
  </template>
  
  <script setup>
  import MainAsideWeather from './MainAsideWeather.vue';
  import MainAsideRace from './MainAsideRace.vue';
  </script>
  
  <style scoped>
  .main-aside {
    position: sticky;
    top: 70px;
    width: 300px;
    background: #1c1c1e;
    padding: 20px;
    border-radius: 10px;
  }
  </style>
  