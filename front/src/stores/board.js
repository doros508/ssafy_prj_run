import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";
import { useUserStore } from "@/stores/userStore";

const REST_API_URL = `http://localhost:8080/maratalk/board`;

export const useBoardStore = defineStore("board", () => {
  const boardList = ref([]);
  const board = ref({});

  const getBoardList = function () {
    axios.get(REST_API_URL, {
      withCredentials: true
    }).then((response) => {
      boardList.value = response.data;
    });
  };

  const createBoard = async function(formData) {
    const userStore = useUserStore();
    
    try {
      if (!userStore.isLoggedIn || !userStore.user) {
        alert("로그인이 필요한 서비스입니다.");
        router.push({ name: "login" });
        return;
      }

      const response = await axios.post(REST_API_URL, formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        },
        withCredentials: true
      });
      
      if (response.data) {
        router.push({ name: "boardList" });
        return response.data;
      }
    } catch (error) {
      console.error("게시글 작성 실패:", error);
      if (error.response?.status === 500) {
        alert("서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
      } else {
        alert("게시글 작성에 실패했습니다.");
      }
      throw error;
    }
  };

  const getBoard = function (boardNo) {
    axios.get(`${REST_API_URL}/${boardNo}`, {
      withCredentials: true
    })
    .then((response) => {
      board.value = response.data;
      if (Array.isArray(board.value.boardFileList)) {
        board.value.boardFileList.forEach((file) => {
          file.imageUrl = `http://localhost:8080/uploads/board${file.path}/${file.systemName}`;
        });
      }
    })
    .catch((error) => {
      console.error("게시글 조회 실패:", error);
    });
  };

  const updateBoard = function (boardNo) {
    axios.put(`${REST_API_URL}/${boardNo}`, board.value, {
      withCredentials: true
    }).then(() => {
      router.push({ name: "boardList" });
    });
  };

  const deleteBoard = function (boardNo) {
    axios.delete(`${REST_API_URL}/${boardNo}`, {
      withCredentials: true
    }).then(() => {
      router.push({ name: "boardList" });
    });
  };

  const searchBoardList = function (searchCondition) {
    axios.get(REST_API_URL, {
      params: searchCondition,
      withCredentials: true
    }).then((res) => {
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