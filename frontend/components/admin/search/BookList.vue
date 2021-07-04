<template>
  <div v-if="books.length > 0">
      <div class="block min-h-screen mt-2">
        <div v-for="book in books" :key="book.id">
          <BookCard :data="book" />
        </div>
      </div>
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
                'author': this.nesto[2].query, 'bookStatus': this.nesto[3].query }

        }
    },
    mounted() {
        this.searchBooks();
    },
    methods : {
        searchBooks() {
            this.books = [];
            axios
            .get(this.getRequestText())
            .then( x => { this.books = x.data; console.log(x); })
            .catch( x => { console.log(x); })
        },
        getRequestText() {
            const bookId = this.data.bookId ? "bookId=" + this.data.bookId.trim() + '&': ''
            const title = this.data.title ? "title=" + this.data.title.trim() + '&': ''
            const author = this.data.author ? "author=" + this.data.author.trim() + '&': ''
            const bState = this.data.bookStatus ? "status=" + this.data.bookStatus.trim() + '&' : ''
            const requestText = `/api/search-book?${bookId}${title}${author}${bState}`
            return requestText.slice(0, -1)
        },
    }
}
</script>

<style>

</style>