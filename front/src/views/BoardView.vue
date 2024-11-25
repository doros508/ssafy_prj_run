<template>
  <div>
    <header>
      <TheHeader />
    </header>
    <div class="body">
      <div class="main">
        <main>
          <RouterView />
        </main>
        <aside class="right">
          <MainAside />
        </aside>
      </div>
    </div>
  </div>
</template>

<script>
import TheHeader from "@/components/common/TheHeader.vue";
import MainAside from "@/components/main/aside/MainAside.vue";

export default {
  components: {
    TheHeader,
    MainAside,
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
* {
  margin: 0px;
  box-sizing: border-box;
}

/* - 글씨 작은 버전
 .body {
  display: flex;
  justify-content: center;
  height: 100vb;
} */

.body {
  height: 100%;
  background-color: #181818;
  font-family: sans-serif;
  color: white;
  font-size: 24px;
  display: flex;
  flex-direction: column;
  background-image: url("src/assets/common/backgroundSample/원본 7.png");
}

.main {
  width: 100%;
  display: flex;
  justify-content: center;
  height: 100%;
}

main {
  width: 70%;
  display: flex;
  justify-content: center;
}

.right {
  margin: 20px;
}

.background-img {
  position: absolute;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: -1;
}
</style>
