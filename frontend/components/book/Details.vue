<template>
  <div class="block w-11/12 mt-6 mx-auto bg-white shadow-lg">
    <div class="block p-8 w-full">
      <div class="text-xl text-gray-700 tracking-wide font-medium">Details</div>
        <div class="grid grid-rows-flow sm:grid-rows-5 lg:grid-rows-4 sm:grid-flow-col sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 text-sm font-medium text-gray-700 pt-2">
          <div v-for="att in attributes" :key="att.name" class="truncate">
            <div class="pt-2">{{att.text}}: <span class="font-normal text-gray-600 truncate"> {{getData(att)}}</span></div>
          </div>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['data'],
  data () {
    return {
      attributes: [
        { name: 'language', text: 'Language' },
        { name: 'pageCount', text: 'Pages' },
        { name: 'dimensions', text: 'Dimensions' },
        { name: 'year', text: 'Publication date' },
        { name: 'publisherName', text: 'Publisher' },
        { name: 'id', text: 'MySBN' },
        { name: 'takeOut', text: 'Can be taken out' },
      ],
    }
  },
  methods: {
    getData (att) {
      if (att.name === "language") return 'English'
      if (att.name === "takeOut") return this.data[att.name] ? 'Yes' : 'No'
      else if (att.name === "publisher") return this.data[att.name]["name"]
      else if (att.name === "dimensions") {
        const h = Math.round(this.data.dimensions / 1.61 * 10)
        const w = Math.round(this.data.dimensions * 10)
        const g = Math.round(this.data.pageCount / (Math.random()*10 + 10))
        return h.toString() + ' x ' + w.toString() + ' x ' + g.toString() + 'mm'
      }
      else return this.data[att.name]
    },
  }
}
</script>

<style>

</style>