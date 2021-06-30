<template>
  <div>
    <div class="w-300 md:w-5/5 lg:w-4/4 xl:w-2/2 mx-auto bg-white shadow px-6">
      <div class="text-lg lg:text-xl">
        <div class="">
          <input @keydown="doSearch" type="text" autocomplete="off" v-model="query" :placeholder="'Search ' + what"
                 class="inline-block mb-0 px-2 h-14 w-12/12 md:w-300 font-light focus:outline-none">
        </div>
        <ul class="options">
          <li v-for="item in suggestions" :key="item.id">
            <UserSuggestion v-bind:name="item.name" v-bind:id="item.id" v-bind:surname="item.surname" />
          </li>
          <li v-if="suggestions.length===0">Item not found</li>
        </ul>
      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios";
import UserSuggestion from "~/components/admin/UserSuggestion";
export default {
  name: "MiniSearchBar",
  components: {UserSuggestion},
  props: ['what', 'how'],
  data() {
    return {
      suggestions : [],
      query: ""
    }
  },
  methods:{
    doSearch() {
        axios.get("/api/admin/user_search/?query=" + this.query)
        .then(response => {
          console.log("AAAAAAAAAAAAA");
          console.log(response.data);
          this.suggestions = response.data;
        });
    }
  },
  computed: {
    
  }
}
</script>

<style scoped>

</style>
