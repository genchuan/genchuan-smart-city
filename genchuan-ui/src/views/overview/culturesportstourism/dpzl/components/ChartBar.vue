<template>
  <div class="chart-bar-container" ref="chartContainer"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';
import { useRouter } from 'vue-router';

// 接收父组件传递的参数
const props = defineProps({
  data: {
    type: Object,
    required: true,
    default: () => ({ xAxis: [], series: [] })
  },
  xAxisName: { type: String, default: '' },
  yAxisName: { type: String, default: '' },
  height: { type: String, default: '100%' },
  baseFontScale: { type: Number, default: 1 },
  // 第一个柱子跳转路径（原配置）
  jumpPath1: {
    type: String,
    default: '/overview/tourismscenicspot/dpzl'
  },
  // 新增：第二个柱子跳转路径（场馆页面）
  jumpPath2: {
    type: String,
    default: '/overview/tourismvenue/dpzl'
  },
  // 第三个柱子跳转路径（原新增配置，变量名调整为jumpPath3避免混淆）
  jumpPath3: {
    type: String,
    default: '/overview/tourismactivity/dpzl'
  },
  targetBarName: { type: String, default: '景区资源数量' }
});

const chartContainer = ref(null);
let chartInstance = null;
const router = useRouter();

// 第一个柱子跳转函数（原有）
const jumpToTourismScenicSpot = () => {
  try {
    router.push(props.jumpPath1);
  } catch (error) {
    console.error('跳转景区资源页面失败：', error);
  }
};

// 新增：第二个柱子跳转函数（场馆页面）
const jumpToTourismVenue = () => {
  try {
    router.push(props.jumpPath2); // 使用props中的跳转路径，更灵活
  } catch (error) {
    console.error('跳转旅游场馆页面失败：', error);
  }
};

// 第三个柱子跳转函数（原新增，函数名调整更语义化）
const jumpToTourismActivity = () => {
  try {
    router.push(props.jumpPath3);
  } catch (error) {
    console.error('跳转旅游活动页面失败：', error);
  }
};

// 计算 vw 对应的 px 值
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 初始化图表
const initChart = () => {
  if (!chartContainer.value) return;

  if (chartInstance) {
    chartInstance.dispose();
  }

  const tooltipFontSize = vwToPx(0.65);
  const axisLabelFontSize = vwToPx(0.6);
  const axisNameFontSize = vwToPx(0.7);

  chartInstance = echarts.init(chartContainer.value);

  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      axisPointer: { type: 'shadow' },
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      textStyle: { color: '#fff', fontSize: tooltipFontSize }
    },
    grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
    xAxis: {
      type: 'category',
      data: props.data.xAxis,
      name: props.xAxisName,
      nameTextStyle: { color: '#ccc', fontSize: axisNameFontSize },
      axisLine: { lineStyle: { color: 'rgba(0, 204, 255, 0.3)' } },
      axisLabel: { color: '#ccc', fontSize: axisLabelFontSize },
      splitLine: { show: false }
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: { color: '#ccc', fontSize: axisNameFontSize },
      axisLine: { lineStyle: { color: 'rgba(0, 204, 255, 0.3)' } },
      axisLabel: { color: '#ccc', fontSize: axisLabelFontSize },
      splitLine: { lineStyle: { color: 'rgba(0, 204, 255, 0.1)' } }
    },
    series: props.data.series.map(series => ({
      ...series,
      type: 'bar',
      barWidth: '60%',
      emphasis: {
        itemStyle: { shadowBlur: 10, shadowColor: 'rgba(0, 204, 255, 0.5)' }
      }
    }))
  };

  chartInstance.setOption(option);

  // 绑定点击事件：区分不同柱子跳转
  chartInstance.on('click', (params) => {
    // 第一个柱子（dataIndex=0）
    if (params.dataIndex === 0) {
      jumpToTourismScenicSpot();
    }
    // 新增：第二个柱子（dataIndex=1）→ 跳转场馆页面
    else if (params.dataIndex === 1) {
      jumpToTourismVenue();
    }
    // 第三个柱子（dataIndex=2）→ 跳转活动页面（原逻辑）
    else if (params.dataIndex === 2) {
      jumpToTourismActivity();
    }
    // 可扩展：其他柱子的跳转逻辑
    // else if (params.dataIndex === N) { ... }
  });
};

// 监听数据变化重渲染
watch(
  () => [props.data, props.baseFontScale],
  () => initChart(),
  { deep: true }
);

// 窗口resize适配
const handleResize = () => {
  if (!chartInstance) return;
  const tooltipFontSize = vwToPx(0.65);
  const axisLabelFontSize = vwToPx(0.6);
  const axisNameFontSize = vwToPx(0.7);
  chartInstance.setOption({
    tooltip: {textStyle: {fontSize: tooltipFontSize}},
    xAxis: {
      nameTextStyle: {fontSize: axisNameFontSize},
      axisLabel: {fontSize: axisLabelFontSize}
    },
    yAxis: {
      nameTextStyle: {fontSize: axisNameFontSize},
      axisLabel: {fontSize: axisLabelFontSize}
    }
  });
  chartInstance.resize();
};

onMounted(() => {
  initChart();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
  if (chartInstance) {
    chartInstance.off('click'); // 解绑点击事件
    chartInstance.dispose();
    chartInstance = null;
  }
});
</script>

<style scoped>
.chart-bar-container {
  width: 100%;
  height: v-bind(height);
}
</style>
