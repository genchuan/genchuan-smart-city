<template>
  <div class="chart-type-poly-lines-css" :style="{ height: height }">
    <div :id="idName" :style="{ height: polyLinesHeight, width: polyLinesWidth }"></div>
  </div>
</template>

<script setup>
import { onMounted, defineProps, watch, nextTick } from 'vue';
import * as echarts from 'echarts';

const props = defineProps({
  // 组件整体高度（默认占满父容器）
  height: {
    type: String,
    default: '100%'
  },
  // 图表内部高度（默认占满组件）
  polyLinesHeight: {
    type: String,
    default: '100%'
  },
  // 图表内部宽度（默认占满组件）
  polyLinesWidth: {
    type: String,
    default: '100%'
  },
  // 图表容器ID（避免重复）
  idName: {
    type: String,
    default: 'polyLinesId'
  },
  // 核心数据：与原ChartLine保持一致的结构 { xAxis: [], series: [] }
  data: {
    type: Object,
    default: () => ({
      xAxis: [], // X轴标签数据
      series: [] // 系列数据（[{name: '', data: []}]）
    })
  },
  // Y轴顶部显示名称（与原ChartLine一致）
  yAxisName: {
    type: String,
    default: ''
  }
});

// 预设默认颜色（如果系列数据没有指定颜色，会使用这些默认值）
const defaultLineColors = [
  '#02a8f1', // 浅蓝色
  '#13ce66', // 绿色
  '#ff7d00', // 橙色
  '#ff4949', // 红色
  '#722ed1'  // 紫色
];
const defaultAreaColors = [
  ['rgba(2, 168, 241, 0.5)', 'rgba(12, 30, 101, 0.1)'], // 浅蓝色渐变
  ['rgba(19, 206, 102, 0.5)', 'rgba(10, 50, 10, 0.1)'],  // 绿色渐变
  ['rgba(255, 125, 0, 0.5)', 'rgba(50, 30, 10, 0.1)'],   // 橙色渐变
  ['rgba(255, 73, 73, 0.5)', 'rgba(50, 10, 10, 0.1)'],   // 红色渐变
  ['rgba(114, 46, 209, 0.5)', 'rgba(30, 10, 50, 0.1)']   // 紫色渐变
];

// 初始化图表
const initChart = () => {
  const chartDom = document.getElementById(props.idName);
  if (!chartDom) return;

  // 销毁已有实例，避免重复渲染
  echarts.dispose(chartDom);
  const myChart = echarts.init(chartDom);

  // 处理数据：将原data转换为图表所需格式
  const {xAxis: xAxisData, series: rawSeries} = props.data;

  // 处理系列数据（补充颜色等样式）
  const series = rawSeries.map((item, index) => {
    // 优先使用系列数据自带的样式，没有则用默认值
    const lineColor = item.lineColor || defaultLineColors[index % defaultLineColors.length];
    const areaColor = item.areaColor || defaultAreaColors[index % defaultAreaColors.length];

    return {
      name: item.name || `系列${index + 1}`,
      type: 'line',
      symbolSize: 1, // 折线拐点大小
      smooth: true, // 平滑曲线
      showSymbol: false, // 不显示拐点
      data: item.data || [], // 纵坐标值
      lineStyle: {
        width: 2,
        color: lineColor // 线条颜色
      },
      areaStyle: {
        // 区域渐变填充
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {offset: 0, color: areaColor[0]},
          {offset: 1, color: areaColor[1]}
        ])
      }
    };
  });

  // 图表配置项
  const option = {
    color: series.map(item => item.lineStyle.color), // 图例颜色与线条一致
    tooltip: {
      trigger: 'axis',
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      textStyle: {color: '#fff'}
    },
    grid: {
      left: 10,
      top: 30,
      right: 38,
      bottom: 15,
      containLabel: true
    },
    legend: {
      top: 2,
      right: 20,
      itemGap: 10,
      itemWidth: 15,
      itemHeight: 10,
      textStyle: {
        color: '#fff',
        fontSize: 12
      },
      data: series.map(item => item.name)
    },
    xAxis: {
      type: 'category',
      axisTick: {show: false}, // 隐藏刻度线
      axisLabel: {
        color: '#96a7c0', // 坐标轴文字颜色
        interval: 'auto'
      },
      boundaryGap: false, // 取消左侧间隙
      axisLine: {show: false}, // 隐藏轴线
      data: xAxisData
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: '#fff',
        fontSize: 12
      },
      min: 0,
      minInterval: 1,
      axisTick: {show: false}, // 隐藏刻度线
      splitLine: {
        show: true,
        lineStyle: {color: '#002f56', width: 1}
      },
      axisLabel: {
        color: '#96a7c0',
        fontSize: 12
      }
    },
    series
  };

  myChart.setOption(option);

  // 监听窗口大小变化，自动调整图表
  const handleResize = () => {
    myChart.resize();
  };
  window.addEventListener('resize', handleResize);

  // 组件卸载时清理
  const cleanup = () => {
    window.removeEventListener('resize', handleResize);
    echarts.dispose(myChart);
  };
  return cleanup;
};

// 初始化图表
let cleanupFn;
onMounted(() => {
  cleanupFn = initChart();
});

// 数据变化时重新渲染图表
watch(
  () => [props.data, props.yAxisName],
  () => {
    nextTick(() => {
      if (cleanupFn) cleanupFn(); // 先清理旧实例
      cleanupFn = initChart();
    });
  },
  {deep: true}
);
</script>

<style lang="scss" scoped>
.chart-type-poly-lines-css {
  width: 100%;
  padding: 0;
  box-sizing: border-box;
}
</style>
