<template>
  <div class="md:pr-8">
    <div class="flex justify-between">
      <div class="text-lg font-medium tracking-wide text-blue-800 opacity-70">Popular books</div>
      <div class="text-lg font-medium tracking-wide text-blue-800 opacity-70">[Filter]</div>
    </div>
    
      
    <transition-group id="grid" name="slide" class="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 
      pt-10 -mx-2 lg:-mx-3" @change="booksToShow">
      <div v-for="book in booksToShow" :key="book.id">
          <BookCardSimple :data="book" />
      </div>
    </transition-group>

    <div v-if="popular.length" class="text-center mx-auto -mt-1">
      <button @click="showMore = !showMore" class="h-8 md:h-7 lg:h-8 px-6 sm:px-4 lg:px-6 pb-1 text-gray-400
      text-center text-lg md:text-base lg:text-lg focus:outline-none rounded-lg
      hover:transition duration-300 ease-in-out transform hover:-translate-y-1" v-text="showMore ? 'Show less' : 'Show more'"></button>
    </div>

  </div>
</template>

<script>
import axios from 'axios';
import BookCardSimple from "./BookCardSimple.vue"
export default {
  components: {BookCardSimple},
  data () {
    return {
      popular: [],
      showMore: false,
      slicer: 10
    }
  },
  mounted () {
    this.getBooks()
    this.resizeHandler()
    window.addEventListener("resize", this.resizeHandler)
  },
  methods: {
    getBooks() {
      axios.get("/api/topreads")
    .then(response => {
      this.popular = response.data;
    });
    },
    resizeHandler(e) {
      const el = document.getElementById("grid")
      const screenWidth = window.innerWidth|| document.documentElement.clientWidth|| document.body.clientWidth;
      if (el) {
        if (screenWidth > 1280) {
          this.slicer = 10
        }
        else {
          this.slicer = 12
        }
      }
      
    },
  },
  computed: {
    booksToShow () {
      if (this.showMore) {
        return this.popular
      } else {
        return this.popular.slice(0, this.slicer)
      }
    }
  }
};
</script>

<style>
.slide-enter-active {
  animation: slide-in .4s ease-out forwards;
}

.slide-leave-active {
  animation: slide-out .4s ease-out forwards;
}

@keyframes slide-in {
  from {
    transform: translateY(50px);
    opacity: 1;
  }

  to {
    transform: translateY(0);
  }
}

@keyframes slide-out {
  from {
    transform: translateY(0);
  }

  to {
    transform: translateY(50px);
    opacity: 0;
  }
}
</style>