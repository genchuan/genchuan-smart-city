<template>
  <div ref="chartRef" class="chart-line-container" :style="{ width: '100%', height: '100%' }"></div>
</template>

<script setup>
import {ref, watch, onMounted, onUnmounted, computed, nextTick} from 'vue';
import * as echarts from 'echarts';

const props = defineProps({
  xAxis: {
    type: Array,
    required: true,
    default: () => []
  },
  series: {
    type: Array,
    required: true,
    default: () => []
  },
  customOption: {
    type: Object,
    default: () => ({})
  },
  baseFontScale: {
    type: Number,
    default: 1
  }
});

// 图表实例引用（全局变量，避免重复创建）
const chartRef = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（结合基础缩放比例）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 调试日志：打印接收的参数（方便排查数据问题）
console.log('ChartLine1 接收参数：', {
  xAxis: props.xAxis,
  series: props.series,
  baseFontScale: props.baseFontScale
});

// 核心：默认ECharts配置（适配你的深色主题+折线图）
const getDefaultOption = computed(() => {
  // 计算自适应字号
  const legendFontSize = vwToPx(0.7);     // 图例文字
  const tooltipFontSize = vwToPx(0.65);   // 提示框文字
  const axisLabelFontSize = vwToPx(0.6);  // 坐标轴标签文字
  const legendIconSize = vwToPx(0.6);     // 图例图标大小

  return {
    // 背景透明，匹配父容器深色风格
    backgroundColor: 'transparent',
    // 图例样式（适配你的红色/浅色文本）
    legend: {
      top: '0%',
      textStyle: {color: '#ffc1e9', fontSize: legendFontSize},
      itemWidth: legendIconSize,
      itemHeight: legendIconSize,
      icon: 'circle'
    },
    // 提示框样式（深色背景+红色边框）
    tooltip: {
      trigger: 'axis',
      textStyle: {color: '#d9d9d9', fontSize: tooltipFontSize},
      backgroundColor: 'rgba(16, 32, 64, 0.9)',
      borderColor: 'rgba(255, 77, 79, 0.4)',
      borderWidth: 1,
      padding: 10,
      axisPointer: {
        type: 'line',
        lineStyle: {color: 'rgba(255, 77, 79, 0.6)'}
      }
    },
    // 网格（适配深色边框）
    grid: {
      left: '3%',
      right: '4%',
      bottom: '2%',
      top: '20%',
      containLabel: true
    },
    // X轴配置（匹配你的文本颜色）
    xAxis: {
      type: 'category',
      data: props.xAxis,
      axisLine: {lineStyle: {color: 'rgba(255, 77, 79, 0.4)'}},
      axisTick: {lineStyle: {color: 'rgba(255, 77, 79, 0.2)'}},
      axisLabel: {color: '#b3b3b3', fontSize: axisLabelFontSize},
      splitLine: {show: false}
    },
    // Y轴配置
    yAxis: {
      type: 'value',
      axisLine: {lineStyle: {color: 'rgba(255, 77, 79, 0.4)'}},
      axisTick: {lineStyle: {color: 'rgba(255, 77, 79, 0.2)'}},
      axisLabel: {color: '#b3b3b3', fontSize: axisLabelFontSize},
      splitLine: {lineStyle: {color: 'rgba(255, 77, 79, 0.1)'}}
    },
    // 系列数据（折线图核心配置）
    series: props.series.map((item, index) => {
      // 预设颜色（匹配你的应急处置主题色）
      const colors = ['#ff4d4f', '#13ce66', '#faad14', '#409eff', '#722ED1', '#67C23A'];
      return {
        name: item.name,
        type: 'line', // 折线图核心类型
        data: item.data,
        smooth: true, // 平滑折线
        symbol: 'circle', // 数据点样式
        symbolSize: 6, // 数据点大小（固定值，视觉核心点无需自适应）
        lineStyle: {width: 2, color: colors[index % colors.length]},
        itemStyle: {color: colors[index % colors.length]},
        areaStyle: { // 可选：渐变填充（增强视觉效果）
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            {offset: 0, color: `${colors[index % colors.length]}80`},
            {offset: 1, color: `${colors[index % colors.length]}10`}
          ])
        },
        emphasis: {
          symbol: 'circle',
          symbolSize: 8 // 高亮数据点大小（固定值）
        }
      };
    })
  };
});

// 合并默认配置和自定义配置
const finalOption = computed(() => ({
  ...getDefaultOption.value,
  ...props.customOption
}));

// 初始化图表（核心：确保容器存在后再创建）
const initChart = async () => {
  await nextTick(); // 等待DOM渲染完成
  if (!chartRef.value) {
    console.error('ChartLine1：容器元素不存在！');
    return;
  }
  // 销毁旧实例（避免重复创建）
  if (chartInstance) {
    chartInstance.dispose();
  }
  // 创建新实例
  chartInstance = echarts.init(chartRef.value);
  // 设置配置项
  chartInstance.setOption(finalOption.value);
  console.log('ChartLine1：图表初始化成功！');
};

// 监听容器尺寸变化（自适应，新增文本尺寸更新逻辑）
const resizeChart = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const legendFontSize = vwToPx(0.7);
  const tooltipFontSize = vwToPx(0.65);
  const axisLabelFontSize = vwToPx(0.6);
  const legendIconSize = vwToPx(0.6);

  // 先更新文本配置
  chartInstance.setOption({
    legend: {
      textStyle: {fontSize: legendFontSize},
      itemWidth: legendIconSize,
      itemHeight: legendIconSize
    },
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    xAxis: {
      axisLabel: {fontSize: axisLabelFontSize}
    },
    yAxis: {
      axisLabel: {fontSize: axisLabelFontSize}
    }
  });

  // 再调整图表尺寸
  chartInstance.resize();
  console.log('ChartLine1：图表自适应尺寸（含文本更新）！');
};

// 监听props变化（数据更新时重新渲染，新增baseFontScale监听）
watch([() => props.xAxis, () => props.series, () => props.baseFontScale], () => {
  console.log('ChartLine1：数据/缩放比例变化，重新渲染图表');
  initChart();
}, {deep: true});

// 生命周期：挂载时初始化
onMounted(() => {
  initChart();
  // 监听窗口resize
  window.addEventListener('resize', resizeChart);
});

// 生命周期：卸载时销毁实例（避免内存泄漏）
onUnmounted(() => {
  window.removeEventListener('resize', resizeChart);
  if (chartInstance) {
    chartInstance.dispose();
    chartInstance = null;
    console.log('ChartLine1：图表实例已销毁');
  }
});

// 暴露方法（父组件可手动触发resize）
defineExpose({
  resizeChart
});
</script>

<style scoped>
.chart-line-container {
  width: 100% !important;
  height: 100% !important;
  overflow: hidden !important;
  position: relative !important;
  z-index: 1 !important;
}
</style>
