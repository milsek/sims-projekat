<template>
  <div class="block md:flex w-11/12 mx-auto rounded-lg bg-white shadow-lg">
    <div class="p-4 md:p-8 h-full">
      <BookFilter />
    </div>
    <div class="p-4 md:p-8 md:pl-0 h-full">
      <BookList ref="bookList"/>
    </div>
  </div>
</template>

<script>
import BookFilter from "./BookFilter.vue"
import BookList from "./BookList.vue"
export default {
  components: { BookFilter, BookList },
  methods: {
    refreshSearch() {
      this.$refs.bookList.getBooks()
    }
  }
};
</script>

<style>
</style>