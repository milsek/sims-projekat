<template>
  <div class="w-11/12 mx-auto rounded-lg bg-white md:shadow-lg md:block">
  
    <div class="mt-10 text-lg lg:text-xl gap-6 md:flex justify-around mx-auto w-full sm:w-4/5 md:w-3/5 lg:5/1 xl:w-2/5">
      <MiniSearchBar @user-is-selected="userIsSelected($event)" @book-is-selected="bookIsSelected($event)"
        what="by book id" how="autocomplete-book-id" class=""/>
    </div>

    <div class="block text-center mx-auto mt-12 w-4/5 md:w-11/12 lg:w-3/5 xl:w-1/2">
      <div>
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
      book_selected: false,
      data_id: "",
      selected_book: ""
    }
  },
  methods: {
    bookIsSelected(data) {
      this.data_id = data.id;
      axios.get("/api/book/?id=" + this.data_id)
        .then(response => {
          this.selected_book = response.data;
          this.book_selected = true;
        });
    }

  }
}
</script>

<style>

</style>
