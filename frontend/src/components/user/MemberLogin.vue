<template>
  <v-container fluid fill-height>
    <v-layout justify-center>
      <v-flex xs12 sm8 md6>
        <v-card class="elevation-12">
          <v-toolbar
            dark
            color="blue"
            elevation="7"
            prominent
            src="https://i.pinimg.com/564x/55/5d/42/555d425ba3f29f21c8f037c545f83bfc.jpg"
          >
            <v-toolbar-title><h3>로그인</h3></v-toolbar-title>
          </v-toolbar>
          <v-alert
            elevation="5"
            prominent
            color="indigo"
            type="warning"
            v-if="isLoginError"
            >아이디 또는 비밀번호를 확인하세요.</v-alert
          >
          <v-card-text>
            <v-form>
              <v-text-field
                id="userid"
                v-model="user.id"
                required
                placeholder="아이디 입력"
                @keyup.enter="confirm"
                label="아이디"
                type="text"
              ></v-text-field>
              <v-text-field
                type="password"
                id="userpwd"
                v-model="user.password"
                required
                placeholder="비밀번호 입력"
                @keyup.enter="confirm"
                name="password"
                label="비밀번호"
              ></v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn type="button" variant="primary" class="m-1" @click="confirm"
              >로그인
            </v-btn>
            <v-btn type="button" variant="success" class="m-1" @click="movePage"
              >회원가입
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        id: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
    refreshIsLogin() {
      this.refreshLoginError();
      return true;
    },
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      console.log("confirm start");
      console.log(this.user);
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        this.getUserInfo(token).then(this.$router.push({ name: "map" }));
      }
    },
    movePage() {
      this.$router.push({ name: "signUp" });
    },
  },
};
</script>

<style></style>
