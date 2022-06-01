<template>
  <v-container class="bv-example-row mt-3">
    <v-row>
      <v-col><v-alert outlined text type="info">삭제 처리중...</v-alert></v-col>
    </v-row>
  </v-container>
</template>

<script>
import { noticeDelete } from "@/api/notice";
export default {
  name: "NoticeDelete",
  created() {
    noticeDelete(
      this.$route.params.noticeno,
      (response) => {
        let msg = "삭제 처리 시 문제가 발생하였습니다.";
        if (response.data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        this.$router.push({ name: "noticelist" });
      },
      (error) => {
        alert("삭제 처리시 에러가 발생하였습니다.", error.status);
        this.$router.push({ name: "noticelist" });
      },
    );
  },
};
</script>

<style></style>
