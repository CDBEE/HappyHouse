<template>
  <v-container class="bv-example-row mt-3">
    <v-card class="w-100 blue lighten-5">
      <v-card-title class="blue lighten-1">
        {{ notice.noticeno }}. {{ notice.title }}
        <v-spacer></v-spacer>
        <v-btn class="ml-2" @click="noticeList">목록</v-btn>
        <v-btn
          class="ml-2"
          @click="noticeModify"
          v-if="
            userInfo == null ? false : userInfo.role == 'normal' ? false : true
          "
          >수정</v-btn
        >

        <v-btn
          class="ml-2 red lighten-2"
          @click="noticeDelete"
          v-if="
            userInfo == null ? false : userInfo.role == 'normal' ? false : true
          "
          >삭제</v-btn
        >
      </v-card-title>
      <v-card-text>
        <v-card-subtitle class="text-right">
          <v-spacer></v-spacer>작성일 : {{ notice.regtime | dateFormat }} 조회수
          :
          {{ notice.hit }}
        </v-card-subtitle>
        <div class="text-left subtitle-1 font-weight-black">
          {{ notice.content }}
        </div>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import moment from "moment";
import { mapState } from "vuex";
import { getNotice } from "@/api/notice";

const memberStore = "memberStore";

export default {
  name: "NoticeDetail",
  components: {},
  data() {
    return {
      notice: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),

    message() {
      if (this.notice.content)
        return this.notice.content.split("\n").join("<br>");
      return "";
    },
  },
  methods: {
    noticeList() {
      this.$router.push({ name: "noticelist" });
    },
    noticeModify() {
      this.$router.push({
        name: "noticemodify",
        params: { notice: this.notice },
      });
    },
    noticeDelete() {
      this.$router.push({
        name: "noticedelete",
        params: { noticeno: this.notice.noticeno },
      });
    },
  },
  created() {
    getNotice(
      this.$route.params.noticeno,
      (response) => {
        this.notice = response.data;
      },
      (error) => {
        alert("조회 중 에러가 발생하였습니다.", error.status);
        this.$router.push({ name: "noticelist" });
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
