<template>
  <div class="chart-pie-container" ref="chartContainer"></div>
</template>

<script setup>
import { ref, watch, onMounted, onUnmounted } from 'vue';
import * as echarts from 'echarts';

// 接收父组件传入的饼图数据
const props = defineProps({
  data: {
    type: Object,
    required: true,
    default: () => ({
      legend: [],
      series: [],
      colors: [],
      radius: ''
    }),
  },
  activeIndex: {
    type: Number,
    default: -1 // 默认没选中
  },
  // 新增：基础字体缩放比例（可选）
  baseFontScale: {
    type: Number,
    default: 1
  }
});

// 定义点击事件，向父组件传递点击的扇区索引
const emits = defineEmits(['click']);

const chartContainer = ref(null);
let chartInstance = null;

const defaultColors = [
  '#1890ff', // 蓝
  '#7cb305', // 青
  '#f7ba1e', // 浅橙
  '#ff4d4f'  // 粉红
];

// 计算 vw 对应的 px 值（1vw = 视口宽度的 1%）
const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

// 初始化图表
const initChart = () => {
  if (!chartContainer.value) return;

  // 销毁已存在的实例
  if (chartInstance) {
    chartInstance.dispose();
  }

  // 计算自适应字号
  const legendFontSize = vwToPx(0.65); // 图例文字
  const labelFontSize = vwToPx(0.6); // 扇区标签文字
  const tooltipFontSize = vwToPx(0.65); // 提示框文字

  // 创建新实例
  chartInstance = echarts.init(chartContainer.value);

  // 配置图表选项（带自适应字号）
  const option = {
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b}: {c} ({d}%)',
      backgroundColor: 'rgba(25, 25, 25, 0.9)',
      borderColor: 'rgba(200, 200, 200, 0.3)',
      textStyle: {
        color: '#fff',
        fontSize: tooltipFontSize // 提示框文字自适应
      },
      padding: 10
    },
    legend: {
      orient: 'horizontal',
      bottom: 0,
      textStyle: {
        color: '#e0e0e0',
        fontSize: legendFontSize // 图例文字自适应
      },
      data: props.data.legend,
      itemWidth: 12,
      itemHeight: 12
    },
    series: [
      {
        name: '数量',
        type: 'pie',
        radius: props.data.radius, // 普通饼图（单一半径）
        center: ['52%', '50%'], // 居中调整
        avoidLabelOverlap: false,
        // 允许“单选”（一次只能选中一个扇区）
        selectedMode: 'single',
        // 根据activeIndex指定哪个扇区被选中
        selected: props.activeIndex !== -1 ? {
          [props.activeIndex]: true // 只有activeIndex对应的扇区被选中
        } : {},
        // 选中的扇区向外突出10px，更明显
        selectOffset: 10,
        itemStyle: {
          borderRadius: 6, // 圆角更明显
          borderColor: '#fff', // 白色边框区分扇区
          // 选中的扇区边框加粗（3px），未选中的保持1.5px
          borderWidth: (params) => {
            return params.dataIndex === props.activeIndex ? 3 : 1.5;
          }
        },
        label: {
          show: props.activeIndex === -1,
          position: 'outside',
          formatter: '{d}%', // 显示名称和百分比
          color: '#e0e0e0',
          fontSize: labelFontSize, // 标签文字自适应
          lineHeight: vwToPx(0.8) // 行高也自适应
        },
        labelLine: {
          show: props.activeIndex === -1,
          length: 15,
          length2: 10,
          lineStyle: {
            color: '#888'
          }
        },
        data: props.data.legend.map((name, index) => ({
          name,
          value: props.data.series[index] || 0
        }))
      }
    ]
  };

  // 设置颜色（优先用传入的colors，否则用默认配色）
  option.color = props.data.colors.length > 0 ? props.data.colors : defaultColors;

  // 设置图表选项
  chartInstance.setOption(option);

  // 绑定点击事件
  chartInstance.on('click', (params) => {
    emits('click', params.dataIndex);
  });
};

// 同时监听data和activeIndex的变化
watch(
  () => [props.data, props.activeIndex, props.baseFontScale], // 新增监听基础缩放比例
  () => {
    initChart(); // 任何一个变化，就重新渲染饼图
  },
  {deep: true}
);

// 处理窗口大小变化：更新字号并调整图表
const handleResize = () => {
  if (!chartInstance) return;

  // 重新计算自适应字号
  const legendFontSize = vwToPx(0.65);
  const labelFontSize = vwToPx(0.6);
  const tooltipFontSize = vwToPx(0.65);

  // 更新图表文本大小
  chartInstance.setOption({
    tooltip: {
      textStyle: {fontSize: tooltipFontSize}
    },
    legend: {
      textStyle: {fontSize: legendFontSize}
    },
    series: [{
      label: {
        fontSize: labelFontSize,
        lineHeight: vwToPx(0.8)
      }
    }]
  });

  // 调整图表尺寸
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
  height: 100%;
  min-height: 24vh;
}
</style>
