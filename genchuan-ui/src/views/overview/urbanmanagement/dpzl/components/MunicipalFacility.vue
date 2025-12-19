<template>
  <div class="municipal-facility-container">
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/facility.png"
          alt="市政设施图标"
          class="title-icon"
        />
        市政设施专题
      </h1>
      <div class="header-actions">
        <el-button size="small" @click="refreshData">刷新数据</el-button>
        <el-button size="small" @click="handleBack">返回总览</el-button>
      </div>
    </div>

    <div class="main-content">
      <div class="map-area" style="min-width: 6vw;">
        <div class="map-top-stats">
          <div class="stats-cards">
            <div
              class="stat-card emergency-card"
              @click="filterByLevel('紧急故障')"
              :class="{ pulse: hasNewEmergencyFault }"
            >
              <div class="stat-title">紧急故障</div>
              <div class="stat-value">{{ overviewData.emergencyCount }}</div>
              <div class="stat-desc">超时未处置: {{ overviewData.timeoutCount }} 个</div>
            </div>
            <div class="stat-card normal-card" @click="filterByLevel('一般故障')">
              <div class="stat-title">一般故障</div>
              <div class="stat-value">{{ overviewData.normalCount }}</div>
              <div class="stat-desc">今日新增: {{ todayNormalCount }} 个</div>
            </div>
            <div class="stat-card rate-card" :class="overviewData.handlingRate < 90 ? 'danger-rate' : 'normal-rate'">
              <div class="stat-title">处置率</div>
              <div class="stat-value">{{ overviewData.handlingRate }}%</div>
              <div class="stat-desc">已处置/总故障: {{ overviewData.handledCount }}/{{ overviewData.totalFault }}</div>
            </div>
          </div>
        </div>

        <div class="panel map-container" ref="mapPanel">
          <div class="panel-header">
            <h3>市政设施故障地图</h3>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('mapPanel')">
                <el-icon color="#00c6ff" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <MunicipalFacilityMap
              ref="mapComponent"
              idName="municipalMap"
              :geometriesArray="filteredFaultList"
            />
          </div>
          <div class="panel-footer"></div>
        </div>
      </div>

      <div class="right-area" style="min-width: 4vw;">
        <div class="right-list-area">
          <div class="panel filter-bar list-filter-bar">
            <el-select v-model="filterParams.faultLevel" placeholder="故障等级" size="small">
              <el-option label="全部" value="" />
              <el-option label="紧急故障" value="紧急故障" />
              <el-option label="一般故障" value="一般故障" />
            </el-select>
            <el-select
              v-model="filterParams.facilityType"
              placeholder="设施类型"
              size="small"
              multiple
              collapse-tags
            >
              <el-option
                v-for="type in facilityTypeDict"
                :key="type.value"
                :label="type.label"
                :value="type.value"
              />
            </el-select>
            <el-select v-model="filterParams.handleStatus" placeholder="处置状态" size="small">
              <el-option label="全部" value="" />
              <el-option label="未处置" value="未处置" />
              <el-option label="处置中" value="处置中" />
              <el-option label="已完成" value="已完成" />
            </el-select>
            <el-select v-model="filterParams.timeRange" placeholder="上报时间" size="small">
              <el-option label="全部" value="" />
              <el-option label="今日" value="today" />
              <el-option label="昨日" value="yesterday" />
              <el-option label="本周" value="week" />
            </el-select>
            <el-button size="small" type="success" style="margin-left: 3vw" @click="resetFilter">重 置</el-button>
          </div>

          <div class="panel task-list-container" ref="taskTablePanel">
            <div class="panel-header">
              <h3>故障工单列表</h3>
              <div class="header-actions">
                <button class="panel-fullscreen-btn" @click="toggleFullscreen('taskTablePanel')">
                  <el-icon color="#ec4899" size="16">
                    <FullScreen/>
                  </el-icon>
                </button>
              </div>
            </div>
            <div class="panel-body table-scroll">
              <transition-group name="task-list">
                <el-table
                  ref="taskTable"
                  :data="filteredFaultList"
                  border
                  size="small"
                  style="width: 100%"
                  @row-click="handleTaskRowClick"
                  key="task-table"
                >
                  <el-table-column prop="mng_comp_id" label="设施ID" />
                  <el-table-column prop="comp_name" label="设施名称" />
                  <el-table-column prop="comp_type" label="设施类型" />
                  <el-table-column prop="comp_status" label="故障等级">
                    <template #default="scope">
                      <el-tag
                        :type="scope.row.comp_status === '紧急故障' ? 'danger' : scope.row.comp_status === '一般故障' ? 'warning' : 'success'"
                      >
                        {{ scope.row.comp_status }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column prop="report_time" label="上报时间">
                    <template #default="scope">
                      <span :class="{ timeout: scope.row.is_timeout }">{{ scope.row.report_time }}</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="handle_status" label="处置状态">
                    <template #default="scope">
                      <el-tag
                        :type="scope.row.handle_status === '未处置' ? 'danger' : scope.row.handle_status === '处置中' ? 'info' : 'success'"
                      >
                        {{ scope.row.handle_status }}
                      </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column label="操作">
                    <template #default="scope">
                      <el-button
                        size="small"
                        type="primary"
                        @click.stop="handleDispatch(scope.row)"
                        v-if="scope.row.handle_status !== '已完成'"
                      >
                        派单
                      </el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </transition-group>
            </div>
            <div class="panel-footer"></div>
          </div>
        </div>
        <div class="right-chart-area">
          <div class="chart-panels">
            <div class="panel chart-panel pie-panel" ref="piePanel">
              <div class="panel-header">
                <h3>故障类型分布</h3>
                <div class="header-actions">
                  <button class="panel-fullscreen-btn" @click="toggleFullscreen('piePanel')">
                    <el-icon color="#4f46e5" size="16">
                      <FullScreen/>
                    </el-icon>
                  </button>
                </div>
              </div>
              <div class="panel-body">
                <ChartPie :data="faultTypeDistribution" />
              </div>
              <div class="panel-footer"></div>
            </div>
            <div class="panel chart-panel trend-panel" ref="trendPanel">
              <div class="panel-header">
                <h3>近7天故障趋势</h3>
                <div class="header-actions">
                  <button class="panel-fullscreen-btn" @click="toggleFullscreen('trendPanel')">
                    <el-icon color="#14b8a6" size="16">
                      <FullScreen/>
                    </el-icon>
                  </button>
                </div>
              </div>
              <div class="panel-body">
                <ChartLine :xAxis="faultTrend.xAxis" :series="faultTrend.series" :yAxisName="''" />
              </div>
              <div class="panel-footer"></div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <el-dialog
      v-model="workOrderDetailVisible"
      :title="currentFault?.comp_name || '故障工单详情'"
      width="700px"
      class="work-order-dialog"
    >
      <div class="work-order-detail">
        <div class="detail-section">
          <h3>设施基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="设施ID">{{ currentFault?.mng_comp_id }}</el-descriptions-item>
            <el-descriptions-item label="设施名称">{{ currentFault?.comp_name }}</el-descriptions-item>
            <el-descriptions-item label="设施类型">{{ currentFault?.comp_type }}</el-descriptions-item>
            <el-descriptions-item label="故障等级">{{ currentFault?.comp_status }}</el-descriptions-item>
            <el-descriptions-item label="故障原因">{{ currentFault?.fault_reason }}</el-descriptions-item>
            <el-descriptions-item label="上报时间">{{ currentFault?.report_time }}</el-descriptions-item>
            <el-descriptions-item label="坐标位置">
              ({{ currentFault?.coord_x?.toFixed(6) }}, {{ currentFault?.coord_y?.toFixed(6) }})
            </el-descriptions-item>
            <el-descriptions-item label="处置状态">{{ currentFault?.handle_status }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section" v-if="currentFault?.handle_status !== '未处置'">
          <h3>处置信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="处置人员">{{ currentFault?.handler || '未分配' }}</el-descriptions-item>
            <el-descriptions-item label="更新时间">{{ currentFault?.update_time }}</el-descriptions-item>
            <el-descriptions-item label="处置措施">{{ currentFault?.handle_measures || '未制定' }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <div class="detail-section" v-if="workOrderId">
          <h3>工单信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="工单ID">{{ workOrderId }}</el-descriptions-item>
            <el-descriptions-item label="派单时间">{{ dispatchTime }}</el-descriptions-item>
            <el-descriptions-item label="派单状态">已派单</el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
      <template #footer>
        <el-button @click="workOrderDetailVisible = false">关闭</el-button>
        <el-button
          type="primary"
          @click="handleCompleteRepair"
          v-if="currentFault?.handle_status === '处置中'"
        >
          标记完成
        </el-button>
      </template>
    </el-dialog>

    <el-dialog
      v-model="dispatchVisible"
      title="故障抢修派单"
      width="600px"
      class="dispatch-dialog"
      :close-on-click-modal="false"
    >
      <el-form :model="dispatchForm" label-width="120px" :rules="dispatchRules" ref="dispatchFormRef">
        <el-form-item label="维修人员" prop="handler">
          <el-select v-model="dispatchForm.handler" placeholder="选择维修人员">
            <el-option label="张三" value="张三" />
            <el-option label="李四" value="李四" />
            <el-option label="王五" value="王五" />
            <el-option label="赵六" value="赵六" />
          </el-select>
        </el-form-item>
        <el-form-item label="预计完成时间" prop="expectTime">
          <el-date-picker
            v-model="dispatchForm.expectTime"
            type="datetime"
            placeholder="选择预计完成时间"
            format="YYYY-MM-DD HH:mm:ss"
            value-format="YYYY-MM-DD HH:mm:ss"
          />
        </el-form-item>
        <el-form-item label="派单备注" prop="remark">
          <el-input
            v-model="dispatchForm.remark"
            type="textarea"
            rows="3"
            placeholder="输入派单备注（选填）"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dispatchVisible = false">取消</el-button>
        <el-button type="primary" @click="submitDispatch">确认派单</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {computed, getCurrentInstance, onMounted, ref, watch} from 'vue';
import {useRouter} from 'vue-router';
import {ElMessage, ElMessageBox} from 'element-plus';
import screenFull from 'screenfull';
import ChartLine from './ChartLine2.vue';
import ChartPie from './ChartPie2.vue';
import MunicipalFacilityMap from './MunicipalFacilityMap.vue';
import {FullScreen} from "@element-plus/icons-vue";
import {
  createRepairWorkOrder,
  fetchFacilityOverview,
  fetchFacilityTypeDict,
  fetchFaultFacilitySpatial,
  fetchFaultTrend,
  fetchFaultTypeDistribution,
  markFaultHandled
} from '@/api/overview/urbanmanagement/SubdomainScenarioTopics.js';

const router = useRouter();
const instance = getCurrentInstance();

// 全屏切换功能
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

// 基础数据
const overviewData = ref({
  emergencyCount: 0,
  normalCount: 0,
  totalFault: 0,
  handledCount: 0,
  handlingRate: 0,
  timeoutCount: 0
});
const faultFacilityList = ref([]);
const facilityTypeDict = ref([]);
const faultTrend = ref({ xAxis: [], series: [] });
const faultTypeDistribution = ref({ legend: [], series: [] });
const hasNewEmergencyFault = ref(false);

// 筛选参数
const filterParams = ref({
  faultLevel: '',
  facilityType: [],
  handleStatus: '',
  timeRange: ''
});

// 弹窗相关
const workOrderDetailVisible = ref(false);
const dispatchVisible = ref(false);
const currentFault = ref(null);
const workOrderId = ref('');
const dispatchTime = ref('');

// 派单表单
const dispatchForm = ref({
  handler: '',
  expectTime: '',
  remark: ''
});

// 表单校验
const dispatchRules = ref({
  handler: [{ required: true, message: '请选择维修人员', trigger: 'change' }],
  expectTime: [{ required: true, message: '请选择预计完成时间', trigger: 'change' }]
});
const dispatchFormRef = ref(null);

// 计算属性
const todayNormalCount = computed(() => {
  const today = new Date().toLocaleDateString();
  return faultFacilityList.value.filter(item =>
    item.comp_status === '一般故障' &&
    new Date(item.report_time).toLocaleDateString() === today
  ).length;
});

const filteredFaultList = computed(() => {
  return faultFacilityList.value.filter(item => {
    const matchLevel = !filterParams.value.faultLevel || item.comp_status === filterParams.value.faultLevel;
    const matchType = filterParams.value.facilityType.length === 0 || filterParams.value.facilityType.includes(item.comp_type);
    const matchStatus = !filterParams.value.handleStatus || item.handle_status === filterParams.value.handleStatus;

    let matchTime = true;
    if (filterParams.value.timeRange) {
      const today = new Date();
      const reportDate = new Date(item.report_time);
      const todayStr = today.toLocaleDateString();
      const yesterdayStr = new Date(today.setDate(today.getDate() - 1)).toLocaleDateString();
      const weekStart = new Date(today.setDate(today.getDate() - today.getDay() + 1)).toLocaleDateString();

      switch (filterParams.value.timeRange) {
        case 'today':
          matchTime = reportDate.toLocaleDateString() === todayStr;
          break;
        case 'yesterday':
          matchTime = reportDate.toLocaleDateString() === yesterdayStr;
          break;
        case 'week':
          matchTime = reportDate.toLocaleDateString() >= weekStart;
          break;
        default:
          matchTime = true;
      }
    }
    return matchLevel && matchType && matchStatus && matchTime;
  });
});

// 页面操作方法
const refreshData = async () => {
  try {
    await initData();
    ElMessage.success('市政设施故障数据已刷新');
  } catch (error) {
    ElMessage.error('数据刷新失败');
    console.error(error);
  }
};

const handleBack = () => {
  router.push('/overview/urbanmanagement/dpzl');
};

const handleTaskRowClick = (row) => {
  currentFault.value = row;
  workOrderId.value = '';
  dispatchTime.value = '';
  workOrderDetailVisible.value = true;
};

const handleDispatch = (row) => {
  currentFault.value = row;
  dispatchForm.value = { handler: '', expectTime: '', remark: '' };
  dispatchVisible.value = true;
};

const submitDispatch = async () => {
  if (!dispatchFormRef.value) return;
  try {
    await dispatchFormRef.value.validate();
    const res = await createRepairWorkOrder({
      mng_comp_id: currentFault.value.mng_comp_id,
      handler: dispatchForm.value.handler,
      expect_time: dispatchForm.value.expectTime,
      remark: dispatchForm.value.remark
    });
    if (res.code === 200) {
      workOrderId.value = res.data.wo_id;
      dispatchTime.value = new Date().toLocaleString();
      ElMessage.success('派单成功！');
      dispatchVisible.value = false;

      const faultIndex = faultFacilityList.value.findIndex(item => item.mng_comp_id === currentFault.value.mng_comp_id);
      if (faultIndex > -1) {
        faultFacilityList.value[faultIndex].handle_status = '处置中';
        faultFacilityList.value[faultIndex].handler = dispatchForm.value.handler;
        faultFacilityList.value[faultIndex].update_time = new Date().toLocaleString();
      }
      await fetchOverviewData();
    }
  } catch (error) {
    console.error('派单失败：', error);
    ElMessage.error('派单失败，请重试！');
  }
};

const handleCompleteRepair = async () => {
  try {
    await ElMessageBox.confirm('确认该故障已处置完成？', '提示', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    });
    const res = await markFaultHandled(currentFault.value.mng_comp_id);
    if (res.code === 200) {
      const faultIndex = faultFacilityList.value.findIndex(item => item.mng_comp_id === currentFault.value.mng_comp_id);
      if (faultIndex > -1) {
        faultFacilityList.value[faultIndex].handle_status = '已完成';
        faultFacilityList.value[faultIndex].comp_status = '完好';
        faultFacilityList.value[faultIndex].update_time = new Date().toLocaleString();
        faultFacilityList.value[faultIndex].handle_measures = '已完成处置';
      }
      ElMessage.success('处置完成！');
      workOrderDetailVisible.value = false;
      await fetchOverviewData();
    }
  } catch (error) {
    if (error !== 'cancel') {
      console.error('标记完成失败：', error);
      ElMessage.error('标记完成失败，请重试！');
    }
  }
};

const filterByLevel = (level) => {
  filterParams.value.faultLevel = level;
};

const resetFilter = () => {
  filterParams.value = {
    faultLevel: '',
    facilityType: [],
    handleStatus: '',
    timeRange: ''
  };
};

// 数据请求方法
const fetchOverviewData = async () => {
  const data = await fetchFacilityOverview();
  overviewData.value = data;
  if (data.emergencyCount > 0) {
    hasNewEmergencyFault.value = true;
    setTimeout(() => hasNewEmergencyFault.value = false, 5000);
  }
};

const fetchFaultData = async () => {
  faultFacilityList.value = await fetchFaultFacilitySpatial();
};

const fetchTypeDict = async () => {
  facilityTypeDict.value = await fetchFacilityTypeDict();
};

const fetchChartData = async () => {
  faultTrend.value = await fetchFaultTrend();
  faultTypeDistribution.value = await fetchFaultTypeDistribution();
};

const initData = async () => {
  await fetchOverviewData();
  await fetchFaultData();
  await fetchTypeDict();
  await fetchChartData();
};

// 监听筛选参数变化
watch(filterParams, () => {
  const filtered = filteredFaultList.value;
  overviewData.value.emergencyCount = filtered.filter(item => item.comp_status === '紧急故障').length;
  overviewData.value.normalCount = filtered.filter(item => item.comp_status === '一般故障').length;
  overviewData.value.totalFault = filtered.length;
  overviewData.value.handledCount = filtered.filter(item => item.handle_status === '已完成').length;
  overviewData.value.handlingRate = filtered.length > 0
    ? Math.round((overviewData.value.handledCount / filtered.length) * 100 * 10) / 10
    : 0;
  overviewData.value.timeoutCount = filtered.filter(item => item.is_timeout).length;
}, { deep: true });

// 生命周期
onMounted(async () => {
  await initData();
  setTimeout(() => {
    hasNewEmergencyFault.value = true;
    setTimeout(() => hasNewEmergencyFault.value = false, 5000);
  }, 3000);
});
</script>

<style lang="scss" scoped>
// 全屏按钮样式
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

// 通用面板样式
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

  // 不同面板专属渐变顶部边框
  &.map-container {
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
    .panel-header h3 { color: #00c6ff; }
    .panel-body {
      width: 100%;
      height: 100%;
      padding: 0;
    }
  }

  &.pie-panel {
    &::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 3px;
      background: linear-gradient(90deg, #4f46e5, rgba(79, 70, 229, 0.6));
      z-index: 1;
    }
    .panel-header h3 { color: #4f46e5; }
  }

  &.trend-panel {
    &::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 3px;
      background: linear-gradient(90deg, #14b8a6, rgba(20, 184, 166, 0.6));
      z-index: 1;
    }
    .panel-header h3 { color: #14b8a6; }
  }

  &.filter-bar {
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
    .panel-header h3 { color: #f7931e; }
  }

  &.task-list-container {
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
    .panel-header h3 { color: #ec4899; }
  }
}

// 页头样式
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
    background: linear-gradient(90deg, #facc15, #ec4899, #00c6ff);
  }

  h1 {
    margin: 0;
    font-size: 1.6vw;
    background: linear-gradient(90deg, #facc15, #00c6ff, #ec4899);
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
    --el-button-border-color: #00c6ff;
    --el-button-hover-bg-color: rgba(0, 198, 255, 0.4);
    --el-button-hover-border-color: #ec4899;
    --el-button-active-bg-color: rgba(236, 72, 153, 0.3);
    --el-button-active-border-color: #ec4899;
    border-radius: 4px;
    font-size: 0.85vw;
    padding: 0.6vh 1.2vw;
    transition: all 0.3s ease;
  }
}

// 主体内容
.main-content {
  display: flex;
  gap: 1.2vw;
  height: calc(100vh - 12vh);

  .map-area {
    flex: 6;
    height: 100%;
    display: flex;
    flex-direction: column;
    gap: 1vw;

    .map-top-stats {
      width: 100%;

      .stats-cards {
        display: flex;
        gap: 1vw;
        width: 100%;
        box-sizing: border-box;

        .stat-card {
          flex: 1;
          background: rgba(0, 68, 102, 0.8);
          border-radius: 8px;
          padding: 0.8vw 1vw;
          border: 1px solid rgba(0, 198, 255, 0.3);
          box-shadow: 0 0 1vw rgba(0, 198, 255, 0.2);
          cursor: pointer;
          transition: all 0.3s ease;
          box-sizing: border-box;
          position: relative;
          overflow: hidden;

          // 卡片背景渐变点缀
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
            transform: translateY(-3px);
            box-shadow: 0 0 20px rgba(0, 198, 255, 0.3);
            border-color: transparent;
          }

          &.emergency-card {
            &:hover {
              box-shadow: 0 0 20px rgba(255, 77, 109, 0.3);
              border-color: #ff4d6d;
            }
            &.pulse {
              animation: pulse-red 1s infinite alternate;
            }
          }

          &.normal-card {
            &:hover {
              box-shadow: 0 0 20px rgba(247, 147, 30, 0.3);
              border-color: #f7931e;
            }
          }

          &.rate-card {
            &:hover {
              box-shadow: 0 0 20px rgba(255, 77, 109, 0.3);
              border-color: #ff4d6d;
            }

            .stat-title::before { background: #ff4d6d; }
            .stat-title { color: #ff99aa; }

            // 处置率卡片背景
            &.danger-rate {
              background: rgba(255, 179, 186, 0.2);
              .stat-value {
                color: #ff4d6d;
                background: linear-gradient(90deg, #ff4d6d, #ff99aa);
                -webkit-background-clip: text;
                background-clip: text;
              }
              .stat-title, .stat-desc { color: #ff99aa; }
            }

            &.normal-rate {
              background: rgba(255, 204, 211, 0.1);
              .stat-value {
                color: #ff4d6d;
                background: linear-gradient(90deg, #ff7086, #ffc4cc);
                -webkit-background-clip: text;
                background-clip: text;
              }
              .stat-title, .stat-desc { color: #ffc4cc; }
            }
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
          }

          .stat-desc {
            font-size: 0.7vw;
            color: #67e8f9;
            line-height: 1.4;
            position: relative;
            z-index: 1;
          }

          // 不同卡片专属配色
          &.emergency-card {
            .stat-title::before { background: #ff4d6d; }
            .stat-title { color: #ff99aa; }
            .stat-value {
              color: #ff4d6d;
              background: linear-gradient(90deg, #ff4d6d, #ff99aa);
              -webkit-background-clip: text;
              background-clip: text;
            }
          }

          &.normal-card {
            .stat-title::before { background: #f7931e; }
            .stat-title { color: #fbb866; }
            .stat-value {
              color: #f7931e;
              background: linear-gradient(90deg, #f7931e, #fbb866);
              -webkit-background-clip: text;
              background-clip: text;
            }
          }
        }
      }
    }

    .map-container {
      width: 100%;
      flex: 1;
      position: relative;
      padding: 0;
    }
  }

  .right-area {
    flex: 4;
    display: flex;
    flex-direction: column;
    gap: 1vw;
    height: 100%;

    .right-chart-area {
      height: 28vh;
      width: 100%;

      .chart-panels {
        display: flex;
        gap: 1vw;
        height: 100%;
        width: 100%;

        .chart-panel {
          height: 100%;
          width: 100%;

          .panel-body {
            padding: 0.5vw;
            height: calc(100% - 2vh);
            width: 100%;
          }
        }

        .pie-panel { flex: 4; }
        .trend-panel { flex: 6; }
      }
    }

    .right-list-area {
      flex: 1;
      display: flex;
      flex-direction: column;
      gap: 1vw;
      height: calc(100% - 28vh - 1vw);

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

        .el-select, .el-button {
          font-size: 0.8vw;
        }

        .el-select {
          width: calc(20% - 0.8vw);
          max-width: 12vw;
          --el-select-text-color: #ffffff;
          --el-select-border-color: #f7931e;
          --el-select-hover-border-color: #ec4899;
          --el-select-dropdown-bg-color: rgba(0, 68, 102, 0.5);
          --el-select-option-hover-bg-color: rgba(247, 147, 30, 0.2);
        }

        // 批量派单按钮专属样式
        .batch-dispatch-btn {
          --el-button-bg-color: linear-gradient(90deg, #ff6b6b, #ff8e8e);
          --el-button-border-color: transparent;
          --el-button-text-color: #ffffff;
          --el-button-hover-bg-color: linear-gradient(90deg, #ff5252, #ff7979);
          --el-button-hover-border-color: transparent;
          --el-button-active-bg-color: linear-gradient(90deg, #e53935, #f44336);
          --el-button-active-border-color: transparent;
          padding: 0.6vh 1.5vw;
          border-radius: 6px;
          font-weight: 600;
          box-shadow: 0 2px 8px rgba(255, 107, 107, 0.3);
          transition: all 0.3s ease;

          &:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(255, 82, 82, 0.4);
          }

          &:active {
            transform: translateY(0);
            box-shadow: 0 2px 6px rgba(229, 57, 53, 0.5);
          }
        }
      }

      .task-list-container {
        flex: 1;
        background: rgba(0, 47, 75, 0.4);
        border-radius: 8px;
        border: 1px solid rgba(0, 198, 255, 0.3);
        overflow-y: auto;
        position: relative;

        .table-scroll {
          overflow-y: auto;
          height: calc(100% - 2vh - 1vh); // 减去header和footer高度

          &::-webkit-scrollbar {
            width: 6px;
            height: 6px;
          }
          &::-webkit-scrollbar-track {
            background: rgba(0, 68, 102, 0.8);
            border-radius: 3px;
          }
          &::-webkit-scrollbar-thumb {
            background: linear-gradient(180deg, #00c6ff, #ec4899);
            border-radius: 3px;
          }
          &::-webkit-scrollbar-thumb:hover {
            background: linear-gradient(180deg, #ec4899, #7e57c2);
          }
        }

        .batch-dispatch-btn {
          position: absolute;
          bottom: 1vw;
          right: 1vw;
          z-index: 10;

          .el-button {
            --el-button-bg-color: linear-gradient(90deg, #ec4899, #7e57c2);
            --el-button-border-color: transparent;
            --el-button-hover-bg-color: linear-gradient(90deg, #f074b3, #9878d0);
            --el-button-hover-border-color: transparent;
            padding: 1vh 2vw;
            font-size: 1vw;
            font-weight: 600;
          }
        }

        .task-list-enter-active, .task-list-leave-active {
          transition: all 0.5s ease;
        }
        .task-list-enter-from, .task-list-leave-to {
          opacity: 0;
          transform: translateY(30px);
        }

        .el-table {
          --el-table-bg-color: transparent;
          --el-table-text-color: #ffffff;
          --el-table-border-color: rgba(0, 198, 255, 0.3);
          --el-table-header-text-color: #facc15;
          --el-table-header-bg-color: rgba(0, 68, 102, 0.5);
          --el-table-row-hover-bg-color: rgba(0, 198, 255, 0.1);
          --el-table-row-stripes-bg-color: rgba(0, 68, 102, 0.3);

          .timeout {
            color: #ff4d6d !important;
            font-weight: 500;
            text-shadow: 0 0 3px rgba(255, 77, 109, 0.3);
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
      }
    }
  }
}

// 弹窗样式
.work-order-dialog, .dispatch-dialog, .batch-dispatch-dialog {
  --el-dialog-bg-color: rgba(0, 47, 75, 0.4);
  --el-dialog-border-color: rgba(0, 198, 255, 0.3);
  --el-dialog-title-color: #facc15;
  --el-dialog-text-color: #ffffff;
  --el-dialog-header-border-color: rgba(0, 198, 255, 0.3);
  --el-dialog-footer-border-color: rgba(0, 198, 255, 0.3);
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(0, 198, 255, 0.3), 0 0 40px rgba(236, 72, 153, 0.1);

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
      background: linear-gradient(90deg, #00c6ff, #ec4899, #f7931e, transparent);
    }
  }

  .work-order-detail {
    .detail-section {
      margin-bottom: 24px;

      h3 {
        font-size: 16px;
        color: #facc15;
        margin-bottom: 12px;
        padding-bottom: 6px;
        border-bottom: 1px solid rgba(0, 198, 255, 0.3);
        font-weight: 600;
        background: linear-gradient(90deg, #facc15, #f7931e);
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
        --el-select-border-color: #ec4899;
        --el-select-hover-border-color: #7e57c2;
      }

      .el-date-picker {
        --el-input-border-color: #14b8a6;
        --el-input-hover-border-color: #00c6ff;
      }

      .el-input {
        --el-input-border-color: #4f46e5;
        --el-input-hover-border-color: #ec4899;
      }
    }
  }

  .el-dialog__footer {
    border-top: 1px solid rgba(0, 198, 255, 0.3);
    padding-top: 15px;
  }
}

// 标签样式
.el-tag--danger {
  --el-tag-bg-color: rgba(255, 77, 109, 0.2);
  --el-tag-border-color: #ff4d6d;
  --el-tag-text-color: #ff4d6d;
}

.el-tag--warning {
  --el-tag-bg-color: rgba(247, 147, 30, 0.2);
  --el-tag-border-color: #f7931e;
  --el-tag-text-color: #f7931e;
}

.el-tag--success {
  --el-tag-bg-color: rgba(56, 176, 0, 0.2);
  --el-tag-border-color: #38b000;
  --el-tag-text-color: #38b000;
}

.el-tag--info {
  --el-tag-bg-color: rgba(0, 198, 255, 0.2);
  --el-tag-border-color: #00c6ff;
  --el-tag-text-color: #00c6ff;
}

// 动画
@keyframes pulse-red {
  0% {
    box-shadow: 0 0 0 0 rgba(255, 77, 109, 0.7);
  }
  100% {
    box-shadow: 0 0 0 15px rgba(255, 77, 109, 0);
  }
}

@keyframes dialog-fade {
  0% {
    opacity: 0;
    transform: translate(-50%, -50%) scale(0.8);
  }
  100% {
    opacity: 1;
    transform: translate(-50%, -50%) scale(1);
  }
}

// 表格深度样式
::v-deep .el-table {
  table-layout: fixed;
  background-color: rgba(0, 47, 75, 0.4);
  border-color: rgba(0, 198, 255, 0.3);

  th {
    background-color: rgba(0, 68, 102, 0.5);
    color: #facc15;
    font-size: 0.8vw;
    padding: 0.5vw 0;
    text-align: center;
    border-color: rgba(0, 198, 255, 0.3);
    font-weight: 500;
    background: linear-gradient(90deg, rgba(0, 68, 102, 0.5), rgba(0, 47, 75, 0.5));
  }

  td {
    background-color: transparent;
    color: #ffffff;
    font-size: 0.7vw;
    border-bottom: 1px solid rgba(0, 198, 255, 0.3);
    padding: 0.4vw 0;
    text-align: center;
    transition: all 0.2s;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .el-table__row {
    background-color: rgba(0, 47, 75, 0.3);
    &:nth-child(even) {
      background-color: rgba(0, 68, 102, 0.3);
    }
    &:hover > td {
      background-color: rgba(0, 198, 255, 0.1) !important;
      color: #facc15;
    }
  }

  .el-table__row--current > td {
    background-color: rgba(236, 72, 153, 0.1) !important;
    color: #ec4899;
  }

  .el-table__empty-text {
    color: #67e8f9;
    background-color: rgba(0, 47, 75, 0.4);
  }

  &::before {
    background-color: rgba(0, 198, 255, 0.3);
  }

  .el-table__fixed-right::before,
  .el-table__fixed::before {
    background-color: transparent;
  }
}

// 容器基础样式
.municipal-facility-container {
  width: 100%;
  height: 100vh;
  overflow: auto;
  background: url("@/assets/chart/images/bg.jpg") no-repeat center center;
  background-size: cover;
  color: #ffffff;
  padding: 0 1.5vw;
  box-sizing: border-box;
  touch-action: pan-x pan-y;
  // 叠加科技蓝渐变增强层次感
  &::after {
    content: '';
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(135deg, rgba(0, 47, 75, 0.5) 0%, rgba(0, 102, 153, 0.4) 100%);
    z-index: -1;
  }
}
</style>
