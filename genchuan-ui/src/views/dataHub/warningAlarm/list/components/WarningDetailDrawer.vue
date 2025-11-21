<template>
  <el-drawer
    v-model="localVisible"
    :size="isFullscreen ? '100%' : '75%'"
    :modal="false"
    :destroy-on-close="true"
    :custom-class="isFullscreen ? 'fullscreen-drawer' : ''"
    @open="handleDrawerOpen"
    :before-close="handleBeforeClose"
  >
    <template #title>
      <div class="drawer-title-container">
        <span>预警详情 - {{ detailData.alertCode || '' }}</span>
        <div class="absolute top-4 right-16 flex flex-col items-center gap-2">
          <el-button
            :icon="FullScreen"
            circle
            size="small"
            @click="handleToggleFullscreen"
            :title="isFullscreen ? '退出全屏' : '全屏展开'"
            class="custom-fullscreen-btn"
          />
        </div>
      </div>
    </template>

    <el-tabs v-model="activeTab" class="warning-detail-tabs">
      <!-- 基本信息 -->
      <el-tab-pane label="基本信息" name="basic">
        <el-descriptions :column="2" border>
          <el-descriptions-item label="预警编码">{{ detailData.alertCode }}</el-descriptions-item>
          <el-descriptions-item label="关联对象类型">{{ detailData.relatedObjectType }}</el-descriptions-item>
          <el-descriptions-item label="关联对象名称">{{ detailData.relatedObjectName }}</el-descriptions-item>
          <el-descriptions-item label="预警领域">{{ detailData.warningField }}</el-descriptions-item>
          <el-descriptions-item label="预警类型">{{ detailData.warningType }}</el-descriptions-item>
          <el-descriptions-item label="预警等级">{{ formatLevel(detailData.warningLevel) }}</el-descriptions-item>
          <el-descriptions-item label="当前状态">{{ formatStatus(detailData.status) }}</el-descriptions-item>
          <el-descriptions-item label="触发时间"  :formatter="dateFormatter">{{ detailData.triggerTime }}</el-descriptions-item>
          <el-descriptions-item label="触发原因">{{ detailData.triggerReason }}</el-descriptions-item>
          <el-descriptions-item label="责任人">{{ detailData.responsiblePerson }}</el-descriptions-item>
        </el-descriptions>
      </el-tab-pane>

      <!-- 处置日志 -->
      <el-tab-pane label="处置日志" name="handle">
        <template v-if="detailData.processInstanceId">
          <el-table
            v-loading="processInstanceLoading"
            :data="tasks"
            border
            style="width: 100%"
            size="default"
          >
            <el-table-column prop="name" label="审批节点" min-width="140" />
            <el-table-column prop="assigneeUser.nickname" label="审批人" min-width="120" align="center" />
            <el-table-column prop="createTime" label="开始时间" min-width="160" align="center"  :formatter="dateFormatter"/>
            <el-table-column align="center" label="审批状态" prop="status" min-width="90">
              <template #default="scope">
                <dict-tag :type="DICT_TYPE.BPM_TASK_STATUS" :value="scope.row.status" />
              </template>
            </el-table-column>
            <el-table-column prop="endTime" label="结束时间" min-width="160"  :formatter="dateFormatter" />
            <el-table-column prop="durationInMillis" label="耗时(ms)" width="100" />
            <el-table-column prop="comment" label="审批意见" min-width="180" show-overflow-tooltip />
          </el-table>
        </template>
        <template v-else>
          <el-empty description="暂无流程实例信息" />
        </template>
      </el-tab-pane>
    </el-tabs>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, reactive, onUnmounted } from 'vue'
import { ElMessage, ElButton } from 'element-plus'
import { FullScreen } from '@element-plus/icons-vue'
import { dateFormatter } from '@/utils/formatTime'
import * as TaskApi from '@/api/bpm/task'
import { WarningAlertListTableVO } from '@/api/dataHub/warningAlarm/list'
import { DICT_TYPE } from '@/utils/dict'

/** Drawer 可见性 */
const localVisible = ref(false)
/** 当前激活的标签页 */
const activeTab = ref('basic')
/** 加载状态 */
const processInstanceLoading = ref(false)
/** 当前预警 ID */
const currentId = ref<number | null>(null)
/** 流程任务记录 */
const tasks = ref<any[]>([])
/** 全屏状态 */
const isFullscreen = ref(false)

/** 详情数据 */
const detailData = reactive<WarningAlertListTableVO>({
  alertCode: '',
  relatedObjectType: '',
  relatedObjectName: '',
  warningField: '',
  warningType: '',
  warningLevel: '',
  warningStatus: '',
  triggerReason: '',
  relatedEventCode: '',
  dispatchDepartment: '',
  responsiblePerson: '',
  responsiblePersonPhone: '',
  triggerTime: null,
  requiredCompleteTime: null,
  disposalProgressDesc: '',
  disposalAttachmentPath: '',
  reviewOpinion: '',
  reviewer: '',
  reviewTime: null,
  deviceId: '',
  status: 0,
  processInstanceId: ''
})

const emit = defineEmits(['update:currentId'])

/** 打开详情抽屉 */
const open = (data: WarningAlertListTableVO) => {
  currentId.value = data.id || null
  localVisible.value = true
  console.log('流程实例ID：', data.processInstanceId)
  loadData(data)
  emit('update:currentId', currentId.value)
}

/** 加载详情与任务列表 */
const loadData = async (data: WarningAlertListTableVO) => {
  Object.keys(data).forEach((key) => {
    detailData[key] = data[key]
  })

  if (detailData.processInstanceId) {
    await loadProcessTasks()
  } else {
    tasks.value = []
  }
}

/** 根据流程实例 ID 加载任务记录 */
const loadProcessTasks = async () => {
  try {
    processInstanceLoading.value = true
    const res = await TaskApi.getTaskListByProcessInstanceId(detailData.processInstanceId)
    tasks.value = res || []
  } catch (err) {
    console.error(err)
    ElMessage.error('加载流程任务失败')
    tasks.value = []
  } finally {
    processInstanceLoading.value = false
  }
}

/** 切换全屏状态 */
const handleToggleFullscreen = () => {
  isFullscreen.value = !isFullscreen.value
}

/** 抽屉打开时 */
const handleDrawerOpen = () => {
  // 可添加逻辑，比如自动切到基本信息
  activeTab.value = 'basic'
}

/** 抽屉关闭前 */
const handleBeforeClose = () => {
  if (isFullscreen.value) {
    isFullscreen.value = false
  }
  localVisible.value = false
  emit('update:currentId', null)
  return true
}

/** 格式化等级 */
const formatLevel = (level: string) => {
  switch (level) {
    case 'emergency': return '紧急'
    case 'important': return '重要'
    default: return '一般'
  }
}

/** 格式化状态 */
const formatStatus = (status: number) => {
  switch (status) {
    case 0: return '未派单'
    default: return '已派单'
  }
}

/** 监听浏览器退出全屏同步状态 */
document.addEventListener('fullscreenchange', () => {
  if (!document.fullscreenElement && isFullscreen.value) {
    isFullscreen.value = false
  }
})

onUnmounted(() => {
  document.removeEventListener('fullscreenchange', () => {})
})

defineExpose({ open })
</script>

<style scoped>
.drawer-title-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-right: 40px;
  position: relative;
}

.title-actions {
  position: absolute;
  right: 16px;
  top: 4px;
}

.custom-fullscreen-btn {
  transition: all 0.3s ease;
}

.warning-detail-tabs {
  margin-top: 10px;
}

/* 全屏模式样式可选 */
::v-deep(.fullscreen-drawer) {
  height: 100vh !important;
  top: 0 !important;
  left: 0 !important;
}

.absolute {
  position: absolute;
}
.top-4 {
  top: -1px; /* 与标题栏顶部对齐 */
}

.right-16 {
  right: 1px; /* 与右侧关闭按钮保持间距（可根据实际需求调整） */
}
.flex-col {
  flex-direction: column;
}
.items-center {
  align-items: center;
}
.gap-2 {
  gap: 10px; /* 与关闭按钮的垂直间距（可按需调整） */
}
</style>
