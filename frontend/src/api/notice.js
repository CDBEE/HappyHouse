import { apiInstance } from "./index.js";

const api = apiInstance();

function noticeCreate(notice, success, fail) {
  api.post(`/notice/create`, JSON.stringify(notice)).then(success).catch(fail);
}

function noticeModify(notice, success, fail) {
  api
    .put(`/notice/${notice.noticeno}`, JSON.stringify(notice))
    .then(success)
    .catch(fail);
}

function noticeDelete(noticeno, success, fail) {
  api.delete(`/notice/${noticeno}`).then(success).catch(fail);
}

function getNotice(noticeno, success, fail) {
  api.get(`/notice/${noticeno}`).then(success).catch(fail);
}

export { noticeCreate, noticeModify, noticeDelete, getNotice };
