<template>
  <div class="chart-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, onMounted, watch, defineEmits, onUnmounted } from 'vue';
import * as echarts from 'echarts';

const chartRef = ref(null);
const chartInstance = ref(null);
const emits = defineEmits(['barClick']);

// 接收的props
const props = defineProps({
  xAxis: {
    type: Array,
    default: () => []
  },
  series: {
    type: Array,
    default: () => []
  },
  unit: {
    type: String,
    default: ''
  },
  // 基准值，用于对比
  benchmark: {
    type: Number,
    default: 90
  },
  title: { type: String, default: '' },
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

const getGridTop = () => {
  const titleFontSize = vwToPx(0.8);
  const topPercent = (titleFontSize * 15) / (window.innerHeight * 0.01);
  return `${Math.max(2, Math.min(15, topPercent))}%`;
};

// 初始化图表
const initChart = () => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }

  // 计算自适应字号和边距
  const titleFontSize = vwToPx(0.8); // 图表标题
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const axisLabelFontSize = vwToPx(0.6); // 坐标轴标签
  const gridTop = getGridTop(); // 自适应边距

  chartInstance.value = echarts.init(chartRef.value);

  // 点击事件
  chartInstance.value.on('click', (params) => {
    if (params.componentType === 'series' && params.seriesIndex === 0) {
      emits('barClick', props.xAxis[params.dataIndex]);
    }
  });

  // 处理数据，添加基准线
  let processedSeries = [...props.series];
  processedSeries.push({
    name: '基准值',
    data: props.xAxis.map(() => props.benchmark),
    type: 'line',
    lineStyle: {
      width: 2,
      color: '#ff4949',
      type: 'dashed'
    },
    symbol: 'none',
    z: 10
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
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      },
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      borderWidth: 1,
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      top: gridTop,
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: props.xAxis,
      axisLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#ccc',
        fontSize: axisLabelFontSize, // x轴标签自适应
        rotate: props.xAxis.length > 5 ? 30 : 0
      }
    },
    yAxis: {
      type: 'value',
      min: 0,
      max: 100,
      axisLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#ccc',
        fontSize: axisLabelFontSize, // y轴标签自适应
        formatter: `{value}${props.unit}`
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.1)'
        }
      }
    },
    series: processedSeries.map((item, index) => {
      if (index === 0) { // 第一个系列是柱状图
        return {
          ...item,
          type: 'bar',
          barWidth: '50%',
          itemStyle: {
            color: function (params) {
              // 根据值设置不同颜色
              return params.value >= props.benchmark ?
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {offset: 0, color: '#13ce66'},
                  {offset: 1, color: '#043f25'}
                ]) :
                new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {offset: 0, color: '#db8fef'},
                  {offset: 1, color: '#6d00a3'}
                ]);
            }
          },
          emphasis: {
            itemStyle: {
              color: item.value >= props.benchmark ? '#42b983' : '#ff7d00'
            }
          }
        };
      } else { // 其他系列保持原样（通常是基准线）
        return item;
      }
    })
  };

  chartInstance.value.setOption(option);
};

// 监听数据、基准值及缩放比例变化
watch([() => props.xAxis, () => props.series, () => props.benchmark, () => props.baseFontScale], () => {
  if (chartInstance.value) {
    initChart();
  }
});

// 窗口大小变化
const handleResize = () => {
  if (!chartInstance.value) return;

  // 重新计算自适应字号和边距
  const titleFontSize = vwToPx(0.8);
  const tooltipFontSize = vwToPx(0.65);
  const axisLabelFontSize = vwToPx(0.6);
  const gridTop = getGridTop();

  // 更新文本配置和边距
  chartInstance.value.setOption({
    title: {
      textStyle: { fontSize: titleFontSize }
    },
    tooltip: {
      textStyle: { fontSize: tooltipFontSize }
    },
    grid: {
      top: gridTop
    },
    xAxis: {
      axisLabel: { fontSize: axisLabelFontSize }
    },
    yAxis: {
      axisLabel: { fontSize: axisLabelFontSize }
    }
  });

  chartInstance.value.resize();
};

onMounted(() => {
  initChart();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
  if (chartInstance.value) {
    chartInstance.value.dispose();
  }
});
</script>

<style scoped>
.chart-container {
  width: 100%;
  height: 100%;
}
</style>
