<template>
  <div class="page-container">
    <div class="mainbox">
      <div class="indicators-container">
        <div class="indicators-filter">
          <el-checkbox-group v-model="showIndicators" @change="filterIndicators">
            <el-checkbox label="population">人口</el-checkbox>
            <el-checkbox label="facility">设施</el-checkbox>
            <el-checkbox label="service">服务</el-checkbox>
            <el-checkbox label="safety">安全</el-checkbox>
            <el-checkbox label="grid">网格</el-checkbox>
          </el-checkbox-group>
        </div>

        <div class="indicators-cards">
          <!-- 使用计算属性过滤后的数据进行循环，移除v-if -->
          <div
            v-for="type in filteredIndicators"
            :key="type"
            class="indicator-card"
            :class="{ abnormal: indicators[type]?.abnormal }"
            @click="gotoDetail(type)"
          >
            <div class="indicator-name">{{ getIndicatorName(type) }}</div>
            <div class="indicator-value">{{ indicators[type].value }}</div>
            <div class="indicator-change" :class="indicators[type].changeType">
              {{ indicators[type].change }}
            </div>
          </div>
        </div>

        <!-- 趋势图表 -->
        <div class="trend-chart-section">
          <div class="section-header">
            <h3>指标趋势分析</h3>
            <el-select v-model="selectedTrendType" @change="updateTrendChart">
              <el-option label="设施在线率" value="facility" />
              <el-option label="服务办结率" value="service" />
              <el-option label="安全事件率" value="safety" />
            </el-select>
          </div>
          <ChartLine :data="trendChartData" v-if="trendChartData.xAxis.length" />
          <div class="empty-chart" v-else>暂无趋势数据</div>
        </div>
      </div>
    </div>

    <!-- 阈值编辑弹窗 -->
    <el-dialog
      title="阈值设置"
      v-model="indicatorEditVisible"
      width="500px"
    >
      <el-form :model="thresholdForm" ref="thresholdFormRef" :rules="thresholdRules">
        <el-form-item label="警告阈值" prop="warningThreshold">
          <el-input v-model.number="thresholdForm.warningThreshold" />
        </el-form-item>
        <el-form-item label="紧急阈值" prop="criticalThreshold">
          <el-input v-model.number="thresholdForm.criticalThreshold" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="indicatorEditVisible = false">取消</el-button>
        <el-button type="primary" @click="saveThreshold">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, onUnmounted, computed } from 'vue';
import { ElMessage, ElDialog, ElForm, ElFormItem, ElInput, ElSelect, ElOption, ElCheckboxGroup, ElCheckbox } from 'element-plus';
import ChartLine from './ChartLine.vue';
import GlobalSituationOverviewAPI from '@/api/overview/smartcommunity/GlobalSituationOverview.js';
import { useRouter } from 'vue-router';

const router = useRouter();

// 筛选条件
const communityFilter = ref('幸福社区');
const timeFilter = ref('month');
const selectedTrendType = ref('facility');
const showIndicators = ref(['population', 'facility', 'service', 'safety', 'grid']);
const thresholdFormRef = ref(null);
const indicatorEditVisible = ref(false);

// 指标数据
const indicators = reactive({
  population: { value: '12,548', change: '+2.3%', changeType: 'increase', abnormal: false },
  facility: { value: '96.7%', change: '+1.2%', changeType: 'increase', abnormal: false },
  service: { value: '78.5%', change: '-3.1%', changeType: 'decrease', abnormal: true },
  safety: { value: '0.3', change: '-0.1', changeType: 'decrease', abnormal: false },
  grid: { value: '98.2%', change: '0%', changeType: 'same', abnormal: false }
});

// 表单验证规则
const thresholdRules = {
  warningThreshold: [{ required: true, message: '请输入警告阈值', trigger: 'blur' }],
  criticalThreshold: [{ required: true, message: '请输入紧急阈值', trigger: 'blur' }]
};

// 阈值表单
const thresholdForm = reactive({
  warningThreshold: 80,
  criticalThreshold: 60,
  autoRefresh: true,
  refreshInterval: '10m'
});

// 趋势图表数据
const trendChartData = ref({
  xAxis: ['1月', '2月', '3月', '4月', '5月', '6月'],
  series: [{ name: '设施在线率(%)', data: [92.5, 93.8, 94.2, 95.1, 96.0, 96.7] }]
});

// 计算属性：过滤出需要显示且存在数据的指标
const filteredIndicators = computed(() => {
  return showIndicators.value.filter(type => {
    return indicators[type] !== undefined;
  });
});

// 指标名称映射
const getIndicatorName = (type) => {
  const nameMap = {
    population: '常住人口',
    facility: '设施在线率',
    service: '服务办结率',
    safety: '安全事件率',
    grid: '网格覆盖率'
  };
  return nameMap[type] || type;
};

// 筛选指标
const filterIndicators = () => {
  fetchIndicatorData();
};

// 获取指标数据
let isLoading = ref(false);
const fetchIndicatorData = async () => {
  if (isLoading.value) return;
  try {
    isLoading.value = true;
    const params = {
      community: communityFilter.value,
      timeRange: timeFilter.value,
      indicators: showIndicators.value
    };
    const { data } = await GlobalSituationOverviewAPI.getIndicators(params);

    Object.keys(data).forEach(key => {
      if (indicators[key]) {
        indicators[key] = { ...indicators[key], ...data[key] };
        checkAbnormalStatus(key);
      }
    });
    ElMessage.success('指标数据加载成功');
  } catch (error) {
    console.error('数据获取失败:', error);
    ElMessage.error('指标数据加载失败');
  } finally {
    isLoading.value = false;
  }
};

// 更新趋势图表
const updateTrendChart = () => {
  const trendConfig = {
    facility: { name: '设施在线率(%)', data: [92.5, 93.8, 94.2, 95.1, 96.0, 96.7] },
    service: { name: '服务办结率(%)', data: [82.1, 81.5, 80.2, 79.8, 79.1, 78.5] },
    safety: { name: '安全事件率(起/平方公里)', data: [0.5, 0.4, 0.3, 0.3, 0.2, 0.3] }
  };
  const config = trendConfig[selectedTrendType.value];
  if (config) {
    trendChartData.value = {
      xAxis: generateTimeAxis(timeFilter.value),
      series: [{ name: config.name, data: config.data }]
    };
  }
};

// 生成时间轴
const generateTimeAxis = (rangeType) => {
  const months = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];
  const quarters = ['Q1', 'Q2', 'Q3', 'Q4'];
  const years = ['2020', '2021', '2022', '2023', '2024'];

  switch (rangeType) {
    case 'month': return months.slice(0, 6);
    case 'quarter': return quarters;
    case 'year': return years.slice(-5);
    default: return months.slice(0, 6);
  }
};

// 检查异常状态
const checkAbnormalStatus = (type) => {
  const indicator = indicators[type];
  if (!indicator) return;
  const numericValue = parseFloat(indicator.value);
  const rules = {
    service: val => val < 80,
    facility: val => val < 90,
    safety: val => val > 0.5,
    grid: val => val < 95,
    population: () => false
  };
  indicator.abnormal = rules[type]?.(numericValue) || false;
};

// 保存阈值设置
const saveThreshold = async () => {
  try {
    await thresholdFormRef.value.validate();
    await GlobalSituationOverviewAPI.saveThreshold({
      ...thresholdForm,
      indicatorType: selectedTrendType.value
    });
    ElMessage.success('阈值设置保存成功');
    indicatorEditVisible.value = false;
  } catch (error) {
    if (error.name === 'ValidationError') return;
    ElMessage.error('保存失败');
  }
};

// 跳转到详情页
const gotoDetail = (type) => {
  router.push({ name: 'IndicatorDetail', params: { type } });
};

// 定时刷新
let refreshTimer = null;
onMounted(() => {
  fetchIndicatorData();
  updateTrendChart();
  refreshTimer = setInterval(fetchIndicatorData, 300000); // 5分钟
});

onUnmounted(() => {
  if (refreshTimer) clearInterval(refreshTimer);
});
</script>

<style lang="scss" scoped>
.page-container {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  background: url("@/assets/chart/images/bg.jpg");
  color: #fff;
  padding: 0 20px;
  box-sizing: border-box;
}

.mainbox {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 15px 0;
  height: calc(100% - 110px);
  box-sizing: border-box;
}

.indicators-container {
  padding: 20px;
  background: rgba(0, 30, 60, 0.3);
  border-radius: 8px;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.indicators-filter {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  gap: 20px;
  flex-wrap: wrap;
}

.indicator-types {
  display: flex;
  gap: 15px;
  flex-wrap: wrap;

  :deep(.el-checkbox-group) {
    display: flex;
    gap: 15px;
    flex-wrap: wrap;

    .el-checkbox {
      margin: 0;

      .el-checkbox__label {
        color: #ccc;
        font-size: 14px;
      }
    }
  }
}

.indicators-cards {
  display: flex;
  gap: 20px;
  justify-content: space-between;
  flex-wrap: wrap;
  margin-bottom: 30px;
}

.indicator-card {
  flex: 1;
  min-width: 200px;
  background: linear-gradient(135deg, rgba(0, 30, 60, 0.6), rgba(0, 50, 100, 0.8));
  border: 1px solid rgba(0, 204, 255, 0.3);
  border-radius: 8px;
  padding: 20px;
  text-align: center;
  transition: all 0.3s;
  cursor: pointer;
  color: #fff;
  position: relative;
  overflow: hidden;
}

.indicator-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 15px rgba(0, 204, 255, 0.3);
  border-color: #00ccff;
}

.indicator-card.abnormal {
  border-color: #ff4949;
  background: linear-gradient(135deg, rgba(245, 34, 45, 0.1), rgba(245, 34, 45, 0.2));
  animation: pulse 2s infinite;
}

@keyframes pulse {
  0% {
    box-shadow: 0 0 0 0 rgba(255, 73, 73, 0.4);
  }
  70% {
    box-shadow: 0 0 0 10px rgba(255, 73, 73, 0);
  }
  100% {
    box-shadow: 0 0 0 0 rgba(255, 73, 73, 0);
  }
}

.indicator-name {
  font-size: 18px;
  margin-bottom: 15px;
  color: #9fbdff;
  font-weight: 600;
}

.indicator-value {
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #00ccff;
}

.indicator-card.abnormal .indicator-value {
  color: #ff4949;
}

.indicator-change {
  margin-bottom: 10px;
  font-size: 16px;
  font-weight: 500;
}

.increase {
  color: #13ce66;
}

.decrease {
  color: #ff4949;
}

.same {
  color: #fac858;
}

.indicator-desc {
  font-size: 12px;
  color: #ccc;
  position: relative;
  padding-top: 10px;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
}

.indicator-tip {
  position: absolute;
  bottom: 30px;
  left: 50%;
  transform: translateX(-50%);
  background: rgba(0, 0, 0, 0.9);
  color: white;
  padding: 8px 12px;
  border-radius: 4px;
  font-size: 12px;
  white-space: nowrap;
  z-index: 10;
  border: 1px solid rgba(0, 204, 255, 0.3);
}

.indicator-tip::after {
  content: '';
  position: absolute;
  top: 100%;
  left: 50%;
  transform: translateX(-50%);
  border: 5px solid transparent;
  border-top-color: rgba(0, 0, 0, 0.9);
}

.trend-chart-section {
  flex: 1;
  background: rgba(0, 30, 60, 0.4);
  border-radius: 8px;
  padding: 20px;
  border: 1px solid rgba(0, 204, 255, 0.2);

  .section-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 15px;

    h3 {
      color: #00ccff;
      margin: 0;
      font-size: 16px;
      font-weight: 600;
    }
  }
}

.indicator-detail-content {
  .detail-section {
    margin-bottom: 20px;

    h3 {
      color: #00ccff;
      margin-bottom: 10px;
      font-size: 16px;
      font-weight: 600;
    }
  }

  .detail-chart {
    margin-top: 20px;
  }
}

.threshold-edit {
  .el-form-item {
    margin-bottom: 16px;

    .el-form-item__label {
      color: #9fbdff;
    }
  }
}

/* 响应式设计 */
@media (max-width: 1200px) {
  .indicators-cards {
    justify-content: center;
  }

  .indicator-card {
    min-width: 180px;
    flex: 0 0 calc(33.333% - 20px);
  }
}

@media (max-width: 768px) {
  .indicators-filter {
    flex-direction: column;
    align-items: flex-start;
  }

  .indicator-card {
    flex: 0 0 calc(50% - 20px);
  }
}

@media (max-width: 480px) {
  .indicator-card {
    flex: 0 0 100%;
  }
}

:deep(.el-select) {
  .el-input__wrapper {
    background: rgba(0, 30, 60, 0.6);
    border: 1px solid rgba(0, 204, 255, 0.3);
    box-shadow: none;

    .el-input__inner {
      color: #fff;
    }
  }

  .el-select__caret {
    color: #00ccff;
  }
}

:deep(.el-dialog) {
  background: rgba(0, 20, 40, 0.95);
  border: 1px solid rgba(0, 204, 255, 0.3);

  .el-dialog__header {
    background: rgba(0, 30, 60, 0.8);
    border-bottom: 1px solid rgba(0, 204, 255, 0.2);

    .el-dialog__title {
      color: #00ccff;
    }
  }

  .el-dialog__body {
    color: #fff;
  }
}

:deep(.el-descriptions) {
  .el-descriptions__label {
    color: #9fbdff;
    background: rgba(0, 30, 60, 0.6);
  }

  .el-descriptions__content {
    color: #fff;
    background: rgba(0, 30, 60, 0.4);
  }
}

:deep(.el-checkbox) {
  .el-checkbox__input.is-checked .el-checkbox__inner {
    background-color: #00ccff;
    border-color: #00ccff;
  }

  .el-checkbox__input.is-checked + .el-checkbox__label {
    color: #00ccff;
  }
}

// 大屏适配优化
@media (min-width: 1920px) {
  .indicator-card {
    min-width: min(200px, 100%);
    flex: 1 1 calc(25% - 20px);
  }

  @media (max-width: 1200px) {
    .indicator-card {
      flex: 1 1 calc(33.333% - 20px);
    }
  }

  @media (max-width: 768px) {
    .indicator-card {
      flex: 1 1 calc(50% - 20px);
    }
  }

  @media (max-width: 480px) {
    .indicator-card {
      flex: 1 1 100%;
    }
  }

  .indicator-value {
    font-size: 32px;
  }

  .indicator-name {
    font-size: 20px;
  }
}

.map-container {
  min-height: 500px;
}


// 4K屏幕适配
@media (min-width: 3840px) {
  .indicator-card {
    min-width: 300px;
    padding: 30px;

    .indicator-value {
      font-size: 48px;
    }

    .indicator-name {
      font-size: 28px;
    }
  }

  .map-controls-toolbar {
    font-size: 18px;
  }
}

.empty-chart {
  text-align: center;
  padding: 40px 0;
  color: #999;
}
</style>
