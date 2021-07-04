<template>
     <div
    class="block md:flex mx-auto rounded-xl border border-dashed border-gray-300 p-6"
    >
        <div class="flex-shrink-0">
        <img
            :src="data.editionImageLarge"
            alt=""
            class="text-center sm:text-left rounded-md
        w-36 lg:w-40 h-56 lg:h-60 object-fill mx-auto"
            draggable="false"
        />
        </div>
        <div class="md:pl-4 text-center md:text-left" v-if="data.bookState">
            <div
                class="text-xl md:text-base lg:text-xl font-bold text-blue-900 opacity-90
            line-clamp-2 mt-4 md:mt-0"
            >
                {{ data.editionTitleTitle }}
            </div>
            <div class="text-xs tracking-wide text-gray-400 line-clamp-2">
                {{ data.authorName }}
            </div>

            <div class="mt-2 text-sm font-medium tracking-wide text-gray-500 ">
                <div class="line-clamp-2">ID: {{ data.id }}</div>
                <div class="line-clamp-2">
                Isle: {{ data.lineIsleName }} - Row: {{ data.lineNumber }}
                </div>
                <div class="line-clamp-2">
                Condition:
                <span :class="[colorCondition]" class="font-bold">{{
                    data.condition
                }}</span>
                </div>
            <div class="line-clamp-2">State: {{ data.bookState }}</div>
        </div>
        </div>
     </div>

</template>

<script>
export default {
    props: ["data"],

}
</script>

<style>

</style>