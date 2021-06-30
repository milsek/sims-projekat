<template>
  <div class="w-11/12 mx-auto rounded-lg bg-white shadow-lg md:block">
  
    <div class="w-7/12 mt-4  text-lg lg:text-xl gap-6 md:flex justify-around mx-auto">
      <div class="w-96 my-aut ">
        <MiniSearchBar v-on:userIsSelected="userIsSelected($event)" v-on:book-is-selected="bookIsSelected($event)" what="by book id" how="search_book"/>
      </div>
      
    </div>
    
    <div class=" lg:text-xl  md:flex mt-4 mx-2 flex-wrap">
      <div class="my-aut md:flex flex-wrap">
        <SelectedUser v-show="user_selected" v-bind:id=user_id v-bind:name=user_name v-bind:surname=user_surname />
      </div>
      <div class="my-aut md:flex flex-wrap justify-around mx-auto">
        <SelectedBook v-if="book_selected" v-bind:data=selected_book />
      </div>
    </div>

  </div>
  
</template>

<script>
import axios from "axios";
import MiniSearchBar from "~/components/admin/MiniSearchBar";
import SelectedUser from "~/components/admin/SelectedUser";
import SelectedBook from "~/components/admin/SelectedBook";
export default {
  components: {MiniSearchBar, SelectedUser, SelectedBook},
  data() {
    return {
      user_selected : false,
      book_selected: false,
      user_id : -1,
      user_name : "",
      user_surname: "",
      data_id: "",
      selected_book: ""
    }
  },
  methods: {
    userIsSelected(data) {
      this.user_selected = true;
      this.user_id = data.id;
      this.user_name = data.name;
      this.user_surname = data.surname;
    },
    bookIsSelected(data) {
      this.data_id = data.id;
      axios.get("/api/admin/get_book/?query=" + this.data_id)
        .then(response => {
          console.log(response);
          console.log(response.data);
          this.selected_book = response.data;
          this.book_selected = true;
        });
    }

  }
}
</script>

<style>

</style>
