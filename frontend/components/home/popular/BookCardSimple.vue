<template>
  <div class="block mx-2 lg:mx-3 mb-6 ">
    <div>
      <NuxtLink :to="{ name: 'bookinstance', params: {id: data.id}}">
        <img :src="data.imageSmall" alt="" 
        class="text-center sm:text-left m-auto sm:m-0 rounded-md shadow-lg w-40 sm:w-36 h-56 sm:h-48 lg:h-48 object-fill" draggable="false">
      </NuxtLink>
    </div>
    <div class="py-4 text-center sm:text-left">
      <NuxtLink :to="{ name: 'bookinstance', params: {id: data.id}}"
      class="m-auto sm:m-0 text-sm font-bold text-blue-900 opacity-70
       line-clamp-2 hover:text-blue-800 hover:opacity-90 w-32">
        {{ data.title }}
      </NuxtLink>
      <div class="m-auto sm:m-0 text-xs tracking-wide text-gray-400 line-clamp-2">{{data.author.name}}</div>
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