import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";
import { useUserStore } from "./userStore";

const REST_API_URL = `http://localhost:8080/maratalk/board`; // 로컬
// const REST_API_URL = `http://192.168.210.63:8080/maratalk/board` //종수
// const REST_API_URL = `http://192.168.210.53:8080/maratalk/board` // 건우

export const useBoardStore = defineStore("board", () => {
  const boardList = ref([]);

  const getBoardList = function () {
    axios.get(REST_API_URL, {}).then((response) => {
      console.log(response.data);
      boardList.value = response.data;
    });
  };

  // 게시글 등록
  const createBoard = function(formData) {
    const userStore = useUserStore();
    if (!userStore.isLoggedIn) {
      alert("로그인이 필요한 서비스입니다.");
      router.push({ name: "login" });
      return;
    }
    
    axios.post(REST_API_URL, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then(() => {
      router.push({ name: "boardList" });
    })
    .catch((error) => {
      console.error("게시글 작성 실패:", error);
      alert("게시글 작성에 실패했습니다.");
    });
  };

  // 게시글 조회
  const board = ref({});
  const getBoard = function (boardNo) {
    console.log("board.js게시글 가져오기 시도(프론트) 보드번호 확인", boardNo);
    axios
      .get(`${REST_API_URL}/${boardNo}`)
      .then((response) => {
        console.log("백엔드 응답 데이터:", response.data); // 응답 데이터 확인


        board.value = response.data;
        console.log("보드가 가져온 것은?");
        console.log(board.value.boardFileList);
        // 파일 노출
        if (Array.isArray(board.value.boardFileList)) {
          console.log("배열이군");
          board.value.boardFileList.forEach((file) => {
            file.imageUrl = `http://localhost:8080/uploads/board${file.path}/${file.systemName}`;
            console.log("이미지 URL: " + file.imageUrl);
          });
        } else {
          console.log("배열이 아니군");
        }
      })
      .catch((error) => {
        console.log(boardNo);
        console.log("흠 잘 안되네", error);
      });
  };

  // 게시글 수정
  const updateBoard = function (boardNo) {
    axios.put(`${REST_API_URL}/${boardNo}`, board.value).then(() => {
      router.push({ name: "boardList" });
    });
  };

  // 게시글 삭제
  const deleteBoard = function (boardNo) {
    axios.delete(`${REST_API_URL}/${boardNo}`).then(() => {
      router.push({ name: "boardList" });
    });
  };

  const searchBoardList = function (searchCondition) {
    axios
      .get(REST_API_URL, {
        params: searchCondition,
      })
      .then((res) => {
        boardList.value = res.data;
      });
  };

  return {
    boardList,
    getBoardList,
    createBoard,
    board,
    getBoard,
    updateBoard,
    deleteBoard,
    searchBoardList,
  };
});
