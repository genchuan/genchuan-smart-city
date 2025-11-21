<template>
  <div class="chart-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted, nextTick } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传递的参数
const props = defineProps({
  data: {
    type: Object,
    required: true,
    default: () => ({
      xAxis: [],
      series: []
    })
  },
  yAxisName: {
    type: String,
    default: ''
  },
  showGrid: {
    type: Boolean,
    default: true
  },
  barMaxWidth: {
    type: Number,
    default: 30
  },
  // 新增：允许父组件控制基础字号比例（可选）
  baseFontScale: {
    type: Number,
    default: 1
  }
});

// 图表实例和DOM引用
const chartRef = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（1vw = 视口宽度的 1%）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 初始化图表
const initChart = () => {
  if (!chartRef.value) return;

  // 销毁已存在的实例
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 创建新实例
  chartInstance = echarts.init(chartRef.value);

  // 设置图表配置（包含自适应字号）
  const option = getChartOption();
  chartInstance.setOption(option);
};

// 生成图表配置项（带自适应字号）
const getChartOption = () => {
  // 计算各元素自适应字号（基于vw）
  const legendFontSize = vwToPx(0.7); // 图例文字
  const xAxisLabelFontSize = vwToPx(0.6); // X轴标签
  const yAxisLabelFontSize = vwToPx(0.6); // Y轴标签
  const xAxisNameFontSize = vwToPx(0.7); // X轴名称
  const yAxisNameFontSize = vwToPx(0.7); // Y轴名称
  const tooltipFontSize = vwToPx(0.65); // 提示框文字

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
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      }
    },
    legend: {
      data: props.data.series.map(item => item.name),
      top: 0,
      textStyle: {
        color: '#ccc',
        fontSize: legendFontSize // 图例文字自适应
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '25vh',
      containLabel: true,
      show: props.showGrid,
      lineStyle: {
        color: 'rgba(255, 255, 255, 0.1)'
      }
    },
    xAxis: {
      type: 'category',
      data: props.data.xAxis,
      axisLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#ccc',
        rotate: 30,
        interval: 0,
        fontSize: xAxisLabelFontSize // X轴标签自适应
      },
      name: props.xAxisName || '', // 补充X轴名称支持（与文件1对齐）
      nameTextStyle: {
        color: '#ccc',
        fontSize: xAxisNameFontSize // X轴名称自适应
      },
      splitLine: {
        show: false
      }
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: '#ccc',
        fontSize: yAxisNameFontSize // Y轴名称自适应
      },
      axisLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#ccc',
        fontSize: yAxisLabelFontSize, // Y轴标签自适应
        formatter: function (value) {
          if (props.yAxisName.includes('%')) {
            return value + '%';
          }
          return value;
        }
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.1)'
        }
      }
    },
    series: props.data.series.map((item, index) => {
      const colors = [
        '#00ccff',
        '#13ce66',
        '#ff7d00',
        '#ff4949',
        '#722ed1'
      ];

      return {
        name: item.name,
        type: 'bar',
        data: item.data,
        barMaxWidth: props.barMaxWidth,
        itemStyle: {
          color: colors[index % colors.length],
          borderRadius: [4, 4, 0, 0]
        },
        emphasis: {
          itemStyle: {
            borderColor: '#fff',
            borderWidth: 1
          }
        }
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

// 窗口大小变化时更新字体大小并刷新图表
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算所有自适应字号并更新
  const legendFontSize = vwToPx(0.7);
  const xAxisLabelFontSize = vwToPx(0.6);
  const yAxisLabelFontSize = vwToPx(0.6);
  const xAxisNameFontSize = vwToPx(0.7);
  const yAxisNameFontSize = vwToPx(0.7);
  const tooltipFontSize = vwToPx(0.65);

  chartInstance.setOption({
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    legend: {
      textStyle: {fontSize: legendFontSize}
    },
    xAxis: {
      axisLabel: {fontSize: xAxisLabelFontSize},
      nameTextStyle: {fontSize: xAxisNameFontSize}
    },
    yAxis: {
      axisLabel: {fontSize: yAxisLabelFontSize},
      nameTextStyle: {fontSize: yAxisNameFontSize}
    }
  });

  // 调整图表尺寸
  chartInstance.resize();
};

// 监听基础字号比例变化（可选功能）
watch(
  () => props.baseFontScale,
  () => {
    if (chartInstance) {
      handleResize();
    }
  }
);

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
  max-height: 25vh;
}
</style>
