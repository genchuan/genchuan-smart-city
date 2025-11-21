<template>
  <div class="chart-line-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, watch, onMounted, onUnmounted} from 'vue';
import * as echarts from 'echarts';

// 接收父组件参数
const props = defineProps({
  // x轴数据（如 ['10/1', '10/2']）
  xAxis: {
    type: Array,
    default: () => []
  },
  // 系列数据（多组污染物趋势，如 [{name: 'COD', data: [18.2, 19.5]}]）
  series: {
    type: Array,
    default: () => []
  },
  // y轴名称（如 '排放量 (吨/日)'）
  yAxisName: {
    type: String,
    default: ''
  },
  // 图表高度（默认自适应父容器）
  height: {
    type: String,
    default: '100%'
  },
  // 新增：基础字体缩放比例
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

// 污染源趋势图专用颜色（匹配污染物类型，科技风配色）
const POLLUTANT_COLORS = [
  '#ff4949', // 红色（COD）
  '#ff7d00', // 橙色（氨氮）
  '#00ccff', // 亮蓝（二氧化硫）
  '#13ce66', // 绿色（氮氧化物）
  '#722ed1'  // 紫色（粉尘）
];

// 初始化图表
const initChart = () => {
  if (chartInstance) chartInstance.dispose(); // 销毁旧实例
  chartInstance = echarts.init(chartRef.value);

  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const axisLabelFontSize = vwToPx(0.6); // 坐标轴标签
  const yAxisNameFontSize = vwToPx(0.7); // y轴名称

  const option = {
    backgroundColor: 'transparent', // 透明背景融入页面
    tooltip: {
      trigger: 'axis',
      axisPointer: {type: 'shadow'}, // 阴影指示器
      backgroundColor: 'rgba(0, 20, 40, 0.85)', // 深色半透背景
      borderColor: 'rgba(0, 204, 255, 0.4)',
      borderWidth: 1,
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      },
      formatter: (params) => {
        // 自定义tooltip：日期 + 各污染物排放量
        let res = `<div>${params[0].name}</div>`;
        params.forEach(item => {
          res += `<div style="display: flex; gap: 8px; margin-top: 4px;">
            <span style="display: inline-block; width: 8px; height: 8px; background: ${item.color}; border-radius: 50%;"></span>
            <span>${item.seriesName}: ${item.value} ${props.yAxisName.includes('吨') ? '吨' : ''}</span>
          </div>`;
        });
        return res;
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '6%',
      top: '5%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: props.xAxis,
      axisLine: {lineStyle: {color: 'rgba(255, 255, 255, 0.2)'}},
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: axisLabelFontSize, // x轴标签自适应
        rotate: 30 // 避免文字重叠
      },
      splitLine: {show: false} // 隐藏x轴网格
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: yAxisNameFontSize // y轴名称自适应
      },
      axisLine: {lineStyle: {color: 'rgba(255, 255, 255, 0.2)'}},
      axisLabel: {
        color: 'rgba(255, 255, 255, 0.7)',
        fontSize: axisLabelFontSize // y轴标签自适应
      },
      splitLine: {lineStyle: {color: 'rgba(255, 255, 255, 0.1)'}},
      min: 0 // 排放量从0开始，确保数据对比合理
    },
    series: props.series.map((item, index) => ({
      ...item,
      type: 'line',
      smooth: true, // 平滑曲线
      symbol: 'circle', // 数据点
      symbolSize: 6,
      showSymbol: false, // 默认不显示点
      emphasis: {showSymbol: true, symbolSize: 8}, // 悬停显示并放大
      lineStyle: {
        width: 2.5,
        color: POLLUTANT_COLORS[index % POLLUTANT_COLORS.length]
      },
      itemStyle: {
        color: POLLUTANT_COLORS[index % POLLUTANT_COLORS.length],
        borderColor: '#fff',
        borderWidth: 1.5
      },
      areaStyle: {
        // 半透明区域填充，增强层次感
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {offset: 0, color: `${POLLUTANT_COLORS[index % POLLUTANT_COLORS.length]}33`},
          {offset: 1, color: `${POLLUTANT_COLORS[index % POLLUTANT_COLORS.length]}00`}
        ])
      }
    }))
  };

  chartInstance.setOption(option);
};

// 窗口 resize 适配：更新字体并调整尺寸
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const axisLabelFontSize = vwToPx(0.6);
  const yAxisNameFontSize = vwToPx(0.7);

  // 更新文本配置
  chartInstance.setOption({
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
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

// 监听数据及缩放比例变化重新渲染
watch([() => props.xAxis, () => props.series, () => props.yAxisName, () => props.baseFontScale], () => {
  initChart();
}, {deep: true});
</script>

<style scoped>
.chart-line-container {
  width: 100%;
  height: 100%;
}
</style>
