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
            <v-toolbar-title><h3>회원가입</h3></v-toolbar-title>
          </v-toolbar>

          <v-card-text>
            <v-form>
              <v-text-field
                v-model="userid"
                :rules="idRule"
                placeholder="아이디 입력"
                @keyup.enter="confirm"
                label="아이디"
                type="text"
              ></v-text-field>

              <v-text-field
                type="text"
                v-model="useremail"
                :rules="emailRule"
                placeholder="이메일 입력"
                @keyup.enter="registerUser"
                name="이메일"
                label="이메일"
              ></v-text-field>

              <v-text-field
                type="text"
                v-model="username"
                :rules="rules"
                placeholder="이름 입력"
                @keyup.enter="registerUser"
                name="이름"
                label="이름"
              ></v-text-field>

              <v-text-field
                type="password"
                v-model="userpw"
                :rules="rules"
                placeholder="비밀번호 입력"
                @keyup.enter="confirm"
                name="password"
                label="비밀번호"
              ></v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn
              type="button"
              variant="primary"
              class="m-1"
              @click="registerUser"
              >회원가입
            </v-btn>

            <v-btn type="button" variant="primary" class="m-1" @click="movePage"
              >로그인
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import { mapActions, mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberRegister",
  data() {
    return {
      userid: "",
      userpw: "",
      useremail: "",
      username: "",
      idRule: [
        (value) => !!value || "필수항목입니다.",
        (value) =>
          (value && value.length >= 3 && value.length <= 10) ||
          "아이디는 3자 이상 10자 이하입니다.",
      ],
      emailRule: [
        (value) => !!value || "필수항목입니다.",
        (value) => {
          const regex = new RegExp(
            "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
          );
          return (
            (value && regex.test(value)) || "이메일 형식이 올바르지 않습니다."
          );
        },
      ],
      rules: [(value) => !!value || "필수항목입니다."],
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["createUser", "modifyUserInfo"]),
    registerUser() {
      let user = {
        id: this.userid,
        password: this.userpw,
        email: this.useremail,
        name: this.username,
      };

      if (!!user.id && !!user.password && !!user.email && !!user.name) {
        this.createUser(user);
        this.$router.push({ name: "signIn" });
      } else {
        alert("정보를 전부 입력해주세요");
      }
    },
    modifyUser() {
      const user = {
        id: "",
        password: this.userpw,
        email: this.useremail,
        name: this.username,
      };
      this.modifyUserInfo({ user });
      this.$router.push({ name: "mypage" });
    },

    movePage() {
      this.$router.push({ name: "signIn" });
    },

    movePageFront() {
      this.$router.push({ name: "front" });
    },
  },
};
</script>

<style></style>
