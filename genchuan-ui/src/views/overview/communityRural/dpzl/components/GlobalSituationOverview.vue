<template>
  <div class="page-container">
    <!-- 加载遮罩 -->
    <div v-if="loading" class="loading-overlay">
      <div class="loading-content">
        <el-icon class="is-loading" color="#00ccff" size="32"><Loading /></el-icon>
        <div class="loading-text">数据加载中...</div>
      </div>
    </div>

    <div class="mainbox">
      <!-- 上部布局：筛选面板、地图和核心指标 -->
      <div class="top-layout">
        <!-- 左侧：筛选面板 -->
        <div class="top_left">
          <div class="panel filter-panel-panel">
            <div class="panel-header">
              <h2><el-icon><Filter /></el-icon> 数据筛选</h2>
              <div class="header-actions">
                <el-button size="small" @click="resetFilters">
                  <el-icon><Refresh /></el-icon>重置
                </el-button>
              </div>
            </div>
            <div class="panel-body scrollable-body">
              <div class="filter-panel">
                <div class="filter-section">
                  <div class="filter-title">
                    <el-icon><MapLocation /></el-icon> 行政区划筛选
                  </div>
                  <div class="filter-content">
                    <el-cascader
                      v-model="areaFilter"
                      :options="areaOptions"
                      placeholder="选择行政区划"
                      style="width: 100%"
                      clearable
                      :props="{ checkStrictly: true, emitPath: false }"
                      @change="handleAreaChange"
                    />
                  </div>
                </div>

                <div class="filter-section">
                  <div class="filter-title">
                    <el-icon><DataBoard /></el-icon> 数据类型
                  </div>
                  <div class="filter-content">
                    <el-checkbox-group v-model="dataTypes" class="vertical-checkbox-group" @change="handleDataTypeChange">
                      <el-checkbox label="resource">资源点位</el-checkbox>
                      <el-checkbox label="event">事件点位</el-checkbox>
                      <el-checkbox label="facility">基础设施</el-checkbox>
                    </el-checkbox-group>
                  </div>
                </div>

                <div class="filter-section">
                  <div class="filter-title">
                    <el-icon><Calendar /></el-icon> 时间范围
                  </div>
                  <div class="filter-content">
                    <el-date-picker
                      v-model="timeRange"
                      type="daterange"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                      style="width: 100%"
                      value-format="YYYY-MM-DD"
                      @change="handleTimeRangeChange"
                    />
                  </div>
                </div>

                <div class="filter-section">
                  <div class="filter-title">
                    <el-icon><TrendCharts /></el-icon> 数据统计
                  </div>
                  <div class="stats-content">
                    <div class="stat-item">
                      <span class="stat-label">资源总数:</span>
                      <span class="stat-value">{{ filteredResourceData.length }}</span>
                    </div>
                    <div class="stat-item">
                      <span class="stat-label">事件总数:</span>
                      <span class="stat-value">{{ filteredEventData.length }}</span>
                    </div>
                    <div class="stat-item">
                      <span class="stat-label">一级事件:</span>
                      <span class="stat-value danger">{{ getEventCountByLevel(1) }}</span>
                    </div>
                    <div class="stat-item">
                      <span class="stat-label">二级事件:</span>
                      <span class="stat-value warning">{{ getEventCountByLevel(2) }}</span>
                    </div>
                    <div class="stat-item">
                      <span class="stat-label">三级事件:</span>
                      <span class="stat-value primary">{{ getEventCountByLevel(3) }}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>

        <!-- 中间：全域数据地图（集成资源分布） -->
        <div class="top_middle">
          <div class="panel rural-map-panel">
            <div class="panel-header">
              <h2><el-icon><MapLocation /></el-icon> 乡村全域数据地图</h2>
              <div class="header-actions">
                <el-button size="small" @click="refreshData">
                  <el-icon><Refresh /></el-icon>刷新
                </el-button>
                <el-button size="small" type="primary" @click="exportMapData">
                  <el-icon><Download /></el-icon>导出
                </el-button>
              </div>
            </div>
            <div class="panel-body">
              <div class="map-container">
                <div class="map-main">
                  <MapCommon
                    ref="mapCommonRef"
                    idName="ruralMap"
                    :areaData="filteredAreaData"
                    :gridData="filteredGridData"
                    :resourceData="filteredResourceData"
                    :eventData="filteredEventData"
                    :filters="mapFilters"
                    :highlightedResource="highlightedResource"
                    @drillDown="handleMapDrillDown"
                    @pointClick="handlePointClick"
                    @mapLoaded="handleMapLoaded"
                    @areaSelect="handleAreaSelect"
                    @selectionChange="handleSelectionChange"
                    @resourceHighlight="handleResourceHighlight"
                  />
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>

        <!-- 右侧：核心指标 -->
        <div class="top_right">
          <div class="panel core-indicators-panel">
            <div class="panel-header">
              <h2><el-icon><TrendCharts /></el-icon> 乡村核心指标</h2>
              <div class="header-actions compact-actions">
                <el-radio-group v-model="indicatorTimeRange" @change="handleIndicatorTimeRangeChange" size="small">
                  <el-radio-button label="day">日</el-radio-button>
                  <el-radio-button label="week">周</el-radio-button>
                  <el-radio-button label="month">月</el-radio-button>
                </el-radio-group>
              </div>
            </div>
            <div class="panel-body scrollable-body">
              <div class="indicator-cards-grid">
                <div v-for="indicator in coreIndicators" :key="indicator.id"
                     :class="['indicator-card-enhanced', indicator.warning ? 'warning' : 'normal']"
                     @click="showIndicatorTrend(indicator)">
                  <div class="indicator-header">
                    <div class="indicator-title">{{ indicator.name }}</div>
                    <div class="indicator-status">
                      <el-icon v-if="indicator.warning" color="#EF4444" class="warning-icon">
                        <Warning />
                      </el-icon>
                      <el-icon v-else color="#22C55E" class="normal-icon">
                        <CircleCheck />
                      </el-icon>
                    </div>
                  </div>
                  <div class="indicator-main">
                    <div class="indicator-value-section">
                      <div class="indicator-value">
                        <animated-number
                          :value="indicator.value"
                          :formatValue="formatNumber"
                          :duration="1000"
                        />
                        <span class="indicator-unit">{{ indicator.unit }}</span>
                      </div>
                      <div class="indicator-compliance">
                        达标率: <span class="compliance-value">{{ indicator.complianceRate || '100' }}%</span>
                      </div>
                    </div>
                    <div class="indicator-trend-section">
                      <div class="mini-chart-container">
                        <div class="mini-chart" :id="'miniChart-' + indicator.id"></div>
                      </div>
                    </div>
                  </div>
                  <div class="indicator-footer">
                    <div class="indicator-yoy" :class="indicator.change >= 0 ? 'positive' : 'negative'">
                      同比: {{ indicator.change >= 0 ? '+' : '' }}{{ indicator.change }}%
                    </div>
                    <div class="indicator-update">
                      {{ formatUpdateTime(indicator.updateTime) }}
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>

      <!-- 下部布局：人口总览和资源分布列表 -->
      <div class="bottom-layout">
        <!-- 左侧：人口总览 -->
        <div class="bottom_left">
          <div class="panel population-overview-panel">
            <div class="panel-header">
              <h2><el-icon><User /></el-icon> 乡村人口总览</h2>
              <div class="header-actions compact-actions">
                <el-select v-model="populationTimeRange" placeholder="时间范围" size="small"
                           @change="handlePopulationTimeRangeChange" class="compact-filter">
                  <el-option label="近3个月" value="3months" />
                  <el-option label="近6个月" value="6months" />
                  <el-option label="近12个月" value="12months" />
                </el-select>
                <el-button size="small" type="primary" @click="exportPopulationData" class="compact-btn">
                  <el-icon><Download /></el-icon>
                </el-button>
              </div>
            </div>
            <div class="panel-body">
              <div class="population-overview">
                <div class="population-structure">
                  <!-- 性别分布 -->
                  <div class="chart-section">
                    <h3>性别分布</h3>
                    <div class="chart-container">
                      <EchartsComponents
                        type="gender"
                        :data="populationData.gender"
                        @chartClick="handleChartClick"
                      />
                    </div>
                  </div>

                  <!-- 年龄分布 -->
                  <div class="chart-section">
                    <h3>年龄分布</h3>
                    <div class="chart-container">
                      <EchartsComponents
                        type="age"
                        :data="populationData.age"
                        @chartClick="handleChartClick"
                      />
                    </div>
                  </div>

                  <!-- 户籍类型 -->
                  <div class="chart-section">
                    <h3>户籍类型</h3>
                    <div class="chart-container">
                      <EchartsComponents
                        type="household"
                        :data="populationData.household"
                        @chartClick="handleChartClick"
                      />
                    </div>
                  </div>
                </div>

                <!-- 各村人口分布 -->
                <div class="population-distribution">
                  <div class="chart-section full-height">
                    <div class="chart-header">
                      <h3>各村人口分布</h3>
                      <el-button size="small" text @click="exportVillageData" class="compact-btn">导出</el-button>
                    </div>
                    <div class="chart-container">
                      <EchartsComponents
                        type="village"
                        :data="populationData.villageDistribution"
                        @chartClick="handleChartClick"
                      />
                    </div>
                  </div>
                </div>

                <!-- 人口动态变化 -->
                <div class="population-dynamic">
                  <div class="chart-section full-height">
                    <div class="chart-header">
                      <h3>人口动态变化</h3>
                      <el-button size="small" text @click="showPopulationAnalysis" class="compact-btn">分析</el-button>
                    </div>
                    <div class="chart-container">
                      <EchartsComponents
                        type="dynamic"
                        :data="populationData.dynamic"
                        @chartClick="handleChartClick"
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>

        <!-- 右侧：资源分布列表 -->
        <div class="bottom_right">
          <div class="panel resource-distribution-panel">
            <div class="panel-header">
              <h2><el-icon><Box /></el-icon> 乡村资源分布</h2>
              <div class="header-actions compact-actions">
                <el-select v-model="resourceTypeFilter" placeholder="资源类型" size="small"
                           @change="handleResourceTypeChange" clearable class="compact-filter">
                  <el-option label="全部" value="" />
                  <el-option label="土地资源" value="land" />
                  <el-option label="水利资源" value="water" />
                  <el-option label="文旅资源" value="culture" />
                </el-select>
              </div>
            </div>
            <div class="panel-body">
              <div class="resource-distribution">
                <div class="resource-list">
                  <div class="resource-filters">
                    <el-input
                      v-model="resourceSearch"
                      placeholder="搜索资源名称"
                      :prefix-icon="Search"
                      size="small"
                      clearable
                      @input="handleResourceSearch"
                    />
                  </div>

                  <div class="resource-items-container">
                    <div class="resource-type-group" v-if="filteredLandResources.length > 0">
                      <div class="resource-type-title">土地资源 ({{ filteredLandResources.length }})</div>
                      <div class="resource-items">
                        <div v-for="resource in filteredLandResources" :key="resource.id"
                             :class="['resource-item', highlightedResource?.id === resource.id ? 'active' : '']"
                             @click="selectResource(resource)">
                          <div class="resource-name">{{ resource.name }}</div>
                          <div class="resource-info">
                            <span class="resource-status" :class="resource.status">
                              {{ getResourceStatusText(resource.status) }}
                            </span>
                            <span class="resource-usage">使用率: {{ resource.usageRate }}%</span>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div class="resource-type-group" v-if="filteredWaterResources.length > 0">
                      <div class="resource-type-title">水利资源 ({{ filteredWaterResources.length }})</div>
                      <div class="resource-items">
                        <div v-for="resource in filteredWaterResources" :key="resource.id"
                             :class="['resource-item', highlightedResource?.id === resource.id ? 'active' : '']"
                             @click="selectResource(resource)">
                          <div class="resource-name">{{ resource.name }}</div>
                          <div class="resource-info">
                            <span class="resource-status" :class="resource.status">
                              {{ getResourceStatusText(resource.status) }}
                            </span>
                            <span class="resource-usage">使用率: {{ resource.usageRate }}%</span>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div class="resource-type-group" v-if="filteredCultureResources.length > 0">
                      <div class="resource-type-title">文旅资源 ({{ filteredCultureResources.length }})</div>
                      <div class="resource-items">
                        <div v-for="resource in filteredCultureResources" :key="resource.id"
                             :class="['resource-item', highlightedResource?.id === resource.id ? 'active' : '']"
                             @click="selectResource(resource)">
                          <div class="resource-name">{{ resource.name }}</div>
                          <div class="resource-info">
                            <span class="resource-status" :class="resource.status">
                              {{ getResourceStatusText(resource.status) }}
                            </span>
                            <span class="resource-usage">使用率: {{ resource.usageRate }}%</span>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div class="no-resources" v-if="totalFilteredResources === 0">
                      <el-empty description="暂无资源数据" :image-size="50" />
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
      </div>
    </div>

    <!-- 指标趋势弹窗 -->
    <el-dialog v-model="indicatorTrendVisible" :title="currentIndicator?.name + '趋势分析'" width="900px">
      <div class="indicator-trend-content">
        <div class="trend-chart">
          <div class="trend-header">
            <div class="trend-title">{{ currentIndicator?.name }}趋势图</div>
            <div class="trend-period">时间范围: {{ indicatorTimeRange === 'day' ? '日' : indicatorTimeRange === 'week' ? '周' : '月' }}</div>
          </div>
          <div class="chart-placeholder">
            <EchartsComponents
              v-if="currentIndicator"
              type="trend"
              :data="currentIndicator"
            />
          </div>
        </div>
        <div class="trend-analysis">
          <h4>分析说明</h4>
          <p>{{ getTrendAnalysis(currentIndicator) }}</p>
        </div>
      </div>
      <template #footer>
        <el-button @click="indicatorTrendVisible = false">关闭</el-button>
        <el-button type="primary" @click="exportIndicatorData">导出数据</el-button>
      </template>
    </el-dialog>

    <!-- 点位详情弹窗 -->
    <el-dialog v-model="pointDetailVisible" :title="currentPoint?.name || '点位详情'" width="650px">
      <div class="point-detail-content" v-if="currentPoint">
        <el-descriptions :column="1" border>
          <el-descriptions-item label="名称">{{ currentPoint.name }}</el-descriptions-item>
          <el-descriptions-item label="类型">
            <el-tag :type="getPointTypeTag(currentPoint.type)">
              {{ getPointTypeName(currentPoint.type) }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="状态">
            <el-tag :type="getPointStatusTag(currentPoint.status)">
              {{ getPointStatusName(currentPoint.status) }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="等级" v-if="currentPoint.level">
            <el-tag :type="getEventLevelTag(currentPoint.level)">
              {{ getEventLevelName(currentPoint.level) }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="位置坐标">
            {{ currentPoint.x?.toFixed(6) }}, {{ currentPoint.y?.toFixed(6) }}
          </el-descriptions-item>
          <el-descriptions-item label="创建时间">{{ formatDate(currentPoint.createTime) }}</el-descriptions-item>
        </el-descriptions>

        <div class="point-extra-info" v-if="currentPoint.extraInfo">
          <h3>详细信息</h3>
          <div class="info-grid">
            <div v-for="(value, key) in currentPoint.extraInfo" :key="key" class="info-item">
              <span class="info-label">{{ key }}:</span>
              <span class="info-value">{{ value }}</span>
            </div>
          </div>
        </div>
      </div>
      <template #footer>
        <el-button @click="pointDetailVisible = false">关闭</el-button>
        <el-button type="primary" @click="viewRelatedData">查看关联数据</el-button>
      </template>
    </el-dialog>

    <!-- 资源详情弹窗 -->
    <el-dialog v-model="resourceDetailVisible" :title="selectedResource?.name || '资源详情'" width="800px">
      <div class="resource-detail-content" v-if="selectedResource">
        <el-tabs v-model="resourceDetailTab" class="resource-tabs">
          <el-tab-pane label="基本信息" name="basic">
            <el-descriptions :column="2" border>
              <el-descriptions-item label="资源名称">{{ selectedResource.name }}</el-descriptions-item>
              <el-descriptions-item label="资源类型">
                <el-tag :type="getResourceTypeTag(selectedResource.type)">
                  {{ getResourceTypeName(selectedResource.type) }}
                </el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="当前状态">
                <el-tag :type="getResourceStatusTag(selectedResource.status)">
                  {{ getResourceStatusText(selectedResource.status) }}
                </el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="使用率">
                <el-progress
                  :percentage="selectedResource.usageRate"
                  :status="getUsageRateStatus(selectedResource.usageRate)"
                  :show-text="true"
                />
              </el-descriptions-item>
              <el-descriptions-item label="位置信息">{{ selectedResource.location }}</el-descriptions-item>
              <el-descriptions-item label="权属信息">{{ selectedResource.ownership }}</el-descriptions-item>
              <el-descriptions-item label="创建时间">{{ formatDate(selectedResource.createTime) }}</el-descriptions-item>
            </el-descriptions>
          </el-tab-pane>
        </el-tabs>
      </div>
      <template #footer>
        <el-button @click="resourceDetailVisible = false">关闭</el-button>
        <el-button type="primary" @click="editResource">编辑资源</el-button>
      </template>
    </el-dialog>

    <!-- 人口分析弹窗 -->
    <el-dialog v-model="populationAnalysisVisible" title="人口分析说明" width="600px">
      <div class="population-analysis-content">
        <h3>人口结构分析</h3>
        <p>当前乡村人口总数为 {{ populationData.gender.male + populationData.gender.female }} 人，人口结构呈现以下特点：</p>

        <div class="analysis-section">
          <h4>性别结构</h4>
          <p>男性占比 {{ populationData.gender.malePercentage }}%，女性占比 {{ populationData.gender.femalePercentage }}%，性别比例基本平衡。</p>
        </div>

        <div class="analysis-section">
          <h4>年龄结构</h4>
          <p>青壮年（19-60岁）占比最高，达到 {{ populationData.age[1]?.percentage }}%，劳动力资源相对充足。</p>
          <p>老年人口（60岁以上）占比 {{ populationData.age[2]?.percentage }}%，人口老龄化趋势需要关注。</p>
        </div>

        <div class="analysis-section">
          <h4>户籍结构</h4>
          <p>常住人口占比 {{ populationData.household[0]?.percentage }}%，表明人口稳定性较好。</p>
        </div>
      </div>
      <template #footer>
        <el-button type="primary" @click="populationAnalysisVisible = false">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted, nextTick, watch } from 'vue';
import {
  ElSelect, ElOption, ElButton, ElTag, ElDialog, ElDescriptions, ElDescriptionsItem,
  ElTable, ElTableColumn, ElProgress, ElTimeline, ElTimelineItem, ElTabs, ElTabPane,
  ElCheckboxGroup, ElCheckbox, ElCascader, ElDatePicker, ElRadioGroup, ElRadioButton,
  ElInput, ElMessage, ElInputNumber, ElRate, ElEmpty
} from 'element-plus';
import {
  FullScreen, MapLocation, TrendCharts, User, Box, Download, Refresh,
  Top, Bottom, ArrowRight, Male, Female, Search, Loading, Warning, CircleCheck,
  Filter, DataBoard, Calendar, InfoFilled
} from "@element-plus/icons-vue";
import MapCommon from "./MapCommon.vue";
import EchartsComponents from "./EchartsComponents.vue";

// 导入API方法
import {
  fetchRuralGlobalOverview,
  fetchCoreIndicators,
  fetchRuralMapData,
  fetchPopulationOverview,
  fetchResourceDistribution,
  exportData
} from '@/api/overview/communityRural/GlobalSituationOverview.js';

// 响应式数据定义
const loading = ref(false);
const mapCommonRef = ref(null);

// 地图相关数据
const areaFilter = ref([]);
const dataTypes = ref(['resource', 'event']);
const timeRange = ref([]);
const areaData = ref([]);
const gridData = ref([]);
const resourceData = ref([]);
const eventData = ref([]);
const highlightedResource = ref(null);

// 核心指标相关
const coreIndicators = ref([]);
const indicatorTimeRange = ref('month');
const indicatorTrendVisible = ref(false);
const currentIndicator = ref(null);

// 人口总览相关
const populationData = ref({
  gender: { male: 0, female: 0, malePercentage: 0, femalePercentage: 0 },
  age: [],
  household: [],
  villageDistribution: [],
  dynamic: { monthlyIncrease: 0, monthlyDecrease: 0, netGrowth: 0, trend: [] }
});
const populationTimeRange = ref('12months');
const populationAnalysisVisible = ref(false);
const currentTrendPoint = ref(null);

// 资源分布相关
const resourceTypeFilter = ref('');
const resourceStatusFilter = ref('');
const resourceSearch = ref('');
const resources = ref([]);
const selectedResource = ref(null);
const resourceDetailVisible = ref(false);
const resourceDetailTab = ref('basic');

// 点位详情相关
const pointDetailVisible = ref(false);
const currentPoint = ref(null);

// 计算属性
const mapFilters = computed(() => ({
  area: areaFilter.value,
  dataTypes: dataTypes.value,
  timeRange: timeRange.value
}));

const filteredAreaData = computed(() => {
  return areaData.value.filter(area => {
    if (!areaFilter.value) return true;
    return area.id === areaFilter.value;
  });
});

const filteredGridData = computed(() => {
  return gridData.value;
});

const filteredResourceData = computed(() => {
  return resourceData.value.filter(resource => {
    if (areaFilter.value && !resource.areaIds.includes(areaFilter.value)) {
      return false;
    }
    if (timeRange.value.length > 0) {
      const createTime = new Date(resource.createTime);
      if (createTime < new Date(timeRange.value[0]) || createTime > new Date(timeRange.value[1])) {
        return false;
      }
    }
    return dataTypes.value.includes('resource');
  });
});

const filteredEventData = computed(() => {
  return eventData.value.filter(event => {
    if (areaFilter.value && !event.areaIds.includes(areaFilter.value)) {
      return false;
    }
    if (timeRange.value.length > 0) {
      const createTime = new Date(event.createTime);
      if (createTime < new Date(timeRange.value[0]) || createTime > new Date(timeRange.value[1])) {
        return false;
      }
    }
    return dataTypes.value.includes('event');
  });
});

const filteredLandResources = computed(() => {
  return resources.value.filter(resource =>
    resource.type === 'land' &&
    (resourceTypeFilter.value === '' || resource.type === resourceTypeFilter.value) &&
    (resourceStatusFilter.value === '' || resource.status === resourceStatusFilter.value) &&
    (resourceSearch.value === '' || resource.name.toLowerCase().includes(resourceSearch.value.toLowerCase()))
  );
});

const filteredWaterResources = computed(() => {
  return resources.value.filter(resource =>
    resource.type === 'water' &&
    (resourceTypeFilter.value === '' || resource.type === resourceTypeFilter.value) &&
    (resourceStatusFilter.value === '' || resource.status === resourceStatusFilter.value) &&
    (resourceSearch.value === '' || resource.name.toLowerCase().includes(resourceSearch.value.toLowerCase()))
  );
});

const filteredCultureResources = computed(() => {
  return resources.value.filter(resource =>
    resource.type === 'culture' &&
    (resourceTypeFilter.value === '' || resource.type === resourceTypeFilter.value) &&
    (resourceStatusFilter.value === '' || resource.status === resourceStatusFilter.value) &&
    (resourceSearch.value === '' || resource.name.toLowerCase().includes(resourceSearch.value.toLowerCase()))
  );
});

const totalFilteredResources = computed(() => {
  return filteredLandResources.value.length + filteredWaterResources.value.length + filteredCultureResources.value.length;
});

// 行政区划选项
const areaOptions = ref([
  {
    value: 'fujian',
    label: '福建省'
  },
  {
    value: 'fuzhou',
    label: '福州市'
  },
  {
    value: 'xiamen',
    label: '厦门市'
  },
  {
    value: 'gulou',
    label: '鼓楼区'
  },
  {
    value: 'minhou',
    label: '闽侯县'
  }
]);

// 方法定义
const handleAreaChange = () => {
  refreshMapData();
};

const handleDataTypeChange = () => {
  refreshMapData();
};

const handleTimeRangeChange = () => {
  refreshMapData();
};

const handleIndicatorTimeRangeChange = () => {
  refreshCoreIndicators();
};

const handlePopulationTimeRangeChange = () => {
  refreshPopulationData();
};

const handleResourceTypeChange = () => {
  refreshResourceData();
};

const handleResourceStatusChange = () => {
  refreshResourceData();
};

const handleResourceSearch = () => {
  // 资源搜索实时过滤
};

const resetFilters = () => {
  areaFilter.value = [];
  dataTypes.value = ['resource', 'event'];
  timeRange.value = [];
  refreshMapData();
  ElMessage.success('筛选条件已重置');
};

// 数据刷新函数
const refreshData = async () => {
  loading.value = true;
  try {
    await Promise.all([
      refreshMapData(),
      refreshCoreIndicators(),
      refreshPopulationData(),
      refreshResourceData()
    ]);
    ElMessage.success('数据刷新成功');
  } catch (error) {
    console.error('数据刷新失败:', error);
    ElMessage.error('数据加载失败');
  } finally {
    loading.value = false;
  }
};

const refreshMapData = async () => {
  try {
    const mapData = await fetchRuralMapData({
      area: areaFilter.value,
      dataTypes: dataTypes.value,
      timeRange: timeRange.value
    });

    areaData.value = mapData.areaData || [];
    gridData.value = mapData.gridData || [];
    resourceData.value = mapData.resourceData || [];
    eventData.value = mapData.eventData || [];
  } catch (error) {
    console.error('地图数据刷新失败:', error);
  }
};

const refreshCoreIndicators = async () => {
  try {
    const indicators = await fetchCoreIndicators({
      timeRange: indicatorTimeRange.value
    });
    coreIndicators.value = indicators;
  } catch (error) {
    console.error('核心指标刷新失败:', error);
  }
};

const refreshPopulationData = async () => {
  try {
    populationData.value = await fetchPopulationOverview({
      timeRange: populationTimeRange.value
    });
  } catch (error) {
    console.error('人口数据刷新失败:', error);
  }
};

const refreshResourceData = async () => {
  try {
    const resourceData = await fetchResourceDistribution({
      type: resourceTypeFilter.value,
      status: resourceStatusFilter.value
    });
    resources.value = resourceData.resources || [];
  } catch (error) {
    console.error('资源数据刷新失败:', error);
  }
};

// 地图交互
const handleMapDrillDown = (data) => {
  console.log('地图下钻:', data);
  areaFilter.value = data.areaId;
  refreshMapData();
  ElMessage.info(`已下钻到 ${data.areaName}`);
};

const handlePointClick = (point) => {
  currentPoint.value = point;
  pointDetailVisible.value = true;
};

const handleMapLoaded = () => {
  console.log('地图加载完成');
};

const handleAreaSelect = (area) => {
  areaFilter.value = area.id;
  refreshMapData();
};

const handleSelectionChange = (selection) => {
  console.log('区域选择:', selection);
  ElMessage.info(`选中了 ${selection.points.length} 个点位`);
};

const handleResourceHighlight = (resource) => {
  highlightedResource.value = resource;
};

// 资源选择
const selectResource = (resource) => {
  selectedResource.value = resource;
  highlightedResource.value = resource;
  resourceDetailVisible.value = true;

  // 在地图上高亮显示该资源
  if (mapCommonRef.value) {
    mapCommonRef.value.highlightResource(resource);
  }
};

// 指标趋势
const showIndicatorTrend = (indicator) => {
  currentIndicator.value = indicator;
  indicatorTrendVisible.value = true;
};

const getTrendAnalysis = (indicator) => {
  const analysisMap = {
    '乡村总人口': `人口总量为 ${indicator.value} 人，${indicator.change >= 0 ? '同比增长' : '同比减少'} ${Math.abs(indicator.change)}%，人口结构保持稳定。`,
    '集体经济总收入': `集体经济收入达 ${indicator.value} 万元，${indicator.change >= 0 ? '同比增长' : '同比减少'} ${Math.abs(indicator.change)}%，经济发展态势良好。`,
    '事件总数': `累计处理事件 ${indicator.value} 件，${indicator.change >= 0 ? '同比增加' : '同比减少'} ${Math.abs(indicator.change)}%，治理效能持续提升。`,
    '资源总数': `资源总量为 ${indicator.value} 个，${indicator.change >= 0 ? '同比增长' : '同比减少'} ${Math.abs(indicator.change)}%，资源配置不断优化。`
  };
  return analysisMap[indicator?.name] || '暂无详细分析数据。';
};

// 工具函数
const getPointTypeTag = (type) => {
  const tagMap = {
    'resource': 'success',
    'event': 'danger',
    'facility': 'info'
  };
  return tagMap[type] || 'info';
};

const getPointTypeName = (type) => {
  const nameMap = {
    'resource': '资源点位',
    'event': '事件点位',
    'facility': '基础设施'
  };
  return nameMap[type] || type;
};

const getPointStatusTag = (status) => {
  const tagMap = {
    'normal': 'success',
    'warning': 'warning',
    'danger': 'danger',
    'active': 'danger',
    'processing': 'warning',
    'resolved': 'success'
  };
  return tagMap[status] || 'info';
};

const getPointStatusName = (status) => {
  const nameMap = {
    'normal': '正常',
    'warning': '预警',
    'danger': '危险',
    'active': '活跃',
    'processing': '处理中',
    'resolved': '已解决'
  };
  return nameMap[status] || status;
};

const getEventLevelTag = (level) => {
  const tagMap = {
    1: 'danger',
    2: 'warning',
    3: 'primary'
  };
  return tagMap[level] || 'info';
};

const getEventLevelName = (level) => {
  const nameMap = {
    1: '一级事件',
    2: '二级事件',
    3: '三级事件'
  };
  return nameMap[level] || `等级${level}`;
};

const getResourceTypeName = (type) => {
  const nameMap = {
    'land': '土地资源',
    'water': '水利资源',
    'culture': '文旅资源'
  };
  return nameMap[type] || type;
};

const getResourceTypeTag = (type) => {
  const tagMap = {
    'land': 'success',
    'water': 'primary',
    'culture': 'warning'
  };
  return tagMap[type] || 'info';
};

const getResourceStatusTag = (status) => {
  const tagMap = {
    'using': 'success',
    'idle': 'warning',
    'maintenance': 'danger'
  };
  return tagMap[status] || 'info';
};

const getResourceStatusText = (status) => {
  const textMap = {
    'using': '在用',
    'idle': '闲置',
    'maintenance': '维护'
  };
  return textMap[status] || status;
};

const getUsageRateStatus = (rate) => {
  if (rate >= 80) return 'success';
  if (rate >= 50) return 'warning';
  return 'exception';
};

const formatDate = (dateString) => {
  if (!dateString) return '';
  const date = new Date(dateString);
  return date.toLocaleString('zh-CN', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit'
  });
};

const formatNumber = (value) => {
  return new Intl.NumberFormat('zh-CN').format(value);
};

const getEventCountByLevel = (level) => {
  return eventData.value.filter(event => event.level === level).length;
};

const formatUpdateTime = (timeStr) => {
  if (!timeStr) return '';
  const date = new Date(timeStr);
  return `${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')}`;
};

// 导出功能
const exportPopulationData = async () => {
  try {
    await exportData('population', {
      timeRange: populationTimeRange.value
    });
    ElMessage.success('人口数据导出成功');
  } catch (error) {
    ElMessage.error('导出失败: ' + error.message);
  }
};

const exportVillageData = async () => {
  try {
    await exportData('village', {});
    ElMessage.success('村庄数据导出成功');
  } catch (error) {
    ElMessage.error('导出失败: ' + error.message);
  }
};

const exportIndicatorData = async () => {
  try {
    await exportData('indicator', {
      indicatorId: currentIndicator.value?.id,
      timeRange: indicatorTimeRange.value
    });
    ElMessage.success('指标数据导出成功');
  } catch (error) {
    ElMessage.error('导出失败: ' + error.message);
  }
};

const exportMapData = async () => {
  try {
    await exportData('map', {
      area: areaFilter.value,
      dataTypes: dataTypes.value,
      timeRange: timeRange.value
    });
    ElMessage.success('地图数据导出成功');
  } catch (error) {
    ElMessage.error('导出失败: ' + error.message);
  }
};

// 其他交互方法
const handleChartClick = (params) => {
  console.log('图表点击:', params);
  ElMessage.info(`点击了 ${params.name}: ${params.value || params.data.value}`);
};

const showPopulationAnalysis = () => {
  populationAnalysisVisible.value = true;
};

const viewRelatedData = () => {
  ElMessage.info('查看关联数据');
};

const editResource = () => {
  ElMessage.info('编辑资源功能');
};

// 数字滚动组件
const AnimatedNumber = {
  props: ['value', 'formatValue', 'duration'],
  data() {
    return {
      displayValue: 0
    };
  },
  watch: {
    value: {
      immediate: true,
      handler(newVal) {
        this.animateValue(newVal);
      }
    }
  },
  methods: {
    animateValue(newVal) {
      const start = this.displayValue;
      const end = newVal;
      const duration = this.duration || 1000;
      const startTime = performance.now();

      const animate = (currentTime) => {
        const elapsed = currentTime - startTime;
        const progress = Math.min(elapsed / duration, 1);

        this.displayValue = Math.floor(start + (end - start) * progress);

        if (progress < 1) {
          requestAnimationFrame(animate);
        }
      };

      requestAnimationFrame(animate);
    }
  },
  render() {
    return this.formatValue ? this.formatValue(this.displayValue) : this.displayValue;
  }
};

// 初始化
onMounted(() => {
  refreshData();
});

// 监听筛选条件变化
watch([areaFilter, dataTypes, timeRange], () => {
  refreshMapData();
}, { deep: true });

// 监听资源筛选条件变化
watch([resourceTypeFilter, resourceStatusFilter], () => {
  refreshResourceData();
});
</script>

<style lang="scss" scoped>
// 导入公共样式
@import './common-styles.scss';

// 弹窗内容样式调整
.indicator-trend-content,
.point-detail-content,
.resource-detail-content,
.population-analysis-content {
  max-height: 500px;
  overflow-y: auto;
  color: #333;

  .trend-analysis,
  .point-extra-info,
  .resource-tabs,
  .analysis-section {
    margin-top: 20px;
    padding: 15px;
    background: #f8fafc;
    border-radius: 6px;

    h3, h4 {
      color: #1e293b;
      margin-bottom: 10px;
    }

    p {
      color: #475569;
      line-height: 1.6;
      margin: 0;
    }
  }
}

.info-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 10px;

  .info-item {
    display: flex;
    justify-content: space-between;
    padding: 8px;
    background: white;
    border-radius: 4px;
    border: 1px solid #e2e8f0;

    .info-label {
      color: #64748b;
      font-weight: 500;
    }

    .info-value {
      color: #1e293b;
    }
  }
}

// 响应式调整
@media (max-width: 1600px) {
  .population-overview {
    grid-template-columns: 1fr;
    grid-template-rows: auto repeat(3, 1fr);

    .population-structure {
      grid-row: 2 / 4;
      grid-column: 1;
    }

    .population-distribution {
      grid-row: 4;
      grid-column: 1;
    }

    .population-dynamic {
      grid-row: 5;
      grid-column: 1;
    }
  }
}

@media (max-width: 1400px) {
  .indicator-cards-grid {
    grid-template-columns: 1fr;
    grid-template-rows: repeat(4, 1fr);
  }

  .top-layout {
    flex-direction: column;
    height: 60%;

    .top_left, .top_middle, .top_right {
      flex: none;
      height: 33.33%;
    }
  }

  .bottom-layout {
    height: 38%;
    flex-direction: column;

    .bottom_left, .bottom_right {
      flex: none;
      height: 50%;
    }
  }
}

@media (max-width: 768px) {
  .indicator-cards-grid {
    grid-template-columns: 1fr;
  }

  .resource-distribution {
    flex-direction: column;

    .resource-list, .resource-map {
      flex: none;
    }

    .resource-list {
      border-right: none;
      border-bottom: 1px solid $border-color;
      max-height: 300px;
    }
  }

  .header-actions {
    flex-wrap: wrap;
    gap: 8px;
  }
}
</style>
