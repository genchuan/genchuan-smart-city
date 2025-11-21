<template>
  <div class="page-container">
    <div class="mainbox">
      <div class="left" style="min-width: 2vw;">
        <div class="left_top">
          <!-- 应急核心指标 -->
          <div class="panel core-indicators-panel" ref="coreIndicatorsPanelLeft">
            <div class="panel-body">
              <div class="indicator-cards">
                <div
                  v-for="indicator in leftIndicators"
                  :key="indicator.index_id"
                  :class="['indicator-card1', indicator.status === '1' ? 'warning' : 'normal']"
                  @click="showIndicatorDetail(indicator)"
                >
                  <div class="indicator-title">{{ indicator.index_name }}</div>
                  <div class="indicator-value">
                    {{ indicator.current_value }}
                    <!-- 动态显示单位：平均处置时长显示“分钟”，其他显示“%” -->
                    <span class="indicator-unit">{{ indicator.index_code === 'avg_handle_endure' ? '分钟' : '%' }}</span>
                  </div>
                  <div class="indicator-threshold">
                    目标: {{ indicator.target_value }}{{ indicator.index_code === 'avg_handle_endure' ? '分钟' : '%' }}
                    预警: {{ indicator.warning_threshold }}{{ indicator.index_code === 'avg_handle_endure' ? '分钟' : '%' }}
                  </div>
                  <div class="indicator-time">更新于 {{ indicator.update_time }}</div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="left_bottom">
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
                <el-button size="small" @click="refreshOverviewData">
                  刷新
                </el-button>
                <el-button size="small" type="primary" @click="exportOverviewData">
                  导出数据
                </el-button>
              </div>
            </div>
            <div class="panel-body">
              <div class="overview-stats">
                <!-- 1. 应急事件总数 -->
                <div class="overview-stat-item">
                  <div class="stat-title">应急事件总数</div>
                  <div class="stat-value">{{ eventStats?.total_evt_count || 0 }} 起</div>
                  <div class="stat-rate">
                    <span>办结率 {{ eventStats?.total_evt_count ? ((eventStats?.completed_count / eventStats?.total_evt_count) * 100).toFixed(1) : 0 }}%</span>
                  </div>
                </div>
                <!-- 2. 在处置事件数 -->
                <div class="overview-stat-item">
                  <div class="stat-title">在处置事件数</div>
                  <div class="stat-value">{{ eventStats?.handl_count || 0 }} 起</div>
                  <div class="stat-rate" :class="getHandlingRateClass(eventStats?.handl_count || 0, eventStats?.total_evt_count || 1)">
                    占比 {{ eventStats?.total_evt_count ? ((eventStats?.handl_count / eventStats?.total_evt_count) * 100).toFixed(1) : 0 }}%
                  </div>
                </div>
                <!-- 3. 已办结事件数 -->
                <div class="overview-stat-item">
                  <div class="stat-title">已办结事件数</div>
                  <div class="stat-value">{{ eventStats?.completed_count || 0 }} 起</div>
                  <div class="stat-rate" :class="getCompletedRateClass(eventStats?.completed_count || 0, eventStats?.total_evt_count || 1)">
                    {{ eventStats?.total_evt_count ? ((eventStats?.completed_count / eventStats?.total_evt_count) * 100).toFixed(1) : 0 }}% 完成
                  </div>
                </div>
                <!-- 4. 应急资源总数 -->
                <div class="overview-stat-item">
                  <div class="stat-title">应急资源总数</div>
                  <div class="stat-value">{{ resourceStats?.total_res_count || 0 }} 项</div>
                  <div class="stat-rate">
                    <span>资源覆盖率 92.3%</span>
                  </div>
                </div>
                <!-- 5. 高风险隐患数 -->
                <div class="overview-stat-item">
                  <div class="stat-title">高风险隐患数</div>
                  <div class="stat-value">{{ riskRegionStats?.high_risk_count || 0 }} 个</div>
                  <div class="stat-rate" :class="getHighRiskClass(riskRegionStats?.high_risk_count || 0)">
                    {{ riskRegionStats?.high_risk_count > 5 ? '需重点关注' : '可控范围' }}
                  </div>
                </div>
                <!-- 6. 行政区划覆盖数 -->
                <div class="overview-stat-item">
                  <div class="stat-title">行政区划覆盖数</div>
                  <div class="stat-value">{{ riskRegionStats?.region_cover_count || 0 }} 个</div>
                  <div class="stat-rate" :class="getRegionCoverClass(riskRegionStats?.region_cover_count || 0)">
                    覆盖率 {{ riskRegionStats?.region_cover_count ? ((riskRegionStats?.region_cover_count / 28) * 100).toFixed(1) : 0 }}%
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        当前时间：{{currentTime}}
      </div>
      <div class="middle" style="min-width: 2vw;">
        <div class="panel middle_top" ref="map">
          <div class="header-actions">
            <p>应急风险分布地图</p>
            <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('map')">
              <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
            </button>
          </div>
          <map-common idName="chinaEcharts" :geometriesArray="geometriesArray"/>
          <div class="panel-footer"></div>
        </div>
        <!-- 预警信息 -->
        <div class="panel middle_bottom" ref="warningsPanel">
          <div class="panel-header">
            <h2>近期预警信息</h2>
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
        <div class="right_top">
          <!-- 应急核心指标 -->
          <div class="panel core-indicators-panel" ref="coreIndicatorsPanelRight">
            <div class="panel-body">
              <div class="indicator-cards">
                <div
                  v-for="indicator in rightIndicators"
                  :key="indicator.index_id"
                  :class="['indicator-card2', indicator.status === '1' ? 'warning' : 'normal']"
                  @click="showIndicatorDetail(indicator)"
                >
                  <div class="indicator-title">{{ indicator.index_name }}</div>
                  <div class="indicator-value">
                    {{ indicator.current_value }}
                    <!-- 动态显示单位：平均处置时长显示“分钟”，其他显示“%” -->
                    <span class="indicator-unit">{{ indicator.index_code === 'avg_handle_endure' ? '分钟' : '%' }}</span>
                  </div>
                  <div class="indicator-threshold">
                    目标: {{ indicator.target_value }}{{ indicator.index_code === 'avg_handle_endure' ? '分钟' : '%' }}
                    预警: {{ indicator.warning_threshold }}{{ indicator.index_code === 'avg_handle_endure' ? '分钟' : '%' }}
                  </div>
                  <div class="indicator-time">更新于 {{ indicator.update_time }}</div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="panel right_bottom" ref="list">
          <div class="panel-header">
            <h2>应急资源总览</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('list')">
                <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
              </button>
            </div>
          </div>
          <div class="resources-list">
            <!-- 应急资源总览列表 -->
            <el-table
              :data="resources"
              border
              size="small"
              @row-click="showResourceDetail"
              height="calc(100% - 30px)"
              width="100%"
            >
            <el-table-column
              prop="res_id"
              label="资源ID"
            />
            <el-table-column
              prop="res_name"
              label="资源名称"
            />
            <el-table-column
              prop="res_type"
              label="资源类型"
            />
            <el-table-column
              prop="stock_qty"
              label="库存数量"
            />
            <el-table-column
              prop="available_qty"
              label="可用数量"
            />
            <el-table-column
              label="库存状态"
            >
              <template #default="scope">
                <el-tag :type="getStockStatusType(scope.row.available_qty, scope.row.stock_qty)">
                  {{ getStockStatus(scope.row.available_qty, scope.row.stock_qty) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column
              prop="storage_loc"
              label="存放位置"
            />
            <el-table-column
              prop="mngr_name"
              label="负责人"
            />
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
      </div>
    </div>

    <!-- 指标详情弹窗 -->
    <el-dialog
      v-model="indicatorDetailVisible"
      :title="currentIndicator?.index_name || '指标详情'"
      width="600px"
    >
      <div class="indicator-detail">
        <div class="detail-section">
          <h3>指标信息</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="指标编码">{{ currentIndicator?.index_code }}</el-descriptions-item>
            <el-descriptions-item label="统计周期">{{ currentIndicator?.statistic_cycle }}度</el-descriptions-item>
            <el-descriptions-item label="当前值">
              <span class="current-value">
                {{ currentIndicator?.current_value }}
                {{ currentIndicator?.index_code === 'avg_handle_endure' ? '分钟' : '%' }}
              </span>
            </el-descriptions-item>
            <el-form-item label="目标值({{ currentIndicator?.index_code === 'avg_handle_endure' ? '分钟' : '%' }})" required>
              <el-input
                v-model.number="indicatorConfigForm.target_value"
              />
            </el-form-item>
            <el-form-item label="预警阈值({{ currentIndicator?.index_code === 'avg_handle_endure' ? '分钟' : '%' }})" required>
              <el-input
                v-model.number="indicatorConfigForm.warning_threshold"
              />
            </el-form-item>
            <el-descriptions-item label="当前状态">
              <el-tag :type="currentIndicator?.status === '1' ? 'danger' : 'success'">
                {{ currentIndicator?.status === '1' ? '预警' : '正常' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="改进工单" v-if="currentIndicator?.improve_order_id">
              <a href="javascript:void(0)">{{ currentIndicator?.improve_order_id }}</a>
            </el-descriptions-item>
            <el-descriptions-item label="更新时间">{{ currentIndicator?.update_time }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>近7天趋势</h3>
          <div class="chart-container">
            <ChartLine
              :data="indicatorTrendData"
              :yAxisName="getYAxisName(currentIndicator)"
            />
          </div>
        </div>
      </div>
      <template #footer>
        <el-button @click="indicatorDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="showIndicatorConfig"
        >
          修改阈值
        </el-button>
      </template>
    </el-dialog>

    <!-- 指标配置弹窗 -->
    <el-dialog
      v-model="indicatorConfigVisible"
      title="指标阈值配置"
      width="500px"
    >
      <el-form :model="indicatorConfigForm" ref="indicatorConfigRef" label-width="120px">
        <el-form-item label="指标名称" disabled>
          <el-input v-model="indicatorConfigForm.index_name" />
        </el-form-item>
        <el-form-item label="统计周期" disabled>
          <el-input v-model="indicatorConfigForm.statistic_cycle" />
        </el-form-item>
        <el-form-item label="目标值(%)" required>
          <el-input
            v-model.number="indicatorConfigForm.target_value"
          />
        </el-form-item>
        <el-form-item label="预警阈值(%)" required>
          <el-input
            v-model.number="indicatorConfigForm.warning_threshold"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="indicatorConfigVisible = false">取消</el-button>
        <el-button type="primary" @click="saveIndicatorConfig">保存配置</el-button>
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
            <el-descriptions-item label="资源ID">{{ currentResource?.res_id }}</el-descriptions-item>  <!-- 字段更新 -->
            <el-descriptions-item label="资源类型">{{ currentResource?.res_type }}</el-descriptions-item>  <!-- 字段更新 -->
            <el-descriptions-item label="规格型号">{{ currentResource?.spec_model }}</el-descriptions-item>
            <el-descriptions-item label="存放位置">{{ currentResource?.storage_loc }}</el-descriptions-item>  <!-- 字段更新 -->
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>库存信息</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="库存总量">{{ currentResource?.stock_qty }}</el-descriptions-item>  <!-- 字段更新 -->
            <el-descriptions-item label="可用数量">{{ currentResource?.available_qty }}</el-descriptions-item>  <!-- 字段更新 -->
            <el-descriptions-item label="当前状态">
              <el-tag :type="getResourceStatusTagType(currentResource?.status)">
                {{ getResourceStatusText(currentResource?.status) }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="管理责任人">{{ currentResource?.mngr_name }}</el-descriptions-item>  <!-- 字段更新 -->
            <el-descriptions-item label="最近调拨记录" v-if="currentResource?.allocation_id">
              <a href="javascript:void(0)">{{ currentResource?.allocation_id }}</a>
            </el-descriptions-item>
            <el-descriptions-item label="数据更新时间">{{ currentResource?.update_time }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>资源调拨</h3>
          <el-form>
            <el-form-item label="调拨数量" required>
              <el-input type="number" v-model="allocationQuantity" min="1" :max="currentResource?.available_qty" />
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
          @click="handleSubmitResourceAllocation "
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
import { ref, computed, onMounted, onUnmounted, getCurrentInstance } from 'vue';
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
  ElCheckboxGroup
} from 'element-plus';
import screenFull from 'screenfull';
import { FullScreen, Warning } from "@element-plus/icons-vue";
import MapCommon from './MapCommon.vue';
import ChartLine from './ChartLine.vue';
import ChartPie from './ChartPie.vue';

// 导入API方法
import {
  fetchEmergencyGlobalOverview,
  exportEmergencyGlobalOverview,
  fetchEmergencyRiskGeometries,
  viewEmergencyAnalysisReport,
  fetchEmergencyCoreIndicators,
  updateIndicatorThreshold,
  handleRiskWarning,
  fetchEmergencyResources,
  submitResourceAllocation,
  fetchWarningScrollData,
  fetchWarningTypes,
  fetchEmergencyHeatmapData  // 新增热力图接口
} from '@/api/overview/emergencysafety/GlobalSituationOverview.js';

const geometriesArray = ref([]);

// 头部区域相关数据和方法
const currentTime = ref('');

// 获取当前组件实例，用于访问ref
const instance = getCurrentInstance();

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

// 应急全域数据概览相关数据
const emergencyGlobalOverview = ref([]);
const currentOverview = ref(null);
const overviewTimeRange = ref('today');
const overviewDataType = ref('');
const eventStats = ref({});
const resourceStats = ref({});
const riskRegionStats = ref({});

const riskStats = ref(0);
const highRiskCount = ref(0);
const heatmapData = ref([]);  // 热力图数据（从API获取）

// 刷新概览数据（包含热力图数据）
const refreshOverviewData = async () => {
  try {
    // 并行获取概览数据和热力图数据
    const [overviewData, heatmapResult] = await Promise.all([
      fetchEmergencyGlobalOverview(),
      fetchEmergencyHeatmapData()
    ]);

    emergencyGlobalOverview.value = overviewData;
    heatmapData.value = heatmapResult;

    if (overviewData) {
      eventStats.value = {
        total_evt_count: overviewData.totalEvtCount,
        handl_count: overviewData.handlCount,
        completed_count: overviewData.completedCount
      };
      resourceStats.value = {
        total_res_count: overviewData.totalResCount
      };
      riskRegionStats.value = {
        high_risk_count: overviewData.highRiskCount,
        region_cover_count: overviewData.regionCoverCount
      };
    }
    // 默认显示最新的一条数据
    if (overviewData.length > 0) {
      currentOverview.value = overviewData[overviewData.length - 1];
    }

    ElMessage.success('概览数据已刷新');
  } catch (error) {
    ElMessage.error('刷新失败: ' + (error.message || '未知错误'));
  }
};

const getHandlingRateClass = (handlingCount, totalCount) => {
  const rate = (handlingCount / totalCount) * 100;
  return rate > 30 ? 'danger' : rate > 10 ? 'warning' : 'normal';
};

// 已办结事件数样式
const getCompletedRateClass = (completedCount, totalCount) => {
  const rate = (completedCount / totalCount) * 100;
  return rate < 70 ? 'danger' : rate < 85 ? 'warning' : 'normal';
};

// 高风险隐患数样式
const getHighRiskClass = (highRiskCount) => {
  return highRiskCount > 5 ? 'danger' : highRiskCount > 2 ? 'warning' : 'normal';
};

// 行政区划覆盖数样式
const getRegionCoverClass = (coverCount) => {
  return coverCount < 15 ? 'danger' : coverCount < 20 ? 'warning' : 'normal';
};

// 导出概览数据
const exportOverviewData = async () => {
  try {
    const result = await exportEmergencyGlobalOverview({
      timeRange: overviewTimeRange.value,
      dataType: overviewDataType.value
    });
    ElMessage.success(result.message);
  } catch (error) {
    ElMessage.error('导出失败: ' + (error.message || '未知错误'));
  }
};

// 查看分析报告
const reportVisible = ref(false);
const currentReport = ref(null);

const viewAnalysisReport = async (reportId) => {
  try {
    const report = await viewEmergencyAnalysisReport(reportId);
    currentReport.value = report;
    reportVisible.value = true;
  } catch (error) {
    ElMessage.error('查看报告失败: ' + (error.message || '未知错误'));
  }
};

// 导出报告
const exportReport = () => {
  ElMessage.success('报告导出成功');
};

// 启动应急响应
const handleEmergencyResponse = () => {
  ElMessage.info('正在启动应急响应程序...');
  // 实际应用中应打开应急响应流程界面
};

// 钻取至详情数据
const drillDownToDetail = () => {
  ElMessage.info('正在钻取至详情数据...');
};

// 计算库存状态（充足/紧张/短缺）
const getStockStatus = (availableQty, stockQty) => {
  if (stockQty === 0) return '无库存';
  const ratio = availableQty / stockQty;
  if (ratio > 0.7) return '充足';
  if (ratio > 0.3) return '紧张';
  return '短缺';
};

// 库存状态标签样式（对应element-plus的tag类型）
const getStockStatusType = (availableQty, stockQty) => {
  const status = getStockStatus(availableQty, stockQty);
  switch (status) {
    case '充足': return 'success';
    case '紧张': return 'warning';
    case '短缺': return 'danger';
    default: return 'info';
  }
};

// 获取达标率样式类
const getRateClass = (rate) => {
  if (rate < 70) return 'danger';
  if (rate < 85) return 'warning';
  return 'normal';
};

// 获取响应时间样式类
const getResponseTimeClass = (minutes) => {
  if (minutes > 30) return 'danger';
  if (minutes > 15) return 'warning';
  return 'normal';
};

// 获取高风险点样式类
const getHighRiskRateClass = () => {
  if (highRiskCount.value > 5) return 'danger';
  if (highRiskCount.value > 2) return 'warning';
  return 'normal';
};

// 应急核心指标相关数据
const indicatorStatus = ref('');
const indicatorCycle = ref('');

// 筛选后的指标
const filteredIndicators = computed(() => {
  return coreIndicators.value.filter(indicator => {
    const matchesStatus = !indicatorStatus.value || indicator.status === indicatorStatus.value;
    const matchesCycle = !indicatorCycle.value || indicator.statistic_cycle === indicatorCycle.value;
    return matchesStatus && matchesCycle;
  });
});

// 指标详情弹窗
const indicatorDetailVisible = ref(false);
const currentIndicator = ref(null);
const indicatorTrendData = ref({
  xAxis: [],
  series: []
});

// 显示指标详情
const showIndicatorDetail = (indicator) => {
  currentIndicator.value = {...indicator};
  // 解析趋势数据
  if (indicator.trend_7d) {
    try {
      const trendData = JSON.parse(indicator.trend_7d);
      indicatorTrendData.value = {
        xAxis: trendData.xAxis,
        series: [{
          name: indicator.index_name,
          data: trendData.series
        }]
      };
    } catch (e) {
      console.error('解析趋势数据失败:', e);
    }
  }
  indicatorDetailVisible.value = true;
};

// 核心指标数据
const coreIndicators = ref([]);

const leftIndicators = computed(() => {
  return coreIndicators.value.slice(0, 2);
});

const rightIndicators = computed(() => {
  return coreIndicators.value.slice(2, 5);
});

// 动态设置趋势图Y轴名称
const getYAxisName = (indicator) => {
  if (!indicator) return '';
  return indicator.index_code === 'avg_handle_endure' ? '分钟' : '%';
};

// 指标配置表单初始化（补充单位适配）
const showIndicatorConfig = () => {
  if (!currentIndicator.value) return;

  indicatorConfigForm.value = {
    index_name: currentIndicator.value.index_name,
    statistic_cycle: currentIndicator.value.statistic_cycle,
    target_value: currentIndicator.value.target_value,
    warning_threshold: currentIndicator.value.warning_threshold
  };

  indicatorConfigVisible.value = true;
};

// 刷新核心指标
const refreshCoreIndicators = async () => {
  try {
    const data = await fetchEmergencyCoreIndicators();
    coreIndicators.value = data;
    ElMessage.success('核心指标已刷新');
  } catch (error) {
    ElMessage.error('刷新失败: ' + (error.message || '未知错误'));
  }
};


// 指标配置弹窗
const indicatorConfigVisible = ref(false);
const indicatorConfigForm = ref({
  index_name: '',
  statistic_cycle: '',
  target_value: '',
  warning_threshold: ''
});
const indicatorConfigRef = ref(null);

// 保存指标配置
const saveIndicatorConfig = async () => {
  if (!currentIndicator.value) return;

  try {
    await updateIndicatorThreshold({
      index_id: currentIndicator.value.index_id,
      target_value: indicatorConfigForm.value.target_value,
      warning_threshold: indicatorConfigForm.value.warning_threshold
    });

    // 更新本地数据
    const index = coreIndicators.value.findIndex(i => i.index_id === currentIndicator.value.index_id);
    if (index !== -1) {
      coreIndicators.value[index].target_value = indicatorConfigForm.value.target_value;
      coreIndicators.value[index].warning_threshold = indicatorConfigForm.value.warning_threshold;
    }

    // 更新当前指标
    currentIndicator.value.target_value = indicatorConfigForm.value.target_value;
    currentIndicator.value.warning_threshold = indicatorConfigForm.value.warning_threshold;

    ElMessage.success('指标配置已保存');
    indicatorConfigVisible.value = false;
  } catch (error) {
    ElMessage.error('保存失败: ' + (error.message || '未知错误'));
  }
};

// 风险分布视图相关数据
const risks = ref([]);
const riskTypeFilter = ref('');
const riskLevelFilter = ref('');
const visibleLayers = ref(['危化品', '地质灾害', '消防', '交通', '电力']);
const layerControlVisible = ref(false);

// 筛选后的风险点
const filteredRisks = computed(() => {
  return risks.value.filter(risk => {
    const matchesType = !riskTypeFilter.value || risk.risk_type === riskTypeFilter.value;
    const matchesLevel = !riskLevelFilter.value || risk.risk_level.toString() === riskLevelFilter.value;
    const matchesLayer = visibleLayers.value.includes(risk.risk_type);
    return matchesType && matchesLevel && matchesLayer;
  });
});

// 风险点详情弹窗
const riskDetailVisible = ref(false);
const currentRisk = ref(null);
const riskDisposalContent = ref('');

// 显示风险点详情
const showRiskDetail = (risk) => {
  currentRisk.value = {...risk};
  riskDisposalContent.value = '';
  riskDetailVisible.value = true;
};

// 应用图层控制
const applyLayerControl = () => {
  layerControlVisible.value = false;
  ElMessage.success('图层设置已应用');
};

// 提交风险处置措施
const submitRiskDisposal = async () => {
  if (!currentRisk.value || !riskDisposalContent.value) return;

  try {
    await handleRiskWarning({
      risk_point_id: currentRisk.value.risk_point_id,
      disposal_content: riskDisposalContent.value,
      disposal_user: 'admin'
    });

    ElMessage.success('风险处置措施已提交');
    riskDetailVisible.value = false;
  } catch (error) {
    ElMessage.error('提交失败: ' + (error.message || '未知错误'));
  }
};

// 获取风险等级标签类型
const getRiskLevelTagType = (level) => {
  switch (level) {
    case 5: return 'danger';
    case 4: return 'warning';
    case 3: return 'info';
    default: return 'success';
  }
};

// 全屏相关功能
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

// 应急资源总览相关数据
const resources = ref([]);
const resourceTypeFilter = ref('');
const resourceStatusFilter = ref('');

// 筛选后的资源
const filteredResources = computed(() => {
  return resources.value.filter(resource => {
    const matchesType = !resourceTypeFilter.value || resource.resource_type === resourceTypeFilter.value;
    const matchesStatus = !resourceStatusFilter.value || resource.status === resourceStatusFilter.value;
    return matchesType && matchesStatus;
  });
});

// 资源统计数据
const equipmentCount = computed(() => resources.value.filter(r => r.resource_type === '设备').length);
const equipmentAvailableCount = computed(() => {
  const eq = resources.value.filter(r => r.resource_type === '设备');
  return eq.reduce((sum, item) => sum + item.available_quantity, 0);
});

const materialCount = computed(() => resources.value.filter(r => r.resource_type === '物资').length);
const materialAvailableCount = computed(() => {
  const mt = resources.value.filter(r => r.resource_type === '物资');
  return mt.reduce((sum, item) => sum + item.available_quantity, 0);
});

const teamCount = computed(() => resources.value.filter(r => r.resource_type === '队伍').length);
const teamAvailableCount = computed(() => {
  const tm = resources.value.filter(r => r.resource_type === '队伍');
  return tm.reduce((sum, item) => sum + item.available_quantity, 0);
});

const placeCount = computed(() => resources.value.filter(r => r.resource_type === '场所').length);
const placeAvailableCount = computed(() => {
  const pc = resources.value.filter(r => r.resource_type === '场所');
  return pc.reduce((sum, item) => sum + item.available_quantity, 0);
});

// 资源详情弹窗
const resourceDetailVisible = ref(false);
const currentResource = ref(null);
const allocationQuantity = ref(1);
const allocationReason = ref('');
const allocationReceiver = ref('');

// 显示资源详情
const showResourceDetail = (resource) => {
  currentResource.value = {...resource};
  allocationQuantity.value = 1;
  allocationReason.value = '';
  allocationReceiver.value = '';
  resourceDetailVisible.value = true;
};

// 提交资源调拨申请
const handleSubmitResourceAllocation  = async () => {
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

// 获取资源状态文本
const getResourceStatusText = (status) => {
  switch (status) {
    case '0': return '在用';
    case '1': return '闲置';
    case '2': return '维修';
    default: return '未知';
  }
};

// 获取资源状态标签类型
const getResourceStatusTagType = (status) => {
  switch (status) {
    case '0': return 'warning';
    case '1': return 'success';
    case '2': return 'info';
    default: return 'default';
  }
};

// 预警信息滚动配置（初始化时data为空，通过API获取）
const warningScrollConfig = ref({
  header: ['区域', '预警类型', '时间', '状态'],
  data: [],  // 初始化为空数组
  rowNum: 5,
  align: ['center', 'center', 'center', 'center']
});


// 新预警通知相关变量（新增存储预警类型数据的响应式变量）
const newWarningVisible = ref(false);
const newWarning = ref(null);
const warningTypes = ref([]);  // 存储从API获取的预警类型数据

// 模拟新预警推送（使用从API获取的warningTypes）
const simulateNewWarning = () => {
  // 若预警类型数据未加载，直接返回
  if (warningTypes.value.length === 0) return;

  // 随机选择一个预警项
  const randomIndex = Math.floor(Math.random() * warningTypes.value.length);
  newWarning.value = {
    ...warningTypes.value[randomIndex],
    time: formatTime(new Date())
  };

  newWarningVisible.value = true;
};

// 忽略预警
const ignoreWarning = () => {
  newWarningVisible.value = false;
};

// 处理新预警
const handleNewWarning = () => {
  if (!newWarning.value) {
    newWarningVisible.value = false;
    return;
  }

  // 风险相关预警
  if (newWarning.value.title.includes('风险等级')) {
    // 宽松匹配（忽略大小写）
    const risk = risks.value.find(r =>
      r.risk_point_name.toLowerCase().includes('东方化工厂仓库'.toLowerCase())
    );
    if (risk) {
      showRiskDetail(risk);
    } else {
      ElMessage.warning('未找到对应的风险点数据');
    }
  }
  // 资源相关预警
  else if (newWarning.value.title.includes('资源')) {
    // 注意：资源名称字段是res_name，不是resource_name
    const resource = resources.value.find(r =>
      r.res_name.toLowerCase().includes('防汛沙袋'.toLowerCase())
    );
    if (resource) {
      showResourceDetail(resource);
    } else {
      ElMessage.warning('未找到对应的资源数据');
    }
  }
  // 指标相关预警
  else if (newWarning.value.title.includes('指标')) {
    // 查找现有指标“平均处置时长”（与修改后的预警内容匹配）
    const indicator = coreIndicators.value.find(i =>
      i.index_name.toLowerCase().includes('平均处置时长'.toLowerCase())
    );

    if (indicator) {
      showIndicatorDetail(indicator); // 打开“平均处置时长”的详情弹窗
    } else {
      ElMessage.warning('未找到对应的指标数据');
    }
  }

  newWarningVisible.value = false;
};

onMounted(() => {
  // 初始化时间
  currentTime.value = formatTime(new Date());
  const timer = setInterval(() => {
    currentTime.value = formatTime(new Date());
  }, 1000);

  // 初始化数据
  const initData = async () => {
    try {
      const [
        overviewData,
        indicators,
        riskGeometriesData,
        resourceData,
        heatmapResult,
        warningScrollData,
        warnTypes
      ] = await Promise.all([
        fetchEmergencyGlobalOverview(),
        fetchEmergencyCoreIndicators(),
        fetchEmergencyRiskGeometries(),
        fetchEmergencyResources(),
        fetchEmergencyHeatmapData(),
        fetchWarningScrollData(),
        fetchWarningTypes()
      ]);

      emergencyGlobalOverview.value = overviewData;
      coreIndicators.value = indicators;
      geometriesArray.value = riskGeometriesData;
      resources.value = resourceData;
      heatmapData.value = heatmapResult;
      warningScrollConfig.value.data = warningScrollData;
      warningTypes.value = warnTypes;

      if (overviewData) { // 现在overviewData是对象而非数组
        currentOverview.value = overviewData;
        // 映射事件统计数据（字段名从驼峰转下划线，保持与模板一致）
        eventStats.value = {
          total_evt_count: overviewData.totalEvtCount,
          handl_count: overviewData.handlCount,
          completed_count: overviewData.completedCount
        };
        // 映射资源统计数据
        resourceStats.value = {
          total_res_count: overviewData.totalResCount
        };
        // 映射风险与区域统计数据
        riskRegionStats.value = {
          high_risk_count: overviewData.highRiskCount,
          region_cover_count: overviewData.regionCoverCount
        };
      }
    } catch (error) {
      console.error('初始化数据失败:', error);
      ElMessage.error('数据加载失败，请刷新页面重试');
    }
  };

  initData();

  // 定时模拟新预警
  const warningTimer = setInterval(() => {
    // 30%概率出现新预警
    if (Math.random() < 0.3) {
      simulateNewWarning();  // 此时warningTypes已通过initData加载
    }
  }, 30000);  // 每30秒检查一次

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
  flex: 1;
}

.left_top {
  height: 30%;
  margin-bottom: 2%;
}

.left_bottom {
  height: 68%;
}

.middle {
  flex: 1;
}

.middle_top {
  height: 68%;
  margin-bottom: 2%;
}

.middle_bottom {
  height: 30%;
}

.right {
  flex: 1;
}

.right_top {
  height: 30%;
  margin-bottom: 2%;
}

.right_bottom {
  height: 68%;
  padding: 0 0.6vw;
}


</style>
