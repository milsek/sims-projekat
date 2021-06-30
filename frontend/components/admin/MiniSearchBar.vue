<template>
  <div>
    <div class="mx-auto bg-white shadow px-6">
      <div class="text-lg lg:text-xl">
        <div class="">
          <input @keyup="doSearch" type="text" autocomplete="off" v-model="query" :placeholder="'Search ' + what"
                 class="inline-block mb-0 px-2 h-14 w-80 font-light focus:outline-none">
        </div>
        <ul class="options">
          <li v-show="how == 'search_user'" v-for="item in suggestions" :key="item.id">
            <UserSuggestion @userIsSelected="selected($event)" v-bind:name="item.name" v-bind:id="item.id" v-bind:surname="item.surname" />
          </li>
          <li v-show="how == 'search_book'" v-for="item in suggestions" :key="'a' + item.id">
            <BookSuggestion @book-is-selected="selected($event)" v-bind:title="item.title" v-bind:id="item.id" />
          </li>
          <li v-if="suggestions.length===0 && query.length != 0">Item not found</li>
        </ul>
      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios";
import UserSuggestion from "~/components/admin/UserSuggestion";
import BookSuggestion from "~/components/admin/BookSuggestion";
export default {
  name: "MiniSearchBar",
  components: {UserSuggestion, BookSuggestion},
  props: ['what', 'how'],
  data() {
    return {
      suggestions : [],
      query: ""
    }
  },
  methods:{
    doSearch() {
        console.log("/api/admin/" + this.how + "/?query=" + this.query);
        if(this.query.length == 0){ 
          this.suggestions = [];
          return;
        }
        axios.get("/api/admin/" + this.how + "/?query=" + this.query)
        .then(response => {
          console.log("AAAAAAAAAAAAA");
          console.log(response.data);
          this.suggestions = response.data;
          for(let item in this.suggestions)
            console.log(item.id);
        });
    },
    selected(data) {
            console.log("MSB");
            this.suggestions = [];
            this.$emit('userIsSelected', data);
    }
  },
  computed: {
    
  }
}
</script>

<style scoped>

</style>
