<template>
  <div class="chart-customize2-css">
    <div class="f1">
      <span>{{ paramsData[0].name }}</span>
      <div><i>{{ paramsData[0].num }}</i> {{ paramsData[0].unit }}</div>
    </div>
    <div class="f2">
      <div class="g1">
        <div class="h1">{{ paramsData[1].name }}</div>
        <div class="h2"><i>{{ paramsData[1].num }}</i> {{ paramsData[1].unit }}</div>
      </div>
      <div class="g1">
        <div class="h1">{{ paramsData[2].name }}</div>
        <div class="h2"><i>{{ paramsData[2].num }}</i> {{ paramsData[2].unit }}</div>
      </div>
    </div>
    <div class="f2 f3">
      <div class="g1">
        <div class="h1">{{ paramsData[3].name }}</div>
        <div class="h2"><i>{{ paramsData[3].num }}</i> {{ paramsData[3].unit }}</div>
      </div>
      <div class="g1">
        <div class="h1">{{ paramsData[4].name }}</div>
        <div class="h2"><i>{{ paramsData[4].num }}</i> {{ paramsData[4].unit }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  paramsData: {
    type: Array,
    default: () => [
      { name: '', num: '', unit: '' },
      { name: '', num: '', unit: '' },
      { name: '', num: '', unit: '' },
      { name: '', num: '', unit: '' },
      { name: '', num: '', unit: '' }
    ]
  }
});
</script>

<style lang="scss" scoped>
.chart-customize2-css {
  background: url("@/assets/chart/images/dataV-01.jpg") no-repeat;
  background-size: 100% 100%;
  margin: 25px;
  height: calc(100% - 50px - 35px);

  .f1 {
    padding-top: 20px;
    display: flex;
    flex-wrap: nowrap;
    justify-content: space-between;
    align-items: center;
    flex-direction: row;

    span {
      margin-left: 70px;
      color: #ffffff;
      font-size: 16px;
    }

    div {
      margin-right: 30px;
      color: #0eafff;
      font-size: 14px;

      i {
        font-size: 26px;
        font-weight: bold;
      }
    }
  }

  .f2 {
    display: flex;
    flex-wrap: nowrap;
    justify-content: space-between;
    align-items: center;
    flex-direction: row;
    padding: 28px 30px 0;

    .g1 {
      .h1 {
        color: #ffffff;
        font-size: 14px;
        height: auto;
      }

      .h2 {
        color: #0eafff;
        font-size: 14px;
        height: auto;
        i {
          font-size: 26px;
          font-weight: bold;
        }
      }
    }
  }

  .f3 {
    padding-top: 22px;
  }
}
</style>
