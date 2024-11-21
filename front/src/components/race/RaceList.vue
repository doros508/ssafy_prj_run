<template>
  <div class="container">
    <h1 class="logo">Race Events</h1>
    <div class="content">
      <table class="table table-dark table-striped table-hover">
        <thead>
          <tr>
            <th scope="col">번호</th>
            <th scope="col">대회명</th>
            <th scope="col">대회 일자</th>
            <th scope="col">장소</th>
            <th scope="col">거리</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="race in raceList" :key="race.raceNo">
            <td>{{ race.raceNo }}</td>
            <td>
              <RouterLink :to="`/race/${race.raceNo}`">{{ race.raceName }}</RouterLink>
            </td>
            <td>{{ race.raceDate }}</td>
            <td>{{ race.racePlace }}</td>
            <td>{{ race.raceDistance }}</td>
          </tr>
        </tbody>
      </table>
      <button type="button" id="writeform-btn" class="btn btn-primary" @click="writeRace">
        <i class="bi bi-pencil-square"></i> 대회 등록
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const raceList = ref([])
const router = useRouter()

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/race')
    raceList.value = response.data
  } catch (error) {
    console.error('Error fetching races:', error)
  }
})

const writeRace = () => {
  router.push({ name: 'raceWrite' })
}
</script>

<style scoped>
.logo {
  text-align: center;
  margin: 15px 0;
  color: #fff;
  font-weight: bold;
}

.content {
  position: relative;
  width: 100%;
  padding: 20px;
  background-color: rgba(0, 0, 0, 0.7);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.table {
  text-align: center;
  border-radius: 15px;
  overflow: hidden;
}

#writeform-btn {
  align-self: flex-end;
  margin-top: 10px;
  background-color: #ff5722;
  border-color: #ff5722;
}

#writeform-btn:hover {
  background-color: #e64a19;
  border-color: #e64a19;
}

a {
  color: grey;
  text-decoration: none;
}
</style>