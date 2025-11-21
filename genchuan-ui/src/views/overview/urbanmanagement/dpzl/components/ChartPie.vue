<template>
  <div class="chart-pie-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传入的参数
const props = defineProps({
  // 图表数据：数组格式，每个元素包含名称字段和值字段
  data: {
    type: Array,
    required: true,
    default: () => []
  },
  // 名称字段的键名（对应数据中的名称属性）
  nameField: {
    type: String,
    required: true
  },
  // 值字段的键名（对应数据中的数值属性）
  valueField: {
    type: String,
    required: true
  },
  // 图表标题（可选）
  title: {
    type: String,
    default: ''
  },
  // 是否显示图例
  showLegend: {
    type: Boolean,
    default: true
  },
  // 新增：基础字体缩放比例，统一控制文本大小
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
  if (!chartRef.value) return;

  // 销毁已有实例
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 计算所有文本元素自适应字号
  const titleFontSize = vwToPx(0.8); // 标题字号（与其他图表保持一致）
  const legendFontSize = vwToPx(0.7); // 图例字号
  const tooltipFontSize = vwToPx(0.65); // 提示框字号
  const labelFontSize = vwToPx(0.6); // 扇区标签字号（预留）

  // 创建新实例
  chartInstance = echarts.init(chartRef.value);

  // 处理图表数据
  const chartData = props.data.map(item => ({
    name: item[props.nameField],
    value: item[props.valueField],
    itemStyle: item.itemStyle || {} // 支持自定义单个扇形样式
  }));

  // 配置项
  const option = {
    title: props.title
      ? {
        text: props.title,
        left: 'center',
        top: '5%',
        textStyle: {
          color: 'rgba(255, 255, 255, 0.9)',
          fontSize: titleFontSize // 标题自适应
        }
      }
      : null,
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)',
      textStyle: {
        color: '#000',
        fontSize: tooltipFontSize // 提示框文字自适应
      },
      backgroundColor: 'rgba(255, 255, 255, 0.9)', // 优化背景与文字对比度
      borderColor: 'rgba(0, 0, 0, 0.1)',
      borderWidth: 1
    },
    legend: props.showLegend
      ? {
        orient: 'vertical',
        left: 30,
        top: 30,
        textStyle: {
          color: '#ccc',
          fontSize: legendFontSize // 图例字体自适应
        },
        data: chartData.map(item => item.name),
        itemWidth: vwToPx(0.5), // 图例图标大小自适应
        itemHeight: vwToPx(0.5)
      }
      : null,
    series: [
      {
        name: props.nameField,
        type: 'pie',
        radius: '50%', // 饼图半径
        center: ['55%', '50%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 4,
          borderColor: 'rgba(255,255,255,0.99)',
          borderWidth: 2
        },
        label: {
          show: false, // 如需显示可开启并设置fontSize
          position: 'center',
          fontSize: labelFontSize // 预留标签字号
        },
        labelLine: {
          show: false
        },
        data: chartData
      }
    ]
  };

  // 设置配置项
  chartInstance.setOption(option);
};

// 监听数据及配置变化，重新渲染图表
watch(
  () => [props.data, props.showLegend, props.title, props.baseFontScale],
  () => {
    initChart();
  },
  { deep: true }
);

// 窗口大小变化时更新所有文本字号并刷新图表
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算所有文本元素字号
  const titleFontSize = vwToPx(0.8);
  const legendFontSize = vwToPx(0.7);
  const tooltipFontSize = vwToPx(0.65);
  const labelFontSize = vwToPx(0.6);

  // 更新图表文本配置
  const updateOption = {};

  // 更新标题
  if (props.title) {
    updateOption.title = {
      textStyle: { fontSize: titleFontSize }
    };
  }

  // 更新提示框
  updateOption.tooltip = {
    textStyle: { fontSize: tooltipFontSize }
  };

  // 更新图例
  if (props.showLegend) {
    updateOption.legend = {
      textStyle: { fontSize: legendFontSize },
      itemWidth: vwToPx(0.5),
      itemHeight: vwToPx(0.5)
    };
  }

  // 更新标签（如需显示）
  updateOption.series = [{
    label: {
      fontSize: labelFontSize
    }
  }];

  chartInstance.setOption(updateOption);
  chartInstance.resize();
};

// 生命周期钩子
onMounted(() => {
  initChart();
  window.addEventListener('resize', handleResize);
});

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
  min-height: 16vw;
}
</style>
