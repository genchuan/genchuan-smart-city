<template>
  <el-drawer
    v-model="isDrawerOpen"
    :size="isFullscreen ? '100%' : '75%'"
    @close="handleClose"
    class="detail-drawer"
  >
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">重点督办任务详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <div class="grid-layout">
        <!-- 基本信息卡片 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">基本信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">主键：</span>
              <span class="info-value">{{ detail.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">督办任务ID：</span>
              <span class="info-value font-bold text-blue-500">{{ detail.supvTaskId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">督办任务编码：</span>
              <span class="info-value font-bold text-blue-500">{{
                detail.supvTaskCode || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联问题ID：</span>
              <span class="info-value">{{ detail.problemId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联问题名称：</span>
              <span class="info-value">{{ detail.problemName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 关联任务信息卡片 -->
        <el-card class="info-card task-info-card">
          <div class="card-header">关联任务信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">关联整改任务ID：</span>
              <span class="info-value">{{ detail.rectifyTaskId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联整改任务编码：</span>
              <span class="info-value font-bold text-blue-500">{{
                detail.rectifyTaskCode || '-'
              }}</span>
            </div>
          </div>
        </el-card>

        <!-- 督办人信息卡片 -->
        <el-card class="info-card supervisor-info-card">
          <div class="card-header">督办人信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">督办人ID：</span>
              <span class="info-value">{{ detail.supvUserId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">督办人姓名：</span>
              <span class="info-value font-bold text-blue-500">{{
                detail.supvUserName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">督办频次：</span>
              <span class="info-value">{{ detail.supvFrequency || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 时间与状态信息卡片 -->
        <el-card class="info-card time-status-card">
          <div class="card-header">时间与状态信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">督办截止时间：</span>
              <span class="info-value">{{ formatDate(detail.supvDeadlineTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">督办任务状态：</span>
              <span class="info-value">
                <span class="status-tag" :class="getStatusClass()">
                  {{ detail.supvStatus || '-' }}
                </span>
              </span>
            </div>
            <div class="info-item">
              <span class="info-label">是否逾期：</span>
              <span class="info-value">
                <span class="status-tag" :class="isOverdue ? 'status-danger' : 'status-success'">
                  {{ isOverdue ? '是' : '否' }}
                </span>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 督办要求卡片 -->
        <el-card class="info-card requirement-card" :span="2">
          <div class="card-header">督办要求</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">督办要求描述：</span>
              <span class="info-value block">{{ detail.supvRequirement || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card ext-info-card" :span="2">
          <div class="card-header">扩展信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">分类扩展字段1：</span>
              <span class="info-value">{{ detail.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分类扩展字段2：</span>
              <span class="info-value">{{ detail.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">通用扩展字段1：</span>
              <span class="info-value">{{ detail.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">通用扩展字段2：</span>
              <span class="info-value">{{ detail.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectSupvTaskVO } from '@/api/dataHub/supervisionInspection/keyTaskOversight/keyTaskOversightCreation'
import { ref, computed } from 'vue'

defineOptions({ name: 'InspectSupvTaskDetail' })

// 避免直接修改props，使用内部状态控制抽屉显示
const isDrawerOpen = ref(false)
const isFullscreen = ref(false)
const detail = ref<InspectSupvTaskVO>({} as InspectSupvTaskVO)
const emit = defineEmits(['close'])

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 获取状态文本
// const getStatusText = () => {
//   switch (detail.value.supvStatus) {
//     case '1':
//       return '未开始'
//     case '2':
//       return '进行中'
//     case '3':
//       return '已完成'
//     case '4':
//       return '已逾期'
//     default:
//       return detail.value.supvStatus || '-'
//   }
// }

// 获取状态样式类
const getStatusClass = () => {
  switch (detail.value.supvStatus) {
    case '督办中':
      return 'status-info'
    case '2':
      return 'status-warning'
    case '已完成':
      return 'status-success'
    case '4':
      return 'status-danger'
    default:
      return 'status-default'
  }
}

// 判断是否逾期
const isOverdue = computed(() => {
  if (!detail.value.supvDeadlineTime || detail.value.supvStatus === '3') return false
  const deadline = new Date(detail.value.supvDeadlineTime).getTime()
  const now = new Date().getTime()
  return now > deadline
})

// 打开抽屉
const open = (row: InspectSupvTaskVO) => {
  detail.value = { ...row }
  isDrawerOpen.value = true
  isFullscreen.value = false
}

// 关闭抽屉
const handleClose = () => {
  isDrawerOpen.value = false
  emit('close')
}

// 对外暴露方法
defineExpose({ open })
</script>

<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
}

.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.info-card {
  border-radius: 8px;
  border: none;
  transition: all 0.3s ease;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.08);
}

.info-card[span='2'] {
  grid-column: span 2;
}

.card-header {
  padding: 12px 15px;
  font-weight: 500;
  color: #4e5969;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.task-info-card .card-header {
  background-color: #fff7e6;
}

.supervisor-info-card .card-header {
  background-color: #f0f9ff;
}

.time-status-card .card-header {
  background-color: #f6ffed;
}

.requirement-card .card-header {
  background-color: #f9f0ff;
}

.ext-info-card .card-header {
  background-color: #fff0f0;
}

.card-body {
  padding: 15px;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.6;
  align-items: center;
}

.info-item:last-child {
  margin-bottom: 0;
}

.info-label {
  flex: 0 0 130px;
  color: #6b7280;
  font-size: 14px;
}

.info-value {
  flex: 1;
  font-size: 14px;
  word-break: break-all;
}

.info-value.block {
  margin-left: 120px;
  margin-top: 4px;
  line-height: 1.8;
}

.status-tag {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  color: #fff;
  font-weight: 500;
}

.status-info {
  background-color: #4096ff;
}

.status-warning {
  background-color: #faad14;
}

.status-success {
  background-color: #52c41a;
}

.status-danger {
  background-color: #ff4d4f;
}

.status-default {
  background-color: #8c8c8c;
}

::v-deep .el-drawer__body {
  padding: 0;
  overflow-y: auto;
  height: calc(100% - 55px);
}

/* 响应式调整 */
@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }

  .info-card[span='2'] {
    grid-column: span 1;
  }

  .info-value.block {
    margin-left: 0;
  }
}
</style>
