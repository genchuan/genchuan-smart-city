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
  }
});

const chartRef = ref(null);
let chartInstance = null;

// 初始化图表
const initChart = () => {
  // 销毁已存在的图表实例
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 创建新图表实例
  chartInstance = echarts.init(chartRef.value);

  // 预设颜色方案，适配深色背景
  const colors = [
    '#00ccff', // 亮蓝色
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
        color: '#fff'
      },
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      left: 10,
      top: 'center',
      textStyle: {
        color: '#ccc'
      },
      data: props.data.legend
    },
    series: [{
      name: '数量',
      type: 'pie',
      radius: props.radius,
      center: ['65%', '50%'],
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
        formatter: '{b}: {d}%',
        fontSize: 12
      },
      emphasis: {
        label: {
          show: true,
          fontSize: 14,
          fontWeight: 'bold',
          color: '#00ccff'
        }
      },
      labelLine: {
        show: props.showLabelLine,
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.3)'
        },
        length: 15,
        length2: 20
      },
      data: props.data.series.map((value, index) => ({
        name: props.data.legend[index],
        value: value
      }))
    }]
  };

  chartInstance.setOption(option);
};

// 监听窗口大小变化，重绘图表
const handleResize = () => {
  if (chartInstance) {
    chartInstance.resize();
  }
};

// 当数据变化时重新初始化图表
watch(() => props.data, () => {
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
  min-height: 150px;
}
</style>
