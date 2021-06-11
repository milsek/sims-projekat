<template>
  <div class="block mx-2 lg:mx-3 mb-6">
    <div>
      <img :src="require(`@/assets/images/${data.image}`)" alt="" class="rounded-md mx-auto shadow-lg" draggable="false">
    </div>
    <div class="py-4">
      <div class="text-sm font-bold text-blue-900 opacity-70 line-clamp-2">{{data.title}}</div>
      <div class="text-xs  tracking-wide text-gray-400 line-clamp-2">{{data.author}}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: [ "data" ]
};
</script>

<style>
</style>