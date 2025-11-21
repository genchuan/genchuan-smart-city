<template>
  <div class="chart-container">
    <div ref="chartRef" class="chart-wrapper"></div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, watch, nextTick } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传递的参数
const props = defineProps({
  // 图表数据 { legend: [], series: [] }
  data: {
    type: Object,
    required: true,
    default: () => ({ legend: [], series: [] })
  },
  // 饼图半径
  radius: {
    type: Array,
    default: () => ['40%', '70%']
  },
  // 是否显示标签
  showLabel: {
    type: Boolean,
    default: true
  },
  // 是否显示标签连接线
  showLabelLine: {
    type: Boolean,
    default: true
  },
  // 基础字体缩放比例
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

// 初始化图表
const initChart = () => {
  // 销毁已存在的图表实例
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const legendFontSize = vwToPx(0.7); // 图例文字
  const labelFontSize = vwToPx(0.6); // 饼图标签文字
  const emphasisLabelFontSize = vwToPx(0.8); // 高亮状态标签文字

  // 创建新图表实例
  chartInstance = echarts.init(chartRef.value);

  // 预设颜色方案，适配深色背景
  const colors = [
    '#13ce66', // 绿色
    '#ff7d00', // 橙色
    '#ff4949', // 红色
    '#722ed1', // 紫色
    '#fac858', // 黄色
    '#0fc6c2', // 青色
    '#7cb305'  // 深绿色
  ];

  // 设置图表配置
  const option = {
    backgroundColor: 'transparent',
    color: colors,
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
      left: 10,
      bottom: 10,
      textStyle: {
        color: '#ccc',
        fontSize: legendFontSize // 图例文字自适应
      },
      itemWidth: vwToPx(0.6), // 图例图标大小自适应
      itemHeight: vwToPx(0.6),
      data: props.data.legend
    },
    series: [{
      name: '数量',
      type: 'pie',
      radius: props.radius,
      center: ['60%', '55%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 4,
        borderColor: 'rgba(0, 30, 60, 0.8)',
        borderWidth: 2
      },
      label: {
        show: props.showLabel,
        position: 'outside',
        color: '#ccc',
        formatter: '{d}%',
        fontSize: labelFontSize // 饼图标签文字自适应
      },
      emphasis: {
        label: {
          show: true,
          fontSize: emphasisLabelFontSize, // 高亮标签文字自适应
          fontWeight: 'bold',
          color: '#00ccff'
        }
      },
      labelLine: {
        show: props.showLabelLine,
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.3)'
        },
        length: 5
      },
      data: props.data.series.map((value, index) => ({
        name: props.data.legend[index],
        value: value
      }))
    }]
  };

  chartInstance.setOption(option);
};

// 监听窗口大小变化，更新字体并调整图表尺寸
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const legendFontSize = vwToPx(0.7);
  const labelFontSize = vwToPx(0.6);
  const emphasisLabelFontSize = vwToPx(0.8);

  // 更新文本配置
  chartInstance.setOption({
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    legend: {
      textStyle: {fontSize: legendFontSize},
      itemWidth: vwToPx(0.6),
      itemHeight: vwToPx(0.6)
    },
    series: [{
      label: {fontSize: labelFontSize},
      emphasis: {
        label: {fontSize: emphasisLabelFontSize}
      }
    }]
  });

  chartInstance.resize();
};

// 当数据或缩放比例变化时重新初始化图表
watch([() => props.data, () => props.baseFontScale], () => {
  nextTick(() => {
    initChart();
  });
}, {deep: true});

// 组件挂载时初始化图表
onMounted(() => {
  nextTick(() => {
    initChart();
    window.addEventListener('resize', handleResize);
  });
});

// 组件卸载时销毁图表
onUnmounted(() => {
  if (chartInstance) {
    chartInstance.dispose();
    chartInstance = null;
  }
  window.removeEventListener('resize', handleResize);
});
</script>

<style scoped>
.chart-container {
  width: 100%;
  height: 100%;
  position: relative;
}

.chart-wrapper {
  width: 100%;
  height: 100%;
  min-height: 30vh;
}
</style>
