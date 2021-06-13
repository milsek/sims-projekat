<template>
  <div>
    <div v-show="showBooks" class="block min-h-screen">
      <div v-for="book in booklist" :key="book.id">
        <BookCard :data="book" />
      </div>
    </div>
    <Pagination :maxVisibleButtons="maxVisibleButtons" :totalPages="totalPages" :total="totalBooks"
    :perPage="resultsPerPage" :currentPage="pageIndex + 1" :hasMorePages="hasMorePages"
      @pagechanged="showMore" class="pt-12 pb-16"/>

    <div v-show="!showBooks" class="h-screen w-full bg-white">

    </div>
  </div>
</template>

<script>
import BookCard from "./BookCard"
import Pagination from "./Pagination"
import getBooklist from "~/composables/getTopTenBooks"
export default {
  components: { BookCard, Pagination },
  data() {
    return {
      pageIndex: 0,
      resultsPerPage: 6,
      showBooks: false,
      showPagination: false,
      totalPages: 1,
      totalBooks: 1,
      maxVisibleButtons: 3,
      booklist: []
    };
  },
  mounted () {
    this.getBooks()
    this.showPagination = true
    this.showBooks = true
  },
  methods: {
    getBooks() {
      const bl = getBooklist(this.pageIndex * this.resultsPerPage, (this.pageIndex + 1) * this.resultsPerPage)
      this.totalBooks = 8
      this.totalPages = Math.ceil(this.totalBooks/this.resultsPerPage)
      this.booklist = bl
    },
    showMore(page) {
      this.pageIndex = page - 1
      this.getBooks()
    }
  },
  computed: {
    hasMorePages () {
      if (this.pageIndex === this.totalPages-1) {
        return true
      }
      else {
        false
      }
    }
  }
};
</script>

<style>
  .pagination {
      list-style-type: none;
    }

  .pagination-item {
    display: inline-block;
  }
</style>
