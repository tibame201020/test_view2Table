import { defineStore } from 'pinia'
import axios from 'axios'
import { useViewStore } from '../stores/viewStore'

const baseUrl = 'http://localhost:8080/api'

export const useAccountStore = defineStore('accountStore', {
  state: () => ({
    data: []
  }),
  actions: {
    getData() {
      const viewStore = useViewStore();
      axios.post(baseUrl + '/transFromView', viewStore.data)
        .then(res => this.data = res.data)
    }
  },
})