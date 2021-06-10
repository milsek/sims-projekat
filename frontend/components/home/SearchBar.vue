<template>
  <div>
    <div class="w-11/12 md:w-4/5 lg:w-3/4 xl:w-1/2 mx-auto bg-white shadow px-6">
      <div class="grid grid-cols-6 text-lg lg:text-xl">
        <div class="col-start-1 col-span-5 md:col-span-4 my-auto">
          <input type="query" name="query" required v-model="query" placeholder="Search a book, an author, or a genre" 
          class="inline-block mb-0 px-4 h-14 w-11/12 md:w-120 font-light focus:outline-none">
        </div>
        <div class="col-start-6 m-auto hidden md:flex">
          <button class="h-9 px-6 rounded-full bg-blue-500 text-gray-50
          text-center shadow-md hover:bg-blue-600 focus:outline-none">search</button>
        </div>
      </div>
    </div>

    <div class="text-center mx-auto pt-6 md:hidden text-xl">
      <button class="h-10 px-8 rounded-full bg-blue-500 text-gray-50
        text-center shadow-md hover:bg-blue-600 focus:outline-none">search</button>
    </div>
  </div>
  
</template>

<script>
export default {
  data() {
    return {
      query: ''
    }
  }
};
</script>

<style>

</style>
