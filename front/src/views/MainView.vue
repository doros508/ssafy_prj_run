<template>
  <div class="main-container">
    <div class="main-content">
    <TheHeader />
    <MainWelcome />
    <div class="content-box">
      <div class="content">
        <MainContent/>
      </div>
      <MainAside />
    </div>
  </div>  
  <!-- 이 자리에 footer -->
  </div>
</template>
  
<script setup>
import TheHeader from '@/components/common/TheHeader.vue';
import MainWelcome from '../components/main/MainWelcome.vue';
import MainContent from '@/components/main/content/MainContent.vue';
import MainAside from '@/components/main/aside/MainAside.vue';
</script>
  
<style scoped>
.main-container {
  background-color: #141414;
  color: #fff;
}
.content-box {
  display: flex;
  justify-content: space-between;
  padding: 20px;
}
</style>