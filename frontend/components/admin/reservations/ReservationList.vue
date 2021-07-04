<template>
  <div class="w-full">
    <div v-if="reservations.length > 0" class="w-full">
      <div class="inline-grid w-full items-stretch grid-flow-row grid-cols-1 lg:grid-cols-2 xl:grid-cols-3
      gap-x-4 gap-y-4">
        <div v-for="reservation in reservations" :key="reservation.id" class="h-full w-full">
          <ReservationCard :data="reservation" class="h-full w-full"/>
        </div>
      </div>

      <Pagination :maxVisibleButtons="maxVisibleButtons" :totalPages="totalPages" :total="totalReservations"
      :perPage="resultsPerPage" :currentPage="pageIndex + 1" :hasMorePages="hasMorePages"
        @pagechanged="showMore" class="pt-12 pb-16"/>
    </div>

    <div v-else class="block w-full">
      <div class="w-full text-4xl font-thin text-center mx-auto">{{loadMessage}}</div>
    </div>

  </div>
</template>

<script>
import axios from 'axios'
import ReservationCard from "~/components/admin/reservations/ReservationCard"
import Pagination from "~/components/search/Pagination"
export default {
  components: { ReservationCard, Pagination },
  props: ['data'],
  data() {
    return {
      pageIndex: 0,
      resultsPerPage: 24,
      showPagination: false,
      totalPages: 1,
      totalReservations: 1,
      maxVisibleButtons: 3,
      reservations: [],
      loadMessage: 'Loading...'
    };
  },
  mounted () {
    console.log(this.data);
    this.getReservations()
    this.showPagination = true
  },
  methods: {
    getReservations () {
      if (this.allFieldsEmpty()) {
        this.loadMessage = 'No results. Make sure you fill at least one field.'
        this.reservations = []
        return
      } else {
        this.loadMessage = ''
      }
      console.log(this.getRequestText());
      axios.get(this.getRequestText())
      .then(response => {
        this.totalReservations = Object.keys(response.data)[0]
        this.totalPages = Math.ceil(this.totalReservations/this.resultsPerPage)
        this.reservations = response.data[this.totalReservations]
        
        if (!this.reservations) this.reservations = []  
        let that = this
        setTimeout(function() {
          if (that.reservations.length == 0) {
            that.loadMessage = 'No results.'
          } else {
            that.loadMessage = ''
          }
        }, 1500);
      })
    },
    getRequestText() {
      const userId = this.data.userId ? "userId=" + this.data.userId.trim() + '&': ''
      const bookId = this.data.bookId ? "bookId=" + this.data.bookId.trim() + '&': ''
      const bookTitle = this.data.bookTitle ? "bookTitle=" + this.data.bookTitle.trim() + '&': ''
      const reservationState = this.data.reservationState ? "status=" + this.data.reservationState.trim().toLowerCase() + '&': ''
      const requestText = `/api/search-reservations?page=${this.pageIndex + 1}&amount=24&${userId}${bookId}${bookTitle}${reservationState}`

      return requestText.slice(0, -1)
    },
    showMore(page) {
      this.pageIndex = page - 1
      this.getReservations()
    },
    refreshPagination() {
      this.pageIndex = 0
    },
    allFieldsEmpty() {
      if (!this.data.userId && !this.data.bookId && !this.data.bookTitle && !this.data.reservationState)
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