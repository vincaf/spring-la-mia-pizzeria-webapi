<template>
  <div id="app">
    <div class="container">
      <h1>Pizze</h1>
      <ul>
        <li v-for="pizza in pizzas" :key="pizza.id">
          {{ pizza.name }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'App',
  components: {

  },

  data() {
    return {
      apiUrl: 'http://localhost:8080/api/1/',
      pizzas: [],
    }
  },

  methods: {
    getPizzas() {
      axios.get(this.apiUrl + "pizza/all")
        .then(response => {
          this.pizzas = response.data
        })
        .catch(error => {
          console.log(error)
        })
    }
  },

  mounted() {
    this.getPizzas()
  }
}
</script>

<style lang="scss">

</style>