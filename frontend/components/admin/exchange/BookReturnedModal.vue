<template>
  <div class="fixed z-10 inset-0 overflow-y-auto" aria-labelledby="modal-title" role="dialog" aria-modal="true">
	<div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0 -mt-32 sm:-mt-0">
	  <div @click.self="closeModal" class="fixed inset-0 bg-gray-700 bg-opacity-75 transition-opacity" aria-hidden="true"></div>
	  <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>

	  <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl
	   transform transition-all sm:my-8 sm:align-middle sm:max-w-lg w-11/12 sm:w-full">

		<div class="inline-block mx-auto mt-4 ml-4 space-x-4 justify-center">
			<div v-for="i in fields" :key="i.name" class="inline-block space-x-4 justify-evenly">
				<InputField @input-change="updateInput" :what="i"/>
			</div>
		</div>

		

		<div v-if="isReserved" class="text-gray-600 text-lg ml-4" >
			Added to the reservation list!
		</div>

		<div v-if="reservationComponent" class="text-gray-600 text-lg ml-4 mt-8" >
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
  props: ['id', 'isle', 'row'],
  data () {
	return {
		fields : [
			{ name: "islename", text: "Isle name", placeholder: this.isle, query: ''  },
			{ name: "rownumber", text: "Row number", placeholder: this.row, query: '' },
		],
		reservationComponent: false,
		isReserved: false
	}
  },
  methods: {
	confirmAction() {
    let isleN = this.isle
    let rowN = this.row

		if (this.fields[0]['query'] && this.fields[1]['query']) {
      isleN = this.fields[0]['query']
      rowN = this.fields[1]['query']
    }
		
		axios
		.post("/api/change-placement?bookId=" + this.id, 
		{
			isleName: isleN,
			lineNumber: rowN,
		})
		.then(x => {
			console.log(x);
		})
		.catch(x => {
			console.log(x);
		});

		this.closeModal()
	
	},
	closeModal () {
		this.$emit('close-modal')
	},
	markAsReserved () {
		this.isReserved = true;
		this.reservationComponent = false;
		axios
		.post("/api/assign-book-to-reservation/?id=" + this.id)
		.then(x => { 
			console.log(x.data);
			if(x.data == true) {
				this.reservationComponent = false;
			}
	  	})
	  	.catch();
	},
	hideReservation () {
		this.reservationComponent = false;
	},
    updateInput (field, text) {
        this.fields.filter((f) => {if (f.name===field) return f})[0]['query'] = text;
    },

  
  },
  mounted() {
		let that = this;
		axios
		.get("/api/new-reservation/?bookId=" + this.id)
		.then(x => { 
			console.log(x.data);
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