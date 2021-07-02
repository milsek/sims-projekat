<template>
  <div class="fixed z-10 inset-0 overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
    <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0 -mt-32 sm:-mt-0">
      <div @click.self="closeModal" class="fixed inset-0 bg-gray-700 bg-opacity-75 transition-opacity" aria-hidden="true"></div>
      <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>

      <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl
       transform transition-all sm:my-8 sm:align-middle sm:max-w-lg w-11/12 sm:w-full">

        <div class="block px-12 py-6">
          <!-- DROPDOWN THING -->
          <MiniSearchBar @user-is-selected="userIsSelected($event)"
            what="members" how="autocomplete-user-id" class=""/>
        </div>

        <div v-if="userSelected" class="block px-6 sm:px-12 sm:p-6">
          <SelectedUser :id=userId :name=userName :surname=userSurname />
          <div v-if="errorMsg" class="text-lg font-medium text-red-600 text-center mx-auto">
            {{errorMsg}}
          </div>
        </div>

      <div class="bg-gray-100 px-4 py-5 mt-8 sm:px-6 flex justify-end">
          <button @click="closeModal" type="button" class="inline-flex justify-center
          rounded-md shadow-sm px-6 py-2 bg-white text-base font-medium text-gray-700
        hover:bg-gray-300 focus:outline-none w-auto sm:text-sm">
          Cancel
          </button>
          <button @click="lendBook" :disabled="!userSelected" :class="[!userSelected ? 'bg-gray-600' : 'bg-blue-700 hover:bg-blue-900']" type="button" class="inline-flex justify-center rounded-md shadow-sm
          px-6 py-2 text-base font-medium text-white
          focus:outline-none ml-3 w-auto sm:text-sm">
          Lend
          </button>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import MiniSearchBar from "~/components/admin/exchange/MiniSearchBar"
import SelectedUser from "~/components/admin/exchange/SelectedUser"
export default {
  components: { MiniSearchBar, SelectedUser },
  data () {
    return {
      userSelected : false,
      userId : -1,
      userName : "",
      userSurname: "",
      errorMsg: ''
    }
  },
  methods: {
    closeModal () {
      this.$emit('close-modal')
    },
    userIsSelected(data) {
      this.userSelected = true;
      this.userId = data.id;
      this.userName = data.name;
      this.userSurname = data.surname;
    },
    lendBook() {
      this.$emit('lend-book', this.userId)
    },
    successfulLending(success) {
      if (success) {
        this.errorMsg = ''
        this.closeModal()
      }
      else this.errorMsg='Lending failed'
    }
  },
}
</script>

<style>

</style>