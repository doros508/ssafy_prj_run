<template>
  <div class="weather-widget">
    <h3>현재 날씨</h3>
    <div v-if="weatherData">
      <p>{{ weatherData.location }}: {{ weatherData.temperature }}°C</p>
      <p>{{ weatherData.description }}</p>
    </div>
    <div v-else>
      <p>날씨 정보를 불러오는 중...</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const weatherData = ref(null);

onMounted(async () => {
  // 날씨 API 예시 코드 (실제 API 사용 시 API_KEY와 URL 변경)
  const response = await fetch(`https://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=YOUR_API_KEY&units=metric`);
  const data = await response.json();
  weatherData.value = {
    location: data.name,
    temperature: data.main.temp,
    description: data.weather[0].description,
  };

  // 예시 데이터
  weatherData.value = {
    location: 'Seoul',
    temperature: 21,
    description: '맑음',
  };
});
</script>

<style scoped>
.weather-widget {
  background: #333;
  padding: 15px;
  border-radius: 10px;
  color: #fff;
  margin-bottom: 20px;
}
</style>
