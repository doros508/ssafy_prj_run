import { defineStore } from "pinia";

export const useUserStore = defineStore("user", {
  //새로고침 상태유지 persist
  persist: true,

  state: () => ({
    user: null,
    isLoggedIn: false,
  }),
  actions: {
    setUser(userData) {
      this.user = userData;
    },
    setIsLoggedIn(status) {
      this.isLoggedIn = status;
    },
    logout() {
      this.user = null;
      this.isLoggedIn = false;
      this.token = null;
    },
  },
  getters: {
    getCurrentUser: (state) => state.user,
    getIsLoggedIn: (state) => state.isLoggedIn,
    getToken: (state) => state.token,
  },
  persist: true, // 상태 유지 (옵션)
});
