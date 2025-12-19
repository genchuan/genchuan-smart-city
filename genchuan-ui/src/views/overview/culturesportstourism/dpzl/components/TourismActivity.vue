<template>
  <div class="page-container">
    <!-- 页头区域：标题及筛选操作区 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/activity.png"
          alt="文旅活动图标"
          class="title-icon"
        />
        文旅活动专题
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
          <div class="stat-title">活动总数</div>
          <div class="stat-value" :class="totalSceneCount > 100 ? 'warning' : 'normal'">
            {{ totalSceneCount }} <span class="unit">场</span>
          </div>
          <div class="stat-desc">近30天统计 | 高风险场景: {{ highRiskSceneCount }} 个</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">参与总人数</div>
          <div class="stat-value normal">
            {{ formatNumber(totalRptCount) }} <span class="unit">人</span>
          </div>
          <div class="stat-desc">累计统计 | 日均参与: {{ Math.round(totalRptCount/30) }} 人</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">实时参与人数</div>
          <div class="stat-value" :class="currentCount > 20000 ? 'warning' : 'normal'">
            {{ formatNumber(currentCount) }} <span class="unit">人</span>
          </div>
          <div class="stat-desc">当前在线 | 峰值: {{ Math.round(currentCount*1.2) }} 人</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">安全事件数</div>
          <div class="stat-value" :class="abnCompCount > 15 ? 'danger' : abnCompCount > 8 ? 'warning' : 'normal'">
            {{ abnCompCount }} <span class="unit">起</span>
          </div>
          <div class="stat-desc">近30天 | 已处理: {{ Math.round(abnCompCount*0.8) }} 起</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">舆情好评率</div>
          <div class="stat-value" :class="praiseRate > 90 ? 'normal' : praiseRate > 80 ? 'warning' : 'danger'">
            {{ praiseRate }} <span class="unit">%</span>
          </div>
          <div class="stat-desc">
            <span
              class="compare-item"
              :class="praiseRateYoY > 0 ? 'increase' : 'decrease'"
            >
              同比 {{ praiseRateYoY > 0 ? '↑' : '↓' }}{{ Math.abs(praiseRateYoY).toFixed(1) }}%
            </span>
            <span
              class="compare-item"
              :class="praiseRateMoM > 0 ? 'increase' : 'decrease'"
            >
              环比 {{ praiseRateMoM > 0 ? '↑' : '↓' }}{{ Math.abs(praiseRateMoM).toFixed(1) }}%
            </span>
          </div>
        </div>
      </div>

      <!-- 图表行：三栏布局（趋势+类型分布+安全事件） -->
      <div class="chart-row">
        <!-- 左侧：文旅活动趋势折线图（近14天） -->
        <div class="panel trend-panel" ref="trendPanel">
          <div class="panel-header">
            <h2>文旅活动-参与人数趋势（近14天）</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('trendPanel')">
                <el-icon color="#ffd100" size="16">
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

        <!-- 中间：活动类型分布柱状图 -->
        <div class="panel type-bar-panel" ref="typeBarPanel">
          <div class="panel-header">
            <h2>活动类型分布</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('typeBarPanel')">
                <el-icon color="#ffd100" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartBar
              :data="typeBarData"
            />
          </div>
          <div class="panel-footer"></div>
        </div>

        <!-- 右侧：安全事件分布饼图 -->
        <div class="panel safety-pie-panel" ref="safetyPiePanel">
          <div class="panel-header">
            <h2>安全事件分布</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('safetyPiePanel')">
                <el-icon color="#ffd100" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartPie
              :data="safetyPieData"
            />
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <!-- 文旅活动详细数据表格 -->
      <div class="panel data-table-panel" ref="dataTablePanel">
        <div class="panel-header">
          <h2>文旅活动监测数据</h2>
          <div class="header-actions">
            <!-- 时间范围筛选器（今日/本周） -->
            <el-select v-model="statCycle" placeholder="时间范围" size="small">
              <el-option label="全部" value="" />
              <el-option label="今日" value="今日" />
              <el-option label="本周" value="本周" />
            </el-select>
            <!-- 活动类型筛选器（展览/演出/赛事） -->
            <el-select v-model="activityType" placeholder="活动类型" size="small">
              <el-option label="全部" value="" />
              <el-option label="展览" value="展览" />
              <el-option label="演出" value="演出" />
              <el-option label="赛事" value="赛事" />
            </el-select>
            <!-- 活动状态筛选器（筹备/进行中/结束） -->
            <el-select v-model="activityStatus" placeholder="活动状态" size="small">
              <el-option label="全部" value="" />
              <el-option label="筹备" value="pending" />
              <el-option label="进行中" value="ongoing" />
              <el-option label="结束" value="completed" />
            </el-select>
            <!-- 安全事件筛选器 -->
            <el-select v-model="hasSafetyEvent" placeholder="安全事件" size="small">
              <el-option label="全部" value="" />
              <el-option label="有事件" value="1" />
              <el-option label="无事件" value="0" />
            </el-select>
            <el-input
              v-model="searchKeyword"
              placeholder="搜索活动名称"
              size="small"
              style="width: 15vw"
            />
            <el-button size="small" type="primary" @click="exportData">导出数据</el-button>
            <button class="panel-fullscreen-btn" @click="toggleFullscreen('dataTablePanel')">
              <el-icon color="#ffd100" size="16">
                <FullScreen/>
              </el-icon>
            </button>
          </div>
        </div>
        <!-- 表格滚动容器 -->
        <div class="panel-body table-scroll">
          <el-table
            :data="filteredActivityData"
            border
            size="small"
            style="width: 100%"
          >
            <el-table-column prop="app_scene_name" label="活动名称" min-width="150" />
            <el-table-column prop="minor_name" label="活动类型" />
            <el-table-column prop="scene_status" label="活动状态">
              <template #default="scope">
                <el-tag :type="getActivityStatusTagType(scope.row.scene_status)">
                  {{ getActivityStatusName(scope.row.scene_status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="location" label="活动地点" />
            <el-table-column prop="actual_people" label="实际参与人数">
              <template #default="scope">
                {{ formatNumber(Math.round(scope.row.actual_people)) }}
              </template>
            </el-table-column>
            <el-table-column prop="safety_event" label="安全事件数（起）">
              <template #default="scope">
                <el-tag :type="scope.row.safety_event === '1' ? 'danger' : 'success'">
                  {{ scope.row.safety_event }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="praise_rate" label="好评率（%）" />
            <el-table-column prop="create_time" label="创建时间" width="180" />
            <el-table-column label="操作" width="80">
              <template #default="scope">
                <el-button size="small" link @click="showActivityDetail(scope.row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="panel-footer"></div>
      </div>
    </div>

    <!-- 活动详情弹窗 -->
    <el-dialog
      v-model="activityDetailVisible"
      :title="currentActivity?.app_scene_name || '文旅活动详情'"
      width="700px"
    >
      <div class="activity-detail">
        <!-- 基础信息区域 -->
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="活动ID">{{ currentActivity?.scene_id }}</el-descriptions-item>
            <el-descriptions-item label="活动类型">{{ currentActivity?.minor_name }}</el-descriptions-item>
            <el-descriptions-item label="活动状态">
              <el-tag :type="getActivityStatusTagType(currentActivity?.scene_status)">
                {{ getActivityStatusName(currentActivity?.scene_status) }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="时间周期">{{ currentActivity?.stat_cycle }}</el-descriptions-item>
            <el-descriptions-item label="活动地点">{{ currentActivity?.location }}</el-descriptions-item>
            <el-descriptions-item label="预计参与人数">{{ formatNumber(currentActivity?.expected_people) }} 人</el-descriptions-item>
            <el-descriptions-item label="实际参与人数">{{ formatNumber(Math.round(currentActivity?.actual_people)) }} 人</el-descriptions-item>
            <el-descriptions-item label="安全事件数">{{ currentActivity?.safety_event }} 起</el-descriptions-item>
            <el-descriptions-item label="创建时间">{{ currentActivity?.create_time }}</el-descriptions-item>
            <el-descriptions-item label="好评率">{{ currentActivity?.praise_rate }}%</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 运营信息区域 -->
        <div class="detail-section">
          <h3>运营信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="负责人">{{ currentActivity?.manager || '未分配' }}</el-descriptions-item>
            <el-descriptions-item label="开始时间">{{ currentActivity?.start_time || '未开始' }}</el-descriptions-item>
            <el-descriptions-item label="结束时间">{{ currentActivity?.end_time || '进行中' }}</el-descriptions-item>
            <el-descriptions-item label="安全措施">{{ currentActivity?.safety_measures || '未制定' }}</el-descriptions-item>
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
import ChartPie from './ChartPie2.vue'; // 导入饼图组件
import { FullScreen } from "@element-plus/icons-vue";

// 导入文旅活动API
import {
  fetchCulturalTourismOverview,
  fetchCulturalTourismTrend,
  fetchActivityTypeDistribution,
  fetchSafetyEventDistribution,
  fetchActivityList,
  fetchActivityDetailData,
  fetchActivityDetailTrend
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
const statCycle = ref('');             // 时间范围（今日/本周）
const activityType = ref('');          // 活动类型（展览/演出/赛事）
const activityStatus = ref('');        // 活动状态（筹备/进行中/结束）
const hasSafetyEvent = ref('');        // 安全事件筛选
const searchKeyword = ref('');         // 搜索关键词

// 核心指标数据变量
const totalSceneCount = ref(0);        // 活动总数
const highRiskSceneCount = ref(0);     // 高风险场景数（活动总数*0.15）
const totalRptCount = ref(0);          // 参与总人数
const currentCount = ref(0);           // 实时参与人数
const abnCompCount = ref(0);           // 安全事件数
const praiseRate = ref(0);             // 舆情好评率
const praiseRateYoY = ref(0);          // 好评率同比
const praiseRateMoM = ref(0);          // 好评率环比
const highRiskScene = ref('');         // 高风险场景
const lastMonitorTime = ref('');       // 最近监测时间

// 图表数据变量
const trendXAxis = ref([]);
const trendSeries = ref([]);
const typeBarData = ref({});
const safetyPieData = ref([]);

// 活动数据变量
const activityList = ref([]);
const currentActivity = ref(null);
const activityDetailVisible = ref(false);
const activityTrendXAxis = ref([]);
const activityTrendSeries = ref([]);
const activityData = ref([]);

// 活动状态名称映射
const getActivityStatusName = (status) => {
  const map = {
    'pending': '筹备',
    'ongoing': '进行中',
    'completed': '结束'
  };
  return map[status] || '未知状态';
};

// 活动状态标签样式映射（黄色主题）
const getActivityStatusTagType = (status) => {
  const map = {
    'pending': 'info',       // 筹备-蓝色
    'ongoing': 'warning',    // 进行中-黄色（主题色）
    'completed': 'success'   // 结束-绿色
  };
  return map[status] || 'default';
};

// 筛选后的表格数据
const filteredActivityData = computed(() => {
  return activityData.value.filter(item => {
    // 时间范围筛选
    const matchesCycle = !statCycle.value || item.stat_cycle === statCycle.value;
    // 活动类型筛选
    const matchesType = !activityType.value || item.minor_name === activityType.value;
    // 活动状态筛选
    const matchesStatus = !activityStatus.value || item.scene_status === activityStatus.value;
    // 安全事件筛选
    const matchesEvent = !hasSafetyEvent.value || item.safety_event === hasSafetyEvent.value;
    // 名称关键词筛选
    const matchesKeyword = !searchKeyword.value || item.app_scene_name.includes(searchKeyword.value);
    return matchesCycle && matchesType && matchesStatus && matchesEvent && matchesKeyword;
  });
});

// 显示活动详情
const showActivityDetail = async (activity) => {
  currentActivity.value = { ...activity };
  // 获取活动详情趋势数据
  const trendData = await fetchActivityDetailTrend(activity.scene_id);
  activityTrendXAxis.value = trendData.xAxis;
  activityTrendSeries.value = trendData.series;
  activityDetailVisible.value = true;
};

// 刷新数据
const refreshData = async () => {
  try {
    await initData();
    ElMessage.success('文旅活动数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
    console.error(error);
  }
};

// 导出数据
const exportData = () => {
  ElMessage.success('文旅活动监测数据导出成功');
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/culturesportstourism/dpzl');
};

// 初始化数据
const initData = async () => {
  // 概览数据
  const overviewData = await fetchCulturalTourismOverview();
  totalSceneCount.value = overviewData.total_scene_count;
  highRiskSceneCount.value = Math.round(overviewData.total_scene_count * 0.15);
  totalRptCount.value = overviewData.total_rpt_count;
  currentCount.value = overviewData.current_count;
  abnCompCount.value = overviewData.abn_comp_count;
  praiseRate.value = overviewData.praise_rate;
  praiseRateYoY.value = overviewData.praise_rate_yoy;
  praiseRateMoM.value = overviewData.praise_rate_mom;
  highRiskScene.value = overviewData.high_risk_scene;
  lastMonitorTime.value = overviewData.last_monitor_time;

  // 趋势数据（近14天）
  const trendData = await fetchCulturalTourismTrend();
  trendXAxis.value = trendData.xAxis;
  trendSeries.value = trendData.series;

  // 活动类型分布
  const typeData = await fetchActivityTypeDistribution();
  typeBarData.value = typeData;

  // 安全事件分布
  const safetyData = await fetchSafetyEventDistribution();
  safetyPieData.value = safetyData;

  // 活动列表
  activityList.value = await fetchActivityList();

  // 活动详细数据
  activityData.value = await fetchActivityDetailData();
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
    background: rgba(255, 209, 0, 0.2); // 黄色hover背景
  }
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.2vh 0;
  border-bottom: 0.2vh solid rgba(255, 209, 0, 0.3);
  margin-bottom: 1.2vw;
  position: relative;

  &::after {
    content: '';
    position: absolute;
    bottom: -0.3vh;
    left: 0;
    width: 16vw;
    height: 0.3vh;
    background: #ffd100; // 黄色下划线
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
    --el-button-text-color: #ffd100;
    --el-button-bg-color: rgba(255, 209, 0, 0.2);
    --el-button-border-color: #ffd100;
    --el-button-hover-bg-color: rgba(255, 209, 0, 0.4);
    --el-button-hover-border-color: #ffd100;
    --el-button-active-bg-color: rgba(255, 209, 0, 0.5);
    --el-button-active-border-color: #ffd100;
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

// 核心指标卡片样式
.stats-cards {
  display: flex;
  gap: 1.2vw;
  flex-wrap: wrap;
}

.stat-card {
  flex: 1;
  min-width: 15vw;
  background: rgba(16, 32, 80, 0.5);
  border-radius: 8px;
  padding: 1.2vw;
  border: 1px solid rgba(255, 209, 0, 0.3);
  box-shadow: 0 0 1vw rgba(255, 209, 0, 0.15);
  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-3px);
    box-shadow: 0 0 20px rgba(255, 209, 0, 0.25); // 黄色阴影
    border-color: #ffd100;
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
      background: #ffd100; // 黄色指标点
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
      color: #ffd100;
      text-shadow: 0 0 8px rgba(255, 209, 0, 0.3); // 黄色发光
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

// 面板通用样式
.panel {
  position: relative;
  border: 1px solid rgba(255, 209, 0, 0.3);
  background: rgba(10, 16, 100, 0.6);
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  min-height: 0;
  box-shadow: 0 0 1vw rgba(255, 209, 0, 0.15);
  overflow: hidden;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 3px;
    background: linear-gradient(90deg, #ffd100, rgba(255, 209, 0, 0.3)); // 黄色渐变
  }

  .panel-header {
    padding: 1vh 1.2vw;
    border-bottom: 1px solid rgba(255, 209, 0, 0.1);
    display: flex;
    justify-content: space-between;
    align-items: center;

    h2 {
      margin: 0;
      font-size: 1.1vw;
      color: #ffd100; // 黄色标题
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
    border-top: 1px solid rgba(255, 209, 0, 0.1);
  }
}

// 图表行布局（三栏）
.chart-row {
  display: flex;
  gap: 1.2vw;
  height: 28vh;

  .trend-panel {
    flex: 2;
  }
  .type-bar-panel, .safety-pie-panel {
    flex: 1;
  }
}

// 数据表格面板样式
.data-table-panel {
  max-height: 40vh;

  .panel-header {
    .header-actions {
      display: flex;
      gap: 0.8vw;
      align-items: center;
    }

    .el-select, .el-input {
      --el-select-text-color: #999999;
      --el-select-placeholder-color: #666666;
      --el-select-dropdown-bg-color: rgba(16, 32, 80, 0.5);
      --el-select-dropdown-border-color: rgba(255, 209, 0, 0.3);
      --el-select-dropdown-item-hover-bg-color: rgba(255, 209, 0, 0.2); // 黄色hover
      --el-select-dropdown-item-text-color: #999999;
      --el-input-bg-color: rgba(16, 32, 64, 0.5);
      --el-input-border-color: rgba(255, 209, 0, 0.3);
      --el-input-text-color: #999999;
      --el-input-placeholder-color: #666666;
      font-size: 0.7vw;
      width: 8vw;

      &:last-of-type {
        width: 30vw;
        max-width: 40vw;
      }
    }

    .el-button--primary {
      --el-button-text-color: #fff;
      --el-button-bg-color: #ffd100;
      --el-button-border-color: #ffd100;
      --el-button-hover-bg-color: #ffe033;
      --el-button-hover-border-color: #ffe033;
      --el-button-active-bg-color: #ffc800;
      --el-button-active-border-color: #ffc800;
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
      background: #ffd100;
      border-radius: 3px;
    }

    &::-webkit-scrollbar-thumb:hover {
      background: #ffe033;
    }
  }

  ::v-deep .el-table {
    --el-table-bg-color: transparent;
    --el-table-text-color: #999999;
    --el-table-border-color: rgba(255, 209, 0, 0.1);
    --el-table-header-text-color: #ffd100;
    --el-table-header-bg-color: rgba(16, 32, 64, 0.8);
    --el-table-row-hover-bg-color: rgba(255, 209, 0, 0.1); // 黄色hover行
    --el-table-row-stripes-bg-color: rgba(16, 32, 64, 0.3);
    border-radius: 4px;
    overflow: hidden;

    .el-table__header {
      border-bottom: 1px solid rgba(255, 209, 0, 0.3);
    }

    .el-table__cell {
      border-bottom: 1px solid rgba(255, 209, 0, 0.1);
      padding: 1vh 0.8vw !important;
    }

    .el-tag {
      font-size: 0.75vw;
      padding: 0.2vh 0.6vw;
      border-radius: 12px;
      transition: all 0.3s ease;
    }
  }
}

// 表格全局样式优化
::v-deep .el-table {
  table-layout: fixed;
  background-color: rgba(0, 30, 60, 0.4);
  border-color: rgba(255, 209, 0, 0.2); // 黄色边框

  th {
    background-color: rgba(0, 40, 80, 0.8);
    color: #ffd100;
    font-size: 0.8vw;
    padding: 0.5vw 0;
    text-align: center;
    border-color: rgba(255, 209, 0, 0.3);
    font-weight: 500;
  }

  td {
    background-color: transparent;
    color: #b3d9ff;
    font-size: 0.7vw;
    border-bottom: 1px solid rgba(255, 209, 0, 0.15);
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
      background-color: rgba(255, 209, 0, 0.3) !important; // 黄色hover背景
      color: #ffffff;
    }
  }

  .el-table__row--current > td {
    background-color: rgba(255, 209, 0, 0.4) !important;
    color: #ffffff;
  }

  .el-table__empty-text {
    color: #ffd100;
    background-color: rgba(0, 30, 60, 0.4);
  }

  &::before {
    background-color: rgba(255, 209, 0, 0.2);
  }
}

// 标签样式映射
.el-tag--danger {
  --el-tag-bg-color: rgba(255, 73, 73, 0.2);
  --el-tag-border-color: #ff4949;
  --el-tag-text-color: #ff4949;
}

.el-tag--warning {
  --el-tag-bg-color: rgba(255, 209, 0, 0.2);
  --el-tag-border-color: #ffd100;
  --el-tag-text-color: #ffd100;
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
.activity-detail {
  .detail-section {
    margin-bottom: 24px;

    h3 {
      font-size: 16px;
      color: #ffd100;
      margin-bottom: 12px;
      padding-bottom: 6px;
      border-bottom: 1px solid rgba(255, 209, 0, 0.3);
      font-weight: 600;
    }
  }

  .el-descriptions {
    --el-descriptions-bg-color: transparent;
    --el-descriptions-border-color: rgba(255, 209, 0, 0.1);
    --el-descriptions-title-color: #999999;
    --el-descriptions-content-color: #999999;
    font-size: 0.9vw;

    .el-descriptions__label {
      color: #999999;
      font-weight: 500;
      width: 110px !important;
      text-align: left !important;
    }

    .el-descriptions__content {
      color: #999999;
      padding: 10px 0;
    }

    .el-descriptions__item {
      border-bottom: 1px solid rgba(255, 209, 0, 0.1);

      &:last-child {
        border-bottom: none;
      }
    }
  }
}

// 弹窗样式优化（黄色主题）
.el-dialog {
  --el-dialog-bg-color: rgba(10, 16, 100, 0.6);
  --el-dialog-border-color: rgba(255, 209, 0, 0.3);
  --el-dialog-title-color: #ffd100;
  --el-dialog-text-color: #999999;
  --el-dialog-header-border-color: rgba(255, 209, 0, 0.1);
  --el-dialog-footer-border-color: rgba(255, 209, 0, 0.1);
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(255, 209, 0, 0.2); // 黄色阴影

  .el-dialog__title {
    font-size: 18px;
    font-weight: 600;
  }

  .el-dialog__header {
    padding: 16px 20px;
    border-bottom: 1px solid rgba(255, 209, 0, 0.1);
  }

  .el-dialog__body {
    padding: 20px;
    color: #999999;
  }

  .el-dialog__footer {
    padding: 12px 20px;
    border-top: 1px solid rgba(255, 209, 0, 0.1);
  }
}
</style>
