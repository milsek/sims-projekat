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

      <div class="mt-2 text-sm font-medium tracking-wide text-gray-500 ">
        <div class="line-clamp-2">ID: {{data.id}}</div>
        <div class="line-clamp-2">Isle: {{data.line.isle.id}}   Row: {{data.line.id}}</div>
        <div class="line-clamp-2">Condition: <span :class="[colorCondition]" class="font-bold">{{ data.condition }}</span></div>
        <div class="line-clamp-2">State: {{ data.bookState }}</div>
      </div>
        
      <div v-if="data.bookState === 'IN_STOCK'" class="mt-5">
        <button class="h-9 mt-4 px-8 md:px-6 pb-1 bg-indigo-700 hover:bg-indigo-900
         opacity-90 text-white text-center text-lg shadow-md focus:outline-none rounded-lg">
          lend book
        </button>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  props: [ "data" ],
  mounted() {
      console.log(this.data);
  },
  computed: {
    colorCondition () {
      if (this.data.condition === 'UNUSABLE') return "text-red-600"
      if (this.data.condition === 'DAMAGED') return "orange-text"
      if (this.data.condition === 'WORN_OUT') return "text-yellow-400"
      if (this.data.condition === 'PERFECT') return "text-green-600"
    },
  }
};
</script>

<style>
.orange-text {
  color: #eb8052;
}
</style>