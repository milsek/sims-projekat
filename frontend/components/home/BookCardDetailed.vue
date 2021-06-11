<template>
  <div class="p-4 text-center sm:text-left">
    <div class="block sm:grid grid-cols-5 grid-rows-2">
      <div class="col-span-3 md:col-span-2 lg:col-span-3 xl:col-span-2 p-2 md:h-full">
        <img :src="require(`@/assets/images/${data.image}`)" alt="" class="rounded-md mx-auto" draggable="false">
      </div>
      <div class=" sm:col-start-4 md:col-start-3 lg:col-start-4 xl:col-start-3 col-span-2 md:col-span-3 lg:col-span-2 xl:col-span-3 w-full p-2">
        <div class="font-sans font-medium text-gray-700 text-base md:text-sm lg:text-base tracking-wide line-clamp-2">{{data.title}}</div>
        <div class="mb-2 font-sans text-sm font-thin text-gray-400 tracking-wider line-clamp-2">by {{data.author}}</div>
        <div class="pb-0 inline-block font-thin align-middle text-sm text-gray-400">Reads: {{readsInThousands}}k</div>
        <div class="flow">
          <img :src="require(`@/assets/icons/star.svg`)" alt="" class="inline-block align-middle w-3 h-3 my-0" draggable="false">
          <p class="inline-block align-middle text-sm text-gray-500 my-0">{{data.rating}}</p>
        </div>
      </div>
      <div class="hidden sm:block row-start-3 sm:row-start-2 col-span-5 p-2 mt-4">
        <div class="text-lg text-gray-700 tracking-wide font-medium py-2">Description</div>
        <div class="text-sm text-justify font-light tracking-wide text-gray-400 mb-2 line-clamp-4">{{data.description}}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["data"],
  computed: {
    readsInThousands () {
      return Math.round(this.data.reads/1000)
    }
  }
};
</script>

<style>
</style>