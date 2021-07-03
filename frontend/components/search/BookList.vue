<template>
  <div>
    <div v-if="booklist.length > 0">
      <div class="block min-h-screen">
        <div v-for="book in booklist" :key="book.id">
          <BookCard :data="book" />
        </div>
      </div>
      <Pagination :maxVisibleButtons="maxVisibleButtons" :totalPages="totalPages" :total="totalBooks"
      :perPage="resultsPerPage" :currentPage="pageIndex + 1" :hasMorePages="hasMorePages"
        @pagechanged="showMore" class="pt-12 pb-16"/>
    </div>

    <div v-else class="block w-full">
      <div class="w-full text-4xl font-thin text-center mx-auto">{{loadMessage}}</div>
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
      showPagination: false,
      totalPages: 1,
      totalBooks: 1,
      maxVisibleButtons: 3,
      booklist: [],
      loadMessage: 'Loading...'
    };
  },
  mounted () {
    this.getBooks()
    this.showPagination = true
  },
  methods: {
    getBooks() {
      const text = this.$store.state.search.searchValue ? "text=" + this.$store.state.search.searchValue.trim() : ''
      axios.get(`/api/fullSearch?${text}&page=${this.pageIndex + 1}&amount=${this.resultsPerPage}`)
      .then(response => {
        this.totalBooks = response.data.hitCount;
        this.totalPages = Math.ceil(this.totalBooks/this.resultsPerPage);
        
        this.booklist = response.data.displayDtoList;
        let that = this;
        setTimeout(function() {
            if (that.booklist.length == 0) {
              that.loadMessage = 'No results.';
            }
          
        }, 1500);

      })
      
    },
    showMore(page) {
      this.pageIndex = page - 1
      this.getBooks()
    },
    refreshPagination() {
      this.pageIndex = 0
    }
  },
  computed: {
    hasMorePages () {
      if (this.pageIndex < this.totalPages-1) {
        return true
      }
      else {
        return false
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
