<template>
  <div class="page-container">
    <!-- 页面标题及操作区 -->
    <div class="page-header">
      <h1>空气质量专题视图</h1>
      <div class="header-actions">
        <!-- 功能按钮 -->
        <el-button size="small" @click="refreshData">刷新</el-button>
        <el-button size="small" @click="handleBack">返回总览</el-button>
      </div>
    </div>

    <!-- 主体内容区 -->
    <div class="main-content">
      <!-- 核心指标卡片 -->
      <div class="stats-cards">
        <div class="stat-card">
          <div class="stat-title">AQI指数（今日）</div>
          <div class="stat-value" :class="getAqiLevelClass(currentAqi)">
            {{ currentAqi }}
          </div>
          <div class="stat-desc">{{ getAqiLevelText(currentAqi) }}</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">PM2.5浓度</div>
          <div class="stat-value">{{ pm25Value }} μg/m³</div>
          <div class="stat-desc">较昨日 {{ pm25Change > 0 ? '↑' : '↓' }}{{ Math.abs(pm25Change) }}%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">PM10浓度</div>
          <div class="stat-value">{{ pm10Value }} μg/m³</div>
          <div class="stat-desc">较昨日 {{ pm10Change > 0 ? '↑' : '↓' }}{{ Math.abs(pm10Change) }}%</div>
        </div>
        <div class="stat-card">
          <div class="stat-title">达标天数（本月）</div>
          <div class="stat-value">{{ qualifiedDays }} / {{ totalDays }} 天</div>
          <div class="stat-desc">达标率 {{ qualifiedRate }}%</div>
        </div>
      </div>

      <!-- 趋势图表 -->
      <div class="panel trend-chart-panel">
        <div class="panel-header">
          <h2>空气质量趋势（近30天）</h2>
        </div>
        <div class="panel-body">
          <ChartLine
            :xAxis="trendXAxis"
            :series="trendSeries"
            :yAxisName="'浓度 (μg/m³)'"
            height="240px"
            style="margin-top: 20px"
          />
        </div>
        <div class="panel-footer"></div>
      </div>

      <!-- 详细数据表格 -->
      <div class="panel data-table-panel" ref="dataTablePanel">
        <div class="panel-header">
          <h2>空气质量详细数据</h2>
          <div class="header-actions">
            <!-- 时间范围筛选 -->
            <el-select v-model="timeRange" placeholder="时间范围" size="small">
              <el-option label="今日" value="today" />
              <el-option label="本周" value="week" />
              <el-option label="本月" value="month" />
              <el-option label="全年" value="year" />
            </el-select>
            <!-- 监测区域筛选 -->
            <el-select v-model="regionFilter" placeholder="监测区域" size="small">
              <el-option label="全部区域" value="" />
              <el-option label="城区" value="urban" />
              <el-option label="郊区" value="suburban" />
              <el-option label="工业区" value="industrial" />
            </el-select>
            <el-input
              v-model="searchKeyword"
              placeholder="搜索监测项"
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
        <div class="panel-body table-scroll">
          <el-table
            :data="filteredAirData"
            border
            size="small"
            style="width: 100%"
          >
            <el-table-column prop="monitor_time" label="监测时间" width="160" />
            <el-table-column prop="station_id" label="站点ID" width="100" />
            <el-table-column prop="region" label="区域" />
            <el-table-column prop="aqi" label="AQI" />
            <el-table-column prop="pm25" label="PM2.5 (μg/m³)" />
            <el-table-column prop="pm10" label="PM10 (μg/m³)" />
            <el-table-column prop="so2" label="SO₂ (μg/m³)" />
            <el-table-column prop="no2" label="NO₂ (μg/m³)" />
            <el-table-column prop="status" label="状态">
              <template #default="scope">
                <el-tag :type="getTagType(scope.row.status)">{{ scope.row.status }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <el-button size="small" link @click="showStationDetail(scope.row.station_id)">
                  详情
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="panel-footer"></div>
      </div>
    </div>

    <!-- 监测站点详情弹窗 -->
    <el-dialog
      v-model="stationDetailVisible"
      :title="currentStation?.station_name || '监测站点详情'"
      width="800px"
      :before-close="handleDialogClose"
    >
      <div class="station-detail">
        <!-- 基本信息区域 -->
        <el-descriptions column="2" border class="mb-4">
          <el-descriptions-item label="站点ID" label-class-name="font-bold">{{ currentStation?.station_id }}</el-descriptions-item>
          <el-descriptions-item label="站点类型" label-class-name="font-bold">{{ getStationTypeText(currentStation?.station_type) }}</el-descriptions-item>
          <el-descriptions-item label="所属区域" label-class-name="font-bold">{{ currentStation?.region }}</el-descriptions-item>
          <el-descriptions-item label="建站时间" label-class-name="font-bold">{{ currentStation?.establish_date }}</el-descriptions-item>
          <el-descriptions-item label="站点位置" label-class-name="font-bold">{{ currentStation?.address }}</el-descriptions-item>
          <el-descriptions-item label="地理坐标" label-class-name="font-bold">{{ currentStation?.lng }}, {{ currentStation?.lat }}</el-descriptions-item>
          <el-descriptions-item label="设备状态" label-class-name="font-bold">
            <el-tag :type="getDeviceStatusType(currentStation?.device_status)">{{ currentStation?.device_status }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="最后校准时间" label-class-name="font-bold">{{ currentStation?.calibration_time }}</el-descriptions-item>
        </el-descriptions>

        <!-- 实时监测数据 -->
        <div class="realtime-data-section mb-4">
          <h4 class="section-title">实时监测数据</h4>
          <div class="realtime-indicators">
            <div class="indicator-card">
              <div class="indicator-label">实时AQI</div>
              <div class="indicator-value" :class="getAqiLevelClass(currentStation?.real_time_aqi)">
                {{ currentStation?.real_time_aqi }}
              </div>
              <div class="indicator-desc">{{ getAqiLevelText(currentStation?.real_time_aqi) }}</div>
            </div>
            <div class="indicator-card">
              <div class="indicator-label">PM2.5</div>
              <div class="indicator-value">{{ currentStation?.pm25 }} μg/m³</div>
              <div class="indicator-desc">{{ getPollutantLevel(currentStation?.pm25, 'pm25') }}</div>
            </div>
            <div class="indicator-card">
              <div class="indicator-label">PM10</div>
              <div class="indicator-value">{{ currentStation?.pm10 }} μg/m³</div>
              <div class="indicator-desc">{{ getPollutantLevel(currentStation?.pm10, 'pm10') }}</div>
            </div>
            <div class="indicator-card">
              <div class="indicator-label">SO₂</div>
              <div class="indicator-value">{{ currentStation?.so2 }} μg/m³</div>
              <div class="indicator-desc">{{ getPollutantLevel(currentStation?.so2, 'so2') }}</div>
            </div>
            <div class="indicator-card">
              <div class="indicator-label">NO₂</div>
              <div class="indicator-value">{{ currentStation?.no2 }} μg/m³</div>
              <div class="indicator-desc">{{ getPollutantLevel(currentStation?.no2, 'no2') }}</div>
            </div>
            <div class="indicator-card">
              <div class="indicator-label">O₃</div>
              <div class="indicator-value">{{ currentStation?.o3 }} μg/m³</div>
              <div class="indicator-desc">{{ getPollutantLevel(currentStation?.o3, 'o3') }}</div>
            </div>
          </div>
          <div class="update-time text-sm text-gray-500 mt-2">
            数据更新时间: {{ currentStation?.update_time }}
          </div>
        </div>

        <!-- 主要污染物信息 -->
        <div v-if="currentStation?.main_pollutant" class="pollutant-info mb-4 p-3 bg-warning/10 border border-warning/30 rounded">
          <h4 class="section-title flex items-center">
            <el-icon size="16" class="mr-1"><Warning /></el-icon>
            主要污染物
          </h4>
          <p>{{ currentStation?.main_pollutant }} - {{ getPollutantDescription(currentStation?.main_pollutant) }}</p>
        </div>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="handleDialogClose">关闭</el-button>
          <el-button type="primary" @click="exportStationData">导出数据</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
// 导入Vue核心模块
import {ref, computed, onMounted, getCurrentInstance} from 'vue';
import {useRouter} from 'vue-router';
import {ElMessage} from 'element-plus';

// 导入全屏工具
import screenFull from 'screenfull';

// 导入组件和图标
import ChartLine from './ChartLine1.vue';
import {FullScreen, Warning} from "@element-plus/icons-vue";

// 导入API接口
import {
  fetchAirQualityTrend,
  fetchAirQualityStations,
  fetchAirQualityDetails,
  fetchAirQualityCoreIndicators,
  fetchAirQualityStationTrend
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

// 筛选条件
const timeRange = ref('month');
const regionFilter = ref('');
const searchKeyword = ref('');
const visibleLayers = ref(['urban', 'suburban', 'industrial']);

// 核心数据
const currentAqi = ref(0);
const pm25Value = ref(0);
const pm25Change = ref(0);
const pm10Value = ref(0);
const pm10Change = ref(0);
const qualifiedDays = ref(0);
const totalDays = ref(0);
const qualifiedRate = ref(0);

// 趋势图表数据
const trendXAxis = ref([]);
const trendSeries = ref([
  {name: 'PM2.5', data: []},
  {name: 'PM10', data: []},
  {name: 'AQI', data: []}
]);

// 监测站点数据
const stations = ref([]);
const currentStation = ref(null);
const stationDetailVisible = ref(false);
const stationTrendXAxis = ref([]);
const stationTrendSeries = ref([]);

// 详细表格数据
const airQualityData = ref([]);

// 区域筛选映射（将筛选值转换为表格中的区域名称）
const regionMap = {
  'urban': '城区',
  'suburban': '郊区',
  'industrial': '工业区'
};

// 检查时间是否在筛选范围内
const isTimeInRange = (monitorTime, range) => {
  const date = new Date(monitorTime);
  const today = new Date();
  today.setHours(0, 0, 0, 0);

  switch (range) {
    case 'today':
      // 今天：时间 >= 今天0点
      return date >= today;
    case 'week':
      // 本周：时间 >= 本周一0点
      const weekStart = new Date(today);
      weekStart.setDate(today.getDate() - today.getDay() + (today.getDay() === 0 ? -6 : 1));
      weekStart.setHours(0, 0, 0, 0);
      return date >= weekStart;
    case 'month':
      // 本月：时间 >= 本月1日0点
      const monthStart = new Date(today.getFullYear(), today.getMonth(), 1);
      return date >= monthStart;
    case 'year':
      // 全年：时间 >= 今年1月1日0点
      const yearStart = new Date(today.getFullYear(), 0, 1);
      return date >= yearStart;
    default:
      return true;
  }
};

// 筛选后的表格数据
const filteredAirData = computed(() => {
  return airQualityData.value.filter(item => {
    // 1. 时间范围筛选
    const matchesTime = isTimeInRange(item.monitor_time, timeRange.value);

    // 2. 区域筛选（通过映射表匹配）
    const targetRegion = regionMap[regionFilter.value] || '';
    const matchesRegion = !regionFilter.value || item.region === targetRegion;

    // 3. 关键词搜索筛选
    const matchesKeyword = !searchKeyword.value
      || item.region.includes(searchKeyword.value)
      || item.monitor_time.includes(searchKeyword.value)
      || item.station_id.includes(searchKeyword.value)
      || item.aqi.toString().includes(searchKeyword.value)
      || item.pm25.toString().includes(searchKeyword.value)
      || item.pm10.toString().includes(searchKeyword.value)
      || item.so2.toString().includes(searchKeyword.value)
      || item.no2.toString().includes(searchKeyword.value)
      || item.status.includes(searchKeyword.value);

    // 所有条件都满足才保留
    return matchesTime && matchesRegion && matchesKeyword;
  });
});

// 筛选后的站点
const filteredStations = computed(() => {
  return stations.value.filter(station => {
    const matchesRegion = !regionFilter.value || station.region_type === regionFilter.value;
    const matchesLayer = visibleLayers.value.includes(station.region_type);
    return matchesRegion && matchesLayer;
  });
});

// 获取AQI等级样式
const getAqiLevelClass = (aqi) => {
  if (!aqi) return '';
  if (aqi <= 50) return 'excellent';
  if (aqi <= 100) return 'good';
  if (aqi <= 150) return 'light-pollution';
  if (aqi <= 200) return 'moderate-pollution';
  return 'severe-pollution';
};

// 获取AQI等级文本
const getAqiLevelText = (aqi) => {
  if (!aqi) return '';
  if (aqi <= 50) return '优';
  if (aqi <= 100) return '良';
  if (aqi <= 150) return '轻度污染';
  if (aqi <= 200) return '中度污染';
  return '严重污染';
};

// 获取站点类型文本描述
const getStationTypeText = (type) => {
  const typeMap = {
    'standard': '标准监测站',
    'mobile': '移动监测站',
    'background': '背景监测站',
    'roadside': '路边监测站'
  };
  return typeMap[type] || '未知类型';
};

// 获取设备状态标签类型
const getDeviceStatusType = (status) => {
  switch (status) {
    case '运行中':
      return 'success';
    case '维护中':
      return 'warning';
    case '故障':
      return 'danger';
    default:
      return 'info';
  }
};

// 获取污染物等级描述
const getPollutantLevel = (value, type) => {
  if (value === undefined || value === null) return '无数据';

  // 各污染物国家标准限值（24小时平均）
  const standards = {
    pm25: [35, 75, 115, 150],  // 优, 良, 轻度, 中度, 重度
    pm10: [50, 150, 250, 350],
    so2: [50, 150, 475, 800],
    no2: [40, 80, 180, 280],
    o3: [100, 160, 215, 265]    // 8小时平均
  };

  if (!standards[type]) return '正常';

  const levels = ['优', '良', '轻度污染', '中度污染', '重度污染'];
  for (let i = 0; i < standards[type].length; i++) {
    if (value <= standards[type][i]) {
      return levels[i];
    }
  }
  return levels[levels.length - 1];
};

// 获取污染物说明
const getPollutantDescription = (pollutant) => {
  const descriptions = {
    'PM2.5': '细颗粒物，能较长时间悬浮于空气中，对呼吸系统和心血管系统造成伤害',
    'PM10': '可吸入颗粒物，会对人体呼吸系统造成影响',
    'SO₂': '二氧化硫，主要来源于燃烧含硫燃料，会刺激呼吸道',
    'NO₂': '二氧化氮，主要来源于机动车尾气，对呼吸系统有危害',
    'O₃': '臭氧，近地面臭氧是一种污染物，会刺激眼睛和呼吸道'
  };
  return descriptions[pollutant] || '该污染物可能对人体健康造成影响';
};

// 获取标签类型
const getTagType = (status) => {
  switch (status) {
    case '正常':
      return 'success';
    case '轻度污染':
      return 'warning';
    case '严重污染':
      return 'danger';
    default:
      return 'info';
  }
};

// 显示站点详情
const showStationDetail = async (stationId) => {
  // 根据stationId查找对应的站点信息
  const station = stations.value.find(s => s.station_id === stationId);
  if (!station) {
    ElMessage.error('未找到该站点信息');
    return;
  }

  currentStation.value = {...station};

  const trendData = await fetchAirQualityStationTrend(stationId);
  stationTrendXAxis.value = trendData.xAxis;
  stationTrendSeries.value = trendData.series;

  stationDetailVisible.value = true;
};

// 关闭弹窗
const handleDialogClose = () => {
  stationDetailVisible.value = false;
};

// 导出站点数据
const exportStationData = () => {
  if (!currentStation.value) return;
  ElMessage.success(`已导出 ${currentStation.value.station_name} 的数据`);
};

// 刷新数据
const refreshData = async () => {
  await initData(); // 刷新时重新从API获取数据
  ElMessage.success('空气质量数据已刷新');
};

// 导出数据
const exportData = () => {
  ElMessage.success('空气质量数据导出成功');
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/ecologicalprotection/dpzl');
};

// 初始化数据
const initData = async () => {
  // 从API获取趋势数据
  const trendData = await fetchAirQualityTrend();
  trendXAxis.value = trendData.xAxis;
  trendSeries.value = trendData.series;

  // 从API获取站点数据
  stations.value = await fetchAirQualityStations();

  // 从API获取详细表格数据
  airQualityData.value = await fetchAirQualityDetails();

  // 从API获取核心指标数据
  const coreIndicators = await fetchAirQualityCoreIndicators();
  currentAqi.value = coreIndicators.currentAqi;
  pm25Value.value = coreIndicators.pm25Value;
  pm25Change.value = coreIndicators.pm25Change;
  pm10Value.value = coreIndicators.pm10Value;
  pm10Change.value = coreIndicators.pm10Change;
  qualifiedDays.value = coreIndicators.qualifiedDays;
  totalDays.value = coreIndicators.totalDays;
  qualifiedRate.value = coreIndicators.qualifiedRate;
};

// 组件挂载时初始化
onMounted(() => {
  initData();
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

// 详情弹窗样式优化
.station-detail {
  .section-title {
    font-size: 16px;
    font-weight: 500;
    margin-bottom: 12px;
    color: rgba(178, 174, 231, 0.6);
  }

  .realtime-data-section {
    .realtime-indicators {
      display: flex;
      flex-wrap: wrap;
      gap: 12px;
      margin-bottom: 10px;
    }

    .indicator-card {
      flex: 1;
      min-width: 120px;
      background: #f5f7fa;
      border-radius: 6px;
      padding: 10px;
      text-align: center;

      .indicator-label {
        font-size: 14px;
        color: #666;
        margin-bottom: 5px;
      }

      .indicator-value {
        font-size: 20px;
        font-weight: bold;
        margin-bottom: 3px;
        color: #333;

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

      .indicator-desc {
        font-size: 12px;
        color: #999;
      }
    }
  }

  .font-bold {
    font-weight: 500;
  }

  .mb-4 {
    margin-bottom: 16px;
  }
}

@keyframes pulse {
  0% {
    transform: translate(-50%, -50%) scale(1);
  }
  50% {
    transform: translate(-50%, -50%) scale(1.1);
  }
  100% {
    transform: translate(-50%, -50%) scale(1);
  }
}

::v-deep .el-input .el-input__inner::placeholder {
  font-size: 0.7vw;
}
</style>
