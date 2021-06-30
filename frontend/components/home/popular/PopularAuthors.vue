<template>
  <div class="px-8 md:px-2 text-center md:text-left">
    <div>
      <div class="text-lg font-medium tracking-wide text-blue-800 opacity-70">Authors</div>
    </div>
    <div class="pt-6 md:pt-8">
      <div v-for="author in popularAuthors" :key="author.id">
        <div @click="searchAuthor(author.name)"
        class="text-lg md:text-sm lg:text-base xl:text-lg font-light md:font-normal
         lg:font-light tracking-wide pb-1 text-blue-800 opacity-70 truncate cursor-pointer hover:opacity-100">{{author.name}}</div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  // getPopularAuthors
  data () {

    return {
      popularAuthors: []
    }
  },
  mounted() {
    this.getAuthors();
  },
  methods: {
    getAuthors() {
      axios.get("/api/topreads")
        .then(response => {
          response.data.forEach((x) =>
          {
            this.popularAuthors.push
            (
              {
                name: x.title.contributions[0].contributor.name,
                id: x.title.contributions[0].contributor.id
              }
            );
          }
          );
        });
      },
    searchAuthor(name) {
      this.$store.commit('search/update', name);
      this.$router.push('/search');
      this.$emit('refreshSearch');
    }
  }
}
</script>

<style>

</style>
