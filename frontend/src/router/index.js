import Vue from "vue";
import VueRouter from "vue-router";
import MapView from "../views/MapView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "map",
    component: MapView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "singin",
        name: "signIn",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "singup",
        name: "signUp",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "mypage",
        name: "mypage",
        // beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/MemberMyPage.vue"),
      },
      {
        path: "findpassword",
        name: "findpassword",
        // beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/FindPassword.vue"),
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    component: () => import("@/views/NoticeView.vue"),
    redirect: "/notice/list/",
    children: [
      {
        path: "list",
        name: "noticelist",
        component: () => import("@/components/notice/NoticeList.vue"),
      },
      {
        path: "create",
        name: "noticecreate",
        component: () => import("@/components/notice/NoticeCreate.vue"),
      },
      {
        path: "modify/:noticeno",
        name: "noticemodify",
        component: () => import("@/components/notice/NoticeModify.vue"),
      },
      {
        path: "delete/:noticeno",
        name: "noticedelete",
        component: () => import("@/components/notice/NoticeDelete.vue"),
      },
      {
        path: "detail/:noticeno",
        name: "noticedetail",
        component: () => import("@/components/notice/NoticeDetail.vue"),
      },
    ],
  },
  {
    path: "/qna",
    name: "qna",
    component: () => import("@/views/QnaView.vue"),
    redirect: "/qna/list/",
    children: [
      {
        path: "list",
        name: "list",
        component: () => import("@/components/qna/QnaList.vue"),
      },
      {
        path: "create",
        name: "create",
        component: () => import("@/components/qna/QnaCreate.vue"),
      },
      {
        path: "delete/:qnano",
        name: "delete",
        component: () => import("@/components/qna/QnaDelete.vue"),
      },
      {
        path: "modify/:qnano",
        name: "modify",
        component: () => import("@/components/qna/QnaModify.vue"),
      },
      {
        path: "detail/:qnano",
        name: "detail",
        component: () => import("@/components/qna/QnaDetail.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
