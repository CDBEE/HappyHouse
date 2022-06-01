<template>
  <v-container class="bv-example-row mt-3">
    <v-card class="w-100 blue lighten-5">
      <v-card-title class="blue lighten-1">
        {{ qna.qnano }}.{{ qna.title }}
        <v-spacer></v-spacer>
        <v-btn class="ml-2" @click="qnaList">목록</v-btn>
        <v-btn
          class="ml-2"
          @click="qnaModify"
          v-if="
            userInfo == null ? false : userInfo.id == qna.userid ? true : false
          "
          >수정</v-btn
        >
        <v-btn
          class="ml-2 red lighten-2"
          @click="qnaDelete"
          v-if="
            userInfo == null ? false : userInfo.id == qna.userid ? true : false
          "
          >삭제</v-btn
        >
      </v-card-title>
      <v-card-text>
        <v-card-subtitle class="text-right">
          <v-spacer></v-spacer>
          작성자 : {{ qna.username }} 작성일 :
          {{ qna.regtime | dateFormat }} 조회수 :
          {{ qna.hit }}
        </v-card-subtitle>
        <div class="text-left subtitle-1 font-weight-black">
          {{ qna.content }}
        </div>
      </v-card-text>
    </v-card>
    <v-row class="mb-1">
      <v-col>
        <v-text-field
          label="댓글달기"
          outlined
          v-model="replyContent"
          class="mt-3"
          :append-outer-icon="`mdi-send`"
          @click:append-outer="addReply"
          v-if="userInfo"
        >
        </v-text-field>
        <qna-reply-item
          :replies="replies"
          :userid="userInfo == null ? null : userInfo.id"
          v-on:replyDeleted="replyDelete"
          v-on:replyModified="replyModify"
        ></qna-reply-item>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import moment from "moment";
import { mapState } from "vuex";
import { addReply, getQna, modifyReply, deleteReply } from "@/api/qna";
import QnaReplyItem from "@/components/qna/item/QnaReplyItem.vue";

const memberStore = "memberStore";

export default {
  name: "QnaDetail",
  components: {
    QnaReplyItem,
  },
  data() {
    return {
      qna: {},
      replies: [],
      replyContent: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),

    message() {
      if (this.qna.content) return this.qna.content.split("\n").join("<br>");
      return "";
    },
  },
  methods: {
    replyDelete(data) {
      deleteReply(data.replyno, (response) => {
        if (response.data === "success") {
          let arr = this.replies;
          for (let i = 0; i < arr.length; i++) {
            if (arr[i].replyno == data.replyno) {
              arr.splice(i, 1);
              break;
            }
          }
          this.replies = [...arr];
          alert("댓글이 삭제되었습니다.");
        } else {
          alert("댓글 삭제 중 에러가 발생하였습니다.");
        }
      });
    },
    replyModify(data) {
      modifyReply(
        {
          replyno: data.replyno,
          content: data.content,
        },
        (response) => {
          if (response.data == "success") {
            alert("댓글이 수정되었습니다.");
          } else {
            alert("댓글 수정 중 문제가 발생하였습니다.");
          }
        },
      );
    },
    qnaList() {
      this.$router.push({ name: "list" });
    },
    qnaModify() {
      this.$router.push({ name: "modify", params: { qna: this.qna } });
    },
    qnaDelete() {
      this.$router.push({ name: "delete", params: { qnano: this.qna.qnano } });
    },
    addReply() {
      addReply(
        {
          qnano: this.qna.qnano,
          userid: this.userInfo.id,
          content: this.replyContent,
        },
        (response) => {
          alert("댓글이 등록되었습니다.");
          this.replies = [...response.data];
          this.replyContent = "";
        },
        (error) => {
          alert("댓글 등록 중 에러가 발생하였습니다. ", error.status);
        },
      );
    },
  },
  created() {
    getQna(
      this.$route.params.qnano,
      (response) => {
        this.qna = response.data;
        this.replies = response.data.replies;
      },
      (error) => {
        alert("조회 중 에러가 발생하였습니다.", error.status);
        this.$router.push({ name: "list" });
      },
    );
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
