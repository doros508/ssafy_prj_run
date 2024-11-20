import { createApp } from "vue";
import { createPinia } from "pinia";
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate';

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import "bootstrap-icons/font/bootstrap-icons.css";

import App from "./App.vue";
import router from "./router";
import axios from "axios";

axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.withCredentials = true

// CORS 대응을 위한 추가 설정
axios.defaults.withCredentials = true;

const app = createApp(App);
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

app.use(pinia);
app.use(router);

app.mount("#app");