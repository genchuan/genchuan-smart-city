<template>
  <div class="page-container">
    <!-- 主体内容区 -->
    <div class="mainbox">
      <!-- 内容布局：左-中-右三列 -->
      <div class="content-layout">
        <!-- 左侧：社区人口总览视图 -->
        <div class="left-column">
          <div class="population-overview-panel panel">
            <div class="panel-header">
              <h2>社区人口总览</h2>
              <div class="header-actions">
                <el-select v-model="populationTimeFilter" size="small" @change="updatePopulationData">
                  <el-option label="年度" value="year" />
                  <el-option label="季度" value="quarter" />
                </el-select>
                <el-select v-model="populationHukouFilter" size="small" @change="updatePopulationData">
                  <el-option label="全部户籍" value="" />
                  <el-option label="本地户籍" value="local" />
                  <el-option label="外地户籍" value="nonlocal" />
                </el-select>
              </div>
            </div>
            <div class="panel-body">
              <!-- 人口总量统计 -->
              <div class="population-total-stats">
                <div class="total-stat-card">
                  <div class="stat-value">{{ populationStats.total }}</div>
                  <div class="stat-label">总人口</div>
                </div>
                <div class="total-stat-card">
                  <div class="stat-value">{{ populationStats.permanent }}</div>
                  <div class="stat-label">常住人口</div>
                </div>
                <div class="total-stat-card">
                  <div class="stat-value">{{ populationStats.float }}</div>
                  <div class="stat-label">流动人口</div>
                </div>
                <div class="total-stat-card">
                  <div class="stat-value">{{ populationStats.households }}</div>
                  <div class="stat-label">户数</div>
                </div>
              </div>

              <!-- 人口结构分析 -->
              <div class="population-structure">
                <div class="structure-chart">
                  <h3>年龄结构</h3>
                  <ChartPie
                    :data="ageStructureData"
                    :radius="['40%', '70%']"
                    @click="handleAgeGroupClick"
                  />
                </div>
                <div class="structure-chart">
                  <h3>户籍类型</h3>
                  <ChartPie
                    :data="hukouTypeData"
                    :radius="['40%', '60%']"
                    @click="handleHukouTypeClick"
                  />
                </div>
                <div class="structure-chart">
                  <h3>性别比例</h3>
                  <div class="gender-ratio">
                    <div class="gender-item male">
                      <div class="gender-icon">♂</div>
                      <div class="gender-value">{{ genderRatio.male }}%</div>
                      <div class="gender-label">男性</div>
                    </div>
                    <div class="gender-item female">
                      <div class="gender-icon">♀</div>
                      <div class="gender-value">{{ genderRatio.female }}%</div>
                      <div class="gender-label">女性</div>
                    </div>
                  </div>
                </div>
                <div class="structure-chart population-heatmap-chart">
                  <h3>人口分布热力图</h3>
                  <div class="heatmap-container">
                    <div class="heatmap-grid">
                      <div
                        v-for="cell in populationHeatmapData"
                        :key="cell.id"
                        class="heatmap-cell"
                        :style="{
                          backgroundColor: getHeatmapColor(cell.density),
                          opacity: 0.7 + (cell.density / 100) * 0.3
                        }"
                        @click="handleHeatmapCellClick(cell)"
                        :title="`区域 ${cell.id}: 人口密度 ${cell.density}人/平方公里`"
                      >
                        <span class="cell-density">{{ cell.density }}</span>
                      </div>
                    </div>
                  </div>
                  <div class="heatmap-legend">
                    <span>低</span>
                    <div class="legend-gradient">
                      <div class="gradient-bar"></div>
                    </div>
                    <span>高</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 中间：社区全域数据地图 -->
        <div class="center-column">
          <div class="community-map-panel panel">
            <div class="panel-header">
              <h2>社区全域数据地图</h2>
              <div class="header-actions">
                <div class="map-controls-toolbar">
                  <!-- 筛选控件组 -->
                  <div class="filter-controls-group">
                    <el-select
                      v-model="selectedGrid"
                      placeholder="网格筛选"
                      size="small"
                      @change="handleGridFilterChange"
                    >
                      <el-option label="全部网格" value="" />
                      <el-option
                        v-for="grid in gridList"
                        :key="grid.id"
                        :label="grid.grid_name"
                        :value="grid.id"
                      />
                    </el-select>

                    <!-- 修复：资源类型筛选器 -->
                    <el-select
                      v-model="resourceTypeFilter"
                      placeholder="资源类型"
                      size="small"
                      @change="updateMapLayers"
                      clearable
                    >
                      <el-option
                        v-for="option in resourceTypeOptions"
                        :key="option.value"
                        :label="option.label"
                        :value="option.value"
                      />
                    </el-select>

                    <el-select v-model="eventTypeFilter" placeholder="异常类型" size="small" @change="updateMapLayers">
                      <el-option label="全部异常" value="" />
                      <el-option label="设施故障" value="设施故障" />
                      <el-option label="安全事件" value="安全事件" />
                    </el-select>

                    <el-select v-model="selectedTimeRange" size="small" @change="handleTimeFilterChange">
                      <el-option label="今日" value="today" />
                      <el-option label="本周" value="week" />
                      <el-option label="本月" value="month" />
                    </el-select>
                  </div>

                  <!-- 地图操作按钮 -->
                  <div class="map-operation-buttons">
                    <el-button size="small" @click="zoomIn" title="放大">
                      <el-icon><ZoomIn /></el-icon>
                    </el-button>
                    <el-button size="small" @click="zoomOut" title="缩小">
                      <el-icon><ZoomOut /></el-icon>
                    </el-button>
                    <el-button size="small" @click="resetMapView" title="重置视图">
                      <el-icon><Aim /></el-icon>
                    </el-button>
                    <el-button size="small" @click="refreshMapData" title="刷新数据">
                      <el-icon><Refresh /></el-icon>
                    </el-button>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-body map-container">
              <!-- 地图容器 -->
              <TMapCommon
                ref="communityMap"
                :idName="mapId"
                :geometriesArray="mapGeometries"
                @loaded="onMapLoaded"
                @click="handleMapClick"
                @dblclick="handleMapDoubleClick"
                @contextmenu="handleMapContextMenu"
              />

              <!-- 地图加载状态 -->
              <div v-if="!isMapLoaded" class="map-loading">
                <el-icon class="loading-icon"><Loading /></el-icon>
                <span>地图加载中...</span>
              </div>

              <!-- 图层控制面板 -->
              <div class="layer-control-panel">
                <div class="layer-title">图层控制</div>
                <div class="layer-list">
                  <div class="layer-item" v-for="layer in layerConfigs" :key="layer.id">
                    <el-checkbox
                      v-model="layer.visible"
                      @change="toggleLayerVisibility(layer.id, layer.visible)"
                    >
                      {{ layer.name }}
                    </el-checkbox>
                    <div class="layer-color-indicator" :style="{backgroundColor: layer.color}"></div>
                    <span class="layer-count">{{ getLayerCount(layer.id) }}</span>
                  </div>
                </div>
              </div>

              <!-- 地图图例 -->
              <div class="map-legend">
                <div class="legend-title">图例</div>
                <div class="legend-items">
                  <div class="legend-item">
                    <div class="legend-color" style="background-color: #1890ff; border: 1px solid #1890ff;"></div>
                    <span>网格边界</span>
                  </div>
                  <div class="legend-item">
                    <div class="legend-color" style="background-color: #52c41a;"></div>
                    <span>资源点位</span>
                  </div>
                  <div class="legend-item">
                    <div class="legend-color" style="background-color: #faad14;"></div>
                    <span>重点区域</span>
                  </div>
                  <div class="legend-item">
                    <div class="legend-color" style="background-color: #ff4d4f;"></div>
                    <span>设施故障</span>
                  </div>
                  <div class="legend-item">
                    <div class="legend-color" style="background-color: #fa8c16;"></div>
                    <span>安全事件</span>
                  </div>
                </div>
              </div>

              <!-- 钻取导航 -->
              <div v-if="currentDrillLevel !== 'community'" class="drill-navigation">
                <el-button size="small" @click="drillUp">
                  <el-icon><ArrowLeft /></el-icon>
                  返回{{ getDrillParentLevel() }}
                </el-button>
                <span class="current-level">当前: {{ getDrillLevelName() }}</span>
              </div>
            </div>
          </div>
        </div>

        <!-- 右侧：社区资源分布视图 -->
        <div class="right-column">
          <div class="resource-distribution-panel panel">
            <div class="panel-header">
              <h2>社区资源分布</h2>
              <div class="header-actions">
                <!-- 修复：资源状态筛选器 -->
                <el-select
                  v-model="resourceStatusFilter"
                  size="small"
                  @change="updateResourceData"
                  clearable
                  placeholder="资源状态"
                >
                  <el-option
                    v-for="option in resourceStatusOptions"
                    :key="option.value"
                    :label="option.label"
                    :value="option.value"
                  />
                </el-select>
              </div>
            </div>
            <div class="panel-body">
              <!-- 资源类型选择 -->
              <div class="resource-type-selector">
                <el-radio-group v-model="selectedResourceType" @change="updateResourceData">
                  <el-radio-button label="facility">
                    <el-icon><Tools /></el-icon>
                    设施
                  </el-radio-button>
                  <el-radio-button label="service">
                    <el-icon><Location /></el-icon>
                    服务点
                  </el-radio-button>
                  <el-radio-button label="personnel">
                    <el-icon><User /></el-icon>
                    人员
                  </el-radio-button>
                </el-radio-group>
              </div>

              <!-- 资源统计概览 -->
              <div class="resource-overview-stats">
                <div class="resource-stat-card">
                  <div class="stat-value">{{ resourceStats.total }}</div>
                  <div class="stat-label">资源总数</div>
                </div>
                <div class="resource-stat-card">
                  <div class="stat-value">{{ resourceStats.online }}</div>
                  <div class="stat-label">在线数量</div>
                </div>
                <div class="resource-stat-card">
                  <div class="stat-value">{{ resourceStats.offline }}</div>
                  <div class="stat-label">离线数量</div>
                </div>
                <div class="resource-stat-card">
                  <div class="stat-value">{{ resourceStats.density }}</div>
                  <div class="stat-label">密度(个/km²)</div>
                </div>
              </div>

              <!-- 资源列表 -->
              <div class="resource-list-view">
                <div class="list-header">
                  <span>资源名称</span>
                  <span>状态</span>
                  <span>操作</span>
                </div>
                <div class="resource-list">
                  <div
                    v-for="resource in visibleResources"
                    :key="resource.id"
                    class="resource-item"
                    :class="{
                      offline: resource.status === 'offline',
                      abnormal: resource.status === 'abnormal'
                      }"
                    @click="showResourceDetail(resource)"
                  >
                    <div class="resource-info">
                      <div class="resource-icon" :class="getResourceIconClass(resource.type)"></div>
                      <div class="resource-details">
                        <div class="resource-name">{{ resource.name }}</div>
                        <div class="resource-location">{{ resource.location }}</div>
                        <div class="resource-grid">网格: {{ resource.gridName }}</div>
                      </div>
                    </div>
                    <div class="resource-status">
                      <el-tag
                        :type="getStatusTagType(resource.status)"
                        size="small"
                      >
                        {{ getStatusText(resource.status) }}
                      </el-tag>
                    </div>
                    <div class="resource-actions">
                      <el-button size="small" @click.stop="showResourceDetail(resource)">详情</el-button>
                      <el-button
                        size="small"
                        type="primary"
                        v-if="resource.status === 'offline' || resource.status === 'abnormal'"
                        @click.stop="handleRepairRequest(resource)"
                      >
                        报修
                      </el-button>
                      <el-button
                        size="small"
                        type="warning"
                        v-if="resource.status === 'maintenance'"
                        @click.stop="handleMaintenanceStatus(resource)"
                      >
                        维护中
                      </el-button>
                    </div>
                  </div>
                </div>
                <div class="list-footer">
                  <el-pagination
                    small
                    :current-page="resourceCurrentPage"
                    :page-size="resourcePageSize"
                    :total="resourceTotal"
                    @current-change="handleResourcePageChange"
                    layout="prev, pager, next"
                  />
                </div>
              </div>

              <!-- 实时预警信息部分优化 -->
              <div class="realtime-alerts">
                <div class="alerts-header">
                  <h4>实时预警</h4>
                  <el-tag type="danger" size="small">{{ realtimeAlerts.length }}</el-tag>
                </div>
                <div class="alerts-list">
                  <div
                    v-for="alert in realtimeAlerts"
                    :key="alert.id"
                    class="alert-item"
                    :class="alert.level"
                    @click="handleAlertClick(alert)"
                  >
                    <div class="alert-icon">
                      <el-icon v-if="alert.level === 'high'"><Warning /></el-icon>
                      <el-icon v-if="alert.level === 'medium'"><InfoFilled /></el-icon>
                      <el-icon v-if="alert.level === 'low'"><Bell /></el-icon>
                    </div>
                    <div class="alert-content">
                      <div class="alert-title">{{ alert.title }}</div>
                      <div class="alert-desc">{{ alert.description || alert.desc }}</div>
                      <div class="alert-meta">
                        <span class="alert-time">{{ formatTime(alert.time) }}</span>
                        <span class="alert-location">{{ alert.location }}</span>
                      </div>
                    </div>
                    <div class="alert-status" :class="alert.status">
                      {{ getAlertStatusText(alert.status) }}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 社区核心指标 -->
      <div class="core-indicators-section panel">
        <div class="panel-header">
          <h2>社区核心指标</h2>
          <div class="header-actions">
            <el-select
              v-model="communityFilter"
              placeholder="选择社区"
              size="small"
              @change="handleCommunityChange"
            >
              <el-option label="幸福社区" value="幸福社区" />
              <el-option label="和谐社区" value="和谐社区" />
              <el-option label="平安社区" value="平安社区" />
            </el-select>
            <el-select
              v-model="timeFilter"
              placeholder="时间范围"
              size="small"
              @change="handleTimeFilterChange"
            >
              <el-option label="月度" value="month" />
              <el-option label="季度" value="quarter" />
              <el-option label="年度" value="year" />
            </el-select>
            <el-button
              size="small"
              @click="refreshAllData"
              :loading="indicatorLoading.all"
            >
              {{ indicatorLoading.all ? '刷新中...' : '刷新' }}
            </el-button>
          </div>
        </div>
        <div class="panel-body">
          <div class="indicators-cards">
            <!-- 常住人口 -->
            <div
              class="indicator-card"
              :class="{
                abnormal: indicators.population.abnormal,
                loading: indicatorLoading.population
              }"
              @click="refreshIndicator('population')"
              @dblclick="showIndicatorDetail('population')"
            >
              <div class="indicator-icon">
                <el-icon><User /></el-icon>
              </div>
              <div class="indicator-name">常住人口数</div>
              <div class="indicator-value">{{ indicators.population.value }}</div>
              <div class="indicator-unit">{{ indicators.population.unit }}</div>
              <div class="indicator-change" :class="indicators.population.changeType">
                {{ indicators.population.change }}
              </div>
              <div class="indicator-source">来源: {{ indicators.population.source }}</div>
              <div class="indicator-desc">
                点击刷新 | 双击查看详情
              </div>
              <div class="indicator-loading" v-if="indicatorLoading.population">
                <el-icon class="loading-icon"><Loading /></el-icon>
              </div>
            </div>

            <!-- 设施在线率 -->
            <div
              class="indicator-card"
              :class="{
                abnormal: indicators.facility.abnormal,
                loading: indicatorLoading.facility
              }"
              @click="refreshIndicator('facility')"
              @dblclick="showIndicatorDetail('facility')"
            >
              <div class="indicator-icon">
                <el-icon><Monitor /></el-icon>
              </div>
              <div class="indicator-name">设施在线率</div>
              <div class="indicator-value">{{ indicators.facility.value }}</div>
              <div class="indicator-progress">
                <el-progress
                  :percentage="parseFloat(indicators.facility.value)"
                  :color="indicators.facility.abnormal ? '#ff4d4f' : '#13ce66'"
                  :show-text="false"
                />
              </div>
              <div class="indicator-change" :class="indicators.facility.changeType">
                {{ indicators.facility.change }}
              </div>
              <div class="indicator-source">来源: biz_device_status</div>
              <div class="indicator-desc">
                点击刷新 | 双击查看详情
              </div>
              <div class="indicator-loading" v-if="indicatorLoading.facility">
                <el-icon class="loading-icon"><Loading /></el-icon>
              </div>
            </div>

            <!-- 服务办结率 -->
            <div
              class="indicator-card"
              :class="{
                abnormal: indicators.service.abnormal,
                loading: indicatorLoading.service
              }"
              @click="refreshIndicator('service')"
              @dblclick="showIndicatorDetail('service')"
            >
              <div class="indicator-icon">
                <el-icon><Setting /></el-icon>
              </div>
              <div class="indicator-name">服务办结率</div>
              <div class="indicator-value">{{ indicators.service.value }}</div>
              <div class="indicator-progress">
                <el-progress
                  :percentage="parseFloat(indicators.service.value)"
                  :color="indicators.service.abnormal ? '#ff4d4f' : '#13ce66'"
                  :show-text="false"
                />
              </div>
              <div class="indicator-change" :class="indicators.service.changeType">
                {{ indicators.service.change }}
              </div>
              <div class="indicator-source">来源: biz_community_svc</div>
              <div class="indicator-desc">
                点击刷新 | 双击查看详情
              </div>
              <div class="indicator-loading" v-if="indicatorLoading.service">
                <el-icon class="loading-icon"><Loading /></el-icon>
              </div>
            </div>

            <!-- 安全事件发生率 -->
            <div
              class="indicator-card"
              :class="{
                abnormal: indicators.safety.abnormal,
                loading: indicatorLoading.safety
              }"
              @click="refreshIndicator('safety')"
              @dblclick="showIndicatorDetail('safety')"
            >
              <div class="indicator-icon">
                <el-icon><Warning /></el-icon>
              </div>
              <div class="indicator-name">安全事件发生率</div>
              <div class="indicator-value">{{ indicators.safety.value }}</div>
              <div class="indicator-unit">{{ indicators.safety.unit }}</div>
              <div class="indicator-change" :class="indicators.safety.changeType">
                {{ indicators.safety.change }}
              </div>
              <div class="indicator-source">来源: biz_safety_event</div>
              <div class="indicator-desc">
                点击刷新 | 双击查看详情
              </div>
              <div class="indicator-loading" v-if="indicatorLoading.safety">
                <el-icon class="loading-icon"><Loading /></el-icon>
              </div>
            </div>

            <!-- 网格覆盖率 -->
            <div
              class="indicator-card"
              :class="{
                abnormal: indicators.grid.abnormal,
                loading: indicatorLoading.grid
              }"
              @click="refreshIndicator('grid')"
              @dblclick="showIndicatorDetail('grid')"
            >
              <div class="indicator-icon">
                <el-icon><Grid /></el-icon>
              </div>
              <div class="indicator-name">网格覆盖率</div>
              <div class="indicator-value">{{ indicators.grid.value }}</div>
              <div class="indicator-progress">
                <el-progress
                  :percentage="parseFloat(indicators.grid.value)"
                  :color="indicators.grid.abnormal ? '#ff4d4f' : '#13ce66'"
                  :show-text="false"
                />
              </div>
              <div class="indicator-change" :class="indicators.grid.changeType">
                {{ indicators.grid.change }}
              </div>
              <div class="indicator-source">来源: biz_grid_mng</div>
              <div class="indicator-desc">
                点击刷新 | 双击查看详情
              </div>
              <div class="indicator-loading" v-if="indicatorLoading.grid">
                <el-icon class="loading-icon"><Loading /></el-icon>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 弹窗部分 -->
    <el-dialog
      v-model="indicatorDetailVisible"
      :title="indicatorDetailTitle"
      width="90%"
      :before-close="handleDetailClose"
      destroy-on-close
    >
      <IndicatorDetail
        :indicator-type="currentIndicatorType"
        :time-range="timeFilter"
        :community="communityFilter"
        @close="indicatorDetailVisible = false"
      />
    </el-dialog>

    <el-dialog
      v-model="mapDetailVisible"
      :title="mapDetailTitle"
      width="600px"
      destroy-on-close
    >
      <MapPointDetail
        :detail="mapCurrentDetail"
        @close="mapDetailVisible = false"
        @edit="handleMapPointEdit"
        @navigate="handleMapNavigation"
        @repair="handleMapPointRepair"
      />
    </el-dialog>

    <el-dialog
      v-model="resourceDetailVisible"
      :title="resourceDetailTitle"
      width="800px"
      destroy-on-close
    >
      <ResourceDetail
        :resource="currentResource"
        @close="resourceDetailVisible = false"
        @edit="handleResourceEdit"
        @maintenance="handleResourceMaintenance"
      />
    </el-dialog>

    <el-dialog
      v-model="populationDetailVisible"
      :title="populationDetailTitle"
      width="90%"
      destroy-on-close
    >
      <PopulationDetail
        :grid="currentGrid"
        :age-group="currentAgeGroup"
        :hukou-type="currentHukouType"
        @close="populationDetailVisible = false"
      />
    </el-dialog>

    <el-dialog
      v-model="editDialogVisible"
      :title="editDialogTitle"
      width="500px"
      destroy-on-close
    >
      <EditForm
        :data="editData"
        :type="editType"
        @save="handleEditSave"
        @cancel="editDialogVisible = false"
      />
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, computed, watch } from 'vue';
import { ElMessage, ElLoading } from 'element-plus';
import {
  ZoomIn, ZoomOut, Aim, Refresh, Loading, Warning,
  Monitor, Tools, InfoFilled, User, Setting, Grid,
  Location, Bell, ArrowLeft
} from '@element-plus/icons-vue';

// 组件导入
import IndicatorDetail from './IndicatorDetail.vue';
import MapPointDetail from './MapPointDetail.vue';
import ResourceDetail from './ResourceDetail.vue';
import PopulationDetail from './PopulationDetail.vue';
import EditForm from './EditForm.vue';
import ChartPie from './ChartPie.vue';
import TMapCommon from './MapCommon.vue';
import GlobalSituationOverviewAPI from '@/api/overview/smartcommunity/GlobalSituationOverview.js';

// 社区核心指标数据
const communityFilter = ref('幸福社区');
const timeFilter = ref('month');
const indicators = reactive({
  population: {
    value: '--',
    change: '--',
    changeType: 'same',
    abnormal: false,
    source: 'sys_pop',
    threshold: 0,
    unit: '人'
  },
  facility: {
    value: '--',
    change: '--',
    changeType: 'same',
    abnormal: false,
    source: 'biz_device_status',
    threshold: 90,
    unit: '%'
  },
  service: {
    value: '--',
    change: '--',
    changeType: 'same',
    abnormal: false,
    source: 'biz_community_svc',
    threshold: 80,
    unit: '%'
  },
  safety: {
    value: '--',
    change: '--',
    changeType: 'same',
    abnormal: false,
    source: 'biz_safety_event',
    threshold: 0.5,
    unit: '起/平方公里·月'
  },
  grid: {
    value: '--',
    change: '--',
    changeType: 'same',
    abnormal: false,
    source: 'biz_grid_mng',
    threshold: 95,
    unit: '%'
  }
});

// 加载状态
const indicatorLoading = reactive({
  population: false,
  facility: false,
  service: false,
  safety: false,
  grid: false,
  all: false
});

// 社区ID映射
const COMMUNITY_MAP = {
  '幸福社区': 'comm1001',
  '和谐社区': 'comm1002',
  '平安社区': 'comm1003'
};

// 弹窗控制
const indicatorDetailVisible = ref(false);
const currentIndicatorType = ref('');
const indicatorDetailTitle = ref('');

/**
 * 加载核心指标数据
 */
const loadCoreIndicators = async (forceRefresh = false) => {
  indicatorLoading.all = true;

  try {
    console.log('开始加载核心指标数据...');

    // 直接使用 API 获取数据
    const indicatorsData = await GlobalSituationOverviewAPI.getCoreIndicators({
      communityId: COMMUNITY_MAP[communityFilter.value],
      timeRange: timeFilter.value,
      forceRefresh
    });

    console.log('API返回数据:', indicatorsData);

    if (indicatorsData) {
      // 确保每个指标都有完整的数据结构
      Object.keys(indicators).forEach(key => {
        if (indicatorsData[key]) {
          // 保留原有的单位、来源等基础信息，只更新数值相关字段
          Object.assign(indicators[key], {
            value: indicatorsData[key].value,
            change: indicatorsData[key].change,
            changeType: indicatorsData[key].changeType,
            abnormal: indicatorsData[key].abnormal
          });
        }
      });
      console.log('核心指标数据加载成功');
    } else {
      throw new Error('API返回数据为空');
    }
  } catch (error) {
    console.error('加载核心指标数据失败:', error);
    // 使用增强的模拟数据兜底 - 确保数据结构完整
    const mockData = getMockIndicatorData(communityFilter.value, timeFilter.value);

    Object.keys(mockData).forEach(key => {
      if (indicators[key]) {
        Object.assign(indicators[key], mockData[key]);
      }
    });
  } finally {
    indicatorLoading.all = false;
  }
};

/**
 * 获取模拟指标数据
 */
const getMockIndicatorData = (community, timeRange) => {
  const baseData = {
    '幸福社区': {
      month: {
        population: { value: '12,548', change: '+2.3%', changeType: 'increase', abnormal: false },
        facility: { value: '96.7%', change: '+1.2%', changeType: 'increase', abnormal: false },
        service: { value: '78.5%', change: '-3.1%', changeType: 'decrease', abnormal: true },
        safety: { value: '0.3', change: '-0.1', changeType: 'decrease', abnormal: false },
        grid: { value: '98.2%', change: '0%', changeType: 'same', abnormal: false }
      },
      quarter: {
        population: { value: '12,340', change: '+1.8%', changeType: 'increase', abnormal: false },
        facility: { value: '97.2%', change: '+0.8%', changeType: 'increase', abnormal: false },
        service: { value: '80.1%', change: '+1.6%', changeType: 'increase', abnormal: false },
        safety: { value: '0.2', change: '-0.2', changeType: 'decrease', abnormal: false },
        grid: { value: '98.2%', change: '0%', changeType: 'same', abnormal: false }
      },
      year: {
        population: { value: '11,980', change: '+4.2%', changeType: 'increase', abnormal: false },
        facility: { value: '95.8%', change: '+2.1%', changeType: 'increase', abnormal: false },
        service: { value: '82.3%', change: '+3.8%', changeType: 'increase', abnormal: false },
        safety: { value: '0.4', change: '-0.3', changeType: 'decrease', abnormal: false },
        grid: { value: '97.8%', change: '+0.4%', changeType: 'increase', abnormal: false }
      }
    },
    '和谐社区': {
      month: {
        population: { value: '18,720', change: '+3.1%', changeType: 'increase', abnormal: false },
        facility: { value: '94.5%', change: '-0.5%', changeType: 'decrease', abnormal: true },
        service: { value: '82.3%', change: '+1.2%', changeType: 'increase', abnormal: false },
        safety: { value: '0.4', change: '+0.1', changeType: 'increase', abnormal: false },
        grid: { value: '97.8%', change: '-0.2%', changeType: 'decrease', abnormal: false }
      }
    },
    '平安社区': {
      month: {
        population: { value: '9,450', change: '+1.5%', changeType: 'increase', abnormal: false },
        facility: { value: '98.1%', change: '+0.3%', changeType: 'increase', abnormal: false },
        service: { value: '85.2%', change: '+2.1%', changeType: 'increase', abnormal: false },
        safety: { value: '0.1', change: '-0.1', changeType: 'decrease', abnormal: false },
        grid: { value: '99.1%', change: '+0.1%', changeType: 'increase', abnormal: false }
      }
    }
  };

  const communityData = baseData[community] || baseData['幸福社区'];
  const timeData = communityData[timeRange] || communityData.month;

  return timeData;
};

/**
 * 刷新单个指标
 */
const refreshIndicator = async (type) => {
  if (indicatorLoading.all) return;

  indicatorLoading[type] = true;
  const indicatorName = getIndicatorName(type);

  try {
    // 强制刷新所有指标数据（因为API是批量返回的）
    const indicatorsData = await GlobalSituationOverviewAPI.getCoreIndicators({
      communityId: COMMUNITY_MAP[communityFilter.value],
      timeRange: timeFilter.value,
      forceRefresh: true
    });

    if (indicatorsData && indicatorsData[type]) {
      Object.assign(indicators[type], indicatorsData[type]);
    }
  } catch (error) {
    console.error(`刷新${type}指标失败:`, error);
  } finally {
    indicatorLoading[type] = false;
  }
};

/**
 * 显示指标详情
 */
const showIndicatorDetail = (type) => {
  currentIndicatorType.value = type;
  indicatorDetailTitle.value = `${getIndicatorName(type)}详情分析`;

  // 预加载指标详情数据
  loadIndicatorDetailData(type).then(() => {
    indicatorDetailVisible.value = true;
  });
};

/**
 * 预加载指标详情数据
 */
const loadIndicatorDetailData = async (type) => {
  try {
    const { data } = await GlobalSituationOverviewAPI.getIndicatorDetail({
      type: type,
      communityId: COMMUNITY_MAP[communityFilter.value],
      timeRange: timeFilter.value
    });

    if (data) {
      // 更新当前指标数据，供弹窗使用
      Object.assign(indicators[type], data);
      console.log(`指标 ${type} 详情数据加载成功:`, data);
    }
  } catch (error) {
    console.error(`加载指标 ${type} 详情数据失败:`, error);
    // 使用模拟数据兜底
    const mockDetailData = {
      population: {
        currentValue: indicators.population.value,
        change: indicators.population.change,
        changeType: indicators.population.changeType,
        abnormal: indicators.population.abnormal,
        unit: '人',
        lastUpdateTime: new Date().toLocaleString()
      },
      facility: {
        currentValue: indicators.facility.value,
        change: indicators.facility.change,
        changeType: indicators.facility.changeType,
        abnormal: indicators.facility.abnormal,
        unit: '%',
        lastUpdateTime: new Date().toLocaleString()
      },
      service: {
        currentValue: indicators.service.value,
        change: indicators.service.change,
        changeType: indicators.service.changeType,
        abnormal: indicators.service.abnormal,
        unit: '%',
        lastUpdateTime: new Date().toLocaleString()
      },
      safety: {
        currentValue: indicators.safety.value,
        change: indicators.safety.change,
        changeType: indicators.safety.changeType,
        abnormal: indicators.safety.abnormal,
        unit: '起/平方公里·月',
        lastUpdateTime: new Date().toLocaleString()
      },
      grid: {
        currentValue: indicators.grid.value,
        change: indicators.grid.change,
        changeType: indicators.grid.changeType,
        abnormal: indicators.grid.abnormal,
        unit: '%',
        lastUpdateTime: new Date().toLocaleString()
      }
    };

    if (mockDetailData[type]) {
      Object.assign(indicators[type], mockDetailData[type]);
    }
  }
};

/**
 * 获取指标名称
 */
const getIndicatorName = (type) => {
  const nameMap = {
    population: '常住人口',
    facility: '设施在线率',
    service: '服务办结率',
    safety: '安全事件发生率',
    grid: '网格覆盖率'
  };
  return nameMap[type] || type;
};

/**
 * 社区切换处理
 */
const handleCommunityChange = async (value) => {
  await loadCoreIndicators();
};

/**
 * 时间筛选处理
 */
const handleTimeFilterChange = () => {
  loadCoreIndicators();
};

/**
 * 刷新所有数据
 */
const refreshAllData = async () => {
  const loading = ElLoading.service({
    lock: true,
    text: '数据刷新中...',
    background: 'rgba(0, 0, 0, 0.7)'
  });

  try {
    await loadCoreIndicators(true);
  } catch (error) {
    console.error('刷新数据失败:', error);
    ElMessage.error('数据刷新失败');
  } finally {
    loading.close();
  }
};

// 使用计算属性来根据社区和时间筛选动态获取人口数据
const communityPopulationStats = computed(() => {
  const stats = {
    '幸福社区': {
      year: {
        total: '12,560',
        permanent: '10,048',
        float: '2,512',
        households: '4,230',
        ageStructure: [1884, 7536, 3140],
        genderRatio: { male: 50, female: 50 },
        hukouType: [10048, 2512]
      },
      quarter: {
        total: '12,340',
        permanent: '9,950',
        float: '2,390',
        households: '4,190',
        ageStructure: [1850, 7450, 3040],
        genderRatio: { male: 50.2, female: 49.8 },
        hukouType: [9950, 2390]
      }
    },
    '和谐社区': {
      year: {
        total: '18,720',
        permanent: '14,040',
        float: '4,680',
        households: '6,350',
        ageStructure: [2808, 11232, 4680],
        genderRatio: { male: 51, female: 49 },
        hukouType: [14040, 4680]
      },
      quarter: {
        total: '18,450',
        permanent: '13,900',
        float: '4,550',
        households: '6,280',
        ageStructure: [2780, 11150, 4520],
        genderRatio: { male: 51.2, female: 48.8 },
        hukouType: [13900, 4550]
      }
    },
    '平安社区': {
      year: {
        total: '9,450',
        permanent: '8,030',
        float: '1,420',
        households: '3,210',
        ageStructure: [1418, 5670, 2362],
        genderRatio: { male: 49.5, female: 50.5 },
        hukouType: [8030, 1420]
      },
      quarter: {
        total: '9,320',
        permanent: '7,980',
        float: '1,340',
        households: '3,180',
        ageStructure: [1400, 5590, 2330],
        genderRatio: { male: 49.6, female: 50.4 },
        hukouType: [7980, 1340]
      }
    }
  };
  return stats;
});

// 当前选中社区的人口数据
const populationStats = computed(() => {
  const timeType = populationTimeFilter.value;
  return communityPopulationStats.value[communityFilter.value][timeType];
});

const ageStructureData = computed(() => ({
  legend: ['0-18岁', '19-60岁', '60岁以上'],
  series: populationStats.value.ageStructure
}));

const genderRatio = computed(() => populationStats.value.genderRatio);

const hukouTypeData = computed(() => ({
  legend: ['本地户籍', '外地户籍'],
  series: populationStats.value.hukouType
}));

// 人口热力图数据
const populationHeatmapData = computed(() => {
  const communityData = {
    '幸福社区': [
      { id: 1, density: 25 }, { id: 2, density: 45 }, { id: 3, density: 65 }, { id: 4, density: 35 },
      { id: 5, density: 75 }, { id: 6, density: 55 }, { id: 7, density: 40 }, { id: 8, density: 60 },
      { id: 9, density: 30 }, { id: 10, density: 50 }, { id: 11, density: 70 }, { id: 12, density: 45 },
      { id: 13, density: 65 }, { id: 14, density: 35 }, { id: 15, density: 55 }, { id: 16, density: 75 }
    ],
    '和谐社区': [
      { id: 1, density: 45 }, { id: 2, density: 65 }, { id: 3, density: 85 }, { id: 4, density: 55 },
      { id: 5, density: 90 }, { id: 6, density: 75 }, { id: 7, density: 60 }, { id: 8, density: 80 },
      { id: 9, density: 50 }, { id: 10, density: 70 }, { id: 11, density: 95 }, { id: 12, density: 65 },
      { id: 13, density: 85 }, { id: 14, density: 55 }, { id: 15, density: 75 }, { id: 16, density: 90 }
    ],
    '平安社区': [
      { id: 1, density: 15 }, { id: 2, density: 25 }, { id: 3, density: 35 }, { id: 4, density: 20 },
      { id: 5, density: 45 }, { id: 6, density: 30 }, { id: 7, density: 22 }, { id: 8, density: 38 },
      { id: 9, density: 18 }, { id: 10, density: 30 }, { id: 11, density: 40 }, { id: 12, density: 25 },
      { id: 13, density: 35 }, { id: 14, density: 20 }, { id: 15, density: 30 }, { id: 16, density: 45 }
    ]
  };
  return communityData[communityFilter.value];
});

// 社区全域数据地图数据
const mapId = ref('communityOverviewMap');
const selectedGrid = ref('');
const resourceTypeFilter = ref('');
const eventTypeFilter = ref('');
const selectedTimeRange = ref('today');
const communityMap = ref(null);
const isMapLoaded = ref(false);
const currentDrillLevel = ref('community');

// 地图几何数据 - 用于传递给腾讯地图组件
const mapGeometries = ref([]);

const layerConfigs = ref([
  { id: 'grids', name: '网格边界', visible: true, color: '#1890ff', table: 'biz_unit_grid_div' },
  { id: 'resources', name: '资源点位', visible: true, color: '#52c41a', table: 'biz_mng_comp' },
  { id: 'events', name: '异常事件', visible: true, color: '#ff4d4f', table: 'biz_mon_evt_info' },
  { id: 'pois', name: '重点区域', visible: true, color: '#faad14', table: 'sys_poi_data' }
]);

const gridList = ref([]);
const mapResources = ref([]);
const mapEvents = ref([]);
const mapPOIs = ref([]);

// 社区资源分布视图数据
const resourceStatusFilter = ref('');
const selectedResourceType = ref('facility');
const resourceStats = reactive({
  total: 156,
  online: 142,
  offline: 14,
  density: 32
});

const visibleResources = ref([]);

const realtimeAlerts = ref([]);

// 分页数据
const resourceCurrentPage = ref(1);
const resourcePageSize = ref(10);
const resourceTotal = ref(156);

// 弹窗控制
const mapDetailVisible = ref(false);
const resourceDetailVisible = ref(false);
const populationDetailVisible = ref(false);
const editDialogVisible = ref(false);

const mapCurrentDetail = ref(null);
const mapDetailTitle = ref('');
const currentResource = ref(null);
const resourceDetailTitle = ref('');
const currentGrid = ref(null);
const currentAgeGroup = ref('');
const currentHukouType = ref('');
const populationDetailTitle = ref('');
const editDialogTitle = ref('');
const editData = ref(null);
const editType = ref('');

// 其他原有变量
const populationTimeFilter = ref('year');
const populationHukouFilter = ref('');

// ========== 修复：资源筛选相关配置 ==========
// 资源类型选项配置
const resourceTypeOptions = ref([
  { label: '全部资源', value: '' },
  { label: '路灯', value: '路灯' },
  { label: '监控', value: '监控' },
  { label: '消防设施', value: '消防设施' },
  { label: '垃圾点', value: '垃圾点' }
]);

// 资源状态选项配置
const resourceStatusOptions = ref([
  { label: '全部状态', value: '' },
  { label: '在线', value: 'online' },
  { label: '离线', value: 'offline' }
]);

// 计算方法
const getLayerCount = (layerId) => {
  const countMap = {
    grids: gridList.value.length,
    resources: mapResources.value.length,
    events: mapEvents.value.length,
    pois: mapPOIs.value.length
  };
  return countMap[layerId] || 0;
};

const getHeatmapColor = (density) => {
  if (density >= 70) return '#ff4d4f';
  if (density >= 60) return '#fa8c16';
  if (density >= 50) return '#faad14';
  if (density >= 40) return '#a0d911';
  if (density >= 30) return '#52c41a';
  return '#1890ff';
};

const getDrillLevelName = () => {
  const levelMap = {
    community: '社区全域',
    grid: '网格详情',
    building: '楼栋详情'
  };
  return levelMap[currentDrillLevel.value] || '未知层级';
};

const getDrillParentLevel = () => {
  const parentMap = {
    grid: '社区全域',
    building: '网格详情'
  };
  return parentMap[currentDrillLevel.value] || '上一级';
};

// 社区人口总览视图方法
const updatePopulationData = async () => {
  await loadPopulationData();
};

const handleAgeGroupClick = (params) => {
  console.log('点击年龄组:', params);
  currentAgeGroup.value = params.name;
  populationDetailTitle.value = `${params.name}人口详情`;

  // 预加载人口详情数据
  loadPopulationDetailData().then(() => {
    populationDetailVisible.value = true;
  });
};

const handleHukouTypeClick = (params) => {
  console.log('点击户籍类型:', params);
  currentHukouType.value = params.name;
  populationDetailTitle.value = `${params.name}人口详情`;

  // 预加载人口详情数据
  loadPopulationDetailData().then(() => {
    populationDetailVisible.value = true;
  });
};

const handleHeatmapCellClick = (cell) => {
  console.log('点击热力图单元格:', cell);
  currentGrid.value = {
    id: cell.id,
    name: `区域${cell.id}`,
    population: Math.round(cell.density * 100),
    density: cell.density
  };
  populationDetailTitle.value = `区域${cell.id}人口详情`;

  // 预加载人口详情数据
  loadPopulationDetailData().then(() => {
    populationDetailVisible.value = true;
  });
};

// 社区全域数据地图方法
const onMapLoaded = () => {
  console.log('地图加载完成');
  isMapLoaded.value = true;
  loadMapData();
};

const toggleLayerVisibility = (layerId, visible) => {
  ElMessage.info(`${layerId}图层${visible ? '显示' : '隐藏'}`);
  updateMapGeometries();
};

const refreshMapData = async () => {
  await loadMapData();
};

const handleMapClick = (event) => {
  console.log('地图点击事件:', event);
  if (event.targetType === 'marker') {
    showMarkerDetail(event);
  }
};

const handleMapDoubleClick = (event) => {
  console.log('地图双击事件:', event);
  if (currentDrillLevel.value === 'community' && event.targetType === 'polygon') {
    drillToGrid(event.targetId);
  } else if (currentDrillLevel.value === 'grid') {
    drillToBuilding(event.targetId);
  }
};

const handleMapContextMenu = (event) => {
  console.log('地图右键事件:', event);
  if (currentDrillLevel.value !== 'community') {
    drillUp();
  }
};

const showMarkerDetail = (event) => {
  console.log('显示标记详情:', event);
  const attributes = event.attributes || {};
  mapCurrentDetail.value = {
    id: event.targetId,
    title: `${attributes.dataType || '未知'}详情`,
    name: attributes.name || '未知点位',
    type: attributes.dataType || '未知类型',
    location: attributes.location || '未知位置',
    status: attributes.status || 'normal',
    coordinates: event.coordinates,
    gridName: attributes.gridName || '未知网格',
    manager: attributes.manager || '系统管理员',
    phone: attributes.phone || '13800138000',
    createTime: attributes.createTime || '2023-01-15',
    desc: attributes.desc || '暂无描述',
    remark: attributes.remark || '暂无备注',
    allowEdit: true
  };
  mapDetailTitle.value = mapCurrentDetail.value.title;
  mapDetailVisible.value = true;
};

const drillToGrid = (gridId) => {
  currentDrillLevel.value = 'grid';
};

const drillToBuilding = (buildingId) => {
  currentDrillLevel.value = 'building';
};

const drillUp = () => {
  if (currentDrillLevel.value === 'building') {
    currentDrillLevel.value = 'grid';
  } else if (currentDrillLevel.value === 'grid') {
    currentDrillLevel.value = 'community';
  }
  ElMessage.info(`返回${getDrillParentLevel()}`);
};

const zoomIn = () => {
  if (communityMap.value && communityMap.value.zoomIn) {
    communityMap.value.zoomIn();
  }
};

const zoomOut = () => {
  if (communityMap.value && communityMap.value.zoomOut) {
    communityMap.value.zoomOut();
  }
};

const resetMapView = () => {
  if (communityMap.value && communityMap.value.reset) {
    communityMap.value.reset();
  }
  currentDrillLevel.value = 'community';
};

// 社区资源分布视图方法
const updateResourceData = async () => {
  await loadResourceData();
};

// 新增方法：资源状态标签类型
const getStatusTagType = (status) => {
  const typeMap = {
    'online': 'success',
    'offline': 'danger',
    'abnormal': 'warning',
    'maintenance': 'info'
  };
  return typeMap[status] || 'info';
};

// 新增方法：资源状态文本
const getStatusText = (status) => {
  const textMap = {
    'online': '在线',
    'offline': '离线',
    'abnormal': '异常',
    'maintenance': '维护中'
  };
  return textMap[status] || '未知';
};

// 新增方法：资源图标类名
const getResourceIconClass = (resourceType) => {
  const iconMap = {
    '路灯': 'light',
    '监控': 'camera',
    '消防设施': 'fire',
    '垃圾点': 'garbage',
    '服务点': 'service'
  };
  return iconMap[resourceType] || 'default';
};

const showResourceDetail = (resource) => {
  console.log('显示资源详情:', resource);

  // 确保资源数据完整
  currentResource.value = {
    id: resource.id,
    name: resource.name || resource.comp_name,
    type: resource.type || resource.comp_type,
    location: resource.location,
    status: resource.status,
    gridName: resource.gridName || resource.grid_name,
    installTime: resource.installTime || resource.install_time,
    coordinates: resource.coordinates || [resource.coordinateX, resource.coordinateY],
    manager: resource.manager || '系统管理员',
    phone: resource.phone || '13800138000',
    model: resource.model || '标准型号',
    serialNumber: resource.serialNumber || `SN${resource.id.slice(-4)}`,
    maintenanceUnit: resource.maintenanceUnit || '社区维护中心',
    remark: resource.remark || '暂无备注'
  };

  resourceDetailTitle.value = `${currentResource.value.name}详情`;
  resourceDetailVisible.value = true;
};

const handleRepairRequest = async (resource) => {
  try {
    const loading = ElLoading.service({
      lock: true,
      text: '提交报修请求中...',
      background: 'rgba(0, 0, 0, 0.7)'
    });

    // 调用API提交报修请求
    const result = await GlobalSituationOverviewAPI.handleWarning({
      resourceId: resource.id,
      resourceName: resource.name,
      issueType: resource.status === 'offline' ? '设备离线' : '设备异常',
      description: `${resource.name}出现${resource.status === 'offline' ? '离线' : '异常'}状态，需要检修`,
      priority: 'high',
      location: resource.location
    });

    loading.close();

    if (result.code === 200) {
      ElMessage.success('报修请求提交成功，维修人员将尽快处理');
      // 刷新资源数据
      await loadResourceData();
    } else {
      ElMessage.error('报修请求提交失败: ' + (result.message || '未知错误'));
    }
  } catch (error) {
    ElMessage.error('报修请求提交失败: ' + error.message);
  }
};

// 新增方法：处理维护状态
const handleMaintenanceStatus = (resource) => {
  ElMessage.info(`${resource.name}正在维护中，预计完成时间: ${resource.maintenanceEndTime || '未知'}`);
};

const handleResourcePageChange = (page) => {
  resourceCurrentPage.value = page;
  updateResourceData();
};

const handleAlertClick = (alert) => {
  console.log('查看预警详情:', alert);

  // 根据预警类型跳转到相应页面
  if (alert.resourceId) {
    // 如果是资源相关的预警，找到对应的资源并显示详情
    const relatedResource = visibleResources.value.find(r => r.id === alert.resourceId);
    if (relatedResource) {
      showResourceDetail(relatedResource);
      return;
    }
  }

  // 显示预警详情弹窗
  mapCurrentDetail.value = {
    id: alert.id,
    title: '预警详情',
    name: alert.title,
    type: 'alert',
    location: alert.location,
    status: alert.status,
    description: alert.description || alert.desc,
    level: alert.level,
    time: alert.time,
    handler: alert.handler || '待处理',
    contact: alert.contact || '13800138000'
  };
  mapDetailTitle.value = '预警详情';
  mapDetailVisible.value = true;
};

// 新增方法：格式化时间
const formatTime = (time) => {
  if (!time) return '--';
  // 如果是完整的时间字符串，提取时间部分
  if (time.includes(' ')) {
    return time.split(' ')[1];
  }
  return time;
};

// 新增方法：获取预警状态文本
const getAlertStatusText = (status) => {
  const statusMap = {
    'pending': '待处理',
    'processing': '处理中',
    'resolved': '已解决'
  };
  return statusMap[status] || '未知';
};

// 通用方法
const handleGridFilterChange = () => {
  updateMapLayers();
};

const updateMapLayers = async () => {
  await loadMapData();
};

// 新增方法：预加载人口详情数据
const loadPopulationDetailData = async () => {
  try {
    const populationData = await GlobalSituationOverviewAPI.getPopulationDistribution({
      communityId: COMMUNITY_MAP[communityFilter.value],
      timeType: populationTimeFilter.value
    });

    console.log('预加载人口详情数据:', populationData);
  } catch (error) {
    console.error('预加载人口详情数据失败:', error);
  }
};

// 更新地图几何数据
const updateMapGeometries = () => {
  const geometries = [];

  // 添加网格数据
  if (layerConfigs.value.find(layer => layer.id === 'grids' && layer.visible)) {
    gridList.value.forEach((grid, index) => {
      geometries.push({
        x: grid.latitude || 26.793227 + (Math.random() - 0.5) * 0.02,
        y: grid.longitude || 117.810114 + (Math.random() - 0.5) * 0.02,
        dataType: 'grid',
        status: 'normal',
        name: grid.grid_name,
        id: grid.id
      });
    });
  }

  // 添加资源数据
  if (layerConfigs.value.find(layer => layer.id === 'resources' && layer.visible)) {
    mapResources.value.forEach((resource, index) => {
      geometries.push({
        x: resource.latitude || 26.793227 + (Math.random() - 0.5) * 0.02,
        y: resource.longitude || 117.810114 + (Math.random() - 0.5) * 0.02,
        dataType: resource.comp_type || 'resource',
        status: resource.status === 1 ? 'online' : 'offline',
        name: resource.comp_name,
        id: resource.id
      });
    });
  }

  // 添加事件数据
  if (layerConfigs.value.find(layer => layer.id === 'events' && layer.visible)) {
    mapEvents.value.forEach((event, index) => {
      geometries.push({
        x: event.latitude || 26.793227 + (Math.random() - 0.5) * 0.02,
        y: event.longitude || 117.810114 + (Math.random() - 0.5) * 0.02,
        dataType: 'event',
        status: event.event_level || 'medium',
        name: event.event_title,
        id: event.id
      });
    });
  }

  // 添加POI数据
  if (layerConfigs.value.find(layer => layer.id === 'pois' && layer.visible)) {
    mapPOIs.value.forEach((poi, index) => {
      geometries.push({
        x: poi.latitude || 26.793227 + (Math.random() - 0.5) * 0.02,
        y: poi.longitude || 117.810114 + (Math.random() - 0.5) * 0.02,
        dataType: 'poi',
        status: 'normal',
        name: poi.poi_name,
        id: poi.id
      });
    });
  }

  mapGeometries.value = geometries;
};
// 优化：加载资源数据方法
const loadResourceData = async () => {
  try {
    console.log('开始加载资源数据，筛选条件:', {
      type: resourceTypeFilter.value,
      status: resourceStatusFilter.value,
      page: resourceCurrentPage.value
    });

    const filterParams = {
      communityId: COMMUNITY_MAP[communityFilter.value],
      page: resourceCurrentPage.value,
      pageSize: resourcePageSize.value
    };

    // 添加类型筛选
    if (resourceTypeFilter.value) {
      filterParams.type = resourceTypeFilter.value;
    }

    // 添加状态筛选 - 修正状态映射
    if (resourceStatusFilter.value) {
      // API 状态映射：1-正常，0-异常，2-维护中
      const statusMap = {
        'online': 1,
        'offline': 0,
        'abnormal': 0,
        'maintenance': 2
      };
      filterParams.status = statusMap[resourceStatusFilter.value];
    }

    const resourcesData = await GlobalSituationOverviewAPI.getMngComponents(filterParams);

    console.log('API返回资源数据:', resourcesData);

    const totalResources = resourcesData.data || [];

    // 计算统计信息
    const onlineResources = totalResources.filter(r => r.status === 1);
    const offlineResources = totalResources.filter(r => r.status === 0);
    const maintenanceResources = totalResources.filter(r => r.status === 2);

    // 更新统计信息
    resourceStats.total = totalResources.length;
    resourceStats.online = onlineResources.length;
    resourceStats.offline = offlineResources.length + maintenanceResources.length;
    resourceStats.density = Math.round(totalResources.length / 1.5);

    // 更新可见资源列表
    visibleResources.value = totalResources.map(resource => {
      // 映射状态
      let status = 'online';
      if (resource.status === 0) status = 'offline';
      if (resource.status === 2) status = 'maintenance';

      return {
        id: resource.id,
        name: resource.comp_name,
        type: resource.comp_type,
        location: resource.location,
        status: status,
        gridName: resource.grid_name,
        installTime: resource.install_time,
        coordinates: [resource.coordinateX, resource.coordinateY],
        manager: resource.manager,
        phone: resource.phone,
        remark: resource.remark
      };
    });
    resourceTotal.value = totalResources.length;
  } catch (error) {
    console.error('加载资源数据失败:', error);
    // 使用模拟数据兜底
    const mockResources = generateMockResources();
    visibleResources.value = mockResources;
    resourceTotal.value = mockResources.length;
  }
};

// API 调用方法
const loadPopulationData = async () => {
  try {
    const populationData = await GlobalSituationOverviewAPI.getPopulationDistribution({
      communityId: COMMUNITY_MAP[communityFilter.value],
      timeType: populationTimeFilter.value
    });

    console.log('人口数据:', populationData);

  } catch (error) {
    console.error('加载人口数据失败:', error);
  }
};

const loadMapData = async () => {
  try {
    // 并行加载所有地图数据
    const [gridsData, resourcesData, eventsData, poisData] = await Promise.all([
      GlobalSituationOverviewAPI.getUnitGrids({
        communityId: COMMUNITY_MAP[communityFilter.value]
      }),
      GlobalSituationOverviewAPI.getMngComponents({
        communityId: COMMUNITY_MAP[communityFilter.value],
        type: resourceTypeFilter.value || ''
      }),
      GlobalSituationOverviewAPI.getMonitorEvents({
        communityId: COMMUNITY_MAP[communityFilter.value],
        type: eventTypeFilter.value || '',
        timeRange: selectedTimeRange.value
      }),
      GlobalSituationOverviewAPI.getPoiData({
        communityId: COMMUNITY_MAP[communityFilter.value]
      })
    ]);

    // 处理网格数据
    gridList.value = gridsData.data || [];

    // 处理资源数据
    mapResources.value = resourcesData.data || [];

    // 处理事件数据
    mapEvents.value = eventsData.data || [];

    // 处理POI数据
    mapPOIs.value = poisData.data || [];

    // 更新实时告警
    const alertsData = await GlobalSituationOverviewAPI.getRealTimeAlerts({
      communityId: COMMUNITY_MAP[communityFilter.value]
    });
    realtimeAlerts.value = (alertsData.data || []).map(alert => ({
      id: alert.id,
      title: alert.alert_title,
      desc: alert.alert_desc,
      time: alert.occur_time?.split(' ')[1] || '14:30',
      location: alert.location,
      level: alert.alert_level
    }));

    // 更新地图几何数据
    updateMapGeometries();

  } catch (error) {
    console.error('加载地图数据失败:', error);
  }
};

// 弹窗处理方法
const handleDetailClose = (done) => {
  done();
};

const handleMapPointEdit = (data) => {
  editType.value = 'mapPoint';
  editDialogTitle.value = '编辑地图点位';
  editData.value = data;
  editDialogVisible.value = true;
};

const handleMapNavigation = (data) => {
  ElMessage.info(`导航到: ${data.name}`);
};

const handleMapPointRepair = (data) => {
  ElMessage.info(`报修: ${data.name}`);
};

const handleResourceEdit = (data) => {
  editType.value = 'resource';
  editDialogTitle.value = '编辑资源信息';
  editData.value = data;
  editDialogVisible.value = true;
};

const handleResourceMaintenance = (data) => {
  ElMessage.info(`查看${data.name}维护记录`);
};

const handleEditSave = (data) => {
  ElMessage.success('保存成功');
  editDialogVisible.value = false;
};

// 监听图层配置变化，更新地图几何数据
watch(layerConfigs, () => {
  updateMapGeometries();
}, { deep: true });

// 监听社区和时间筛选变化
watch([communityFilter, timeFilter], () => {
  loadCoreIndicators();
});

// 新增方法：生成模拟资源数据
const generateMockResources = () => {
  const mockData = [
    {
      id: 'res101',
      comp_name: '主干道路灯1',
      comp_type: '路灯',
      location: '第一网格主干道',
      coordinateX: 116.39048,
      coordinateY: 39.91582,
      status: 1,
      install_time: '2023-01-15',
      grid_name: '第一网格',
      remark: '主干道照明',
      manager: '张三',
      phone: '13800138001'
    },
    {
      id: 'res102',
      comp_name: '十字路口监控1',
      comp_type: '监控',
      location: '第一/二网格交界处',
      coordinateX: 116.39748,
      coordinateY: 39.91582,
      status: 1,
      install_time: '2023-02-20',
      grid_name: '第一网格',
      remark: '交通监控',
      manager: '李四',
      phone: '13900139001'
    },
    {
      id: 'res103',
      comp_name: '消防栓A1',
      comp_type: '消防设施',
      location: '第二网格居民区内',
      coordinateX: 116.40048,
      coordinateY: 39.91282,
      status: 0,
      install_time: '2023-03-10',
      grid_name: '第二网格',
      remark: '水压异常',
      manager: '王五',
      phone: '13700137001'
    },
    {
      id: 'res104',
      comp_name: '垃圾集中点B3',
      comp_type: '垃圾点',
      location: '第三网格中心',
      coordinateX: 116.39548,
      coordinateY: 39.90582,
      status: 2,
      install_time: '2023-04-05',
      grid_name: '第三网格',
      remark: '垃圾分类点',
      manager: '赵六',
      phone: '13600136001'
    }
  ];

  return mockData.map(resource => {
    let status = 'online';
    if (resource.status === 0) status = 'offline';
    if (resource.status === 2) status = 'maintenance';

    return {
      id: resource.id,
      name: resource.comp_name,
      type: resource.comp_type,
      location: resource.location,
      status: status,
      gridName: resource.grid_name,
      installTime: resource.install_time,
      coordinates: [resource.coordinateX, resource.coordinateY],
      manager: resource.manager,
      phone: resource.phone,
      remark: resource.remark
    };
  });
};

// 初始化 - 确保数据加载
onMounted(async () => {
  console.log('组件挂载，开始加载数据...');
  await loadCoreIndicators();

  // 同时加载其他必要数据
  await loadMapData();
  await loadResourceData();
  await loadPopulationData();
});
</script>

<style lang="scss" scoped>
.page-container {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  background: url("@/assets/chart/images/bg.jpg");
  color: #fff;
  padding: 0 20px;
  box-sizing: border-box;
  font-family: 'Microsoft YaHei', Arial, sans-serif;
}

.mainbox {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 10px 0;
  height: calc(100% - 80px);
  box-sizing: border-box;
  gap: 10px;
}

// 公共面板样式
.panel {
  position: relative;
  border: 0.2vh solid rgba(25, 186, 139, 0.17);
  background: url("@/assets/chart/images/line(1).png") rgba(255, 255, 255, .04);
  display: flex;
  flex-direction: column;
  min-height: 0;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  z-index: 1;

  // 四角装饰
  &::before, &::after {
    content: '';
    position: absolute;
    width: 1.5vh;
    height: 1.5vh;
    border-width: 0.5vh;
    border-style: solid;
    border-color: #02a6b5;
    z-index: 10;
  }

  &::before {
    top: 0;
    left: 0;
    border-right: none;
    border-bottom: none;
  }

  &::after {
    top: 0;
    right: 0;
    border-left: none;
    border-bottom: none;
  }

  // 底部装饰
  .panel-footer {
    position: relative;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 1vh;
    z-index: 1;

    &::before, &::after {
      content: '';
      position: absolute;
      width: 1.5vh;
      height: 1.5vh;
      border-width: 0.5vh;
      border-style: solid;
      border-color: #02a6b5;
      z-index: 10;
    }

    &::before {
      bottom: 0;
      left: 0;
      border-right: none;
      border-top: none;
    }

    &::after {
      bottom: 0;
      right: 0;
      border-left: none;
      border-top: none;
    }
  }
}

.panel-header {
  height: 35px;
  line-height: 35px;
  padding: 0 15px;
  border-bottom: 1px solid rgba(0, 204, 255, 0.1);
  display: flex;
  justify-content: space-between;
  align-items: center;

  h2 {
    margin: 0;
    font-size: 16px;
    color: #00ccff;
    font-weight: 600;
  }
}

.header-actions {
  display: flex;
  gap: 8px;
  align-items: center;
}

.panel-body {
  width: 100%;
  height: 100%;
  flex: 1;
  padding: 10px;
  box-sizing: border-box;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

// 社区核心指标样式
.core-indicators-section {
  flex: 0 0 auto;
  height: 280px;
  min-height: 280px;
}

.indicators-cards {
  display: flex;
  gap: 15px;
  height: 100%;
  padding: 10px 0;
  min-height: 200px;
}

.indicator-card {
  flex: 1;
  background: rgba(0, 30, 60, 0.8) !important;
  border-radius: 8px;
  padding: 20px;
  cursor: pointer;
  transition: all 0.3s;
  border: 2px solid rgba(0, 204, 255, 0.3) !important;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  position: relative;
  user-select: none;
  min-height: 180px;

  &:hover {
    transform: scale(1.05);
    box-shadow: 0 4px 12px rgba(0, 204, 255, 0.2);
    border-color: rgba(0, 204, 255, 0.5);
  }

  &.abnormal {
    border-color: #ff4d4f;
    background: linear-gradient(135deg, rgba(245, 34, 45, 0.1), rgba(245, 34, 45, 0.2)) !important;
    animation: pulse 2s infinite;
  }

  &.loading {
    opacity: 0.7;
    pointer-events: none;
  }
}

.indicator-icon {
  font-size: 32px;
  color: #00ccff;
  margin-bottom: 12px;
}

.indicator-name {
  font-size: 16px;
  color: #9fbdff;
  margin-bottom: 12px;
  font-weight: 600;
}

.indicator-value {
  font-size: 32px;
  font-weight: bold;
  color: #00ccff;
  margin-bottom: 8px;
  text-shadow: 0 0 10px rgba(0, 204, 255, 0.5);
}

.indicator-unit {
  font-size: 14px;
  color: #9fbdff;
  margin-bottom: 10px;
}

.indicator-progress {
  width: 80%;
  margin: 12px 0;
}

.indicator-change {
  font-size: 14px;
  font-weight: 500;
  margin-bottom: 8px;

  &.increase {
    color: #13ce66;
  }

  &.decrease {
    color: #ff4949;
  }

  &.same {
    color: #fac858;
  }
}

.indicator-source {
  font-size: 12px;
  color: #666;
  margin-bottom: 8px;
}

.indicator-desc {
  font-size: 12px;
  color: #9fbdff;
  opacity: 0.8;
  margin-top: 8px;
}

.indicator-loading {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 10;

  .loading-icon {
    font-size: 24px;
    color: #00ccff;
    animation: rotate 2s linear infinite;
  }
}

@keyframes pulse {
  0% {
    box-shadow: 0 0 0 0 rgba(255, 73, 73, 0.4);
  }
  70% {
    box-shadow: 0 0 0 10px rgba(255, 73, 73, 0);
  }
  100% {
    box-shadow: 0 0 0 0 rgba(255, 73, 73, 0);
  }
}

@keyframes rotate {
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
}

// 内容布局
.content-layout {
  display: flex;
  flex: 1;
  gap: 10px;
  height: calc(100% - 300px);
  min-height: 0;
}

.left-column, .center-column, .right-column {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.left-column {
  flex: 0 0 320px;
}

.center-column {
  flex: 1;
  min-width: 0;
}

.right-column {
  flex: 0 0 280px;
}

// 社区人口总览视图样式调整
.population-overview-panel {
  flex: 1;
}

.population-total-stats {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 8px;
  margin-bottom: 12px;
}

.total-stat-card {
  background: rgba(0, 30, 60, 0.6);
  border: 1px solid rgba(0, 204, 255, 0.2);
  border-radius: 6px;
  padding: 10px;
  text-align: center;

  .stat-value {
    font-size: 18px;
    font-weight: bold;
    color: #00ccff;
    margin-bottom: 4px;
  }

  .stat-label {
    font-size: 11px;
    color: #9fbdff;
  }
}

// 修改人口结构分析的网格布局为2x2
.population-structure {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr;
  gap: 8px;
  margin-bottom: 12px;
  height: 280px;
}

.structure-chart {
  background: rgba(0, 30, 60, 0.4);
  border-radius: 6px;
  padding: 8px;
  display: flex;
  flex-direction: column;

  h3 {
    margin: 0 0 6px 0;
    font-size: 13px;
    color: #00ccff;
    text-align: center;
  }
}

// 人口热力图专用样式
.population-heatmap-chart {
  .heatmap-container {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 5px;
  }

  .heatmap-grid {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    grid-template-rows: repeat(4, 1fr);
    gap: 2px;
    width: 100%;
    height: 100%;
    max-width: 120px;
    max-height: 120px;
  }

  .heatmap-cell {
    border-radius: 2px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: white;
    cursor: pointer;
    transition: all 0.3s;
    border: 1px solid rgba(255, 255, 255, 0.1);
    font-size: 8px;
    font-weight: bold;

    &:hover {
      transform: scale(1.1);
      box-shadow: 0 0 6px rgba(0, 204, 255, 0.5);
      z-index: 2;
    }

    .cell-density {
      text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.7);
    }
  }

  .heatmap-legend {
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 9px;
    color: #9fbdff;
    margin-top: 5px;

    .legend-gradient {
      width: 60px;
      height: 6px;
      margin: 0 6px;
      border-radius: 3px;
      overflow: hidden;

      .gradient-bar {
        width: 100%;
        height: 100%;
        background: linear-gradient(to right, #1890ff, #52c41a, #faad14, #fa8c16, #ff4d4f);
      }
    }
  }
}

.gender-ratio {
  display: flex;
  justify-content: space-around;
  align-items: center;
  height: 100%;
}

.gender-item {
  text-align: center;

  .gender-icon {
    font-size: 20px;
    margin-bottom: 5px;
  }

  .gender-value {
    font-size: 14px;
    font-weight: bold;
    margin-bottom: 2px;
  }

  .gender-label {
    font-size: 10px;
    color: #9fbdff;
  }

  &.male .gender-value {
    color: #1890ff;
  }

  &.female .gender-value {
    color: #eb2f96;
  }
}

// 社区全域数据地图样式
.community-map-panel {
  flex: 1;
}

// 地图控制工具栏样式
.map-controls-toolbar {
  display: flex;
  gap: 6px;
  align-items: center;
  flex-wrap: nowrap;
}

// 筛选控件组 - 放在同一行
.filter-controls-group {
  display: flex;
  gap: 4px;
  align-items: center;
  flex-wrap: nowrap;

  // 缩小选择框宽度
  :deep(.el-select) {
    width: 100px;

    .el-input__wrapper {
      padding: 1px 6px;
    }

    .el-input__inner {
      font-size: 11px;
    }
  }
}

// 地图操作按钮组
.map-operation-buttons {
  display: flex;
  gap: 2px;
  flex-shrink: 0;

  .el-button {
    padding: 4px 6px;
    min-width: 28px;

    .el-icon {
      font-size: 12px;
    }
  }
}

.map-container {
  position: relative;
  flex: 1;
  padding: 0;
  overflow: hidden;
  background: #f8f9fa;
  border-radius: 4px;
  border: 1px solid rgba(0, 204, 255, 0.2);
}

// 图层控制面板
.layer-control-panel {
  position: absolute;
  top: 10px;
  left: 10px;
  background: rgba(0, 30, 60, 0.9);
  border: 1px solid rgba(0, 204, 255, 0.3);
  border-radius: 6px;
  padding: 10px;
  z-index: 1000;
  min-width: 180px;

  .layer-title {
    color: #00ccff;
    font-size: 14px;
    font-weight: 600;
    margin-bottom: 8px;
  }

  .layer-list {
    display: flex;
    flex-direction: column;
    gap: 6px;
  }

  .layer-item {
    display: flex;
    align-items: center;
    gap: 8px;
    font-size: 12px;

    .layer-color-indicator {
      width: 12px;
      height: 12px;
      border-radius: 2px;
      border: 1px solid rgba(255, 255, 255, 0.3);
    }

    .layer-count {
      color: #9fbdff;
      font-size: 11px;
      margin-left: auto;
    }
  }
}

// 地图图例
.map-legend {
  position: absolute;
  bottom: 10px;
  right: 10px;
  background: rgba(0, 30, 60, 0.9);
  border: 1px solid rgba(0, 204, 255, 0.3);
  border-radius: 6px;
  padding: 10px;
  z-index: 1000;

  .legend-title {
    color: #00ccff;
    font-size: 12px;
    font-weight: 600;
    margin-bottom: 8px;
  }

  .legend-items {
    display: flex;
    flex-direction: column;
    gap: 5px;
  }

  .legend-item {
    display: flex;
    align-items: center;
    gap: 8px;
    font-size: 11px;
    color: #fff;

    .legend-color {
      width: 12px;
      height: 12px;
      border-radius: 2px;
    }
  }
}

// 钻取导航
.drill-navigation {
  position: absolute;
  top: 10px;
  right: 200px;
  background: rgba(0, 30, 60, 0.9);
  border: 1px solid rgba(0, 204, 255, 0.3);
  border-radius: 6px;
  padding: 8px;
  z-index: 1000;
  display: flex;
  align-items: center;
  gap: 10px;

  .current-level {
    font-size: 12px;
    color: #00ccff;
    font-weight: 500;
  }
}

// 社区资源分布视图样式
.resource-distribution-panel {
  flex: 1;
}

.resource-type-selector {
  margin-bottom: 15px;

  :deep(.el-radio-group) {
    width: 100%;
    display: flex;

    .el-radio-button {
      flex: 1;

      .el-radio-button__inner {
        width: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 8px 4px;
        background: rgba(0, 30, 60, 0.6);
        border: 1px solid rgba(0, 204, 255, 0.2);
        color: #9fbdff;

        .el-icon {
          font-size: 16px;
          margin-bottom: 4px;
        }
      }

      &.is-active .el-radio-button__inner {
        background: rgba(0, 204, 255, 0.2);
        border-color: #00ccff;
        color: #00ccff;
      }
    }
  }
}

.resource-overview-stats {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 8px;
  margin-bottom: 15px;
}

.resource-stat-card {
  background: rgba(0, 30, 60, 0.6);
  border: 1px solid rgba(0, 204, 255, 0.2);
  border-radius: 6px;
  padding: 10px;
  text-align: center;

  .stat-value {
    font-size: 18px;
    font-weight: bold;
    color: #00ccff;
    margin-bottom: 2px;
  }

  .stat-label {
    font-size: 10px;
    color: #9fbdff;
  }
}

.resource-list-view {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.list-header {
  display: grid;
  grid-template-columns: 1fr auto auto;
  gap: 10px;
  padding: 8px 0;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
  font-size: 12px;
  color: #9fbdff;
  margin-bottom: 8px;
}

.resource-list {
  flex: 1;
  overflow-y: auto;
}

.resource-item {
  display: grid;
  grid-template-columns: 1fr auto auto;
  gap: 10px;
  padding: 8px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.05);
  transition: background 0.3s;

  &:hover {
    background: rgba(0, 204, 255, 0.1);
  }

  &.offline {
    background: rgba(255, 77, 79, 0.1);
  }
}

.resource-info {
  display: flex;
  align-items: center;
  gap: 8px;
}

/* 修复：资源图标样式 */
.resource-icon {
  width: 32px;
  height: 32px;
  border-radius: 6px;
  background-size: 20px 20px;
  background-repeat: no-repeat;
  background-position: center;
  border: 2px solid;
  flex-shrink: 0;
}

/* 路灯图标 */
.resource-icon.light {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1024 1024'%3E%3Cpath d='M511.6 3.4c-247.5 0-448.1 204-448.1 454.9 0 93.5 23 171.1 75.5 252.6 103.7 172.8 305.5 308.5 373 308.5 67.4 0 268.8-136.6 373-308.5 51.6-81.5 75.5-159.2 75.5-252.6-0.8-251-201.4-454.9-448.9-454.9z m0 821.8c-206.1 0-373.4-167.3-373.4-373.4 0-206.1 167.3-373.4 373.4-373.4C717.7 78.5 885 245.7 885 451.9c0 206.1-167.3 373.3-373.4 373.3z m0 0' fill='%2352c41a'/%3E%3Cpath d='M512 451.9m-373.4 0a373.4 373.4 0 1 0 746.8 0 373.4 373.4 0 1 0-746.8 0Z' fill='%23FFFFFF'/%3E%3Cpath d='M690 299.1v-19.2c0-26.9-21.8-48.6-48.6-48.6H381.9c-26.9 0-48.6 21.8-48.6 48.6v19.2c-19.4 6.8-32.4 25.1-32.4 45.7 0 26.9 21.8 48.6 48.6 48.6 26.9 0 48.6-21.8 48.6-48.6 0-20.6-13-38.9-32.4-45.7v-19.2c0-9 7.3-16.2 16.2-16.2h113.5v275.6h-6.8c-9 0-17.5 3.6-23.9 10-6.3 6.4-9.9 15-9.8 23.9l-7 95.8h-49.8c-8.7 0.4-15.5 7.5-15.5 16.2s6.8 15.8 15.5 16.2h243.2c9 0 16.2-7.3 16.2-16.2 0-9-7.3-16.2-16.2-16.2h-66l-7.1-97c0-17.5-15-32.7-33.7-32.7h-6.8V263.7h113.5c9 0 16.2 7.3 16.2 16.2v19.2c-19.4 6.8-32.4 25.1-32.4 45.7 0 26.9 21.8 48.6 48.6 48.6 26.9 0 48.6-21.8 48.6-48.6 0.2-20.6-12.8-38.9-32.2-45.7z m-340.5 61.8c-9 0-16.2-7.3-16.2-16.2 0-9 7.3-16.2 16.2-16.2 9 0 16.2 7.3 16.2 16.2 0 9-7.3 16.2-16.2 16.2z m186.3 212.3l7 95.8h-62.4l6.9-94.6c0-2 0.6-2.6 1.3-2.6h45.9c0.8-0.1 1.3 0.5 1.3 1.4z m137.9-212.3c-9 0-16.2-7.3-16.2-16.2 0-9 7.3-16.2 16.2-16.2 9 0 16.2 7.3 16.2 16.2 0.1 9-7.2 16.2-16.2 16.2z m0 0' fill='%2352c41a'/%3E%3C/svg%3E");
  background-color: rgba(82, 196, 26, 0.1);
  border-color: #52c41a;
}

/* 监控图标 */
.resource-icon.camera {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1024 1024'%3E%3Cpath d='M512 320.2c-80.3 0-145.7 69.5-145.7 154.8 0 41.4 15.2 80.3 42.7 109.5 27.5 29.2 64.1 45.4 103 45.4 38.8 0 75.4-16.1 102.9-45.4 27.5-29.3 42.7-68.2 42.7-109.5 0.1-85.3-65.3-154.8-145.6-154.8z m-0.5 223.7c-18.3 0-35.5-7.6-48.4-21.5-12.9-13.9-20.1-32.3-20.1-52 0-40.5 30.7-73.5 68.5-73.5s68.5 33 68.5 73.5-30.7 73.5-68.5 73.5z' fill='%231890ff'/%3E%3Cpath d='M881 421.2v-5.4c-0.8-95-40.3-184-111.2-250.7C699.5 98.9 606.6 62.5 509 63.9c-203.4 1.6-367.6 161.9-366 357.4v5.9c1.4 150.1 100.9 281.9 248.5 330.1l-48.7 125.1h-97.2c-18.5 0-36.1 11.5-40.5 29.4-6.3 25.5 13.8 48.3 39.3 48.3h534.2c18.5 0 36.1-11.5 40.5-29.5 6.2-25.5-13.8-48.2-39.3-48.2h-98.5l-48.7-125c53.7-17.5 103.1-47.3 143.3-86.6 68.7-67 106-155.7 105.1-249.6zM429 882.4l44.6-118.8c14.3 1.5 27.3 2.3 39.6 2.3h9.3c11.6-0.1 23.8-0.8 37.2-2.2l44.6 118.7H429z m237.3-233.6c-45.2 26.6-98.3 39.3-151.7 39.4H509c-157.6 0-286.9-119-288.3-265.2v-5.8C219.4 268 349 145.6 509.7 144.4h2.3c159.3 0 290 120.3 291.3 268.2v5.3c0.9 92.8-48.7 178.8-137 230.9z' fill='%231890ff'/%3E%3Cpath d='M512 280.4c21.4 0 38.8-17.4 38.8-38.8s-17.4-38.8-39-38.8c-6.6 0.2-13.3 2-19.3 5.3-11.8 6.9-19.3 19.8-19.4 33.5 0.1 21.3 17.5 38.8 38.9 38.8z' fill='%231890ff'/%3E%3C/svg%3E");
  background-color: rgba(24, 144, 255, 0.1);
  border-color: #1890ff;
}

/* 消防设施图标 */
.resource-icon.fire {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1024 1024'%3E%3Cpath d='M491.054545 0c1.070545 0 2.048 0.186182 3.072 0.325818h43.287273C538.530909 0.186182 539.461818 0 540.532364 0h10.612363a26.996364 26.996364 0 0 1 26.624 27.415273l-0.046545 0.651636c77.730909 16.663273 141.032727 73.541818 168.820363 148.48h44.125091c25.134545 0 45.428364 18.664727 45.428364 41.751273 0 23.04-20.293818 41.704727-45.428364 41.704727h-27.229091V439.854545c16.011636-3.258182 32.581818 1.024 45.149091 11.68291 12.613818 10.658909 19.921455 26.530909 19.921455 43.287272v125.253818c0 16.756364-7.307636 32.674909-19.874909 43.333819-12.613818 10.658909-29.184 14.941091-45.149091 11.682909v241.105454H837.818182V1024H193.815273v-107.845818H268.101818v-241.105455c-15.965091 3.258182-32.581818-1.024-45.149091-11.636363a56.738909 56.738909 0 0 1-19.921454-43.380364v-125.207273c0-16.756364 7.307636-32.674909 19.921454-43.333818 12.567273-10.658909 29.184-14.894545 45.149091-11.682909V260.002909H231.610182C206.568727 260.002909 186.181818 241.338182 186.181818 218.298182c0-23.086545 20.340364-41.751273 45.428364-41.751273h53.527273C312.878545 101.608727 376.180364 44.683636 453.911273 28.066909L453.864727 27.415273A26.996364 26.996364 0 0 1 480.488727 0zM512 445.067636c-84.759273 0-153.413818 70.656-153.413818 157.789091 0 87.226182 68.654545 157.882182 153.367273 157.882182 84.712727 0 153.413818-70.656 153.413818-157.835636S596.666182 445.067636 511.953455 445.067636z m-49.570909 69.352728a96.488727 96.488727 0 0 1 99.095273 0 102.353455 102.353455 0 0 1 49.338181 88.436363 102.353455 102.353455 0 0 1-49.338181 88.436364 96.488727 96.488727 0 0 1-99.141819 0 102.353455 102.353455 0 0 1-49.338181-88.436364 102.353455 102.353455 0 0 1 49.338181-88.436363z' fill='%23ff4d4f'/%3E%3C/svg%3E");
  background-color: rgba(255, 77, 79, 0.1);
  border-color: #ff4d4f;
}

/* 垃圾点图标 */
.resource-icon.garbage {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1215 1024'%3E%3Cpath d='M962.757223 434.927522a252.237202 252.237202 0 0 0-252.237203 252.237202c0 210.197669 252.237202 336.835276 252.237203 336.835276s252.237202-126.118601 252.237202-336.835276a252.237202 252.237202 0 0 0-252.237202-252.237202z m0 336.835276a84.079067 84.079067 0 1 1 84.079067-84.079068 84.079067 84.079067 0 0 1-84.079067 84.079068z' fill='%23faad14'/%3E%3Cpath d='M684.569691 687.164724a278.187532 278.187532 0 0 1 205.007602-267.288394V323.341105H80.965028v646.68221h756.1926a381.988849 381.988849 0 0 1-152.587937-282.858591z m-280.263558 120.928535H323.341105V485.271161h80.965028z m242.376077 0H565.717182V485.271161h80.965028zM970.023315 80.965028h-323.341105V0H323.341105v80.965028H0v161.411049h970.023315V80.965028z' fill='%23faad14'/%3E%3C/svg%3E");
  background-color: rgba(250, 173, 20, 0.1);
  border-color: #faad14;
}

/* 默认图标 */
.resource-icon.default {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1024 1024'%3E%3Cpath d='M512 85.333333c235.637333 0 426.666667 191.029333 426.666667 426.666667s-191.029333 426.666667-426.666667 426.666667S85.333333 747.637333 85.333333 512 276.362667 85.333333 512 85.333333z m0 64c-200.298667 0-362.666667 162.368-362.666667 362.666667s162.368 362.666667 362.666667 362.666667 362.666667-162.368 362.666667-362.666667S712.298667 149.333333 512 149.333333z m0 597.333334a42.666667 42.666667 0 1 1 0 85.333333 42.666667 42.666667 0 0 1 0-85.333333z m0-469.333334a42.666667 42.666667 0 0 1 42.666667 42.666667v256a42.666667 42.666667 0 0 1-85.333334 0V320a42.666667 42.666667 0 0 1 42.666667-42.666667z' fill='%239fbdff'/%3E%3C/svg%3E");
  background-color: rgba(159, 189, 255, 0.1);
  border-color: #9fbdff;
}

/* 离线状态的图标 */
.resource-item.offline .resource-icon {
  filter: grayscale(100%) brightness(0.7);
  opacity: 0.6;
}

.resource-details {
  .resource-name {
    font-size: 12px;
    color: #fff;
    margin-bottom: 2px;
  }

  .resource-location {
    font-size: 10px;
    color: #9fbdff;
    margin-bottom: 2px;
  }

  .resource-grid {
    font-size: 9px;
    color: #666;
  }
}

.resource-status {
  display: flex;
  align-items: center;
}

.resource-actions {
  display: flex;
  gap: 4px;
  align-items: center;
}

.list-footer {
  margin-top: 10px;
  display: flex;
  justify-content: center;
}

.realtime-alerts {
  margin-top: 15px;
}

.alerts-header {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 8px;

  h4 {
    margin: 0;
    font-size: 14px;
    color: #00ccff;
  }
}

.alerts-list {
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.alert-item {
  display: flex;
  gap: 8px;
  padding: 8px;
  background: rgba(0, 30, 60, 0.4);
  border-radius: 6px;
  border-left: 3px solid transparent;
  cursor: pointer;
  transition: all 0.3s;

  &:hover {
    background: rgba(0, 204, 255, 0.1);
  }

  &.high {
    border-left-color: #ff4d4f;
  }

  &.medium {
    border-left-color: #fa8c16;
  }

  &.low {
    border-left-color: #faad14;
  }
}

.alert-icon {
  color: #00ccff;
  font-size: 14px;
  flex-shrink: 0;
  margin-top: 2px;
}

.alert-content {
  flex: 1;

  .alert-title {
    font-size: 12px;
    color: #fff;
    margin-bottom: 2px;
    font-weight: 500;
  }

  .alert-desc {
    font-size: 10px;
    color: #9fbdff;
    margin-bottom: 4px;
  }

  .alert-meta {
    display: flex;
    justify-content: space-between;
    font-size: 9px;
    color: #666;
  }
}

// 加载状态
.map-loading {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #00ccff;
  z-index: 9999;
  background: rgba(0, 30, 60, 0.8);
  padding: 20px;
  border-radius: 8px;
  border: 1px solid rgba(0, 204, 255, 0.3);

  .loading-icon {
    font-size: 32px;
    margin-bottom: 8px;
    animation: rotate 2s linear infinite;
  }

  span {
    font-size: 14px;
    font-weight: 500;
  }
}
/* 资源图标样式 */
.resource-icon {
  width: 32px;
  height: 32px;
  border-radius: 6px;
  background-size: 20px 20px;
  background-repeat: no-repeat;
  background-position: center;
  border: 2px solid;
  flex-shrink: 0;
}

/* 路灯图标 */
.resource-icon.路灯 {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1024 1024'%3E%3Cpath d='M511.6 3.4c-247.5 0-448.1 204-448.1 454.9 0 93.5 23 171.1 75.5 252.6 103.7 172.8 305.5 308.5 373 308.5 67.4 0 268.8-136.6 373-308.5 51.6-81.5 75.5-159.2 75.5-252.6-0.8-251-201.4-454.9-448.9-454.9z m0 821.8c-206.1 0-373.4-167.3-373.4-373.4 0-206.1 167.3-373.4 373.4-373.4C717.7 78.5 885 245.7 885 451.9c0 206.1-167.3 373.3-373.4 373.3z m0 0' fill='%2352c41a'/%3E%3Cpath d='M512 451.9m-373.4 0a373.4 373.4 0 1 0 746.8 0 373.4 373.4 0 1 0-746.8 0Z' fill='%23FFFFFF'/%3E%3Cpath d='M690 299.1v-19.2c0-26.9-21.8-48.6-48.6-48.6H381.9c-26.9 0-48.6 21.8-48.6 48.6v19.2c-19.4 6.8-32.4 25.1-32.4 45.7 0 26.9 21.8 48.6 48.6 48.6 26.9 0 48.6-21.8 48.6-48.6 0-20.6-13-38.9-32.4-45.7v-19.2c0-9 7.3-16.2 16.2-16.2h113.5v275.6h-6.8c-9 0-17.5 3.6-23.9 10-6.3 6.4-9.9 15-9.8 23.9l-7 95.8h-49.8c-8.7 0.4-15.5 7.5-15.5 16.2s6.8 15.8 15.5 16.2h243.2c9 0 16.2-7.3 16.2-16.2 0-9-7.3-16.2-16.2-16.2h-66l-7.1-97c0-17.5-15-32.7-33.7-32.7h-6.8V263.7h113.5c9 0 16.2 7.3 16.2 16.2v19.2c-19.4 6.8-32.4 25.1-32.4 45.7 0 26.9 21.8 48.6 48.6 48.6 26.9 0 48.6-21.8 48.6-48.6 0.2-20.6-12.8-38.9-32.2-45.7z m-340.5 61.8c-9 0-16.2-7.3-16.2-16.2 0-9 7.3-16.2 16.2-16.2 9 0 16.2 7.3 16.2 16.2 0 9-7.3 16.2-16.2 16.2z m186.3 212.3l7 95.8h-62.4l6.9-94.6c0-2 0.6-2.6 1.3-2.6h45.9c0.8-0.1 1.3 0.5 1.3 1.4z m137.9-212.3c-9 0-16.2-7.3-16.2-16.2 0-9 7.3-16.2 16.2-16.2 9 0 16.2 7.3 16.2 16.2 0.1 9-7.2 16.2-16.2 16.2z m0 0' fill='%2352c41a'/%3E%3C/svg%3E");
  background-color: rgba(82, 196, 26, 0.1);
  border-color: #52c41a;
}

/* 监控图标 */
.resource-icon.监控 {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1024 1024'%3E%3Cpath d='M512 320.2c-80.3 0-145.7 69.5-145.7 154.8 0 41.4 15.2 80.3 42.7 109.5 27.5 29.2 64.1 45.4 103 45.4 38.8 0 75.4-16.1 102.9-45.4 27.5-29.3 42.7-68.2 42.7-109.5 0.1-85.3-65.3-154.8-145.6-154.8z m-0.5 223.7c-18.3 0-35.5-7.6-48.4-21.5-12.9-13.9-20.1-32.3-20.1-52 0-40.5 30.7-73.5 68.5-73.5s68.5 33 68.5 73.5-30.7 73.5-68.5 73.5z' fill='%231890ff'/%3E%3Cpath d='M881 421.2v-5.4c-0.8-95-40.3-184-111.2-250.7C699.5 98.9 606.6 62.5 509 63.9c-203.4 1.6-367.6 161.9-366 357.4v5.9c1.4 150.1 100.9 281.9 248.5 330.1l-48.7 125.1h-97.2c-18.5 0-36.1 11.5-40.5 29.4-6.3 25.5 13.8 48.3 39.3 48.3h534.2c18.5 0 36.1-11.5 40.5-29.5 6.2-25.5-13.8-48.2-39.3-48.2h-98.5l-48.7-125c53.7-17.5 103.1-47.3 143.3-86.6 68.7-67 106-155.7 105.1-249.6zM429 882.4l44.6-118.8c14.3 1.5 27.3 2.3 39.6 2.3h9.3c11.6-0.1 23.8-0.8 37.2-2.2l44.6 118.7H429z m237.3-233.6c-45.2 26.6-98.3 39.3-151.7 39.4H509c-157.6 0-286.9-119-288.3-265.2v-5.8C219.4 268 349 145.6 509.7 144.4h2.3c159.3 0 290 120.3 291.3 268.2v5.3c0.9 92.8-48.7 178.8-137 230.9z' fill='%231890ff'/%3E%3Cpath d='M512 280.4c21.4 0 38.8-17.4 38.8-38.8s-17.4-38.8-39-38.8c-6.6 0.2-13.3 2-19.3 5.3-11.8 6.9-19.3 19.8-19.4 33.5 0.1 21.3 17.5 38.8 38.9 38.8z' fill='%231890ff'/%3E%3C/svg%3E");
  background-color: rgba(24, 144, 255, 0.1);
  border-color: #1890ff;
}

/* 消防设施图标 */
.resource-icon.消防设施 {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1024 1024'%3E%3Cpath d='M491.054545 0c1.070545 0 2.048 0.186182 3.072 0.325818h43.287273C538.530909 0.186182 539.461818 0 540.532364 0h10.612363a26.996364 26.996364 0 0 1 26.624 27.415273l-0.046545 0.651636c77.730909 16.663273 141.032727 73.541818 168.820363 148.48h44.125091c25.134545 0 45.428364 18.664727 45.428364 41.751273 0 23.04-20.293818 41.704727-45.428364 41.704727h-27.229091V439.854545c16.011636-3.258182 32.581818 1.024 45.149091 11.68291 12.613818 10.658909 19.921455 26.530909 19.921455 43.287272v125.253818c0 16.756364-7.307636 32.674909-19.874909 43.333819-12.613818 10.658909-29.184 14.941091-45.149091 11.682909v241.105454H837.818182V1024H193.815273v-107.845818H268.101818v-241.105455c-15.965091 3.258182-32.581818-1.024-45.149091-11.636363a56.738909 56.738909 0 0 1-19.921454-43.380364v-125.207273c0-16.756364 7.307636-32.674909 19.921454-43.333818 12.567273-10.658909 29.184-14.894545 45.149091-11.682909V260.002909H231.610182C206.568727 260.002909 186.181818 241.338182 186.181818 218.298182c0-23.086545 20.340364-41.751273 45.428364-41.751273h53.527273C312.878545 101.608727 376.180364 44.683636 453.911273 28.066909L453.864727 27.415273A26.996364 26.996364 0 0 1 480.488727 0zM512 445.067636c-84.759273 0-153.413818 70.656-153.413818 157.789091 0 87.226182 68.654545 157.882182 153.367273 157.882182 84.712727 0 153.413818-70.656 153.413818-157.835636S596.666182 445.067636 511.953455 445.067636z m-49.570909 69.352728a96.488727 96.488727 0 0 1 99.095273 0 102.353455 102.353455 0 0 1 49.338181 88.436363 102.353455 102.353455 0 0 1-49.338181 88.436364 96.488727 96.488727 0 0 1-99.141819 0 102.353455 102.353455 0 0 1-49.338181-88.436364 102.353455 102.353455 0 0 1 49.338181-88.436363z' fill='%23ff4d4f'/%3E%3C/svg%3E");
  background-color: rgba(255, 77, 79, 0.1);
  border-color: #ff4d4f;
}

/* 垃圾点图标 */
.resource-icon.垃圾点 {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1215 1024'%3E%3Cpath d='M962.757223 434.927522a252.237202 252.237202 0 0 0-252.237203 252.237202c0 210.197669 252.237202 336.835276 252.237203 336.835276s252.237202-126.118601 252.237202-336.835276a252.237202 252.237202 0 0 0-252.237202-252.237202z m0 336.835276a84.079067 84.079067 0 1 1 84.079067-84.079068 84.079067 84.079067 0 0 1-84.079067 84.079068z' fill='%23faad14'/%3E%3Cpath d='M684.569691 687.164724a278.187532 278.187532 0 0 1 205.007602-267.288394V323.341105H80.965028v646.68221h756.1926a381.988849 381.988849 0 0 1-152.587937-282.858591z m-280.263558 120.928535H323.341105V485.271161h80.965028z m242.376077 0H565.717182V485.271161h80.965028zM970.023315 80.965028h-323.341105V0H323.341105v80.965028H0v161.411049h970.023315V80.965028z' fill='%23faad14'/%3E%3C/svg%3E");
  background-color: rgba(250, 173, 20, 0.1);
  border-color: #faad14;
}

/* 离线状态的图标 */
.resource-item.offline .resource-icon {
  filter: grayscale(100%) brightness(0.7);
  opacity: 0.6;
}

// 响应式设计调整
@media (max-width: 1400px) {
  .left-column {
    flex: 0 0 280px;
  }

  .right-column {
    flex: 0 0 250px;
  }

  .filter-controls-group {
    :deep(.el-select) {
      width: 90px;
    }
  }
}

@media (max-width: 1200px) {
  .content-layout {
    flex-direction: column;
  }

  .left-column, .center-column, .right-column {
    flex: none;
    width: 100%;
  }

  .left-column, .right-column {
    flex-direction: row;
  }

  .population-overview-panel, .resource-distribution-panel {
    flex: 1;
  }

  .map-controls-toolbar {
    flex-wrap: wrap;
    gap: 4px;
  }

  .filter-controls-group {
    :deep(.el-select) {
      width: 80px;
    }
  }

  // 在移动端调整指标卡片布局
  .indicators-cards {
    flex-wrap: wrap;
    gap: 10px;
  }

  .indicator-card {
    flex: 0 0 calc(50% - 5px);
    min-height: 120px;
  }
}

@media (max-width: 768px) {
  .mainbox {
    padding: 5px 0;
    height: calc(100% - 60px);
    gap: 5px;
  }

  .content-layout {
    height: calc(100% - 250px);
  }

  .core-indicators-section {
    height: 240px;
  }

  .indicators-cards {
    gap: 8px;
  }

  .indicator-card {
    flex: 0 0 100%;
    min-height: 100px;
    padding: 15px;
  }

  .indicator-value {
    font-size: 28px;
  }

  .indicator-name {
    font-size: 14px;
  }
}

// Element Plus 组件样式覆盖
:deep(.el-select) {
  .el-input__wrapper {
    background: rgba(0, 30, 60, 0.6);
    border: 1px solid rgba(0, 204, 255, 0.3);
    box-shadow: none;

    .el-input__inner {
      color: #fff;
    }
  }

  .el-select__caret {
    color: #00ccff;
  }
}

:deep(.el-dialog) {
  background: rgba(0, 20, 40, 0.95);
  border: 1px solid rgba(0, 204, 255, 0.3);
  max-width: 50% !important;
  margin: 0 auto !important;
  top: 50% !important;
  transform: translateY(-50%) !important;

  .el-dialog__header {
    background: rgba(0, 30, 60, 0.8);
    border-bottom: 1px solid rgba(0, 204, 255, 0.2);

    .el-dialog__title {
      color: #00ccff;
      font-size: 16px;
      font-weight: 600;
    }
  }

  .el-dialog__body {
    color: #fff;
    max-height: 60vh;
    overflow-y: auto;
  }
}

:deep(.el-tag) {
  border: none;
  font-weight: 500;

  &.el-tag--success {
    background: rgba(82, 196, 26, 0.2);
    color: #52c41a;
  }
  &.el-tag--warning {
    background: rgba(250, 173, 20, 0.2);
    color: #faad14;
  }
  &.el-tag--danger {
    background: rgba(245, 34, 45, 0.2);
    color: #f5222d;
  }
  &.el-tag--info {
    background: rgba(24, 144, 255, 0.2);
    color: #1890ff;
  }
}
.resource-item.offline {
  background: rgba(255, 77, 79, 0.05) !important;
  border-left: 3px solid #ff4d4f;
}

.resource-item.abnormal {
  background: rgba(250, 173, 20, 0.05) !important;
  border-left: 3px solid #faad14;
}

.resource-item:hover {
  background: rgba(0, 204, 255, 0.1) !important;
  transform: translateX(2px);
  transition: all 0.3s ease;
}

/* 预警项状态样式 */
.alert-item {
  cursor: pointer;
  transition: all 0.3s ease;
}

.alert-item:hover {
  background: rgba(0, 204, 255, 0.1) !important;
  transform: translateX(2px);
}

.alert-status {
  font-size: 10px;
  padding: 2px 6px;
  border-radius: 10px;
  white-space: nowrap;
}

.alert-status.pending {
  background: rgba(255, 77, 79, 0.2);
  color: #ff4d4f;
}

.alert-status.processing {
  background: rgba(250, 173, 20, 0.2);
  color: #faad14;
}

.alert-status.resolved {
  background: rgba(82, 196, 26, 0.2);
  color: #52c41a;
}

/* 服务点图标样式 */
.resource-icon.service {
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 1024 1024'%3E%3Cpath d='M512 85.333333c235.637333 0 426.666667 191.029333 426.666667 426.666667s-191.029333 426.666667-426.666667 426.666667S85.333333 747.637333 85.333333 512 276.362667 85.333333 512 85.333333z m0 64c-200.298667 0-362.666667 162.368-362.666667 362.666667s162.368 362.666667 362.666667 362.666667 362.666667-162.368 362.666667-362.666667S712.298667 149.333333 512 149.333333z m0 597.333334a42.666667 42.666667 0 1 1 0 85.333333 42.666667 42.666667 0 0 1 0-85.333333z m0-469.333334a42.666667 42.666667 0 0 1 42.666667 42.666667v256a42.666667 42.666667 0 0 1-85.333334 0V320a42.666667 42.666667 0 0 1 42.666667-42.666667z' fill='%231890ff'/%3E%3C/svg%3E");
  background-color: rgba(24, 144, 255, 0.1);
  border-color: #1890ff;
}
</style>
