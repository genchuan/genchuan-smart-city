<template>
  <div class="chart-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, onMounted, watch, onUnmounted} from 'vue';
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
  title: {type: String, default: ''},
  // 新增：基础字体缩放比例
  baseFontScale: {
    type: Number,
    default: 1
  }
});

// 计算 vw 对应的 px 值（结合基础缩放比例）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 图表颜色方案（保留原有渐变效果）
const colorScheme = [
  new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    {offset: 0, color: '#f1c9c9'},
    {offset: 1, color: '#ff0000'}
  ]),
  new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    {offset: 0, color: '#adefff'},
    {offset: 1, color: '#00327c'}
  ]),
  new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    {offset: 0, color: '#a9ffda'},
    {offset: 1, color: '#006739'}
  ])
];

// 初始化图表
const initChart = () => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }

  // 计算自适应字号
  const titleFontSize = vwToPx(0.8); // 图表标题
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const legendFontSize = vwToPx(0.7); // 图例文字
  const labelFontSize = vwToPx(0.6); // 标签文字（默认隐藏，预留适配）

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
        fontSize: titleFontSize, // 标题文字自适应
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
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      },
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'horizontal', // 水平排列
      bottom: 0, // 距离顶部的距离
      left: 'center', // 水平居中
      textStyle: {
        color: '#ccc',
        fontSize: legendFontSize // 图例文字自适应
      },
      itemWidth: vwToPx(0.6), // 图例图标大小自适应
      itemHeight: vwToPx(0.6),
      data: props.data.legend
    },
    series: [{
      name: props.data.series[0]?.name || '数据',
      type: 'pie',
      radius: ['40%', '80%'],
      center: ['55%', '55%'],
      roseType: 'area',
      itemStyle: {
        borderRadius: 8,
        borderColor: 'rgba(0, 30, 60, 0.8)',
        borderWidth: 2
      },
      label: {
        show: false,
        fontSize: labelFontSize, // 标签文字自适应（预留）
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

// 监听数据及缩放比例变化，重新渲染图表
watch([() => props.data, () => props.baseFontScale], () => {
  if (chartInstance.value) {
    initChart();
  }
}, {deep: true});

// 窗口大小变化时更新字体并调整图表尺寸
const handleResize = () => {
  if (!chartInstance.value) return;

  // 重新计算自适应字号
  const titleFontSize = vwToPx(0.8);
  const tooltipFontSize = vwToPx(0.65);
  const legendFontSize = vwToPx(0.7);
  const labelFontSize = vwToPx(0.6);

  // 更新文本配置
  chartInstance.value.setOption({
    title: {
      textStyle: {fontSize: titleFontSize}
    },
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    legend: {
      textStyle: {fontSize: legendFontSize},
      itemWidth: vwToPx(0.6),
      itemHeight: vwToPx(0.6)
    },
    series: [{
      label: {fontSize: labelFontSize}
    }]
  });

  chartInstance.value.resize();
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
  min-height: 30vh;
}
</style>
