import Vue from "vue";
import VueRouter from "vue-router";
import LoginPage from "./components/LoginForm";
import RewardBoard from "./components/RewardBoard";
import RewardBoardDetail from "./components/RewardBoardDetail";


Vue.use(VueRouter); // vue 에서 vue router 를 사용하기 위해 알려줘야합니다.

const router = new VueRouter({
  mode: "history", // browser history mode 를 사용합니다.
  routes: [
    { path: "/", component: LoginPage },
    { path: "/login", component: LoginPage },
    {path: "/board", component: RewardBoard},
    {path: "/board/detail", component: RewardBoardDetail}
  ] // path 별 component 를 추가합니다.
});

export default router;