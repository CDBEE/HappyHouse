import jwt_decode from "jwt-decode";
import { login, join, findById, deleteUser, modify } from "@/api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    users: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    CREATE_USER: (state, user) => {
      state.users.push(user);
      console.log("CREATE_USER");
    },
    SET_IS_LOGIN: (state, isLogin) => {
      console.log("set is login");
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.userInfo = userInfo;
    },
  },
  actions: {
    createUser({ commit }, user) {
      join(user, (response) => {
        if (response.data.message === "success") {
          commit("CREATE_USER", user);
        }
      });
    },

    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
            console.log("로그인 성공");
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {},
      );
    },
    async getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      console.log(decode_token);
      await findById(
        decode_token.userid,
        (response) => {
          console.log(response.data);
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userinfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    deleteUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      let userDto = {
        id: decode_token.userid,
      };
      deleteUser(userDto, (response) => {
        if (response.data === "success") {
          commit("SET_IS_LOGIN", false);
          commit("SET_IS_LOGIN_ERROR", false);
          commit("SET_USER_INFO", null);
          sessionStorage.removeItem("access-token");
          alert("탈퇴되었습니다");
        } else {
          console.log("탈퇴 실패");
        }
      });
    },
    modifyUserInfo({ commit }, user) {
      let error = true;
      let msg = "";

      !user.name && ((msg = "이름을 입력해주세요"), (error = false));
      error &&
        !user.password &&
        ((msg = "비밀번호를 입력해주세요"), (error = false));
      error &&
        !user.email &&
        ((msg = "이메일을 입력해주세요"), (error = false));
      if (!error) {
        alert(msg);
      } else {
        modify(
          user,
          (response) => {
            if (response.data === "success") {
              commit("SET_USER_INFO", user);
              alert("회원 정보 수정 완료");
            } else {
              console.log("유저 정보 없음");
            }
          },
          (error) => {
            console.log(error);
          },
        );
      }
    },
  },
};

export default memberStore;
