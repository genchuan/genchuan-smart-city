<template>
  <div class="page-container">
    <!-- 页头区域：标题及筛选操作区 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/trafficpass.png"
          alt="通行效率图标"
          class="title-icon"
        />
        通行效率监测视图
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
          <div class="stat-title">总道路里程</div>
          <div class="stat-value">{{ totalRoadLength }} <span class="unit">公里</span></div>
          <div class="stat-desc">快速路: {{ expresswayLength }} | 主干道: {{ arterialLength }} | 其他: {{ otherRoadLength }}</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">平均速度</div>
          <div class="stat-value" :class="getSpeedStatusClass(peakAvgSpeed)">
            {{ peakAvgSpeed }} <span class="unit">km/h</span>
          </div>
          <div class="stat-desc">
            <span
              class="compare-item"
              :class="efficiencyYoY > 0 ? 'increase' : 'decrease'"
            >
              同比 {{ efficiencyYoY > 0 ? '↑' : '↓' }}{{ Math.abs(efficiencyYoY).toFixed(1) }}%
            </span>
            <span
              class="compare-item"
              :class="efficiencyMoM > 0 ? 'increase' : 'decrease'"
            >
              环比 {{ efficiencyMoM > 0 ? '↑' : '↓' }}{{ Math.abs(efficiencyMoM).toFixed(1) }}%
            </span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">拥堵路段数</div>
          <div class="stat-value" :class="congestionRoadCount > 5 ? 'danger' : congestionRoadCount > 0 ? 'warning' : 'normal'">
            {{ congestionRoadCount }} <span class="unit">条</span>
          </div>
          <div class="stat-desc">拥堵率: {{ congestionRate }}% | 主要区域: {{ mainCongestionArea }}</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">拥堵率</div>
          <!-- 核心数值：显示拥堵率+%，根据数值自动切换颜色 -->
          <div class="stat-value" :class="congestionRate > 15 ? 'danger' : congestionRate > 5 ? 'warning' : 'normal'">
            {{ congestionRate }} <span class="unit">%</span>
          </div>
          <!-- 描述：同比+环比变化（和参考代码风格一致） -->
          <div class="stat-desc">
            <span
              class="compare-item"
              :class="congestionYoY > 0 ? 'increase' : 'decrease'"
            >
              同比 {{ congestionYoY > 0 ? '↑' : '↓' }}{{ Math.abs(congestionYoY).toFixed(1) }}%
            </span>
            <span
              class="compare-item"
              :class="congestionMoM > 0 ? 'increase' : 'decrease'"
            >
              环比 {{ congestionMoM > 0 ? '↑' : '↓' }}{{ Math.abs(congestionMoM).toFixed(1) }}%
            </span>
          </div>
        </div>
      </div>

      <!-- 图表行：左右分栏布局 -->
      <div class="chart-row">
        <!-- 左侧：拥堵区域分布柱状图 -->
        <div class="panel congestion-bar-panel" ref="congestionBarPanel">
          <div class="panel-header">
            <h2>拥堵区域分布</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('congestionBarPanel')">
                <el-icon color="#00ccff" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartBar
              :data="congestionBarData"
            />
          </div>
          <div class="panel-footer"></div>
        </div>

        <!-- 右侧：通行效率趋势折线图（速度+拥堵率） -->
        <div class="panel trend-panel" ref="trendPanel">
          <div class="panel-header">
            <h2>通行效率趋势（近6个月）</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('trendPanel')">
                <el-icon color="#00ccff" size="16">
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
      </div>

      <!-- 道路详细数据表格 -->
      <div class="panel data-table-panel" ref="dataTablePanel">
        <div class="panel-header">
          <h2>道路通行监测数据</h2>
          <div class="header-actions">
            <!-- 时间范围筛选器 -->
            <el-select v-model="timeRange" placeholder="时间范围" size="small">
              <el-option label="今日" value="today" />
              <el-option label="本周" value="week" />
              <el-option label="本月" value="month" />
            </el-select>
            <!-- 道路类型筛选器 -->
            <el-select v-model="roadType" placeholder="道路类型" size="small">
              <el-option label="全部" value="" />
              <el-option label="快速路" value="expressway" />
              <el-option label="主干道" value="arterial" />
              <el-option label="次干道" value="collector" />
              <el-option label="支路" value="local" />
            </el-select>
            <!-- 通行状态筛选器 -->
            <el-select v-model="trafficStatus" placeholder="通行状态" size="small">
              <el-option label="全部" value="" />
              <el-option label="畅通" value="smooth" />
              <el-option label="缓行" value="slow" />
              <el-option label="拥堵" value="congested" />
            </el-select>
            <el-input
              v-model="searchKeyword"
              placeholder="搜索道路名称"
              size="small"
              style="width: 15vw"
            />
            <el-button size="small" type="primary" @click="exportData">导出数据</el-button>
            <button class="panel-fullscreen-btn" @click="toggleFullscreen('dataTablePanel')">
              <el-icon color="#00ccff" size="16">
                <FullScreen/>
              </el-icon>
            </button>
          </div>
        </div>
        <!-- 表格滚动容器 -->
        <div class="panel-body table-scroll">
          <el-table
            :data="filteredRoadData"
            border
            size="small"
            style="width: 100%"
          >
            <el-table-column prop="name" label="道路名称" />
            <el-table-column prop="type" label="道路类型">
              <template #default="scope">
                <el-tag :type="getRoadTypeTagType(scope.row.type)">
                  {{ getRoadTypeName(scope.row.type) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="region" label="所属区域" />
            <el-table-column prop="peak_speed" label="高峰速度 (km/h)" />
            <el-table-column prop="offpeak_speed" label="平峰速度 (km/h)" />
            <el-table-column prop="congestion_rate" label="拥堵率 (%)" />
            <el-table-column prop="status" label="通行状态">
              <template #default="scope">
                <el-tag :type="getTrafficStatusTagType(scope.row.status)">
                  {{ getTrafficStatusName(scope.row.status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="last_check" label="最近监测时间" />
            <el-table-column label="操作">
              <template #default="scope">
                <el-button size="small" link @click="showRoadDetail(scope.row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="panel-footer"></div>
      </div>
    </div>

    <!-- 道路详情弹窗 -->
    <el-dialog
      v-model="roadDetailVisible"
      :title="currentRoad?.name || '道路通行详情'"
      width="700px"
    >
      <div class="road-detail">
        <!-- 基础信息区域 -->
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="道路ID">{{ currentRoad?.road_id }}</el-descriptions-item>
            <el-descriptions-item label="道路类型">{{ getRoadTypeName(currentRoad?.type) }}</el-descriptions-item>
            <el-descriptions-item label="所属区域">{{ currentRoad?.region }}</el-descriptions-item>
            <el-descriptions-item label="车道数">{{ currentRoad?.lane_count }} 条</el-descriptions-item>
            <el-descriptions-item label="设计时速">{{ currentRoad?.design_speed }} km/h</el-descriptions-item>
            <el-descriptions-item label="最近监测时间">{{ currentRoad?.last_check }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 通行指标区域 -->
        <div class="detail-section">
          <h3>通行指标（最近一次监测）</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="高峰速度">{{ currentRoad?.peak_speed }} km/h</el-descriptions-item>
            <el-descriptions-item label="平峰速度">{{ currentRoad?.offpeak_speed }} km/h</el-descriptions-item>
            <el-descriptions-item label="拥堵率">{{ currentRoad?.congestion_rate }}%</el-descriptions-item>
            <el-descriptions-item label="通行状态">{{ getTrafficStatusName(currentRoad?.status) }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 拥堵详情区域（仅拥堵道路显示） -->
        <div class="detail-section" v-if="currentRoad?.status === 'congested'">
          <h3>拥堵详情</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="拥堵时段">{{ currentRoad?.congestion_time || '无' }}</el-descriptions-item>
            <el-descriptions-item label="主要原因">{{ currentRoad?.main_cause || '未明确' }}</el-descriptions-item>
            <el-descriptions-item label="改善措施">{{ currentRoad?.improvement_measures || '未制定' }}</el-descriptions-item>
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
import ChartLine from './ChartLine1.vue';
import ChartBar from './ChartBar1.vue';
import { FullScreen } from "@element-plus/icons-vue";

// 保留原API导入
import {
  fetchTrafficEfficiencyOverview,
  fetchTrafficEfficiencyTrend,
  fetchTrafficCongestionDistribution,
  fetchRoadList,
  fetchRoadDetailData,
  fetchRoadDetailTrend
} from '@/api/overview/transportation/GlobalSituationOverview.js';

const router = useRouter();
const instance = getCurrentInstance();

// 面板全屏切换功能（复用参考代码逻辑）
const toggleFullscreen = (panelRefName) => {
  if (!screenFull.isEnabled) {
    ElMessage.warning('您的浏览器不支持全屏功能');
    return;
  }

  // 获取面板DOM元素
  const panel = instance.refs[panelRefName];
  if (!panel) {
    ElMessage.error('未找到面板元素');
    return;
  }

  // 切换全屏状态
  if (screenFull.isFullscreen && document.fullscreenElement === panel) {
    screenFull.exit();
  } else {
    screenFull.request(panel);
  }
};

// 筛选条件变量
const timeRange = ref('month');          // 时间范围筛选
const roadType = ref('');                // 道路类型筛选
const trafficStatus = ref('');           // 通行状态筛选
const searchKeyword = ref('');           // 搜索关键词

// 核心指标数据变量（通行效率专属）
const totalRoadLength = ref(0);
const expresswayLength = ref(0);
const arterialLength = ref(0);
const otherRoadLength = ref(0);
const peakAvgSpeed = ref(0);
const congestionRoadCount = ref(0);
const congestionRate = ref(0);
const efficiencyYoY = ref(0);
const efficiencyMoM = ref(0);
const mainCongestionArea = ref('');
const congestionYoY = ref(0);
const congestionMoM = ref(0);

// 道路数据变量
const roadList = ref([]);
const currentRoad = ref(null);
const roadDetailVisible = ref(false);
const roadTrendXAxis = ref([]);
const roadTrendSeries = ref([]);
const trendXAxis = ref([]);
const trendSeries = ref([]);
const congestionBarData = ref({});
const roadData = ref([]);

// 道路类型名称映射（枚举）
const getRoadTypeName = (type) => {
  const map = {
    'expressway': '快速路',
    'arterial': '主干道',
    'collector': '次干道',
    'local': '支路'
  };
  return map[type] || '未知类型';
};

// 道路类型标签样式映射
const getRoadTypeTagType = (type) => {
  const map = {
    'expressway': 'primary',
    'arterial': 'success',
    'collector': 'info',
    'local': 'warning'
  };
  return map[type] || 'default';
};

// 通行状态名称映射（枚举）
const getTrafficStatusName = (status) => {
  const map = {
    'smooth': '畅通',
    'slow': '缓行',
    'congested': '拥堵'
  };
  return map[status] || '未知状态';
};

// 通行状态标签样式映射
const getTrafficStatusTagType = (status) => {
  const map = {
    'smooth': 'success',
    'slow': 'warning',
    'congested': 'danger'
  };
  return map[status] || 'default';
};

// 高峰速度状态样式（根据速度判断等级）
const getSpeedStatusClass = (speed) => {
  if (speed >= 40) return 'excellent';  // 优秀（快速路水平）
  if (speed >= 30) return 'good';       // 良好（主干道畅通）
  if (speed >= 20) return 'warning';    // 一般（缓行）
  return 'danger';                      // 较差（拥堵）
};

// 筛选后的道路列表（计算属性）
const filteredRoadList = computed(() => {
  return roadList.value.filter(road => {
    const matchesType = !roadType.value || road.type === roadType.value;
    const matchesStatus = !trafficStatus.value || road.status === trafficStatus.value;
    return matchesType && matchesStatus;
  });
});

// 筛选后的表格数据（计算属性）
const filteredRoadData = computed(() => {
  return roadData.value.filter(item => {
    const matchesType = !roadType.value || item.type === roadType.value;
    const matchesStatus = !trafficStatus.value || item.status === trafficStatus.value;
    const matchesKeyword = !searchKeyword.value || item.name.includes(searchKeyword.value);
    return matchesType && matchesStatus && matchesKeyword;
  });
});

// 显示道路详情
const showRoadDetail = async (road) => {
  currentRoad.value = { ...road };
  // 从API获取该道路的趋势数据
  const trendData = await fetchRoadDetailTrend(road.road_id);
  roadTrendXAxis.value = trendData.xAxis;
  roadTrendSeries.value = trendData.series;
  roadDetailVisible.value = true;
};

// 刷新数据
const refreshData = async () => {
  try {
    await initData();
    ElMessage.success('通行效率数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
    console.error(error);
  }
};

// 导出数据
const exportData = () => {
  ElMessage.success('道路通行监测数据导出成功');
};

// 返回总览页（根据项目路由调整）
const handleBack = () => {
  router.push('/overview/transportation/dpzl');
};

// 初始化数据（复用参考代码逻辑，替换为通行效率API）
const initData = async () => {
  const overviewData = await fetchTrafficEfficiencyOverview();
  totalRoadLength.value = overviewData.totalRoadLength;
  peakAvgSpeed.value = overviewData.peakAvgSpeed;
  congestionRoadCount.value = overviewData.congestionRoadCount;
  congestionRate.value = overviewData.congestionRate;
  efficiencyYoY.value = overviewData.efficiencyYoY;
  efficiencyMoM.value = overviewData.efficiencyMoM;
  mainCongestionArea.value = overviewData.mainCongestionArea;
  congestionYoY.value = overviewData.congestionYoY;
  congestionMoM.value = overviewData.congestionMoM;

  expresswayLength.value = (overviewData.totalRoadLength * 0.223).toFixed(1);
  arterialLength.value = (overviewData.totalRoadLength * 0.395).toFixed(1);
  otherRoadLength.value = (overviewData.totalRoadLength * 0.382).toFixed(1);

  const trendData = await fetchTrafficEfficiencyTrend();
  trendXAxis.value = trendData.xAxis;
  trendSeries.value = trendData.series;

  const distributionData = await fetchTrafficCongestionDistribution();
  congestionBarData.value = distributionData;

  roadList.value = await fetchRoadList();

  roadData.value = await fetchRoadDetailData();
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
$text-secondary: #999999;   // 次要文本色
$text-tertiary: #666666;    // 辅助文本色
$bg-main: #0a1064;          // 页面主背景（深科技蓝黑）
$bg-card: rgba(16, 32, 80, 0.5); // 卡片背景（半透明磨砂）
$bg-panel: rgba(10, 16, 100, 0.6); // 面板背景
$border-color: rgba(0, 204, 255, 0.3); // 边框色
$border-light: rgba(0, 204, 255, 0.1); // 浅色边框
$shadow: 0 0 1vw rgba(0, 204, 255, 0.15); // 阴影效果

// 最外层容器样式（主题核心布局）
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
    background: rgba(0, 128, 255, 0.2);
  }
}

// 页头样式
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
    background: #00f2fe;
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
    --el-button-text-color: #8cd5ff;
    --el-button-bg-color: rgba(0, 128, 255, 0.2);
    --el-button-border-color: #8cd5ff;
    --el-button-hover-bg-color: rgba(0, 128, 255, 0.4);
    --el-button-hover-border-color: #8cd5ff;
    --el-button-active-bg-color: rgba(0, 128, 255, 0.5);
    --el-button-active-border-color: #8cd5ff;
    border-radius: 4px;
    font-size: 0.85vw;
    padding: 0.6vh 1.2vw;
  }
}

// 主内容区域
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
    box-shadow: 0 0 20px rgba(0, 204, 255, 0.25);
    border-color: #8cd5ff;
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
      background: orangered;
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
      color: #8cd5ff;
      text-shadow: 0 0 8px rgba(0, 204, 255, 0.3);
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

  // 同比/环比基础样式
  .compare-item {
    font-size: 0.8vw !important;
    padding: 0.2vw 0.4vw;
    border-radius: 4px;
    font-weight: 500;
    color: inherit; // 继承父级颜色，后续通过 increase/decrease 覆盖
  }

  // 增长-绿色（复用你的 $secondary-color 变量）
  .compare-item.increase {
    color: $secondary-color;
    background: rgba(19, 206, 102, 0.2); // 淡绿色背景，和参考代码一致
  }

  // 下降-红色（复用你的 $danger-color 变量）
  .compare-item.decrease {
    color: $danger-color;
    background: rgba(255, 73, 73, 0.2); // 淡红色背景，和参考代码一致
  }
}

// 图表面板通用样式
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
    background: linear-gradient(90deg, #8cd5ff, rgba(0, 204, 255, 0.3));
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
      color: #8cd5ff;
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

// 图表行布局（左右分栏）
.chart-row {
  display: flex;
  gap: 1.2vw;
  height: 28vh; // 图表区域高度

  .trend-panel, .congestion-bar-panel {
    flex: 1; // 左右各占50%宽度
  }
}

// 表格面板样式
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
      --el-select-dropdown-item-hover-bg-color: rgba(0, 128, 255, 0.2);
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
      --el-button-bg-color: #00b3e6;
      --el-button-border-color: #00b3e6;
      --el-button-hover-bg-color: #00b3e6;
      --el-button-hover-border-color: #00b3e6;
      --el-button-active-bg-color: #00749d;
      --el-button-active-border-color: #00749d;
    }
  }

  .table-scroll {
    overflow-y: auto;

    // 滚动条样式优化（完全生效，保留）
    &::-webkit-scrollbar {
      width: 6px;
      height: 6px;
    }

    &::-webkit-scrollbar-track {
      background: rgba(16, 32, 64, 0.5);
      border-radius: 3px;
    }

    &::-webkit-scrollbar-thumb {
      background: #8cd5ff;
      border-radius: 3px;
    }

    &::-webkit-scrollbar-thumb:hover {
      background: #00b3e6;
    }
  }

  ::v-deep .el-table {
    --el-table-bg-color: transparent;
    --el-table-text-color: $text-secondary;
    --el-table-border-color: $border-light;
    --el-table-header-text-color: #8cd5ff;
    --el-table-header-bg-color: rgba(16, 32, 64, 0.8);
    --el-table-row-hover-bg-color: rgba(0, 128, 255, 0.1);
    --el-table-row-stripes-bg-color: rgba(16, 32, 64, 0.3);
    border-radius: 4px;
    overflow: hidden;

    .el-table__header {
      border-bottom: 1px solid $border-color; // 局部表头边框，优先生效
    }

    .el-table__cell {
      border-bottom: 1px solid $border-light; // 局部单元格边框，优先生效
      padding: 1vh 0.8vw !important;
    }

    .el-tag {
      font-size: 0.75vw;
      padding: 0.2vh 0.6vw;
      border-radius: 12px;
    }
  }
}

// 全局表格样式
::v-deep .el-table {
  table-layout: fixed;
  background-color: rgba(0, 30, 60, 0.4);
  border-color: rgba(0, 204, 255, 0.2);

  // 表头样式
  th {
    background-color: rgba(0, 40, 80, 0.8);
    color: #e6f7ff;
    font-size: 0.8vw;
    padding: 0.5vw 0;
    text-align: center;
    border-color: rgba(0, 204, 255, 0.3);
    font-weight: 500;
  }

  // 表格内容单元格样式
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

  // 表格行样式
  .el-table__row {
    background-color: rgba(0, 30, 60, 0.5);
    &:nth-child(even) {
      background-color: rgba(0, 40, 80, 0.5);
    }
    &:hover > td {
      background-color: rgba(0, 122, 255, 0.3) !important;
      color: #ffffff;
    }
  }

  // 选中行样式
  .el-table__row--current > td {
    background-color: rgba(0, 122, 255, 0.4) !important;
    color: #ffffff;
  }

  // 空数据提示样式
  .el-table__empty-text {
    color: #8cbfff;
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

// 道路类型/状态标签样式映射（主题化）
.el-tag--primary {
  --el-tag-bg-color: rgba(0, 128, 255, 0.2);
  --el-tag-border-color: #0080ff;
  --el-tag-text-color: #00b3ff;
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

.el-tag--warning {
  --el-tag-bg-color: rgba(255, 125, 0, 0.2);
  --el-tag-border-color: $warning-color;
  --el-tag-text-color: $warning-color;
}

.el-tag--danger {
  --el-tag-bg-color: rgba(255, 73, 73, 0.2);
  --el-tag-border-color: $danger-color;
  --el-tag-text-color: $danger-color;
}

// 状态颜色通用样式
.normal {
  color: $secondary-color;
  font-weight: bold;
}

.warning {
  color: $warning-color;
  font-weight: bold;
}

.danger {
  color: $danger-color;
  font-weight: bold;
}

// 详情弹窗样式
.road-detail {
  .detail-section {
    margin-bottom: 24px;

    h3 {
      font-size: 16px;
      color: #8cd5ff;
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

// 弹窗样式优化
.el-dialog {
  --el-dialog-bg-color: $bg-panel;
  --el-dialog-border-color: $border-color;
  --el-dialog-title-color: #8cd5ff;
  --el-dialog-text-color: $text-secondary;
  --el-dialog-header-border-color: $border-light;
  --el-dialog-footer-border-color: $border-light;
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(0, 204, 255, 0.2);

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
