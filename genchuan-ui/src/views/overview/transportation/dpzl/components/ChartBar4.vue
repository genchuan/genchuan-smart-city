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
      legend: ['交通事故', '设备故障', '道路施工', '自然灾害', '其他事件'], // 事件类型默认值（与父组件一致）
      series: [{name: '处置事件数', data: []}] // 系列名称贴合业务
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

// 处置事件类型配色（与父组件 getEventTypeTagType 完全对应）
const EVENT_TYPE_COLORS = {
  '交通事故': 'rgba(245, 63, 63, 0.8)',
  '设备故障': 'rgba(255, 125, 0, 0.8)',
  '道路施工': 'rgba(255, 107, 107, 0.8)',
  '自然灾害': 'rgba(255, 73, 73, 0.8)',
  '其他事件': 'rgba(70,169,146,0.8)'
};

// 事件类型默认排序（确保颜色匹配不错乱）
const DEFAULT_EVENT_TYPES = ['交通事故', '设备故障', '道路施工', '自然灾害', '其他事件'];

// 计算占比（用于tooltip，贴合事件分布统计需求）
const getPercent = (value, total) => {
  return total > 0 ? `${((value / total) * 100).toFixed(1)}%` : '0%';
};

// 获取事件类型对应的颜色（兼容自定义legend顺序）
const getEventTypeColor = (eventType) => {
  return EVENT_TYPE_COLORS[eventType] || EVENT_TYPE_COLORS['其他事件'];
};

// 初始化图表（处置事件类型分布专属适配）
const initChart = () => {
  // 容错：确保数据结构安全（贴合事件类型业务）
  const safeData = props.data || {
    legend: DEFAULT_EVENT_TYPES,
    series: [{name: '处置事件数', data: []}]
  };
  const safeSeries = safeData.series || [{name: '处置事件数', data: []}];
  const firstSeries = safeSeries[0] || {name: '处置事件数', data: []};
  const seriesData = firstSeries.data || [];
  const legendData = safeData.legend || DEFAULT_EVENT_TYPES;

  // 计算总事件数（用于占比统计）
  const total = seriesData.reduce((sum, val) => sum + (val || 0), 0);

  // 计算自适应字号（与父组件表格/卡片字体严格匹配）
  const tooltipFontSize = vwToPx(0.6);    // 提示框文字（匹配父组件表格文字大小）
  const axisLabelFontSize = vwToPx(0.55); // 坐标轴标签（适配事件类型名称长度）
  const nameTextFontSize = vwToPx(0.65);  // y轴名称（略大于标签，保持层级）
  const labelFontSize = vwToPx(0.55);     // 柱状图数值标签（清晰不突兀）

  if (chartInstance) chartInstance.dispose(); // 销毁已有实例
  chartInstance = echarts.init(chartRef.value);

  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
        shadowStyle: {
          color: 'rgba(255, 107, 107, 0.1)' // 红色阴影（贴合父组件主题）
        }
      },
      backgroundColor: 'rgba(10, 16, 100, 0.9)', // 与父组件面板背景完全一致
      borderColor: '#ff6b6b',                   // 父组件主题红边框
      borderWidth: 1,
      padding: [10, 15],
      borderRadius: 6,
      textStyle: {
        color: '#b3d9ff', // 浅蓝文字（与父组件表格文字颜色一致）
        fontSize: tooltipFontSize
      },
      formatter: (params) => {
        const param = params[0] || {};
        const value = param.value || 0;
        const eventType = param.name || '未知类型';
        const eventColor = getEventTypeColor(eventType);

        return `
          <div style="font-weight: 600; margin-bottom: 5px; color: #ffc1e9;">${eventType}</div>
          <div style="display: flex; align-items: center; margin: 3px 0;">
            <span style="display: inline-block; width: 10px; height: 10px; border-radius: 2px; background: ${eventColor}; margin-right: 6px;"></span>
            <span>处置数量：</span>
            <span style="font-weight: 600; margin-left: 4px; color: #fff;">${value} 件</span>
          </div>
          <div style="display: flex; align-items: center; margin: 3px 0;">
            <span style="display: inline-block; width: 10px; height: 10px; border-radius: 2px; background: ${eventColor}; margin-right: 6px;"></span>
            <span>占总事件：</span>
            <span style="font-weight: 600; margin-left: 4px; color: #fff;">${getPercent(value, total)}</span>
          </div>
        `;
      }
    },
    grid: {
      left: '4%',
      right: '4%',
      bottom: '3%',
      top: '8%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: legendData,
      axisLine: {
        lineStyle: {color: 'rgba(255, 107, 107, 0.3)'} // 父组件边框透明度一致
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.8)', // 与父组件坐标轴文字颜色一致
        fontSize: axisLabelFontSize,
        fontWeight: 500,
        margin: 12,
        rotate: legendData.length > 4 ? 30 : 0, // 事件类型过多时旋转防重叠
        align: 'right'
      },
      axisTick: {
        show: false // 隐藏刻度线，保持父组件简洁风格
      },
      splitLine: {
        show: false
      }
    },
    yAxis: {
      type: 'value',
      name: '处置事件数（件）', // 贴合事件分布业务场景
      nameTextStyle: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: nameTextFontSize,
        padding: [0, 10, 0, 0]
      },
      axisLine: {
        lineStyle: {color: 'rgba(255, 107, 107, 0.3)'}
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: axisLabelFontSize,
        formatter: (value) => value === 0 ? '0' : value // 优化0值显示，保持统一
      },
      axisTick: {
        show: false
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(255, 107, 107, 0.1)', // 浅红色分割线（贴合父组件主题）
          type: 'solid'
        }
      },
      min: 0,
      splitNumber: 5 // 合理分割y轴，避免刻度过多（与父组件图表规范一致）
    },
    series: safeSeries.map((item, seriesIdx) => ({
      ...item,
      type: 'bar',
      barWidth: '45%', // 适配多事件类型，避免拥挤
      data: item.data || [],
      itemStyle: {
        // 按事件类型匹配对应颜色（严格遵循父组件标签颜色映射）
        color: (params) => getEventTypeColor(params.name),
        borderRadius: [8, 8, 0, 0], // 顶部圆角（与父组件卡片风格一致）
        shadowBlur: 6,
        shadowColor: (params) => {
          const baseColor = getEventTypeColor(params.name);
          return `${baseColor}60`; // 半透明阴影，增强层次感
        },
        shadowOffsetY: 3
      },
      label: {
        show: true,
        position: 'top',
        color: (params) => {
          // 危险类型（交通事故/自然灾害）用对应警示色，其他用白色，突出重点
          const dangerTypes = ['交通事故', '自然灾害'];
          return dangerTypes.includes(params.name) ? getEventTypeColor(params.name) : 'rgba(255, 255, 255, 0.9)';
        },
        fontSize: labelFontSize,
        fontWeight: 600,
        distance: 5
      }
    }))
  };

  chartInstance.setOption(option);
};

// 窗口大小变化时更新字体并重绘（与父组件适配逻辑一致）
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

// 生命周期（与父组件图表生命周期一致）
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
  background: linear-gradient(180deg, rgba(10, 16, 100, 0.05) 0%, rgba(255, 107, 107, 0.02) 100%);
}

::v-deep(.ec-tooltip) {
  box-shadow: 0 4px 16px rgba(255, 107, 107, 0.2) !important;
}

::v-deep(.ec-series-bar .ec-bar) {
  transition: all 0.3s ease;
}

::v-deep(.ec-series-bar .ec-bar:hover) {
  transform: translateY(-3px);
  opacity: 0.95;
  box-shadow: 0 6px 12px rgba(255, 107, 107, 0.3) !important;
}

::v-deep(.ec-axis-tick-line) {
  display: none !important;
}

::v-deep(.ec-series-bar) {
  --ec-series-item-border-radius: 8px 8px 0 0 !important;
}
</style>
