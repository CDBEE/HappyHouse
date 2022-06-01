import { apiInstance } from "./index.js";

const api = apiInstance();

function getBounds(mapBound, success) {
  api.get(`/aptlist`, { params: mapBound }).then(success);
}

function getLotteryBounds(mapBound, success) {
  api.get("/lottery/list", { params: mapBound }).then(success);
}

function getSubwayBounds(mapBound, success) {
  api.get("/subwaylist", { params: mapBound }).then(success);
}

function getLotteryDetail(mapBound, success) {
  api.get("/lottery/detail", { params: mapBound }).then(success);
}

function getAptDetail(aptcode, success) {
  api.get("aptdetail", { params: aptcode }).then(success);
}

function getInterestList(userid, success) {
  api.get(`/interest`, { params: userid }).then(success);
}

function addInterest(interest, success) {
  api.post("/interest", JSON.stringify(interest)).then(success);
}

async function deleteInterest(interest, success) {
  await api.delete("/interest", { params: interest }).then(success);
}

export {
  getBounds,
  getLotteryBounds,
  getLotteryDetail,
  getAptDetail,
  getInterestList,
  addInterest,
  deleteInterest,
  getSubwayBounds,
};
