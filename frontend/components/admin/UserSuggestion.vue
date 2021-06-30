<template>
    <div class="block w-11/12" @click="selected">
        <div class="grid grid-cols-12">
            <div class="col-start-1 col-span-3 my-aut">{{ id }}</div>
            <div class="col-start-4 col-span-3 my-aut">{{ name }}</div>
            <div class="col-start-8 col-span-12 my-aut">{{ surname }}</div>
        </div>
    </div>
</template>

<script>
export default {
    props: ["name", "surname", "id"],
    methods: {
        selected() {
            console.log("Emit");
            this.$emit('userIsSelected', { id : this.id, name: this.name, surname: this.surname });
            //
        }
    }
}
</script>