import { defineStore } from "pinia";
import axios from "axios";
const REST_API_URL = `http://localhost:8080/maratalk/crew`; // 추가: API URL
export const useCrewStore = defineStore("crew", {
  state: () => ({
    crews: [],
    cities: [
      { value: 1, label: "서울" },
      { value: 2, label: "부산" },
      { value: 3, label: "대구" },
      { value: 4, label: "인천" },
      { value: 5, label: "광주" },
      { value: 6, label: "대전" },
      { value: 7, label: "울산" },
      { value: 8, label: "세종" },
    ],
    loading: false,
    error: null,
  }),

  actions: {
    // 크루 검색
    async searchCrews({ cityNo, crewDays }) {
      this.loading = true;
      try {
        const params = {};
        if (cityNo) params.cityNo = cityNo;
        if (crewDays) params.crewDays = crewDays;

        const response = await axios.get("/maratalk/crew", {
          params: { cityNo, crewDays },
        });

        return this.crews;
      } catch (error) {
        this.error = error.message;
        throw error;
      } finally {
        this.loading = false;
      }
    },

    // 특정 도시의 크루 조회
    async getCrewsByCity(cityNo) {
      try {
        const response = await axios.get(`${REST_API_URL}/city/${cityNo}`);
        this.crews = response.data; // 추가: 응답 데이터를 state에 저장
        return response.data;
      } catch (error) {
        console.error("도시별 Crew 조회 실패:", error);
        throw error;
      }
    },
  },

  getters: {
    getCityName: (state) => (cityNo) => {
      const city = state.cities.find((c) => c.value === cityNo);
      return city ? city.label : "알 수 없음";
    },
  },
});
