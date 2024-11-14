import { createRouter, createWebHistory } from "vue-router";
// views
import MainView from "@/views/MainView.vue"; // 메인 뷰
import BoardView from "@/views/BoardView.vue"; // 보드 뷰
import MagazineView from "@/views/MagazineView.vue"; // 메거진 뷰
import CrewView from "@/views/CrewView.vue";  // 크루 뷰
import RaceView from "@/views/RaceView.vue";

// components
import BoardList from "@/components/board/BoardList.vue";
import MagazineList from "@/components/magazine/MagazineList.vue";
import CrewList from "@/components/crew/CrewList.vue";
import RaceList from "@/components/race/RaceList.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainView,
    },
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
      ],
    },
    {
      path: "/magazine",
      name: "magazine",
      component: MagazineView,
      children: [
        {
          path: "",
          name: "magazineList",
          component: MagazineList,
        }
      ]
    },
    {
      path: "/crew",
      name: "crew",
      component: CrewView,
      children: [
        {
          path: "",
          name: "crewList",
          component: CrewList,
        },
      ],
    },
    {
      path: "/race",
      name: "race",
      component: RaceView,
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

export default router;
