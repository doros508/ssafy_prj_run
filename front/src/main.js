import { createApp } from "vue";
import { createPinia } from "pinia";

import App from "./App.vue";
import router from "./router";
import axios from "axios";

axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.withCredentials = true

// CORS 대응을 위한 추가 설정
axios.defaults.withCredentials = true;

const app = createApp(App);
const pinia = createPinia();

app.use(createPinia());
app.use(router);

app.mount("#app");
