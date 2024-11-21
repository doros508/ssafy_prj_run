import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_API_URL = `http://localhost:8080/maratalk/race`

export const useRaceStore = defineStore("race", () => {
  const raceList = ref([]);

  // 대회 전체 및 검색 조회
  const getRaceList = function () {
    axios.get(REST_API_URL, {

    })
    .then((response)=>{
      console.log(response.data);
      raceList.value = response.data;
    })
    .catch((error)=>{
      console.log("뭐여 왜 안되는겨", error)
    })
  };

  return { raceList, getRaceList };
})