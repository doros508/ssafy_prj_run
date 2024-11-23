<template>
  <div class="search-section mb-4">
    <div class="row">
      <!-- 도시 선택 -->
      <div class="col-md-6">
        <div class="form-group">
          <label class="text-white mb-2">도시</label>
          <select class="form-control" v-model="selectedCity">
            <option value="">전체</option>
            <option
              v-for="city in cities"
              :key="city.value"
              :value="city.value"
            >
              {{ city.label }}
            </option>
          </select>
        </div>
      </div>

      <!-- 요일 다중 선택 -->
      <div class="col-md-6">
        <div class="form-group">
          <label class="text-white mb-2">모임요일 (다중선택 가능)</label>
          <div class="day-select">
            <div v-for="day in days" :key="day.value" class="form-check">
              <input
                type="checkbox"
                class="form-check-input"
                :id="day.value"
                v-model="selectedDays"
                :value="day.value"
              />
              <label class="form-check-label text-white" :for="day.value">
                {{ day.label }}
              </label>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row mt-3">
      <div class="col">
        <button @click="search" class="btn btn-primary w-100">검색</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useCrewStore } from "@/stores/crew";

const emit = defineEmits(["search"]);
const store = useCrewStore();

// 도시 데이터
const cities = [
  { value: "1", label: "서울" },
  { value: "2", label: "부산" },
  { value: "3", label: "대구" },
  { value: "4", label: "인천" },
  { value: "5", label: "광주" },
  { value: "6", label: "대전" },
  { value: "7", label: "울산" },
  { value: "8", label: "세종" },
];

// 요일 데이터
const days = [
  { value: "all", label: "전체" },
  { value: "월", label: "월요일" },
  { value: "화", label: "화요일" },
  { value: "수", label: "수요일" },
  { value: "목", label: "목요일" },
  { value: "금", label: "금요일" },
  { value: "토", label: "토요일" },
  { value: "일", label: "일요일" },
];

const selectedCity = ref("");
const selectedDays = ref([]);

// 검색 실행
const search = async () => {
  try {
    const results = await store.searchCrews({
      cityNo: selectedCity.value,
      crewDays: selectedDays.value.filter((day) => day !== "all").join(","),
    });
    emit("search", results);
  } catch (error) {
    console.error("검색 실패:", error);
  }
};
</script>

<style scoped>
.day-select {
  background-color: rgba(255, 255, 255, 0.9);
  padding: 10px;
  border-radius: 4px;
  max-height: 200px;
  overflow-y: auto;
}

.form-check {
  margin-bottom: 8px;
}
</style>
