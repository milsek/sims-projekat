<template>
  <div v-if="books.length > 0">
      <div class="block min-h-screen">
        <div v-for="book in books" :key="book.id">
          <BookCard :data="book" />
        </div>
      </div>
      <Pagination :maxVisibleButtons="maxVisibleButtons" :totalPages="totalPages" :total="totalReservations"
      :perPage="resultsPerPage" :currentPage="pageIndex + 1" :hasMorePages="hasMorePages"
        @pagechanged="showMore" class="pt-12 pb-16"/>
    </div>
</template>

<script>
import BookCard from '~/components/admin/search/BookCard';
import axios from 'axios';
export default {
    props : ["nesto"],
    components : {BookCard},
    data() {
        return {
            books: [],
            data: { 'bookId': this.nesto[0].query, 'title': this.nesto[1].query,
                'author': this.nesto[2].query }

        }
    },
    mounted() {
        console.log(this.data);
        this.searchBooks();
    },
    methods : {
        searchBooks() {
            console.log("RADIM PRETRAGU");
            axios
            .get(this.getRequestText())
            .then( x => { this.books = x.data; console.log(x); })
            .catch( x => { console.log(x); })
        },
        getRequestText() {
            const bookId = this.data.bookId ? "bookId=" + this.data.bookId.trim() + '&': ''
            const title = this.data.title ? "title=" + this.data.title.trim() + '&': ''
            const author = this.data.author ? "author=" + this.data.author.trim() + '&': ''
            const requestText = `/api/search-book?${bookId}${title}${author}`
            return requestText.slice(0, -1)
        },
    }
}
</script>

<style>

</style>