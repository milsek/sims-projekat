<template>
  <div class="grid grid-cols-6">
    <div class="col-span-5">
      <PopularBooks />
    </div>
    <div class="col-start-6 col-span-1">
      <PopularAuthors />
    </div>
  </div>
</template>

<script>
import PopularBooks from "./PopularBooks.vue";
import PopularAuthors from "./PopularAuthors.vue";
export default {
  components: { PopularBooks, PopularAuthors },
};
</script>

<style>
</style>