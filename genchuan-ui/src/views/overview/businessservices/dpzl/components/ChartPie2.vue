<template>
  <div class="chart-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, onMounted, watch, onUnmounted} from 'vue';
import * as echarts from 'echarts';

const chartRef = ref(null);
const chartInstance = ref(null);

// 接收的props
const props = defineProps({
  data: {
    type: Object,
    default: () => ({
      legend: [], // 图例数据（如：['制造业', '服务业']）
      series: []  // 系列数据（支持两种格式：[100, 200] 或 [{name: 'xx', value: 100}, ...]）
    })
  },
  title: {type: String, default: ''},
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
  'rgba(43,82,128,0.8)', 'rgba(30,124,81,0.8)', 'rgba(236,171,109,0.8)',
  'rgba(163,71,180,0.8)', '#1976d2', '#8d6e63', '#e53935'
];

// 初始化图表
const initChart = () => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }

  // 计算自适应字号（补充内部标签字号配置）
  const titleFontSize = vwToPx(0.8); // 图表标题
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const legendFontSize = vwToPx(0.7); // 图例文字（预留）
  const legendIconSize = vwToPx(0.6); // 图例图标大小（预留）
  const innerLabelFontSize = vwToPx(0.7); // 饼图内部标签文字
  const innerLabelLineHeight = vwToPx(0.8); // 内部标签行高

  chartInstance.value = echarts.init(chartRef.value);

  // 核心修复：格式化系列数据，确保每个数据项都有name（与图例匹配）
  const formattedSeries = props.data.series.map(seriesItem => {
    // 处理数据格式：将数值数组转换为 {name, value} 对象数组
    const formattedData = seriesItem.data.map((item, index) => {
      // 如果是已包含name的对象，直接使用
      if (typeof item === 'object' && item.name) {
        return item;
      }
      // 否则自动匹配图例的name
      return {
        name: props.data.legend[index] || `类别${index + 1}`,
        value: item
      };
    });
    return {...seriesItem, data: formattedData};
  });

  const option = {
    animation: false,
    backgroundColor: 'transparent',
    title: {
      text: props.title,
      textStyle: {
        fontSize: titleFontSize, // 标题文字自适应
        color: 'white'
      },
      left: 'center'
    },
    legend: {
      show: false
    },
    tooltip: {
      trigger: 'item',
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      borderWidth: 1,
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      },
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    series: formattedSeries.map(item => ({
      ...item,
      type: 'pie',
      radius: '80%',
      center: ['50%', '60%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 6,
        borderColor: 'rgba(0, 30, 60, 0.8)',
        borderWidth: 2
      },
      label: {
        show: true,
        position: 'inside',
        color: '#fff',
        fontSize: innerLabelFontSize, // 内部标签文字自适应
        lineHeight: innerLabelLineHeight, // 内部标签行高自适应
        formatter: '{b}' // 分行显示名称和数值
      },
      emphasis: {
        scale: true,
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      },
      labelLine: {show: false},
      color: colorScheme
    }))
  };

  chartInstance.value.setOption(option);
  setTimeout(() => chartInstance.value?.resize(), 0);
};

// 监听数据及缩放比例变化，重新渲染图表
watch([() => props.data, () => props.baseFontScale], () => {
  if (chartInstance.value) initChart();
}, {deep: true});

// 窗口大小变化时更新所有文本配置并调整图表尺寸
const handleResize = () => {
  if (!chartInstance.value) return;

  // 重新计算所有自适应字号
  const titleFontSize = vwToPx(0.8);
  const tooltipFontSize = vwToPx(0.65);
  const legendFontSize = vwToPx(0.7);
  const legendIconSize = vwToPx(0.6);
  const innerLabelFontSize = vwToPx(0.7);
  const innerLabelLineHeight = vwToPx(0.8);

  // 全面更新文本配置（包含内部标签）
  chartInstance.value.setOption({
    title: {
      textStyle: {fontSize: titleFontSize}
    },
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    legend: {
      textStyle: {fontSize: legendFontSize},
      itemWidth: legendIconSize,
      itemHeight: legendIconSize
    },
    series: [{
      label: {
        fontSize: innerLabelFontSize,
        lineHeight: innerLabelLineHeight
      }
    }]
  });

  chartInstance.value.resize();
};

onMounted(() => {
  initChart();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
  chartInstance.value?.dispose();
});
</script>

<style scoped>
.chart-container {
  width: 100%;
  height: 100%;
  min-height: 30vh;
}
</style>
