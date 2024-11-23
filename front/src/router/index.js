import { createRouter, createWebHistory } from "vue-router";
import { useUserStore } from "@/stores/userStore";

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
import BoardUpdate from "@/components/board/BoardUpdate.vue";
import Login from "@/components/user/Login.vue";
import SignUp from "@/components/user/SignUp.vue";
import MagazineList from "@/components/magazine/MagazineList.vue";
import MagazineDetail from "@/components/magazine/MagazineDetail.vue";
import RaceList from "@/components/race/RaceList.vue";

// 메인 컴포넌트
import MagazineZone from "@/components/main/content/MainContentMagazine.vue";
import CommunityZone from "@/components/main/content/MainContentCommunity.vue";
import Welcome from "@/components/main/MainWelcome.vue";

// 크루 관련 컴포넌트
import CrewSearch from "@/components/crew/CrewSearch.vue";
import CrewMap from "@/components/crew/CrewMap.vue";
import CrewList from "@/components/crew/CrewList.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // 메인 페이지
    {
      path: "/",
      name: "main",
      component: MainView,
      meta: { requiresAuth: false },
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
          meta: { hideAuthButton: true },
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
          meta: { requiresAuth: false },
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
      meta: { requiresAuth: false },
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
        },
      ],
    },

    // 매거진 페이지
    {
      path: "/magazine",
      name: "magazine",
      component: MagazineView,
      meta: { requiresAuth: false },
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
      ],
    },

    // 크루 페이지
    {
      path: "/crew",
      name: "crew",
      component: CrewView,
      meta: { requiresAuth: false },
      children: [
        {
          path: "",
          name: "crewSearch",
          component: CrewSearch,
        },
        {
          path: "map",
          name: "crewMap",
          component: CrewMap,
        },
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
      meta: { requiresAuth: false },
      children: [
        {
          path: "",
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

  if (to.meta.requiresAuth && !userStore.getIsLoggedIn) {
    next({
      name: "login",
      query: { redirect: to.fullPath },
    });
  } else {
    next();
  }
});

export default router;
