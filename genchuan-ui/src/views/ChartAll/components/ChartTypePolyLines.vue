<template>
  <div class="chart-type-poly-lines-css" :style="{ height: height }">
    <div :id="idName" :style="{ height: polyLinesHeight, width: polyLinesWidth }"></div>
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
  polyLinesHeight: {
    type: String,
    default: '100%'
  },
  polyLinesWidth: {
    type: String,
    default: '100%'
  },
  idName: {
    type: String,
    default: 'polyLinesId'
  },
  // x轴数据
  xAxisData: {
    type: Array,
    default: () => []
  },
  // y轴顶部显示名称
  yAxisName: {
    type: String,
    default: ''
  },
  // 数据集合
  polyLinesData: {
    type: Array,
    default: () => []
  }
});

const polyLinesChart = () => {
  const chartDom = document.getElementById(props.idName);
  const myChart = echarts.init(chartDom);
  let option;
  let color = []; // X轴线的颜色
  let legendName = [];
  let series = [];
  for (let i = 0; i < props.polyLinesData.length; i++) {
    color.push(props.polyLinesData[i].lineColor);
    legendName.push({ name: props.polyLinesData[i].name, textStyle: { color: '#fff' } });
    if (props.polyLinesData[i].type === 'line') {
      let jsonData = {
        name: props.polyLinesData[i].name,
        type: 'line',
        symbolSize: 1, // 折线拐点大小
        smooth: true,
        showSymbol: false, // 去除圆点
        data: props.polyLinesData[i].data, // 纵坐标值
        lineStyle: {
          width: 2
        },
        areaStyle: {
          // normal: {
            // 渐变填充色（线条下半部分）
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              { offset: 0, color: props.polyLinesData[i].color.length > 0? props.polyLinesData[i].color[0] : '#02a8f1' },
              { offset: 1, color: props.polyLinesData[i].color.length > 1? props.polyLinesData[i].color[1] : '#0c1e65' }
            ])
          // }
        }
      };
      series.push(jsonData);
    }
  }
  option = {
    color: color,
    tooltip: {
      trigger: 'axis'
    },
    grid: {
      left: 10,
      top: 30,
      right: 38,
      bottom: 15,
      containLabel: true // false是依据坐标轴来对齐的,true是依据坐标轴上面的文字边界来对齐
    },
    legend: {
      top: 2,
      right: 20,
      itemGap: 5, // 图例每项之间的间隔
      height: 10,
      itemWidth: 15, // 图例标记的图形宽度
      itemHeight: 10,
      padding: [5, 0, 0, 0],
      textStyle: {
        padding: [1, 0, 0, -5]
      },
      data: legendName
    },
    xAxis: {
      type: 'category',
      axisTick: {
        show: false // 去掉刻度线
      },
      axisLabel: {
        color: '#96a7c0', // 坐标上字的颜色
        interval: 'auto', // 0 都显示
        // textStyle: {
          fontSize: 8 // 设置横坐标文字大小为12px
        // }
      },
      boundaryGap: false, // 防止统计图左侧和纵轴有间隙
      axisLine: {
        show: false, // 去掉抽线
        lineStyle: { color: 'lightgray', width: 1 }
      },
      data: props.xAxisData // 横坐标的标签文字
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: '#ffffff',
        nameLocation: 'start'
      },
      min: 0,
      minInterval: 1,
      nameLocation: 'end',
      axisTick: { show: false }, // 隐藏纵坐标刻度小线条
      splitLine: {
        show: true, // 去掉折线图中的横线
        lineStyle: { color: '#002f56', width: 1 }
      },
      axisLabel: {
        color: '#96a7c0' // 坐标上字的颜色
      }
    },
    series: series
  };
  option && myChart.setOption(option);
};

onMounted(() => {
  polyLinesChart();
});
</script>

<style lang="scss" scoped>
.chart-type-poly-lines-css {
  padding: 0 0;
}
</style>
