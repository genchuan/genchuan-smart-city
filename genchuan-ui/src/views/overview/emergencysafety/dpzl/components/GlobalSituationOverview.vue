<template>
  <div class="page-container">
    <div class="mainbox">
      <div class="left" style="min-width: 2vw;">
        <div class="left_top">
          <!-- 应急全域数据概览 -->
          <div class="core-overview-panel panel" ref="emergencyOverviewPanel">
            <div class="panel-header">
              <h2>应急全域数据概览</h2>
              <div class="header-actions">
                <el-select v-model="overviewTimeRange" placeholder="选择时间范围" size="small">
                  <el-option label="今日" value="today" />
                  <el-option label="本周" value="week" />
                  <el-option label="本月" value="month" />
                </el-select>
              </div>
            </div>
            <div class="panel-body">
              <div class="overview-stats">
                <!-- 1. 应急事件总数 -->
                <div class="overview-stat-item" @click="jumpToEmergencyDisposalProgress()" style="cursor: pointer;">
                  <div class="stat-title">应急事件总数</div>
                  <div class="stat-value">{{ eventStats?.total_evt_count || 0 }}</div>
                  <div class="stat-unit">
                    <span>件</span>
                  </div>
                </div>
                <!-- 2. 在处置事件数 -->
                <div class="overview-stat-item" @click="jumpToEventDisposalTracking()" style="cursor: pointer;">
                  <div class="stat-title">在处置事件数</div>
                  <div class="stat-value">{{ eventStats?.handl_count || 0 }}</div>
                  <div class="stat-unit">
                    <span>件</span>
                  </div>
                </div>
                <!-- 3. 已办结事件数 -->
                <div class="overview-stat-item" @click="jumpToEmergencyDisposalProgress()" style="cursor: pointer;">
                  <div class="stat-title">已办结事件数</div>
                  <div class="stat-value">{{ eventStats?.completed_count || 0 }}</div>
                  <div class="stat-unit">
                    <span>件</span>
                  </div>
                </div>
                <!-- 4. 应急资源总数 -->
                <div class="overview-stat-item" @click="jumpToResourceDistribution()" style="cursor: pointer;">
                  <div class="stat-title">应急资源总数</div>
                  <div class="stat-value">{{ resourceStats?.total_res_count || 0 }}</div>
                  <div class="stat-unit">
                    <span>件/套</span>
                  </div>
                </div>
                <!-- 5. 高风险隐患数 -->
                <div class="overview-stat-item" @click="jumpToAccidentHazardWarning()" style="cursor: pointer;">
                  <div class="stat-title">高风险隐患数</div>
                  <div class="stat-value">{{ riskRegionStats?.high_risk_count || 0 }}</div>
                  <div class="stat-unit">
                    <span>项</span>
                  </div>
                </div>
                <!-- 6. 行政区划覆盖数 -->
                <div class="overview-stat-item">
                  <div class="stat-title">行政区划覆盖数</div>
                  <div class="stat-value">{{ riskRegionStats?.region_cover_count || 0 }}</div>
                  <div class="stat-unit">
                    <span>个</span>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="left_bottom">
          <!-- 应急核心指标 -->
          <div class="panel core-indicators-panel" ref="coreIndicatorsPanelLeft">
            <div class="panel-body">
              <div class="indicator-cards">
                <template v-if="leftIndicators.length">
                  <div
                    v-for="indicator in leftIndicators"
                    :key="indicator.key"
                    class="indicator-card1"
                    @click="showIndicatorDetail(indicator)"
                  >
                    <div class="indicator-title">{{ indicator.name }}</div>
                    <div class="indicator-compliance1">
                      <el-progress
                        :percentage="getIndicatorPercentage(indicator)"
                        type="circle"
                        :stroke-width="circleStrokeWidth"
                        :stroke-linecap="'round'"
                        :color="getIndicatorColor(indicator)"
                        :width="circleWidth"
                      >
                        <template #default>
                          <div class="compliance-text">
                            {{ indicator.value }}
                            <span class="indicator-unit">{{ indicator.unit }}</span>
                          </div>
                        </template>
                      </el-progress>
                    </div>
                  </div>
                </template>
                <!-- 空状态提示 -->
                <div class="empty-tip" v-else>暂无核心指标数据</div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        当前时间：{{ currentTime }}
      </div>
      <div class="middle" style="min-width: 2vw;">
        <div class="panel middle_top" ref="map">
          <div class="header-actions">
            <p>应急风险分布地图</p>
            <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('map')">
              <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
            </button>
          </div>
          <map-common idName="chinaEcharts" :geometriesArray="geometriesArray" />
          <div class="panel-footer"></div>
        </div>
        <!-- 预警信息 -->
        <div class="panel middle_bottom" ref="warningsPanel">
          <div class="panel-header">
            <h2>近期预警信息</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('warningsPanel')">
                <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <dv-scroll-board
              :config="warningScrollConfig"
              style="width: 100%; height: 86%;"
            />
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>
      <div class="right" style="min-width: 2vw;">
        <div class="panel right_top" ref="list">
          <div class="panel-header">
            <h2>应急资源总览</h2>
            <div class="header-actions">
              <!-- 资源类型筛选 -->
              <el-select
                v-model="resourceTypeFilter"
                placeholder="资源类型"
                size="small"
                style="margin-right: 0.5vw; width: 6vw;"
              >
                <el-option label="全部" value="" />
                <el-option label="设备" value="设备" />
                <el-option label="应急物资" value="应急物资" />
                <el-option label="救援队伍" value="救援队伍" />
                <el-option label="公共设施" value="公共设施" />
              </el-select>
              <!-- 库存状态筛选 -->
              <el-select
                v-model="resourceStatusFilter"
                placeholder="库存状态"
                size="small"
                style="margin-right: 0.5vw; width: 6vw;"
              >
                <el-option label="全部" value="" />
                <el-option label="充足" value="充足" />
                <el-option label="紧张" value="紧张" />
                <el-option label="短缺" value="短缺" />
              </el-select>
              <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('list')">
                <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
              </button>
            </div>
          </div>
          <div class="resources-list">
            <!-- 应急资源总览列表 -->
            <el-table
              :data="filteredResources"
              border
              size="small"
              @row-click="showResourceDetail"
              height="calc(100% - 30px)"
              width="100%"
            >
              <el-table-column
                prop="resId"
                label="资源ID"
              />
              <el-table-column
                prop="resName"
                label="资源名称"
              />
              <el-table-column
                prop="resType"
                label="资源类型"
              />
              <el-table-column
                prop="totalQty"
                label="库存数量"
              />
              <el-table-column
                prop="availableQty"
                label="可用数量"
              />
              <el-table-column prop="status" label="库存状态">
                <template #default="scope">
                  <el-tag :type="scope.row.stockStatus">
                    {{ scope.row.stockStatus }}
                  </el-tag>
                </template>
              </el-table-column>
              <el-table-column
                label="操作"
              >
                <template #default="scope">
                  <el-button
                    size="small"
                    type="text"
                    @click="showResourceDetail(scope.row)"
                  >
                    详情
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel right_bottom">
          <!-- 应急核心指标 -->
          <div class="core-indicators-panel" ref="coreIndicatorsPanelRight">
            <div class="panel-body">
              <div class="indicator-cards">
                <template v-if="rightIndicators.length">
                  <div
                    v-for="indicator in rightIndicators"
                    :key="indicator.key"
                    class="indicator-card2"
                    @click="showIndicatorDetail(indicator)"
                  >
                    <div class="indicator-title">{{ indicator.name }}</div>
                    <div class="indicator-compliance2">
                      <el-progress
                        :percentage="getIndicatorPercentage(indicator)"
                        type="circle"
                        :stroke-width="circleStrokeWidth"
                        :stroke-linecap="'round'"
                        :color="getIndicatorColor(indicator)"
                        :width="circleWidth"
                      >
                        <template #default>
                          <div class="compliance-text">
                            {{ indicator.value }}
                            <span class="indicator-unit">{{ indicator.unit }}</span>
                          </div>
                        </template>
                      </el-progress>
                    </div>
                  </div>
                </template>
                <!-- 空状态提示 -->
                <div class="empty-tip" v-else>暂无核心指标数据</div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
    </div>

    <!-- 指标详情弹窗 -->
    <el-dialog
      v-model="indicatorDetailVisible"
      :title="currentIndicator?.name || '指标详情'"
      width="600px"
    >
      <div class="indicator-detail">
        <!-- 不同指标的专属字段 -->
        <div class="detail-section" v-if="currentIndicator?.extData">
          <el-descriptions column="2" border>
            <template v-if="['emergEventCompleteRate', 'earlyWarnAccRate'].includes(currentIndicator.key)">
              <el-descriptions-item label="已完成数">{{ currentIndicator.extData.completedCount || 0 }} 件</el-descriptions-item>
              <el-descriptions-item label="总数">{{ currentIndicator.extData.totalCount || 0 }} 件</el-descriptions-item>
            </template>
            <template v-if="currentIndicator.key === 'resUseRate'">
              <el-descriptions-item label="已使用数">{{ currentIndicator.extData.usedCount || 0 }} 件</el-descriptions-item>
              <el-descriptions-item label="总数">{{ currentIndicator.extData.totalCount || 0 }} 件</el-descriptions-item>
            </template>
            <template v-if="currentIndicator.key === 'riskRectifyRate'">
              <el-descriptions-item label="已整改数">{{ currentIndicator.extData.rectifiedCount || 0 }} 项</el-descriptions-item>
              <el-descriptions-item label="总数">{{ currentIndicator.extData.totalCount || 0 }} 项</el-descriptions-item>
            </template>
          </el-descriptions>
        </div>
        <div class="detail-section">
          <el-descriptions column="1" border>
            <el-descriptions-item label="当前值">
              <span class="current-value">
                {{ currentIndicator?.value }} {{ currentIndicator?.unit }}
              </span>
            </el-descriptions-item>
            <el-descriptions-item label="预警阈值">
              {{ currentIndicator?.warningThreshold || '-' }}
              <span v-if="currentIndicator?.warningThreshold">
                {{ currentIndicator?.unit === '分钟' ? '分钟' : '%' }}
              </span>
            </el-descriptions-item>
            <el-descriptions-item label="更新时间" :span="2">{{ currentIndicator?.updateTime || '-' }}</el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <template #footer>
        <el-button @click="indicatorDetailVisible = false">关闭</el-button>
      </template>
    </el-dialog>

    <!-- 资源详情弹窗 -->
    <el-dialog
      v-model="resourceDetailVisible"
      :title="currentResource?.resource_name || '资源详情'"
      width="600px"
    >
      <div class="resource-detail">
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="资源ID">{{ currentResource?.resId }}</el-descriptions-item>
            <el-descriptions-item label="资源类型">{{ currentResource?.resType }}</el-descriptions-item>
            <el-descriptions-item label="存放位置">{{ currentResource?.storageLoc }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>库存信息</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="库存总量">{{ currentResource?.totalQty }}</el-descriptions-item>
            <el-descriptions-item label="可用数量">{{ currentResource?.availableQty }}</el-descriptions-item>
            <el-descriptions-item label="库存状态">
              <el-tag :type="currentResource?.stockStatus">
                {{ currentResource?.stockStatus }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="管理责任人">{{ currentResource?.mngrName }}</el-descriptions-item>
            <el-descriptions-item label="数据更新时间">
              {{ currentResource?.updateTime ? formatDate(currentResource.updateTime) : '暂无' }}
            </el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>资源调拨</h3>
          <el-form>
            <el-form-item label="调拨数量" required>
              <el-input type="number" v-model="allocationQuantity" min="1" :max="currentResource?.availableQty" />
            </el-form-item>
            <el-form-item label="调拨原因" required>
              <el-input type="textarea" v-model="allocationReason" rows="2" />
            </el-form-item>
            <el-form-item label="接收人" required>
              <el-input v-model="allocationReceiver" />
            </el-form-item>
          </el-form>
        </div>
      </div>
      <template #footer>
        <el-button @click="resourceDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="handleSubmitResourceAllocation"
          :disabled="!allocationQuantity || !allocationReason || !allocationReceiver"
        >
          提交调拨申请
        </el-button>
      </template>
    </el-dialog>

    <!-- 分析报告弹窗 -->
    <el-dialog
      v-model="reportVisible"
      :title="currentReport?.title || '应急分析报告'"
      width="800px"
      :max-height="600"
    >
      <div class="report-content">
        <div class="report-header">
          <div class="report-stat-time">统计时间: {{ currentReport?.stat_time }}</div>
          <div class="report-create-user">生成人: {{ currentReport?.create_user }}</div>
        </div>
        <div class="report-body">
          <p>{{ currentReport?.content }}</p>
        </div>
      </div>
      <template #footer>
        <el-button @click="reportVisible = false">关闭</el-button>
        <el-button type="primary" @click="exportReport">导出报告</el-button>
      </template>
    </el-dialog>

    <!-- 图层控制弹窗 -->
    <el-dialog
      v-model="layerControlVisible"
      title="图层控制"
      width="300px"
    >
      <el-checkbox-group v-model="visibleLayers">
        <el-checkbox label="危化品" />
        <el-checkbox label="地质灾害" />
        <el-checkbox label="消防" />
        <el-checkbox label="交通" />
        <el-checkbox label="电力" />
      </el-checkbox-group>
      <template #footer>
        <el-button @click="layerControlVisible = false">取消</el-button>
        <el-button type="primary" @click="applyLayerControl">应用</el-button>
      </template>
    </el-dialog>

    <!-- 新预警通知弹窗 -->
    <el-dialog
      v-model="newWarningVisible"
      title="新预警通知"
      width="400px"
      :show-close="false"
    >
      <div class="warning-notification">
        <div class="warning-icon">
          <Warning />
        </div>
        <div class="warning-content">
          <p><strong>{{ newWarning?.title }}</strong></p>
          <p>{{ newWarning?.content }}</p>
          <p class="warning-time">发生时间: {{ newWarning?.time }}</p>
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
import { ref, computed, onMounted, onUnmounted, getCurrentInstance, reactive } from 'vue';
import {
  ElSelect,
  ElOption,
  ElButton,
  ElTag,
  ElInput,
  ElDialog,
  ElDescriptions,
  ElDescriptionsItem,
  ElForm,
  ElFormItem,
  ElMessage,
  ElCheckbox,
  ElCheckboxGroup,
  ElTable,
  ElTableColumn,
  ElProgress
} from 'element-plus';
import { formatDate } from '@/utils/formatTime'
import screenFull from 'screenfull';
import { FullScreen, Warning } from "@element-plus/icons-vue";
import MapCommon from './MapCommon.vue';
import { useRouter } from 'vue-router';

// 导入API方法
import {
  fetchEmergencyGlobalOverview,
  fetchEmergencyRiskGeometries,
  fetchEmergencyCoreIndicators,
  fetchEmergencyResources,
  submitResourceAllocation,
  fetchWarningScrollData,
  fetchWarningTypes
} from '@/api/overview/emergencysafety/GlobalSituationOverview.js';

// 初始化响应式数据
const geometriesArray = ref([]);
const currentTime = ref('');
const instance = getCurrentInstance();
const router = useRouter();

// 环形进度条尺寸配置
const circleSize = reactive({
  width: 7.5,
  strokeWidth: 0.7
});
const circleWidth = computed(() => window.innerWidth * (circleSize.width / 100));
const circleStrokeWidth = computed(() => window.innerWidth * (circleSize.strokeWidth / 100));

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

// 计算环形图百分比
const getIndicatorPercentage = (indicator) => {
  if (!indicator) return 0;
  // 百分比类型直接返回数值，时长类型按比例（假设最大100分钟）
  if (indicator.unit === '%') {
    return Math.min(Math.round(indicator.value), 100);
  } else {
    return Math.min(Math.round((indicator.value / 100) * 100), 100); // 时长转百分比展示
  }
};

// 获取环形图颜色（适配业务逻辑版）
const getIndicatorColor = (indicator) => {
  if (!indicator) return '#67C23A';

  const percentage = getIndicatorPercentage(indicator);

  if (indicator.key === 'avgHandleEndure') {
    if (percentage < 50) return '#67C23A';
    if (percentage < 80) return '#E6A23C';
    return '#ff3434';
  }
  else {
    if (percentage >= 85) return '#67C23A';
    if (percentage >= 60) return '#E6A23C';
    return '#ff3434';
  }
};

// 概览数据相关
const emergencyGlobalOverview = ref([]);
const currentOverview = ref(null);
const overviewTimeRange = ref('today');
const overviewDataType = ref('');
const eventStats = ref({});
const resourceStats = ref({});
const riskRegionStats = ref({});

// 刷新概览数据
const refreshOverviewData = async () => {
  try {
    const overviewData = await fetchEmergencyGlobalOverview();
    emergencyGlobalOverview.value = overviewData;
    if (overviewData) {
      eventStats.value = {
        total_evt_count: overviewData.totalEvtCount || 0,
        handl_count: overviewData.handlCount || 0,
        completed_count: overviewData.completedCount || 0
      };
      resourceStats.value = { total_res_count: overviewData.totalResCount || 0 };
      riskRegionStats.value = {
        high_risk_count: overviewData.highRiskCount || 0,
        region_cover_count: overviewData.regionCoverCount || 0
      };
    }
    currentOverview.value = overviewData;
    ElMessage.success('概览数据已刷新');
  } catch (error) {
    ElMessage.error('刷新失败: ' + (error.message || '未知错误'));
  }
};

// 路由跳转方法
const jumpToEventDisposalTracking = () => router.push('/overview/eventdisposaltracking/dpzl');
const jumpToEmergencyDisposalProgress = () => router.push('/overview/emergencydisposalprogress/dpzl');
const jumpToResourceDistribution = () => router.push('/overview/resourcedistribution/dpzl');
const jumpToAccidentHazardWarning = () => router.push('/overview/accidenthazardwarning/dpzl');

// 指标相关 - 核心：适配接口返回的对象结构，转为数组（包含所有字段）
const coreIndicators = ref([]); // 初始化就是空数组

// 数据转换：把接口返回的对象转为前端渲染的数组格式（包含所有接口字段）
const transformCoreIndicators = (data) => {
  if (!data || typeof data !== 'object') return [];

  const indicatorMap = [
    {
      key: 'emergEventCompleteRate',
      name: '应急事件完成率',
      unit: '%',
      getValue: (d) => d.emergEventCompleteRate?.rate || 0,
      getWarningThreshold: (d) => d.emergEventCompleteRate?.warningThreshold || 0,
      getUpdateTime: (d) => formatDate(d.emergEventCompleteRate?.updateTime) || '',
      getExtData: (d) => ({
        completedCount: d.emergEventCompleteRate?.completedCount || 0,
        totalCount: d.emergEventCompleteRate?.totalCount || 0
      })
    },
    {
      key: 'earlyWarnAccRate',
      name: '预警准确率',
      unit: '%',
      getValue: (d) => d.earlyWarnAccRate?.rate || 0,
      getWarningThreshold: (d) => d.earlyWarnAccRate?.warningThreshold || 0,
      getUpdateTime: (d) => formatDate(d.earlyWarnAccRate?.updateTime) || '',
      getExtData: (d) => ({
        completedCount: d.earlyWarnAccRate?.completedCount || 0,
        totalCount: d.earlyWarnAccRate?.totalCount || 0
      })
    },
    {
      key: 'resUseRate',
      name: '资源调用率',
      unit: '%',
      getValue: (d) => d.resUseRate?.rate || 0,
      getWarningThreshold: (d) => d.resUseRate?.warningThreshold || 0,
      getUpdateTime: (d) => formatDate(d.resUseRate?.updateTime) || '',
      getExtData: (d) => ({
        usedCount: d.resUseRate?.usedCount || 0,
        totalCount: d.resUseRate?.totalCount || 0
      })
    },
    {
      key: 'riskRectifyRate',
      name: '风险整改率',
      unit: '%',
      getValue: (d) => d.riskRectifyRate?.rate || 0,
      getWarningThreshold: (d) => d.riskRectifyRate?.warningThreshold || 0,
      getUpdateTime: (d) => formatDate(d.riskRectifyRate?.updateTime) || '',
      getExtData: (d) => ({
        rectifiedCount: d.riskRectifyRate?.rectifiedCount || 0,
        totalCount: d.riskRectifyRate?.totalCount || 0
      })
    },
    {
      key: 'avgHandleEndure',
      name: '平均处置时长',
      unit: '分钟',
      getValue: (d) => d.avgHandleEndure?.avgHandleDuration || 0,
      getWarningThreshold: (d) => d.avgHandleEndure?.warningThreshold || 40,
      getUpdateTime: (d) => formatDate(d.avgHandleEndure?.updateTime) || '',
      getExtData: (d) => ({})
    }
  ];

  return indicatorMap.map(item => ({
    key: item.key,
    name: item.name,
    unit: item.unit,
    value: item.getValue(data),
    warningThreshold: item.getWarningThreshold(data),
    updateTime: item.getUpdateTime(data),
    extData: item.getExtData(data)
  }));
};

// 拆分左右指标
const leftIndicators = computed(() => {
  return coreIndicators.value.slice(0, 2);
});
const rightIndicators = computed(() => {
  return coreIndicators.value.slice(2, 5);
});

// 指标详情相关
const indicatorDetailVisible = ref(false);
const currentIndicator = ref(null);

// 显示指标详情
const showIndicatorDetail = (indicator) => {
  currentIndicator.value = { ...indicator };
  indicatorDetailVisible.value = true;
};

// 刷新核心指标 - 适配接口返回的对象结构
const refreshCoreIndicators = async () => {
  try {
    const data = await fetchEmergencyCoreIndicators();
    // 转换数据格式（包含所有字段）
    const transformedData = transformCoreIndicators(data);
    coreIndicators.value = transformedData;
    ElMessage.success('核心指标已刷新');
  } catch (error) {
    ElMessage.error('刷新失败: ' + (error.message || '未知错误'));
    coreIndicators.value = []; // 出错时重置为空数组
  }
};

// 风险相关
const risks = ref([]);
const riskTypeFilter = ref('');
const riskLevelFilter = ref('');
const visibleLayers = ref(['危化品', '地质灾害', '消防', '交通', '电力']);
const layerControlVisible = ref(false);
const riskDetailVisible = ref(false);
const currentRisk = ref(null);
const riskDisposalContent = ref('');

// 显示风险点详情
const showRiskDetail = (risk) => {
  currentRisk.value = { ...risk };
  riskDisposalContent.value = '';
  riskDetailVisible.value = true;
};

// 应用图层控制
const applyLayerControl = () => {
  layerControlVisible.value = false;
  ElMessage.success('图层设置已应用');
};

// 全屏功能
const togglePanelFullscreen = (panelRefName) => {
  if (!screenFull.isEnabled) {
    ElMessage.warning('您的浏览器不支持全屏功能');
    return;
  }
  const panel = instance?.refs[panelRefName];
  if (!panel) {
    ElMessage.error('未找到面板元素');
    return;
  }
  screenFull.isFullscreen && document.fullscreenElement === panel ? screenFull.exit() : screenFull.request(panel);
};

// 资源相关
const resources = ref([]);
const resourceTypeFilter = ref('');
const resourceStatusFilter = ref('');
const filteredResources = computed(() => {
  const validResources = Array.isArray(resources.value) ? resources.value : [];
  return validResources.filter(resource => {
    const matchesType = !resourceTypeFilter.value || resource.resType === resourceTypeFilter.value;
    const matchesStatus = !resourceStatusFilter.value || resource.stockStatus === resourceStatusFilter.value;
    return matchesType && matchesStatus;
  });
});

// 资源详情
const resourceDetailVisible = ref(false);
const currentResource = ref(null);
const allocationQuantity = ref(1);
const allocationReason = ref('');
const allocationReceiver = ref('');

// 显示资源详情
const showResourceDetail = (resource) => {
  currentResource.value = { ...resource };
  allocationQuantity.value = 1;
  allocationReason.value = '';
  allocationReceiver.value = '';
  resourceDetailVisible.value = true;
};

// 提交资源调拨申请
const handleSubmitResourceAllocation = async () => {
  if (!currentResource.value || !allocationQuantity.value || !allocationReason.value || !allocationReceiver.value) return;
  try {
    await submitResourceAllocation({
      resource_id: currentResource.value.resource_id,
      quantity: allocationQuantity.value,
      reason: allocationReason.value,
      receiver: allocationReceiver.value,
      apply_user: 'admin',
      apply_time: formatTime(new Date())
    });
    ElMessage.success('资源调拨申请已提交');
    resourceDetailVisible.value = false;
  } catch (error) {
    ElMessage.error('提交失败: ' + (error.message || '未知错误'));
  }
};

// 预警相关
const warningScrollConfig = ref({
  header: ['区域名称', '预警类型', '预警描述', '发现时间', '状态'],
  data: [],
  rowNum: 5,
  align: ['center', 'center', 'center', 'center', 'center'],
  columnWidth: [100, 100, 220, 180, 80]
});

const transformWarningScrollData = (warningData) => {
  if (!Array.isArray(warningData)) return [];
  return warningData.map(item => [
    item.regionName || '-',        // 区域名称
    item.type || '-',              // 预警类型
    item.description || '-',       // 预警描述
    item.discoveryTime || '-',     // 发现时间
    item.status || '-'             // 状态
  ]);
};

const newWarningVisible = ref(false);
const newWarning = ref(null);
const warningTypes = ref([]);

// 模拟新预警
const simulateNewWarning = () => {
  const validWarnings = Array.isArray(warningTypes.value) ? warningTypes.value : [];
  if (validWarnings.length === 0) return;
  const randomIndex = Math.floor(Math.random() * validWarnings.length);
  newWarning.value = { ...validWarnings[randomIndex], time: formatTime(new Date()) };
  newWarningVisible.value = true;
};

// 忽略预警
const ignoreWarning = () => newWarningVisible.value = false;

// 处理新预警
const handleNewWarning = () => {
  if (!newWarning.value) {
    newWarningVisible.value = false;
    return;
  }
  if (newWarning.value.title.includes('风险等级')) {
    const validRisks = Array.isArray(risks.value) ? risks.value : [];
    const risk = validRisks.find(r => r.risk_point_name?.toLowerCase().includes('东方化工厂仓库'.toLowerCase()));
    risk ? showRiskDetail(risk) : ElMessage.warning('正在找对应的风险点数据');
  } else if (newWarning.value.title.includes('资源')) {
    const validResources = Array.isArray(resources.value) ? resources.value : [];
    const resource = validResources.find(r => r.resName?.toLowerCase().includes('防汛沙袋'.toLowerCase()));
    resource ? showResourceDetail(resource) : ElMessage.warning('正在找对应的资源数据');
  } else if (newWarning.value.title.includes('指标')) {
    const indicator = coreIndicators.value.find(i => i.name?.toLowerCase().includes('平均处置时长'.toLowerCase()));
    indicator ? showIndicatorDetail(indicator) : ElMessage.warning('正在找对应的指标数据');
  }
  newWarningVisible.value = false;
};

// 报告相关
const reportVisible = ref(false);
const currentReport = ref(null);
const exportReport = () => ElMessage.success('报告导出成功');

// 初始化
onMounted(() => {
  // 初始化时间
  currentTime.value = formatTime(new Date());
  const timer = setInterval(() => currentTime.value = formatTime(new Date()), 1000);

  // 初始化数据
  const initData = async () => {
    try {
      const [
        overviewData,
        indicators,
        riskGeometriesData,
        resourceData,
        warningScrollData,
        warnTypes
      ] = await Promise.all([
        fetchEmergencyGlobalOverview(),
        fetchEmergencyCoreIndicators(),
        fetchEmergencyRiskGeometries(),
        fetchEmergencyResources(),
        fetchWarningScrollData(),
        fetchWarningTypes()
      ]);

      // 处理概览数据
      emergencyGlobalOverview.value = overviewData;
      if (overviewData) {
        currentOverview.value = overviewData;
        eventStats.value = {
          total_evt_count: overviewData.totalEvtCount || 0,
          handl_count: overviewData.handlCount || 0,
          completed_count: overviewData.completedCount || 0
        };
        resourceStats.value = { total_res_count: overviewData.totalResCount || 0 };
        riskRegionStats.value = {
          high_risk_count: overviewData.highRiskCount || 0,
          region_cover_count: overviewData.regionCoverCount || 0
        };
      }

      // 转换核心指标数据（包含所有接口字段）
      coreIndicators.value = transformCoreIndicators(indicators);

      // 处理其他数据
      geometriesArray.value = Array.isArray(riskGeometriesData) ? riskGeometriesData : [];
      resources.value = Array.isArray(resourceData) ? resourceData : [];
      warningScrollConfig.value.data = transformWarningScrollData(warningScrollData);
      warningTypes.value = Array.isArray(warnTypes) ? warnTypes : [];
    } catch (error) {
      console.error('初始化数据失败:', error);
      ElMessage.error('数据加载失败，请刷新页面重试');
      // 出错时重置所有数组类型数据
      coreIndicators.value = [];
      resources.value = [];
      warningTypes.value = [];
    }
  };

  initData();

  // 定时模拟新预警
  const warningTimer = setInterval(() => {
    if (Math.random() < 0.3) simulateNewWarning();
  }, 30000);

  // 清理定时器
  onUnmounted(() => {
    clearInterval(timer);
    clearInterval(warningTimer);
  });
});
</script>

<style lang="scss" scoped>
@import url('./common-styles.scss');

// 最外层容器
.page-container {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  background: url("@/assets/chart/images/bg.jpg");
  background-size: 100% 100%;
  color: #fff;
  padding: 0 1vw;
  display: flex;
  box-sizing: border-box;
}

// 页面主体盒子
.mainbox {
  display: flex;
  margin: 0 auto;
  padding: 0.6vw 0;
  height: 88vh;
  box-sizing: border-box;
  gap: 0.6vw;
  width: 100%;
}

// 公共面板样式
.panel {
  position: relative;
  height: 100%;
  border: 0.2vh solid rgba(25, 186, 139, 0.17);
  background: url("@/assets/chart/images/line(1).png") rgba(255, 255, 255, .04);
  box-sizing: border-box;
}

.left {
  flex: 3;
}

.left_top {
  height: 58%;
  margin-bottom: 2%;
}

.left_bottom {
  height: 40%;
}

.middle {
  flex: 4;
}

.middle_top {
  height: 60%;
  margin-bottom: 2%;
}

.middle_bottom {
  height: 38%;
}

.right {
  flex: 3;
}

.right_top {
  height: 58%;
  margin-bottom: 2%;
}

.right_bottom {
  height: 40%;
  padding: 0 0.6vw;
}
</style>
