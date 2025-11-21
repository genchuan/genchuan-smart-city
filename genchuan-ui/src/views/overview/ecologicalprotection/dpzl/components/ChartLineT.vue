<template>
  <!-- 折线图容器 -->
  <div class="chart-container" :style="{ height: height }"></div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, watch } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传递的参数
const props = defineProps({
  // x轴数据（如：['10/24', '10/25', ...]）
  xAxis: {
    type: Array,
    default: () => []
  },
  // 系列数据（如：[{ name: '排放量', data: [12, 15, ...] }]）
  series: {
    type: Array,
    default: () => []
  },
  // 图表高度（如：'200px'）
  height: {
    type: String,
    default: '300px'
  }
});

// 图表实例
const chartInstance = ref(null);
const chartDom = ref(null);

// 初始化图表
const initChart = () => {
  // 销毁已有实例（避免重复创建）
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }
  // 创建新实例
  chartInstance.value = echarts.init(chartDom.value);

  // 图表配置项
  const option = {
    // 图表边距
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true // 包含坐标轴标签
    },
    //  tooltip配置（鼠标悬停提示）
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow' // 阴影指示器
      },
      backgroundColor: 'rgba(0, 30, 60, 0.8)', // 深色背景
      borderColor: 'rgba(0, 204, 255, 0.3)',
      borderWidth: 1,
      textStyle: {
        color: '#fff' // 文字白色
      }
    },
    // x轴配置
    xAxis: {
      type: 'category',
      data: props.xAxis,
      axisLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.3)' // 轴线颜色
        }
      },
      axisLabel: {
        color: '#ccc' // 标签颜色
      }
    },
    // y轴配置
    yAxis: {
      type: 'value',
      axisLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#ccc',
        formatter: '{value}' // 可根据需求添加单位（如：'{value} 吨'）
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.1)' // 网格线颜色
        }
      }
    },
    // 系列数据（折线配置）
    series: props.series.map(item => ({
      ...item,
      type: 'line', // 折线类型
      smooth: true, // 平滑曲线
      symbol: 'circle', // 数据点形状
      symbolSize: 6, // 数据点大小
      lineStyle: {
        width: 2 // 线宽
      },
      itemStyle: {
        color: '#00ccff', // 数据点颜色（默认，可在series中覆盖）
        borderWidth: 2,
        borderColor: '#fff'
      },
      emphasis: {
        // 鼠标悬停时的样式
        itemStyle: {
          symbolSize: 8
        }
      }
    }))
  };

  // 设置图表配置
  chartInstance.value.setOption(option);
};

// 监听容器大小变化（自适应）
const handleResize = () => {
  chartInstance.value?.resize();
};

// 组件挂载时初始化
onMounted(() => {
  // 获取DOM元素
  chartDom.value = document.querySelector('.chart-container');
  // 初始化图表
  initChart();
  // 监听窗口大小变化
  window.addEventListener('resize', handleResize);
});

// 组件卸载时清理
onUnmounted(() => {
  // 移除事件监听
  window.removeEventListener('resize', handleResize);
  // 销毁图表实例
  if (chartInstance.value) {
    chartInstance.value.dispose();
    chartInstance.value = null;
  }
});

// 监听数据变化，重新渲染图表
watch([() => props.xAxis, () => props.series], () => {
  if (chartInstance.value) {
    initChart();
  }
}, { deep: true }); // 深度监听数组变化
</script>

<style scoped>
.chart-container {
  width: 100%;
  min-width: 400px; /* 最小宽度，避免过窄 */
}
</style>
