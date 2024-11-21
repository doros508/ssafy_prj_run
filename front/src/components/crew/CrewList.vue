<template>
  <div class="container-fluid">
    <!-- 탭 네비게이션 -->
    <ul class="nav nav-tabs mb-3">
      <li class="nav-item">
        <a
          class="nav-link"
          :class="{ active: activeTab === 'all' }"
          @click="setActiveTab('all')"
          >전체</a
        >
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          :class="{ active: activeTab === 'city' }"
          @click="setActiveTab('city')"
          >도시별</a
        >
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          :class="{ active: activeTab === 'day' }"
          @click="setActiveTab('day')"
          >요일별</a
        >
      </li>
    </ul>

    <!-- 검색 조건 섹션 -->
    <div class="search-section bg-white rounded p-4 mb-4">
      <div class="row">
        <!-- 도시 선택 (도시별 탭에서만 표시) -->
        <div v-if="activeTab === 'city'" class="col-md-6">
          <label class="form-label">도시</label>
          <select v-model="selectedCity" class="form-select">
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

        <!-- 요일 선택 (요일별 탭에서만 표시) -->
        <div v-if="activeTab === 'day'" class="col-md-12">
          <label class="form-label">모임요일</label>
          <div class="btn-group" role="group">
            <input
              type="checkbox"
              class="btn-check"
              v-model="selectedDays"
              v-for="day in days"
              :key="day.value"
              :id="day.value"
              :value="day.value"
            />
            <label
              class="btn btn-outline-primary"
              v-for="day in days"
              :key="day.value"
              :for="day.value"
              >{{ day.label }}</label
            >
          </div>
        </div>
      </div>

      <div class="row mt-3">
        <div class="col">
          <button @click="searchCrews" class="btn btn-primary">검색</button>
        </div>
      </div>
    </div>

    <!-- 나머지 컴포넌트 내용 -->
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  setup() {
    const activeTab = ref("all");
    const selectedCity = ref("");
    const selectedDays = ref([]);
    const days = [
      { value: "월", label: "월" },
      { value: "화", label: "화" },
      { value: "수", label: "수" },
      { value: "목", label: "목" },
      { value: "금", label: "금" },
      { value: "토", label: "토" },
      { value: "일", label: "일" },
    ];

    const searchCrews = async () => {
      try {
        const params = {};

        if (activeTab.value === "city" && selectedCity.value) {
          params.cityNo = selectedCity.value;
        }

        if (activeTab.value === "day" && selectedDays.value.length > 0) {
          params.crewDays = selectedDays.value.join(",");
        }

        const response = await axios.get("/api/crews", { params });
        crews.value = response.data;
      } catch (error) {
        console.error("크루 검색 실패:", error);
      }
    };

    return {
      activeTab,
      selectedCity,
      selectedDays,
      days,
      searchCrews,
      setActiveTab: (tab) => (activeTab.value = tab),
    };
  },
};
</script>
