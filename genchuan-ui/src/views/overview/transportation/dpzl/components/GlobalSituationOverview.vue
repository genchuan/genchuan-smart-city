<template>
  <div class="page-container">
    <div class="mainbox">
      <div class="left" style="min-width: 29vw;">
        <!-- 交通资源分布 -->
        <div class="panel distribution-panel" ref="distributionPanel">
          <div class="panel-header">
            <h2>交通资源分布视图</h2>
            <div class="header-actions">
              <el-select v-model="areaFilter" placeholder="区域" size="small" popper-append-to-body="false">
                <el-option label="全部" value="" />
                <el-option label="东山区" value="东山区" />
                <el-option label="西城区" value="西城区" />
                <el-option label="南城区" value="南城区" />
                <el-option label="北城区" value="北城区" />
              </el-select>
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('distributionPanel')">
                <el-icon color="#00ccff" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <div class="resource-stats">
              <div class="resource-stat-item">
                <div class="stat-number">{{ totalResources }}</div>
                <div class="stat-label">资源总数</div>
              </div>
              <div class="resource-stat-item">
                <div class="stat-number">{{ normalResources }}</div>
                <div class="stat-label">正常资源</div>
              </div>
              <div class="resource-stat-item">
                <div class="stat-number">{{ abnormalResources }}</div>
                <div class="stat-label">异常资源</div>
              </div>
            </div>

            <!-- 资源类型占比饼图 -->
            <div class="resource-pie-chart">
              <ChartPie
                :data="resourcePieData"
                :activeIndex="activePieIndex"
                @click="handlePieClick"
              />
            </div>

            <el-table
              :data="filteredResources"
              border
              size="small"
            >
              <el-table-column prop="area_name" label="区域" />
              <el-table-column label="资源类型">
                <template #default="scope">
                  {{ scope.row.resource_type === '1' ? '道路' :
                  scope.row.resource_type === '2' ? '公交站' : '枢纽' }}
                </template>
              </el-table-column>
              <el-table-column prop="total_count" label="资源总数" />
              <el-table-column prop="abnormal_count" label="异常数" />
              <el-table-column label="异常率">
                <template #default="scope">
                  <div>
                    <el-progress
                      :percentage="Number((scope.row.abnormal_count / scope.row.total_count * 100).toFixed(1))"
                      :stroke-width="6"
                      :stroke-color="getAbnormalRateColor(scope.row.abnormal_count / scope.row.total_count)"
                      :show-text="false"
                    />
                  </div>
                </template>
              </el-table-column>
              <el-table-column label="操作">
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

            <div class="table-actions">
              <el-button size="small" type="primary" @click="exportResourceData">导出数据</el-button>
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>
        当前时间：{{currentTime}}
      </div>
      <div class="middle" style="min-width: 39vw;">
        <div class="panel middle_top" ref="map">
          <div class="header-actions">
            <p>交通全域数据地图</p>
            <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('map')">
              <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
            </button>
          </div>
          <map-common idName="chinaEcharts" :geometriesArray="geometriesArray"/>
          <div class="panel-footer"></div>
        </div>
        <div class="middle_bottom">
          <!-- 交通流量总览 -->
          <div class="panel traffic-flow-panel" ref="trafficFlowPanel" style="height: 100%">
            <div class="panel-header">
              <h2>交通流量总览</h2>
              <div class="header-actions">
                <el-select v-model="flowObjTypeFilter" placeholder="对象类型" size="small" style="width: 6vw;" popper-append-to-body="false">
                  <el-option label="全部" value="" />
                  <el-option label="路段" value="1" />
                  <el-option label="枢纽" value="2" />
                </el-select>
                <el-select v-model="flowStatusFilter" placeholder="流量状态" size="small" style="width: 6vw;" popper-append-to-body="false">
                  <el-option label="全部" value="" />
                  <el-option label="正常" value="0" />
                  <el-option label="接近饱和" value="1" />
                  <el-option label="超饱和" value="2" />
                </el-select>
                <el-button size="small" type="primary" @click="toggleView">
                  {{ currentView === 'flowTable' ? '显示路段流量TOP10' : '显示交通流量列表' }}
                </el-button>
                <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('trafficFlowPanel')">
                  <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body">
              <el-table
                v-if="currentView === 'flowTable'"
                :data="filteredTrafficFlow"
                border
                size="small"
              >
                <el-table-column prop="monitor_obj_name" label="监测对象" />
                <el-table-column label="对象类型">
                  <template #default="scope">
                    {{ scope.row.monitor_obj_type === '1' ? '路段' : '枢纽' }}
                  </template>
                </el-table-column>
                <el-table-column label="流量">
                  <template #default="scope">
                    <div>
                      <span>
                        {{ scope.row.vehicle_flow !== null ? scope.row.vehicle_flow + '辆/小时' :
                        scope.row.people_flow + '人/小时' }}
                      </span>
                      <div class="param-threshold">
                        (饱和值: {{ scope.row.saturation_value }}{{ scope.row.vehicle_flow !== null ? '辆/小时' : '人/小时' }})
                      </div>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="monitor_period" label="监测时段" />
                <el-table-column prop="flow_status" label="状态">
                  <template #default="scope">
                    <el-tag
                      :type="scope.row.flow_status === '0' ? 'success' :
                             scope.row.flow_status === '1' ? 'warning' : 'danger'">
                      {{ scope.row.flow_status === '0' ? '正常' :
                      scope.row.flow_status === '1' ? '接近饱和' : '超饱和' }}
                    </el-tag>
                  </template>
                </el-table-column>
                <el-table-column prop="monitor_time" label="监测时间" />
                <el-table-column label="操作">
                  <template #default="scope">
                    <el-button
                      size="small"
                      type="text"
                      @click="showFlowDetail(scope.row)"
                    >
                      详情
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>

              <div v-else class="top10-container">
                <div class="top10-list">
                  <div
                    v-for="item in top10Roads"
                    :key="item.flow_id"
                    class="top10-item"
                    @click="showFlowDetail(item)"
                  >
                    <!-- 排名 -->
                    <div class="top10-rank" :class="getRankClass(item.rank)">
                      {{ item.rank }}
                    </div>
                    <!-- 路段信息 -->
                    <div class="top10-info">
                      <div class="top10-name">{{ item.monitor_obj_name }}</div>
                      <div class="top10-meta">
                        <span>当前流量：{{ item.vehicle_flow }} 辆/小时</span>
                        <span>容量：{{ item.saturation_value }} 辆/小时</span>
                        <span :class="getOverRateClass(item.overRate)">
                          超容率：{{ item.overRate.toFixed(1) }}%
                        </span>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
      <div class="right" style="min-width: 29vw;">
        <div class="panel right_top">
          <!-- 核心指标卡片 -->
          <div class="panel-body">
            <div class="indicator-cards">
              <!-- 通行效率卡片（蓝色渐变） -->
              <div
                v-if="coreIndicators[0]"
                class="indicator-card"
                :class="['card-traffic', getGroupStatusClass(coreIndicators[0])]"
                @click="jumpToTrafficEfficiency()"
                :key="0"
              >
                <div class="indicator-title">{{ coreIndicators[0].name }}</div>
                <div class="sub-indicators">
                  <div v-for="sub in coreIndicators[0].indicators" :key="sub.code" class="sub-indicator-item">
                    <div class="sub-indicator-value">
                      {{ sub.name }}
                      <br/>
                      {{ sub.value }} {{ sub.unit }}
                    </div>
                  </div>
                </div>
              </div>

              <!-- 事故预警卡片（橙色渐变） -->
              <div
                v-if="coreIndicators[1]"
                class="indicator-card"
                :class="['card-accident', getGroupStatusClass(coreIndicators[1])]"
                @click="jumpToAccidentWarning()"
                :key="1"
              >
                <div class="indicator-title">{{ coreIndicators[1].name }}</div>
                <div class="sub-indicators">
                  <div v-for="sub in coreIndicators[1].indicators" :key="sub.code" class="sub-indicator-item">
                    <div class="sub-indicator-value">
                      {{ sub.name }}
                      <br/>
                      {{ sub.value }} {{ sub.unit }}
                    </div>
                  </div>
                </div>
              </div>

              <!-- 服务质量卡片（紫色渐变） -->
              <div
                v-if="coreIndicators[2]"
                class="indicator-card"
                :class="['card-service', getGroupStatusClass(coreIndicators[2])]"
                @click="jumpToServiceQuality()"
                :key="2"
              >
                <div class="indicator-title">{{ coreIndicators[2].name }}</div>
                <div class="sub-indicators">
                  <div v-for="sub in coreIndicators[2].indicators" :key="sub.code" class="sub-indicator-item">
                    <div class="sub-indicator-value">
                      {{ sub.name }}
                      <br/>
                      {{ sub.value }} {{ sub.unit }}
                    </div>
                  </div>
                </div>
              </div>

              <!-- 应急处置卡片（红色渐变） -->
              <div
                v-if="coreIndicators[3]"
                class="indicator-card"
                :class="['card-disposal', getGroupStatusClass(coreIndicators[3])]"
                @click="jumpToDisposalEfficiency()"
                :key="3"
              >
                <div class="indicator-title">{{ coreIndicators[3].name }}</div>
                <div class="sub-indicators">
                  <div v-for="sub in coreIndicators[3].indicators" :key="sub.code" class="sub-indicator-item">
                    <div class="sub-indicator-value">
                      {{ sub.name }}
                      <br/>
                      {{ sub.value }} {{ sub.unit }}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="panel-footer"></div>
        </div>
        <div class="panel right_middle">
          <!-- 指标趋势图 -->
          <div ref="indicatorTrendPanel" style="height: 100%">
            <div class="panel-header">
              <h2>核心指标趋势</h2>
              <div class="header-actions">
                <el-select v-model="trendIndicatorId" placeholder="选择指标组" style="width: 8vw" size="small" @change="changeTrendIndicator" popper-append-to-body="false">
                  <el-option
                    v-for="indicator in coreIndicators"
                    :key="indicator.type"
                    :label="indicator.name"
                    :value="indicator.type"
                  />
                </el-select>
              </div>
            </div>
            <div class="panel-body">
              <div class="chart-container">
                <ChartBar
                  :key="trendKey"
                  :data="indicatorTrendData"
                  :yAxisName="'数值'"
                />
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="right_bottom">
          <!-- 近期异常信息 -->
          <div class="panel" ref="warningsPanel" style="height: 100%">
            <div class="panel-header">
              <h2>近期异常信息</h2>
              <div class="header-actions">
                <button class="panel-fullscreen-btn" @click="togglePanelFullscreen('warningsPanel')">
                  <el-icon color="#00ccff" size="16"><FullScreen/></el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body">
              <dv-scroll-board
                :config="warningScrollConfig"
                style="width: 100%; height: 100%;"
              />
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
    </div>

    <!-- 核心指标详情弹窗（修改为指标组弹窗） -->
    <el-dialog
      v-model="indicatorDetailVisible"
      :title="currentIndicatorGroup?.name || '指标详情'"
      width="800px"
    >
      <div class="indicator-detail">
        <div class="detail-section">
          <h3>指标信息</h3>
          <el-descriptions column=1 border>
            <template v-for="sub in currentIndicatorGroup.indicators" :key="sub.code">
              <el-descriptions-item :label="sub.name">
                <div class="sub-indicator-detail">
                  <!-- 原有：当前值 + 目标值 + 状态 -->
                  <span class="current-value">{{ sub.value }}{{ sub.unit }}</span>
                  <span class="target-value">目标: {{ sub.target }}{{ sub.unit }}</span>
                  <!-- 新增：同比变化 -->
                  <span
                    class="year-on-year"
                    :class="sub.yearOnYear > 0 ? 'increase' : 'decrease'"
                  >
                {{ sub.yearOnYear > 0 ? '同比 ↑' : '同比 ↓' }}{{ Math.abs(sub.yearOnYear).toFixed(1) }}%
              </span>
                  <el-tag :type="sub.status === '1' ? 'danger' : 'success'" class="status-tag">
                    {{ sub.status === '1' ? '异常' : '正常' }}
                  </el-tag>
                </div>
              </el-descriptions-item>
            </template>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>30天趋势</h3>
          <div class="chart-container">
            <!-- 支持切换子指标趋势图 -->
            <el-select
              v-model="selectedSubIndicator"
              placeholder="选择子指标"
              size="small"
              style="width: 8vw"
              @change="loadSubIndicatorTrend"
            >
              <el-option
                v-for="sub in currentIndicatorGroup.indicators"
                :key="sub.code"
                :label="sub.name"
                :value="sub.code"
              />
            </el-select>
            <ChartLine
              :data="indicatorHistoryData"
              :yAxisName="getYAxisName()"
            />
          </div>
        </div>
      </div>
      <template #footer>
        <el-button @click="indicatorDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="modifyIndicatorTarget"
        >
          修改目标值
        </el-button>
      </template>
    </el-dialog>

    <!-- 流量详情弹窗 -->
    <el-dialog
      v-model="flowDetailVisible"
      :title="currentFlow?.monitor_obj_name || '流量详情'"
      width="600px"
    >
      <div class="flow-detail">
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column=1 border>
            <el-descriptions-item label="对象ID">{{ currentFlow?.monitor_obj_id }}</el-descriptions-item>
            <el-descriptions-item label="对象类型">
              {{ currentFlow?.monitor_obj_type === '1' ? '路段' : '枢纽' }}
            </el-descriptions-item>
            <el-descriptions-item label="监测时段">{{ currentFlow?.monitor_period }}</el-descriptions-item>
            <el-descriptions-item label="传感器编号">{{ currentFlow?.ext1 }}</el-descriptions-item>
            <el-descriptions-item label="流量趋势">{{ currentFlow?.ext2 }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>流量数据</h3>
          <el-descriptions column=1 border>
            <el-descriptions-item label="流量值">
              {{ currentFlow?.vehicle_flow !== null ?
              currentFlow?.vehicle_flow + '辆/小时' :
              currentFlow?.people_flow + '人/小时' }}
            </el-descriptions-item>
            <el-descriptions-item label="饱和值">
              {{ currentFlow?.saturation_value }}{{ currentFlow?.vehicle_flow !== null ? '辆/小时' : '人/小时' }}
            </el-descriptions-item>
            <el-descriptions-item label="流量状态">
              <el-tag
                :type="currentFlow?.flow_status === '0' ? 'success' :
                       currentFlow?.flow_status === '1' ? 'warning' : 'danger'">
                {{ currentFlow?.flow_status === '0' ? '正常' :
                currentFlow?.flow_status === '1' ? '接近饱和' : '超饱和' }}
              </el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="监测时间">{{ currentFlow?.monitor_time }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section">
          <h3>24小时流量趋势</h3>
          <div class="chart-container">
            <ChartLine
              :data="flowTrendData"
              :yAxisName="currentFlow?.vehicle_flow !== null ? '辆/小时' : '人/小时'"
            />
          </div>
        </div>
      </div>
      <template #footer>
        <el-button @click="flowDetailVisible = false">关闭</el-button>
      </template>
    </el-dialog>

    <!-- 资源分布详情弹窗 -->
    <el-dialog
      v-model="resourceDetailVisible"
      :title="currentResource?.area_name + '-' + getResourceTypeName(currentResource?.resource_type) + '详情'"
      width="600px"
    >
      <div class="resource-dist-detail">
        <div class="detail-section">
          <h3>统计信息</h3>
          <el-descriptions column=1 border>
            <el-descriptions-item label="区域ID">{{ currentResource?.area_id }}</el-descriptions-item>
            <el-descriptions-item label="区域名称">{{ currentResource?.area_name }}</el-descriptions-item>
            <el-descriptions-item label="资源类型">
              {{ getResourceTypeName(currentResource?.resource_type) }}
            </el-descriptions-item>
            <el-descriptions-item label="资源总数">{{ currentResource?.total_count }}</el-descriptions-item>
            <el-descriptions-item label="正常资源数">{{ currentResource?.normal_count }}</el-descriptions-item>
            <el-descriptions-item label="异常资源数">{{ currentResource?.abnormal_count }}</el-descriptions-item>
            <el-descriptions-item label="异常率">
              {{ (currentResource?.abnormal_count / currentResource?.total_count * 100).toFixed(1) }}%
            </el-descriptions-item>
            <el-descriptions-item label="统计时间">{{ currentResource?.stat_time }}</el-descriptions-item>
            <el-descriptions-item label="资源密度">{{ currentResource?.ext2 }}</el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <template #footer>
        <el-button @click="resourceDetailVisible = false">关闭</el-button>
      </template>
    </el-dialog>

    <!-- 新异常通知弹窗 -->
    <el-dialog
      v-model="newWarningVisible"
      title="新异常通知"
      width="400px"
      :show-close="false"
    >
      <div class="warning-notification">
        <div class="warning-icon">
          警告
        </div>
        <div class="warning-content">
          <p><strong>{{ newWarning?.resource_name }}</strong> 出现异常</p>
          <p>{{ newWarning?.status_desc }}</p>
          <p class="warning-time">发生时间: {{ newWarning?.time }}</p>
        </div>
      </div>
      <template #footer>
        <el-button @click="ignoreWarning">忽略</el-button>
        <el-button type="primary" @click="handleNewWarning">处理异常</el-button>
      </template>
    </el-dialog>

    <!-- 修改指标目标值弹窗（适配子指标） -->
    <el-dialog
      v-model="modifyTargetVisible"
      title="修改指标目标值"
      width="400px"
    >
      <el-form :model="targetForm" ref="targetFormRef" label-width="100px">
        <el-form-item label="指标组" disabled>
          <el-input v-model="targetForm.indicatorName" />
        </el-form-item>
        <el-form-item label="子指标" disabled>
          <el-input v-model="targetForm.subIndicatorName" />
        </el-form-item>
        <el-form-item label="当前目标值" disabled>
          <el-input v-model="targetForm.currentTarget" :suffix="targetForm.unit" />
        </el-form-item>
        <el-form-item label="新目标值" required>
          <el-input
            v-model="targetForm.newTarget"
            type="number"
            :min="getMinValue()"
            step="0.1"
            :suffix="targetForm.unit"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="modifyTargetVisible = false">取消</el-button>
        <el-button type="primary" @click="saveIndicatorTarget">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>
<script setup>
import { ref, computed, onMounted, onUnmounted, getCurrentInstance, watch } from 'vue';
import {
  ElSelect,
  ElOption,
  ElButton,
  ElTable,
  ElTableColumn,
  ElTag,
  ElProgress,
  ElInput,
  ElDialog,
  ElDescriptions,
  ElDescriptionsItem,
  ElForm,
  ElFormItem,
  ElMessage
} from 'element-plus';
import screenFull from 'screenfull';
import {FullScreen, ArrowLeft} from "@element-plus/icons-vue";
import MapCommon from "./MapCommon.vue";
import ChartLine from './ChartLine.vue';
import ChartBar from './ChartBar.vue';
import ChartPie from './ChartPie.vue';

// 导入API方法
import {
  fetchTransportEquipmentGeometries,
  fetchResourceDistribution,
  fetchCoreIndicators,
  fetchTrafficFlowOverview,
  fetchWarningScrollData,
  handleAbnormal,
  configureIndicatorTarget,
  fetchIndicatorHistory,
  fetchFlowTrend,
  fetchIndicatorTrend
} from '@/api/overview/transportation/GlobalSituationOverview.js';

const geometriesArray = ref([]);

// 头部区域相关数据和方法
const currentTime = ref('');

// 获取当前组件实例（用于访问ref）
const instance = getCurrentInstance();

import { useRouter } from 'vue-router'; // 导入路由钩子

const router = useRouter(); // 初始化路由实例

// 面板全屏切换功能
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

  // 记录所有父级Flex容器
  const flexContainers = [
    document.querySelector('.content-wrapper'),
    document.querySelector('.middle-right-container'),
    panel.parentElement
  ];

  if (screenFull.isFullscreen && document.fullscreenElement === panel) {
    screenFull.exit().then(() => {
      // 强制重绘Flex容器，修复布局
      flexContainers.forEach(container => {
        if (container) {
          container.style.opacity = '0.99';
          setTimeout(() => {
            container.style.opacity = '1';
          }, 0);
        }
      });
    });
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

// 交通全域数据地图相关数据
const mapResources = ref([]);

// 地图筛选条件
const mapResourceType = ref('');
const mapStatusFilter = ref('');

// 筛选后的地图资源
const filteredMapResources = computed(() => {
  return mapResources.value.filter(resource => {
    const matchesType = !mapResourceType.value || resource.resource_type === mapResourceType.value;
    const matchesStatus = !mapStatusFilter.value || resource.real_time_status === mapStatusFilter.value;
    return matchesType && matchesStatus;
  });
});

// 地图资源详情弹窗
const resourceOnMapDetailVisible = ref(false);
const currentMapResource = ref(null);
const handleContent = ref('');

// 显示地图资源详情
const showResourceOnMapDetail = (resource) => {
  currentMapResource.value = {...resource};
  handleContent.value = '';
  resourceOnMapDetailVisible.value = true;
};

// 处理资源异常
const handleResourceAbnormal = async () => {
  if (!currentMapResource.value) return;

  try {
    await handleAbnormal({
      mapDataId: currentMapResource.value.map_data_id,
      handleContent: handleContent.value,
      handleUser: 'admin'
    });

    // 更新本地数据
    const index = mapResources.value.findIndex(f => f.map_data_id === currentMapResource.value.map_data_id);
    if (index !== -1) {
      mapResources.value[index].real_time_status = '0';
      mapResources.value[index].status_desc = '已处理';
      mapResources.value[index].abnormal_handle_id = 'handle_' + Date.now();
    }

    currentMapResource.value.real_time_status = '0';
    currentMapResource.value.status_desc = '已处理';
    currentMapResource.value.abnormal_handle_id = 'handle_' + Date.now();

    ElMessage.success('异常处理成功');
    resourceOnMapDetailVisible.value = false;
  } catch (error) {
    ElMessage.error('异常处理失败: ' + (error.message || '未知错误'));
  }
};

// 交通资源分布视图相关数据
const resourceDistribution = ref([]);

// 资源分布筛选条件
const resourceTypeFilter = ref('');
const areaFilter = ref('');

// 筛选后的资源分布
const filteredResources = computed(() => {
  return resourceDistribution.value.filter(resource => {
    const matchesType = !resourceTypeFilter.value || resource.resource_type === resourceTypeFilter.value;
    const matchesArea = !areaFilter.value || resource.area_name === areaFilter.value;
    return matchesType && matchesArea;
  });
});

// 饼图数据
const resourcePieData = computed(() => {
  // 统计各类资源总数
  const road = filteredResources.value
    .filter(r => r.resource_type === '1')
    .reduce((sum, r) => sum + r.total_count, 0);

  const bus = filteredResources.value
    .filter(r => r.resource_type === '2')
    .reduce((sum, r) => sum + r.total_count, 0);

  const hub = filteredResources.value
    .filter(r => r.resource_type === '3')
    .reduce((sum, r) => sum + r.total_count, 0);

  return {
    legend: ['道路', '公交站', '枢纽'],
    series: [road, bus, hub],
    colors: ['#1890ff', '#7cb305', '#f7ba1e'],
    radius: '70%'
  };
});

// 跟踪当前选中的饼图扇区索引
const activePieIndex = ref(-1);

// 点击饼图事件
const handlePieClick = (index) => {
  if (activePieIndex.value === index) {
    resourceTypeFilter.value = '';
    activePieIndex.value = -1;
  } else {
    resourceTypeFilter.value = index === 0 ? '1' : index === 1 ? '2' : '3';
    activePieIndex.value = index;
  }
};

// 监听区域筛选变化
watch(
  () => areaFilter.value,
  () => {
    resourceTypeFilter.value = '';
    activePieIndex.value = -1;
  }
);

// 资源统计数据
const totalResources = computed(() => {
  return filteredResources.value.reduce((sum, item) => sum + item.total_count, 0);
});

const normalResources = computed(() => {
  return filteredResources.value.reduce((sum, item) => sum + item.normal_count, 0);
});

const abnormalResources = computed(() => {
  return filteredResources.value.reduce((sum, item) => sum + item.abnormal_count, 0);
});

// 资源分布详情弹窗
const resourceDetailVisible = ref(false);
const currentResource = ref(null);

// 显示资源分布详情
const showResourceDetail = (resource) => {
  currentResource.value = {...resource};
  resourceDetailVisible.value = true;
};

// 导出资源分布数据
const exportResourceData = () => {
  console.log('导出资源分布数据', filteredResources.value);
  ElMessage.success('资源分布数据导出成功');
};

// 获取资源类型名称
const getResourceTypeName = (type) => {
  switch (type) {
    case '1': return '道路';
    case '2': return '公交站';
    case '3': return '枢纽';
    default: return '未知';
  }
};

// 获取异常率颜色
const getAbnormalRateColor = (rate) => {
  if (rate > 0.3) {
    return '#ff4949'; // 高异常率-红色
  } else if (rate > 0.1) {
    return '#ff7d00'; // 中异常率-橙色
  } else {
    return '#13ce66'; // 低异常率-绿色
  }
};

// 交通核心指标相关数据（修改为指标组）
const coreIndicators = ref([]);

const jumpToTrafficEfficiency = () => {
  router.push('/overview/trafficefficiency/dpzl');
};

const jumpToAccidentWarning = () => {
  router.push('/overview/accidentwarning/dpzl');
};

const jumpToServiceQuality = () => {
  router.push('/overview/servicequality/dpzl');
};

const jumpToDisposalEfficiency = () => {
  router.push('/overview/disposalefficiency/dpzl');
};

// 当前选中的指标组
const currentIndicatorGroup = ref(null);
// 弹窗中选中的子指标
const selectedSubIndicator = ref('');
// 子指标趋势数据
const indicatorHistoryData = ref({ xAxis: [], series: [] });

// 判断指标组的整体状态
const getGroupStatusClass = (group) => {
  const hasDanger = group.indicators.some(sub => sub.status === '1');
  return hasDanger ? 'danger' : 'normal';
};

// 显示指标组详情
const showIndicatorDetail = (group) => {
  currentIndicatorGroup.value = { ...group };
  if (group.indicators.length > 0) {
    selectedSubIndicator.value = group.indicators[0].code;
    loadSubIndicatorTrend(selectedSubIndicator.value);
  }
  indicatorDetailVisible.value = true;
};

// 加载子指标趋势数据
const loadSubIndicatorTrend = async (subCode) => {
  if (!currentIndicatorGroup.value) return;
  const sub = currentIndicatorGroup.value.indicators.find(s => s.code === subCode);
  if (!sub) return;

  const data = await fetchIndicatorHistory(sub.code, sub.target);
  indicatorHistoryData.value = data;
};

// 获取Y轴单位
const getYAxisName = () => {
  if (!currentIndicatorGroup.value || !selectedSubIndicator.value) return '';
  const sub = currentIndicatorGroup.value.indicators.find(s => s.code === selectedSubIndicator.value);
  return sub ? sub.unit : '';
};

// 指标详情弹窗控制
const indicatorDetailVisible = ref(false);

// 修改指标目标值相关
const modifyTargetVisible = ref(false);
const targetForm = ref({
  indicatorName: '',
  subIndicatorName: '',
  currentTarget: '',
  newTarget: '',
  unit: ''
});
const targetFormRef = ref(null);

// 获取目标值最小值（根据指标类型）
const getMinValue = () => {
  if (!selectedSubIndicator.value) return 0;
  // 计数类指标最小为0，比例类最小为0
  return 0;
};

// 打开修改目标值弹窗
const modifyIndicatorTarget = () => {
  if (!currentIndicatorGroup.value || !selectedSubIndicator.value) return;
  const sub = currentIndicatorGroup.value.indicators.find(s => s.code === selectedSubIndicator.value);
  if (!sub) return;

  targetForm.value.indicatorName = currentIndicatorGroup.value.name;
  targetForm.value.subIndicatorName = sub.name;
  targetForm.value.currentTarget = sub.target;
  targetForm.value.unit = sub.unit;
  targetForm.value.newTarget = '';

  modifyTargetVisible.value = true;
};

// 保存子指标目标值
const saveIndicatorTarget = async () => {
  if (!currentIndicatorGroup.value || !selectedSubIndicator.value || !targetForm.value.newTarget) return;
  const subIndex = currentIndicatorGroup.value.indicators.findIndex(s => s.code === selectedSubIndicator.value);
  if (subIndex === -1) return;

  try {
    await configureIndicatorTarget({
      groupType: currentIndicatorGroup.value.type,
      subCode: selectedSubIndicator.value,
      targetValue: parseFloat(targetForm.value.newTarget)
    });

    // 更新本地数据
    currentIndicatorGroup.value.indicators[subIndex].target = parseFloat(targetForm.value.newTarget);
    // 重新计算状态
    const sub = currentIndicatorGroup.value.indicators[subIndex];
    sub.status = judgeSubIndicatorStatus(sub.code, sub.value, sub.target);

    ElMessage.success('目标值配置成功');
    modifyTargetVisible.value = false;
    // 刷新趋势图
    loadSubIndicatorTrend(selectedSubIndicator.value);
  } catch (error) {
    ElMessage.error('配置失败: ' + (error.message || '未知错误'));
  }
};

// 根据子指标类型判断状态
const judgeSubIndicatorStatus = (code, value, target) => {
  switch (code) {
    case 'avg_speed': // 平均速度：≥目标为正常
    case 'bus_on_time_rate': // 准点率：≥目标为正常
      return value >= target ? '0' : '1';
    case 'congest_rate': // 拥堵率：≤目标为正常
    case 'accident_count': // 事故数：≤目标为正常
    case 'warning_count': // 预警数：≤目标为正常
    case 'complain_count': // 投诉数：≤目标为正常
    case 'emer_resp_time': // 响应时间：≤目标为正常
      return value <= target ? '0' : '1';
    default: return '1';
  }
};

// 指标趋势图相关
const trendIndicatorId = ref('');
const indicatorTrendData = ref({
  xAxis: [],
  series: []
});

const trendKey = ref(0);

// 切换趋势图指标组
const changeTrendIndicator = async (type) => {
  if (!type) return;
  const group = coreIndicators.value.find(g => g.type === type);
  if (!group) return;

  const { xAxis, series } = await fetchIndicatorTrend(group.indicators);

  indicatorTrendData.value = { xAxis, series };
  setTimeout(() => {
    indicatorTrendData.value = { xAxis: [...xAxis], series: [...series] };
    trendKey.value++;
  }, 20);
};

const togglePanelFullscreen = (panelRefName) => {
  toggleFullscreen(panelRefName);
};

// 交通流量总览相关数据
const trafficFlowOverview = ref([]);

// 流量筛选条件
const flowObjTypeFilter = ref('');
const flowStatusFilter = ref('');

// 筛选后的交通流量数据
const filteredTrafficFlow = computed(() => {
  return trafficFlowOverview.value.filter(flow => {
    const matchesType = !flowObjTypeFilter.value || flow.monitor_obj_type === flowObjTypeFilter.value;
    const matchesStatus = !flowStatusFilter.value || flow.flow_status === flowStatusFilter.value;
    return matchesType && matchesStatus;
  });
});

// 流量详情弹窗
const flowDetailVisible = ref(false);
const currentFlow = ref(null);
const flowTrendData = ref({
  xAxis: [],
  series: []
});

// 显示流量详情
const showFlowDetail = async (flow) => {
  currentFlow.value = {...flow};
  await generateFlowTrendData(flow.flow_id);
  flowDetailVisible.value = true;
};

// 流量趋势数据
const generateFlowTrendData = async (flowId) => {
  try {
    const data = await fetchFlowTrend(flowId);
    flowTrendData.value = data;
  } catch (error) {
    console.error('获取流量趋势数据失败:', error);
  }
};

// 导出流量数据
const exportFlowData = () => {
  console.log('导出交通流量数据', filteredTrafficFlow.value);
  ElMessage.success('交通流量数据导出成功');
};

// 显示流量趋势
const showFlowTrends = () => {
  ElMessage.info('流量趋势分析功能待实现');
};


// 1. 添加视图切换状态（默认显示交通流量表格）
const currentView = ref('flowTable'); // flowTable=表格视图，top10Flow=TOP10视图

// 2. 视图切换方法
const toggleView = () => {
  currentView.value = currentView.value === 'flowTable' ? 'top10Flow' : 'flowTable';
};

// 3. 计算TOP10路段数据（筛选路段+排序+超容率计算）
const top10Roads = computed(() => {
  return [...trafficFlowOverview.value]
    // 只筛选「路段类型」（monitor_obj_type=1）
    .filter(flow => flow.monitor_obj_type === '1')
    // 按实时流量降序排序
    .sort((a, b) => (b.vehicle_flow || 0) - (a.vehicle_flow || 0))
    // 取前10条
    .slice(0, 10)
    // 补充排名和超容率
    .map((item, index) => ({
      ...item,
      rank: index + 1, // 排名（1-10）
      // 超容率 = (当前流量 / 饱和值) * 100%（避免除数为0）
      overRate: item.saturation_value > 0
        ? (item.vehicle_flow / item.saturation_value) * 100
        : 0
    }));
});

// 4. 辅助方法：排名样式（1-3名特殊颜色，4-10名统一颜色）
const getRankClass = (rank) => {
  switch (rank) {
    case 1: return 'rank-first'; // 金色
    case 2: return 'rank-second'; // 银色
    case 3: return 'rank-third'; // 铜色
    default: return 'rank-other'; // 蓝色
  }
};

// 5. 辅助方法：超容率颜色（正常/接近饱和/超容）
const getOverRateClass = (rate) => {
  if (rate > 100) return 'danger'; // 超容（红色）
  if (rate > 80) return 'warning'; // 接近饱和（橙色）
  return 'normal'; // 正常（绿色）
};


// 异常信息滚动配置
const warningScrollConfig = ref({
  header: ['位置', '异常类型', '时间', '状态'],
  data: [],
  rowNum: 5,
  align: ['center', 'center', 'center', 'center']
});

// 新异常通知相关逻辑
const newWarningVisible = ref(false);
const newWarning = ref(null);

// 模拟新异常推送
const simulateNewWarning = () => {
  const abnormalResources = mapResources.value.filter(f => f.real_time_status === '1');
  if (abnormalResources.length === 0) return;

  const randomIndex = Math.floor(Math.random() * abnormalResources.length);
  const resource = abnormalResources[randomIndex];

  newWarning.value = {
    resource_name: resource.resource_name,
    status_desc: resource.status_desc,
    time: formatTime(new Date())
  };

  newWarningVisible.value = true;
};

// 忽略异常
const ignoreWarning = () => {
  newWarningVisible.value = false;
};

// 处理新异常
const handleNewWarning = () => {
  if (newWarning.value) {
    const resource = mapResources.value.find(f => f.resource_name === newWarning.value.resource_name);
    if (resource) {
      showResourceOnMapDetail(resource);
    }
  }
  newWarningVisible.value = false;
};

const initData = async () => {
  try {
    const [
      equipmentGeometriesData,
      resources,
      indicators,
      flowData,
      warningScrollData
    ] = await Promise.all([
      fetchTransportEquipmentGeometries(),
      fetchResourceDistribution(),
      fetchCoreIndicators('today'),
      fetchTrafficFlowOverview(),
      fetchWarningScrollData()
    ]);

    geometriesArray.value = equipmentGeometriesData;
    resourceDistribution.value = resources;
    coreIndicators.value = indicators;
    trafficFlowOverview.value = flowData;
    warningScrollConfig.value.data = warningScrollData;

    // 初始化指标趋势图
    if (coreIndicators.value.length > 0) {
      trendIndicatorId.value = coreIndicators.value[0].type;
      changeTrendIndicator(trendIndicatorId.value);
    }
  } catch (error) {
    console.error('初始化数据失败:', error);
    ElMessage.error('数据加载失败，请刷新页面重试');
  }
};

onMounted(() => {
  // 初始化时间
  currentTime.value = formatTime(new Date());
  const timer = setInterval(() => {
    currentTime.value = formatTime(new Date());
  }, 1000);

  // 初始化数据
  initData();

  // 定时模拟新异常
  const warningTimer = setInterval(() => {
    if (Math.random() < 0.3) {
      simulateNewWarning();
    }
  }, 30000);

  onUnmounted(() => {
    clearInterval(timer);
    clearInterval(warningTimer);
  });
});
</script>
<style lang="scss" scoped>

@import url('./common-styles.scss');

// 公共面板样式
.panel {
  position: relative;
  border: 0.2vh solid rgba(25, 186, 139, 0.17);
  background: url("@/assets/chart/images/line(1).png") rgba(255, 255, 255, .04);
  display: flex;
  flex-direction: column;
  min-height: 0;
}

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

.left {
  flex: 3;
}

.middle {
  flex: 4;
}

.middle_top {
  height: 50%;
  margin-bottom: 2%;
}

.middle_bottom {
  height: 48%;
}

.right {
  flex: 3;
}

.right_top {
  height: 28%;
  margin-bottom: 3%;
}

.right_middle {
  height: 38%;
  margin-bottom: 3%;
}

.right_bottom {
  height: 30%;
}
</style>
