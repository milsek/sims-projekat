<template>
  <div>
    <div class="w-11/12 md:w-4/5 lg:w-3/4 xl:w-1/2 mx-auto bg-white shadow px-6">
      <div class="grid grid-cols-6 text-lg lg:text-xl">
        <div class="col-start-1 col-span-5 md:col-span-4 my-auto">
          <input type="text" autocomplete="off" name="query" required v-model="query" placeholder="Search a book, an author, or a genre" 
          class="inline-block mb-0 px-4 h-14 w-11/12 md:w-120 font-light focus:outline-none">
        </div>
        <div class="col-start-6 m-auto hidden md:flex">
          <!-- bg-gradient-to-tr from-blue-400 to-purple-600 -->
          <button @click="doSearch" class="h-9 px-6 pb-1 rounded-full bg-gradient-to-tr from-red-400 to-purple-400 text-white
          text-center shadow-md focus:outline-none hover:text-gray-900">search</button>
        </div>
      </div>
    </div>

    <div class="text-center mx-auto pt-6 md:hidden text-xl">
          <!-- bg-gradient-to-tr from-blue-400 to-purple-600 -->
      <button @click="doSearch" class="h-10 px-8 pb-1 rounded-full bg-gradient-to-tr from-red-400 to-purple-400 text-gray-50
        text-center shadow-md focus:outline-none">search</button>
    </div>
  </div>
  
</template>

<script>
export default {
  methods: {
    doSearch () {
      if (this.$route.path === '/') {
        this.$router.push('/search')
      } else {
        this.$emit('refreshSearch')
      }
    }
  },
  computed:{
    query:{
        get: function(){ 
            return this.$store.state.search.searchValue; 
        }, 
        set: function(newName){ 
            this.$store.commit('search/update', newName); 
        }
    }
} 
};
</script>

<style>
* { -webkit-tap-highlight-color: rgba(0,0,0,0); }
</style>
