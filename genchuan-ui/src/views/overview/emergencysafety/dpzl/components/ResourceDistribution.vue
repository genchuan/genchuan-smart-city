<template>
  <div class="page-container">
    <!-- 页头区域：标题及核心操作 -->
    <div class="page-header">
      <h1>
        <img
          src="@/assets/chart/images/resource.png"
          alt="应急资源图标"
          class="title-icon"
        />
        应急资源分布视图
      </h1>
      <div class="header-actions">
        <el-button size="small" @click="refreshData">刷新</el-button>
        <el-button size="small" @click="handleBack">返回总览</el-button>
      </div>
    </div>

    <!-- 主内容区域 -->
    <div class="main-content">
      <!-- 核心资源指标卡片组 -->
      <div class="stats-cards">
        <div class="stat-card">
          <div class="stat-title">应急资源总数</div>
          <div class="stat-value normal">
            {{ totalResourceCount }} <span class="unit">件/套</span>
          </div>
          <div class="stat-desc">
            关键资源数: <span class="level-item poor">{{ keyResourceCount }}件/套</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">资源状态分布</div>
          <div class="stat-value" style="font-size: 1.1vw !important; line-height: 1.4;">
            <span class="level-item poor">待调配: {{ pendingAllocation }}件/套</span>
            <span class="level-item good">在用: {{ inUseResources }}件/套</span>
          </div>
          <div class="stat-desc">
            可用: <span class="level-item excellent">{{ availableResources }}件/套</span>
            故障: <span class="level-item poor">{{ faultyResources }}件/套</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">核心资源效率</div>
          <div class="stat-value" style="font-size: 1.1vw !important; line-height: 1.4;">
            <span class="level-item excellent">可用率: {{ resourceAvailabilityRate }}%</span>
            <span class="level-item good">完好率: {{ resourceIntegrityRate }}%</span>
          </div>
          <div class="stat-desc">
            平均维护时长: <span class="level-item average">{{ avgMaintenanceDuration }}分钟</span>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-title">各类型资源维护耗时</div>
          <div class="stat-value" style="font-size: 1vw !important; line-height: 1.4;">
            <span class="level-item poor">医疗: {{ avgTypeMaintenance.medical }}分钟</span>
            <span class="level-item poor">救援: {{ avgTypeMaintenance.rescue }}分钟</span>
          </div>
          <div class="stat-desc">
            最近同步: <span class="update-time">{{ lastSyncTime }}</span>
          </div>
        </div>
      </div>

      <div class="chart-row">
        <div class="panel trend-panel" ref="trendPanel">
        <div class="panel-header">
          <h2>近7天资源状态趋势</h2>
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
        <div class="panel duration-panel" ref="durationPanel">
          <div class="panel-header">
            <h2>各类型资源维护耗时分析</h2>
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
        <div class="panel status-panel" ref="statusPanel">
          <div class="panel-header">
            <h2>资源状态分布</h2>
            <div class="header-actions">
              <button class="panel-fullscreen-btn" @click="toggleFullscreen('statusPanel')">
                <el-icon color="#ff6b6b" size="16">
                  <FullScreen/>
                </el-icon>
              </button>
            </div>
          </div>
          <div class="panel-body">
            <ChartPie :labels="statusLabels" :data="statusData" :colors="statusColors" />
          </div>
        </div>
      </div>
    </div>

    <!-- 资源列表表格（核心：带状态、类型、可用率信息） -->
    <div class="panel data-table-panel" ref="dataTablePanel">
      <div class="panel-header">
        <h2>应急资源列表</h2>
        <div class="header-actions">
          <el-select v-model="filterType" placeholder="资源类型" size="small" class="filter-item">
            <el-option label="全部" value="" />
            <el-option label="消防类" value="fire" />
            <el-option label="医疗类" value="medical" />
            <el-option label="交通类" value="traffic" />
            <el-option label="通讯类" value="communication" />
            <el-option label="救援类" value="rescue" />
            <el-option label="后勤类" value="logistics" />
          </el-select>
          <el-select v-model="filterStatus" placeholder="资源状态" size="small" class="filter-item">
            <el-option label="全部" value="" />
            <el-option label="待调配" value="pending_allocation" />
            <el-option label="在用" value="in_use" />
            <el-option label="可用" value="available" />
            <el-option label="故障" value="faulty" />
            <el-option label="维修中" value="maintaining" />
            <el-option label="已报废" value="scrapped" />
          </el-select>
          <el-select v-model="filterLevel" placeholder="资源等级" size="small" class="filter-item">
            <el-option label="全部" value="" />
            <el-option label="普通资源" value="normal" />
            <el-option label="重要资源" value="important" />
            <el-option label="关键资源" value="key" />
          </el-select>
          <el-input
            v-model="searchKeyword"
            placeholder="搜索资源名称/ID"
            size="small"
            class="filter-item search-input"
            @keyup.enter="fetchResourceList"
          >
            <template #append>
              <el-icon class="search-icon">
                <Search />
              </el-icon>
            </template>
          </el-input>
          <el-button size="small" type="danger" @click="batchRemindFaulty">
            <WarningFilled size="14" /> 故障资源提醒
          </el-button>
          <el-button size="small" type="primary" @click="exportResourceData">导出数据</el-button>
          <button class="panel-fullscreen-btn" @click="toggleFullscreen('dataTablePanel')">
            <el-icon color="#ff6b6b" size="16">
              <FullScreen/>
            </el-icon>
          </button>
        </div>
      </div>
      <div class="panel-body table-scroll">
        <el-table
          :data="filteredResourceList"
          border
          size="small"
          style="width: 100%"
          @row-click="showResourceDetail"
        >
          <el-table-column prop="resource_id" label="资源ID" width="120" />
          <el-table-column prop="resource_name" label="资源名称" min-width="200" />
          <el-table-column prop="resource_type" label="资源类型">
            <template #default="scope">
              <el-tag :type="getResourceTypeTagType(scope.row.resource_type)">
                {{ getResourceTypeName(scope.row.resource_type) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="level" label="资源等级">
            <template #default="scope">
              <el-tag :type="getLevelTagType(scope.row.level)">
                {{ getLevelName(scope.row.level) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="status" label="资源状态">
            <template #default="scope">
              <el-tag :type="getStatusTagType(scope.row.status)">
                {{ getStatusName(scope.row.status) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="资源可用率">
            <template #default="scope">
              <div class="progress-container">
                <el-progress
                  :percentage="scope.row.availability_rate"
                  :status="getAvailabilityStatus(scope.row.availability_rate)"
                  size="small"
                  :stroke-width="8"
                  style="width: 100px; display: inline-block;"
                  :show-text="false"
                />
                <span class="progress-text">{{ scope.row.availability_rate }}%</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="next_maintenance_time" label="下次维护时间">
            <template #default="scope">
                <span :class="isMaintenanceOverdue(scope.row) ? 'text-red-500 font-bold' : ''">
                  {{ scope.row.next_maintenance_time }}
                </span>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="160" align="center">
            <template #default="scope">
              <el-button size="small" link @click.stop="showResourceDetail(scope.row)">详情</el-button>
              <el-button
                v-if="scope.row.status !== 'scrapped'"
                size="small"
                link
                @click.stop="quickUpdateStatus(scope.row)"
              >
                更新状态
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
          :total="resourceList.length"
          layout="total, sizes, prev, pager, next"
          size="small"
        />
      </div>
    </div>

    <!-- 资源详情弹窗（核心：基础信息+时间线+状态更新） -->
    <el-dialog
      v-model="resourceDetailVisible"
      :title="currentResource?.resource_name || '应急资源详情'"
      width="50%"
      :close-on-click-modal="false"
    >
      <div class="resource-detail">
        <!-- 基础信息区域 -->
        <div class="detail-section">
          <h3>基础信息</h3>
          <el-descriptions column="2" border>
            <el-descriptions-item label="资源ID">{{ currentResource?.resource_id }}</el-descriptions-item>
            <el-descriptions-item label="资源类型">{{ getResourceTypeName(currentResource?.resource_type) }}</el-descriptions-item>
            <el-descriptions-item label="资源等级">{{ getLevelName(currentResource?.level) }}</el-descriptions-item>
            <el-descriptions-item label="所属区域">{{ currentResource?.area }}</el-descriptions-item>
            <el-descriptions-item label="采购时间">{{ currentResource?.purchase_time || '未录入' }}</el-descriptions-item>
            <el-descriptions-item label="上次维护时间">{{ currentResource?.last_maintenance_time || '未维护' }}</el-descriptions-item>
            <el-descriptions-item label="资源状态">{{ getStatusName(currentResource?.status) }}</el-descriptions-item>
            <el-descriptions-item label="资源可用率">
              <el-progress
                :percentage="currentResource?.availability_rate || 0"
                :status="getAvailabilityStatus(currentResource?.availability_rate || 0)"
                size="small"
                :stroke-width="8"
                style="width: 100px; display: inline-block;"
                :show-text="false"
              />
              <span class="ml-2">{{ currentResource?.availability_rate || 0 }}%</span>
            </el-descriptions-item>
            <el-descriptions-item label="下次维护时间">
                <span :class="isDetailMaintenanceOverdue() ? 'text-red-500 font-bold' : ''">
                  {{ currentResource?.next_maintenance_time }}
                </span>
            </el-descriptions-item>
            <el-descriptions-item label="管理团队">{{ currentResource?.management_team }}</el-descriptions-item>
            <el-descriptions-item label="管理员">{{ currentResource?.manager }}</el-descriptions-item>
            <el-descriptions-item label="报废时间">{{ currentResource?.actual_scrap_time || '在用/可用' }}</el-descriptions-item>
          </el-descriptions>
        </div>

        <!-- 资源描述区域 -->
        <div class="detail-section">
          <h3>资源描述</h3>
          <div class="content-card">
            {{ currentResource?.description || '无详细描述' }}
          </div>
        </div>

        <!-- 资源时间线（核心：各状态变更记录+维护耗时） -->
        <div class="detail-section">
          <h3>资源状态时间线</h3>
          <el-timeline :reverse="false">
            <el-timeline-item
              v-for="(item, index) in resourceTimeline"
              :key="index"
              :timestamp="item.time"
              :color="getTimelineColor(item.status)"
              placement="top"
            >
              <div class="timeline-content">
                <h4 class="timeline-stage">{{ getTimelineStatusName(item.status) }}</h4>
                <p class="timeline-operator">操作人：{{ item.operator }}</p>
                <p class="timeline-desc">{{ item.content }}</p>
                <p class="timeline-duration">耗时：{{ item.duration }}</p>
              </div>
            </el-timeline-item>
          </el-timeline>
        </div>

        <!-- 状态更新表单（核心操作） -->
        <div class="detail-section" v-if="currentResource?.status !== 'scrapped'">
          <h3>更新资源状态</h3>
          <el-card shadow="hover" class="status-form-card">
            <el-form :model="statusForm" label-width="100px" size="small">
              <el-form-item label="资源状态" required>
                <el-select v-model="statusForm.status" placeholder="选择当前状态" required>
                  <el-option label="待调配" value="pending_allocation"/>
                  <el-option label="在用" value="in_use"/>
                  <el-option label="可用" value="available"/>
                  <el-option label="故障" value="faulty"/>
                  <el-option label="维修中" value="maintaining"/>
                  <el-option label="已报废" value="scrapped"/>
                </el-select>
              </el-form-item>
              <el-form-item label="维护记录" required>
                <el-input
                  v-model="statusForm.content"
                  type="textarea"
                  :rows="3"
                  placeholder="请输入维护/状态变更记录内容"
                />
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="saveResourceStatus">提交状态更新</el-button>
                <el-button @click="resetStatusForm">取消</el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </div>
      </div>

      <!-- 弹窗底部操作按钮 -->
      <template #footer>
        <el-button type="primary" @click="refreshResourceDetail">刷新详情</el-button>
        <el-button @click="resourceDetailVisible = false">关闭</el-button>
      </template>
    </el-dialog>

    <!-- 快速更新状态弹窗 -->
    <el-dialog
      v-model="quickStatusVisible"
      title="快速更新资源状态"
      width="25%"
      :close-on-click-modal="false"
    >
      <el-form :model="quickStatusForm" label-width="100px" size="small">
        <el-form-item label="资源状态" required>
          <el-select v-model="quickStatusForm.status" placeholder="选择当前状态" required>
            <el-option label="待调配" value="pending_allocation"/>
            <el-option label="在用" value="in_use"/>
            <el-option label="可用" value="available"/>
            <el-option label="故障" value="faulty"/>
            <el-option label="维修中" value="maintaining"/>
            <el-option label="已报废" value="scrapped"/>
          </el-select>
        </el-form-item>
        <el-form-item label="可用率(%)" required>
          <el-input-number
            v-model="quickStatusForm.availability_rate"
            :min="0"
            :max="100"
            :step="5"
            placeholder="输入资源可用率"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="quickStatusVisible = false">取消</el-button>
        <el-button type="primary" @click="saveQuickStatus">确认更新</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, getCurrentInstance, watch } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { FullScreen, Search, WarningFilled } from "@element-plus/icons-vue";
import screenFull from 'screenfull';

// 导入应急资源分布核心API
import {
  fetchResourceDistributionOverview,
  fetchResourceStatusDistribution,
  fetchResourceTypeDurationAnalysis,
  fetchResourceTrend7Days,
  eResourceList,
  fetchResourceTimeline,
  updateResourceStatus,
  submitResourceRecord
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
const filterType = ref('');         // 资源类型筛选
const filterStatus = ref('');       // 资源状态筛选
const filterLevel = ref('');        // 资源等级筛选
const searchKeyword = ref('');      // 搜索关键词
const currentPage = ref(1);         // 当前页码
const pageSize = ref(10);           // 每页条数

// 核心资源指标数据
const totalResourceCount = ref(0);
const pendingAllocation = ref(0);
const inUseResources = ref(0);
const availableResources = ref(0);
const faultyResources = ref(0);
const avgMaintenanceDuration = ref(0);
const avgTypeMaintenance = ref({
  fire: 0, medical: 0, traffic: 0, communication: 0, rescue: 0, logistics: 0
});
const resourceAvailabilityRate = ref(0);
const resourceIntegrityRate = ref(0);
const keyResourceCount = ref(0);
const lastSyncTime = ref('');

// 图表数据
const statusLabels = ref([]);
const statusData = ref([]);
const statusColors = ref([]);
const durationXAxis = ref([]);
const durationSeries = ref([]);
const trendXAxis = ref([]);
const trendSeries = ref([]);

// 资源列表数据
const resourceList = ref([]);
const currentResource = ref(null);
const resourceDetailVisible = ref(false);
const resourceTimeline = ref([]);

// 状态更新表单数据
const statusForm = ref({
  resource_id: '',
  status: '',
  availability_rate: 0,
  content: ''
});

// 快速更新状态弹窗
const quickStatusVisible = ref(false);
const quickStatusForm = ref({
  resource_id: '',
  status: '',
  availability_rate: 0
});

// ---------------------- 映射逻辑 ----------------------
// 资源类型映射
const getResourceTypeName = (type) => {
  const map = {
    'fire': '消防类',
    'medical': '医疗类',
    'traffic': '交通类',
    'communication': '通讯类',
    'rescue': '救援类',
    'logistics': '后勤类'
  };
  return map[type] || '未知类型';
};

const getResourceTypeTagType = (type) => {
  const map = {
    'fire': 'danger',
    'medical': 'success',
    'traffic': 'warning',
    'communication': 'primary',
    'rescue': 'error',
    'logistics': 'info'
  };
  return map[type] || 'default';
};

// 资源等级映射
const getLevelName = (level) => {
  const map = {
    'normal': '普通资源',
    'important': '重要资源',
    'key': '关键资源'
  };
  return map[level] || '未知等级';
};

const getLevelTagType = (level) => {
  const map = {
    'normal': 'default',
    'important': 'warning',
    'key': 'danger'
  };
  return map[level] || 'default';
};

// 资源状态映射
const getStatusName = (status) => {
  const map = {
    'pending_allocation': '待调配',
    'in_use': '在用',
    'available': '可用',
    'faulty': '故障',
    'maintaining': '维修中',
    'scrapped': '已报废'
  };
  return map[status] || '未知状态';
};

const getStatusTagType = (status) => {
  const map = {
    'pending_allocation': 'info',
    'in_use': 'primary',
    'available': 'success',
    'faulty': 'danger',
    'maintaining': 'warning',
    'scrapped': 'default'
  };
  return map[status] || 'default';
};

// 可用率状态映射
const getAvailabilityStatus = (rate) => {
  if (rate === 0) return 'exception';
  if (rate < 100) return 'processing';
  return 'success';
};

// 时间线映射
const getTimelineStatusName = (status) => {
  const map = {
    'purchase': '采购入库',
    'allocation': '资源调配',
    'use': '启用使用',
    'maintenance': '维护保养',
    'fault': '故障报修',
    'scrap': '报废处理'
  };
  return map[status] || '未知操作';
};

const getTimelineColor = (status) => {
  const map = {
    'purchase': '#909399',
    'allocation': '#409EFF',
    'use': '#722ED1',
    'maintenance': '#E6A23C',
    'fault': '#F56C6C',
    'scrap': '#67C23A'
  };
  return map[status] || '#909399';
};
// ---------------------- 映射逻辑结束 ----------------------

// 筛选后的资源列表
const filteredResourceList = computed(() => {
  return resourceList.value.filter(item => {
    const matchesType = !filterType.value || item.resource_type === filterType.value;
    const matchesStatus = !filterStatus.value || item.status === filterStatus.value;
    const matchesLevel = !filterLevel.value || item.level === filterLevel.value;
    const matchesKeyword = !searchKeyword.value ||
      item.resource_name.includes(searchKeyword.value) ||
      item.resource_id.includes(searchKeyword.value);
    return matchesType && matchesStatus && matchesLevel && matchesKeyword;
  }).slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
});

// 判断下次维护时间是否超时
const isMaintenanceOverdue = (row) => {
  if (!row.next_maintenance_time || row.status === 'scrapped') return false;
  const nextTime = new Date(row.next_maintenance_time);
  const now = new Date();
  return now > nextTime;
};

// 详情页判断维护超时
const isDetailMaintenanceOverdue = () => {
  if (!currentResource.value?.next_maintenance_time || currentResource.value.status === 'scrapped') return false;
  const nextTime = new Date(currentResource.value.next_maintenance_time);
  const now = new Date();
  return now > nextTime;
};

// 加载资源列表
const fetchResourceList = async () => {
  try {
    const params = {
      type: filterType.value,
      status: filterStatus.value,
      level: filterLevel.value,
      keyword: searchKeyword.value,
      page: currentPage.value,
      size: pageSize.value
    };
    const data = await eResourceList(params);
    resourceList.value = data;
  } catch (error) {
    ElMessage.error('加载资源列表失败：' + error.message);
  }
};

// 显示资源详情
const showResourceDetail = async (resource) => {
  currentResource.value = { ...resource };
  statusForm.value.resource_id = resource.resource_id;
  statusForm.value.status = resource.status;
  statusForm.value.availability_rate = resource.availability_rate;
  // 加载资源时间线
  await fetchResourceTimelineData(resource.resource_id);
  resourceDetailVisible.value = true;
};

// 加载资源时间线
const fetchResourceTimelineData = async (resourceId) => {
  try {
    const data = await fetchResourceTimeline(resourceId);
    resourceTimeline.value = data;
  } catch (error) {
    ElMessage.error('加载资源时间线失败：' + error.message);
  }
};

// 刷新资源详情
const refreshResourceDetail = async () => {
  if (!currentResource.value?.resource_id) return;
  try {
    await fetchResourceTimelineData(currentResource.value.resource_id);
    // 刷新资源基本信息
    const resources = await fetchResourceList({ resource_id: currentResource.value.resource_id });
    if (resources.length > 0) {
      currentResource.value = { ...resources[0] };
      statusForm.value.status = currentResource.value.status;
      statusForm.value.availability_rate = currentResource.value.availability_rate;
    }
    ElMessage.success('详情刷新成功！');
  } catch (error) {
    ElMessage.error('刷新详情失败：' + error.message);
  }
};

// 保存资源状态更新
const saveResourceStatus = async () => {
  if (!statusForm.value.status || !statusForm.value.content || statusForm.value.availability_rate === null) {
    ElMessage.warning('请完善资源状态、可用率和记录内容');
    return;
  }
  try {
    // 更新资源状态
    const statusRes = await updateResourceStatus(
      statusForm.value.resource_id,
      statusForm.value.status,
      statusForm.value.availability_rate
    );
    if (statusRes.success) {
      // 提交维护记录
      const recordParams = {
        resource_id: statusForm.value.resource_id,
        status: statusForm.value.status,
        availability_rate: statusForm.value.availability_rate,
        content: statusForm.value.content,
        operator: '当前用户', // 实际项目取登录用户
        team: currentResource.value.management_team
      };
      const recordRes = await submitResourceRecord(recordParams);
      if (recordRes.success) {
        // 更新当前资源数据
        currentResource.value.status = statusForm.value.status;
        currentResource.value.availability_rate = statusForm.value.availability_rate;
        // 添加时间线记录
        resourceTimeline.value.push({
          status: statusForm.value.status === 'scrapped' ? 'scrap' :
            statusForm.value.status === 'faulty' ? 'fault' :
              statusForm.value.status === 'maintaining' ? 'maintenance' :
                statusForm.value.status === 'in_use' ? 'use' :
                  statusForm.value.status === 'pending_allocation' ? 'allocation' : 'purchase',
          time: statusRes.update_time,
          operator: '当前用户',
          content: statusForm.value.content,
          duration: '实时更新',
          attachments: []
        });
        // 刷新列表数据
        fetchResourceList();
        ElMessage.success('资源状态更新成功！');
        resetStatusForm();
      }
    }
  } catch (error) {
    ElMessage.error('更新资源状态失败：' + error.message);
  }
};

// 重置状态表单
const resetStatusForm = () => {
  statusForm.value.content = '';
};

// 快速更新状态弹窗
const quickUpdateStatus = (row) => {
  quickStatusForm.value.resource_id = row.resource_id;
  quickStatusForm.value.status = row.status;
  quickStatusForm.value.availability_rate = row.availability_rate;
  quickStatusVisible.value = true;
};

// 保存快速状态更新
const saveQuickStatus = async () => {
  if (!quickStatusForm.value.status || quickStatusForm.value.availability_rate === null) {
    ElMessage.warning('请完善资源状态和可用率');
    return;
  }
  try {
    const res = await updateResourceStatus(
      quickStatusForm.value.resource_id,
      quickStatusForm.value.status,
      quickStatusForm.value.availability_rate
    );
    if (res.success) {
      // 更新列表数据
      fetchResourceList();
      // 关闭弹窗
      quickStatusVisible.value = false;
      ElMessage.success('资源状态快速更新成功！');
    }
  } catch (error) {
    ElMessage.error('快速更新状态失败：' + error.message);
  }
};

// 批量提醒故障资源
const batchRemindFaulty = () => {
  const faultyResources = resourceList.value.filter(item => item.status === 'faulty');
  if (faultyResources.length === 0) {
    ElMessage.info('暂无故障资源');
    return;
  }
  ElMessage.success(`已向${faultyResources.length}个故障资源的管理员发送提醒！`);
};

// 分页相关方法
const handleSizeChange = (val) => {
  pageSize.value = val;
  currentPage.value = 1;
  fetchResourceList();
};

const handleCurrentChange = (val) => {
  currentPage.value = val;
  fetchResourceList();
};

// 导出资源数据
const exportResourceData = () => {
  ElMessage.success('应急资源分布数据导出成功！');
};

// 返回总览页
const handleBack = () => {
  router.push('/overview/emergencysafety/dpzl');
};

// 刷新所有数据
const refreshData = async () => {
  try {
    ElMessage.info('正在刷新数据...');
    const [overviewData, statusDataRes, durationData, trendData] = await Promise.all([
      fetchResourceDistributionOverview(),
      fetchResourceStatusDistribution(),
      fetchResourceTypeDurationAnalysis(),
      fetchResourceTrend7Days()
    ]);

    // 更新核心指标
    totalResourceCount.value = overviewData.totalResourceCount;
    pendingAllocation.value = overviewData.pendingAllocation;
    inUseResources.value = overviewData.inUseResources;
    availableResources.value = overviewData.availableResources;
    faultyResources.value = overviewData.faultyResources;
    avgMaintenanceDuration.value = overviewData.avgMaintenanceDuration;
    avgTypeMaintenance.value = overviewData.avgTypeMaintenance;
    resourceAvailabilityRate.value = overviewData.resourceAvailabilityRate;
    resourceIntegrityRate.value = overviewData.resourceIntegrityRate;
    keyResourceCount.value = overviewData.keyResourceCount;
    lastSyncTime.value = overviewData.lastSyncTime;

    // 更新图表数据
    statusLabels.value = statusDataRes.labels;
    statusData.value = statusDataRes.data;
    statusColors.value = statusDataRes.colors;
    durationXAxis.value = durationData.xAxis;
    durationSeries.value = durationData.series;
    trendXAxis.value = trendData.xAxis;
    trendSeries.value = trendData.series;

    await fetchResourceList();

    ElMessage.success('应急资源分布数据刷新成功！');
  } catch (error) {
    ElMessage.error('数据刷新失败：' + error.message);
    console.error('刷新失败详情：', error);
  }
};

// 初始化数据
const initData = async () => {
  try {
    const [overviewData, statusDataRes, durationData, trendData] = await Promise.all([
      fetchResourceDistributionOverview(),
      fetchResourceStatusDistribution(),
      fetchResourceTypeDurationAnalysis(),
      fetchResourceTrend7Days()
    ]);

    // 赋值核心指标
    totalResourceCount.value = overviewData.totalResourceCount;
    pendingAllocation.value = overviewData.pendingAllocation;
    inUseResources.value = overviewData.inUseResources;
    availableResources.value = overviewData.availableResources;
    faultyResources.value = overviewData.faultyResources;
    avgMaintenanceDuration.value = overviewData.avgMaintenanceDuration;
    avgTypeMaintenance.value = overviewData.avgTypeMaintenance;
    resourceAvailabilityRate.value = overviewData.resourceAvailabilityRate;
    resourceIntegrityRate.value = overviewData.resourceIntegrityRate;
    keyResourceCount.value = overviewData.keyResourceCount;
    lastSyncTime.value = overviewData.lastSyncTime;

    // 赋值图表数据
    statusLabels.value = statusDataRes.labels;
    statusData.value = statusDataRes.data;
    statusColors.value = statusDataRes.colors;
    durationXAxis.value = durationData.xAxis;
    durationSeries.value = durationData.series;
    trendXAxis.value = trendData.xAxis;
    trendSeries.value = trendData.series;

    await fetchResourceList();

    console.log('应急资源分布数据初始化完成');
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
watch([filterType, filterStatus, filterLevel, searchKeyword], () => {
  currentPage.value = 1;
  fetchResourceList();
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
$bg-card: rgba(144, 164, 215, 0.2);
$bg-panel: rgba(144, 164, 215, 0.1);
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

  .status-panel {
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
.resource-detail {
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

  // 状态表单样式
  .status-form-card {
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
      --el-input-bg-color: rgba(193, 203, 241, 0.7);
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

// 快速更新状态弹窗样式
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
