<template>
  <div class="environ-sanitation-container">
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/environ-sanitation.png"
          alt="环境卫生图标"
          class="title-icon"
        />
        环境卫生专题
      </h1>
      <div class="header-actions">
        <el-button size="small" @click="refreshData">刷新数据</el-button>
        <el-button size="small" @click="handleBack">返回总览</el-button>
      </div>
    </div>

    <div class="main-content">
      <div class="left-area" style="min-width: 5vw;">
        <div class="map-container panel" ref="mapPanel">
          <div class="panel-header">
            <h3>环卫作业地图（清运点+保洁区域）</h3>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('mapPanel')">
                <el-icon color="#00c6ff" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body map-body">
            <EnvironSanitationMap
              ref="mapComponent"
              idName="environMap"
              :clearPointList="filteredClearPointList"
              :cleanAreaList="filteredCleanAreaList"
              @clear-point-click="handleClearPointClick"
              @clean-area-click="handleCleanAreaClick"
            />
          </div>
        </div>
      </div>

      <div class="right-area" style="min-width: 6vw;">
        <div class="stat-card-container">
          <div
            class="stat-card clear-rate-card"
            :class="filteredOverviewData.clearCompletionRate < 80 ? 'danger-rate' : 'normal-rate'"
          >
            <div class="stat-title">清运完成率</div>
            <div class="stat-value" :class="`count-animation ${filteredOverviewData.clearCompletionRate < 80 ? 'danger-text' : 'normal-text'}`">
              {{ filteredOverviewData.clearCompletionRate }}%
            </div>
            <div class="stat-desc">
              总清运点：{{ filteredOverviewData.totalClearPoint }}
              <br/>
              已清运/总桶数：{{ filteredOverviewData.completedClearPoint }}/{{ filteredOverviewData.totalClearPointBucket }}
            </div>
          </div>
          <div
            class="stat-card clean-rate-card"
            :class="filteredOverviewData.cleanCoverageRate < 80 ? 'danger-rate' : 'normal-rate'"
          >
            <div class="stat-title">保洁覆盖率</div>
            <div class="stat-value" :class="`count-animation ${filteredOverviewData.cleanCoverageRate < 80 ? 'danger-text' : 'normal-text'}`">
              {{ filteredOverviewData.cleanCoverageRate }}%
            </div>
            <div class="stat-desc">
              总保洁面积：{{ (filteredOverviewData.totalCleanArea / 10000).toFixed(2) }}万㎡
              <br/>
              已保洁：{{ (filteredOverviewData.cleanedArea / 10000).toFixed(2) }}万㎡
            </div>
          </div>
          <div
            class="stat-card overflow-handle-rate-card"
            :class="filteredOverviewData.overflowHandleRate < 90 ? 'danger-rate' : 'normal-rate'"
          >
            <div class="stat-title">满溢事件处置率</div>
            <div class="stat-value" :class="`count-animation ${filteredOverviewData.overflowHandleRate < 90 ? 'danger-text' : 'normal-text'}`">
              {{ filteredOverviewData.overflowHandleRate }}%
            </div>
            <div class="stat-desc">
              总事件数：{{ filteredOverviewData.overflowEventCount }}
              <br/>
              已处置：{{ filteredOverviewData.handledOverflowCount }}
              <br/>
              超时未处置：{{ filteredOverviewData.timeoutOverflowCount }}
            </div>
          </div>
        </div>

        <div class="panel filter-bar list-filter-bar">
          <el-cascader
            v-model="filterParams.region"
            :options="regionDict"
            placeholder="选择区域"
            size="small"
            :props="{ expandTrigger: 'hover', label: 'label', value: 'value' }"
            clearable
            collapse-tags
          />
          <el-select v-model="filterParams.clearStatus" placeholder="清运状态" size="small" clearable>
            <el-option label="全部" value="" />
            <el-option label="满溢" value="满溢" />
            <el-option label="待清运" value="待清运" />
            <el-option label="已清运" value="已清运" />
          </el-select>
          <el-select v-model="filterParams.cleanStatus" placeholder="保洁状态" size="small" clearable>
            <el-option label="全部" value="" />
            <el-option label="未保洁" value="未保洁" />
            <el-option label="保洁中" value="保洁中" />
            <el-option label="已保洁" value="已保洁" />
          </el-select>
          <el-select v-model="filterParams.timeRange" placeholder="时间筛选" size="small" clearable>
            <el-option label="全部" value="" />
            <el-option label="今日" value="today" />
            <el-option label="昨日" value="yesterday" />
          </el-select>
          <el-button size="small" type="success" style="margin-left: 3vw" @click="resetFilter">重 置</el-button>
        </div>

        <div class="task-list-container panel" ref="taskTablePanel">
          <div class="panel-header">
            <h3>待办任务列表</h3>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('taskTablePanel')">
                <el-icon color="#ec4899" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body table-scroll">
            <el-tabs v-model="activeTab" type="card" size="small" class="task-tabs">
              <el-tab-pane label="未清运清运点" name="unclear">
                <el-table
                  :data="filteredClearPointList.filter(item => item.clear_status === '满溢' || item.clear_status === '待清运')"
                  border
                  size="small"
                  style="width: 100%"
                  @row-click="handleTaskRowClick('clearPoint', $event)"
                  v-loading="tableLoading"
                >
                  <el-table-column prop="clear_point_id" label="清运点ID" />
                  <el-table-column prop="point_name" label="清运点名称" width="140" />
                  <el-table-column prop="clear_status" label="清运状态">
                    <template #default="scope">
                      <el-tag
                        :type="scope.row.clear_status === '满溢' ? 'danger' : 'warning'"
                      >
                        {{ scope.row.clear_status }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column prop="bucket_remaining" label="剩余桶数" />
                  <el-table-column prop="responsible_person" label="负责人" />
                  <el-table-column prop="plan_clear_time" label="计划清运时间" width="140" />
                  <el-table-column label="操作" min-width="110">
                    <template #default="scope">
                      <el-button
                        size="small"
                        type="primary"
                        @click.stop="markClearCompleted(scope.row)"
                      >
                        标记已清运
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <el-tab-pane label="未保洁区域" name="unclean">
                <el-table
                  :data="filteredCleanAreaList.filter(item => item.clean_status === '未保洁' || item.clean_status === '保洁中')"
                  border
                  size="small"
                  style="width: 100%"
                  @row-click="handleTaskRowClick('cleanArea', $event)"
                  v-loading="tableLoading"
                >
                  <el-table-column prop="clean_area_id" label="区域ID" />
                  <el-table-column prop="area_name" label="保洁区域名称" min-width="100" />
                  <el-table-column prop="clean_status" label="保洁状态">
                    <template #default="scope">
                      <el-tag
                        :type="scope.row.clean_status === '未保洁' ? 'danger' : 'info'"
                      >
                        {{ scope.row.clean_status }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column prop="coverage_rate" label="覆盖率(%)" />
                  <el-table-column prop="responsible_person" label="负责人" />
                  <el-table-column prop="plan_clean_time" label="计划保洁时间" width="140" />
                  <el-table-column label="操作" min-width="110">
                    <template #default="scope">
                      <el-button
                        size="small"
                        type="primary"
                        @click.stop="markCleanCompleted(scope.row)"
                      >
                        标记已保洁
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <el-tab-pane label="未处置满溢事件" name="overflow">
                <el-table
                  :data="filteredOverflowEventList.filter(item => item.handle_status === '未处置' || item.handle_status === '处置中')"
                  border
                  size="small"
                  style="width: 100%"
                  @row-click="handleTaskRowClick('overflow', $event)"
                  v-loading="tableLoading"
                >
                  <el-table-column prop="overflow_event_id" label="事件ID" />
                  <el-table-column prop="event_name" label="事件名称" min-width="100" />
                  <el-table-column prop="handle_status" label="处置状态">
                    <template #default="scope">
                      <el-tag
                        :type="scope.row.handle_status === '未处置' ? 'danger' : 'info'"
                      >
                        {{ scope.row.handle_status }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column prop="report_time" label="上报时间" width="140" />
                  <el-table-column prop="complaint_count" label="投诉次数" />
                  <el-table-column label="操作" min-width="100">
                    <template #default="scope">
                      <el-button
                        size="small"
                        type="primary"
                        @click.stop="handleOverflowEvent(scope.row)"
                      >
                        处置事件
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
            </el-tabs>
          </div>
        </div>
      </div>
    </div>

    <el-dialog
      v-model="clearPointDetailVisible"
      :title="currentClearPoint?.point_name || '清运点详情'"
      width="700px"
      class="order-detail-dialog"
      @close="resetCurrentClearPoint"
    >
      <div class="order-detail">
        <div class="detail-section">
          <h3>清运点基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="清运点ID">{{ currentClearPoint?.clear_point_id || '-' }}</el-descriptions-item>
            <el-descriptions-item label="名称">{{ currentClearPoint?.point_name || '-' }}</el-descriptions-item>
            <el-descriptions-item label="清运状态">{{ currentClearPoint?.clear_status || '-' }}</el-descriptions-item>
            <el-descriptions-item label="总桶数">{{ currentClearPoint?.bucket_total || 0 }}</el-descriptions-item>
            <el-descriptions-item label="剩余桶数">{{ currentClearPoint?.bucket_remaining || 0 }}</el-descriptions-item>
            <el-descriptions-item label="已清运桶数">{{ currentClearPoint?.bucket_cleaned || 0 }}</el-descriptions-item>
            <el-descriptions-item label="负责人">{{ currentClearPoint?.responsible_person || '-' }}</el-descriptions-item>
            <el-descriptions-item label="计划清运时间">{{ currentClearPoint?.plan_clear_time || '-' }}</el-descriptions-item>
            <el-descriptions-item label="实际清运时间">{{ currentClearPoint?.actual_clear_time || '未清运' }}</el-descriptions-item>
            <el-descriptions-item label="是否超时">{{ currentClearPoint?.is_timeout ? '是' : '否' }}</el-descriptions-item>
            <el-descriptions-item label="关联满溢事件ID">{{ currentClearPoint?.overflow_event_id || '无' }}</el-descriptions-item>
            <el-descriptions-item label="坐标位置">
              ({{ currentClearPoint?.coord_x?.toFixed(6) || '-' }}, {{ currentClearPoint?.coord_y?.toFixed(6) || '-' }})
            </el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <template #footer>
        <el-button @click="clearPointDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="markClearCompleted(currentClearPoint)"
          v-if="currentClearPoint?.clear_status !== '已清运'"
        >
          标记已清运
        </el-button>
      </template>
    </el-dialog>

    <el-dialog
      v-model="cleanAreaDetailVisible"
      :title="currentCleanArea?.area_name || '保洁区域详情'"
      width="700px"
      class="order-detail-dialog"
      @close="resetCurrentCleanArea"
    >
      <div class="order-detail">
        <div class="detail-section">
          <h3>保洁区域基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="区域ID">{{ currentCleanArea?.clean_area_id || '-' }}</el-descriptions-item>
            <el-descriptions-item label="名称">{{ currentCleanArea?.area_name || '-' }}</el-descriptions-item>
            <el-descriptions-item label="保洁状态">{{ currentCleanArea?.clean_status || '-' }}</el-descriptions-item>
            <el-descriptions-item label="保洁覆盖率">{{ currentCleanArea?.coverage_rate || 0 }}%</el-descriptions-item>
            <el-descriptions-item label="总面积">{{ (currentCleanArea?.total_area / 10000).toFixed(2) || 0 }}万㎡</el-descriptions-item>
            <el-descriptions-item label="已保洁面积">{{ (currentCleanArea?.cleaned_area / 10000).toFixed(2) || 0 }}万㎡</el-descriptions-item>
            <el-descriptions-item label="责任保洁队">{{ currentCleanArea?.responsible_team || '-' }}</el-descriptions-item>
            <el-descriptions-item label="负责人">{{ currentCleanArea?.responsible_person || '-' }}</el-descriptions-item>
            <el-descriptions-item label="计划保洁时间">{{ currentCleanArea?.plan_clean_time || '-' }}</el-descriptions-item>
            <el-descriptions-item label="实际保洁时间">{{ currentCleanArea?.actual_clean_time || '未保洁' }}</el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <template #footer>
        <el-button @click="cleanAreaDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="markCleanCompleted(currentCleanArea)"
          v-if="currentCleanArea?.clean_status !== '已保洁'"
        >
          标记已保洁
        </el-button>
      </template>
    </el-dialog>

    <el-dialog
      v-model="overflowHandleVisible"
      title="垃圾满溢事件处置"
      width="600px"
      class="dispatch-dialog"
      :close-on-click-modal="false"
      @close="resetOverflowForm"
    >
      <el-form :model="overflowHandleForm" label-width="120px" :rules="overflowHandleRules" ref="overflowHandleFormRef">
        <el-form-item label="事件ID" prop="eventId">
          <el-input v-model="overflowHandleForm.eventId" disabled />
        </el-form-item>
        <el-form-item label="事件名称" prop="eventName">
          <el-input v-model="overflowHandleForm.eventName" disabled />
        </el-form-item>
        <el-form-item label="处置人员" prop="handlePerson">
          <el-select v-model="overflowHandleForm.handlePerson" placeholder="选择处置人员" clearable>
            <el-option label="张三" value="张三" />
            <el-option label="李四" value="李四" />
            <el-option label="王五" value="王五" />
            <el-option label="赵六" value="赵六" />
          </el-select>
        </el-form-item>
        <el-form-item label="处置措施" prop="handleMeasures">
          <el-input
            v-model="overflowHandleForm.handleMeasures"
            type="textarea"
            rows="3"
            placeholder="输入处置措施"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="overflowHandleVisible = false">取消</el-button>
        <el-button type="primary" @click="submitOverflowHandle">确认处置</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { computed, onMounted, ref, onUnmounted } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage, ElMessageBox, ElLoading } from 'element-plus';
import screenFull from 'screenfull';
import { FullScreen } from "@element-plus/icons-vue";
import EnvironSanitationMap from './EnvironSanitationMap.vue';
import {
  fetchEnvironOverview,
  fetchClearPointSpatial,
  fetchCleanAreaSpatial,
  fetchOverflowEvent,
  fetchEnvironRegionDict,
  markClearPointCompleted,
  markCleanAreaCompleted,
  handleOverflowEvent as handleOverflowEventApi
} from '@/api/overview/urbanmanagement/SubdomainScenarioTopics.js';

const router = useRouter();
let refreshTimer = null;

const mapPanel = ref(null);
const taskTablePanel = ref(null);
const mapComponent = ref(null);
const overflowHandleFormRef = ref(null);
const tableLoading = ref(false);

const toggleFullscreen = (panelType) => {
  if (!screenFull.isEnabled) {
    ElMessage.warning('您的浏览器不支持全屏功能');
    return;
  }

  let panel = null;
  if (panelType === 'mapPanel') {
    panel = mapPanel.value;
  } else if (panelType === 'taskTablePanel') {
    panel = taskTablePanel.value;
  }

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

const overviewData = ref({
  clearCompletionRate: 0,
  cleanCoverageRate: 0,
  overflowHandleRate: 0,
  totalClearPoint: 0,
  completedClearPoint: 0,
  totalCleanArea: 0,
  cleanedArea: 0,
  overflowEventCount: 0,
  handledOverflowCount: 0,
  timeoutOverflowCount: 0
});

const filteredOverviewData = ref({
  clearCompletionRate: 0,
  cleanCoverageRate: 0,
  overflowHandleRate: 0,
  totalClearPoint: 0,
  completedClearPoint: 0,
  totalClearPointBucket: 0,
  totalCleanArea: 0,
  cleanedArea: 0,
  overflowEventCount: 0,
  handledOverflowCount: 0,
  timeoutOverflowCount: 0
});

const clearPointList = ref([]);
const cleanAreaList = ref([]);
const overflowEventList = ref([]);
const regionDict = ref([]);

const filterParams = ref({
  region: [],
  clearStatus: '',
  cleanStatus: '',
  timeRange: ''
});

const clearPointDetailVisible = ref(false);
const cleanAreaDetailVisible = ref(false);
const overflowHandleVisible = ref(false);
const currentClearPoint = ref(null);
const currentCleanArea = ref(null);
const currentOverflowEvent = ref(null);
const activeTab = ref('unclear');

const overflowHandleForm = ref({
  eventId: '',
  eventName: '',
  handlePerson: '',
  handleMeasures: ''
});
const overflowHandleRules = ref({
  handlePerson: [{ required: true, message: '请选择处置人员', trigger: 'change' }],
  handleMeasures: [{ required: true, message: '请输入处置措施', trigger: 'blur' }]
});

const resetCurrentClearPoint = () => {
  currentClearPoint.value = null;
};

const resetCurrentCleanArea = () => {
  currentCleanArea.value = null;
};

const resetOverflowForm = () => {
  overflowHandleForm.value = {
    eventId: '',
    eventName: '',
    handlePerson: '',
    handleMeasures: ''
  };
  overflowHandleFormRef.value?.resetFields();
  currentOverflowEvent.value = null;
};

const filterTime = (timeStr) => {
  if (!filterParams.value.timeRange || !timeStr || timeStr === '-') return true;

  try {
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    const yesterday = new Date(today);
    yesterday.setDate(yesterday.getDate() - 1);

    const targetDate = new Date(timeStr);
    if (isNaN(targetDate.getTime())) return true;

    targetDate.setHours(0, 0, 0, 0);

    switch (filterParams.value.timeRange) {
      case 'today':
        return targetDate.getTime() === today.getTime();
      case 'yesterday':
        return targetDate.getTime() === yesterday.getTime();
      default:
        return true;
    }
  } catch (e) {
    return true;
  }
};

const filteredClearPointList = computed(() => {
  return clearPointList.value.filter(item => {
    const matchRegion = filterParams.value.region.length === 0
      ? true
      : (item.area_code || '').includes(filterParams.value.region[filterParams.value.region.length - 1] || '');
    const matchStatus = !filterParams.value.clearStatus || item.clear_status === filterParams.value.clearStatus;
    const matchTime = filterTime(item.plan_clear_time);
    return matchRegion && matchStatus && matchTime;
  });
});

const filteredCleanAreaList = computed(() => {
  return cleanAreaList.value.filter(item => {
    const matchRegion = filterParams.value.region.length === 0
      ? true
      : (item.area_code || '').includes(filterParams.value.region[filterParams.value.region.length - 1] || '');
    const matchStatus = !filterParams.value.cleanStatus || item.clean_status === filterParams.value.cleanStatus;
    const matchTime = filterTime(item.plan_clean_time);
    return matchRegion && matchStatus && matchTime;
  });
});

const filteredOverflowEventList = computed(() => {
  return overflowEventList.value.filter(item => {
    const matchRegion = filterParams.value.region.length === 0
      ? true
      : (item.area_code || '').includes(filterParams.value.region[filterParams.value.region.length - 1] || '');
    const matchStatus = filterParams.value.clearStatus === '满溢'
      ? item.handle_status !== '已处置'
      : true;
    const matchTime = filterTime(item.report_time);
    return matchRegion && matchStatus && matchTime;
  });
});

const triggerCountAnimation = () => {
  const animationElements = document.querySelectorAll('.count-animation');
  animationElements.forEach(el => {
    el.classList.remove('animate');
    void el.offsetWidth;
    el.classList.add('animate');
  });
};

const calculateFilteredData = () => {
  const totalBuckets = clearPointList.value.reduce((sum, item) => sum + Number(item.bucket_total || 0), 0);
  const completedBuckets = clearPointList.value.reduce((sum, item) => sum + Number(item.bucket_cleaned || 0), 0);

  const totalArea = cleanAreaList.value.reduce((sum, item) => sum + Number(item.total_area || 0), 0);
  const cleanedArea = cleanAreaList.value.reduce((sum, item) => sum + Number(item.cleaned_area || 0), 0);

  const totalEvents = overflowEventList.value.length;
  const handledEvents = overflowEventList.value.filter(item => item.handle_status === '已处置').length;

  filteredOverviewData.value = {
    clearCompletionRate: overviewData.value.clearCompletionRate || (totalBuckets > 0 ? Math.round((completedBuckets / totalBuckets) * 100 * 10) / 10 : 0),
    cleanCoverageRate: overviewData.value.cleanCoverageRate || (totalArea > 0 ? Math.round((cleanedArea / totalArea) * 100 * 10) / 10 : 0),
    overflowHandleRate: overviewData.value.overflowHandleRate || (totalEvents > 0 ? Math.round((handledEvents / totalEvents) * 100 * 10) / 10 : 0),
    totalClearPoint: overviewData.value.totalClearPoint,
    completedClearPoint: overviewData.value.completedClearPoint,
    totalClearPointBucket: totalBuckets,
    totalCleanArea: overviewData.value.totalCleanArea,
    cleanedArea: overviewData.value.cleanedArea,
    overflowEventCount: overviewData.value.overflowEventCount,
    handledOverflowCount: overviewData.value.handledOverflowCount,
    timeoutOverflowCount: overviewData.value.timeoutOverflowCount
  };

  triggerCountAnimation();
};

const refreshData = async () => {
  const loading = ElLoading.service({
    lock: true,
    text: '数据加载中...',
    background: 'rgba(0, 0, 0, 0.7)'
  });
  try {
    await initData();
    calculateFilteredData();
    ElMessage.success('环境卫生数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
    console.error(error);
  } finally {
    loading.close();
  }
};

const handleBack = () => {
  router.push('/overview/urbanmanagement/dpzl').catch(err => console.error('返回失败：', err));
};

const handleTaskRowClick = (type, row) => {
  if (!row) return;

  if (mapComponent.value) {
    if (type === 'clearPoint') {
      mapComponent.value.locateToPoint(row.coord_x, row.coord_y);
    } else if (type === 'cleanArea') {
      const center = getPolygonCenter(row.polygon_coords);
      mapComponent.value.locateToPoint(center.lat, center.lng);
    }
  }

  if (type === 'clearPoint') {
    currentClearPoint.value = row;
    clearPointDetailVisible.value = true;
  } else if (type === 'cleanArea') {
    currentCleanArea.value = row;
    cleanAreaDetailVisible.value = true;
  } else if (type === 'overflow') {
    currentOverflowEvent.value = row;
    overflowHandleForm.value = {
      eventId: row.overflow_event_id || '',
      eventName: row.event_name || '',
      handlePerson: row.handle_person || '',
      handleMeasures: row.handle_measures || ''
    };
    overflowHandleVisible.value = true;
  }
};

const getPolygonCenter = (coords) => {
  if (!Array.isArray(coords) || coords.length === 0) {
    return { lat: 26.0753, lng: 119.3062 };
  }
  let latSum = 0, lngSum = 0, count = 0;
  coords.forEach(coord => {
    if (Array.isArray(coord) && coord.length >= 2 && !isNaN(coord[0]) && !isNaN(coord[1])) {
      lngSum += Number(coord[0]);
      latSum += Number(coord[1]);
      count++;
    }
  });
  return {
    lat: count > 0 ? latSum / count : 26.0753,
    lng: count > 0 ? lngSum / count : 119.3062
  };
};

const handleClearPointClick = (point) => {
  currentClearPoint.value = point;
  clearPointDetailVisible.value = true;
};

const handleCleanAreaClick = (area) => {
  currentCleanArea.value = area;
  cleanAreaDetailVisible.value = true;
};

const markClearCompleted = async (row) => {
  if (!row || !row.clear_point_id) {
    ElMessage.error('清运点ID不能为空');
    return;
  }

  try {
    await ElMessageBox.confirm('确认该清运点已完成清运？', '提示', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    });

    tableLoading.value = true;
    const res = await markClearPointCompleted(row.clear_point_id);

    if (res.code === 200) {
      const index = clearPointList.value.findIndex(item => item.clear_point_id === row.clear_point_id);
      if (index > -1) {
        clearPointList.value[index].clear_status = '已清运';
        clearPointList.value[index].bucket_remaining = 0;
        clearPointList.value[index].bucket_cleaned = Number(row.bucket_total || 0);
        clearPointList.value[index].actual_clear_time = res.data.actual_clear_time;
        clearPointList.value[index].is_timeout = false;
      }
      await fetchOverviewData();
      calculateFilteredData();
      ElMessage.success('标记清运完成成功！');
      clearPointDetailVisible.value = false;
    } else {
      ElMessage.error(res.msg || '标记失败');
    }
  } catch (error) {
    if (error !== 'cancel') {
      console.error('标记清运完成失败：', error);
      ElMessage.error('标记失败，请重试！');
    }
  } finally {
    tableLoading.value = false;
  }
};

const markCleanCompleted = async (row) => {
  if (!row || !row.clean_area_id) {
    ElMessage.error('保洁区域ID不能为空');
    return;
  }

  try {
    await ElMessageBox.confirm('确认该区域已完成保洁？', '提示', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    });

    tableLoading.value = true;
    const res = await markCleanAreaCompleted(row.clean_area_id);

    if (res.code === 200) {
      const index = cleanAreaList.value.findIndex(item => item.clean_area_id === row.clean_area_id);
      if (index > -1) {
        cleanAreaList.value[index].clean_status = '已保洁';
        cleanAreaList.value[index].coverage_rate = 100.0;
        cleanAreaList.value[index].cleaned_area = Number(row.total_area || 0);
        cleanAreaList.value[index].actual_clean_time = res.data.actual_clean_time;
      }
      await fetchOverviewData();
      calculateFilteredData();
      ElMessage.success('标记保洁完成成功！');
      cleanAreaDetailVisible.value = false;
    } else {
      ElMessage.error(res.msg || '标记失败');
    }
  } catch (error) {
    if (error !== 'cancel') {
      console.error('标记保洁完成失败：', error);
      ElMessage.error('标记失败，请重试！');
    }
  } finally {
    tableLoading.value = false;
  }
};

const handleOverflowEvent = (row) => {
  if (!row || !row.overflow_event_id) {
    ElMessage.error('满溢事件ID不能为空');
    return;
  }
  currentOverflowEvent.value = row;
  overflowHandleForm.value = {
    eventId: row.overflow_event_id,
    eventName: row.event_name || '',
    handlePerson: row.handle_person || '',
    handleMeasures: row.handle_measures || ''
  };
  overflowHandleVisible.value = true;
};

const submitOverflowHandle = async () => {
  if (!overflowHandleFormRef.value) return;

  try {
    await overflowHandleFormRef.value.validate();
    if (!currentOverflowEvent.value?.overflow_event_id) {
      ElMessage.error('事件ID不能为空');
      return;
    }

    tableLoading.value = true;
    const res = await handleOverflowEventApi(currentOverflowEvent.value.overflow_event_id, {
      handle_person: overflowHandleForm.value.handlePerson,
      handle_measures: overflowHandleForm.value.handleMeasures
    });

    if (res.code === 200) {
      const index = overflowEventList.value.findIndex(item => item.overflow_event_id === currentOverflowEvent.value.overflow_event_id);
      if (index > -1) {
        overflowEventList.value[index].handle_status = '已处置';
        overflowEventList.value[index].handle_person = overflowHandleForm.value.handlePerson;
        overflowEventList.value[index].handle_time = res.data.handle_time;
        overflowEventList.value[index].handle_measures = overflowHandleForm.value.handleMeasures;
        overflowEventList.value[index].is_timeout = false;
      }
      const pointIndex = clearPointList.value.findIndex(item => item.overflow_event_id === currentOverflowEvent.value.overflow_event_id);
      if (pointIndex > -1) {
        clearPointList.value[pointIndex].clear_status = '待清运';
      }
      await fetchOverviewData();
      calculateFilteredData();
      ElMessage.success('事件处置成功！');
      overflowHandleVisible.value = false;
    } else {
      ElMessage.error(res.msg || '处置失败');
    }
  } catch (error) {
    console.error('事件处置失败：', error);
    ElMessage.error('处置失败，请重试！');
  } finally {
    tableLoading.value = false;
  }
};

const resetFilter = async () => {
  tableLoading.value = true;
  try {
    filterParams.value = {
      region: [],
      clearStatus: '',
      cleanStatus: '',
      timeRange: ''
    };
    ElMessage.success('筛选条件已重置');
  } catch (error) {
    ElMessage.error('重置失败，请重试');
    console.error(error);
  } finally {
    tableLoading.value = false;
  }
};
const fetchOverviewData = async () => {
  try {
    const data = await fetchEnvironOverview();
    overviewData.value = {
      ...overviewData.value,
      ...data,
      clearCompletionRate: Number.isFinite(Number(data.clearCompletionRate)) ? Number(data.clearCompletionRate) : 0,
      cleanCoverageRate: Number.isFinite(Number(data.cleanCoverageRate)) ? Number(data.cleanCoverageRate) : 0,
      overflowHandleRate: Number.isFinite(Number(data.overflowHandleRate)) ? Number(data.overflowHandleRate) : 0,
      totalClearPoint: Number.isFinite(Number(data.totalClearPoint)) ? Number(data.totalClearPoint) : 0,
      completedClearPoint: Number.isFinite(Number(data.completedClearPoint)) ? Number(data.completedClearPoint) : 0,
      totalCleanArea: Number.isFinite(Number(data.totalCleanArea)) ? Number(data.totalCleanArea) : 0,
      cleanedArea: Number.isFinite(Number(data.cleanedArea)) ? Number(data.cleanedArea) : 0,
      overflowEventCount: Number.isFinite(Number(data.overflowEventCount)) ? Number(data.overflowEventCount) : 0,
      handledOverflowCount: Number.isFinite(Number(data.handledOverflowCount)) ? Number(data.handledOverflowCount) : 0,
      timeoutOverflowCount: Number.isFinite(Number(data.timeoutOverflowCount)) ? Number(data.timeoutOverflowCount) : 0
    };
  } catch (error) {
    console.error('获取概览数据失败：', error);
    ElMessage.warning('概览数据加载失败，使用缓存数据');
  }
};

const fetchClearPointData = async () => {
  try {
    const data = await fetchClearPointSpatial();
    clearPointList.value = Array.isArray(data) ? data.map(item => ({
      ...item,
      bucket_total: Number(item.bucket_total || 0),
      bucket_remaining: Number(item.bucket_remaining || 0),
      bucket_cleaned: Number(item.bucket_cleaned || 0)
    })) : [];
  } catch (error) {
    console.error('获取清运点数据失败：', error);
    clearPointList.value = [];
    ElMessage.warning('清运点数据加载失败');
  }
};

const fetchCleanAreaData = async () => {
  try {
    const data = await fetchCleanAreaSpatial();
    cleanAreaList.value = Array.isArray(data) ? data.map(item => ({
      ...item,
      total_area: Number(item.total_area || 0),
      cleaned_area: Number(item.cleaned_area || 0),
      coverage_rate: Number(item.coverage_rate || 0)
    })) : [];
  } catch (error) {
    console.error('获取保洁区域数据失败：', error);
    cleanAreaList.value = [];
    ElMessage.warning('保洁区域数据加载失败');
  }
};

const fetchOverflowEventData = async () => {
  try {
    const data = await fetchOverflowEvent();
    overflowEventList.value = Array.isArray(data) ? data.map(item => ({
      ...item,
      complaint_count: Number(item.complaint_count || 0)
    })) : [];
  } catch (error) {
    console.error('获取满溢事件数据失败：', error);
    overflowEventList.value = [];
    ElMessage.warning('满溢事件数据加载失败');
  }
};

const fetchRegionDictData = async () => {
  try {
    const data = await fetchEnvironRegionDict();
    regionDict.value = Array.isArray(data) ? data : [];
  } catch (error) {
    console.error('获取区域字典失败：', error);
    regionDict.value = [];
    ElMessage.warning('区域数据加载失败');
  }
};

const initData = async () => {
  await Promise.allSettled([
    fetchOverviewData(),
    fetchClearPointData(),
    fetchCleanAreaData(),
    fetchOverflowEventData(),
    fetchRegionDictData()
  ]);
};

onMounted(async () => {
  const loading = ElLoading.service({
    lock: true,
    text: '页面初始化中...',
    background: 'rgba(0, 0, 0, 0.7)'
  });

  await initData();
  calculateFilteredData();

  setTimeout(() => {
    triggerCountAnimation();
  }, 300);

  refreshTimer = setInterval(refreshData, 30 * 60 * 1000);

  loading.close();
});

onUnmounted(() => {
  if (refreshTimer) clearInterval(refreshTimer);
  if (screenFull.isFullscreen) {
    screenFull.exit();
  }
});
</script>

<style lang="scss" scoped>
.environ-sanitation-container {
  width: 100%;
  height: 100vh;
  overflow: auto;
  background: url("@/assets/chart/images/bg.jpg") no-repeat center center;
  background-size: cover;
  color: #ffffff;
  padding: 0 1.5vw;
  box-sizing: border-box;
  touch-action: pan-x pan-y;

  &::after {
    content: '';
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(135deg, rgba(0, 47, 75, 0.6) 0%, rgba(0, 102, 153, 0.5) 100%);
    z-index: -1;
  }
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.2vh 0;
  border-bottom: 0.2vh solid rgba(0, 198, 255, 0.3);
  margin-bottom: 1.2vw;
  position: relative;

  &::after {
    content: '';
    position: absolute;
    bottom: -0.3vh;
    left: 0;
    width: 16vw;
    height: 0.3vh;
    background: linear-gradient(90deg, #FACC15, #9333EA, #EF4444);
  }

  h1 {
    margin: 0;
    font-size: 1.6vw;
    background: linear-gradient(90deg, #FACC15, #9333EA, #EF4444);
    -webkit-background-clip: text;
    background-clip: text;
    color: transparent;
    font-weight: 700;
    letter-spacing: 0.1vw;
    text-shadow: 0 0 8px rgba(0, 198, 255, 0.2);

    .title-icon {
      width: 1.8vw;
      height: 1.8vw;
      object-fit: contain;
      vertical-align: middle;
      margin-right: 0.5vw;
      filter: drop-shadow(0 0 3px rgba(0, 198, 255, 0.5));
    }
  }

  .header-actions {
    display: flex;
    gap: 0.8vw;
    align-items: center;
  }

  .el-button {
    --el-button-text-color: #ffffff;
    --el-button-bg-color: rgba(0, 198, 255, 0.2);
    --el-button-border-color: #9333EA;
    --el-button-hover-bg-color: rgba(147, 51, 234, 0.4);
    --el-button-hover-border-color: #FACC15;
    --el-button-active-bg-color: rgba(250, 204, 21, 0.3);
    --el-button-active-border-color: #FACC15;
    border-radius: 4px;
    font-size: 0.85vw;
    padding: 0.6vh 1.2vw;
    transition: all 0.3s ease;
  }
}

.main-content {
  display: flex;
  gap: 1.2vw;
  height: calc(100vh - 12vh);

  .left-area {
    flex: 5;
    display: flex;
    flex-direction: column;
    gap: 1vw;
    height: 100%;

    .map-container {
      flex: 1;
      position: relative;
      padding: 0;
      height: 100%;

      &::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 3px;
        background: linear-gradient(90deg, #00c6ff, rgba(0, 198, 255, 0.6));
        z-index: 1;
      }

      .panel-header {
        padding: 1vh 1.2vw;
        display: flex;
        justify-content: space-between;
        align-items: center;
        position: relative;
        z-index: 2;
        height: 6%;
        box-sizing: border-box;

        h3 {
          margin: 0;
          font-size: 0.9vw;
          font-weight: 600;
          color: #ffb700;
          text-shadow: 0 0 5px rgba(255, 166, 0, 0.3);
        }
      }

      .map-body {
        width: 100%;
        height: 94%;
        padding: 0 !important;
        margin: 0 !important;
        overflow: hidden;

        #environMap {
          width: 100% !important;
          height: 100% !important;
        }
      }
    }
  }

  .right-area {
    flex: 6;
    display: flex;
    flex-direction: column;
    gap: 1vw;
    height: 100%;

    .stat-card-container {
      display: flex;
      gap: 1vw;
      height: 20%;

      .stat-card {
        flex: 1;
        background: rgba(0, 68, 102, 0.8);
        border-radius: 8px;
        padding: 0.8vw 1vw;
        border: 1px solid rgba(0, 198, 255, 0.3);
        box-shadow: 0 0 1vw rgba(0, 198, 255, 0.2);
        transition: all 0.3s ease;
        box-sizing: border-box;
        position: relative;
        overflow: hidden;
        cursor: default;

        &::after {
          content: '';
          position: absolute;
          top: 0;
          right: 0;
          width: 40%;
          height: 100%;
          background: linear-gradient(45deg, transparent, rgba(0, 198, 255, 0.1));
          z-index: 0;
        }

        &:hover {
          transform: none;
          box-shadow: 0 0 1vw rgba(0, 198, 255, 0.2);
          border-color: rgba(0, 198, 255, 0.3);
        }

        .stat-title {
          font-size: 1vw;
          color: #67e8f9;
          margin-bottom: 0.5vw;
          display: flex;
          align-items: center;
          position: relative;
          z-index: 1;

          &::before {
            content: '';
            display: inline-block;
            width: 0.7vw;
            height: 1.4vh;
            border-radius: 50%;
            margin-right: 0.8vw;
          }
        }

        .stat-value {
          font-size: 1.8vw;
          font-weight: 700;
          margin-bottom: 0.5vh;
          line-height: 1.2;
          position: relative;
          z-index: 1;
          text-shadow: 0 0 8px rgba(0, 198, 255, 0.2);
          color: inherit !important;

          &.count-animation {
            opacity: 0;
            transform: translateY(10px);
            transition: all 0.8s ease;
          }
          &.count-animation.animate {
            opacity: 1;
            transform: translateY(0);
          }
        }

        .stat-desc {
          font-size: 0.7vw;
          color: #67e8f9;
          line-height: 1.4;
          position: relative;
          z-index: 1;
          white-space: normal;
          word-break: break-all;
          margin-left: 5vw;
        }

        &.clear-rate-card {
          .stat-title::before {
            background: #FACC15;
          }
          .stat-title {
            color: #fbb866;
          }
          .stat-value {
            background: linear-gradient(90deg, #FACC15, #fbb866);
            -webkit-background-clip: text;
            background-clip: text;
            color: transparent;
          }
          &.danger-rate .stat-value {
            background: linear-gradient(90deg, #EF4444, #fca5a5);
            -webkit-background-clip: text;
            background-clip: text;
            color: transparent;
          }
        }

        &.clean-rate-card {
          .stat-title::before {
            background: #9333EA;
          }
          .stat-title {
            color: #c4b5fd;
          }
          .stat-value {
            background: linear-gradient(90deg, #9333EA, #c4b5fd);
            -webkit-background-clip: text;
            background-clip: text;
            color: transparent;
          }
          &.danger-rate .stat-value {
            background: linear-gradient(90deg, #EF4444, #fca5a5);
            -webkit-background-clip: text;
            background-clip: text;
            color: transparent;
          }
        }

        &.overflow-handle-rate-card {
          .stat-title::before {
            background: #EF4444;
          }
          .stat-title {
            color: #fca5a5;
          }
          .stat-value {
            background: linear-gradient(90deg, #EF4444, #fca5a5);
            -webkit-background-clip: text;
            background-clip: text;
            color: transparent;
          }
          &.normal-rate .stat-value {
            background: linear-gradient(90deg, #9333EA, #c4b5fd);
            -webkit-background-clip: text;
            background-clip: text;
            color: transparent;
          }
        }
      }
    }

    .filter-bar.list-filter-bar {
      display: flex;
      gap: 0.8vw;
      align-items: center;
      padding: 0.8vw 1.2vw;
      background: rgba(0, 68, 102, 0.5);
      border-radius: 8px;
      border: 1px solid rgba(0, 198, 255, 0.3);
      width: 100%;
      flex-direction: row;
      flex-wrap: nowrap;
      overflow: hidden;
      position: relative;

      &::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 3px;
        background: linear-gradient(90deg, #f7931e, rgba(247, 147, 30, 0.6));
        z-index: 1;
      }

      .el-select, .el-cascader {
        font-size: 0.8vw;
        width: calc(20% - 0.8vw);
        max-width: 12vw;
        --el-select-text-color: #ffffff;
        --el-select-border-color: #f7931e;
        --el-select-hover-border-color: #ec4899;
        --el-select-dropdown-bg-color: rgba(0, 68, 102, 0.5);
        --el-select-option-hover-bg-color: rgba(247, 147, 30, 0.2);
      }

      .el-button {
        font-size: 0.8vw;
      }
    }

    .task-list-container {
      flex: 1;
      background: rgba(0, 47, 75, 0.4);
      border-radius: 8px;
      border: 1px solid rgba(0, 198, 255, 0.3);
      overflow-y: auto;
      position: relative;
      height: 83%;

      &::before {
        content: '';
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 3px;
        background: linear-gradient(90deg, #ec4899, rgba(236, 72, 153, 0.6));
        z-index: 1;
      }

      .panel-header h3 {
        color: #ec4899;
      }

      .table-scroll {
        overflow-y: auto;
        height: calc(100% - 2vh - 1vh);

        &::-webkit-scrollbar {
          width: 6px;
          height: 6px;
        }

        &::-webkit-scrollbar-track {
          background: rgba(0, 68, 102, 0.8);
          border-radius: 3px;
        }

        &::-webkit-scrollbar-thumb {
          background: linear-gradient(180deg, #9333EA, #FACC15);
          border-radius: 3px;
        }

        &::-webkit-scrollbar-thumb:hover {
          background: linear-gradient(180deg, #EF4444, #9333EA);
        }
      }
    }
  }
}

.panel {
  position: relative;
  border: 1px solid rgba(0, 198, 255, 0.3);
  background: rgba(255, 204, 211, 0.05);
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  min-height: 0;
  box-shadow: 0 0 1vw rgba(0, 198, 255, 0.2);
  overflow: hidden;

  .panel-header {
    padding: 1vh 1.2vw;
    border-bottom: 1px solid rgba(0, 198, 255, 0.3);
    display: flex;
    justify-content: space-between;
    align-items: center;
    position: relative;
    z-index: 2;

    h3 {
      margin: 0;
      font-size: 0.9vw;
      font-weight: 600;
      display: flex;
      align-items: center;
      text-shadow: 0 0 5px rgba(0, 198, 255, 0.3);
    }

    .header-actions {
      display: flex;
      align-items: center;
    }
  }

  .panel-body {
    flex: 1;
    padding: 0.5vw;
    overflow: hidden;
    position: relative;
    z-index: 2;
  }

  .panel-footer {
    height: 1vh;
    border-top: 1px solid rgba(0, 198, 255, 0.3);
    position: relative;
    z-index: 2;
  }
}

.panel-fullscreen-btn {
  background: transparent;
  border: none;
  cursor: pointer;
  padding: 4px;
  border-radius: 4px;
  transition: all 0.2s ease;
  display: flex;
  align-items: center;
  justify-content: center;

  &:hover {
    background: rgba(255, 255, 255, 0.1);
    transform: scale(1.1);
  }
}

:deep(.el-table) {
  table-layout: fixed;
  background-color: rgba(26, 16, 46, 0.4);
  border-color: rgba(147, 51, 234, 0.3);

  th {
    background: linear-gradient(90deg, rgba(26, 16, 46, 0.8), rgba(41, 24, 71, 0.8)) !important;
    color: #FACC15;
    font-size: 0.8vw;
    padding: 0.5vw 0;
    text-align: center;
    border-color: rgba(147, 51, 234, 0.3);
    font-weight: 500;
  }

  td {
    background-color: transparent;
    color: #C8B69C;
    font-size: 0.7vw;
    border-bottom: 1px solid rgba(147, 51, 234, 0.3);
    padding: 0.4vw 0;
    text-align: center;
    transition: all 0.2s;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .el-table__row {
    background-color: rgba(26, 16, 46, 0.3);
    &:nth-child(even) {
      background-color: rgba(41, 24, 71, 0.3);
    }

    &:hover > td {
      background-color: rgba(147, 51, 234, 0.15) !important;
      color: #FACC15;
    }
  }

  .el-table__row--current > td {
    background-color: rgba(236, 72, 153, 0.1) !important;
    color: #ec4899;
  }

  .el-table__empty-text {
    color: #c4b5fd;
    background-color: rgba(26, 16, 46, 0.4);
  }

  &::before {
    background-color: rgba(147, 51, 234, 0.3);
  }

  .el-table__fixed-right::before,
  .el-table__fixed::before {
    background-color: transparent;
  }

  .el-table__cell {
    padding: 1vh 0.8vw !important;
    font-size: 0.8vw;
  }

  .el-tag {
    font-size: 0.75vw;
    padding: 0.2vh 0.6vw;
    border-radius: 12px;
    font-weight: 500;
  }
}

.order-detail-dialog, .dispatch-dialog {
  --el-dialog-bg-color: rgba(0, 47, 75, 0.8);
  --el-dialog-border-color: rgba(0, 198, 255, 0.3);
  --el-dialog-title-color: #FACC15;
  --el-dialog-text-color: #ffffff;
  --el-dialog-header-border-color: rgba(0, 198, 255, 0.3);
  --el-dialog-footer-border-color: rgba(0, 198, 255, 0.3);
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(0, 198, 255, 0.3), 0 0 40px rgba(147, 51, 234, 0.1);

  .el-dialog__header {
    border-bottom: 1px solid rgba(0, 198, 255, 0.3);
    position: relative;

    &::after {
      content: '';
      position: absolute;
      bottom: -1px;
      left: 0;
      width: 100%;
      height: 1px;
      background: linear-gradient(90deg, #FACC15, #9333EA, #EF4444, transparent);
    }
  }

  .order-detail {
    .detail-section {
      margin-bottom: 24px;

      h3 {
        font-size: 16px;
        color: #FACC15;
        margin-bottom: 12px;
        padding-bottom: 6px;
        border-bottom: 1px solid rgba(0, 198, 255, 0.3);
        font-weight: 600;
        background: linear-gradient(90deg, #FACC15, #9333EA);
        -webkit-background-clip: text;
        background-clip: text;
      }
    }

    .el-descriptions {
      --el-descriptions-bg-color: transparent;
      --el-descriptions-border-color: rgba(0, 198, 255, 0.3);
      --el-descriptions-content-color: #ffffff;
      font-size: 0.9vw;

      .el-descriptions__label {
        color: #00c6ff;
        font-weight: 500;
      }
    }
  }

  .el-form {
    .el-form-item {
      label {
        color: #ffffff;
      }

      .el-select {
        --el-select-border-color: #9333EA;
        --el-select-hover-border-color: #FACC15;
      }

      .el-input {
        --el-input-border-color: #14b8a6;
        --el-input-hover-border-color: #00c6ff;
      }
    }
  }

  .el-dialog__footer {
    border-top: 1px solid rgba(0, 198, 255, 0.3);
    padding-top: 15px;
  }
}

.el-tag--danger {
  --el-tag-bg-color: rgba(239, 68, 68, 0.2);
  --el-tag-border-color: #EF4444;
  --el-tag-text-color: #EF4444;
}

.el-tag--warning {
  --el-tag-bg-color: rgba(247, 147, 30, 0.2);
  --el-tag-border-color: #f7931e;
  --el-tag-text-color: #f7931e;
}

.el-tag--info {
  --el-tag-bg-color: rgba(0, 198, 255, 0.2);
  --el-tag-border-color: #00c6ff;
  --el-tag-text-color: #00c6ff;
}

.export-footer {
  display: flex;
  justify-content: flex-end;
  padding: 10px 0;
  border-top: 1px solid rgba(0, 198, 255, 0.3);
  margin-top: 15px;

  .el-button {
    --el-button-bg-color: linear-gradient(90deg, #00c6ff, #ec4899);
    --el-button-border-color: transparent;
    --el-button-text-color: #ffffff;
    --el-button-hover-bg-color: linear-gradient(90deg, #00b4d8, #d946ef);
    --el-button-hover-border-color: transparent;
    padding: 0.6vh 1.2vw;
    font-size: 0.85vw;
  }
}

:deep(.el-loading-mask) {
  background-color: rgba(0, 0, 0, 0.7) !important;
}
:deep(.el-loading-text) {
  color: #ffffff !important;
  font-size: 1vw !important;
}

// 调整字体大小
:deep(.el-button--text.el-button--small) {
  font-size: 0.7vw;
}

:deep(.el-tag) {
  font-size: 0.7vw;
}

:deep(.el-button--small.el-button--primary) {
  font-size: 0.7vw;
  padding: 0.3vw 0.8vw;
  min-width: 5vw;
  max-width: 8vw;
  height: auto;
  line-height: 1.2;
  border-radius: 0.3vw;
}

:deep(.el-button--small.el-button--primary:hover) {
  padding: 0.35vw 0.85vw;
}
</style>

<style lang="scss">
.task-tabs.el-tabs--card > .el-tabs__header .el-tabs__item {
  font-size: 0.85vw;
  color: #67e8f9 !important;
  font-weight: 700 !important;
  border-radius: 8px 8px 0 0 !important;
  border: 1px solid rgba(0, 198, 255, 0.3) !important;
  background-color: rgba(0, 47, 75, 0.6) !important;
}

.task-tabs.el-tabs--card > .el-tabs__header .el-tabs__item:hover {
  color: #00c6ff !important;
  background-color: rgba(0, 68, 102, 0.8) !important;
}

.task-tabs.el-tabs--card > .el-tabs__header .el-tabs__item.is-active {
  color: #FACC15 !important;
  font-weight: 700;
  background-color: rgba(0, 47, 75, 0.8) !important;
  border-bottom-color: transparent !important;
}

.task-tabs.el-tabs--card > .el-tabs__header .el-tabs__active-bar {
  background-color: #FACC15 !important;
  height: 2px !important;
}

.task-tabs.el-tabs--card {
  .el-tabs__header {
    border-bottom: 1px solid rgba(0, 198, 255, 0.3) !important;
  }
}
</style>
