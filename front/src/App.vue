<template>
  <div>
    <h2>App.vue</h2>
    <Theheader/>
    <RouterView />
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router';
import Theheader from '@/components/common/Theheader.vue';
</script>

<style scoped>

</style>