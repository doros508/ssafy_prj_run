import { createRouter, createWebHistory } from "vue-router";
import { useUserStore } from "@/stores/userStore"; // Pinia 스토어 추가

// views
import MainView from "@/views/MainView.vue";
import UserView from "@/views/UserView.vue";
import BoardView from "@/views/BoardView.vue";
import MagazineView from "@/views/MagazineView.vue";
import CrewView from "@/views/CrewView.vue";
import RaceView from "@/views/RaceView.vue";

// components
import BoardList from "@/components/board/BoardList.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardDetail from "@/components/board/BoardDetail.vue";

import Login from "@/components/user/Login.vue";
import SignUp from "@/components/user/SignUp.vue";

import MagazineList from "@/components/magazine/MagazineList.vue";
import CrewList from "@/components/crew/CrewList.vue";
import RaceList from "@/components/race/RaceList.vue";

import MagazineZone from "@/components/main/content/MainContentMagazine.vue";
import CommunityZone from "@/components/main/content/MainContentCommunity.vue";
import Welcome from "@/components/main/MainWelcome.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // 메인 페이지
    {
      path: "/",
      name: "main",
      component: MainView,
      children: [
        {
          path: "",
          name: "mainWelcome",
          component: Welcome,
        },
        {
          path: "",
          name: "mainMagazine",
          component: MagazineZone,
        },
        {
          path: "",
          name: "mainCommunity",
          component: CommunityZone,
        },
      ],
    },

    // 유저 페이지
    {
      path: "/user",
      name: "user",
      component: UserView,
      children: [
        {
          path: "signup",
          name: "signUp",
          component: SignUp,
          beforeEnter: (to, from, next) => {
            const userStore = useUserStore();
            if (userStore.getIsLoggedIn) {
              next({ name: "main" });
            } else {
              next();
            }
          },
        },
        {
          path: "login",
          name: "login",
          component: Login,
          beforeEnter: (to, from, next) => {
            const userStore = useUserStore();
            if (userStore.getIsLoggedIn) {
              next({ name: "main" });
            } else {
              next();
            }
          },
        },
      ],
    },

    // 게시판 페이지
    {
      path: "/board",
      name: "board",
      component: BoardView,
      redirect: { name: "boardList" },
      children: [
        {
          path: "",
          name: "boardList",
          component: BoardList,
        },
        {
          path: "write",
          name: "boardWrite",
          component: BoardWrite,
          meta: { requiresAuth: true },
        },
        {
          path: ":id",
          name: "boardDetail",
          component: BoardDetail,
          props: true,
        },
      ],
    },

    // 메거진 페이지
    {
      path: "/magazine",
      name: "magazine",
      component: MagazineView,
      redirect: { name: "magazineList" },
      children: [
        {
          path: "",
          name: "magazineList",
          component: MagazineList,
        },
      ],
    },

    // 크루 페이지
    {
      path: "/crew",
      name: "crew",
      component: CrewView,
      redirect: { name: "crewList" },
      children: [
        {
          path: "list",
          name: "crewList",
          component: CrewList,
        },
      ],
    },

    // 대회 페이지
    {
      path: "/race",
      name: "race",
      component: RaceView,
      children: [
        {
          path: "list",
          name: "raceList",
          component: RaceList,
        },
      ],
    },
  ],
});

// 전역 네비게이션 가드
router.beforeEach((to, from, next) => {
  const userStore = useUserStore();

  // 인증이 필요한 라우트 체크
  if (to.meta.requiresAuth && !userStore.getIsLoggedIn) {
    // 로그인되지 않은 사용자를 로그인 페이지로 리다이렉트
    next({
      name: "login",
      query: { redirect: to.fullPath },
    });
  } else {
    next();
  }
});

export default router;
