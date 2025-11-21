<template>
  <div class="chart-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, onMounted, watch, defineEmits } from 'vue';
import * as echarts from 'echarts';

const chartRef = ref(null);
const chartInstance = ref(null);
const emits = defineEmits(['barClick']);

// 接收的props
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
  title: { type: String, default: '' }
});

// 初始化图表
const initChart = () => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }

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
        fontSize: 16,
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
      borderWidth: 1,
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
        fontSize: 12,
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
          { offset: 0, color: index === 0 ? '#00ccff' : '#42b983' },
          { offset: 1, color: index === 0 ? '#0066ff' : '#2c3e50' }
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

// 监听数据变化
watch([() => props.xAxis, () => props.series], () => {
  if (chartInstance.value) {
    initChart();
  }
});

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
