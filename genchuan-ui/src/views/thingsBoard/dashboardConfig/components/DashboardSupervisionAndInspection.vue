<template>
  <!--监督检查-->
  <div :class="['dashboard-supervision-and-inspection-css',className]">
    <div ref="chartRef" class="chart-wrapper"></div>
  </div>
</template>

<!-- vue3写法 -->
<script lang="ts" setup name="test">
import {ref, reactive, onMounted} from 'vue'
import * as echarts from 'echarts';
const props = defineProps({
  // class名称
  className: {
    type: String,
    default: ''
  }
})
// 图表容器引用
const chartRef = ref(null);
// 图表实例
let chartInstance = null;

// 响应式数据
const chartData = ref({
  categories: ['昨日巡检', '今日巡检', '本周巡检', '本月巡检'],
  values: [120, 200, 150, 80],
  // 方法1：预定义颜色数组（与数据一一对应）
  colors: ['#5470c6', '#91cc75', '#fac858', '#ee6666']
});

// 初始化图表
const initChart = () => {
  if (!chartRef.value) return;

  // 销毁已有实例（防止重复初始化）
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 创建新实例
  chartInstance = echarts.init(chartRef.value);

  // 柱形设置
  const seriesList = chartData.value.categories.map((category, index) => {
    return {
      name: category, // 系列名称 = 图例项名称（关键关联）
      type: 'bar',
      data: [chartData.value.values[index]], // 每个系列仅1个数据（对应单个柱形）
      itemStyle: {
        color: chartData.value.colors[index], // 系列颜色 = 图例颜色
      },
    };
  });

  // 设置图表配置
  const option = {

    title: {
      text: '监督检查', // 主标题文本
      // subtext: '昨日/今日/本周/本月', // 副标题文本（可选）
      left: '1%', // 标题水平位置（left/center/right 或具体像素值如 '10px'）
      top: '5%', // 标题垂直位置（top/middle/bottom 或具体像素值）
      textStyle: {
        fontSize: 16, // 主标题字体大小
        // fontWeight: 'bold', // 主标题字体粗细
        color: '#333' // 主标题颜色
      },
    },

    grid: {
      left: '3%',   // 左留白
      right: '1%',  // 右留白
      bottom: '4%', // 下留白（关键：调小底部留白）
      top: '25%',    // 上留白（关键：调小顶部留白）
      containLabel: true // 确保标签不被裁剪
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: { type: 'shadow' }
    },

    yAxis: {
      type: 'value',
      // 最小刻度从0开始（可选，根据数据特性决定）
      min: 0,
      // 最大刻度比最大值多5%（避免顶部紧贴数据）
      max: Math.max(...chartData.value.values) * 1.05,
      // 隐藏Y轴轴线和刻度线（可选，减少视觉干扰）
      axisLine: { show: false },
      axisTick: { show: false }
    },

    xAxis: {
      type: 'category',
      data: ['监督检查'],
      // 隐藏轴线
      axisLine: { show: false },
      // 隐藏刻度线
      axisTick: { show: false },
      // 隐藏刻度标签
      axisLabel: { show: false },
      // 隐藏网格线（如果有的话）
      splitLine: { show: false }
    },
    legend: {
      top: '5%',
      left: 'center'
    },

    series: seriesList
  };

  chartInstance.setOption(option);
};

// 监听窗口大小变化
const handleResize = () => {
  chartInstance?.resize();
};


// 组件挂载时初始化
onMounted(() => {
  initChart();
  window.addEventListener('resize', handleResize);
});

// 监听数据变化，更新图表
watch(
    chartData,
    () => {
      initChart(); // 数据变化时重新初始化图表
    },
    { deep: true }
);

// 组件卸载时清理
onUnmounted(() => {
  chartInstance?.dispose();
  window.removeEventListener('resize', handleResize);
});

// 示例：更新数据的方法
const updateData = () => {
  chartData.value.values = [150, 180, 220, 90, 130, 170];
  // 可以同时更新颜色
  chartData.value.colors = ['#c23531', '#2f4554', '#61a0a8', '#d48265', '#91c7ae', '#749f83'];
};
</script>

<style lang="scss" scoped>
.chart-wrapper {
  width: 100%;
  height: 100%;
}
</style>
