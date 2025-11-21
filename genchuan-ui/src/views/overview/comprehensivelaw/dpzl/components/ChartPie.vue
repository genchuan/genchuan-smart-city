<template>
  <div class="chart-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, onMounted, watch, nextTick } from 'vue';
import * as echarts from 'echarts';

const chartRef = ref(null);
const chartInstance = ref(null);

// 接收的props
const props = defineProps({
  data: {
    type: Object,
    default: () => ({
      legend: [],
      series: []
    })
  },
  title: { type: String, default: '' }
});

// 图表颜色方案
const colorScheme = [
  '#00ccff', '#42b983', '#ff7d00', '#ff4949',
  '#9c27b0', '#1976d2', '#8d6e63', '#e53935'
];

// 初始化图表
const initChart = () => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }

  if (!chartRef.value) return;

  chartInstance.value = echarts.init(chartRef.value);

  // 处理数据格式
  const formattedSeries = props.data.series.map(seriesItem => {
    const formattedData = seriesItem.data.map((item, index) => {
      if (typeof item === 'object' && item.name && item.value !== undefined) {
        return item;
      }
      return {
        name: props.data.legend[index] || `类别${index + 1}`,
        value: item
      };
    });
    return { ...seriesItem, data: formattedData };
  });

  const option = {
    animation: false,
    backgroundColor: 'transparent',
    title: {
      text: props.title,
      textStyle: {
        fontSize: 16,
        color: 'white'
      },
      left: 'center'
    },
    tooltip: {
      trigger: 'item',
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      borderWidth: 1,
      textStyle: { color: '#fff' },
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'horizontal',
      left: 'center',
      bottom: 0,
      textStyle: { color: '#ccc', fontSize: 12 },
      data: props.data.legend
    },
    series: formattedSeries.map(item => ({
      ...item,
      type: 'pie',
      radius: ['40%', '70%'],
      center: ['50%', '45%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 4,
        borderColor: 'rgba(0, 30, 60, 0.8)',
        borderWidth: 2
      },
      label: { show: false, position: 'center' },
      emphasis: {
        label: {
          show: true,
          fontSize: 16,
          fontWeight: 'bold',
          color: '#fff',
          formatter: '{b}\n{c} ({d}%)'
        }
      },
      labelLine: { show: false },
      data: item.data,
      color: colorScheme
    }))
  };

  chartInstance.value.setOption(option);
};

// 监听数据变化，重新渲染图表
watch(() => props.data, () => {
  if (chartInstance.value) initChart();
}, { deep: true });

// 窗口大小变化时重绘
const handleResize = () => {
  chartInstance.value?.resize();
};

onMounted(() => {
  initChart();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
  chartInstance.value?.dispose();
});
</script>

<style scoped>
.chart-container {
  width: 100%;
  height: 100%;
  min-height: 200px;
}
</style>
