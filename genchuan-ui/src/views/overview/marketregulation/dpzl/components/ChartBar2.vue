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
  // 基准值，用于对比
  benchmark: {
    type: Number,
    default: 90
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
    if (params.componentType === 'series' && params.seriesIndex === 0) {
      emits('barClick', props.xAxis[params.dataIndex]);
    }
  });

  // 处理数据，添加基准线
  let processedSeries = [...props.series];
  processedSeries.push({
    name: '基准值',
    data: props.xAxis.map(() => props.benchmark),
    type: 'line',
    lineStyle: {
      width: 2,
      color: '#ff4949',
      type: 'dashed'
    },
    symbol: 'none',
    z: 10
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
      min: 0,
      max: 100,
      axisLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#ccc',
        fontSize: 12,
        formatter: `{value}${props.unit}`
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.1)'
        }
      }
    },
    series: processedSeries.map((item, index) => {
      if (index === 0) { // 第一个系列是柱状图
        return {
          ...item,
          type: 'bar',
          barWidth: '50%',
          itemStyle: {
            color: function (params) {
              // 根据值设置不同颜色
              return params.value >= props.benchmark ?
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {offset: 0, color: '#13ce66'},
                  {offset: 1, color: '#0a8f54'}
                ]) :
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {offset: 0, color: '#ff4949'},
                  {offset: 1, color: '#a30000'}
                ]);
            }
          },
          emphasis: {
            itemStyle: {
              color: item.value >= props.benchmark ? '#42b983' : '#ff7d00'
            }
          }
        };
      } else { // 其他系列保持原样（通常是基准线）
        return item;
      }
    })
  };

  chartInstance.value.setOption(option);
};

// 监听数据变化
watch([() => props.xAxis, () => props.series, () => props.benchmark], () => {
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
