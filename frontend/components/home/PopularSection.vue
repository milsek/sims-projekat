<template>
  <div class="block md:grid md:grid-cols-5">
    <div class="col-start-5 col-span-1 order-2">
      <PopularAuthors />
    </div>
    <div class="col-start-1 col-span-4 order-1">
      <PopularBooks />
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