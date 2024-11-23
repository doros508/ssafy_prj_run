import axios from "axios";
const api = axios.create({
  baseURL: "http://localhost:8080/maratalk", // /maratalk 추가
  headers: {
    "Content-Type": "application/json",
  },
});

export const crewApi = {
  // API 경로에서 /api 제거
  getCrews(params) {
    return api.get("/crew", { params }); // /api/crews를 /crew로 변경
  },

  getCities() {
    return api.get("/cities"); // /api/cities를 /cities로 변경
  },

  searchByLocation(lat, lng, radius) {
    return api.get("/crew/location", {
      // /api/crews/location을 /crew/location으로 변경
      params: { lat, lng, radius },
    });
  },
};
