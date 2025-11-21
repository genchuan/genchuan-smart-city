<template>
  <div class="chart-bar-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, watch, onMounted, onUnmounted} from 'vue';
import * as echarts from 'echarts';

// 接收父组件参数（与原ChartPie数据结构兼容，无需修改父组件）
const props = defineProps({
  // 数据格式：{ legend: ['工业', '农业', ...], series: [{name: '数量', data: [22, 8, ...]}] }
  data: {
    type: Object,
    default: () => ({legend: [], series: [{name: '', data: []}]})
  },
  // 图表高度（默认300px）
  height: {
    type: String,
    default: '300px'
  },
  // 新增：基础字体缩放比例
  baseFontScale: {
    type: Number,
    default: 1
  }
});

const chartRef = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（结合基础缩放比例）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 污染源类型对应颜色（与页面地图标记、图例颜色保持一致）
const POLLUTANT_TYPE_COLORS = [
  'rgba(255, 73, 73, 0.8)', // 工业（红色）
  'rgba(255, 193, 7, 0.8)', // 农业（黄色）
  'rgba(0, 150, 255, 0.8)', // 生活（蓝色）
  'rgba(153, 102, 255, 0.8)' // 机动车（紫色）
];

// 计算各类型占比（用于tooltip显示）
const getPercent = (value, total) => {
  return total > 0 ? `${((value / total) * 100).toFixed(1)}%` : '0%';
};

// 初始化图表
const initChart = () => {
  if (chartInstance) chartInstance.dispose(); // 销毁旧实例
  chartInstance = echarts.init(chartRef.value);

  // 计算总数量（用于占比计算）
  const total = props.data.series[0]?.data.reduce((sum, val) => sum + val, 0) || 0;

  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const xAxisLabelFontSize = vwToPx(0.6); // x轴标签
  const yAxisLabelFontSize = vwToPx(0.6); // y轴标签
  const yAxisNameFontSize = vwToPx(0.7); // y轴名称
  const labelFontSize = vwToPx(0.6); // 柱状图顶部标签

  const option = {
    backgroundColor: 'transparent', // 透明背景融入页面
    tooltip: {
      trigger: 'axis',
      axisPointer: {type: 'shadow'}, // 阴影指示器
      backgroundColor: 'rgba(0, 20, 40, 0.85)',
      borderColor: 'rgba(0, 204, 255, 0.4)',
      borderWidth: 1,
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      },
      formatter: (params) => {
        // 显示类型、数量、占比
        const value = params[0].value;
        return `
          <div>${params[0].name}</div>
          <div>数量：${value} 个</div>
          <div>占比：${getPercent(value, total)}</div>
        `;
      }
    },
    grid: {
      left: '5%',
      right: '5%',
      bottom: '20%',
      top: '10%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: props.data.legend, // x轴为污染源类型
      axisLine: {lineStyle: {color: 'rgba(255, 255, 255, 0.2)'}},
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: xAxisLabelFontSize // x轴标签自适应
      },
      splitLine: {show: false} // 隐藏x轴网格
    },
    yAxis: {
      type: 'value',
      name: '数量（个）',
      nameTextStyle: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: yAxisNameFontSize // y轴名称自适应
      },
      axisLine: {lineStyle: {color: 'rgba(255, 255, 255, 0.2)'}},
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: yAxisLabelFontSize, // y轴标签自适应
        formatter: '{value}' // 整数显示
      },
      splitLine: {lineStyle: {color: 'rgba(255, 255, 255, 0.1)'}},
      min: 0 // 数量从0开始
    },
    series: props.data.series.map((item, index) => ({
      ...item,
      type: 'bar',
      barWidth: '40%', // 柱子宽度
      itemStyle: {
        // 柱子颜色与类型对应，添加圆角
        color: (params) => {
          return POLLUTANT_TYPE_COLORS[params.dataIndex % POLLUTANT_TYPE_COLORS.length];
        },
        borderRadius: [4, 4, 0, 0]
      },
      // 柱子顶部显示数值
      label: {
        show: true,
        position: 'top',
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: labelFontSize // 柱状图标签自适应
      }
    }))
  };

  chartInstance.setOption(option);
};

// 窗口大小变化时更新字体并调整尺寸
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const xAxisLabelFontSize = vwToPx(0.6);
  const yAxisLabelFontSize = vwToPx(0.6);
  const yAxisNameFontSize = vwToPx(0.7);
  const labelFontSize = vwToPx(0.6);

  // 更新文本配置
  chartInstance.setOption({
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    xAxis: {
      axisLabel: {fontSize: xAxisLabelFontSize}
    },
    yAxis: {
      nameTextStyle: {fontSize: yAxisNameFontSize},
      axisLabel: {fontSize: yAxisLabelFontSize}
    },
    series: [{
      label: {fontSize: labelFontSize}
    }]
  });

  chartInstance.resize();
};

// 生命周期
onMounted(() => {
  chartRef.value.style.height = props.height;
  initChart();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  chartInstance?.dispose();
  window.removeEventListener('resize', handleResize);
});

// 监听数据及缩放比例变化，重新渲染图表
watch([() => props.data, () => props.baseFontScale], () => {
  initChart();
}, {deep: true});
</script>

<style scoped>
.chart-bar-container {
  width: 100%;
  height: 100%;
}
</style>
