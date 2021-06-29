<template>
  <div class="flex">
    <Sidebar v-on:component-change="updateComponent" />
    <BookExchange v-if="currentComponent=='exchange'"/>
    <RegisterNew v-if="currentComponent=='register'"/>
    <AccountSettings v-if="currentComponent=='settings'"/>
  </div>
</template>

<script>
import Sidebar from '~/components/nav/Sidebar'
import BookExchange from '~/components/admin/BookExchange'
import RegisterNew from '~/components/admin/RegisterNew'
import AccountSettings from '~/components/admin/AccountSettings'
export default {
  layout: "admin",
  components: { Sidebar, BookExchange, RegisterNew, AccountSettings },
  data () {
    return {
      currentComponent: 'exchange'
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