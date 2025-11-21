<template>
  <div class="chart-bar-container" ref="chartContainer"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

const props = defineProps({
  data: {
    type: Object,
    required: true,
    default: () => ({ xAxis: [], series: [] })
  },
  xAxisName: { type: String, default: '' },
  yAxisName: { type: String, default: '' },
  height: { type: Number, default: 300 },
  title: { type: String, default: '' },
  // 新增：基础字体缩放比例，便于统一调整
  baseFontScale: {
    type: Number,
    default: 1
  }
});

const emit = defineEmits(['click']);
const chartContainer = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（结合基础缩放比例）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

const initChart = () => {
  if (chartInstance) chartInstance.dispose();

  // 数据校验
  const validData = {
    xAxis: props.data.xAxis || [],
    series: props.data.series?.length ? props.data.series : [{ name: '数据', data: [] }]
  };

  chartInstance = echarts.init(chartContainer.value);
  const seriesCount = validData.series.length; // 系列数量

  // 动态布局参数
  const barWidth = seriesCount === 1 ? '40%' :
    seriesCount === 2 ? '30%' :
      seriesCount === 3 ? '20%' : '15%';

  const barGap = seriesCount <= 2 ? '10%' : '5%';
  const barCategoryGap = seriesCount <= 2 ? '30%' : '40%';

  // 颜色配置
  const colors = [
    [{ offset: 0, color: 'rgba(0, 255, 150, 0.8)' }, { offset: 1, color: 'rgba(0, 150, 100, 0.6)' }],
    [{ offset: 0, color: 'rgba(255, 200, 0, 0.8)' }, { offset: 1, color: 'rgba(200, 150, 0, 0.6)' }],
    [{ offset: 0, color: 'rgba(255, 80, 80, 0.8)' }, { offset: 1, color: 'rgba(180, 50, 50, 0.6)' }]
  ];

  // 计算所有文本元素自适应字号
  const titleFontSize = vwToPx(0.8);
  const xAxisNameFontSize = vwToPx(0.7);
  const yAxisNameFontSize = vwToPx(0.7);
  const xAxisLabelFontSize = vwToPx(0.6);
  const yAxisLabelFontSize = vwToPx(0.6);
  const tooltipFontSize = vwToPx(0.65); // 新增：提示框文字字号

  const option = {
    title: {
      text: props.title,
      left: 'center',
      textStyle: {
        color: 'rgba(255, 255, 255, 0.9)',
        fontSize: titleFontSize
      }
    },
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      axisPointer: { type: 'shadow' },
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      borderWidth: 1,
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 补充：提示框文字自适应
      }
    },
    grid: {
      left: '5%',
      right: '15%',
      bottom: '5%',
      top: '25%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: validData.xAxis,
      axisLine: { lineStyle: { color: 'rgba(255, 255, 255, 0.3)' } },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        rotate: validData.xAxis.length > 5 ? 45 : 0,
        interval: 0,
        fontSize: xAxisLabelFontSize
      },
      name: props.xAxisName,
      nameTextStyle: {
        color: 'rgba(0, 204, 255, 0.9)',
        fontSize: xAxisNameFontSize
      }
    },
    yAxis: {
      type: 'value',
      axisLine: { lineStyle: { color: 'rgba(255, 255, 255, 0.3)' } },
      splitLine: { lineStyle: { color: 'rgba(255, 255, 255, 0.1)' } },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: yAxisLabelFontSize
      },
      name: props.yAxisName,
      nameTextStyle: {
        color: 'rgba(0, 204, 255, 0.9)',
        fontSize: yAxisNameFontSize
      },
      minInterval: 1
    },
    series: validData.series.map((item, index) => ({
      ...item,
      type: 'bar',
      barWidth,
      barGap,
      barCategoryGap,
      itemStyle: {
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, colors[index % 3]),
        borderRadius: [4, 4, 0, 0]
      },
      emphasis: {
        itemStyle: {
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1,
            colors[index % 3].map(c => ({
              ...c,
              color: c.color.replace('0.8', '1').replace('0.6', '0.8')
            }))
          )
        }
      }
    }))
  };

  chartInstance.setOption(option);

  // 点击事件
  chartInstance.on('click', (params) => {
    emit('click', {
      area: params.name,
      status: params.seriesName,
      value: params.value
    });
  });
};

// 窗口大小变化时更新所有文本字号并刷新图表
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算所有文本元素字号
  const newTitleFontSize = vwToPx(0.8);
  const newXAxisNameFontSize = vwToPx(0.7);
  const newYAxisNameFontSize = vwToPx(0.7);
  const newXAxisLabelFontSize = vwToPx(0.6);
  const newYAxisLabelFontSize = vwToPx(0.6);
  const newTooltipFontSize = vwToPx(0.65); // 新增：提示框字号更新

  chartInstance.setOption({
    title: {
      textStyle: { fontSize: newTitleFontSize }
    },
    tooltip: {
      textStyle: { fontSize: newTooltipFontSize } // 补充：更新提示框文字大小
    },
    xAxis: {
      nameTextStyle: { fontSize: newXAxisNameFontSize },
      axisLabel: { fontSize: newXAxisLabelFontSize },
    },
    yAxis: {
      nameTextStyle: { fontSize: newYAxisNameFontSize },
      axisLabel: { fontSize: newYAxisLabelFontSize },
    }
  });
  chartInstance.resize();
};

// 监听数据变化重新初始化图表
watch(() => props.data, initChart, { deep: true });

// 监听高度和基础缩放比例变化
watch([() => props.height, () => props.baseFontScale], () => {
  if (chartContainer.value) chartContainer.value.style.height = `${props.height}px`;
  handleResize();
});

onMounted(() => {
  if (chartContainer.value) chartContainer.value.style.height = `${props.height}px`;
  initChart();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
  if (chartInstance) chartInstance.dispose();
});
</script>

<style scoped>
.chart-bar-container {
  width: 100%;
  max-height: 16vh;
  margin-top: 2vh;
}
</style>
