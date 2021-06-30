<template>
  <div class="">
      <div class="inline"> {{id}} </div>
      <div class="inline"> {{name}} </div>
      <div class="inline"> {{surname}} </div>
  </div>
</template>

<script>
export default {
    props: ["id", "name", "surname"]
};
</script>

<style>
</style>