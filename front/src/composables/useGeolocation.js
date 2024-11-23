import { ref } from "vue";

export function useGeolocation() {
  const currentPosition = ref(null);
  const error = ref(null);

  const getCurrentPosition = () => {
    if (!navigator.geolocation) {
      error.value = "위치 정보가 지원되지 않습니다.";
      return;
    }

    return new Promise((resolve, reject) => {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          currentPosition.value = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
          resolve(currentPosition.value);
        },
        (err) => {
          error.value = err.message;
          reject(err);
        }
      );
    });
  };

  return {
    currentPosition,
    error,
    getCurrentPosition,
  };
}
