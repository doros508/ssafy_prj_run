import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue' // 얘랑

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView, // 얘는 수정해야 해! 참고하라고 남겨놨슈~_~
    },
  ],
})

export default router
