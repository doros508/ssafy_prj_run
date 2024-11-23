<template>
  <div class="container">
    <h1 class="logo">크루 검색</h1>

    <div class="content">
      <!-- Search Filters -->
      <div class="row mb-4">
        <!-- 지역 선택 -->
        <div class="col-md-4">
          <div class="form-group">
            <label for="cityFilter" class="text-white mb-2">도시</label>
            <select
              class="form-control"
              id="cityFilter"
              v-model="filters.cityNo"
              @change="onCityChange"
            >
              <option value="">전체</option>
              <option v-for="city in cities" :key="city.id" :value="city.id">
                {{ city.name }}
              </option>
            </select>
          </div>
        </div>

        <!-- 구 선택 -->
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
                :key="district.id"
                :value="district.id"
              >
                {{ district.name }}
              </option>
            </select>
          </div>
        </div>

        <!-- 요일 선택 -->
        <div class="col-md-4">
          <div class="form-group">
            <label for="daysFilter" class="text-white mb-2">모임 요일</label>
            <select
              class="form-control"
              id="daysFilter"
              v-model="filters.days"
              multiple
            >
              <option value="Mon">월</option>
              <option value="Tue">화</option>
              <option value="Wed">수</option>
              <option value="Thu">목</option>
              <option value="Fri">금</option>
              <option value="Sat">토</option>
              <option value="Sun">일</option>
            </select>
          </div>
        </div>
      </div>

      <!-- Search Button -->
      <div class="row mb-4">
        <div class="col-12">
          <button class="btn btn-primary w-100" @click="applyFilters">
            검색
          </button>
        </div>
      </div>

      <!-- Crew List Table -->
      <table class="table table-dark table-striped table-hover">
        <thead>
          <tr>
            <th scope="col">크루명</th>
            <th scope="col">장소</th>
            <th scope="col">모임 요일</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="crew in filteredCrews" :key="crew.id">
            <td>{{ crew.name }}</td>
            <td>{{ crew.location }}</td>
            <td>{{ crew.meetingDays.join(", ") }}</td>
          </tr>
        </tbody>
      </table>

      <!-- Register Button -->
      <button
        type="button"
        id="writeform-btn"
        class="btn btn-primary"
        @click="writeCrew"
      >
        <i class="bi bi-pencil-square"></i> 크루 등록
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useCrewStore } from "@/stores/crew";

const store = useCrewStore();

const filters = ref({
  cityNo: "",
  districtNo: "",
  days: [],
});

// 지역과 구 데이터 가져오기
const cities = ref([]);
const districts = ref([]);

const loadCitiesAndDistricts = async () => {
  // 시, 구 목록을 각각 불러오는 메서드
  cities.value = await store.getCityList(); // getCityList()로 대체
  districts.value = await store.getDistrictList(filters.value.cityNo); // getDistrictList()로 대체
};

onMounted(() => {
  loadCitiesAndDistricts();
  store.getCrewList();
});

// 시 변경 시 구 목록 업데이트
const onCityChange = async () => {
  await loadCitiesAndDistricts();
};

// 필터링된 크루 목록
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

  if (filters.value.days.length) {
    results = results.filter((crew) =>
      filters.value.days.every((day) => crew.meetingDays.includes(day))
    );
  }

  return results;
});

// 필터 적용
const applyFilters = () => {
  store.applyCrewFilters(filters.value);
};

const writeCrew = () => {
  // 크루 등록 페이지로 이동
};
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
