<template>
  <div class="page-container">
    <!-- 页头区域：标题及筛选操作区 -->
    <div class="page-header">
      <h1>重点污染源专题视图</h1>
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
          <div class="stat-title">重点污染源总数</div>
          <div class="stat-value">{{ totalPollutants }}</div>
          <div class="stat-desc">在线监测率 {{ onlineRate }}%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">超标污染源</div>
          <div class="stat-value" :class="overstandardCount > 0 ? 'danger' : 'normal'">
            {{ overstandardCount }} 个
          </div>
          <div class="stat-desc">超标率 {{ overstandardRate }}%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">主要污染物</div>
          <div class="stat-value">{{ mainPollutant }}</div>
          <div class="stat-desc">最高浓度 {{ maxConcentration }}{{ concentrationUnit }}</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">今日监管次数</div>
          <div class="stat-value">{{ supervisionCount }}</div>
          <div class="stat-desc">整改完成: {{ rectificationCount }} 家</div>
        </div>
      </div>

      <!-- 图表行：污染源类型分布与排放趋势 -->
      <div class="chart-row">
        <!-- 左侧：污染源类型分布柱状图 -->
        <div class="panel type-chart-panel">
          <div class="panel-header">
            <h2>污染源类型分布</h2>
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

        <!-- 右侧：主要污染物排放趋势折线图 -->
        <div class="panel emission-trend-panel">
          <div class="panel-header">
            <h2>主要污染物排放趋势（近15天）</h2>
          </div>
          <div class="panel-body">
            <ChartLine
              :xAxis="trendXAxis"
              :series="emissionSeries"
              :yAxisName="'排放量 (吨/日)'"
              height="240px"
              style="margin-top: 20px"
            />
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <!-- 重点污染源详细数据表格 -->
      <div class="panel data-table-panel" ref="dataTablePanel">
        <div class="panel-header">
          <h2>重点污染源监测数据</h2>
          <div class="header-actions">
            <!-- 时间范围筛选器 -->
            <el-select v-model="timeRange" placeholder="时间范围" size="small">
              <el-option label="今日" value="today" />
              <el-option label="本周" value="week" />
              <el-option label="本月" value="month" />
            </el-select>
            <!-- 污染源类型筛选器 -->
            <el-select v-model="pollutantType" placeholder="污染源类型" size="small">
              <el-option label="全部" value="" />
              <el-option label="工业" value="industrial" />
              <el-option label="农业" value="agricultural" />
              <el-option label="生活" value="domestic" />
              <el-option label="机动车" value="vehicle" />
            </el-select>
            <!-- 超标状态筛选器 -->
            <el-select v-model="overstandardStatus" placeholder="超标状态" size="small">
              <el-option label="全部" value="" />
              <el-option label="未超标" value="0" />
              <el-option label="已超标" value="1" />
            </el-select>
            <el-input
              v-model="searchKeyword"
              placeholder="搜索污染源名称"
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
            :data="filteredPollutantData"
            border
            size="small"
            style="width: 100%"
          >
            <el-table-column prop="name" label="污染源名称" />
            <el-table-column prop="type" label="类型">
              <template #default="scope">
                <el-tag :type="getTypeTagType(scope.row.type)">
                  {{ getTypeName(scope.row.type) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="region" label="所属区域" />
            <el-table-column prop="emission" label="日排放量 (吨)" />
            <el-table-column prop="standard" label="排放标准 (吨)" />
            <el-table-column prop="overstandard" label="超标状态">
              <template #default="scope">
                <el-tag :type="scope.row.overstandard === '1' ? 'danger' : 'success'">
                  {{ scope.row.overstandard === '1' ? '已超标' : '未超标' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="monitor_status" label="监测状态">
              <template #default="scope">
                <el-tag :type="scope.row.monitor_status === 'online' ? 'success' : 'warning'">
                  {{ scope.row.monitor_status === 'online' ? '在线' : '离线' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="last_monitor" label="最近监测时间" />
            <el-table-column label="操作" min-width="88vw">
              <template #default="scope">
                <el-button size="small" link @click="showPollutantDetail(scope.row)">详情</el-button>
                <el-button
                  size="small"
                  link
                  @click="handleSupervision(scope.row)"
                  v-if="scope.row.overstandard === '1'"
                >
                  监管
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="panel-footer"></div>
      </div>
    </div>

    <!-- 污染源详情弹窗 -->
    <el-dialog
      v-model="pollutantDetailVisible"
      :title="currentPollutant?.name || '污染源详情'"
      width="700px"
    >
      <div class="pollutant-detail">
        <!-- 基础信息区域 -->
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="污染源ID">{{ currentPollutant?.id }}</el-descriptions-item>
            <el-descriptions-item label="类型">{{ getTypeName(currentPollutant?.type) }}</el-descriptions-item>
            <el-descriptions-item label="所属区域">{{ currentPollutant?.region }}</el-descriptions-item>
            <el-descriptions-item label="地址">{{ currentPollutant?.address }}</el-descriptions-item>
            <el-descriptions-item label="负责人">{{ currentPollutant?.manager }}</el-descriptions-item>
            <el-descriptions-item label="联系方式">{{ currentPollutant?.contact }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 排放指标区域 -->
        <div class="detail-section">
          <h3>排放指标</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="主要污染物">{{ currentPollutant?.main_pollutant }}</el-descriptions-item>
            <el-descriptions-item label="日排放量">{{ currentPollutant?.emission }} 吨</el-descriptions-item>
            <el-descriptions-item label="排放标准">{{ currentPollutant?.standard }} 吨/日</el-descriptions-item>
            <el-descriptions-item label="超标倍数" v-if="currentPollutant?.overstandard === '1'">
              <span class="danger">{{ currentPollutant?.overstandard_multiple }} 倍</span>
            </el-descriptions-item>
            <el-descriptions-item label="监测设备状态">{{ currentPollutant?.monitor_status === 'online' ? '在线' : '离线' }}</el-descriptions-item>
            <el-descriptions-item label="最近校准时间">{{ currentPollutant?.last_calibration }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 排放趋势图表区域 -->
        <div class="detail-section">
          <h3>近7天排放趋势</h3>
          <ChartLineT
            :xAxis="pollutantTrendXAxis"
            :series="pollutantTrendSeries"
            height="200px"
          />
        </div>

        <!-- 超标处理记录区域（仅超标污染源显示） -->
        <div class="detail-section" v-if="currentPollutant?.overstandard === '1'">
          <h3>超标处理记录</h3>
          <el-table
            :data="currentPollutant?.treatment_records || []"
            border
            size="small"
            style="width: 100%"
          >
            <el-table-column prop="time" label="处理时间" width="160" />
            <el-table-column prop="content" label="处理措施" />
            <el-table-column prop="handler" label="处理人" width="100" />
          </el-table>
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
import ChartLine from './ChartLine3.vue';
import ChartLineT from './ChartLineT.vue';
import ChartBar from './ChartBar3.vue';
import {FullScreen} from "@element-plus/icons-vue";

// 导入API接口
import {
  fetchPollutantStatistics,
  fetchPollutantTypeDistribution,
  fetchPollutantEmissionTrend,
  fetchPollutantList,
  fetchPollutantTableData,
  fetchPollutantDetailTrend
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
const timeRange = ref('month');            // 时间范围筛选
const pollutantType = ref('');             // 污染源类型筛选
const overstandardStatus = ref('');        // 超标状态筛选
const searchKeyword = ref('');             // 搜索关键词
const visibleTypes = ref(['industrial', 'agricultural', 'domestic', 'vehicle']); // 可见的污染源类型

// 核心指标数据变量
const totalPollutants = ref(0);            // 重点污染源总数
const onlineRate = ref(0);                 // 在线监测率
const overstandardCount = ref(0);          // 超标污染源数量
const overstandardRate = ref(0);           // 超标率
const mainPollutant = ref('');             // 主要污染物
const maxConcentration = ref(0);           // 最高浓度
const concentrationUnit = ref('');         // 浓度单位
const supervisionCount = ref(0);           // 今日监管次数
const rectificationCount = ref(0);         // 整改完成数量

// 污染源数据变量
const pollutants = ref([]);                // 污染源列表
const pollutantData = ref([]);             // 污染源表格数据
const currentPollutant = ref(null);        // 当前选中的污染源详情
const pollutantDetailVisible = ref(false); // 详情弹窗显示状态
const pollutantTrendXAxis = ref([]);       // 污染源趋势图X轴
const pollutantTrendSeries = ref([]);      // 污染源趋势图数据

// 图表数据变量
const typeBarData = ref({                  // 污染源类型分布柱状图数据
  legend: [],
  series: [{name: '数量', data: []}]
});
const trendXAxis = ref([]);                // 排放趋势图X轴
const emissionSeries = ref([               // 排放趋势图数据
  {name: 'COD', data: []},
  {name: '氨氮', data: []},
  {name: '二氧化硫', data: []}
]);

// 筛选后的污染源列表（计算属性）
const filteredPollutants = computed(() => {
  return pollutants.value.filter(item => {
    const matchesType = !pollutantType.value || item.type === pollutantType.value;
    const matchesStatus = !overstandardStatus.value || item.overstandard === overstandardStatus.value;
    const matchesVisible = visibleTypes.value.includes(item.type);
    return matchesType && matchesStatus && matchesVisible;
  });
});

// 筛选后的表格数据（计算属性）
const filteredPollutantData = computed(() => {
  return pollutantData.value.filter(item => {
    const matchesType = !pollutantType.value || item.type === pollutantType.value;
    const matchesStatus = !overstandardStatus.value || item.overstandard === overstandardStatus.value;
    const matchesKeyword = !searchKeyword.value || item.name.includes(searchKeyword.value);
    return matchesType && matchesStatus && matchesKeyword;
  });
});

// 获取污染源类型名称（将英文类型转换为中文显示）
const getTypeName = (type) => {
  const typeMap = {
    industrial: '工业',
    agricultural: '农业',
    domestic: '生活',
    vehicle: '机动车'
  };
  return typeMap[type] || type;
};

// 获取污染源类型样式
const getPollutantTypeClass = (type) => {
  return type;
};

// 获取类型标签样式（根据不同类型返回不同标签样式）
const getTypeTagType = (type) => {
  const typeMap = {
    industrial: 'danger',
    agricultural: 'warning',
    domestic: 'info',
    vehicle: 'primary'
  };
  return typeMap[type] || 'default';
};

// 显示污染源详情
const showPollutantDetail = async (pollutant) => {
  currentPollutant.value = {...pollutant};
  pollutantDetailVisible.value = true;

  try {
    // 调用接口获取趋势数据
    const trendData = await fetchPollutantDetailTrend(pollutant.id);
    pollutantTrendXAxis.value = trendData.xAxis;
    pollutantTrendSeries.value = trendData.series;
  } catch (error) {
    console.error('获取污染源趋势数据失败:', error);
    ElMessage.error('获取趋势数据失败');
  }
};

// 处理监管操作
const handleSupervision = (pollutant) => {
  ElMessage.info(`对${pollutant.name}发起监管流程`);
};

// 刷新数据
const refreshData = async () => {
  try {
    await initData();
    ElMessage.success('重点污染源数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
  }
};

// 导出数据
const exportData = () => {
  ElMessage.success('重点污染源数据导出成功');
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/ecologicalprotection/dpzl');
};

// 初始化数据
const initData = async () => {
  try {
    // 1. 获取核心统计数据
    const stats = await fetchPollutantStatistics();
    totalPollutants.value = stats.totalPollutants;
    onlineRate.value = stats.onlineRate;
    overstandardCount.value = stats.overstandardCount;
    overstandardRate.value = stats.overstandardRate;
    mainPollutant.value = stats.mainPollutant;
    maxConcentration.value = stats.maxConcentration;
    concentrationUnit.value = stats.concentrationUnit;
    supervisionCount.value = stats.supervisionCount;
    rectificationCount.value = stats.rectificationCount;

    // 2. 获取类型分布数据
    const typeData = await fetchPollutantTypeDistribution();
    typeBarData.value = typeData;

    // 3. 获取排放趋势数据
    const trendData = await fetchPollutantEmissionTrend();
    trendXAxis.value = trendData.xAxis;
    emissionSeries.value = trendData.series;

    // 4. 获取污染源列表
    const pollutantList = await fetchPollutantList();
    pollutants.value = pollutantList;

    // 5. 获取表格数据
    const tableData = await fetchPollutantTableData();
    pollutantData.value = tableData;
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
.type-chart-panel {
  flex: 1;
}

.emission-trend-panel {
  flex: 2;
}

// 状态颜色样式
.danger {
  color: #ff4949;
  font-weight: bold;
}

.normal {
  color: #13ce66;
  font-weight: bold;
}

// 超标闪烁动画
@keyframes flash {
  0% {
    box-shadow: 0 0 5px 1px red;
  }
  50% {
    box-shadow: 0 0 10px 3px red;
  }
  100% {
    box-shadow: 0 0 5px 1px red;
  }
}

::v-deep .el-input .el-input__inner::placeholder {
  font-size: 0.7vw;
}
</style>
