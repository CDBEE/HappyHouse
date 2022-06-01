import { getAptDetail, getLotteryDetail } from "@/api/map.js";

const mapStore = {
  namespaced: true,
  state: {
    houseDetail: null,
    lotteryDetail: null,
  },
  getters: {
    checkHouseDetail: function (state) {
      return state.houseDetail;
    },
    checkLotteryDetail: function (state) {
      return state.lotteryDetail;
    },
  },
  mutations: {
    SET_HOUSE_DETAIL: (state, detail) => {
      state.houseDetail = detail;
    },
    SET_LOTTERY_DETAIL: (state, detail) => {
      state.lotteryDetail = detail;
    },
  },
  actions: {
    async setHouseDetail({ commit }, aptcode) {
      await getAptDetail(aptcode, (response) => {
        console.log(response.data);
        commit("SET_HOUSE_DETAIL", response.data);
      });
    },
    async setLotteryDetail({ commit }, lotterycode) {
      await getLotteryDetail(lotterycode, (response) => {
        console.log(response.data);
        commit("SET_LOTTERY_DETAIL", response.data);
      });
    },
  },
};

export default mapStore;
