// stores/race.js
import { defineStore } from 'pinia'
import axios from 'axios'

export const useRaceStore = defineStore('race', {
  state: () => ({
    raceList: [],
    currentRace: null
  }),
  
  actions: {
    async getRaceList() {
      try {
        const response = await axios.get('http://localhost:8080/api/race')
        this.raceList = response.data
      } catch (error) {
        console.error('Error:', error)
      }
    },
    
    async getRaceByNo(raceNo) {
      try {
        const response = await axios.get(`http://localhost:8080/api/race/${raceNo}`)
        this.currentRace = response.data
      } catch (error) {
        console.error('Error:', error)
      }
    }
  }
})