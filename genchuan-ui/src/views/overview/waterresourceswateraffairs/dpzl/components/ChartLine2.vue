<template>
  <div class="chart-line-container" ref="chartContainer"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传入的参数
const props = defineProps({
  // x轴数据（日期数组，如：["10/22", "10/23", ...]）
  xAxis: {
    type: Array,
    default: () => []
  },
  // 系列数据（实际值+目标值，如：[{name: '实际日供水量', data: [...]}, ...]）
  series: {
    type: Array,
    default: () => []
  },
  // y轴名称（如："日供水量(万m³)"）
  yAxisName: {
    type: String,
    default: ""
  },
  // 图表高度（默认300px）
  height: {
    type: String,
    default: "300px"
  }
});

// 图表实例和容器
const chartContainer = ref(null);
let chartInstance = null;

// 初始化图表
const initChart = () => {
  // 销毁已有实例，避免重复创建
  if (chartInstance) {
    chartInstance.dispose();
  }
  // 创建新实例
  chartInstance = echarts.init(chartContainer.value);

  // 图表配置项
  const option = {
    backgroundColor: "transparent", // 透明背景，适配深色主题
    tooltip: {
      trigger: "axis", // 坐标轴触发提示
      axisPointer: { type: "line" }, // 线式指示器
      backgroundColor: "rgba(0, 20, 40, 0.9)", // 提示框背景
      borderColor: "rgba(0, 204, 255, 0.5)",
      borderWidth: 1,
      textStyle: { color: "#fff" },
      formatter: (params) => {
        // 自定义提示框格式，显示日期+数值
        let result = `${params[0].name}<br/>`;
        params.forEach(item => {
          result += `${item.seriesName}: ${item.value}${item.unit || ''}<br/>`;
        });
        return result;
      }
    },
    legend: {
      data: props.series.map(item => item.name), // 图例（实际值/目标值）
      top: 0,
      textStyle: { color: "#00ccff" }, // 图例文字颜色
      icon: "circle" // 图例标记为圆形
    },
    grid: {
      left: "5%",
      right: "5%",
      bottom: "10%",
      top: "15%",
      containLabel: true // 包含坐标轴标签
    },
    xAxis: {
      type: "category",
      data: props.xAxis,
      axisLine: { lineStyle: { color: "rgba(0, 204, 255, 0.3)" } }, // x轴线颜色
      axisLabel: {
        color: "#ccc",
        rotate: 30 // 日期标签旋转30度，避免重叠
      },
      splitLine: { show: false } // 隐藏x轴网格线
    },
    yAxis: {
      type: "value",
      name: props.yAxisName,
      nameTextStyle: { color: "#00ccff", padding: [0, 0, 0, 10] }, // y轴名称样式
      axisLine: { lineStyle: { color: "rgba(0, 204, 255, 0.3)" } }, // y轴线颜色
      axisLabel: { color: "#ccc" },
      splitLine: {
        lineStyle: { color: "rgba(0, 204, 255, 0.1)" } // 淡蓝色网格线
      },
      // 根据y轴名称自动适配精度（如百分比保留1位小数）
      axisLabel: {
        formatter: (value) => {
          return props.yAxisName.includes('%') ? `${value.toFixed(1)}%` : value.toFixed(2);
        }
      }
    },
    series: props.series.map((item, index) => ({
      ...item,
      symbol: "circle", // 数据点为圆形
      symbolSize: 6, // 数据点大小
      itemStyle: {
        // 实际值用蓝色，目标值用橙色（与水务系统主题匹配）
        color: index === 0 ? "#00ccff" : "#ff7d00"
      },
      emphasis: {
        symbolSize: 8, // 鼠标hover时放大
        itemStyle: { shadowBlur: 10, shadowColor: "rgba(0, 204, 255, 0.5)" }
      },
      // 继承传入的线样式（目标值为虚线）
      lineStyle: item.lineStyle || { width: 2 }
    }))
  };

  // 渲染图表
  chartInstance.setOption(option);
};

// 监听props变化，重新渲染图表
watch(
  () => [props.xAxis, props.series, props.yAxisName],
  () => {
    if (chartInstance) {
      initChart();
    }
  },
  { deep: true } // 深度监听数组变化
);

// 窗口大小变化时自适应
const handleResize = () => {
  if (chartInstance) {
    chartInstance.resize();
  }
};

// 组件挂载时初始化
onMounted(() => {
  chartContainer.value.style.height = props.height;
  initChart();
  window.addEventListener("resize", handleResize);
});

// 组件卸载时清理
onUnmounted(() => {
  window.removeEventListener("resize", handleResize);
  if (chartInstance) {
    chartInstance.dispose();
    chartInstance = null;
  }
});
</script>

<style scoped>
.chart-line-container {
  width: 100%;
  height: 100%;
  min-height: 200px;
}
</style>
