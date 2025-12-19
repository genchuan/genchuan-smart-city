<template>
  <div class="chart-pie-container" ref="chartContainer"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传递的参数
const props = defineProps({
  // 图表数据格式: [{ name: '', value: '', itemStyle: {} }, ...]
  data: {
    type: Array,
    required: true,
    default: () => []
  },
  // 名称字段（对应数据中的名称键名）
  nameField: {
    type: String,
    default: 'name'
  },
  // 值字段（对应数据中的值键名）
  valueField: {
    type: String,
    default: 'value'
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

const chartContainer = ref(null);
let chartInstance = null;

// 计算 vw 对应的 px 值（结合基础缩放比例）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 初始化图表
const initChart = () => {
  if (!chartContainer.value) return;

  // 销毁已有实例
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 计算自适应字号
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const legendFontSize = vwToPx(0.7); // 图例文字
  const emphasisLabelFontSize = vwToPx(0.8); // 强调状态中心标签文字

  // 创建新实例
  chartInstance = echarts.init(chartContainer.value);

  // 处理数据格式（适配ECharts要求）
  const formattedData = props.data.map(item => ({
    name: item[props.nameField],
    value: item[props.valueField],
    itemStyle: item.itemStyle || {} // 支持自定义颜色
  }));

  // 设置图表配置
  const option = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'item',
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      },
      formatter: '{a} <br/>{b}: {c} ({d}%)' // 显示名称、值、百分比
    },
    legend: {
      orient: 'vertical', // 垂直布局
      left: 'left', // 居左显示
      top: 30,
      textStyle: {
        color: '#ccc',
        fontSize: legendFontSize // 图例文字自适应
      },
      itemWidth: vwToPx(0.6), // 图例图标大小自适应
      itemHeight: vwToPx(0.6)
    },
    series: [
      {
        name: '统计',
        type: 'pie',
        radius: ['50%', '85%'], // 环形饼图
        center: ['55%', '50%'], // 居中偏右，给图例留空间
        avoidLabelOverlap: false,
        itemStyle: {
          borderColor: 'rgba(0, 20, 40, 0.8)', // 扇区边框，与背景融合
          borderWidth: 2
        },
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: emphasisLabelFontSize, // 强调标签文字自适应
            fontWeight: 'bold',
            color: '#fff'
          }
        },
        labelLine: {
          show: false
        },
        data: formattedData
      }
    ]
  };

  chartInstance.setOption(option);
};

// 监听数据及缩放比例变化，重新渲染图表
watch(
  () => [props.data, props.baseFontScale],
  () => {
    initChart();
  },
  {deep: true}
);

// 监听容器大小变化，更新字体并自适应图表
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const legendFontSize = vwToPx(0.7);
  const emphasisLabelFontSize = vwToPx(0.8);

  // 更新文本配置
  chartInstance.setOption({
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    legend: {
      textStyle: {fontSize: legendFontSize},
      itemWidth: vwToPx(0.6),
      itemHeight: vwToPx(0.6)
    },
    series: [{
      emphasis: {
        label: {
          fontSize: emphasisLabelFontSize
        }
      }
    }]
  });

  chartInstance.resize();
};

onMounted(() => {
  initChart();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  window.removeEventListener('resize', handleResize);
  if (chartInstance) {
    chartInstance.dispose();
    chartInstance = null;
  }
});
</script>

<style scoped>
.chart-pie-container {
  width: 100%;
  height: v-bind(height); /* 使用v-bind绑定父组件传递的高度 */
}
</style>
