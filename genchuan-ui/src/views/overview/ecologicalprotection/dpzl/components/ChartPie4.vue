<template>
  <div class="chart-pie-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, watch, onMounted, onUnmounted} from 'vue';
import * as echarts from 'echarts';

const props = defineProps({
  data: {
    type: Object,
    default: () => ({
      legend: [],
      series: [{name: '', data: []}]
    })
  },
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

const PIE_COLORS = [
  '#13ce66', '#0096ff', '#ff4949', '#ff7d00', '#722ed1', '#eb0aa4'
];

const initChart = () => {
  if (chartInstance) {
    chartInstance.dispose();
  }
  chartInstance = echarts.init(chartRef.value);

  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const labelFontSize = vwToPx(0.7); // 扇区标签文字

  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'item',
      backgroundColor: 'rgba(0, 20, 40, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      },
      formatter: '{b}{a}: <br/> {c}吨 ({d}%)'
    },
    legend: {
      show: false
    },
    series: props.data.series.map(item => ({
      ...item,
      type: 'pie',
      radius: ['40%', '70%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 4,
        borderColor: 'rgba(0, 30, 60, 0.5)',
        borderWidth: 2
      },
      label: {
        show: true,
        position: 'outside',
        color: 'rgba(245,255,139,0.9)',
        fontSize: labelFontSize, // 扇区标签文字自适应
        formatter: '{b}'
      },
      labelLine: {
        show: true,
        length: 15,
        length2: 20,
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.5)'
        }
      },
      color: PIE_COLORS
    }))
  };

  chartInstance.setOption(option);
};

// 窗口大小变化时更新字体并调整尺寸
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const labelFontSize = vwToPx(0.7);

  // 更新文本配置
  chartInstance.setOption({
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    series: [{
      label: {fontSize: labelFontSize}
    }]
  });

  chartInstance.resize();
};

onMounted(() => {
  chartRef.value.style.height = props.height;
  initChart();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  chartInstance?.dispose();
  window.removeEventListener('resize', handleResize);
});

// 监听数据及缩放比例变化，重新渲染图表
watch([() => props.data, () => props.baseFontScale], () => {
  initChart();
}, {deep: true});
</script>

<style scoped>
.chart-pie-container {
  width: 100%;
  height: 100%;
}
</style>
