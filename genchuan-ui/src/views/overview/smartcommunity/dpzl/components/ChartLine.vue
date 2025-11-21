<template>
  <div class="chart-container">
    <div ref="chartRef" class="chart-wrapper"></div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, watch, nextTick } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传递的参数
const props = defineProps({
  // 图表数据 { xAxis: [], series: [{name: '', data: []}, ...] }
  data: {
    type: Object,
    required: true,
    default: () => ({ xAxis: [], series: [] })
  },
  // Y轴名称
  yAxisName: {
    type: String,
    default: ''
  },
  // 是否显示网格
  showGrid: {
    type: Boolean,
    default: true
  },
  // 是否平滑曲线
  smooth: {
    type: Boolean,
    default: true
  }
});

const chartRef = ref(null);
let chartInstance = null;

// 初始化图表
const initChart = () => {
  // 销毁已存在的图表实例
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 创建新图表实例
  chartInstance = echarts.init(chartRef.value);

  // 设置图表配置
  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      borderWidth: 1,
      textStyle: {
        color: '#fff'
      }
    },
    legend: {
      data: props.data.series.map(item => item.name),
      textStyle: {
        color: '#ccc'
      },
      top: 0
    },
    grid: {
      left: '3%',
      right: '3%',
      bottom: '2%',
      containLabel: true,
      show: props.showGrid,
      lineStyle: {
        color: 'rgba(255, 255, 255, 0.1)'
      }
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: props.data.xAxis,
      axisLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#ccc'
      }
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: '#00ccff'
      },
      axisLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#ccc',
        formatter: props.yAxisName.includes('%') ? '{value}%' : '{value}'
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.1)'
        }
      }
    },
    series: props.data.series.map((item, index) => {
      const colors = ['#00ccff', '#13ce66', '#ff7d00', '#ff4949', '#722ed1', '#fac858'];
      return {
        name: item.name,
        type: 'line',
        data: item.data,
        smooth: props.smooth,
        symbol: 'circle',
        symbolSize: 6,
        showSymbol: false,
        emphasis: {
          showSymbol: true
        },
        lineStyle: {
          width: 2
        },
        itemStyle: {
          color: colors[index % colors.length]
        },
        areaStyle: {
          color: {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [{
              offset: 0, color: colors[index % colors.length] + '80' // 透明度80%
            }, {
              offset: 1, color: colors[index % colors.length] + '00' // 透明度0%
            }]
          }
        }
      };
    })
  };

  chartInstance.setOption(option);
};

// 监听窗口大小变化，重绘图表
const handleResize = () => {
  if (chartInstance) {
    chartInstance.resize();
  }
};

// 当数据变化时重新初始化图表
watch(() => props.data, () => {
  nextTick(() => {
    initChart();
  });
}, { deep: true });

// 组件挂载时初始化图表
onMounted(() => {
  nextTick(() => {
    initChart();
    window.addEventListener('resize', handleResize);
  });
});

// 组件卸载时销毁图表
onUnmounted(() => {
  if (chartInstance) {
    chartInstance.dispose();
    chartInstance = null;
  }
  window.removeEventListener('resize', handleResize);
});
</script>

<style scoped>
.chart-container {
  width: 100%;
  height: 100%;
  position: relative;
}

.chart-wrapper {
  width: 100%;
  height: 100%;
  min-height: 150px;
}
</style>
