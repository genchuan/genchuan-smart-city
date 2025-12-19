<template>
  <div class="page-container">
    <div class="mainbox">
      <div class="top">
        <div class="top_left" style="min-width: 1vw;">
          <!-- 水务核心指标面板 -->
          <div class="panel core-indicators-panel" ref="coreIndicatorsPanel">
            <div class="panel-body">
              <div class="indicator-cards">
                <div
                  v-for="indicator in coreIndicators"
                  :key="indicator.indicator_id"
                  :class="['indicator-card', indicator.indicator_status === '1' ? 'warning' : 'normal']"
                  @click="showIndicatorDetail(indicator)"
                >
                  <!-- 指标名称 -->
                  <div class="indicator-title">{{ indicator.indicator_name }}</div>

                  <!-- 实时值（大号数字） -->
                  <div class="indicator-real-time">
                    {{ getRealTimeValue(indicator) }}
                    <span class="indicator-unit">{{ indicator.unit }}</span>
                  </div>

                  <!-- 细分数据（累计/其他值） -->
                  <div class="indicator-details">
                    <template v-if="indicator.indicator_code === 'water_supply'">
                      日累计: {{ indicator.daily_total }}{{ indicator.unit }}
                    </template>
                    <br/>
                    <template v-if="indicator.indicator_code === 'water_supply'">
                      月累计: {{ indicator.monthly_total }}{{ indicator.unit }}
                    </template>
                    <template v-if="indicator.indicator_code === 'water_quality_rate'">
                      日达标率: {{ indicator.daily_rate }}{{ indicator.unit }}
                    </template>
                    <br/>
                    <template v-if="indicator.indicator_code === 'water_quality_rate'">
                      月达标率: {{ indicator.monthly_rate }}{{ indicator.unit }}
                    </template>
                    <template v-if="indicator.indicator_code === 'water_leak_rate'">
                      日漏损率: {{ indicator.daily_leak_rate }}{{ indicator.unit }}
                    </template>
                    <template v-if="indicator.indicator_code === 'water_complain_resp_rate'">
                      未响应: {{ indicator.unresp_count }}件
                    </template>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="panel top_middle" style="min-width: 2vw;" ref="map">
          <div class="header-actions">
            <p>水务全域数据地图</p>
            <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('map')">
              <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
            </button>
          </div>
          <map-common idName="chinaEcharts" :geometriesArray="geometriesArray"/>
          <div class="panel-footer"></div>
        </div>
        <div class="top_right" style="min-width: 1vw;">
          <!-- 资源利用率趋势 -->
          <div class="panel resource-trend-panel" ref="resourceTrendPanel">
            <div class="panel-header">
              <h2>资源利用率趋势</h2>
              <div class="header-actions">
                <!-- 资源选择器 -->
                <el-select v-model="trendResourceId" placeholder="选择资源" size="small" style="width: 8vw;" @change="changeTrendResource">
                  <el-option
                    v-for="resource in coreResources"
                    :key="resource.resource_dist_id"
                    :label="resource.resource_name"
                    :value="resource.resource_dist_id"
                  />
                </el-select>
                <!-- 全屏按钮 -->
                <button class="panel-fullscreen-btn" @click="toggleFullscreen('resourceTrendPanel')">
                  <el-icon color="#00ccff" class="small-icon">
                    <FullScreen/>
                  </el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body">
              <div class="chart-container">
                <ChartLine
                  :data="resourceTrendData"
                  :yAxisName="'利用率(%)'"
                />
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
      <div class="bottom">
        <div class="bottom_left" style="min-width: 2vw;">
          <!-- 近期预警信息 -->
          <div class="panel warnings-panel" ref="warningsPanel">
            <div class="panel-header">
              <h2>近期预警信息</h2>
              <div class="header-actions">
                <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('warningsPanel')">
                  <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body">
              <!-- 自定义滚动列表 -->
              <div class="warning-scroll-container">
                <div class="warning-scroll-header">
                  <div class="warning-header-item">区域</div>
                  <div class="warning-header-item">预警类型</div>
                  <div class="warning-header-item">时间</div>
                  <div class="warning-header-item">状态</div>
                </div>
                <div class="warning-scroll-content" ref="warningScrollContent">
                  <div
                    class="warning-item"
                    v-for="(item, index) in warningScrollConfig.data"
                    :key="index"
                    @click="handleWarningClick(item)"
                  >
                    <div class="warning-item-col">{{ item[0] }}</div>
                    <div class="warning-item-col">{{ item[1] }}</div>
                    <div class="warning-item-col">{{ item[2] }}</div>
                    <div class="warning-item-col">
                      <el-tag :type="item[3] === '未处理' ? 'danger' : 'success'">
                        {{ item[3] }}
                      </el-tag>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="bottom_middle" style="min-width: 3vw;">
          <!-- 水务设施运行视图 -->
          <div class="panel facility-run-panel" ref="facilityRunPanel">
            <div class="panel-header">
              <h2>水务设施运行视图</h2>
              <div class="header-actions">
                <!-- 设施类型筛选 -->
                <el-select v-model="facilityTypeFilter" placeholder="设施类型" size="small">
                  <el-option label="全部" value="" />
                  <el-option label="泵站" value="泵站" />
                  <el-option label="水厂" value="水厂" />
                  <el-option label="管网" value="管网" />
                </el-select>
                <!-- 运行状态筛选 -->
                <el-select v-model="runStatusFilter" placeholder="运行状态" size="small">
                  <el-option label="全部" value="" />
                  <el-option label="正常" value="正常" />
                  <el-option label="异常" value="异常" />
                  <el-option label="维护" value="维护" />
                </el-select>
                <el-button size="small" type="primary" @click="exportFacilityData">导出数据</el-button>
                <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('facilityRunPanel')">
                  <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body">
              <!-- 设施运行状态表格 -->
              <el-table
                :data="filteredFacilities"
                border
                size="small"
                style="height: 98%"
              >
                <el-table-column prop="water_fac_id" label="设施ID" />
                <el-table-column prop="water_fac_name" label="设施名称" />
                <el-table-column prop="fac_type" label="类型" />
                <el-table-column prop="location" label="位置" />
                <el-table-column prop="online_status" label="在线状态">
                  <template #default="scope">
                    <el-tag :type="scope.row.online_status === '在线' ? 'success' : 'danger'">
                      {{ scope.row.online_status }}
                    </el-tag>
                  </template>
                </el-table-column>
                <el-table-column prop="run_status" label="运行状态">
                  <template #default="scope">
                    <el-tag
                      :type="scope.row.run_status === '正常' ? 'success' :
               scope.row.run_status === '异常' ? 'danger' : 'warning'"
                    >
                      {{ scope.row.run_status }}
                    </el-tag>
                  </template>
                </el-table-column>
                <el-table-column label="关键参数">
                  <template #default="scope">
                    <div>
                      <template v-if="scope.row.fac_type === '泵站'">
                        <span>流量: {{ scope.row.flow }} m³/h</span>
                        <div class="param-threshold">
                          (阈值: {{ scope.row.flow_thr_min }}-{{ scope.row.flow_thr_max }} m³/h)
                        </div>
                      </template>
                      <template v-if="scope.row.fac_type === '水厂'">
                        <span>压力: {{ scope.row.pressure }} MPa</span>
                        <div class="param-threshold">
                          (阈值: {{ scope.row.pressure_thr_min }}-{{ scope.row.pressure_thr_max }} MPa)
                        </div>
                      </template>
                      <template v-if="scope.row.fac_type === '管网'">
                        <span>流速: {{ scope.row.speed }} m/s</span>
                        <div class="param-threshold">
                          (阈值: {{ scope.row.speed_thr_min }}-{{ scope.row.speed_thr_max }} m/s)
                        </div>
                      </template>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column label="故障信息">
                  <template #default="scope">
                    <div v-if="scope.row.fault_type" class="fault-info">
                      <div>{{ scope.row.fault_type }}</div>
                      <div class="fault-time">{{ scope.row.fault_time }}</div>
                    </div>
                    <div v-else>-</div>
                  </template>
                </el-table-column>
                <el-table-column prop="feedback_time" label="反馈时间" width="120" />
                <el-table-column label="操作">
                  <template #default="scope">
                    <el-button
                      size="small"
                      type="text"
                      @click="handleFeedback(scope.row)"
                    >
                      {{ scope.row.run_status !== '正常' ? '反馈' : '详情' }}
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="bottom_right" style="min-width: 2vw;">
          <div class="panel core-resource-panel" ref="resourceDistributionPanel">
            <div class="panel-body">
              <!-- 资源类型卡片：水厂/泵站/水库/管网 -->
              <div class="resource-cards">
                <div
                  class="resource-card"
                  v-for="item in resourceTypeCards"
                  :key="item.type"
                  @click="handleCardClick(item.type)"
                  :class="{
                    active: activeResourceType === item.type,
                    'type-plant': item.type === '水厂',
                    'type-pump': item.type === '泵站',
                    'type-reservoir': item.type === '水库',
                    'type-pipe': item.type === '管网'
                  }"
                >
                  <h3>{{ item.name }}</h3>
                  <p class="card-total">{{ item.total }}</p>
                </div>
              </div>

              <!-- 柱状图：展示区域分布与状态 -->
              <div class="bar-chart-container">
                <ChartBar
                  :data="barChartData"
                  :xAxisName="'行政区划'"
                  :yAxisName="'数量'"
                  :height="280"
                  :title="barChartTitle"
                />
              </div>

              <!-- 资源统计数据：总数/正常/预警/告警 -->
              <div class="resource-stats">
                <div class="resource-stat-item">
                  <span class="stat-label">资源总数：</span>
                  <span class="stat-number">{{ totalResources }}</span>
                </div>
                <div class="resource-stat-item">
                  <span class="stat-label">正常：</span>
                  <span class="stat-number">{{ normalResources }}</span>
                </div>
                <div class="resource-stat-item">
                  <span class="stat-label">预警：</span>
                  <span class="stat-number">{{ yellowAlertResources }}</span>
                </div>
                <div class="resource-stat-item">
                  <span class="stat-label">告警：</span>
                  <span class="stat-number">{{ redAlertResources }}</span>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
      当前时间：{{currentTime}}
    </div>

    <!-- 设施详情弹窗 -->
    <el-dialog
      v-model="facilityDetailVisible"
      :title="currentFacility?.facility_name || '设施详情'"
      width="600px"
    >
      <div class="facility-detail">
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="设施ID">{{ currentFacility?.facility_id }}</el-descriptions-item>
            <el-descriptions-item label="设施类型">{{ currentFacility?.facility_type }}</el-descriptions-item>
            <el-descriptions-item label="位置">{{ currentFacility?.facility_pos }}</el-descriptions-item>
            <el-descriptions-item label="经纬度">
              {{ currentFacility?.longitude }}, {{ currentFacility?.latitude }}
            </el-descriptions-item>
            <el-descriptions-item label="扩展信息">
              {{ currentFacility?.ext1 }} | {{ currentFacility?.ext2 }}
            </el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>运行状态</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="运行状态">
              <el-tag :type="currentFacility?.run_status === '1' ? 'success' : 'info'">
                {{ currentFacility?.run_status === '1' ? '运行中' : '停运' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="预警状态">
              <el-tag :type="currentFacility?.warn_status === '1' ? 'danger' : 'success'">
                {{ currentFacility?.warn_status === '1' ? '已预警' : '无预警' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="预警原因" v-if="currentFacility?.warn_status === '1'">
              {{ getFacilityWarningReason(currentFacility) }}
            </el-descriptions-item>
            <el-descriptions-item label="确认状态">
              <el-tag :type="currentFacility?.confirm_status === '1' ? 'success' : 'warning'">
                {{ currentFacility?.confirm_status === '1' ? '已确认' : '未确认' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="确认信息" v-if="currentFacility?.confirm_status === '1'">
              确认人: {{ currentFacility?.confirm_user }}<br />
              确认时间: {{ currentFacility?.confirm_time }}
            </el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 预警确认区域（仅未确认的预警显示） -->
        <template v-if="currentFacility?.warn_status === '1' && currentFacility?.confirm_status === '0'">
          <div class="confirm-section">
            <h3>预警确认</h3>
            <el-form>
              <el-form-item label="确认备注" required>
                <el-input type="textarea" v-model="confirmRemark" rows="3" />
              </el-form-item>
            </el-form>
          </div>
        </template>
      </div>
      <template #footer>
        <el-button @click="facilityDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="confirmFacilityWarning"
          v-if="currentFacility?.warn_status === '1' && currentFacility?.confirm_status === '0'"
        >
          确认预警
        </el-button>
      </template>
    </el-dialog>

    <!-- 核心指标详情弹窗 -->
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
            <el-descriptions-item label="统计周期">{{ currentIndicator?.stat_cycle }}</el-descriptions-item>
            <el-descriptions-item label="当前值">
              <span class="current-value">{{ currentIndicator?.current_value }}{{ currentIndicator?.unit }}</span>
            </el-descriptions-item>
            <el-descriptions-item label="阈值范围">
              {{ currentIndicator?.threshold_min }} - {{ currentIndicator?.threshold_max }}{{ currentIndicator?.unit }}
            </el-descriptions-item>
            <el-descriptions-item label="指标状态">
              <el-tag :type="currentIndicator?.indicator_status === '1' ? 'danger' : 'success'">
                {{ currentIndicator?.indicator_status === '1' ? '预警' : '正常' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="统计时间">{{ currentIndicator?.stat_time }}</el-descriptions-item>
            <el-descriptions-item label="数据来源">{{ currentIndicator?.ext2 }}</el-descriptions-item>
            <el-descriptions-item label="计算方式">{{ currentIndicator?.ext1 }}</el-descriptions-item>
          </el-descriptions>
        </div>
        <div class="detail-section">
          <h3>近7天趋势</h3>
          <div class="chart-container" style="height: 300px;">
            <ChartLine2
              :xAxis="indicatorTrendData.xAxis"
              :series="indicatorTrendData.series"
              :yAxisName="`${currentIndicator?.unit || ''}`"
            />
          </div>
        </div>
      </div>
      <template #footer>
        <el-button @click="indicatorDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="modifyIndicatorThreshold"
        >
          修改阈值
        </el-button>
      </template>
    </el-dialog>

    <!-- 运维反馈弹窗 -->
    <el-dialog
      v-model="feedbackVisible"
      title="运维反馈"
      width="500px"
    >
      <el-form :model="feedbackForm" ref="feedbackFormRef" label-width="80px">
        <el-form-item label="设施名称" disabled>
          <el-input v-model="feedbackForm.facilityName" />
        </el-form-item>
        <el-form-item label="预警参数" disabled>
          <el-input v-model="feedbackForm.warningParam" />
        </el-form-item>
        <el-form-item label="处理状态" required>
          <el-select v-model="feedbackForm.status">
            <el-option label="处理中" value="处理中" />
            <el-option label="已解决" value="已解决" />
            <el-option label="需维修" value="需维修" />
          </el-select>
        </el-form-item>
        <el-form-item label="反馈内容" required>
          <el-input type="textarea" v-model="feedbackForm.content" rows="4" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="feedbackVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmitFeedback">提交反馈</el-button>
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
            <el-descriptions-item label="资源ID">{{ currentResource?.resource_id }}</el-descriptions-item>
            <el-descriptions-item label="资源类型">{{ currentResource?.resource_type }}</el-descriptions-item>
            <el-descriptions-item label="所在区域">{{ currentResource?.area }}</el-descriptions-item>
            <el-descriptions-item label="总容量">{{ currentResource?.total_capacity }}万{{ currentResource?.capacity_unit || 't/d' }}</el-descriptions-item>
            <el-descriptions-item label="管理单位">{{ currentResource?.ext1 || '未知' }}</el-descriptions-item>
            <el-descriptions-item label="投用时间">{{ currentResource?.ext2 || '未知' }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>运行状态</h3>
          <el-descriptions column="1" border>
            <el-descriptions-item label="当前容量">{{ currentResource?.current_capacity }}万{{ currentResource?.capacity_unit || 't/d' }}</el-descriptions-item>
            <el-descriptions-item label="利用率">{{ currentResource?.usage_rate }}%</el-descriptions-item>
            <el-descriptions-item label="提醒状态">
              <el-tag
                :type="currentResource?.remind_status === '0' ? 'success' :
                currentResource?.remind_status === '1' ? 'warning' : 'danger'"
              >
                {{ currentResource?.remind_status === '0' ? '正常' :
                currentResource?.remind_status === '1' ? '预警' : '告警' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="最新更新时间">{{ currentResource?.latest_update_time }}</el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <template #footer>
        <el-button @click="resourceDetailVisible = false">关闭</el-button>
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
        <div class="warning-icon">警告</div>
        <div class="warning-content">
          <p><strong>{{ newWarning?.name }}</strong> 出现异常</p>
          <p>{{ newWarning?.warning_reason }}</p>
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
// Element Plus组件
import {
  ElSelect, ElOption, ElButton, ElTable, ElTableColumn,
  ElTag, ElDialog, ElDescriptions, ElDescriptionsItem,
  ElForm, ElFormItem, ElInput, ElMessage
} from 'element-plus';
import MapCommon from './MapCommon.vue';
import screenFull from 'screenfull';
import { FullScreen } from "@element-plus/icons-vue";
import ChartLine from './ChartLine.vue';
import ChartLine2 from './ChartLine2.vue';
import ChartBar from './ChartBar.vue';

// API接口
import {
  fetchGlobalDataMap,
  fetchCoreResources,
  fetchCoreIndicators,
  fetchIndicatorTrend,
  fetchFacilityRunStatus,
  fetchWarningScrollData,
  confirmWarning,
  submitFeedback,
  fetchResourceTrend
} from '@/api/overview/waterresourceswateraffairs/GlobalSituationOverview.js';

const geometriesArray = ref([]);

// 基础变量
// 当前组件实例
const instance = getCurrentInstance();

// 当前时间
const currentTime = ref('');

// 面板单独全屏切换
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

  // 切换指定面板的全屏状态
  if (screenFull.isFullscreen && document.fullscreenElement === panel) {
    screenFull.exit();
  } else {
    screenFull.request(panel);
  }
};

// 根据指标类型返回实时值字段
const getRealTimeValue = (indicator) => {
  switch (indicator.indicator_code) {
    case 'water_supply': return indicator.real_time_value;
    case 'water_quality_rate': return indicator.real_time_rate;
    case 'water_leak_rate': return indicator.real_time_leak_rate;
    case 'water_complain_resp_rate': return indicator.real_time_resp_rate;
    default: return 0;
  }
};

// 预警面板全屏切换
const togglePanelFullscreen = (panelRefName) => {
  toggleFullscreen(panelRefName);
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

const mapFacilities = ref([]);

// 设施详情弹窗控制
const facilityDetailVisible = ref(false);
const currentFacility = ref(null);
const confirmRemark = ref('');

// 显示设施详情
const showFacilityDetail = (facility) => {
  currentFacility.value = { ...facility };
  confirmRemark.value = '';
  facilityDetailVisible.value = true;
};

// 确认设施预警
const confirmFacilityWarning = async () => {
  if (!currentFacility.value) return;

  try {
    // 调用API确认预警
    await confirmWarning({
      mapDataId: currentFacility.value.map_data_id,
      remark: confirmRemark.value,
      confirmUser: 'admin'
    });

    // 更新本地数据
    const index = mapFacilities.value.findIndex(f => f.map_data_id === currentFacility.value.map_data_id);
    if (index !== -1) {
      mapFacilities.value[index].confirm_status = '1';
      mapFacilities.value[index].confirm_user = '管理员';
      mapFacilities.value[index].confirm_time = formatTime(new Date());
    }

    // 更新当前弹窗数据
    currentFacility.value.confirm_status = '1';
    currentFacility.value.confirm_user = '管理员';
    currentFacility.value.confirm_time = formatTime(new Date());

    ElMessage.success('预警确认成功');
    facilityDetailVisible.value = false;
  } catch (error) {
    ElMessage.error('预警确认失败: ' + (error.message || '未知错误'));
  }
};

// 获取设施预警原因
const getFacilityWarningReason = (facility) => {
  const reasons = [];
  if (facility.pressure !== undefined && facility.pressure < 0.2) {
    reasons.push(`管网压力 ${facility.pressure}MPa < 0.2MPa`);
  }
  if (facility.turbidity !== undefined && facility.turbidity > 5) {
    reasons.push(`水质浊度 ${facility.turbidity}NTU > 5NTU`);
  }
  return reasons.length > 0 ? reasons.join('; ') : '未知原因';
};

// 核心资源分布相关
const coreResources = ref([]);
// 资源类型卡片配置
const resourceTypeCards = ref([
  { type: '水厂', name: '供水厂资源', total: 0 },
  { type: '泵站', name: '泵站资源', total: 0 },
  { type: '水库', name: '水库资源', total: 0 },
  { type: '管网', name: '管网资源', total: 0 }
]);
// 激活的资源类型
const activeResourceType = ref('水厂');

// 计算资源类型卡片总量
const calculateResourceTotals = () => {
  resourceTypeCards.value.forEach(card => {
    card.total = coreResources.value.filter(res => res.resource_type === card.type).length;
  });
};

// 柱状图标题（根据激活的资源类型动态生成）
const barChartTitle = computed(() => {
  const typeName = resourceTypeCards.value.find(card => card.type === activeResourceType.value)?.name;
  return `${typeName}区域分布与状态占比`;
});

// 柱状图数据（按区域和状态统计）
const barChartData = computed(() => {
  const filtered = coreResources.value.filter(res => res.resource_type === activeResourceType.value);
  const areas = [...new Set(filtered.map(res => res.area))]; // 去重区域

  // 按状态分组统计
  const normalData = areas.map(area => filtered.filter(res => res.area === area && res.remind_status === '0').length);
  const warningData = areas.map(area => filtered.filter(res => res.area === area && res.remind_status === '1').length);
  const alertData = areas.map(area => filtered.filter(res => res.area === area && res.remind_status === '2').length);

  return {
    xAxis: areas,
    series: [
      { name: '正常', data: normalData },
      { name: '预警', data: warningData },
      { name: '告警', data: alertData }
    ]
  };
});

// 切换资源类型卡片
const handleCardClick = (type) => {
  activeResourceType.value = type;
};

// 资源统计数据
const totalResources = computed(() => coreResources.value.length);
const normalResources = computed(() => coreResources.value.filter(r => r.remind_status === '0').length);
const yellowAlertResources = computed(() => coreResources.value.filter(r => r.remind_status === '1').length);
const redAlertResources = computed(() => coreResources.value.filter(r => r.remind_status === '2').length);

// 资源详情弹窗
const resourceDetailVisible = ref(false);
const currentResource = ref(null);
const currentResourceTrendData = ref({ xAxis: [], series: [] });

// 显示资源详情
const showResourceDetail = (resource) => {
  currentResource.value = { ...resource };
  resourceDetailVisible.value = true;
};

// 资源利用率趋势相关
const trendResourceId = ref('');
// 趋势图数据
const resourceTrendData = ref({ xAxis: [], series: [] });

// 切换趋势图资源
const changeTrendResource = async (resourceId) => {
  if (!resourceId) return;

  try {
    // 调用API获取趋势数据
    const trendData = await fetchResourceTrend(resourceId);
    resourceTrendData.value = trendData;
  } catch (error) {
    ElMessage.error('获取趋势数据失败: ' + (error.message || '未知错误'));
  }
};

// 核心指标数据
const coreIndicators = ref([]);

// 指标时间范围
const indicatorTimeRange = ref('today');

// 指标详情弹窗
const indicatorDetailVisible = ref(false);
const currentIndicator = ref(null);
// 存储指标趋势数据的变量
const indicatorTrendData = ref({
  xAxis: [],    // 日期轴（如：["10/22", "10/23", ...]）
  series: [],   // 数据系列（实际值 + 目标值）
  unit: ''      // 指标单位（用于Y轴标注）
});

// 获取指标近7天趋势数据的方法
const getIndicatorTrend = async (indicatorId) => {
  try {
    const data = await fetchIndicatorTrend(indicatorId);
    indicatorTrendData.value = data; // 赋值趋势数据（包含xAxis、series、unit）
  } catch (error) {
    console.error('获取指标趋势数据失败:', error);
    ElMessage.error('加载趋势数据失败');
  }
};

// 显示指标详情
const showIndicatorDetail = (indicator) => {
  currentIndicator.value = { ...indicator };
  indicatorDetailVisible.value = true;
  getIndicatorTrend(indicator.indicator_id);
};

// 修改指标阈值（待实现）
const modifyIndicatorThreshold = () => {
  ElMessage.info('阈值修改功能待实现');
};

// 设施运行原始数据
const facilityRunData = ref([]);
// 设施筛选条件
const facilityTypeFilter = ref('');
const runStatusFilter = ref('');

// 筛选后的设施数据
const filteredFacilities = computed(() => {
  return facilityRunData.value.filter(facility => {
    const matchesType = !facilityTypeFilter.value || facility.fac_type === facilityTypeFilter.value;
    const matchesStatus = !runStatusFilter.value || facility.run_status === runStatusFilter.value;
    return matchesType && matchesStatus;
  });
});

// 运维反馈弹窗
const feedbackVisible = ref(false);
const currentFeedbackFacility = ref(null);
const feedbackForm = ref({
  facilityName: '',
  warningParam: '',
  status: '处理中',
  content: ''
});
const feedbackFormRef = ref(null);

// 打开运维反馈弹窗
const handleFeedback = (facility) => {
  currentFeedbackFacility.value = { ...facility };

  // 填充表单数据
  feedbackForm.value.facilityName = facility.water_fac_name;

  // 确定预警参数
  let warningParam = '';
  if (facility.fac_type === '泵站') {
    warningParam = `流量: ${facility.flow}m³/h (阈值: ${facility.flow_thr_min}-${facility.flow_thr_max}m³/h)`;
  } else if (facility.fac_type === '水厂') {
    warningParam = `压力: ${facility.pressure}MPa (阈值: ${facility.pressure_thr_min}-${facility.pressure_thr_max}MPa)`;
  } else if (facility.fac_type === '管网') {
    warningParam = `流速: ${facility.speed}m/s (阈值: ${facility.speed_thr_min}-${facility.speed_thr_max}m/s)`;
  }
  feedbackForm.value.warningParam = warningParam;

  feedbackForm.value.status = '处理中';
  feedbackForm.value.content = '';

  feedbackVisible.value = true;
};

// 提交运维反馈
const handleSubmitFeedback = async () => {
  if (!currentFeedbackFacility.value) return;

  try {
    // 调用API提交反馈
    await submitFeedback({
      runViewId: currentFeedbackFacility.value.run_view_id,
      ...feedbackForm.value,
      maintainUserId: 'maintain01'
    });

    // 更新本地数据
    const index = facilityRunData.value.findIndex(f => f.run_view_id === currentFeedbackFacility.value.run_view_id);
    if (index !== -1) {
      facilityRunData.value[index].feedback_content = feedbackForm.value.content;
      facilityRunData.value[index].feedback_time = formatTime(new Date());

      // 若标记为已解决，更新运行状态
      if (feedbackForm.value.status === '已解决') {
        facilityRunData.value[index].run_status = '0';
      }
    }

    ElMessage.success('反馈提交成功');
    feedbackVisible.value = false;
  } catch (error) {
    ElMessage.error('提交反馈失败: ' + (error.message || '未知错误'));
  }
};

// 导出设施数据
const exportFacilityData = () => {
  console.log('导出设施运行数据', filteredFacilities.value);
  ElMessage.success('设施运行数据导出成功');
};

// 预警信息相关功能
// 预警信息配置
const warningScrollConfig = ref({
  header: ['区域', '预警类型', '时间', '状态'],
  data: [],  // 预警数据列表
  rowNum: 5  // 显示行数
});

// 预警滚动相关
const warningScrollContent = ref(null);
let scrollInterval = null;

// 启动预警滚动
const startWarningScroll = () => {
  // 清除已有定时器
  if (scrollInterval) clearInterval(scrollInterval);

  // 只有数据足够多时才滚动
  if (warningScrollConfig.value.data.length <= warningScrollConfig.value.rowNum) return;

  const contentEl = warningScrollContent.value;
  if (!contentEl) return;

  // 设置滚动定时器（每3秒滚动一行）
  scrollInterval = setInterval(() => {
    // 滚动一行高度（36px）
    contentEl.scrollBy({ top: 36, behavior: 'smooth' });

    // 滚动到末尾时重置
    if (contentEl.scrollTop + contentEl.clientHeight >= contentEl.scrollHeight - 10) {
      setTimeout(() => {
        contentEl.scrollTop = 0;
      }, 500);
    }
  }, 3000);
};

// 处理预警点击（查看详情）
const handleWarningClick = (item) => {
  // 查找对应的地图项并显示详情
  const targetItem = mapFacilities.value.find(
    mapItem => mapItem.area === item[0] && mapItem.facility_name.includes(item[1])
  );

  if (targetItem) {
    showFacilityDetail(targetItem);
  } else {
    ElMessage.info('未找到相关数据');
  }
};

// 新预警通知
const newWarningVisible = ref(false);
const newWarning = ref(null);

// 模拟新预警推送（随机从异常数据中选取）
const simulateNewWarning = () => {
  const warningItems = mapFacilities.value.filter(item => {
    return item.warn_status === '1' && item.confirm_status === '0';
  });

  if (warningItems.length === 0) return;

  const randomIndex = Math.floor(Math.random() * warningItems.length);
  const item = warningItems[randomIndex];

  let warningReason = getFacilityWarningReason(item);

  newWarning.value = {
    name: item.facility_name,
    warning_reason: warningReason,
    time: formatTime(new Date())
  };

  newWarningVisible.value = true;
};

// 忽略预警
const ignoreWarning = () => {
  newWarningVisible.value = false;
};

// 处理新预警（查看详情）
const handleNewWarning = () => {
  if (newWarning.value) {
    const item = mapFacilities.value.find(i => i.facility_name === newWarning.value.name);
    if (item) {
      showFacilityDetail(item);
    }
  }
  newWarningVisible.value = false;
};

// 生命周期钩子
onMounted(() => {
  // 初始化时间
  currentTime.value = formatTime(new Date());
  const timer = setInterval(() => {
    currentTime.value = formatTime(new Date());
  }, 1000);

  // 初始化数据
  const initData = async () => {
    try {
      // 并行请求所有初始化数据
      const [mapData, resources, indicators, facilityData, warningScrollData] = await Promise.all([
        fetchGlobalDataMap(),
        fetchCoreResources(),
        fetchCoreIndicators('today'),
        fetchFacilityRunStatus(),
        fetchWarningScrollData()
      ]);

      // 赋值数据
      geometriesArray.value = mapData;
      coreResources.value = resources;
      coreIndicators.value = indicators;
      facilityRunData.value = facilityData;
      warningScrollConfig.value.data = warningScrollData;

      // 初始化资源统计
      calculateResourceTotals();

      // 初始化资源趋势图
      if (coreResources.value.length > 0) {
        trendResourceId.value = coreResources.value[0].resource_dist_id;
        // 直接调用API，无需手动生成数据
        changeTrendResource(trendResourceId.value);
      }

      // 启动预警滚动
      startWarningScroll();
    } catch (error) {
      console.error('初始化数据失败:', error);
      ElMessage.error('数据加载失败，请刷新页面重试');
    }
  };

  // 初始化数据
  initData();

  // 定时刷新预警信息（1分钟一次）
  const warningDataTimer = setInterval(() => {
    fetchWarningScrollData().then(data => {
      warningScrollConfig.value.data = data;
      // 刷新后重启滚动
      startWarningScroll();
    }).catch(err => {
      console.warn('刷新预警信息失败:', err);
    });
  }, 60000);

  // 定时模拟新预警推送（30秒一次，30%概率）
  const warningTimer = setInterval(() => {
    if (Math.random() < 0.3) {
      simulateNewWarning();
    }
  }, 30000);

  // 清理定时器
  onUnmounted(() => {
    clearInterval(timer);
    clearInterval(warningTimer);
    clearInterval(warningDataTimer);
    if (scrollInterval) clearInterval(scrollInterval);
  });
});
</script>
<style lang="scss" scoped>

@import url('./common-styles.scss');

// 页面容器
.page-container {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  background: url("@/assets/chart/images/bg.jpg");
  background-size: 100% 100%;
  color: #fff;
  padding: 0 20px;
  box-sizing: border-box;
}

// 主体内容盒子
.mainbox {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 0.6vw 0;
  height: 89vh;
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
  height: 46%;
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
