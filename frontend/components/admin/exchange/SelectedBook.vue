<template>
  <div class="block md:flex mx-auto rounded-xl border border-dashed border-gray-300 p-6">
    <div class="flex-shrink-0">
      <img :src="data.edition.imageLarge" alt=""
      class="text-center sm:text-left rounded-md
      w-36 lg:w-40 h-56 lg:h-60 object-fill mx-auto" draggable="false">
    </div>
    <div class="md:pl-4 text-center md:text-left">
      <div class="text-xl md:text-base lg:text-xl font-bold text-blue-900 opacity-90
       line-clamp-2 mt-4 md:mt-0">
        {{ data.edition.title.title }}
      </div>
      <div class="text-xs tracking-wide text-gray-400 line-clamp-2">
        {{data.edition.title.contributions[0].contributor.name}}
      </div>

      <div>Row {{}}</div>

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