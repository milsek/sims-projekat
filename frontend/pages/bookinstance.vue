<template>
  <div class="w-full min-h-screen py-16" :class="[dataLoaded ? 'bg-gradient-to-bl from-blue-100 to-blue-300' : 'bg-white']">
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
import BookContent from "~/components/book/BookContent.vue"
import getBookInstance from "~/composables/getBookInstance"
export default {
  components: { BookContent },
  data () {
    return {
      data: {},
      dataLoaded: true,
      isMounted: false
    }
  },
  mounted() {
    this.data = getBookInstance(this.$route.params.id)
    if (!this.data) this.dataLoaded = false
    this.isMounted = true
    console.log(this.dataLoaded)
  }
};
</script>

<style>

</style>