<template>
  <div>
    <h4>날씨정보</h4>
    <div>기온 : {{ tmp }}℃</div>
    <div>하늘상태 : {{ sky }}</div>
    <div>강수형태 : {{ pty }}</div>
    <div>강수확률 : {{ pop }}%</div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import axios from "axios";
const tmp = ref(null);
const sky = ref(null);
const pty = ref(null);
const pop = ref(0);
onMounted(() => {
  const API_URL = `http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst`;

  const today = new Date();
  let year = today.getFullYear();
  let month = today.getMonth() + 1;
  let day = today.getDate();
  month = month < 10 ? "0" + month : month;
  day = day < 10 ? "0" + day : day;
  const todayStr = `${year}${month}${day}`;

  // API 발표 시각 배열 (base_time)
  const baseTimes = [
    "0200",
    "0500",
    "0800",
    "1100",
    "1400",
    "1700",
    "2000",
    "2300",
  ];
  // API 제공 시각 배열 (실제 데이터 이용 가능 시각, 발표시각 + 10분)
  const availableTimes = [
    "0210",
    "0510",
    "0810",
    "1110",
    "1410",
    "1710",
    "2010",
    "2310",
  ];

  // 현재 시각 (HHMM 형식)
  const currentHour = today.getHours().toString().padStart(2, "0");
  const currentMinute = today.getMinutes().toString().padStart(2, "0");
  const currentTime = currentHour + currentMinute;

  // 현재 시각보다 이전인 가장 최근 발표 시각 찾기
  let baseTime = "0200"; // 기본값
  for (let i = availableTimes.length - 1; i >= 0; i--) {
    if (currentTime >= availableTimes[i]) {
      baseTime = baseTimes[i];
      break;
    }
  }
  // 현재 시각이 02:10 이전인 경우 전날 마지막 발표 데이터 사용
  if (currentTime < "0210") {
    const yesterday = new Date(today);
    yesterday.setDate(yesterday.getDate() - 1);
    year = yesterday.getFullYear();
    month = (yesterday.getMonth() + 1).toString().padStart(2, "0");
    day = yesterday.getDate().toString().padStart(2, "0");
    todayStr = `${year}${month}${day}`;
    baseTime = "2300";
  }

  axios
    .get(API_URL, {
      params: {
        ServiceKey: import.meta.env.VITE_WEATHER_API_KEY,
        dataType: "JSON",
        base_date: todayStr,
        base_time: baseTime,
        numOfRows: 60,
        nx: 66,
        ny: 100,
      },
      withCredentials: false,
    })
    .then((response) => {
      console.log("API 호출 시각:", todayStr, baseTime);
      console.log("날씨 데이터:", response.data.response.body.items.item);
      return response.data.response.body.items.item;
    })
    .then((items) => {
      items.forEach((item) => {
        if (item.category === "TMP") {
          tmp.value = item.fcstValue;
        } else if (item.category === "TMN") {
          tmn.value = item.fcstValue;
        } else if (item.category === "TMX") {
          tmx.value = item.fcstValue;
        } else if (item.category === "SKY") {
          switch (item.fcstValue) {
            case "1":
              sky.value = "맑음";
              break;
            case "3":
              sky.value = "구름많음";
              break;
            case "4":
              sky.value = "흐림";
              break;
          }
        } else if (item.category === "PTY") {
          switch (item.fcstValue) {
            case "0":
              pty.value = "없음";
              break;
            case "1":
              pty.value = "비";
              break;
            case "2":
              pty.value = "비/눈";
              break;
            case "3":
              pty.value = "눈";
              break;
            case "4":
              pty.value = "소나기";
              break;
          }
        } else if (item.category === "POP") {
          pop.value = item.fcstValue;
        }
      });
    });
});
</script>

<style scoped></style>
