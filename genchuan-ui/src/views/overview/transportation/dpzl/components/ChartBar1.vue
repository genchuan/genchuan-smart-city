<template>
  <div class="chart-bar-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, watch, onMounted, onUnmounted} from 'vue';
import * as echarts from 'echarts';

const props = defineProps({
  data: {
    type: Object,
    default: () => ({legend: [], series: [{name: '拥堵点数量', data: []}]})
  },
  height: {
    type: String,
    default: '100%'
  },
  baseFontScale: {
    type: Number,
    default: 1
  },
  // 可选：是否显示拥堵等级标注
  showCongestionLevel: {
    type: Boolean,
    default: true
  }
});

const chartRef = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（结合基础缩放比例）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 拥堵等级色阶（交通行业通用）：畅通→缓行→拥堵→严重拥堵→极度拥堵
const CONGESTION_COLORS = [
  'rgba(0, 180, 42, 0.8)', // 绿色-畅通（拥堵点少）
  'rgba(255, 191, 0, 0.8)', // 黄色-缓行
  'rgba(255, 125, 0, 0.8)', // 橙色-拥堵
  'rgba(245, 63, 63, 0.8)', // 红色-严重拥堵
  'rgba(160, 32, 240, 0.8)' // 紫色-极度拥堵
];

// 根据拥堵数量判断等级（可根据实际业务调整阈值）
const getCongestionLevel = (value, maxValue) => {
  if (maxValue === 0) return '畅通';
  const ratio = value / maxValue;
  if (ratio < 0.2) return '畅通';
  if (ratio < 0.4) return '缓行';
  if (ratio < 0.6) return '拥堵';
  if (ratio < 0.8) return '严重拥堵';
  return '极度拥堵';
};

// 计算占比（用于tooltip）
const getPercent = (value, total) => {
  return total > 0 ? `${((value / total) * 100).toFixed(1)}%` : '0%';
};

// 初始化图表（增强拥堵场景适配）
const initChart = () => {
  // 容错：确保数据结构安全
  const safeData = props.data || {legend: [], series: [{name: '拥堵点数量', data: []}]};
  const safeSeries = safeData.series || [{name: '拥堵点数量', data: []}];
  const firstSeries = safeSeries[0] || {name: '拥堵点数量', data: []};
  const seriesData = firstSeries.data || [];
  const legendData = safeData.legend || [];

  // 计算总数和最大值（用于占比和等级判断）
  const total = seriesData.reduce((sum, val) => sum + (val || 0), 0);
  const maxValue = seriesData.length > 0 ? Math.max(...seriesData.filter(val => val !== null && val !== undefined)) : 0;

  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const axisLabelFontSize = vwToPx(0.55); // 区域名称可能较长，适当缩小
  const nameTextFontSize = vwToPx(0.7);
  const labelFontSize = vwToPx(0.55);
  const levelFontSize = vwToPx(0.45);

  if (chartInstance) chartInstance.dispose();
  chartInstance = echarts.init(chartRef.value);

  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
        shadowStyle: {
          color: 'rgba(245, 63, 63, 0.1)' // 指针阴影贴合拥堵主题
        }
      },
      backgroundColor: 'rgba(15, 23, 42, 0.95)', // 深色高透明度，突出警示感
      borderColor: 'rgba(245, 63, 63, 0.8)',
      borderWidth: 1,
      padding: [12, 16],
      borderRadius: 6,
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize
      },
      formatter: (params) => {
        const param = params[0] || {};
        const value = param.value || 0;
        const areaName = param.name || '未知区域';
        const level = getCongestionLevel(value, maxValue);
        // 匹配等级颜色
        const levelColor = CONGESTION_COLORS.find((_, idx) => {
          const levels = ['畅通', '缓行', '拥堵', '严重拥堵', '极度拥堵'];
          return levels[idx] === level;
        });
        return `
          <div style="font-weight: 600; margin-bottom: 8px;">${areaName}</div>
          <div style="margin: 4px 0;">拥堵点数量：<span style="color: #ff7d00; font-weight: 600;">${value} 个</span></div>
          <div style="margin: 4px 0;">占总拥堵数：<span style="color: #ff7d00; font-weight: 600;">${getPercent(value, total)}</span></div>
          <div style="margin: 4px 0; display: flex; align-items: center;">
            拥堵等级：<span style="display: inline-block; width: 8px; height: 8px; border-radius: 50%; background: ${levelColor}; margin: 0 6px;"></span>
            <span style="font-weight: 600;">${level}</span>
          </div>
        `;
      }
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
      data: legendData,
      axisLine: {
        lineStyle: {color: 'rgba(255, 255, 255, 0.3)'}
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.85)',
        fontSize: axisLabelFontSize,
        rotate: legendData.length > 4 ? 30 : 0, // 区域名称多则旋转防重叠
        align: 'right',
        margin: 12,
        overflow: 'truncate', // 超长文本截断
        ellipsis: '...'
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
      name: '拥堵点数量（个）',
      nameTextStyle: {
        color: 'rgba(255, 255, 255, 0.85)',
        fontSize: nameTextFontSize,
        padding: [0, 10, 0, 0]
      },
      axisLine: {
        lineStyle: {color: 'rgba(255, 255, 255, 0.3)'}
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.85)',
        fontSize: axisLabelFontSize,
        formatter: (value) => value === 0 ? '0' : value // 隐藏0值的多余显示
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
      min: 0,
      splitNumber: 5 // 合理分割y轴，避免刻度过多
    },
    series: safeSeries.map((item, seriesIdx) => ({
      ...item,
      type: 'bar',
      barWidth: '45%', // 适当缩窄，避免多区域重叠
      data: item.data || [],
      itemStyle: {
        // 按拥堵数量动态分配颜色（数量越多颜色越警示）
        color: (params) => {
          const value = params.value || 0;
          const ratio = maxValue === 0 ? 0 : value / maxValue;
          if (ratio < 0.2) return CONGESTION_COLORS[0];
          if (ratio < 0.4) return CONGESTION_COLORS[1];
          if (ratio < 0.6) return CONGESTION_COLORS[2];
          if (ratio < 0.8) return CONGESTION_COLORS[3];
          return CONGESTION_COLORS[4];
        },
        borderRadius: [6, 6, 0, 0], // 顶部圆角，更现代
        shadowBlur: 8,
        shadowColor: (params) => {
          // 拥堵越严重，阴影越明显
          const value = params.value || 0;
          const ratio = maxValue === 0 ? 0 : value / maxValue;
          const baseColor = CONGESTION_COLORS[Math.min(Math.floor(ratio * 5), 4)];
          return `${baseColor}60`; // 半透明阴影
        },
        shadowOffsetY: 3
      },
      label: {
        show: true,
        position: 'top',
        color: (params) => {
          // 拥堵严重时标签用对应警示色
          const value = params.value || 0;
          const ratio = maxValue === 0 ? 0 : value / maxValue;
          if (ratio >= 0.6) return CONGESTION_COLORS[Math.min(Math.floor(ratio * 5), 4)];
          return 'rgba(255, 255, 255, 0.9)';
        },
        fontSize: labelFontSize,
        fontWeight: 600,
        distance: 5
      },
      // 拥堵等级小标签（可选）
      emphasis: {
        label: {
          show: props.showCongestionLevel,
          formatter: (params) => {
            const value = params.value || 0;
            const level = getCongestionLevel(value, maxValue);
            return `${value}\n${level}`;
          },
          lineHeight: 14
        }
      }
    }))
  };

  chartInstance.setOption(option);
};

// 窗口大小变化时更新字体并重绘
const handleResize = () => {
  if (!chartInstance) return;

  const tooltipFontSize = vwToPx(0.65);
  const axisLabelFontSize = vwToPx(0.55);
  const nameTextFontSize = vwToPx(0.7);
  const labelFontSize = vwToPx(0.55);
  const levelFontSize = vwToPx(0.45);

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
      label: {fontSize: labelFontSize},
      emphasis: {
        label: {
          fontSize: `${levelFontSize}px`,
          lineHeight: 14
        }
      }
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
watch(() => [props.data, props.baseFontScale, props.showCongestionLevel], () => {
  initChart();
}, {deep: true});
</script>

<style scoped>
.chart-bar-container {
  width: 100%;
  height: 100%;
  background: linear-gradient(180deg, rgba(15, 23, 42, 0.08) 0%, rgba(245, 63, 63, 0.03) 100%);
}

::v-deep(.ec-tooltip) {
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.35) !important;
}

::v-deep(.ec-series-bar .ec-bar) {
  transition: all 0.3s ease;
}

::v-deep(.ec-series-bar .ec-bar:hover) {
  transform: translateY(-2px);
  opacity: 0.95;
}
</style>
