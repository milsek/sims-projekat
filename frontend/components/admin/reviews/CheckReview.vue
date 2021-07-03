<template>
  <div>
    <div class="justify-center w-full text-center mx-auto py-10 px-10">
      <div class="text-4xl tracking-tight text-blue-900 opacity-70">Review Check</div>
      <div class="font-medium text-gray-500 mt-2">Approve/Decline reviews</div>
    </div>
    <div v-for="review in reviews" :key="review.id" class="p-4 xl:px-14 pb-1">
      <ReviewCard @values-changed="getReviews" v-bind:review="review" />
    </div>
  </div>
</template>

<script>
import ReviewCard from '~/components/admin/reviews/ReviewCard';
export default {
    components: {ReviewCard},
    data() {
        return {
            reviews: [
                {
                    id: 1
                },
                {
                    id: 2
                }
            ]
        }
    },
    methods: {
        getReviews() {
            console.log("HELLO I GOT HERE");
        }
    }

}
</script>

<style>

</style>