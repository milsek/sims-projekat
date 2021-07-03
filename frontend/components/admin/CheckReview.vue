<template>
    <div>
        <div v-for="review in reviews" :key="review.id">
            <ReviewCard v-bind:review="review" />
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
    }

}
</script>

<style>

</style>