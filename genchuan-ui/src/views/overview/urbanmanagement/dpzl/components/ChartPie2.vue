<template>
  <div class="chart-pie-container" ref="chartContainer"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传递的参数（适配市政设施页面的故障类型分布数据格式）
const props = defineProps({
  // 图表数据格式: { legend: ['井盖','路灯'], series: [{ name: '故障数', data: [8,6] }] }
  data: {
    type: Object,
    required: true,
    default: () => ({ legend: [], series: [] })
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

const chartContainer = ref(null);
let chartInstance = null;

// 市政设施故障类型配色（与页面风格统一）
const faultTypeColors = [
  '#ff4949', // 井盖 - 紧急红
  '#ff7d00', // 路灯 - 一般橙
  '#13ce66', // 管网 - 已处置绿
  '#00c6ff', // 消防栓 - 天蓝
  '#722ed1'  // 垃圾桶 - 紫色
];

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

  // 计算自适应字号（匹配市政页面的字体大小体系）
  const tooltipFontSize = vwToPx(0.65); // 提示框文字
  const legendFontSize = vwToPx(0.7);   // 图例文字
  const emphasisLabelFontSize = vwToPx(0.8); // 强调状态中心标签文字

  // 创建新实例
  chartInstance = echarts.init(chartContainer.value);

  // 处理数据格式（适配父组件传递的故障类型分布数据）
  const formattedData = props.data.legend.map((name, index) => {
    const value = props.data.series[0]?.data[index] || 0;
    return {
      name,
      value,
      itemStyle: {
        color: faultTypeColors[index % faultTypeColors.length], // 循环使用配色
        borderColor: 'rgba(10, 16, 100, 0.8)', // 与背景融合的边框
        borderWidth: 2
      }
    };
  });

  // 设置图表配置（匹配市政页面的深色大屏风格）
  const option = {
    backgroundColor: 'transparent', // 透明背景，继承父容器样式
    tooltip: {
      trigger: 'item',
      backgroundColor: 'rgba(10, 16, 100, 0.8)', // 与页面面板背景一致
      borderColor: 'rgba(0, 204, 255, 0.3)',    // 统一边框色
      borderWidth: 1,
      padding: [8, 12],
      textStyle: {
        color: '#b3d9ff', // 统一文字色
        fontSize: tooltipFontSize,
        fontWeight: 500
      },
      formatter: '{b}: {c} 个 ({d}%)' // 适配故障数展示
    },
    legend: {
      orient: 'vertical', // 垂直布局
      left: 'left',       // 居左显示
      top: 'center',      // 垂直居中
      textStyle: {
        color: '#ffb800', // 图例文字色（与页面标题色一致）
        fontSize: legendFontSize,
        fontWeight: 500
      },
      itemWidth: vwToPx(0.8),   // 图例图标大小自适应
      itemHeight: vwToPx(0.8),
      itemGap: vwToPx(0.8)      // 图例项间距
    },
    series: [
      {
        name: '故障数',
        type: 'pie',
        radius: ['35%', '65%'],
        center: ['60%', '50%'],
        avoidLabelOverlap: false,
        label: {
          show: false, // 隐藏默认标签
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: emphasisLabelFontSize,
            fontWeight: 'bold',
            color: '#fff' // 强调标签白色
          },
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)' // 强调时的阴影效果
          }
        },
        labelLine: {
          show: false // 隐藏标签线
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
  { deep: true }
);

// 监听容器大小变化，自适应图表
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const tooltipFontSize = vwToPx(0.65);
  const legendFontSize = vwToPx(0.7);
  const emphasisLabelFontSize = vwToPx(0.8);

  // 更新文本配置
  chartInstance.setOption({
    tooltip: {
      textStyle: { fontSize: tooltipFontSize }
    },
    legend: {
      textStyle: { fontSize: legendFontSize },
      itemWidth: vwToPx(0.8),
      itemHeight: vwToPx(0.8)
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
  height: v-bind(height); /* 绑定父组件传递的高度 */
  /* 继承父容器的背景和样式 */
  background: transparent;
}

/* 适配滚动条（如果有） */
.chart-pie-container ::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}
.chart-pie-container ::-webkit-scrollbar-track {
  background: rgba(16, 32, 64, 0.5);
  border-radius: 3px;
}
.chart-pie-container ::-webkit-scrollbar-thumb {
  background: #ff7d00;
  border-radius: 3px;
}
</style>
