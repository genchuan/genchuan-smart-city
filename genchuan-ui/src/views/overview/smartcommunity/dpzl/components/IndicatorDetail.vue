<template>
  <div class="indicator-detail-content">
    <!-- 详情头部 -->
    <div class="detail-header">
      <h2>{{ indicatorTitle }}</h2>
      <div class="header-actions">
        <el-button @click="exportData">导出数据</el-button>
        <el-button type="primary" @click="handleEdit">编辑阈值</el-button>
      </div>
    </div>

    <!-- 核心指标 -->
    <div class="detail-section">
      <h3>当前指标状态</h3>
      <div class="current-status" v-if="isLoading">
        <div class="status-loading">加载中...</div>
      </div>
      <div class="current-status" v-else>
        <div class="status-value">{{ indicatorData.currentValue }} {{ indicatorData.unit }}</div>
        <div class="status-change" :class="indicatorData.changeType">
          {{ indicatorData.change }} 较上期
        </div>
        <el-tag v-if="indicatorData.abnormal" type="danger">异常</el-tag>
        <el-tag v-else type="success">正常</el-tag>
      </div>
    </div>

    <!-- 趋势图表 -->
    <div class="trend-charts">
      <div class="chart-container">
        <h4>月度趋势</h4>
        <ChartLine :data="monthlyTrendData" v-if="monthlyTrendData.xAxis.length" />
        <div class="empty-chart" v-else>暂无月度数据</div>
      </div>
      <div class="chart-container">
        <h4>年度对比</h4>
        <ChartLine :data="yearlyComparisonData" v-if="yearlyComparisonData.series[0].data.length" />
        <div class="empty-chart" v-else>暂无年度数据</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, computed, watch } from 'vue';
import { ElMessage, ElButton, ElTag } from 'element-plus';
import ChartLine from './ChartLine.vue';
import GlobalSituationOverviewAPI from '@/api/overview/smartcommunity/GlobalSituationOverview.js';
import { useRoute, useRouter } from 'vue-router';

// 路由实例
const route = useRoute();
const router = useRouter();

// 加载状态
const isLoading = ref(true);

// Props 定义
const props = defineProps({
  indicatorType: {
    type: String,
    default: 'facility',
    validator: (val) => ['population', 'facility', 'service', 'safety', 'grid'].includes(val)
  },
  timeRange: { type: String, default: 'month' },
  community: { type: String, default: '幸福社区' }
});

// 社区ID映射
const COMMUNITY_MAP = {
  '幸福社区': 'comm1001',
  '和谐社区': 'comm1002',
  '平安社区': 'comm1003'
};

// 事件发射
const emit = defineEmits(['close', 'edit']);

// 指标数据 - 提供完整的默认值
const indicatorData = reactive({
  currentValue: '--',
  change: '--',
  changeType: 'same',
  abnormal: false,
  unit: '',
  lastUpdateTime: ''
});

// 图表数据
const monthlyTrendData = ref({
  xAxis: [],
  series: [{ name: '指标值', data: [] }]
});

const yearlyComparisonData = ref({
  xAxis: ['2023年', '2024年'],
  series: [{ name: '对比数据', data: [] }]
});

// 阈值表单
const thresholdForm = reactive({
  warningThreshold: 80,
  criticalThreshold: 60,
  autoRefresh: false
});

// 指标标题
const indicatorTitle = computed(() => {
  const titleMap = {
    population: '常住人口分析',
    facility: '设施在线率分析',
    service: '服务办结率分析',
    safety: '安全事件率分析',
    grid: '网格覆盖率分析'
  };
  return titleMap[props.indicatorType] || '指标分析';
});

// 加载指标详情数据
const loadIndicatorData = async () => {
  try {
    isLoading.value = true;
    console.log('开始加载指标详情数据:', {
      type: props.indicatorType,
      communityId: COMMUNITY_MAP[props.community],
      timeRange: props.timeRange
    });

    const response = await GlobalSituationOverviewAPI.getIndicatorDetail({
      type: props.indicatorType,
      communityId: COMMUNITY_MAP[props.community],
      timeRange: props.timeRange
    });

    console.log('API响应:', response);

    if (response && response.data) {
      Object.assign(indicatorData, response.data);
      console.log('指标数据加载成功:', indicatorData);
    } else {
      throw new Error('API返回数据为空');
    }
  } catch (error) {
    console.error('加载指标数据失败:', error);
    // 使用模拟数据兜底
    const mockData = getMockIndicatorData(props.indicatorType);
    Object.assign(indicatorData, mockData);
  } finally {
    isLoading.value = false;
  }
};

// 获取模拟指标数据
const getMockIndicatorData = (type) => {
  const mockData = {
    population: {
      currentValue: '12,548',
      change: '+2.3%',
      changeType: 'increase',
      abnormal: false,
      unit: '人',
      lastUpdateTime: '2024-01-15 14:30:00'
    },
    facility: {
      currentValue: '96.7',
      change: '+1.2%',
      changeType: 'increase',
      abnormal: false,
      unit: '%',
      lastUpdateTime: '2024-01-15 14:25:00'
    },
    service: {
      currentValue: '78.5',
      change: '-3.1%',
      changeType: 'decrease',
      abnormal: true,
      unit: '%',
      lastUpdateTime: '2024-01-15 14:20:00'
    },
    safety: {
      currentValue: '0.3',
      change: '-0.1',
      changeType: 'decrease',
      abnormal: false,
      unit: '起/平方公里·月',
      lastUpdateTime: '2024-01-15 14:15:00'
    },
    grid: {
      currentValue: '98.2',
      change: '+3%',
      changeType: 'same',
      abnormal: false,
      unit: '%',
      lastUpdateTime: '2024-01-15 14:10:00'
    }
  };

  return mockData[type] || mockData.facility;
};

// 加载图表数据
const loadChartData = async () => {
  try {
    console.log('开始加载图表数据...');

    const chartsData = await GlobalSituationOverviewAPI.getSituationCharts({
      chartType: 'indicatorTrend',
      communityId: COMMUNITY_MAP[props.community],
      indicatorType: props.indicatorType
    });

    console.log('图表数据响应:', chartsData);

    // 处理月度趋势数据
    if (chartsData?.data) {
      monthlyTrendData.value.xAxis = chartsData.data.map(item => item.date);
      monthlyTrendData.value.series[0].data = chartsData.data.map(item => item.value);
    } else {
      // 使用模拟数据
      const mockTrendData = getMockTrendData(props.indicatorType);
      monthlyTrendData.value.xAxis = mockTrendData.xAxis;
      monthlyTrendData.value.series[0].data = mockTrendData.data;
    }

    // 处理年度对比数据
    yearlyComparisonData.value.series[0].data = getYearlyComparisonData(props.indicatorType);

    console.log('图表数据加载完成');
  } catch (error) {
    console.error('加载图表数据失败:', error);
    // 使用模拟数据兜底
    const mockTrendData = getMockTrendData(props.indicatorType);
    monthlyTrendData.value.xAxis = mockTrendData.xAxis;
    monthlyTrendData.value.series[0].data = mockTrendData.data;
    yearlyComparisonData.value.series[0].data = getYearlyComparisonData(props.indicatorType);
  }
};

// 获取模拟趋势数据
const getMockTrendData = (type) => {
  const trendData = {
    population: {
      xAxis: ['1月', '2月', '3月', '4月', '5月', '6月'],
      data: [12230, 12350, 12480, 12548, 12620, 12700]
    },
    facility: {
      xAxis: ['1月', '2月', '3月', '4月', '5月', '6月'],
      data: [95.2, 95.8, 96.1, 96.7, 97.2, 97.5]
    },
    service: {
      xAxis: ['1月', '2月', '3月', '4月', '5月', '6月'],
      data: [82.3, 81.6, 80.2, 78.5, 79.1, 80.3]
    },
    safety: {
      xAxis: ['1月', '2月', '3月', '4月', '5月', '6月'],
      data: [0.5, 0.4, 0.3, 0.3, 0.2, 0.2]
    },
    grid: {
      xAxis: ['1月', '2月', '3月', '4月', '5月', '6月'],
      data: [98.2, 98.2, 98.2, 98.2, 98.2, 98.2]
    }
  };

  return trendData[type] || trendData.facility;
};

// 获取年度对比数据
const getYearlyComparisonData = (type) => {
  const comparisonData = {
    population: [11800, 12548],
    facility: [94.5, 96.7],
    service: [81.6, 78.5],
    safety: [0.4, 0.3],
    grid: [97.8, 98.2]
  };

  return comparisonData[type] || comparisonData.facility;
};

// 导出数据
const exportData = () => {
  GlobalSituationOverviewAPI.exportIndicatorData({
    type: props.indicatorType,
    communityId: COMMUNITY_MAP[props.community],
    format: 'xlsx'
  }).then((response) => {
    const blob = new Blob([response.data], {
      type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
    });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `${indicatorTitle.value}.xlsx`;
    a.click();
    URL.revokeObjectURL(url);
    ElMessage.success('数据导出成功');
  }).catch((error) => {
    console.error('导出失败:', error);
    ElMessage.error('导出失败，请重试');
  });
};

// 编辑阈值
const handleEdit = () => {
  emit('edit', {
    type: props.indicatorType,
    data: indicatorData,
    threshold: thresholdForm
  });
};

// 监听指标类型变化
watch(() => props.indicatorType, (newType) => {
  console.log('指标类型变化:', newType);
  loadAllData();
});

// 加载所有数据
const loadAllData = async () => {
  await loadIndicatorData();
  await loadChartData();
};

// 初始化加载数据
onMounted(() => {
  console.log('IndicatorDetail 组件挂载，开始加载数据...');
  loadAllData();
});
</script>

<style lang="scss" scoped>
.indicator-detail-content {
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  min-height: calc(100vh - 120px);
  color: #333;
}

.detail-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
  padding-bottom: 16px;
  border-bottom: 1px solid #e8e8e8;

  h2 {
    margin: 0;
    font-size: 24px;
    color: #1890ff;
  }

  .header-actions {
    display: flex;
    gap: 12px;
  }
}

.detail-section {
  margin-bottom: 32px;

  h3 {
    margin: 0 0 16px 0;
    font-size: 18px;
    color: #333;
    border-left: 4px solid #1890ff;
    padding-left: 12px;
  }
}

.current-status {
  background: #f8f9fa;
  border-radius: 8px;
  padding: 24px;
  text-align: center;
  border: 1px solid #e8e8e8;

  .status-loading {
    color: #666;
    font-size: 16px;
  }

  .status-value {
    font-size: 48px;
    font-weight: bold;
    color: #1890ff;
    margin-bottom: 8px;
  }

  .status-change {
    font-size: 16px;
    margin-bottom: 12px;

    &.increase {
      color: #52c41a;
    }

    &.decrease {
      color: #ff4d4f;
    }

    &.same {
      color: #faad14;
    }
  }
}

.trend-charts {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 24px;
  margin-bottom: 32px;

  .chart-container {
    background: #f8f9fa;
    border-radius: 8px;
    padding: 20px;
    border: 1px solid #e8e8e8;

    h4 {
      margin: 0 0 16px 0;
      font-size: 16px;
      color: #333;
      text-align: center;
    }
  }
}

.empty-chart {
  text-align: center;
  padding: 40px 0;
  color: #999;
  font-size: 14px;
  background: #f5f5f5;
  border-radius: 4px;
}

// 响应式设计
@media (max-width: 768px) {
  .indicator-detail-content {
    padding: 16px;
  }

  .detail-header {
    flex-direction: column;
    gap: 16px;
    align-items: flex-start;

    h2 {
      font-size: 20px;
    }
  }

  .trend-charts {
    grid-template-columns: 1fr;
    gap: 16px;
  }

  .current-status {
    padding: 16px;

    .status-value {
      font-size: 36px;
    }
  }
}
</style>
