<template>
  <div class="chart-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted, nextTick } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传递的参数
const props = defineProps({
  // 图表数据 { xAxis: [], series: [{name: '', data: []}, ...] }
  data: {
    type: Object,
    required: true,
    default: () => ({
      xAxis: [],
      series: []
    })
  },
  // Y轴名称
  yAxisName: {
    type: String,
    default: ''
  },
  // 是否显示网格线
  showGrid: {
    type: Boolean,
    default: true
  },
  // 是否显示面积填充
  showArea: {
    type: Boolean,
    default: false
  }
});

// 图表实例和DOM引用
const chartRef = ref(null);
let chartInstance = null;

// 初始化图表
const initChart = () => {
  if (!chartRef.value) return;

  // 销毁已存在的实例
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 创建新实例
  chartInstance = echarts.init(chartRef.value);

  // 设置图表配置
  const option = getChartOption();
  chartInstance.setOption(option);
};

// 生成图表配置项
const getChartOption = () => {
  return {
    backgroundColor: 'transparent',
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
    legend: {
      data: props.data.series.map(item => item.name),
      top: 0,
      textStyle: {
        color: '#666'
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true,
      show: props.showGrid,
      lineStyle: {
        color: 'rgba(0, 204, 255, 0.1)'
      }
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: props.data.xAxis,
      axisLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#666',
        rotate: 30,
        interval: 0
      },
      splitLine: {
        show: false
      }
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: '#666'
      },
      axisLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#666',
        formatter: function (value) {
          // 如果是百分比，添加%符号
          if (props.yAxisName.includes('%')) {
            return value + '%';
          }
          return value;
        }
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.1)'
        }
      }
    },
    series: props.data.series.map((item, index) => {
      // 预设几种颜色，循环使用
      const colors = [
        '#00ccff', // 亮蓝色
        '#13ce66', // 绿色
        '#ff7d00', // 橙色
        '#ff4949', // 红色
        '#722ed1'  // 紫色
      ];

      return {
        name: item.name,
        type: item.type || 'line',
        data: item.data,
        symbol: 'circle',
        symbolSize: 6,
        smooth: true,
        showSymbol: false,
        emphasis: {
          showSymbol: true
        },
        lineStyle: {
          width: 3,
          color: colors[index % colors.length]
        },
        itemStyle: {
          color: colors[index % colors.length]
        },
        areaStyle: props.showArea ? {
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
        } : undefined,
        // 虚线样式（如果指定）
        ...(item.lineStyle ? {
          lineStyle: {
            ...item.lineStyle,
            color: colors[index % colors.length]
          }
        } : {})
      };
    })
  };
};

// 监听数据变化，更新图表
watch(
  () => props.data,
  () => {
    if (chartInstance) {
      chartInstance.setOption(getChartOption());
    }
  },
  {deep: true}
);

// 监听窗口大小变化，调整图表尺寸
const handleResize = () => {
  if (chartInstance) {
    chartInstance.resize();
  }
};

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
  min-height: 200px;
}
</style>

