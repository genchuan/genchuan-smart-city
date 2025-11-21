<template>
  <div class="chart-pie-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传入的饼图数据
const props = defineProps({
  data: {
    type: Object,
    required: true,
    default: () => ({
      legend: [], // 图例数据（如：['工业', '农业', '生活', '机动车']）
      series: []  // 系列数据（如：[{name: '数量', data: [12, 8, 15, 7]}]）
    })
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

// 初始化饼图
const initChart = () => {
  // 销毁已有实例
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 计算自适应字号
  const legendFontSize = vwToPx(0.7);
  const tooltipFontSize = vwToPx(0.65);
  const emphasisLabelFontSize = vwToPx(0.8);

  // 创建新实例
  chartInstance = echarts.init(chartRef.value);

  // 设置图表配置
  const option = {
    color: ['#ff4949', '#ff7d00', '#0066ff', '#800080'], // 匹配污染源类型颜色
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)',
      textStyle: {
        fontSize: tooltipFontSize // 提示框文字自适应
      }
    },
    legend: {
      orient: 'horizontal',
      bottom: 8,
      textStyle: {
        color: '#ccc',
        fontSize: legendFontSize // 图例字体大小（基于vw计算）
      },
    },
    series: [
      {
        name: props.data.series[0]?.name || '数量',
        type: 'pie',
        radius: ['40%', '70%'], // 环形饼图
        center: ['52%', '38%'], // 居中调整
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 4,
          borderColor: '#001e3c', // 扇区边框颜色
          borderWidth: 2
        },
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: emphasisLabelFontSize, // 强调标签自适应
            fontWeight: 'bold',
            color: '#fff'
          }
        },
        labelLine: {
          show: false
        },
        data: props.data.series[0]?.data.map((value, index) => ({
          name: props.data.legend[index],
          value: value
        })) || []
      }
    ]
  };

  chartInstance.setOption(option);
};

// 监听数据及缩放比例变化，重新渲染图表
watch(
  () => [props.data, props.baseFontScale],
  () => {
    if (chartInstance) {
      initChart();
    }
  },
  {deep: true}
);

// 窗口大小变化时更新所有文本字号并刷新图表
const handleResize = () => {
  if (chartInstance) {
    // 重新计算自适应字号
    const newLegendFontSize = vwToPx(0.7);
    const newTooltipFontSize = vwToPx(0.65);
    const newEmphasisLabelFontSize = vwToPx(0.8);

    // 更新文本配置
    chartInstance.setOption({
      tooltip: {
        textStyle: {fontSize: newTooltipFontSize}
      },
      legend: {
        textStyle: {fontSize: newLegendFontSize}
      },
      series: [{
        emphasis: {
          label: {
            fontSize: newEmphasisLabelFontSize
          }
        }
      }]
    });
    // 刷新图表尺寸
    chartInstance.resize();
  }
};

onMounted(() => {
  // 初始化图表
  initChart();
  // 监听窗口大小变化
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  // 清理工作
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
  min-height: 100px;
}
</style>
