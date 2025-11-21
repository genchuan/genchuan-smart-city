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
  // 图表数据 { xAxis: [], series: [{name: '', data: []}, ...] }
  data: {
    type: Object,
    required: true,
    default: () => ({xAxis: [], series: []})
  },
  // Y轴名称
  yAxisName: {
    type: String,
    default: ''
  },
  // 是否显示网格
  showGrid: {
    type: Boolean,
    default: true
  },
  // 是否平滑曲线
  smooth: {
    type: Boolean,
    default: true
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

// 初始化图表
const initChart = () => {
  // 销毁已存在的图表实例
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const legendFontSize = vwToPx(0.7); // 图例文字
  const axisLabelFontSize = vwToPx(0.6); // 坐标轴标签
  const yAxisNameFontSize = vwToPx(0.7); // y轴名称

  // 创建新图表实例
  chartInstance = echarts.init(chartRef.value);

  // 设置图表配置
  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      borderWidth: 1,
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      }
    },
    legend: {
      data: props.data.series.map(item => item.name),
      textStyle: {
        color: '#ccc',
        fontSize: legendFontSize // 图例文字自适应
      },
      top: 0,
      itemWidth: vwToPx(0.6), // 图例图标大小自适应
      itemHeight: vwToPx(0.6)
    },
    grid: {
      left: '3%',
      right: '3%',
      bottom: '2%',
      containLabel: true,
      show: props.showGrid,
      lineStyle: {
        color: 'rgba(255, 255, 255, 0.1)'
      }
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: props.data.xAxis,
      axisLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#ccc',
        fontSize: axisLabelFontSize // x轴标签自适应
      }
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: '#00ccff',
        fontSize: yAxisNameFontSize // y轴名称自适应
      },
      axisLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.3)'
        }
      },
      axisLabel: {
        color: '#ccc',
        fontSize: axisLabelFontSize, // y轴标签自适应
        formatter: props.yAxisName.includes('%') ? '{value}%' : '{value}'
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.1)'
        }
      }
    },
    series: props.data.series.map((item, index) => {
      // 预设颜色方案，适配深色背景
      const colors = [
        '#ff4949', // 红色
        '#722ed1', // 紫色
        '#fac858'  // 黄色
      ];

      return {
        name: item.name,
        type: 'line',
        data: item.data,
        smooth: props.smooth,
        symbol: 'circle',
        symbolSize: 6,
        showSymbol: false,
        emphasis: {
          showSymbol: true
        },
        lineStyle: {
          width: 2
        },
        itemStyle: {
          color: colors[index % colors.length]
        },
        areaStyle: {
          color: {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [{
              offset: 0, color: colors[index % colors.length] + '80' // 透明度80%
            }, {
              offset: 1, color: colors[index % colors.length] + '00' // 透明度0%
            }]
          }
        }
      };
    })
  };

  chartInstance.setOption(option);
};

// 监听窗口大小变化，更新字体并调整图表尺寸
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const legendFontSize = vwToPx(0.7);
  const axisLabelFontSize = vwToPx(0.6);
  const yAxisNameFontSize = vwToPx(0.7);

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
    xAxis: {
      axisLabel: {fontSize: axisLabelFontSize}
    },
    yAxis: {
      nameTextStyle: {fontSize: yAxisNameFontSize},
      axisLabel: {fontSize: axisLabelFontSize}
    }
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
  min-height: 15vh;
}
</style>
