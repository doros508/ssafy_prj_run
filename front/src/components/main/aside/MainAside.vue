<template>
  <aside>
    <MainAsideWeather />
    <!-- <Weather_test/> -->
    <MainAsideRace />
    <iframe
      src="https://www.chatbase.co/chatbot-iframe/TD2TzvmI-IkK-9GsOKgV7"
      width="100%"
      style="height: 100%; min-height: 500px"
      frameborder="0"
    ></iframe>
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

iframe {
  border-radius: 10px;
  margin-top: 10px;
}
</style>
