import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from 'axios'
import router from '@/router'

const REST_API_URL = `http://localhost:8080/maratalk/board`

export const useBoardStore = defineStore("board", () => {
  const boardList = ref([]);

  const getBoardList = function () {
    axios.get(REST_API_URL, {

    })
    .then((response) => {
      console.log(response.data);
      boardList.value = response.data;
    });
  };

  // 게시글 등록
  const createBoard = function(board) {
    const boardData = {
      boardTitle: board.boardTitle,
      boardContent: board.boardContent
    }

    axios.post(REST_API_URL, boardData, {
      headers: {
        'Content-Type': 'application/json',
      }
    })
    .then(()=>{
      console.log("완료");
      router.push({name: 'boardList'});
    })
    .catch((error)=>{
      console.log("실패", error)
    });
  };

  const board = ref({})

  const getBoard = function (boardNo) {
    axios.get(`${REST_API_URL}/${boardNo}`)
    .then((response)=>{
      board.value = response,data
    })
  }

  const updateBoard = function () {
    axios.put(REST_API_URL, board.value)
    .then(() => {
      router.push({ name: 'boardList' })
    })
  }

  const searchBoardList = function (searchCondition) {
    axios.get(REST_API_URL, {
      params:searchCondition
    })
    .then((res)=>{
      boardList.value = res.data
    })
  }

  return { boardList, getBoardList, createBoard, board, getBoard, updateBoard, searchBoardList };
});

