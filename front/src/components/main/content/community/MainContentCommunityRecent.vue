<template>
  <div class="recent-section">
    <h3>실시간 게시글</h3>
    <div v-for="post in recentPosts" :key="post.id" class="recent-post">
      <p>{{ post.title }}</p>
    </div>
  </div>
</template>

<script setup>
const recentPosts = [
  { id: 1, title: '러닝 후기' },
  { id: 2, title: '추천 코스' },
];
// 서버 연동 코드
// const recentPosts = await fetchRecentPosts();
</script>

<style scoped>
.recent-section {
  margin-bottom: 20px;
}
</style>
