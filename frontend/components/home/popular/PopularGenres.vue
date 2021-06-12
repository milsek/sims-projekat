<template>
  <div class="pt-8 md:py-16 px-8 md:px-2 text-center md:text-left">
    <div>
      <div class="text-lg font-medium tracking-wide text-blue-800 opacity-70">
        Genres
      </div>
    </div>
    <div class="pt-8">
      <div v-for="genre in popularGenres" :key="genre.id">
        <div class="text-lg md:text-sm lg:text-base xl:text-lg font-light md:font-normal lg:font-light tracking-wide pb-1 text-blue-800 opacity-70 md:truncate">
          {{ genre.name }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// getPopularGenres
export default {
  data() {
    return {
      popularGenres: [
        {name: 'Crime', id: 1},
        {name: 'Thriller', id: 2},
        {name: 'Cooking', id: 3},
        {name: 'Kids', id: 4},
        {name: 'Psychology', id: 5},
      ]
    };
  },
};
</script>

<style>
</style>