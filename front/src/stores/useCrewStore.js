// stores/useCrewStore.js
import { defineStore } from "pinia";
import axios from "axios";

export const useCrewStore = defineStore("crew", {
  state: () => ({
    crewList: [],
    cityList: [],
    districtList: [],
    selectedCity: "",
    selectedDistrict: "",
    selectedDays: [],
  }),

  actions: {
    // 크루 목록을 불러오는 메서드 (필터링 적용)
    async getCrewList() {
      try {
        const response = await axios.get("/api/crew", {
          // 여기에 실제 크루 목록을 불러오는 API 엔드포인트 추가
          params: {
            city: this.selectedCity,
            district: this.selectedDistrict,
            days: this.selectedDays.join(","),
          },
        });
        this.crewList = response.data;
      } catch (error) {
        console.error("크루 목록을 불러오는 중 오류 발생:", error);
      }
    },

    // 시 목록을 불러오는 메서드 (CityController의 API 호출)
    async getCityList() {
      try {
        const response = await axios.get("/api/city");
        this.cityList = response.data;
      } catch (error) {
        console.error("시 목록을 불러오는 중 오류 발생:", error);
      }
    },

    // 구 목록을 불러오는 메서드 (DistrictController의 API 호출)
    async getDistrictList(cityNo) {
      try {
        const response = await axios.get(`/api/district/city/${cityNo}`);
        this.districtList = response.data;
      } catch (error) {
        console.error("구 목록을 불러오는 중 오류 발생:", error);
      }
    },

    // 필터 적용 후 크루 리스트를 가져오는 메서드
    async applyFilters() {
      await this.getCrewList(); // 필터된 크루 목록을 가져오기 위해 getCrewList 호출
    },
  },
});
