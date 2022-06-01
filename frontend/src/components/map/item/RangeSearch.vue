<template>
  <v-card flat color="white">
    <v-subheader><b>가격</b>/<small>단위:억</small></v-subheader>
    <v-card-text>
      <v-row>
        <v-col class="px-4">
          <v-range-slider
            v-model="pricerange"
            :max="pricemax"
            :min="pricemin"
            hide-details
            thumb-label="always"
            class="align-center"
            ticks
            @change="sendRange"
          >
          </v-range-slider>
        </v-col>
      </v-row>
    </v-card-text>
    <v-subheader><b>평수</b></v-subheader>
    <v-card-text>
      <v-row>
        <v-col class="px-4">
          <v-range-slider
            v-model="sizerange"
            :max="sizemax"
            :min="sizemin"
            hide-details
            thumb-label="always"
            class="align-center"
            ticks
            @change="sendRange"
          >
          </v-range-slider>
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>

<script>
// import { mapState, mapActions } from "vuex";

// const rangeStore = "rangeStore";

export default {
  name: "RangeSearch",
  data() {
    return {
      pricerange: [0, 30],
      pricemax: 30,
      pricemin: 0,
      sizerange: [0, 60],
      sizemax: 60,
      sizemin: 0,
    };
  },
  component: {},
  computed: {
    // ...mapState(rangeStore, ["priceRange", "areaRange"]),
  },
  methods: {
    // ...mapActions(rangeStore, ["changePriceRange", "changeAreaRange"]),
    sendRange() {
      let newRange = [
        this.pricerange[0],
        this.pricerange[1],
        this.sizerange[0],
        this.sizerange[1],
      ];
      // await this.changePriceRange(this.pricerange);
      // console.log(newRange);
      this.$emit("changeMapRange", newRange);
    },
    resetRange() {
      (this.pricerange = [0, 30]), (this.sizerange = [0, 60]);
    },
  },
};
</script>

<style></style>
