<template>
  <div class="chart-bar-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, watch, onMounted, onUnmounted} from 'vue';
import * as echarts from 'echarts';

const props = defineProps({
  data: {
    type: Object,
    default: () => ({legend: [], series: [{name: '风险预警数', data: []}]})
  },
  height: {
    type: String,
    default: '100%'
  },
  baseFontScale: {
    type: Number,
    default: 1
  },
  // 可选：是否显示风险等级标注
  showRiskLevel: {
    type: Boolean,
    default: true
  }
});

const chartRef = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（与父组件字体比例协调）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 风险等级色阶（与父组件预警标签颜色完全一致）
const RISK_LEVEL_COLORS = [
  'rgba(19, 206, 102, 0.8)', // 绿色-低风险（对应父组件success）
  'rgba(255, 125, 0, 0.8)',  // 橙色-中风险（对应父组件warning）
  'rgba(255, 73, 73, 0.8)',  // 红色-高风险（对应父组件danger）
  'rgba(160, 32, 240, 0.8)', // 紫色-极高风险（扩展等级）
  'rgba(255, 184, 0, 0.8)'   // 亮橙-中高风险（过渡等级）
];

// 风险等级文本映射（与父组件预警级别一致）
const RISK_LEVEL_TEXT = ['低风险', '中风险', '高风险', '极高风险', '中高风险'];

// 根据预警数量判断风险等级（可根据实际业务调整阈值）
const getRiskLevel = (value, maxValue) => {
  if (maxValue === 0) return '低风险';
  const ratio = value / maxValue;
  if (ratio < 0.3) return '低风险';
  if (ratio < 0.6) return '中风险';
  if (ratio < 0.8) return '高风险';
  return '极高风险';
};

// 计算占比（用于tooltip）
const getPercent = (value, total) => {
  return total > 0 ? `${((value / total) * 100).toFixed(1)}%` : '0%';
};

// 初始化图表（风险区域分布专属适配）
const initChart = () => {
  // 容错：确保数据结构安全
  const safeData = props.data || {legend: [], series: [{name: '风险预警数', data: []}]};
  const safeSeries = safeData.series || [{name: '风险预警数', data: []}];
  const firstSeries = safeSeries[0] || {name: '风险预警数', data: []};
  const seriesData = firstSeries.data || [];
  const legendData = safeData.legend || [];

  // 计算总数和最大值（用于占比和风险等级判断）
  const total = seriesData.reduce((sum, val) => sum + (val || 0), 0);
  const maxValue = seriesData.length > 0 ? Math.max(...seriesData.filter(val => val !== null && val !== undefined)) : 0;

  // 计算自适应字号（与父组件表格/卡片字体协调）
  const tooltipFontSize = vwToPx(0.6);    // 提示框文字
  const axisLabelFontSize = vwToPx(0.55); // 区域名称标签（适当缩小，适配多区域）
  const nameTextFontSize = vwToPx(0.65);  // y轴名称
  const labelFontSize = vwToPx(0.55);     // 柱状图数值标签
  const levelFontSize = vwToPx(0.45);     // 风险等级小标签

  if (chartInstance) chartInstance.dispose();
  chartInstance = echarts.init(chartRef.value);

  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
        shadowStyle: {
          color: 'rgba(255, 125, 0, 0.1)' // 橙色阴影（贴合主题）
        }
      },
      backgroundColor: 'rgba(10, 16, 100, 0.95)', // 与父组件面板背景一致
      borderColor: '#ff7d00',                   // 橙色边框（预警主题）
      borderWidth: 1,
      padding: [12, 16],
      borderRadius: 6,
      textStyle: {
        color: '#b3d9ff', // 与父组件表格文字颜色一致
        fontSize: tooltipFontSize
      },
      formatter: (params) => {
        const param = params[0] || {};
        const value = param.value || 0;
        const areaName = param.name || '未知区域';
        const level = getRiskLevel(value, maxValue);
        // 匹配风险等级颜色
        const levelColor = RISK_LEVEL_COLORS[RISK_LEVEL_TEXT.indexOf(level) || 1];
        return `
          <div style="font-weight: 600; margin-bottom: 8px; color: #ffb800;">${areaName}</div>
          <div style="margin: 4px 0;">风险预警数：<span style="color: #ff7d00; font-weight: 600;">${value} 起</span></div>
          <div style="margin: 4px 0;">占总预警数：<span style="color: #ff7d00; font-weight: 600;">${getPercent(value, total)}</span></div>
          <div style="margin: 4px 0; display: flex; align-items: center;">
            风险等级：<span style="display: inline-block; width: 8px; height: 8px; border-radius: 50%; background: ${levelColor}; margin: 0 6px;"></span>
            <span style="font-weight: 600;">${level}</span>
          </div>
        `;
      }
    },
    grid: {
      left: '6%',
      right: '4%',
      bottom: legendData.length > 5 ? '12%' : '8%', // 多区域时预留更多底部空间
      top: '8%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: legendData,
      axisLine: {
        lineStyle: {color: 'rgba(255, 255, 255, 0.3)'} // 与父组件边框透明度一致
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: axisLabelFontSize,
        rotate: legendData.length > 4 ? 30 : 0, // 区域名称多则旋转防重叠
        align: 'right',
        margin: 12,
        overflow: 'truncate', // 超长文本截断
        ellipsis: '...'
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
      name: '风险预警数（起）', // 贴合风险区域业务
      nameTextStyle: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: nameTextFontSize,
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
      min: 0,
      splitNumber: 5 // 合理分割y轴，避免刻度过多
    },
    series: safeSeries.map((item, seriesIdx) => ({
      ...item,
      type: 'bar',
      barWidth: '45%', // 适当缩窄，避免多区域重叠
      data: item.data || [],
      itemStyle: {
        // 按预警数量动态分配风险颜色（数量越多颜色越警示）
        color: (params) => {
          const value = params.value || 0;
          const ratio = maxValue === 0 ? 0 : value / maxValue;
          if (ratio < 0.3) return RISK_LEVEL_COLORS[0]; // 低风险-绿色
          if (ratio < 0.6) return RISK_LEVEL_COLORS[1]; // 中风险-橙色
          if (ratio < 0.8) return RISK_LEVEL_COLORS[2]; // 高风险-红色
          return RISK_LEVEL_COLORS[3]; // 极高风险-紫色
        },
        borderRadius: [6, 6, 0, 0], // 顶部圆角，更现代
        shadowBlur: 8,
        shadowColor: (params) => {
          // 风险越高，阴影越明显
          const value = params.value || 0;
          const ratio = maxValue === 0 ? 0 : value / maxValue;
          const baseColor = ratio < 0.3 ? RISK_LEVEL_COLORS[0] :
            ratio < 0.6 ? RISK_LEVEL_COLORS[1] :
              ratio < 0.8 ? RISK_LEVEL_COLORS[2] : RISK_LEVEL_COLORS[3];
          return `${baseColor}60`; // 半透明阴影
        },
        shadowOffsetY: 3
      },
      label: {
        show: true,
        position: 'top',
        color: (params) => {
          // 高风险区域标签用红色，其他用白色，突出重点
          const value = params.value || 0;
          const ratio = maxValue === 0 ? 0 : value / maxValue;
          return ratio >= 0.6 ? RISK_LEVEL_COLORS[2] : 'rgba(255, 255, 255, 0.9)';
        },
        fontSize: labelFontSize,
        fontWeight: 600,
        distance: 5
      },
      // 风险等级小标签（可选）
      emphasis: {
        label: {
          show: props.showRiskLevel,
          formatter: (params) => {
            const value = params.value || 0;
            const level = getRiskLevel(value, maxValue);
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

  const tooltipFontSize = vwToPx(0.6);
  const axisLabelFontSize = vwToPx(0.55);
  const nameTextFontSize = vwToPx(0.65);
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
watch(() => [props.data, props.baseFontScale, props.showRiskLevel], () => {
  initChart();
}, {deep: true});
</script>

<style scoped>
.chart-bar-container {
  width: 100%;
  height: 100%;
  background: linear-gradient(180deg, rgba(10, 16, 100, 0.05) 0%, rgba(255, 125, 0, 0.02) 100%);
}

::v-deep(.ec-tooltip) {
  box-shadow: 0 6px 20px rgba(255, 125, 0, 0.25) !important;
}

::v-deep(.ec-series-bar .ec-bar) {
  transition: all 0.3s ease;
}

::v-deep(.ec-series-bar .ec-bar:hover) {
  transform: translateY(-2px);
  opacity: 0.95;
}

::v-deep(.ec-axis-tick-line) {
  display: none !important;
}
</style>
