<template>
  <div class="p-4 text-center sm:text-left">
    <div class="block sm:grid grid-cols-12">
      <div class="col-span-6 xl:col-span-5 p-2 md:h-full">
        <NuxtLink :to="{ name: 'bookinstance', params: {id: data.id}}">
          <img :src="data.imageSmall" alt="" class="rounded-md mx-auto shadow-md sm:w-32 xl:w-full h-64 sm:h-40 md:h-44 xl:h-48 object-fill" draggable="false">
        </NuxtLink>
      </div>
      <div class="mt-8 md:mt-12 lg:mt-12 xl:mt-14 sm:col-start-7 xl:col-start-6 col-span-6 xl:col-span-7 w-full p-2 sm:h-40">
        <!-- text-gray-700 -->
        <NuxtLink :to="{ name: 'bookinstance', params: {id: data.id}}"
        class="hover:text-blue-800 w-auto font-sans font-medium text-blue-900 opacity-75 hover:opacity-90
         text-base md:text-sm lg:text-base tracking-wide line-clamp-2">
          {{ data.title.title }}
        </NuxtLink>
        <div class="mb-2 font-sans text-sm font-thin text-gray-400 tracking-wider line-clamp-2">by {{data.title.contributions[0].contributor.name}}</div>
        <div class="pb-0 inline-block font-thin align-middle text-sm text-gray-400">Reads: {{readsInThousands}}</div>
        <div class="flow">
          <img :src="require(`@/assets/icons/star.svg`)" alt="" class="inline-block align-middle w-3 h-3 my-0" draggable="false">
          <p class="inline-block align-middle text-sm text-gray-500 my-0">{{data.rating}}</p>
        </div>
      </div>
      <div class="hidden sm:block p-2 mt-4 col-span-full">
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
      if (this.data.reads > 1000) {
        return Math.round(this.data.reads/1000) + "k"
      } else return this.data.reads
    }
  },
  mounted() {
  }
};

</script>

<style>
</style>
