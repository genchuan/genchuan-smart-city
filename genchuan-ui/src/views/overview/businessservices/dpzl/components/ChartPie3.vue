<template>
  <div class="chart-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, onMounted, watch} from 'vue';
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
  new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    {offset: 0, color: '#00ccff'},
    {offset: 1, color: '#0066ff'}
  ]),
  new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    {offset: 0, color: '#42b983'},
    {offset: 1, color: '#0a8f54'}
  ]),
  new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    {offset: 0, color: '#ff7d00'},
    {offset: 1, color: '#d45a00'}
  ])
];

// 初始化图表
const initChart = () => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }

  chartInstance.value = echarts.init(chartRef.value);

  // 处理数据格式
  const formattedData = props.data.series[0]?.data.map((value, index) => ({
    value,
    name: props.data.legend[index] || `类别${index + 1}`
  })) || [];

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
      textStyle: {
        color: '#fff'
      },
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'horizontal', // 改为水平排列
      top: 50, // 距离顶部的距离（可根据需要调整，如10px）
      left: 'center', // 水平居中（也可设为'left'或具体数值靠左/右）
      textStyle: {
        color: '#ccc',
        fontSize: 12
      },
      data: props.data.legend
    },
    series: [{
      name: props.data.series[0]?.name || '数据',
      type: 'pie',
      radius: ['30%', '70%'],
      center: ['55%', '55%'],
      roseType: 'area',
      itemStyle: {
        borderRadius: 8,
        borderColor: 'rgba(0, 30, 60, 0.8)',
        borderWidth: 2
      },
      label: {
        show: false,
        fontSize: 12,
        color: '#fff',
        formatter: '{b}: {c}'
      },
      emphasis: {
        scale: true,
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      },
      labelLine: {
        show: false
      },
      data: formattedData,
      color: colorScheme
    }]
  };

  chartInstance.value.setOption(option);
  setTimeout(() => chartInstance.value?.resize(), 0);
};

// 监听数据变化
watch(() => props.data, () => {
  if (chartInstance.value) {
    initChart();
  }
}, {deep: true});

// 窗口大小变化
const handleResize = () => {
  if (chartInstance.value) {
    chartInstance.value.resize();
  }
};

onMounted(() => {
  initChart();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }
});
</script>

<style scoped>
.chart-container {
  width: 100%;
  height: 100%;
}
</style>
