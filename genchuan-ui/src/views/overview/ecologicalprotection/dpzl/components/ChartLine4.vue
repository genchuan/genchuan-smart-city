<template>
  <div class="chart-line-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, watch, onMounted, onUnmounted} from 'vue';
import * as echarts from 'echarts';

// 接收父组件传递的参数
const props = defineProps({
  // x轴数据（数组，如 ['1月', '2月']）
  xAxis: {
    type: Array,
    default: () => []
  },
  // 系列数据（数组，如 [{name: '数据1', data: [10, 20]}]）
  series: {
    type: Array,
    default: () => []
  },
  // y轴名称
  yAxisName: {
    type: String,
    default: ''
  },
  // 图表高度（默认300px）
  height: {
    type: String,
    default: '300px'
  },
  // 新增：基础字体缩放比例
  baseFontScale: {
    type: Number,
    default: 1
  }
});

const chartRef = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（结合基础缩放比例）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 初始化图表
const initChart = () => {
  // 销毁已有实例（避免重复渲染）
  if (chartInstance) {
    chartInstance.dispose();
  }
  // 创建实例
  chartInstance = echarts.init(chartRef.value);

  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const axisLabelFontSize = vwToPx(0.6); // 坐标轴标签
  const yAxisNameFontSize = vwToPx(0.7); // y轴名称

  // 配置项
  const option = {
    backgroundColor: 'transparent', // 透明背景（适配父组件风格）
    tooltip: {
      trigger: 'axis',
      axisPointer: {type: 'shadow'},
      backgroundColor: 'rgba(0, 20, 40, 0.8)', // tooltip背景
      borderColor: 'rgba(0, 204, 255, 0.3)',
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: props.xAxis,
      axisLine: {lineStyle: {color: 'rgba(255, 255, 255, 0.3)'}}, // x轴线颜色
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: axisLabelFontSize // x轴标签自适应
      }
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: yAxisNameFontSize // y轴名称自适应
      },
      axisLine: {lineStyle: {color: 'rgba(255, 255, 255, 0.3)'}},
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: axisLabelFontSize // y轴标签自适应
      },
      splitLine: {lineStyle: {color: 'rgba(255, 255, 255, 0.1)'}} // 网格线
    },
    series: props.series.map(item => ({
      ...item,
      type: 'line',
      smooth: true, // 平滑曲线
      symbol: 'circle', // 数据点样式
      symbolSize: 6,
      itemStyle: {borderWidth: 2, borderColor: '#fff'}, // 数据点边框
      lineStyle: {width: 2}
    }))
  };

  // 设置配置项
  chartInstance.setOption(option);
};

// 监听窗口大小变化，更新字体并调整图表尺寸
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const axisLabelFontSize = vwToPx(0.6);
  const yAxisNameFontSize = vwToPx(0.7);

  // 更新文本配置
  chartInstance.setOption({
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    xAxis: {
      axisLabel: {fontSize: axisLabelFontSize}
    },
    yAxis: {
      nameTextStyle: {fontSize: yAxisNameFontSize},
      axisLabel: {fontSize: axisLabelFontSize}
    }
  });

  chartInstance.resize();
};

// 生命周期钩子
onMounted(() => {
  // 设置容器高度
  chartRef.value.style.height = props.height;
  // 初始化图表
  initChart();
  // 监听窗口大小
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  // 销毁实例和事件监听
  chartInstance?.dispose();
  window.removeEventListener('resize', handleResize);
});

// 监听数据及缩放比例变化，重新渲染图表
watch([() => props.xAxis, () => props.series, () => props.yAxisName, () => props.baseFontScale], () => {
  initChart();
});
</script>

<style scoped>
.chart-line-container {
  width: 100%;
  height: 100%;
}
</style>
