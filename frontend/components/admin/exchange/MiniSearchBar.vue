<template>
  <div class="w-full">
    <div class="mx-auto bg-white shadow px-6">
      <div class="text-lg lg:text-xl">
        <div>
          <input @keyup="doSearch" type="text" autocomplete="off" v-model="query" :placeholder="'Search ' + what"
                 class="inline-block mb-0 px-2 py-2 font-light focus:outline-none">
        </div>
        <ul v-if="suggestions.length" class="options pb-2">
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
import UserSuggestion from "~/components/admin/exchange/UserSuggestion";
import BookSuggestion from "~/components/admin/exchange/BookSuggestion";
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
            console.log(data);
            this.suggestions = [];
            console.log(this.how);
            if(this.how == "search_book") {
              console.log("Hello");
              this.$emit('book-is-selected', data);
            } else {
              this.$emit('userIsSelected', data);
            }
    }
  },
  computed: {
    
  }
}
</script>

<style scoped>

</style>
