<template>
  <div>
    <div class="flex w-11/12 mx-auto">
      <div class="block bg-white shadow-lg w-full">
        <div class="block md:flex p-8 pt-12 md:pt-8 border-b border-solid border-gray-200">
          <div class="min-w-64 w-64 object-cover text-center md:text-left m-auto md:m-0">
            <img :src="data.imageLarge" alt="" class="w-full">
          </div>

          <div class="text-center md:text-left w-full mt-6 md:mt-0 md:w-3/5 ml-0 md:ml-6">
            <div class="text-4xl text-gray-700 font-medium">{{data.title}}</div>
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

    <div class="block w-11/12 mt-6 mx-auto bg-white shadow-lg">
      <div class="block p-8 w-full">
        <div class="text-xl text-gray-700 tracking-wide font-medium">Details</div>
          <div class="grid grid-rows-flow sm:grid-rows-5 lg:grid-rows-4 sm:grid-flow-col sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 text-sm font-medium text-gray-700 pt-2">
            <div v-for="att in attributes" :key="att.name" class="truncate">
              <div class="pt-2">{{att.text}}: <span class="font-normal text-gray-600 truncate"> {{getData(att)}}</span></div>
            </div>
          </div>
      </div>
    </div>

    <div class="block w-11/12 mt-6 mx-auto bg-white shadow-lg">
      <div class="block p-8 w-full">
        <div class="text-xl text-gray-700 tracking-wide font-medium">Related editions</div>
        <RelatedEditions />
      </div>
    </div>
    
    <LeaveReview v-if="userCanReview" @send-review="sendReview" />
    <ReviewConfirmationModal v-if="showReviewConfirmation" @close-modal="closeReviewConfirmationModal" />

    <ReviewList :data="data"/>

  </div>

</template>

<script>
import RelatedEditions from '~/components/book/RelatedEditions'
import ReviewList from '~/components/book/ReviewList'
import LeaveReview from '~/components/book/LeaveReview'
import ReviewConfirmationModal from '~/components/book/ReviewConfirmationModal.vue'
import axios from 'axios'
export default {
  props: ["data"],
  components: { RelatedEditions, ReviewList, LeaveReview, ReviewConfirmationModal },
  data () {
    return {
      showMore: false,
      attributes: [
        { name: 'language', text: 'Language' },
        { name: 'pageCount', text: 'Pages' },
        { name: 'dimensions', text: 'Dimensions' },
        { name: 'year', text: 'Publication date' },
        { name: 'publisherName', text: 'Publisher' },
        { name: 'id', text: 'MySBN' },
        { name: 'takeOut', text: 'Can be taken out' },
      ],
      showReviewConfirmation: false,
      userCanReview: false,
    }
  },
  mounted() {
    // axios
    // .get("/api/edition-reviews?editionId=" + this.data.id)
    // .then(x => {
    //   console.log(x);
    //   this.data.reviews = x.data;
    //   this.reviews = true;
    // })
    // .catch()
    // console.log("BRAAAAAAAAA");
    // let user_id = document.cookie.split(";")[1].split("=")[1];
    // axios
    // .get("/api/user-can-review?editionId=" + this.data.id + "&userId=" + user_id)
    // .then(x => { this.userCanReview = x.data; console.log(x.data); })
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
    getData (att) {
      if (att.name === "language") return 'English'
      if (att.name === "takeOut") return this.data[att.name] ? 'Yes' : 'No'
      else if (att.name === "publisher") return this.data[att.name]["name"]
      else if (att.name === "dimensions") {
        const h = Math.round(this.data.dimensions / 1.61 * 10)
        const w = Math.round(this.data.dimensions * 10)
        const g = Math.round(this.data.pageCount / (Math.random()*10 + 10))
        return h.toString() + ' x ' + w.toString() + ' x ' + g.toString() + 'mm'
      }
      else return this.data[att.name]
    },
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
      // post request
    },
    closeReviewConfirmationModal() {
      this.showReviewConfirmation = false
    },
  }
};
</script>

<style>
</style>
