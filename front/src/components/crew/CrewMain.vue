<template>
  <div class="container">
    <h1 class="logo">크루 검색</h1>

    <div class="content">
      <!-- 검색 조건 -->
      <div class="row mb-4">
        <div class="col-md-4">
          <div class="form-group">
            <label for="cityFilter" class="text-white mb-2">도시</label>
            <select
              class="form-control"
              id="cityFilter"
              v-model="filters.cityNo"
            >
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
            <label for="districtFilter" class="text-white mb-2">구</label>
            <select
              class="form-control"
              id="districtFilter"
              v-model="filters.districtNo"
            >
              <option value="">전체</option>
              <option value="1">강남구</option>
              <option value="2">홍대구</option>
              <option value="3">종로구</option>
            </select>
          </div>
        </div>
        <div class="col-md-4">
          <div class="form-group">
            <label for="dayFilter" class="text-white mb-2">모임 요일</label>
            <select
              class="form-control"
              id="dayFilter"
              v-model="filters.meetingDay"
            >
              <option value="">전체</option>
              <option value="월">월요일</option>
              <option value="화">화요일</option>
              <option value="수">수요일</option>
              <option value="목">목요일</option>
              <option value="금">금요일</option>
              <option value="토">토요일</option>
              <option value="일">일요일</option>
            </select>
          </div>
        </div>
      </div>

      <!-- 검색 버튼 -->
      <div class="row mb-4">
        <div class="col-12">
          <button class="btn btn-primary w-100" @click="applyFilters">
            검색
          </button>
        </div>
      </div>

      <!-- 카카오 지도 -->
      <div
        class="map-container"
        ref="kakaoMap"
        style="width: 100%; height: 400px"
      ></div>

      <!-- 검색 결과 -->
      <div v-if="filteredCrews.length">
        <h3 class="mt-4">검색된 크루 목록</h3>
        <ul class="list-group">
          <li
            class="list-group-item"
            v-for="crew in filteredCrews"
            :key="crew.id"
          >
            <div class="row">
              <div class="col-md-8">
                <strong>{{ crew.name }}</strong>
                <p>
                  {{ crew.city }} {{ crew.district }} - {{ crew.meetingDay }}
                </p>
              </div>
              <div class="col-md-4">
                <p>위도: {{ crew.latitude }} | 경도: {{ crew.longitude }}</p>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div v-else>
        <p>조건에 맞는 크루가 없습니다.</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useCrewStore } from "@/stores/crew"; // 크루 목록을 가져오는 store

const store = useCrewStore();

// 검색 필터
const filters = ref({
  cityNo: "",
  districtNo: "",
  meetingDay: "",
});

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

  if (filters.value.meetingDay) {
    results = results.filter(
      (crew) => crew.meetingDay === filters.value.meetingDay
    );
  }

  return results;
});

// 필터 적용 함수
const applyFilters = async () => {
  try {
    await store.getCrews(filters.value); // 필터 조건을 적용하여 크루 목록을 가져오는 API 호출
    initKakaoMap(); // 필터링 후 카카오 맵 초기화
  } catch (error) {
    console.error("Error applying filters:", error);
  }
};

// 페이지가 처음 로드될 때 크루 목록을 가져옴
onMounted(() => {
  store.getCrews(); // 초기 크루 목록 가져오기
  initKakaoMap(); // 페이지 로드 시 카카오 맵 초기화
});

// 카카오 맵 초기화
const initKakaoMap = () => {
  // 카카오 맵 API 로드
  if (!window.kakao) {
    alert("카카오 맵 API가 로드되지 않았습니다.");
    return;
  }

  const mapContainer = document.getElementById("kakaoMap"); // 지도를 표시할 div
  const mapOption = {
    center: new kakao.maps.LatLng(37.5665, 126.978), // 서울 중심
    level: 3, // 확대 레벨
  };

  const map = new kakao.maps.Map(mapContainer, mapOption); // 지도 생성

  // 필터링된 크루들에 대한 마커 표시
  filteredCrews.value.forEach((crew) => {
    const markerPosition = new kakao.maps.LatLng(crew.latitude, crew.longitude); // 크루의 좌표
    const marker = new kakao.maps.Marker({
      position: markerPosition,
    });

    marker.setMap(map); // 마커 지도에 표시
  });
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
}

.form-control {
  background-color: rgba(255, 255, 255, 0.9);
}

.form-control:focus {
  background-color: #fff;
}

.map-container {
  margin-top: 20px;
  width: 100%;
  height: 400px;
  border-radius: 10px;
}

.table {
  text-align: center;
  border-radius: 15px;
  margin-bottom: 20px;
}

.btn-primary {
  background-color: #ff5722;
  border-color: #ff5722;
}

.btn-primary:hover {
  background-color: #e64a19;
  border-color: #e64a19;
}
</style>
