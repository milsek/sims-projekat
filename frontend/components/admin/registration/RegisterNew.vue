<template>
  <div class="block justify-center w-full text-center mx-auto py-10 px-10">
    <div>
      <div class="text-4xl tracking-tight text-blue-900 opacity-70">Register New Member</div>
      <div class="font-medium text-gray-500 mt-2">Enter information about the new member</div>
    </div>

    <div class="grid grid-cols-2 grid-flow-row text-left mt-10 gap-y-4 lg:gap-x-6 xl:gap-x-10 sm:px-24 md:px-12 lg:px-20 xl:px-32">
      <div v-for="i in fields" :key="i.text" class="col-span-2 lg:col-span-1">
        <InputField @input-change="updateInput" :what="i"/>
      </div>
    </div>

    <div class="mt-10">
      <button @click="registerMember" class="h-10 mt-4 px-10 md:px-12
        opacity-90 text-white text-center text-xl pb-1 focus:outline-none rounded-lg bg-blue-700 hover:bg-blue-800">
        register
      </button>
    </div>

  </div>
</template>

<script>
import InputField from '~/components/admin/registration/InputField'
export default {
  components: { InputField },
  data () {
    return {
      fields: [
        {text: 'Name', placeholder: 'Name', name: 'name', query: ''},
        {text: 'Surname', placeholder: 'Surname', name: 'surname', query: ''},
        {text: 'Email', placeholder: 'Email Address', name: 'email', query: ''},
        {text: 'Password', placeholder: 'Password', name: 'password', query: ''},
        {text: 'Birth Date', placeholder: '[dd-mm-yyyy]', name: 'birth', query: ''},
        {text: 'Bank Account Number', placeholder: 'Not a scam', name: 'bank', query: ''},
      ]
    }
  },
  methods: {
    updateInput (field, text) {
      this.fields.filter((f) => {return f.name=field})[0]['query'] = text
    },
    registerMember () {
      console.log('Member registered!')
    }
  }
};
</script>

<style>
</style>