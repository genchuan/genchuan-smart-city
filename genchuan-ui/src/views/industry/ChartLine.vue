<template>
  <div class="chart-container" ref="chartRef"></div>
</template>

<script setup>
import {ref, watch, onMounted, onUnmounted, nextTick} from 'vue';
import * as echarts from 'echarts';

const props = defineProps({
  data: {
    type: Object,
    required: true,
    default: () => ({
      xAxis: [],
      series: [],
      predictXAxis: []
    })
  },
  yAxisName: {
    type: String,
    default: ''
  },
  showGrid: {
    type: Boolean,
    default: true
  },
  showArea: {
    type: Boolean,
    default: false
  },
  baseFontScale: {
    type: Number,
    default: 1
  }
});

const chartRef = ref(null);
let chartInstance = null;

const vwToPx = (vw) => {
  return window.innerWidth * (vw / 100) * props.baseFontScale;
};

const initChart = () => {
  if (!chartRef.value) return;

  if (chartInstance) {
    chartInstance.dispose();
  }

  chartInstance = echarts.init(chartRef.value);
  const option = getChartOption();
  chartInstance.setOption(option);
};

const getChartOption = () => {
  const tooltipFontSize = vwToPx(0.65);
  const legendFontSize = vwToPx(0.7);
  const axisLabelFontSize = vwToPx(0.6);
  const yAxisNameFontSize = vwToPx(0.7);

  const predictStartIndex = props.data.xAxis.length - 7;
  const validPredictStartIndex = predictStartIndex > 0 ? predictStartIndex : props.data.xAxis.length;

  return {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      },
      backgroundColor: 'rgba(0, 30, 60, 0.8)',
      borderColor: 'rgba(0, 204, 255, 0.3)',
      borderWidth: 1,
      textStyle: {
        color: '#ccc',
        fontSize: tooltipFontSize
      },
      formatter: (params) => {
        let res = `<strong>${params[0].axisValue}</strong>`;
        const currentDate = params[0].axisValue;
        let abnormalReason = '';

        props.data.series.forEach(series => {
          if (series.markArea?.data) {
            series.markArea.data.forEach(area => {
              const start = area[0].xAxis;
              const end = area[1].xAxis;
              if (currentDate >= start && currentDate <= end) {
                abnormalReason = area[0].name;
              }
            });
          }
        });

        if (abnormalReason) {
          res += `<br/><span style="color: #ff4d4f; font-weight: bold;">⚠️ 异常原因：${abnormalReason}</span>`;
        }

        params.forEach(param => {
          const isPredict = param.dataIndex >= validPredictStartIndex;
          const predictTag = isPredict
            ? '<span style="background: #409eff; color: #fff; padding: 0 4px; border-radius: 2px; margin-left: 6px;">预测</span>'
            : '';
          res += `<br/>${param.seriesName}：${param.value} ${predictTag}`;
        });
        return res;
      }
    },
    // ========== 新增：图例配置 ==========
    legend: {
      top: '5%',          // 图例位置（顶部）
      left: 'center',     // 水平居中
      textStyle: {
        color: '#fff',    // 图例文字颜色（适配深色背景）
        fontSize: legendFontSize
      },
      // 图例项样式
      itemStyle: {
        borderWidth: 2
      },
      // 图例图标大小
      itemSize: 20,
      // 自动从series中获取名称和颜色
      data: props.data.series.map(item => ({
        name: item.name,
        icon: 'circle'    // 图例图标样式（圆形，与线条匹配）
      }))
    },
    grid: {
      top: '26%',
      left: '4%',
      right: '4%',
      bottom: '2%',
      containLabel: true,
      show: props.showGrid,
      lineStyle: {
        color: '#ccc'
      }
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: props.data.xAxis,
      axisLine: {
        lineStyle: {
          color: '#ccc'
        }
      },
      axisLabel: {
        color: '#666',
        fontSize: axisLabelFontSize,
        rotate: 30,
        interval: 5,
        formatter: (value) => value
      },
      axisTick: {
        interval: 0,
        lineStyle: {
          color: '#ccc'
        }
      },
      splitLine: {
        show: false
      }
    },
    yAxis: {
      type: 'value',
      name: props.yAxisName,
      nameTextStyle: {
        color: '#666',
        fontSize: yAxisNameFontSize
      },
      axisLine: {
        lineStyle: {
          color: '#ccc'
        }
      },
      axisLabel: {
        color: '#666',
        fontSize: axisLabelFontSize,
        formatter: function (value) {
          return props.yAxisName.includes('%') ? `${value}%` : value;
        }
      },
      splitLine: {
        lineStyle: {
          color: '#ccc'
        }
      }
    },
    series: props.data.series.map((item, index) => {
      const colors = ['#13ce66', '#ff7d00', '#ff4949', '#722ed1'];

      return {
        name: item.name,
        type: item.type || 'line',
        data: item.data,
        symbol: 'circle',
        symbolSize: 6,
        smooth: true,
        showSymbol: false,
        emphasis: {
          showSymbol: true
        },
        lineStyle: {
          width: 3,
          color: colors[index % colors.length],
          type: (item.lineStyle?.type || (param => param.dataIndex >= validPredictStartIndex ? 'dashed' : 'solid'))
        },
        itemStyle: {
          color: colors[index % colors.length]
        },
        areaStyle: props.showArea ? {
          color: {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [
              {offset: 0, color: `${colors[index % colors.length]}80`},
              {offset: 1, color: `${colors[index % colors.length]}00`}
            ]
          }
        } : undefined,
        markPoint: {
          data: [
            {
              name: '峰值',
              type: 'max',
              valueFormatter: (value) => `峰值: ${props.yAxisName.includes('%') ? `${value}%` : value}`,
              itemStyle: {
                color: '#ff0000',
                borderColor: '#fff',
                borderWidth: 2
              }
            },
            {
              name: '谷值',
              type: 'min',
              valueFormatter: (value) => `谷值: ${props.yAxisName.includes('%') ? `${value}%` : value}`,
              itemStyle: {
                color: '#1890ff',
                borderColor: '#fff',
                borderWidth: 2
              }
            }
          ],
          symbol: 'pin',
          symbolSize: 20,
          label: {
            show: false
          }
        }
      };
    })
  };
};

// 监听数据变化更新图表
watch(
  () => [props.data, props.baseFontScale],
  () => {
    if (chartInstance) {
      chartInstance.setOption(getChartOption(), true);
    }
  },
  {deep: true}
);

// 窗口resize适配
const handleResize = () => {
  if (!chartInstance) return;
  chartInstance.resize();
};

// 初始化&销毁
onMounted(() => {
  nextTick(() => {
    initChart();
    window.addEventListener('resize', handleResize);
  });
});

onUnmounted(() => {
  if (chartInstance) {
    chartInstance.dispose();
    chartInstance = null;
  }
  window.removeEventListener('resize', handleResize);
});
</script>

<style scoped>
.chart-container {
  width: 100%;
  height: 100%;
}
</style>
