<template>
  <div class="bg-gradient-to-bl from-blue-100 to-blue-300 w-full">
    <div class="pt-20">
      <SearchBar v-on:refreshSearch="refreshSearch" />
    </div>
    <div class="py-20">
      <SearchLayout ref="searchLayout"/>
    </div>
  </div>
</template>

<script>
import SearchBar from "../components/home/SearchBar"
import SearchLayout from "../components/search/SearchLayout"
export default {
  components: { SearchBar, SearchLayout },
  methods: {
    refreshSearch () {
      this.$refs.searchLayout.refreshSearch()
    }
  },
  head () {
    return {
      title: this.$route.meta.title
    }
  },
};
</script>

<style>
</style>