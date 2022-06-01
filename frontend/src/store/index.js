import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import memberStore from "@/store/modules/memberStore.js";
import mapStore from "@/store/modules/mapStore.js";
import rangeStore from "./modules/rangeStore";
const store = new Vuex.Store({
  modules: {
    memberStore,
    mapStore,
    rangeStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});

export default store;
