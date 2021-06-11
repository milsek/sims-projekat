<template>
  <div class="block md:grid md:grid-cols-6">
    <div class="col-start-6 col-span-1 order-2">
      <PopularAuthors />
    </div>
    <div class="col-start-1 col-span-5 order-1">
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