<template>
  <div>
    <h2>메인페이지</h2>
    <hr />
    <RouterView />
  </div>
</template>

<script setup>
import Theheader from "@/components/common/Theheader.vue";
import { RouterView } from "vue-router";
</script>

<style scoped></style>
