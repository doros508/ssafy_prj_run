import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

// const REST_API_URL = `http://localhost:8080/maratalk/race` //로컬
// const REST_API_URL = `http://192.168.210.63:8080/maratalk/race` //종수
const REST_API_URL = `http://192.168.210.53:8080/maratalk/race` // 건우

export const useRaceStore = defineStore("race", () => {
  const raceList = ref([]);

  const getRaceList = async () => {
    try {
      const response = await axios.get(REST_API_URL);
      raceList.value = response.data;
    } catch (error) {
      console.error("Error fetching races:", error);
    }
  };

  const getRacesByFilter = async (filters) => {
    try {
      const response = await axios.get(`${REST_API_URL}/search`, { 
        params: 
        {
          date: filters.date || '',
          cityNo: filters.cityNo || '',
          distance: filters.distance || ''
        }
      });
      raceList.value = response.data;
    } catch (error) {
      console.error("Error searching races:", error);
    }
  };

  return { 
    raceList, 
    getRaceList,
    getRacesByFilter 
  };
});