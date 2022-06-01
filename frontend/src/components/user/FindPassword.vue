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
            <v-toolbar-title><h3>비밀번호 찾기</h3></v-toolbar-title>
          </v-toolbar>
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
                id="email"
                v-model="user.email"
                required
                placeholder="이메일 입력"
                @keyup.enter="confirm"
                label="이메일"
                type="text"
              ></v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn type="button" variant="primary" class="m-1" @click="confirm"
              >비밀번호 찾기
            </v-btn>
            <v-btn type="button" class="m-1" @click="movePage">로그인 </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
// import { mapState } from "vuex";
// const memberStore = "memberStore";
import { findPassword } from "@/api/member.js";

export default {
  name: "FindPassword",
  data() {
    return {
      user: {
        id: null,
        email: null,
      },
    };
  },
  methods: {
    confirm() {
      console.log(this.user);
      findPassword(this.user, (response) => {
        if (response.status == "200") {
          console.log(response);
          alert(response.data);
        } else if (response.status == "409") {
          alert("서버 문제 발생");
        } else if (response.status == "204") {
          alert("ID와 등록된 Email이 맞지않습니다.");
        }
      });
    },
    movePage() {
      this.$router.push({ name: "signIn" });
    },
  },
};
</script>

<style></style>
