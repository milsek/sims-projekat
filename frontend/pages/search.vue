<template>
  <div class="bg-gradient-to-bl from-blue-100 to-blue-300 w-full">
    <div class="pt-20">
      <SearchBar />
    </div>
    <div class="py-20">
      <SearchLayout />
    </div>
  </div>
</template>

<script>
import SearchBar from "../components/home/SearchBar"
import SearchLayout from "../components/search/SearchLayout"
export default {
  components: { SearchBar, SearchLayout }
};
</script>

<style>
</style>