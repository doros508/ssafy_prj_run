<template>
  <div id="map" style="width: 100%; height: 400px"></div>
</template>

<script setup>
import { onMounted, watchEffect } from "vue";
import { useCrewStore } from "@/stores/useCrewStore";

const store = useCrewStore();
const filteredCrews = store.crewList; // 크루 목록

const loadMap = () => {
  const mapContainer = document.getElementById("map");
  const mapOption = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };
  const map = new kakao.maps.Map(mapContainer, mapOption);

  filteredCrews.forEach((crew) => {
    if (crew.crewLocation) {
      const geocoder = new kakao.maps.services.Geocoder();

      geocoder.addressSearch(crew.crewLocation, (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
          const markerPosition = new kakao.maps.LatLng(
            result[0].y,
            result[0].x
          );
          const marker = new kakao.maps.Marker({
            position: markerPosition,
          });
          marker.setMap(map);
        }
      });
    }
  });
};

onMounted(() => {
  loadMap(); // 맵 로드
});
</script>

<style scoped>
/* 스타일 추가 */
</style>
