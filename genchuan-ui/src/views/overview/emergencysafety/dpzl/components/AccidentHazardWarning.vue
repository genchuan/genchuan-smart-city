<template>
  <div class="page-container">
    <!-- 页头区域：标题及核心操作 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/hazard.png"
          alt="事故隐患图标"
          class="title-icon"
        />
        事故隐患预警视图
      </h1>
      <div class="header-actions">
        <el-button size="small" @click="refreshData">刷新</el-button>
        <el-button size="small" @click="handleBack">返回总览</el-button>
      </div>
    </div>

    <!-- 主内容区域 -->
    <div class="main-content">
      <!-- 核心预警指标卡片组 -->
      <div class="stats-cards">
        <div class="stat-card">
          <div class="stat-title">总隐患数</div>
          <div class="stat-value normal">
            {{ totalHazards }} <span class="unit">项</span>
          </div>
          <div class="stat-desc">
            高风险隐患数: <span class="level-item poor">{{ majorHazardEvents }}项</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">隐患处理状态</div>
          <div class="stat-value" style="font-size: 1.1vw !important; line-height: 1.4;">
            <span class="level-item poor">待处理: {{ pendingHandleHazards }}项</span>
            <span class="level-item good">处理中: {{ handlingHazards }}项</span>
          </div>
          <div class="stat-desc">
            已处理: <span class="level-item excellent">{{ handledHazards }}项</span>
            逾期: <span class="level-item poor">{{ overdueHandleHazards }}项</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">核心处理效率</div>
          <div class="stat-value" style="font-size: 1.1vw !important; line-height: 1.4;">
            <span class="level-item excellent">完成率: {{ hazardHandleRate }}%</span>
            <span class="level-item good">及时率: {{ timelyHandleRate }}%</span>
          </div>
          <div class="stat-desc">
            平均处理时长: <span class="level-item average">{{ avgHandleDuration }}分钟</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">各环节平均耗时</div>
          <div class="stat-value" style="font-size: 1vw !important; line-height: 1.4;">
            <span class="level-item poor">核查: {{ avgStageDuration.verify }}分钟</span>
            <span class="level-item poor">整改: {{ avgStageDuration.rectify }}分钟</span>
          </div>
          <div class="stat-desc">
            最近同步: <span class="update-time">{{ lastSyncTime }}</span>
          </div>
        </div>
      </div>

      <!-- 图表行1：预警级别分布 + 环节耗时分析 -->
      <div class="chart-row">
        <!-- 左侧：预警级别分布饼图 -->
        <div class="panel stage-panel" ref="stagePanel">
          <div class="panel-header">
            <h2>预警级别分布</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('stagePanel')">
                <el-icon color="#ff6b6b" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartPie :labels="stageLabels" :data="stageData" :colors="stageColors" />
          </div>
        </div>

        <!-- 右侧：各环节处理时长分析柱状图 -->
        <div class="panel duration-panel" ref="durationPanel">
          <div class="panel-header">
            <h2>各环节处理时长分析</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('durationPanel')">
                <el-icon color="#ff6b6b" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartBar :xAxis="durationXAxis" :series="durationSeries" />
          </div>
        </div>

        <!-- 图表行2：近7天隐患预警趋势 -->
        <div class="panel trend-panel" ref="trendPanel">
          <div class="panel-header">
            <h2>近7天隐患预警趋势</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('trendPanel')">
                <el-icon color="#ff6b6b" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartLine :xAxis="trendXAxis" :series="trendSeries" />
          </div>
        </div>
      </div>
    </div>

    <!-- 隐患预警表格（核心：带进度+状态+耗时） -->
    <div class="panel data-table-panel" ref="dataTablePanel">
      <div class="panel-header">
        <h2>事故隐患预警列表</h2>
        <div class="header-actions">
          <el-select v-model="filterType" placeholder="隐患类型" size="small" class="filter-item">
            <el-option label="全部" value="" />
            <el-option label="火灾隐患" value="fire_hazard" />
            <el-option label="电气隐患" value="electrical_hazard" />
            <el-option label="结构隐患" value="structural_hazard" />
            <el-option label="设备隐患" value="equipment_hazard" />
            <el-option label="操作隐患" value="operation_hazard" />
            <el-option label="环境隐患" value="environment_hazard" />
            <el-option label="其他隐患" value="other_hazard" />
          </el-select>
          <el-select v-model="filterStage" placeholder="处理状态" size="small" class="filter-item">
            <el-option label="全部" value="" />
            <el-option label="待上报" value="pending_report" />
            <el-option label="已派单" value="dispatched" />
            <el-option label="已核查" value="verified" />
            <el-option label="整改中" value="rectifying" />
            <el-option label="待验收" value="pending_acceptance" />
            <el-option label="已销号" value="cancelled" />
          </el-select>
          <el-select v-model="filterPriority" placeholder="预警级别" size="small" class="filter-item">
            <el-option label="全部" value="" />
            <el-option label="轻微隐患" value="minor" />
            <el-option label="一般隐患" value="general" />
            <el-option label="较大隐患" value="medium" />
            <el-option label="重大隐患" value="major" />
          </el-select>
          <el-input
            v-model="searchKeyword"
            placeholder="搜索隐患名称/ID"
            size="small"
            class="filter-item search-input"
            @keyup.enter="fetchHazardWarnings"
          >
            <template #append>
              <el-icon class="search-icon">
                <Search />
              </el-icon>
            </template>
          </el-input>
          <el-button size="small" type="danger" @click="batchRemindOverdue">
            <WarningFilled size="14" /> 逾期隐患提醒
          </el-button>
          <el-button size="small" type="primary" @click="exportHazardData">导出数据</el-button>
          <button class="panel-fullscreen-btn" @click="toggleFullscreen('dataTablePanel')">
            <el-icon color="#ff6b6b" size="16">
              <FullScreen/>
            </el-icon>
          </button>
        </div>
      </div>
      <div class="panel-body table-scroll">
        <el-table
          :data="filteredEventList"
          border
          size="small"
          style="width: 100%"
          @row-click="showHazardDetail"
        >
          <el-table-column prop="hazard_id" label="隐患ID" width="120" />
          <el-table-column prop="hazard_name" label="隐患名称" min-width="200" />
          <el-table-column prop="hazard_type" label="隐患类型">
            <template #default="scope">
              <el-tag :type="getHazardTypeTagType(scope.row.hazard_type)">
                {{ getHazardTypeName(scope.row.hazard_type) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="warning_level" label="预警级别">
            <template #default="scope">
              <el-tag :type="getWarningLevelTagType(scope.row.warning_level)">
                {{ getWarningLevelName(scope.row.warning_level) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="handle_status" label="处理状态">
            <template #default="scope">
              <el-tag :type="getHandleStatusTagType(scope.row.handle_status)">
                {{ getHandleStatusName(scope.row.handle_status) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="整改进度">
            <template #default="scope">
              <div class="progress-container">
                <el-progress
                  :percentage="scope.row.progress"
                  :status="getProgressStatus(scope.row.progress)"
                  size="small"
                  :stroke-width="8"
                  style="width: 100px; display: inline-block;"
                  :show-text="false"
                />
                <span class="progress-text">{{ scope.row.progress }}%</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="expected_complete_time" label="预计完成时间">
            <template #default="scope">
                <span :class="isCompleteOverdue(scope.row) ? 'text-red-500 font-bold' : ''">
                  {{ scope.row.expected_complete_time }}
                </span>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="160" align="center">
            <template #default="scope">
              <el-button size="small" link @click.stop="showHazardDetail(scope.row)">详情</el-button>
              <el-button
                v-if="scope.row.handle_status !== 'cancelled'"
                size="small"
                link
                @click.stop="quickUpdateProgress(scope.row)"
              >
                更新进度
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <!-- 分页 -->
      <div class="panel-footer pagination-container">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[10, 20, 50]"
          :page-size="pageSize"
          :total="eventList.length"
          layout="total, sizes, prev, pager, next"
          size="small"
        />
      </div>
    </div>
  </div>

  <!-- 隐患详情弹窗（核心：基础信息+时间线+进度更新） -->
  <el-dialog
    v-model="hazardDetailVisible"
    :title="currentHazard?.hazard_name || '事故隐患预警详情'"
    width="50%"
    :close-on-click-modal="false"
  >
    <div class="hazard-detail">
      <!-- 基础信息区域 -->
      <div class="detail-section">
        <h3>基础信息</h3>
        <el-descriptions column="2" border>
          <el-descriptions-item label="隐患ID">{{ currentHazard?.hazard_id }}</el-descriptions-item>
          <el-descriptions-item label="隐患类型">{{ getHazardTypeName(currentHazard?.hazard_type) }}</el-descriptions-item>
          <el-descriptions-item label="预警级别">{{ getWarningLevelName(currentHazard?.warning_level) }}</el-descriptions-item>
          <el-descriptions-item label="所属区域">{{ currentHazard?.area }}</el-descriptions-item>
          <el-descriptions-item label="预警时间">{{ currentHazard?.warning_time || '未上报' }}</el-descriptions-item>
          <el-descriptions-item label="派单时间">{{ currentHazard?.dispatch_time || '未派单' }}</el-descriptions-item>
          <el-descriptions-item label="处理状态">{{ getHandleStatusName(currentHazard?.handle_status) }}</el-descriptions-item>
          <el-descriptions-item label="整改进度">
            <el-progress
              :percentage="currentHazard?.progress || 0"
              :status="getProgressStatus(currentHazard?.progress || 0)"
              size="small"
              :stroke-width="8"
              style="width: 100px; display: inline-block;"
              :show-text="false"
            />
            <span class="ml-2">{{ currentHazard?.progress || 0 }}%</span>
          </el-descriptions-item>
          <el-descriptions-item label="预计完成时间">
              <span :class="isDetailCompleteOverdue() ? 'text-red-500 font-bold' : ''">
                {{ currentHazard?.expected_complete_time }}
              </span>
          </el-descriptions-item>
          <el-descriptions-item label="负责部门">{{ currentHazard?.responsible_dept }}</el-descriptions-item>
          <el-descriptions-item label="负责人">{{ currentHazard?.leader }}</el-descriptions-item>
          <el-descriptions-item label="实际完成时间">{{ currentHazard?.actual_complete_time || '处理中' }}</el-descriptions-item>
        </el-descriptions>
      </div>

      <!-- 隐患描述区域 -->
      <div class="detail-section">
        <h3>隐患描述</h3>
        <div class="content-card">
          {{ currentHazard?.description || '无详细描述' }}
        </div>
      </div>

      <!-- 处理时间线（核心：各环节耗时+操作记录） -->
      <div class="detail-section">
        <h3>处理时间线</h3>
        <el-timeline :reverse="false">
          <el-timeline-item
            v-for="(item, index) in hazardTimeline"
            :key="index"
            :timestamp="item.time"
            :color="getTimelineColor(item.stage)"
            placement="top"
          >
            <div class="timeline-content">
              <h4 class="timeline-stage">{{ getTimelineStageName(item.stage) }}</h4>
              <p class="timeline-operator">操作人：{{ item.operator }}</p>
              <p class="timeline-desc">{{ item.content }}</p>
              <p class="timeline-duration">耗时：{{ item.duration }}</p>
            </div>
          </el-timeline-item>
        </el-timeline>
      </div>

      <!-- 进度更新表单（核心操作） -->
      <div class="detail-section" v-if="currentHazard?.handle_status !== 'cancelled'">
        <h3>更新整改进度</h3>
        <el-card shadow="hover" class="progress-form-card">
          <el-form :model="progressForm" label-width="100px" size="small">
            <el-form-item label="处理状态" required>
              <el-select v-model="progressForm.status" placeholder="选择当前状态" required>
                <el-option label="待上报" value="pending_report"/>
                <el-option label="已派单" value="dispatched"/>
                <el-option label="已核查" value="verified"/>
                <el-option label="整改中" value="rectifying"/>
                <el-option label="待验收" value="pending_acceptance"/>
                <el-option label="已销号" value="cancelled"/>
              </el-select>
            </el-form-item>
            <el-form-item label="处理记录" required>
              <el-input
                v-model="progressForm.content"
                type="textarea"
                :rows="3"
                placeholder="请输入整改记录内容"
              />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="saveHazardProgress">提交进度更新</el-button>
              <el-button @click="resetProgressForm">取消</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </div>

    <!-- 弹窗底部操作按钮 -->
    <template #footer>
      <el-button type="primary" @click="refreshHazardDetail">刷新详情</el-button>
      <el-button @click="hazardDetailVisible = false">关闭</el-button>
    </template>
  </el-dialog>

  <!-- 快速更新进度弹窗 -->
  <el-dialog
    v-model="quickProgressVisible"
    title="快速更新整改进度"
    width="25%"
    :close-on-click-modal="false"
  >
    <el-form :model="quickProgressForm" label-width="100px" size="small">
      <el-form-item label="处理状态" required>
        <el-select v-model="quickProgressForm.status" placeholder="选择当前状态" required>
          <el-option label="待上报" value="pending_report"/>
          <el-option label="已派单" value="dispatched"/>
          <el-option label="已核查" value="verified"/>
          <el-option label="整改中" value="rectifying"/>
          <el-option label="待验收" value="pending_acceptance"/>
          <el-option label="已销号" value="cancelled"/>
        </el-select>
      </el-form-item>
      <el-form-item label="整改进度(%)" required>
        <el-input-number
          v-model="quickProgressForm.progress"
          :min="0"
          :max="100"
          :step="5"
          placeholder="输入整改进度"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="quickProgressVisible = false">取消</el-button>
      <el-button type="primary" @click="saveQuickProgress">确认更新</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, computed, onMounted, getCurrentInstance, watch } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { FullScreen, Search, WarningFilled } from "@element-plus/icons-vue";
import screenFull from 'screenfull';

// 导入事故隐患预警核心API
import {
  fetchHazardWarningOverview,
  fetchHazardLevelDistribution,
  fetchHazardHandleDurationAnalysis,
  fetchHazardWarningTrend7Days,
  fetchHazardWarningList,
  fetchHazardHandleTimeline,
  updateHazardHandleProgress,
  submitHazardHandleRecord
} from '@/api/overview/emergencysafety/GlobalSituationOverview.js';

// 导入图表组件
import ChartPie from './ChartPie1.vue';
import ChartBar from './ChartBar1.vue';
import ChartLine from './ChartLine1.vue';

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

// 筛选条件
const filterType = ref('');         // 隐患类型筛选
const filterStage = ref('');        // 处理状态筛选
const filterPriority = ref('');     // 预警级别筛选
const searchKeyword = ref('');      // 搜索关键词
const currentPage = ref(1);         // 当前页码
const pageSize = ref(10);           // 每页条数

// 核心预警指标数据
const totalHazards = ref(0);
const pendingHandleHazards = ref(0);
const handlingHazards = ref(0);
const handledHazards = ref(0);
const overdueHandleHazards = ref(0);
const avgHandleDuration = ref(0);
const avgStageDuration = ref({
  report: 0, dispatch: 0, verify: 0, rectify: 0, acceptance: 0, cancel: 0
});
const hazardHandleRate = ref(0);
const timelyHandleRate = ref(0);
const majorHazardEvents = ref(0);
const lastSyncTime = ref('');

// 图表数据
const stageLabels = ref([]);
const stageData = ref([]);
const stageColors = ref([]);
const durationXAxis = ref([]);
const durationSeries = ref([]);
const trendXAxis = ref([]);
const trendSeries = ref([]);

// 隐患预警数据
const eventList = ref([]);
const currentHazard = ref(null);
const hazardDetailVisible = ref(false);
const hazardTimeline = ref([]);

// 进度更新表单数据
const progressForm = ref({
  hazard_id: '',
  status: '',
  progress: 0,
  content: ''
});

// 快速更新进度弹窗
const quickProgressVisible = ref(false);
const quickProgressForm = ref({
  hazard_id: '',
  status: '',
  progress: 0
});

// ---------------------- 映射逻辑 ----------------------
// 隐患类型映射
const getHazardTypeName = (type) => {
  const map = {
    'fire_hazard': '火灾隐患',
    'electrical_hazard': '电气隐患',
    'structural_hazard': '结构隐患',
    'equipment_hazard': '设备隐患',
    'operation_hazard': '操作隐患',
    'environment_hazard': '环境隐患',
    'other_hazard': '其他隐患'
  };
  return map[type] || '未知类型';
};

const getHazardTypeTagType = (type) => {
  const map = {
    'fire_hazard': 'danger',
    'electrical_hazard': 'warning',
    'structural_hazard': 'error',
    'equipment_hazard': 'primary',
    'operation_hazard': 'info',
    'environment_hazard': 'success',
    'other_hazard': 'default'
  };
  return map[type] || 'default';
};

// 预警级别映射
const getWarningLevelName = (level) => {
  const map = {
    'minor': '轻微隐患',
    'general': '一般隐患',
    'medium': '较大隐患',
    'major': '重大隐患'
  };
  return map[level] || '未知级别';
};

const getWarningLevelTagType = (level) => {
  const map = {
    'minor': 'success',
    'general': 'info',
    'medium': 'warning',
    'major': 'danger'
  };
  return map[level] || 'default';
};

// 处理状态映射
const getHandleStatusName = (status) => {
  const map = {
    'pending_report': '待上报',
    'dispatched': '已派单',
    'verified': '已核查',
    'rectifying': '整改中',
    'pending_acceptance': '待验收',
    'cancelled': '已销号'
  };
  return map[status] || '未知状态';
};

const getHandleStatusTagType = (status) => {
  const map = {
    'pending_report': 'info',
    'dispatched': 'primary',
    'verified': 'warning',
    'rectifying': 'error',
    'pending_acceptance': 'success',
    'cancelled': 'success'
  };
  return map[status] || 'default';
};

// 进度状态映射
const getProgressStatus = (progress) => {
  if (progress === 0) return 'exception';
  if (progress < 100) return 'processing';
  return 'success';
};

// 时间线映射
const getTimelineStageName = (stage) => {
  const map = {
    'report': '上报登记',
    'dispatch': '派单分配',
    'verify': '现场核查',
    'rectify': '整改实施',
    'acceptance': '验收确认',
    'cancel': '销号归档'
  };
  return map[stage] || '未知环节';
};

const getTimelineColor = (stage) => {
  const map = {
    'report': '#909399',
    'dispatch': '#409EFF',
    'verify': '#722ED1',
    'rectify': '#E6A23C',
    'acceptance': '#13ce66',
    'cancel': '#67C23A'
  };
  return map[stage] || '#909399';
};
// ---------------------- 映射逻辑结束 ----------------------

// 筛选后的隐患列表
const filteredEventList = computed(() => {
  return eventList.value.filter(item => {
    const matchesType = !filterType.value || item.hazard_type === filterType.value;
    const matchesStage = !filterStage.value || item.handle_status === filterStage.value;
    const matchesPriority = !filterPriority.value || item.warning_level === filterPriority.value;
    const matchesKeyword = !searchKeyword.value ||
      item.hazard_name.includes(searchKeyword.value) ||
      item.hazard_id.includes(searchKeyword.value);
    return matchesType && matchesStage && matchesPriority && matchesKeyword;
  }).slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
});

// 判断预计完成时间是否逾期
const isCompleteOverdue = (row) => {
  if (!row.expected_complete_time || row.handle_status === 'cancelled') return false;
  const expectedTime = new Date(row.expected_complete_time);
  const now = new Date();
  return now > expectedTime;
};

// 详情页判断逾期
const isDetailCompleteOverdue = () => {
  if (!currentHazard.value?.expected_complete_time || currentHazard.value.handle_status === 'cancelled') return false;
  const expectedTime = new Date(currentHazard.value.expected_complete_time);
  const now = new Date();
  return now > expectedTime;
};

// 加载隐患预警列表
const fetchHazardWarnings = async () => {
  try {
    const params = {
      type: filterType.value,
      status: filterStage.value,
      level: filterPriority.value,
      keyword: searchKeyword.value,
      page: currentPage.value,
      size: pageSize.value
    };
    const data = await fetchHazardWarningList(params);
    eventList.value = data;
  } catch (error) {
    ElMessage.error('加载隐患预警列表失败：' + error.message);
  }
};

// 显示隐患详情
const showHazardDetail = async (event) => {
  currentHazard.value = { ...event };
  progressForm.value.hazard_id = event.hazard_id;
  progressForm.value.status = event.handle_status;
  progressForm.value.progress = event.progress;
  // 加载处理时间线
  await fetchHazardTimelineData(event.hazard_id);
  hazardDetailVisible.value = true;
};

// 加载隐患处理时间线
const fetchHazardTimelineData = async (hazardId) => {
  try {
    const data = await fetchHazardHandleTimeline(hazardId);
    hazardTimeline.value = data;
  } catch (error) {
    ElMessage.error('加载隐患处理时间线失败：' + error.message);
  }
};

// 刷新隐患详情
const refreshHazardDetail = async () => {
  if (!currentHazard.value?.hazard_id) return;
  try {
    await fetchHazardTimelineData(currentHazard.value.hazard_id);
    // 刷新隐患基本信息
    const hazards = await fetchHazardWarningList({ hazard_id: currentHazard.value.hazard_id });
    if (hazards.length > 0) {
      currentHazard.value = { ...hazards[0] };
      progressForm.value.status = currentHazard.value.handle_status;
      progressForm.value.progress = currentHazard.value.progress;
    }
    ElMessage.success('详情刷新成功！');
  } catch (error) {
    ElMessage.error('刷新详情失败：' + error.message);
  }
};

// 保存隐患处理进度更新
const saveHazardProgress = async () => {
  if (!progressForm.value.status || !progressForm.value.content || progressForm.value.progress === null) {
    ElMessage.warning('请完善处理状态、进度和记录内容');
    return;
  }
  try {
    // 更新隐患处理进度
    const progressRes = await updateHazardHandleProgress(
      progressForm.value.hazard_id,
      progressForm.value.status,
      progressForm.value.progress
    );
    if (progressRes.success) {
      // 提交处理记录
      const recordParams = {
        hazard_id: progressForm.value.hazard_id,
        status: progressForm.value.status,
        progress: progressForm.value.progress,
        content: progressForm.value.content,
        operator: '当前用户', // 实际项目取登录用户
        dept: currentHazard.value.responsible_dept
      };
      const recordRes = await submitHazardHandleRecord(recordParams);
      if (recordRes.success) {
        // 更新当前隐患数据
        currentHazard.value.handle_status = progressForm.value.status;
        currentHazard.value.progress = progressForm.value.progress;
        // 添加时间线记录
        hazardTimeline.value.push({
          stage: progressForm.value.status === 'cancelled' ? 'cancel' :
            progressForm.value.status === 'rectifying' ? 'rectify' :
              progressForm.value.status === 'verified' ? 'verify' :
                progressForm.value.status === 'dispatched' ? 'dispatch' :
                  progressForm.value.status === 'pending_acceptance' ? 'acceptance' : 'report',
          time: progressRes.update_time,
          operator: '当前用户',
          content: progressForm.value.content,
          duration: '实时更新',
          attachments: []
        });
        // 刷新列表数据
        fetchHazardWarnings();
        ElMessage.success('隐患处理进度更新成功！');
        resetProgressForm();
      }
    }
  } catch (error) {
    ElMessage.error('更新隐患处理进度失败：' + error.message);
  }
};

// 重置进度表单
const resetProgressForm = () => {
  progressForm.value.content = '';
};

// 快速更新进度弹窗
const quickUpdateProgress = (row) => {
  quickProgressForm.value.hazard_id = row.hazard_id;
  quickProgressForm.value.status = row.handle_status;
  quickProgressForm.value.progress = row.progress;
  quickProgressVisible.value = true;
};

// 保存快速进度更新
const saveQuickProgress = async () => {
  if (!quickProgressForm.value.status || quickProgressForm.value.progress === null) {
    ElMessage.warning('请完善处理状态和进度');
    return;
  }
  try {
    const res = await updateHazardHandleProgress(
      quickProgressForm.value.hazard_id,
      quickProgressForm.value.status,
      quickProgressForm.value.progress
    );
    if (res.success) {
      // 更新列表数据
      fetchHazardWarnings();
      // 关闭弹窗
      quickProgressVisible.value = false;
      ElMessage.success('隐患处理进度快速更新成功！');
    }
  } catch (error) {
    ElMessage.error('快速更新进度失败：' + error.message);
  }
};

// 批量提醒逾期隐患
const batchRemindOverdue = () => {
  const overdueHazards = eventList.value.filter(item => isCompleteOverdue(item));
  if (overdueHazards.length === 0) {
    ElMessage.info('暂无逾期未处理隐患');
    return;
  }
  ElMessage.success(`已向${overdueHazards.length}个逾期隐患的负责人发送提醒！`);
};

// 分页相关方法
const handleSizeChange = (val) => {
  pageSize.value = val;
  currentPage.value = 1;
  fetchHazardWarnings();
};

const handleCurrentChange = (val) => {
  currentPage.value = val;
  fetchHazardWarnings();
};

// 导出隐患数据
const exportHazardData = () => {
  ElMessage.success('事故隐患预警数据导出成功！');
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/emergencysafety/dpzl');
};

// 刷新所有数据
const refreshData = async () => {
  try {
    ElMessage.info('正在刷新数据...');
    const [overviewData, levelDataRes, durationData, trendData] = await Promise.all([
      fetchHazardWarningOverview(),
      fetchHazardLevelDistribution(),
      fetchHazardHandleDurationAnalysis(),
      fetchHazardWarningTrend7Days()
    ]);

    // 更新核心指标
    totalHazards.value = overviewData.totalHazards;
    pendingHandleHazards.value = overviewData.pendingHandleHazards;
    handlingHazards.value = overviewData.handlingHazards;
    handledHazards.value = overviewData.handledHazards;
    overdueHandleHazards.value = overviewData.overdueHandleHazards;
    avgHandleDuration.value = overviewData.avgHandleDuration;
    avgStageDuration.value = overviewData.avgStageDuration;
    hazardHandleRate.value = overviewData.hazardHandleRate;
    timelyHandleRate.value = overviewData.timelyHandleRate;
    majorHazardEvents.value = overviewData.majorHazardEvents;
    lastSyncTime.value = overviewData.lastSyncTime;

    // 更新图表数据
    stageLabels.value = levelDataRes.labels;
    stageData.value = levelDataRes.data;
    stageColors.value = levelDataRes.colors;
    durationXAxis.value = durationData.xAxis;
    durationSeries.value = durationData.series;
    trendXAxis.value = trendData.xAxis;
    trendSeries.value = trendData.series;

    await fetchHazardWarnings();

    ElMessage.success('事故隐患预警数据刷新成功！');
  } catch (error) {
    ElMessage.error('数据刷新失败：' + error.message);
    console.error('刷新失败详情：', error);
  }
};

// 初始化数据
const initData = async () => {
  try {
    const [overviewData, levelDataRes, durationData, trendData] = await Promise.all([
      fetchHazardWarningOverview(),
      fetchHazardLevelDistribution(),
      fetchHazardHandleDurationAnalysis(),
      fetchHazardWarningTrend7Days()
    ]);

    // 赋值核心指标
    totalHazards.value = overviewData.totalHazards;
    pendingHandleHazards.value = overviewData.pendingHandleHazards;
    handlingHazards.value = overviewData.handlingHazards;
    handledHazards.value = overviewData.handledHazards;
    overdueHandleHazards.value = overviewData.overdueHandleHazards;
    avgHandleDuration.value = overviewData.avgHandleDuration;
    avgStageDuration.value = overviewData.avgStageDuration;
    hazardHandleRate.value = overviewData.hazardHandleRate;
    timelyHandleRate.value = overviewData.timelyHandleRate;
    majorHazardEvents.value = overviewData.majorHazardEvents;
    lastSyncTime.value = overviewData.lastSyncTime;

    // 赋值图表数据
    stageLabels.value = levelDataRes.labels;
    stageData.value = levelDataRes.data;
    stageColors.value = levelDataRes.colors;
    durationXAxis.value = durationData.xAxis;
    durationSeries.value = durationData.series;
    trendXAxis.value = trendData.xAxis;
    trendSeries.value = trendData.series;

    await fetchHazardWarnings();

    console.log('事故隐患预警数据初始化完成');
  } catch (error) {
    ElMessage.error('初始化数据失败：' + error.message);
    console.error('初始化失败详情：', error);
  }
};

// 组件挂载时初始化数据
onMounted(async () => {
  await initData();
});

// 监听筛选条件变化，自动刷新列表
watch([filterType, filterStage, filterPriority, searchKeyword], () => {
  currentPage.value = 1;
  fetchHazardWarnings();
});
</script>

<style lang="scss" scoped>
$primary-color: #ff4d4f; // 核心警示红
$secondary-color: #13ce66; // 安全绿
$warning-color: #faad14; // 警告黄
$danger-color: #ff3838; // 危险红
$info-color: #409eff; // 信息蓝
$text-primary: #b3b3b3; // 主文本色
$text-secondary: #8c8c8c; // 次要文本色
$text-tertiary: #5d5b5b; // tertiary文本色
$bg-main: #0a0f28; // 主背景（深黑蓝）
$bg-card: rgba(255, 106, 0, 0.2);
$bg-panel: rgba(255, 106, 0, 0.1);
$border-color: rgba(255, 77, 79, 0.4); // 边框红
$border-light: rgba(255, 77, 79, 0.2); // 浅色边框红
$shadow: 0 0 1vw rgba(255, 77, 79, 0.2); // 红色阴影
$hover-shadow: 0 0 1.5vw rgba(255, 77, 79, 0.3); // 悬停阴影
$replace-white: #d9d9d9;

// 基础布局样式
.page-container {
  width: 100%;
  height: 100vh;
  overflow: auto;
  background: url("@/assets/chart/images/bg.jpg") center/cover no-repeat;
  color: $text-primary;
  padding: 0 1.5vw;
  box-sizing: border-box;
  touch-action: pan-x pan-y;
}

.panel-fullscreen-btn {
  background: transparent;
  border: none;
  cursor: pointer;
  padding: 4px;
  border-radius: 4px;
  transition: all 0.2s ease;

  &:hover {
    background: rgba(255, 77, 79, 0.2);
  }
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.2vh 0;
  border-bottom: 0.2vh solid $border-color;
  margin-bottom: 1.2vw;
  position: relative;

  &::after {
    content: '';
    position: absolute;
    bottom: -0.3vh;
    left: 0;
    width: 16vw;
    height: 0.3vh;
    background: $primary-color;
  }

  h1 {
    margin: 0;
    font-size: 1.6vw;
    color: #00f2fe;
    font-weight: 600;
    letter-spacing: 0.1vw;

    .title-icon {
      width: 1.8vw;
      height: 1.8vw;
      object-fit: contain;
      vertical-align: middle;
    }
  }

  .header-actions {
    display: flex;
    gap: 0.8vw;
    align-items: center;
  }

  .el-button {
    --el-button-text-color: #ffc1e9;
    --el-button-bg-color: rgba(255, 77, 79, 0.2);
    --el-button-border-color: #ffc1e9;
    --el-button-hover-bg-color: rgba(255, 77, 79, 0.4);
    --el-button-hover-border-color: #ffc1e9;
    border-radius: 4px;
    font-size: 0.85vw;
    padding: 0.6vh 1.2vw;
  }

  .el-button--primary {
    --el-button-text-color: $replace-white;
    --el-button-bg-color: $primary-color;
    --el-button-border-color: $primary-color;
    --el-button-hover-bg-color: $danger-color;
  }
}

.main-content {
  display: flex;
  flex-direction: column;
  gap: 1.2vw;
}

.filter-item {
  --el-select-text-color: #ffc1e9;
  --el-select-placeholder-color: $text-tertiary;
  --el-select-dropdown-bg-color: $bg-card;
  --el-select-dropdown-border-color: $border-color;
  --el-select-dropdown-item-hover-bg-color: rgba(255, 77, 79, 0.2);
  --el-select-dropdown-item-text-color: #ffc1e9;
  --el-input-bg-color: rgba(16, 32, 64, 0.7);
  --el-input-border-color: $border-color;
  --el-input-text-color: #ffc1e9;
  --el-input-placeholder-color: $text-tertiary;
  font-size: 0.75vw;
  width: 10vw;
}

.search-input {
  width: 16vw !important;
}

.search-icon {
  color: #ffc1e9;
}

// 核心指标卡片样式
.stats-cards {
  display: flex;
  gap: 1.2vw;
  flex-wrap: wrap;
}

.stat-card {
  flex: 1;
  min-width: 200px;
  background: $bg-card;
  border-radius: 8px;
  padding: 1.2vw;
  border: 1px solid $border-color;
  box-shadow: $shadow;
  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-3px);
    box-shadow: $hover-shadow;
    border-color: #ff7373;
  }

  .stat-title {
    font-size: 1.2vw !important;
    color: #a7f3d0;
    margin-bottom: 0.8vw;
    display: flex;
    align-items: center;

    &::before {
      content: '';
      display: inline-block;
      width: 0.7vw;
      height: 1.4vh;
      background: $primary-color;
      margin-right: 0.8vw;
      border-radius: 50%;
    }
  }

  .stat-value {
    font-size: 1.6vw !important;
    font-weight: 700;
    margin-bottom: 1vh;
    color: $secondary-color;
    line-height: 1.2;

    .unit {
      font-size: 1.1vw !important;
      margin-left: 0.4vw;
      color: $text-tertiary;
    }

    &.excellent {
      color: $secondary-color;
      text-shadow: 0 0 8px rgba(19, 206, 102, 0.4);
    }

    &.good {
      color: $warning-color;
      text-shadow: 0 0 8px rgba(250, 173, 20, 0.4);
    }

    &.poor {
      color: $danger-color;
      text-shadow: 0 0 8px rgba(255, 56, 56, 0.4);
    }

    &.normal {
      color: #8cd5ff;
      text-shadow: 0 0 8px rgba(0, 204, 255, 0.4);
    }
  }

  .stat-desc {
    font-size: 0.8vw !important;
    color: #ccc;
    line-height: 1.4;
    display: flex;
    gap: 1vw;
    flex-wrap: wrap;
  }

  .level-item {
    padding: 0.2vw 0.4vw;
    border-radius: 4px;
    font-size: 0.8vw !important;
  }

  .level-item.excellent {
    color: $secondary-color;
    background: rgba(19, 206, 102, 0.2);
  }

  .level-item.good {
    color: $warning-color;
    background: rgba(250, 173, 20, 0.2);
  }

  .level-item.average {
    color: $info-color;
    background: rgba(64, 158, 255, 0.2);
  }

  .level-item.poor {
    color: $danger-color;
    background: rgba(255, 56, 56, 0.2);
  }

  .update-time {
    color: #a7f3d0;
  }
}

// 面板样式
.panel {
  position: relative;
  border: 1px solid $border-color;
  background: $bg-panel;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  min-height: 0;
  box-shadow: $shadow;
  overflow: hidden;

  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 3px;
    background: linear-gradient(90deg, $primary-color, rgba(255, 77, 79, 0.4));
  }

  .panel-header {
    padding: 1vh 1.2vw;
    border-bottom: 1px solid $border-light;
    display: flex;
    justify-content: space-between;
    align-items: center;

    h2 {
      margin: 0;
      font-size: 1.1vw;
      color: #ffc1e9;
      font-weight: 600;
    }
  }

  .panel-body {
    flex: 1;
    padding: 1.2vw;
    overflow: hidden;
  }

  .panel-footer {
    height: 1vh;
    border-top: 1px solid $border-light;
  }
}

// 图表行样式
.chart-row {
  display: flex;
  gap: 1.2vw;
  height: 30vh;
  margin-bottom: 1.2vw;

  .stage-panel {
    flex: 1;
  }

  .duration-panel {
    flex: 2;
  }

  .trend-panel {
    flex: 2;
  }
}

// 表格面板样式
.data-table-panel {
  max-height: 40vh;

  .panel-header {
    .header-actions {
      display: flex;
      gap: 0.8vw;
      align-items: center;
    }

    .el-button--success {
      --el-button-text-color: $replace-white;
      --el-button-bg-color: $secondary-color;
      --el-button-border-color: $secondary-color;
    }
  }

  .table-scroll {
    overflow-y: auto;

    &::-webkit-scrollbar {
      width: 8px;
      height: 8px;
    }

    &::-webkit-scrollbar-track {
      background: rgba(16, 32, 64, 0.5);
      border-radius: 4px;
    }

    &::-webkit-scrollbar-thumb {
      background: $primary-color;
      border-radius: 4px;
      border: 1px solid rgba(255, 255, 255, 0.1);
    }

    &::-webkit-scrollbar-thumb:hover {
      background: $danger-color;
    }
  }

  .pagination-container {
    display: flex;
    justify-content: flex-end;
    padding: 0.8vw 1.2vw;
    border-top: 1px solid $border-light;

    .el-pagination {
      --el-pagination-color: #ffc1e9;
      --el-pagination-hover-color: $primary-color;
      --el-pagination-active-color: $replace-white;
      --el-pagination-active-bg-color: $primary-color;
      font-size: 0.75vw;
    }
  }
}

// 核心表格样式
::v-deep .el-table {
  --el-table-bg-color: transparent !important;
  --el-table-text-color: $text-primary !important;
  --el-table-border-color: $border-light !important;
  --el-table-header-text-color: #ffc1e9 !important;
  --el-table-header-bg-color: rgba(18, 28, 66, 0.9) !important;
  --el-table-row-hover-bg-color: rgba(255, 77, 79, 0.15) !important;
  --el-table-row-stripes-bg-color: rgba(18, 28, 66, 0.5) !important;
  --el-table-cell-hover-bg-color: transparent !important;
  --el-table-selected-row-bg-color: rgba(255, 77, 79, 0.2) !important;
  border-radius: 8px;
  overflow: hidden;
  border: 1px solid $border-color !important;
  background-color: transparent !important;

  .el-table__header {
    border-bottom: 1px solid $border-color !important;
    background-color: transparent !important;

    th {
      font-weight: 600;
      font-size: 0.85vw;
      padding: 1.2vh 0 !important;
      background: linear-gradient(to bottom, rgba(18, 28, 66, 0.95), rgba(18, 28, 66, 0.8)) !important;
      border-right: 1px solid $border-light !important;
      color: #ffc1e9 !important;
      text-align: center !important;
      &:last-child {
        border-right: none !important;
      }
    }
  }

  .el-table__body {
    background-color: transparent !important;

    tr {
      background-color: transparent !important;

      td {
        background-color: rgba(18, 28, 66, 0.3) !important;
        text-align: center !important;
      }

      &.current-row {
        td {
          background-color: rgba(255, 77, 79, 0.2) !important;
          color: $replace-white !important;
        }
      }
    }
  }

  .el-table__cell {
    border-bottom: 1px solid $border-light !important;
    border-right: 1px solid $border-light !important;
    padding: 1.2vh 0.8vw !important;
    font-size: 0.8vw;
    transition: all 0.2s;
    background-color: transparent !important;
    color: $text-primary !important;
    text-align: center !important;

    &:last-child {
      border-right: none !important;
    }
  }

  // 状态标签样式
  ::v-deep .el-tag {
    font-size: 0.75vw;
    padding: 0.3vh 0.6vw;
    border-radius: 12px;
    font-weight: 500;
    background-color: transparent !important;

    &.el-tag--danger {
      border-color: $danger-color !important;
      color: $danger-color !important;
    }

    &.el-tag--warning {
      border-color: $warning-color !important;
      color: $warning-color !important;
    }

    &.el-tag--success {
      border-color: $secondary-color !important;
      color: $secondary-color !important;
    }

    &.el-tag--primary {
      border-color: $info-color !important;
      color: $info-color !important;
    }

    &.el-tag--default {
      border-color: $text-tertiary !important;
      color: $text-tertiary !important;
    }
  }

  // 表格行hover效果
  &.el-table--enable-row-hover .el-table__body tr:hover > td {
    background-color: rgba(255, 77, 79, 0.15) !important;
    color: #666;
  }

  // 清除表格默认内边距和背景
  .el-table__inner-wrapper {
    background-color: transparent !important;
  }

  .el-table__fixed {
    background-color: transparent !important;

    .el-table__fixed-body-wrapper {
      background-color: transparent !important;
    }
  }
}

// 进度条容器样式
.progress-container {
  display: flex;
  align-items: center;
  gap: 0.8vw;
  padding: 0 0.4vw;

  .progress-text {
    font-size: 0.75vw;
    color: #ffc1e9;
    width: 40px;
    text-align: right;
    font-weight: 500;
  }

  ::v-deep .el-progress {
    --el-progress-stroke-color: $primary-color;
    --el-progress-background-color: rgba(255, 255, 255, 0.1);

    &.el-progress--status-success {
      --el-progress-stroke-color: $secondary-color;
    }

    &.el-progress--status-exception {
      --el-progress-stroke-color: $danger-color;
    }
  }
}

// 详情弹窗样式
.hazard-detail {
  .detail-section {
    margin-bottom: 20px;

    h3 {
      font-size: 18px;
      color: #ff5555;
      margin-bottom: 12px;
      padding-bottom: 6px;
      border-bottom: 1px solid $border-color;
      font-weight: 600;
      display: flex;
      align-items: center;

      &::before {
        content: '';
        display: inline-block;
        width: 4px;
        height: 16px;
        background: $primary-color;
        margin-right: 8px;
        border-radius: 2px;
      }
    }
  }

  .content-card {
    background: rgba(211, 226, 241, 0.8);
    border: 1px solid $border-light;
    border-radius: 8px;
    padding: 12px;
    color: $text-secondary;
    line-height: 1.6;
    font-size: 0.9vw;
    min-height: 60px;
    white-space: pre-line;
  }

  .el-descriptions {
    --el-descriptions-bg-color: transparent;
    --el-descriptions-border-color: $border-light;
    --el-descriptions-title-color: $text-secondary;
    --el-descriptions-content-color: $text-secondary;
    font-size: 0.9vw;

    .el-descriptions__label {
      color: #ffc1e9;
      font-weight: 500;
      width: 110px !important;
      text-align: left !important;
    }

    .el-descriptions__content {
      color: $text-secondary;
      padding: 10px 0;
    }

    .el-descriptions__item {
      border-bottom: 1px solid $border-light;

      &:last-child {
        border-bottom: none;
      }
    }
  }

  // 时间线样式
  .el-timeline {
    padding-left: 16px;

    .el-timeline-item {
      margin-bottom: 16px;

      &:last-child {
        margin-bottom: 0;
      }

      .el-timeline-item__node {
        background-color: $bg-card;
        border-color: $primary-color;
      }

      .el-timeline-item__timestamp {
        color: #ffc1e9;
        font-size: 0.8vw;
        margin-bottom: 4px;
        font-weight: 500;
      }
    }
  }

  .timeline-content {
    background: rgba(211, 226, 241, 0.7);
    border: 1px solid $border-light;
    border-radius: 8px;
    padding: 12px;

    .timeline-stage {
      font-size: 0.9vw;
      color: #008186;
      margin: 0 0 4px 0;
    }

    .timeline-operator {
      font-size: 0.8vw;
      color: #005d32;
      margin: 0 0 4px 0;
    }

    .timeline-desc {
      font-size: 0.85vw;
      color: $text-secondary;
      margin: 0;
    }

    .timeline-duration {
      font-size: 0.8vw;
      color: #ff5555;
      margin: 4px 0 0 0;
      font-weight: 500;
    }
  }

  // 进度表单样式
  .progress-form-card {
    background: rgba(211, 226, 241, 0.7);
    border: 1px solid $border-light;
    margin-bottom: 16px;

    .form-title {
      font-size: 0.95vw;
      color: #ff5555;
      margin: 0 0 12px 0;
      padding-bottom: 8px;
      border-bottom: 1px solid $border-light;
    }
  }
}

// 弹窗样式
::v-deep .el-dialog {
  --el-dialog-bg-color: white;
  --el-dialog-border-color: $border-color;
  --el-dialog-title-color: #ffc1e9;
  --el-dialog-text-color: $text-secondary;
  border-radius: 8px;
  box-shadow: 0 0 2vw rgba(255, 77, 79, 0.2);

  .el-dialog__header {
    border-bottom: 1px solid $border-light;
    padding: 16px 20px;
  }

  .el-dialog__title {
    font-size: 1.1vw;
    font-weight: 600;
  }

  .el-dialog__body {
    padding: 20px;
    max-height: 60vh;
    overflow-y: auto;
    background-color: transparent !important;

    &::-webkit-scrollbar {
      width: 6px;
    }

    &::-webkit-scrollbar-track {
      background: rgba(16, 32, 64, 0.5);
      border-radius: 3px;
    }

    &::-webkit-scrollbar-thumb {
      background: $primary-color;
      border-radius: 3px;
    }
  }

  .el-dialog__footer {
    border-top: 1px solid $border-light;
    padding: 16px 20px;

    .el-button {
      --el-button-text-color: $replace-white;
    }
  }
}

// 表单样式
::v-deep .el-form {
  --el-form-label-color: #ffc1e9;
  --el-form-input-text-color: $text-secondary;
  --el-form-input-bg-color: rgba(18, 28, 66, 0.7);
  --el-form-input-border-color: $border-color;
  --el-form-input-focus-border-color: $primary-color;

  .el-form-item {
    margin-bottom: 16px;

    .el-form-item__label {
      font-size: 0.85vw;
      width: 100px !important;
    }

    .el-input, .el-select, .el-input-number {
      --el-input-text-color: $text-secondary;
      --el-input-bg-color: rgba(18, 28, 66, 0.7);
      --el-input-border-color: $border-color;
      font-size: 0.85vw;
    }

    .el-textarea {
      min-height: 80px;
      resize: vertical;
    }
  }
}

// 超时文本样式强化（无白色）
.text-red-500 {
  color: $danger-color !important;
  font-weight: 600 !important;
  text-shadow: 0 0 3px rgba(255, 56, 56, 0.3);
}

// 快速更新进度弹窗样式
::v-deep .el-input-number {
  --el-input-number-text-color: $text-secondary;
  --el-input-number-bg-color: rgba(18, 28, 66, 0.7);
  --el-input-number-border-color: $border-color;
  width: 100%;
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
