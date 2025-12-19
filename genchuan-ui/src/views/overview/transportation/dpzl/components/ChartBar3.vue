<template>
  <div class="chart-bar-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, watch, onMounted, onUnmounted} from 'vue';
import * as echarts from 'echarts';

const props = defineProps({
  data: {
    type: Object,
    default: () => ({
      legend: ['优秀', '良好', '一般', '差评'],
      series: [{name: '评价数量', data: []}]
    })
  },
  height: {
    type: String,
    default: '100%'
  },
  baseFontScale: {
    type: Number,
    default: 1
  }
});

const chartRef = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（与父组件字体比例严格协调）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 评价等级配色（与父组件标签颜色完全一致）
const EVALUATION_LEVEL_COLORS = [
  'rgba(117,210,158,0.8)',
  'rgba(153,125,217,0.8)',
  'rgba(225,147,69,0.8)',
  'rgba(222,116,116,0.8)'
];

// 评价等级文本映射（与父组件一致）
const EVALUATION_LEVEL_TEXT = ['优秀', '良好', '一般', '差评'];

// 计算占比（用于tooltip）
const getPercent = (value, total) => {
  return total > 0 ? `${((value / total) * 100).toFixed(1)}%` : '0%';
};

// 初始化图表（评价等级分布专属适配）
const initChart = () => {
  // 容错：确保数据结构安全
  const safeData = props.data || {
    legend: ['优秀', '良好', '一般', '差评'],
    series: [{name: '评价数量', data: []}]
  };
  const safeSeries = safeData.series || [{name: '评价数量', data: []}];
  const firstSeries = safeSeries[0] || {name: '评价数量', data: []};
  const seriesData = firstSeries.data || [];
  const legendData = safeData.legend || ['优秀', '良好', '一般', '差评'];

  // 计算总评价数（用于占比计算）
  const total = seriesData.reduce((sum, val) => sum + (val || 0), 0);

  // 计算自适应字号（与父组件表格/卡片字体大小严格匹配）
  const tooltipFontSize = vwToPx(0.6);    // 提示框文字（略小于父组件表格文字）
  const axisLabelFontSize = vwToPx(0.55); // 坐标轴标签（适配紧凑布局）
  const nameTextFontSize = vwToPx(0.65);  // y轴名称（略大于标签）
  const labelFontSize = vwToPx(0.55);     // 柱状图数值标签

  if (chartInstance) chartInstance.dispose(); // 销毁已有实例
  chartInstance = echarts.init(chartRef.value);

  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
        shadowStyle: {
          color: 'rgba(139, 92, 246, 0.1)' // 紫色阴影（贴合主题）
        }
      },
      backgroundColor: 'rgba(10, 16, 100, 0.9)', // 与父组件面板背景一致
      borderColor: '#8b5cf6',                   // 紫色边框（服务质量主题）
      borderWidth: 1,
      padding: [10, 15],
      borderRadius: 6,
      textStyle: {
        color: '#b3d9ff', // 浅蓝文字（与父组件表格文字完全一致）
        fontSize: tooltipFontSize
      },
      formatter: (params) => {
        const param = params[0] || {};
        const value = param.value || 0;
        const level = param.name || '未知等级';
        // 匹配评价等级颜色
        const levelIndex = EVALUATION_LEVEL_TEXT.indexOf(level);
        const levelColor = levelIndex !== -1 ? EVALUATION_LEVEL_COLORS[levelIndex] : EVALUATION_LEVEL_COLORS[1];

        return `
          <div style="font-weight: 600; margin-bottom: 5px; color: #c4b5fd;">${level}</div>
          <div style="display: flex; align-items: center; margin: 3px 0;">
            <span style="display: inline-block; width: 10px; height: 10px; border-radius: 2px; background: ${levelColor}; margin-right: 6px;"></span>
            <span>评价数量：</span>
            <span style="font-weight: 600; margin-left: 4px; color: #fff;">${value} 条</span>
          </div>
          <div style="display: flex; align-items: center; margin: 3px 0;">
            <span style="display: inline-block; width: 10px; height: 10px; border-radius: 2px; background: ${levelColor}; margin-right: 6px;"></span>
            <span>占总评价：</span>
            <span style="font-weight: 600; margin-left: 4px; color: #fff;">${getPercent(value, total)}</span>
          </div>
        `;
      }
    },
    grid: {
      left: '6%',
      right: '4%',
      bottom: '10%', // 预留更多底部空间（适配等级名称）
      top: '8%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: legendData,
      axisLine: {
        lineStyle: {color: 'rgba(139, 92, 246, 0.3)'} // 与父组件边框透明度一致
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: axisLabelFontSize,
        fontWeight: 500,
        margin: 10
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
      name: '评价数量（条）', // 贴合评价等级分布业务
      nameTextStyle: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: nameTextFontSize,
        padding: [0, 10, 0, 0]
      },
      axisLine: {
        lineStyle: {color: 'rgba(139, 92, 246, 0.3)'}
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
          color: 'rgba(139, 92, 246, 0.1)', // 浅紫色分割线，贴合主题
          type: 'solid'
        }
      },
      min: 0,
      splitNumber: 5 // 合理分割y轴，避免刻度过多
    },
    series: safeSeries.map((item, seriesIdx) => ({
      ...item,
      type: 'bar',
      barWidth: '40%', // 适当缩窄，提升美观度
      data: item.data || [],
      itemStyle: {
        // 按评价等级顺序分配颜色（优秀→良好→一般→差评）
        color: (params) => {
          const levelIndex = EVALUATION_LEVEL_TEXT.indexOf(params.name);
          return levelIndex !== -1
            ? EVALUATION_LEVEL_COLORS[levelIndex]
            : EVALUATION_LEVEL_COLORS[params.dataIndex % EVALUATION_LEVEL_COLORS.length];
        },
        borderRadius: [8, 8, 0, 0], // 顶部圆角，更现代
        shadowBlur: 6,
        shadowColor: (params) => {
          const levelIndex = EVALUATION_LEVEL_TEXT.indexOf(params.name);
          const baseColor = levelIndex !== -1
            ? EVALUATION_LEVEL_COLORS[levelIndex]
            : EVALUATION_LEVEL_COLORS[params.dataIndex % EVALUATION_LEVEL_COLORS.length];
          return `${baseColor}60`; // 半透明阴影，增强层次感
        },
        shadowOffsetY: 3
      },
      label: {
        show: true,
        position: 'top',
        color: (params) => {
          // 差评用红色标签，其他用白色，突出负面评价
          const levelIndex = EVALUATION_LEVEL_TEXT.indexOf(params.name);
          return levelIndex === 3 ? '#ff4949' : 'rgba(255, 255, 255, 0.9)';
        },
        fontSize: labelFontSize,
        fontWeight: 600,
        distance: 5
      }
    }))
  };

  chartInstance.setOption(option);
};

// 窗口大小变化时更新字体并重绘
const handleResize = () => {
  if (!chartInstance) return;

  const tooltipFontSize = vwToPx(0.6);
  const axisLabelFontSize = vwToPx(0.55);
  const nameTextFontSize = vwToPx(0.65);
  const labelFontSize = vwToPx(0.55);

  // 更新文本配置
  chartInstance.setOption({
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    xAxis: {
      axisLabel: {fontSize: axisLabelFontSize}
    },
    yAxis: {
      nameTextStyle: {fontSize: nameTextFontSize},
      axisLabel: {fontSize: axisLabelFontSize}
    },
    series: props.data?.series?.map(() => ({
      label: {fontSize: labelFontSize}
    }))
  });

  chartInstance.resize();
};

// 生命周期
onMounted(() => {
  chartRef.value.style.height = props.height;
  initChart();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  chartInstance?.dispose();
  window.removeEventListener('resize', handleResize);
});

// 监听数据及缩放比例变化，重新渲染图表
watch(() => [props.data, props.baseFontScale], () => {
  initChart();
}, {deep: true});
</script>

<style scoped>
.chart-bar-container {
  width: 100%;
  height: 100%;
  background: linear-gradient(180deg, rgba(10, 16, 100, 0.05) 0%, rgba(139, 92, 246, 0.02) 100%);
}

::v-deep(.ec-tooltip) {
  box-shadow: 0 4px 16px rgba(139, 92, 246, 0.2) !important;
}

::v-deep(.ec-series-bar .ec-bar) {
  transition: all 0.3s ease;
}

::v-deep(.ec-series-bar .ec-bar:hover) {
  transform: translateY(-3px);
  opacity: 0.95;
}

::v-deep(.ec-axis-tick-line) {
  display: none !important;
}
</style>
