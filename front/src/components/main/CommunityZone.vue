<template>
  <div class="community">
    <!-- Community Header -->
    <div class="community-header">Community</div>

    <!-- Posts Section -->
    <div class="posts-container">
      <div class="post-card" v-for="(post, index) in posts" :key="index">
        <!-- Left Image Section -->
        <img class="post-image" :src="post.authorImage" alt="author image" />

        <!-- Right Content Section -->
        <div class="post-content">
          <div class="post-text">{{ post.content }}</div>
          <div class="post-likes">좋아요 {{ post.likes }}</div>
          <div class="post-comments">댓글 {{ post.comments }}</div>
          <div class="post-title">{{ post.title }}</div>
          <div class="post-date">{{ post.date }}</div>
          <div class="post-author">{{ post.author }}</div>
        </div>
      </div>
    </div>
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
          authorImage: "div16.png",
        },
        {
          content: "글 내용 최대3 줄",
          likes: 4,
          comments: 134,
          title: "게시판 제목",
          date: "작성 일자",
          author: "작성자",
          authorImage: "div20.png",
        },
        {
          content: "글 내용 최대3 줄",
          likes: 4,
          comments: 134,
          title: "게시판 제목",
          date: "작성 일자",
          author: "작성자",
          authorImage: "div24.png",
        },
      ],
    };
  },
};
</script>

<style scoped>
/* Community Container */
.community {
  display: flex;
  flex-direction: column;
  gap: 16px;
  padding: 16px;
  background-color: #1e1e1e; /* 배경색 */
  border-radius: 8px;
}

/* Header Section */
.community-header {
  font-size: 32px;
  font-weight: 900;
  color: white;
  text-align: left;
}

/* Posts Container */
.posts-container {
  display: grid;
  grid-template-columns: 1fr 1fr; /* 두 열로 배치 */
  gap: 16px;
}

/* Post Card */
.post-card {
  display: flex;
  gap: 16px;
  background-color: #2c2c2c;
  border-radius: 8px;
  padding: 16px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
}

/* Post Image */
.post-image {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  object-fit: cover;
}

/* Post Content */
.post-content {
  display: flex;
  flex-direction: column;
  gap: 8px;
  color: #ffffff;
}

/* Individual Text Styles */
.post-text {
  font-size: 16px;
  font-weight: 400;
}

.post-likes {
  justify-content: right;
}

.post-comments {
  font-size: 14px;
  color: #aaaaaa;
}

.post-title {
  font-size: 18px;
  font-weight: bold;
}

.post-date,
.post-author {
  font-size: 12px;
  color: #aaaaaa;
}
</style>
