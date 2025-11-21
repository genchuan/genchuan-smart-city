<template>
  <div class="chart-bar-container" ref="chartContainer"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

const props = defineProps({
  data: {
    type: Object,
    required: true,
    default: () => ({ xAxis: [], series: [] })
  },
  xAxisName: { type: String, default: '' },
  yAxisName: { type: String, default: '' },
  height: { type: Number, default: 300 },
  title: { type: String, default: '' },
  showGrid: { type: Boolean, default: true },
  smooth: { type: Boolean, default: false }
});

const emit = defineEmits(['click']);
const chartContainer = ref(null);
let chartInstance = null;

const initChart = () => {
  if (!chartContainer.value) return;

  if (chartInstance) {
    chartInstance.dispose();
  }

  // 数据校验
  const validData = {
    xAxis: Array.isArray(props.data.xAxis) ? props.data.xAxis : [],
    series: Array.isArray(props.data.series) && props.data.series.length > 0
      ? props.data.series
      : [{ name: '数据', data: [] }]
  };

  chartInstance = echarts.init(chartContainer.value);
  const seriesCount = validData.series.length;

  // 动态布局参数
  const barWidth = seriesCount === 1 ? '40%' :
    seriesCount === 2 ? '30%' :
      seriesCount === 3 ? '20%' : '15%';

  const barGap = seriesCount <= 2 ? '10%' : '5%';
  const barCategoryGap = seriesCount <= 2 ? '30%' : '40%';

  // 颜色配置
  const colors = [
    [{ offset: 0, color: 'rgba(0, 255, 150, 0.8)' }, { offset: 1, color: 'rgba(0, 150, 100, 0.6)' }],
    [{ offset: 0, color: 'rgba(255, 200, 0, 0.8)' }, { offset: 1, color: 'rgba(200, 150, 0, 0.6)' }],
    [{ offset: 0, color: 'rgba(255, 80, 80, 0.8)' }, { offset: 1, color: 'rgba(180, 50, 50, 0.6)' }]
  ];

  const option = {
    title: {
      text: props.title,
      left: 'center',
      textStyle: { color: 'rgba(255, 255, 255, 0.9)', fontSize: 16 }
    },
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      axisPointer: { type: 'shadow' },
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      borderWidth: 1,
      textStyle: { color: '#fff' }
    },
    grid: {
      left: '5%',
      right: '12%',
      bottom: seriesCount > 3 ? '15%' : '10%',
      top: props.title ? '15%' : '5%',
      containLabel: true,
      show: props.showGrid
    },
    xAxis: {
      type: 'category',
      data: validData.xAxis,
      axisLine: { lineStyle: { color: 'rgba(255, 255, 255, 0.3)' } },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        rotate: validData.xAxis.length > 5 ? 45 : 0,
        interval: 0
      },
      name: props.xAxisName,
      nameTextStyle: { color: 'rgba(0, 204, 255, 0.9)' }
    },
    yAxis: {
      type: 'value',
      axisLine: { lineStyle: { color: 'rgba(255, 255, 255, 0.3)' } },
      splitLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.1)',
          type: props.showGrid ? 'solid' : 'none'
        }
      },
      axisLabel: { color: 'rgba(255, 255, 255, 0.7)' },
      name: props.yAxisName,
      nameTextStyle: { color: 'rgba(0, 204, 255, 0.9)' }
    },
    series: validData.series.map((item, index) => ({
      ...item,
      type: 'bar',
      barWidth,
      barGap,
      barCategoryGap,
      itemStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, colors[index % 3]),
        borderRadius: [4, 4, 0, 0]
      },
      emphasis: {
        itemStyle: {
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1,
            colors[index % 3].map(c => ({
              ...c,
              color: c.color.replace('0.8', '1').replace('0.6', '0.8')
            }))
          )
        }
      }
    }))
  };

  chartInstance.setOption(option);

  // 点击事件
  chartInstance.on('click', (params) => {
    emit('click', {
      category: params.name,
      series: params.seriesName,
      value: params.value
    });
  });
};

const handleResize = () => {
  if (chartInstance) {
    chartInstance.resize();
  }
};

// 监听数据变化
watch(() => props.data, initChart, { deep: true });

// 监听高度变化
watch(() => props.height, () => {
  if (chartContainer.value) {
    chartContainer.value.style.height = `${props.height}px`;
    handleResize();
  }
});

onMounted(() => {
  if (chartContainer.value) {
    chartContainer.value.style.height = `${props.height}px`;
    initChart();
  }
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
  if (chartInstance) {
    chartInstance.dispose();
  }
});
</script>

<style scoped>
.chart-bar-container {
  width: 100%;
  min-width: 300px;
  transition: height 0.3s ease;
}
</style>
