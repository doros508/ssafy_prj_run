<template>
  <div id="map" style="width: 100%; height: 400px"></div>
</template>

<script setup>
import { onMounted } from "vue";
import { useCrewStore } from "@/stores/useCrewStore";

const store = useCrewStore();
const filteredCrews = store.crewList;

const loadMap = () => {
  const mapContainer = document.getElementById("map");
  const mapOption = {
    center: new window.kakao.maps.LatLng(36.3504, 127.3845),
    level: 7,
  };

  const map = new window.kakao.maps.Map(mapContainer, mapOption);

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

      const infowindow = new window.kakao.maps.InfoWindow({
        content: `<div style="padding:5px;font-size:12px;">
          <strong>${crew.crew_name}</strong><br>
          ${crew.crew_location}
        </div>`,
      });

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
    script.src = `https://dapi.kakao.com/v2/maps/sdk.js?appkey=${
      import.meta.env.VITE_KAKAO_MAP_KEY
    }&libraries=services&autoload=false`;
    script.onload = () => window.kakao.maps.load(loadMap);
    document.head.appendChild(script);
  }
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
