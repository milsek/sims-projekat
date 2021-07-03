<template>
  <div class="block w-11/12 mt-6 mx-auto bg-white shadow-lg">
    <div class="block p-8 w-full">
      <div class="text-xl text-gray-700 tracking-wide font-medium">
          You've read this edition. Let others know what it is like.
      </div>

      <div class="flex space-x-1 mt-4">
        <div v-for="n in 5" :key="n">
          <span class='icon is-left w-2 h-2'
          :class="[focusStars >= n ? 'text-yellow-300 transition duration-300 ease-in-out transform -translate-y-2' : 'text-gray-300', 'cursor-pointer']"
          @click="selectedStars=n" @mouseover="hoveredStars=n" @mouseleave="hoveredStars=0">
            <font-awesome-icon :icon="['fas', 'star']"/>
          </span>
        </div>
      </div>

      <div class="w-full mt-2">
        <textarea class="w-full border border-solid border-gray-200 resize-none h-72 md:h-56
        text-lg text-thin text-gray-700 p-2 leading-tight rounded-md focus:outline-none focus:border-gray-300"
        v-model="reviewText" placeholder="Your review"></textarea>
      </div>

      <div class="flex justify-center md:justify-end mt-2">
        <div class="block justify-end">
          <button @click="tryLeaveReview" id="tooltip-target-1"
          :class="[!selectedStars ? 'bg-gray-600' : 'bg-blue-700 hover:bg-blue-900']" 
          class="h-9 mt-4 px-8 md:px-10
          text-white text-center text-lg shadow-md focus:outline-none rounded-lg">
            leave review
          </button>
          <div v-if="errorMsg && !selectedStars"  class="text-xs text-gray-400 text-center">{{ errorMsg }}</div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      selectedStars: 0,
      hoveredStars: 0,
      reviewText: '',
      errorMsg: ''
    }
  },
  methods: {
    tryLeaveReview () {
      if (!this.selectedStars) {
        this.errorMsg = 'Please choose a rating first' 
      }
      else {
        this.errorMsg = ''
        this.$emit('send-review', this.selectedStars, this.reviewText)
      }
      
    }
  },
  computed: {
    focusStars () {
      return this.hoveredStars > 0 ? this.hoveredStars : this.selectedStars
    }
  }
}
</script>

<style>

</style>