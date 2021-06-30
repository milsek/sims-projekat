<template>
  <div class="w-11/12 mx-auto rounded-lg bg-white md:shadow-lg md:block">
  
    <div class="mt-4 text-lg lg:text-xl gap-6 md:flex justify-around mx-auto w-full sm:w-4/5 md:w-3/5 lg:5/1 xl:w-2/5">
      <MiniSearchBar v-on:userIsSelected="userIsSelected($event)" v-on:book-is-selected="bookIsSelected($event)"
        what="by book id" how="search_book" class=""/>
    </div>

    <div class="block text-center mx-auto mt-12 w-4/5 lg:w-3/5">
      <div class=" md:flex flex-wrap">
        <SelectedUser v-show="user_selected" :id=user_id :name=user_name :surname=user_surname />
      </div>
      <div class="">
        <SelectedBook v-if="book_selected" :data=selected_book />
      </div>
    </div>

  </div>
  
</template>

<script>
import axios from "axios"
import MiniSearchBar from "~/components/admin/exchange/MiniSearchBar"
import SelectedUser from "~/components/admin/exchange/SelectedUser"
import SelectedBook from "~/components/admin/exchange/SelectedBook"
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
