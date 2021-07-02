<template>
  <div class="block md:flex mx-auto rounded-xl border border-dashed border-gray-300 p-6 my-1">
	<div class="flex-shrink-0">
		<div class="text-center sm:text-left rounded-mdw-36
					lg:w-24 h-56 lg:h-60 object-fill mx-auto">
			<img :src="'https://i.pravatar.cc/150?u=' + review.id" >
		</div>
		
	</div>

	<div class="md:pl-3 text-center md:text-left">
	  <div class="text-xl md:text-base lg:text-xl font-bold text-blue-900 opacity-90
	   line-clamp-2 mt-4 md:mt-0">
		Ime i prezime
	  </div>
	  <div class="text-xs tracking-wide text-gray-400 line-clamp-2">
		Korisnik
	  </div>

	  <div class="mt-2 font-medium tracking-wide text-base text-gray-700">
		  	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
	  </div>
	</div>
  </div>
</template>

<script>
export default {
	props: ["review"],
	methods: {

	},
	mounted() {
		console.log(this.review);
	}
}
</script>

<style>

</style>