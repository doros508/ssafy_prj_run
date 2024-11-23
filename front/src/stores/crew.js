
// src/stores/crew.js
import { defineStore } from "pinia";

export const useCrewStore = defineStore("crew", {
  state: () => ({
    crewList: [],
  }),
  actions: {
    async getCrewList() {
      // 예시: API 호출 후 크루 리스트를 상태에 저장
      const response = await fetch("/api/crew");
      this.crewList = await response.json();

    },
  },
});
