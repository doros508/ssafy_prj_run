<template>
  <div class="container-fluid">
    <!-- 검색 조건 섹션 -->
    <div class="search-section bg-white rounded p-4 mb-4">
      <div class="row">
        <!-- 도시 드롭다운 -->
        <div class="col-md-6 mb-3">
          <label class="form-label fw-bold">도시</label>
          <select
            v-model="selectedCity"
            class="form-select"
            @change="handleCityChange"
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

        <!-- 요일 드롭다운 -->
        <div class="col-md-6 mb-3">
          <label class="form-label fw-bold">모임요일 (다중선택 가능)</label>
          <select v-model="selectedDays" class="form-select" multiple>
            <option value="">전체</option>
            <option v-for="day in days" :key="day.value" :value="day.value">
              {{ day.label }}
            </option>
          </select>
        </div>
      </div>

      <div class="row mt-3">
        <div class="col">
          <button @click="searchCrews" class="btn btn-primary w-100">
            크루 검색
          </button>
        </div>
      </div>
    </div>

    <!-- 카카오맵 섹션 -->
    <div class="map-section mb-4">
      <KakaoMap
        :center="mapCenter"
        :level="3"
        :max-level="8"
        @load="handleMapLoad"
        style="width: 100%; height: 400px"
        class="rounded"
      >
        <KakaoMapMarker
          v-for="crew in crews"
          :key="crew.crewNo"
          :position="{ lat: crew.crewLat, lng: crew.crewLng }"
          @click="handleMarkerClick(crew)"
        >
          <KakaoMapInfoWindow
            :open="selectedCrew === crew.crewNo"
            :close-on-click-map="true"
          >
            <div class="p-2">
              <h6>{{ crew.crewName }}</h6>
              <p class="mb-1">{{ crew.crewLocation }}</p>
              <p class="mb-0">모임요일: {{ crew.crewDay }}</p>
            </div>
          </KakaoMapInfoWindow>
        </KakaoMapMarker>
      </KakaoMap>
    </div>

    <!-- 크루 리스트 섹션 -->
    <div class="crew-list-section">
      <div class="row">
        <div v-for="crew in crews" :key="crew.crewNo" class="col-md-6 mb-3">
          <div class="card h-100" @click="goToCrewUrl(crew.crewUrl)">
            <div class="card-body">
              <h5 class="card-title">{{ crew.crewName }}</h5>
              <div class="card-text">
                <p>
                  <i class="bi bi-geo-alt"></i> <strong>위치:</strong>
                  {{ crew.crewLocation }}
                </p>
                <p>
                  <i class="bi bi-people"></i> <strong>인원:</strong>
                  {{ crew.crewSize }}명
                </p>
                <p>
                  <i class="bi bi-calendar"></i> <strong>모임요일:</strong>
                  {{ crew.crewDay }}
                </p>
                <p class="crew-content">{{ crew.crewContent }}</p>
              </div>
            </div>
            <div class="card-footer bg-transparent">
              <button class="btn btn-outline-primary btn-sm w-100">
                크루 가입하기
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { KakaoMap, KakaoMapMarker, KakaoMapInfoWindow } from "vue3-kakao-maps";
import axios from "axios";

// 상태 관리
const crews = ref([]);
const cities = ref([]);
const selectedCity = ref("");
const selectedDays = ref([]);
const selectedCrew = ref(null);
const mapCenter = ref({ lat: 36.5, lng: 127.5 }); // 대한민국 중심 좌표

// 요일 데이터
const days = [
  { value: "월", label: "월요일" },
  { value: "화", label: "화요일" },
  { value: "수", label: "수요일" },
  { value: "목", label: "목요일" },
  { value: "금", label: "금요일" },
  { value: "토", label: "토요일" },
  { value: "일", label: "일요일" },
];

// 도시 정보 로드
const loadCities = async () => {
  try {
    const response = await axios.get("/api/cities");
    cities.value = response.data;
  } catch (error) {
    console.error("도시 정보 로딩 실패:", error);
  }
};

// 크루 검색
const searchCrews = async () => {
  try {
    const params = {};
    if (selectedCity.value) {
      params.cityNo = selectedCity.value;
    }
    if (selectedDays.value.length > 0 && !selectedDays.value.includes("")) {
      params.crewDays = selectedDays.value.join(",");
    }
    const response = await axios.get("/api/crews", { params });
    crews.value = response.data;
    updateMapCenter();
  } catch (error) {
    console.error("크루 검색 실패:", error);
  }
};

// 지도 중심 좌표 업데이트
const updateMapCenter = () => {
  if (crews.value.length > 0) {
    const firstCrew = crews.value[0];
    mapCenter.value = {
      lat: firstCrew.crewLat,
      lng: firstCrew.crewLng,
    };
  }
};

// 마커 클릭 핸들러
const handleMarkerClick = (crew) => {
  selectedCrew.value = crew.crewNo;
};

// 크루 URL로 이동
const goToCrewUrl = (url) => {
  if (url) window.open(url, "_blank");
};

// 도시 변경 핸들러
const handleCityChange = () => {
  if (selectedCity.value) {
    const selectedCityObj = cities.value.find(
      (c) => c.cityNo === selectedCity.value
    );
    if (selectedCityObj) {
      searchCrews();
    }
  }
};

onMounted(() => {
  loadCities();
  searchCrews();
});
</script>

<style scoped>
.search-section {
  background-color: #f8f9fa;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.form-select[multiple] {
  height: 200px;
}

.card {
  cursor: pointer;
  transition: transform 0.2s, box-shadow 0.2s;
  border: none;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.crew-content {
  max-height: 100px;
  overflow-y: auto;
  font-size: 0.9rem;
  color: #666;
}
</style>
