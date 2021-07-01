<template>
  <div class="block cursor-pointer hover:bg-gray-100 px-2 py-2" @click="selected">
    <div class="flex">
      <div class="my-auto w-14">{{ id }}</div>
      <div class="my-auto">{{ name }}</div>
      <div class="my-auto ml-2">{{ surname }}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["name", "surname", "id"],
  methods: {
    selected() {
      console.log("Emit");
      this.$emit('user-is-selected', { id : this.id, name: this.name, surname: this.surname });
      //
    }
  }
}
</script>