<template>
  <v-container class="mt-3">
    <v-row
      v-for="(reply, replyno) in replies"
      :key="replyno"
      style="max-height: 70px"
    >
      <div>
        <div style="font-size: 10px">작성자</div>
        <div>{{ reply.username }}</div>
      </div>

      <v-col cols="10" style="max-height: 70px; padding-top: 0px">
        <v-text-field
          v-model="reply.content"
          outlined
          :disabled="!(reply.userid == userid)"
        >
        </v-text-field>
      </v-col>
      <div style="width: 50px" v-if="reply.userid == userid">
        <v-btn x-small @click="replyModify(reply.replyno, reply.content)"
          >수정</v-btn
        >
        <v-btn x-small @click="replyDelete(reply.replyno)">삭제</v-btn>
      </div>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "QnaReplyItem",
  data() {
    return {
      modifyBtn: "수정",
    };
  },
  props: {
    replies: Array,
    userid: Object,
  },
  methods: {
    replyModify(replyno, content) {
      this.$emit("replyModified", { replyno: replyno, content: content });
    },
    replyDelete(replyno) {
      this.$emit("replyDeleted", { replyno: replyno });
    },
  },
};
</script>

<style></style>
