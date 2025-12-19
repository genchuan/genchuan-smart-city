<template>
  <div class="page-container">
    <!-- 页头区域：标题及筛选操作区 -->
    <div class="page-header">
      <h1>饮用水水源地专题视图</h1>
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
          <div class="stat-title">水源地总数</div>
          <div class="stat-value">{{ totalSources }}</div>
          <div class="stat-desc">地表水: {{ surfaceCount }} | 地下水: {{ groundCount }}</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">本月达标率</div>
          <div class="stat-value" :class="qualifiedRate >= 95 ? 'normal' : 'warning'">
            {{ qualifiedRate }}%
          </div>
          <div class="stat-desc">较上月 {{ rateChange > 0 ? '↑' : '↓' }}{{ Math.abs(rateChange) }}%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">超标水源地</div>
          <div class="stat-value" :class="unqualifiedCount > 0 ? 'danger' : 'normal'">
            {{ unqualifiedCount }} 个
          </div>
          <div class="stat-desc">主要超标项: {{ mainPollutant }}</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">监测频次</div>
          <div class="stat-value">{{ monitorFrequency }}</div>
          <div class="stat-desc">最近监测: {{ lastMonitorTime }}</div>
        </div>
      </div>

      <!-- 图表行：左右分栏布局 -->
      <div class="chart-row">
        <!-- 左侧：达标率趋势折线图 -->
        <div class="panel trend-panel">
          <div class="panel-header">
            <h2>达标率趋势（近6个月）</h2>
          </div>
          <div class="panel-body">
            <ChartLine
              :xAxis="trendXAxis"
              :series="trendSeries"
              :yAxisName="'达标率 (%)'"
              height="240px"
              style="margin-top: 20px"
            />
          </div>
          <div class="panel-footer"></div>
        </div>

        <!-- 右侧：水源地类型分布柱状图 -->
        <div class="panel type-bar-panel">
          <div class="panel-header">
            <h2>水源地类型分布</h2>
          </div>
          <div class="panel-body">
            <ChartBar
              :data="typeBarData"
              height="280px"
              style="margin-top: 20px"
            />
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <!-- 水源地详细数据表格 -->
      <div class="panel data-table-panel" ref="dataTablePanel">
        <div class="panel-header">
          <h2>水源地水质监测数据</h2>
          <div class="header-actions">
            <!-- 时间范围筛选器 -->
            <el-select v-model="timeRange" placeholder="时间范围" size="small">
              <el-option label="今日" value="today" />
              <el-option label="本周" value="week" />
              <el-option label="本月" value="month" />
            </el-select>
            <!-- 水源类型筛选器 -->
            <el-select v-model="sourceType" placeholder="水源类型" size="small">
              <el-option label="全部" value="" />
              <el-option label="地表水" value="surface" />
              <el-option label="地下水" value="ground" />
            </el-select>
            <!-- 达标状态筛选器 -->
            <el-select v-model="qualificationStatus" placeholder="达标状态" size="small">
              <el-option label="全部" value="" />
              <el-option label="达标" value="qualified" />
              <el-option label="不达标" value="unqualified" />
            </el-select>
            <el-input
              v-model="searchKeyword"
              placeholder="搜索水源地名称"
              size="small"
              style="width: 30vw"
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
            :data="filteredSourceData"
            border
            size="small"
            style="width: 100%"
          >
            <el-table-column prop="name" label="水源地名称" />
            <el-table-column prop="type" label="类型">
              <template #default="scope">
                <el-tag :type="scope.row.type === 'surface' ? 'info' : 'primary'">
                  {{ scope.row.type === 'surface' ? '地表水' : '地下水' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="region" label="所属区域" />
            <el-table-column prop="ph" label="pH值" />
            <el-table-column prop="dissolved_oxygen" label="溶解氧 (mg/L)" />
            <el-table-column prop="total_coliform" label="总大肠菌群 (MPN/100mL)" />
            <el-table-column prop="status" label="状态">
              <template #default="scope">
                <el-tag :type="scope.row.status === 'qualified' ? 'success' : 'danger'">
                  {{ scope.row.status === 'qualified' ? '达标' : '不达标' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="last_check" label="最近检测时间" />
            <el-table-column label="操作">
              <template #default="scope">
                <el-button size="small" link @click="showSourceDetail(scope.row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="panel-footer"></div>
      </div>
    </div>

    <!-- 水源地详情弹窗 -->
    <el-dialog
      v-model="sourceDetailVisible"
      :title="currentSource?.name || '水源地详情'"
      width="700px"
    >
      <div class="source-detail">
        <!-- 基础信息区域 -->
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="水源地ID">{{ currentSource?.source_id }}</el-descriptions-item>
            <el-descriptions-item label="类型">{{ currentSource?.type === 'surface' ? '地表水' : '地下水' }}</el-descriptions-item>
            <el-descriptions-item label="所属区域">{{ currentSource?.region }}</el-descriptions-item>
            <el-descriptions-item label="供应人口">{{ currentSource?.population_served }} 万人</el-descriptions-item>
            <el-descriptions-item label="面积">{{ currentSource?.area }} 平方公里</el-descriptions-item>
            <el-descriptions-item label="建成时间">{{ currentSource?.build_time }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 水质指标区域 -->
        <div class="detail-section">
          <h3>水质指标（最近一次检测）</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="pH值">{{ currentSource?.ph }}</el-descriptions-item>
            <el-descriptions-item label="溶解氧">{{ currentSource?.dissolved_oxygen }} mg/L</el-descriptions-item>
            <el-descriptions-item label="总大肠菌群">{{ currentSource?.total_coliform }} MPN/100mL</el-descriptions-item>
            <el-descriptions-item label="浑浊度">{{ currentSource?.turbidity }} NTU</el-descriptions-item>
            <el-descriptions-item label="高锰酸盐指数">{{ currentSource?.cod_mn }} mg/L</el-descriptions-item>
            <el-descriptions-item label="氨氮">{{ currentSource?.ammonia_nitrogen }} mg/L</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 超标详情区域（仅超标水源地显示） -->
        <div class="detail-section" v-if="currentSource?.status === 'unqualified'">
          <h3>超标详情</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="超标指标">{{ currentSource?.unqualified_item }}</el-descriptions-item>
            <el-descriptions-item label="实测值">{{ currentSource?.actual_value }}{{ currentSource?.unit }}</el-descriptions-item>
            <el-descriptions-item label="标准限值">{{ currentSource?.standard_limit }}{{ currentSource?.unit }}</el-descriptions-item>
            <el-descriptions-item label="处理措施">{{ currentSource?.treatment_measures || '未制定' }}</el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
// 导入Vue核心模块
import {ref, computed, onMounted} from 'vue';
import {useRouter} from 'vue-router';
import {ElMessage} from 'element-plus';

// 导入全屏工具
import screenFull from 'screenfull';

// 导入组件和图标
import ChartLine from './ChartLine2.vue';
import ChartBar from './ChartBar2.vue';
import {FullScreen} from "@element-plus/icons-vue";

// 导入API接口
import {
  fetchWaterSourceOverview,
  fetchWaterSourceTrend,
  fetchWaterSourceDistribution,
  fetchWaterSources,
  fetchWaterSourceData,
  fetchWaterSourceDetailTrend
} from '@/api/overview/ecologicalprotection/GlobalSituationOverview.js';

// 获取当前组件实例（用于访问ref）
const instance = getCurrentInstance();

// 路由实例
const router = useRouter();

// 面板全屏切换功能
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
const sourceType = ref('');              // 水源类型筛选
const qualificationStatus = ref('');     // 达标状态筛选
const searchKeyword = ref('');           // 搜索关键词

// 核心指标数据变量
const totalSources = ref(0);             // 水源地总数
const surfaceCount = ref(0);             // 地表水数量
const groundCount = ref(0);              // 地下水数量
const qualifiedRate = ref(0);            // 本月达标率
const rateChange = ref(0);               // 达标率环比变化
const unqualifiedCount = ref(0);         // 超标水源地数量
const mainPollutant = ref('');           // 主要超标项
const monitorFrequency = ref('');        // 监测频次
const lastMonitorTime = ref('');         // 最近监测时间

// 水源地数据变量
const waterSources = ref([]);            // 水源地列表
const currentSource = ref(null);         // 当前选中的水源地详情
const sourceDetailVisible = ref(false);  // 详情弹窗显示状态
const sourceTrendXAxis = ref([]);        // 水源地趋势图X轴
const sourceTrendSeries = ref([]);       // 水源地趋势图数据

// 图表数据变量
const trendXAxis = ref([]);              // 达标率趋势图X轴
const trendSeries = ref([{name: '达标率', data: []}]); // 达标率趋势图数据
const typeBarData = ref({});             // 水源地类型分布柱状图数据

// 表格数据变量
const sourceData = ref([]);              // 水源地监测数据表格

// 筛选后的水源地列表（计算属性）
const filteredSources = computed(() => {
  return waterSources.value.filter(source => {
    const matchesType = !sourceType.value || source.type === sourceType.value;
    const matchesStatus = !qualificationStatus.value || source.status === qualificationStatus.value;
    return matchesType && matchesStatus;
  });
});

// 筛选后的表格数据（计算属性）
const filteredSourceData = computed(() => {
  return sourceData.value.filter(item => {
    const matchesType = !sourceType.value || item.type === sourceType.value;
    const matchesStatus = !qualificationStatus.value || item.status === qualificationStatus.value;
    const matchesKeyword = !searchKeyword.value || item.name.includes(searchKeyword.value);
    return matchesType && matchesStatus && matchesKeyword;
  });
});

// 获取水源地类型样式
const getSourceTypeClass = (type) => {
  return type === 'surface' ? 'surface-water' : 'ground-water';
};

// 显示水源地详情
const showSourceDetail = async (source) => {
  currentSource.value = {...source};
  // 从API获取该水源地的趋势数据
  const trendData = await fetchWaterSourceDetailTrend(source.source_id);
  sourceTrendXAxis.value = trendData.xAxis;
  sourceTrendSeries.value = trendData.series;
  sourceDetailVisible.value = true;
};

// 刷新数据
const refreshData = async () => {
  try {
    await initData();
    ElMessage.success('饮用水水源地数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
    console.error(error);
  }
};

// 导出数据
const exportData = () => {
  ElMessage.success('饮用水水源地数据导出成功');
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/ecologicalprotection/dpzl');
};

// 初始化数据
const initData = async () => {
  // 1. 获取核心指标数据
  const overviewData = await fetchWaterSourceOverview();
  totalSources.value = overviewData.totalSources;
  surfaceCount.value = overviewData.surfaceCount;
  groundCount.value = overviewData.groundCount;
  qualifiedRate.value = overviewData.qualifiedRate;
  rateChange.value = overviewData.rateChange;
  unqualifiedCount.value = overviewData.unqualifiedCount;
  mainPollutant.value = overviewData.mainPollutant;
  monitorFrequency.value = overviewData.monitorFrequency;
  lastMonitorTime.value = overviewData.lastMonitorTime;

  // 2. 获取达标率趋势数据
  const trendData = await fetchWaterSourceTrend();
  trendXAxis.value = trendData.xAxis;
  trendSeries.value = trendData.series;

  // 3. 获取水源地类型分布数据
  const distributionData = await fetchWaterSourceDistribution();
  typeBarData.value = distributionData;

  // 4. 获取水源地列表数据
  waterSources.value = await fetchWaterSources();

  // 5. 获取水源地表格数据
  sourceData.value = await fetchWaterSourceData();
};

// 组件挂载时初始化数据
onMounted(async () => {
  await initData();
});
</script>

<style lang="scss" scoped>
// 复用主页面基础样式
@import url('./common-styles.scss');

.panel {
  position: relative;
  border: 0.2vh solid rgba(25, 186, 139, 0.17);
  background: url("@/assets/chart/images/line(1).png") rgba(255, 255, 255, .04);
  display: flex;
  flex-direction: column;
  min-height: 0;
}

// 最外层容器样式
.page-container {
  width: 100%;
  height: 100vh;
  overflow: auto;
  background: url("@/assets/chart/images/bg.jpg");
  background-size: 100% 100%;
  color: #fff;
  padding: 0 1.5vw;
  box-sizing: border-box;
  touch-action: pan-x pan-y;
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1vh 0;
  border-bottom: 1px solid rgba(0, 204, 255, 0.2);
  margin-bottom: 1vw;

  h1 {
    margin: 0;
    font-size: 1.5vw;
    color: #00ccff;
  }

  .header-actions {
    display: flex;
    gap: 0.4vw;
    align-items: center;
  }
}

.main-content {
  display: flex;
  flex-direction: column;
  gap: 1vw;
}

.stats-cards {
  display: flex;
  gap: 1vw;
}

.stat-card {
  flex: 1;
  background: rgba(0, 30, 60, 0.6);
  border-radius: 8px;
  padding: 1vw;
  border: 1px solid rgba(0, 204, 255, 0.2);

  .stat-title {
    font-size: 1vw;
    color: #ccc;
    margin-bottom: 0.5vw;
  }

  .stat-value {
    font-size: 2vw;
    font-weight: bold;
    margin-bottom: 0.5vh;
    color: #fff;

    &.excellent {
      color: #13ce66;
    }

    &.good {
      color: #00ccff;
    }

    &.light-pollution {
      color: #ff7d00;
    }

    &.moderate-pollution {
      color: #ff4949;
    }

    &.severe-pollution {
      color: #9e0000;
    }
  }

  .stat-desc {
    font-size: 0.8vw;
    color: #999;
  }
}

// 图表行布局（左右分栏）
.chart-row {
  display: flex;
  gap: 20px; // 两图表间距
  height: 300px; // 图表区域高度

  .trend-panel, .type-bar-panel {
    flex: 1; // 左右各占50%宽度
  }
}

// 详情弹窗样式
.detail-section {
  margin-bottom: 20px;

  h3 {
    font-size: 16px;
    color: #00ccff;
    margin-bottom: 10px;
    padding-bottom: 5px;
    border-bottom: 1px solid rgba(0, 204, 255, 0.2);
  }
}

// 状态颜色样式
.normal {
  color: #13ce66;
  font-weight: bold;
}

.warning {
  color: #ff7d00;
  font-weight: bold;
}

.danger {
  color: #ff4949;
  font-weight: bold;
}

::v-deep .el-input .el-input__inner::placeholder {
  font-size: 0.7vw;
}
</style>
