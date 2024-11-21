<!-- RaceList.vue -->
<template>
  <div class="container">
    <h1 class="logo">Race Events</h1>
    
    <div class="content">
      <!-- Search Filters -->
      <div class="row mb-4">
        <div class="col-md-4">
          <div class="form-group">
            <label for="dateFilter" class="text-white mb-2">날짜</label>
            <input type="month" class="form-control" id="dateFilter" v-model="filters.date">
          </div>
        </div>
        <div class="col-md-4">
          <div class="form-group">
            <label for="regionFilter" class="text-white mb-2">지역</label>
            <select class="form-control" id="regionFilter" v-model="filters.cityNo">
              <option value="">전체</option>
              <option value="1">서울</option>
              <option value="2">경기</option>
              <option value="3">인천</option>
              <option value="4">강원</option>
              <option value="5">충청</option>
              <option value="6">전라</option>
              <option value="7">경상</option>
              <option value="8">제주</option>
            </select>
          </div>
        </div>
        <div class="col-md-4">
          <div class="form-group">
            <label for="distanceFilter" class="text-white mb-2">거리</label>
            <select class="form-control" id="distanceFilter" v-model="filters.distance">
              <option value="">전체</option>
              <option value="5K">5KM</option>
              <option value="10K">10KM</option>
              <option value="Half">하프코스</option>
              <option value="Full">풀코스</option>
            </select>
          </div>
        </div>
      </div>

      <!-- Search Button -->
      <div class="row mb-4">
        <div class="col-12">
          <button class="btn btn-primary w-100" @click="applyFilters">검색</button>
        </div>
      </div>

      <!-- Race List Table -->
      <table class="table table-dark table-striped table-hover">
        <thead>
          <tr>
            <th scope="col">번호</th>
            <th scope="col">대회명</th>
            <th scope="col">대회 일자</th>
            <th scope="col">장소</th>
            <th scope="col">거리</th>
            <th scope="col">등록일</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="race in store.raceList" :key="race.raceNo">
            <td>{{ race.raceNo }}</td>
            <td>
              <RouterLink :to="`/race/${race.raceNo}`">{{ race.raceName }}</RouterLink>
            </td>
            <td>{{ formatDate(race.raceDate) }}</td>
            <td>{{ race.racePlace }}</td>
            <td>{{ race.raceDistance }}</td>
            <td>{{ formatDate(race.raceRegDate) }}</td>
          </tr>
        </tbody>
      </table>

      <!-- Register Button -->
      <button type="button" id="writeform-btn" class="btn btn-primary" @click="writeRace">
        <i class="bi bi-pencil-square"></i> 대회 등록
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useRaceStore } from '@/stores/race'

const store = useRaceStore()
const router = useRouter()

const filters = ref({
  date: '',
  cityNo: '',
  distance: ''
})

// 날짜 포맷팅 함수
const formatDate = (dateString) => {
  if (!dateString) return ''
  const date = new Date(dateString)
  return date.toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit'
  })
}

// 필터링된 레이스 목록
const filteredRaces = computed(() => {
  let results = store.raceList

  if (filters.value.date) {
    results = results.filter(race => 
      race.raceDate.includes(filters.value.date)
    )
  }

  if (filters.value.cityNo) {
    results = results.filter(race => 
      race.cityNo === parseInt(filters.value.cityNo)
    )
  }

  if (filters.value.distance) {
    results = results.filter(race => 
      race.raceDistance === filters.value.distance
    )
  }

  return results
})

// 필터 적용
const applyFilters = async () => {
  try {
    if (filters.value.cityNo) {
      await store.getRacesByCity(parseInt(filters.value.cityNo))
    } else {
      await store.getRaceList()
    }
  } catch (error) {
    console.error('Error applying filters:', error)
  }
}
const writeRace = () => {
  router.push({ name: 'raceWrite' })
}

onMounted(() => {
  store.getRaceList()
})
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
  /* height: auto; */
  /* min-height: 500px; */
}

.table {
  text-align: center;
  border-radius: 15px;
  overflow: hidden;
  margin-bottom: 20px;
}

.form-control {
  background-color: rgba(255, 255, 255, 0.9);
}

.form-control:focus {
  background-color: #fff;
}

#writeform-btn {
  /* float: right; */
  align-self: flex-end; /* 버튼을 오른쪽 끝에 배치 */
  margin-top: 10px;
}

.btn-primary {
  background-color: #ff5722;
  border-color: #ff5722;
}

.btn-primary:hover {
  background-color: #e64a19;
  border-color: #e64a19;
}

a {
  color: white;
  text-decoration: none;
}

a:hover {
  color: #ff5722;
}

label {
  font-weight: bold;
}

.form-group {
  margin-bottom: 15px;
}
</style>