<template>
    <div class="community">
      <div class="div12" v-for="(post, index) in posts" :key="index">
        <div class="div13"></div>
        <div class="_36">
          <div class="_37">{{ post.content }}</div>
          <div class="_44">좋아요 {{ post.likes }}</div>
          <div class="_134">댓글 {{ post.comments }}</div>
          <div class="div14">{{ post.title }}</div>
          <div class="div15">{{ post.date }}</div>
          <div class="div16">{{ post.author }}</div>
          <img class="div17" :src="post.authorImage" />
        </div>
      </div>
      <div class="community2">Community</div>
    </div>
  </template>
  
  
  <script> 
  export default {
    data() {
      return {
        posts: [
          {
            content: "글 내용 최대3 줄",
            likes: 4,
            comments: 134,
            title: "게시판 제목",
            date: "작성 일자",
            author: "작성자",
            authorImage: "div16.png"
          },
          {
            content: "글 내용 최대3 줄",
            likes: 4,
            comments: 134,
            title: "게시판 제목",
            date: "작성 일자",
            author: "작성자",
            authorImage: "div20.png"
          },
          {
            content: "글 내용 최대3 줄",
            likes: 4,
            comments: 134,
            title: "게시판 제목",
            date: "작성 일자",
            author: "작성자",
            authorImage: "div24.png"
          }
        ]
      }
    }
  }
  </script>
  
  
  <style scoped>
  .community2 {
    color: #ffffff;
    text-align: left;
    font-family: "Inter-Black", sans-serif;
    font-size: 55px;
    font-weight: 900;
  }
  </style>