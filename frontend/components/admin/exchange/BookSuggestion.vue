<template>
  <div class="block cursor-pointer hover:bg-gray-100 px-2 py-2" @click="selected">
    <div class="flex">
      <div class="my-auto w-14">{{ id }}</div>
      <div class="my-auto">{{ title }}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["id", "title"],
  methods: {
    selected() {
      this.$emit('book-is-selected', { id : this.id, title: this.title });
    }
  }
}
</script>