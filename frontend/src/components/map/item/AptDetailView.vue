<template>
  <v-navigation-drawer
    style="background-color: #ffffff; width: 100%"
    permanent
    v-show="detailShow"
    v-if="houseDetail == null ? false : true"
  >
    <v-list-item style="background-color: #1e88e5; color: white">
      <v-list-item-content>
        <v-list-item-title style="font-size: 15px">
          {{ houseDetail.dongname }} {{ houseDetail.apartmentName }}
        </v-list-item-title>
        <v-list-item-subtitle style="font-size: 11px; color: #cfd8dc">
          {{ houseDetail.address }} {{ houseDetail.jibun }}
        </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>
    <v-list-item style="background-color: #f5f5f5">
      <v-list-item-content style="color: #424242">
        <div style="font-size: 13px">
          건축년도 : {{ houseDetail.buildYear }}년
        </div>
        <div class="mt-1">
          <span style="font-size: 13px"> 평균 실거래 가격 : </span>
          {{ houseDetail.dealAmount }}억
        </div>
      </v-list-item-content>
    </v-list-item>
    <v-sparkline
      :value="getDealItems"
      smooth="5"
      line-width="3"
      auto-draw
      height="150"
    >
    </v-sparkline>
    <v-data-table
      :headers="headers"
      :items="houseDetail.dealList"
      :items-per-page="8"
      item-class="caption"
      class="elevation-1"
      hide-default-footer
      :page.sync="page"
      @page-count="pageCount = $event"
    >
      <template v-slot:item="{ item }">
        <tr>
          <td>{{ item.dealDate }}</td>
          <td>{{ (item.dealAmount / 10000).toFixed(1) }}억</td>
          <td>{{ item.area }}</td>
          <td>{{ item.floor }}층</td>
        </tr>
      </template>
    </v-data-table>
    <div class="text-center pt-2">
      <v-pagination v-model="page" :length="pageCount"></v-pagination>
    </div>
  </v-navigation-drawer>
</template>

<script>
export default {
  name: "AptDetailView",
  props: {
    houseDetail: Object,
    detailShow: Boolean,
  },
  computed: {
    getDealItems() {
      let it = this.houseDetail.dealList.map((item) =>
        parseInt(item.dealAmount),
      );
      return it.reverse();
    },
  },
  data() {
    return {
      page: 1,
      pageCount: 5,
      headers: [
        {
          text: "계약일",
          value: "dealDate",
          class: ["button", "Button", "0.875rem", "500", ".0892857143em", 4],
        },
        {
          text: "가격",
          value: "dealAmount",
          class: ["button", "Button", "0.875rem", "500", ".0892857143em", 4],
        },
        {
          text: "평수",
          value: "area",
          class: ["button", "Button", "0.875rem", "500", ".0892857143em", 4],
        },
        {
          text: "층",
          value: "floor",
          class: ["button", "Button", "0.875rem", "500", ".0892857143em", 4],
        },
      ],
    };
  },
};
</script>

<style></style>
