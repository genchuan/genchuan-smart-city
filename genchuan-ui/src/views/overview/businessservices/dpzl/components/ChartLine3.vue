<template>
  <div class="chart-line-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传递的参数
const props = defineProps({
  // x轴数据（如 ['10/1', '10/2']）
  xAxis: {
    type: Array,
    default: () => []
  },
  series: {
    type: Array,
    default: () => []
  },
  // y轴名称（如 '浓度 (μg/m³)'）
  yAxisName: {
    type: String,
    default: ''
  },
  // 图表高度（默认自适应父容器，可自定义）
  height: {
    type: String,
    default: '100%'
  },
  title: { type: String, default: '' }
});

const chartRef = ref(null);
let chartInstance = null;

const AIR_CHART_COLORS = [
  '#13ce66', // 绿色（PM2.5）
  '#0096ff', // 蓝色（PM10）
  '#ff7d00', // 橙色（AQI）
  '#722ed1', // 紫色（SO₂）
  '#eb0aa4'  // 粉色（NO₂）
];

// 初始化图表
const initChart = () => {
  // 销毁已有实例，避免重复渲染
  if (chartInstance) {
    chartInstance.dispose();
  }
  // 创建ECharts实例
  chartInstance = echarts.init(chartRef.value);

  // 图表配置项
  const option = {
    animation: false,
    backgroundColor: 'transparent', // 透明背景，融入父组件
    tooltip: {
      trigger: 'axis', // 坐标轴触发
      axisPointer: { type: 'line' }, // 线式指示器
      backgroundColor: 'rgba(0, 20, 40, 0.85)', // 深色半透背景
      borderColor: 'rgba(0, 204, 255, 0.4)', // 边框高亮
      borderWidth: 1,
      textStyle: { color: '#fff', fontSize: 12 }, // 白色文字
      formatter: function(params) {
        // 自定义tooltip格式：时间 + 各指标值
        let res = `<div>${params[0].name}</div>`;
        params.forEach(item => {
          res += `<div style="display: flex; gap: 8px;">
            <span style="display: inline-block; width: 8px; height: 8px; border-radius: 50%; background: ${item.color};"></span>
            <span>${item.seriesName}: ${item.value} ${props.yAxisName.includes('μg/m³') ? 'μg/m³' : ''}</span>
          </div>`;
        });
        return res;
      }
    },
    grid: {
      left: '4%',
      right: '6%',
      bottom: '6%',
      top: '5%',
      containLabel: true // 包含坐标轴标签
    },
    xAxis: {
      type: 'category',
      data: props.xAxis,
      axisLine: {
        lineStyle: { color: 'rgba(255, 255, 255, 0.2)' } // 浅色轴线
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)', // 浅色文字
        fontSize: 12,
        rotate: 30 // 横轴文字旋转，避免重叠
      },
      splitLine: { show: false } // 隐藏x轴网格线
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: 12,
        padding: [0, 0, 0, 10] // 名称与轴线间距
      },
      axisLine: {
        lineStyle: { color: 'rgba(255, 255, 255, 0.2)' }
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: 12
      },
      splitLine: {
        lineStyle: { color: 'rgba(255, 255, 255, 0.1)' } // 浅色网格线
      },
      min: 0 // y轴从0开始，确保数据对比合理
    },
    series: props.series.map((item, index) => ({
      ...item,
      type: 'line',
      smooth: true, // 平滑曲线，更美观
      symbol: 'circle', // 数据点为圆形
      symbolSize: 6, // 数据点大小
      showSymbol: false, // 默认不显示数据点
      emphasis: {
        showSymbol: true, // 鼠标悬停时显示数据点
        symbolSize: 8 // 悬停时放大
      },
      lineStyle: {
        width: 2.5,
        color: AIR_CHART_COLORS[index % AIR_CHART_COLORS.length] // 循环使用预设颜色
      },
      itemStyle: {
        color: AIR_CHART_COLORS[index % AIR_CHART_COLORS.length],
        borderColor: '#fff', // 数据点白色边框
        borderWidth: 1.5
      },
      areaStyle: {
        // 区域填充半透明效果（增强视觉层次）
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          { offset: 0, color: `${AIR_CHART_COLORS[index % AIR_CHART_COLORS.length]}33` }, // 顶部半透
          { offset: 1, color: `${AIR_CHART_COLORS[index % AIR_CHART_COLORS.length]}00` } // 底部透明
        ])
      }
    }))
  };

  // 应用配置项
  chartInstance.setOption(option);
};

// 监听窗口大小变化，自动调整图表尺寸
const handleResize = () => {
  chartInstance?.resize();
};

// 组件挂载时初始化
onMounted(() => {
  // 设置容器高度
  if (props.height) {
    chartRef.value.style.height = props.height;
  }
  // 初始化图表
  initChart();
  // 监听窗口大小
  window.addEventListener('resize', handleResize);
});

// 组件卸载时清理
onUnmounted(() => {
  // 销毁图表实例
  chartInstance?.dispose();
  // 移除事件监听
  window.removeEventListener('resize', handleResize);
});

// 监听数据变化，重新渲染图表
watch([() => props.xAxis, () => props.series, () => props.yAxisName], () => {
  initChart();
}, { deep: true }); // 深度监听对象/数组变化
</script>

<style scoped>
.chart-line-container {
  width: 100%;
  height: 100%; /* 默认占满父容器高度 */
}
</style>
