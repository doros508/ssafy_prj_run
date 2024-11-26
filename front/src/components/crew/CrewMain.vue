CrewMain
<template>
  <div class="container">
    <h1 class="logo">Crew</h1>

    <div class="content">
      <!-- Search Filters -->
      <div class="row mb-4">
        <div class="col-md-4">
          <div class="form-group">
            <label for="cityFilter" class="text-white mb-2">도시</label>
            <select
              class="form-control"
              id="cityFilter"
              v-model="filters.cityNo"
              @change="loadDistricts"
            >
              <option value="">전체</option>
              <option
                v-for="city in cities"
                :key="city.cityNo"
                :value="city.cityNo"
              >
                {{ city.cityName }}
              </option>
            </select>
          </div>
        </div>
        <div class="col-md-4">
          <div class="form-group">
            <label for="districtFilter" class="text-white mb-2">구</label>
            <select
              class="form-control"
              id="districtFilter"
              v-model="filters.districtNo"
            >
              <option value="">전체</option>
              <option
                v-for="district in districts"
                :key="district.districtNo"
                :value="district.districtNo"
              >
                {{ district.districtName }}
              </option>
            </select>
          </div>
        </div>
        <div class="col-md-4">
          <div class="form-group">
            <label for="daysFilter" class="text-white mb-2">모임요일</label>
            <select class="form-control" id="daysFilter" v-model="filters.days">
              <option value="">전체</option>
              <option value="1">월요일</option>
              <option value="2">화요일</option>
              <option value="3">수요일</option>
              <option value="4">목요일</option>
              <option value="5">금요일</option>
              <option value="6">토요일</option>
              <option value="7">일요일</option>
            </select>
          </div>
        </div>
      </div>

      <!-- Search Button -->
      <div class="row mb-4">
        <div class="col-12">
          <button class="btn btn-primary w-100" @click="searchCrews">
            검색
          </button>
        </div>
      </div>

      <!-- Crew List Table -->
      <table class="table table-dark table-striped table-hover">
        <thead>
          <tr>
            <th scope="col">크루명</th>
            <th scope="col">모임장소</th>
            <th scope="col">크루인원</th>
            <th scope="col">모임요일</th>
            <th scope="col">크루 홈페이지</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="crew in filteredCrews.slice(0, 15)" :key="crew.crewNo">
            <td>{{ crew.crewName }}</td>
            <td>{{ crew.crewLocation }}</td>
            <td>{{ crew.crewSize }}</td>
            <td>{{ crew.crewDay }}</td>
            <td>
              <a :href="crew.crewUrl" target="_blank" class="btn btn-link">
                바로가기
              </a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useCrewStore } from "@/stores/useCrewStore";

const store = useCrewStore();
const emit = defineEmits(["filter-crews"]); // defineEmits를 사용해 이벤트 정의

const filters = ref({
  cityNo: "",
  districtNo: "",
  days: [],
});

const filteredCrews = computed(() => {
  let results = store.crewList;

  if (filters.value.cityNo) {
    results = results.filter(
      (crew) => crew.cityNo === parseInt(filters.value.cityNo)
    );
  }

  if (filters.value.districtNo) {
    results = results.filter(
      (crew) => crew.districtNo === parseInt(filters.value.districtNo)
    );
  }

  if (filters.value.days && filters.value.days.length > 0) {
    const selectedDays = Array.isArray(filters.value.days)
      ? filters.value.days
      : [filters.value.days];

    results = results.filter((crew) => {
      const crewDays = crew.crewDay.split(",");
      return selectedDays.some((day) => {
        return crewDays.includes(getDayName(day)[0]);
      });
    });
  }

  return results;
});

const cities = computed(() => store.cityList);
const districts = computed(() => store.districtList);

const loadCitiesAndDistricts = async () => {
  await store.getCityList();
};

const loadDistricts = async () => {
  if (filters.value.cityNo) {
    await store.getDistrictList(filters.value.cityNo);
  }
};

const searchCrews = async () => {
  await store.getCrewList();
  // 필터링된 결과를 부모 컴포넌트로 전달
  emit("filter-crews", filteredCrews.value);
};

const getDayName = (dayNumber) => {
  const days = [
    "월요일",
    "화요일",
    "수요일",
    "목요일",
    "금요일",
    "토요일",
    "일요일",
  ];
  return days[dayNumber - 1];
};

onMounted(() => {
  loadCitiesAndDistricts();
  store.getCrewList();
});
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
  background-color: rgba(0, 0, 0, 0.5);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
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
  align-self: flex-end;
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
