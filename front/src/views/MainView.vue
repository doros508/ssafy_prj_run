<template>
  <div class="main-container">
    <div class="main-content">
      <TheHeader />
      <MainWelcome />
      <div class="content-box">
        <div class="content">
          <!-- <MainContent/> -->
          <MainContentMagazine />
          <MainContentCommunity />
        </div>
        <div class="right">
          <MainAside />
        </div>
      </div>
    </div>
    <!-- 이 자리에 footer -->
  </div>
</template>

<script>
import TheHeader from "@/components/common/TheHeader.vue";
import MainWelcome from "../components/main/MainWelcome.vue";
import MainContent from "@/components/main/content/MainContent.vue";
import MainAside from "@/components/main/aside/MainAside.vue";
import MainContentMagazine from "@/components/main/content/MainContentMagazine.vue";
import MainContentCommunity from "@/components/main/content/MainContentCommunity.vue";

export default {
  components: {
    TheHeader,
    MainWelcome,
    MainContent,
    MainAside,
    MainContentMagazine,
    MainContentCommunity,
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
</script>



<style scoped>
.main-container {
  background-color: #141414;
  color: #fff;
  background-color: #181818;
  font-family: sans-serif;
  color: white;
  font-size: 24px;
}
.content-box {
  display: flex;
  padding: 20px;
}
/* .right {
  position: relative;
} */
.content {
  width: 80%;
}
</style>
