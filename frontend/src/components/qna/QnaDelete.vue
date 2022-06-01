<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <v-col><v-alert outlined text type="info">삭제 처리중...</v-alert></v-col>
    </b-row>
  </b-container>
</template>

<script>
import { qnaDelete } from "@/api/qna";
export default {
  name: "QnaDelete",
  created() {
    qnaDelete(
      this.$route.params.qnano,
      (response) => {
        let msg = "삭제 처리 시 문제가 발생하였습니다.";
        if (response.data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        this.$router.push({ name: "list" });
      },
      (error) => {
        alert("삭제 처리시 에러가 발생하였습니다.", error.status);
        this.$router.push({ name: "list" });
      },
    );
  },
};
</script>

<style></style>
