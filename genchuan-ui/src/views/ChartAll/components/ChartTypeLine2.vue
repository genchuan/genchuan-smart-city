<template>
  <div class="chart-type-line" :style="{ height: height }">
    <div class="chart-type-line-left chart-type-line-left2" :id="idName" :style="{ height: lineHeight, width: lineWidth }"></div>
  </div>
</template>

<script setup>
import { onMounted, defineProps } from 'vue';
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
    default: '100%'
  },
  idName: {
    type: String,
    default: 'picLeftId'
  },
  // x轴数据
  xAxisData: {
    type: Array,
    default: () => []
  },
  // y轴hover显示名称
  yAxisName: {
    type: String,
    default: ''
  },
  // y轴数据
  seriesData: {
    type: Array,
    default: () => []
  }
});

const lineGraph2 = () => {
  const chartDom = document.getElementById(props.idName);
  const myChart = echarts.init(chartDom);
  let option;

  let series = [];
  for (let i = 0; i < props.seriesData.length; i++) {
    // 柱形图
    if (props.seriesData[i].type === 'bar') {
      let jsonData = {
        name: props.seriesData[i].name? props.seriesData[i].name : '',
        type: 'bar',
        barWidth: 24,
        itemStyle: { // 图形样式
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
                offset: 0, // 0%处的颜色
                color: props.seriesData[i].color.length > 0? props.seriesData[i].color[0] : '#02a8f1'
              },
              {
                offset: 1, // 100%处的颜色
                color: props.seriesData[i].color.length > 1? props.seriesData[i].color[1] : '#0c1e65'
              }
            ]
          }
        },
        data: props.seriesData[i].data
      };
      series.push(jsonData);
    }
  }

  option = {
    title: {},
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type:'shadow'
      }
    },
    grid: {
      top: '15',
      left: '15',
      right: '15',
      bottom: '15',
      containLabel: true
    },
    xAxis: [
      {
        type: 'category',
        data: props.xAxisData,
        axisTick: {
          show: false // 去掉刻度线
        },
        axisLabel: {
          margin: 20,
          color: '#96a7c0' // 坐标上字的颜色
        },
        axisLine: {
          show: false, // 去掉轴线
          // 轴线颜色
          lineStyle: {
            color: '#d2d2d2'
          }
        }
      }
    ],
    yAxis: [
      {
        name: props.yAxisName,
        type: 'value',
        // offset:'-20',//name 的偏移量
        nameTextStyle: {
          fontSize: 16,
          color: '#ffffff',
          nameLocation: 'start',
          align: 'right'
        },
        splitLine: {
          show: true, // 去掉折线图中的横线
          lineStyle: {
            color: '#001845'
          }
        },
        axisLine: {
          show: true, // 去掉轴线
          // 轴线颜色
          lineStyle: {
            color: '#001845'
          }
        },
        axisLabel: {
          color: '#96a7c0' // 坐标上字的颜色
        }
      }
    ],
    series: series
  };

  option && myChart.setOption(option);
};

onMounted(() => {
  lineGraph2();
});
</script>

<style lang="scss" scoped></style>
