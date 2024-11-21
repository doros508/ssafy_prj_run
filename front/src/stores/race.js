// stores/race.js
import { defineStore } from 'pinia'
import axios from 'axios'

export const useRaceStore = defineStore('race', {
  state: () => ({
    raceList: [],
    race: null,
    error: null
  }),
  
  actions: {
    async getRaceList() {
      try {
        const response = await axios.get('http://localhost:8080/api/race')
        this.raceList = response.data
        this.error = null
      } catch (error) {
        console.error('Error fetching races:', error)
        this.error = error.message
        this.raceList = []
      }
    },

    async getRacesByCity(cityNo) {
      try {
        const response = await axios.get(`http://localhost:8080/api/race/city/${cityNo}`)
        if (response.data) {
          this.raceList = response.data
          this.error = null
        }
      } catch (error) {
        console.error('Error fetching races by city:', error)
        this.error = error.message
        this.raceList = []
      }
    }
  }
})