import { onMounted, onUnmounted } from "vue";

export function useKakaoMap(container, options = {}) {
  let map = null;
  let markers = [];

  // 지도 초기화
  const initMap = () => {
    const defaultOptions = {
      center: new kakao.maps.LatLng(36.5, 127.5),
      level: 13,
      ...options,
    };
    map = new kakao.maps.Map(container, defaultOptions);
  };

  // 마커 생성
  const addMarker = (position, content) => {
    const marker = new kakao.maps.Marker({
      position: new kakao.maps.LatLng(position.lat, position.lng),
      map: map,
    });

    if (content) {
      const infoWindow = new kakao.maps.InfoWindow({
        content: content,
      });

      kakao.maps.event.addListener(marker, "click", () => {
        infoWindow.open(map, marker);
      });
    }

    markers.push(marker);
    return marker;
  };

  // 마커 전체 제거
  const clearMarkers = () => {
    markers.forEach((marker) => marker.setMap(null));
    markers = [];
  };

  // 지도 범위 재설정
  const fitBounds = (positions) => {
    if (positions.length === 0) return;

    const bounds = new kakao.maps.LatLngBounds();
    positions.forEach((pos) => {
      bounds.extend(new kakao.maps.LatLng(pos.lat, pos.lng));
    });
    map.setBounds(bounds);
  };

  onMounted(() => {
    initMap();
  });

  onUnmounted(() => {
    clearMarkers();
  });

  return {
    map,
    addMarker,
    clearMarkers,
    fitBounds,
  };
}
