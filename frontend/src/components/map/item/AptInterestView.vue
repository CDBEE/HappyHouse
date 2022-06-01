<template>
  <v-navigation-drawer
    absolute
    right
    permanent
    style="z-index: 99; background-color: #eceff1"
    v-show="interestShow"
  >
    <v-list>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title
            ><div class="headline font-weight-bold" style="color: #757575">
              내 관심지역
            </div></v-list-item-title
          >
        </v-list-item-content>
      </v-list-item>
      <v-divider class="m-1"></v-divider>
      <v-list-item-group>
        <v-list-item v-for="(item, index) in interestList" :key="index">
          <v-list-item-content>
            <v-list-item-title
              class="overline"
              v-text="item.address"
              @click="gotoInterest(item.lat, item.lng)"
            >
            </v-list-item-title>
          </v-list-item-content>
          <v-btn
            x-small
            style="font-size: 8px"
            class="p-0"
            @click="deleteInterest(item.dongcode)"
            >삭제</v-btn
          >
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
export default {
  name: "AptInterestView",
  props: {
    interestShow: Boolean,
    interestList: Array,
  },
  methods: {
    gotoInterest(lat, lng) {
      this.$emit("interestLoc", {
        lat: lat,
        lng: lng,
      });
    },
    deleteInterest(dongcode) {
      this.$emit("deleteLoc", {
        dongcode: dongcode,
      });
    },
  },
};
</script>

<style></style>
