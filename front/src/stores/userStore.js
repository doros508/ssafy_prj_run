import { defineStore } from "pinia";

export const useUserStore = defineStore("user", {
  state: () => ({
    user: null,
    isLoggedIn: false,
  }),
  actions: {
    setUser(userData) {
      this.user = userData;
      this.isLoggedIn = !!userData;
      if (userData) {
        localStorage.setItem('user', JSON.stringify(userData));
      }
    },
    clearUser() {
      this.user = null;
      this.isLoggedIn = false;
      localStorage.removeItem('user');
    },
    initializeFromLocalStorage() {
      const savedUser = localStorage.getItem('user');
      if (savedUser) {
        try {
          const user = JSON.parse(savedUser);
          this.setUser(user);
        } catch (error) {
          this.clearUser();
        }
      } else {
        this.clearUser();
      }
    }
  },
  getters: {
    getUser: (state) => state.user,
    getIsLoggedIn: (state) => state.isLoggedIn
  }
});
