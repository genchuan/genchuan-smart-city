<template>
  <div class="chart-bar-container" ref="chartRef"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

const props = defineProps({
  // 场馆运营率数据结构：{xAxis: ['场馆名1',...], series: [{name: '运营率（%）', data: [92.5,...]}]}
  data: {
    type: Object,
    default: () => ({ xAxis: [], series: [{ name: '运营率（%）', data: [] }] })
  },
  height: {
    type: String,
    default: '100%'
  },
  baseFontScale: {
    type: Number,
    default: 1
  },
  // 可选：是否显示运营等级标注（优秀/良好/一般/待提升）
  showOperationLevel: {
    type: Boolean,
    default: true
  }
});

const chartRef = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（与父组件场馆监控视图字体比例协调）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 运营率色阶（贴合场馆绿色主题，运营率越高颜色越鲜亮）
const OPERATION_RATE_COLORS = [
  'rgba(0, 153, 76, 0.8)',   // 待提升（深绿-低饱和度）
  'rgba(0, 191, 99, 0.8)',   // 一般（中绿）
  'rgba(19, 206, 102, 0.8)', // 良好（主绿，匹配父组件主题）
  'rgba(51, 235, 142, 0.8)'  // 优秀（亮绿）
];

// 运营率等级文本映射（贴合场馆运营业务）
const OPERATION_LEVEL_TEXT = ['待提升', '一般', '良好', '优秀'];

// 根据运营率判断等级（0-100% 区间划分）
const getOperationLevel = (value) => {
  if (value < 70) return '待提升';
  if (value < 80) return '一般';
  if (value < 90) return '良好';
  return '优秀';
};

// 初始化图表（场馆运营率TOP5专属适配）
const initChart = () => {
  // 容错：确保数据结构兼容父组件的场馆运营率数据
  const safeData = props.data || { xAxis: [], series: [{ name: '运营率（%）', data: [] }] };
  const safeSeries = safeData.series || [{ name: '运营率（%）', data: [] }];
  const firstSeries = safeSeries[0] || { name: '运营率（%）', data: [] };
  const seriesData = firstSeries.data || [];
  const xAxisData = safeData.xAxis || [];

  // 计算自适应字号（与父组件场馆监控视图的表格/卡片字体协调）
  const tooltipFontSize = vwToPx(0.6);    // 提示框文字
  const axisLabelFontSize = vwToPx(0.55); // 场馆名称标签
  const nameTextFontSize = vwToPx(0.65);  // y轴名称
  const labelFontSize = vwToPx(0.55);     // 运营率数值标签
  const levelFontSize = vwToPx(0.45);     // 运营等级小标签

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
          color: 'rgba(19, 206, 102, 0.1)' // 主绿阴影（贴合场馆主题）
        }
      },
      backgroundColor: 'rgba(10, 16, 100, 0.95)', // 与父组件面板背景一致
      borderColor: '#13ce66',                      // 主绿边框（场馆主题）
      borderWidth: 1,
      padding: [12, 16],
      borderRadius: 6,
      textStyle: {
        color: '#b3d9ff', // 与父组件表格文字颜色一致
        fontSize: tooltipFontSize
      },
      formatter: (params) => {
        const param = params[0] || {};
        const rate = param.value || 0;
        const venueName = param.name || '未知场馆';
        const level = getOperationLevel(rate);
        // 匹配运营等级对应的绿色
        const levelColor = OPERATION_RATE_COLORS[OPERATION_LEVEL_TEXT.indexOf(level) || 2];

        return `
          <div style="font-weight: 600; margin-bottom: 8px; color: #13ce66;">${venueName}</div>
          <div style="margin: 4px 0;">运营率：<span style="color: #13ce66; font-weight: 600;">${rate}%</span></div>
          <div style="margin: 4px 0; display: flex; align-items: center;">
            运营等级：<span style="display: inline-block; width: 8px; height: 8px; border-radius: 50%; background: ${levelColor}; margin: 0 6px;"></span>
            <span style="font-weight: 600;">${level}</span>
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
        lineStyle: { color: 'rgba(255, 255, 255, 0.3)' } // 与父组件边框透明度一致
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: axisLabelFontSize,
        rotate: xAxisData.length > 4 ? 30 : 0, // 场馆名称多则旋转防重叠
        align: 'right',
        margin: 12,
        overflow: 'truncate', // 超长场馆名截断
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
      name: '运营率（%）', // 贴合场馆运营业务
      nameTextStyle: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: nameTextFontSize,
        padding: [0, 10, 0, 0]
      },
      axisLine: {
        lineStyle: { color: 'rgba(255, 255, 255, 0.3)' }
      },
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.8)',
        fontSize: axisLabelFontSize,
        formatter: (value) => `${value}%` // 显示百分比符号
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
      max: 100, // 运营率固定0-100%
      splitNumber: 5 // 合理分割y轴（0/20/40/60/80/100）
    },
    series: safeSeries.map((item) => ({
      ...item,
      type: 'bar',
      barWidth: '45%', // 适当缩窄，避免多场馆重叠
      data: item.data || [],
      itemStyle: {
        // 按运营率动态分配绿色（运营率越高颜色越亮）
        color: (params) => {
          const rate = params.value || 0;
          if (rate < 70) return OPERATION_RATE_COLORS[0]; // 待提升-深绿
          if (rate < 80) return OPERATION_RATE_COLORS[1]; // 一般-中绿
          if (rate < 90) return OPERATION_RATE_COLORS[2]; // 良好-主绿
          return OPERATION_RATE_COLORS[3]; // 优秀-亮绿
        },
        borderRadius: [6, 6, 0, 0], // 顶部圆角，更现代
        shadowBlur: 8,
        shadowColor: (params) => {
          // 运营率越高，阴影越明显
          const rate = params.value || 0;
          const baseColor = rate < 70 ? OPERATION_RATE_COLORS[0] :
            rate < 80 ? OPERATION_RATE_COLORS[1] :
              rate < 90 ? OPERATION_RATE_COLORS[2] : OPERATION_RATE_COLORS[3];
          return `${baseColor}60`; // 半透明阴影
        },
        shadowOffsetY: 3
      },
      label: {
        show: true,
        position: 'top',
        color: (params) => {
          // 优秀/良好等级用主绿，其他用白色，突出高运营率场馆
          const rate = params.value || 0;
          return rate >= 80 ? OPERATION_RATE_COLORS[2] : 'rgba(255, 255, 255, 0.9)';
        },
        fontSize: labelFontSize,
        fontWeight: 600,
        distance: 5,
        formatter: (params) => `${params.value}%` // 数值显示百分比
      },
      // 运营等级小标签（可选）
      emphasis: {
        label: {
          show: props.showOperationLevel,
          formatter: (params) => {
            const rate = params.value || 0;
            const level = getOperationLevel(rate);
            return `${rate}%\n${level}`;
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
watch(() => [props.data, props.baseFontScale, props.showOperationLevel], () => {
  initChart();
}, { deep: true });
</script>

<style scoped>
.chart-bar-container {
  width: 100%;
  height: 100%;
  /* 场馆主题渐变背景：浅绿+深蓝底，贴合父组件风格 */
  background: linear-gradient(180deg, rgba(10, 16, 100, 0.05) 0%, rgba(19, 206, 102, 0.02) 100%);
}

/* 提示框阴影：绿色系，贴合场馆主题 */
::v-deep(.ec-tooltip) {
  box-shadow: 0 6px 20px rgba(19, 206, 102, 0.25) !important;
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
