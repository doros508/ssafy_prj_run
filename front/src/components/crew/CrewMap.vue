<template>
  <div class="map-section mb-4">
    <!-- 카카오맵이 표시될 영역 -->
    <div id="map" class="map-container"></div>
  </div>
</template>

<script setup>
import { onMounted, watch } from "vue";

const kakaoApiKey = import.meta.env.VITE_KAKAO_MAP_KEY;

const props = defineProps({
  crews: {
    type: Array,
    required: true,
    default: () => [],
  },
});

let map = null;
let markers = [];
let infoWindows = [];

// 카카오맵 초기화
const initializeMap = () => {
  if (window.kakao && window.kakao.maps) {
    const container = document.getElementById("map");
    const options = {
      center: new kakao.maps.LatLng(36.5, 127.5), // 대한민국 중심 좌표
      level: 13, // 지도 확대 레벨
    };

    map = new kakao.maps.Map(container, options);

    // 지도 컨트롤러 추가
    const zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
  }
};

// 마커 업데이트
const updateMarkers = () => {
  // 기존 마커와 인포윈도우 제거
  markers.forEach((marker) => marker.setMap(null));
  infoWindows.forEach((info) => info.close());
  markers = [];
  infoWindows = [];

  // 새로운 마커 생성
  props.crews.forEach((crew) => {
    const geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표 검색
    geocoder.addressSearch(crew.crewLocation, (result, status) => {
      if (status === kakao.maps.services.Status.OK) {
        const position = new kakao.maps.LatLng(result[0].y, result[0].x);

        const marker = new kakao.maps.Marker({
          position: position,
          map: map,
        });

        const infoWindow = new kakao.maps.InfoWindow({
          content: `
            <div class="p-2">
              <h6>${crew.crewName}</h6>
              <p>모임요일: ${crew.crewDay}</p>
              <p>인원: ${crew.crewSize}명</p>
            </div>
          `,
        });

        kakao.maps.event.addListener(marker, "click", () => {
          infoWindows.forEach((info) => info.close());
          infoWindow.open(map, marker);
        });

        markers.push(marker);
        infoWindows.push(infoWindow);
      }
    });
  });
};

// crews prop 변경 감지
watch(() => props.crews, updateMarkers, { deep: true });

onMounted(() => {
  initializeMap();
});
</script>

<style scoped>
.map-section {
  width: 100%;
  margin-bottom: 20px;
}

.map-container {
  width: 100%;
  height: 400px;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>
