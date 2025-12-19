<template>
  <div class="page-container">
    <!-- 页头区域：标题及筛选操作区 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/venue.png"
          alt="文体场馆图标"
          class="title-icon"
        />
        文体场馆专题
      </h1>
      <div class="header-actions">
        <el-button size="small" @click="refreshVenueData">刷新</el-button>
        <el-button size="small" @click="handleBack">返回总览</el-button>
      </div>
    </div>

    <!-- 主内容区域 -->
    <div class="main-content">
      <!-- 核心指标卡片组（5个） -->
      <div class="stats-cards">
        <div class="stat-card">
          <div class="stat-title">场馆总数</div>
          <div class="stat-value" :class="total_venue_count > 100 ? 'warning' : 'normal'">
            {{ total_venue_count }} <span class="unit">个</span>
          </div>
          <div class="stat-desc">正常运营: {{ total_venue_count - abn_venue_count }} 个</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">预约总量</div>
          <div class="stat-value" :class="reserve_count > 15000 ? 'normal' : 'warning'">
            {{ formatNumber(reserve_count) }} <span class="unit">人次</span>
          </div>
          <div class="stat-desc">平均预约率: {{ reserve_rate_avg }}%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">实时客流</div>
          <div class="stat-value" :class="current_count > 8000 ? 'warning' : 'normal'">
            {{ formatNumber(current_count) }} <span class="unit">人</span>
          </div>
          <div class="stat-desc">客流承载率: {{ ((current_count / (total_venue_count * 100)) * 100).toFixed(1) }}%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">设施故障数</div>
          <div class="stat-value" :class="abn_venue_count > 10 ? 'danger' : abn_venue_count > 5 ? 'warning' : 'normal'">
            {{ abn_venue_count }} <span class="unit">个</span>
          </div>
          <div class="stat-desc">设施故障率: {{ fault_rate }}%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">投诉办结率</div>
          <div class="stat-value" :class="complete_rate > 90 ? 'normal' : complete_rate > 80 ? 'warning' : 'danger'">
            {{ complete_rate }} <span class="unit">%</span>
          </div>
          <div class="stat-desc">最近更新: {{ last_update_time }}</div>
        </div>
      </div>

      <!-- 图表行：左侧预约趋势折线图 + 右侧运营率TOP5柱状图 -->
      <div class="chart-row">
        <!-- 左侧：场馆预约趋势折线图（1小时粒度） -->
        <div class="panel trend-panel" ref="trendPanel">
          <div class="panel-header">
            <h2>场馆预约趋势（近24小时）</h2>
            <div class="header-actions">
              <el-select v-model="selectedVenue" placeholder="选择场馆" size="small" @change="handleVenueChange">
                <el-option label="全部场馆" value="" />
                <el-option
                  v-for="venue in venueList"
                  :key="venue.venue_name"
                :label="venue.venue_name"
                :value="venue.venue_name"
                />
              </el-select>
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('trendPanel')">
                <el-icon color="#13ce66" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartLine
              :xAxis="venueReserveTrendXAxis"
            :series="venueReserveTrendSeries"
            :yAxisName="''"
            :baseFontScale="1"
            />
          </div>
          <div class="panel-footer"></div>
        </div>

        <!-- 右侧：场馆运营率TOP5柱状图 -->
        <div class="panel top5-panel" ref="top5Panel">
          <div class="panel-header">
            <h2>场馆运营率TOP5</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('top5Panel')">
                <el-icon color="#13ce66" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartBar
              :data="venueOperationTop5Data"
            :baseFontScale="1"
            />
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <!-- 底部：设施故障列表 + 投诉列表 -->
      <div class="bottom-row">
        <!-- 左侧：设施故障列表 -->
        <div class="panel fault-panel" ref="faultPanel">
          <div class="panel-header">
            <h2>设施故障列表（总数：{{ venueFaultList.length }} 个）</h2>
            <div class="header-actions">
              <el-select v-model="faultLevel" placeholder="故障等级" size="small">
                <el-option label="全部" value="" />
                <el-option label="高风险" value="high" />
                <el-option label="中风险" value="medium" />
                <el-option label="低风险" value="low" />
              </el-select>
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('faultPanel')">
                <el-icon color="#13ce66" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body table-scroll">
            <el-table
              :data="filteredVenueFaultList"
            border
            size="small"
            style="width: 100%"
            >
            <el-table-column prop="fault_id" label="故障ID" />
            <el-table-column prop="venue_name" label="所属场馆" />
            <el-table-column prop="facility_name" label="故障设施" />
            <el-table-column prop="fault_type" label="故障类型" />
            <el-table-column prop="fault_level" label="故障等级">
              <template #default="scope">
                <el-tag :type="getFaultLevelTagType(scope.row.fault_level)">
                  {{ getFaultLevelName(scope.row.fault_level) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="handle_status" label="处理状态">
              <template #default="scope">
                <el-tag :type="getFaultStatusTagType(scope.row.handle_status)">
                  {{ getFaultStatusName(scope.row.handle_status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="occur_time" label="发生时间" width="160" />
            </el-table>
          </div>
          <div class="panel-footer"></div>
        </div>

        <!-- 右侧：投诉列表 -->
        <div class="panel complaint-panel" ref="complaintPanel">
          <div class="panel-header">
            <h2>投诉列表（总数：{{ venueComplaintList.length }} 条）</h2>
            <div class="header-actions">
              <el-select v-model="complaintStatus" placeholder="办结状态" size="small">
                <el-option label="全部" value="" />
                <el-option label="已办结" value="handled" />
                <el-option label="处理中" value="processing" />
                <el-option label="未办结" value="pending" />
              </el-select>
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('complaintPanel')">
                <el-icon color="#13ce66" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body table-scroll">
            <el-table
              :data="filteredVenueComplaintList"
            border
            size="small"
            style="width: 100%"
            >
            <el-table-column prop="complaint_id" label="投诉ID" />
            <el-table-column prop="venue_name" label="所属场馆" />
            <el-table-column prop="complaint_content" label="投诉内容" />
            <el-table-column prop="complaint_type" label="投诉类型" />
            <el-table-column prop="complete_status" label="办结状态">
              <template #default="scope">
                <el-tag :type="getComplaintStatusTagType(scope.row.complete_status)">
                  {{ getComplaintStatusName(scope.row.complete_status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="create_time" label="投诉时间" width="160" />
            </el-table>
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, getCurrentInstance, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import screenFull from 'screenfull';
import ChartLine from './ChartLine1.vue';
import ChartBar from './ChartBar3.vue';
import { FullScreen } from "@element-plus/icons-vue";

// 导入文体场馆API
import {
  fetchVenueOverview,
  fetchVenueReserveTrend,
  fetchVenueOperationTop5,
  fetchVenueFaultList,
  fetchVenueComplaintList,
  fetchVenueList
} from '@/api/overview/culturesportstourism/SubdomainScenarioTopics.js';

const router = useRouter();
const instance = getCurrentInstance();
let venueRefreshTimer = null;

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
const selectedVenue = ref('');       // 选中的场馆（用于筛选趋势图）
const faultLevel = ref('');          // 故障等级筛选
const complaintStatus = ref('');     // 投诉办结状态筛选

// 核心指标数据变量
const total_venue_count = ref(0);     // 场馆总数
const reserve_count = ref(0);        // 预约总量
const current_count = ref(0);        // 实时客流
const abn_venue_count = ref(0);       // 设施故障数
const complete_rate = ref(0);        // 投诉办结率
const reserve_rate_avg = ref(0);     // 平均预约率
const fault_rate = ref(0);           // 设施故障率
const last_update_time = ref('');    // 最近更新时间

// 图表/列表数据变量
const venueReserveTrendXAxis = ref([]);   // 预约趋势X轴
const venueReserveTrendSeries = ref([]);  // 预约趋势系列
const venueOperationTop5Data = ref({});   // 运营率TOP5数据
const venueList = ref([]);                // 场馆列表（用于筛选）
const venueFaultList = ref([]);           // 设施故障列表
const venueComplaintList = ref([]);       // 投诉列表

// 故障等级名称映射
const getFaultLevelName = (level) => {
  const map = {
    'high': '高风险',
    'medium': '中风险',
    'low': '低风险'
  };
  return map[level] || '未知等级';
};

// 故障等级标签样式映射（绿色主题）
const getFaultLevelTagType = (level) => {
  const map = {
    'high': 'danger',    // 高风险标红
    'medium': 'warning', // 中风险标橙
    'low': 'success'     // 低风险标绿
  };
  return map[level] || 'default';
};

// 故障处理状态名称映射
const getFaultStatusName = (status) => {
  const map = {
    'handled': '已处理',
    'processing': '处理中',
    'pending': '未处理'
  };
  return map[status] || '未知状态';
};

// 故障处理状态标签样式映射
const getFaultStatusTagType = (status) => {
  const map = {
    'handled': 'success',
    'processing': 'info',
    'pending': 'danger'
  };
  return map[status] || 'default';
};

// 投诉办结状态名称映射
const getComplaintStatusName = (status) => {
  const map = {
    'handled': '已办结',
    'processing': '处理中',
    'pending': '未办结'
  };
  return map[status] || '未知状态';
};

// 投诉办结状态标签样式映射（未办结标橙）
const getComplaintStatusTagType = (status) => {
  const map = {
    'handled': 'success',    // 已办结标绿
    'processing': 'info',    // 处理中标蓝
    'pending': 'warning'     // 未办结标橙
  };
  return map[status] || 'default';
};

// 筛选后的故障列表
const filteredVenueFaultList = computed(() => {
  return venueFaultList.value.filter(item => {
    return !faultLevel.value || item.fault_level === faultLevel.value;
  });
});

// 筛选后的投诉列表
const filteredVenueComplaintList = computed(() => {
  return venueComplaintList.value.filter(item => {
    return !complaintStatus.value || item.complete_status === complaintStatus.value;
  });
});

// 切换场馆筛选趋势图
const handleVenueChange = async () => {
  const trendData = await fetchVenueReserveTrend(selectedVenue.value);
  venueReserveTrendXAxis.value = trendData.xAxis;
  venueReserveTrendSeries.value = trendData.series;
};

// 刷新数据
const refreshVenueData = async () => {
  try {
    // 加载概览数据
    const overviewData = await fetchVenueOverview();
    total_venue_count.value = overviewData.total_venue_count;
    reserve_count.value = overviewData.reserve_count;
    current_count.value = overviewData.current_count;
    abn_venue_count.value = overviewData.abn_venue_count;
    complete_rate.value = overviewData.complete_rate;
    reserve_rate_avg.value = overviewData.reserve_rate_avg;
    fault_rate.value = overviewData.fault_rate;
    last_update_time.value = overviewData.last_update_time;

    // 加载预约趋势数据
    const trendData = await fetchVenueReserveTrend(selectedVenue.value);
    venueReserveTrendXAxis.value = trendData.xAxis;
    venueReserveTrendSeries.value = trendData.series;

    // 加载运营率TOP5数据
    const top5Data = await fetchVenueOperationTop5();
    venueOperationTop5Data.value = top5Data;

    // 加载场馆列表
    venueList.value = await fetchVenueList();

    // 加载故障列表
    venueFaultList.value = await fetchVenueFaultList();

    // 加载投诉列表
    venueComplaintList.value = await fetchVenueComplaintList();

    ElMessage.success('文体场馆数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
    console.error(error);
  }
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/culturesportstourism/dpzl');
};

// 初始化数据
const initVenueData = async () => {
  await refreshVenueData();
  // 开启实时更新（3分钟一次）
  venueRefreshTimer = setInterval(refreshVenueData, 180000);
};

// 组件挂载时初始化
onMounted(async () => {
  await initVenueData();
});

// 组件卸载时清理定时器和事件
onUnmounted(() => {
  if (venueRefreshTimer) {
    clearInterval(venueRefreshTimer);
  }
  window.removeEventListener('resize', () => {});
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
    background: rgba(19, 206, 102, 0.2); // 绿色hover
  }
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.2vh 0;
  border-bottom: 0.2vh solid rgba(19, 206, 102, 0.3);
  margin-bottom: 1.2vw;
  position: relative;

  &::after {
    content: '';
    position: absolute;
    bottom: -0.3vh;
    left: 0;
    width: 16vw;
    height: 0.3vh;
    background: #13ce66; // 绿色下划线
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
    --el-button-text-color: #13ce66;
    --el-button-bg-color: rgba(19, 206, 102, 0.2); // 绿色主题按钮
    --el-button-border-color: #13ce66;
    --el-button-hover-bg-color: rgba(19, 206, 102, 0.4);
    --el-button-hover-border-color: #13ce66;
    --el-button-active-bg-color: rgba(19, 206, 102, 0.5);
    --el-button-active-border-color: #13ce66;
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

// 核心指标卡片组（5个）
.stats-cards {
  display: flex;
  gap: 1.2vw;
  flex-wrap: wrap;

  .stat-card {
    flex: 1;
    background: rgba(16, 32, 80, 0.5);
    border-radius: 8px;
    padding: 1.2vw;
    border: 1px solid rgba(19, 206, 102, 0.3);
    box-shadow: 0 0 1vw rgba(19, 206, 102, 0.15);
    transition: all 0.3s ease;

    &:hover {
      transform: translateY(-3px);
      box-shadow: 0 0 20px rgba(19, 206, 102, 0.25); // 绿色阴影
      border-color: #13ce66;
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
        background: #13ce66;
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
  }
}

// 图表行布局（预约趋势+运营率TOP5）
.chart-row {
  display: flex;
  gap: 1.2vw;
  height: 28vh;

  .trend-panel {
    flex: 1;
  }

  .top5-panel {
    flex: 1;
  }
}

// 底部行布局（故障列表+投诉列表）
.bottom-row {
  display: flex;
  gap: 1.2vw;
  height: 40vh;

  .fault-panel {
    flex: 1;
  }

  .complaint-panel {
    flex: 1;
  }
}

// 面板通用样式
.panel {
  position: relative;
  border: 1px solid rgba(19, 206, 102, 0.3);
  background: rgba(10, 16, 100, 0.6);
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  min-height: 0;
  box-shadow: 0 0 1vw rgba(19, 206, 102, 0.15);
  overflow: hidden;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 3px;
    background: linear-gradient(90deg, #13ce66, rgba(19, 206, 102, 0.3)); // 绿色渐变
  }

  .panel-header {
    padding: 1vh 1.2vw;
    border-bottom: 1px solid rgba(19, 206, 102, 0.1);
    display: flex;
    justify-content: space-between;
    align-items: center;

    h2 {
      margin: 0;
      font-size: 1.1vw;
      color: #13ce66;
      font-weight: 600;
      display: flex;
      align-items: center;
    }

    .header-actions {
      display: flex;
      gap: 0.8vw;
      align-items: center;

      .el-select {
        --el-select-text-color: #999999;
        --el-select-placeholder-color: #666666;
        --el-select-dropdown-bg-color: rgba(16, 32, 80, 0.5);
        --el-select-dropdown-border-color: rgba(19, 206, 102, 0.3);
        --el-select-dropdown-item-hover-bg-color: rgba(19, 206, 102, 0.2);
        --el-select-dropdown-item-text-color: #999999;
        font-size: 0.7vw;
        width: 12vw;
      }
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
        background: #13ce66;
        border-radius: 3px;
      }

      &::-webkit-scrollbar-thumb:hover {
        background: #00b42a; // 深绿
      }
    }
  }

  .panel-footer {
    height: 1vh;
    border-top: 1px solid rgba(19, 206, 102, 0.1);
  }
}

// 表格全局样式
::v-deep .el-table {
  --el-table-bg-color: transparent;
  --el-table-text-color: #999999;
  --el-table-border-color: rgba(19, 206, 102, 0.1);
  --el-table-header-text-color: #13ce66;
  --el-table-header-bg-color: rgba(16, 32, 64, 0.8);
  --el-table-row-hover-bg-color: rgba(19, 206, 102, 0.1);
  --el-table-row-stripes-bg-color: rgba(16, 32, 64, 0.3);
  border-radius: 4px;
  overflow: hidden;
  height: 100%;

  .el-table__header {
    border-bottom: 1px solid rgba(19, 206, 102, 0.3);
  }

  .el-table__cell {
    border-bottom: 1px solid rgba(19, 206, 102, 0.1);
    padding: 1vh 0.8vw !important;
  }

  .el-tag {
    font-size: 0.75vw;
    padding: 0.2vh 0.6vw;
    border-radius: 12px;
  }

  th {
    background-color: rgba(0, 40, 80, 0.8);
    color: #13ce66;
    font-size: 0.8vw;
    padding: 0.5vw 0;
    text-align: center;
    border-color: rgba(19, 206, 102, 0.3);
    font-weight: 500;
  }

  td {
    background-color: transparent;
    color: #b3d9ff;
    font-size: 0.7vw;
    border-bottom: 1px solid rgba(19, 206, 102, 0.15);
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
      background-color: rgba(19, 206, 102, 0.3) !important; // 绿色hover
      color: #ffffff;
    }
  }

  .el-table__row--current > td {
    background-color: rgba(19, 206, 102, 0.4) !important;
    color: #ffffff;
  }

  .el-table__empty-text {
    color: #13ce66;
    background-color: rgba(0, 30, 60, 0.4);
  }

  &::before {
    background-color: rgba(19, 206, 102, 0.2);
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
