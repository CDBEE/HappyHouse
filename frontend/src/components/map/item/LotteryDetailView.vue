<template>
  <v-navigation-drawer
    style="background-color: #ffffff; width: 100%"
    permanent
    v-show="lotteryShow"
    v-if="lotteryDetail == null ? false : true"
  >
    <v-list-item style="background-color: #1e88e5; color: white">
      <v-list-item-content>
        <v-list-item-title style="font-size: 15px">
          {{ lotteryDetail.subscrptAreaCodeNm }} {{ lotteryDetail.houseNm }}
        </v-list-item-title>
      </v-list-item-content>
    </v-list-item>
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th>구분</th>
            <th>내용</th>
          </tr>
        </thead>
        <tbody class="dense">
          <tr>
            <td style="font-size: 6px">분양구분</td>
            <td style="font-size: 6px">{{ lotteryDetail.rentSeCdNm }}</td>
          </tr>
          <tr>
            <td style="font-size: 6px">공급규모</td>
            <td style="font-size: 6px">
              {{ lotteryDetail.totSuplyHshldco }}채
            </td>
          </tr>
          <tr>
            <td style="font-size: 6px">모집공고일</td>
            <td style="font-size: 6px">{{ lotteryDetail.rcritPblancDe }}</td>
          </tr>
          <tr>
            <td style="font-size: 6px">청약접수일</td>
            <td style="font-size: 6px">
              {{ lotteryDetail.rceptBgnde }} ~ {{ lotteryDetail.rceptEndde }}
            </td>
          </tr>
          <tr>
            <td style="font-size: 6px">당첨자 발표일</td>
            <td style="font-size: 6px">
              {{ lotteryDetail.przwnerPresnatnDe }}
            </td>
          </tr>
          <tr>
            <td style="font-size: 6px">계약일</td>
            <td style="font-size: 6px">
              {{ lotteryDetail.cntrctCnclsBgnde }} ~
              {{ lotteryDetail.cntrctCnclsEndde }}
            </td>
          </tr>
          <tr>
            <td style="font-size: 6px">입주예정일</td>
            <td style="font-size: 6px">{{ lotteryDetail.mvnPrearngeYm }}</td>
          </tr>
          <tr>
            <td style="font-size: 6px">홈페이지주소</td>
            <td style="font-size: 6px">
              <a href="${lotteryDetail.hmpgAdres}" target="_blank">{{
                lotteryDetail.hmpgAdres
              }}</a>
            </td>
          </tr>
          <tr>
            <td style="font-size: 6px">건설업체명(시공사)</td>
            <td style="font-size: 6px">{{ lotteryDetail.cnstrctEntrpsNm }}</td>
          </tr>
          <tr>
            <td style="font-size: 6px">사업 주체명</td>
            <td style="font-size: 6px">{{ lotteryDetail.bsnsMbyNm }}</td>
          </tr>
          <tr>
            <td style="font-size: 6px">문의처</td>
            <td style="font-size: 6px">{{ lotteryDetail.mdhsTelno }}</td>
          </tr>
          <tr>
            <td style="font-size: 6px">투기 과열지구</td>
            <td style="font-size: 6px">{{ lotteryDetail.specltRdnEarthAt }}</td>
          </tr>
          <tr>
            <td style="font-size: 6px">조정대상지역</td>
            <td style="font-size: 6px">
              {{ lotteryDetail.mdatTrgetAreaSecd }}
            </td>
          </tr>
          <tr>
            <td style="font-size: 6px">공공주택지구</td>
            <td style="font-size: 6px">
              {{ lotteryDetail.publicHouseEarthAt }}
            </td>
          </tr>
          <tr>
            <td style="font-size: 6px">대규모 택지 개발지구</td>
            <td style="font-size: 6px">{{ lotteryDetail.lrsclBldlndAt }}</td>
          </tr>
          <tr>
            <td style="font-size: 6px">수도권 내 민영 공공 주택 지구</td>
            <td style="font-size: 6px">
              {{ lotteryDetail.nplnPrvoprPublicHouseAt }}
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-navigation-drawer>
</template>

<script>
export default {
  name: "LotteryDetailView",
  props: {
    lotteryShow: Boolean,
    lotteryDetail: Object,
  },
};
</script>

<style></style>
