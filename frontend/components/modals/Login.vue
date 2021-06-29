<template>
  <!-- This example requires Tailwind CSS v2.0+ -->
  <div class="fixed z-10 inset-0 overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
    <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0 -mt-32 sm:-mt-0">
      <div @click.self="closeModal" class="fixed inset-0 bg-gray-700 bg-opacity-75 transition-opacity" aria-hidden="true"></div>

      <!-- This element is to trick the browser into centering the modal contents. -->
      <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>

      <!--
      Modal panel, show/hide based on modal state.

      Entering: "ease-out duration-300"
          From: "opacity-0 t  ranslate-y-4 sm:translate-y-0 sm:scale-95"
          To: "opacity-100 translate-y-0 sm:scale-100"
      Leaving: "ease-in duration-200"
          From: "opacity-100 translate-y-0 sm:scale-100"
          To: "opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
      -->
      <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl
       transform transition-all sm:my-8 sm:align-middle sm:max-w-lg w-11/12 sm:w-full">
        <div class="bg-white pt-5 pb-4 sm:p-6 sm:pb-4">
          
          <div class="mt-3 sm:mt-0">
            <h3 class="text-2xl text-center font-medium text-blue-700 opacity-70">
              Sign In
            </h3>
          </div>
          
        </div>

        <div class="block px-6 sm:px-12 sm:p-6">
          <div class="block">
            <div class="text-sm text-gray-600 font-bold">Email Address: </div>
            <input @keydown.enter="tryLogin" type="email" v-model="email" placeholder="Email Address"
            class="block border-b text-gray-700 border-solid border-gray-300 text-xl py-1 focus:outline-none w-full"/>
          </div>
          <div class="mt-6">
            <div class="text-sm text-gray-600 font-bold">Password: </div>
            <input @keydown.enter="tryLogin" type="password" v-model="password" placeholder="Password"
            class="block border-b text-gray-700 border-solid border-gray-300 text-xl py-1 focus:outline-none w-full"/>
          </div>
          <div class="mt-6">
            <div class="text-sm text-red-500 tracking-wide">{{ error }}</div>
          </div>
        </div>

      <div class="bg-gray-100 px-4 py-5 mt-8 sm:px-6 flex justify-end">
          <button @click="closeModal" type="button" class="inline-flex justify-center rounded-md shadow-sm px-6 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-300 focus:outline-none w-auto sm:text-sm">
          Cancel
          </button>
          <button @click="tryLogin" type="button" class="inline-flex justify-center rounded-md shadow-sm px-6 py-2 bg-blue-700 text-base font-medium text-white hover:bg-blue-900 focus:outline-none ml-3 w-auto sm:text-sm">
          Sign In
          </button>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
      email: '',
      password: '',
      error: ''
    }
  },
  methods: {
    closeModal () {
      this.error = ''
      this.$emit('close-modal')
    },
    tryLogin () {
      // simulates login
      let userdata = {}
      axios.get("/api/login/?mail=" + this.email + '&password=' + this.password)
      .then(response => {
        console.log(document.cookie)
        if (document.cookie)
        {
          this.$store.commit('session/update')
          this.$emit('close-modal')
        }
        else {
          console.log('Wrong credentials')
          this.error = 'Wrong credentials!'
        }
      })
      .catch(error => {
        console.log(error);
          console.log('Wrong credentials w/error')
          this.error = 'Wrong credentials w/error!'
      });


      var that = this
      setTimeout(function(){
        if (that.$route.path != '/')
          that.$router.push({name:'index'})
        else
          that.$forceUpdate()
      }, 1000)
    }
  }
}
</script>

<style>

</style>