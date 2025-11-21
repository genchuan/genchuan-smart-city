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
      legend: [], // 图例数据（如：['制造业', '服务业']）
      series: []  // 系列数据（支持两种格式：[100, 200] 或 [{name: 'xx', value: 100}, ...]）
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

  chartInstance.value = echarts.init(chartRef.value);

  // 核心修复：格式化系列数据，确保每个数据项都有name（与图例匹配）
  const formattedSeries = props.data.series.map(seriesItem => {
    // 处理数据格式：将数值数组转换为 {name, value} 对象数组
    const formattedData = seriesItem.data.map((item, index) => {
      // 如果是已包含name的对象，直接使用
      if (typeof item === 'object' && item.name) {
        return item;
      }
      // 否则自动匹配图例的name
      return {
        name: props.data.legend[index] || `类别${index + 1}`,
        value: item
      };
    });
    return {...seriesItem, data: formattedData};
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
      textStyle: {color: '#fff'},
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    // legend: {
    //   orient: 'vertical',
    //   right: 5,
    //   textStyle: {color: '#ccc', fontSize: 12},
    //   data: props.data.legend,
    //   // 图例显示名称+数值
    //   formatter: function (name) {
    //     const target = formattedSeries[0]?.data.find(item => item.name === name);
    //     return target ? `${name} (${target.value})` : name;
    //   }
    // },
    series: formattedSeries.map(item => ({
      ...item,
      type: 'pie',
      radius: '60%',
      center: ['45%', '45%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 6,
        borderColor: 'rgba(0, 30, 60, 0.8)',
        borderWidth: 2
      },
      label: {show: false},
      emphasis: {
        scale: true,
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      },
      labelLine: {show: false},
      color: colorScheme
    }))
  };

  chartInstance.value.setOption(option);
  setTimeout(() => chartInstance.value?.resize(), 0);
};

// 监听数据变化，重新渲染图表
watch(() => props.data, () => {
  if (chartInstance.value) initChart();
}, {deep: true});

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

<style scoped></style>
