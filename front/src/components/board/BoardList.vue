<template>
  <div class="group-76">
    <div class="div">
      <!-- 배경 이미지 -->
      <img class="board-background" src="../../assets/board/list//zzzz/zboard_background.png" />

      <!-- 게시판 검색 영역 -->
      <div class="search-box"></div>
      <img class="search-icon" src="../../assets/board/list/search.png" />
      <div class="search-text">게시판 검색</div>

      <!-- 커뮤니티 텍스트 -->
      <div class="community">Community</div>

      <!-- 게시판 내용 배경 -->
      <img class="content-background" src="../../assets/board/list/content_background.png" />
      <!-- <img class="div3" src="@/assets/div2.svg" /> -->

      <!-- 테이블 헤더 -->
      <div class="div4">번호</div> 
      <div class="div5">제목</div>
      <div class="div6">작성일</div>
      <div class="div7">작성자</div>
      <div class="div8">조회수</div>

      <!-- 게시물 내용 반복 -->
      <div v-for="(post, index) in posts" :key="post.id" class="content-value">
        <div class="number">{{ post.number }}</div>
        <div class="title">{{ post.title }}</div>
        <div class="date">{{ post.date }}</div>
        <div class="writer">{{ post.writer }}</div>
        <div class="views">{{ post.views }}</div>
      </div>

      <!-- 페이지 구분선 -->
      <div class="line-15"></div>

      <!-- 글쓰기 버튼 -->
      <div class="write-box" @click="navigateToWrite">
        <div class="write-text">글쓰기</div>
      </div>

      <!-- 페이지 네비게이션 -->
      <div class="paging">
        <div class="before" @click="goToPage(currentPage - 1)">이전</div>
        <div class="next" @click="goToPage(currentPage + 1)">다음</div>
        <div class="first" @click="goToPage(1)">처음</div>
        <div class="last" @click="goToPage(totalPages)">마지막</div>
        <div class="page-num">페이지 {{ currentPage }} / {{ totalPages }}</div>
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
          date: "24.11.07",
          writer: "홍길동",
          views: "100",
        },
        {
          id: 2,
          number: "2",
          title: "공지사항2 : 필독",
          date: "24.11.08",
          writer: "이순신",
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
      // 페이지에 맞는 게시물 목록을 API로 받아오는 로직을 추가할 수 있습니다.
    },
    // 글쓰기 페이지로 이동
    navigateToWrite() {
      this.$router.push("/write"); // 글쓰기 페이지로 라우팅 (Vue Router 사용 시)
    },
  },
};
</script>

<style scoped>
.group-76,
.group-76 * {
  box-sizing: border-box;
}

.group-76 {
  height: 1728px;
  position: relative;
}

.div {
  width: 1921px;
  height: 1728px;
  position: absolute;
  left: 0px;
  top: 0px;
  overflow: hidden;
}

.board-background {
  width: 100.05%;
  height: 100%;
  position: absolute;
  right: -0.05%;
  left: 0%;
  bottom: 0%;
  top: 0%;
  object-fit: cover;
}

.search-box {
  background: rgba(255, 255, 255, 0.15);
  border-radius: 25px;
  border-style: solid;
  border-color: rgba(255, 255, 255, 0.4);
  border-width: 1px;
  width: 21.2%;
  height: 3.04%;
  position: absolute;
  right: 3.59%;
  left: 75.21%;
  bottom: 83.03%;
  top: 13.93%;
}

.search-icon {
  width: 1.46%;
  height: 1.64%;
  position: absolute;
  right: 22.08%;
  left: 76.46%;
  bottom: 83.7%;
  top: 14.66%;
  object-fit: cover;
}

.search-text {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Medium", sans-serif;
  font-size: 20px;
  font-weight: 500;
  position: absolute;
  right: 16.2%;
  left: 78.7%;
  width: 5.1%;
  bottom: 83.7%;
  top: 14.84%;
  height: 1.46%;
}

.community {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Black", sans-serif;
  font-size: 55px;
  font-weight: 900;
  position: absolute;
  right: 78.91%;
  left: 3.65%;
  width: 17.45%;
  bottom: 83.03%;
  top: 13.26%;
  height: 3.71%;
}

.content-background {
  border-radius: 0px;
  width: 92.81%;
  height: 75.36%;
  position: absolute;
  right: 4.19%;
  left: 2.99%;
  bottom: 4.59%;
  top: 20.04%;
  overflow: visible;
}

.div3 {
  border-radius: 0px;
  width: 91.49%;
  height: 2.62%;
  position: absolute;
  right: 4.22%;
  left: 4.29%;
  bottom: 75.02%;
  top: 22.36%;
  overflow: visible;
}

.div4, .div5, .div6, .div7, .div8 {
  color: #000000;
  text-align: left;
  font-family: "Inter-Bold", sans-serif;
  font-size: 20px;
  font-weight: 700;
  position: absolute;
  width: 2%;
  bottom: 75.73%;
  top: 22.99%;
}

.content-value {
  position: absolute;
  inset: 0;
}

.views, .date, .writer, .title, .number {
  color: #ffffff;
  text-align: center;
  font-family: "Inter-Medium", sans-serif;
  font-size: 25px;
  font-weight: 500;
  position: absolute;
  width: 6%;
  bottom: 70.95%;
}

.line-15 {
  margin-top: -2px;
  border-style: solid;
  border-color: rgba(255, 255, 255, 0.2);
  border-width: 2px 0 0 0;
  width: 88.7%;
  height: 0%;
  position: absolute;
  right: 5.63%;
  left: 5.68%;
  bottom: 70.92%;
  top: 29.08%;
}

.write-box {
  background: #f8f8f8;
  border-style: solid;
  border-color: #a0a0a0;
  border-width: 1px;
  width: 4.27%;
  height: 2.13%;
  position: absolute;
  right: 4.68%;
  left: 91.05%;
  bottom: 80.39%;
  top: 17.48%;
}

.write-text {
  color: #383838;
  text-align: left;
  font-family: "Inter-Bold", sans-serif;
}
</style>
