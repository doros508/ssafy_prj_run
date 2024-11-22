<template>
  <div class="race-widget">
    <h4><i class="bi bi-trophy-fill"></i>대회 일정</h4>
    <ul>
      <li v-for="race in store.raceList.slice(0, 5)" :key="race.raceNo" class="race-item">
        <p>{{ race.raceName }}</p>
        <small>{{ race.raceDate }}</small>
      </li>
    </ul>
    <div v-if="store.raceList.length === 0">대회 정보를 불러오는 중...</div>
  </div>
</template>

<script setup>
import { useRaceStore } from '@/stores/race'
import { ref, onMounted } from 'vue';

const store = useRaceStore()
// const raceList = ref([]);

onMounted(() => {
  store.getRaceList()
})

</script>

<style scoped>
.race-widget {
  background-color: #212529;
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
.bi-trophy-fill {
  color: gold;
}
p {
  margin: 0px;
}
ul {
  padding: 0px;
}
li {
  list-style-type: none;
}
</style>
