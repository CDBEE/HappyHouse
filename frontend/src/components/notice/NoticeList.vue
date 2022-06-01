<template>
  <v-container class="mt-3">
    <v-row>
      <v-card class="w-100 blue lighten-5">
        <v-card-title class="blue lighten-3">
          <b>공지사항</b>
          <v-spacer></v-spacer>
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="검색"
            single-line
            hide-details
          ></v-text-field>
          <v-btn
            @click="moveWrite()"
            class="ml-3"
            v-if="
              userInfo == null
                ? false
                : userInfo.role == 'normal'
                ? false
                : true
            "
            >공지사항 등록</v-btn
          >
        </v-card-title>
        <v-col v-if="notices.length">
          <v-data-table
            :headers="headers"
            :items="notices"
            :items-per-page="10"
            :search="search"
            class="elevation-5 text-left"
          >
            <template v-slot:item="{ item }">
              <tr @click="rowClick(item)">
                <td>{{ item.noticeno }}</td>
                <td>{{ item.title }}</td>
                <td>{{ item.hit }}회</td>
                <td>{{ item.regtime | dateFormat }}</td>
              </tr>
            </template>
          </v-data-table>
        </v-col>
        <v-col v-else class="text-center"
          ><h4>notice 목록이 없습니다.</h4></v-col
        >
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
import http from "@/api/http";
import moment from "moment";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NoticeList",
  components: {},
  data() {
    return {
      search: "",
      headers: [
        { text: "공지사항 번호", align: "start", value: "noticeno" },
        {
          text: "제목",
          value: "title",
        },
        {
          text: "조회수",
          value: "hit",
        },
        {
          text: "작성일",
          value: "regtime",
        },
      ],
      notices: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    http.get(`/notice/list`).then(({ data }) => {
      this.notices = data;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "noticecreate" });
    },
    rowClick(item) {
      this.$router.push({
        name: "noticedetail",
        params: { noticeno: item.noticeno },
      });
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
