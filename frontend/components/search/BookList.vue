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
import axios from 'axios';
import BookCard from "./BookCard"
import Pagination from "./Pagination"
export default {
  components: { BookCard, Pagination },
  data() {
    return {
      pageIndex: 0,
      resultsPerPage: 10,
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
      const text = this.$store.state.search.searchValue ? "text=" + this.$store.state.search.searchValue.trim() : ''
      axios.get(`/api/fullSearch?${text}&page=${this.pageIndex + 1}&amount=${this.resultsPerPage}`)
      .then(response => {
        this.booklist = response.data;
      });
    }
    ,
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
