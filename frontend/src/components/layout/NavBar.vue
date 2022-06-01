<template>
  <v-toolbar light dense max-height="50">
    <v-toolbar-title>
      <router-link to="/">
        <v-icon>fa-home</v-icon>
        HappyHouse</router-link
      >
    </v-toolbar-title>
    <v-toolbar-items class="ml-5">
      <v-tabs>
        <router-link :to="{ name: 'map' }"
          ><v-tab class="mt-3">홈</v-tab></router-link
        >
        <router-link :to="{ name: 'notice' }"
          ><v-tab class="mt-3">공지사항</v-tab></router-link
        >
        <router-link :to="{ name: 'qna' }"
          ><v-tab class="mt-3">QnA</v-tab></router-link
        >
      </v-tabs>
    </v-toolbar-items>
    <v-spacer></v-spacer>
    <div>
      <span v-if="isLogin">{{ userInfo.name }} 님, 환영합니다. </span>
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-icon v-bind="attrs" v-on="on">fa fa-user-circle-o</v-icon>
        </template>
        <v-list>
          <v-list-item v-if="!isLogin">
            <v-list-item-title>
              <router-link :to="{ name: 'signUp' }" class="link"
                >회원가입</router-link
              >
            </v-list-item-title>
          </v-list-item>
          <v-list-item v-if="!isLogin">
            <v-list-item-title>
              <router-link :to="{ name: 'signIn' }" class="link"
                >로그인
              </router-link>
            </v-list-item-title>
          </v-list-item>
          <v-list-item v-if="isLogin">
            <v-list-item-title>
              <router-link
                :to="{ name: 'mypage' }"
                class="link align-self-center"
                >내정보보기
              </router-link>
            </v-list-item-title>
          </v-list-item>
          <v-list-item v-if="isLogin">
            <v-list-item-title link @click.prevent="onClickLogout">
              로그아웃
            </v-list-item-title>
          </v-list-item>
          <v-list-item v-if="!isLogin">
            <v-list-item-title>
              <router-link
                :to="{ name: 'findpassword' }"
                class="link align-self-center"
                >비밀번호 찾기
              </router-link>
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </div>
  </v-toolbar>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";
export default {
  name: "NavBar",
  data() {
    return {
      items: [
        { title: "Click Me" },
        { title: "Click Me" },
        { title: "Click Me" },
        { title: "Click Me 2" },
      ],
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "map" });
    },
  },
};
</script>

<style scoped>
a:hover {
  text-decoration: none;
}
</style>
