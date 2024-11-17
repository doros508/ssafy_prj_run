<template>
  <section class="community-section">
    <MainContentCommunityTopTen />
    <MainContentCommunityRecent />
  </section>
</template>
  
  <script setup>
  import MainCommunityTopTen from './MainContentCommunityTopTen.vue';
  import MainCommunityRecent from './MainContentCommunityRecent.vue';
  </script>
  
  <style scoped>
  .community-section {
    margin-bottom: 20px;
  }
  </style>