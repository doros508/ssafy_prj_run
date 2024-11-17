<template>
  <section class="magazine-section">
    <h2>Magazine</h2>
    <div class="magazine-grid">
      <div v-for="(post, index) in magazinePosts" :key="post.id" class="magazine-item">
        <!-- 동적으로 이미지 가져올 땐, <img :src="post.image" alt="Magazine Image" /> -->
        <!-- 정적으로 이미지를 가져올 땐, require() 사용인데 vite 환경에서는 안된다고 함-->
        <!-- <img :src="require(`${post.image}`)" alt="Magazine Image" /> -->
        <h3>{{ post.title }}</h3>
      </div>
      <div class="magazine-item more-item">
        <p>더보기</p>
      </div>
    </div>
  </section>
</template>

<script setup>
const magazinePosts = [
  { id: 1, title: '가을 마라톤', image: '@/assets/main/mainContentMagazine/img1.png' },
  { id: 2, title: '수분 보충 팁', image: '@/assets/main/mainContentMagazine/img2.png' },
  { id: 3, title: '러닝화 추천', image: '@/assets/main/mainContentMagazine/img3.png' },
];
// 서버 연동 코드 (주석 처리)
// import { fetchMagazines } from '@/api';
// const magazinePosts = await fetchMagazines();
</script>

<style scoped>
.magazine-section {
  margin-bottom: 20px;
}
.magazine-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
}
.magazine-item {
  background: #222;
  padding: 10px;
  border-radius: 10px;
}
.more-item {
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  border-radius: 10px;
}
</style>
