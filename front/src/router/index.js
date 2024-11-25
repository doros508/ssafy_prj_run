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
// 게시판
import BoardList from "@/components/board/BoardList.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardDetail from "@/components/board/BoardDetail.vue";
import BoardUpdate from "@/components/board/BoardUpdate.vue";

// 유저
import Login from "@/components/user/Login.vue";
import SignUp from "@/components/user/SignUp.vue";

import MagazineList from "@/components/magazine/MagazineList.vue";
import CrewList from "@/components/crew/CrewMain.vue";
import RaceList from "@/components/race/RaceList.vue";

import MagazineZone from "@/components/main/content/MainContentMagazine.vue";
import CommunityZone from "@/components/main/content/MainContentCommunity.vue";
import Welcome from "@/components/main/MainWelcome.vue";

import MagazineDetail from "@/components/magazine/MagazineDetail.vue";

import KakaoView from "@/components/crew/kakaoView.vue";
import MagazineWrite from "@/components/magazine/MagazineWrite.vue";
import MagazineUpdate from "@/components/magazine/MagazineUpdate.vue";
import RaceDetail from "@/components/race/RaceDetail.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // 메인 페이지
    {
      path: "/",
      name: "main",
      component: MainView,
      meta: { requiresAuth: false }, // 추가
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
          meta: { hideAuthButton: true }, // 메타 필드 추가
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
          meta: { requiresAuth: false }, // 추가
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
      meta: { requiresAuth: false }, // 추가
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
          path: ":boardNo",
          name: "boardDetail",
          component: BoardDetail,
        },
        {
          path: "update/:boardNo",
          name: "boardUpdate",
          component: BoardUpdate,
          requiresAuth: false,
        },
      ],
    },

    // 메거진 페이지
    {
      path: "/magazine",
      name: "magazine",
      component: MagazineView,
      meta: { requiresAuth: false }, // 추가
      redirect: { name: "magazineList" },
      children: [
        {
          path: "",
          name: "magazineList",
          component: MagazineList,
        },
        {
          path: ":magazineNo",
          name: "magazineDetail",
          component: MagazineDetail,
        },
        {
          path: "write",
          name: "magazineWrite",
          component: MagazineWrite,
        },
        {
          path: "update/:magazineNo",
          name: "magazineUpdate",
          component: MagazineUpdate,
        },
      ],
    },

    // 크루 페이지
    {
      path: "/crew",
      name: "crew",
      component: CrewView,
      meta: { requiresAuth: false }, // 추가
      redirect: { name: "crewList" },
      children: [
        {
          path: "list",
          name: "crewList",
          component: CrewList,
        },
        {
          path: "/kakao",
          name: "kakao",
          component: KakaoView,
        },
      ],
    },

    // 대회 페이지
    {
      path: "/race",
      name: "race",
      component: RaceView,
      meta: { requiresAuth: false }, // 추가
      redirect: { name: "raceList" },
      children: [
        {
          path: "",
          name: "raceList",
          component: RaceList,
        },
        {
          path: ":raceNo",
          name: "raceDetail",
          component: RaceDetail,
        },
      ],
    },
  ],
});

// 전역 네비게이션 가드
router.beforeEach((to, from, next) => {
  const userStore = useUserStore();

  




    // 이미 로그인한 사용자가 로그인/회원가입 페이지로 접근하는 경우
    if ((to.name === 'login' || to.name === 'signUp') && userStore.getIsLoggedIn) {
      next({ name: 'main' });
      return;
    }

 // 로그인 상태 확인 방식 수정
 if (to.meta.requiresAuth && !userStore.isLoggedIn) {
  alert("로그인 후에 이용해주세요.");
  next({
    name: "login",
    query: { redirect: to.fullPath },
  });
  return;
}

next();
});

export default router;
