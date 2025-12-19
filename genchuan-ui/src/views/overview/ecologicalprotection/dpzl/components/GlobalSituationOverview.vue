<template>
  <div class="page-container">
    <div class="mainbox">
      <div class="top">
        <div class="panel top_left" style="min-width: 1vw;">
          <!-- 环保核心指标 -->
          <div class="core-indicator">
            <!-- 指标卡片列表 -->
            <div class="indicator-cards">
              <div
                v-for="indicator in leftIndicators"
                :key="indicator.indicator_id"
                :class="['indicator-card', indicator.warn_status === '1' ? 'warning' : 'normal']"
              >
                <div class="indicator-title">{{ indicator.indicator_name }}</div>
                <div class="indicator-compliance">
                  <el-progress
                    :percentage="indicator.compliance_rate"
                    type="circle"
                    :stroke-width="circleStrokeWidth"
                  :stroke-linecap="'round'"
                  :color="getComplianceColor(indicator.compliance_rate)"
                  :width="circleWidth"
                  >
                  <template #default>
                    <span class="compliance-text">{{ indicator.compliance_rate }}%</span>
                  </template>
                  </el-progress>
                </div>
                <div class="indicator-yoy" :class="indicator.year_on_year >= 0 ? 'positive' : 'negative'">
                  同比：{{ indicator.year_on_year >= 0 ? '+' : '' }}{{ indicator.year_on_year }}%
                </div>
                <div class="indicator-actions">
                  <el-button
                    size="small"
                    link
                    class="action-btn trend-btn"
                    @click.stop="showIndicatorTrend(indicator)"
                  >
                    趋势
                  </el-button>
                  <el-button
                    size="small"
                    link
                    class="action-btn abnormal-btn"
                    @click.stop="showIndicatorAbnormal(indicator)"
                    :disabled="!indicator.abnormal_reason"
                  >
                    异常
                  </el-button>
                </div>
              </div>
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel top_middle" style="min-width: 2vw;" ref="map">
          <div class="header-actions">
            <p>区域生态格局视图</p>
            <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('map')">
              <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
            </button>
          </div>
          <map-common
            idName="ecoMap"
            :geometriesArray="geometriesArray"
          />
          <div class="panel-footer"></div>
        </div>
        <div class="panel top_right" style="min-width: 1vw;">
          <!-- 环保核心指标 -->
          <div class="core-indicator">
            <!-- 指标卡片列表 -->
            <div class="indicator-cards">
              <div
                v-for="indicator in rightIndicators"
                :key="indicator.indicator_id"
                :class="['indicator-card', indicator.warn_status === '1' ? 'warning' : 'normal']"
              >
                <div class="indicator-title">{{ indicator.indicator_name }}</div>
                <div class="indicator-compliance">
                  <el-progress
                    :percentage="indicator.compliance_rate"
                    type="circle"
                    :stroke-width="circleStrokeWidth"
                    :stroke-linecap="'round'"
                    :color="getComplianceColor(indicator.compliance_rate)"
                    :width="circleWidth"
                  >
                  <template #default>
                    <span class="compliance-text">{{ indicator.compliance_rate }}%</span>
                  </template>
                  </el-progress>
                </div>
                <div class="indicator-yoy" :class="indicator.year_on_year >= 0 ? 'positive' : 'negative'">
                  同比：{{ indicator.year_on_year >= 0 ? '+' : '' }}{{ indicator.year_on_year }}%
                </div>
                <div class="indicator-actions">
                  <el-button
                    size="small"
                    link
                    class="action-btn trend-btn"
                    @click.stop="showIndicatorTrend(indicator)"
                  >
                    趋势
                  </el-button>
                  <el-button
                    size="small"
                    link
                    class="action-btn abnormal-btn"
                    @click.stop="showIndicatorAbnormal(indicator)"
                    :disabled="!indicator.abnormal_reason"
                  >
                    异常
                  </el-button>
                </div>
              </div>
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>
      <div class="bottom">
        <div class="bottom_left" style="min-width: 2vw;">
          <!-- 环保全域数据概览 -->
          <div class="core-overview-panel panel" ref="ecoOverviewPanel">
            <div class="panel-header">
              <h2>环保全域数据概览</h2>
              <div class="header-actions" v-if="currentOverview">
                <el-button
                  type="primary"
                  size="small"
                  @click="viewAnalysisReport(currentOverview.report_id)"
                  :disabled="!currentOverview.report_id"
                >
                  查看分析报告
                </el-button>
              </div>
            </div>
            <div class="panel-body">
              <!-- 概览统计项 -->
              <div class="overview-stats">
                <div class="overview-stat-item" @click="jumpToAirQuality()">
                  <div class="stat-title">空气质量达标天数</div>
                  <div class="stat-value">{{ currentOverview?.air_quality_days || 0 }} 天</div>
                  <div class="stat-rate" :class="getRateClass(
                    (currentOverview?.air_quality_days / currentOverview?.air_total_days * 100) || 0
                  )">
                    占比 {{ Math.round((currentOverview?.air_quality_days / currentOverview?.air_total_days * 100) || 0) }}%
                  </div>
                </div>
                <div class="overview-stat-item" @click="jumpToDrinkingWater()">
                  <div class="stat-title">饮用水水源地达标率</div>
                  <div class="stat-value">{{ currentOverview?.drinking_water_normal || 0 }} / {{ currentOverview?.drinking_water_total || 0 }}</div>
                  <div class="stat-rate" :class="getRateClass(
                    (currentOverview?.drinking_water_normal / currentOverview?.drinking_water_total * 100) || 0
                  )">
                    达标率 {{ Math.round((currentOverview?.drinking_water_normal / currentOverview?.drinking_water_total * 100) || 0) }}%
                  </div>
                </div>
                <div class="overview-stat-item" @click="jumpToPollutantSource()">
                  <div class="stat-title">重点污染源在线率</div>
                  <div class="stat-value">{{ currentOverview?.pollutant_online_count || 0 }} / {{ currentOverview?.pollutant_total_count || 0 }}</div>
                  <div class="stat-rate" :class="getRateClass(
                    (currentOverview?.pollutant_online_count / currentOverview?.pollutant_total_count * 100) || 0
                  )">
                    在线率 {{ Math.round((currentOverview?.pollutant_online_count / currentOverview?.pollutant_total_count * 100) || 0) }}%
                  </div>
                </div>
                <div class="overview-stat-item" @click="jumpToSolidWaste()">
                  <div class="stat-title">固废处置率</div>
                  <div class="stat-value">{{ currentOverview?.solid_dispose_completed || 0 }} / {{ currentOverview?.solid_dispose_total || 0 }} 吨</div>
                  <div class="stat-rate" :class="getRateClass(
                    (currentOverview?.solid_dispose_completed / currentOverview?.solid_dispose_total * 100) || 0
                  )">
                    处置率 {{ Math.round((currentOverview?.solid_dispose_completed / currentOverview?.solid_dispose_total * 100) || 0) }}%
                  </div>
                </div>
              </div>

            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="bottom_middle" style="min-width: 3vw;">
          <!-- 污染源分布视图 -->
          <div class="panel" ref="pollutantDistributionPanel">
            <div class="panel-header">
              <h2>污染源分布视图</h2>
              <div class="header-actions">
                <!-- 污染源类型筛选 -->
                <el-select v-model="pollutantTypeFilter" placeholder="污染源类型" size="small">
                  <el-option label="全部" value="" />
                  <el-option label="工业源" value="工业源" />
                  <el-option label="农业源" value="农业源" />
                  <el-option label="生活源" value="生活源" />
                  <el-option label="移动源" value="移动源" />
                </el-select>
                <!-- 超标状态筛选 -->
                <el-select v-model="pollutantOverstandardFilter" placeholder="超标状态" size="small">
                  <el-option label="全部" value="" />
                  <el-option label="未超标" value="0" />
                  <el-option label="已超标" value="1" />
                </el-select>
                <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('pollutantDistributionPanel')">
                  <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
                </button>
              </div>
            </div>
            <div style="width: 100%; height: 100%;">
              <!-- 污染源表格 -->
              <el-table
                :data="filteredPollutants"
                border
                size="small"
                @row-click="showPollutantDetail"
                height="86%"
              >
                <el-table-column
                  prop="pollutant_name"
                  label="污染源名称"
                  width="150px"
                />
                <el-table-column
                  prop="pollutant_type"
                  label="类型"
                />
                <el-table-column
                  prop="real_emission_index"
                  label="实时监测值"
                  width="180px"
                />
                <el-table-column
                  prop="overstandard_status"
                  label="超标状态"
                >
                  <template #default="scope">
                    <el-tag :type="scope.row.overstandard_status === '1' ? 'danger' : 'success'">
                      {{ scope.row.overstandard_status === '1' ? '已超标' : '未超标' }}
                    </el-tag>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="ext_cat1"
                  label="联系人"
                />
                <el-table-column
                  label="操作"
                >
                  <template #default="scope">
                    <el-button
                      size="small"
                      link
                      @click="showPollutantDetail(scope.row)"
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
        <div class="bottom_right" style="min-width: 2vw;">
          <!-- 污染源统计 -->
          <div class="panel pollutant-stats-panel" ref="pollutantStatsPanel">
            <div class="panel-header">
              <h2>污染源统计</h2>
              <div class="header-actions">
                <el-button size="small" type="primary" @click="exportPollutantData">
                  导出数据
                </el-button>
              </div>
            </div>
            <div class="panel-body">
              <!-- 统计卡片网格 -->
              <div class="pollutant-grid">
                <div class="pollutant-card">
                  <div class="pollutant-title">工业源：{{ industrialCount }}</div>
                  <div class="pollutant-sub">超标: {{ industrialOverstandardCount }}</div>
                </div>
                <div class="pollutant-card">
                  <div class="pollutant-title">农业源：{{ agriculturalCount }}</div>
                  <div class="pollutant-sub">超标: {{ agriculturalOverstandardCount }}</div>
                </div>
                <div class="pollutant-card">
                  <div class="pollutant-title">生活源：{{ domesticCount }}</div>
                  <div class="pollutant-sub">超标: {{ domesticOverstandardCount }}</div>
                </div>
                <div class="pollutant-card">
                  <div class="pollutant-title">移动源：{{ mobileSourceCount }}</div>
                  <div class="pollutant-sub">超标: {{ mobileSourceOverstandardCount }}</div>
                </div>
              </div>

              <!-- 污染源饼图 -->
              <div class="pollutant-chart">
                <ChartPie :data="pollutantPieData" />
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
      当前时间：{{currentTime}}
    </div>

    <!-- 指标详情弹窗 -->
    <el-dialog
      v-model="indicatorDetailVisible"
      :title="currentIndicator?.indicator_name || '指标详情'"
      width="600px"
    >
      <div class="indicator-detail">
        <div class="detail-section">
          <h3>指标信息</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="指标编码">{{ currentIndicator?.indicator_code }}</el-descriptions-item>
            <el-descriptions-item label="实时值">
              <span class="current-value">{{ currentIndicator?.real_value }}{{ currentIndicator?.unit }}</span>
            </el-descriptions-item>
            <el-descriptions-item label="阈值范围">
              {{ currentIndicator?.threshold_min }} - {{ currentIndicator?.threshold_max }}{{ currentIndicator?.unit }}
            </el-descriptions-item>
            <el-descriptions-item label="预警状态">
              <el-tag :type="currentIndicator?.warn_status === '1' ? 'danger' : 'success'">
                {{ currentIndicator?.warn_status === '1' ? '预警' : '正常' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="异常原因" v-if="currentIndicator?.warn_status === '1'">
              <el-input
                type="textarea"
                v-model="currentIndicator.abnormal_reason"
                rows="3"
                @change="handleReasonChange"
              />
            </el-descriptions-item>
            <el-descriptions-item label="更新时间">{{ currentIndicator?.update_time }}</el-descriptions-item>
            <el-descriptions-item label="数据来源">{{ currentIndicator?.ext1 }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>近24小时趋势</h3>
          <div class="chart-container">
            <ChartLine
              :xAxis="indicatorTrendData.xAxis"
              :series="indicatorTrendData.series"
              :unit="currentIndicator?.unit"
              :title="currentIndicator?.indicator_name"
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
      <el-form :model="indicatorConfigForm" ref="indicatorConfigRef" label-width="100px">
        <el-form-item label="指标名称" disabled>
          <el-input v-model="indicatorConfigForm.indicator_name" />
        </el-form-item>
        <el-form-item label="阈值下限" required>
          <el-input
            v-model.number="indicatorConfigForm.threshold_min"
            suffix-icon="el-icon-arrow-down"
          />
        </el-form-item>
        <el-form-item label="阈值上限" required>
          <el-input
            v-model.number="indicatorConfigForm.threshold_max"
            suffix-icon="el-icon-arrow-up"
          />
        </el-form-item>
        <el-form-item label="单位" required>
          <el-input v-model="indicatorConfigForm.unit" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="indicatorConfigVisible = false">取消</el-button>
        <el-button type="primary" @click="saveIndicatorConfig">保存配置</el-button>
      </template>
    </el-dialog>

    <!-- 区域生态详情弹窗 -->
    <el-dialog
      v-model="regionDetailVisible"
      :title="currentRegion?.region_name || '区域生态详情'"
      width="600px"
    >
      <div class="region-detail">
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="区域ID">{{ currentRegion?.region_id }}</el-descriptions-item>
            <el-descriptions-item label="区域类型">{{ currentRegion?.eco_region_type }}</el-descriptions-item>
            <el-descriptions-item label="保护级别">{{ currentRegion?.ext1 }}</el-descriptions-item>
            <el-descriptions-item label="监测点数量">{{ currentRegion?.monitor_point_count }} 个</el-descriptions-item>
            <el-descriptions-item label="更新时间">{{ currentRegion?.update_time }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>核心生态指标</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="指标详情">{{ currentRegion?.core_eco_indicator }}</el-descriptions-item>
            <el-descriptions-item label="预警状态">
              <el-tag :type="currentRegion?.warn_status === '1' ? 'danger' : 'success'">
                {{ currentRegion?.warn_status === '1' ? '预警' : '正常' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="预警原因" v-if="currentRegion?.warn_status === '1'">
              {{ currentRegion?.warn_reason }}
            </el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 预警处理区域 -->
        <template v-if="currentRegion?.warn_status === '1'">
          <div class="warning-handle-section">
            <h3>预警处理</h3>
            <el-form>
              <el-form-item label="处理措施" required>
                <el-input type="textarea" v-model="regionWarningHandle" rows="3" />
              </el-form-item>
            </el-form>
          </div>
        </template>
      </div>
      <template #footer>
        <el-button @click="regionDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="submitRegionWarningHandle"
          v-if="currentRegion?.warn_status === '1'"
        >
          提交处理措施
        </el-button>
      </template>
    </el-dialog>

    <!-- 污染源详情弹窗 -->
    <el-dialog
      v-model="pollutantDetailVisible"
      :title="currentPollutant?.pollutant_name || '污染源详情'"
      width="600px"
    >
      <div class="pollutant-detail">
        <div class="detail-section">
          <el-descriptions column="1" border>
            <el-descriptions-item label="污染源ID">{{ currentPollutant?.pollutant_id }}</el-descriptions-item>
            <el-descriptions-item label="类型">{{ currentPollutant?.pollutant_type }}</el-descriptions-item>
            <el-descriptions-item label="所属区域">{{ currentPollutant?.belong_region }}</el-descriptions-item>
            <el-descriptions-item label="坐标">{{ currentPollutant?.coordinate }}</el-descriptions-item>
            <el-descriptions-item label="实时监测值">{{ currentPollutant?.real_emission_index }}</el-descriptions-item>
            <el-descriptions-item label="超标状态">
              <el-tag :type="currentPollutant?.overstandard_status === '1' ? 'danger' : 'success'">
                {{ currentPollutant?.overstandard_status === '1' ? '已超标' : '未超标' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="联系人">{{ currentPollutant?.ext_cat1 || '未填写' }}</el-descriptions-item>
            <el-descriptions-item label="最近监管时间">{{ currentPollutant?.last_supervise_time || '无记录' }}</el-descriptions-item>
            <el-descriptions-item label="数据更新时间">{{ currentPollutant?.update_time }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>监管记录</h3>
          <el-form>
            <el-form-item label="监管情况" required>
              <el-input type="textarea" v-model="pollutantSuperviseContent" rows="3" />
            </el-form-item>
          </el-form>
        </div>
      </div>
      <template #footer>
        <el-button @click="pollutantDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="handleSubmitPollutantSupervise"
        >
          提交监管记录
        </el-button>
      </template>
    </el-dialog>

    <!-- 分析报告弹窗 -->
    <el-dialog
      v-model="reportVisible"
      :title="currentReport?.title || '环保分析报告'"
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
        <el-checkbox label="生态红线区" />
        <el-checkbox label="植被覆盖区" />
        <el-checkbox label="湿地保护区" />
        <el-checkbox label="水源涵养区" />
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
// 导入Vue核心API
import { ref, computed, onMounted, onUnmounted, getCurrentInstance, reactive } from 'vue';
// 导入Element Plus组件
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
  ElMessageBox,
  ElCheckbox,
  ElCheckboxGroup
} from 'element-plus';
// 导入全屏工具
import screenFull from 'screenfull';
// 导入自定义组件
import MapCommon from './MapCommon.vue';
import ChartLine from './ChartLine.vue';
import ChartPie from './ChartPie.vue';
// 导入Element Plus图标
import {FullScreen, Warning} from "@element-plus/icons-vue";

// 导入API方法
import {
  fetchEcoGlobalOverview,
  exportEcoGlobalOverview,
  fetchEcoGeometries,
  viewEcoAnalysisReport,
  fetchEcoCoreIndicators,
  updateIndicatorReason,
  updateIndicatorThreshold,
  fetchEcoRegionPatterns,
  fetchWarningScrollData,
  fetchWarningTypes,
  handleRegionWarning,
  fetchPollutantDistributions,
  submitPollutantSupervise
} from '@/api/overview/ecologicalprotection/GlobalSituationOverview.js';

const geometriesArray = ref([]);

const currentTime = ref('');

// 获取当前组件实例（用于访问ref）
const instance = getCurrentInstance();

import { useRouter } from 'vue-router'; // 导入路由钩子

const router = useRouter(); // 初始化路由实例

// 环形进度条尺寸配置（vw单位）
const circleSize = reactive({
  width: 8, // 环形直径（vw）
  strokeWidth: 0.5 // 线宽（vw）
});

// 计算环形直径（vw转px）
const circleWidth = computed(() => {
  return window.innerWidth * (circleSize.width / 100);
});

// 计算线宽（vw转px）
const circleStrokeWidth = computed(() => {
  return window.innerWidth * (circleSize.strokeWidth / 100);
});

// 窗口缩放时更新环形尺寸
const handleResize = () => {
  // 强制触发计算属性更新
  circleWidth.value;
  circleStrokeWidth.value;
};

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

// 时间格式化工具
const formatTime = (date) => {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');
  return `${year}年${month}月${day}日 ${hours}时${minutes}分${seconds}秒`;
};


// 全域概览数据存储
const ecoGlobalOverview = ref([]);
const currentOverview = ref(null); // 当前显示的概览数据
// 筛选条件
const overviewTimeRange = ref('today'); // 时间范围筛选
const overviewWarnStatus = ref(''); // 预警状态筛选

// 刷新概览数据
const refreshOverviewData = async () => {
  try {
    const data = await fetchEcoGlobalOverview();
    ecoGlobalOverview.value = data;
    // 默认显示最新的一条数据
    if (data.length > 0) {
      currentOverview.value = data[data.length - 1];
    }
    ElMessage.success('概览数据已刷新');
  } catch (error) {
    ElMessage.error('刷新失败: ' + (error.message || '未知错误'));
  }
};

// 导出概览数据
const exportOverviewData = async () => {
  try {
    const result = await exportEcoGlobalOverview({
      timeRange: overviewTimeRange.value,
      warnStatus: overviewWarnStatus.value
    });
    ElMessage.success(result.message);
  } catch (error) {
    ElMessage.error('导出失败: ' + (error.message || '未知错误'));
  }
};

// 查看分析报告相关
const reportVisible = ref(false); // 报告弹窗显示状态
const currentReport = ref(null); // 当前报告数据

const viewAnalysisReport = async (reportId) => {
  try {
    const report = await viewEcoAnalysisReport(reportId);
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

// 新增：获取达标率颜色
const getComplianceColor = (rate) => {
  if (rate < 60) return '#F56C6C';
  if (rate < 85) return '#E6A23C';
  return '#67C23A';
};

// 新增：显示指标趋势
const showIndicatorTrend = (indicator) => {
  currentIndicator.value = {...indicator};
  indicatorDetailVisible.value = true;

  if (indicator.trend_24h) {
    try {
      setTimeout(() => {
        const trendData = JSON.parse(indicator.trend_24h);
        indicatorTrendData.value = {
          xAxis: trendData.xAxis,
          series: [{name: indicator.indicator_name, data: trendData.series}]
        };
      }, 0);
    } catch (e) {
      console.error('解析24小时趋势数据失败:', e);
    }
  }
};

// 新增：显示指标异常信息
const showIndicatorAbnormal = (indicator) => {
  currentIndicator.value = {...indicator};
  // 显示异常原因弹窗
  ElMessageBox.alert(
    `<div style="padding: 10px 0">
      <p><strong>异常原因：</strong>${indicator.abnormal_reason || '暂无异常原因描述'}</p>
      <p><strong>更新时间：</strong>${indicator.update_time}</p>
    </div>`,
    `${indicator.indicator_name}异常信息`,
    {
      dangerouslyUseHTMLString: true,
      confirmButtonText: '关闭',
      type: 'warning'
    }
  );
};

// 异常数据点击跳转至对应专题视图
const jumpToAirQuality = () => {
  router.push('/overview/airquality/dpzl');
};

const jumpToDrinkingWater = () => {
  router.push('/overview/drinkingwater/dpzl');
};

const jumpToPollutantSource = () => {
  router.push('/overview/keypollutantsource/dpzl');
};

const jumpToSolidWaste = () => {
  router.push('/overview/solidwastedisposal/dpzl');
};

// 处理预警跳转
const handleWarnJump = () => {
  if (currentOverview.value?.ext2 === '空气') {
    ElMessage.info('正在跳转到空气领域预警详情...');
  } else {
    ElMessage.info('正在跳转到对应领域预警详情...');
  }
};

// 钻取至分域明细
const drillDownToDetail = () => {
  ElMessage.info('正在钻取至分域明细...');
};

// 获取达标率样式类（根据比例显示不同颜色）
const getRateClass = (rate) => {
  if (rate < 85) return 'danger';
  if (rate < 90) return 'warning';
  return 'normal';
};


// 核心指标数据存储
const coreIndicators = ref([]);

const leftIndicators = computed(() => {
  return coreIndicators.value.slice(0, 2);
});

const rightIndicators = computed(() => {
  return coreIndicators.value.slice(2, 4);
});

// 指标详情弹窗相关
const indicatorDetailVisible = ref(false); // 弹窗显示状态
const currentIndicator = ref(null); // 当前指标数据
const indicatorTrendData = ref({xAxis: [], series: []}); // 指标趋势数据

// 显示指标详情
const showIndicatorDetail = (indicator) => {
  currentIndicator.value = {...indicator};
  indicatorDetailVisible.value = true; // 先显示弹窗，确保DOM渲染开始

  // 弹窗显示后再解析数据（此时容器尺寸已稳定）
  if (indicator.trend_24h) {
    try {
      // 用setTimeout微延迟，确保弹窗DOM已完成首次渲染
      setTimeout(() => {
        const trendData = JSON.parse(indicator.trend_24h);
        indicatorTrendData.value = {
          xAxis: trendData.xAxis,
          series: [{name: indicator.indicator_name, data: trendData.series}]
        };
      }, 0);
    } catch (e) {
      console.error('解析24小时趋势数据失败:', e);
    }
  }
};

// 刷新核心指标
const refreshCoreIndicators = async () => {
  try {
    const data = await fetchEcoCoreIndicators();
    coreIndicators.value = data;
    ElMessage.success('核心指标已刷新');
  } catch (error) {
    ElMessage.error('刷新失败: ' + (error.message || '未知错误'));
  }
};

// 处理异常原因变更
const handleReasonChange = async () => {
  if (!currentIndicator.value) return;

  try {
    await updateIndicatorReason({
      indicator_id: currentIndicator.value.indicator_id,
      abnormal_reason: currentIndicator.value.abnormal_reason
    });

    // 更新本地数据
    const index = coreIndicators.value.findIndex(i => i.indicator_id === currentIndicator.value.indicator_id);
    if (index !== -1) {
      coreIndicators.value[index].abnormal_reason = currentIndicator.value.abnormal_reason;
    }

    ElMessage.success('异常原因已更新');
  } catch (error) {
    ElMessage.error('更新失败: ' + (error.message || '未知错误'));
  }
};

// 指标配置弹窗相关
const indicatorConfigVisible = ref(false); // 弹窗显示状态
const indicatorConfigForm = ref({ // 配置表单数据
  indicator_name: '',
  threshold_min: '',
  threshold_max: '',
  unit: ''
});
const indicatorConfigRef = ref(null); // 表单引用

// 显示指标配置
const showIndicatorConfig = () => {
  if (!currentIndicator.value) return;

  indicatorConfigForm.value = {
    indicator_name: currentIndicator.value.indicator_name,
    threshold_min: currentIndicator.value.threshold_min,
    threshold_max: currentIndicator.value.threshold_max,
    unit: currentIndicator.value.unit
  };

  indicatorConfigVisible.value = true;
};

// 保存指标配置
const saveIndicatorConfig = async () => {
  if (!currentIndicator.value) return;

  try {
    await updateIndicatorThreshold({
      indicator_id: currentIndicator.value.indicator_id,
      threshold_min: indicatorConfigForm.value.threshold_min,
      threshold_max: indicatorConfigForm.value.threshold_max,
      unit: indicatorConfigForm.value.unit
    });

    // 更新本地数据
    const index = coreIndicators.value.findIndex(i => i.indicator_id === currentIndicator.value.indicator_id);
    if (index !== -1) {
      coreIndicators.value[index].threshold_min = indicatorConfigForm.value.threshold_min;
      coreIndicators.value[index].threshold_max = indicatorConfigForm.value.threshold_max;
      coreIndicators.value[index].unit = indicatorConfigForm.value.unit;
    }

    // 更新当前指标
    currentIndicator.value.threshold_min = indicatorConfigForm.value.threshold_min;
    currentIndicator.value.threshold_max = indicatorConfigForm.value.threshold_max;
    currentIndicator.value.unit = indicatorConfigForm.value.unit;

    ElMessage.success('指标配置已保存');
    indicatorConfigVisible.value = false;
  } catch (error) {
    ElMessage.error('保存失败: ' + (error.message || '未知错误'));
  }
};


// 生态区域数据存储
const ecoRegions = ref([]);

// 筛选条件
const regionTypeFilter = ref(''); // 区域类型筛选
const regionWarnFilter = ref(''); // 预警状态筛选
const visibleLayers = ref(['生态红线区', '植被覆盖区', '湿地保护区', '水源涵养区']); // 可见图层

// 图层控制弹窗
const layerControlVisible = ref(false);

// 区域详情弹窗相关
const regionDetailVisible = ref(false); // 弹窗显示状态
const currentRegion = ref(null); // 当前区域数据
const regionWarningHandle = ref(''); // 预警处理措施

// 显示区域详情
const showRegionDetail = (region) => {
  currentRegion.value = {...region};
  regionWarningHandle.value = '';
  regionDetailVisible.value = true;
};

const togglePanelFullscreen = (panelRefName) => {
  toggleFullscreen(panelRefName);
};


// 应用图层控制
const applyLayerControl = () => {
  layerControlVisible.value = false;
  ElMessage.success('图层设置已应用');
};

// 提交区域预警处理措施
const submitRegionWarningHandle = async () => {
  if (!currentRegion.value || !regionWarningHandle.value) return;

  try {
    await handleRegionWarning({
      pattern_id: currentRegion.value.pattern_id,
      handle_measure: regionWarningHandle.value,
      handle_user: 'admin'
    });

    // 更新本地数据
    const index = ecoRegions.value.findIndex(r => r.pattern_id === currentRegion.value.pattern_id);
    if (index !== -1) {
      ecoRegions.value[index].warn_status = '0';
      ecoRegions.value[index].warn_reason = '';
    }

    currentRegion.value.warn_status = '0';
    currentRegion.value.warn_reason = '';

    ElMessage.success('预警处理措施已提交');
    regionDetailVisible.value = false;
  } catch (error) {
    ElMessage.error('提交失败: ' + (error.message || '未知错误'));
  }
};


// 污染源数据存储
const pollutants = ref([]);
// 筛选条件
const pollutantTypeFilter = ref(''); // 污染源类型筛选
const pollutantOverstandardFilter = ref(''); // 超标状态筛选

// 筛选后的污染源
const filteredPollutants = computed(() => {
  return pollutants.value.filter(pollutant => {
    const matchesType = !pollutantTypeFilter.value || pollutant.pollutant_type === pollutantTypeFilter.value;
    const matchesOverstandard = !pollutantOverstandardFilter.value || pollutant.overstandard_status === pollutantOverstandardFilter.value;
    return matchesType && matchesOverstandard;
  });
});

// 污染源统计数据（计算属性）
const industrialCount = computed(() => pollutants.value.filter(p => p.pollutant_type === '工业源').length);
const industrialOverstandardCount = computed(() => pollutants.value.filter(p => p.pollutant_type === '工业源' && p.overstandard_status === '1').length);

const agriculturalCount = computed(() => pollutants.value.filter(p => p.pollutant_type === '农业源').length);
const agriculturalOverstandardCount = computed(() => pollutants.value.filter(p => p.pollutant_type === '农业源' && p.overstandard_status === '1').length);

const domesticCount = computed(() => pollutants.value.filter(p => p.pollutant_type === '生活源').length);
const domesticOverstandardCount = computed(() => pollutants.value.filter(p => p.pollutant_type === '生活源' && p.overstandard_status === '1').length);

const mobileSourceCount = computed(() => pollutants.value.filter(p => p.pollutant_type === '移动源').length);
const mobileSourceOverstandardCount = computed(() => pollutants.value.filter(p => p.pollutant_type === '移动源' && p.overstandard_status === '1').length);

// 污染源饼图数据
const pollutantPieData = computed(() => ({
  legend: ['工业源', '农业源', '生活源', '移动源'],
  series: [
    {
      name: '数量',
      data: [
        industrialCount.value,
        agriculturalCount.value,
        domesticCount.value,
        mobileSourceCount.value
      ]
    }
  ]
}));

// 污染源详情弹窗相关
const pollutantDetailVisible = ref(false); // 弹窗显示状态
const currentPollutant = ref(null); // 当前污染源数据
const pollutantSuperviseContent = ref(''); // 监管记录内容

// 显示污染源详情
const showPollutantDetail = (pollutant) => {
  currentPollutant.value = {...pollutant};
  pollutantSuperviseContent.value = '';
  pollutantDetailVisible.value = true;
};

// 刷新污染源数据
const refreshPollutantData = async () => {
  try {
    const data = await fetchPollutantDistributions();
    pollutants.value = data;
    ElMessage.success('污染源数据已刷新');
  } catch (error) {
    ElMessage.error('刷新失败: ' + (error.message || '未知错误'));
  }
};

// 导出污染源数据
const exportPollutantData = () => {
  ElMessage.success('污染源数据导出成功');
};

// 提交污染源监管记录
const handleSubmitPollutantSupervise = async () => {
  if (!currentPollutant.value || !pollutantSuperviseContent.value) return;

  try {
    await submitPollutantSupervise({
      distribution_id: currentPollutant.value.distribution_id,
      supervise_content: pollutantSuperviseContent.value,
      supervise_user: 'admin',
      supervise_time: formatTime(new Date())
    });

    // 更新本地数据
    const index = pollutants.value.findIndex(p => p.distribution_id === currentPollutant.value.distribution_id);
    if (index !== -1) {
      pollutants.value[index].last_supervise_time = formatTime(new Date());
      pollutants.value[index].supervise_user = 'admin';
    }

    currentPollutant.value.last_supervise_time = formatTime(new Date());
    currentPollutant.value.supervise_user = 'admin';

    ElMessage.success('监管记录已提交');
    pollutantDetailVisible.value = false;
  } catch (error) {
    ElMessage.error('提交失败: ' + (error.message || '未知错误'));
  }
};


// 预警信息滚动配置
const warningScrollConfig = ref({
  header: ['区域', '预警类型', '时间', '状态'],
  data: [],  // 初始化为空数组，通过API获取
  rowNum: 5,
  align: ['center', 'center', 'center', 'center']
});

// 新预警通知相关
const newWarningVisible = ref(false); // 新预警弹窗显示状态
const newWarning = ref(null); // 新预警数据
const warningTypes = ref([]);  // 预警类型数据（从API获取）

// 模拟新预警
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
  if (newWarning.value) {
    if (newWarning.value.title.includes('空气')) {
      ElMessage.info('正在跳转到空气质量预警详情...');
    } else if (newWarning.value.title.includes('污染源')) {
      const pollutant = pollutants.value.find(p => p.pollutant_name.includes('钢铁厂'));
      if (pollutant) {
        showPollutantDetail(pollutant);
      }
    } else if (newWarning.value.title.includes('生态区域')) {
      const region = ecoRegions.value.find(r => r.region_name.includes('南部湿地'));
      if (region) {
        showRegionDetail(region);
      }
    }
  }
  newWarningVisible.value = false;
};

// 组件挂载时执行
onMounted(() => {
  // 初始化时间（当前时间显示）
  currentTime.value = formatTime(new Date());
  const timer = setInterval(() => {
    currentTime.value = formatTime(new Date());
  }, 1000);

  // 监听窗口缩放
  window.addEventListener('resize', handleResize);

  // 初始化数据
  const initData = async () => {
    try {
      // 并行加载所有初始数据
      const [
        overviewData,
        indicators,
        ecoGeometries,
        regions,
        pollutantData,
        warningScrollData,  // 预警滚动数据
        warnTypes           // 预警类型数据
      ] = await Promise.all([
        fetchEcoGlobalOverview(),
        fetchEcoCoreIndicators(),
        fetchEcoGeometries(),
        fetchEcoRegionPatterns(),
        fetchPollutantDistributions(),
        fetchWarningScrollData(),
        fetchWarningTypes()
      ]);

      // 赋值所有数据
      ecoGlobalOverview.value = overviewData;
      if (overviewData.length > 0) {
        currentOverview.value = overviewData[overviewData.length - 1];
      }

      coreIndicators.value = indicators;
      geometriesArray.value = ecoGeometries;
      ecoRegions.value = regions;
      pollutants.value = pollutantData;
      warningScrollConfig.value.data = warningScrollData;
      warningTypes.value = warnTypes;
    } catch (error) {
      console.error('初始化数据失败:', error);
      ElMessage.error('数据加载失败，请刷新页面重试');
    }
  };

  // 初始化数据
  initData();

  // 自动刷新定时器（5分钟 = 300000毫秒）
  const autoRefreshTimer = setInterval(() => {
    refreshOverviewData();
  }, 300000);

  // 定时模拟新预警（每30秒检查一次，30%概率触发）
  const warningTimer = setInterval(() => {
    if (Math.random() < 0.3) {
      simulateNewWarning();
    }
  }, 30000);

  // 组件卸载时清理定时器
  onUnmounted(() => {
    clearInterval(timer);
    clearInterval(autoRefreshTimer);
    clearInterval(warningTimer);
    window.removeEventListener('resize', handleResize); // 移除窗口缩放监听
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
  box-sizing: border-box;
}

// 页面主体盒子
.mainbox {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 0.6vw 0;
  height: 90vh;
  box-sizing: border-box;
  gap: 0.6vw;
}

// 公共面板样式
.panel {
  position: relative;
  height: 100%;
  border: 0.2vh solid rgba(25, 186, 139, 0.17);
  background: url("@/assets/chart/images/line(1).png") rgba(255, 255, 255, .04);
  display: flex;
  flex-direction: column;
  min-height: 0;
}

.top {
  display: flex;
  gap: 0.6vw;
  height: 52%;
}

.top_left {
  flex: 1;
}

.top_middle {
  flex: 2;
}

.top_right {
  flex: 1;
}

.bottom {
  display: flex;
  gap: 0.6vw;
  height: 44%;
}

.bottom_left {
  flex: 2;
}

.bottom_middle {
  flex: 3;
}

.bottom_right {
  flex: 2;
}

</style>
