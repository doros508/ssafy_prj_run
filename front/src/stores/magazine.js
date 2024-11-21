import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from 'axios'
import router from '@/router'

const REST_API_URL = `http://localhost:8080/maratalk/magazine`

export const useMagazineStore = defineStore("magazine", () => {
  
    // 매거진 전체 조회 및 검색 조회
    const magazineList = ref([]);
  const getMagazineList = function () {
    axios.get(REST_API_URL, {

    })
    .then((response) => {
      console.log(response.data);
      magazineList.value = response.data;
      console.log("안보이지만 매거진 다 뜬거다잉")
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

  // 매거진 상세 조회
  const magazine = ref({})
  const getMagazine = function (magazineNo) {
    axios.get(`${REST_API_URL}/${magazineNo}`)
    .then((response)=>{
      magazine.value = response.data
      console.log(magazine)
      router.push({ name: 'magazineDetail', params: { magazineNo } })
    })
    .catch((error)=>{
      console.log(magazineNo)
      console.log(magazine)
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

  return { magazineList, getMagazineList, magazine, getMagazine };
});

