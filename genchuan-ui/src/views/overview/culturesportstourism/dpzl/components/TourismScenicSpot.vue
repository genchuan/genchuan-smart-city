<template>
  <div class="page-container">
    <!-- 页头区域：标题及筛选操作区 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/scenic.png"
          alt="旅游景区图标"
          class="title-icon"
        />
        旅游景区专题
      </h1>
      <div class="header-actions">
        <!-- 功能按钮组 -->
        <el-button size="small" @click="refreshData">刷新</el-button>
        <el-button size="small" @click="handleBack">返回总览</el-button>
      </div>
    </div>

    <!-- 主内容区域 -->
    <div class="main-content">
      <!-- 顶部：核心指标卡片组 -->
      <div class="stats-cards">
        <div class="stat-card">
          <div class="stat-title">景区总数</div>
          <div class="stat-value" :class="totalSceneCount > 35 ? 'warning' : 'normal'">
            {{ totalSceneCount }} <span class="unit">个</span>
          </div>
          <div class="stat-desc">高风险景区: {{ highRiskSceneCount }} 个</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">实时客流</div>
          <div class="stat-value" :class="currentCount > 100000 ? 'danger' : currentCount > 80000 ? 'warning' : 'normal'">
            {{ formatNumber(currentCount) }} <span class="unit">人</span>
          </div>
          <div class="stat-desc">总承载量: {{ formatNumber(capacity) }} 人</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">客流饱和度</div>
          <div class="stat-value" :class="flowSaturation > 80 ? 'danger' : flowSaturation > 60 ? 'warning' : 'normal'">
            {{ flowSaturation }} <span class="unit">%</span>
          </div>
          <div class="stat-desc">预警阈值: 80%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">设施完好率</div>
          <div class="stat-value" :class="facilityRate > 95 ? 'normal' : facilityRate > 90 ? 'warning' : 'danger'">
            {{ facilityRate }} <span class="unit">%</span>
          </div>
          <div class="stat-desc">
            <span
              class="compare-item"
              :class="facilityRateYoY > 0 ? 'increase' : 'decrease'"
            >
              同比 {{ facilityRateYoY > 0 ? '↑' : '↓' }}{{ Math.abs(facilityRateYoY).toFixed(1) }}%
            </span>
          </div>
        </div>
      </div>

      <!-- 中间：趋势图+TOP5景区列表 -->
      <div class="middle-row">
        <!-- 左侧：客流趋势折线图 -->
        <div class="panel flow-trend-panel" ref="flowTrendPanel">
          <div class="panel-header">
            <h2>景区客流趋势（近24小时）</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('flowTrendPanel')">
                <el-icon color="#ff7d00" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartLine
              :xAxis="flowTrendXAxis"
              :series="flowTrendSeries"
              :yAxisName="''"
            />
          </div>
          <div class="panel-footer"></div>
        </div>

        <!-- 右侧：TOP5景区列表 -->
        <div class="panel top5-panel" ref="top5Panel">
          <div class="panel-header">
            <h2>客流TOP5景区</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('top5Panel')">
                <el-icon color="#ff7d00" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <el-table
              :data="top5ScenicSpotData"
              border
              size="small"
              style="width: 100%; height: 100%"
              row-class-name="top5-row"
            >
              <el-table-column prop="rank" label="排名" width="80" align="center">
                <template #default="scope">
                  <div class="rank-tag">{{ scope.row.rank }}</div>
                </template>
              </el-table-column>
              <el-table-column prop="scene_name" label="景区名称" />
              <el-table-column prop="current_count" label="实时客流（人）">
                <template #default="scope">
                  {{ formatNumber(scope.row.current_count) }}
                </template>
              </el-table-column>
              <el-table-column prop="saturation" label="客流饱和度（%）">
                <template #default="scope">
                  <span :class="scope.row.saturation > 80 ? 'text-danger' : scope.row.saturation > 60 ? 'text-warning' : 'text-success'">
                    {{ scope.row.saturation }}
                  </span>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <!-- 底部：事件列表 + 投诉列表 -->
      <div class="bottom-row">
        <!-- 左侧：安全事件列表 -->
        <div class="panel safety-event-panel" ref="safetyEventPanel">
          <div class="panel-header">
            <h2>安全事件列表（共 {{ safetyEventTotal }} 起）</h2>
            <div class="header-actions">
              <el-select v-model="eventLevel" placeholder="事件级别" style="width: 8vw" size="small">
                <el-option label="全部" value="" />
                <el-option label="高风险" value="high" />
                <el-option label="中风险" value="medium" />
                <el-option label="低风险" value="low" />
              </el-select>
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('safetyEventPanel')">
                <el-icon color="#ff7d00" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body table-scroll">
            <el-table
              :data="filteredSafetyEventData"
              border
              size="small"
              style="width: 100%; height: 100%"
            >
              <el-table-column prop="event_id" label="事件ID" width="100" />
              <el-table-column prop="matter_name" label="事件名称" min-width="180" />
              <el-table-column prop="scene_name" label="所属景区" />
              <el-table-column prop="level" label="事件级别">
                <template #default="scope">
                  <el-tag :type="getEventLevelTagType(scope.row.level)">
                    {{ getEventLevelName(scope.row.level) }}
                  </el-tag>
                </template>
              </el-table-column>
              <el-table-column prop="occur_time" label="发生时间" width="180" />
            </el-table>
          </div>
          <div class="panel-footer"></div>
        </div>

        <!-- 右侧：投诉列表 -->
        <div class="panel complaint-panel" ref="complaintPanel">
          <div class="panel-header">
            <h2>投诉列表（共 {{ complaintTotal }} 条）</h2>
            <div class="header-actions">
              <el-select v-model="complaintStatus" placeholder="处理状态" style="width: 8vw" size="small">
                <el-option label="全部" value="" />
                <el-option label="已处理" value="handled" />
                <el-option label="处理中" value="processing" />
                <el-option label="未处理" value="pending" />
              </el-select>
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('complaintPanel')">
                <el-icon color="#ff7d00" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body table-scroll">
            <el-table
              :data="filteredComplaintData"
              border
              size="small"
              style="width: 100%; height: 100%"
            >
              <el-table-column prop="complaint_id" label="投诉ID" width="100" />
              <el-table-column prop="matter_name" label="投诉名称" min-width="180" />
              <el-table-column prop="scene_name" label="所属景区" />
              <el-table-column prop="complaint_type" label="投诉类型" />
              <el-table-column prop="handle_status" label="处理状态">
                <template #default="scope">
                  <el-tag :type="getComplaintStatusTagType(scope.row.handle_status)">
                    {{ getComplaintStatusName(scope.row.handle_status) }}
                  </el-tag>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, getCurrentInstance } from 'vue';
import { useRouter } from 'vue-router';
import {ElMessage, ElSelect} from 'element-plus';
import screenFull from 'screenfull';
import ChartLine from './ChartLine2.vue';
import ChartBar from './ChartBar2.vue';
import { FullScreen } from "@element-plus/icons-vue";

// 导入旅游景区API
import {
  fetchScenicSpotOverview,
  fetchScenicSpotFlowTrend,
  fetchSafetyEventList,
  fetchComplaintList,
  fetchTop5ScenicSpot
} from '@/api/overview/culturesportstourism/SubdomainScenarioTopics.js';

const router = useRouter();
const instance = getCurrentInstance();

// 数字格式化（千分位）
const formatNumber = (num) => {
  return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
};

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
const eventLevel = ref('');           // 安全事件级别筛选
const complaintStatus = ref('');      // 投诉处理状态筛选

// 核心指标数据变量
const totalSceneCount = ref(0);       // 景区总数
const highRiskSceneCount = ref(0);    // 高风险景区数（总数*0.2）
const currentCount = ref(0);          // 实时客流
const capacity = ref(0);              // 总承载量
const flowSaturation = ref(0);        // 客流饱和度
const level1Count = ref(0);           // 安全事件数
const facilityRate = ref(0);          // 设施完好率
const facilityRateYoY = ref(0);       // 设施完好率同比
const facilityRateMoM = ref(0);       // 设施完好率环比
const highRiskScene = ref('');        // 高风险景区
const lastMonitorTime = ref('');      // 最近监测时间

// 图表/列表数据变量
const flowTrendXAxis = ref([]);
const flowTrendSeries = ref([]);
const safetyEventData = ref([]);
const complaintData = ref([]);
const top5ScenicSpotData = ref([]);

// 安全事件级别名称映射
const getEventLevelName = (level) => {
  const map = {
    'high': '高风险',
    'medium': '中风险',
    'low': '低风险'
  };
  return map[level] || '未知级别';
};

// 安全事件级别标签样式映射（橙色主题）
const getEventLevelTagType = (level) => {
  const map = {
    'high': 'danger',
    'medium': 'warning',
    'low': 'success'
  };
  return map[level] || 'default';
};

// 投诉状态名称映射
const getComplaintStatusName = (status) => {
  const map = {
    'handled': '已处理',
    'processing': '处理中',
    'pending': '未处理'
  };
  return map[status] || '未知状态';
};

// 投诉状态标签样式映射
const getComplaintStatusTagType = (status) => {
  const map = {
    'handled': 'success',
    'processing': 'info',
    'pending': 'danger'
  };
  return map[status] || 'default';
};

// 安全事件总数
const safetyEventTotal = computed(() => safetyEventData.value.length);

// 筛选后的安全事件数据
const filteredSafetyEventData = computed(() => {
  return safetyEventData.value.filter(item => {
    return !eventLevel.value || item.level === eventLevel.value;
  });
});

// 投诉总数
const complaintTotal = computed(() => complaintData.value.length);

// 筛选后的投诉数据
const filteredComplaintData = computed(() => {
  return complaintData.value.filter(item => {
    return !complaintStatus.value || item.handle_status === complaintStatus.value;
  });
});

// 刷新数据
const refreshData = async () => {
  try {
    await initData();
    ElMessage.success('旅游景区数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
    console.error(error);
  }
};

// 导出数据（预留）
const exportData = () => {
  ElMessage.success('旅游景区监测数据导出成功');
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/culturesportstourism/dpzl');
};

// 初始化数据
const initData = async () => {
  // 概览数据
  const overviewData = await fetchScenicSpotOverview();
  totalSceneCount.value = overviewData.total_scene_count;
  highRiskSceneCount.value = Math.round(overviewData.total_scene_count * 0.2);
  currentCount.value = overviewData.current_count;
  capacity.value = overviewData.capacity;
  flowSaturation.value = overviewData.flow_saturation;
  level1Count.value = overviewData.level1_count;
  facilityRate.value = overviewData.facility_rate;
  facilityRateYoY.value = overviewData.facility_rate_yoy;
  facilityRateMoM.value = overviewData.facility_rate_mom;
  highRiskScene.value = overviewData.highRiskScene;
  lastMonitorTime.value = overviewData.lastMonitorTime;

  // 客流趋势数据（30分钟粒度）
  const trendData = await fetchScenicSpotFlowTrend();
  flowTrendXAxis.value = trendData.xAxis;
  flowTrendSeries.value = trendData.series;

  // 安全事件列表
  safetyEventData.value = await fetchSafetyEventList();

  // 投诉列表
  complaintData.value = await fetchComplaintList();

  // TOP5景区数据
  top5ScenicSpotData.value = await fetchTop5ScenicSpot();
};

// 组件挂载时初始化数据
onMounted(async () => {
  await initData();
});
</script>

<style lang="scss" scoped>
// 页面基础布局
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
  border-bottom: 0.2vh solid rgba(0, 204, 255, 0.3);
  margin-bottom: 1.2vw;
  position: relative;

  &::after {
    content: '';
    position: absolute;
    bottom: -0.3vh;
    left: 0;
    width: 16vw;
    height: 0.3vh;
    background: #ff7d00; // 橙色主题色
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

// 核心指标卡片组
.stats-cards {
  display: flex;
  gap: 1.2vw;
  flex-wrap: wrap;

  .stat-card {
    flex: 1;
    background: rgba(16, 32, 80, 0.5);
    border-radius: 8px;
    padding: 1.2vw;
    border: 1px solid rgba(0, 204, 255, 0.3);
    box-shadow: 0 0 1vw rgba(0, 204, 255, 0.15);
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
        background: #ff7d00; // 橙色指标点
        margin-right: 0.8vw;
        border-radius: 50%;
      }
    }

    .stat-value {
      font-size: 1.6vw !important;
      font-weight: 700;
      margin-bottom: 1vh;
      line-height: 1.2;

      .unit {
        font-size: 1.1vw !important;
        margin-left: 0.4vw;
        color: #666666;
      }

      &.normal {
        color: #13ce66;
        text-shadow: 0 0 8px rgba(19, 206, 102, 0.3);
      }

      &.warning {
        color: #ff7d00;
        text-shadow: 0 0 8px rgba(255, 125, 0, 0.3);
      }

      &.danger {
        color: #ff4949;
        text-shadow: 0 0 8px rgba(255, 73, 73, 0.3);
      }
    }

    .stat-desc {
      font-size: 0.8vw !important;
      color: #666666;
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
      color: #ff4949;
      background: rgba(255, 73, 73, 0.2);
    }

    .compare-item.decrease {
      color: #13ce66;
      background: rgba(19, 206, 102, 0.2);
    }
  }
}

// 中间行布局
.middle-row {
  display: flex;
  gap: 1.2vw;
  height: 39vh;

  // 左侧：客流趋势图
  .flow-trend-panel {
    flex: 3;
  }

  // 右侧：TOP5景区列表
  .top5-panel {
    flex: 2;

    .rank-tag {
      display: inline-block;
      width: 24px;
      height: 24px;
      line-height: 24px;
      text-align: center;
      border-radius: 50%;
      color: #fff;
      font-weight: bold;
      background: #ff7d00; // 橙色排名标签
    }

    .top5-row {
      &:nth-child(1) .rank-tag { background: #ffd100; } // 第一名金色
      &:nth-child(2) .rank-tag { background: #c0c0c0; } // 第二名银色
      &:nth-child(3) .rank-tag { background: #cd7f32; } // 第三名铜色
    }

    .text-danger { color: #ff4949; }
    .text-warning { color: #ff7d00; }
    .text-success { color: #13ce66; }
  }
}

// 底部行布局 - 事件列表+投诉列表
.bottom-row {
  display: flex;
  gap: 1.2vw;
  height: 30vh;

  // 安全事件列表
  .safety-event-panel {
    flex: 1;

    .header-actions {
      display: flex;
      gap: 0.8vw;
      align-items: center;
    }
  }

  // 投诉列表
  .complaint-panel {
    flex: 1;

    .header-actions {
      display: flex;
      gap: 0.8vw;
      align-items: center;
    }
  }
}

// 面板通用样式
.panel {
  position: relative;
  border: 1px solid rgba(0, 204, 255, 0.3);
  background: rgba(10, 16, 100, 0.6);
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  min-height: 0;
  box-shadow: 0 0 1vw rgba(0, 204, 255, 0.15);
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
    border-bottom: 1px solid rgba(0, 204, 255, 0.1);
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

    .table-scroll {
      height: 100%;
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
  }

  .panel-footer {
    height: 1vh;
    border-top: 1px solid rgba(0, 204, 255, 0.1);
  }
}

// 表格全局样式
::v-deep .el-table {
  --el-table-bg-color: transparent;
  --el-table-text-color: #999999;
  --el-table-border-color: rgba(0, 204, 255, 0.1);
  --el-table-header-text-color: #ffb800; // 橙色表头
  --el-table-header-bg-color: rgba(16, 32, 64, 0.8);
  --el-table-row-hover-bg-color: rgba(255, 125, 0, 0.1);
  --el-table-row-stripes-bg-color: rgba(16, 32, 64, 0.3);
  border-radius: 4px;
  overflow: hidden;
  height: 100%;

  .el-table__header {
    border-bottom: 1px solid rgba(0, 204, 255, 0.3);
  }

  .el-table__cell {
    border-bottom: 1px solid rgba(0, 204, 255, 0.1);
    padding: 1vh 0.8vw !important;
  }

  .el-tag {
    font-size: 0.75vw;
    padding: 0.2vh 0.6vw;
    border-radius: 12px;
  }

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
}

// 标签样式映射
.el-tag--danger {
  --el-tag-bg-color: rgba(255, 73, 73, 0.2);
  --el-tag-border-color: #ff4949;
  --el-tag-text-color: #ff4949;
}

.el-tag--warning {
  --el-tag-bg-color: rgba(255, 125, 0, 0.2);
  --el-tag-border-color: #ff7d00;
  --el-tag-text-color: #ff7d00;
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
</style>
