<template>
  <div class="block w-full">
    <div class="justify-center w-full text-center mx-auto py-10 px-10">
      <div class="text-4xl tracking-tight text-blue-900 opacity-70">Book search</div>
      <div class="font-medium text-gray-500 mt-2">Find books in library</div>

      <div class="grid grid-cols-3 grid-flow-row text-left mt-10 gap-y-4 gap-x-4 xl:gap-x-4 sm:px-4 md:px-0 xl:px-24">
        <div v-for="i in fields" :key="i.name" class="col-span-4 sm:col-span-2 lg:col-span-1">
          <InputField @input-change="updateInput" :what="i"/>
        </div>
      </div>

      <div class="mt-2 mx-auto">
        <button @click="getBooks" class="h-10 mt-4 px-6 md:px-8
          opacity-90 text-white text-center text-xl pb-1 focus:outline-none rounded-lg bg-blue-700 hover:bg-blue-800">
          find books
        </button>
      </div>

      <BookList v-if="showList" :nesto="fields" />
      
    </div>
  </div>
</template>

<script>
import InputField from '~/components/admin/registration/InputField'
import BookList from '~/components/admin/search/BookList'
export default {
  components: { InputField, BookList },
  data() {
      return {
        fields: [
                {text: 'Book ID', placeholder: 'Book ID', name: 'bookId', query: ''},
                {text: 'Book Title', placeholder: 'Book Title', name: 'title', query: ''},
                {text: 'Author', placeholder: 'Author', name: 'author', query: ''},
            ],
        showList: false
      }
  },
  methods : {
    updateInput (field, text) {
        this.fields.filter((f) => {if (f.name===field) return f})[0]['query'] = text
    },
    getBooks() {
        this.showList = true;
    },
    computed: {
        inputData () {
        return { 'bookId': this.fields[0].query, 'title': this.fields[1].query,
                'author': this.fields[2].query }
        }
    }
  }
  

}
</script>

<style>

</style>