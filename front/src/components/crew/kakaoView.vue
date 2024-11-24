<template>
  <div id="map" style="width: 100%; height: 400px"></div>
</template>

<script setup>
import { onMounted, watch } from "vue";

const props = defineProps({
  crews: {
    type: Array,
    default: () => [],
  },
});

// 도시별 중심 좌표 설정
const cityCoordinates = {
  1: { lat: 37.5665, lng: 126.978 }, // 서울
  2: { lat: 36.3504, lng: 127.3845 }, // 대전
  3: { lat: 35.1796, lng: 129.0756 }, // 부산
};

const loadMap = () => {
  if (!window.kakao || !window.kakao.maps) {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${
      import.meta.env.VITE_KAKAO_MAP_KEY
    }&libraries=services&autoload=false`;
    script.onload = () => {
      window.kakao.maps.load(() => {
        initializeMap();
      });
    };
    document.head.appendChild(script);
  } else {
    initializeMap();
  }
};

const initializeMap = () => {
  const mapContainer = document.getElementById("map");

  // 선택된 도시의 중심 좌표 가져오기
  const selectedCity = props.crews[0]?.cityNo || 2; // 기본값은 대전
  const centerCoord = cityCoordinates[selectedCity];

  const mapOption = {
    center: new window.kakao.maps.LatLng(centerCoord.lat, centerCoord.lng),
    level: 7,
  };

  const map = new window.kakao.maps.Map(mapContainer, mapOption);

  // 마커 생성
  props.crews.forEach((crew) => {
    if (crew.crewLat && crew.crewLng) {
      const markerPosition = new window.kakao.maps.LatLng(
        crew.crewLat,
        crew.crewLng
      );

      const marker = new window.kakao.maps.Marker({
        position: markerPosition,
        map: map,
      });

      const infowindow = new window.kakao.maps.InfoWindow({
        content: `<div style="padding:5px;font-size:12px;">
          <strong>${crew.crewName}</strong><br>
          ${crew.crewLocation}
        </div>`,
      });

      window.kakao.maps.event.addListener(marker, "click", () => {
        infowindow.open(map, marker);
      });
    }
  });
};

watch(
  () => props.crews,
  () => {
    if (window.kakao && window.kakao.maps) {
      initializeMap();
    }
  },
  { deep: true }
);

onMounted(() => {
  loadMap();
});
</script>

<style scoped>
#map {
  width: 100%;
  height: 400px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>
