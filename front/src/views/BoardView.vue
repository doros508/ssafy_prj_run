<template>
  <div>
    <h2>여기는 BoardView.vue !</h2>
    <!-- 헤더 영역 수정1 -->
    <TheHeader />
    <RouterView />
    <BoardList />
  </div>
</template>

<script setup>
import BoardList from "@/components/board/BoardList.vue";
import TheHeader from "@/components/common/TheHeader.vue";
import { RouterView } from "vue-router";
</script>

<style scoped></style>
