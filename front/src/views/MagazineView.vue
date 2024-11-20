<template>
  <div>
    <!-- 헤더 영역 -->
    <TheHeader/>
    <div class="content-box">
      <RouterView/>
      <MainAside/>
    </div>
  </div>
</template>

<script setup>
import TheHeader from "@/components/common/TheHeader.vue";
import MagazineList from '@/components/magazine/MagazineList.vue';
import MainAside from "@/components/main/aside/MainAside.vue";
</script>

<style scoped>
  .content-box {
    display: flex;
  }
</style>
