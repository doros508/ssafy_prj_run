<template>
    <div class="main-content">
      <MainContentMagazine />
      <MainContentCommunity />
    </div>
  </template>
  
  <script setup>
  import MainContentMagazine from '@/components/main/content/MainContentMagazine.vue';
  import MainContentCommunity from '@/components/main/content/MainContentCommunity.vue';
  </script>
  
  <style scoped>
  .main-content {
    flex: 3;
  }
  </style>
  