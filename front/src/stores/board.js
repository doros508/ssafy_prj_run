import { ref, computed } from "vue";
import { defineStore } from "pinia";import axios from 'axios'
import router from '@/router'

const REST_API_URL = `http://localhost:8080/maratalk/board` // 로컬
// const REST_API_URL = `http://192.168.210.63:8080/maratalk/board` //종수
// const REST_API_URL = `http://192.168.210.53:8080/maratalk/board` // 건우

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
  const createBoard = function(formData) {
    // const boardData = {
    //   boardTitle: board.boardTitle,
    //   boardContent: board.boardContent
    // }

    axios.post(REST_API_URL, formData, {
      headers: {
        // 'Content-Type': 'application/json',
        'Content-Type': 'multipart/form-data',
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

  // 게시글 조회
  const board = ref({})
  const getBoard = function (boardNo) {
    axios.get(`${REST_API_URL}/${boardNo}`)
    .then((response)=>{
      board.value = response.data
      console.log(board)
    })
    .catch((error)=>{
      console.log(boardNo)
      console.log("흠 잘 안되네", error)
    });
  }

  // 게시글 수정
  const updateBoard = function (boardNo) {
    axios.put(`${REST_API_URL}/${boardNo}`, board.value)
    .then(() => {
      router.push({ name: 'boardList' })
    })
  }

  // 게시글 삭제
  const deleteBoard = function(boardNo) {
    axios.delete(`${REST_API_URL}/${boardNo}`)
    .then(()=>{
      router.push({name: 'boardList'})
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

  return { boardList, getBoardList, createBoard, board, getBoard, updateBoard, deleteBoard, searchBoardList };
});

