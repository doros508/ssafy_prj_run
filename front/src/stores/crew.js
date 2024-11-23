import { defineStore } from "pinia";
import axios from "axios";

export const useCrewStore = defineStore("crew", {
  state: () => ({
    crewList: [], // 크루 목록
  }),
  actions: {
    // 모든 크루를 가져오는 함수
    async getCrews(filters = {}) {
      try {
        const response = await axios.get("/api/crews", { params: filters });
        this.crewList = response.data; // 서버에서 가져온 데이터를 상태에 저장
      } catch (error) {
        console.error("Error fetching crews:", error);
      }
    },
  },
});
