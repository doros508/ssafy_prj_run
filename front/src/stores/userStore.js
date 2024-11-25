import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

export const useUserStore = defineStore("user", {
  state: () => ({
    user: null,
    isLoggedIn: false,
  }),
  
  getters: {
    getIsLoggedIn: (state) => state.isLoggedIn,
    getUserInfo: (state) => state.user
  },

  actions: {
    async login(userId, userPassword) {
      try {
        const response = await axios.post("/maratalk/login", {
          userId,
          userPassword,
        },  {
          withCredentials: true
        });

        if (response.data && response.data.user) {
          this.user = response.data.user;
          this.isLoggedIn = true;
          localStorage.setItem("user", JSON.stringify(response.data.user));
          router.push("/");
          return true;
        }
        return false;
      } catch (error) {
        console.error("로그인 실패:", error);
        return false;
      }
    },

    clearUser() {
      this.user = null;
      this.isLoggedIn = false;
      localStorage.removeItem("user");
    },

    initializeFromLocalStorage() {
      const savedUser = localStorage.getItem("user");
      if (savedUser) {
        try {
          this.user = JSON.parse(savedUser);
          this.isLoggedIn = true;
        } catch (error) {
          this.clearUser();
        }
      } else {
        this.clearUser(); // 저장된 사용자 정보가 없으면 초기화
      }
    }
  }
});

