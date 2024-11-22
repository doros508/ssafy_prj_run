<template>
  <div class = "weatherWidget">
    <h4><i class="bi bi-geo-alt-fill"></i>유성구 날씨정보</h4>
    <div><i class="bi bi-thermometer-half"></i> 기온 : {{ tmp }}℃</div>
    <div><i class="bi bi-thermometer-half"></i> 최고기온 : {{ tmx }}℃</div>
    <div><i class="bi bi-thermometer-half"></i> 최저기온 : {{ tmn }}℃</div>
    <div><i class="bi bi-cloud-fill"></i> 하늘상태 : {{ sky }}</div>
    <div><i class="bi bi-cloud-rain-fill"></i> 강수형태 : {{ pty }}</div>
    <div><i class="bi bi-droplet-fill"></i> 강수확률 : {{ pop }}%</div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import axios from "axios";
const tmp = ref('로딩 중...');
const tmx = ref('로딩 중...');
const tmn = ref('로딩 중...');
const sky = ref('로딩 중...');
const pty = ref('로딩 중...');
const pop = ref('로딩 중...');

// const tmx = ref(null);
// const tmn = ref(null);
// const tmp = ref(null);
// const sky = ref(null);
// const pty = ref(null);
// const pop = ref(0);
onMounted(() => {
  const API_URL = `http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst`;

  const today = new Date();
  let year = today.getFullYear();
  let month = today.getMonth() + 1;
  let day = today.getDate();
  month = month < 10 ? "0" + month : month;
  day = day < 10 ? "0" + day : day;
  let todayStr = `${year}${month}${day}`;

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
      ServiceKey:"zwAO6IQSX/4n4/Hoq0UNV+G7I2RtEt6TwRhfs8tS2PuCZYpfRGr41iYMOl727nPrUJ9teOMkIhG4nZNChc3gfA==",
      // import.meta.env.VITE_WEATHER_API_KEY,
      dataType: "JSON",
      base_date: todayStr,
      base_time: baseTime,
      numOfRows: 100,
      nx: 66, //유성
      ny: 100,
    },
    withCredentials: false, 
  })
  
  .then((response) => {
    console.log("전체 응답:", response); // 전체 응답 구조 확인
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

<style>
.weatherWidget{
  color: #FFFFFF;
  background-color: #212529;
  border-radius: 10px;
  padding: 10px;
  margin-bottom: 10px;
}


.bi {
  margin-right: 8px;
}

.bi-thermometer-half {
  color: #ff9800;
}

.bi-cloud-fill {
  color: #a9a9a9;
}

.bi-cloud-rain-fill {
  color: #4a90e2;
}

.bi-droplet-fill {
  color: #4a90e2;
}

.bi-geo-alt-fill {
  color: #dc3545;
}
</style>
