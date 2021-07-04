<template>
  <div class="flex min-h-screen">
    <Sidebar v-on:component-change="updateComponent" />
    <BookExchange v-if="currentComponent=='exchange'" class="md:ml-80"/>
    <BookReservations v-if="currentComponent=='reservations'" class="md:ml-80"/>
    <BookSearch v-if="currentComponent=='search'" class="md:ml-80" />
    <CheckReview v-if="currentComponent=='reviews'" class="md:ml-80" />
    <RegisterNew v-if="currentComponent=='register'" class="md:ml-80"/>
    <AccountSettings v-if="currentComponent=='settings'" class="md:ml-80"/>
  </div>
</template>

<script>
import Sidebar from '~/components/nav/Sidebar'
import BookExchange from '~/components/admin/exchange/BookExchange'
import RegisterNew from '~/components/admin/registration/RegisterNew'
import AccountSettings from '~/components/admin/AccountSettings'
import BookReservations from "~/components/admin/reservations/BookReservations"
import CheckReview from "~/components/admin/reviews/CheckReview"
import BookSearch from '../components/admin/search/BookSearch.vue'

export default {
  layout: "admin",
  components: { Sidebar, BookExchange, BookSearch, BookReservations, RegisterNew, AccountSettings, CheckReview },
  data () {
    return {
      currentComponent: 'exchange'
    }
  },
  beforeCreate () {
    if (this.$store.state.session.role != '1') {
      this.$router.push('/404')
    }
  },
  methods: {
    updateComponent (componentName) {
      this.currentComponent = componentName
    }
  },
  head () {
    return {
      title: this.$route.meta.title
    }
  },
};
</script>

<style>
</style>
