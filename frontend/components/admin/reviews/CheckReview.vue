<template>
  <div>
    <div class="justify-center w-full text-center mx-auto py-10 px-10">
      <div class="text-4xl tracking-tight text-blue-900 opacity-70">Review Check</div>
      <div class="font-medium text-gray-500 mt-2">Approve/Decline reviews</div>
    </div>
    <div v-for="review in reviews" :key="review.id" class="p-4 xl:px-14 pb-1">
      <ReviewCard @values-changed="removeReview" v-bind:review="review" />
    </div>
    
    <div class="text-center mx-auto mt-8">
      <button @click="showMore" class="h-10 md:h-7 lg:h-8 px-6 sm:px-4 lg:px-6 pb-1 text-gray-800
      text-center text-lg md:text-base lg:text-2xl mb-12 focus:outline-none rounded-lg
      hover:transition duration-300 ease-in-out transform hover:-translate-y-1"
      v-text="pageIndex + 1 == totalPages ? 'Show less' : 'Show more'"></button>
    </div>

  </div>
</template>

<script>
import ReviewCard from '~/components/admin/reviews/ReviewCard';
import axios from 'axios';
export default {
    components: {ReviewCard},
    data() {
        return {
              reviews: [],
              pageIndex: 0,
              resultsPerPage: 8,
              showPagination: false,
              totalPages: 1,
              maxVisibleButtons: 3,
              loadMessage: 'Loading...'
        }
    },
    methods: {
      getReviews() {
        axios
        .get("/api/unchecked-reviews?page=" + this.pageIndex + "&amount=" + this.resultsPerPage)
        .then(x => {
          this.reviews.push(...x.data["content"]);
          this.totalPages = x.data["totalPages"];
        })
        .catch()
      },
      showMore() {
        if (this.pageIndex + 1 == this.totalPages) {
          window.scrollTo({ top: 0, behavior: 'smooth' });
          this.reviews = this.reviews.slice(0, this.resultsPerPage);
          this.pageIndex = 1;
        } else {
          this.pageIndex++;
          this.getReviews();
        }
      },
      removeReview (id) {
        console.log(id)
        console.log("BEFORE")
        console.log(this.reviews)
        this.reviews = this.reviews.filter(review => review.id != id)
        console.log("AFTER")
        console.log(this.reviews)
      }
    },
    mounted() {
      this.getReviews();
    }

}
</script>

<style>

</style>