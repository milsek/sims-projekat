<template>
  <div class="flex z-0 rounded-xl border border-dashed border-gray-300 p-3 relative">

    <div class="flex flex-col text-center absolute opacity-0 hover:opacity-100 bg-opacity-80 justify-center
    inset-0 z-50 rounded-xl bg-gray-900">
      <div v-if="data.state === 'APPROVED'" class="my-auto align-middle">
        <button
          @click="lendBook"
          class="h-9 my-auto align-middle px-8 md:px-6 bg-white hover:bg-gray-200
          opacity-90 text-gray-900 text-center text-lg shadow-md focus:outline-none rounded-lg">
          lend book
        </button>
      </div>
    </div>

    <div class="flex-shrink-0">
      <img :src="data.editionImageLarge" alt="" class="text-left rounded-md
      w-28 h-40 object-fill" draggable="false"/>
    </div>

    <div class="pl-4 text-left">
      <div class="text-sm font-bold text-blue-900 opacity-90
      line-clamp-2">
        {{ data.editionTitleTitle }}
      </div>

      <div class="mt-2 text-sm font-medium tracking-wide text-gray-500 ">
        <div class="line-clamp-2">Reservation ID: {{ data.id }}</div>
        <div class="line-clamp-2">Edition ID: {{ data.editionId }}</div>
        <div class="line-clamp-2" v-if="data.bookId" >Book ID: {{ data.bookId }}</div>
        <div class="line-clamp-2" v-if="data.bookLineIsleName" >
          Isle: {{ data.bookLineIsleName }} - Row: {{ data.bookLineNumber }}
        </div>
        <div class="line-clamp-2">State: {{ data.state }}</div>
        <div class="line-clamp-2">Member: {{ data.userName }} {{ data.userSurname }}, {{data.userId}}</div>
      </div>

    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  props: ["data"],
  data() {
    return {
      showLending: false
    };
  },
  methods: {
    lendBook() {
      this.justReturned = false;
      let that = this;
      console.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
      console.log(this.data.bookId + "&userId=" + this.data.userId);
      axios
        .post("/api/take-book?bookId=" + this.data.id + "&userId=" + this.data.userId)
        .then(x => {
          that.data.state = "SEIZED";
          console.log("Book is taken.");
        })
        .catch(error => {
          console.log(error);
          console.log("Book is NOT taken");
        });
    }
  }
};
</script>

<style></style>
