<template>
  <b-container class="mt-3 text-center col-10 mx-auto">
    <b-row>
      <b-col class="border">
        <b-button class="m-3 bgc" style="min-width: 95%; min-height: 150px"
          >매물로 검색하기</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mt-3 border p-3">
      <news-item></news-item>
    </b-row>
  </b-container>
</template>

<script>
import NewsItem from "@/components/front/NewsList";
export default {
  name: "FrontView",
  components: {
    NewsItem,
  },
};
</script>

<style scoped>
.bgc {
  background-color: rgb(39, 118, 145);
}
</style>
