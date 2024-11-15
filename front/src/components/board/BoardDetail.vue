<template>
  <div class="board-detail">
    <img class="background-img" src="background-img0.png" alt="Background" />
    <div class="comment">
      <div class="comment-writer">{{ comment.writer }}</div>
      <div class="comment-write-time">{{ comment.writeTime }}</div>
      <div class="comment-content">{{ comment.content }}</div>
      <img
        class="comment-profile-picture"
        :src="comment.profilePicture"
        alt="Comment Profile"
      />
      <div class="comment-split-line"></div>
      <div class="comment-edit">
        신고 또는 댓글수정 또는 삭제
        <br />
        <br />
      </div>
    </div>
    <div class="search-writer-content">
      <div class="writer-more">
        {{ writer.nickname }} 님의 게시글 더보기 &gt;
      </div>
      <img
        class="writer-profile-pic"
        :src="writer.profilePicture"
        alt="Writer Profile"
      />
    </div>
    <div class="content">
      <div class="content-content">
        {{ content.text }}
        <br />
      </div>
      <div class="content-title">내용</div>
    </div>
    <div class="title-info">
      <div class="title">
        <div class="title-title">제목</div>
        <div class="title-content">{{ title.text }}</div>
      </div>
      <div class="writer">
        <div class="writer-title">작성자</div>
        <div class="writer-content">{{ writer.name }}</div>
      </div>
      <div class="write-time">
        <div class="write-time-title">작성일자</div>
        <div class="write-time-content">
          {{ writeTime }}
          <br />
        </div>
      </div>
      <img class="div" src="div0.svg" alt="Divider" />
    </div>
    <div class="community-logo">Community</div>
    <div class="search-board">
      <div class="rectangle-30"></div>
      <img class="_300-x-1" src="_300-x-10.png" alt="Search Icon" />
      <div class="div2">게시판 검색</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BoardDetail",
  data() {
    return {
      comment: {
        writer: "채건우",
        writeTime: "2024.11.08. 16:24",
        content: "안전 러닝하세요~!",
        profilePicture: "comment-profile-picture0.png",
      },
      writer: {
        nickname: "작성자 닉네임",
        profilePicture: "writer-profile-pic0.png",
        name: "가을남자",
      },
      title: {
        text: "오늘 날씨 좋네요",
      },
      content: {
        text: "내용 입력하기. text 편집기.",
      },
      writeTime: "2024.11.12",
    };
  },
};
</script>
<style scoped>
.board-detail,
.board-detail * {
  box-sizing: border-box;
}
.board-detail {
  height: 1644px;
  position: relative;
}
.background-img {
  width: 100%;
  height: 100%;
  position: absolute;
  right: 0%;
  left: 0%;
  bottom: 0%;
  top: 0%;
  object-fit: cover;
}
.comment {
  width: 1405.56px;
  height: 110.46px;
  position: static;
}
.comment-writer {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Italic", sans-serif;
  font-size: 25px;
  font-weight: 400;
  font-style: italic;
  position: absolute;
  right: 66.56%;
  left: 22.09%;
  width: 11.35%;
  bottom: 15.92%;
  top: 82.34%;
  height: 1.74%;
}
.comment-write-time {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Italic", sans-serif;
  font-size: 14px;
  font-weight: 400;
  font-style: italic;
  position: absolute;
  right: 68.24%;
  left: 22.16%;
  width: 9.59%;
  bottom: 12.33%;
  top: 86.69%;
  height: 0.98%;
}
.comment-content {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Italic", sans-serif;
  font-size: 20px;
  font-weight: 400;
  font-style: italic;
  position: absolute;
  right: 65.24%;
  left: 22.16%;
  width: 12.6%;
  bottom: 13.95%;
  top: 84.66%;
  height: 1.39%;
}
.comment-profile-picture {
  border-radius: 118px;
  width: 65px;
  height: 64px;
  position: absolute;
  left: 327px;
  top: 1354px;
  object-fit: cover;
}
.comment-split-line {
  margin-top: -1px;
  border-style: solid;
  border-color: #dad0d0;
  border-width: 1px 0 0 0;
  width: 73.17%;
  height: 0%;
  position: absolute;
  right: 10.24%;
  left: 16.6%;
  bottom: 10.94%;
  top: 89.06%;
}
.comment-edit {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Italic", sans-serif;
  font-size: 20px;
  font-weight: 400;
  font-style: italic;
  position: absolute;
  right: 10.46%;
  left: 76.68%;
  width: 12.86%;
  bottom: 11.68%;
  top: 87.1%;
  height: 1.22%;
}
.search-writer-content {
  width: 630.65px;
  height: 65px;
  position: static;
}
.writer-more {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Italic", sans-serif;
  font-size: 25px;
  font-weight: 400;
  font-style: italic;
  position: absolute;
  right: 59.83%;
  left: 11.53%;
  width: 28.64%;
  bottom: 21.28%;
  top: 76.99%;
  height: 1.74%;
}
.writer-profile-pic {
  border-radius: 118px;
  width: 68px;
  height: 65px;
  position: absolute;
  left: 141px;
  top: 1248px;
  object-fit: cover;
}
.content {
  width: 1735px;
  height: 631px;
  position: static;
}
.content-content {
  color: #ffffff;
  text-align: center;
  font-family: "Inter-Italic", sans-serif;
  font-size: 25px;
  font-weight: 400;
  font-style: italic;
  position: absolute;
  right: 4.84%;
  left: 10.88%;
  width: 84.28%;
  bottom: 28.95%;
  top: 32.66%;
  height: 38.38%;
}
.content-title {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Bold", sans-serif;
  font-size: 32px;
  font-weight: 700;
  position: absolute;
  left: 93px;
  top: 548px;
  width: 105px;
  height: 20px;
}
.title-info {
  width: 1807px;
  height: 154px;
  position: static;
}
.title {
  width: 1605px;
  height: 36.21px;
  position: static;
}
.title-title {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Bold", sans-serif;
  font-size: 32px;
  font-weight: 700;
  position: absolute;
  left: 88px;
  top: 354px;
  width: 105.07px;
  height: 23.36px;
}
.title-content {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Bold", sans-serif;
  font-size: 32px;
  font-weight: 700;
  position: absolute;
  left: 228.09px;
  top: 354px;
  width: 1464.91px;
  height: 36.21px;
}
.writer {
  width: 1605px;
  height: 36.21px;
  position: static;
}
.writer-title {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Bold", sans-serif;
  font-size: 20px;
  font-weight: 700;
  position: absolute;
  left: 88px;
  top: 407.57px;
  width: 105.07px;
  height: 23.36px;
}
.writer-content {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Bold", sans-serif;
  font-size: 20px;
  font-weight: 700;
  position: absolute;
  left: 228.09px;
  top: 407.57px;
  width: 1464.91px;
  height: 36.21px;
}
.write-time {
  width: 1605px;
  height: 36.21px;
  position: static;
}
.write-time-title {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Bold", sans-serif;
  font-size: 20px;
  font-weight: 700;
  position: absolute;
  left: 88px;
  top: 442.79px;
  width: 105.07px;
  height: 23.36px;
  -webkit-text-stroke: 1px #000000;
}
.write-time-content {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Bold", sans-serif;
  font-size: 20px;
  font-weight: 700;
  position: absolute;
  left: 228.09px;
  top: 442.79px;
  width: 1464.91px;
  height: 36.21px;
  -webkit-text-stroke: 1px #000000;
}
.div {
  width: 1807px;
  height: 0px;
  position: absolute;
  left: 88px;
  top: 508px;
  overflow: visible;
}
.community-logo {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Black", sans-serif;
  font-size: 55px;
  font-weight: 900;
  position: absolute;
  right: 77.98%;
  left: 4.58%;
  width: 17.44%;
  bottom: 83.03%;
  top: 13.26%;
  height: 3.71%;
}
.search-board {
  width: 407px;
  height: 50px;
  position: static;
}
.rectangle-30 {
  background: rgba(255, 255, 255, 0.15);
  border-radius: 25px;
  border-style: solid;
  border-color: rgba(255, 255, 255, 0.4);
  border-width: 1px;
  width: 21.19%;
  height: 3.04%;
  position: absolute;
  right: 2.71%;
  left: 76.11%;
  bottom: 83.03%;
  top: 13.93%;
}
._300-x-1 {
  width: 1.46%;
  height: 1.64%;
  position: absolute;
  right: 21.19%;
  left: 77.36%;
  bottom: 83.7%;
  top: 14.66%;
  object-fit: cover;
}
.div2 {
  color: #ffffff;
  text-align: left;
  font-family: "Inter-Medium", sans-serif;
  font-size: 20px;
  font-weight: 500;
  position: absolute;
  right: 15.3%;
  left: 79.59%;
  width: 5.1%;
  bottom: 83.7%;
  top: 14.84%;
  height: 1.46%;
}


</style>