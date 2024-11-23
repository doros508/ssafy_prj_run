import { defineStore } from "pinia";
import axios from "axios";

export const useCrewStore = defineStore("crew", {
  state: () => ({
    crewList: [],
    cityList: [], // 시 데이터
    districtList: [], // 구 데이터
    searchCondition: {
      cityNo: null,
      districtNo: null,
      meetingDays: [],
    },
  }),
  actions: {
    // 크루 목록 가져오기
    async getCrewList() {
      try {
        const response = await axios.get("/maratalk/crew", {
          params: this.searchCondition,
        });
        this.crewList = response.data;
      } catch (error) {
        console.error("크루 목록을 불러오는 중 오류 발생:", error);
      }
    },

    // 시 목록 가져오기
    async getCityList() {
      try {
        const response = await axios.get("/maratalk/cities");
        this.cityList = response.data;
      } catch (error) {
        console.error("도시 목록을 불러오는 중 오류 발생:", error);
      }
    },

    // 구 목록 가져오기 (특정 시에 속한 구들)
    async getDistrictList(cityNo) {
      try {
        const response = await axios.get(
          `/maratalk/cities/${cityNo}/districts`
        );
        this.districtList = response.data;
      } catch (error) {
        console.error("구 목록을 불러오는 중 오류 발생:", error);
      }
    },
  },
});
