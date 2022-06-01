<template>
  <v-container>
    <v-card class="blue lighten-5">
      <v-card-title class="blue lighten-2">공지사항</v-card-title>
      <br />
      <v-card-text>
        <v-text-field
          label="제목"
          v-model="notice.title"
          outlined
        ></v-text-field>
        <v-textarea label="내용" v-model="notice.content" outlined></v-textarea>
        <div>
          <v-spacer></v-spacer>
          <v-btn v-if="type === 'register'" @click="onSubmit">글 작성</v-btn>
          <v-btn v-if="type === 'modify'" @click="onSubmit">글 수정</v-btn>
          <v-btn class="ml-2" @click="noticeList">목록</v-btn>
        </div>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import { noticeCreate, noticeModify } from "@/api/notice";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NoticeInputItem",
  data() {
    return {
      notice: {
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
      this.notice.title = this.$route.params.notice.title;
      this.notice.content = this.$route.params.notice.content;
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
      err && !this.notice.title && ((msg = "제목 입력해주세요"), (err = false));
      err &&
        !this.notice.content &&
        ((msg = "내용 입력해주세요"), (err = false));

      if (!err) alert(msg);
      else this.type === "register" ? this.registNotice() : this.modifyNotice();
    },
    registNotice() {
      noticeCreate(
        {
          title: this.notice.title,
          content: this.notice.content,
        },
        (response) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (response.data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "noticelist" });
        },
        (error) => {
          alert("등록 처리시 문제가 발생했습니다.", error.status);
          this.$router.push({ name: "noticelist" });
        },
      );
    },
    noticeList() {
      this.$router.push({ name: "noticelist" });
    },
    modifyNotice() {
      noticeModify(
        {
          noticeno: this.$route.params.notice.noticeno,
          title: this.notice.title,
          content: this.notice.content,
        },
        (response) => {
          let msg = "수정 처리에 실패하였습니다.";
          if (response.data === "success") {
            msg = "수정되었습니다.";
          }
          alert(msg);
          this.$router.push({ name: "noticelist" });
        },
        (error) => {
          alert("수정 처리에 실패하였습니다.", error.status);
          this.$router.push({ name: "noticelist" });
        },
      );
    },
  },
};
</script>

<style></style>
