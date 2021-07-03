<template>
  <div class="block md:flex mx-auto rounded-xl border border-dashed border-gray-300 p-6">
    <div class="flex-shrink-0">
      <img :src="data.editionImageLarge" alt=""
      class="text-center sm:text-left rounded-md
      w-36 lg:w-40 h-56 lg:h-60 object-fill mx-auto" draggable="false">
    </div>
    <div class="md:pl-4 text-center md:text-left" v-if="data.bookState">
      <div class="text-xl md:text-base lg:text-xl font-bold text-blue-900 opacity-90
       line-clamp-2 mt-4 md:mt-0">
        {{ data.editionTitleTitle }}
      </div>
      <div class="text-xs tracking-wide text-gray-400 line-clamp-2">
        {{data.authorName}}
      </div>

      <div class="mt-2 text-sm font-medium tracking-wide text-gray-500 ">
        <div class="line-clamp-2">ID: {{data.id}}</div>
        <div class="line-clamp-2">Isle: {{data.lineIsleName}} - Row: {{data.lineNumber}}</div>
        <div class="line-clamp-2">Condition: <span :class="[colorCondition]" class="font-bold">{{ data.condition }}</span></div>
        <div class="line-clamp-2">State: {{ data.bookState }}</div>
      </div>
        
      <div v-if="data.bookState === 'IN_STOCK'" class="mt-5">
        <button @click="showUserModal = !showUserModal" class="h-9 mt-4 px-8 md:px-6 bg-blue-700 hover:bg-blue-900
         opacity-90 text-white text-center text-lg shadow-md focus:outline-none rounded-lg">
          lend book
        </button>
      </div>
      <div v-if="data.bookState === 'TAKEN'" class="mt-5">
        <button @click="markBookReturned" class="h-9 mt-4 px-8 md:px-6 bg-gray-700 hover:bg-gray-800
         opacity-90 text-white text-center text-lg shadow-md focus:outline-none rounded-lg">
          mark returned
        </button>
      </div>

      <div v-if="showUserModal">
        <UserModal @close-modal="closeModal" @lend-book="lendBook" ref="userModal"/>
      </div>

      <div v-if="justReturned">
        <BookReturnedModal @close-modal="closeReturnedModal" v-bind:id="data.id"/>
      </div>

    </div>
  </div>
</template>

<script>
import UserModal from '~/components/admin/exchange/UserModal'
import BookReturnedModal from '~/components/admin/exchange/BookReturnedModal'
import axios from 'axios';
export default {
  props: [ "data" ],
  components: { UserModal, BookReturnedModal },
  data () {
    return {
      showUserModal: false,
      justReturned: false,
      showReturnedModal: false
    }
  },
  methods: {
    closeModal () {
      this.showUserModal = false
    },
    closeReturnedModal () {
      this.justReturned = false
    },
    markBookReturned () {
      let that = this;
      axios
        .post("/api/return-book/?id=" + this.data.id)
        .then(x => {that.data.bookState = 'IN_STOCK'; that.justReturned = true; })
        .catch(error => {
          console.log("Book is NOT returned.")
        });
      console.log("Book is returned.")
    },
    lendBook(userId) {
      console.log('lending book to ', userId);
      this.justReturned = false;
      let that = this;
      axios
        .post("/api/take-book?bookId=" + this.data.id + "&userId=" + userId)
        .then(x => {
          that.data.bookState = 'TAKEN';
          console.log("Book is taken.");
          this.$refs.userModal.successfulLending(true);
        })
        .catch(error => {
          console.log(error);
          console.log("Book is NOT taken");
          this.$refs.userModal.successfulLending(false);
        })

      
      // success msg, true does closeModal, false shows an error msg 
      
    }
  },
  computed: {
    colorCondition () {
      switch (this.data.condition) {
        case 'UNUSABLE': return "text-red-600"
        case 'DAMAGED': return "text-damaged"
        case 'WORN_OUT': return "text-yellow-400"
        case 'PERFECT': return "text-green-600"
      }
    },
  }
};
</script>

<style>
</style>