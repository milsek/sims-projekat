<template>
  <div v-if="related.length" class="pb-10 mt-6 px-6 lg:px-8">
    <div class="px-4 hidden lg:block">
      <VueSlickCarousel :arrows="true" :dots="false" :slidesToShow="3">
        <div v-for="book in related" :key="book.title.title">
          <RelatedEditionCard :data="book" class="px-2"/>
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
        <div  v-for="book in related" :key="book.title.title">
          <RelatedEditionCard :data="book" class="px-2"/>
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
        <div  v-for="book in related" :key="book.title.title">
          <RelatedEditionCard :data="book" class="px-2"/>
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
import RelatedEditionCard from './RelatedEditionCard.vue'
import VueSlickCarousel from 'vue-slick-carousel'
import 'vue-slick-carousel/dist/vue-slick-carousel.css'
import 'vue-slick-carousel/dist/vue-slick-carousel-theme.css'
export default {
  components: { VueSlickCarousel, RelatedEditionCard },
  data () {
    return {
      related: [],
    }
  },
  mounted () {
    this.getBooks()
  },
  methods: {
    getBooks() {
      axios.get("/api/topten")
    .then(response => {
      this.related = response.data;
      // this.related.forEach((x) => { console.log("HELLO"); console.log(); });
    });
    }
  },
};
</script>

<style>
</style>
