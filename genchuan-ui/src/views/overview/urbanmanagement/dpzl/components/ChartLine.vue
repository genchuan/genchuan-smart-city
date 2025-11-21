<template>
  <div class="chart-line-container" ref="chartContainer"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传入的参数
const props = defineProps({
  // x轴数据（如：["10/22", "10/23", ..., "10/28"]）
  xAxis: {
    type: Array,
    default: () => []
  },
  // 系列数据（如：[{name: '实际值', data: [96.5, 97.2, ...]}, {name: '目标值', data: [95, 95, ...]}]）
  series: {
    type: Array,
    default: () => []
  },
  // y轴名称（如："数值(%)"）
  yAxisName: {
    type: String,
    default: ""
  },
  // 图表高度（默认300px，可外部调整）
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
  // 销毁已有实例（避免重复创建）
  if (chartInstance) {
    chartInstance.dispose();
  }
  // 创建新实例
  chartInstance = echarts.init(chartContainer.value);

  // 设置图表配置项
  const option = {
    backgroundColor: "transparent", // 透明背景，适配深色主题
    tooltip: {
      trigger: "axis", // 坐标轴触发提示框
      axisPointer: {
        type: "shadow" // 阴影指示器
      },
      backgroundColor: "rgba(0, 30, 60, 0.8)", // 提示框背景
      borderColor: "rgba(0, 204, 255, 0.3)",
      borderWidth: 1,
      textStyle: { color: "#fff" } // 提示框文字颜色
    },
    legend: {
      data: props.series.map(item => item.name), // 图例数据（实际值、目标值）
      top: 0,
      textStyle: {color: "#666"}, // 图例文字颜色
      icon: "circle" // 图例标记为圆形
    },
    grid: {
      left: "3%",
      right: "4%",
      bottom: "3%",
      containLabel: true // 网格包含坐标轴标签
    },
    xAxis: {
      type: "category",
      data: props.xAxis,
      axisLine: {lineStyle: {color: "rgba(0, 204, 255, 0.3)"}}, // x轴线颜色
      axisLabel: {color: "#666"}, // x轴标签颜色
      splitLine: {show: false} // 隐藏x轴网格线
    },
    yAxis: {
      type: "value",
      name: props.yAxisName,
      nameTextStyle: {color: "#00ccff"}, // y轴名称颜色
      axisLine: {lineStyle: {color: "rgba(0, 204, 255, 0.3)"}}, // y轴线颜色
      axisLabel: {
        color: "#666",
        formatter: "{value}%" // 显示百分比（根据实际场景调整）
      },
      splitLine: {
        lineStyle: {color: "rgba(0, 204, 255, 0.1)"} // y轴网格线颜色
      }
    },
    series: props.series.map(item => ({
      ...item,
      symbol: "circle", // 数据点标记为圆形
      symbolSize: 6, // 数据点大小
      itemStyle: {
        color: item.name === "目标值" ? "#ff7d00" : "#00ccff" // 目标值橙色，实际值蓝色
      },
      emphasis: {
        scale: true, // 鼠标 hover 时放大数据点
        itemStyle: {shadowBlur: 10, shadowColor: "rgba(0, 204, 255, 0.5)"}
      }
    }))
  };

  // 设置配置项并渲染
  chartInstance.setOption(option);
};

// 监听props变化，重新渲染图表
watch(
  () => [props.xAxis, props.series, props.yAxisName],
  () => {
    if (chartInstance) {
      initChart(); // 数据变化时重新初始化
    }
  },
  {deep: true} // 深度监听数组变化
);

// 监听窗口大小变化，自适应图表
const handleResize = () => {
  if (chartInstance) {
    chartInstance.resize();
  }
};

// 组件挂载时初始化图表
onMounted(() => {
  // 设置容器高度
  chartContainer.value.style.height = props.height;
  // 初始化图表
  initChart();
  // 监听窗口 resize
  window.addEventListener("resize", handleResize);
});

// 组件卸载时清理
onUnmounted(() => {
  window.removeEventListener("resize", handleResize);
  if (chartInstance) {
    chartInstance.dispose(); // 销毁图表实例
    chartInstance = null;
  }
});
</script>

<style scoped>
.chart-line-container {
  width: 100%;
  height: 100%;
  min-height: 200px; /* 最小高度，避免容器塌陷 */
}
</style>
