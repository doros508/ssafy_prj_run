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
            <div class="post-title">{{ post.title }}</div>
            <div class="post-text">{{ post.content }}</div>
            <div class="post-info">
              <span class="post-likes">좋아요 {{ post.likes }}</span>
              <span class="post-comments">댓글 {{ post.comments }}</span>
            </div>
            <div class="post-meta">
              <div class="post-date">{{ post.date }}</div>
              <div class="post-author">{{ post.author }}</div>
            </div>
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
            content: "내용1",
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
            content: "인간은 직립보행을 하기 때문에 달릴 때 바람의 저항을",
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
  /* 전체 커뮤니티 컨테이너 */
  .community {
    display: flex;
    flex-direction: column;
    gap: 20px;
    padding: 20px;
    /* background-color: #1e1e1e; */
    border-radius: 10px;
    width: 90%;
    max-width: 90%;
    margin: 0 auto; /* 왼쪽 마진을 20px로 설정 */
    
  }

  /* 커뮤니티 헤더 스타일 */
  .community-header {
    font-size: 35px;
    font-weight: 900;
    color: white;
    text-align: center;
    margin-bottom: 20px;
  }

  /* 포스트 목록을 위한 컨테이너 */
  .posts-container {
    display: grid;
    
    grid-template-columns: 1fr 1fr; /* 두 열로 배치 */
    gap: 20px;
    width: 100%;
  }

  /* 포스트 카드 스타일 */
  .post-card {
    display: flex;
    gap: 16px;
    background-color: #2c2c2c;
    border-radius: 8px;
    padding: 16px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
    transition: transform 0.3s ease-in-out;
  }

  .post-card:hover {
    transform: translateY(-5px);
  }

  /* 작성자 이미지 스타일 */
  .post-image {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    object-fit: cover;
  }

  /* 포스트 내용 영역 */
  .post-content {
    display: flex;
    flex-direction: column;
    gap: 12px;
    color: #ffffff;
    flex: 1;
  }

  /* 포스트 제목 스타일 */
  .post-title {
    font-size: 20px;
    font-weight: bold;
    color: #ffffff;
  }

  /* 포스트 텍스트 스타일 */
  .post-text {
    font-size: 16px;
    color: #dddddd;
    line-height: 1.5;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    
  }

  /* 좋아요, 댓글 등의 정보 스타일 */
  .post-info {
    font-size: 14px;
    color: #aaaaaa;
    display: flex;
    gap: 10px;
  }

  /* 포스트 날짜 및 작성자 스타일 */
  .post-meta {
    font-size: 12px;
    color: #aaaaaa;
    display: flex;
    justify-content: space-between;
  }

  /* 반응형 디자인 (모바일, 작은 화면) */
  @media screen and (max-width: 768px) {
    .posts-container {
      grid-template-columns: 1fr; /* 모바일에서는 한 열로 배치 */
    }

    .post-card {
      flex-direction: column; /* 카드 내용 세로로 배치 */
      align-items: center;
    }

    .post-text {
      white-space: normal; /* 텍스트가 한 줄로 끊어지지 않도록 설정 */
    }

    .post-info {
      flex-direction: column; /* 좋아요 및 댓글 수 세로로 배치 */
    }

    .post-meta {
      flex-direction: column; /* 날짜 및 작성자 세로로 배치 */
    }
  }
  </style>
