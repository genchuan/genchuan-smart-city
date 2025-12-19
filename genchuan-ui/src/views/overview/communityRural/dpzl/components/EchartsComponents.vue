<template>
  <div class="echarts-container">
    <!-- 性别分布饼图 -->
    <div ref="genderChart" class="echarts-chart" v-if="type === 'gender'"></div>

    <!-- 年龄分布饼图 -->
    <div ref="ageChart" class="echarts-chart" v-if="type === 'age'"></div>

    <!-- 户籍类型饼图 -->
    <div ref="householdChart" class="echarts-chart" v-if="type === 'household'"></div>

    <!-- 村庄分布柱状图 -->
    <div ref="villageChart" class="echarts-chart" v-if="type === 'village'"></div>

    <!-- 人口动态折线图 -->
    <div ref="dynamicChart" class="echarts-chart" v-if="type === 'dynamic'"></div>

    <!-- 指标趋势折线图 -->
    <div ref="trendChart" class="echarts-chart" v-if="type === 'trend'"></div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch, nextTick } from 'vue';
import * as echarts from 'echarts';

const props = defineProps({
  type: {
    type: String,
    required: true,
    validator: (value) => ['gender', 'age', 'household', 'village', 'dynamic', 'trend'].includes(value)
  },
  data: {
    type: Object,
    required: true
  },
  options: {
    type: Object,
    default: () => ({})
  }
});

const emit = defineEmits(['chartClick']);

// 图表实例
const genderChart = ref(null);
const ageChart = ref(null);
const householdChart = ref(null);
const villageChart = ref(null);
const dynamicChart = ref(null);
const trendChart = ref(null);

let chartInstance = null;

// 初始化图表
const initChart = () => {
  if (!chartInstance) {
    const chartRef = getChartRef();
    if (chartRef && chartRef.value) {
      chartInstance = echarts.init(chartRef.value);
      chartInstance.on('click', (params) => {
        emit('chartClick', params);
      });
    }
  }
  updateChart();
};

// 获取对应的图表引用
const getChartRef = () => {
  switch (props.type) {
    case 'gender': return genderChart;
    case 'age': return ageChart;
    case 'household': return householdChart;
    case 'village': return villageChart;
    case 'dynamic': return dynamicChart;
    case 'trend': return trendChart;
    default: return genderChart;
  }
};

// 更新图表数据
const updateChart = () => {
  if (!chartInstance) return;

  const option = getChartOption();
  chartInstance.setOption(option, true);
};

// 根据类型获取图表配置
const getChartOption = () => {
  const baseOption = {
    backgroundColor: 'transparent',
    tooltip: {
      trigger: 'item',
      backgroundColor: 'rgba(255, 255, 255, 0.95)',
      borderColor: '#e2e8f0',
      borderWidth: 1,
      textStyle: {
        color: '#334155'
      },
      formatter: function(params) {
        return `${params.seriesName}<br/>${params.name}: ${params.value} (${params.percent}%)`;
      }
    },
    legend: {
      type: 'scroll',
      orient: 'horizontal',
      bottom: 10,
      textStyle: {
        color: '#64748b',
        fontSize: 12
      }
    },
    grid: {
      top: 20,
      right: 20,
      bottom: 40,
      left: 50,
      containLabel: true
    },
    textStyle: {
      color: '#64748b',
      fontSize: 12
    }
  };

  switch (props.type) {
    case 'gender':
      return {
        ...baseOption,
        title: {
          text: '性别分布',
          left: 'center',
          textStyle: {
            color: '#334155',
            fontSize: 14,
            fontWeight: 'bold'
          }
        },
        series: [{
          name: '性别分布',
          type: 'pie',
          radius: ['40%', '70%'],
          center: ['50%', '45%'],
          avoidLabelOverlap: false,
          itemStyle: {
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            show: true,
            formatter: '{b}: {d}%'
          },
          emphasis: {
            label: {
              show: true,
              fontSize: 14,
              fontWeight: 'bold'
            }
          },
          labelLine: {
            show: true
          },
          data: [
            { value: props.data.male, name: '男性', itemStyle: { color: '#3B82F6' } },
            { value: props.data.female, name: '女性', itemStyle: { color: '#EC4899' } }
          ],
          animationType: 'scale',
          animationEasing: 'elasticOut',
          animationDelay: function (idx) {
            return Math.random() * 200;
          }
        }]
      };

    case 'age':
      return {
        ...baseOption,
        title: {
          text: '年龄分布',
          left: 'center',
          textStyle: {
            color: '#334155',
            fontSize: 14,
            fontWeight: 'bold'
          }
        },
        series: [{
          name: '年龄分布',
          type: 'pie',
          radius: ['40%', '70%'],
          center: ['50%', '45%'],
          itemStyle: {
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            formatter: '{b}: {d}%'
          },
          emphasis: {
            label: {
              show: true,
              fontSize: 14,
              fontWeight: 'bold'
            }
          },
          data: props.data.map(item => ({
            value: item.count,
            name: item.range,
            itemStyle: { color: item.color }
          })),
          animationType: 'scale',
          animationEasing: 'elasticOut'
        }]
      };

    case 'household':
      return {
        ...baseOption,
        title: {
          text: '户籍类型',
          left: 'center',
          textStyle: {
            color: '#334155',
            fontSize: 14,
            fontWeight: 'bold'
          }
        },
        series: [{
          name: '户籍类型',
          type: 'pie',
          radius: ['40%', '70%'],
          center: ['50%', '45%'],
          itemStyle: {
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            formatter: '{b}: {d}%'
          },
          emphasis: {
            label: {
              show: true,
              fontSize: 14,
              fontWeight: 'bold'
            }
          },
          data: props.data.map(item => ({
            value: item.count,
            name: item.type,
            itemStyle: { color: item.color }
          })),
          animationType: 'scale',
          animationEasing: 'elasticOut'
        }]
      };

    case 'village':
      return {
        ...baseOption,
        title: {
          text: '各村人口分布',
          left: 'center',
          textStyle: {
            color: '#334155',
            fontSize: 14,
            fontWeight: 'bold'
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          },
          formatter: function(params) {
            return `${params[0].name}<br/>人口: ${params[0].value}`;
          }
        },
        xAxis: {
          type: 'category',
          data: props.data.map(item => item.name),
          axisLabel: {
            color: '#64748b',
            rotate: 45
          },
          axisLine: {
            lineStyle: {
              color: '#e2e8f0'
            }
          }
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            color: '#64748b'
          },
          axisLine: {
            lineStyle: {
              color: '#e2e8f0'
            }
          },
          splitLine: {
            lineStyle: {
              color: '#f1f5f9',
              type: 'dashed'
            }
          }
        },
        series: [{
          name: '人口数量',
          type: 'bar',
          data: props.data.map((item, index) => ({
            value: item.population,
            itemStyle: {
              color: item.color
            }
          })),
          barWidth: '60%',
          itemStyle: {
            borderRadius: [4, 4, 0, 0]
          },
          animationDelay: function (idx) {
            return idx * 100;
          }
        }]
      };

    case 'dynamic':
      return {
        ...baseOption,
        title: {
          text: '人口动态变化',
          left: 'center',
          textStyle: {
            color: '#334155',
            fontSize: 14,
            fontWeight: 'bold'
          }
        },
        tooltip: {
          trigger: 'axis',
          formatter: function(params) {
            const point = props.data.trend[params[0].dataIndex];
            return `${point.month}<br/>总人口: ${point.value}<br/>新增: ${point.increase}<br/>减少: ${point.decrease}`;
          }
        },
        legend: {
          data: ['总人口', '新增人口', '减少人口'],
          top: 30
        },
        xAxis: {
          type: 'category',
          data: props.data.trend.map(item => item.month),
          axisLabel: {
            color: '#64748b'
          },
          axisLine: {
            lineStyle: {
              color: '#e2e8f0'
            }
          }
        },
        yAxis: [
          {
            type: 'value',
            name: '人口数量',
            axisLabel: {
              color: '#64748b'
            },
            axisLine: {
              lineStyle: {
                color: '#e2e8f0'
              }
            },
            splitLine: {
              lineStyle: {
                color: '#f1f5f9',
                type: 'dashed'
              }
            }
          }
        ],
        series: [
          {
            name: '总人口',
            type: 'line',
            data: props.data.trend.map(item => item.value),
            smooth: true,
            lineStyle: {
              color: '#3B82F6',
              width: 3
            },
            itemStyle: {
              color: '#3B82F6'
            },
            symbolSize: 8,
            animationDelay: function (idx) {
              return idx * 100;
            }
          },
          {
            name: '新增人口',
            type: 'bar',
            data: props.data.trend.map(item => item.increase),
            itemStyle: {
              color: '#10B981'
            },
            barWidth: '40%',
            animationDelay: function (idx) {
              return idx * 100 + 100;
            }
          },
          {
            name: '减少人口',
            type: 'bar',
            data: props.data.trend.map(item => item.decrease),
            itemStyle: {
              color: '#EF4444'
            },
            barWidth: '40%',
            animationDelay: function (idx) {
              return idx * 100 + 200;
            }
          }
        ],
        animationEasing: 'elasticOut'
      };

    case 'trend':
      return {
        ...baseOption,
        title: {
          text: props.data.name + '趋势',
          left: 'center',
          textStyle: {
            color: '#334155',
            fontSize: 14,
            fontWeight: 'bold'
          }
        },
        tooltip: {
          trigger: 'axis',
          formatter: function(params) {
            return `${params[0].axisValue}<br/>${params[0].seriesName}: ${params[0].value}`;
          }
        },
        xAxis: {
          type: 'category',
          data: props.data.trendData.map(item => item.date),
          axisLabel: {
            color: '#64748b'
          },
          axisLine: {
            lineStyle: {
              color: '#e2e8f0'
            }
          }
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            color: '#64748b'
          },
          axisLine: {
            lineStyle: {
              color: '#e2e8f0'
            }
          },
          splitLine: {
            lineStyle: {
              color: '#f1f5f9',
              type: 'dashed'
            }
          }
        },
        series: [{
          name: props.data.name,
          type: 'line',
          data: props.data.trendData.map(item => item.value),
          smooth: true,
          lineStyle: {
            color: '#00ccff',
            width: 3
          },
          itemStyle: {
            color: '#00ccff'
          },
          areaStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              { offset: 0, color: 'rgba(0, 204, 255, 0.3)' },
              { offset: 1, color: 'rgba(0, 204, 255, 0.1)' }
            ])
          },
          symbolSize: 6,
          animationDelay: function (idx) {
            return idx * 50;
          }
        }],
        animationEasing: 'elasticOut'
      };

    default:
      return baseOption;
  }
};

// 监听数据变化
watch(() => props.data, () => {
  updateChart();
}, { deep: true });

// 监听选项变化
watch(() => props.options, () => {
  updateChart();
}, { deep: true });

// 响应窗口大小变化
const handleResize = () => {
  if (chartInstance) {
    chartInstance.resize();
  }
};

onMounted(() => {
  nextTick(() => {
    initChart();
    window.addEventListener('resize', handleResize);
  });
});

// 组件卸载时清理
import { onUnmounted } from 'vue';
onUnmounted(() => {
  if (chartInstance) {
    chartInstance.dispose();
    chartInstance = null;
  }
  window.removeEventListener('resize', handleResize);
});

// 暴露方法给父组件
defineExpose({
  resize: handleResize,
  getInstance: () => chartInstance
});
</script>

<style scoped>
.echarts-container {
  width: 100%;
  height: 100%;
}

.echarts-chart {
  width: 100%;
  height: 100%;
  min-height: 300px;
}
</style>
