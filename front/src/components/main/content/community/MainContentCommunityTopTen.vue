<template>
  <div class="top-ten-section">
    <h3>TOP 10 🔥</h3>
    <div class="top-ten-grid">
      <div v-for="post in topTenPosts" :key="post.id" class="top-ten-item">
        <img :src="post.image" alt="Top Image" />
        <p>{{ post.title }}</p>
      </div>
    </div>
  </div>
</template>
  
<script setup>
const topTenPosts = [
  { id: 1, title: '마라톤 준비', image: '/img/top1.png' },
  { id: 2, title: '리뷰', image: '/img/top2.png' },
];
// 서버 연동 코드
// const topTenPosts = await fetchTopTen();
</script>

<style scoped>
.top-ten-grid {
  display: flex;
  gap: 10px;
}
</style>