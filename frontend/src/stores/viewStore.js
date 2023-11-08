import { defineStore } from 'pinia'
import axios from 'axios'

const baseUrl = 'http://localhost:8080/api'

export const useViewStore = defineStore('viewStore', {
  state: () => ({
    data: []
  }),
  actions: {
    getData() {
      axios.get(baseUrl + '/getAllViews')
        .then(res => this.data = res.data)
    }
  },
})