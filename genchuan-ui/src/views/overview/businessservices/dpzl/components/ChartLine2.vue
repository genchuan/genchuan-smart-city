<template>
  <div class="chart-line-container" ref="chartContainer"></div>
</template>

<script setup>
import { ref, onMounted, watch, nextTick, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传递的参数
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
  title: {
    type: String,
    default: '指标趋势'
  },
  // 新增：基础字体缩放比例
  baseFontScale: {
    type: Number,
    default: 1
  }
});

const chartContainer = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（结合基础缩放比例）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

const getGridTop = () => {
  const titleFontSize = vwToPx(0.8);
  const topPercent = (titleFontSize * 15) / (window.innerHeight * 0.01);
  return `${Math.max(2, Math.min(15, topPercent))}%`;
};

// 初始化图表
const initChart = () => {
  if (chartInstance) {
    chartInstance.dispose();
  }

  if (!chartContainer.value) return;

  // 计算自适应字号和边距
  const titleFontSize = vwToPx(0.8); // 图表标题
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const axisLabelFontSize = vwToPx(0.6); // 坐标轴标签
  const gridTop = getGridTop(); // 自适应边距

  chartInstance = echarts.init(chartContainer.value);

  const option = {
    animation: false,
    title: {
      text: props.title,
      textStyle: {
        fontSize: titleFontSize, // 标题文字自适应
        color: 'white'
      },
      left: 'center'
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      },
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
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
        fontSize: axisLabelFontSize, // x轴标签自适应
        // 处理x轴标签过多的情况
        interval: props.xAxis.length > 12 ? 'auto' : 0,
        rotate: 45
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
        fontSize: axisLabelFontSize, // y轴标签自适应
        formatter: `{value} ${props.unit}`
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.1)'
        }
      }
    },
    series: props.series.map(item => ({
      ...item,
      type: 'line',
      smooth: true,
      symbol: 'circle',
      symbolSize: 6,
      lineStyle: {
        width: 2,
        color: '#00ccff'
      },
      itemStyle: {
        color: '#00ccff',
        borderColor: '#fff',
        borderWidth: 2
      },
      emphasis: {
        scale: true
      },
      areaStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {offset: 0, color: 'rgba(0, 204, 255, 0.3)'},
          {offset: 1, color: 'rgba(0, 204, 255, 0)'}
        ])
      }
    }))
  };

  chartInstance.setOption(option);
};

// 监听数据及缩放比例变化，重新绘制图表
watch([() => props.xAxis, () => props.series, () => props.baseFontScale], () => {
  nextTick(() => {
    initChart();
  });
});

// 窗口大小变化时重绘
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号和边距
  const titleFontSize = vwToPx(0.8);
  const tooltipFontSize = vwToPx(0.65);
  const axisLabelFontSize = vwToPx(0.6);
  const gridTop = getGridTop();

  // 更新文本配置和边距
  chartInstance.setOption({
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

  chartInstance.resize();
};

onMounted(() => {
  window.addEventListener('resize', handleResize);
  initChart();
});

// 组件卸载时清理
onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
  if (chartInstance) {
    chartInstance.dispose();
    chartInstance = null;
  }
});
</script>

<style scoped>
.chart-line-container {
  width: 100%;
  height: 100%;
}
</style>
