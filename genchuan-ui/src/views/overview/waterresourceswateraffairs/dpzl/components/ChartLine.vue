<template>
  <div :id="chartId" class="chart-container"></div>
</template>

<script setup>
import { ref, onMounted, watch, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 定义组件接收的参数
const props = defineProps({
  // 图表数据 { xAxis: [], series: [{name: '', data: []}, ...] }
  data: {
    type: Object,
    required: true
  },
  // x轴标签旋转角度
  rotateXAxisLabel: {
    type: Number,
    default: 0
  },
  // 图表标题
  title: {
    type: String,
    default: ''
  },
  // y轴名称
  yAxisName: {
    type: String,
    default: ''
  },
  // 新增：基础字体缩放比例（可选）
  baseFontScale: {
    type: Number,
    default: 1
  }
});

// 生成唯一ID
const chartId = ref(`line-chart-${Date.now()}`);
let chartInstance = null;

// 计算 vw 对应的 px 值（1vw = 视口宽度的 1%）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 初始化图表
const initChart = () => {
  // 销毁已有实例
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 计算自适应字号
  const titleFontSize = vwToPx(0.8); // 标题字体
  const xAxisLabelFontSize = vwToPx(0.6); // x轴标签字体
  const yAxisLabelFontSize = vwToPx(0.6); // y轴标签字体
  const yAxisNameFontSize = vwToPx(0.7); // y轴名称字体
  const tooltipFontSize = vwToPx(0.65); // 提示框字体

  // 创建新实例
  chartInstance = echarts.init(document.getElementById(chartId.value));

  // 线颜色配置
  const lineColors = [
    '#00ccff',  // 亮蓝色
    '#11e48a',  // 绿色
    '#ff7d00',  // 橙色
    '#ff4d4f'   // 红色
  ];

  // 处理系列数据
  const seriesData = props.data.series.map((series, index) => {
    const colorIndex = index % lineColors.length;

    return {
      ...series,
      type: 'line',
      symbol: 'circle',
      symbolSize: 8,
      lineStyle: {
        width: 3,
        color: lineColors[colorIndex]
      },
      itemStyle: {
        color: lineColors[colorIndex],
        borderWidth: 2,
        borderColor: '#fff'
      },
      areaStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {offset: 0, color: `${lineColors[colorIndex]}80`},
          {offset: 1, color: `${lineColors[colorIndex]}00`}
        ])
      }
    };
  });

  // 配置项
  const option = {
    backgroundColor: 'transparent',
    title: {
      text: props.title,
      left: 'center',
      textStyle: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: titleFontSize // 标题自适应
      }
    },
    tooltip: {
      trigger: 'axis',
      backgroundColor: 'rgba(15, 23, 54, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      borderWidth: 1,
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 提示框自适应
      }
    },
    grid: {
      left: '5%',
      right: '5%',
      bottom: '10%',
      top: props.data.series.length > 1 ? '25%' : '15%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: props.data.xAxis,
      axisLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.2)'
        }
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        rotate: props.rotateXAxisLabel,
        fontSize: xAxisLabelFontSize // x轴标签自适应
      },
      splitLine: {
        show: false
      }
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: yAxisNameFontSize // y轴名称自适应
      },
      axisLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.2)'
        }
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: yAxisLabelFontSize // y轴标签自适应
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.1)'
        }
      }
    },
    series: seriesData
  };

  // 设置配置项
  chartInstance.setOption(option);
};

// 处理窗口大小变化：更新字号并调整图表
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const titleFontSize = vwToPx(0.8);
  const xAxisLabelFontSize = vwToPx(0.6);
  const yAxisLabelFontSize = vwToPx(0.6);
  const yAxisNameFontSize = vwToPx(0.7);
  const tooltipFontSize = vwToPx(0.65);

  // 更新图表文本大小
  chartInstance.setOption({
    title: {
      textStyle: {fontSize: titleFontSize}
    },
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    xAxis: {
      axisLabel: {fontSize: xAxisLabelFontSize}
    },
    yAxis: {
      axisLabel: {fontSize: yAxisLabelFontSize},
      nameTextStyle: {fontSize: yAxisNameFontSize}
    }
  });

  // 调整图表尺寸
  chartInstance.resize();
};

// 初始化图表
onMounted(() => {
  initChart();
  window.addEventListener('resize', handleResize);
});

// 监听数据变化，重新渲染图表
watch(() => props.data, () => {
  initChart();
}, {deep: true});

// 监听其他属性变化（包含新增的baseFontScale）
watch([() => props.rotateXAxisLabel, () => props.title, () => props.yAxisName, () => props.baseFontScale], () => {
  initChart();
});

// 组件卸载时清理
onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
  if (chartInstance) {
    chartInstance.dispose();
  }
});
</script>

<style scoped>
.chart-container {
  width: 100%;
  height: 100%;
}
</style>
