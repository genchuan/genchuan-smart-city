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
  // 新增：基础字体缩放比例
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
  '#00ccff', '#42b983', '#ff7d00', '#ff4949',
  '#9c27b0', '#1976d2', '#8d6e63', '#e53935'
];

// 初始化图表
const initChart = () => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }

  // 计算自适应字号
  const titleFontSize = vwToPx(0.8); // 图表标题
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const legendFontSize = vwToPx(0.7); // 图例文字
  const emphasisLabelFontSize = vwToPx(0.8); // 高亮状态标签文字

  chartInstance.value = echarts.init(chartRef.value);

  const formattedSeries = props.data.series.map(seriesItem => {
    const formattedData = seriesItem.data.map((item, index) => {
      if (typeof item === 'object' && item.name) {
        return item;
      }
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
    legend: {
      orient: 'vertical',
      right: '0%',
      bottom: '0%',
      textStyle: {
        color: '#ccc',
        fontSize: legendFontSize // 图例文字自适应
      },
      itemWidth: vwToPx(0.6), // 图例图标大小自适应
      itemHeight: vwToPx(0.6),
      data: props.data.legend // 图例数据
    },
    series: formattedSeries.map(item => ({
      ...item,
      type: 'pie',
      radius: ['40%', '70%'],
      center: ['35%', '55%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 4,
        borderColor: 'rgba(0, 30, 60, 0.8)',
        borderWidth: 2
      },
      label: {show: false, position: 'center'},
      emphasis: {
        label: {
          show: true,
          fontSize: emphasisLabelFontSize, // 高亮标签文字自适应
          fontWeight: 'bold',
          color: '#fff'
        },
        animation: false
      },
      labelLine: {show: false},
      color: colorScheme,
      animation: false,
      animationDuration: 0,
      animationEasingUpdate: 'none'
    }))
  };

  chartInstance.value.setOption(option);
  chartInstance.value.resize();
};

// 监听数据及缩放比例变化，重新渲染图表
watch([() => props.data, () => props.baseFontScale], () => {
  if (chartInstance.value) initChart();
}, {deep: true});

// 窗口大小变化时更新字体并调整图表尺寸
const handleResize = () => {
  if (!chartInstance.value) return;

  // 重新计算自适应字号
  const titleFontSize = vwToPx(0.8);
  const tooltipFontSize = vwToPx(0.65);
  const legendFontSize = vwToPx(0.7);
  const emphasisLabelFontSize = vwToPx(0.8);

  // 更新文本配置
  chartInstance.value.setOption({
    title: {
      textStyle: {fontSize: titleFontSize}
    },
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    legend: {
      textStyle: {fontSize: legendFontSize},
      itemWidth: vwToPx(0.6),
      itemHeight: vwToPx(0.6)
    },
    series: [{
      emphasis: {
        label: {fontSize: emphasisLabelFontSize}
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
  max-height: 200px;
}
</style>
