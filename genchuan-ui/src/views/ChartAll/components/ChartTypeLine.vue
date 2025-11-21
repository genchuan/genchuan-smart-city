<template>
  <div class="chart-type-line" :style="{ height: height }">
    <div class="chart-type-line-left" :id="idName"
         :style="{ height: lineHeight, width: lineWidth }"></div>
    <ul class="chart-type-line-right" v-if="rightData.length > 0">
      <li v-for="(item, key) in rightData" :key="key">
        <span class="c1"></span>
        <div class="c2">
          <span>{{ item.title }}</span>
          <label><i>{{ item.num }}</i> {{ item.unit }}</label>
        </div>
      </li>
    </ul>
  </div>
</template>

<script setup>
import {onMounted, defineProps} from 'vue';
import * as echarts from 'echarts';

const props = defineProps({
  height: {
    type: String,
    default: 'calc(100% - 35px)'
  },
  lineHeight: {
    type: String,
    default: '100%'
  },
  lineWidth: {
    type: String,
    default: '300px'
  },
  idName: {
    type: String,
    default: 'picLeftId'
  },
  rightData: {
    type: Array,
    default: () => []
  },
  xAxisData: {
    type: Array,
    default: () => []
  },
  yAxisName: {
    type: String,
    default: ''
  },
  seriesData: {
    type: Array,
    default: () => []
  },
  seriesName: {
    type: String,
    default: ''
  }
});

const lineGraph = () => {
  const chartDom = document.getElementById(props.idName);
  const myChart = echarts.init(chartDom);
  const option = {
    title: {},
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    grid: {
      top: '20',
      left: '0',
      right: '0',
      bottom: '15',
      containLabel: true
    },
    xAxis: [
      {
        type: 'category',
        data: props.xAxisData,
        axisTick: {
          show: false
        },
        axisLabel: {
          margin: 20,
          color: '#96a7c0'
        },
        axisLine: {
          show: false,
          lineStyle: {
            color: '#d2d2d2'
          }
        }
      }
    ],
    yAxis: [
      {
        type: 'value',
        name: props.yAxisName,
        nameTextStyle: {
          fontSize: 16,
          color: '#ffffff',
          nameLocation: 'start',
          align: 'right'
        },
        splitLine: {
          show: true,
          lineStyle: {
            color: '#001845'
          }
        },
        axisLine: {
          show: true,
          lineStyle: {
            color: '#001845'
          }
        },
        axisLabel: {
          color: '#96a7c0'
        }
      }
    ],
    series: [
      {
        name: props.seriesName,
        type: 'bar',
        barWidth: '60%',
        itemStyle: {
          // barBorderRadius: [12, 12, 0, 0],
          borderRadius: [12, 12, 0, 0],
          color: {
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            type: 'linear',
            global: false,
            colorStops: [
              {
                offset: 0,
                color: '#02a8f1'
              },
              {
                offset: 1,
                color: '#0c1e65'
              }
            ]
          }
        },
        data: props.seriesData
      }
    ]
  };

  option && myChart.setOption(option);
};

onMounted(() => {
  lineGraph();
});
</script>

<style lang="scss" scoped>
.chart-type-line {
  display: flex;
  flex-wrap: nowrap;
  justify-content: flex-start;
  align-items: center;
  flex-direction: row;
  padding: 0 20px;

  .chart-type-line-right {
    flex: 1;

    li {
      display: flex;
      flex-wrap: nowrap;
      justify-content: flex-start;
      align-items: center;
      flex-direction: row;
      background: #001118;
      padding: 5px 0;

      .c1 {
        background-image: linear-gradient(#03a0e9, #0f5fbf);
        width: 8px;
        height: 35px;
        border-radius: 12px;
        margin-right: 15px;
      }

      .c2 {
        span {
          color: #ffffff;
          font-size: 14px;
          display: block;
        }

        label {
          color: #0eafff;
          font-size: 16px;

          i {
            font-size: 22px;
            font-weight: bold;
          }

        }
      }
    }

    li:nth-of-type(2) {
      margin: 8px 0;
    }
  }
}
</style>
