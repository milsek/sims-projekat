<template>
  <div class="flex">
    <Sidebar v-on:component-change="updateComponent" />
    <BookExchange v-if="currentComponent=='exchange'"/>
    <BookReservations v-if="currentComponent=='reservations'" />
    <RegisterNew v-if="currentComponent=='register'"/>
    <AccountSettings v-if="currentComponent=='settings'"/>
  </div>
</template>

<script>
import Sidebar from '~/components/nav/Sidebar'
import BookExchange from '~/components/admin/exchange/BookExchange'
import RegisterNew from '~/components/admin/RegisterNew'
import AccountSettings from '~/components/admin/AccountSettings'
import BookReservations from "~/components/admin/BookReservations";
export default {
  layout: "admin",
  components: { Sidebar, BookExchange, BookReservations, RegisterNew, AccountSettings },
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
  }
};
</script>

<style>
</style>
