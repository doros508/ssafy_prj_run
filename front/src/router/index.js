import { createRouter, createWebHistory } from "vue-router";
// views
import MainView from "@/views/MainView.vue"; // 메인 뷰
import UserView from "@/views/UserView.vue"; // 유저 뷰
import BoardView from "@/views/BoardView.vue"; // 보드 뷰
import MagazineView from "@/views/MagazineView.vue"; // 메거진 뷰
import CrewView from "@/views/CrewView.vue"; // 크루 뷰
import RaceView from "@/views/RaceView.vue";

// components
import BoardList from "@/components/board/BoardList.vue"; // 보드
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardDetail from "@/components/board/BoardDetail.vue";

import Login from "@/components/user/Login.vue"; // 유저
import SignUp from "@/components/user/SignUp.vue";

import MagazineList from "@/components/magazine/MagazineList.vue"; // 메거진

import CrewList from "@/components/crew/CrewList.vue"; // 크루

import RaceList from "@/components/race/RaceList.vue"; // 대회
import MagazineZone from "@/components/main/MagazineZone.vue";
import CommunityZone from "@/components/main/CommunityZone.vue";
import Welcome from "@/components/main/welcome.vue";

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
        },
        {
          path: "login",
          name: "login",
          component: Login,
        },
      ],
    },

    // 게시판 페이지
    {
      path: "/board",
      name: "board",
      component: BoardView,
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
        },
        {
          path: "detail",
          name: "boardDetail",
          component: BoardDetail,
        },
      ],
    },

    // 메거진 페이지
    {
      path: "/magazine",
      name: "magazine",
      component: MagazineView,
      children: [
        {
          path: "list",
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

export default router;
