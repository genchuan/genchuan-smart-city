<template>
  <div class="page-container">
    <!-- 页头区域：标题及筛选操作区 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/disposalefficiency.png"
          alt="应急处置图标"
          class="title-icon"
        />
        应急处置效率视图
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
          <div class="stat-title">平均处置时长</div>
          <div class="stat-value" :class="averageDisposalTime <= 25 ? 'excellent' : averageDisposalTime <= 30 ? 'good' : 'poor'">
            {{ averageDisposalTime }} <span class="unit">分钟</span>
          </div>
          <div class="stat-desc">
            <span
              class="compare-item"
              :class="efficiencyYoY < 0 ? 'increase' : 'decrease'"
            >
              同比 {{ efficiencyYoY < 0 ? '↓' : '↑' }}{{ Math.abs(efficiencyYoY).toFixed(1) }}%
            </span>
            <span
              class="compare-item"
              :class="efficiencyMoM < 0 ? 'increase' : 'decrease'"
            >
              环比 {{ efficiencyMoM < 0 ? '↓' : '↑' }}{{ Math.abs(efficiencyMoM).toFixed(1) }}%
            </span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">总处置事件数</div>
          <div class="stat-value normal">
            {{ totalEvents }} <span class="unit">件</span>
          </div>
          <div class="stat-desc">近30天统计 | 有效上报率: 99.2%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">核心完成指标</div>
          <div class="stat-value" style="font-size: 1.1vw !important; line-height: 1.4;">
            <span class="level-item excellent">完成率: {{ completionRate }}%</span>
            <span class="level-item good">及时率: {{ timelyRate }}%</span>
          </div>
          <div class="stat-desc">
            <span class="level-item average">未完成: {{ unfinishedEvents }}件</span>
            <span class="level-item poor">超时: {{ overtimeEvents }}件</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">应急响应时间</div>
          <!-- 响应时间数值，根据阈值显示不同颜色 -->
          <div class="stat-value" :class="avgResponseTime <= 5 ? 'excellent' : avgResponseTime <= 10 ? 'good' : 'poor'">
            {{ avgResponseTime }} <span class="unit">分钟</span>
          </div>
          <!-- 响应相关指标描述 -->
          <div class="stat-desc">
            <span class="level-item excellent">达标率: {{ responseRate }}%</span>
            <span class="level-item poor">慢响应: {{ slowResponseCount }}件</span>
          </div>
        </div>
      </div>

      <!-- 图表行：左右分栏布局 -->
      <div class="chart-row">
        <!-- 左侧：处置效率趋势折线图（时长+完成率） -->
        <div class="panel trend-panel" ref="trendPanel">
          <div class="panel-header">
            <h2>处置效率趋势（近6个月）</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('trendPanel')">
                <el-icon color="#ff6b6b" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartLine4
              :xAxis="trendXAxis"
              :series="trendSeries"
              :yAxisName="''"
            />
          </div>
          <div class="panel-footer"></div>
        </div>

        <!-- 右侧：处置类型分布柱状图 -->
        <div class="panel type-bar-panel" ref="typeBarPanel">
          <div class="panel-header">
            <h2>处置事件类型分布</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('typeBarPanel')">
                <el-icon color="#ff6b6b" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartBar4
              :data="typeBarData"
            />
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <!-- 处置事件详细数据表格 -->
      <div class="panel data-table-panel" ref="dataTablePanel">
        <div class="panel-header">
          <h2>应急处置事件数据</h2>
          <div class="header-actions">
            <!-- 时间范围筛选器 -->
            <el-select v-model="timeRange" placeholder="时间范围" size="small">
              <el-option label="今日" value="today" />
              <el-option label="本周" value="week" />
              <el-option label="本月" value="month" />
            </el-select>
            <!-- 事件类型筛选器 -->
            <el-select v-model="eventType" placeholder="事件类型" size="small">
              <el-option label="全部" value="" />
              <el-option label="交通事故" value="traffic_accident" />
              <el-option label="设备故障" value="equipment_failure" />
              <el-option label="道路施工" value="road_construction" />
              <el-option label="自然灾害" value="natural_disaster" />
              <el-option label="其他事件" value="other" />
            </el-select>
            <!-- 处置状态筛选器 -->
            <el-select v-model="eventStatus" placeholder="处置状态" size="small">
              <el-option label="全部" value="" />
              <el-option label="已完成" value="completed" />
              <el-option label="处置中" value="processing" />
              <el-option label="待处置" value="pending" />
              <el-option label="超时未处置" value="overtime" />
            </el-select>
            <el-input
              v-model="searchKeyword"
              placeholder="搜索事件名称/区域"
              size="small"
              style="width: 15vw"
            />
            <el-button size="small" type="primary" @click="exportData">导出数据</el-button>
            <button class="panel-fullscreen-btn" @click="toggleFullscreen('dataTablePanel')">
              <el-icon color="#ff6b6b" size="16">
                <FullScreen/>
              </el-icon>
            </button>
          </div>
        </div>
        <!-- 表格滚动容器 -->
        <div class="panel-body table-scroll">
          <el-table
            :data="filteredEventData"
            border
            size="small"
            style="width: 100%"
          >
            <el-table-column prop="name" label="事件名称" />
            <el-table-column prop="type" label="事件类型">
              <template #default="scope">
                <el-tag :type="getEventTypeTagType(scope.row.type)">
                  {{ getEventTypeName(scope.row.type) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="level" label="事件等级">
              <template #default="scope">
                <el-tag :type="getEventLevelTagType(scope.row.level)">
                  {{ getEventLevelName(scope.row.level) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="area" label="所属区域" />
            <el-table-column prop="disposal_time" label="处置时长（分钟）" />
            <el-table-column prop="create_time" label="上报时间" />
            <el-table-column prop="status" label="处置状态">
              <template #default="scope">
                <el-tag :type="getEventStatusTagType(scope.row.status)">
                  {{ getEventStatusName(scope.row.status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <el-button size="small" link @click="showEventDetail(scope.row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="panel-footer"></div>
      </div>
    </div>

    <!-- 事件详情弹窗 -->
    <el-dialog
      v-model="eventDetailVisible"
      :title="currentEvent?.name || '应急处置事件详情'"
      width="700px"
    >
      <div class="event-detail">
        <!-- 基础信息区域 -->
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="事件ID">{{ currentEvent?.event_id }}</el-descriptions-item>
            <el-descriptions-item label="事件类型">{{ getEventTypeName(currentEvent?.type) }}</el-descriptions-item>
            <el-descriptions-item label="事件等级">{{ getEventLevelName(currentEvent?.level) }}</el-descriptions-item>
            <el-descriptions-item label="所属区域">{{ currentEvent?.area }}</el-descriptions-item>
            <el-descriptions-item label="上报时间">{{ currentEvent?.create_time }}</el-descriptions-item>
            <el-descriptions-item label="响应时间">{{ currentEvent?.response_time || '0' }} 分钟</el-descriptions-item>
            <el-descriptions-item label="处置时长">{{ currentEvent?.disposal_time || '0' }} 分钟</el-descriptions-item>
            <el-descriptions-item label="预计时长">{{ currentEvent?.expected_time }} 分钟</el-descriptions-item>
            <el-descriptions-item label="处置状态">{{ getEventStatusName(currentEvent?.status) }}</el-descriptions-item>
            <el-descriptions-item label="处置团队">{{ currentEvent?.handler_team }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 事件描述区域 -->
        <div class="detail-section">
          <h3>事件描述</h3>
          <div class="content-card">
            {{ currentEvent?.description || '无详细描述' }}
          </div>
        </div>

        <!-- 处置结果区域 -->
        <div class="detail-section">
          <h3>处置结果与反馈</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="处置结果">{{ currentEvent?.result || '未处置' }}</el-descriptions-item>
            <el-descriptions-item label="反馈评价">{{ currentEvent?.feedback || '无反馈' }}</el-descriptions-item>
            <el-descriptions-item label="处置人员">{{ currentEvent?.handler || '未分配' }}</el-descriptions-item>
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
import ChartLine4 from './ChartLine4.vue';
import ChartBar4 from './ChartBar4.vue';
import { FullScreen } from "@element-plus/icons-vue";

// 导入应急处置API
import {
  fetchDisposalOverview,
  fetchDisposalTrend,
  fetchDisposalTypeDistribution,
  fetchDisposalEventList,
  fetchDisposalEventDetailData,
  fetchEventDisposalTrend
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
const eventType = ref('');                 // 事件类型筛选
const eventStatus = ref('');               // 处置状态筛选
const searchKeyword = ref('');             // 搜索关键词

// 核心指标数据变量（应急处置专属）
const totalEvents = ref(0);
const averageDisposalTime = ref(0);
const completionRate = ref(0);
const timelyRate = ref(0);
const unfinishedEvents = ref(0);
const overtimeEvents = ref(0);
const efficiencyYoY = ref(0);
const efficiencyMoM = ref(0);
const avgResponseTime = ref(0);
const responseRate = ref(0);
const slowResponseCount = ref(0);

// 事件数据变量
const eventList = ref([]);
const currentEvent = ref(null);
const eventDetailVisible = ref(false);
const eventTrendXAxis = ref([]);
const eventTrendSeries = ref([]);
const trendXAxis = ref([]);
const trendSeries = ref([]);
const typeBarData = ref({});
const eventData = ref([]);

// 事件类型名称映射
const getEventTypeName = (type) => {
  const map = {
    'traffic_accident': '交通事故',
    'equipment_failure': '设备故障',
    'road_construction': '道路施工',
    'natural_disaster': '自然灾害',
    'other': '其他事件'
  };
  return map[type] || '未知类型';
};

// 事件类型标签样式映射
const getEventTypeTagType = (type) => {
  const map = {
    'traffic_accident': 'danger',
    'equipment_failure': 'warning',
    'road_construction': 'primary',
    'natural_disaster': 'error',
    'other': 'default'
  };
  return map[type] || 'default';
};

// 事件等级名称映射
const getEventLevelName = (level) => {
  const map = {
    'low': '低级别',
    'medium': '中级别',
    'high': '高级别'
  };
  return map[level] || '未知等级';
};

// 事件等级标签样式映射
const getEventLevelTagType = (level) => {
  const map = {
    'low': 'success',
    'medium': 'warning',
    'high': 'danger'
  };
  return map[level] || 'default';
};

// 处置状态名称映射
const getEventStatusName = (status) => {
  const map = {
    'completed': '已完成',
    'processing': '处置中',
    'pending': '待处置',
    'overtime': '超时未处置'
  };
  return map[status] || '未知状态';
};

// 处置状态标签样式映射
const getEventStatusTagType = (status) => {
  const map = {
    'completed': 'success',
    'processing': 'info',
    'pending': 'warning',
    'overtime': 'danger'
  };
  return map[status] || 'default';
};

// 筛选后的事件列表（计算属性）
const filteredEventList = computed(() => {
  return eventList.value.filter(event => {
    const matchesType = !eventType.value || event.type === eventType.value;
    const matchesStatus = !eventStatus.value || event.status === eventStatus.value;
    return matchesType && matchesStatus;
  });
});

// 筛选后的表格数据（计算属性）
const filteredEventData = computed(() => {
  return eventData.value.filter(item => {
    const matchesType = !eventType.value || item.type === eventType.value;
    const matchesStatus = !eventStatus.value || item.status === eventStatus.value;
    const matchesKeyword = !searchKeyword.value ||
      item.name.includes(searchKeyword.value) ||
      item.area.includes(searchKeyword.value);
    return matchesType && matchesStatus && matchesKeyword;
  });
});

// 显示事件详情
const showEventDetail = async (event) => {
  currentEvent.value = { ...event };
  // 从API获取该类型事件的趋势数据
  const trendData = await fetchEventDisposalTrend(event.event_id);
  eventTrendXAxis.value = trendData.xAxis;
  eventTrendSeries.value = trendData.series;
  eventDetailVisible.value = true;
};

// 刷新数据
const refreshData = async () => {
  try {
    await initData();
    ElMessage.success('应急处置数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
    console.error(error);
  }
};

// 导出数据
const exportData = () => {
  ElMessage.success('应急处置事件数据导出成功');
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/transportation/dpzl');
};

// 初始化数据
const initData = async () => {
  const overviewData = await fetchDisposalOverview();
  totalEvents.value = overviewData.totalEvents;
  averageDisposalTime.value = overviewData.averageDisposalTime;
  completionRate.value = overviewData.completionRate;
  timelyRate.value = overviewData.timelyRate;
  unfinishedEvents.value = overviewData.unfinishedEvents;
  overtimeEvents.value = overviewData.overtimeEvents;
  efficiencyYoY.value = overviewData.efficiencyYoY;
  efficiencyMoM.value = overviewData.efficiencyMoM;
  avgResponseTime.value = overviewData.avgResponseTime;
  responseRate.value = overviewData.responseRate;
  slowResponseCount.value = overviewData.slowResponseCount;

  const trendData = await fetchDisposalTrend();
  trendXAxis.value = trendData.xAxis;
  trendSeries.value = trendData.series;

  const distributionData = await fetchDisposalTypeDistribution();
  typeBarData.value = distributionData;

  eventList.value = await fetchDisposalEventList();

  eventData.value = await fetchDisposalEventDetailData();
};

// 组件挂载时初始化数据
onMounted(async () => {
  await initData();
});
</script>

<style lang="scss" scoped>
$secondary-color: #13ce66;
$primary-color: #ff6b6b; // 核心主题色：红色
$warning-color: #ff7d00;
$danger-color: #ff4949;
$text-secondary: #999999;
$text-tertiary: #666666;
$bg-main: #0a1064;
$bg-card: rgba(16, 32, 80, 0.5);
$bg-panel: rgba(10, 16, 100, 0.6);
$border-color: rgba(255, 107, 107, 0.3); // 红色边框
$border-light: rgba(255, 107, 107, 0.1); // 浅色红色边框
$shadow: 0 0 1vw rgba(255, 107, 107, 0.15); // 红色阴影

// 复用基础布局样式，仅调整主题色
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
    background: rgba(255, 107, 107, 0.2);
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
    background: $primary-color; // 红色主题线
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
    --el-button-text-color: #ffc1e9;
    --el-button-bg-color: rgba(255, 107, 107, 0.2); // 红色主题按钮
    --el-button-border-color: #ffc1e9;
    --el-button-hover-bg-color: rgba(255, 107, 107, 0.4);
    --el-button-hover-border-color: #ffc1e9;
    --el-button-active-bg-color: rgba(255, 107, 107, 0.5);
    --el-button-active-border-color: #ffc1e9;
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
    box-shadow: 0 0 20px rgba(255, 107, 107, 0.25); // 红色阴影
    border-color: #ffc1e9;
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
      background: $primary-color; // 红色指标图标
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

  // 应急处置特殊逻辑：时长缩短=效率提升（绿色），时长增加=效率下降（红色）
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
    color: $warning-color;
    background: rgba(255, 125, 0, 0.2);
  }

  .level-item.average {
    color: $primary-color;
    background: rgba(255, 107, 107, 0.2);
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
    background: linear-gradient(90deg, $primary-color, rgba(255, 107, 107, 0.3)); // 红色渐变
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
      color: #ffc1e9; // 红色标题
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

  .trend-panel, .type-bar-panel {
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
      --el-select-dropdown-item-hover-bg-color: rgba(255, 107, 107, 0.2); // 红色hover
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
      --el-button-bg-color: #ff6b6b; // 红色主按钮
      --el-button-border-color: #ff6b6b;
      --el-button-hover-bg-color: #ff4d4f;
      --el-button-hover-border-color: #ff4d4f;
      --el-button-active-bg-color: #f5222d;
      --el-button-active-border-color: #f5222d;
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
      background: $primary-color; // 红色滚动条
      border-radius: 3px;
    }

    &::-webkit-scrollbar-thumb:hover {
      background: #ff4d4f;
    }
  }

  ::v-deep .el-table {
    --el-table-bg-color: transparent;
    --el-table-text-color: $text-secondary;
    --el-table-border-color: $border-light;
    --el-table-header-text-color: #ffc1e9; // 红色表头
    --el-table-header-bg-color: rgba(16, 32, 64, 0.8);
    --el-table-row-hover-bg-color: rgba(255, 107, 107, 0.1);
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
  border-color: rgba(255, 107, 107, 0.2);

  th {
    background-color: rgba(0, 40, 80, 0.8);
    color: #ffc1e9; // 红色表头文字
    font-size: 0.8vw;
    padding: 0.5vw 0;
    text-align: center;
    border-color: rgba(255, 107, 107, 0.3);
    font-weight: 500;
  }

  td {
    background-color: transparent;
    color: #b3d9ff;
    font-size: 0.7vw;
    border-bottom: 1px solid rgba(255, 107, 107, 0.15);
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
      background-color: rgba(255, 107, 107, 0.3) !important; // 红色hover
      color: #ffffff;
    }
  }

  .el-table__row--current > td {
    background-color: rgba(255, 107, 107, 0.4) !important;
    color: #ffffff;
  }

  .el-table__empty-text {
    color: #ffc1e9;
    background-color: rgba(0, 30, 60, 0.4);
  }

  &::before {
    background-color: rgba(255, 107, 107, 0.2);
  }

  .el-table__fixed-right::before,
  .el-table__fixed::before {
    background-color: transparent;
  }
}

// 事件描述卡片样式
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
.event-detail {
  .detail-section {
    margin-bottom: 24px;

    h3 {
      font-size: 16px;
      color: #ffc1e9; // 红色标题
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

// 弹窗样式优化（红色主题）
.el-dialog {
  --el-dialog-bg-color: $bg-panel;
  --el-dialog-border-color: $border-color;
  --el-dialog-title-color: #ffc1e9;
  --el-dialog-text-color: $text-secondary;
  --el-dialog-header-border-color: $border-light;
  --el-dialog-footer-border-color: $border-light;
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(255, 107, 107, 0.2);

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
