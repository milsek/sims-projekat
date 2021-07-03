<template>
  <div class="fixed z-10 inset-0 overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
	<div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0 -mt-32 sm:-mt-0">
	  <div @click.self="closeModal" class="fixed inset-0 bg-gray-700 bg-opacity-75 transition-opacity" aria-hidden="true"></div>
	  <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>

	  <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl
	   transform transition-all sm:my-8 sm:align-middle sm:max-w-lg w-11/12 sm:w-full">

		<div class="flex space-x-2 justify-evenly my-4">
			<div>
				<InputField :what="rowWhat" />
			</div>
			<div>
				<InputField :what="isleWhat" />
			</div>
		</div>

		<div v-if="isReserved" class="text-gray-600 text-lg" >
				Added to the reservation list!
		</div>

		<div v-if="reservationComponent" class="text-gray-600 text-lg ml-8 mt-8" >
			<div class="text-gray-600 text-lg" >
				There is a reservation waiting for this edition!
			</div>
			<div class="text-gray-600 text-base" >
				Do you want to add it to the reserved list?
			</div>
			<div class="text-center -ml-8 mt-2">
			<button @click="markAsReserved" type="button" class="inline-flex justify-center rounded-md shadow-sm 
			px-3 py-2 text-base font-medium text-gray-800 bg-gray-300 hover:bg-gray-500 
			focus:outline-none w-16 sm:text-sm">
			Yes
		  	</button>

			<button @click="hideReservation" type="button" class="inline-flex justify-center rounded-md shadow-sm 
			px-3 py-2 text-base font-medium text-gray-800 bg-gray-300 hover:bg-gray-500 
			focus:outline-none w-16 sm:text-sm">
			No
		  	</button>
			</div>
			
		</div>
	  
	  <div class="bg-gray-100 px-4 py-5 mt-8 sm:px-6 flex justify-end">
		  <button @click="confirmAction" type="button" class="inline-flex justify-center
		  rounded-md shadow-sm px-6 py-2 bg-green-500 hover:bg-green-600 text-base font-medium text-white
		  focus:outline-none w-auto sm:text-sm">
		  Accept
		  </button>
		  <button @click="closeModal" type="button" class="inline-flex justify-center rounded-md shadow-sm 
		  px-6 py-2 text-base font-medium text-white bg-red-500 hover:bg-red-600 
		  focus:outline-none ml-3 w-auto sm:text-sm">
		  Discard
		  </button>
		</div>
	  </div>

	</div>
  </div>
</template>

<script>
import axios from 'axios';
import InputField from '../registration/InputField.vue';
export default {
  components: { InputField },
  props: ["confirm", "id"],
  data () {
	return {
		isleWhat : { name: "Isle name", text: "Isle name", placeholder: "Isle name", query: ''  },
		rowWhat : { name: "Row number", text: "Row number", placeholder: "Row number", query: '' },
		reservationComponent: true, //!!!
		isReserved: false
	}
  },
  methods: {
	confirmAction() {
	  
	},
	closeModal () {
		this.$emit('close-modal')
	},
	markAsReserved () {
		this.isReserved = true;
		this.reservationComponent = false;
	},
	hideReservation () {
		this.reservationComponent = false;
	},

  
  },
  mounted() {
		let that = this;
		axios
		.get("/api/active-reservation/?bookId=" + this.id)
		.then(x => { 
			console.log(x);
			console.log(x.data);
			console.log(x.data.data);
			if(x.data == true) {
				this.reservationComponent = true;
			}
	  	})
	  	.catch();
  }
}
</script>

<style>

</style>