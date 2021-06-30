<template>
  <div class="block mx-2 lg:mx-3 mb-6 ">
    <div>
      <div>
        <img :src="data.edition.imageLarge" alt=""
        class="text-center sm:text-left m-auto sm:m-0 rounded-md shadow-lg w-40 sm:w-36 h-56 sm:h-48 lg:h-48 object-fill" draggable="false">
      </div>
    </div>
    <div class="py-4 text-center sm:text-left">
      <div class="m-auto sm:m-0 text-sm font-bold text-blue-900 opacity-70
       line-clamp-2 hover:text-blue-800 hover:opacity-90 w-32">
        {{ data.edition.title.title }}
      </div>
      <div class="m-auto sm:m-0 text-xs tracking-wide text-gray-400 line-clamp-2">{{data.edition.title.contributions[0].contributor.name}}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: [ "data" ],
  mounted() {
      console.log(this.data);
  }
};
</script>

<style>
</style>
