<template>
  <div class="block w-full">
    <div class="justify-center w-full text-center mx-auto py-10 px-10">
      <div class="text-4xl tracking-tight text-blue-900 opacity-70">Reservation Control</div>
      <div class="font-medium text-gray-500 mt-2">Approve/Decline reservations</div>

      <div class="grid grid-cols-4 grid-flow-row text-left mt-10 gap-y-4 gap-x-4 xl:gap-x-4 sm:px-4 md:px-0 xl:px-24">
        <div v-for="i in fields" :key="i.text" class="col-span-4 sm:col-span-2 lg:col-span-1">
          <InputField @input-change="updateInput" :what="i"/>
        </div>
        
        <div class="block col-span-4 sm:col-span-2 lg:col-span-1">
          <div class="text-sm text-gray-500 font-medium pl-2">Reservation Status</div>
          <select name="status" id="reservation-status-select"
          class="block rounded-md border text-gray-600 border-solid border-gray-200 text-lg
          mt-1 py-1 px-4 focus:outline-none focus:border-gray-300 w-full">
            <option value="">-- Status --</option>
            <option value="approved">Approved</option>
            <option value="denied">Denied</option>
            <option value="new">New</option>
            <option value="returned">Returned</option>
            <option value="seized">Seized</option>
          </select>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import InputField from '~/components/admin/registration/InputField'
export default {
  components: { InputField },
  data () {
    return {
      fields: [
        {text: 'User ID', placeholder: 'User ID', name: 'userId', query: ''},
        {text: 'Book ID', placeholder: 'Book ID', name: 'bookId', query: ''},
        {text: 'Book Title', placeholder: 'Book Title', name: 'title', query: ''},
      ]
    }
  },
  methods: {
    updateInput (field, text) {
      this.fields.filter((f) => {return f.name=field})[0]['query'] = text
    },
  }
}
</script>

<style>

</style>
