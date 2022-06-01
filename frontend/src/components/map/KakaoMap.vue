<template>
  <v-row>
    <div id="map" class="mt-4 ml-4">
      <v-col cols="3" class="pt-3 pl-3 pb-0" style="z-index: 99">
        <v-autocomplete
          :items="items"
          v-model="model"
          @change="moveToAdd"
          :search-input.sync="search"
          hide-selected
          item-text="Address"
          item-value="ADDRESS"
          dense
          filled
          background-color="white"
          label="지역 키워드 검색"
          menu-props="{'closeOnContentClick': true}"
          no-data-text="해당되는 지역이 없습니다."
        ></v-autocomplete>
      </v-col>
      <!-- <div style="position: absolute; z-index: 99; top: 110px; left: 20px">
        <range-search> </range-search>
      </div> -->

      <v-col cols="3" style="z-index: 99; height: 80%">
        <lottery-detail-view
          :lotteryShow="lotteryShow"
          :lotteryDetail="lotteryDetail"
        >
        </lottery-detail-view>
        <apt-detail-view
          :houseDetail="houseDetail"
          :detailShow="detailShow"
        ></apt-detail-view>
        <div v-if="rangeShow">
          <range-search ref="rangeRef" v-on:changeMapRange="changeMapRange">
          </range-search>
        </div>
      </v-col>
      <div style="position: absolute; z-index: 5; top: 65px; right: 25px">
        <div
          @click="toggleInterest"
          class="border text-center pt-1"
          style="width: 35px; height: 35px; background-color: #42a5f5"
        >
          <v-icon>fa-regular fa-star</v-icon>
        </div>
      </div>
      <div
        style="
          position: absolute;
          top: 120px;
          right: 25px;
          min-height: 300px;
          width: 300px;
        "
      >
        <apt-interest-view
          :interestShow="interestShow"
          :interestList="interestList"
          v-on:interestLoc="interestLoc"
          v-on:deleteLoc="deleteLoc"
        ></apt-interest-view>
      </div>
    </div>
  </v-row>
</template>

<script>
import { mapState, mapActions } from "vuex";
import {
  getBounds,
  getLotteryBounds,
  getSubwayBounds,
  getInterestList,
  addInterest,
  deleteInterest,
} from "@/api/map";
import AptDetailView from "@/components/map/item/AptDetailView.vue";
import AptInterestView from "@/components/map/item/AptInterestView.vue";
import RangeSearch from "@/components/map/item/RangeSearch.vue";
import LotteryDetailView from "@/components/map/item/LotteryDetailView.vue";
const mapStore = "mapStore";
const memberStore = "memberStore";
const rangeStore = "rangeStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      markers: [],
      lotteries: [],
      subways: [],
      entries: [],
      isLoading: false,
      model: null,
      search: null,
      addNum: null,
      modelLat: null,
      modelLng: null,
      detailShow: false,
      rangeShow: true,
      interestShow: false,
      interestList: [],
      geocoder: "",
      rangeList: [],
      lotteryShow: false,
    };
  },
  components: {
    AptDetailView,
    RangeSearch,
    AptInterestView,
    LotteryDetailView,
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" +
        process.env.VUE_APP_KAKAO_MAP_API_KEY +
        "&libraries=services";
      document.head.appendChild(script);
    }
  },
  computed: {
    ...mapState(mapStore, ["houseDetail", "lotteryDetail"]),
    ...mapState(memberStore, ["userInfo", "isLogin"]),
    ...mapState(rangeStore, ["priceRange", "areaRange"]),
    items() {
      return this.entries.map((entry) => {
        const Address = entry.address;
        return Object.assign({}, entry, { Address });
      });
    },
    detailChanged() {
      console.log(this.houseDetail);
      return this.houseDetail;
    },
  },
  watch: {
    search() {
      if (this.isLoading) return;

      fetch("http://localhost/happyhouse/addsearch?addString=" + this.search)
        .then((res) => res.json())
        .then((res) => {
          // console.log(res);
          const entries = res;
          this.entries = entries;
        })
        .catch((err) => {
          console.log(err);
        })
        .finally(() => (this.isLoading = false));
    },
  },
  methods: {
    ...mapActions(mapStore, ["setHouseDetail", "setLotteryDetail"]),
    interestLoc(data) {
      this.map.panTo(new kakao.maps.LatLng(data.lat, data.lng));
    },
    deleteLoc(data) {
      deleteInterest(
        {
          dongcode: data.dongcode,
          userid: this.userInfo.id,
        },
        (response) => {
          if (response.status == "204") {
            alert(response.data);
          } else {
            this.interestList = response.data;
          }
        },
      );
    },
    toggleInterest() {
      if (!this.interestShow && this.isLogin) {
        getInterestList({ userid: this.userInfo.id }, (response) => {
          this.interestList = response.data;
        });
      }
      this.interestShow = !this.interestShow;
    },
    filterAdd() {
      this.filteredAdd = this.address;
    },
    getAddList() {
      this.items = this.getAddress(this.addString);
    },

    initMap() {
      const mapContainer = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.4999072, 127.0373932), // 지도의 중심좌표
        level: 3,
      };

      this.map = new kakao.maps.Map(mapContainer, options);

      kakao.maps.event.addListener(
        this.map,
        "dragend",
        () => {
          this.hideMarkers();
          this.hideLotteries();
          this.hideSubway();
          this.getAptList(this.map.getBounds());
          this.getLotteryList(this.map.getBounds());
          this.getSubwayList(this.map.getBounds());
          this.detailShow = false;
          if (this.map.getLevel() < 5) {
            if (this.rangeShow == false) {
              this.rangeShow = true;
            } else {
              this.resetRangeMap();
            }
          }
          this.lotteryShow = false;
        },
        { passive: true },
      );

      kakao.maps.event.addListener(this.map, "zoom_changed", () => {
        this.hideMarkers();
        this.hideLotteries();
        this.hideSubway();
        this.getAptList(this.map.getBounds());
        this.getLotteryList(this.map.getBounds());
        this.getSubwayList(this.map.getBounds());
        if (this.map.getLevel() < 5) {
          if (this.rangeShow == false) {
            // 필터값을 리셋해서 필터 다시 보여주기
            this.rangeShow = true;
          } else {
            this.resetRangeMap();
          }
          if (this.detailShow == true) {
            this.rangeShow = false;
          }
        }
        if (this.map.getLevel() >= 5) {
          this.detailShow = false;
        }
        this.lotteryShow = false;
      });

      kakao.maps.event.addListener(this.map, "rightclick", (mouseEvent) => {
        this.showInterestModal(mouseEvent.latLng);
      });
      this.geocoder = new kakao.maps.services.Geocoder();
    },
    showInterestModal(latLng) {
      if (confirm("이곳을 관심지역 추가 하시겠습니까?")) {
        if (this.interestShow) {
          this.interestShow = !this.interestShow;
        }
        this.geocoder.coord2RegionCode(
          latLng.La,
          latLng.Ma,
          (result, status) => {
            if (status === kakao.maps.services.Status.OK) {
              for (var i = 0; i < result.length; i++) {
                if (result[i].region_type === "B") {
                  addInterest(
                    {
                      userid: this.userInfo.id,
                      dongname: result[i].address_name,
                    },
                    (response) => {
                      if (response.status == "201") {
                        alert(response.data);
                      } else {
                        alert(response.data);
                      }
                    },
                  );
                  break;
                }
              }
            }
          },
        );
      }
    },
    hideMarkers() {
      for (let i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    hideLotteries() {
      for (let i = 0; i < this.lotteries.length; i++) {
        this.lotteries[i].setMap(null);
      }
      this.lotteries = [];
    },
    hideSubway() {
      for (let i = 0; i < this.subways.length; i++) {
        this.subways[i].setMap(null);
      }
      this.subways = [];
    },
    getAptList(mapbound) {
      if (this.map.getLevel() < 5) {
        getBounds(
          {
            minLatitude: mapbound.qa,
            minLongitude: mapbound.ha,
            maxLatitude: mapbound.pa,
            maxLongitude: mapbound.oa,
            zoomLevel: this.map.getLevel(),
          },
          ({ data }) => {
            for (let i in data) {
              this.addMarker(
                new kakao.maps.LatLng(data[i].lat, data[i].lng),
                data[i].area,
                data[i].dealAmount,
                data[i].apartmentName,
                data[i].address,
                data[i].aptcode,
              );
            }
          },
        );
      } else {
        this.rangeShow = false;
        getBounds(
          {
            minLatitude: mapbound.qa,
            minLongitude: mapbound.ha,
            maxLatitude: mapbound.pa,
            maxLongitude: mapbound.oa,
            zoomLevel: this.map.getLevel(),
          },
          ({ data }) => {
            for (let i in data) {
              this.addBigMarker(
                new kakao.maps.LatLng(data[i].lat, data[i].lng),
                data[i].dealamount,
                data[i].location,
                data[i].address,
              );
            }
          },
        );
      }
    },
    getSubwayList(mapbound) {
      if (this.map.getLevel() < 6) {
        console.log("mapbound.qa", mapbound.qa);
        console.log("mapbound.ha", mapbound.ha);
        console.log("mapbound.pa", mapbound.pa);
        console.log("mapbound.oa", mapbound.oa);
        getSubwayBounds(
          {
            minLatitude: mapbound.qa,
            minLongitude: mapbound.ha,
            maxLatitude: mapbound.pa,
            maxLongitude: mapbound.oa,
          },
          ({ data }) => {
            console.log("subway data", data);
            for (let i in data) {
              this.addSubwayMarker(
                new kakao.maps.LatLng(data[i].lat, data[i].lng),
                data[i].subwayname,
                data[i].lineno,
              );
            }
          },
        );
      } else {
        this.hideSubway();
      }
    },
    getLotteryList(mapbound) {
      if (this.map.getLevel() < 6) {
        getLotteryBounds(
          {
            minLatitude: mapbound.qa,
            minLongitude: mapbound.ha,
            maxLatitude: mapbound.pa,
            maxLongitude: mapbound.oa,
          },
          ({ data }) => {
            for (let i in data) {
              this.addLotterMarker(
                new kakao.maps.LatLng(data[i].lat, data[i].lng),
                data[i].houseNm,
                data[i].status,
                data[i].lotteryId,
              );
            }
          },
        );
      } else {
        this.hideLotteries();
      }
    },
    //마커 찍기
    addMarker(position, area, dealamount, apartmentName, address, aptcode) {
      let content = document.createElement("div");

      content.onclick = () => {
        this.map.panTo(position);
        this.setHouseDetail({ aptcode: aptcode });
        this.rangeShow = false;
        this.detailShow = true;
        this.lotteryShow = false;
      };
      let title = document.createElement("div");
      title.style.backgroundColor = "#1E88E5";
      title.style.minWidth = "50px";
      title.style.textAlign = "center";
      title.style.padding = "3px";
      title.style.borderRadius = "5px";
      let areaDiv = document.createElement("div");
      areaDiv.style.fontSize = "11px";
      areaDiv.style.color = "#BDBDBD";
      areaDiv.appendChild(document.createTextNode(`${area}평`));
      title.appendChild(areaDiv);

      let distance = document.createElement("div");
      distance.appendChild(document.createTextNode(`${dealamount}억`));
      distance.style.fontSize = "14px";
      distance.style.color = "#FFFFFF";
      title.appendChild(distance);

      content.appendChild(title);

      const infoContent = `
        <div class="ml-4 mt-4" style="min-width:180px;min-height:60px;">
          <div style="font-size:15px"><b>${apartmentName}</b></div>
          <div style="font-size:13px">${address}</div>
        </div>
      `;

      const infowindow = new kakao.maps.InfoWindow({
        content: infoContent,
        removable: true,
      });

      const marker = new kakao.maps.CustomOverlay({
        position: position,
        content: content,
        clickable: false,
      });

      const fakeMarker = new kakao.maps.Marker({
        position: position,
      });

      marker.a.addEventListener(
        "mouseover",
        this.makeOverListener(this.map, fakeMarker, infowindow),
      );

      marker.a.addEventListener("mouseout", this.makeOutListener(infowindow));

      marker.setMap(this.map);
      this.markers.push(marker);

      return marker;
    },
    addBigMarker(position, dealamount, location, address) {
      let content = `
        <div class="text-center p-1 rounded" style="min-width:50px;background-color:#1E88E5">
          <div style="font-size:11px;color:#BDBDBD">
            ${location}
          </div>
          <div style="font-size:14px;color:#FFFFFF;">
            ${dealamount / 10}억
          </div>
        </div>
      `;

      const infoContent = `
        <div class="ml-4 mt-4" style="min-width:180px;min-height:60px;">
          <div style="font-size:13px"><b>${address}</b></div>
          <div style="font-size:15px">${location}</div>
        </div>
      `;

      const infowindow = new kakao.maps.InfoWindow({
        content: infoContent,
        removable: true,
      });

      const marker = new kakao.maps.CustomOverlay({
        position: position,
        content: content,
      });

      const fakeMarker = new kakao.maps.Marker({
        position: position,
      });

      marker.a.addEventListener(
        "mouseover",
        this.makeOverListener(this.map, fakeMarker, infowindow),
      );
      marker.a.addEventListener("mouseout", this.makeOutListener(infowindow));

      marker.a.addEventListener(
        "click",
        this.makeAptOnClickListener(this.map, position),
      );

      marker.setMap(this.map);

      this.markers.push(marker);

      return marker;
    },
    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
        infowindow.a.style.zIndex = "99";
      };
    },
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },
    makeAptOnClickListener(map, position) {
      return () => {
        map.panTo(position);
        map.setLevel(5);
      };
    },
    addSubwayMarker(position, subwayname, lineno) {
      let content = document.createElement("div");
      content.onclick = () => {
        this.map.panTo(position);
      };
      let title = document.createElement("div");
      title.style.backgroundColor = "#f2bf09";
      title.style.minWidth = "40px";
      title.style.textAlign = "center";
      title.style.padding = "3px";
      title.style.borderRadius = "5px";
      let areaDiv = document.createElement("div");
      areaDiv.style.fontSize = "7px";
      areaDiv.style.color = "#FFFFFF";
      areaDiv.appendChild(document.createTextNode(lineno));
      title.appendChild(areaDiv);

      let distance = document.createElement("div");
      distance.appendChild(document.createTextNode(subwayname));
      distance.style.fontSize = "10px";
      distance.style.color = "#FFFFFF";
      title.appendChild(distance);

      content.appendChild(title);

      const marker = new kakao.maps.CustomOverlay({
        position: position,
        content: content,
      });

      this.lotteries.push(marker);
      marker.setMap(this.map);
    },
    addLotterMarker(position, housenm, status, lotteryId) {
      let content = document.createElement("div");
      content.onclick = () => {
        this.map.panTo(position);
        //청약 디테일 온클릭 리스너 달기
        this.setLotteryDetail({ lotteryId: lotteryId });
        this.detailShow = false;
        this.lotteryShow = true;
        this.rangeShow = false;
      };
      let title = document.createElement("div");
      title.style.backgroundColor = "#EF6C00";
      title.style.minWidth = "100px";
      title.style.textAlign = "center";
      title.style.padding = "3px";
      title.style.borderRadius = "5px";
      let areaDiv = document.createElement("div");
      areaDiv.style.fontSize = "11px";
      areaDiv.style.color = "#BDBDBD";
      areaDiv.appendChild(document.createTextNode(status));
      title.appendChild(areaDiv);

      let distance = document.createElement("div");
      distance.appendChild(document.createTextNode(housenm));
      distance.style.fontSize = "13px";
      distance.style.color = "#FFFFFF";
      title.appendChild(distance);

      content.appendChild(title);

      const marker = new kakao.maps.CustomOverlay({
        position: position,
        content: content,
      });

      this.lotteries.push(marker);
      marker.setMap(this.map);
    },

    moveToAdd() {
      let list = this.entries;
      for (let i in list) {
        let movingAddress = list[i].address;
        if (movingAddress === this.model) {
          this.modelLat = list[i].latitude;
          this.modelLng = list[i].longitude;
        }
      }

      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(this.modelLat, this.modelLng);

      // 지도 중심을 이동 시킵니다
      this.map.setCenter(moveLatLon);
      this.hideMarkers();
      this.hideLotteries();
      this.hideSubway();
      this.getAptList(this.map.getBounds());
      this.getLotteryList(this.map.getBounds());
      this.getSubwayList(this.map.getBounds());
    },
    changeMapRange(data) {
      // this.rangeList = data;

      // 마커 리스트를 갖고 가격에
      // console.log("change Map range");
      // console.log(data);
      for (let i = 0; i < this.markers.length; i++) {
        let price = this.markers[i]
          .getContent()
          .getElementsByTagName("div")[2]
          .innerHTML.slice(0, -1);
        let area = this.markers[i]
          .getContent()
          .getElementsByTagName("div")[1]
          .innerHTML.slice(0, -1);
        // console.log(price);
        // console.log(area);
        if (
          price >= data[0] &&
          price <= data[1] &&
          area >= data[2] &&
          area <= data[3]
        ) {
          this.markers[i].setMap(this.map);
        } else {
          this.markers[i].setMap(null);
        }
      }

      this.detailShow = false;
      this.lotteryShow = false;
      this.rangeShow = true;
    },
    resetRangeMap() {
      this.$refs.rangeRef.resetRange();
    },
  },
};
</script>

<style>
#map {
  width: 100vw;
  height: 89vh;
}
#rangeSearch {
  position: absolute;
  top: 300px;
  left: 500px;
  z-index: 99;
}
</style>
