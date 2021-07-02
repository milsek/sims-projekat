<template>
  <div class="w-full">
    <div class="mx-auto bg-white px-6 border-gray-400 border border-solid">
      <div class="text-lg lg:text-xl">
        <div>
          <input @keyup="doSearch" type="text" autocomplete="off" v-model="query" :placeholder="'Search ' + what"
                 class="inline-block mb-0 px-2 py-2 font-light focus:outline-none w-full">
        </div>
        <ul v-if="suggestions.length" class="options pb-2">
          <li v-show="how == 'autocomplete-user-id'" v-for="item in suggestions" :key="item.id">
            <UserSuggestion @user-is-selected="selected($event)" v-bind:name="item.name" v-bind:id="item.id" v-bind:surname="item.surname" />
          </li>
          <li v-show="how == 'autocomplete-book-id'" v-for="item in suggestions" :key="'a' + item.id">
            <BookSuggestion @book-is-selected="selected($event)" v-bind:title="item.editionTitleTitle" v-bind:id="item.id" />
          </li>
          <li v-if="suggestions.length===0 && query.length != 0 && !isSelected">Item not found</li>
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
      query: "",
      isSelected: false
    }
  },
  methods:{
    doSearch() {
      console.log("/api/admin/" + this.how + "/?id=" + this.query);
      if(this.query.length == 0){ 
        this.suggestions = [];
        return;
      }
      axios.get("/api/" + this.how + "/?id=" + this.query)
      .then(response => {
        this.suggestions = response.data;
      });
    },
    selected(data) {
      this.suggestions = [];
      this.isSelected = true;
      if(this.how == "autocomplete-book-id") {
        this.$emit('book-is-selected', data);
      } else {
        this.$emit('user-is-selected', data);
      }
    }
  },
  computed: {
    
  }
}
</script>

<style scoped>

</style>
