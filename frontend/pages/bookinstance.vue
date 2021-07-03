<template>
  <div class="w-full py-16 flex flex-col flex-1 m-0" :class="[dataLoaded && isMounted ? 'bg-gradient-to-bl from-blue-100 to-blue-300' : 'bg-white']">
    <div v-if="dataLoaded" class="">
      <div v-if="isMounted">
        <BookContent :data="data"/>
      </div>
      <div v-else class="w-3/4 xl:w-1/2 mx-auto">
        <h1 class="text-center text-4xl sm:text-5xl lg:text-6xl text-gray-900 font-light font-semi mt-52 border-b border-gray-300 py-4">Loading.</h1>
      </div>
    </div>
    <div v-else class="w-3/4 xl:w-1/2 mx-auto">
      <h1 class="text-center text-4xl sm:text-5xl lg:text-6xl text-gray-900 font-light font-semi mt-52 border-b border-gray-300 py-4">This page does not exist.</h1>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import BookContent from "~/components/book/BookContent.vue"
export default {
  components: { BookContent },
  data () {
    return {
      data: {},
      dataLoaded: true,
      isMounted: false
    }
  },
  mounted () {
    this.getBooks()
  },
  methods: {
    getBooks() {
      axios.get(`/api/edition/?id=${this.$route.params.id}`)
    .then(response => {
      this.data = response.data
      if (Object.keys(this.data).length === 0) this.dataLoaded = false
      // console.log(this.data.description.split(/\r\n|\r|\n/).length - 1)
      // this.data.description = this.data.description.replace(/(?:\r\n|\r|\n)/g, '<br>');
      this.isMounted = true
    });
    }
  },
  head () {
    return {
      title: this.$route.meta.title
    }
  },
};
</script>

<style>

</style>
