<template>
  <div class="px-8 md:px-20 pt-16">
    <div class="flex justify-between">
      <div class="text-lg font-medium tracking-wide text-blue-800 opacity-70">Popular books</div>
      <div class="text-lg font-medium tracking-wide text-blue-800 opacity-70">[Filter]</div>
    </div>
    <div class="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 xl:grid-cols-5 pt-10 -mx-2 lg:-mx-3">
      <div v-for="book in suggested" :key="book.id">
        <BookCardSimple :data="book" />
      </div>
    </div>
  </div>
</template>

<script>
import BookCardSimple from "./BookCardSimple.vue"
export default {
  components: {BookCardSimple},
  data () {
    return {
      suggested: [
        {title: "Aion",
        author: "C.G. Jung",
        reads: 267400,
        rating: 4.7,
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
        {title: "The Ego and the Id",
        author: "Sigmund Freud",
        reads: 268000,
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
        image: "book-placeholder2.png",
        id: 7,
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempor metus ex, sollicitudin sagittis nisi posuere nec. Phasellus pharetra, sapien vitae lacinia porta, lacus sem egestas sapien, non tempor odio orci at elit."},
        {title: "Book Title 8",
        author: "Author Name",
        reads: 12623,
        rating: 4.3,
        image: "book-placeholder2.png",
        id: 8,
        description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempor metus ex, sollicitudin sagittis nisi posuere nec. Phasellus pharetra, sapien vitae lacinia porta, lacus sem egestas sapien, non tempor odio orci at elit."},
      ],
    }
  }
};
</script>

<style>
</style>