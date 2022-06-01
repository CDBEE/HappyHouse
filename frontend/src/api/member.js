import { apiInstance } from "./index.js";

const api = apiInstance();

async function join(user, success, fail) {
  await api.post(`/user/join`, JSON.stringify(user)).then(success).catch(fail);
}

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(id, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${id}`).then(success).catch(fail);
}

async function deleteUser(user, success, fail) {
  await api
    .post(`/user/deleteuser`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function modify(user, success, fail) {
  await api.post(`user/update`, JSON.stringify(user)).then(success).catch(fail);
}

async function findPassword(user, success) {
  await api.get("user/findPassword", { params: user }).then(success);
}

export { login, join, findById, deleteUser, modify, findPassword };
