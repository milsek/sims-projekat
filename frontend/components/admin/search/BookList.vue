<template>
    <div>
        <div v-if="books.length > 0">
            <div class="block min-h-screen mt-2 mx-auto w-full lg:w-11/12 xl:w-2/3">
                <div v-for="book in books" :key="book.id">
                    <BookCard :data="book" />
                </div>
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
                'author': this.nesto[2].query, 'bookStatus': this.nesto[3].query },
            pageIndex: 0,
            resultsPerPage: 24,
            showPagination: false,
            totalPages: 1,
            totalReservations: 1,
            maxVisibleButtons: 3,
            reservations: [],
            loadMessage: 'Loading...'
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
        showMore(page) {
            this.pageIndex = page - 1
            this.getBooks()
        },
        refreshPagination() {
            this.pageIndex = 0
        },
        allFieldsEmpty() {
            if (true)
                return true
            return false
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
}
</script>

<style>

</style>