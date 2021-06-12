<template>
  <div class="block md:grid md:grid-cols-5 p-8 md:p-20">
    <div class="col-start-1 col-span-4 order-1 pt-12 md:py-0">
      <PopularBooks />
    </div>
    <div class="block col-start-5 col-span-1 order-2 py-8 pb-16 md:py-0 ">
      <PopularAuthors />
      <PopularGenres />
    </div>
  </div>
</template>

<script>
import PopularBooks from "./PopularBooks.vue";
import PopularAuthors from "./PopularAuthors.vue";
import PopularGenres from "./PopularGenres.vue";
export default {
  components: { PopularBooks, PopularAuthors, PopularGenres },
};
</script>

<style>
</style>