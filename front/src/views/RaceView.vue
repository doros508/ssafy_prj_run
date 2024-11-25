<template>
  <div class="body">
    <header>
      <TheHeader />
    </header>
    <div class="main">
      <main>
        <RouterView />
      </main>
      <aside class="right">
        <MainAside />
      </aside>
    </div>
    <footer></footer>
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
.body {
  height: 100vh; /* 여기 */
  background: #181818;
  font-family: sans-serif;
  color: white;
  font-size: 24px;
  display: flex;
  flex-direction: column;
}
/* .header {

    } */
/* .main {
  display: flex;
  flex: 1;
}

main {
  display: flex;
  flex: 5 5 150px;
  justify-content: center;
  
}

.right {
  padding: 3em 0 3em 0;
  flex: 1 1 100px;
} */

.main {
  width: 100%;
  display: flex;
  justify-content: center;
  height: 100%;
  width: 100%;
  position: relative;
  /* background-image: url("src/assets/common/backgroundSample/원본 7.png"); */
  background-image: url("src/assets/common/backgroundSample/원본 4.png");
  background-size: cover;
  background-position: center;
  padding: 20px; /* 내부 여백 추가 */
}

main {
  width: 70%;
  display: flex;
  justify-content: center;
}

.right {
  margin: 20px;
}

@media all and (max-width: 550px) {
  .main {
    flex-direction: column;
  }
  main {
    padding: 5em 0 5em 0;
  }
}
</style>
