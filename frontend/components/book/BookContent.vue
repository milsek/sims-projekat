<template>
  <div>
    <div class="flex w-11/12 mx-auto">
      <div class="block bg-white shadow-lg w-full">
        <div class="block md:flex p-8 pt-12 md:pt-8 border-b border-solid border-gray-200">
          <div class="min-w-64 w-64 object-cover text-center md:text-left m-auto md:m-0">
            <img :src="data.imageLarge" alt="" class="w-full">
          </div>

          <div class="text-center md:text-left w-full mt-6 md:mt-0 md:w-3/5 ml-0 md:ml-6">
            <div class="text-4xl text-gray-700 font-medium">{{data.titleTitle}}</div>
            <div class="text-xl text-gray-700 font-thin">by {{data.authorName}}</div>
            <div class="text-sm text-gray-400 font-thin">{{data.publisherName}}</div>
            <div class="pb-0 inline-block font-thin align-middle text-sm text-gray-400 pt-2">Reads: {{readsInThousands}}</div>
            <div class="flow">
              <img :src="require(`@/assets/icons/star.svg`)" alt="" class="inline-block align-middle w-4 h-4 my-0" draggable="false">
              <p class="inline-block align-middle text-gray-500 my-0">{{data.rating}}</p>
            </div>

            <div>

              <div class="block lg:hidden mt-12 md:mt-0 pt-2 border-t border-solid border-gray-200 md:border-none">
                <div class="text-lg text-gray-700 font-medium tracking-wide font-base">Availability</div>
                <div :class="[data.availableCopies > 0 ? 'text-green-700' : 'text-red-600', 'mt-1']">
                  Books available: {{data.availableCopies}}
                </div>
                <div v-if="$store.state.session.role === '0'" class="pt-2">
                  <button class="h-8 mt-4 md:h-7 lg:h-8 px-6 sm:px-4 lg:px-6 pb-1 bg-indigo-800 hover:bg-indigo-900 text-white text-center text-lg md:text-base
                  lg:text-lg shadow-md focus:outline-none rounded-lg">
                    reservation
                  </button>
                </div>
              </div>

              <div class="hidden xl:block mt-4">
                <div class="text-justify font-light md:tracking-wide mb-4 text-gray-500" :class="[showMore ? '' : 'line-clamp-3 xl:line-clamp-5']" v-html="data.description"></div>
                <div class="text-center mx-auto -mt-1">
                  <button @click="showMore = !showMore" class="h-8 md:h-7 lg:h-8 px-6 sm:px-4 lg:px-6 pb-1 text-gray-400
                  text-center text-lg md:text-base lg:text-lg focus:outline-none rounded-lg
                  hover:transition duration-300 ease-in-out transform hover:-translate-y-1" v-text="showMore ? 'Show less' : 'Show more'"></button>
                </div>
              </div>
            </div>

          </div>

        </div>
      </div>

      <div class="hidden lg:block flex-shrink-0 lg:w-64 xl:w-72 bg-white shadow-lg ml-6 h-full align-bottom">
        <div class="block p-8">
          <div class="text-xl text-gray-700 tracking-wide font-medium">Availability</div>
          <div :class="[data.availableCopies > 0 ? 'text-green-700' : 'text-red-600', 'mt-1']">
            Books available: {{data.availableCopies}}
          </div>
          <div v-if="$store.state.session.role === '0'" class="pt-2">
            <button class="w-full h-8 mt-4 md:h-7 lg:h-8 px-6 sm:px-4 lg:px-6 pb-1 bg-indigo-800 text-white text-center text-lg md:text-base
            lg:text-lg shadow-md focus:outline-none hover:text-gray-900 rounded-lg">
              reservation
            </button>
          </div>
        </div>
      </div>

    </div>

    <div class="block xl:hidden w-11/12 mt-6 mx-auto bg-white shadow-lg">
      <div class="block p-8 w-full">
        <div class="text-xl text-gray-700 tracking-wide font-medium">Description</div>

        <div class="text-justify font-light md:tracking-wide mb-4 text-gray-500 pt-4"
        :class="[showMore ? '' : 'line-clamp-5']"
        v-html="data.description">
        </div>

        <div class="text-center mx-auto -mt-1">
          <button @click="showMore = !showMore" class="h-8 md:h-7 lg:h-8 px-6 sm:px-4 lg:px-6 pb-1 text-gray-400
          text-center text-lg md:text-base lg:text-lg focus:outline-none rounded-lg
          hover:transition duration-300 ease-in-out transform hover:-translate-y-1" v-text="showMore ? 'Show less' : 'Show more'"></button>
        </div>

      </div>
    </div>

    <Details :data="data" />
    <RelatedEditions />

    <LeaveReview v-if="userCanReview" @send-review="sendReview" />
    <ReviewConfirmationModal v-if="showReviewConfirmation" @close-modal="closeReviewConfirmationModal" />

    <ReviewList :data="data"/>

  </div>

</template>

<script>
import Details from '~/components/book/Details'
import RelatedEditions from '~/components/book/RelatedEditions'
import ReviewList from '~/components/book/ReviewList'
import LeaveReview from '~/components/book/LeaveReview'
import ReviewConfirmationModal from '~/components/book/ReviewConfirmationModal.vue'
import axios from 'axios'
export default {
  props: ["data"],
  components: { Details, RelatedEditions, ReviewList, LeaveReview, ReviewConfirmationModal },
  data () {
    return {
      showMore: false,
      showReviewConfirmation: false,
      userCanReview: false,
    }
  },
  mounted() {
    this.checkUserCanReview()
  },
  computed: {
    readsInThousands () {
      if (this.data.reads > 1000) {
        return Math.round(this.data.reads/1000) + "k"
      } else return this.data.reads
    },
  },
  methods: {
    checkUserCanReview() {
      let user_id = document.cookie.split(";")[1].split("=")[1];
      axios
      .get("/api/user-can-review?editionId=" + this.data.id + "&userId=" + user_id)
      .then(response => { this.userCanReview = response.data; console.log(response.data); })
    },
    sendReview (stars, text) {
      console.log('Stars: ', stars, '\nText: ', text)
      let user_id = document.cookie.split(";")[1].split("=")[1];
      //if success
      
      axios
      .post("/api/review?editionId=" + this.data.id, {
        content: text.trim(),
        bookReservationMemberId: user_id,
        rating: stars
      })
      .then(x => { this.showReviewConfirmation = true; console.log(x); })
      .catch()
    },
    closeReviewConfirmationModal() {
      this.showReviewConfirmation = false
    },
  }
};
</script>

<style>
</style>
