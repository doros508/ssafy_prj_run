import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

const REST_API_URL = `http://localhost:8080/maratalk/magazine`; // 로컬
// const REST_API_URL = `http://192.168.210.63:8080/maratalk/magazine` //종수
// const REST_API_URL = `http://192.168.210.53:8080/maratalk/magazine` // 건우

export const useMagazineStore = defineStore("magazine", () => {
  const magazineList = ref([]);

  const getMagazineList = function () {
    axios.get(REST_API_URL, {}).then((response) => {
      console.log(response.data);
      magazineList.value = response.data;
    });
  };

  // 게시글 등록
  const createMagazine = function (formData) {
    // const magazineData = {
    //   magazineTitle: magazine.magazineTitle,
    //   magazineContent: magazine.magazineContent
    // }

    axios
      .post(REST_API_URL, formData, {
        headers: {
          // 'Content-Type': 'application/json',
          "Content-Type": "multipart/form-data",
        },
      })
      .then(() => {
        console.log("완료");
        router.push({ name: "magazineList" });
      })
      .catch((error) => {
        console.log("실패", error);
      });
  };

  // 게시글 조회
  const magazine = ref({});
  const getMagazine = function (magazineNo) {
    console.log("게시글 가져오기 시도(프론트)");
    axios
      .get(`${REST_API_URL}/${magazineNo}`)
      .then((response) => {
        magazine.value = response.data;
        console.log("매거진이 가져온 것은?");
        console.log(magazine.value.magazineFileList);
        // 파일 노출
        if (Array.isArray(magazine.value.magazineFileList)) {
          console.log("배열이군");
          magazine.value.magazineFileList.forEach((file) => {
            file.imageUrl = `http://localhost:8080/uploads/magazine${file.path}/${file.systemName}`;
            console.log("이미지 URL: " + file.imageUrl);
          });
        } else {
          console.log("배열이 아니군");
        }
      })
      .catch((error) => {
        console.log(magazineNo);
        console.log("흠 잘 안되네", error);
      });
  };

  // 게시글 수정
  const updateMagazine = function (magazineNo) {
    axios.put(`${REST_API_URL}/${magazineNo}`, magazine.value).then(() => {
      router.push({ name: "magazineList" });
    });
  };

  // 게시글 삭제
  const deleteMagazine = function (magazineNo) {
    axios.delete(`${REST_API_URL}/${magazineNo}`).then(() => {
      router.push({ name: "magazineList" });
    });
  };

  const searchMagazineList = function (searchCondition) {
    axios
      .get(REST_API_URL, {
        params: searchCondition,
      })
      .then((res) => {
        magazineList.value = res.data;
      });
  };

  return {
    magazineList,
    getMagazineList,
    createMagazine,
    magazine,
    getMagazine,
    updateMagazine,
    deleteMagazine,
    searchMagazineList,
  };
});
