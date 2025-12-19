<template>
  <div class="chart-line-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, watch, onMounted, onUnmounted} from 'vue';
import * as echarts from 'echarts';

// 接收父组件参数
const props = defineProps({
  // x轴数据
  xAxis: {
    type: Array,
    default: () => []
  },
  // 系列数据
  series: {
    type: Array,
    default: () => []
  },
  // y轴名称
  yAxisName: {
    type: String,
    default: '通行效率'
  },
  // 图表高度
  height: {
    type: String,
    default: '100%'
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

const TRAFFIC_EFFICIENCY_COLORS = [
  '#00b469',
  '#ffc918',
  '#ff7d00',
  '#f53f3f',
  '#722ed1'
];

// 初始化图表
const initChart = () => {
  if (chartInstance) chartInstance.dispose(); // 销毁已有实例
  chartInstance = echarts.init(chartRef.value);

  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const axisLabelFontSize = vwToPx(0.6); // 坐标轴标签
  const yAxisNameFontSize = vwToPx(0.7); // y轴名称
  const legendFontSize = vwToPx(0.65); // 图例文字

  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'line',
        lineStyle: {
          color: 'rgba(24, 144, 255, 0.3)',
          width: 2
        }
      },
      backgroundColor: 'rgba(15, 23, 42, 0.9)', // 深色背景更显专业
      borderColor: 'rgba(24, 144, 255, 0.6)',
      borderWidth: 1,
      padding: [10, 15],
      textStyle: {
        color: '#e6f7ff',
        fontSize: tooltipFontSize
      },
      formatter: (params) => {
        // 自定义提示框格式，突出通行效率数值
        let res = `<div style="font-weight: 500; margin-bottom: 5px;">${params[0].axisValue}</div>`;
        params.forEach(item => {
          res += `<div style="display: flex; align-items: center; margin: 3px 0;">
            <span style="display: inline-block; width: 10px; height: 10px; border-radius: 2px; background: ${item.color}; margin-right: 6px;"></span>
            <span>${item.seriesName}：</span>
            <span style="font-weight: 600; margin-left: 4px;">${item.value}${props.yAxisName.includes('率') ? '%' : ''}</span>
          </div>`;
        });
        return res;
      }
    },
    legend: {
      show: props.series.length > 1, // 多系列时显示图例
      top: '0%',
      textStyle: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: legendFontSize
      },
      itemWidth: 12,
      itemHeight: 12,
      itemGap: 20
    },
    grid: {
      left: '6%',
      right: '4%',
      bottom: '2%',
      top: '20%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: props.xAxis,
      axisLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.25)'
        }
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: axisLabelFontSize,
        rotate: props.xAxis.length > 8 ? 45 : 0, // 数据更多时旋转角度更大
        align: 'right',
        margin: 10
      },
      axisTick: {
        show: false
      },
      splitLine: {
        show: false
      }
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: yAxisNameFontSize,
        padding: [0, 10, 0, 0]
      },
      axisLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.25)'
        }
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: axisLabelFontSize,
        formatter: (value) => props.yAxisName.includes('率') ? `${value}%` : value
      },
      axisTick: {
        show: false
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.1)',
          type: 'solid'
        }
      },
      // 通行效率合理范围设置
      min: props.yAxisName.includes('率') ? 60 : 0, // 效率率值最低60%起
      max: props.yAxisName.includes('率') ? 100 : null, // 效率率值最高100%
      scale: !props.yAxisName.includes('率') // 非率值时启用缩放
    },
    series: props.series.map((item, index) => ({
      ...item,
      type: 'line',
      smooth: true, // 平滑曲线体现趋势
      symbol: 'circle',
      symbolSize: 7,
      showSymbol: false,
      emphasis: {
        showSymbol: true,
        symbolSize: 9,
        itemStyle: {
          shadowBlur: 8,
          shadowColor: 'rgba(24, 144, 255, 0.5)'
        }
      },
      lineStyle: {
        width: 3, // 线条更粗，突出趋势
        color: TRAFFIC_EFFICIENCY_COLORS[index % TRAFFIC_EFFICIENCY_COLORS.length],
        shadowBlur: 6,
        shadowColor: `${TRAFFIC_EFFICIENCY_COLORS[index % TRAFFIC_EFFICIENCY_COLORS.length]}80`,
        shadowOffsetY: 3
      },
      itemStyle: {
        color: TRAFFIC_EFFICIENCY_COLORS[index % TRAFFIC_EFFICIENCY_COLORS.length],
        borderColor: '#fff',
        borderWidth: 2,
        shadowBlur: 4,
        shadowColor: 'rgba(0, 0, 0, 0.2)'
      },
      areaStyle: {
        // 更淡的渐变填充，不干扰趋势查看
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {
            offset: 0,
            color: `${TRAFFIC_EFFICIENCY_COLORS[index % TRAFFIC_EFFICIENCY_COLORS.length]}20`
          },
          {
            offset: 1,
            color: `${TRAFFIC_EFFICIENCY_COLORS[index % TRAFFIC_EFFICIENCY_COLORS.length]}00`
          }
        ])
      },
      // 数值标签（可选，需要时开启）
      label: {
        show: false,
        fontSize: axisLabelFontSize * 0.9,
        color: 'rgba(255, 255, 255, 0.7)',
        position: 'top',
        formatter: (params) => props.yAxisName.includes('率') ? `${params.value}%` : params.value
      }
    }))
  };

  chartInstance.setOption(option);
};

// 窗口大小变化时更新字体并重绘
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const axisLabelFontSize = vwToPx(0.6);
  const yAxisNameFontSize = vwToPx(0.7);
  const legendFontSize = vwToPx(0.65);

  // 更新文本配置
  chartInstance.setOption({
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    legend: {
      textStyle: {fontSize: legendFontSize}
    },
    xAxis: {
      axisLabel: {fontSize: axisLabelFontSize}
    },
    yAxis: {
      nameTextStyle: {fontSize: yAxisNameFontSize},
      axisLabel: {fontSize: axisLabelFontSize}
    },
    series: props.series.map(item => ({
      label: {
        fontSize: axisLabelFontSize * 0.9
      }
    }))
  });

  chartInstance.resize();
};

// 生命周期
onMounted(() => {
  chartRef.value.style.height = props.height; // 设置高度
  initChart();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  chartInstance?.dispose(); // 销毁实例释放资源
  window.removeEventListener('resize', handleResize);
});

// 监听数据及缩放比例变化，重新渲染图表
watch([() => props.xAxis, () => props.series, () => props.yAxisName, () => props.baseFontScale], () => {
  initChart();
}, {deep: true});
</script>

<style scoped>
.chart-line-container {
  width: 100%;
  height: 100%;
  background: linear-gradient(180deg, rgba(15, 23, 42, 0.05) 0%, rgba(24, 144, 255, 0.02) 100%);
}

::v-deep(.ec-legend-item-hover) {
  opacity: 0.9;
}

::v-deep(.ec-tooltip) {
  border-radius: 6px !important;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.3) !important;
}
</style>
