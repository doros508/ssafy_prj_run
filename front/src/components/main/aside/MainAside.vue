<template>
    <aside class="main-aside">
      <MainAsideWeather_tmp />
      <!-- <MainAsideWeather/> -->
      <!-- <WeatherForecast/> -->
      <MainAsideRace />
      
    </aside>
  </template>
  
  <script setup>
  // import MainAsideWeather from './MainAsideWeather.vue';
  import MainAsideWeather_tmp from './MainAsideWeather_tmp.vue';
  import MainAsideRace from './MainAsideRace.vue';
  // import WeatherForecast from '@/components/weather/WeatherForecast.vue';
  </script>
  
  <style scoped>
  .main-aside {
    background: #1c1c1e;
    padding: 20px;
    border-radius: 10px;
    position: sticky;
    top: 120px;
  }
  </style>
  