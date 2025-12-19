<template>
  <div class="page-container">
    <!-- 页头区域：标题及核心操作 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/tracking.png"
          alt="事件追踪图标"
          class="title-icon"
        />
        事件处置追踪视图
      </h1>
      <div class="header-actions">
        <el-button size="small" @click="refreshData">刷新</el-button>
        <el-button size="small" @click="handleBack">返回总览</el-button>
      </div>
    </div>

    <!-- 主内容区域 -->
    <div class="main-content">
      <!-- 核心追踪指标卡片组 -->
      <div class="stats-cards">
        <div class="stat-card">
          <div class="stat-title">总追踪事件数</div>
          <div class="stat-value normal">
            {{ totalTrackingEvents }} <span class="unit">件</span>
          </div>
          <div class="stat-desc">
            高级别事件: <span class="level-item poor">{{ highLevelTrackingEvents }}件</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">在处置事件数</div>
          <div class="stat-value" :class="handlCount <= 10 ? 'good' : 'poor'">
            {{ handlCount }} <span class="unit">件</span>
          </div>
          <div class="stat-desc">
            超时未跟进: <span class="level-item poor">{{ overtimeFollowEvents }}件</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">核心追踪效率</div>
          <div class="stat-value" style="font-size: 1.1vw !important; line-height: 1.4;">
            <span class="level-item excellent">跟进覆盖率: {{ followUpRate }}%</span>
            <span class="level-item good">及时跟进率: {{ timelyFollowRate }}%</span>
          </div>
          <div class="stat-desc">
            平均跟进间隔: <span class="level-item average">{{ avgFollowUpInterval }}分钟</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">平均处置周期</div>
          <div class="stat-value" :class="avgDisposalCycle <= 150 ? 'excellent' : avgDisposalCycle <= 200 ? 'good' : 'poor'">
            {{ avgDisposalCycle }} <span class="unit">分钟</span>
          </div>
          <div class="stat-desc">
            已完成数: <span class="level-item excellent">{{ completedTrackingEvents }}件</span>
            <span class="update-time">最近同步: {{ lastSyncTime }}</span>
          </div>
        </div>
      </div>

      <!-- 图表行：双栏核心图表（进度分布+区域效率） -->
      <div class="chart-row">
        <!-- 左侧：处置进度分布饼图（核心：看事件流转状态） -->
        <div class="panel progress-panel" ref="progressPanel">
          <div class="panel-header">
            <h2>处置进度分布</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('progressPanel')">
                <el-icon color="#ff6b6b" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartPie :labels="progressLabels" :data="progressData" :colors="progressColors" />
          </div>
        </div>

        <!-- 右侧：区域追踪效率柱状图（核心：看区域效率差异） -->
        <div class="panel area-efficiency-panel" ref="areaEfficiencyPanel">
          <div class="panel-header">
            <h2>区域追踪效率</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('areaEfficiencyPanel')">
                <el-icon color="#ff6b6b" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartBar :xAxis="areaXAxis" :series="areaSeries" />
          </div>
        </div>
      </div>

      <!-- 追踪事件表格（核心：带进度+跟进信息） -->
      <div class="panel data-table-panel" ref="dataTablePanel">
        <div class="panel-header">
          <h2>事件处置追踪列表</h2>
          <div class="header-actions">
            <el-select v-model="filterType" placeholder="事件类型" size="small" class="filter-item">
              <el-option label="全部" value="" />
              <el-option label="交通事故" value="traffic_accident" />
              <el-option label="设备故障" value="equipment_failure" />
              <el-option label="道路施工" value="road_construction" />
              <el-option label="自然灾害" value="natural_disaster" />
              <el-option label="其他事件" value="other" />
            </el-select>
            <el-select v-model="filterStatus" placeholder="处置状态" size="small" class="filter-item">
              <el-option label="全部" value="" />
              <el-option label="待派单" value="pending" />
              <el-option label="已派单" value="assigned" />
              <el-option label="处置中" value="processing" />
              <el-option label="待验收" value="pending_acceptance" />
              <el-option label="已完成" value="completed" />
              <el-option label="已关闭" value="closed" />
            </el-select>
            <el-input
              v-model="searchKeyword"
              placeholder="搜索事件名称/ID"
              size="small"
              class="filter-item search-input"
              @keyup.enter="fetchTrackingEvents"
            >
              <template #append>
                <el-icon class="search-icon">
                  <Search />
                </el-icon>
              </template>
            </el-input>
            <el-button size="small" type="warning" @click="batchRemindOverdue">
              <WarningFilled size="14" /> 超时事件提醒
            </el-button>
            <el-button size="small" type="primary" @click="exportTrackingData">导出数据</el-button>
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
            @row-click="showEventDetail"
          >
            <el-table-column prop="event_id" label="事件ID" width="120" />
            <el-table-column prop="name" label="事件名称" min-width="200" />
            <el-table-column prop="type" label="事件类型">
              <template #default="scope">
                <el-tag :type="getEventTypeTagType(scope.row.type)">
                  {{ getEventTypeName(scope.row.type) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="level" label="事件等级">
              <template #default="scope">
                <el-tag :type="getEventLevelTagType(scope.row.level)">
                  {{ getEventLevelName(scope.row.level) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="status" label="处置状态">
              <template #default="scope">
                <el-tag :type="getEventStatusTagType(scope.row.status)">
                  {{ getEventStatusName(scope.row.status) }}
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
            <el-table-column prop="last_follow_time" label="最近跟进时间" width="200">
              <template #default="scope">
                <span :class="isFollowOverdue(scope.row) ? 'text-red-500 font-bold' : ''">
                  {{ scope.row.last_follow_time || '未跟进' }}
                </span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center">
              <template #default="scope">
                <el-button size="small" link @click.stop="showEventDetail(scope.row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <!-- 分页（保留，适配多数据场景） -->
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

    <!-- 事件详情弹窗（核心：基础信息+时间线+跟进记录） -->
    <el-dialog
      v-model="eventDetailVisible"
      :title="currentEvent?.name || '事件处置追踪详情'"
      width="60%"
      :close-on-click-modal="false"
    >
      <div class="event-detail">
        <!-- 基础信息区域 -->
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="事件ID">{{ currentEvent?.event_id }}</el-descriptions-item>
            <el-descriptions-item label="事件类型">{{ getEventTypeName(currentEvent?.type) }}</el-descriptions-item>
            <el-descriptions-item label="事件等级">{{ getEventLevelName(currentEvent?.level) }}</el-descriptions-item>
            <el-descriptions-item label="所属区域">{{ currentEvent?.area }}</el-descriptions-item>
            <el-descriptions-item label="上报时间">{{ currentEvent?.create_time }}</el-descriptions-item>
            <el-descriptions-item label="派单时间">{{ currentEvent?.assign_time || '未派单' }}</el-descriptions-item>
            <el-descriptions-item label="处置状态">{{ getEventStatusName(currentEvent?.status) }}</el-descriptions-item>
            <el-descriptions-item label="处置进度">
              <el-progress
                :percentage="currentEvent?.progress || 0"
                :status="getProgressStatus(currentEvent?.progress || 0)"
                size="small"
                :stroke-width="8"
                style="width: 100px; display: inline-block;"
                :show-text="false"
              />
              <span class="ml-2">{{ currentEvent?.progress || 0 }}%</span>
            </el-descriptions-item>
            <el-descriptions-item label="预计完成时间">
              <span :class="isCompletionOverdue() ? 'text-red-500 font-bold' : ''">
                {{ currentEvent?.expected_completion_time }}
              </span>
            </el-descriptions-item>
            <el-descriptions-item label="处置团队">{{ currentEvent?.handler_team }}</el-descriptions-item>
            <el-descriptions-item label="负责人">{{ currentEvent?.handler }}</el-descriptions-item>
            <el-descriptions-item label="跟进次数">{{ currentEvent?.follow_up_count || 0 }}次</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 事件描述区域 -->
        <div class="detail-section">
          <h3>事件描述</h3>
          <div class="content-card">
            {{ currentEvent?.description || '无详细描述' }}
          </div>
        </div>

        <!-- 处置时间线（核心：追溯事件流程） -->
        <div class="detail-section">
          <h3>处置时间线</h3>
          <el-timeline :reverse="false">
            <el-timeline-item
              v-for="(item, index) in eventTimeline"
              :key="index"
              :timestamp="item.time"
              :color="getTimelineColor(item.stage)"
              placement="top"
            >
              <div class="timeline-content">
                <h4 class="timeline-stage">{{ getTimelineStageName(item.stage) }}</h4>
                <p class="timeline-operator">操作人：{{ item.operator }}</p>
                <p class="timeline-desc">{{ item.content }}</p>
              </div>
            </el-timeline-item>
          </el-timeline>
        </div>

        <!-- 跟进记录（核心：查看+新增跟进） -->
        <div class="detail-section">
          <h3>跟进记录（{{ followUpRecords.length }}条）</h3>
          <!-- 新增跟进记录表单 -->
          <el-card shadow="hover" class="follow-up-form-card" v-if="currentEvent?.status !== 'completed' && currentEvent?.status !== 'closed'">
            <h4 class="form-title">新增跟进记录</h4>
            <el-form :model="followUpForm" label-width="100px" size="small">
              <el-form-item label="跟进内容" required>
                <el-input
                  v-model="followUpForm.content"
                  type="textarea"
                  :rows="3"
                  placeholder="请输入跟进内容"
                />
              </el-form-item>
              <el-form-item label="进度更新">
                <el-select v-model="followUpForm.progress" placeholder="选择当前进度" required>
                  <el-option label="20%（已响应）" value="20"/>
                  <el-option label="50%（处置中）" value="50"/>
                  <el-option label="80%（待验收）" value="80"/>
                  <el-option label="100%（已完成）" value="100"/>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="saveFollowUp">提交跟进记录</el-button>
                <el-button @click="resetFollowUpForm">取消</el-button>
              </el-form-item>
            </el-form>
          </el-card>

          <!-- 跟进记录列表 -->
          <el-table
            :data="followUpRecords"
            border
            size="small"
            style="width: 100%; margin-top: 16px;"
          >
            <el-table-column prop="follow_time" label="跟进时间" width="180" />
            <el-table-column prop="follower" label="跟进人" />
            <el-table-column prop="progress_update" label="进度更新" width="160" />
            <el-table-column prop="content" label="跟进内容" min-width="300" />
          </el-table>
        </div>
      </div>

      <!-- 弹窗底部操作按钮 -->
      <template #footer>
        <el-button type="primary" @click="refreshEventDetail">刷新详情</el-button>
        <el-button @click="eventDetailVisible = false">关闭</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, getCurrentInstance, watch } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage, ElTag, ElProgress, ElTimeline, ElTimelineItem, ElForm, ElFormItem, ElInput, ElSelect, ElOption } from 'element-plus';
import { FullScreen, Search, WarningFilled } from "@element-plus/icons-vue";
import screenFull from 'screenfull';

// 导入追踪核心API
import {
  fetchTrackingOverview,
  fetchDisposalProgressDistribution,
  fetchAreaTrackingEfficiency,
  fetchTrackingEventList,
  fetchEventTimeline,
  fetchFollowUpRecords,
  saveFollowUpRecord,
  updateEventProgress
} from '@/api/overview/emergencysafety/GlobalSituationOverview.js';

// 导入图表组件（精简：只保留核心图表）
import ChartPie from './ChartPie1.vue';
import ChartBar from './ChartBar1.vue';

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

// 筛选条件（精简：只保留核心筛选）
const filterType = ref('');         // 事件类型筛选
const filterStatus = ref('');       // 处置状态筛选
const searchKeyword = ref('');      // 搜索关键词
const currentPage = ref(1);         // 当前页码
const pageSize = ref(10);           // 每页条数

// 核心追踪指标数据
const totalTrackingEvents = ref(0);
const handlCount = ref(0);
const overtimeFollowEvents = ref(0);
const completedTrackingEvents = ref(0);
const avgFollowUpInterval = ref(0);
const avgDisposalCycle = ref(0);
const followUpRate = ref(0);
const timelyFollowRate = ref(0);
const highLevelTrackingEvents = ref(0);
const lastSyncTime = ref('');

// 图表数据（只保留核心图表）
const progressLabels = ref([]);
const progressData = ref([]);
const progressColors = ref([]);
const areaXAxis = ref([]);
const areaSeries = ref([]);

// 事件数据
const eventList = ref([]);
const currentEvent = ref(null);
const eventDetailVisible = ref(false);
const eventTimeline = ref([]);
const followUpRecords = ref([]);

// 跟进表单数据（精简：去掉附件）
const followUpForm = ref({
  event_id: '',
  content: '',
  progress: ''
});

// ---------------------- 复用参考代码的映射逻辑 ----------------------
const getEventTypeName = (type) => {
  const map = {
    'traffic_accident': '交通事故',
    'equipment_failure': '设备故障',
    'road_construction': '道路施工',
    'natural_disaster': '自然灾害',
    'other': '其他事件'
  };
  return map[type] || '未知类型';
};

const getEventTypeTagType = (type) => {
  const map = {
    'traffic_accident': 'danger',
    'equipment_failure': 'warning',
    'road_construction': 'primary',
    'natural_disaster': 'error',
    'other': 'default'
  };
  return map[type] || 'default';
};

const getEventLevelName = (level) => {
  const map = {
    'low': '低级别',
    'medium': '中级别',
    'high': '高级别'
  };
  return map[level] || '未知等级';
};

const getEventLevelTagType = (level) => {
  const map = {
    'low': 'success',
    'medium': 'warning',
    'high': 'danger'
  };
  return map[level] || 'default';
};

// 扩展追踪场景的状态映射
const getEventStatusName = (status) => {
  const map = {
    'pending': '待派单',
    'assigned': '已派单',
    'processing': '处置中',
    'pending_acceptance': '待验收',
    'completed': '已完成',
    'closed': '已关闭'
  };
  return map[status] || '未知状态';
};

const getEventStatusTagType = (status) => {
  const map = {
    'pending': 'info',
    'assigned': 'primary',
    'processing': 'warning',
    'pending_acceptance': 'success',
    'completed': 'success',
    'closed': 'danger'
  };
  return map[status] || 'default';
};

// 进度状态映射
const getProgressStatus = (progress) => {
  if (progress === 0) return 'exception';
  if (progress < 100) return 'processing';
  return 'success';
};

// 时间线映射（追踪核心：事件流程追溯）
const getTimelineStageName = (stage) => {
  const map = {
    'reported': '事件上报',
    'accepted': '事件受理',
    'assigned': '派单分配',
    'responded': '团队响应',
    'processing': '处置实施',
    'pending_acceptance': '待验收',
    'completed': '处置完成',
    'closed': '事件关闭'
  };
  return map[stage] || '未知阶段';
};

const getTimelineColor = (stage) => {
  const map = {
    'reported': '#909399',
    'accepted': '#409EFF',
    'assigned': '#722ED1',
    'responded': '#E6A23C',
    'processing': '#FF7D00',
    'pending_acceptance': '#67C23A',
    'completed': '#1989FA',
    'closed': '#F56C6C'
  };
  return map[stage] || '#909399';
};
// ---------------------- 映射逻辑结束 ----------------------

// 筛选后的事件列表（计算属性）
const filteredEventList = computed(() => {
  return eventList.value.filter(item => {
    const matchesType = !filterType.value || item.type === filterType.value;
    const matchesStatus = !filterStatus.value || item.status === filterStatus.value;
    const matchesKeyword = !searchKeyword.value ||
      item.name.includes(searchKeyword.value) ||
      item.event_id.includes(searchKeyword.value);
    return matchesType && matchesStatus && matchesKeyword;
  }).slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
});

// 判断跟进是否超时（2小时未跟进）
const isFollowOverdue = (row) => {
  if (!row.last_follow_time || row.status === 'completed' || row.status === 'closed') return false;
  const lastFollowTime = new Date(row.last_follow_time);
  const now = new Date();
  const diffHours = (now - lastFollowTime) / (1000 * 60 * 60);
  return diffHours > 2;
};

// 判断预计完成时间是否超时
const isCompletionOverdue = () => {
  if (!currentEvent.value?.expected_completion_time ||
    currentEvent.value.status === 'completed' ||
    currentEvent.value.status === 'closed') return false;
  const expectedTime = new Date(currentEvent.value.expected_completion_time);
  const now = new Date();
  return now > expectedTime;
};

// 加载追踪事件列表
const fetchTrackingEvents = async () => {
  try {
    const params = {
      type: filterType.value,
      status: filterStatus.value,
      keyword: searchKeyword.value,
      page: currentPage.value,
      size: pageSize.value
    };
    const data = await fetchTrackingEventList(params);
    eventList.value = data;
  } catch (error) {
    ElMessage.error('加载追踪事件列表失败：' + error.message);
  }
};

// 显示事件详情
const showEventDetail = async (event) => {
  currentEvent.value = { ...event };
  followUpForm.value.event_id = event.event_id;
  // 加载时间线和跟进记录
  await Promise.all([
    fetchEventTimelineData(event.event_id),
    fetchFollowUpRecordsData(event.event_id)
  ]);
  eventDetailVisible.value = true;
};

// 加载事件时间线
const fetchEventTimelineData = async (eventId) => {
  try {
    const data = await fetchEventTimeline(eventId);
    eventTimeline.value = data;
  } catch (error) {
    ElMessage.error('加载事件时间线失败：' + error.message);
  }
};

// 加载跟进记录
const fetchFollowUpRecordsData = async (eventId) => {
  try {
    const data = await fetchFollowUpRecords(eventId);
    followUpRecords.value = data;
  } catch (error) {
    ElMessage.error('加载跟进记录失败：' + error.message);
  }
};

// 刷新事件详情
const refreshEventDetail = async () => {
  if (!currentEvent.value?.event_id) return;
  try {
    await Promise.all([
      fetchEventTimelineData(currentEvent.value.event_id),
      fetchFollowUpRecordsData(currentEvent.value.event_id)
    ]);
    // 刷新事件基本信息
    const events = await fetchTrackingEventList({ event_id: currentEvent.value.event_id });
    if (events.length > 0) {
      currentEvent.value = { ...events[0] };
    }
    ElMessage.success('详情刷新成功！');
  } catch (error) {
    ElMessage.error('刷新详情失败：' + error.message);
  }
};

// 保存跟进记录（精简：去掉附件逻辑）
const saveFollowUp = async () => {
  if (!followUpForm.value.content || !followUpForm.value.progress) {
    ElMessage.warning('请完善跟进内容和进度');
    return;
  }
  try {
    const params = {
      event_id: followUpForm.value.event_id,
      content: followUpForm.value.content,
      progress: followUpForm.value.progress,
      follower: '当前用户', // 实际项目取登录用户
      follower_team: '当前团队' // 实际项目取登录用户团队
    };
    const res = await saveFollowUpRecord(params);
    if (res.success) {
      // 更新跟进记录列表
      const newRecord = {
        record_id: res.record_id,
        follow_time: new Date().toLocaleString('zh-CN', {
          year: 'numeric',
          month: '2-digit',
          day: '2-digit',
          hour: '2-digit',
          minute: '2-digit',
          second: '2-digit'
        }).replace(/\//g, '-'),
        follower: params.follower,
        follower_team: params.follower_team,
        content: params.content,
        progress_update: `${params.progress}%（${params.progress == 20 ? '已响应' : params.progress == 50 ? '处置中' : params.progress == 80 ? '待验收' : '已完成'}）`,
        attachments: []
      };
      followUpRecords.value.unshift(newRecord);
      // 更新事件进度和状态
      let status = currentEvent.value.status;
      if (params.progress == 20) status = 'assigned';
      else if (params.progress == 50) status = 'processing';
      else if (params.progress == 80) status = 'pending_acceptance';
      else if (params.progress == 100) status = 'completed';

      const progressRes = await updateEventProgress(followUpForm.value.event_id, params.progress, status);
      if (progressRes.success) {
        currentEvent.value.progress = params.progress;
        currentEvent.value.status = status;
        currentEvent.value.last_follow_time = newRecord.follow_time;
        currentEvent.value.follow_up_count = (currentEvent.value.follow_up_count || 0) + 1;
        // 刷新列表数据
        fetchTrackingEvents();
      }
      ElMessage.success('跟进记录提交成功！');
      resetFollowUpForm();
    }
  } catch (error) {
    ElMessage.error('提交跟进记录失败：' + error.message);
  }
};

// 重置跟进表单
const resetFollowUpForm = () => {
  followUpForm.value.content = '';
  followUpForm.value.progress = '';
};

// 确认完成事件
const completeEvent = async () => {
  try {
    const res = await updateEventProgress(currentEvent.value.event_id, 100, 'completed');
    if (res.success) {
      currentEvent.value.progress = 100;
      currentEvent.value.status = 'completed';
      // 添加完成时间线
      eventTimeline.value.push({
        stage: 'completed',
        time: res.update_time,
        operator: '当前用户',
        content: '确认事件处置完成，现场已恢复正常，事件闭环',
        attachments: []
      });
      ElMessage.success('事件已确认完成！');
      fetchTrackingEvents();
    }
  } catch (error) {
    ElMessage.error('确认完成失败：' + error.message);
  }
};

// 关闭事件
const closeEvent = async () => {
  try {
    const res = await updateEventProgress(currentEvent.value.event_id, currentEvent.value.progress, 'closed');
    if (res.success) {
      currentEvent.value.status = 'closed';
      // 添加关闭时间线
      eventTimeline.value.push({
        stage: 'closed',
        time: res.update_time,
        operator: '当前用户',
        content: '手动关闭事件，无需继续处置',
        attachments: []
      });
      ElMessage.success('事件已关闭！');
      fetchTrackingEvents();
    }
  } catch (error) {
    ElMessage.error('关闭事件失败：' + error.message);
  }
};

// 批量提醒超时事件
const batchRemindOverdue = () => {
  const overdueEvents = eventList.value.filter(item => isFollowOverdue(item));
  if (overdueEvents.length === 0) {
    ElMessage.info('暂无超时未跟进事件');
    return;
  }
  ElMessage.success(`已向${overdueEvents.length}个超时事件的负责人发送提醒！`);
};

// 分页相关方法
const handleSizeChange = (val) => {
  pageSize.value = val;
  currentPage.value = 1;
  fetchTrackingEvents();
};

const handleCurrentChange = (val) => {
  currentPage.value = val;
  fetchTrackingEvents();
};

// 快速跟进（表格快捷操作）
const quickFollowUp = (row) => {
  showEventDetail(row);
  // 聚焦跟进表单
  setTimeout(() => {
    const textarea = document.querySelector('.follow-up-form-card textarea');
    textarea?.focus();
  }, 500);
};

// 刷新所有数据
const refreshData = async () => {
  try {
    ElMessage.info('正在刷新数据...');
    const [overviewData, resProgress, areaData] = await Promise.all([
      fetchTrackingOverview(),
      fetchDisposalProgressDistribution(),
      fetchAreaTrackingEfficiency()
    ]);

    totalTrackingEvents.value = overviewData.totalTrackingEvents;
    handlCount.value = overviewData.handlCount;
    overtimeFollowEvents.value = overviewData.overtimeFollowEvents;
    completedTrackingEvents.value = overviewData.completedTrackingEvents;
    avgFollowUpInterval.value = overviewData.avgFollowUpInterval;
    avgDisposalCycle.value = overviewData.avgDisposalCycle;
    followUpRate.value = overviewData.followUpRate;
    timelyFollowRate.value = overviewData.timelyFollowRate;
    highLevelTrackingEvents.value = overviewData.highLevelTrackingEvents;
    lastSyncTime.value = overviewData.lastSyncTime;

    progressLabels.value = resProgress.labels;
    progressData.value = resProgress.data;
    progressColors.value = resProgress.colors;
    areaXAxis.value = areaData.xAxis;
    areaSeries.value = areaData.series;

    await fetchTrackingEvents();

    ElMessage.success('追踪数据刷新成功！');
  } catch (error) {
    ElMessage.error('数据刷新失败：' + error.message);
    console.error('刷新失败详情：', error);
  }
};

// 导出追踪数据
const exportTrackingData = () => {
  ElMessage.success('事件处置追踪数据导出成功！');
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/emergencysafety/dpzl');
};

// 初始化数据
const initData = async () => {
  try {
    const [overviewData, resProgress, areaData] = await Promise.all([
      fetchTrackingOverview(),
      fetchDisposalProgressDistribution(),
      fetchAreaTrackingEfficiency()
    ]);

    totalTrackingEvents.value = overviewData.totalTrackingEvents;
    handlCount.value = overviewData.handlCount;
    overtimeFollowEvents.value = overviewData.overtimeFollowEvents;
    completedTrackingEvents.value = overviewData.completedTrackingEvents;
    avgFollowUpInterval.value = overviewData.avgFollowUpInterval;
    avgDisposalCycle.value = overviewData.avgDisposalCycle;
    followUpRate.value = overviewData.followUpRate;
    timelyFollowRate.value = overviewData.timelyFollowRate;
    highLevelTrackingEvents.value = overviewData.highLevelTrackingEvents;
    lastSyncTime.value = overviewData.lastSyncTime;

    progressLabels.value = resProgress.labels;
    progressData.value = resProgress.data;
    progressColors.value = resProgress.colors;
    areaXAxis.value = areaData.xAxis;
    areaSeries.value = areaData.series;

    await fetchTrackingEvents();

    console.log('饼图数据赋值成功：', {
      labels: progressLabels.value,
      data: progressData.value,
      colors: progressColors.value
    });
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
watch([filterType, filterStatus, searchKeyword], () => {
  currentPage.value = 1;
  fetchTrackingEvents();
});
</script>

<style lang="scss" scoped>
$primary-color: #ff4d4f; // 核心警示红
$secondary-color: #13ce66; // 安全绿
$warning-color: #faad14; // 警告黄
$danger-color: #ff3838; // 危险红
$info-color: #409eff; // 信息蓝
$text-primary: #b3b3b3; // 主文本色（替代白色）
$text-secondary: #8c8c8c; // 次要文本色
$text-tertiary: #5d5b5b; // tertiary文本色
$bg-main: #0a0f28; // 主背景（深黑蓝）
$bg-card: rgba(56, 32, 80, 0.6);
$bg-panel: rgba(50, 16, 100, 0.5);
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
    background: $primary-color; // 红色主题线
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

  .progress-panel {
    flex: 1;
  }

  .area-efficiency-panel {
    flex: 3;
  }
}

// 表格面板样式（核心调整区域）
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

// 核心表格样式（彻底清除TD白色，优先级拉满）
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

  // 表头样式（添加 text-align: center 居中）
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
      text-align: center !important; // 表头文字居中（核心新增）
      &:last-child {
        border-right: none !important;
      }
    }
  }

  // 表体样式
  .el-table__body {
    background-color: transparent !important;

    tr {
      background-color: transparent !important;

      td {
        background-color: rgba(18, 28, 66, 0.3) !important;
        text-align: center !important; // 单元格文字居中（核心新增）
      }

      // 选中行样式
      &.current-row {
        td {
          background-color: rgba(255, 77, 79, 0.2) !important;
          color: $replace-white !important;
        }
      }
    }
  }

  // 单元格核心样式（彻底清除白色，补充居中）
  .el-table__cell {
    border-bottom: 1px solid $border-light !important;
    border-right: 1px solid $border-light !important;
    padding: 1.2vh 0.8vw !important;
    font-size: 0.8vw;
    transition: all 0.2s;
    background-color: transparent !important;
    color: $text-primary !important;
    text-align: center !important; // 兜底确保居中（核心新增）

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
.event-detail {
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
  }

  // 跟进表单样式
  .follow-up-form-card {
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

  // 跟进记录表格样式（同样清除白色）
  ::v-deep .el-table--small {
    --el-table-header-text-color: #ffc1e9 !important;
    --el-table-header-bg-color: rgba(18, 28, 66, 0.9) !important;
    --el-table-bg-color: transparent !important;
    --el-table-text-color: $text-secondary !important;
    --el-table-border-color: $border-light !important;
    --el-table-row-hover-bg-color: rgba(255, 77, 79, 0.1) !important;
    --el-table-row-stripes-bg-color: rgba(18, 28, 66, 0.5) !important;
    border: 1px solid $border-color !important;

    .el-table__header {
      border-bottom: 1px solid $border-color !important;

      th {
        background: rgba(18, 28, 66, 0.9) !important;
        border-right: 1px solid $border-light !important;

        &:last-child {
          border-right: none !important;
        }
      }
    }

    .el-table__body {
      tr {
        background-color: transparent !important;
      }
    }

    .el-table__cell {
      border-bottom: 1px solid $border-light !important;
      border-right: 1px solid $border-light !important;
      padding: 0.8vh 0.6vw !important;
      font-size: 0.85vw;
      background-color: transparent !important;

      &:last-child {
        border-right: none !important;
      }
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

    .el-input, .el-select {
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
