<template>
  <div class="bg-white pt-10 w-11/12 lg:w-5/6 mx-auto shadow-lg rounded-lg">
    <!-- suggestions -->
    <div>
      <BookSuggestions />
    </div>
    <div>
      <PopularSection />
    </div>
    
  </div>
</template>

<script>
import BookSuggestions from "./suggestions/BookSuggestions"
import PopularSection from "./popular/PopularSection"
export default {
  components: { BookSuggestions, PopularSection }
};
</script>

<style>
</style>