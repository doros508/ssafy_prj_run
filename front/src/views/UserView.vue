<template>
    <div>
        <TheHeader/>
        <RouterView/>
    </div>
</template>

<script setup>
import TheHeader from '@/components/common/TheHeader.vue';
import { RouterView } from "vue-router";


</script>

<style scoped>

</style>