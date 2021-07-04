<template>
  <div class="fixed z-10 inset-0 overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
    <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0 -mt-32 sm:-mt-0">
      <div @click.self="closeModal" class="fixed inset-0 bg-gray-700 bg-opacity-75 transition-opacity" aria-hidden="true"></div>
      <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>

      <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl
       transform transition-all sm:my-8 sm:align-middle sm:max-w-lg w-11/12 sm:w-full">

        <div class="mx-auto my-auto text-center text-lg md:text-xl pt-6 px-4 text-gray-600 font-medium ">
           {{msg}}
        </div>
        <div class="bg-gray-100 px-4 py-5 mt-8 sm:px-6 flex justify-center md:justify-end">
          <button @click="closeModal" type="button" class="rounded-md shadow-sm px-6
          py-2 bg-green-500 hover:bg-green-600 text-base font-medium text-white
          focus:outline-none w-auto sm:text-sm">
          Confirm
          </button>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  components: { },
  props: ["msg"],
  data () {
    return {
    }
  },
  methods: {
    closeModal () {
      this.$emit('close-modal')
    },
  },
}
</script>

<style>

</style>