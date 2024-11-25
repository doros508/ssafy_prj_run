  <template>
    <div class="community">
      <!-- Community Header -->
      <div class="community-header">Community</div>

      <div class="content">
        <!-- 게시판 목록 테이블 -->
        <table class="table table-dark table-striped table-hover">
          <thead>
            <tr>
              <th scope="col">제목</th>
              <th scope="col">조회수</th>
              <th scope="col">작성일</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="board in store.boardList.slice(0, 10)" :key="board.boardNo">
              <td>
                <RouterLink :to="`/board/${board.boardNo}`">{{
                  board.boardTitle
                  }}</RouterLink>
              </td>
              <td>{{ board.boardViewCnt }}</td>
              <td>{{ board.boardRegDate }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </template>

<script setup>
import { useBoardStore } from "@/stores/board";
import { onMounted } from "vue";
import { useRouter } from "vue-router";

const store = useBoardStore();
const router = useRouter();
onMounted(() => {
  store.getBoardList();
});

</script>

<style scoped>
* {
  text-decoration: none;
}

a {
  color: white;
}

.table {
  text-align: center;
  border-radius: 15px;
  overflow: hidden;
}

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
  margin: 0 auto;
  /* 왼쪽 마진을 20px로 설정 */

}

/* 커뮤니티 헤더 스타일 */
.community-header {
  font-size: 35px;
  font-weight: 900;
  color: white;
  text-align: center;
}

.content {
  position: relative;
  width: 100%;
  padding: 20px;
  background-color: rgba(0, 0, 0, 0.7);
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
