const rangeStore = {
  namespaced: true,
  state: {
    priceRange: null,
    areaRange: null,
  },
  getters: {
    getPriceRange: function (state) {
      return state.priceRange;
    },

    getAreaRange: function (state) {
      return state.areaRange;
    },
  },
  mutations: {
    CHANGE_PRICE_RANGE: (state, pricerange) => {
      console.log("changeRange mutations");
      console.log(pricerange);
      state.priceRange = pricerange;
    },
    CHANGE_AREA_RANGE: (state, arearange) => {
      state.areaRange = arearange;
    },
  },
  actions: {
    changePriceRange({ commit }, pricerange) {
      console.log("changeRange Action");
      commit("CHANGE_PRICE_RANGE", pricerange);
    },
    changeAreaRange({ commit }, arearange) {
      commit("CHANGE_AREA_RANGE", arearange);
    },
  },
};

export default rangeStore;
