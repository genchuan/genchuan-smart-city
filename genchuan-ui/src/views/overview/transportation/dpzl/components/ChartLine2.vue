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
    default: ''
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

// 计算 vw 对应的 px 值（结合基础缩放比例，与父组件字体协调）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 事故预警主题色（与父组件保持一致：红色-事故数、橙色-预警数、辅助色）
const ACCIDENT_WARNING_COLORS = [
  '#ff4949',    // 红色-事故数（高警示）
  '#ff7d00',    // 橙色-预警数（核心指标）
  '#ffb800',    // 亮橙-辅助指标（如高风险预警）
  '#00ccff'     // 蓝色-参考指标（如处理率）
];

// 初始化图表
const initChart = () => {
  if (chartInstance) chartInstance.dispose(); // 销毁已有实例
  chartInstance = echarts.init(chartRef.value);

  // 计算自适应字号（与父组件表格/卡片字体大小协调）
  const tooltipFontSize = vwToPx(0.6);    // 提示框文字（略小于父组件表格文字）
  const axisLabelFontSize = vwToPx(0.55); // 坐标轴标签（适配紧凑布局）
  const yAxisNameFontSize = vwToPx(0.65); // y轴名称（略大于标签）
  const legendFontSize = vwToPx(0.6);     // 图例文字

  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'line',
        lineStyle: {
          color: 'rgba(255, 125, 0, 0.3)', // 橙色指针（贴合主题）
          width: 2
        }
      },
      backgroundColor: 'rgba(10, 16, 100, 0.9)', // 与父组件面板背景一致
      borderColor: '#ff7d00',                   // 橙色边框（预警主题）
      borderWidth: 1,
      padding: [10, 15],
      borderRadius: 6,
      textStyle: {
        color: '#b3d9ff', // 浅蓝文字（与父组件表格文字一致）
        fontSize: tooltipFontSize
      },
      formatter: (params) => {
        // 自定义提示框格式，突出事故/预警数值
        let res = `<div style="font-weight: 600; margin-bottom: 5px; color: #ffb800;">${params[0].axisValue}</div>`;
        params.forEach(item => {
          res += `<div style="display: flex; align-items: center; margin: 3px 0;">
            <span style="display: inline-block; width: 10px; height: 10px; border-radius: 2px; background: ${item.color}; margin-right: 6px;"></span>
            <span>${item.seriesName}：</span>
            <span style="font-weight: 600; margin-left: 4px; color: #fff;">${item.value}${item.seriesName.includes('率') ? '%' : '起'}</span>
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
      itemGap: 15
    },
    grid: {
      left: '6%',
      right: '4%',
      bottom: props.series.length > 1 ? '10%' : '8%', // 预留图例空间
      top: props.series.length > 1 ? '12%' : '8%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: props.xAxis,
      axisLine: {
        lineStyle: {color: 'rgba(255, 255, 255, 0.3)'} // 与父组件边框透明度一致
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: axisLabelFontSize,
        rotate: props.xAxis.length > 6 ? 30 : 0, // 数据多则旋转防重叠
        align: 'right',
        margin: 8
      },
      axisTick: {
        show: false // 隐藏刻度线，更简洁
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
        lineStyle: {color: 'rgba(255, 255, 255, 0.3)'}
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: axisLabelFontSize,
        formatter: (value) => value === 0 ? '0' : value // 优化0值显示
      },
      axisTick: {
        show: false
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(255, 255, 255, 0.1)', // 浅分割线，不干扰视觉
          type: 'solid'
        }
      },
      min: 0, // 事故/预警数从0起，符合业务逻辑
      splitNumber: 5 // 合理分割y轴，避免刻度过多
    },
    series: props.series.map((item, index) => ({
      ...item,
      type: 'line',
      smooth: true, // 平滑曲线体现趋势
      symbol: 'circle', // 数据点样式
      symbolSize: 7,
      showSymbol: props.series.length <= 2, // 系列少则默认显示数据点，更直观
      emphasis: {
        showSymbol: true,
        symbolSize: 9,
        itemStyle: {
          shadowBlur: 8,
          shadowColor: 'rgba(255, 125, 0, 0.5)' // 橙色阴影，增强警示感
        }
      },
      lineStyle: {
        width: 3, // 线条加粗，突出趋势
        color: ACCIDENT_WARNING_COLORS[index % ACCIDENT_WARNING_COLORS.length],
        shadowBlur: 6,
        shadowColor: `${ACCIDENT_WARNING_COLORS[index % ACCIDENT_WARNING_COLORS.length]}80`,
        shadowOffsetY: 3
      },
      itemStyle: {
        color: ACCIDENT_WARNING_COLORS[index % ACCIDENT_WARNING_COLORS.length],
        borderColor: '#fff',
        borderWidth: 2,
        shadowBlur: 4,
        shadowColor: 'rgba(0, 0, 0, 0.2)'
      },
      areaStyle: {
        // 区域填充渐变（浅色调，不干扰趋势，增强层次感）
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {
            offset: 0,
            color: `${ACCIDENT_WARNING_COLORS[index % ACCIDENT_WARNING_COLORS.length]}20`
          },
          {
            offset: 1,
            color: `${ACCIDENT_WARNING_COLORS[index % ACCIDENT_WARNING_COLORS.length]}00`
          }
        ])
      },
      // 数值标签（事故/预警数关键节点可显示，默认隐藏）
      label: {
        show: false,
        fontSize: axisLabelFontSize * 0.9,
        color: 'rgba(255, 255, 255, 0.8)',
        position: 'top',
        formatter: (params) => params.value + (params.seriesName.includes('率') ? '%' : '起')
      }
    }))
  };

  chartInstance.setOption(option);
};

// 窗口大小变化时更新字体并重绘
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.6);
  const axisLabelFontSize = vwToPx(0.55);
  const yAxisNameFontSize = vwToPx(0.65);
  const legendFontSize = vwToPx(0.6);

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
  background: linear-gradient(180deg, rgba(10, 16, 100, 0.05) 0%, rgba(255, 125, 0, 0.02) 100%);
}

::v-deep(.ec-legend-item-hover) {
  opacity: 0.9;
  color: #ffb800 !important;
}

::v-deep(.ec-tooltip) {
  box-shadow: 0 4px 16px rgba(255, 125, 0, 0.2) !important;
}

::v-deep(.ec-axis-tick-line) {
  display: none !important;
}
</style>
