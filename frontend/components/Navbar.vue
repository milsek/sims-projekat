<!-- This example requires Tailwind CSS v2.0+ -->
<template>
  <div>
    <div class="sm:items-stretch sm:justify-start w-full">
      <div class="absolute left-0 flex items-center sm:hidden p-3">
        <!-- Mobile menu button-->
        <button @click="handleNavbarDropdown()" class="inline-flex items-center focus:outline-none justify-center ml-2 p-2 rounded-md focus:ring-2 focus:ring-inset focus:ring-white">
          <span class="sr-only">Open main menu</span>
          <img src="~/assets/icons/menu1.png" v-if="!navbarOpen" aria-hidden="true" class="block h-6 w-6"/>
          <img src="~/assets/icons/xsm.png" v-else aria-hidden="true" class="block h-6 w-6"/>
        </button>
      </div>

      <div class="grid grid-cols-7 gap-4 sm:flex-initial sm:items-stretch sm:justify-start w-full">
        <div class="hidden sm:flex p-5 col-start-1 col-span-3">
          <div class="flex space-x-6" >
              <nuxt-link v-for="item in navigation" :key="item.name" :to="item.route">
                <span :class="[item.route === $route.path ? 'text-blue-500' : 'text-gray-400 hover:text-indigo-500',
                'px-6 lg:px-8 py-2 rounded-md align-middle text-xs lg:text-sm font-medium tracking-wider font-sans']"
                :aria-current="item.current ? 'page' : undefined">{{item.name}}</span>
              </nuxt-link>
          </div>
        </div>

        <div class="flex flex-grow-0 my-auto mx-auto col-start-4">
          <nuxt-link to="/">
            <div class="flex justify-center align-middle">
              <img :src="require('~/assets/images/library-logo.svg')" alt="" class="hidden md:flex w-10 h-10 lg:w-12 lg:h-12">
              <img :src="require('~/assets/images/library-logo.svg')" alt=""  class="block sm:hidden w-10 h-10 my-3">
            </div>
          </nuxt-link>
        </div>

        <!-- <div class="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
        <button class="bg-gray-800 p-1 rounded-full text-gray-400 hover:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-800 focus:ring-white">
          <span class="sr-only">View notifications</span>
          <BellIcon class="h-6 w-6" aria-hidden="true" />
        </button> -->
       
      <div class="flex justify-center items-center col-start-7 mr-4 md:mr-0">
        <div x-data="{ profileOpen: false }" class="flex justify-center items-center">
            <div class="relative border-b-4 border-transparent py-2" :class="{'border-indigo-300 transform transition duration-300 ': profileOpen}" x-transition:enter-end="transform opacity-100 scale-100" x-transition:leave="transition ease-in duration-75" x-transition:leave-start="transform opacity-100 scale-100">
              <div @click="handleProfileDropdown()" class="flex justify-center items-center space-x-3 cursor-pointer">
                <div class="w-9 h-9 md:w-10 md:h-10 rounded-full overflow-hidden">
                  <img :src="require('~/assets/icons/user.png')" alt="" class="w-full h-full object-cover">
                </div>
              </div>
              <div v-if="profileOpen" v-click-outside="disableProfileDropdown" x-transition:enter="transition ease-out duration-100" x-transition:enter-start="transform opacity-0 scale-95" x-transition:enter-end="transform opacity-100 scale-100" x-transition:leave="transition ease-in duration-75" x-transition:leave-start="transform opacity-100 scale-100" x-transition:leave-end="transform opacity-0 scale-95" class="absolute w-60 px-5 py-3 right-0 dark:bg-gray-800 bg-white rounded-lg shadow border dark:border-transparent mt-5">
                <ul class="space-y-3 dark:text-white">
                  <li class="font-medium">
                    <a href="#" class="flex items-center transform transition-colors duration-200 border-r-4 border-transparent hover:border-indigo-300">
                      Account
                    </a>
                  </li>
                  <li class="font-medium">
                    <a href="#" class="flex items-center transform transition-colors duration-200 border-r-4 border-transparent hover:border-indigo-300">
                      Settings
                    </a>
                  </li>
                  <hr class="dark:border-gray-700">
                  <li class="font-medium">
                    <a href="#" class="flex items-center transform transition-colors duration-200 border-r-4 border-transparent hover:border-red-600">
                      Sign Out
                    </a>
                  </li>
                </ul>
              </div>
            </div>
        </div>
      </div>

      </div>
    </div>

    <div v-if="navbarOpen && !profileOpen" v-click-outside="disableNavbarDropdown" class="absolute w-full z-50 sm:hidden p-1">
      <div class="bg-white rounded-lg shadow p-2">
        <nuxt-link v-for="item in navigation" :key="item.name" :to="item.route" @click="navbarOpen=false" class="space-y-10">
          <span :class="[item.route === $route.path ? 'bg-white text-indigo-400' : 'bg-white text-gray-900 bg-opacity-75', 
          'block mx-3 pl-1 py-3 font-medium transform transition-colors duration-200 border-r-4 border-transparent hover:border-indigo-300']"
          :aria-current="item.current ? 'page' : undefined">{{ item.name }}</span>
        </nuxt-link>
      </div>
    </div>

  </div>
</template>

<script>
import Vue from 'vue'
import vClickOutside from 'v-click-outside'
 
Vue.use(vClickOutside)
export default {
  data () {
    return {
      navigation: [
        { name: 'Home', route: '/'},
        { name: 'About', route: '/about' },
        { name: 'Contact', route: '/contact'},
      ],
      navbarOpen: false,
      profileOpen: false
    }
  },
  methods: {
    handleNavbarDropdown() {
      if (!this.profileOpen) this.navbarOpen=!this.navbarOpen
    },
    handleProfileDropdown() {
      if (!this.navbarOpen) this.profileOpen=!this.profileOpen
    },
    disableProfileDropdown() {
      this.profileOpen = false
    },
    disableNavbarDropdown() {
      this.navbarOpen = false
    },
    
  }
};
</script>