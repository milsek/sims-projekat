<template>
  <div class="block w-full">
    <div class="justify-center w-full text-center mx-auto py-10 px-10">
      <div class="text-4xl tracking-tight text-blue-900 opacity-70">Book search</div>
      <div class="font-medium text-gray-500 mt-2">Find books in library</div>

      <div class="grid grid-cols-4 grid-flow-row text-left mt-10 gap-y-4 gap-x-4 xl:gap-x-4 sm:px-4 md:px-0 xl:px-24">
        <div v-for="i in fields" :key="i.name" class="col-span-4 sm:col-span-2 lg:col-span-1">
          <InputField @input-change="updateInput" :what="i"/>
        </div>
      

      <div class="block col-span-4 sm:col-span-2 lg:col-span-1">
          <div class="text-sm text-gray-500 font-medium pl-2">Book Status</div>
          <select name="status" id="reservation-status-select" v-model="bookStatus"
          class="block rounded-md border text-gray-600 border-solid border-gray-200 text-lg
          mt-1 py-1 px-4 focus:outline-none focus:border-gray-300 w-full">
            <option value="">-- Status --</option>
            <option value="IN_STOCK">In stock</option>
            <option value="MISSING">Missing</option>
            <option value="TAKEN">Taken</option>
            <option value="REFURBISHMENT">Refursbishment</option>
            <option value="RESERVED">Reserved</option>
          </select>
        </div>
      </div>

      <div class="mt-2 mx-auto">
        <button @click="getBooks" class="h-10 mt-4 px-6 md:px-8
          opacity-90 text-white text-center text-xl pb-1 focus:outline-none rounded-lg bg-blue-700 hover:bg-blue-800">
          find books
        </button>
      </div>

      <BookList :key="br" v-if="showList" :nesto="newFields" />
      
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
        showList: false,
        bookStatus: '',
        newFields: '',
        br : 0
      }
  },
  methods : {
    updateInput (field, text) {
        this.fields.filter((f) => {if (f.name===field) return f})[0]['query'] = text
    },
    getBooks() {
        this.newFields = [];
        for(let a in this.fields) {
          this.newFields.push(this.fields[a]);
        }

        console.log(this.bookStatus);
        const that = this.bookStatus;
        console.log(that)
        this.newFields.push({text: 'Book status', name : 'bookStatus', query: that});
        console.log(this.newFields[4]);
        console.log(this.newFields);
        this.br++;
        this.showList = true;
    }
  }
  

}
</script>

<style>

</style>