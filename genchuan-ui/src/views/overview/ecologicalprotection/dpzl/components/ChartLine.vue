<template>
  <div class="chart-line-container" ref="chartContainer"></div>
</template>

<script setup>
import { ref, onMounted, watch, nextTick } from 'vue';
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
  }
});

const chartContainer = ref(null);
let chartInstance = null;

// 初始化图表
const initChart = () => {
  if (chartInstance) {
    chartInstance.dispose();
  }

  if (!chartContainer.value) return;

  chartInstance = echarts.init(chartContainer.value);

  const option = {
    title: {
      text: props.title,
      left: 'center',
      textStyle: {
        color: '#00ccff',
        fontSize: 14
      }
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      },
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      textStyle: {
        color: '#fff'
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
      axisLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#ccc',
        fontSize: 12,
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

// 监听数据变化，重新绘制图表
watch([() => props.xAxis, () => props.series], () => {
  nextTick(() => {
    initChart();
  });
});

// 窗口大小变化时重绘
const handleResize = () => {
  if (chartInstance) {
    chartInstance.resize();
  }
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
