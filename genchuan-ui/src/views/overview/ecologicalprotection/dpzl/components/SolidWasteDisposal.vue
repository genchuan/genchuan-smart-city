<template>
  <div class="page-container">
    <!-- 页头区域：标题及筛选操作区 -->
    <div class="page-header">
      <h1>固废处置专题视图</h1>
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
          <div class="stat-title">固废产生总量</div>
          <div class="stat-value">{{ totalWaste }} 吨</div>
          <div class="stat-desc">较上月 {{ totalChange > 0 ? '↑' : '↓' }}{{ Math.abs(totalChange) }}%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">处置总量</div>
          <div class="stat-value">{{ disposedWaste }} 吨</div>
          <div class="stat-desc">处置率 {{ disposalRate }}%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">无害化处置量</div>
          <div class="stat-value">{{ harmlessWaste }} 吨</div>
          <div class="stat-desc">无害化率 {{ harmlessRate }}%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">处置设施数量</div>
          <div class="stat-value">{{ facilityCount }} 座</div>
          <div class="stat-desc">运行中: {{ runningFacility }} 座</div>
        </div>
      </div>

      <!-- 图表行：固废类型分布与处置趋势 -->
      <div class="chart-row">
        <!-- 左侧：固废类型分布饼图 -->
        <div class="panel type-distribution-panel">
          <div class="panel-header">
            <h2>固废类型分布</h2>
          </div>
          <div class="panel-body">
            <ChartPie
              :data="typePieData"
              height="240px"
              style="margin-top: 20px"
            />
          </div>
          <div class="panel-footer"></div>
        </div>

        <!-- 右侧：处置量趋势折线图 -->
        <div class="panel disposal-trend-panel">
          <div class="panel-header">
            <h2>处置量趋势（近6个月）</h2>
          </div>
          <div class="panel-body">
            <ChartLine
              :xAxis="trendXAxis"
              :series="trendSeries"
              :yAxisName="'处置量 (吨)'"
              height="240px"
              style="margin-top: 10px"
            />
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <!-- 固废处置详细数据表格 -->
      <div class="panel data-table-panel" ref="dataTablePanel">
        <div class="panel-header">
          <h2>固废处置详情</h2>
          <div class="header-actions">
            <!-- 时间范围筛选器 -->
            <el-select v-model="timeRange" placeholder="时间范围" size="small">
              <el-option label="今日" value="today" />
              <el-option label="本周" value="week" />
              <el-option label="本月" value="month" />
              <el-option label="全年" value="year" />
            </el-select>
            <!-- 固废类型筛选器 -->
            <el-select v-model="wasteType" placeholder="固废类型" size="small">
              <el-option label="全部" value="" />
              <el-option label="生活垃圾" value="domestic" />
              <el-option label="工业固废" value="industrial" />
              <el-option label="危险废物" value="hazardous" />
              <el-option label="医疗废物" value="medical" />
            </el-select>
            <el-input
              v-model="searchKeyword"
              placeholder="搜索区域或设施"
              size="small"
              style="width: 24vw"
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
            :data="filteredWasteData"
            border
            size="small"
            style="width: 100%"
          >
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="type" label="固废类型">
              <template #default="scope">
                <el-tag :type="getTypeTagType(scope.row.type)">
                  {{ getTypeName(scope.row.type) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="generation" label="产生量 (吨)" />
            <el-table-column prop="disposal" label="处置量 (吨)" />
            <el-table-column prop="disposal_rate" label="处置率">
              <template #default="scope">
                <el-progress
                  :percentage="parseFloat(scope.row.disposal_rate)"
                  :stroke-width="6"
                  :color="getRateColor(scope.row.disposal_rate)"
                  :show-text="false"
                />
                <span style="margin-left: 5px">{{ scope.row.disposal_rate }}%</span>
              </template>
            </el-table-column>
            <el-table-column prop="disposal_method" label="处置方式" />
            <el-table-column prop="main_facility" label="主要处置设施" />
            <el-table-column prop="update_time" label="更新时间" />
            <el-table-column label="操作">
              <template #default="scope">
                <el-button size="small" link @click="showDisposalDetail(scope.row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="panel-footer"></div>
      </div>
    </div>

    <!-- 处置设施详情弹窗 -->
    <el-dialog
      v-model="facilityDetailVisible"
      :title="currentFacility?.name || '处置设施详情'"
      width="700px"
    >
      <div class="facility-detail">
        <!-- 设施基础信息区域 -->
        <div class="detail-section">
          <h3>设施信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="设施ID">{{ currentFacility?.id }}</el-descriptions-item>
            <el-descriptions-item label="类型">{{ getTypeName(currentFacility?.type) }}</el-descriptions-item>
            <el-descriptions-item label="所属区域">{{ currentFacility?.region }}</el-descriptions-item>
            <el-descriptions-item label="地址">{{ currentFacility?.address }}</el-descriptions-item>
            <el-descriptions-item label="设计日处理能力">{{ currentFacility?.daily_capacity }} 吨/日</el-descriptions-item>
            <el-descriptions-item label="实际日处理量">{{ currentFacility?.actual_daily }} 吨/日</el-descriptions-item>
            <el-descriptions-item label="运行状态">{{ currentFacility?.status === 'running' ? '运行中' : '停运中' }}</el-descriptions-item>
            <el-descriptions-item label="投用时间">{{ currentFacility?.commissioning_time }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 处理工艺与标准区域 -->
        <div class="detail-section">
          <h3>处理工艺与标准</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="主要处理工艺">{{ currentFacility?.technology }}</el-descriptions-item>
            <el-descriptions-item label="排放标准">{{ currentFacility?.discharge_standard }}</el-descriptions-item>
            <el-descriptions-item label="最近检测时间">{{ currentFacility?.last_inspection }}</el-descriptions-item>
            <el-descriptions-item label="运营单位">{{ currentFacility?.operator }}</el-descriptions-item>
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
import ChartLine from './ChartLine4.vue';
import ChartPie from './ChartPie4.vue';
import {FullScreen} from "@element-plus/icons-vue";

// 导入API接口
import {
  fetchSolidWasteStats,
  fetchSolidWasteTypeDistribution,
  fetchSolidWasteTrend,
  fetchSolidWasteFacilities,
  fetchSolidWasteDetailData,
  fetchSolidWasteFacilityTrend
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
const timeRange = ref('month');        // 时间范围筛选
const wasteType = ref('');             // 固废类型筛选
const searchKeyword = ref('');         // 搜索关键词

// 核心指标数据变量
const totalWaste = ref(0);             // 固废产生总量
const totalChange = ref(0);            // 较上月变化率
const disposedWaste = ref(0);          // 处置总量
const disposalRate = ref(0);           // 处置率
const harmlessWaste = ref(0);          // 无害化处置量
const harmlessRate = ref(0);           // 无害化率
const facilityCount = ref(0);          // 处置设施数量
const runningFacility = ref(0);        // 运行中设施数量

// 处置设施数据变量
const facilities = ref([]);            // 处置设施列表
const currentFacility = ref(null);     // 当前选中的设施详情
const facilityDetailVisible = ref(false); // 详情弹窗显示状态
const facilityTrendXAxis = ref([]);    // 设施趋势图X轴
const facilityTrendSeries = ref([]);   // 设施趋势图数据

// 图表数据变量
const typePieData = ref({              // 固废类型分布饼图数据
  series: [{
    name: '产生量',
    data: []
  }]
});
const trendXAxis = ref([]);            // 处置趋势图X轴
const trendSeries = ref([              // 处置趋势图数据
  {name: '产生量', data: []},
  {name: '处置量', data: []}
]);

// 表格数据变量
const wasteData = ref([]);             // 固废处置详情表格数据

// 筛选后的设施列表（计算属性）
const filteredFacilities = computed(() => {
  return facilities.value.filter(facility => {
    return !wasteType.value || facility.type === wasteType.value;
  });
});

// 筛选后的表格数据（计算属性）
const filteredWasteData = computed(() => {
  return wasteData.value.filter(item => {
    const matchesType = !wasteType.value || item.type === wasteType.value;
    const matchesKeyword = !searchKeyword.value
      || item.region.includes(searchKeyword.value)
      || item.main_facility.includes(searchKeyword.value);
    return matchesType && matchesKeyword;
  });
});

// 获取固废类型名称（将英文类型转换为中文显示）
const getTypeName = (type) => {
  const typeMap = {
    domestic: '生活垃圾',
    industrial: '工业固废',
    hazardous: '危险废物',
    medical: '医疗废物'
  };
  return typeMap[type] || type;
};

// 获取设施类型样式
const getFacilityTypeClass = (type) => {
  return type;
};

// 获取类型标签样式（根据不同类型返回不同标签样式）
const getTypeTagType = (type) => {
  const typeMap = {
    domestic: 'success',
    industrial: 'info',
    hazardous: 'danger',
    medical: 'warning'
  };
  return typeMap[type] || 'default';
};

// 获取处置率颜色（根据处置率返回不同进度条颜色）
const getRateColor = (rate) => {
  const value = parseFloat(rate);
  if (value < 80) return '#ff4949';
  if (value < 90) return '#ff7d00';
  return '#13ce66';
};

// 显示设施详情
const showFacilityDetail = async (facility) => {
  currentFacility.value = {...facility};
  try {
    const trendData = await fetchSolidWasteFacilityTrend(facility.id);
    facilityTrendXAxis.value = trendData.xAxis;
    facilityTrendSeries.value = trendData.series;
    facilityDetailVisible.value = true;
  } catch (error) {
    console.error('获取设施趋势数据失败:', error);
    ElMessage.error('获取设施趋势数据失败');
  }
};

// 显示处置详情（通过设施名称匹配设施详情）
const showDisposalDetail = async (row) => {
  // 根据行数据中的设施名称匹配对应的设施详情
  const facility = facilities.value.find(f => f.name === row.main_facility);
  if (!facility) {
    ElMessage.error('未找到对应的处置设施信息');
    return;
  }

  try {
    // 设置当前设施数据
    currentFacility.value = {...facility};

    // 获取设施趋势数据
    const trendData = await fetchSolidWasteFacilityTrend(facility.id);
    facilityTrendXAxis.value = trendData.xAxis;
    facilityTrendSeries.value = trendData.series;

    // 显示弹窗
    facilityDetailVisible.value = true;
  } catch (error) {
    console.error('获取设施趋势数据失败:', error);
    ElMessage.error('获取设施趋势数据失败');
  }
};

// 刷新数据
const refreshData = async () => {
  try {
    ElMessage.loading('正在刷新数据...', 0);
    await initData();
    ElMessage.success('固废处置数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
  }
};

// 导出数据
const exportData = () => {
  ElMessage.success('固废处置数据导出成功');
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/ecologicalprotection/dpzl');
};

// 初始化数据
const initData = async () => {
  try {
    // 1. 获取核心统计数据
    const statsData = await fetchSolidWasteStats();
    totalWaste.value = statsData.totalWaste;
    totalChange.value = statsData.totalChange;
    disposedWaste.value = statsData.disposedWaste;
    disposalRate.value = statsData.disposalRate;
    harmlessWaste.value = statsData.harmlessWaste;
    harmlessRate.value = statsData.harmlessRate;
    facilityCount.value = statsData.facilityCount;
    runningFacility.value = statsData.runningFacility;

    // 2. 获取类型分布数据
    const typeData = await fetchSolidWasteTypeDistribution();
    typePieData.value = typeData;

    // 3. 获取趋势数据
    const trendData = await fetchSolidWasteTrend();
    trendXAxis.value = trendData.xAxis;
    trendSeries.value = trendData.series;

    // 4. 获取处置设施数据
    const facilityData = await fetchSolidWasteFacilities();
    facilities.value = facilityData;

    // 5. 获取表格数据
    const tableData = await fetchSolidWasteDetailData();
    wasteData.value = tableData;
  } catch (error) {
    console.error('初始化数据失败:', error);
    ElMessage.error('数据加载失败，请重试');
  }
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

// 图表行布局
.chart-row {
  display: flex;
  gap: 20px;
  height: 300px;
}

// 图表面板宽度分配
.type-distribution-panel, .disposal-trend-panel {
  flex: 1;
}

::v-deep .el-input .el-input__inner::placeholder {
  font-size: 0.7vw;
}
</style>
