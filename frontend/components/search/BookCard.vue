<template>
  <div class="mx-auto">
    <div class="flex pb-10 mx-auto">
      <div class="inline-block w-40 sm:w-36 md:w-32">
          <img :src="require(`@/assets/images/${data.image}`)"  alt="" class="rounded-lg object-cover w-full">
      </div>
      <div class="minline-block font-display pl-4 md:pl-6 w-5/6">
        <div class="text-base md:text-xl font-bold">
          <NuxtLink :to="{ name: '/', params: {id: data.id}}" class="hover:text-blue-800 hover:opacity-80 w-auto">
              {{ data.title }}
          </NuxtLink>
        </div>
        <div class="text-sm font-light pb-2 text-gray-500">
          {{ data.author }}
        </div>
        <div class="text-xs md:text-sm text-justify font-light md:tracking-wide mb-4 line-clamp-3 md:line-clamp-2 xl:line-clamp-3 text-gray-400">
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