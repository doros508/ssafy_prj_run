<template>
    <div>
        <h2>여기는 UserView.vue ~_~</h2>
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