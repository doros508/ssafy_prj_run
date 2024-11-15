<template>
  <div class="main-page">
    <!-- 헤더 영역 -->
    <TheHeader />
    <Welcome/>
    <MagazineZone/>
    <CommunityZone/>
  </div>
</template>

<script setup>
import TheHeader from "@/components/common/TheHeader.vue";
import CommunityZone from "@/components/main/CommunityZone.vue";
import MagazineZone from "@/components/main/MagazineZone.vue";
import Welcome from "@/components/main/welcome.vue";
import { RouterView } from "vue-router";
</script>

<style>

</style>