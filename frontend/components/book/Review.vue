<template>
  <div class="block rounded-xl border border-dashed border-gray-300 p-4 md:p-6">
    <div class="flex flex-shrink-0">
      
      <div class="align-middle object-fill">
        <img :src="'https://i.pravatar.cc/150?u=' + review.id" class="w-12 h-12 rounded-md">
      </div>
      
      <div class="block pl-4">
        <div class="-mt-1 text-lg md:text-xl font-bold text-blue-900 opacity-90
        line-clamp-2 ">Name Surname</div>
        
        <div class="flex">
          <div v-for="n in 5" :key="n">
            <span class='icon is-left w-2 h-2' :class="[rating >= n ? 'text-yellow-300' : 'text-gray-300']">
              <font-awesome-icon :icon="['fas', 'star']"/>
            </span>
          </div>
        </div>
      </div>
      
    </div>

    <div class="text-justify font-light md:tracking-wide mb-4 text-gray-500 pt-4 whitespace-pre-line"
        :class="[showMore ? '' : 'line-clamp-3']">
      Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
      Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
    </div>
    <div class="text-center mx-auto -mt-1">
      <button @click="showMore = !showMore" class="h-8 md:h-7 lg:h-8 px-6 sm:px-4 lg:px-6 pb-1 text-gray-400
      text-center text-base focus:outline-none rounded-lg
      hover:transition duration-300 ease-in-out transform hover:-translate-y-1" v-text="showMore ? 'Show less' : 'Show more'"></button>
    </div>
      
  </div>
</template>

<script>
export default {
	props: ["review"],
  data () {
    return {
      showMore: false,
      rating: Math.floor(Math.random()*5+1)
    }
  },
	methods: {

	},
	mounted() {
		console.log(this.review);
	}
}
</script>

<style>

</style>