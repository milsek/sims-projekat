<template>
  <div class="flex overflow-x-hidden mx-auto rounded-xl border border-dashed border-gray-300 p-4 sm:p-6 mt-4 ">
    <div class="flex-shrink-0">
      <img :src="data.editionImageLarge"
        alt=""
        class="text-left rounded-md w-28 h-40 object-fill mx-auto"
        draggable="false"
      />
    </div>
    <div class="pl-4 text-left" v-if="data.bookState">
      <div class="text-base lg:text-lg font-bold text-blue-900 opacity-90
      line-clamp-2" >
          {{ data.editionTitleTitle }}
      </div>
      <div class="text-xs tracking-wide text-gray-400 line-clamp-2">
          {{ data.authorName }}
      </div>

      <div class="mt-2 text-sm font-medium tracking-wide text-gray-500 ">
          <div class="line-clamp-2">ID: {{ data.id }}</div>
          <div class="line-clamp-2">
          Isle: {{ data.lineIsleName }} - Row: {{ data.lineNumber }}
          </div>
          <div class="line-clamp-2">
          Condition:
          <span class="font-bold">{{
              data.condition
          }}</span>
          </div>
      <div class="line-clamp-2">State: {{ data.bookState }}</div>
    </div>
    </div>
  </div>

</template>

<script>
export default {
    props: ["data"],

}
</script>

<style>

</style>