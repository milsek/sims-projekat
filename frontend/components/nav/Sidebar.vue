<template>
  <div class="h-screen min-w-80 bg-gray-100">
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
</template>

<script>
export default {
  data () {
    return {
      menuOptions: [
        {text: 'Book Exchange', icon: 'quran', name: 'exchange'},
        {text: 'Register New Members', icon: 'user-plus', name: 'register'},
        {text: 'Search Books N Stuff', icon: 'search',  name: 'search'},
      ],
      personalOptions: [
        {text: 'Account Settings', icon: 'user-cog', name: 'settings'},
        {text: 'Sign Out', icon: 'sign-out-alt', name: 'signout'},
      ],
      currentActive: 'exchange'
    }
  },
  methods: {
    handleChoice (opt) {
      console.log(opt)
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
    }
  }
};
</script>

<style>
</style>