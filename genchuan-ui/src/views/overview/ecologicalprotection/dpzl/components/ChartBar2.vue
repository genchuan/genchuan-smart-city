<template>
  <div class="chart-bar-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, watch, onMounted, onUnmounted} from 'vue';
import * as echarts from 'echarts';

// 接收父组件参数（水源地类型分布数据）
const props = defineProps({
  // 数据格式：{ legend: ['地表水', '地下水'], series: [{name: '数量（个）', data: [8, 4]}] }
  data: {
    type: Object,
    default: () => ({legend: [], series: [{name: '', data: []}]}) // 默认值确保结构安全
  },
  // 图表高度
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

// 水源地类型对应颜色（与地图标记、标签颜色一致）
const WATER_SOURCE_COLORS = [
  'rgba(0, 150, 255, 0.8)', // 地表水（蓝色）
  'rgba(19, 206, 102, 0.8)' // 地下水（绿色）
];

// 计算占比（用于tooltip）
const getPercent = (value, total) => {
  return total > 0 ? `${((value / total) * 100).toFixed(1)}%` : '0%';
};

// 初始化图表（增加数据容错处理）
const initChart = () => {
  // 容错：确保数据结构安全
  const safeData = props.data || {legend: [], series: [{name: '', data: []}]};
  const safeSeries = safeData.series || [{name: '', data: []}];
  const firstSeries = safeSeries[0] || {name: '', data: []};
  const seriesData = firstSeries.data || [];

  // 计算总数
  const total = seriesData.reduce((sum, val) => sum + (val || 0), 0);

  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const axisLabelFontSize = vwToPx(0.6); // 坐标轴标签
  const nameTextFontSize = vwToPx(0.7); // 坐标轴名称
  const labelFontSize = vwToPx(0.6); // 柱状图标签

  if (chartInstance) chartInstance.dispose(); // 销毁已有实例
  chartInstance = echarts.init(chartRef.value);

  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      axisPointer: {type: 'shadow'},
      backgroundColor: 'rgba(0, 20, 40, 0.85)',
      borderColor: 'rgba(0, 204, 255, 0.4)',
      borderWidth: 1,
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      },
      formatter: (params) => {
        const value = params[0]?.value || 0;
        return `
          <div>${params[0]?.name || '未知'}</div>
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
      data: safeData.legend || [],
      axisLine: {lineStyle: {color: 'rgba(255, 255, 255, 0.2)'}},
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: axisLabelFontSize // x轴标签自适应
      },
      splitLine: {show: false}
    },
    yAxis: {
      type: 'value',
      name: '数量（个）',
      nameTextStyle: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: nameTextFontSize // y轴名称自适应
      },
      axisLine: {lineStyle: {color: 'rgba(255, 255, 255, 0.2)'}},
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: axisLabelFontSize // y轴标签自适应
      },
      splitLine: {lineStyle: {color: 'rgba(255, 255, 255, 0.1)'}},
      min: 0
    },
    series: safeSeries.map((item, index) => ({
      ...item,
      type: 'bar',
      barWidth: '50%',
      data: item.data || [],
      itemStyle: {
        color: (params) => {
          return WATER_SOURCE_COLORS[params.dataIndex % WATER_SOURCE_COLORS.length];
        },
        borderRadius: [4, 4, 0, 0]
      },
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

// 窗口大小变化时更新字体并重绘
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const axisLabelFontSize = vwToPx(0.6);
  const nameTextFontSize = vwToPx(0.7);
  const labelFontSize = vwToPx(0.6);

  // 更新文本配置
  chartInstance.setOption({
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    xAxis: {
      axisLabel: {fontSize: axisLabelFontSize}
    },
    yAxis: {
      nameTextStyle: {fontSize: nameTextFontSize},
      axisLabel: {fontSize: axisLabelFontSize}
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
watch(() => [props.data, props.baseFontScale], () => {
  initChart();
}, {deep: true});
</script>

<style scoped>
.chart-bar-container {
  width: 100%;
  height: 100%;
}
</style>
