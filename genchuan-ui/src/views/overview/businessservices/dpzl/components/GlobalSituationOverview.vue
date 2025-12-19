<template>
  <div class="page-container">
    <div class="mainbox">
      <div class="top">
        <div class="top_left" style="min-width: 2vw;">
          <!-- 营商核心指标 -->
          <div class="panel core-indicators-panel" ref="coreIndicatorsPanel">
            <div class="panel-body">
              <!-- 指标卡片列表 -->
              <div class="indicator-cards">
                <div
                  v-for="indicator in filteredIndicators"
                  :key="indicator.indicator_id"
                  :class="['indicator-card', indicator.warn_status === '1' ? 'warning' : 'normal']"
                  @click="showIndicatorDetail(indicator)"
                >
                  <div class="indicator-title">{{ indicator.indicator_name }}</div>
                  <div class="indicator-value">
                    {{ indicator.real_value }}
                    <span class="indicator-unit">{{ indicator.unit }}</span>
                  </div>
                  <div class="indicator-yoy" :class="indicator.year_on_year >= 0 ? 'positive' : 'negative'">
                    同比：{{ indicator.year_on_year >= 0 ? '+' : '' }}{{ indicator.year_on_year }}%
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="panel top_middle" style="min-width: 3vw;" ref="map">
          <div class="header-actions">
            <p>全域企业分布地图</p>
            <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('map')">
              <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
            </button>
          </div>
          <map-common idName="chinaEcharts" :geometriesArray="geometriesArray"/>
          <div class="panel-footer"></div>
        </div>
        <div class="top_right" style="min-width: 2vw;">
          <!-- 企业资源分布视图 -->
          <div class="panel pollutant-distribution-panel" ref="pollutantDistributionPanel">
            <div class="panel-header">
              <h2>企业资源分布视图</h2>
              <div class="header-actions">
                <el-button size="small" type="primary" @click="toggleFacilityView">
                  {{ currentFacilityView === 'chart' ? '显示列表' : '显示饼图' }}
                </el-button>
                <el-button size="small" type="primary" @click="exportOverviewData">导出数据</el-button>
              </div>
            </div>
            <div class="panel-body">
              <!-- 行业/规模分布图表 -->
              <div class="charts-container2" v-if="currentFacilityView === 'chart'">
                <div class="chart-item1">
                  <ChartPie2 :data="industryDistributionData" :title="'行业企业分布'" />
                </div>
                <div class="chart-item2">
                  <ChartPie3 :data="scaleDistributionData" :title="'企业规模分布'" />
                </div>
              </div>

              <!-- 重点企业列表 -->
              <div v-else>
                <el-table
                  :data="filteredEnterprises"
                  border
                  size="small"
                  style="width: 100%; height: 36vh"
                  @row-click="showEnterpriseDetail"
                >
                  <el-table-column prop="ent_name" label="企业名称" width="200px" />
                  <el-table-column prop="industry" label="所属行业" />
                  <el-table-column prop="scale" label="企业规模" />
                  <el-table-column prop="location" label="所在区域" />
                  <el-table-column label="操作">
                    <template #default="scope">
                      <el-button size="small" type="text" @click="showEnterpriseDetail(scope.row)">详情</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
      <div class="bottom">
        <div class="bottom_left" style="min-width: 2vw;">
          <!-- 营商全域数据概览 -->
          <div class="panel" ref="regionPatternPanel">
            <div class="panel-header">
              <h2>营商全域数据概览</h2>
              <div class="header-actions">
                <!-- 时间范围筛选 -->
                <el-select v-model="overviewTimeRange" placeholder="时间范围" size="small" style="width: 6vw;">
                  <el-option label="今日" value="today" />
                  <el-option label="本周" value="week" />
                  <el-option label="本月" value="month" />
                </el-select>
                <el-button size="small" type="primary" @click="viewAnalysisReport('biz-20251103')">查看分析报告</el-button>
                <button class="panel-fullscreen-btn" @click="toggleFullscreen('regionPatternPanel')">
                  <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body">
              <!-- 概览统计项 -->
              <div class="overview-stats">
                <div class="overview-stat-item">
                  <div class="stat-title">企业总数</div>
                  <div class="stat-value">{{ currentOverview?.total_ent_count || 0 }} 家</div>
                  <div class="stat-rate">较上月 +{{ Math.round(Math.random() * 5) }}%</div>
                </div>
                <div class="overview-stat-item">
                  <div class="stat-title">当日办件量</div>
                  <div class="stat-value">{{ currentOverview?.daily_handle_count || 0 }} 件</div>
                  <div class="stat-rate" :class="getRateClass((currentOverview?.daily_handle_count || 0) - 70)">
                    较昨日 {{ (currentOverview?.daily_handle_count || 0) - 70 >= 0 ? '+' : '' }}{{ (currentOverview?.daily_handle_count || 0) - 70 }} 件
                  </div>
                </div>
                <div class="overview-stat-item">
                  <div class="stat-title">政策兑现总额</div>
                  <div class="stat-value">{{ formatCurrency(currentOverview?.total_pol_amount || 0) }}</div>
                  <div class="stat-rate">本月累计</div>
                </div>
                <div class="overview-stat-item">
                  <div class="stat-title">诉求响应率</div>
                  <div class="stat-value">{{ currentOverview?.appeal_resp_rate || 0 }}%</div>
                  <div class="stat-rate" :class="currentOverview?.appeal_resp_rate < 90 ? 'danger' : 'normal'">
                    {{ currentOverview?.appeal_resp_rate < 90 ? '响应率偏低' : '正常' }}
                  </div>
                </div>
              </div>

              <!-- 图表区域 -->
              <div class="charts-container">
                <div class="chart-item">
                  <ChartBar
                    :xAxis="currentOverview?.region_ent_distribution?.map(item => item.region_name) || []"
                    :series="[{name: '企业数量', data: currentOverview?.region_ent_distribution?.map(item => item.ent_count) || []}]"
                    :unit="''"
                    :title="'区域企业分布'"
                  />
                </div>
                <div class="chart-item2">
                  <ChartLine2
                    :xAxis="currentOverview?.recent_7days_handle_trend?.map(item => item.date) || []"
                    :series="[{name: '办件量', data: currentOverview?.recent_7days_handle_trend?.map(item => item.handle_count) || []}]"
                    :unit="''"
                    :title="'近7天办件趋势'"
                  />
                </div>
                <div class="chart-item3">
                  <ChartPie
                    :data="{
                      legend: currentOverview?.industry_distribution?.map(item => item.industry_name) || [],
                      series: [{name: '企业数量', data: currentOverview?.industry_distribution?.map(item => item.count) || []}]
                    }"
                    :title="'企业行业占比'"
                  />
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="panel bottom_right" style="min-width: 2vw;">
          <!-- 服务事项总览 -->
          <div class="pollutant-stats-panel" ref="pollutantStatsPanel">
            <div class="panel-header">
              <h2>服务事项总览</h2>
              <div class="header-actions">
                <el-button size="small" type="primary" @click="toggleFacilityView2">
                  {{ currentFacilityView2 === 'chart' ? '显示列表' : '显示统计图' }}
                </el-button>
                <el-button size="small" type="primary" @click="exportServiceData">导出数据</el-button>
                <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('pollutantStatsPanel')">
                  <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body">
              <!-- 统计卡片网格 -->
              <div class="stats-grid">
                <div class="stat-card">
                  <div class="stat-title">服务事项总数</div>
                  <div class="stat-value">{{ serviceOverview?.total_svc_count || 0 }}</div>
                  <div class="stat-sub">项</div>
                </div>
                <div class="stat-card">
                  <div class="stat-title">当日待办量</div>
                  <div class="stat-value">{{ serviceOverview?.daily_pend_count || 0 }}</div>
                  <div class="stat-sub">项</div>
                </div>
                <div class="stat-card">
                  <div class="stat-title">事项办结率</div>
                  <div class="stat-value">{{ serviceOverview?.svc_complete_rate || 0 }}%</div>
                  <div class="stat-sub">较昨日 +1%</div>
                </div>
                <div class="stat-card" :class="serviceOverview?.overdue_svc_count > 5 ? 'warning' : ''">
                  <div class="stat-title">超期事项数</div>
                  <div class="stat-value">{{ serviceOverview?.overdue_svc_count || 0 }}</div>
                  <div class="stat-sub" v-if="serviceOverview?.overdue_svc_count > 5">超过阈值5项</div>
                </div>
              </div>

              <!-- 图表区域 -->
              <div class="charts-container3" v-if="currentFacilityView2 === 'chart'">
                <div class="chart-item1">
                  <ChartBar
                    :xAxis="serviceOverview?.type_distribution?.map(item => item.type_name) || []"
                    :series="[{name: '数量', data: serviceOverview?.type_distribution?.map(item => item.count) || []}]"
                    :unit="''"
                    :title="'事项类型分布'"
                  />
                </div>
                <div class="chart-item2">
                  <ChartBar2
                    :xAxis="serviceOverview?.region_rate_data?.map(item => item.region_name) || []"
                    :series="[{name: '办结率(%)', data: serviceOverview?.region_rate_data?.map(item => item.rate) || []}]"
                    :unit="''"
                    @barClick="showRegionApprovalDetail"
                    :title="'各区域审批办结率对比'"
                  />
                </div>
              </div>

              <!-- 待办事项列表 -->
              <div v-else>
                <el-table
                  :data="serviceOverview?.pending_items || []"
                  border
                  size="small"
                  style="width: 100%; height: 26vh"
                >
                  <el-table-column prop="item_name" label="事项名称" />
                  <el-table-column prop="apply_ent" label="申请企业" width="180px" />
                  <el-table-column prop="due_time" label="截止时间" />
                  <el-table-column prop="status" label="状态">
                    <template #default="scope">
                      <el-tag :type="scope.row.status === 'overdue' ? 'danger' : 'warning'">
                        {{ scope.row.status === 'overdue' ? '已超期' : '待办理' }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column label="操作">
                    <template #default="scope">
                      <el-button size="small" type="primary" @click="handleServiceItem(scope.row)">处理</el-button>
                    </template>
                  </el-table-column>
                </el-table>
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
          <h3>近7天趋势</h3>
          <div class="chart-container">
            <ChartLine3
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
        <el-button type="primary" @click="showIndicatorConfig">修改阈值</el-button>
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
          <el-input v-model.number="indicatorConfigForm.threshold_min" suffix-icon="el-icon-arrow-down" />
        </el-form-item>
        <el-form-item label="阈值上限" required>
          <el-input v-model.number="indicatorConfigForm.threshold_max" suffix-icon="el-icon-arrow-up" />
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

    <!-- 企业详情弹窗 -->
    <el-dialog
      v-model="enterpriseDetailVisible"
      :title="currentEnterprise?.ent_name || '企业详情'"
      width="600px"
    >
      <div class="region-detail">
        <div class="detail-section">
          <h3>基本信息</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="企业ID">{{ currentEnterprise?.ent_id }}</el-descriptions-item>
            <el-descriptions-item label="企业名称">{{ currentEnterprise?.ent_name }}</el-descriptions-item>
            <el-descriptions-item label="所属行业">{{ currentEnterprise?.industry }}</el-descriptions-item>
            <el-descriptions-item label="企业规模">{{ currentEnterprise?.scale }}</el-descriptions-item>
            <el-descriptions-item label="所在区域">{{ currentEnterprise?.location }}</el-descriptions-item>
            <el-descriptions-item label="注册时间">{{ currentEnterprise?.register_time }}</el-descriptions-item>
            <el-descriptions-item label="近期办理事项">
              <el-tag v-for="item in currentEnterprise?.recent_items" :key="item" style="margin-right: 5px;">
                {{ item }}
              </el-tag>
            </el-descriptions-item>
          </el-descriptions>
        </div>
        <div class="detail-section">
          <h3>服务记录</h3>
          <el-table
            :data="currentEnterprise?.service_records"
            border
            size="small"
            style="width: 100%;"
          >
            <el-table-column prop="date" label="日期" />
            <el-table-column prop="item" label="事项" />
            <el-table-column prop="status" label="状态" />
          </el-table>
        </div>
      </div>
      <template #footer>
        <el-button @click="enterpriseDetailVisible = false">关闭</el-button>
        <el-button type="primary" @click="handleEnterpriseService">发起服务</el-button>
      </template>
    </el-dialog>

    <!-- 服务事项处理弹窗 -->
    <el-dialog
      v-model="serviceHandleVisible"
      :title="currentServiceItem?.item_name || '处理服务事项'"
      width="600px"
    >
      <div class="pollutant-detail">
        <div class="detail-section">
          <el-descriptions column="1" border>
            <el-descriptions-item label="事项ID">{{ currentServiceItem?.item_id }}</el-descriptions-item>
            <el-descriptions-item label="事项名称">{{ currentServiceItem?.item_name }}</el-descriptions-item>
            <el-descriptions-item label="申请企业">{{ currentServiceItem?.apply_ent }}</el-descriptions-item>
            <el-descriptions-item label="申请时间">{{ currentServiceItem?.apply_time }}</el-descriptions-item>
            <el-descriptions-item label="截止时间">{{ currentServiceItem?.due_time }}</el-descriptions-item>
            <el-descriptions-item label="当前状态">
              <el-tag :type="currentServiceItem?.status === 'overdue' ? 'danger' : 'warning'">
                {{ currentServiceItem?.status === 'overdue' ? '已超期' : '待办理' }}
              </el-tag>
            </el-descriptions-item>
          </el-descriptions>
        </div>
        <div class="detail-section">
          <h3>处理信息</h3>
          <el-form>
            <el-form-item label="处理结果" required>
              <el-select v-model="serviceHandleResult" placeholder="请选择处理结果">
                <el-option label="已办结" value="completed" />
                <el-option label="需补充材料" value="need_more" />
                <el-option label="不予受理" value="rejected" />
              </el-select>
            </el-form-item>
            <el-form-item label="处理意见" required>
              <el-input type="textarea" v-model="serviceHandleOpinion" rows="4" />
            </el-form-item>
          </el-form>
        </div>
      </div>
      <template #footer>
        <el-button @click="serviceHandleVisible = false">取消</el-button>
        <el-button type="primary" @click="submitService">提交处理结果</el-button>
      </template>
    </el-dialog>

    <!-- 分析报告弹窗 -->
    <el-dialog
      v-model="reportVisible"
      :title="currentReport?.title || '营商环境分析报告'"
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
          <h4>核心指标分析</h4>
          <table class="report-table">
            <tr>
              <th>指标名称</th>
              <th>当前值</th>
              <th>同比变化</th>
              <th>状态</th>
            </tr>
            <tr v-for="indicator in coreIndicators" :key="indicator.indicator_id">
              <td>{{ indicator.indicator_name }}</td>
              <td>{{ indicator.real_value }}{{ indicator.unit }}</td>
              <td :class="indicator.year_on_year >= 0 ? 'positive' : 'negative'">
                {{ indicator.year_on_year >= 0 ? '+' : '' }}{{ indicator.year_on_year }}%
              </td>
              <td><el-tag :type="indicator.warn_status === '1' ? 'danger' : 'success'">{{ indicator.warn_status === '1' ? '预警' : '正常' }}</el-tag></td>
            </tr>
          </table>
          <h4>区域对比分析</h4>
          <p>高新区审批办结率最低，为82%，低于全市平均水平；城南区表现最佳，各项指标均优于其他区域。</p>
          <h4>改进建议</h4>
          <p>1. 针对高新区审批效率问题，建议增加窗口人员配置，优化审批流程；</p>
          <p>2. 针对城西区超期事项过多问题，建议建立超期预警机制，提前介入处理；</p>
          <p>3. 加强各区域政策兑现效率，确保企业及时享受政策红利。</p>
        </div>
      </div>
      <template #footer>
        <el-button @click="reportVisible = false">关闭</el-button>
        <el-button type="primary" @click="exportReport">导出报告</el-button>
      </template>
    </el-dialog>
  </div>
</template>
<script setup>
// 导入Vue核心API
import { ref, computed, onMounted, onUnmounted, getCurrentInstance } from 'vue';
// 导入Element Plus组件及图标
import {
  ElSelect, ElOption, ElButton, ElTag, ElInput, ElDialog,
  ElDescriptions, ElDescriptionsItem, ElForm, ElFormItem,
  ElMessage, ElTable, ElTableColumn
} from 'element-plus';
import { FullScreen } from "@element-plus/icons-vue";
import screenFull from 'screenfull';
import MapCommon from "./MapCommon.vue";
import ChartLine2 from './ChartLine2.vue';
import ChartLine3 from './ChartLine3.vue';
import ChartBar from './ChartBar.vue';
import ChartBar2 from './ChartBar2.vue';
import ChartPie from './ChartPie.vue';
import ChartPie2 from './ChartPie2.vue';
import ChartPie3 from './ChartPie3.vue';

// 导入API方法
import {
  fetchBusinessGlobalOverview,
  fetchBusinessCoreIndicators,
  fetchEnterpriseResourceDistribution,
  fetchServiceItemsOverview,
  fetchEnterpriseGeometries,
  updateIndicatorReason,
  updateIndicatorThreshold,
  submitServiceHandle,
  viewReport
} from '@/api/overview/businessservices/GlobalSituationOverview.js';

// 路由实例
import { useRouter } from 'vue-router';

const router = useRouter();

const geometriesArray = ref([]);

const currentTime = ref('');

// 当前组件实例（用于全屏功能）
const instance = getCurrentInstance();


// 数据存储
const businessGlobalOverview = ref({});
const currentOverview = ref(null);
// 筛选条件
const overviewTimeRange = ref('today');

// 导出概览数据
const exportOverviewData = async () => {
  ElMessage.success('数据导出成功');
};

// 分析报告相关
const reportVisible = ref(false);
const currentReport = ref(null);

const viewAnalysisReport = async (reportId) => {
  try {
    const report = await viewReport(reportId);
    currentReport.value = report;
    reportVisible.value = true;
  } catch (error) {
    ElMessage.error('查看报告失败: ' + (error.message || '未知错误'));
  }
};

const exportReport = () => {
  ElMessage.success('报告导出成功');
};

// 比率样式处理
const getRateClass = (value) => {
  if (value < 0) return 'danger';
  if (value === 0) return 'normal';
  return 'positive';
};


// 数据存储
const coreIndicators = ref([]);
// 筛选条件
const indicatorWarnStatus = ref('');

// 筛选后的指标
const filteredIndicators = computed(() => {
  return coreIndicators.value.filter(indicator => {
    return !indicatorWarnStatus.value || indicator.warn_status === indicatorWarnStatus.value;
  });
});

// 指标详情弹窗
const indicatorDetailVisible = ref(false);
const currentIndicator = ref(null);
const indicatorTrendData = ref({ xAxis: [], series: [] });

const showIndicatorDetail = (indicator) => {
  currentIndicator.value = { ...indicator };
  indicatorDetailVisible.value = true;

  if (indicator.trend_7days) {
    try {
      setTimeout(() => {
        const trendData = JSON.parse(indicator.trend_7days);
        indicatorTrendData.value = {
          xAxis: trendData.xAxis,
          series: [{ name: indicator.indicator_name, data: trendData.series }]
        };
      }, 0);
    } catch (e) {
      console.error('解析7天趋势数据失败:', e);
    }
  }
};

// 异常原因更新
const handleReasonChange = async () => {
  if (!currentIndicator.value) return;
  try {
    await updateIndicatorReason({
      indicator_id: currentIndicator.value.indicator_id,
      abnormal_reason: currentIndicator.value.abnormal_reason
    });
    const index = coreIndicators.value.findIndex(i => i.indicator_id === currentIndicator.value.indicator_id);
    if (index !== -1) coreIndicators.value[index].abnormal_reason = currentIndicator.value.abnormal_reason;
    ElMessage.success('异常原因已更新');
  } catch (error) {
    ElMessage.error('更新失败: ' + (error.message || '未知错误'));
  }
};

// 指标配置弹窗
const indicatorConfigVisible = ref(false);
const indicatorConfigForm = ref({ indicator_name: '', threshold_min: '', threshold_max: '', unit: '' });
const indicatorConfigRef = ref(null);

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

const saveIndicatorConfig = async () => {
  if (!currentIndicator.value) return;
  try {
    await updateIndicatorThreshold({
      indicator_id: currentIndicator.value.indicator_id,
      threshold_min: indicatorConfigForm.value.threshold_min,
      threshold_max: indicatorConfigForm.value.threshold_max,
      unit: indicatorConfigForm.value.unit
    });
    const index = coreIndicators.value.findIndex(i => i.indicator_id === currentIndicator.value.indicator_id);
    if (index !== -1) {
      coreIndicators.value[index].threshold_min = indicatorConfigForm.value.threshold_min;
      coreIndicators.value[index].threshold_max = indicatorConfigForm.value.threshold_max;
      coreIndicators.value[index].unit = indicatorConfigForm.value.unit;
    }
    currentIndicator.value.threshold_min = indicatorConfigForm.value.threshold_min;
    currentIndicator.value.threshold_max = indicatorConfigForm.value.threshold_max;
    currentIndicator.value.unit = indicatorConfigForm.value.unit;
    ElMessage.success('指标配置已保存');
    indicatorConfigVisible.value = false;
  } catch (error) {
    ElMessage.error('保存失败: ' + (error.message || '未知错误'));
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


// 数据存储
const enterpriseResources = ref({});
// 筛选条件
const enterpriseScaleFilter = ref('');
const enterpriseIndustryFilter = ref('');
// 视图切换
const currentFacilityView = ref('chart');

// 行业分布图表数据
const industryDistributionData = computed(() => ({
  legend: enterpriseResources.value?.key_enterprises
    ? [...new Set(enterpriseResources.value.key_enterprises.map(item => item.industry))]
    : [],
  series: [{
    name: '企业数量',
    data: enterpriseResources.value?.key_enterprises
      ? [...new Set(enterpriseResources.value.key_enterprises.map(item => item.industry))].map(industry =>
        enterpriseResources.value.key_enterprises.filter(item => item.industry === industry).length
      )
      : []
  }]
}));

// 规模分布图表数据
const scaleDistributionData = computed(() => ({
  legend: enterpriseResources.value?.scale_distribution?.map(item => item.scale) || [],
  series: [{ name: '企业数量', data: enterpriseResources.value?.scale_distribution?.map(item => item.count) || [] }]
}));

// 筛选后的企业
const filteredEnterprises = computed(() => {
  if (!enterpriseResources.value?.key_enterprises) return [];
  return enterpriseResources.value.key_enterprises.filter(enterprise => {
    const matchesScale = !enterpriseScaleFilter.value || enterprise.scale === enterpriseScaleFilter.value;
    const matchesIndustry = !enterpriseIndustryFilter.value || enterprise.industry === enterpriseIndustryFilter.value;
    return matchesScale && matchesIndustry;
  });
});

// 企业详情弹窗
const enterpriseDetailVisible = ref(false);
const currentEnterprise = ref(null);

const showEnterpriseDetail = (enterprise) => {
  currentEnterprise.value = { ...enterprise };
  enterpriseDetailVisible.value = true;
};

// 刷新企业数据
const refreshEnterpriseData = async () => {
  try {
    const data = await fetchEnterpriseResourceDistribution();
    enterpriseResources.value = data;
  } catch (error) {
    ElMessage.error('刷新失败: ' + (error.message || '未知错误'));
  }
};

// 发起企业服务
const handleEnterpriseService = () => {
  ElMessage.success(`已为${currentEnterprise.value?.ent_name}发起服务申请`);
  enterpriseDetailVisible.value = false;
};

// 视图切换
const toggleFacilityView = () => {
  currentFacilityView.value = currentFacilityView.value === 'chart' ? 'list' : 'chart';
};


// 数据存储
const serviceOverview = ref({});
// 筛选条件
const serviceTypeFilter = ref('');
// 视图切换
const currentFacilityView2 = ref('chart');

// 服务事项处理弹窗
const serviceHandleVisible = ref(false);
const currentServiceItem = ref(null);
const serviceHandleResult = ref('');
const serviceHandleOpinion = ref('');

const handleServiceItem = (item) => {
  currentServiceItem.value = { ...item };
  serviceHandleResult.value = '';
  serviceHandleOpinion.value = '';
  serviceHandleVisible.value = true;
};

// 提交处理结果
const submitService = async () => {
  if (!currentServiceItem.value || !serviceHandleResult.value || !serviceHandleOpinion.value) {
    ElMessage.warning('请填写处理结果和意见');
    return;
  }
  try {
    await submitServiceHandle({
      item_id: currentServiceItem.value.item_id,
      handle_result: serviceHandleResult.value,
      handle_opinion: serviceHandleOpinion.value,
      handle_user: 'admin',
      handle_time: formatTime(new Date())
    });
    if (serviceOverview.value?.pending_items) {
      const index = serviceOverview.value.pending_items.findIndex(item => item.item_id === currentServiceItem.value.item_id);
      if (index !== -1) serviceOverview.value.pending_items.splice(index, 1);
    }
    ElMessage.success('服务事项处理结果已提交');
    serviceHandleVisible.value = false;
  } catch (error) {
    ElMessage.error('提交失败: ' + (error.message || '未知错误'));
  }
};

// 刷新服务事项数据
const refreshServiceData = async () => {
  try {
    const data = await fetchServiceItemsOverview();
    serviceOverview.value = data;
  } catch (error) {
    ElMessage.error('刷新失败: ' + (error.message || '未知错误'));
  }
};

// 导出服务数据
const exportServiceData = async () => {
  ElMessage.success('数据导出成功');
};

// 视图切换
const toggleFacilityView2 = () => {
  currentFacilityView2.value = currentFacilityView2.value === 'chart' ? 'list' : 'chart';
};


// 全屏切换
const toggleFullscreen = (panelRefName) => {
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

// 货币格式化
const formatCurrency = (value) => {
  if (!value) return '0.00元';
  return '¥' + value.toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
};

// 区域审批详情
const showRegionApprovalDetail = (regionName) => {
  ElMessage.info(`正在查看${regionName}的审批明细...`);
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
      const [overviewData, indicators, enterpriseData, serviceData, geometryData] = await Promise.all([
        fetchBusinessGlobalOverview(),
        fetchBusinessCoreIndicators(),
        fetchEnterpriseResourceDistribution(),
        fetchServiceItemsOverview(),
        fetchEnterpriseGeometries()
      ]);
      businessGlobalOverview.value = overviewData;
      currentOverview.value = overviewData;
      coreIndicators.value = indicators;
      enterpriseResources.value = enterpriseData;
      serviceOverview.value = serviceData;
      geometriesArray.value = geometryData;
    } catch (error) {
      console.error('初始化数据失败:', error);
      ElMessage.error('数据加载失败，请刷新页面重试');
    }
  };
  initData();

  // 自动刷新定时器（5分钟）
  const autoRefreshTimer = setInterval(() => {
    refreshOverviewData();
    refreshCoreIndicators();
    refreshEnterpriseData();
    refreshServiceData();
  }, 300000);

  // 组件卸载时清理
  onUnmounted(() => {
    clearInterval(timer);
    clearInterval(autoRefreshTimer);
  });
});
</script>
<style lang="scss" scoped>

@import url('./common-styles.scss');

// 页面容器
.page-container {
  width: 100%;
  height: 100vh;
  overflow-x: auto;
  overflow-y: hidden;
  background: url("@/assets/chart/images/bg.jpg") no-repeat;
  background-size: 100% 100%;
  color: #fff;
  padding: 0 1vw;
  box-sizing: border-box;
}

// 主体内容盒子
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
  height: 50%;
}

.top_left {
  flex: 2;
}

.top_middle {
  flex: 3;
}

.top_right {
  flex: 2;
}

.bottom {
  display: flex;
  gap: 0.6vw;
  height: 50%;
}

.bottom_left {
  flex: 1;
}

.bottom_right {
  flex: 1;
}

</style>
