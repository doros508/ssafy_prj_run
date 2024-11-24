import { defineStore } from "pinia";
import axios from "axios";
import router from "@/router";

export const useUserStore = defineStore("user", {
  state: () => ({
    user: null,
    isLoggedIn: false,
  }),

  actions: {
    async login(userId, userPassword) {
      try {
        const response = await axios.post("/maratalk/login", {
          userId,
          userPassword,
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

    logout() {
      try {
        axios.post("/maratalk/logout");
        this.user = null;
        this.isLoggedIn = false;
        localStorage.removeItem("user");
        router.push("/login");
      } catch (error) {
        console.error("로그아웃 실패:", error);
      }
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
      }
    },

    clearUser() {
      this.user = null;
      this.isLoggedIn = false;
      localStorage.removeItem("user");
    },
  },

  persist: true,
});
