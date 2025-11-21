<template>
  <div class="chart-line-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, watch, onMounted, onUnmounted} from 'vue';
import * as echarts from 'echarts';

// 接收父组件参数
const props = defineProps({
  // x轴数据（如 ['5月', '6月'] 或 ['8月', '9月', '10月']）
  xAxis: {
    type: Array,
    default: () => []
  },
  // 系列数据（如 [{name: '达标率', data: [92, 90]}] 或水质指标趋势）
  series: {
    type: Array,
    default: () => []
  },
  // y轴名称（如 '达标率 (%)' 或 '浓度值'）
  yAxisName: {
    type: String,
    default: ''
  },
  // 图表高度
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

// 水质相关配色（科技风+水质特征）
const WATER_QUALITY_COLORS = [
  '#13ce66', // 绿色（达标率、合格指标）
  '#00ccff', // 亮蓝（常规指标）
  '#ff7d00', // 橙色（警示指标）
  '#ff4949'  // 红色（超标指标）
];

// 初始化图表
const initChart = () => {
  if (chartInstance) chartInstance.dispose(); // 销毁已有实例
  chartInstance = echarts.init(chartRef.value);

  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const axisLabelFontSize = vwToPx(0.6); // 坐标轴标签
  const yAxisNameFontSize = vwToPx(0.7); // y轴名称

  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      axisPointer: {type: 'line'}, // 折线图用线指针更合适
      backgroundColor: 'rgba(0, 20, 40, 0.85)',
      borderColor: 'rgba(0, 204, 255, 0.4)',
      borderWidth: 1,
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      },
      formatter: (params) => {
        // 自定义tooltip：根据指标类型显示单位
        let res = `<div>${params[0].name}</div>`;
        params.forEach(item => {
          const unit = props.yAxisName.includes('率') ? '%' :
            item.seriesName.includes('大肠菌群') ? 'MPN/100mL' :
              item.seriesName.includes('溶解氧') ? 'mg/L' :
                item.seriesName.includes('pH') ? '' : 'NTU';
          res += `<div style="display: flex; gap: 8px; margin-top: 4px;">
            <span style="display: inline-block; width: 8px; height: 8px; background: ${item.color}; border-radius: 50%;"></span>
            <span>${item.seriesName}: ${item.value} ${unit}</span>
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
        rotate: props.xAxis.length > 6 ? 30 : 0 // 数据多则旋转标签防重叠
      },
      splitLine: {show: false}
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
        fontSize: axisLabelFontSize, // y轴标签自适应
        // 达标率特殊处理（显示百分比）
        formatter: props.yAxisName.includes('率') ? '{value}%' : '{value}'
      },
      splitLine: {lineStyle: {color: 'rgba(255, 255, 255, 0.1)'}},
      min: props.yAxisName.includes('率') ? 80 : 0 // 达标率从80%起，更直观展示波动
    },
    series: props.series.map((item, index) => ({
      ...item,
      type: 'line',
      smooth: true, // 平滑曲线
      symbol: 'circle', // 数据点样式
      symbolSize: 6,
      showSymbol: false, // 默认不显示数据点
      emphasis: {showSymbol: true, symbolSize: 8}, // 鼠标悬停显示
      lineStyle: {
        width: 2.5,
        color: WATER_QUALITY_COLORS[index % WATER_QUALITY_COLORS.length]
      },
      itemStyle: {
        color: WATER_QUALITY_COLORS[index % WATER_QUALITY_COLORS.length],
        borderColor: '#fff',
        borderWidth: 1.5
      },
      areaStyle: {
        // 区域填充渐变
        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
          {offset: 0, color: `${WATER_QUALITY_COLORS[index % WATER_QUALITY_COLORS.length]}33`},
          {offset: 1, color: `${WATER_QUALITY_COLORS[index % WATER_QUALITY_COLORS.length]}00`}
        ])
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
  height: 100%; /* 高度由父组件通过props控制 */
}
</style>
