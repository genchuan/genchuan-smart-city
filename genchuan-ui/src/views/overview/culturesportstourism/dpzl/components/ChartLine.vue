<template>
  <div class="chart-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted, nextTick } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传递的参数
const props = defineProps({
  // 图表数据 { xAxis: [], series: [{name: '', data: []}, ...] }
  data: {
    type: Object,
    required: true,
    default: () => ({
      xAxis: [],
      series: []
    })
  },
  // Y轴名称
  yAxisName: {
    type: String,
    default: ''
  },
  // 是否显示网格线
  showGrid: {
    type: Boolean,
    default: true
  },
  // 是否显示面积填充
  showArea: {
    type: Boolean,
    default: false
  },
  // 新增：基础字体缩放比例
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

  // 销毁已存在的实例
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 创建新实例
  chartInstance = echarts.init(chartRef.value);

  // 设置图表配置
  const option = getChartOption();
  chartInstance.setOption(option);
};

// 生成图表配置项
const getChartOption = () => {
  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const legendFontSize = vwToPx(0.7); // 图例文字
  const axisLabelFontSize = vwToPx(0.6); // 坐标轴标签
  const yAxisNameFontSize = vwToPx(0.7); // y轴名称

  return {
    backgroundColor: 'transparent',
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
    legend: {
      data: props.data.series.map(item => item.name),
      top: 0,
      textStyle: {
        color: '#ccc',
        fontSize: legendFontSize // 图例文字自适应
      },
      itemWidth: vwToPx(0.6), // 图例图标大小自适应
      itemHeight: vwToPx(0.6)
    },
    grid: {
      left: '4%',
      right: '4%',
      bottom: '3%',
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
        fontSize: axisLabelFontSize, // x轴标签自适应
        rotate: 30,
        interval: 0
      },
      splitLine: {
        show: false
      }
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: '#ccc',
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
        formatter: function (value) {
          // 如果是百分比，添加%符号
          if (props.yAxisName.includes('%')) {
            return value + '%';
          }
          return value;
        }
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.1)'
        }
      }
    },
    series: props.data.series.map((item, index) => {
      // 预设几种颜色，循环使用
      const colors = [
        '#13ce66', // 绿色
        '#ff7d00', // 橙色
        '#ff4949', // 红色
        '#722ed1'  // 紫色
      ];

      return {
        name: item.name,
        type: item.type || 'line',
        data: item.data,
        symbol: 'circle',
        symbolSize: 6,
        smooth: true,
        showSymbol: false,
        emphasis: {
          showSymbol: true
        },
        lineStyle: {
          width: 3,
          color: colors[index % colors.length]
        },
        itemStyle: {
          color: colors[index % colors.length]
        },
        areaStyle: props.showArea ? {
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
        } : undefined,
        // 虚线样式（如果指定）
        ...(item.lineStyle ? {
          lineStyle: {
            ...item.lineStyle,
            color: colors[index % colors.length]
          }
        } : {})
      };
    })
  };
};

// 监听数据及缩放比例变化，更新图表
watch(
  () => [props.data, props.baseFontScale],
  () => {
    if (chartInstance) {
      chartInstance.setOption(getChartOption());
    }
  },
  {deep: true}
);

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

<style scoped></style>
