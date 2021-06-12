<template>
  <div class="bg-white pt-10 w-11/12 lg:w-5/6 mx-auto shadow-lg">
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
import BookSuggestions from "./BookSuggestions"
import PopularSection from "./PopularSection"
export default {
  components: { BookSuggestions, PopularSection }
};
</script>

<style>
</style>