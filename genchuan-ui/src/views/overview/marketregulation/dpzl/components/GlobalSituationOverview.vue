<template>
  <div class="global-situation-overview">
    <!-- 顶部核心指标区 -->
    <div class="top-core-indicators">
      <!-- 顶部筛选区 -->
      <div class="top-filters">
        <el-select v-model="regionCode" placeholder="区域选择" size="small" @change="handleRegionChange">
          <el-option label="全市" value="" />
          <el-option v-for="region in regionOptions" :key="region.code" :label="region.name" :value="region.code" />
        </el-select>

        <el-select v-model="timeRange" placeholder="时间范围" size="small" @change="handleTimeRangeChange">
          <el-option label="今日" value="today" />
          <el-option label="本周" value="week" />
          <el-option label="本月" value="month" />
          <el-option label="自定义" value="custom" />
        </el-select>

        <el-button size="small" @click="refreshData">
          <el-icon><Refresh /></el-icon>刷新
        </el-button>
      </div>

      <div class="overview-stats-horizontal">
        <div class="overview-stat-item" :class="{ warning: coreIndicators.warning.totalEntity }" @click="showStatDetail('totalEntity')">
          <div class="stat-content">
            <div class="stat-title">市场主体总数</div>
            <div class="stat-value-container">
              <span class="stat-value">{{ formatNumber(coreIndicators.totalEntity) || 0 }}</span>
              <span class="stat-unit">户</span>
            </div>
            <div class="stat-rate positive">{{ coreIndicators.trends.totalEntity || '0%' }}</div>
          </div>
        </div>

        <div class="overview-stat-item" :class="{ warning: coreIndicators.warning.todayNewCase }" @click="showStatDetail('todayNewCase')">
          <div class="stat-content">
            <div class="stat-title">今日新增案件数</div>
            <div class="stat-value-container">
              <span class="stat-value">{{ coreIndicators.todayNewCase || 0 }}</span>
              <span class="stat-unit">件</span>
            </div>
            <div class="stat-rate" :class="coreIndicators.trends.todayNewCaseClass">{{ coreIndicators.trends.todayNewCase || '0%' }}</div>
          </div>
        </div>

        <div class="overview-stat-item" :class="{ warning: coreIndicators.warning.smplQualifiedRate }" @click="showStatDetail('smplQualifiedRate')">
          <div class="stat-content">
            <div class="stat-title">抽检合格率</div>
            <div class="stat-value-container">
              <span class="stat-value">{{ coreIndicators.smplQualifiedRate || 0 }}%</span>
            </div>
            <div class="stat-rate" :class="coreIndicators.trends.smplQualifiedRateClass">{{ coreIndicators.trends.smplQualifiedRate || '0%' }}</div>
          </div>
        </div>

        <div class="overview-stat-item" :class="{ warning: coreIndicators.warning.warnHandleRate }" @click="showStatDetail('warnHandleRate')">
          <div class="stat-content">
            <div class="stat-title">预警处置率</div>
            <div class="stat-value-container">
              <span class="stat-value">{{ coreIndicators.warnHandleRate || 0 }}%</span>
            </div>
            <div class="stat-rate" :class="coreIndicators.trends.warnHandleRateClass">{{ coreIndicators.trends.warnHandleRate || '0%' }}</div>
          </div>
        </div>

        <div class="overview-stat-item" :class="{ warning: coreIndicators.warning.goodCreditRate }" @click="showStatDetail('goodCreditRate')">
          <div class="stat-content">
            <div class="stat-title">信用良好主体占比</div>
            <div class="stat-value-container">
              <span class="stat-value">{{ coreIndicators.goodCreditRate || 0 }}%</span>
            </div>
            <div class="stat-rate" :class="coreIndicators.trends.goodCreditRateClass">{{ coreIndicators.trends.goodCreditRate || '0%' }}</div>
          </div>
        </div>
      </div>
    </div>

    <!-- 主内容区 -->
    <div class="main-content">
      <!-- 左侧2/3区域 - 地图和监管对象分布 -->
      <div class="left-content">
        <!-- 市监全域数据地图 -->
        <div class="panel map-panel" ref="mapPanel">
          <div class="panel-header">
            <h2>
              <el-icon><Location /></el-icon>
              市监全域数据地图
            </h2>
            <div class="header-actions">
              <!-- 图层控制 -->
              <div class="layer-controls">
                <el-checkbox-group v-model="mapLayers" @change="handleMapLayersChange" size="small">
                  <el-checkbox label="supervisionObjects" border>监管对象</el-checkbox>
                  <el-checkbox label="cases" border>案件</el-checkbox>
                  <el-checkbox label="warnings" border>预警事件</el-checkbox>
                </el-checkbox-group>
              </div>

              <el-button size="small" @click="toggleFullScreen('mapPanel')">
                <el-icon><FullScreen /></el-icon>全屏
              </el-button>
            </div>
          </div>

          <div class="panel-body">
            <!-- 地图加载状态 -->
            <div v-if="mapLoading" class="map-loading">
              <el-icon class="loading-icon"><Loading /></el-icon>
              <p>地图加载中...</p>
            </div>

            <!-- 地图组件 -->
            <div v-else class="map-container">
              <MapCommon
                ref="mapComponent"
                :id-name="'market-regulation-map'"
                :geometries-array="mapGeometries"
                @marker-click="handleMapMarkerClick"
                @marker-action="handleMapMarkerAction"
              />

              <!-- 地图统计数据 -->
              <div class="map-stats-card">
                <h3>区域统计</h3>
                <div class="map-stats">
                  <div class="stat-item">
                    <span class="stat-label">监管对象总数:</span>
                    <span class="stat-value">{{ mapOverview.totalSupvObj || 0 }}</span>
                  </div>
                  <div class="stat-item">
                    <span class="stat-label">在办案件数:</span>
                    <span class="stat-value">{{ mapOverview.handlCaseCount || 0 }}</span>
                  </div>
                  <div class="stat-item">
                    <span class="stat-label">未处置预警数:</span>
                    <span class="stat-value" :class="{ 'danger-text': mapOverview.pendWarnCount > 0 }">{{ mapOverview.pendWarnCount || 0 }}</span>
                  </div>
                  <div class="stat-item">
                    <span class="stat-label">区域覆盖率:</span>
                    <span class="stat-value">{{ mapOverview.regionCoverageRate || 0 }}%</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>

        <!-- 监管对象分布视图 -->
        <div class="panel supervision-objects-panel">
          <div class="panel-header">
            <h2>
              <el-icon><DataAnalysis /></el-icon>
              监管对象分布视图
            </h2>
            <div class="header-actions">
              <el-select v-model="supvObjTypeIds" placeholder="对象类型" size="small" multiple @change="handleSupvObjTypeChange">
                <el-option label="企业" value="0" />
                <el-option label="个体户" value="1" />
                <el-option label="特种设备" value="2" />
              </el-select>

              <el-select v-model="supvStatus" placeholder="监管状态" size="small" @change="handleSupvStatusChange">
                <el-option label="全部" value="" />
                <el-option label="未监管" value="0" />
                <el-option label="已监管" value="1" />
                <el-option label="异常" value="2" />
              </el-select>

              <el-button size="small" @click="handleExportSupvObjData">
                <el-icon><Download /></el-icon>导出
              </el-button>
            </div>
          </div>
          <div class="panel-body">
            <div class="supv-obj-content">
              <div class="supv-obj-chart">
                <ChartPie3 :data="supvObjTypeData" title="监管对象类型分布" height="250px" />
              </div>
              <div class="supv-obj-stats">
                <div class="stat-card">
                  <div class="stat-title">监管对象总数</div>
                  <div class="stat-value">{{ supvOverview.totalSupvObj || 0 }}</div>
                </div>
                <div class="stat-card">
                  <div class="stat-title">企业数</div>
                  <div class="stat-value">{{ supvOverview.entCount || 0 }}</div>
                </div>
                <div class="stat-card">
                  <div class="stat-title">个体户数</div>
                  <div class="stat-value">{{ supvOverview.individualCount || 0 }}</div>
                </div>
                <div class="stat-card">
                  <div class="stat-title">特种设备数</div>
                  <div class="stat-value">{{ supvOverview.specEquipCount || 0 }}</div>
                </div>
                <div class="stat-card warning" v-if="supvOverview.unsupvdCount > 0">
                  <div class="stat-title">未监管对象数</div>
                  <div class="stat-value">{{ supvOverview.unsupvdCount || 0 }}</div>
                </div>
                <div class="stat-card">
                  <div class="stat-title">监管覆盖率</div>
                  <div class="stat-value">{{ supvOverview.supvCoverageRate || 0 }}%</div>
                </div>
              </div>
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <!-- 右侧1/3区域 - 案件总览 -->
      <div class="right-content">
        <div class="panel case-overview-panel" ref="caseOverviewPanel">
          <div class="panel-header">
            <h2>
              <el-icon><List /></el-icon>
              市监案件总览视图
            </h2>
            <div class="header-actions">
              <el-select v-model="caseStatusList" placeholder="案件状态" size="small" multiple @change="handleCaseStatusChange">
                <el-option label="待受理" value="0" />
                <el-option label="办理中" value="1" />
                <el-option label="已办结" value="2" />
                <el-option label="已驳回" value="3" />
              </el-select>
              <el-select v-model="caseType" placeholder="案件类型" size="small" @change="handleCaseTypeChange">
                <el-option label="全部" value="" />
                <el-option label="食药案件" value="0" />
                <el-option label="质量案件" value="1" />
              </el-select>
              <el-button size="small" @click="handleExportCaseData">
                <el-icon><Download /></el-icon>导出
              </el-button>
            </div>
          </div>
          <div class="panel-body">
            <div class="case-overview-content">
              <!-- 案件状态分布 -->
              <div class="case-status-chart">
                <ChartPie3 :data="caseStatusData" title="案件状态分布" height="200px" />
              </div>

              <!-- 案件核心指标 -->
              <div class="case-stats">
                <div class="stat-item">
                  <span class="stat-label">案件总数:</span>
                  <span class="stat-value">{{ caseOverview.totalCase || 0 }}</span>
                </div>
                <div class="stat-item">
                  <span class="stat-label">待受理案件:</span>
                  <span class="stat-value">{{ caseOverview.pendAcceptCase || 0 }}</span>
                </div>
                <div class="stat-item">
                  <span class="stat-label">办理中案件:</span>
                  <span class="stat-value">{{ caseOverview.handlCase || 0 }}</span>
                </div>
                <div class="stat-item">
                  <span class="stat-label">已办结案件:</span>
                  <span class="stat-value">{{ caseOverview.completedCase || 0 }}</span>
                </div>
                <div class="stat-item warning" v-if="caseOverview.overtimeCase > 0">
                  <span class="stat-label">超时案件:</span>
                  <span class="stat-value">{{ caseOverview.overtimeCase || 0 }}</span>
                </div>
                <div class="stat-item" :class="{ warning: caseOverview.caseHandleRate < 80 }">
                  <span class="stat-label">案件处置率:</span>
                  <span class="stat-value">{{ caseOverview.caseHandleRate || 0 }}%</span>
                </div>
              </div>

              <!-- 案件列表 -->
              <div class="case-list">
                <el-table :data="filteredCases" border style="width: 100%">
                  <el-table-column prop="caseId" label="案件编号" width="120" />
                  <el-table-column prop="caseName" label="案件名称" min-width="150" />
                  <el-table-column prop="caseType" label="案件类型" width="100">
                    <template #default="{ row }">
                      <span>{{ row.caseType === 0 ? '食药案件' : '质量案件' }}</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="status" label="状态" width="100">
                    <template #default="{ row }">
                      <el-tag :type="getStatusTagType(row.status)">
                        {{ getStatusText(row.status) }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column prop="acceptTime" label="受理时间" width="150" />
                  <el-table-column prop="overtimeDays" label="超时天数" width="100">
                    <template #default="{ row }">
                      <el-tag v-if="row.overtimeDays > 0" type="danger">{{ row.overtimeDays }}天</el-tag>
                      <span v-else>-</span>
                    </template>
                  </el-table-column>
                  <el-table-column label="操作" width="80">
                    <template #default="{ row }">
                      <el-button size="small" type="primary" text @click="viewCaseDetail(row.caseId)">详情</el-button>
                    </template>
                  </el-table-column>
                </el-table>

                <el-pagination
                  v-if="totalCases > 0"
                  :current-page="currentPage"
                  :page-size="pageSize"
                  :total="totalCases"
                  :page-sizes="[10, 20, 50]"
                  layout="total, sizes, prev, pager, next, jumper"
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                />
              </div>
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>
    </div>

    <!-- 案件详情弹窗 -->
    <el-dialog v-model="caseDetailVisible" title="案件详情" width="800px" append-to-body>
      <div v-if="currentCaseDetail">
        <div class="detail-section">
          <h3>基本信息</h3>
          <div class="info-item">
            <span class="info-label">案件编号:</span>
            <span class="info-value">{{ currentCaseDetail.caseId || '暂无' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">案件名称:</span>
            <span class="info-value">{{ currentCaseDetail.caseName || '暂无' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">案件类型:</span>
            <span class="info-value">{{ currentCaseDetail.caseType === 0 ? '食药案件' : '质量案件' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">案件状态:</span>
            <span class="info-value">
              <el-tag :type="getStatusTagType(currentCaseDetail.status)">{{ getStatusText(currentCaseDetail.status) }}</el-tag>
            </span>
          </div>
          <div class="info-item">
            <span class="info-label">受理时间:</span>
            <span class="info-value">{{ currentCaseDetail.acceptTime || '暂无' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">办理时限:</span>
            <span class="info-value">{{ currentCaseDetail.dueTime || '暂无' }}</span>
          </div>
          <div class="info-item" v-if="currentCaseDetail.overtimeDays > 0">
            <span class="info-label">超时天数:</span>
            <span class="info-value danger-text">{{ currentCaseDetail.overtimeDays }}天</span>
          </div>
        </div>

        <div class="detail-section">
          <h3>处置信息</h3>
          <div class="info-item">
            <span class="info-label">处置单位:</span>
            <span class="info-value">{{ currentCaseDetail.handleDept || '暂无' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">承办人员:</span>
            <span class="info-value">{{ currentCaseDetail.handler || '暂无' }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">当前进度:</span>
            <el-progress
              :percentage="currentCaseDetail.progress || 0"
              :status="getProgressStatus(currentCaseDetail.progress)"
              style="width: 120px; display: inline-block; margin-left: 10px;"
            />
          </div>
        </div>

        <div class="detail-section" v-if="currentCaseDetail.description">
          <h3>案件描述</h3>
          <div class="case-description">{{ currentCaseDetail.description }}</div>
        </div>

        <div class="detail-section" v-if="currentCaseDetail.handleLogs && currentCaseDetail.handleLogs.length > 0">
          <h3>处置日志</h3>
          <el-table :data="currentCaseDetail.handleLogs" border style="width: 100%">
            <el-table-column prop="operateTime" label="操作时间" width="160" />
            <el-table-column prop="operator" label="操作人" width="120" />
            <el-table-column prop="content" label="操作内容" />
          </el-table>
        </div>
      </div>
      <div v-else>
        <p>加载案件详情失败，请重试</p>
      </div>
      <template #footer>
        <el-button @click="caseDetailVisible = false">关闭</el-button>
      </template>
    </el-dialog>

    <!-- 核心指标详情弹窗 -->
    <el-dialog v-model="statDetailVisible" :title="currentStatDetail?.title || '指标详情'" width="600px">
      <div v-if="currentStatDetail">
        <div class="stat-detail-value">{{ currentStatDetail.value }}</div>
        <div class="stat-trend-chart">
          <ChartLine3 :data="statTrendData" height="250px" />
        </div>
        <div class="stat-analysis">
          <h4>指标分析</h4>
          <p>{{ getStatAnalysis(currentStatDetail.type) }}</p>
        </div>
      </div>
      <div v-else>
        <p>加载指标详情失败，请重试</p>
      </div>
      <template #footer>
        <el-button @click="statDetailVisible = false">关闭</el-button>
      </template>
    </el-dialog>

    <!-- 导出进度弹窗 -->
    <el-dialog v-model="exportProgressVisible" title="数据导出" width="400px" :close-on-click-modal="false">
      <div class="export-progress-container">
        <el-progress :percentage="exportProgress" :status="exportStatus" />
        <p class="export-message">{{ exportMessage }}</p>
      </div>
      <template #footer>
        <el-button @click="exportProgressVisible = false">关闭</el-button>
      </template>
    </el-dialog>

    <!-- 地图详情弹窗 -->
    <el-dialog v-model="mapDetailVisible" :title="mapDetailTitle" width="800px">
      <div v-if="currentMapDetail">
        <div class="detail-section">
          <h3>基本信息</h3>
          <div class="info-item">
            <span class="info-label">名称:</span>
            <span class="info-value">{{ currentMapDetail.title }}</span>
          </div>
          <div class="info-item">
            <span class="info-label">类型:</span>
            <span class="info-value">
              <el-tag :type="getMapDataTypeTag(currentMapDetail.dataType)">
                {{ getMapDataTypeText(currentMapDetail.dataType) }}
              </el-tag>
            </span>
          </div>
          <div class="info-item">
            <span class="info-label">状态:</span>
            <span class="info-value">
              <el-tag :type="getMapStatusTag(currentMapDetail.status)">
                {{ getMapStatusText(currentMapDetail.status) }}
              </el-tag>
            </span>
          </div>
          <div class="info-item">
            <span class="info-label">位置:</span>
            <span class="info-value">{{ currentMapDetail.position.lat.toFixed(6) }}, {{ currentMapDetail.position.lng.toFixed(6) }}</span>
          </div>
        </div>

        <div class="detail-section" v-if="currentMapDetail.originalData">
          <h3>详细信息</h3>
          <el-descriptions :column="2" border>
            <el-descriptions-item v-for="(value, key) in currentMapDetail.originalData" :key="key" :label="formatFieldName(key)">
              {{ value }}
            </el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <div v-else>
        <p>加载详情失败，请重试</p>
      </div>
      <template #footer>
        <el-button @click="mapDetailVisible = false">关闭</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, computed, nextTick } from 'vue';
import {
  Location,
  DataAnalysis,
  List,
  Download,
  Refresh,
  FullScreen,
  Loading
} from '@element-plus/icons-vue';
import { ElMessage, ElTag, ElProgress, ElTable, ElTableColumn, ElPagination, ElDialog, ElButton, ElSelect, ElOption, ElCheckbox, ElCheckboxGroup, ElDescriptions, ElDescriptionsItem } from 'element-plus';
import screenFull from 'screenfull';
import ChartPie3 from './ChartPie3.vue';
import ChartLine3 from './ChartLine3.vue';
import MapCommon from './MapCommon.vue';

// 导入API方法
import {
  fetchMarketCaseOverview,
  fetchMarketSupvOverview,
  fetchMarketCoreMetrics,
  fetchMarketOverviewMap,
  filterMarketCases,
  fetchMarketCaseDetail,
  exportMarketCaseData,
  exportSupvObjData
} from '@/api/overview/marketregulation/GlobalSituationOverview';

// DOM元素引用
const mapPanel = ref(null);
const caseOverviewPanel = ref(null);
const mapComponent = ref(null);

// 响应式数据定义
const coreIndicators = ref({
  totalEntity: 0,
  todayNewCase: 0,
  smplQualifiedRate: 0,
  warnHandleRate: 0,
  goodCreditRate: 0,
  trends: {
    totalEntity: '0%',
    todayNewCase: '0%',
    todayNewCaseClass: 'positive',
    smplQualifiedRate: '0%',
    smplQualifiedRateClass: 'positive',
    warnHandleRate: '0%',
    warnHandleRateClass: 'positive',
    goodCreditRate: '0%',
    goodCreditRateClass: 'positive'
  },
  warning: {
    totalEntity: false,
    todayNewCase: false,
    smplQualifiedRate: false,
    warnHandleRate: false,
    goodCreditRate: false
  }
});

const caseOverview = ref({
  totalCase: 0,
  pendAcceptCase: 0,
  handlCase: 0,
  completedCase: 0,
  overtimeCase: 0,
  caseHandleRate: 0
});

const supvOverview = ref({
  totalSupvObj: 0,
  entCount: 0,
  individualCount: 0,
  specEquipCount: 0,
  unsupvdCount: 0,
  supvCoverageRate: 0
});

const mapOverview = ref({
  totalSupvObj: 0,
  handlCaseCount: 0,
  pendWarnCount: 0,
  regionCoverageRate: 0
});

// 图表数据
const caseStatusData = ref({ legend: [], series: [] });
const supvObjTypeData = ref({ legend: [], series: [] });
const statTrendData = ref({ xAxis: [], series: [] });

// 案件列表数据
const filteredCases = ref([]);
const totalCases = ref(0);
const currentPage = ref(1);
const pageSize = ref(10);

// 筛选条件
const regionCode = ref('');
const timeRange = ref('today');
const caseStatusList = ref([]);
const caseType = ref('');
const supvObjTypeIds = ref([]);
const supvStatus = ref('');
const mapLayers = ref(['supervisionObjects', 'cases', 'warnings']);

// 地图数据
const mapGeometries = ref([]);

// 弹窗控制
const caseDetailVisible = ref(false);
const statDetailVisible = ref(false);
const exportProgressVisible = ref(false);
const mapDetailVisible = ref(false);
const mapDetailTitle = ref('');
const currentMapDetail = ref(null);

// 导出相关状态
const exportProgress = ref(0);
const exportStatus = ref('');
const exportMessage = ref('');

// 当前选中数据
const currentCaseDetail = ref(null);
const currentStatDetail = ref(null);

// 加载状态
const mapLoading = ref(true);

// 区域选项
const regionOptions = ref([
  { code: '350100', name: '福州市' },
  { code: '350200', name: '厦门市' },
  { code: '350300', name: '莆田市' },
  { code: '350400', name: '三明市' },
  { code: '350500', name: '泉州市' },
  { code: '350600', name: '漳州市' },
  { code: '350700', name: '南平市' },
  { code: '350800', name: '龙岩市' },
  { code: '350900', name: '宁德市' }
]);

// 格式化数字
const formatNumber = (num) => {
  if (!num) return '0';
  if (num >= 10000) {
    return (num / 10000).toFixed(1) + '万';
  }
  return num.toLocaleString();
};

// 处理案件状态标签类型
const getStatusTagType = (status) => {
  const typeMap = {
    '0': 'info',    // 待受理
    '1': 'primary', // 办理中
    '2': 'success', // 已办结
    '3': 'danger'   // 已驳回
  };
  return typeMap[status?.toString()] || 'info';
};

// 处理案件状态文本
const getStatusText = (status) => {
  const textMap = {
    '0': '待受理',
    '1': '办理中',
    '2': '已办结',
    '3': '已驳回'
  };
  return textMap[status?.toString()] || '未知';
};

// 获取进度状态
const getProgressStatus = (progress) => {
  if (progress >= 100) return 'success';
  if (progress > 0) return 'primary';
  return 'warning';
};

// 加载案件列表
const loadFilteredCases = async () => {
  try {
    const filters = {
      statusList: caseStatusList.value,
      type: caseType.value,
      startTime: getTimeRangeParams().startTime,
      endTime: getTimeRangeParams().endTime,
      regionCode: regionCode.value,
      page: currentPage.value,
      size: pageSize.value
    };
    const result = await filterMarketCases(filters);
    filteredCases.value = result.records || [];
    totalCases.value = result.total || 0;
  } catch (error) {
    console.error('加载筛选案件失败:', error);
    filteredCases.value = [];
    totalCases.value = 0;
  }
};

// 分页处理
const handleSizeChange = (val) => {
  pageSize.value = val;
  currentPage.value = 1;
  loadFilteredCases();
};

const handleCurrentChange = (val) => {
  currentPage.value = val;
  loadFilteredCases();
};

// 获取时间范围参数
const getTimeRangeParams = () => {
  const now = new Date();
  const endTime = now.toISOString().slice(0, 19).replace('T', ' ');
  let startTime = '';

  if (timeRange.value === 'today') {
    startTime = now.toISOString().slice(0, 10) + ' 00:00:00';
  } else if (timeRange.value === 'week') {
    const firstDay = new Date(now.setDate(now.getDate() - now.getDay()));
    startTime = firstDay.toISOString().slice(0, 10) + ' 00:00:00';
  } else if (timeRange.value === 'month') {
    startTime = now.toISOString().slice(0, 7) + '-01 00:00:00';
  } else {
    // 默认本月
    startTime = now.toISOString().slice(0, 7) + '-01 00:00:00';
  }

  return { startTime, endTime };
};

// 修复刷新数据函数
const refreshData = async () => {
  try {
    mapLoading.value = true;
    const timeParams = getTimeRangeParams();

    // 并行请求各接口数据
    const [
      caseOverviewData,
      supvOverviewData,
      coreIndicatorsData,
      mapOverviewData
    ] = await Promise.all([
      fetchMarketCaseOverview({
        startTime: timeParams.startTime,
        endTime: timeParams.endTime,
        statusList: caseStatusList.value,
        type: caseType.value
      }),
      fetchMarketSupvOverview({
        regionFullCode: regionCode.value,
        startTime: timeParams.startTime,
        endTime: timeParams.endTime,
        typeIds: supvObjTypeIds.value,
        status: supvStatus.value
      }),
      fetchMarketCoreMetrics({
        regionShortCode: regionCode.value ? regionCode.value.slice(0, 6) : '',
        startTime: timeParams.startTime,
        endTime: timeParams.endTime
      }),
      fetchMarketOverviewMap({
        regionFullCode: regionCode.value,
        supvObjTypeIds: supvObjTypeIds.value,
        caseTypeIds: caseType.value ? [caseType.value] : []
      })
    ]);

    // 更新数据
    caseOverview.value = caseOverviewData || {};
    supvOverview.value = supvOverviewData || {};
    mapOverview.value = mapOverviewData || {};
    updateCoreIndicators(coreIndicatorsData || {});

    // 更新图表数据
    updateCaseStatusData();
    updateSupvObjTypeData();

    // 更新地图数据
    updateMapData();

    // 加载案件列表
    await loadFilteredCases();

    // 确保地图有足够的时间加载
    setTimeout(() => {
      mapLoading.value = false;
      console.log('数据刷新完成，地图数据已更新');
    }, 1500);

  } catch (error) {
    console.error('数据刷新失败:', error);
    ElMessage.error('数据刷新失败，请重试');
    mapLoading.value = false;
  }
};

// 更新核心指标数据
const updateCoreIndicators = (data) => {
  coreIndicators.value = {
    ...coreIndicators.value,
    totalEntity: data.totalEntity || 0,
    todayNewCase: data.todayNewCase || 0,
    smplQualifiedRate: data.smplQualifiedRate || 0,
    warnHandleRate: data.warnHandleRate || 0,
    goodCreditRate: data.goodCreditRate || 0,
    trends: {
      totalEntity: '+2.5%',
      todayNewCase: data.todayNewCase > 20 ? '+15%' : '+5%',
      todayNewCaseClass: data.todayNewCase > 20 ? 'warning' : 'positive',
      smplQualifiedRate: data.smplQualifiedRate < 95 ? '-1.2%' : '+0.5%',
      smplQualifiedRateClass: data.smplQualifiedRate < 95 ? 'danger' : 'positive',
      warnHandleRate: data.warnHandleRate < 80 ? '-3%' : '+1%',
      warnHandleRateClass: data.warnHandleRate < 80 ? 'danger' : 'positive',
      goodCreditRate: data.goodCreditRate < 70 ? '-2%' : '+1.5%',
      goodCreditRateClass: data.goodCreditRate < 70 ? 'warning' : 'positive'
    },
    warning: {
      totalEntity: false,
      todayNewCase: data.todayNewCase > 50,
      smplQualifiedRate: data.smplQualifiedRate < 95,
      warnHandleRate: data.warnHandleRate < 80,
      goodCreditRate: data.goodCreditRate < 70
    }
  };
};

// 更新案件状态图表数据
const updateCaseStatusData = () => {
  caseStatusData.value = {
    legend: ['待受理', '办理中', '已办结', '已驳回'],
    series: [{
      name: '案件数量',
      data: [
        { value: caseOverview.value.pendAcceptCase || 0, name: '待受理', itemStyle: { color: '#94a3b8' } },
        { value: caseOverview.value.handlCase || 0, name: '办理中', itemStyle: { color: '#3B82F6' } },
        { value: caseOverview.value.completedCase || 0, name: '已办结', itemStyle: { color: '#10B981' } },
        { value: (caseOverview.value.totalCase || 0) - (caseOverview.value.pendAcceptCase || 0) - (caseOverview.value.handlCase || 0) - (caseOverview.value.completedCase || 0),
          name: '已驳回', itemStyle: { color: '#EF4444' } }
      ]
    }]
  };
};

// 更新监管对象类型图表数据
const updateSupvObjTypeData = () => {
  supvObjTypeData.value = {
    legend: ['企业', '个体户', '特种设备'],
    series: [{
      name: '数量',
      data: [
        { value: supvOverview.value.entCount || 0, name: '企业', itemStyle: { color: '#3B82F6' } },
        { value: supvOverview.value.individualCount || 0, name: '个体户', itemStyle: { color: '#10B981' } },
        { value: supvOverview.value.specEquipCount || 0, name: '特种设备', itemStyle: { color: '#F59E0B' } }
      ]
    }]
  };
};

// 修复地图数据更新函数
const updateMapData = () => {
  console.log('更新地图数据，图层:', mapLayers.value);

  if (!mapLayers.value || mapLayers.value.length === 0) {
    mapGeometries.value = [];
    return;
  }

  // 生成地图点数据 - 确保数据格式正确
  const geometries = [];

  // 监管对象数据
  if (mapLayers.value.includes('supervisionObjects')) {
    const supervisionCount = Math.min(mapOverview.value.totalSupvObj || 0, 50);
    for (let i = 0; i < supervisionCount; i++) {
      geometries.push({
        x: 26.793227 + (Math.random() - 0.5) * 0.1,
        y: 117.810114 + (Math.random() - 0.5) * 0.1,
        dataType: 'station',
        status: Math.random() > 0.8 ? 'offline' : 'normal',
        name: `监管对象${i + 1}`,
        team: ['食品监管队', '特种设备队', '市场秩序队'][Math.floor(Math.random() * 3)],
        type: ['企业', '个体户', '特种设备'][Math.floor(Math.random() * 3)]
      });
    }
    console.log(`添加 ${supervisionCount} 个监管对象标记`);
  }

  // 案件数据
  if (mapLayers.value.includes('cases')) {
    const caseCount = Math.min(mapOverview.value.handlCaseCount || 0, 30);
    for (let i = 0; i < caseCount; i++) {
      geometries.push({
        x: 26.793227 + (Math.random() - 0.5) * 0.1,
        y: 117.810114 + (Math.random() - 0.5) * 0.1,
        dataType: 'vehicle',
        status: Math.random() > 0.5 ? 'moving' : 'parked',
        name: `案件${i + 1}`,
        team: ['一队', '二队', '三队'][Math.floor(Math.random() * 3)],
        type: ['食药案件', '质量案件'][Math.floor(Math.random() * 2)]
      });
    }
    console.log(`添加 ${caseCount} 个案件标记`);
  }

  // 预警数据
  if (mapLayers.value.includes('warnings')) {
    const warningCount = Math.min(mapOverview.value.pendWarnCount || 0, 20);
    for (let i = 0; i < warningCount; i++) {
      geometries.push({
        x: 26.793227 + (Math.random() - 0.5) * 0.1,
        y: 117.810114 + (Math.random() - 0.5) * 0.1,
        dataType: 'staff',
        status: Math.random() > 0.6 ? 'busy' : 'online',
        name: `预警${i + 1}`,
        team: ['预警中心', '应急处理队'][Math.floor(Math.random() * 2)],
        type: ['高风险', '中风险', '低风险'][Math.floor(Math.random() * 3)]
      });
    }
    console.log(`添加 ${warningCount} 个预警标记`);
  }

  console.log('生成地图几何数据总数:', geometries.length);
  mapGeometries.value = geometries;

  // 确保地图组件已初始化
  nextTick(() => {
    if (mapComponent.value && typeof mapComponent.value.updateMarkers === 'function') {
      setTimeout(() => {
        mapComponent.value.updateMarkers();
      }, 100);
    }
  });
};

// 查看案件详情
const viewCaseDetail = async (caseId) => {
  try {
    const detail = await fetchMarketCaseDetail(caseId);
    currentCaseDetail.value = detail;
    caseDetailVisible.value = true;
  } catch (error) {
    console.error('获取案件详情失败:', error);
    ElMessage.error('获取案件详情失败，请重试');
  }
};

// 查看指标详情
const showStatDetail = (statType) => {
  const titleMap = {
    totalEntity: '市场主体总数',
    todayNewCase: '今日新增案件数',
    smplQualifiedRate: '抽检合格率',
    warnHandleRate: '预警处置率',
    goodCreditRate: '信用良好主体占比'
  };

  const valueMap = {
    totalEntity: `${formatNumber(coreIndicators.value.totalEntity)} 户`,
    todayNewCase: `${coreIndicators.value.todayNewCase} 件`,
    smplQualifiedRate: `${coreIndicators.value.smplQualifiedRate}%`,
    warnHandleRate: `${coreIndicators.value.warnHandleRate}%`,
    goodCreditRate: `${coreIndicators.value.goodCreditRate}%`
  };

  // 模拟趋势数据
  const days = [];
  const today = new Date();
  for (let i = 6; i >= 0; i--) {
    const date = new Date(today);
    date.setDate(today.getDate() - i);
    days.push(`${date.getMonth() + 1}-${date.getDate()}`);
  }

  // 随机生成趋势数据
  const generateRandomData = (baseValue, count) => {
    return Array.from({ length: count }, () => {
      const random = Math.random() * 10 - 5; // -5 到 5 之间的随机数
      return Math.max(0, Math.round((baseValue + random) * 10) / 10);
    });
  };

  let baseValue = 0;
  switch (statType) {
    case 'totalEntity':
      baseValue = coreIndicators.value.totalEntity / 1000;
      break;
    case 'todayNewCase':
      baseValue = coreIndicators.value.todayNewCase;
      break;
    case 'smplQualifiedRate':
    case 'warnHandleRate':
    case 'goodCreditRate':
      baseValue = coreIndicators.value[statType];
      break;
  }

  statTrendData.value = {
    xAxis: days,
    series: [{
      name: titleMap[statType],
      data: generateRandomData(baseValue, 7)
    }]
  };

  currentStatDetail.value = {
    type: statType,
    title: titleMap[statType],
    value: valueMap[statType]
  };
  statDetailVisible.value = true;
};

// 获取指标分析文本
const getStatAnalysis = (statType) => {
  const analysisMap = {
    totalEntity: '市场主体总数反映了区域内经济活跃程度，总数稳步增长通常表明经济环境良好。',
    todayNewCase: '今日新增案件数反映了市场监管的实时状况，数量异常增长可能预示特定领域问题。',
    smplQualifiedRate: '抽检合格率是衡量产品质量的重要指标，较高的合格率表明市场产品质量整体较好。',
    warnHandleRate: '预警处置率反映了监管效率，较高的处置率表明监管响应及时有效。',
    goodCreditRate: '信用良好主体占比体现了市场整体信用环境，较高的占比有助于营造公平竞争环境。'
  };
  return analysisMap[statType] || '暂无详细分析数据。';
};

// 修复地图标记点击处理函数
const handleMapMarkerClick = (properties) => {
  console.log('地图标记点击:', properties);
  mapDetailTitle.value = properties.title || '地图标记详情';
  currentMapDetail.value = {
    title: properties.title,
    dataType: properties.dataType,
    status: properties.status,
    team: properties.team,
    type: properties.type,
    position: {
      lat: properties.originalData?.x || 26.793227,
      lng: properties.originalData?.y || 117.810114
    },
    originalData: properties.originalData || {}
  };
  mapDetailVisible.value = true;
};

// 地图标记操作处理
const handleMapMarkerAction = ({ action, marker }) => {
  const actions = {
    'contact': `正在联系 ${marker.properties.title}...`,
    'dispatch': `正在调度 ${marker.properties.title}...`,
    'navigate': `正在导航至 ${marker.properties.title}...`,
    'detail': `查看 ${marker.properties.title} 的详细信息...`
  };

  ElMessage.success(actions[action] || '操作执行成功');
};

// 地图数据类型标签
const getMapDataTypeTag = (dataType) => {
  const typeMap = {
    'staff': 'primary',
    'vehicle': 'warning',
    'station': 'success'
  };
  return typeMap[dataType] || 'info';
};

// 地图数据类型文本
const getMapDataTypeText = (dataType) => {
  const textMap = {
    'staff': '预警事件',
    'vehicle': '案件',
    'station': '监管对象'
  };
  return textMap[dataType] || '未知';
};

// 地图状态标签
const getMapStatusTag = (status) => {
  const statusMap = {
    'online': 'success',
    'busy': 'warning',
    'offline': 'danger',
    'moving': 'primary',
    'parked': 'info',
    'normal': 'success'
  };
  return statusMap[status] || 'info';
};

// 地图状态文本
const getMapStatusText = (status) => {
  const textMap = {
    'online': '在线',
    'busy': '忙碌',
    'offline': '离线',
    'moving': '移动中',
    'parked': '已停放',
    'normal': '正常'
  };
  return textMap[status] || '未知';
};

// 格式化字段名
const formatFieldName = (key) => {
  const map = {
    'x': '纬度',
    'y': '经度',
    'dataType': '数据类型',
    'status': '状态',
    'name': '名称',
    'team': '所属队伍',
    'type': '类型'
  };
  return map[key] || key;
};

// 导出案件数据
const handleExportCaseData = async () => {
  exportProgressVisible.value = true;
  exportProgress.value = 0;
  exportStatus.value = '';
  exportMessage.value = '准备导出数据...';

  try {
    const params = {
      statusList: caseStatusList.value,
      type: caseType.value,
      startTime: getTimeRangeParams().startTime,
      endTime: getTimeRangeParams().endTime,
      regionCode: regionCode.value
    };

    // 模拟进度更新
    const progressInterval = setInterval(() => {
      if (exportProgress.value < 90) {
        exportProgress.value += 10;
        exportMessage.value = `正在导出数据... ${exportProgress.value}%`;
      }
    }, 200);

    // 执行导出
    const result = await exportMarketCaseData(params);

    clearInterval(progressInterval);
    exportProgress.value = 100;
    exportStatus.value = 'success';
    exportMessage.value = `导出成功: ${result.fileName}`;
    ElMessage.success('案件数据导出成功');

  } catch (error) {
    exportProgress.value = 0;
    exportStatus.value = 'exception';
    exportMessage.value = `导出失败: ${error.message}`;
    ElMessage.error('导出失败: ' + error.message);
  }
};

// 导出监管对象数据
const handleExportSupvObjData = async () => {
  exportProgressVisible.value = true;
  exportProgress.value = 0;
  exportStatus.value = '';
  exportMessage.value = '准备导出数据...';

  try {
    const params = {
      regionFullCode: regionCode.value,
      typeIds: supvObjTypeIds.value,
      status: supvStatus.value,
      startTime: getTimeRangeParams().startTime,
      endTime: getTimeRangeParams().endTime
    };

    // 模拟进度更新
    const progressInterval = setInterval(() => {
      if (exportProgress.value < 90) {
        exportProgress.value += 10;
        exportMessage.value = `正在导出数据... ${exportProgress.value}%`;
      }
    }, 200);

    // 执行导出
    const result = await exportSupvObjData(params);

    clearInterval(progressInterval);
    exportProgress.value = 100;
    exportStatus.value = 'success';
    exportMessage.value = `导出成功: ${result.fileName}`;
    ElMessage.success('监管对象数据导出成功');

  } catch (error) {
    exportProgress.value = 0;
    exportStatus.value = 'exception';
    exportMessage.value = `导出失败: ${error.message}`;
    ElMessage.error('导出失败: ' + error.message);
  }
};

// 添加图层变化处理函数
const handleMapLayersChange = () => {
  console.log('地图图层变化:', mapLayers.value);
  updateMapData();
};

// 切换全屏
const toggleFullScreen = (panelRef) => {
  const panelElement = panelRef === 'mapPanel' ? mapPanel.value : caseOverviewPanel.value;
  if (screenFull.isEnabled) {
    if (screenFull.isFullscreen) {
      screenFull.exit();
    } else {
      screenFull.request(panelElement);
    }
  }
};

// 筛选条件变化处理
const handleRegionChange = () => {
  refreshData();
};

const handleTimeRangeChange = () => {
  refreshData();
};

const handleCaseStatusChange = () => {
  currentPage.value = 1;
  refreshData();
};

const handleCaseTypeChange = () => {
  currentPage.value = 1;
  refreshData();
};

const handleSupvObjTypeChange = () => {
  refreshData();
};

const handleSupvStatusChange = () => {
  refreshData();
};

// 生命周期
onMounted(() => {
  refreshData();

  // 设置定时刷新
  const autoRefreshTimer = setInterval(() => {
    refreshData();
  }, 300000); // 5分钟刷新一次

  onUnmounted(() => {
    clearInterval(autoRefreshTimer);
  });
});
</script>

<style scoped>
/* 导入公共样式 */
@import './common-styles.scss';

/* 顶部筛选区样式 */
.top-filters {
  display: flex;
  gap: 12px;
  align-items: center;
  margin-bottom: 16px;
  justify-content: flex-end;
}

.top-filters .el-select {
  width: 150px;
}

/* 地图面板样式调整 */
.map-panel {
  height: 60%;
  position: relative;
}

.map-panel .panel-body {
  position: relative;
  padding: 0;
  height: calc(100% - 40px); /* 减去头部高度 */
  display: flex;
  flex-direction: column;
}

.map-container {
  flex: 1;
  position: relative;
  width: 100%;
  height: 100%;
  border-radius: 4px;
  overflow: hidden;
  background-color: rgba(15, 23, 42, 0.5);
}

.map-loading {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: rgba(15, 23, 42, 0.8);
  z-index: 100;
}

.loading-icon {
  font-size: 40px;
  color: #3B82F6;
  margin-bottom: 15px;
  animation: spin 2s linear infinite;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.map-stats-card {
  position: absolute;
  top: 15px;
  left: 15px;
  background-color: rgba(15, 23, 42, 0.9);
  border-radius: 8px;
  padding: 15px;
  z-index: 10;
  border: 1px solid rgba(59, 130, 246, 0.2);
  min-width: 280px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
}

.map-stats-card h3 {
  font-size: 14px;
  color: #3B82F6;
  margin: 0 0 12px 0;
  text-align: left;
  border-bottom: 1px solid rgba(59, 130, 246, 0.2);
  padding-bottom: 8px;
}

.map-stats-card .map-stats {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.map-stats-card .stat-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.map-stats-card .stat-label {
  color: #94A3B8;
  font-size: 13px;
}

.map-stats-card .stat-value {
  color: #E2E8F0;
  font-size: 14px;
  font-weight: 500;
}

/* 监管对象分布面板 */
.supervision-objects-panel {
  height: 40%;
  margin-top: 15px;
}

.supv-obj-content {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.supv-obj-chart {
  height: 200px;
  margin-bottom: 15px;
}

.supv-obj-stats {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 12px;
}

/* 案件总览面板 */
.case-overview-panel {
  height: 100%;
}

.case-overview-content {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.case-status-chart {
  height: 180px;
  margin-bottom: 15px;
}

.case-stats {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
  margin-bottom: 15px;
}

.case-list {
  flex: 1;
  overflow: auto;
}

/* 统计卡片样式调整 */
.stat-card {
  text-align: center;
  padding: 12px;
  background-color: rgba(15, 23, 42, 0.5);
  border-radius: 6px;
  border: 1px solid rgba(59, 130, 246, 0.2);
}

.stat-card.warning {
  border-color: #EF4444;
  background-color: rgba(239, 68, 68, 0.1);
}

/* 统计项样式 */
.stat-item {
  display: flex;
  align-items: center;
  padding: 8px 12px;
  background-color: rgba(15, 23, 42, 0.3);
  border-radius: 6px;
}

.stat-item.warning {
  border: 1px solid rgba(239, 68, 68, 0.3);
  background-color: rgba(239, 68, 68, 0.1);
}

.stat-label {
  color: #94A3B8;
  font-size: 13px;
  flex: 1;
}

.stat-value {
  color: #E2E8F0;
  font-size: 14px;
  font-weight: 500;
}

/* 响应式布局 */
@media (max-width: 1600px) {
  .overview-stat-item .stat-value {
    font-size: 24px;
  }

  .supv-obj-stats {
    grid-template-columns: repeat(2, 1fr);
  }

  .case-stats {
    grid-template-columns: 1fr;
  }
}

/* 滚动条优化 */
.case-list :deep(.el-table__body-wrapper) {
  max-height: 300px;
  overflow-y: auto;
}

.case-list :deep(.el-table__body-wrapper)::-webkit-scrollbar {
  width: 6px;
}

.case-list :deep(.el-table__body-wrapper)::-webkit-scrollbar-thumb {
  background-color: rgba(59, 130, 246, 0.3);
  border-radius: 3px;
}

/* 左侧内容区域布局 */
.left-content {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.right-content {
  display: flex;
  flex-direction: column;
  height: 100%;
}

/* 确保面板内部布局正确 */
.panel-body {
  display: flex;
  flex-direction: column;
}

/* 图层控制样式 */
.layer-controls {
  display: flex;
  gap: 10px;
  align-items: center;
}

.layer-controls :deep(.el-checkbox-group) {
  display: flex;
  gap: 8px;
}
</style>
