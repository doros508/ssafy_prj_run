// src/utils/kakaoUtil.js

export function createKakaoMap(containerId, centerCoordinates, zoomLevel = 3) {
  return new Promise((resolve, reject) => {
    if (!window.kakao || !window.kakao.maps) {
      return reject(new Error("Kakao Maps API가 로드되지 않았습니다."));
    }

    // Kakao Maps API 로드 완료 후 실행
    window.kakao.maps.load(() => {
      try {
        const container = document.getElementById(containerId);
        const options = {
          center: new kakao.maps.LatLng(
            centerCoordinates.lat,
            centerCoordinates.lng
          ),
          level: zoomLevel,
        };
        const map = new kakao.maps.Map(container, options);
        resolve(map);
      } catch (error) {
        reject(error);
      }
    });
  });
}

export function addMarker(map, coordinates, content) {
  if (!window.kakao || !window.kakao.maps) {
    throw new Error("Kakao Maps API가 로드되지 않았습니다.");
  }

  const position = new kakao.maps.LatLng(coordinates.lat, coordinates.lng);
  const marker = new kakao.maps.Marker({
    position,
  });
  marker.setMap(map);

  if (content) {
    const infoWindow = new kakao.maps.InfoWindow({
      content,
    });
    infoWindow.open(map, marker);
  }

  return marker;
}
