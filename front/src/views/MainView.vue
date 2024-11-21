<template>
  <div class="main-container">
    <div class="main-content">
      <TheHeader />
      <MainWelcome />
      <div class="content-box">
        <div class="content">
          <!-- <MainContent/> -->
          <MainContentMagazine />
          <MainContentCommunity />
        </div>
        <div class="right">
          <MainAside />
        </div>
      </div>
    </div>
    <!-- 이 자리에 footer -->
  </div>
</template>

<script setup>
import TheHeader from "@/components/common/TheHeader.vue";
import MainWelcome from "../components/main/MainWelcome.vue";
import MainContent from "@/components/main/content/MainContent.vue";
import MainAside from "@/components/main/aside/MainAside.vue";
import MainContentMagazine from "@/components/main/content/MainContentMagazine.vue";
import MainContentCommunity from "@/components/main/content/MainContentCommunity.vue";
</script>

<style scoped>
.main-container {
  background-color: #141414;
  color: #fff;
}
.content-box {
  display: flex;
  padding: 20px;
}
.content {
  width: 80%;
}
</style>
