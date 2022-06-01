<template>
  <v-container fluid fill-height v-if="userInfo">
    <v-layout justify-center>
      <v-flex xs12 sm10 md6>
        <v-card class="elevation-12"
          ><v-toolbar
            dark
            color="blue"
            elevation="7"
            prominent
            src="https://i.pinimg.com/564x/55/5d/42/555d425ba3f29f21c8f037c545f83bfc.jpg"
          >
            <v-toolbar-title><h3>회원 정보</h3></v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <v-form>
              <v-text-field
                id="userid"
                required
                label="ID"
                v-model="userInfo.id"
                type="text"
                disabled
              ></v-text-field>

              <v-text-field
                type="text"
                id="useremail"
                required
                placeholder="이메일 수정"
                v-model="userInfo.email"
                @keyup.enter="registerUser"
                name="이메일"
                label="이메일"
              ></v-text-field>

              <v-text-field
                type="text"
                id="username"
                v-bind="username"
                required
                v-model="userInfo.name"
                placeholder="이름 수정"
                @keyup.enter="registerUser"
                name="이름"
                label="이름"
              ></v-text-field>

              <v-text-field
                type="password"
                id="userpw"
                v-model="userpw"
                required
                placeholder="비밀번호 수정"
                @keyup.enter="confirm"
                name="비밀번호"
                label="비밀번호"
              ></v-text-field>
            </v-form>
          </v-card-text>
          <v-btn type="button" variant="primary" class="m-1" @click="modifyUser"
            >수정
          </v-btn>
          <v-btn type="button" color="primary" class="m-1" @click="cancelUser"
            >회원 탈퇴
          </v-btn>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  data() {
    return {
      userid: "",
      userpw: "",
      useremail: "",
      username: "",
    };
  },
  component: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapGetters(memberStore, ["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["deleteUserInfo", "modifyUserInfo"]),
    // 회원 탈퇴
    async cancelUser() {
      let token = sessionStorage.getItem("access-token");
      await this.deleteUserInfo(token);
      this.$router.push({ name: "signUp" });
    },
    // 회원 정보 수정
    async modifyUser() {
      let newUserInfo = {
        id: this.userInfo.id,
        password: this.userpw,
        email: this.userInfo.email,
        name: this.userInfo.name,
      };
      await this.modifyUserInfo(newUserInfo);
    },
  },
};
</script>

<style>
.v-main__wrap {
  /* background: lightgray; */
}
</style>
