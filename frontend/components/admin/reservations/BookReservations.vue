<template>
  <div class="block w-full">
    <div class="justify-center w-full text-center mx-auto py-10 px-10">
      <div class="text-4xl tracking-tight text-blue-900 opacity-70">Reservation Control</div>
      <div class="font-medium text-gray-500 mt-2">Approve/Decline reservations</div>

      <div class="grid grid-cols-4 grid-flow-row text-left mt-10 gap-y-4 gap-x-4 xl:gap-x-4 sm:px-4 md:px-0 xl:px-24">
        <div v-for="i in fields" :key="i.name" class="col-span-4 sm:col-span-2 lg:col-span-1">
          <InputField @input-change="updateInput" :what="i"/>
        </div>
        
        <div class="block col-span-4 sm:col-span-2 lg:col-span-1">
          <div class="text-sm text-gray-500 font-medium pl-2">Reservation Status</div>
          <select name="status" id="reservation-status-select" v-model="reservationState"
          class="block rounded-md border text-gray-600 border-solid border-gray-200 text-lg
          mt-1 py-1 px-4 focus:outline-none focus:border-gray-300 w-full">
            <option value="">-- Status --</option>
            <option value="APPROVED">Approved</option>
            <option value="DENIED">Denied</option>
            <option value="NEW">New</option>
            <option value="RETURNED">Returned</option>
            <option value="SEIZED">Seized</option>
          </select>
        </div>
      </div>

      <div class="mt-2 mx-auto">
        <button @click="getReservations" class="h-10 mt-4 px-6 md:px-8
          opacity-90 text-white text-center text-xl pb-1 focus:outline-none rounded-lg bg-blue-700 hover:bg-blue-800">
          find reservations
        </button>
      </div>
      
      <ReservationList v-if="showList" :data="inputData" ref="reservationList" class="mt-10"/>

    </div>
  </div>
</template>

<script>
import InputField from '~/components/admin/registration/InputField'
import ReservationList from '~/components/admin/reservations/ReservationList'
export default {
  components: { InputField, ReservationList },
  data () {
    return {
      fields: [
        {text: 'User ID', placeholder: 'User ID', name: 'userId', query: ''},
        {text: 'Book ID', placeholder: 'Book ID', name: 'bookId', query: ''},
        {text: 'Book Title', placeholder: 'Book Title', name: 'title', query: ''},
      ],
      reservationState: '',
      showList: false
    }
  },
  methods: {
    updateInput (field, text) {
      this.fields.filter((f) => {if (f.name===field) return f})[0]['query'] = text
    },
    getReservations () {
      if (!this.showList) {
        this.showList = true
      } else {
        this.$refs.reservationList.refreshPagination()
        this.$refs.reservationList.getReservations()
      }
    }
  },
  computed: {
    inputData () {
      return { 'userId': this.fields[0].query, 'bookId': this.fields[1].query,
                'bookTitle': this.fields[2].query, 'reservationState': this.reservationState }
    }
  }
}
</script>

<style>

</style>
