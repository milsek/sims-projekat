<template>
  <div class="px-14 flex text-lg font-medium bg-indigo-900">
      <div> {{ id }} </div>
      <div> {{ name }} </div>
      <div> {{ surname }} </div>
  </div>
</template>

<script>
export default {
    props: ["id", "name", "surname"]
};
</script>

<style>
</style>