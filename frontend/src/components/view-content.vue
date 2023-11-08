<script setup>
import { ref } from 'vue'
import { useViewStore } from '../stores/viewStore'
import { useAccountStore } from '../stores/accountStore'

const viewStore = useViewStore();
viewStore.getData();
const accountStore = useAccountStore();

const transViewToAccount = () => {
  accountStore.getData(viewStore.data);
}

const exec = () => {

}


</script>

<template>
  <div class="container">
    <div class="row">
      <div class="col-2">
        <button type="button" class="btn btn-warning" @click="transViewToAccount" :disabled="accountStore.data.length !== 0">Trans</button>
      </div>
      <div class="col-2">
        <button type="button" class="btn btn-danger" @click="exec">exec</button>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <hr>
      </div>
    </div>
    <div class="row">
        <div class="col" v-for="viewData in viewStore.data">
            {{ JSON.stringify(viewData) }}
        </div>
    </div>
    <div class="row">
      <div class="col">
        <hr>
      </div>
    </div>
    <div class="row">
      <div class="col-6">
        <div class="row" v-for="accountData in accountStore.data">
          <div class="col">
            {{ JSON.stringify(accountData) }}
          </div>
        </div>

      </div>
      <div class="col-6">

      </div>
    </div>
  </div>
</template>

<style scoped></style>
