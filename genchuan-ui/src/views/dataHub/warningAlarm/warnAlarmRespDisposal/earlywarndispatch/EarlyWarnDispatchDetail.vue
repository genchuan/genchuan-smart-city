<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" @close="onClose">
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">告警预警派单详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container" :class="{ fullscreen: isFullscreen }">
      <!-- 详情内容 -->
      <div class="grid-layout">
        <!-- 基本信息卡片 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">基本信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">派单ID：</span>
              <span class="value font-bold text-blue-500">{{ data.dispatchId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">派单编号：</span>
              <span class="value">{{ data.dispatchNo || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预警ID：</span>
              <span class="value">{{ data.earlyWarnId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">预警名称：</span>
              <span class="value">{{ data.earlyWarnName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">派单类型：</span>
              <span class="value">{{ data.dispatchType || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">派单状态：</span>
              <span class="status-pill" :class="getDispatchStatusClass(data.dispatchStatus)">
                {{ getDispatchStatusText(data.dispatchStatus) }}
              </span>
            </div>
          </div>
        </el-card>

        <!-- 接收信息卡片 -->
        <el-card class="info-card receive-info-card">
          <div class="card-header">接收信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">接收部门ID：</span>
              <span class="value">{{ data.receiveDeptId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">接收部门名称：</span>
              <span class="value">{{ data.receiveDeptName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">接收时间：</span>
              <span class="value">{{ formatDate(data.receiveTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">处置人ID：</span>
              <span class="value">{{ data.handleUserId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">处置人姓名：</span>
              <span class="value">{{ data.handleUserName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">处置时限：</span>
              <span class="value">{{ data.handleLimit || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 派单信息卡片 -->
        <el-card class="info-card dispatch-info-card">
          <div class="card-header">派单信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">派单时间：</span>
              <span class="value">{{ formatDate(data.dispatchTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">派单人ID：</span>
              <span class="value">{{ data.dispatchUserId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">派单人姓名：</span>
              <span class="value">{{ data.dispatchUserName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">拒单原因：</span>
              <span class="value">{{ data.rejectReason || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card extend-info-card">
          <div class="card-header">扩展信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">分类扩展字段1：</span>
              <span class="value">{{ data.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">分类扩展字段2：</span>
              <span class="value">{{ data.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段1：</span>
              <span class="value">{{ data.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">通用扩展字段2：</span>
              <span class="value">{{ data.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { EarlyWarnDispatchVO } from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/earlywarndispatch'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
const data = ref<EarlyWarnDispatchVO>({
  id: 0,
  dispatchId: '',
  dispatchNo: '',
  earlyWarnId: '',
  earlyWarnName: '',
  dispatchType: '',
  receiveDeptId: '',
  receiveDeptName: '',
  handleUserId: '',
  handleUserName: '',
  dispatchStatus: '',
  handleLimit: 0,
  dispatchTime: new Date(),
  receiveTime: new Date(),
  rejectReason: '',
  dispatchUserId: '',
  dispatchUserName: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

const isFullscreen = ref(false)
// 获取派单状态文本
const getDispatchStatusText = (status: string) => {
  const statusMap: Record<string, string> = {
    COMPLETED: '已完成',
    HANDLING: '处理中',
    RECEIVED: '已接收'
  }
  return statusMap[status] || '-'
}

// 获取派单状态样式类名
const getDispatchStatusClass = (status: string) => {
  const classMap: Record<string, string> = {
    COMPLETED: 'status-completed',
    HANDLING: 'status-handling',
    RECEIVED: 'status-received'
  }
  return classMap[status] || ''
}
// 设置详情数据
const setData = (row: EarlyWarnDispatchVO) => {
  data.value = { ...row }
  isFullscreen.value = false
}

// 关闭抽屉
const onClose = () => {
  emit('close')
}

// 格式化日期
const formatDate = (val: any) => {
  return val ? new Date(val).toLocaleString() : '-'
}

defineExpose({ setData })
</script>

<style scoped>
.detail-container {
  padding: 20px;
  height: 100%;
  overflow-y: auto;
}

.fullscreen {
  padding: 0;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  border-radius: 8px;
  overflow: hidden;
  transition: all 0.3s ease;
  border: none;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  color: #4e5969;
  font-size: 14px;
}

/* 派单状态样式显示设置*/

/* 状态标签样式 - 使用/deep/穿透scoped限制 */
/* 状态标签样式 - 自适应内容宽度 */
.status-pill {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  font-weight: 500;
  white-space: nowrap; /* 防止文本换行 */
}

.status-completed {
  background-color: #f0fdf4;
  color: #16a34a;
  border: 1px solid #a7f3d0;
}

.status-handling {
  background-color: #fffbeb;
  color: #d97706;
  border: 1px solid #fde68a;
}

.status-received {
  background-color: #eff6ff;
  color: #2563eb;
  border: 1px solid #bfdbfe;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  border-left: 4px solid #1890ff;
}

.receive-info-card .card-header {
  background-color: #fff7e6;
  border-left: 4px solid #faad14;
}

.dispatch-info-card .card-header {
  background-color: #f6ffed;
  border-left: 4px solid #52c41a;
}

.extend-info-card .card-header {
  background-color: #fff0f6;
  border-left: 4px solid #eb2f96;
}

.card-content {
  padding: 16px;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}

.info-item {
  display: flex;
  flex-wrap: wrap;
}

.label {
  color: #6b7280;
  margin-right: 8px;
  white-space: nowrap;
}

.value {
  color: #1f2937;
  flex: 1;
  word-break: break-all;
}

.status-success {
  color: #52c41a;
  font-weight: 500;
}

.status-danger {
  color: #f5222d;
  font-weight: 500;
}

.text-blue-500 {
  color: #3b82f6;
}

@media screen and (max-width: 1200px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  .card-content {
    grid-template-columns: 1fr;
  }
}
</style>
