<template>
  <div class="chart-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, onMounted, watch, nextTick } from 'vue';
import * as echarts from 'echarts';
const chartRef = ref(null);
const chartInstance = ref(null);

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
  ]),
  new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    {offset: 0, color: '#ff4d4f'},
    {offset: 1, color: '#d4380d'}
  ]),
  new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    {offset: 0, color: '#722ed1'},
    {offset: 1, color: '#391085'}
  ])
];

const initChart = () => {
  if (!chartRef.value) return;

  if (chartInstance.value) {
    chartInstance.value.dispose();
  }

  chartInstance.value = echarts.init(chartRef.value);

  // 处理数据格式 - 支持对象数组和数值数组
  let formattedData = [];

  if (props.data.series && props.data.series[0] && props.data.series[0].data) {
    const seriesData = props.data.series[0].data;

    if (seriesData.length > 0 && typeof seriesData[0] === 'object') {
      // 对象数组格式：{ value, name, itemStyle? }
      formattedData = seriesData.map((item, index) => ({
        value: item.value,
        name: item.name || props.data.legend[index] || `类别${index + 1}`,
        itemStyle: item.itemStyle || { color: colorScheme[index % colorScheme.length] }
      }));
    } else {
      // 数值数组格式
      formattedData = seriesData.map((value, index) => ({
        value,
        name: props.data.legend[index] || `类别${index + 1}`,
        itemStyle: { color: colorScheme[index % colorScheme.length] }
      }));
    }
  }

  const option = {
    animation: true,
    backgroundColor: 'transparent',
    title: {
      text: props.title,
      textStyle: {
        fontSize: 14,
        color: '#f6b5ff'
      },
      left: 'center',
      top: 10
    },
    tooltip: {
      trigger: 'item',
      backgroundColor: 'rgba(30, 41, 59, 0.95)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      borderWidth: 1,
      textStyle: {
        color: '#fff'
      },
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      right: 10,
      top: 'center',
      textStyle: {
        color: '#ccc',
        fontSize: 11
      },
      itemWidth: 12,
      itemHeight: 12,
      data: props.data.legend
    },
    series: [{
      name: props.data.series[0]?.name || '数据',
      type: 'pie',
      radius: ['35%', '65%'],
      center: ['40%', '50%'],
      avoidLabelOverlap: true,
      itemStyle: {
        borderRadius: 6,
        borderColor: 'rgba(0, 30, 60, 0.8)',
        borderWidth: 2
      },
      label: {
        show: true,
        fontSize: 11,
        color: '#e2e8f0',
        formatter: '{b}\n{c} ({d}%)'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: 12,
          fontWeight: 'bold'
        },
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      },
      labelLine: {
        show: true,
        length: 15,
        length2: 10
      },
      data: formattedData
    }]
  };

  chartInstance.value.setOption(option);
  nextTick(() => {
    chartInstance.value?.resize();
  });
};

watch(() => props.data, () => {
  if (chartInstance.value) {
    initChart();
  }
}, {deep: true});

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
