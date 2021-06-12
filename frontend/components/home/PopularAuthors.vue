<template>
  <div class="px-8 md:px-2 text-center md:text-left">
    <div>
      <div class="text-lg font-medium tracking-wide text-blue-800 opacity-70">Authors</div>
    </div>
    <div class="pt-6 md:pt-8">
      <div v-for="author in popularAuthors" :key="author.id">
        <div class="text-lg md:text-sm lg:text-base xl:text-lg font-light md:font-normal lg:font-light tracking-wide pb-1 text-blue-800 opacity-70 truncate">{{author.name}}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  // getPopularAuthors
  data () {
    return {
      popularAuthors: [
        {name: 'Friedrich Nietzsche', id: 1},
        {name: 'C.G. Jung', id: 2},
        {name: 'Sigmund Freud', id: 3},
        {name: 'Firstname Lastname', id: 4},
        {name: 'Firstname Lastname', id: 5},
        ]
    }
  }
}
</script>

<style>

</style>