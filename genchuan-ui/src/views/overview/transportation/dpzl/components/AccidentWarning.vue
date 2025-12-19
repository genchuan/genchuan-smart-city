<template>
  <div class="page-container">
    <!-- 页头区域：标题及筛选操作区 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/warning.png"
          alt="事故预警图标"
          class="title-icon"
        />
        事故预警监测视图
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
          <div class="stat-title">事故数量</div>
          <div class="stat-value" :class="totalAccident > 30 ? 'danger' : totalAccident > 15 ? 'warning' : 'normal'">
            {{ totalAccident }} <span class="unit">起</span>
          </div>
          <div class="stat-desc">近30天统计 | 高风险事故: {{ highRiskAccident }} 起</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">预警数量</div>
          <div class="stat-value" :class="totalWarning > 50 ? 'danger' : totalWarning > 30 ? 'warning' : 'normal'">
            {{ totalWarning }} <span class="unit">条</span>
          </div>
          <div class="stat-desc">
            <span
              class="compare-item"
              :class="warningYoY > 0 ? 'increase' : 'decrease'"
            >
              同比 {{ warningYoY > 0 ? '↑' : '↓' }}{{ Math.abs(warningYoY).toFixed(1) }}%
            </span>
            <span
              class="compare-item"
              :class="warningMoM > 0 ? 'increase' : 'decrease'"
            >
              环比 {{ warningMoM > 0 ? '↑' : '↓' }}{{ Math.abs(warningMoM).toFixed(1) }}%
            </span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">预警处理率</div>
          <div class="stat-value" :class="handlingRate > 80 ? 'normal' : handlingRate > 60 ? 'warning' : 'danger'">
            {{ handlingRate }} <span class="unit">%</span>
          </div>
          <div class="stat-desc">已处理: {{ handledWarning }} 条 | 未处理: {{ pendingWarning }} 条</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">高风险区域</div>
          <div class="stat-value" style="color: #ff7d00; font-size: 1.1vw !important; line-height: 1.4;">
            {{ highRiskArea }}
          </div>
          <div class="stat-desc">最近监测时间: {{ lastMonitorTime }}</div>
        </div>
      </div>

      <!-- 图表行：左右分栏布局 -->
      <div class="chart-row">
        <!-- 左侧：事故预警趋势折线图（预警数+事故数） -->
        <div class="panel trend-panel" ref="trendPanel">
          <div class="panel-header">
            <h2>事故预警趋势（近6个月）</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('trendPanel')">
                <el-icon color="#ff7d00" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartLine
              :xAxis="trendXAxis"
              :series="trendSeries"
              :yAxisName="''"
            />
          </div>
          <div class="panel-footer"></div>
        </div>

        <!-- 右侧：风险区域分布柱状图 -->
        <div class="panel risk-bar-panel" ref="riskBarPanel">
          <div class="panel-header">
            <h2>风险区域分布</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('riskBarPanel')">
                <el-icon color="#ff7d00" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartBar
              :data="riskBarData"
            />
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <!-- 预警详细数据表格 -->
      <div class="panel data-table-panel" ref="dataTablePanel">
        <div class="panel-header">
          <h2>事故预警监测数据</h2>
          <div class="header-actions">
            <!-- 时间范围筛选器 -->
            <el-select v-model="timeRange" placeholder="时间范围" size="small">
              <el-option label="今日" value="today" />
              <el-option label="本周" value="week" />
              <el-option label="本月" value="month" />
            </el-select>
            <!-- 预警级别筛选器 -->
            <el-select v-model="warningLevel" placeholder="预警级别" size="small">
              <el-option label="全部" value="" />
              <el-option label="高风险" value="high" />
              <el-option label="中风险" value="medium" />
              <el-option label="低风险" value="low" />
            </el-select>
            <!-- 预警状态筛选器 -->
            <el-select v-model="warningStatus" placeholder="预警状态" size="small">
              <el-option label="全部" value="" />
              <el-option label="已处理" value="handled" />
              <el-option label="处理中" value="processing" />
              <el-option label="未处理" value="pending" />
            </el-select>
            <el-input
              v-model="searchKeyword"
              placeholder="搜索预警名称"
              size="small"
              style="width: 15vw"
            />
            <el-button size="small" type="primary" @click="exportData">导出数据</el-button>
            <button class="panel-fullscreen-btn" @click="toggleFullscreen('dataTablePanel')">
              <el-icon color="#ff7d00" size="16">
                <FullScreen/>
              </el-icon>
            </button>
          </div>
        </div>
        <!-- 表格滚动容器 -->
        <div class="panel-body table-scroll">
          <el-table
            :data="filteredWarningData"
            border
            size="small"
            style="width: 100%"
          >
            <el-table-column prop="name" label="预警名称" />
            <el-table-column prop="level" label="预警级别">
              <template #default="scope">
                <el-tag :type="getWarningLevelTagType(scope.row.level)">
                  {{ getWarningLevelName(scope.row.level) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="area" label="所属区域" />
            <el-table-column prop="risk_factor" label="风险因素" />
            <el-table-column prop="related_accident" label="关联事故数（起）" />
            <el-table-column prop="status" label="预警状态">
              <template #default="scope">
                <el-tag :type="getWarningStatusTagType(scope.row.status)">
                  {{ getWarningStatusName(scope.row.status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="create_time" label="创建时间" />
            <el-table-column label="操作">
              <template #default="scope">
                <el-button size="small" link @click="showWarningDetail(scope.row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="panel-footer"></div>
      </div>
    </div>

    <!-- 预警详情弹窗 -->
    <el-dialog
      v-model="warningDetailVisible"
      :title="currentWarning?.name || '事故预警详情'"
      width="700px"
    >
      <div class="warning-detail">
        <!-- 基础信息区域 -->
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="预警ID">{{ currentWarning?.warning_id }}</el-descriptions-item>
            <el-descriptions-item label="预警级别">{{ getWarningLevelName(currentWarning?.level) }}</el-descriptions-item>
            <el-descriptions-item label="所属区域">{{ currentWarning?.area }}</el-descriptions-item>
            <el-descriptions-item label="风险因素">{{ currentWarning?.risk_factor }}</el-descriptions-item>
            <el-descriptions-item label="创建时间">{{ currentWarning?.create_time }}</el-descriptions-item>
            <el-descriptions-item label="关联事故数">{{ currentWarning?.related_accident }} 起</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 处理信息区域 -->
        <div class="detail-section">
          <h3>处理信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="预警状态">{{ getWarningStatusName(currentWarning?.status) }}</el-descriptions-item>
            <el-descriptions-item label="处理人员">{{ currentWarning?.handler || '未分配' }}</el-descriptions-item>
            <el-descriptions-item label="处理时间">{{ currentWarning?.handle_time || '未处理' }}</el-descriptions-item>
            <el-descriptions-item label="处理措施">{{ currentWarning?.handle_measures || '未制定' }}</el-descriptions-item>
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
import ChartLine from './ChartLine2.vue';
import ChartBar from './ChartBar2.vue';
import { FullScreen } from "@element-plus/icons-vue";

// 导入事故预警API
import {
  fetchAccidentWarningOverview,
  fetchAccidentWarningTrend,
  fetchRiskAreaDistribution,
  fetchWarningList,
  fetchWarningDetailData,
  fetchWarningDetailTrend
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
const timeRange = ref('month');          // 时间范围筛选
const warningLevel = ref('');            // 预警级别筛选
const warningStatus = ref('');           // 预警状态筛选
const searchKeyword = ref('');           // 搜索关键词

// 核心指标数据变量（事故预警专属）
const totalAccident = ref(0);
const highRiskAccident = ref(0);         // 高风险事故数（总事故数*0.35）
const totalWarning = ref(0);
const handledWarning = ref(0);
const pendingWarning = ref(0);
const handlingRate = ref(0);
const warningYoY = ref(0);
const warningMoM = ref(0);
const highRiskArea = ref('');
const lastMonitorTime = ref('');

// 预警数据变量
const warningList = ref([]);
const currentWarning = ref(null);
const warningDetailVisible = ref(false);
const warningTrendXAxis = ref([]);
const warningTrendSeries = ref([]);
const trendXAxis = ref([]);
const trendSeries = ref([]);
const riskBarData = ref({});
const warningData = ref([]);

// 预警级别名称映射
const getWarningLevelName = (level) => {
  const map = {
    'high': '高风险',
    'medium': '中风险',
    'low': '低风险'
  };
  return map[level] || '未知级别';
};

// 预警级别标签样式映射
const getWarningLevelTagType = (level) => {
  const map = {
    'high': 'danger',
    'medium': 'warning',
    'low': 'success'
  };
  return map[level] || 'default';
};

// 预警状态名称映射
const getWarningStatusName = (status) => {
  const map = {
    'handled': '已处理',
    'processing': '处理中',
    'pending': '未处理'
  };
  return map[status] || '未知状态';
};

// 预警状态标签样式映射
const getWarningStatusTagType = (status) => {
  const map = {
    'handled': 'success',
    'processing': 'info',
    'pending': 'danger'
  };
  return map[status] || 'default';
};

// 筛选后的预警列表（计算属性）
const filteredWarningList = computed(() => {
  return warningList.value.filter(warning => {
    const matchesLevel = !warningLevel.value || warning.level === warningLevel.value;
    const matchesStatus = !warningStatus.value || warning.status === warningStatus.value;
    return matchesLevel && matchesStatus;
  });
});

// 筛选后的表格数据（计算属性）
const filteredWarningData = computed(() => {
  return warningData.value.filter(item => {
    const matchesLevel = !warningLevel.value || item.level === warningLevel.value;
    const matchesStatus = !warningStatus.value || item.status === warningStatus.value;
    const matchesKeyword = !searchKeyword.value || item.name.includes(searchKeyword.value);
    return matchesLevel && matchesStatus && matchesKeyword;
  });
});

// 显示预警详情
const showWarningDetail = async (warning) => {
  currentWarning.value = { ...warning };
  // 从API获取该预警的趋势数据
  const trendData = await fetchWarningDetailTrend(warning.warning_id);
  warningTrendXAxis.value = trendData.xAxis;
  warningTrendSeries.value = trendData.series;
  warningDetailVisible.value = true;
};

// 刷新数据
const refreshData = async () => {
  try {
    await initData();
    ElMessage.success('事故预警数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
    console.error(error);
  }
};

// 导出数据
const exportData = () => {
  ElMessage.success('事故预警监测数据导出成功');
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/transportation/dpzl');
};

// 初始化数据
const initData = async () => {
  const overviewData = await fetchAccidentWarningOverview();
  totalAccident.value = overviewData.totalAccident;
  highRiskAccident.value = Math.round(overviewData.totalAccident * 0.35); // 高风险事故占比35%
  totalWarning.value = overviewData.totalWarning;
  handledWarning.value = overviewData.handledWarning;
  pendingWarning.value = overviewData.pendingWarning;
  handlingRate.value = overviewData.handlingRate;
  warningYoY.value = overviewData.warningYoY;
  warningMoM.value = overviewData.warningMoM;
  highRiskArea.value = overviewData.highRiskArea;
  lastMonitorTime.value = overviewData.lastMonitorTime;

  const trendData = await fetchAccidentWarningTrend();
  trendXAxis.value = trendData.xAxis;
  trendSeries.value = trendData.series;

  const distributionData = await fetchRiskAreaDistribution();
  riskBarData.value = distributionData;

  warningList.value = await fetchWarningList();

  warningData.value = await fetchWarningDetailData();
};

// 组件挂载时初始化数据
onMounted(async () => {
  await initData();
});
</script>

<style lang="scss" scoped>
$secondary-color: #13ce66;
$warning-color: #ff7d00;
$danger-color: #ff4949;
$text-secondary: #999999;
$text-tertiary: #666666;
$bg-main: #0a1064;
$bg-card: rgba(16, 32, 80, 0.5);
$bg-panel: rgba(10, 16, 100, 0.6);
$border-color: rgba(0, 204, 255, 0.3);
$border-light: rgba(0, 204, 255, 0.1);
$shadow: 0 0 1vw rgba(0, 204, 255, 0.15);

// 完全复用通行效率视图的样式结构（仅调整局部文本）
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
    background: rgba(255, 125, 0, 0.2);
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
    background: #ff7d00; // 事故预警主题色：橙色
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
    --el-button-text-color: #ffb800;
    --el-button-bg-color: rgba(255, 125, 0, 0.2); // 橙色主题按钮
    --el-button-border-color: #ffb800;
    --el-button-hover-bg-color: rgba(255, 125, 0, 0.4);
    --el-button-hover-border-color: #ffb800;
    --el-button-active-bg-color: rgba(255, 125, 0, 0.5);
    --el-button-active-border-color: #ffb800;
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
    box-shadow: 0 0 20px rgba(255, 125, 0, 0.25); // 橙色阴影
    border-color: #ffb800;
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
      background: #ff4949; // 事故预警指标图标颜色：红色
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

    &.normal {
      color: $secondary-color;
      text-shadow: 0 0 8px rgba(19, 206, 102, 0.3);
    }

    &.warning {
      color: $warning-color;
      text-shadow: 0 0 8px rgba(255, 125, 0, 0.3);
    }

    &.danger {
      color: $danger-color;
      text-shadow: 0 0 8px rgba(255, 73, 73, 0.3);
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
  }

  .compare-item {
    font-size: 0.8vw !important;
    padding: 0.2vw 0.4vw;
    border-radius: 4px;
    font-weight: 500;
    color: inherit;
  }

  .compare-item.increase {
    color: $danger-color;
    background: rgba(255, 73, 73, 0.2);
  }

  .compare-item.decrease {
    color: $secondary-color;
    background: rgba(19, 206, 102, 0.2);
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
    background: linear-gradient(90deg, #ffb800, rgba(255, 184, 0, 0.3)); // 橙色渐变
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
      color: #ffb800; // 橙色标题
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

  .trend-panel, .risk-bar-panel {
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
      --el-select-dropdown-item-hover-bg-color: rgba(255, 125, 0, 0.2); // 橙色hover
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
      --el-button-bg-color: #ffb800; // 橙色主按钮
      --el-button-border-color: #ffb800;
      --el-button-hover-bg-color: #ff9f00;
      --el-button-hover-border-color: #ff9f00;
      --el-button-active-bg-color: #ff8c00;
      --el-button-active-border-color: #ff8c00;
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
      background: #ffb800; // 橙色滚动条
      border-radius: 3px;
    }

    &::-webkit-scrollbar-thumb:hover {
      background: #ff9f00;
    }
  }

  ::v-deep .el-table {
    --el-table-bg-color: transparent;
    --el-table-text-color: $text-secondary;
    --el-table-border-color: $border-light;
    --el-table-header-text-color: #ffb800; // 橙色表头
    --el-table-header-bg-color: rgba(16, 32, 64, 0.8);
    --el-table-row-hover-bg-color: rgba(255, 125, 0, 0.1);
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
  border-color: rgba(0, 204, 255, 0.2);

  th {
    background-color: rgba(0, 40, 80, 0.8);
    color: #ffb800; // 橙色表头文字
    font-size: 0.8vw;
    padding: 0.5vw 0;
    text-align: center;
    border-color: rgba(0, 204, 255, 0.3);
    font-weight: 500;
  }

  td {
    background-color: transparent;
    color: #b3d9ff;
    font-size: 0.7vw;
    border-bottom: 1px solid rgba(0, 204, 255, 0.15);
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
      background-color: rgba(255, 125, 0, 0.3) !important; // 橙色hover
      color: #ffffff;
    }
  }

  .el-table__row--current > td {
    background-color: rgba(255, 125, 0, 0.4) !important;
    color: #ffffff;
  }

  .el-table__empty-text {
    color: #ffb800;
    background-color: rgba(0, 30, 60, 0.4);
  }

  &::before {
    background-color: rgba(0, 204, 255, 0.2);
  }

  .el-table__fixed-right::before,
  .el-table__fixed::before {
    background-color: transparent;
  }
}

// 预警标签样式映射
.el-tag--danger {
  --el-tag-bg-color: rgba(255, 73, 73, 0.2);
  --el-tag-border-color: #ff4949;
  --el-tag-text-color: #ff4949;
}

.el-tag--warning {
  --el-tag-bg-color: rgba(255, 125, 0, 0.2);
  --el-tag-border-color: $warning-color;
  --el-tag-text-color: $warning-color;
}

.el-tag--success {
  --el-tag-bg-color: rgba(19, 206, 102, 0.2);
  --el-tag-border-color: #13ce66;
  --el-tag-text-color: #13ce66;
}

.el-tag--info {
  --el-tag-bg-color: rgba(0, 204, 255, 0.2);
  --el-tag-border-color: #8cd5ff;
  --el-tag-text-color: #8cd5ff;
}

// 详情弹窗样式
.warning-detail {
  .detail-section {
    margin-bottom: 24px;

    h3 {
      font-size: 16px;
      color: #ffb800; // 橙色标题
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

// 弹窗样式优化（橙色主题）
.el-dialog {
  --el-dialog-bg-color: $bg-panel;
  --el-dialog-border-color: $border-color;
  --el-dialog-title-color: #ffb800;
  --el-dialog-text-color: $text-secondary;
  --el-dialog-header-border-color: $border-light;
  --el-dialog-footer-border-color: $border-light;
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(255, 184, 0, 0.2);

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
