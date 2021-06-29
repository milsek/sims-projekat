<template>
  <div v-if="suggested.length" class="px-6 pb-10 sm:-mt-20 md:-mt-24 sm:px-10 lg:px-14 border-b border-gray-200 border-solid">
    <div class="px-4 hidden lg:block">
      <VueSlickCarousel :arrows="true" :dots="false" :slidesToShow="3">
        <div v-for="book in suggested" :key="book.title">
          <BookCardDetailed :data="book" class="px-2"/>
        </div>

        <template #prevArrow="arrowOption">
          <img :src="require('~/assets/icons/left-arrow.svg')" alt="">
          <div class="custom-arrow">
            {{ arrowOption.currentSlide }}/{{ arrowOption.slideCount }}
          </div>
        </template>
        <template #nextArrow="arrowOption">
          <img :src="require('~/assets/icons/right-arrow.svg')" alt="">
          <div class="custom-arrow">
            {{ arrowOption.currentSlide }}/{{ arrowOption.slideCount }}
          </div>
        </template>
      </VueSlickCarousel>
    </div>

    <div class="hidden sm:block lg:hidden px-4">
      <VueSlickCarousel :arrows="true" :dots="false" :slidesToShow="2">
        <div  v-for="book in suggested" :key="book.title">
          <BookCardDetailed :data="book" class="px-2"/>
        </div>

        <template #prevArrow="arrowOption">
          <img :src="require('~/assets/icons/left-arrow.svg')" alt="">
          <div class="custom-arrow">
            {{ arrowOption.currentSlide }}/{{ arrowOption.slideCount }}
          </div>
        </template>
        <template #nextArrow="arrowOption">
          <img :src="require('~/assets/icons/right-arrow.svg')" alt="">
          <div class="custom-arrow">
            {{ arrowOption.currentSlide }}/{{ arrowOption.slideCount }}
          </div>
        </template>
      </VueSlickCarousel>
    </div>

    <div class="sm:hidden px-4">
      <VueSlickCarousel :arrows="true" :dots="false" :slidesToShow="1">
        <div  v-for="book in suggested" :key="book.title">
          <BookCardDetailed :data="book" class="px-2"/>
        </div>

        <template #prevArrow="arrowOption">
          <img :src="require('~/assets/icons/left-arrow.svg')" alt="">
          <div class="custom-arrow">
            {{ arrowOption.currentSlide }}/{{ arrowOption.slideCount }}
          </div>
        </template>
        <template #nextArrow="arrowOption">
          <img :src="require('~/assets/icons/right-arrow.svg')" alt="">
          <div class="custom-arrow">
            {{ arrowOption.currentSlide }}/{{ arrowOption.slideCount }}
          </div>
        </template>
      </VueSlickCarousel>
    </div>

  </div>

</template>

<script>
import axios from 'axios';
import BookCardDetailed from './BookCardDetailed.vue'
import VueSlickCarousel from 'vue-slick-carousel'
import 'vue-slick-carousel/dist/vue-slick-carousel.css'
import 'vue-slick-carousel/dist/vue-slick-carousel-theme.css'
export default {
  components: { VueSlickCarousel, BookCardDetailed },
  data () {
    return {
      suggested: [],
    }
  },
  mounted () {
    this.getBooks()
  },
  methods: {
    getBooks() {
      axios.get("/api/topten")
    .then(response => {
      this.suggested = response.data;
      this.suggested.forEach((x) => { console.log("HELLO"); console.log(); });
      console.log(this.suggested);
    });
    }
  },
};
</script>

<style>
</style>
