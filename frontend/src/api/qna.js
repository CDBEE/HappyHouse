import { apiInstance } from "./index.js";

const api = apiInstance();

function qnaDelete(qnano, success, fail) {
  api.delete(`/qna/${qnano}`).then(success).catch(fail);
}

function qnaCreate(qna, success, fail) {
  api.post(`/qna/create`, JSON.stringify(qna)).then(success).catch(fail);
}

function qnaModify(qna, success, fail) {
  api.put(`/qna/${qna.qnano}`, JSON.stringify(qna)).then(success).catch(fail);
}

function getQna(qnano, success, fail) {
  api.get(`/qna/${qnano}`).then(success).catch(fail);
}

async function addReply(reply, success, fail) {
  await api
    .post(`/qna/reply/${reply.qnano}`, JSON.stringify(reply))
    .then(success)
    .catch(fail);
}

function modifyReply(reply, success, fail) {
  api
    .put(`/qna/reply/${reply.replyno}`, JSON.stringify(reply))
    .then(success)
    .catch(fail);
}

async function deleteReply(replyno, success, fail) {
  await api.delete(`/qna/reply/${replyno}`).then(success).catch(fail);
}
export {
  qnaDelete,
  qnaCreate,
  qnaModify,
  addReply,
  getQna,
  modifyReply,
  deleteReply,
};
