<template>
  <div class="page-container">
    <!-- 页头区域：标题及筛选操作区 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/servicequality.png"
          alt="服务质量图标"
          class="title-icon"
        />
        服务质量评估视图
      </h1>
      <div class="header-actions">
        <!-- 功能按钮组 -->
        <el-button size="small" @click="refreshData">刷新</el-button>
        <el-button size="small" @click="handleBack">返回总览</el-button>
      </div>
    </div>

    <!-- 主内容区域 -->
    <div class="main-content">
      <!-- 核心指标卡片组 -->
      <div class="stats-cards">
        <div class="stat-card">
          <div class="stat-title">整体满意度</div>
          <div class="stat-value" :class="satisfactionScore >= 90 ? 'excellent' : satisfactionScore >= 80 ? 'good' : 'average'">
            {{ satisfactionScore }} <span class="unit">分</span>
          </div>
          <div class="stat-desc">
            <span
              class="compare-item"
              :class="satisfactionYoY > 0 ? 'increase' : 'decrease'"
            >
              同比 {{ satisfactionYoY > 0 ? '↑' : '↓' }}{{ Math.abs(satisfactionYoY).toFixed(1) }}%
            </span>
            <span
              class="compare-item"
              :class="satisfactionMoM > 0 ? 'increase' : 'decrease'"
            >
              环比 {{ satisfactionMoM > 0 ? '↑' : '↓' }}{{ Math.abs(satisfactionMoM).toFixed(1) }}%
            </span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">总评价数</div>
          <div class="stat-value normal">
            {{ totalEvaluation }} <span class="unit">条</span>
          </div>
          <div class="stat-desc">近30天统计 | 有效评价率: 98.5%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">公交准点率</div>
          <!-- 准点率数值，根据阈值显示不同颜色（≥95%优秀，≥90%良好，否则较差） -->
          <div class="stat-value" :class="busOnTimeRate >= 95 ? 'excellent' : busOnTimeRate >= 90 ? 'good' : 'poor'">
            {{ busOnTimeRate }} <span class="unit">%</span>
          </div>
          <!-- 准点率趋势及晚点线路数 -->
          <div class="stat-desc">
            <span class="compare-item" :class="onTimeRateYoY > 0 ? 'increase' : 'decrease'">
              同比 {{ onTimeRateYoY > 0 ? '↑' : '↓' }}{{ Math.abs(onTimeRateYoY).toFixed(1) }}%
            </span>
            <span class="compare-item" :class="onTimeRateMoM > 0 ? 'increase' : 'decrease'">
              环比 {{ onTimeRateMoM > 0 ? '↑' : '↓' }}{{ Math.abs(onTimeRateMoM).toFixed(1) }}%
            </span>
            <span class="level-item poor">晚点线路: {{ lateRouteCount }}条</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">投诉数量</div>
          <!-- 总投诉量，根据数量显示颜色（≤30件优秀，≤50件良好，否则较差） -->
          <div class="stat-value" :class="complaintTotal <= 30 ? 'excellent' : complaintTotal <= 50 ? 'good' : 'poor'">
            {{ complaintTotal }} <span class="unit">件</span>
          </div>
          <!-- 投诉量细分及趋势 -->
          <div class="stat-desc">
            <span class="level-item average">本周新增: {{ complaintWeekly }}件</span>
            <span class="level-item average">本月累计: {{ complaintMonthly }}件</span>
            <span class="compare-item" :class="complaintReduction > 0 ? 'increase' : 'decrease'">
              同比{{ complaintReduction > 0 ? '减少' : '增加' }}: {{ Math.abs(complaintReduction).toFixed(1) }}%
            </span>
          </div>
        </div>
      </div>

      <!-- 图表行：左右分栏布局 -->
      <div class="chart-row">
        <!-- 左侧：评价等级分布柱状图 -->
        <div class="panel level-bar-panel" ref="levelBarPanel">
          <div class="panel-header">
            <h2>评价等级分布</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('levelBarPanel')">
                <el-icon color="#b392f0" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartBar3
              :data="levelBarData"
            />
          </div>
          <div class="panel-footer"></div>
        </div>

        <!-- 右侧：服务质量趋势折线图（满意度+评价数） -->
        <div class="panel trend-panel" ref="trendPanel">
          <div class="panel-header">
            <h2>服务质量趋势（近6个月）</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('trendPanel')">
                <el-icon color="#b392f0" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartLine3
              :xAxis="trendXAxis"
              :series="trendSeries"
              :yAxisName="''"
            />
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <!-- 评价详细数据表格 -->
      <div class="panel data-table-panel" ref="dataTablePanel">
        <div class="panel-header">
          <h2>服务质量评价数据</h2>
          <div class="header-actions">
            <!-- 时间范围筛选器 -->
            <el-select v-model="timeRange" placeholder="时间范围" size="small">
              <el-option label="今日" value="today" />
              <el-option label="本周" value="week" />
              <el-option label="本月" value="month" />
            </el-select>
            <!-- 评价等级筛选器 -->
            <el-select v-model="evaluationLevel" placeholder="评价等级" size="small">
              <el-option label="全部" value="" />
              <el-option label="优秀" value="excellent" />
              <el-option label="良好" value="good" />
              <el-option label="一般" value="average" />
              <el-option label="差评" value="poor" />
            </el-select>
            <!-- 服务类型筛选器 -->
            <el-select v-model="serviceType" placeholder="服务类型" size="small">
              <el-option label="全部" value="" />
              <el-option label="公共交通" value="public_transit" />
              <el-option label="服务区" value="service_area" />
              <el-option label="客运站" value="station" />
            </el-select>
            <el-input
              v-model="searchKeyword"
              placeholder="搜索评价名称/区域"
              size="small"
              style="width: 15vw"
            />
            <el-button size="small" type="primary" @click="exportData">导出数据</el-button>
            <button class="panel-fullscreen-btn" @click="toggleFullscreen('dataTablePanel')">
              <el-icon color="#b392f0" size="16">
                <FullScreen/>
              </el-icon>
            </button>
          </div>
        </div>
        <!-- 表格滚动容器 -->
        <div class="panel-body table-scroll">
          <el-table
            :data="filteredEvaluationData"
            border
            size="small"
            style="width: 100%"
          >
            <el-table-column prop="name" label="评价名称" />
            <el-table-column prop="level" label="评价等级">
              <template #default="scope">
                <el-tag :type="getEvaluationLevelTagType(scope.row.level)">
                  {{ getEvaluationLevelName(scope.row.level) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="area" label="所属区域" />
            <el-table-column prop="service_type" label="服务类型">
              <template #default="scope">
                {{ getServiceTypeName(scope.row.service_type) }}
              </template>
            </el-table-column>
            <el-table-column prop="score" label="评价分数" />
            <el-table-column prop="create_time" label="评价时间" />
            <el-table-column prop="status" label="处理状态">
              <template #default="scope">
                <el-tag :type="getEvaluationStatusTagType(scope.row.status)">
                  {{ getEvaluationStatusName(scope.row.status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <el-button size="small" link @click="showEvaluationDetail(scope.row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="panel-footer"></div>
      </div>
    </div>

    <!-- 评价详情弹窗 -->
    <el-dialog
      v-model="evaluationDetailVisible"
      :title="currentEvaluation?.name || '服务质量评价详情'"
      width="700px"
    >
      <div class="evaluation-detail">
        <!-- 基础信息区域 -->
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="评价ID">{{ currentEvaluation?.eval_id }}</el-descriptions-item>
            <el-descriptions-item label="评价等级">{{ getEvaluationLevelName(currentEvaluation?.level) }}</el-descriptions-item>
            <el-descriptions-item label="所属区域">{{ currentEvaluation?.area }}</el-descriptions-item>
            <el-descriptions-item label="服务类型">{{ getServiceTypeName(currentEvaluation?.service_type) }}</el-descriptions-item>
            <el-descriptions-item label="评价分数">{{ currentEvaluation?.score }} 分</el-descriptions-item>
            <el-descriptions-item label="评价时间">{{ currentEvaluation?.create_time }}</el-descriptions-item>
            <el-descriptions-item label="联系人">{{ currentEvaluation?.contact || '未提供' }}</el-descriptions-item>
            <el-descriptions-item label="处理状态">{{ getEvaluationStatusName(currentEvaluation?.status) }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 评价内容区域 -->
        <div class="detail-section">
          <h3>评价内容</h3>
          <div class="content-card">
            {{ currentEvaluation?.content || '无评价内容' }}
          </div>
        </div>

        <!-- 处理信息区域 -->
        <div class="detail-section">
          <h3>处理信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="处理人员">{{ currentEvaluation?.handler || '未分配' }}</el-descriptions-item>
            <el-descriptions-item label="处理时间">{{ currentEvaluation?.handle_time || '未处理' }}</el-descriptions-item>
            <el-descriptions-item label="优化措施" :span="2">{{ currentEvaluation?.improvement || '未制定' }}</el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, getCurrentInstance } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import screenFull from 'screenfull';
import ChartLine3 from './ChartLine3.vue';
import ChartBar3 from './ChartBar3.vue';
import { FullScreen } from "@element-plus/icons-vue";

// 导入服务质量API
import {
  fetchServiceQualityOverview,
  fetchServiceQualityTrend,
  fetchEvaluationLevelDistribution,
  fetchEvaluationList,
  fetchEvaluationDetailData,
  fetchEvaluationDetailTrend
} from '@/api/overview/transportation/GlobalSituationOverview.js';

const router = useRouter();
const instance = getCurrentInstance();

// 面板全屏切换功能
const toggleFullscreen = (panelRefName) => {
  if (!screenFull.isEnabled) {
    ElMessage.warning('您的浏览器不支持全屏功能');
    return;
  }

  const panel = instance.refs[panelRefName];
  if (!panel) {
    ElMessage.error('未找到面板元素');
    return;
  }

  if (screenFull.isFullscreen && document.fullscreenElement === panel) {
    screenFull.exit();
  } else {
    screenFull.request(panel);
  }
};

// 筛选条件变量
const timeRange = ref('month');            // 时间范围筛选
const evaluationLevel = ref('');           // 评价等级筛选
const serviceType = ref('');               // 服务类型筛选
const searchKeyword = ref('');             // 搜索关键词

// 核心指标数据变量（服务质量专属）
const totalEvaluation = ref(0);
const satisfactionScore = ref(0);
const satisfactionYoY = ref(0);
const satisfactionMoM = ref(0);
const busOnTimeRate = ref(0);
const onTimeRateYoY = ref(0);
const onTimeRateMoM = ref(0);
const lateRouteCount = ref(0);
const complaintTotal = ref(0);
const complaintWeekly = ref(0);
const complaintMonthly = ref(0);
const complaintReduction = ref(0);



// 评价数据变量
const evaluationList = ref([]);
const currentEvaluation = ref(null);
const evaluationDetailVisible = ref(false);
const evaluationTrendXAxis = ref([]);
const evaluationTrendSeries = ref([]);
const trendXAxis = ref([]);
const trendSeries = ref([]);
const levelBarData = ref({});
const evaluationData = ref([]);

// 评价等级名称映射
const getEvaluationLevelName = (level) => {
  const map = {
    'excellent': '优秀',
    'good': '良好',
    'average': '一般',
    'poor': '差评'
  };
  return map[level] || '未知等级';
};

// 评价等级标签样式映射
const getEvaluationLevelTagType = (level) => {
  const map = {
    'excellent': 'success',
    'good': 'primary',
    'average': 'warning',
    'poor': 'danger'
  };
  return map[level] || 'default';
};

// 服务类型名称映射
const getServiceTypeName = (type) => {
  const map = {
    'public_transit': '公共交通',
    'service_area': '服务区',
    'station': '客运站'
  };
  return map[type] || '未知类型';
};

// 评价状态名称映射
const getEvaluationStatusName = (status) => {
  const map = {
    'processed': '已处理',
    'processing': '处理中',
    'pending': '未处理'
  };
  return map[status] || '未知状态';
};

// 评价状态标签样式映射
const getEvaluationStatusTagType = (status) => {
  const map = {
    'processed': 'success',
    'processing': 'info',
    'pending': 'danger'
  };
  return map[status] || 'default';
};

// 筛选后的评价列表（计算属性）
const filteredEvaluationList = computed(() => {
  return evaluationList.value.filter(evaluation => {
    const matchesLevel = !evaluationLevel.value || evaluation.level === evaluationLevel.value;
    const matchesType = !serviceType.value || evaluation.service_type === serviceType.value;
    return matchesLevel && matchesType;
  });
});

// 筛选后的表格数据（计算属性）
const filteredEvaluationData = computed(() => {
  return evaluationData.value.filter(item => {
    const matchesLevel = !evaluationLevel.value || item.level === evaluationLevel.value;
    const matchesType = !serviceType.value || item.service_type === serviceType.value;
    const matchesKeyword = !searchKeyword.value ||
      item.name.includes(searchKeyword.value) ||
      item.area.includes(searchKeyword.value);
    return matchesLevel && matchesType && matchesKeyword;
  });
});

// 显示评价详情
const showEvaluationDetail = async (evaluation) => {
  currentEvaluation.value = { ...evaluation };
  // 从API获取该评价的趋势数据
  const trendData = await fetchEvaluationDetailTrend(evaluation.eval_id);
  evaluationTrendXAxis.value = trendData.xAxis;
  evaluationTrendSeries.value = trendData.series;
  evaluationDetailVisible.value = true;
};

// 刷新数据
const refreshData = async () => {
  try {
    await initData();
    ElMessage.success('服务质量数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
    console.error(error);
  }
};

// 导出数据
const exportData = () => {
  ElMessage.success('服务质量评价数据导出成功');
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/transportation/dpzl');
};

// 初始化数据
const initData = async () => {
  const overviewData = await fetchServiceQualityOverview();
  totalEvaluation.value = overviewData.totalEvaluation;
  satisfactionScore.value = overviewData.satisfactionScore;
  satisfactionYoY.value = overviewData.satisfactionYoY;
  satisfactionMoM.value = overviewData.satisfactionMoM;
  busOnTimeRate.value = overviewData.busOnTimeRate;
  onTimeRateYoY.value = overviewData.onTimeRateYoY;
  onTimeRateMoM.value = overviewData.onTimeRateMoM;
  lateRouteCount.value = overviewData.lateRouteCount;
  complaintTotal.value = overviewData.complaintTotal;
  complaintWeekly.value = overviewData.complaintWeekly;
  complaintMonthly.value = overviewData.complaintMonthly;
  complaintReduction.value = overviewData.complaintReduction;

  const trendData = await fetchServiceQualityTrend();
  trendXAxis.value = trendData.xAxis;
  trendSeries.value = trendData.series;

  const distributionData = await fetchEvaluationLevelDistribution();
  levelBarData.value = distributionData;

  evaluationList.value = await fetchEvaluationList();

  evaluationData.value = await fetchEvaluationDetailData();
};

// 组件挂载时初始化数据
onMounted(async () => {
  await initData();
});
</script>

<style lang="scss" scoped>
$secondary-color: #13ce66;
$primary-color: #8b5cf6; // 服务质量主题色：紫色
$warning-color: #ff7d00;
$danger-color: #ff4949;
$text-secondary: #999999;
$text-tertiary: #666666;
$bg-main: #0a1064;
$bg-card: rgba(16, 32, 80, 0.5);
$bg-panel: rgba(10, 16, 100, 0.6);
$border-color: rgba(139, 92, 246, 0.3); // 紫色边框
$border-light: rgba(139, 92, 246, 0.1); // 浅色紫色边框
$shadow: 0 0 1vw rgba(139, 92, 246, 0.15); // 紫色阴影

// 完全复用基础布局样式，仅调整主题色
.page-container {
  width: 100%;
  height: 100vh;
  overflow: auto;
  background: url("@/assets/chart/images/bg.jpg");
  background-size: 100% 100%;
  color: #555555;
  padding: 0 1.5vw;
  box-sizing: border-box;
  touch-action: pan-x pan-y;
}

.panel-fullscreen-btn {
  background: transparent;
  border: none;
  cursor: pointer;
  padding: 4px;
  border-radius: 4px;
  transition: all 0.2s ease;

  &:hover {
    background: rgba(139, 92, 246, 0.2);
  }
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.2vh 0;
  border-bottom: 0.2vh solid $border-color;
  margin-bottom: 1.2vw;
  position: relative;

  &::after {
    content: '';
    position: absolute;
    bottom: -0.3vh;
    left: 0;
    width: 16vw;
    height: 0.3vh;
    background: $primary-color; // 紫色主题线
  }

  h1 {
    margin: 0;
    font-size: 1.6vw;
    color: #00f2fe;
    font-weight: 600;
    letter-spacing: 0.1vw;

    .title-icon {
      width: 1.8vw;
      height: 1.8vw;
      object-fit: contain;
      vertical-align: middle;
    }
  }

  .header-actions {
    display: flex;
    gap: 0.8vw;
    align-items: center;
  }

  .el-button {
    --el-button-text-color: #c4b5fd;
    --el-button-bg-color: rgba(139, 92, 246, 0.2); // 紫色主题按钮
    --el-button-border-color: #c4b5fd;
    --el-button-hover-bg-color: rgba(139, 92, 246, 0.4);
    --el-button-hover-border-color: #c4b5fd;
    --el-button-active-bg-color: rgba(139, 92, 246, 0.5);
    --el-button-active-border-color: #c4b5fd;
    border-radius: 4px;
    font-size: 0.85vw;
    padding: 0.6vh 1.2vw;
  }
}

.main-content {
  display: flex;
  flex-direction: column;
  gap: 1.2vw;
}

.stats-cards {
  display: flex;
  gap: 1.2vw;
  flex-wrap: wrap;
}

.stat-card {
  flex: 1;
  background: $bg-card;
  border-radius: 8px;
  padding: 1.2vw;
  border: 1px solid $border-color;
  box-shadow: $shadow;
  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-3px);
    box-shadow: 0 0 20px rgba(139, 92, 246, 0.25); // 紫色阴影
    border-color: #c4b5fd;
  }

  .stat-title {
    font-size: 1.2vw !important;
    color: #a7f3d0;
    margin-bottom: 0.8vw;
    display: flex;
    align-items: center;

    &::before {
      content: '';
      display: inline-block;
      width: 0.7vw;
      height: 1.4vh;
      background: $primary-color; // 紫色指标图标
      margin-right: 0.8vw;
      border-radius: 50%;
    }
  }

  .stat-value {
    font-size: 1.6vw !important;
    font-weight: 700;
    margin-bottom: 1vh;
    color: $secondary-color;
    line-height: 1.2;

    .unit {
      font-size: 1.1vw !important;
      margin-left: 0.4vw;
      color: $text-tertiary;
    }

    &.excellent {
      color: $secondary-color;
      text-shadow: 0 0 8px rgba(19, 206, 102, 0.3);
    }

    &.good {
      color: $primary-color;
      text-shadow: 0 0 8px rgba(139, 92, 246, 0.3);
    }

    &.average {
      color: $warning-color;
      text-shadow: 0 0 8px rgba(255, 125, 0, 0.3);
    }

    &.poor {
      color: $danger-color;
      text-shadow: 0 0 8px rgba(255, 73, 73, 0.3);
    }

    &.normal {
      color: #8cd5ff;
      text-shadow: 0 0 8px rgba(0, 204, 255, 0.3);
    }
  }

  .stat-desc {
    font-size: 0.8vw !important;
    color: $text-tertiary;
    line-height: 1.4;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    display: flex;
    gap: 1vw;
    flex-wrap: wrap;
  }

  .compare-item {
    font-size: 0.8vw !important;
    padding: 0.2vw 0.4vw;
    border-radius: 4px;
    font-weight: 500;
    color: inherit;
  }

  .compare-item.increase {
    color: $secondary-color;
    background: rgba(19, 206, 102, 0.2);
  }

  .compare-item.decrease {
    color: $danger-color;
    background: rgba(255, 73, 73, 0.2);
  }

  .level-item {
    padding: 0.2vw 0.4vw;
    border-radius: 4px;
    font-size: 0.8vw !important;
  }

  .level-item.excellent {
    color: $secondary-color;
    background: rgba(19, 206, 102, 0.2);
  }

  .level-item.good {
    color: $primary-color;
    background: rgba(139, 92, 246, 0.2);
  }

  .level-item.average {
    color: $warning-color;
    background: rgba(255, 125, 0, 0.2);
  }

  .level-item.poor {
    color: $danger-color;
    background: rgba(255, 73, 73, 0.2);
  }
}

.panel {
  position: relative;
  border: 1px solid $border-color;
  background: $bg-panel;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  min-height: 0;
  box-shadow: $shadow;
  overflow: hidden;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 3px;
    background: linear-gradient(90deg, $primary-color, rgba(139, 92, 246, 0.3)); // 紫色渐变
  }

  .panel-header {
    padding: 1vh 1.2vw;
    border-bottom: 1px solid $border-light;
    display: flex;
    justify-content: space-between;
    align-items: center;

    h2 {
      margin: 0;
      font-size: 1.1vw;
      color: #c4b5fd; // 紫色标题
      font-weight: 600;
      display: flex;
      align-items: center;
    }
  }

  .panel-body {
    flex: 1;
    padding: 1.2vw;
    overflow: hidden;
  }

  .panel-footer {
    height: 1vh;
    border-top: 1px solid $border-light;
  }
}

.chart-row {
  display: flex;
  gap: 1.2vw;
  height: 28vh;

  .trend-panel, .level-bar-panel {
    flex: 1;
  }
}

.data-table-panel {
  max-height: 40vh;

  .panel-header {
    .header-actions {
      display: flex;
      gap: 0.8vw;
      align-items: center;
    }

    .el-select, .el-input {
      --el-select-text-color: $text-secondary;
      --el-select-placeholder-color: $text-tertiary;
      --el-select-dropdown-bg-color: $bg-card;
      --el-select-dropdown-border-color: $border-color;
      --el-select-dropdown-item-hover-bg-color: rgba(139, 92, 246, 0.2); // 紫色hover
      --el-select-dropdown-item-text-color: $text-secondary;
      --el-input-bg-color: rgba(16, 32, 64, 0.5);
      --el-input-border-color: $border-color;
      --el-input-text-color: $text-secondary;
      --el-input-placeholder-color: $text-tertiary;
      font-size: 0.7vw;
      width: 8vw;

      &:last-of-type {
        width: 30vw;
        max-width: 40vw;
      }
    }

    .el-button--primary {
      --el-button-text-color: #fff;
      --el-button-bg-color: #8b5cf6; // 紫色主按钮
      --el-button-border-color: #8b5cf6;
      --el-button-hover-bg-color: #7c3aed;
      --el-button-hover-border-color: #7c3aed;
      --el-button-active-bg-color: #6d28d9;
      --el-button-active-border-color: #6d28d9;
    }
  }

  .table-scroll {
    overflow-y: auto;

    &::-webkit-scrollbar {
      width: 6px;
      height: 6px;
    }

    &::-webkit-scrollbar-track {
      background: rgba(16, 32, 64, 0.5);
      border-radius: 3px;
    }

    &::-webkit-scrollbar-thumb {
      background: $primary-color; // 紫色滚动条
      border-radius: 3px;
    }

    &::-webkit-scrollbar-thumb:hover {
      background: #7c3aed;
    }
  }

  ::v-deep .el-table {
    --el-table-bg-color: transparent;
    --el-table-text-color: $text-secondary;
    --el-table-border-color: $border-light;
    --el-table-header-text-color: #c4b5fd; // 紫色表头
    --el-table-header-bg-color: rgba(16, 32, 64, 0.8);
    --el-table-row-hover-bg-color: rgba(139, 92, 246, 0.1);
    --el-table-row-stripes-bg-color: rgba(16, 32, 64, 0.3);
    border-radius: 4px;
    overflow: hidden;

    .el-table__header {
      border-bottom: 1px solid $border-color;
    }

    .el-table__cell {
      border-bottom: 1px solid $border-light;
      padding: 1vh 0.8vw !important;
    }

    .el-tag {
      font-size: 0.75vw;
      padding: 0.2vh 0.6vw;
      border-radius: 12px;
    }
  }
}

// 复用表格全局样式
::v-deep .el-table {
  table-layout: fixed;
  background-color: rgba(0, 30, 60, 0.4);
  border-color: rgba(139, 92, 246, 0.2);

  th {
    background-color: rgba(0, 40, 80, 0.8);
    color: #c4b5fd; // 紫色表头文字
    font-size: 0.8vw;
    padding: 0.5vw 0;
    text-align: center;
    border-color: rgba(139, 92, 246, 0.3);
    font-weight: 500;
  }

  td {
    background-color: transparent;
    color: #b3d9ff;
    font-size: 0.7vw;
    border-bottom: 1px solid rgba(139, 92, 246, 0.15);
    padding: 0.4vw 0;
    text-align: center;
    transition: all 0.2s;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .el-table__row {
    background-color: rgba(0, 30, 60, 0.5);
    &:nth-child(even) {
      background-color: rgba(0, 40, 80, 0.5);
    }
    &:hover > td {
      background-color: rgba(139, 92, 246, 0.3) !important; // 紫色hover
      color: #ffffff;
    }
  }

  .el-table__row--current > td {
    background-color: rgba(139, 92, 246, 0.4) !important;
    color: #ffffff;
  }

  .el-table__empty-text {
    color: #c4b5fd;
    background-color: rgba(0, 30, 60, 0.4);
  }

  &::before {
    background-color: rgba(139, 92, 246, 0.2);
  }

  .el-table__fixed-right::before,
  .el-table__fixed::before {
    background-color: transparent;
  }
}

// 评价内容卡片样式
.content-card {
  background: rgba(211, 226, 241, 0.8);
  border: 1px solid $border-light;
  border-radius: 8px;
  padding: 12px;
  color: $text-secondary;
  line-height: 1.6;
  font-size: 0.9vw;
  min-height: 80px;
  white-space: pre-line;
}

// 详情弹窗样式
.evaluation-detail {
  .detail-section {
    margin-bottom: 24px;

    h3 {
      font-size: 16px;
      color: #c4b5fd; // 紫色标题
      margin-bottom: 12px;
      padding-bottom: 6px;
      border-bottom: 1px solid $border-color;
      font-weight: 600;
    }
  }

  .el-descriptions {
    --el-descriptions-bg-color: transparent;
    --el-descriptions-border-color: $border-light;
    --el-descriptions-title-color: $text-secondary;
    --el-descriptions-content-color: $text-secondary;
    font-size: 0.9vw;

    .el-descriptions__label {
      color: $text-secondary;
      font-weight: 500;
      width: 110px !important;
      text-align: left !important;
    }

    .el-descriptions__content {
      color: $text-secondary;
      padding: 10px 0;
    }

    .el-descriptions__item {
      border-bottom: 1px solid $border-light;

      &:last-child {
        border-bottom: none;
      }
    }
  }
}

// 弹窗样式优化（紫色主题）
.el-dialog {
  --el-dialog-bg-color: $bg-panel;
  --el-dialog-border-color: $border-color;
  --el-dialog-title-color: #c4b5fd;
  --el-dialog-text-color: $text-secondary;
  --el-dialog-header-border-color: $border-light;
  --el-dialog-footer-border-color: $border-light;
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(139, 92, 246, 0.2);

  .el-dialog__title {
    font-size: 18px;
    font-weight: 600;
  }

  .el-dialog__header {
    padding: 16px 20px;
    border-bottom: 1px solid $border-light;
  }

  .el-dialog__body {
    padding: 20px;
    color: $text-secondary;
  }

  .el-dialog__footer {
    padding: 12px 20px;
    border-top: 1px solid $border-light;
  }
}
</style>
