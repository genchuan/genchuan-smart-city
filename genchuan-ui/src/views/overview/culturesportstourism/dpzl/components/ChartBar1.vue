<template>
  <div class="chart-bar-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

const props = defineProps({
  // 文旅资源状态数据结构：{xAxis: ['正常运营','闲置',...], series: [{name: '资源数量', data: [241,...]}]}
  data: {
    type: Object,
    default: () => ({ xAxis: [], series: [{ name: '资源数量', data: [] }] })
  },
  height: {
    type: String,
    default: '100%'
  },
  baseFontScale: {
    type: Number,
    default: 1
  },
  // 可选：是否显示状态等级标注（正常/闲置/维护/待验收/停用）
  showStatusLevel: {
    type: Boolean,
    default: true
  }
});

const chartRef = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（与父组件文旅资源视图字体比例协调）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

const RESOURCE_STATUS_COLORS = [
  'rgba(51,164,234,0.8)',
  'rgba(255,82,82,0.8)'
];

// 文旅资源状态文本映射
const RESOURCE_STATUS_TEXT = ['正常运营', '闲置'];

// 根据状态名称匹配颜色
const getStatusColor = (statusName) => {
  const index = RESOURCE_STATUS_TEXT.indexOf(statusName);
  return index !== -1 ? RESOURCE_STATUS_COLORS[index] : RESOURCE_STATUS_COLORS[0];
};

// 初始化图表（文旅资源状态分布专属适配）
const initChart = () => {
  // 容错：确保数据结构兼容父组件的文旅资源状态数据
  const safeData = props.data || { xAxis: [], series: [{ name: '资源数量', data: [] }] };
  const safeSeries = safeData.series || [{ name: '资源数量', data: [] }];
  const firstSeries = safeSeries[0] || { name: '资源数量', data: [] };
  const seriesData = firstSeries.data || [];
  const xAxisData = safeData.xAxis || [];

  // 计算自适应字号（与父组件文旅资源视图的表格/卡片字体协调）
  const tooltipFontSize = vwToPx(0.6);    // 提示框文字
  const axisLabelFontSize = vwToPx(0.55); // 状态名称标签
  const nameTextFontSize = vwToPx(0.65);  // y轴名称
  const labelFontSize = vwToPx(0.55);     // 资源数量标签
  const levelFontSize = vwToPx(0.45);     // 状态等级小标签

  // 销毁旧实例
  if (chartInstance) chartInstance.dispose();
  chartInstance = echarts.init(chartRef.value);

  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
        shadowStyle: {
          color: 'rgba(147, 51, 234, 0.1)' // 主紫阴影（贴合文旅主题）
        }
      },
      backgroundColor: 'rgba(10, 16, 100, 0.95)', // 与父组件面板背景一致
      borderColor: '#9333ea',                      // 主紫边框（文旅主题）
      borderWidth: 1,
      padding: [12, 16],
      borderRadius: 6,
      textStyle: {
        color: '#d8b4fe', // 与父组件表格文字颜色一致（浅紫）
        fontSize: tooltipFontSize
      },
      formatter: (params) => {
        const param = params[0] || {};
        const count = param.value || 0;
        const statusName = param.name || '未知状态';
        // 匹配状态对应的颜色
        const statusColor = getStatusColor(statusName);

        return `
          <div style="font-weight: 600; margin-bottom: 8px; color: #9333ea;">${statusName}</div>
          <div style="margin: 4px 0;">资源数量：<span style="color: #9333ea; font-weight: 600;">${count} 个</span></div>
          <div style="margin: 4px 0; display: flex; align-items: center;">
            状态类型：<span style="display: inline-block; width: 8px; height: 8px; border-radius: 50%; background: ${statusColor}; margin: 0 6px;"></span>
            <span style="font-weight: 600;">${statusName}</span>
          </div>
        `;
      }
    },
    grid: {
      left: '4%',
      right: '4%',
      bottom: '2%',
      top: '20%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: xAxisData,
      axisLine: {
        lineStyle: { color: 'rgba(147, 51, 234, 0.3)' } // 紫色边框透明度（贴合父组件）
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: axisLabelFontSize,
        align: 'right',
        margin: 12,
        overflow: 'truncate', // 超长状态名截断
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
      name: '资源数量（个）', // 贴合文旅资源业务
      nameTextStyle: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: nameTextFontSize,
        padding: [0, 10, 0, 0]
      },
      axisLine: {
        lineStyle: { color: 'rgba(147, 51, 234, 0.3)' } // 紫色边框
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: axisLabelFontSize,
        formatter: (value) => `${value} 个` // 显示数量单位
      },
      axisTick: {
        show: false
      },
      splitLine: {
        lineStyle: {
          color: 'rgba(147, 51, 234, 0.1)', // 浅紫分割线，不干扰视觉
          type: 'solid'
        }
      },
      min: 0,
      splitNumber: 5 // 合理分割y轴
    },
    series: safeSeries.map((item, seriesIndex) => ({
      ...item,
      type: 'bar',
      barWidth: '45%', // 适当缩窄，避免多状态重叠
      data: item.data || [],
      itemStyle: {
        // 按状态动态分配颜色（闲置用警告黄突出，其他用紫色系）
        color: (params) => {
          const statusName = xAxisData[params.dataIndex] || '';
          return getStatusColor(statusName);
        },
        borderRadius: [6, 6, 0, 0], // 顶部圆角，更现代
        shadowBlur: 8,
        shadowColor: (params) => {
          // 不同状态对应不同阴影
          const statusName = xAxisData[params.dataIndex] || '';
          const baseColor = getStatusColor(statusName);
          return `${baseColor}60`; // 半透明阴影
        },
        shadowOffsetY: 3
      },
      label: {
        show: true,
        position: 'top',
        color: (params) => {
          // 闲置状态用警告黄突出，其他用主紫
          const statusName = xAxisData[params.dataIndex] || '';
          return statusName === '闲置' ? '#ffb800' : '#9333ea';
        },
        fontSize: labelFontSize,
        fontWeight: 600,
        distance: 5,
        formatter: (params) => `${params.value} 个` // 数值显示数量单位
      },
      // 状态等级小标签（可选）
      emphasis: {
        label: {
          show: props.showStatusLevel,
          formatter: (params) => {
            const count = params.value || 0;
            const statusName = xAxisData[params.dataIndex] || '';
            return `${count} 个\n${statusName}`;
          },
          lineHeight: 14,
          fontSize: levelFontSize
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
      textStyle: { fontSize: tooltipFontSize }
    },
    xAxis: {
      axisLabel: { fontSize: axisLabelFontSize }
    },
    yAxis: {
      nameTextStyle: { fontSize: nameTextFontSize },
      axisLabel: { fontSize: axisLabelFontSize }
    },
    series: props.data?.series?.map(() => ({
      label: { fontSize: labelFontSize },
      emphasis: {
        label: {
          fontSize: levelFontSize,
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
watch(() => [props.data, props.baseFontScale, props.showStatusLevel], () => {
  initChart();
}, { deep: true });
</script>

<style scoped>
.chart-bar-container {
  width: 100%;
  height: 100%;
  /* 文旅主题渐变背景：浅紫+深蓝底，贴合父组件风格 */
  background: linear-gradient(180deg, rgba(10, 16, 100, 0.05) 0%, rgba(147, 51, 234, 0.02) 100%);
}

/* 提示框阴影：紫色系，贴合文旅主题 */
::v-deep(.ec-tooltip) {
  box-shadow: 0 6px 20px rgba(147, 51, 234, 0.25) !important;
}

/* 柱状图hover动效 */
::v-deep(.ec-series-bar .ec-bar) {
  transition: all 0.3s ease;
}

::v-deep(.ec-series-bar .ec-bar:hover) {
  transform: translateY(-2px);
  opacity: 0.95;
}

/* 隐藏刻度线 */
::v-deep(.ec-axis-tick-line) {
  display: none !important;
}
</style>
