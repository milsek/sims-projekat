<template>
  <div class="block">
    <div class="text-sm text-gray-500 font-medium">{{what.text}}</div>
    <input type="text" v-model="text" :placeholder="what.text"
    class="block rounded-md border text-gray-600 border-solid border-gray-200 text-lg
    mt-1 py-1 px-4 focus:outline-none focus:border-gray-300 w-full"/>
  </div>
</template>

<script>
export default {
  props: ['what'],
  data () {
    return {
    text: '',
    }
  },
  watch: {
    text: function (newText, oldText) {
      this.$emit('input-change', this.what.name, newText)
    }
  }
}
</script>

<style>

</style>