<template>
  <v-container>
    <v-card class="blue lighten-5">
      <v-card-title class="blue lighten-2">Q&A</v-card-title>

      <v-card-text>
        <v-text-field
          class="mt-3"
          label="작성자"
          v-model="userInfo.name"
          outlined
          disabled
        ></v-text-field>
        <v-text-field label="제목" v-model="qna.title" outlined></v-text-field>
        <v-textarea label="내용" v-model="qna.content" outlined></v-textarea>
        <div>
          <v-spacer></v-spacer>
          <v-btn v-if="type === 'register'" @click="onSubmit">글 작성</v-btn>
          <v-btn v-if="type === 'modify'" @click="onSubmit">글 수정</v-btn>
          <v-btn class="ml-2" @click="qnaList">목록</v-btn>
        </div>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import { qnaCreate, qnaModify } from "@/api/qna";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QnaInputItem",
  data() {
    return {
      qna: {
        username: "",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      this.qna.username = this.$route.params.qna.username;
      this.qna.title = this.$route.params.qna.title;
      this.qna.content = this.$route.params.qna.content;
      this.isUserid = true;
    }
  },
  props: {
    type: { type: String },
  },
  methods: {
    onSubmit() {
      let err = true;
      let msg = "";
      err && !this.qna.title && ((msg = "제목 입력해주세요"), (err = false));
      err && !this.qna.content && ((msg = "내용 입력해주세요"), (err = false));

      if (!err) alert(msg);
      else this.type === "register" ? this.registQna() : this.modifyQna();
    },
    registQna() {
      qnaCreate(
        {
          userid: this.userInfo.id,
          title: this.qna.title,
          content: this.qna.content,
        },
        (response) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (response.data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "list" });
        },
        (error) => {
          alert("등록 처리시 문제가 발생했습니다.", error.status);
          this.$router.push({ name: "list" });
        },
      );
    },
    qnaList() {
      this.$router.push({ name: "list" });
    },
    modifyQna() {
      qnaModify(
        {
          qnano: this.$route.params.qna.qnano,
          title: this.qna.title,
          content: this.qna.content,
        },
        (response) => {
          let msg = "수정 처리에 실패하였습니다.";
          if (response.data === "success") {
            msg = "수정되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "list" });
        },
        (error) => {
          alert("수정 처리에 실패하였습니다.", error.status);
          this.$router.push({ name: "list" });
        },
      );
    },
  },
};
</script>

<style></style>
