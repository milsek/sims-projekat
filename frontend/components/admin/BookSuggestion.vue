<template>
    <div class="block w-11/12" @click="selected">
        <div class="grid grid-cols-12">
            <div class="col-start-1 col-span-3 my-aut">{{ id }}</div>
            <div class="col-start-4 col-span-3 my-aut">{{ title }}</div>
        </div>
    </div>
</template>

<script>
export default {
    props: ["id", "title"],
    methods: {
        selected() {
            console.log("Bookcina");
            this.$emit('book-is-selected', { id : this.id, title: this.title });
        }
    }
}
</script>