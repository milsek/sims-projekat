<template>
  <div v-if="showReviews" class="block w-11/12 mt-6 mx-auto bg-white shadow-lg">
    <div class="block p-8 w-full">
      <div class="text-xl text-gray-700 tracking-wide font-medium">Reviews</div>
      <div class="options pb-2 mt-4 space-y-4">
        <div v-for="item in data.reviews" :key="item.id">
          <ReviewCard v-bind:review="item"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import ReviewCard from '~/components/book/ReviewCard'
export default {
  props: ['data'],
  components: { ReviewCard },
  data () {
    return {
      showReviews: false,
    }
  },
  mounted() {
    this.getReviews()
  },
  methods: {
    getReviews () {
      axios
      .get("/api/edition-reviews?editionId=" + this.data.id)
      .then(response => {
        this.data.reviews = response.data;
        if (this.data.reviews.length) {
          this.showReviews = true;
        }
      })
      .catch()
    }
  }
}
</script>

<style>

</style>