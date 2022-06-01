import { getReion } from "@/api/map.js";

const aptStore = {
  nameSpaced: true,
  state: {
    addList: [],
  },

  getters: {},
  mutations: {
    SET_ADD_LIST: (state, addList) => {
      addList.forEach((address) => {
        state.addList.push(address);
      });
    },
  },
  actions: {
    getAddress: ({ commit }, addString) => {
      getReion(
        addString,
        ({ data }) => {
          commit("SET_ADD_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default aptStore;
