<template>
    <div>
        <div v-for="review in reviews" :key="review.id" class="p-4 pb-1">
            <ReviewCard @values-changed="getReviews" v-bind:review="review" />
        </div>
    </div>
</template>

<script>
import ReviewCard from '~/components/admin/ReviewCard';
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