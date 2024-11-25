<template>
  <div>
    <TheHeader />
    <div class="crew-picture">
      <CrewMain @filter-crews="updateFilteredCrews" />
      <KakaoView :crews="filteredCrews" />
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import TheHeader from "@/components/common/TheHeader.vue";
import CrewMain from "@/components/crew/CrewMain.vue";
import KakaoView from "@/components/crew/kakaoView.vue";

export default {
  components: {
    TheHeader,
    CrewMain,
    KakaoView,
  },
  mounted() {
    // 1. window.embeddedChatbotConfig 설정
    window.embeddedChatbotConfig = {
      chatbotId: "TD2TzvmI-IkK-9GsOKgV7",
      domain: "www.chatbase.co",
    };

    // 2. 외부 스크립트 추가
    const script = document.createElement("script");
    script.src = "https://www.chatbase.co/embed.min.js";
    script.setAttribute("chatbotId", "TD2TzvmI-IkK-9GsOKgV7");
    script.setAttribute("domain", "www.chatbase.co");
    script.defer = true;
    document.body.appendChild(script); // <body>에 스크립트 추가
  },
};

// 필터링된 크루 데이터를 부모에서 관리
const filteredCrews = ref([]);

// CrewMain에서 발생한 검색 이벤트를 처리하는 함수
const updateFilteredCrews = (crews) => {
  filteredCrews.value = crews;
};
</script>

<style scoped>
/* 배경 이미지 설정 */
.crew-picture {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background-image: url("../../assets/common/backgroundSample/원본 8.png");
  background-size: cover;
  background-position: center;
}
</style>
