<template>
  <div class="page-container">
    <div class="mainbox">
      <div class="top">
        <div class="top_left" style="min-width: 3vw;">
          <!-- 行业分布统计 -->
          <div class="panel industry-stats-panel">
            <div class="panel-header">
              <h2>行业分布统计</h2>
            </div>
            <div class="panel-body">
              <div class="chart-container">
                <ChartPie :data="industryDistributionData" />
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="panel top_middle" style="min-width: 4vw;" ref="map">
          <div class="header-actions">
            <p>园区空间格局视图</p>
            <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('map')">
              <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
            </button>
          </div>
          <map-common idName="chinaEcharts" :geometriesArray="geometriesArray" />
          <div class="panel-footer"></div>
        </div>
        <div class="top_right" style="min-width: 3vw;">
          <!-- 园区全域数据概览 -->
          <div class="park-overview-panel panel">
            <div class="panel-header">
              <h2>园区全域数据概览</h2>
              <div class="header-actions">
                <el-select v-model="parkNameFilter" placeholder="选择园区" size="small" style="width: 8vw;">
                  <el-option label="高新技术产业园区" value="高新技术产业园区" />
                  <el-option label="经济开发区" value="经济开发区" />
                  <el-option label="物流园区" value="物流园区" />
                </el-select>
              </div>
            </div>
            <div class="panel-body">
              <div class="park-overview-cards">
                <!-- IoT设备数据 -->
                <div class="park-overview-card">
                  <div class="park-overview-value">
                    <span class="park-overview-unit">在线设备: </span>
                    <br/>
                    {{ filteredParkOverview[0]?.iot_online || 0 }}
                    <span class="park-overview-unit">台</span>
                  </div>
                  <div class="park-overview-desc">
                    离线设备: {{ filteredParkOverview[0]?.iot_offline || 0 }}台
                    <br/>
                    告警设备: {{ filteredParkOverview[0]?.iot_alarm || 0 }}台
                    <br/>
                    数据采集频率: {{ filteredParkOverview[0]?.data_collection_frequency || 0 }}秒/次
                  </div>
                </div>

                <!-- 企业动态数据 -->
                <div class="park-overview-card">
                  <div class="park-overview-value">
                    <span class="park-overview-unit">本月新增企业: </span>
                    <br/>
                    {{ filteredParkOverview[0]?.enterprise_increase || 0 }}
                    <span class="park-overview-unit">家</span>
                  </div>
                  <div class="park-overview-desc">
                    企业从业人员: {{ filteredParkOverview[0]?.employee_total || 0 }}人
                    <br/>
                    重点企业运行状态: <br/>
                    {{ filteredParkOverview[0]?.key_enterprise_normal || 0 }} 正常 |
                    {{ filteredParkOverview[0]?.key_enterprise_warning || 0 }} 预警
                  </div>
                </div>

                <!-- 经济核心数据 -->
                <div class="park-overview-card">
                  <div class="park-overview-value">
                    <span class="park-overview-unit">月度营收总额: </span>
                    <br/>
                    {{ filteredParkOverview[0]?.monthly_revenue || 0 }}
                    <span class="park-overview-unit">万元</span>
                  </div>
                  <div class="park-overview-desc">
                    税收贡献率: {{ filteredParkOverview[0]?.tax_contribution || 0 }}万元
                    <br/>
                    固投完成率: {{ filteredParkOverview[0]?.investment_completion || 0 }}万元
                  </div>
                </div>

                <!-- 事件处置数据 -->
                <div
                  class="park-overview-card"
                  :class="filteredParkOverview[0]?.pending_event > 5 ? 'warning' : ''"
                  @click="gotoWarningList"
                >
                  <div class="park-overview-value">
                    <span class="park-overview-unit">今日事件: </span>
                    <br/>
                    {{ filteredParkOverview[0]?.today_event_total || 0 }}
                    <span class="park-overview-unit">件</span>
                  </div>
                  <div class="park-overview-desc">
                    已处置: {{ filteredParkOverview[0]?.handled_event || 0 }}件
                    <br/>
                    处置完成率: {{ filteredParkOverview[0]?.event_completion_rate || 0 }}%
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
      <div class="bottom">
        <div class="bottom_left" style="min-width: 2vw;">
          <!-- 园区核心指标 -->
          <div class="panel core-indicators-panel">
            <div class="panel-body">
              <div class="core-indicator-cards">
                <!-- 经济类指标 -->
                <div
                  class="core-indicator-card economic-card"
                  @click="handleCardClick('economic')"
                  :class="{ 'active': selectedTrendIndicator === 'economic' }"
                >
                  <div class="core-indicator-title">月度营收总额</div>
                  <div class="core-indicator-value">
                    {{ economic.revenue || 0 }}
                    <span class="core-indicator-unit">万元</span>
                  </div>
                  <div class="core-indicator-desc">
                  <span class="yoy" :class="economic.revenue_yoy >= 0 ? 'rise' : 'fall'">
                    同比增长率 {{ economic.revenue_yoy || 0 }}%
                  </span>
                    税收总额: {{ economic.tax || 0 }}万元
                    <br/>
                    固投完成率: {{ economic.investment_rate || 0 }}%
                  </div>
                </div>

                <!-- 设备类指标 -->
                <div
                  class="core-indicator-card device-card"
                  @click="handleCardClick('device')"
                  :class="{ 'active': selectedTrendIndicator === 'device' }"
                >
                  <div class="core-indicator-title">设备在线率</div>
                  <div class="core-indicator-value">
                    {{ device.online_rate || 0 }}
                    <span class="core-indicator-unit">%</span>
                  </div>
                  <div class="core-indicator-desc">
                  <span class="yoy" :class="device.alert_rate <= 3 ? 'rise' : 'fall'">
                    告警发生率 {{ device.alert_rate || 0 }}%
                  </span>
                    故障修复时长: {{ device.repair_time || 0 }}分钟
                  </div>
                </div>

                <!-- 企业类指标 -->
                <div
                  class="core-indicator-card enterprise-card"
                  @click="handleCardClick('enterprise')"
                  :class="{ 'active': selectedTrendIndicator === 'enterprise' }"
                >
                  <div class="core-indicator-title">重点企业营收占比</div>
                  <div class="core-indicator-value">
                    {{ enterprise.revenue_ratio || 0 }}
                    <span class="core-indicator-unit">%</span>
                  </div>
                  <div class="core-indicator-desc">
                  <span class="yoy" :class="enterprise.loss_rate <= 5 ? 'rise' : 'fall'">
                    企业流失率 {{ enterprise.loss_rate || 0 }}%
                  </span>
                    新增企业注册资本总额: {{ enterprise.registered_capital || 0 }}万元
                  </div>
                </div>

                <!-- 事件类指标 -->
                <div
                  class="core-indicator-card event-card"
                  @click="handleCardClick('event')"
                  :class="{ 'active': selectedTrendIndicator === 'event' }"
                >
                  <div class="core-indicator-title">事件日均处置数</div>
                  <div class="core-indicator-value">
                    {{ event.daily_count || 0 }}
                    <span class="core-indicator-unit">件</span>
                  </div>
                  <div class="core-indicator-desc">
                  <span class="yoy" :class="event.repeat_rate <= 3 ? 'rise' : 'fall'">
                    超时处置率 {{ event.timeout_rate || 0 }}%
                  </span>
                    重复事件发生率: {{ event.repeat_rate || 0 }}%
                  </div>
                </div>
              </div>

              <!-- 趋势折线图 -->
              <div class="chart-container">
                <ChartLine :data="indicatorTrendData" :yAxisName="getYAxisName()" />
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="bottom_right" style="min-width: 2vw;" ref="list">
          <!-- 园区基础信息列表 -->
          <div class="panel" style="width: 100%; height: 100%">
            <div class="panel-header">
              <h2>园区基础信息列表</h2>
              <div class="header-actions">
                <el-select v-model="parkLevelFilter" placeholder="园区等级" size="small" style="width: 8vw;">
                  <el-option label="全部" value="" />
                  <el-option label="国家级" value="国家级" />
                  <el-option label="省级" value="省级" />
                  <el-option label="市级" value="市级" />
                </el-select>
                <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('list')">
                  <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body">
              <el-table :data="filteredParkBaseInfoList" border size="small" style="height: 90%">
                <el-table-column prop="id" label="园区ID" />
                <el-table-column prop="name" label="园区名称" />
                <el-table-column prop="industryType" label="主导产业" />
                <el-table-column prop="parkLevel" label="园区等级">
                  <template #default="scope">
                    <el-tag
                      :type="scope.row.parkLevel === '国家级' ? 'primary' :
                   scope.row.parkLevel === '省级' ? 'success' : 'info'"
                    >
                      {{ scope.row.parkLevel }}
                    </el-tag>
                  </template>
                </el-table-column>
                <el-table-column label="规划面积(㎡)">
                  <template #default="scope">{{ scope.row.plannedArea.toLocaleString() }}</template>
                </el-table-column>
                <el-table-column label="建成率">
                  <template #default="scope">
                    <div class="completion-rate">{{ scope.row.completionRate }}%</div>
                  </template>
                </el-table-column>
                <el-table-column prop="enterpriseCount" label="入驻企业(家)" />
                <el-table-column label="操作">
                  <template #default="scope">
                    <el-button size="small" type="text" @click="viewParkDetail(scope.row)">详情</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
      当前时间：{{ currentTime }}
    </div>

    <!-- 园区详情弹窗 -->
    <el-dialog v-model="parkDetailVisible" :title="currentPark?.name || '园区详情'" width="600px">
      <div class="park-detail">
        <el-descriptions column="1" border>
          <el-descriptions-item label="园区ID">{{ currentPark?.id }}</el-descriptions-item>
          <el-descriptions-item label="园区名称">{{ currentPark?.name }}</el-descriptions-item>
          <el-descriptions-item label="园区等级">{{ currentPark?.parkLevel }}</el-descriptions-item>
          <el-descriptions-item label="主导产业">{{ currentPark?.industryType }}</el-descriptions-item>
          <el-descriptions-item label="规划面积">{{ currentPark?.plannedArea.toLocaleString() }} ㎡</el-descriptions-item>
          <el-descriptions-item label="建成面积">{{ currentPark?.builtArea.toLocaleString() }} ㎡</el-descriptions-item>
          <el-descriptions-item label="建成率">{{ currentPark?.completionRate }}%</el-descriptions-item>
          <el-descriptions-item label="入驻企业总数">{{ currentPark?.enterpriseCount }} 家</el-descriptions-item>
          <el-descriptions-item label="成立时间">{{ currentPark?.establishmentTime }}</el-descriptions-item>
          <el-descriptions-item label="地址">{{ currentPark?.address }}</el-descriptions-item>
          <el-descriptions-item label="管理公司">{{ currentPark?.managementCompany }}</el-descriptions-item>
        </el-descriptions>
      </div>
      <template #footer>
        <el-button @click="parkDetailVisible = false">关闭</el-button>
      </template>
    </el-dialog>

    <!-- 企业详情弹窗 -->
    <el-dialog v-model="enterpriseDetailVisible" :title="currentEnterprise?.enterprise_name || '企业详情'" width="500px">
      <div class="enterprise-detail">
        <div class="detail-section">
          <h3>企业信息</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="企业ID">{{ currentEnterprise?.enterprise_id }}</el-descriptions-item>
            <el-descriptions-item label="企业名称">{{ currentEnterprise?.enterprise_name }}</el-descriptions-item>
            <el-descriptions-item label="所属行业">{{ currentEnterprise?.enterprise_industry }}</el-descriptions-item>
            <el-descriptions-item label="所在园区">{{ currentEnterprise?.park_name }}</el-descriptions-item>
            <el-descriptions-item label="员工数量">{{ currentEnterprise?.employee_num }} 人</el-descriptions-item>
            <el-descriptions-item label="年产值">{{ currentEnterprise?.annual_output_value }} 万元</el-descriptions-item>
            <el-descriptions-item label="企业状态">
              <el-tag
                :type="currentEnterprise?.enterprise_status === '0' ? 'success' :
                         currentEnterprise?.enterprise_status === '1' ? 'warning' : 'danger'"
              >
                {{ currentEnterprise?.enterprise_status === '0' ? '存续' :
                currentEnterprise?.enterprise_status === '1' ? '搬迁' : '注销' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="联系方式">{{ currentEnterprise?.ext1 }}</el-descriptions-item>
            <el-descriptions-item label="注册时间">{{ currentEnterprise?.ext2 }}</el-descriptions-item>
            <el-descriptions-item label="坐标更新时间">{{ currentEnterprise?.coord_update_time }}</el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <template #footer>
        <el-button @click="enterpriseDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="editEnterpriseCoord"
          v-if="currentEnterprise?.enterprise_status !== '2'"
        >
          编辑坐标
        </el-button>
      </template>
    </el-dialog>

    <!-- 坐标编辑弹窗 -->
    <el-dialog v-model="editCoordVisible" title="编辑企业坐标" width="400px">
      <el-form>
        <el-form-item label="企业名称" disabled>
          <el-input v-model="coordForm.enterpriseName" />
        </el-form-item>
        <el-form-item label="当前坐标" disabled>
          <el-input v-model="coordForm.currentCoord" />
        </el-form-item>
        <el-form-item label="新坐标" required>
          <el-input v-model="coordForm.newCoord" placeholder="格式: x,y" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="editCoordVisible = false">取消</el-button>
        <el-button type="primary" @click="saveEnterpriseCoord">保存</el-button>
      </template>
    </el-dialog>

    <!-- 新预警通知弹窗 -->
    <el-dialog v-model="newWarningVisible" title="新预警通知" width="400px" :show-close="false">
      <div class="warning-notification">
        <div class="warning-icon">警告</div>
        <div class="warning-content">
          <p>园区待处理事件已达 {{ parkOverviewData[0]?.pending_event }} 件</p>
          <p>超过阈值5件，请及时处理</p>
          <p class="warning-time">发生时间: {{ formatTime(new Date()) }}</p>
        </div>
      </div>
      <template #footer>
        <el-button @click="ignoreWarning">忽略</el-button>
        <el-button type="primary" @click="handleNewWarning">查看详情</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted, getCurrentInstance } from 'vue';
import {
  ElSelect,
  ElOption,
  ElButton,
  ElTable,
  ElTableColumn,
  ElTag,
  ElDialog,
  ElDescriptions,
  ElDescriptionsItem,
  ElForm,
  ElFormItem,
  ElInput,
  ElMessage
} from 'element-plus';
import screenFull from 'screenfull';
import MapCommon from "./MapCommon.vue";
import { FullScreen } from "@element-plus/icons-vue";
import ChartLine from './ChartLine.vue';
import ChartPie from './ChartPie.vue';

// 导入API方法
import {
  fetchParkOverview,
  fetchParkCoreIndicators,
  fetchGeometriesData,
  fetchEnterpriseDistribution,
  refreshParkStats,
  updateEnterpriseCoord,
  fetchParkBaseInfoList,
  fetchIndustryDistribution,
  fetchIndicatorTrend,
  fetchSpatialPositions
} from '@/api/overview/industrialpark/GlobalSituationOverview.js';

// 空间几何数据
const geometriesArray = ref([]);
// 当前时间
const currentTime = ref('');
// 组件实例（用于全屏操作）
const instance = getCurrentInstance();

// 全屏切换
const togglePanelFullscreen = (panelRefName) => {
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

// 时间格式化
const formatTime = (date) => {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');
  return `${year}年${month}月${day}日 ${hours}时${minutes}分${seconds}秒`;
};

// 园区全域数据概览
const parkOverviewData = ref([]);
const parkNameFilter = ref('高新技术产业园区');
const filteredParkOverview = computed(() => {
  return parkNameFilter.value
    ? parkOverviewData.value.filter(item => item.park_name === parkNameFilter.value)
    : parkOverviewData.value;
});

// 刷新园区数据
const refreshParkData = async () => {
  try {
    const parkId = 'park001';
    const result = await refreshParkStats(parkId);
    parkOverviewData.value = await fetchParkOverview();
    ElMessage.success(result.message);
  } catch (error) {
    ElMessage.error('刷新失败: ' + (error.message || '未知错误'));
  }
};

// 跳转到预警列表
const gotoWarningList = () => {
  ElMessage.info('跳转到事件处置列表页面');
};

// 园区核心指标
const coreIndicators = ref([]);
const indicatorStatPeriod = ref('day');
const indicatorTrendData = ref({ xAxis: [], series: [] });
const selectedTrendIndicator = ref('economic');

// 解构核心指标
const economic = computed(() => coreIndicators.value[0]?.economic || {});
const device = computed(() => coreIndicators.value[0]?.device || {});
const enterprise = computed(() => coreIndicators.value[0]?.enterprise || {});
const event = computed(() => coreIndicators.value[0]?.event || {});

// 刷新核心指标
const refreshCoreIndicators = async () => {
  try {
    coreIndicators.value = await fetchParkCoreIndicators(indicatorStatPeriod.value);
    updateTrendChart();
    ElMessage.success('指标数据已刷新');
  } catch (error) {
    ElMessage.error('刷新失败: ' + (error.message || '未知错误'));
  }
};

// 更新趋势图
const updateTrendChart = () => {
  if (coreIndicators.value.length === 0) return;
  const trend = coreIndicators.value[0].trend_6months;
  const config = {
    economic: { name: '月度营收总额(万元)', data: trend.economic },
    device: { name: '设备在线率(%)', data: trend.device },
    enterprise: { name: '重点企业营收占比(%)', data: trend.enterprise },
    event: { name: '事件日均处置数(件)', data: trend.event }
  };
  indicatorTrendData.value = {
    xAxis: trend.xAxis,
    series: [{ name: config[selectedTrendIndicator.value].name, data: config[selectedTrendIndicator.value].data }]
  };
};

// 切换趋势指标
const handleCardClick = (type) => {
  selectedTrendIndicator.value = type;
  updateTrendChart();
};

// 获取Y轴名称
const getYAxisName = () => {
  const map = { economic: '营收(万元)', device: '在线率(%)', enterprise: '占比(%)', event: '数量(件)' };
  return map[selectedTrendIndicator.value] || '数值';
};

// 初始化趋势图
const initTrendChart = () => {
  if (coreIndicators.value.length > 0) updateTrendChart();
};

// 园区基础信息列表
const parkBaseInfoList = ref([]);
const parkLevelFilter = ref('');
const parkDetailVisible = ref(false);
const currentPark = ref(null);

// 筛选园区列表
const filteredParkBaseInfoList = computed(() => {
  return parkLevelFilter.value
    ? parkBaseInfoList.value.filter(park => park.parkLevel === parkLevelFilter.value)
    : parkBaseInfoList.value;
});

// 获取园区基础信息
const getParkBaseInfoList = async () => {
  try {
    parkBaseInfoList.value = await fetchParkBaseInfoList();
  } catch (error) {
    console.error('获取园区基础信息失败:', error);
    ElMessage.error('加载园区基础信息失败');
  }
};

// 刷新园区基础信息
const refreshParkBaseInfoList = async () => {
  try {
    await getParkBaseInfoList();
    ElMessage.success('园区基础信息已刷新');
  } catch (error) {
    ElMessage.error('刷新失败: ' + (error.message || '未知错误'));
  }
};

// 查看园区详情
const viewParkDetail = (park) => {
  currentPark.value = { ...park };
  parkDetailVisible.value = true;
};

// 空间位置数据
const zoneSpatialData = ref({ positions: {}, sizes: {} });
const enterpriseSpatialData = ref({ positions: {} });

// 获取空间数据
const getZoneSpatialData = async () => {
  try {
    zoneSpatialData.value = await fetchSpatialPositions('zone', 'park001');
  } catch (error) {
    console.error('获取分区空间数据失败:', error);
  }
};

const getEnterpriseSpatialData = async () => {
  try {
    enterpriseSpatialData.value = await fetchSpatialPositions('enterprise', 'park001');
  } catch (error) {
    console.error('获取企业空间数据失败:', error);
  }
};

// 企业分布数据
const enterpriseDistributionData = ref([]);
const currentEnterprise = ref(null);
const enterpriseDetailVisible = ref(false);

// 编辑企业坐标
const editEnterpriseCoord = () => {
  if (!currentEnterprise.value) return;
  coordForm.value = {
    enterpriseName: currentEnterprise.value.enterprise_name,
    currentCoord: currentEnterprise.value.enterprise_coord,
    newCoord: currentEnterprise.value.enterprise_coord
  };
  editCoordVisible.value = true;
};

// 坐标编辑表单
const editCoordVisible = ref(false);
const coordForm = ref({ enterpriseName: '', currentCoord: '', newCoord: '' });

// 保存企业坐标
const saveEnterpriseCoord = async () => {
  try {
    if (!coordForm.value.newCoord) {
      ElMessage.warning('请输入新坐标');
      return;
    }
    const params = {
      enterpriseId: currentEnterprise.value.enterprise_id,
      newCoord: coordForm.value.newCoord
    };
    const result = await updateEnterpriseCoord(params);
    ElMessage.success(result.message);
    editCoordVisible.value = false;
    enterpriseDistributionData.value = await fetchEnterpriseDistribution();
  } catch (error) {
    ElMessage.error('保存失败: ' + (error.message || '未知错误'));
  }
};

// 行业分布数据
const industryDistributionData = ref({ legend: [], series: [] });

// 获取行业分布
const getIndustryDistribution = async () => {
  try {
    industryDistributionData.value = await fetchIndustryDistribution('park001');
  } catch (error) {
    console.error('获取行业分布失败:', error);
    ElMessage.error('加载行业分布数据失败');
  }
};

// 预警通知
const newWarningVisible = ref(false);

// 模拟预警检查
const simulateNewWarning = () => {
  if (parkOverviewData.value.length > 0 && parkOverviewData.value[0].pending_event > 5) {
    newWarningVisible.value = true;
  }
};

// 忽略预警
const ignoreWarning = () => {
  newWarningVisible.value = false;
};

// 处理预警
const handleNewWarning = () => {
  gotoWarningList();
  newWarningVisible.value = false;
};

// 初始化数据
const initData = async () => {
  try {
    const [overviewData, indicators, geometriesData, enterpriseData] = await Promise.all([
      fetchParkOverview(),
      fetchParkCoreIndicators('day'),
      fetchGeometriesData(),
      fetchEnterpriseDistribution()
    ]);
    parkOverviewData.value = overviewData;
    coreIndicators.value = indicators;
    geometriesArray.value = geometriesData;
    enterpriseDistributionData.value = enterpriseData;

    await Promise.all([
      getParkBaseInfoList(),
      getIndustryDistribution(),
      getZoneSpatialData(),
      getEnterpriseSpatialData()
    ]);

    initTrendChart();
    simulateNewWarning();
  } catch (error) {
    console.error('初始化数据失败:', error);
    ElMessage.error('数据加载失败，请刷新页面重试');
  }
};

// 页面挂载时初始化
onMounted(() => {
  currentTime.value = formatTime(new Date());
  const timer = setInterval(() => {
    currentTime.value = formatTime(new Date());
  }, 1000);

  initData();

  const warningTimer = setInterval(simulateNewWarning, 300000);

  onUnmounted(() => {
    clearInterval(timer);
    clearInterval(warningTimer);
  });
});
</script>

<style lang="scss" scoped>
@import url('./common-styles.scss');

.page-container {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  background: url("@/assets/chart/images/bg.jpg");
  background-size: 100% 100%;
  color: #fff;
  padding: 0 1vw;
  box-sizing: border-box;
}

.mainbox {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 0.6vw 0;
  height: 89vh;
  box-sizing: border-box;
  gap: 0.6vw;
}

.panel {
  position: relative;
  height: 100%;
  border: 0.2vh solid rgba(25, 186, 139, 0.17);
  background: url("@/assets/chart/images/line(1).png") rgba(255, 255, 255, .04);
  box-sizing: border-box;
}

.top {
  display: flex;
  gap: 0.6vw;
  height: 52%;
}

.top_left { flex: 1; }
.top_middle { flex: 2; }
.top_right { flex: 1;}

.bottom {
  display: flex;
  gap: 0.6vw;
  height: 44%;
}

.bottom_left { flex: 1; }
.bottom_right {
  flex: 1;
}
</style>
