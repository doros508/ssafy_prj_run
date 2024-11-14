<template>
  <div>
    <!-- <TheHeader /> -->
    <RouterView />
  </div>
</template>

<script setup>
import { RouterView } from "vue-router";
import TheHeader from "@/components/common/TheHeader.vue";
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
</style>
