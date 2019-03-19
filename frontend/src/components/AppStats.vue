<template>
  <div class="service">
    <h1>{{ msg }}</h1>
    <h2>App Stats</h2>

    <!--
    <button @click="callRestService()">CALL Spring Boot REST backend service</button>
    -->

    <table align="center">
      <tr>
        <th>App Name</th>
        <th>App Guid</th>
        <th>Up</th>
      </tr>
      <tr v-for="as in appstatuses">
        <td>{{ as.appName }}</td>
        <td>{{ as.appGuid }}</td>
        <td> <input type="checkbox" id="checkbox" v-model="as.up" @change="changeStatus(as.id, as.appName, as.appGuid, $event.target.checked)"></td>
      </tr>
    </table>

  </div>
</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'

  export default {
    name: 'service',

    data () {
      return {
        msg: 'HowTo call REST-Services:',
        response: [],
        appstatuses: [],
        errors: []
      }
    },
    methods: {
      // Fetches posts when the component is created.
      callRestService () {
        AXIOS.get(`/appstatuses`)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response = response.data
            console.log(response.data)
            this.appstatuses = response.data._embedded.appstatuses
            console.log(this.appstatuses)
          })
          .catch(e => {
            this.errors.push(e)
          })
      },
      changeStatus(id, name, guid, enabled) {
        AXIOS.put("/appstatuses/"+id, {"appName": name, "appGuid": guid, "up": enabled})
                .then(function (response) {
                  console.log(response);
                })
                .catch(function (error) {
                  console.log(error);
                });
      }
    },
    beforeMount() {
      this.callRestService()
    }
  }

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
