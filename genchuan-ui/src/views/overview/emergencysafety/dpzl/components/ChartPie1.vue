<template>
  <div class="chart-pie-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传入的 props（适配平衡版父组件格式）
const props = defineProps({
  // 图例标签（如：['待派单', '处置中', '待验收', '已完成']）
  labels: {
    type: Array,
    required: true,
    default: () => []
  },
  // 图表数据（如：[15, 42, 13, 92]）
  data: {
    type: Array,
    required: true,
    default: () => []
  },
  // 颜色数组（如：['#909399', '#E6A23C', '#722ED1', '#67C23A']）
  colors: {
    type: Array,
    required: true,
    default: () => []
  },
  // 是否显示图例（默认显示）
  showLegend: {
    type: Boolean,
    default: true
  },
  // 新增：基础字体缩放比例（与其他图表组件统一接口）
  baseFontScale: {
    type: Number,
    default: 1
  }
});

// 图表实例和DOM引用
const chartRef = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（结合基础缩放比例）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 初始化图表
const initChart = () => {
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 确保DOM存在再初始化
  if (!chartRef.value) return;

  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65); // 提示框文字（统一标准）
  const legendFontSize = vwToPx(0.7);   // 图例文字
  const legendIconSize = vwToPx(0.6);   // 图例图标大小
  const emphasisLabelFontSize = vwToPx(0.8); // 高亮标签文字（预留）

  chartInstance = echarts.init(chartRef.value);

  // 构建图表配置（适配props格式，保留原有样式）
  const option = {
    tooltip: {
      trigger: 'item',
      formatter: '{b}: {c} 件 ({d}%)', // 调整tooltip显示格式（贴合事件追踪场景）
      textStyle: {
        color: '#333',
        fontSize: tooltipFontSize // 提示框文字自适应
      }
    },
    legend: props.showLegend ? {
      orient: 'vertical',
      left: 'left',
      textStyle: {
        color: '#ccc', // 适配父组件浅色背景，优化可读性
        fontSize: legendFontSize // 图例文字自适应
      },
      itemWidth: legendIconSize, // 图例图标大小自适应
      itemHeight: legendIconSize,
      data: props.labels // 使用父组件传入的labels作为图例
    } : null,
    series: [{
      name: '事件数量',
      type: 'pie',
      radius: '80%',
      center: ['60%', '50%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 4,
        borderColor: '#fff', // 白色边框，贴合父组件卡片样式
        borderWidth: 2
      },
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: false,
          fontSize: emphasisLabelFontSize, // 高亮标签文字自适应（预留）
          fontWeight: 'bold',
          color: '#333'
        }
      },
      labelLine: {
        show: false
      },
      data: props.labels.map((label, index) => ({
        name: label,
        value: props.data[index] || 0 // 映射labels和data为echarts需要的格式
      })),
      color: props.colors // 使用父组件传入的颜色数组
    }]
  };

  chartInstance.setOption(option);
};

// 监听props变化，重新渲染图表（深度监听数组变化，新增baseFontScale监听）
watch([() => props.labels, () => props.data, () => props.colors, () => props.baseFontScale], () => {
  initChart();
}, {deep: true});

// 窗口大小变化时更新字体并调整图表尺寸
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const legendFontSize = vwToPx(0.7);
  const legendIconSize = vwToPx(0.6);
  const emphasisLabelFontSize = vwToPx(0.8);

  // 更新文本配置
  chartInstance.setOption({
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    legend: props.showLegend ? {
      textStyle: {fontSize: legendFontSize},
      itemWidth: legendIconSize,
      itemHeight: legendIconSize
    } : null,
    series: [{
      emphasis: {
        label: {fontSize: emphasisLabelFontSize}
      }
    }]
  });

  chartInstance.resize();
};

// 组件挂载时初始化图表
onMounted(() => {
  initChart();
  window.addEventListener('resize', handleResize);
});

// 组件卸载时销毁图表，避免内存泄漏
onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
  if (chartInstance) {
    chartInstance.dispose();
    chartInstance = null;
  }
});
</script>

<style scoped>
.chart-pie-container {
  width: 100%;
  height: 100%;
}
</style>
