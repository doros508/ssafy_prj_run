<template>
  <div class="board-container">
    <!-- 배경 이미지 -->
    <img class="board-background" src="../../assets/board/list/board_background.png" />

    <!-- 게시판 콘텐츠 영역 -->
    <div class="board-content">
      <!-- 커뮤니티 텍스트 -->
      <h1 class="community">Community</h1>

      <!-- 게시판 목록 테이블 -->
      <div class="table">
        <!-- 테이블 헤더 -->
        <div class="table-header">
          <div class="table-cell">번호</div>
          <div class="table-cell">제목</div>
          <div class="table-cell">작성자</div>
          <div class="table-cell">작성일</div>
          <div class="table-cell">조회수</div>
        </div>

        <!-- 게시물 리스트 -->
        <div class="table-body">
          <div v-for="(post, index) in posts" :key="post.id" class="table-row">
            <div class="table-cell">{{ post.number }}</div>
            <div class="table-cell">{{ post.title }}</div>
            <div class="table-cell">{{ post.writer }}</div>
            <div class="table-cell">{{ post.date }}</div>
            <div class="table-cell">{{ post.views }}</div>
          </div>
        </div>
      </div>

      <!-- 페이지 네비게이션 -->
      <div class="paging">
        <button @click="goToPage(1)">처음</button>
        <button @click="goToPage(currentPage - 1)">이전</button>
        <span>페이지 {{ currentPage }} / {{ totalPages }}</span>
        <button @click="goToPage(currentPage + 1)">다음</button>
        <button @click="goToPage(totalPages)">마지막</button>
      </div>

      <!-- 글쓰기 버튼 -->
      <div class="write-box" @click="navigateToWrite">
        <span class="write-text">글쓰기</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentPage: 1, // 현재 페이지
      totalPages: 5, // 전체 페이지 수
      posts: [
        {
          id: 1,
          number: "1",
          title: "공지사항1 : 필독",
          writer: "홍길동",
          date: "24.11.07",
          views: "100",
        },
        {
          id: 2,
          number: "2",
          title: "공지사항2 : 필독",
          writer: "이순신",
          date: "24.11.08",
          views: "50",
        },
        // 실제 게시물 데이터를 여기에 추가
      ],
    };
  },
  methods: {
    // 페이지 이동
    goToPage(page) {
      if (page < 1 || page > this.totalPages) return;
      this.currentPage = page;
    },
    // 글쓰기 페이지로 이동
    navigateToWrite() {
      this.$router.push("/write");
    },
  },
};
</script>

<style scoped>
.board-container {
  position: relative;
  width: 100%;
  height: 100vh;
  overflow: hidden;
}

.board-background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: 0;
}

.board-content {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  z-index: 1;
}

.community {
  color: #ffffff;
  font-size: 2em;
  font-weight: bold;
  margin-bottom: 20px;
}

.table {
  width: 90%;
  max-width: 1200px;
  background-color: rgba(0, 0, 0, 0.6);
  border-radius: 10px;
  overflow: hidden;
}

.table-header,
.table-row {
  display: flex;
  justify-content: space-between;
  padding: 10px;
  color: #ffffff;
  font-weight: bold;
}

.table-header {
  background-color: rgba(255, 255, 255, 0.2);
}

.table-row {
  border-bottom: 1px solid rgba(255, 255, 255, 0.2);
}

.table-cell {
  flex: 1;
  text-align: center;
}

.paging {
  display: flex;
  gap: 10px;
  margin-top: 20px;
  color: #ffffff;
}

button {
  padding: 10px;
  cursor: pointer;
  background-color: #f0f0f0;
  border: none;
  border-radius: 5px;
}

.write-box {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #ffffff;
  border-radius: 5px;
  cursor: pointer;
}

.write-text {
  font-weight: bold;
}
</style>
