<template>
  <div class="chart-bar-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件参数（适配事件追踪视图的区域效率数据格式）
const props = defineProps({
  // 数据格式：{ xAxis: ['南部城区', ...], series: [{name: '及时跟进率', data: [88.5, ...]}, {name: '平均处置周期', data: [165.2, ...]}] }
  xAxis: {
    type: Array,
    default: () => []
  },
  series: {
    type: Array,
    default: () => [
      { name: '及时跟进率（%）', data: [] },
      { name: '平均处置周期（分钟）', data: [] }
    ]
  },
  // 图表高度（默认100%，适配父组件面板高度）
  height: {
    type: String,
    default: '100%'
  },
  // 基础字体缩放比例（适配页面响应式）
  baseFontScale: {
    type: Number,
    default: 1
  }
});

const chartRef = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（结合基础缩放比例，适配不同屏幕）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 追踪视图主题色（与父组件红色主题保持一致）
const TRACKING_COLORS = [
  'rgba(103, 194, 58, 0.8)',  // 及时跟进率-绿色
  'rgba(255, 107, 107, 0.8)'  // 平均处置周期-红色（主题色）
];

// 初始化图表
const initChart = () => {
  if (chartInstance) chartInstance.dispose(); // 销毁旧实例，避免内存泄漏
  chartInstance = echarts.init(chartRef.value);

  // 计算自适应字号（根据屏幕宽度动态调整）
  const tooltipFontSize = vwToPx(0.65);  // 提示框文字
  const xAxisLabelFontSize = vwToPx(0.6); // x轴标签
  const yAxisLabelFontSize = vwToPx(0.6); // y轴标签
  const legendFontSize = vwToPx(0.65);   // 图例文字
  const labelFontSize = vwToPx(0.55);    // 柱状图顶部标签

  const option = {
    backgroundColor: 'transparent', // 透明背景，融入父组件面板
    tooltip: {
      trigger: 'axis',
      axisPointer: { type: 'shadow' }, // 阴影指示器，提升交互体验
      backgroundColor: 'rgba(16, 32, 64, 0.85)', // 适配父组件深色背景
      borderColor: 'rgba(255, 107, 107, 0.4)', // 红色边框（主题色）
      borderWidth: 1,
      textStyle: {
        color: '#ffc1e9', // 提示框文字色-粉色（与父组件一致）
        fontSize: tooltipFontSize
      },
      formatter: (params) => {
        // 自定义提示框内容，显示区域+指标值
        let result = `<div>${params[0].axisValue}</div>`;
        params.forEach((item) => {
          const value = item.seriesName.includes('率')
            ? `${item.value.toFixed(1)}%`
            : `${item.value.toFixed(1)}分钟`;
          result += `<div>${item.seriesName}：${value}</div>`;
        });
        return result;
      }
    },
    legend: {
      data: props.series.map(item => item.name),
      top: '0%',
      textStyle: {
        color: '#ffc1e9', // 图例文字色-粉色
        fontSize: legendFontSize
      },
      itemWidth: 12,
      itemHeight: 12,
      itemGap: 15
    },
    grid: {
      left: '5%',
      right: '5%',
      bottom: '2%',
      top: '20%',
      containLabel: true // 确保标签不超出图表范围
    },
    xAxis: {
      type: 'category',
      data: props.xAxis, // x轴为区域名称
      axisLine: { lineStyle: { color: 'rgba(255, 107, 107, 0.3)' } }, // 红色轴线（主题色）
      axisLabel: {
        color: '#ffc1e9', // x轴文字色-粉色
        fontSize: xAxisLabelFontSize,
      },
      splitLine: { show: false } // 隐藏x轴网格线，保持界面简洁
    },
    yAxis: [
      // 左侧y轴：及时跟进率（0-100%）
      {
        type: 'value',
        name: '及时跟进率（%）',
        nameTextStyle: {
          color: '#a7f3d0', // 轴名称色-浅绿色
          fontSize: yAxisLabelFontSize
        },
        axisLine: { lineStyle: { color: 'rgba(103, 194, 58, 0.3)' } },
        axisLabel: {
          color: '#a7f3d0',
          fontSize: yAxisLabelFontSize,
          formatter: '{value}%' // 显示百分比符号
        },
        splitLine: { lineStyle: { color: 'rgba(255, 255, 255, 0.1)' } },
        min: 0,
        max: 100
      },
      // 右侧y轴：平均处置周期（分钟）
      {
        type: 'value',
        name: '处置周期（分钟）',
        nameTextStyle: {
          color: '#ffc1e9', // 轴名称色-粉色
          fontSize: yAxisLabelFontSize
        },
        axisLine: { lineStyle: { color: 'rgba(255, 107, 107, 0.3)' } }, // 红色轴线（主题色）
        axisLabel: {
          color: '#ffc1e9',
          fontSize: yAxisLabelFontSize,
          formatter: '{value}分钟'
        },
        splitLine: { show: false }, // 隐藏右侧网格线，避免界面杂乱
        min: 0
      }
    ],
    series: props.series.map((item, index) => ({
      ...item,
      type: 'bar',
      barWidth: '30%', // 柱子宽度，避免多系列重叠
      itemStyle: {
        color: TRACKING_COLORS[index % TRACKING_COLORS.length], // 按系列分配主题色
        borderRadius: [4, 4, 0, 0] // 顶部圆角，提升视觉效果
      },
      // 柱子顶部显示数值
      label: {
        show: true,
        position: 'top',
        color: '#fff',
        fontSize: labelFontSize,
        formatter: (params) => {
          return params.seriesName.includes('率')
            ? `${params.value.toFixed(1)}%`
            : `${params.value.toFixed(1)}`;
        }
      },
      yAxisIndex: index // 0对应左侧y轴，1对应右侧y轴
    }))
  };

  chartInstance.setOption(option);
};

// 窗口大小变化时调整图表
const handleResize = () => {
  if (!chartInstance) return;
  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const xAxisLabelFontSize = vwToPx(0.6);
  const yAxisLabelFontSize = vwToPx(0.6);
  const legendFontSize = vwToPx(0.65);
  const labelFontSize = vwToPx(0.55);

  // 更新文本配置
  chartInstance.setOption({
    tooltip: { textStyle: { fontSize: tooltipFontSize } },
    xAxis: { axisLabel: { fontSize: xAxisLabelFontSize } },
    yAxis: [
      {
        nameTextStyle: { fontSize: yAxisLabelFontSize },
        axisLabel: { fontSize: yAxisLabelFontSize }
      },
      {
        nameTextStyle: { fontSize: yAxisLabelFontSize },
        axisLabel: { fontSize: yAxisLabelFontSize }
      }
    ],
    legend: { textStyle: { fontSize: legendFontSize } },
    series: props.series.map(() => ({
      label: { fontSize: labelFontSize }
    }))
  });

  chartInstance.resize();
};

// 生命周期钩子
onMounted(() => {
  chartRef.value.style.height = props.height;
  initChart();
  window.addEventListener('resize', handleResize); // 监听窗口缩放
});

onUnmounted(() => {
  chartInstance?.dispose(); // 组件卸载时销毁图表
  window.removeEventListener('resize', handleResize); // 移除事件监听
});

// 监听数据变化，重新渲染图表（支持动态更新）
watch([() => props.xAxis, () => props.series, () => props.baseFontScale], () => {
  initChart();
}, { deep: true });
</script>

<style scoped>
.chart-bar-container {
  width: 100%;
  height: 100%;
  transition: all 0.3s ease;
}

/* 图表容器hover效果，与父组件面板风格一致 */
.chart-bar-container:hover {
  box-shadow: 0 0 10px rgba(255, 107, 107, 0.2);
}
</style>
