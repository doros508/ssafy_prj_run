import { createRouter, createWebHistory } from 'vue-router'
import MainView from '@/views/MainView.vue'
import BoardView from '@/views/BoardView.vue'
import BoardList from '@/components/board/BoardList.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/maratalk',
      name: 'main',
      component: MainView,
    },
    {
      path: '/maratalk/board',
      name: 'board',
      component: BoardView,
      children: [
        {
        path: '',
        name: 'boardList',
        component: BoardList,
        },
      ]
    },
  ],
})

export default router
