<template>
  <div class="mx-auto">
    <div class="flex pb-10 mx-auto">
      <div class="inline-block w-40 sm:w-36 md:w-32">
        <NuxtLink :to="{ name: 'bookinstance', params: {id: data.id}}">
          <img :src="data.imageLarge" alt="" class="rounded-lg object-cover w-full">
        </NuxtLink>
        <div class="block sm:hidden pt-2">
          <button class="h-8 md:h-7 lg:h-8 px-6 sm:px-4 lg:px-6 pb-1 bg-gradient-to-tr from-red-400 to-purple-400 text-white
          text-center text-lg md:text-base lg:text-lg shadow-md focus:outline-none hover:text-gray-900 rounded-lg">reservation</button>
        </div>
      </div>
      <div class="inline-block font-display pl-4 md:pl-6 w-5/6">
        <div class="flex justify-between text-base md:text-xl font-bold">
          <NuxtLink :to="{ name: 'bookinstance', params: {id: data.id}}" class="hover:text-blue-800 hover:opacity-80 w-auto">
              {{ data.title.title }}
          </NuxtLink>
        <div class="hidden sm:block -mt-1">
          <button class="h-8 md:h-7 lg:h-8 px-6 sm:px-4 lg:px-6 pb-1 bg-gradient-to-tr from-red-400 to-purple-400 text-white
          text-center text-lg md:text-base lg:text-lg shadow-md focus:outline-none rounded-lg
          hover:transition duration-300 ease-in-out transform hover:-translate-y-1">reservation</button>
        </div>
        </div>
        <div class="text-sm font-light pb-2 text-gray-500">
          {{ data.title.contributions[0].contributor.name }}
        </div>
        <div class="text-sm text-justify font-light md:tracking-wide mb-4 line-clamp-4 md:line-clamp-2 xl:line-clamp-3 text-gray-400">
          {{ data.description }}
        </div>
        <div class="pb-0 inline-block font-thin align-middle text-sm text-gray-400">Reads: {{readsInThousands}}k</div>
        <div class="flow">
          <img :src="require(`@/assets/icons/star.svg`)" alt="" class="inline-block align-middle w-3 h-3 my-0" draggable="false">
          <p class="inline-block align-middle text-sm text-gray-500 my-0">{{data.rating}}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    props: ['data'],
    data () {
        return {
        }
    },
  computed: {
    readsInThousands () {
      return Math.round(this.data.reads/1000)
    }
  }
  };
</script>

<style>

</style>
