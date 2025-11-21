<template>
  <div class="ChartCustomize3-css">
    <el-carousel height="100%" :interval="3000000" indicator-position="none" style="height: 100%" v-if="proJectsList.listData.length">
      <el-carousel-item v-for="(item,key) in proJectsList.listData" :key="key">
        <div class="ChartCustomize3-content">
          <div class="ChartCustomize3-content-left">
            <div class="n1">
              <!--<img src="@/assets/chart/images/dataV-02.jpg">-->
              {{item.name}}
            </div>
            <div class="n2 space1">{{ proJectsList.processName }}：{{item.process}}</div>
            <div class="n2 space1">{{ proJectsList.processingScaleName }}：{{ item.processingScale }}</div>
            <div class="n2 space1">{{ proJectsList.theAmountOfWaterReturnedName }}：{{ item.theAmountOfWaterReturned }}</div>
            <div class="n3">
              <div class="c1">
                <div class="d1">{{ proJectsList.responsibleName }}</div>
                <div class="d2">{{ item.responsible }}</div>
              </div>
              <div class="c1 c2">
                <div class="d1">{{ proJectsList.contactNumberName }}</div>
                <div class="d2"> {{ item.contactNumber }}</div>
              </div>
            </div>
            <div class="n3">
              <div class="c1">
                <div class="d1">{{ proJectsList.safetyOfficerName }}</div>
                <div class="d2">{{ item.safetyOfficer }}</div>
              </div>
              <div class="c1 c2">
                <div class="d1">{{ proJectsList.contactNumber2Name }}</div>
                <div class="d2"> {{ item.contactNumber2 }}</div>
              </div>
            </div>
          </div>
          <div class="ChartCustomize3-content-right">
            <div class="n1" v-if="item.realPictures.length">
              <img :src="item.realPictures[0].imgUrl"/>
              <div>{{ item.realPictures[0].name }}</div>
            </div>
            <div class="n1 n2" v-if="item.realPictures.length">
              <img :src="item.realPictures[1].imgUrl"/>
              <div>{{ item.realPictures[1].name }}</div>
            </div>
          </div>
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  proJectsList: {
    type: Object,
    default: () => {
      return {
        processName: '',
        processingScaleName: '',
        theAmountOfWaterReturnedName: '',
        responsibleName: '',
        contactNumberName: '',
        safetyOfficerName: '',
        contactNumber2Name: '',
        listData: []
      };
    }
  }
});

// const imgUrl = new URL('@/assets/chart/images/1.png', import.meta.url).href;
</script>

<style lang="scss" scoped>
.ChartCustomize3-css {
  height: calc(100% - 35px);


  .ChartCustomize3-content {
    display: flex;
    flex-wrap: nowrap;
    justify-content: space-between;
    align-items: center;
    flex-direction: row;
    padding: 5px 25px 25px 25px;

    .ChartCustomize3-content-left {
      width: 60%;


      .space1 {
        width: 100%;
        height: 30px;
        line-height: 30px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }


      .n1 {
        color: #fff;
        font-size: 18px;
        display: flex;
        flex-wrap: nowrap;
        justify-content: flex-start;
        align-items: center;
        flex-direction: row;
        width: 160%;
        height: 28px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        margin-bottom: 5px;

        img {
          height: 28px;
          margin-right: 15px;
        }
      }

      .n2 {
        color: #b5b5b5;
        font-size: 16px;
        //padding-top: 9px;
      }

      .n3 {
        margin-top: 8px;
        display: flex;
        flex-wrap: nowrap;
        justify-content: flex-start;
        align-items: center;
        flex-direction: row;

        .c1 {
          width: 175px;
          height: 43px;
          background: url("@/assets/chart/images/dataV-03.jpg") no-repeat;
          background-size: 100% 100%;

          .d1 {
            color: #139aff;
            padding: 3px 0 3px 75px;
            font-size: 12px;
          }

          .d2 {
            color: #fff;
            padding: 0 0 0 75px;
            font-size: 12px;
          }
        }

        .c2 {
          margin-left: 12px;
          background: url("@/assets/chart/images/dataV-04.jpg") no-repeat;
          background-size: 100% 100%;
        }
      }
    }

    .ChartCustomize3-content-right {
      display: flex;
      flex-wrap: nowrap;
      justify-content: flex-start;
      align-items: center;
      flex-direction: row;
      width: 40%;
      margin-left: 10px;

      .n1 {
        img {
          width: 150px;
          height: 180px;
        }

        div {
          text-align: center;
          color: #77d7ff;
          font-size: 12px;
          background: #071e61;
          padding: 5px 0;
        }
      }

      .n2 {
        margin-left: 10px;
      }
    }
  }
}
</style>
