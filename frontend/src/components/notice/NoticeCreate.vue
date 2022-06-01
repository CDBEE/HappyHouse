<template>
  <v-container>
    <notice-input-item type="register" />
  </v-container>
</template>

<script>
import NoticeInputItem from "@/components/notice/item/NoticeInputItem";

export default {
  name: "NoticeCreate",
  components: {
    NoticeInputItem,
  },
};
</script>

<style></style>
