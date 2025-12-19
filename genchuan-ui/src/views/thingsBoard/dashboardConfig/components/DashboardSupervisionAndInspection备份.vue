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

  // 设置图表配置
  const option = {
    grid: {
      left: '3%',   // 左留白
      right: '4%',  // 右留白
      bottom: '3%', // 下留白（关键：调小底部留白）
      top: '8%',    // 上留白（关键：调小顶部留白）
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
      data: chartData.value.categories,
      // 隐藏X轴轴线（可选）
      axisLine: { show: false }
    },


    series: [
      {
        data: chartData.value.values.map((value, index) => {
          // 方法2：根据索引动态生成颜色（使用颜色数组）
          return {
            value: value,
            itemStyle: {
              // 可以使用预定义的颜色数组
              color: chartData.value.colors[index],

              // 方法3：根据数值动态设置颜色（示例：数值越大颜色越深）
              // color: `rgba(84, 112, 198, ${Math.min(0.3 + value / 500, 1)})`

              // 方法4：根据条件设置颜色（示例：大于100的柱形用红色）
              // color: value > 100 ? '#ee6666' : '#5470c6'
            }
          };
        }),
        type: 'bar',
        barWidth: '60%' // 柱形宽度
      }
    ]
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
