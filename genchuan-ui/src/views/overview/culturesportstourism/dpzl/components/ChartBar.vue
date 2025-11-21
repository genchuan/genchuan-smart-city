<template>
  <div class="chart-bar-container" ref="chartContainer"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传递的参数
const props = defineProps({
  // 图表数据格式: { xAxis: [], series: [{ name: '', data: [] }] }
  data: {
    type: Object,
    required: true,
    default: () => ({ xAxis: [], series: [] })
  },
  // x轴名称
  xAxisName: {
    type: String,
    default: ''
  },
  // y轴名称
  yAxisName: {
    type: String,
    default: ''
  },
  // 图表高度
  height: {
    type: String,
    default: '100%'
  },
  // 新增：基础字体缩放比例
  baseFontScale: {
    type: Number,
    default: 1
  }
});

const chartContainer = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（结合基础缩放比例）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 初始化图表
const initChart = () => {
  if (!chartContainer.value) return;

  // 销毁已有实例
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const axisLabelFontSize = vwToPx(0.6); // 坐标轴标签
  const axisNameFontSize = vwToPx(0.7); // 坐标轴名称

  // 创建新实例
  chartInstance = echarts.init(chartContainer.value);

  // 设置图表配置
  const option = {
    backgroundColor: 'transparent', // 透明背景，适配父组件深色主题
    tooltip: {
      trigger: 'axis',
      axisPointer: {type: 'shadow'},
      backgroundColor: 'rgba(0, 30, 60, 0.8)', // 深色 tooltip
      borderColor: 'rgba(0, 204, 255, 0.3)',
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: props.data.xAxis,
      name: props.xAxisName,
      nameTextStyle: {
        color: '#ccc',
        fontSize: axisNameFontSize // x轴名称自适应
      },
      axisLine: {lineStyle: {color: 'rgba(0, 204, 255, 0.3)'}}, // 轴线颜色
      axisLabel: {
        color: '#ccc',
        fontSize: axisLabelFontSize // x轴标签自适应
      },
      splitLine: {show: false} // 取消网格线
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: '#ccc',
        fontSize: axisNameFontSize // y轴名称自适应
      },
      axisLine: {lineStyle: {color: 'rgba(0, 204, 255, 0.3)'}},
      axisLabel: {
        color: '#ccc',
        fontSize: axisLabelFontSize // y轴标签自适应
      },
      splitLine: {lineStyle: {color: 'rgba(0, 204, 255, 0.1)'}} // 浅色网格线
    },
    series: props.data.series.map(series => ({
      ...series,
      type: 'bar',
      barWidth: '60%', // 柱宽
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowColor: 'rgba(0, 204, 255, 0.5)' // 高亮时的阴影
        }
      }
    }))
  };

  chartInstance.setOption(option);
};

// 监听数据及缩放比例变化，重新渲染图表
watch(
  () => [props.data, props.baseFontScale],
  () => {
    initChart();
  },
  {deep: true}
);

// 监听容器大小变化，更新字体并自适应图表
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const axisLabelFontSize = vwToPx(0.6);
  const axisNameFontSize = vwToPx(0.7);

  // 更新文本配置
  chartInstance.setOption({
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    xAxis: {
      nameTextStyle: {fontSize: axisNameFontSize},
      axisLabel: {fontSize: axisLabelFontSize}
    },
    yAxis: {
      nameTextStyle: {fontSize: axisNameFontSize},
      axisLabel: {fontSize: axisLabelFontSize}
    }
  });

  chartInstance.resize();
};

onMounted(() => {
  initChart();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
  if (chartInstance) {
    chartInstance.dispose(); // 销毁实例，释放资源
    chartInstance = null;
  }
});
</script>

<style scoped>
.chart-bar-container {
  width: 100%;
  height: v-bind(height); /* 使用v-bind绑定父组件传递的高度 */
}
</style>
