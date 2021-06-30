<template>
  <div class="block md:flex w-11/12 mx-auto rounded-lg bg-white shadow-lg justify-evenly">
    <div class=" mt-4 grid grid-cols-12 text-lg lg:text-xl gap-6 md:flex">
      <div class="col-start-1 col-span-5 my-aut">
        <MiniSearchBar what="user" how="search_user"/>
      </div>
      <div class="col-start-6 col-span-5 my-aut">
        <MiniSearchBar what="book" how="search_book" />
      </div>
    </div>
  </div>
</template>

<script>
import MiniSearchBar from "~/components/admin/MiniSearchBar";
export default {
  components: {MiniSearchBar}
}
</script>

<style>

</style>
