<template>
  <div class="block md:flex mx-auto rounded-xl border border-dashed border-gray-300 p-10 my-1">
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
        Ut tortor pretium viverra suspendisse potenti nullam ac tortor vitae. Senectus et netus et malesuada fames ac turpis egestas maecenas. Ultricies integer quis auctor elit sed vulputate. Sed adipiscing diam donec adipiscing tristique risus. Massa tincidunt dui ut ornare. Pharetra pharetra massa massa ultricies mi. Integer feugiat scelerisque varius morbi enim. Ipsum a arcu cursus vitae. Tellus in metus vulputate eu scelerisque felis. Lacus laoreet non curabitur gravida arcu ac tortor dignissim. Egestas sed sed risus pretium quam vulputate dignissim. Magna fermentum iaculis eu non diam phasellus vestibulum. Bibendum ut tristique et egestas. Massa sapien faucibus et molestie. Est ullamcorper eget nulla facilisi etiam. Eget velit aliquet sagittis id consectetur purus ut faucibus pulvinar. Aenean pharetra magna ac placerat.
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