<template>
  <div class="block fixed">
    <div class="h-screen w-80 hidden md:block bg-gray-100">
      <div class="p-6 h-full">
        <div class="uppercase text-xs font-medium text-gray-400 tracking-wide pb-1
        border-b border-solid border-gray-300 truncate">
          {{$store.state.session.email}}
        </div>
        <!-- -------------------------------------------------------------------------------- -->
        <div class="mt-6 space-y-1">
          <div v-for="option in menuOptions" :key="option.text" @click="handleChoice(option.name)"
          :class="[currentActive == option.name ? 'bg-gray-200 text-gray-800' : 'text-gray-500',
          'flex text-lg px-4 py-2 tracking-wide rounded-lg cursor-pointer hover:bg-gray-300 hover:text-gray-800']">
            <span class='icon is-left w-8'>
              <font-awesome-icon :icon="['fas', option.icon]"/>
            </span>
            <div>{{option.text}}</div>
          </div>
        </div>
        <!-- -------------------------------------------------------------------------------- -->
        <div class="absolute bottom-6 space-y-1 ">

          <div @click="handleChoice('settings')"  :class="[currentActive == 'settings' ? 'bg-gray-200 text-gray-800' : 'text-gray-500',
          'flex text-lg px-4 py-2 tracking-wide rounded-lg cursor-pointer hover:bg-gray-300 hover:text-gray-800']">
            <span class='icon is-left w-8'>
              <font-awesome-icon :icon="['fas', 'user-cog']"/>
            </span>
            <div>Account Settings</div>
          </div>

          <div @click="$router.push('/') " class="flex text-gray-500 text-lg px-4 py-2
          tracking-wide rounded-lg cursor-pointer hover:bg-gray-300 hover:text-gray-800">
            <span class='icon is-left w-8'>
              <font-awesome-icon :icon="['fas', 'arrow-left']"/>
            </span>
            <div>Main Website</div>
          </div>

          <div @click="handleLogout()" class="flex text-gray-500 text-lg px-4 py-2 tracking-wide
          rounded-lg cursor-pointer hover:bg-gray-300 hover:text-gray-800 w-full">
            <span class='icon is-left w-8'>
              <font-awesome-icon :icon="['fas', 'sign-out-alt']"/>
            </span>
            <div>Sign Out</div>
          </div>

        </div>
      </div>
    </div>



    <!-- MOBILE NAVBAR -->
    <div class="z-40 h-screen absolute bg-gray-100 shadow md:h-full flex-col
     justify-between md:hidden transition duration-150 ease-in-out" id="mobile-nav">
        <div v-if="!sidebarOpen" class="h-10 w-10 bg-gray-800 absolute
        right-0 mt-16 -mr-10 flex items-center shadow rounded-tr rounded-br
        justify-center cursor-pointer" @click="handleSidebar(true)">
            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler
            icon-tabler-adjustments" width="20" height="20" viewBox="0 0 24 24"
            stroke-width="1.5" stroke="#FFFFFF" fill="none" stroke-linecap="round" stroke-linejoin="round">
                <path stroke="none" d="M0 0h24v24H0z" />
                <circle cx="6" cy="10" r="2" />
                <line x1="6" y1="4" x2="6" y2="8" />
                <line x1="6" y1="12" x2="6" y2="20" />
                <circle cx="12" cy="16" r="2" />
                <line x1="12" y1="4" x2="12" y2="14" />
                <line x1="12" y1="18" x2="12" y2="20" />
                <circle cx="18" cy="7" r="2" />
                <line x1="18" y1="4" x2="18" y2="5" />
                <line x1="18" y1="9" x2="18" y2="20" />
            </svg>
        </div>
        <div v-if="sidebarOpen" id="closeSideBar" class="md:hidden h-10 w-10 bg-gray-800 absolute
         right-0 mt-16 -mr-10 flex items-center shadow rounded-tr rounded-br
         justify-center cursor-pointer text-white" @click="handleSidebar(false)">
            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-x"
            width="20" height="20" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
            fill="none" stroke-linecap="round" stroke-linejoin="round">
                <path stroke="none" d="M0 0h24v24H0z" />
                <line x1="18" y1="6" x2="6" y2="18" />
                <line x1="6" y1="6" x2="18" y2="18" />
            </svg>
        </div>

        <div class="w-80 p-6 h-full">
          <div class="uppercase text-xs font-medium text-gray-400 tracking-wide pb-1
          border-b border-solid border-gray-300 truncate">
            {{$store.state.session.email}}
          </div>
        <!-- -------------------------------------------------------------------------------- -->
          <div class="mt-6 space-y-1">
            <div v-for="option in menuOptions" :key="option.text" @click="handleChoice(option.name)"
            :class="[currentActive == option.name ? 'bg-gray-200 text-gray-800' : 'text-gray-500',
            'flex text-lg px-4 py-2 tracking-wide rounded-lg cursor-pointer hover:bg-gray-300 hover:text-gray-800']">
              <span class='icon is-left w-8'>
                <font-awesome-icon :icon="['fas', option.icon]"/>
              </span>
              <div>{{option.text}}</div>
            </div>
          </div>
          <!-- -------------------------------------------------------------------------------- -->
          <div class="absolute bottom-6 space-y-1 ">

            <div @click="handleChoice('settings')"  :class="[currentActive == 'settings' ? 'bg-gray-200 text-gray-800' : 'text-gray-500',
            'flex text-lg px-4 py-2 tracking-wide rounded-lg cursor-pointer hover:bg-gray-300 hover:text-gray-800']">
              <span class='icon is-left w-8'>
                <font-awesome-icon :icon="['fas', 'user-cog']"/>
              </span>
              <div>Account Settings</div>
            </div>

            <div @click="$router.push('/') " class="flex text-gray-500 text-lg px-4 py-2
            tracking-wide rounded-lg cursor-pointer hover:bg-gray-300 hover:text-gray-800">
              <span class='icon is-left w-8'>
                <font-awesome-icon :icon="['fas', 'arrow-left']"/>
              </span>
              <div>Main Website</div>
            </div>

            <div @click="handleLogout()" class="flex text-gray-500 text-lg px-4 py-2 tracking-wide
            rounded-lg cursor-pointer hover:bg-gray-300 hover:text-gray-800 w-full">
              <span class='icon is-left w-8'>
                <font-awesome-icon :icon="['fas', 'sign-out-alt']"/>
              </span>
              <div>Sign Out</div>
            </div>

          </div>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      menuOptions: [
        {text: 'Book Exchange', icon: 'quran', name: 'exchange'},
        {text: 'Reservations', icon: 'atlas', name: 'reservations'},
        {text: 'Register New Members', icon: 'user-plus', name: 'register'},
        {text: 'Search Books', icon: 'search',  name: 'search'},
        {text: 'Check reviews', icon: 'tasks', name: 'reviews'}
      ],
      currentActive: 'exchange',
      sidebarOpen: false
    }
  },
  methods: {
    handleChoice (opt) {
      this.currentActive = opt
      this.$emit('component-change', opt)
    },
    handleLogout() {
      // clears cookies
      document.cookie.split(";").forEach(function(c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); });
      this.$store.commit('session/update')

      let that = this
      setTimeout(function(){
        if (that.$route.path != '/')
          that.$router.push({name:'index'})
        else
          that.$forceUpdate()
      }, 200)
    },
    handleSidebar(b) {
      this.sidebarOpen = b

      // 320px = 20rem = w-80 (tailwind)
      var sideBar = document.getElementById("mobile-nav");
      sideBar.style.transform = "translateX(-320px)";
      if (this.$data.moved) {
        sideBar.style.transform = "translateX(0px)";
        this.$data.moved = false;
      } else {
        sideBar.style.transform = "translateX(-320px)";
        this.$data.moved = true;
      }
    }
  }
};
</script>

<style>
</style>
