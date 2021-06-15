<template>
  <div>
    <div class="flex w-11/12 mx-auto">
      <div class="block bg-white shadow-lg w-full">
        <div class="block md:flex p-8 pt-12 md:pt-8 border-b border-solid border-gray-200">
          <div class="min-w-60 w-60 object-cover text-center md:text-left m-auto md:m-0">
            <img :src="data.imageLarge" alt="" class="w-full">
          </div>

          <div class="text-center md:text-left w-full mt-6 md:mt-0 md:w-3/5 ml-0 md:ml-6">
            <div class="text-4xl text-gray-700 font-medium">{{data.title}}</div>
            <div class="text-xl text-gray-700 font-thin">by {{data.author.name}}</div>
            <div class="text-sm text-gray-400 font-thin">{{data.publisher.name}}</div>
            <div class="pb-0 inline-block font-thin align-middle text-sm text-gray-400 pt-2">Reads: {{readsInThousands}}</div>
            <div class="flow">
              <img :src="require(`@/assets/icons/star.svg`)" alt="" class="inline-block align-middle w-4 h-4 my-0" draggable="false">
              <p class="inline-block align-middle text-gray-500 my-0">{{data.rating}}</p>
            </div>

            <div>
              <div class="block lg:hidden mt-12 md:mt-0 pt-2 border-t border-solid border-gray-200 md:border-none">
                <div class="text-lg text-gray-700 font-medium tracking-wide font-base">Availability</div>
              </div>
              <div class="hidden lg:block mt-4">
                <div class="text-justify font-light md:tracking-wide mb-4 line-clamp-6 text-gray-500">{{ data.description }}</div>
              </div>
            </div>

          </div>

        </div>
      </div>

      <div class="hidden lg:block flex-shrink-0 lg:w-64 xl:w-72 bg-white shadow-lg ml-6">
        <div class="block p-8">
          <div class="text-xl text-gray-700 tracking-wide font-medium">Availability</div>
        </div>
      </div>

    </div>

    <div class="block lg:hidden w-11/12 mt-6 mx-auto bg-white shadow-lg">
      <div class="block p-8 w-full">
        <div class="text-xl text-gray-700 tracking-wide font-medium">Description</div>
        <div class="text-justify font-light md:tracking-wide mb-4 text-gray-500">
          {{ data.description }}
        </div>
      </div>
    </div>

    <div class="block w-11/12 mt-6 mx-auto bg-white shadow-lg">
      <div class="block p-8 h-52 w-full">
        <div class="text-xl text-gray-700 tracking-wide font-medium">Details</div>
      </div>
    </div>

  </div>
  
</template>

<script>
export default {
  props: ["data"],
  computed: {
    readsInThousands () {
      if (this.data.reads > 1000) {
        return Math.round(this.data.reads/1000) + "k"
      } else return this.data.reads
    }
  }
};
</script>

<style>
</style>