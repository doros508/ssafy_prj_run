<template>
  <div class="race-widget">
    <h3>대회 일정</h3>
    <ul>
      <li v-for="race in races" :key="race.id" class="race-item">
        <p>{{ race.name }}</p>
        <small>{{ race.date }}</small>
      </li>
    </ul>
    <div v-if="races.length === 0">대회 정보를 불러오는 중...</div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const races = ref([]);

onMounted(async () => {
  // 서버에서 대회 정보 가져오기 (예시 API 호출 코드)
  // const response = await fetch('/api/races');
  // const data = await response.json();
  // races.value = data.slice(0, 5); // 5개의 대회만 표시

  // 예시 데이터
  races.value = [
    { id: 1, name: '서울 마라톤', date: '2024-12-01' },
    { id: 2, name: '부산 마라톤', date: '2024-12-15' },
    { id: 3, name: '대구 러닝', date: '2024-12-22' },
    { id: 4, name: '춘천 마라톤', date: '2024-12-30' },
    { id: 5, name: '제주 런', date: '2025-01-05' },
  ];
});
</script>

<style scoped>
.race-widget {
  background: #333;
  padding: 15px;
  border-radius: 10px;
  color: #fff;
}
.race-item {
  padding: 10px 0;
  border-bottom: 1px solid #444;
}
.race-item:last-child {
  border-bottom: none;
}
</style>
