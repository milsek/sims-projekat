<template>
  <!-- This example requires Tailwind CSS v2.0+ -->
  <div class="fixed z-10 inset-0 overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
    <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0 -mt-32 sm:-mt-0">
      <div @click.self="closeModal" class="fixed inset-0 bg-gray-700 bg-opacity-75 transition-opacity" aria-hidden="true"></div>

      <!-- This element is to trick the browser into centering the modal contents. -->
      <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>

      <!--
      Modal panel, show/hide based on modal state.

      Entering: "ease-out duration-300"
          From: "opacity-0 t  ranslate-y-4 sm:translate-y-0 sm:scale-95"
          To: "opacity-100 translate-y-0 sm:scale-100"
      Leaving: "ease-in duration-200"
          From: "opacity-100 translate-y-0 sm:scale-100"
          To: "opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
      -->
      <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl
       transform transition-all sm:my-8 sm:align-middle sm:max-w-lg w-11/12 sm:w-full">

        <div class="block px-6 sm:px-12 sm:p-6">
          <!-- DROPDOWN THING -->
          <MiniSearchBar @user-is-selected="userIsSelected($event)"
            what="members" how="autocomplete-user-id" class=""/>
        </div>

        <div v-if="user_selected" class="block px-6 sm:px-12 sm:p-6">
          <SelectedUser :id=user_id :name=user_name :surname=user_surname />
        </div>

      <div class="bg-gray-100 px-4 py-5 mt-8 sm:px-6 flex justify-end">
          <button @click="closeModal" type="button" class="inline-flex justify-center
          rounded-md shadow-sm px-6 py-2 bg-white text-base font-medium text-gray-700
        hover:bg-gray-300 focus:outline-none w-auto sm:text-sm">
          Cancel
          </button>
          <button type="button" class="inline-flex justify-center rounded-md shadow-sm
          px-6 py-2 bg-blue-700 text-base font-medium text-white hover:bg-blue-900
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
      user_selected : false,
      user_id : -1,
      user_name : "",
      user_surname: "",
      data_id: "",
    }
  },
  methods: {
    closeModal () {
      this.$emit('close-modal')
    },
    userIsSelected(data) {
      this.user_selected = true;
      this.user_id = data.id;
      this.user_name = data.name;
      this.user_surname = data.surname;
    },
  },
}
</script>

<style>

</style>