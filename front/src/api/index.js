import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/maratalk", // baseURL 수정
  timeout: 10000,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true, // CORS 인증 설정 추가
});

// 응답 인터셉터
api.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error("API Error:", error);
    return Promise.reject(error);
  }
);

export default api;
