<template>
  <div class="chart-pie-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传入的数据
const props = defineProps({
  data: {
    type: Object,
    required: true,
    default: () => ({
      legend: [],
      series: []
    })
  },
  // 图表标题，可选
  title: {
    type: String,
    default: ''
  },
  // 是否显示图例
  showLegend: {
    type: Boolean,
    default: true
  }
});

// 图表实例和DOM引用
const chartRef = ref(null);
let chartInstance = null;

// 初始化图表
const initChart = () => {
  if (chartInstance) {
    chartInstance.dispose();
  }

  chartInstance = echarts.init(chartRef.value);

  // 设置图表配置
  const option = {
    title: props.title ? {
      text: props.title,
      left: 'center',
      textStyle: {
        color: '#fff'
      }
    } : null,
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)'
    },
    legend: props.showLegend ? {
      orient: 'vertical',
      left: 'left',
      textStyle: {
        color: '#ccc'
      },
      data: props.data.legend
    } : null,
    series: props.data.series.map(series => ({
      ...series,
      type: 'pie',
      radius: '70%',
      center: ['50%', '55%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 4,
        borderColor: '#1c2b36',
        borderWidth: 2
      },
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: 16,
          fontWeight: 'bold',
          color: '#fff'
        }
      },
      labelLine: {
        show: false
      }
    }))
  };

  chartInstance.setOption(option);
};

// 监听数据变化，重新渲染图表
watch(() => props.data, () => {
  if (chartInstance) {
    initChart();
  }
}, {deep: true});

// 处理窗口大小变化，重绘图表
const handleResize = () => {
  if (chartInstance) {
    chartInstance.resize();
  }
};

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
  min-height: 180px;
}
</style>
