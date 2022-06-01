<template>
  <v-container class="mt-3">
    <v-row>
      <v-card class="w-100 blue lighten-5">
        <v-card-title class="blue lighten-3">
          <b>QNA</b>
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
            v-if="userInfo == null ? false : true"
            >Q&A 등록</v-btn
          >
        </v-card-title>
        <v-col v-if="qnas.length">
          <v-data-table
            :headers="headers"
            :items="qnas"
            :items-per-page="10"
            :search="search"
            class="elevation-5 text-left"
          >
            <template v-slot:item="{ item }">
              <tr @click="rowClick(item)">
                <td>{{ item.qnano }}</td>
                <td>{{ item.title }}</td>
                <td>{{ item.userid }}</td>
                <td>{{ item.hit }}회</td>
                <td>{{ item.regtime | dateFormat }}</td>
              </tr>
            </template>
          </v-data-table>
        </v-col>
        <v-col v-else class="text-center"><h4>Q&A 목록이 없습니다.</h4></v-col>
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import moment from "moment";

const memberStore = "memberStore";

export default {
  name: "QnaList",
  components: {},
  data() {
    return {
      search: "",
      headers: [
        { text: "Q&A번호", align: "start", value: "qnano" },
        {
          text: "제목",
          value: "title",
        },
        { text: "작성자", value: "userid" },
        {
          text: "조회수",
          value: "hit",
        },
        {
          text: "작성일",
          value: "regtime",
        },
      ],
      qnas: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    http.get(`/qna/listAdmin`).then(({ data }) => {
      this.qnas = data;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "create" });
    },
    rowClick(item) {
      this.$router.push({ name: "detail", params: { qnano: item.qnano } });
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: center;
}
</style>
