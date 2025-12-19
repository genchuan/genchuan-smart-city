<template>
  <el-drawer
    v-model="localVisible"
    :size="isFullscreen ? '100%' : '75%'"
    @open="handleDrawerOpen"
    :before-close="handleBeforeClose"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800"
          >预警详情 - {{ detailData.alertCode || '' }}</span
        >
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <el-tabs v-model="activeTab" class="warning-detail-tabs">
      <!-- 基本信息 -->
      <el-tab-pane label="基本信息" name="basic">
        <el-descriptions :column="2" border class="custom-descriptions">
          <el-descriptions-item label="预警编码"
            >{{ detailData.alertCode || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="关联对象类型"
            >{{ detailData.relatedObjectType || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="关联对象名称"
            >{{ detailData.relatedObjectName || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="预警领域"
            >{{ detailData.warningField || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="预警类型"
            >{{ detailData.warningType || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="预警等级"
            >{{ formatLevel(detailData.warningLevel) }}
          </el-descriptions-item>
          <el-descriptions-item label="当前状态"
            >{{ formatStatus(detailData.status) }}
          </el-descriptions-item>
          <el-descriptions-item label="触发时间"
            >{{ formatDate(detailData.triggerTime) }}
          </el-descriptions-item>
          <el-descriptions-item label="触发原因"
            >{{ detailData.triggerReason || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="责任人"
            >{{ detailData.responsiblePerson || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="行政区划编码"
            >{{ detailData.regionCode || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="所在行政区划名称"
            >{{ detailData.regionName || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="所在网格ID"
            >{{ detailData.gridId || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="所在网格名称"
            >{{ detailData.gridName || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="经度"
            >{{ detailData.longitude || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="纬度">{{ detailData.latitude || '-' }}</el-descriptions-item>
          <el-descriptions-item label="事件发生地址"
            >{{ detailData.address || '-' }}
          </el-descriptions-item>
          <!-- 现场照片 - 修复居中+清理废弃属性 -->
          <el-descriptions-item label="现场照片" :span="2" class="photo-item">
            <div class="photo-container">
              <el-space wrap :size="16" v-if="getScenePhotos.length > 0">
                <div class="photo-wrapper" v-for="(img, index) in getScenePhotos" :key="index">
                  <el-image
                    :src="img"
                    :preview-src-list="getScenePhotos"
                    width="100%"
                    height="100%"
                    fit="cover"
                    class="cursor-pointer"
                    lazy
                  >
                    <template #error>
                      <div class="image-error">加载失败</div>
                    </template>
                  </el-image>
                </div>
              </el-space>
              <div class="empty-photo" v-else>-</div>
            </div>
          </el-descriptions-item>
          <el-descriptions-item label="上报人"
            >{{ detailData.uploaderName || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="上报人电话"
            >{{ detailData.uploaderPhone || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="事件描述"
            >{{ detailData.eventDescription || '-' }}
          </el-descriptions-item>
          <el-descriptions-item label="标题">{{ detailData.title || '-' }}</el-descriptions-item>
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
            <el-table-column
              prop="assigneeUser.nickname"
              label="审批人"
              min-width="120"
              align="center"
            />
            <el-table-column
              prop="createTime"
              label="开始时间"
              min-width="160"
              align="center"
              :formatter="dateFormatter"
            />
            <el-table-column align="center" label="审批状态" prop="status" min-width="90">
              <template #default="scope">
                <dict-tag :type="DICT_TYPE.BPM_TASK_STATUS" :value="scope.row.status" />
              </template>
            </el-table-column>
            <el-table-column
              prop="endTime"
              label="结束时间"
              min-width="160"
              :formatter="dateFormatter"
            />
            <el-table-column prop="durationInMillis" label="耗时(ms)" width="100" />
            <el-table-column
              prop="comment"
              label="审批意见"
              min-width="180"
              show-overflow-tooltip
            />
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
import { ref, reactive, onUnmounted, computed } from 'vue'
import { ElMessage, ElButton } from 'element-plus'
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
const detailData = reactive<
  WarningAlertListTableVO & {
    scenePhotos?: string
    uploaderName?: string
    uploaderPhone?: string
    eventDescription?: string
    title?: string
  }
>({
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
  processInstanceId: '',
  regionCode: '',
  regionName: '',
  gridId: '',
  gridName: '',
  address: '',
  longitude: '',
  latitude: '',
  scenePhotos: '',
  uploaderName: '',
  uploaderPhone: '',
  eventDescription: '',
  title: ''
})

const emit = defineEmits(['update:currentId'])

/** 解析现场照片字段 */
const getScenePhotos = computed(() => {
  if (!detailData.scenePhotos) return []
  try {
    const photos = JSON.parse(detailData.scenePhotos)
    return Array.isArray(photos) ? photos.filter((img) => img) : []
  } catch (e) {
    console.error('解析现场照片失败:', e)
    return []
  }
})

const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

/** 打开详情抽屉 */
const open = (
  data: WarningAlertListTableVO & {
    scenePhotos?: string
    uploaderName?: string
    uploaderPhone?: string
    eventDescription?: string
    title?: string
  }
) => {
  currentId.value = data.id || null
  localVisible.value = true
  console.log('流程实例ID：', data.processInstanceId)
  loadData(data)
  emit('update:currentId', currentId.value)
}

/** 加载详情与任务列表 */
const loadData = async (
  data: WarningAlertListTableVO & {
    scenePhotos?: string
    uploaderName?: string
    uploaderPhone?: string
    eventDescription?: string
    title?: string
  }
) => {
  Object.keys(data).forEach((key) => {
    // @ts-ignore 兼容扩展字段赋值
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

/** 抽屉打开时 */
const handleDrawerOpen = () => {
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
    case 'emergency':
      return '紧急'
    case 'important':
      return '重要'
    default:
      return '一般'
  }
}

/** 格式化状态 */
const formatStatus = (status: number) => {
  switch (status) {
    case 0:
      return '未派单'
    default:
      return '已派单'
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
.warning-detail-tabs {
  margin-top: 10px;
}

.items-center {
  align-items: center;
}

/* 自定义描述组件样式，确保内容区域填充单元格 */
.custom-descriptions .el-descriptions-item {
  display: flex;
  align-items: center;
}

.custom-descriptions .el-descriptions-item__content {
  flex: 1;
  display: flex;
  align-items: center;
  padding: 12px 8px;
}

/* 核心优化：图片单元格完全居中 */
.photo-item .el-descriptions-item__content {
  justify-content: center; /* 水平居中 */
  min-height: 120px; /* 确保单元格有足够高度，避免内容挤压 */
}

.photo-container {
  width: 100%;
  max-height: 300px;
  overflow-y: auto;
  box-sizing: border-box;
  display: flex;
  align-items: center; /* 垂直居中 */
  justify-content: center; /* 水平居中 */
  gap: 16px; /* 替代 el-space 的 size，避免废弃属性 */
  flex-wrap: wrap; /* 自动换行 */
}

.photo-wrapper {
  width: 140px;
  height: 100px;
  border-radius: 4px;
  overflow: hidden;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

.image-error {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f5f5f5;
  color: #999;
  font-size: 12px;
}

/* 无图片时居中显示 */
.empty-photo {
  color: #999;
  font-size: 14px;
  padding: 20px;
}
</style>
