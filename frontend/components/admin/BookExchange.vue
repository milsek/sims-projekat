<template>
  <div class="w-11/12 mx-auto rounded-lg bg-white shadow-lg md:block">
  
    <div class="w-7/12 mt-4  text-lg lg:text-xl gap-6 md:flex justify-around mx-auto">
      <div class="w-96 my-aut ">
        <MiniSearchBar v-on:userIsSelected="userIsSelected($event)" what="by book id" how="search_book"/>
      </div>
      
    </div>
  
    
    <div class=" lg:text-xl  md:flex mt-4 mx-2 flex-wrap">
      <div class="my-aut md:flex flex-wrap">
        <SelectedUser v-show="user_selected" v-bind:id=user_id v-bind:name=user_name v-bind:surname=user_surname />
      </div>
    </div>

  </div>
  
</template>

<script>
import MiniSearchBar from "~/components/admin/MiniSearchBar";
import SelectedUser from "~/components/admin/SelectedUser";
export default {
  components: {MiniSearchBar, SelectedUser},
  data() {
    return {
      user_selected : false,
      user_id : -1,
      user_name : "",
      user_surname: ""
    }
  },
  methods: {
    userIsSelected(data) {
      this.user_selected = true;
      this.user_id = data.id;
      this.user_name = data.name;
      this.user_surname = data.surname;
    }
  }
}
</script>

<style>

</style>
