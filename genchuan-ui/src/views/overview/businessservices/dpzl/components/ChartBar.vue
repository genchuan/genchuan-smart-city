<template>
  <div class="chart-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, onMounted, watch, defineEmits, onUnmounted } from 'vue';
import * as echarts from 'echarts';

const chartRef = ref(null);
const chartInstance = ref(null);
const emits = defineEmits(['barClick']);

const props = defineProps({
  xAxis: {
    type: Array,
    default: () => []
  },
  series: {
    type: Array,
    default: () => []
  },
  unit: {
    type: String,
    default: ''
  },
  title: { type: String, default: '' },
  baseFontScale: {
    type: Number,
    default: 1
  }
});

const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

const getGridTop = () => {
  const titleFontSize = vwToPx(0.8);
  const topPercent = (titleFontSize * 15) / (window.innerHeight * 0.01);
  return `${Math.max(2, Math.min(15, topPercent))}%`;
};

const initChart = () => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }

  // 计算自适应字号和边距
  const titleFontSize = vwToPx(0.8); // 图表标题
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const axisLabelFontSize = vwToPx(0.6); // 坐标轴标签
  const gridTop = getGridTop(); // 标题与图表主体的自适应边距

  chartInstance.value = echarts.init(chartRef.value);

  // 点击事件
  chartInstance.value.on('click', (params) => {
    if (params.componentType === 'series') {
      emits('barClick', props.xAxis[params.dataIndex]);
    }
  });

  const option = {
    animation: false,
    backgroundColor: 'transparent',
    title: {
      text: props.title,
      textStyle: {
        fontSize: titleFontSize,
        color: 'white'
      },
      left: 'center',
      top: 0
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      },
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      borderWidth: 1,
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      top: gridTop,
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: props.xAxis,
      axisLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#ccc',
        fontSize: axisLabelFontSize,
        rotate: props.xAxis.length > 5 ? 30 : 0
      }
    },
    yAxis: {
      type: 'value',
      axisLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#ccc',
        fontSize: axisLabelFontSize,
        formatter: `{value} ${props.unit}`
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.1)'
        }
      }
    },
    series: props.series.map((item, index) => ({
      ...item,
      type: 'bar',
      barWidth: '50%',
      itemStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {offset: 0, color: index === 0 ? '#00ccff' : '#42b983'},
          {offset: 1, color: index === 0 ? '#0066ff' : '#2c3e50'}
        ])
      },
      emphasis: {
        itemStyle: {
          color: index === 0 ? '#42b983' : '#00ccff'
        }
      }
    }))
  };

  chartInstance.value.setOption(option);
};

// 监听数据及缩放比例变化
watch([() => props.xAxis, () => props.series, () => props.baseFontScale], () => {
  if (chartInstance.value) {
    initChart();
  }
});

// 窗口大小变化
const handleResize = () => {
  if (!chartInstance.value) return;

  // 重新计算自适应字号和边距
  const titleFontSize = vwToPx(0.8);
  const tooltipFontSize = vwToPx(0.65);
  const axisLabelFontSize = vwToPx(0.6);
  const gridTop = getGridTop();

  // 更新文本配置和边距
  chartInstance.value.setOption({
    title: {
      textStyle: {fontSize: titleFontSize}
    },
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    grid: {
      top: gridTop
    },
    xAxis: {
      axisLabel: {fontSize: axisLabelFontSize}
    },
    yAxis: {
      axisLabel: {fontSize: axisLabelFontSize}
    }
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

<style scoped></style>
