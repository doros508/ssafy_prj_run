import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useBoardStore = defineStore("board", () => {
  const boardList = ref([]);
  const getBoardList = function () {
    axios.get(REST_API_URL, {}).then((response) => {
      console.log(response.data);
      boardList.value = response.data;
    });
  };

  return { boardList, getBoardList };
});
