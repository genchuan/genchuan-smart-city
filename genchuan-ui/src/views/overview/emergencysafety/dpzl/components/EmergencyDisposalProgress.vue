<template>
  <div class="page-container">
    <!-- 页头区域：标题及核心操作 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/disposal.png"
          alt="应急处置图标"
          class="title-icon"
        />
        应急处置进度视图
      </h1>
      <div class="header-actions">
        <el-button size="small" @click="refreshData">刷新</el-button>
        <el-button size="small" @click="handleBack">返回总览</el-button>
      </div>
    </div>

    <!-- 主内容区域 -->
    <div class="main-content">
      <!-- 核心进度指标卡片组 -->
      <div class="stats-cards">
        <div class="stat-card">
          <div class="stat-title">应急事件总数</div>
          <div class="stat-value normal">
            {{ totalEvtCount }} <span class="unit">件</span>
          </div>
          <div class="stat-desc">
            高优先级事件: <span class="level-item poor">{{ highPriorityEvents }}件</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">处置状态分布</div>
          <div class="stat-value" style="font-size: 1.1vw !important; line-height: 1.4;">
            <span class="level-item poor">待处置: {{ pendingDisposalEvents }}件</span>
            <span class="level-item good">处置中: {{ disposingEvents }}件</span>
          </div>
          <div class="stat-desc">
            已办结事件数: <span class="level-item excellent">{{ completedCount }}件</span>
            已超时事件数: <span class="level-item poor">{{ overtimeDisposalEvents }}件</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">核心处置效率</div>
          <div class="stat-value" style="font-size: 1.1vw !important; line-height: 1.4;">
            <span class="level-item excellent">完成率: {{ disposalCompletionRate }}%</span>
            <span class="level-item good">及时率: {{ timelyDisposalRate }}%</span>
          </div>
          <div class="stat-desc">
            平均处置时长: <span class="level-item average">{{ avgDisposalDuration }}分钟</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">各阶段平均耗时</div>
          <div class="stat-value" style="font-size: 1vw !important; line-height: 1.4;">
            <span class="level-item poor">到场: {{ avgStageDuration.arrive }}分钟</span>
            <span class="level-item poor">处置: {{ avgStageDuration.handle }}分钟</span>
          </div>
          <div class="stat-desc">
            最近同步: <span class="update-time">{{ lastSyncTime }}</span>
          </div>
        </div>
      </div>

      <div class="chart-row">
        <div class="panel duration-panel" ref="durationPanel">
          <div class="panel-header">
            <h2>各阶段耗时分析</h2>
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
        <div class="panel trend-panel" ref="trendPanel">
          <div class="panel-header">
            <h2>近7天处置进度趋势</h2>
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
        <div class="panel stage-panel" ref="stagePanel">
          <div class="panel-header">
            <h2>处置阶段分布</h2>
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
      </div>
    </div>

    <!-- 处置事件表格（核心：带进度+阶段+耗时） -->
    <div class="panel data-table-panel" ref="dataTablePanel">
      <div class="panel-header">
        <h2>应急处置事件列表</h2>
        <div class="header-actions">
          <el-select v-model="filterType" placeholder="事件类型" size="small" class="filter-item">
            <el-option label="全部" value="" />
            <el-option label="火灾事故" value="fire_accident" />
            <el-option label="交通事故" value="traffic_accident" />
            <el-option label="燃气泄漏" value="gas_leak" />
            <el-option label="建筑坍塌" value="building_collapse" />
            <el-option label="城市内涝" value="waterlogging" />
            <el-option label="设备故障" value="equipment_failure" />
            <el-option label="其他应急事件" value="other_emergency" />
          </el-select>
          <el-select v-model="filterStage" placeholder="处置阶段" size="small" class="filter-item">
            <el-option label="全部" value="" />
            <el-option label="待接警" value="pending_alarm" />
            <el-option label="已派单" value="dispatched" />
            <el-option label="已到场" value="arrived" />
            <el-option label="处置中" value="disposing" />
            <el-option label="待验收" value="pending_acceptance" />
            <el-option label="已结案" value="closed" />
          </el-select>
          <el-select v-model="filterPriority" placeholder="优先级" size="small" class="filter-item">
            <el-option label="全部" value="" />
            <el-option label="低优先级" value="low" />
            <el-option label="中优先级" value="medium" />
            <el-option label="高优先级" value="high" />
          </el-select>
          <el-input
            v-model="searchKeyword"
            placeholder="搜索事件名称/ID"
            size="small"
            class="filter-item search-input"
            @keyup.enter="fetchDisposalEvents"
          >
            <template #append>
              <el-icon class="search-icon">
                <Search />
              </el-icon>
            </template>
          </el-input>
          <el-button size="small" type="danger" @click="batchRemindOvertime">
            <WarningFilled size="14" /> 超时处置提醒
          </el-button>
          <el-button size="small" type="primary" @click="exportDisposalData">导出数据</el-button>
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
          @row-click="showDisposalDetail"
        >
          <el-table-column prop="disposal_id" label="处置ID" width="120" />
          <el-table-column prop="event_name" label="事件名称" min-width="200" />
          <el-table-column prop="event_type" label="事件类型">
            <template #default="scope">
              <el-tag :type="getEventTypeTagType(scope.row.event_type)">
                {{ getEventTypeName(scope.row.event_type) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="priority" label="优先级">
            <template #default="scope">
              <el-tag :type="getPriorityTagType(scope.row.priority)">
                {{ getPriorityName(scope.row.priority) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="stage" label="处置阶段">
            <template #default="scope">
              <el-tag :type="getStageTagType(scope.row.stage)">
                {{ getStageName(scope.row.stage) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="处置进度">
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
              <el-button size="small" link @click.stop="showDisposalDetail(scope.row)">详情</el-button>
              <el-button
                v-if="scope.row.stage !== 'closed'"
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

  <!-- 处置详情弹窗（核心：基础信息+时间线+进度更新） -->
  <el-dialog
    v-model="disposalDetailVisible"
    :title="currentDisposal?.event_name || '应急处置进度详情'"
    width="50%"
    :close-on-click-modal="false"
  >
    <div class="disposal-detail">
      <!-- 基础信息区域 -->
      <div class="detail-section">
        <h3>基础信息</h3>
        <el-descriptions column="2" border>
          <el-descriptions-item label="处置ID">{{ currentDisposal?.disposal_id }}</el-descriptions-item>
          <el-descriptions-item label="事件类型">{{ getEventTypeName(currentDisposal?.event_type) }}</el-descriptions-item>
          <el-descriptions-item label="优先级">{{ getPriorityName(currentDisposal?.priority) }}</el-descriptions-item>
          <el-descriptions-item label="所属区域">{{ currentDisposal?.area }}</el-descriptions-item>
          <el-descriptions-item label="接警时间">{{ currentDisposal?.alarm_time || '未接警' }}</el-descriptions-item>
          <el-descriptions-item label="派单时间">{{ currentDisposal?.dispatch_time || '未派单' }}</el-descriptions-item>
          <el-descriptions-item label="处置阶段">{{ getStageName(currentDisposal?.stage) }}</el-descriptions-item>
          <el-descriptions-item label="处置进度">
            <el-progress
              :percentage="currentDisposal?.progress || 0"
              :status="getProgressStatus(currentDisposal?.progress || 0)"
              size="small"
              :stroke-width="8"
              style="width: 100px; display: inline-block;"
              :show-text="false"
            />
            <span class="ml-2">{{ currentDisposal?.progress || 0 }}%</span>
          </el-descriptions-item>
          <el-descriptions-item label="预计完成时间">
              <span :class="isDetailCompleteOverdue() ? 'text-red-500 font-bold' : ''">
                {{ currentDisposal?.expected_complete_time }}
              </span>
          </el-descriptions-item>
          <el-descriptions-item label="处置团队">{{ currentDisposal?.disposal_team }}</el-descriptions-item>
          <el-descriptions-item label="负责人">{{ currentDisposal?.leader }}</el-descriptions-item>
          <el-descriptions-item label="实际完成时间">{{ currentDisposal?.actual_complete_time || '处置中' }}</el-descriptions-item>
        </el-descriptions>
      </div>

      <!-- 事件描述区域 -->
      <div class="detail-section">
        <h3>事件描述</h3>
        <div class="content-card">
          {{ currentDisposal?.description || '无详细描述' }}
        </div>
      </div>

      <!-- 处置时间线（核心：各阶段耗时+操作记录） -->
      <div class="detail-section">
        <h3>处置时间线</h3>
        <el-timeline :reverse="false">
          <el-timeline-item
            v-for="(item, index) in disposalTimeline"
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
      <div class="detail-section" v-if="currentDisposal?.stage !== 'closed'">
        <h3>更新处置进度</h3>
        <el-card shadow="hover" class="progress-form-card">
          <el-form :model="progressForm" label-width="100px" size="small">
            <el-form-item label="处置阶段" required>
              <el-select v-model="progressForm.stage" placeholder="选择当前阶段" required>
                <el-option label="待接警" value="pending_alarm"/>
                <el-option label="已派单" value="dispatched"/>
                <el-option label="已到场" value="arrived"/>
                <el-option label="处置中" value="disposing"/>
                <el-option label="待验收" value="pending_acceptance"/>
                <el-option label="已结案" value="closed"/>
              </el-select>
            </el-form-item>
            <el-form-item label="处置记录" required>
              <el-input
                v-model="progressForm.content"
                type="textarea"
                :rows="3"
                placeholder="请输入处置记录内容"
              />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="saveDisposalProgress">提交进度更新</el-button>
              <el-button @click="resetProgressForm">取消</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </div>

    <!-- 弹窗底部操作按钮 -->
    <template #footer>
      <el-button type="primary" @click="refreshDisposalDetail">刷新详情</el-button>
      <el-button @click="disposalDetailVisible = false">关闭</el-button>
    </template>
  </el-dialog>

  <!-- 快速更新进度弹窗 -->
  <el-dialog
    v-model="quickProgressVisible"
    title="快速更新处置进度"
    width="25%"
    :close-on-click-modal="false"
  >
    <el-form :model="quickProgressForm" label-width="100px" size="small">
      <el-form-item label="处置阶段" required>
        <el-select v-model="quickProgressForm.stage" placeholder="选择当前阶段" required>
          <el-option label="待接警" value="pending_alarm"/>
          <el-option label="已派单" value="dispatched"/>
          <el-option label="已到场" value="arrived"/>
          <el-option label="处置中" value="disposing"/>
          <el-option label="待验收" value="pending_acceptance"/>
          <el-option label="已结案" value="closed"/>
        </el-select>
      </el-form-item>
      <el-form-item label="处置进度(%)" required>
        <el-input-number
          v-model="quickProgressForm.progress"
          :min="0"
          :max="100"
          :step="5"
          placeholder="输入处置进度"
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
import { ElMessage, ElTag, ElProgress, ElTimeline, ElTimelineItem, ElForm, ElFormItem, ElInput, ElSelect, ElOption, ElInputNumber, ElCard } from 'element-plus';
import { FullScreen, Search, WarningFilled } from "@element-plus/icons-vue";
import screenFull from 'screenfull';

// 导入应急处置进度核心API
import {
  fetchDisposalProgressOverview,
  fetchDisposalStageDistribution,
  fetchStageDurationAnalysis,
  fetchDisposalTrend7Days,
  fetchDisposalEventList,
  fetchDisposalTimeline,
  updateDisposalProgress,
  submitDisposalRecord
} from '@/api/overview/emergencysafety/GlobalSituationOverview.js';

// 导入图表组件（复用参考代码的图表组件）
import ChartPie from './ChartPie1.vue';
import ChartBar from './ChartBar1.vue';
import ChartLine from './ChartLine1.vue';

const router = useRouter();
const instance = getCurrentInstance();

// 全屏切换功能（复用参考代码逻辑）
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
const filterType = ref('');         // 事件类型筛选
const filterStage = ref('');        // 处置阶段筛选
const filterPriority = ref('');     // 优先级筛选
const searchKeyword = ref('');      // 搜索关键词
const currentPage = ref(1);         // 当前页码
const pageSize = ref(10);           // 每页条数

// 核心进度指标数据
const totalEvtCount = ref(0);
const pendingDisposalEvents = ref(0);
const disposingEvents = ref(0);
const completedCount = ref(0);
const overtimeDisposalEvents = ref(0);
const avgDisposalDuration = ref(0);
const avgStageDuration = ref({
  alarm: 0, dispatch: 0, arrive: 0, handle: 0, acceptance: 0, close: 0
});
const disposalCompletionRate = ref(0);
const timelyDisposalRate = ref(0);
const highPriorityEvents = ref(0);
const lastSyncTime = ref('');

// 图表数据
const stageLabels = ref([]);
const stageData = ref([]);
const stageColors = ref([]);
const durationXAxis = ref([]);
const durationSeries = ref([]);
const trendXAxis = ref([]);
const trendSeries = ref([]);

// 处置事件数据
const eventList = ref([]);
const currentDisposal = ref(null);
const disposalDetailVisible = ref(false);
const disposalTimeline = ref([]);

// 进度更新表单数据
const progressForm = ref({
  disposal_id: '',
  stage: '',
  progress: 0,
  content: ''
});

// 快速更新进度弹窗
const quickProgressVisible = ref(false);
const quickProgressForm = ref({
  disposal_id: '',
  stage: '',
  progress: 0
});

// ---------------------- 映射逻辑 ----------------------
// 事件类型映射
const getEventTypeName = (type) => {
  const map = {
    'fire_accident': '火灾事故',
    'traffic_accident': '交通事故',
    'gas_leak': '燃气泄漏',
    'building_collapse': '建筑坍塌',
    'waterlogging': '城市内涝',
    'equipment_failure': '设备故障',
    'other_emergency': '其他应急事件'
  };
  return map[type] || '未知类型';
};

const getEventTypeTagType = (type) => {
  const map = {
    'fire_accident': 'danger',
    'traffic_accident': 'warning',
    'gas_leak': 'error',
    'building_collapse': 'danger',
    'waterlogging': 'primary',
    'equipment_failure': 'info',
    'other_emergency': 'default'
  };
  return map[type] || 'default';
};

// 优先级映射
const getPriorityName = (priority) => {
  const map = {
    'low': '低优先级',
    'medium': '中优先级',
    'high': '高优先级'
  };
  return map[priority] || '未知优先级';
};

const getPriorityTagType = (priority) => {
  const map = {
    'low': 'success',
    'medium': 'warning',
    'high': 'danger'
  };
  return map[priority] || 'default';
};

// 处置阶段映射
const getStageName = (stage) => {
  const map = {
    'pending_alarm': '待接警',
    'dispatched': '已派单',
    'arrived': '已到场',
    'disposing': '处置中',
    'pending_acceptance': '待验收',
    'closed': '已结案'
  };
  return map[stage] || '未知阶段';
};

const getStageTagType = (stage) => {
  const map = {
    'pending_alarm': 'info',
    'dispatched': 'primary',
    'arrived': 'warning',
    'disposing': 'error',
    'pending_acceptance': 'success',
    'closed': 'success'
  };
  return map[stage] || 'default';
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
    'alarm': '接警登记',
    'dispatch': '派单分配',
    'arrive': '现场到场',
    'handle': '处置实施',
    'acceptance': '验收确认',
    'close': '结案归档'
  };
  return map[stage] || '未知阶段';
};

const getTimelineColor = (stage) => {
  const map = {
    'alarm': '#909399',
    'dispatch': '#409EFF',
    'arrive': '#722ED1',
    'handle': '#E6A23C',
    'acceptance': '#13ce66',
    'close': '#67C23A'
  };
  return map[stage] || '#909399';
};
// ---------------------- 映射逻辑结束 ----------------------

// 筛选后的事件列表
const filteredEventList = computed(() => {
  return eventList.value.filter(item => {
    const matchesType = !filterType.value || item.event_type === filterType.value;
    const matchesStage = !filterStage.value || item.stage === filterStage.value;
    const matchesPriority = !filterPriority.value || item.priority === filterPriority.value;
    const matchesKeyword = !searchKeyword.value ||
      item.event_name.includes(searchKeyword.value) ||
      item.disposal_id.includes(searchKeyword.value);
    return matchesType && matchesStage && matchesPriority && matchesKeyword;
  }).slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
});

// 判断预计完成时间是否超时
const isCompleteOverdue = (row) => {
  if (!row.expected_complete_time || row.stage === 'closed') return false;
  const expectedTime = new Date(row.expected_complete_time);
  const now = new Date();
  return now > expectedTime;
};

// 详情页判断超时
const isDetailCompleteOverdue = () => {
  if (!currentDisposal.value?.expected_complete_time || currentDisposal.value.stage === 'closed') return false;
  const expectedTime = new Date(currentDisposal.value.expected_complete_time);
  const now = new Date();
  return now > expectedTime;
};

// 加载处置事件列表
const fetchDisposalEvents = async () => {
  try {
    const params = {
      type: filterType.value,
      stage: filterStage.value,
      priority: filterPriority.value,
      keyword: searchKeyword.value,
      page: currentPage.value,
      size: pageSize.value
    };
    const data = await fetchDisposalEventList(params);
    eventList.value = data;
  } catch (error) {
    ElMessage.error('加载处置事件列表失败：' + error.message);
  }
};

// 显示处置详情
const showDisposalDetail = async (event) => {
  currentDisposal.value = { ...event };
  progressForm.value.disposal_id = event.disposal_id;
  progressForm.value.stage = event.stage;
  progressForm.value.progress = event.progress;
  // 加载处置时间线
  await fetchDisposalTimelineData(event.disposal_id);
  disposalDetailVisible.value = true;
};

// 加载处置时间线
const fetchDisposalTimelineData = async (disposalId) => {
  try {
    const data = await fetchDisposalTimeline(disposalId);
    disposalTimeline.value = data;
  } catch (error) {
    ElMessage.error('加载处置时间线失败：' + error.message);
  }
};

// 刷新处置详情
const refreshDisposalDetail = async () => {
  if (!currentDisposal.value?.disposal_id) return;
  try {
    await fetchDisposalTimelineData(currentDisposal.value.disposal_id);
    // 刷新事件基本信息
    const events = await fetchDisposalEventList({ disposal_id: currentDisposal.value.disposal_id });
    if (events.length > 0) {
      currentDisposal.value = { ...events[0] };
      progressForm.value.stage = currentDisposal.value.stage;
      progressForm.value.progress = currentDisposal.value.progress;
    }
    ElMessage.success('详情刷新成功！');
  } catch (error) {
    ElMessage.error('刷新详情失败：' + error.message);
  }
};

// 保存处置进度更新
const saveDisposalProgress = async () => {
  if (!progressForm.value.stage || !progressForm.value.content || progressForm.value.progress === null) {
    ElMessage.warning('请完善处置阶段、进度和记录内容');
    return;
  }
  try {
    // 更新处置进度
    const progressRes = await updateDisposalProgress(
      progressForm.value.disposal_id,
      progressForm.value.stage,
      progressForm.value.progress
    );
    if (progressRes.success) {
      // 提交处置记录
      const recordParams = {
        disposal_id: progressForm.value.disposal_id,
        stage: progressForm.value.stage,
        progress: progressForm.value.progress,
        content: progressForm.value.content,
        operator: '当前用户', // 实际项目取登录用户
        team: currentDisposal.value.disposal_team
      };
      const recordRes = await submitDisposalRecord(recordParams);
      if (recordRes.success) {
        // 更新当前处置数据
        currentDisposal.value.stage = progressForm.value.stage;
        currentDisposal.value.progress = progressForm.value.progress;
        // 添加时间线记录
        disposalTimeline.value.push({
          stage: progressForm.value.stage === 'closed' ? 'close' :
            progressForm.value.stage === 'disposing' ? 'handle' :
              progressForm.value.stage === 'arrived' ? 'arrive' :
                progressForm.value.stage === 'dispatched' ? 'dispatch' :
                  progressForm.value.stage === 'pending_acceptance' ? 'acceptance' : 'alarm',
          time: progressRes.update_time,
          operator: '当前用户',
          content: progressForm.value.content,
          duration: '实时更新',
          attachments: []
        });
        // 刷新列表数据
        fetchDisposalEvents();
        ElMessage.success('处置进度更新成功！');
        resetProgressForm();
      }
    }
  } catch (error) {
    ElMessage.error('更新处置进度失败：' + error.message);
  }
};

// 重置进度表单
const resetProgressForm = () => {
  progressForm.value.content = '';
};

// 快速更新进度弹窗
const quickUpdateProgress = (row) => {
  quickProgressForm.value.disposal_id = row.disposal_id;
  quickProgressForm.value.stage = row.stage;
  quickProgressForm.value.progress = row.progress;
  quickProgressVisible.value = true;
};

// 保存快速进度更新
const saveQuickProgress = async () => {
  if (!quickProgressForm.value.stage || quickProgressForm.value.progress === null) {
    ElMessage.warning('请完善处置阶段和进度');
    return;
  }
  try {
    const res = await updateDisposalProgress(
      quickProgressForm.value.disposal_id,
      quickProgressForm.value.stage,
      quickProgressForm.value.progress
    );
    if (res.success) {
      // 更新列表数据
      fetchDisposalEvents();
      // 关闭弹窗
      quickProgressVisible.value = false;
      ElMessage.success('处置进度快速更新成功！');
    }
  } catch (error) {
    ElMessage.error('快速更新进度失败：' + error.message);
  }
};

// 批量提醒超时处置
const batchRemindOvertime = () => {
  const overdueEvents = eventList.value.filter(item => isCompleteOverdue(item));
  if (overdueEvents.length === 0) {
    ElMessage.info('暂无超时处置事件');
    return;
  }
  ElMessage.success(`已向${overdueEvents.length}个超时处置事件的负责人发送提醒！`);
};

// 分页相关方法
const handleSizeChange = (val) => {
  pageSize.value = val;
  currentPage.value = 1;
  fetchDisposalEvents();
};

const handleCurrentChange = (val) => {
  currentPage.value = val;
  fetchDisposalEvents();
};

// 导出处置数据
const exportDisposalData = () => {
  ElMessage.success('应急处置进度数据导出成功！');
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/emergencysafety/dpzl');
};

// 刷新所有数据
const refreshData = async () => {
  try {
    ElMessage.info('正在刷新数据...');
    const [overviewData, stageDataRes, durationData, trendData] = await Promise.all([
      fetchDisposalProgressOverview(),
      fetchDisposalStageDistribution(),
      fetchStageDurationAnalysis(),
      fetchDisposalTrend7Days()
    ]);

    // 更新核心指标
    totalEvtCount.value = overviewData.totalEvtCount;
    pendingDisposalEvents.value = overviewData.pendingDisposalEvents;
    disposingEvents.value = overviewData.disposingEvents;
    completedCount.value = overviewData.completedCount;
    overtimeDisposalEvents.value = overviewData.overtimeDisposalEvents;
    avgDisposalDuration.value = overviewData.avgDisposalDuration;
    avgStageDuration.value = overviewData.avgStageDuration;
    disposalCompletionRate.value = overviewData.disposalCompletionRate;
    timelyDisposalRate.value = overviewData.timelyDisposalRate;
    highPriorityEvents.value = overviewData.highPriorityEvents;
    lastSyncTime.value = overviewData.lastSyncTime;

    // 更新图表数据
    stageLabels.value = stageDataRes.labels;
    stageData.value = stageDataRes.data;
    stageColors.value = stageDataRes.colors;
    durationXAxis.value = durationData.xAxis;
    durationSeries.value = durationData.series;
    trendXAxis.value = trendData.xAxis;
    trendSeries.value = trendData.series;

    await fetchDisposalEvents();

    ElMessage.success('应急处置进度数据刷新成功！');
  } catch (error) {
    ElMessage.error('数据刷新失败：' + error.message);
    console.error('刷新失败详情：', error);
  }
};

// 初始化数据
const initData = async () => {
  try {
    const [overviewData, stageDataRes, durationData, trendData] = await Promise.all([
      fetchDisposalProgressOverview(),
      fetchDisposalStageDistribution(),
      fetchStageDurationAnalysis(),
      fetchDisposalTrend7Days()
    ]);

    // 赋值核心指标
    totalEvtCount.value = overviewData.totalEvtCount;
    pendingDisposalEvents.value = overviewData.pendingDisposalEvents;
    disposingEvents.value = overviewData.disposingEvents;
    completedCount.value = overviewData.completedCount;
    overtimeDisposalEvents.value = overviewData.overtimeDisposalEvents;
    avgDisposalDuration.value = overviewData.avgDisposalDuration;
    avgStageDuration.value = overviewData.avgStageDuration;
    disposalCompletionRate.value = overviewData.disposalCompletionRate;
    timelyDisposalRate.value = overviewData.timelyDisposalRate;
    highPriorityEvents.value = overviewData.highPriorityEvents;
    lastSyncTime.value = overviewData.lastSyncTime;

    // 赋值图表数据
    stageLabels.value = stageDataRes.labels;
    stageData.value = stageDataRes.data;
    stageColors.value = stageDataRes.colors;
    durationXAxis.value = durationData.xAxis;
    durationSeries.value = durationData.series;
    trendXAxis.value = trendData.xAxis;
    trendSeries.value = trendData.series;

    await fetchDisposalEvents();

    console.log('应急处置进度数据初始化完成');
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
  fetchDisposalEvents();
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
$bg-card: rgba(194, 215, 144, 0.2);
$bg-panel: rgba(153, 255, 0, 0.1);
$border-color: rgba(255, 77, 79, 0.4); // 边框红
$border-light: rgba(255, 77, 79, 0.2); // 浅色边框红
$shadow: 0 0 1vw rgba(255, 77, 79, 0.2); // 红色阴影
$hover-shadow: 0 0 1.5vw rgba(255, 77, 79, 0.3); // 悬停阴影
$replace-white: #d9d9d9;

// 基础布局样式（复用参考代码）
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

// 核心指标卡片样式（复用+微调）
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

// 面板样式（复用）
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

// 图表行样式（调整适配双图表+趋势图）
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

// 表格面板样式（复用）
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

// 核心表格样式（复用）
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
.disposal-detail {
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
