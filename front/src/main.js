import { createApp } from "vue";
import { createPinia } from "pinia";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";

import "bootstrap";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import "bootstrap-icons/font/bootstrap-icons.css";

import App from "./App.vue";
import router from "./router";
import axios from "axios";

import { useKakao } from "vue3-kakao-maps/@utils";

useKakao(import.meta.env.VITE_KAKAOMAP_API_KEY);

axios.defaults.baseURL = "http://localhost:8080"; // 로컬
// axios.defaults.baseURL = "http://192.168.210.63:8080"; // 종수
// axios.defaults.baseURL = "http://192.168.210.53:8080"; // 건우
axios.defaults.withCredentials = true;
axios.defaults.headers.common["Content-Type"] = "application/json";

const app = createApp(App);
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

app.use(pinia);
app.use(router);
app.mount("#app");
