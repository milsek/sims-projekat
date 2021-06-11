<template>
  <div class="px-6 sm:px-10 lg:px-14 border-b pb-4  border-gray-100 border-solid">
    <div class="px-4 hidden lg:block">
      <VueSlickCarousel :arrows="true" :dots="false" :slidesToShow="3">
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
import VueSlickCarousel from 'vue-slick-carousel'
import 'vue-slick-carousel/dist/vue-slick-carousel.css'
import 'vue-slick-carousel/dist/vue-slick-carousel-theme.css'
import BookCardDetailed from './BookCardDetailed.vue'
export default {
  components: { VueSlickCarousel, BookCardDetailed },
  data () {
    return {
      suggested: [
        {title: "Book Title 1 aaaaa",
        author: "Author Name",
        reads: 12000,
        rating: 4.5,
        image: "book-placeholder2.png",
        id: 1,
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempor metus ex, sollicitudin sagittis nisi posuere nec. Phasellus pharetra, sapien vitae lacinia porta, lacus sem egestas sapien, non tempor odio orci at elit. "},
        {title: "Thus Spoke Zarathustra",
        author: "Friedrich Nietzsche",
        reads: 352630,
        rating: 5.0,
        image: "book-placeholder2.png",
        id: 2,
        description: "Nietzsche was one of the most revolutionary and subversive thinkers in Western philosophy, and Thus Spoke Zarathustra remains his most famous and influential work. It describes how the ancient Persian prophet Zarathustra descends from his solitude in the mountains to tell the world that God is dead and that the Superman, the human embodiment of divinity, is his successor."},
        {title: "Book Title 3",
        author: "Author Name",
        reads: 21000,
        rating: 4.6,
        image: "book-placeholder2.png",
        id: 3,
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempor metus ex, sollicitudin sagittis nisi posuere nec. Phasellus pharetra, sapien vitae lacinia porta, lacus sem egestas sapien, non tempor odio orci at elit."},
        {title: "Book Title 4",
        author: "Author Name",
        reads: 34000,
        rating: 4.1,
        image: "book-placeholder2.png",
        id: 4,
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempor metus ex, sollicitudin sagittis nisi posuere nec. Phasellus pharetra, sapien vitae lacinia porta, lacus sem egestas sapien, non tempor odio orci at elit."},
        {title: "Book Title 5",
        author: "Author Name",
        reads: 27000,
        rating: 4.7,
        image: "book-placeholder2.png",
        id: 5,
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempor metus ex, sollicitudin sagittis nisi posuere nec. Phasellus pharetra, sapien vitae lacinia porta, lacus sem egestas sapien, non tempor odio orci at elit."},
        {title: "Book Title 6",
        author: "Author Name",
        reads: 16000,
        rating: 4.4,
        image: "book-placeholder2.png",
        id: 6,
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempor metus ex, sollicitudin sagittis nisi posuere nec. Phasellus pharetra, sapien vitae lacinia porta, lacus sem egestas sapien, non tempor odio orci at elit."},
        {title: "Book Title 7",
        author: "Author Name",
        reads: 17000,
        rating: 4.3,
        image: "book-placeholder.png",
        id: 7,
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempor metus ex, sollicitudin sagittis nisi posuere nec. Phasellus pharetra, sapien vitae lacinia porta, lacus sem egestas sapien, non tempor odio orci at elit."},
      ],
    }
  }
};
</script>

<style>
</style>