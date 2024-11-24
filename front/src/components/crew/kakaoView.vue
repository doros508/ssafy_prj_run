<template>
  <div id="map" style="width: 100%; height: 400px"></div>
</template>

<script setup>
import { onMounted, watchEffect } from "vue";
import { useCrewStore } from "@/stores/useCrewStore";

const store = useCrewStore();
const filteredCrews = store.crewList;

const loadMap = () => {
  const mapContainer = document.getElementById("map");
  const mapOption = {
    center: new window.kakao.maps.LatLng(36.3504, 127.3845), // 대전 중심 좌표
    level: 7,
  };

  const map = new window.kakao.maps.Map(mapContainer, mapOption);

  // 크루 데이터로 마커 생성
  filteredCrews.forEach((crew) => {
    if (crew.crew_lat && crew.crew_lng) {
      const markerPosition = new window.kakao.maps.LatLng(
        crew.crew_lat,
        crew.crew_lng
      );

      const marker = new window.kakao.maps.Marker({
        position: markerPosition,
        map: map,
      });

      // 인포윈도우 생성
      const infowindow = new window.kakao.maps.InfoWindow({
        content: `<div style="padding:5px;font-size:12px;">${crew.crew_name}</div>`,
      });

      // 마커 클릭 이벤트
      window.kakao.maps.event.addListener(marker, "click", () => {
        infowindow.open(map, marker);
      });
    }
  });
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    loadMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${
      import.meta.env.VITE_KAKAO_MAP_KEY
    }&autoload=false`;
    script.onload = () => window.kakao.maps.load(loadMap);
    document.head.appendChild(script);
  }
});
</script>
