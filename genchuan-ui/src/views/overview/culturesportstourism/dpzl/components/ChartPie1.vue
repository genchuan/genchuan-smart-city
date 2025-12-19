<template>
  <div class="chart-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, onMounted, watch, onUnmounted } from 'vue';
import * as echarts from 'echarts';

const chartRef = ref(null);
const chartInstance = ref(null);

// 接收的props（兼容原有传参逻辑）
const props = defineProps({
  data: {
    type: Object,
    default: () => ({
      legend: [],
      series: [] // 确保默认值是空数组，而非undefined
    })
  },
  title: { type: String, default: '' },
  // 基础字体缩放比例
  baseFontScale: {
    type: Number,
    default: 1
  }
});

// 计算 vw 对应的 px 值（结合基础缩放比例）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 图表颜色方案
const colorScheme = [
  new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    { offset: 0, color: '#bfdbfe' },
    { offset: 1, color: '#3b82f6' }
  ]),
  new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    { offset: 0, color: '#fdba74' },
    { offset: 1, color: '#f97316' }
  ]),
  new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    { offset: 0, color: '#c7d2fe' },
    { offset: 1, color: '#6366f1' }
  ]),
  new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    { offset: 0, color: '#a7f3d0' },
    { offset: 1, color: '#059669' }
  ]),
  new echarts.graphic.LinearGradient(0, 0, 0, 1, [
    { offset: 0, color: '#fecdd3' },
    { offset: 1, color: '#e11d48' }
  ])
];

// 初始化图表（核心：全链路空值保护）
const initChart = () => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }

  // 自适应字号计算
  const titleFontSize = vwToPx(0.8);   // 标题字号
  const tooltipFontSize = vwToPx(0.65);// 提示框字号
  const legendFontSize = vwToPx(0.7);  // 图例字号
  const labelFontSize = vwToPx(0.6);   // 标签字号（预留）

  chartInstance.value = echarts.init(chartRef.value);

  // ========== 关键修正：全链路空值判断，避免访问undefined ==========
  // 1. 安全获取series第一项（先判断series是否存在，再取[0]）
  const seriesItem = props.data.series?.[0] || {};
  // 2. 安全获取data数组（避免data为undefined）
  const seriesData = seriesItem.data || [];
  // 3. 安全格式化饼图数据
  const formattedData = seriesData.map((value, index) => ({
    value,
    name: props.data.legend?.[index] || `类别${index + 1}`
  })) || [];

  const option = {
    animation: true, // 恢复动画提升交互体验
    animationDuration: 1000,
    backgroundColor: 'transparent', // 透明背景匹配页面风格
    title: {
      text: props.title,
      textStyle: {
        fontSize: titleFontSize,
        color: '#9333ea', // 紫色标题文字
        fontWeight: 600
      },
      left: 'center',
      top: '1vw'
    },
    tooltip: {
      trigger: 'item',
      backgroundColor: 'rgba(10, 16, 100, 0.8)', // 适配页面背景的深色半透明
      borderColor: 'rgba(147, 51, 234, 0.5)',    // 紫色边框
      borderWidth: 1,
      padding: [8, 12],
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize
      },
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'horizontal',
      bottom: '1vw',
      left: 'center',
      textStyle: {
        color: '#ccc',       // 浅灰色图例文字
        fontSize: legendFontSize,
        fontWeight: 500
      },
      itemWidth: vwToPx(0.8),
      itemHeight: vwToPx(0.8),
      itemGap: vwToPx(1.2),
      // 安全获取legend（避免undefined）
      data: props.data.legend || []
    },
    series: [{
      // 安全获取series名称（避免undefined）
      name: seriesItem.name || '资源数量',
      type: 'pie',
      radius: ['35%', '70%'], // 调整半径比例更美观
      center: ['50%', '45%'], // 居中调整
      roseType: 'radius',     // 玫瑰图样式（适配类型分布展示）
      itemStyle: {
        borderRadius: 8,      // 圆角样式
        borderColor: 'rgba(10, 16, 100, 0.8)', // 深色边框区分区块
        borderWidth: 2
      },
      label: {
        show: false, // 隐藏标签（避免文字重叠）
        fontSize: labelFontSize,
        color: '#fff',
        formatter: '{b}: {c}'
      },
      emphasis: {
        scale: true,
        scaleSize: 10,
        itemStyle: {
          shadowBlur: 15,
          shadowOffsetX: 0,
          shadowColor: 'rgba(147, 51, 234, 0.5)' // 紫色阴影强调
        }
      },
      labelLine: {
        show: false
      },
      data: formattedData,
      color: colorScheme // 应用紫色系配色方案
    }]
  };

  chartInstance.value.setOption(option);
  setTimeout(() => chartInstance.value?.resize(), 0);
};

// 监听数据/缩放比例变化，重新渲染
watch([() => props.data, () => props.baseFontScale], () => {
  if (chartInstance.value) {
    initChart();
  }
}, { deep: true });

// 窗口resize适配
const handleResize = () => {
  if (!chartInstance.value) return;

  // 重新计算自适应字号
  const titleFontSize = vwToPx(0.8);
  const tooltipFontSize = vwToPx(0.65);
  const legendFontSize = vwToPx(0.7);
  const labelFontSize = vwToPx(0.6);

  // 更新文本样式
  chartInstance.value.setOption({
    title: { textStyle: { fontSize: titleFontSize } },
    tooltip: { textStyle: { fontSize: tooltipFontSize } },
    legend: {
      textStyle: { fontSize: legendFontSize },
      itemWidth: vwToPx(0.8),
      itemHeight: vwToPx(0.8)
    },
    series: [{ label: { fontSize: labelFontSize } }]
  });

  chartInstance.value.resize();
};

// 生命周期
onMounted(() => {
  // 延迟初始化，避免DOM未加载完成+数据未就绪
  setTimeout(() => initChart(), 0);
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
  if (chartInstance.value) {
    chartInstance.value.dispose();
    chartInstance.value = null;
  }
});
</script>

<!-- 关键：添加 lang="scss" 声明，兼容 ::v-deep 语法 -->
<style scoped lang="scss">
.chart-container {
  width: 100%;
  height: 100%;
  min-height: 28vh; // 匹配文旅资源页面的图表面板高度
  box-sizing: border-box !important; // 增加 !important 避免优先级问题
}

// 兼容全屏状态下的样式（SCSS 语法）
::v-deep .ec-legend {
  padding-bottom: 8px !important;
}

::v-deep .ec-tooltip {
  border-radius: 4px !important;
}
</style>
