<template>
<div class="border border-dashed border-gray-400 rounded-lg ">
  <div class="block p-4">
    <div class="flex text-indigo-700 opacity-90 text-2xl font-medium">{{ name }} {{ surname }}</div>
    <div class="text-md font-medium tracking-wide text-gray-600">User ID: {{ id }} </div>
  </div>
</div>
</template>

<script>
export default {
  props: ["id", "name", "surname"]
};
</script>

<style>
</style>