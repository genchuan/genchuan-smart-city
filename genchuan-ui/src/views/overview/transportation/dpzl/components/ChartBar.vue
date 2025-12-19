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
  // 允许父组件控制基础字号比例（可选）
  baseFontScale: {
    type: Number,
    default: 1
  },
  // 新增：允许父组件自定义渐变透明度（可选）
  gradientStartOpacity: {
    type: String,
    default: '80' // 渐变起始透明度（16进制，80=50%）
  },
  gradientEndOpacity: {
    type: String,
    default: 'F0' // 渐变结束透明度（16进制，F0=94%）
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

  // 设置图表配置（包含自适应字号+渐变色）
  const option = getChartOption();
  chartInstance.setOption(option);
};

// 生成图表配置项（带自适应字号+渐变色）
const getChartOption = () => {
  // 计算各元素自适应字号（基于vw）
  const legendFontSize = vwToPx(0.7); // 图例文字
  const xAxisLabelFontSize = vwToPx(0.6); // X轴标签
  const yAxisLabelFontSize = vwToPx(0.6); // Y轴标签
  const xAxisNameFontSize = vwToPx(0.7); // X轴名称
  const yAxisNameFontSize = vwToPx(0.7); // Y轴名称
  const tooltipFontSize = vwToPx(0.65); // 提示框文字

  // 基础颜色数组（与原有颜色一致，用于生成渐变）
  const baseColors = [
    '#13ce66',
    '#ff7d00',
    '#ff4949',
    '#722ed1'
  ];

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
      name: props.xAxisName || '', // 支持X轴名称传递
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
      // 当前系列的基础颜色（循环使用baseColors）
      const currentBaseColor = baseColors[index % baseColors.length];

      // 生成垂直渐变色（从下到上）
      const normalGradient = new echarts.graphic.LinearGradient(
        0, 1, // 渐变起始点：柱子底部（x=0, y=1）
        0, 0, // 渐变结束点：柱子顶部（x=0, y=0）
        [
          { offset: 0, color: `${currentBaseColor}${props.gradientStartOpacity}` }, // 底部颜色（带透明度）
          { offset: 1, color: `${currentBaseColor}${props.gradientEndOpacity}` }  // 顶部颜色（带透明度）
        ]
      );

      // 生成hover时的渐变色（更亮）
      const hoverGradient = new echarts.graphic.LinearGradient(
        0, 1, 0, 0,
        [
          { offset: 0, color: `${currentBaseColor}A0` }, // 底部（透明度63%）
          { offset: 1, color: currentBaseColor }        // 顶部（完全不透明）
        ]
      );

      return {
        name: item.name,
        type: 'bar',
        data: item.data,
        barMaxWidth: props.barMaxWidth,
        itemStyle: {
          color: normalGradient, // 应用正常状态渐变色
          borderRadius: [4, 4, 0, 0] // 顶部圆角，底部直角
        },
        emphasis: {
          itemStyle: {
            color: hoverGradient, // 应用hover状态渐变色
            borderColor: '#fff',
            borderWidth: 1 // hover时添加白色边框，增强交互感
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
  { deep: true }
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
      textStyle: { fontSize: tooltipFontSize }
    },
    legend: {
      textStyle: { fontSize: legendFontSize }
    },
    xAxis: {
      axisLabel: { fontSize: xAxisLabelFontSize },
      nameTextStyle: { fontSize: xAxisNameFontSize }
    },
    yAxis: {
      axisLabel: { fontSize: yAxisLabelFontSize },
      nameTextStyle: { fontSize: yAxisNameFontSize }
    }
  });

  // 调整图表尺寸
  chartInstance.resize();
};

// 监听基础字号比例变化
watch(
  () => props.baseFontScale,
  () => {
    if (chartInstance) {
      handleResize();
    }
  }
);

// 监听渐变透明度配置变化（可选功能）
watch(
  [() => props.gradientStartOpacity, () => props.gradientEndOpacity],
  () => {
    if (chartInstance) {
      chartInstance.setOption(getChartOption());
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
