<template>
  <div class="chart-line-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件参数（适配市政设施故障趋势数据格式）
const props = defineProps({
  // x轴数据（日期，如['12-01','12-02']）
  xAxis: {
    type: Array,
    default: () => []
  },
  // 系列数据（紧急故障/一般故障）
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
  // 基础字体缩放比例（适配大屏分辨率）
  baseFontScale: {
    type: Number,
    default: 1
  }
});

const chartRef = ref(null);
let chartInstance = null;

// 市政设施故障趋势配色（与页面风格强绑定）
const FAULT_TREND_COLORS = [
  '#ff4949', // 紧急故障 - 警示红（核心色）
  '#ff7d00'  // 一般故障 - 提醒橙（次要色）
];

// 计算 vw 对应的 px 值（结合基础缩放比例，匹配市政页面字体体系）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 初始化图表
const initChart = () => {
  if (chartInstance) chartInstance.dispose(); // 销毁已有实例
  if (!chartRef.value) return;

  chartInstance = echarts.init(chartRef.value);

  // 计算自适应字号（与市政页面表格/卡片字体大小协调）
  const tooltipFontSize = vwToPx(0.65);   // 提示框文字（匹配饼图）
  const axisLabelFontSize = vwToPx(0.6);  // 坐标轴标签
  const yAxisNameFontSize = vwToPx(0.7);  // y轴名称
  const legendFontSize = vwToPx(0.65);    // 图例文字

  const option = {
    backgroundColor: 'transparent', // 透明背景，继承父容器深色面板
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'line',
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.3)', // 蓝色指针（匹配页面边框色）
          width: 2,
          type: 'solid'
        }
      },
      backgroundColor: 'rgba(10, 16, 100, 0.8)', // 与面板背景一致
      borderColor: 'rgba(0, 204, 255, 0.3)',    // 统一边框色
      borderWidth: 1,
      padding: [8, 12],
      borderRadius: 6,
      textStyle: {
        color: '#b3d9ff', // 与表格文字色一致
        fontSize: tooltipFontSize
      },
      formatter: (params) => {
        // 自定义提示框格式（适配故障数展示）
        let res = `<div style="font-weight: 600; margin-bottom: 4px; color: #ffb800;">${params[0].axisValue}</div>`;
        params.forEach(item => {
          res += `<div style="display: flex; align-items: center; margin: 3px 0;">
            <span style="display: inline-block; width: 10px; height: 10px; border-radius: 2px; background: ${item.color}; margin-right: 6px;"></span>
            <span>${item.seriesName}：</span>
            <span style="font-weight: 600; margin-left: 4px; color: #fff;">${item.value} 个</span>
          </div>`;
        });
        return res;
      }
    },
    legend: {
      show: props.series.length > 1, // 多系列时显示图例
      top: '0%',
      left: 'center', // 居中显示（适配趋势面板宽度）
      textStyle: {
        color: '#ffb800', // 与页面标题色一致
        fontSize: legendFontSize
      },
      itemWidth: vwToPx(0.8),
      itemHeight: vwToPx(0.8),
      itemGap: vwToPx(1)
    },
    grid: {
      left: '8%',
      right: '8%',
      bottom: '10%',
      top: '20%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: props.xAxis,
      axisLine: {
        lineStyle: {color: 'rgba(0, 204, 255, 0.3)'} // 匹配页面边框透明度
      },
      axisLabel: {
        color: '#b3d9ff', // 与表格文字色一致
        fontSize: axisLabelFontSize,
        rotate: props.xAxis.length > 7 ? 20 : 0, // 日期多则轻微旋转
        align: 'center',
        margin: 8
      },
      axisTick: {
        show: false // 隐藏刻度线，简洁大屏风格
      },
      splitLine: {
        show: false
      }
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName || '故障数（个）', // 默认y轴名称
      nameTextStyle: {
        color: '#b3d9ff',
        fontSize: yAxisNameFontSize,
        padding: [0, 0, 0, 10]
      },
      axisLine: {
        lineStyle: {color: 'rgba(0, 204, 255, 0.3)'}
      },
      axisLabel: {
        color: '#b3d9ff',
        fontSize: axisLabelFontSize,
        formatter: (value) => value === 0 ? '0' : value // 优化0值显示
      },
      axisTick: {
        show: false
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(0, 204, 255, 0.1)', // 浅分割线，不干扰视觉
          type: 'solid'
        }
      },
      min: 0, // 故障数从0开始，符合业务逻辑
      splitNumber: 4 // 适配小面板高度，减少刻度数
    },
    series: props.series.map((item, index) => ({
      ...item,
      type: 'line',
      smooth: true, // 平滑曲线体现故障趋势
      symbol: 'circle', // 数据点样式
      symbolSize: 6,
      showSymbol: true, // 始终显示数据点（故障数少，需清晰展示）
      emphasis: {
        showSymbol: true,
        symbolSize: 9,
        itemStyle: {
          shadowBlur: 10,
          shadowColor: `${FAULT_TREND_COLORS[index % FAULT_TREND_COLORS.length]}80` // 渐变阴影
        }
      },
      lineStyle: {
        width: 3, // 线条加粗，大屏更醒目
        color: FAULT_TREND_COLORS[index % FAULT_TREND_COLORS.length],
        shadowBlur: 8,
        shadowColor: `${FAULT_TREND_COLORS[index % FAULT_TREND_COLORS.length]}80`,
        shadowOffsetY: 2
      },
      itemStyle: {
        color: FAULT_TREND_COLORS[index % FAULT_TREND_COLORS.length],
        borderColor: '#fff', // 白色描边，突出数据点
        borderWidth: 2,
        shadowBlur: 4,
        shadowColor: 'rgba(0, 0, 0, 0.2)'
      },
      areaStyle: {
        // 区域填充渐变（贴合主题色，增强层次感）
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {
            offset: 0,
            color: `${FAULT_TREND_COLORS[index % FAULT_TREND_COLORS.length]}20`
          },
          {
            offset: 1,
            color: `${FAULT_TREND_COLORS[index % FAULT_TREND_COLORS.length]}00`
          }
        ])
      },
      // 数值标签（默认隐藏，大屏小面板避免拥挤）
      label: {
        show: false,
        fontSize: axisLabelFontSize * 0.9,
        color: '#fff',
        position: 'top',
        formatter: (params) => params.value + ' 个'
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
  if (chartInstance) {
    chartInstance.dispose(); // 销毁实例释放资源
    chartInstance = null;
  }
  window.removeEventListener('resize', handleResize);
});

// 监听数据及缩放比例变化，重新渲染图表
watch(
  [() => props.xAxis, () => props.series, () => props.yAxisName, () => props.baseFontScale],
  () => {
    initChart();
  },
  {deep: true}
);
</script>

<style scoped>
.chart-line-container {
  width: 100%;
  height: 100%;
  background: transparent;
}

/* 适配大屏交互样式 */
::v-deep(.echarts-legend-item-hover) {
  opacity: 0.9;
  color: #ffb800 !important;
}

::v-deep(.echarts-tooltip) {
  box-shadow: 0 0 15px rgba(0, 204, 255, 0.2) !important;
}

::v-deep(.echarts-axis-tick) {
  display: none !important;
}

/* 滚动条适配（与页面统一） */
.chart-line-container ::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

.chart-line-container ::-webkit-scrollbar-track {
  background: rgba(16, 32, 64, 0.5);
  border-radius: 3px;
}

.chart-line-container ::-webkit-scrollbar-thumb {
  background: #ff7d00;
  border-radius: 3px;
}
</style>
