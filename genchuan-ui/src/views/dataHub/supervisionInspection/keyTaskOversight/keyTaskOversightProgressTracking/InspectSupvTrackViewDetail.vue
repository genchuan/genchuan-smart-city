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
        <span class="text-lg font-bold text-gray-800">督办进度跟踪详情</span>
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
              <span class="info-label">关联问题名称：</span>
              <span class="info-value">{{ detail.problemName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 负责人信息卡片 -->
        <el-card class="info-card leader-info-card">
          <div class="card-header">负责人信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">督办人姓名：</span>
              <span class="info-value font-bold text-blue-500">{{
                detail.supvUserName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改负责人姓名：</span>
              <span class="info-value font-bold text-blue-500">{{
                detail.leaderUserName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">督办操作次数：</span>
              <span class="info-value">{{ detail.supvOperCount || 0 }}</span>
            </div>
          </div>
        </el-card>

        <!-- 时间信息卡片 -->
        <el-card class="info-card time-info-card">
          <div class="card-header">时间信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">督办截止时间：</span>
              <span class="info-value">{{ formatDate(detail.supvDeadlineTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">最新督办进展时间：</span>
              <span class="info-value">{{ formatDate(detail.latestSupvTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">是否超期：</span>
              <span class="info-value">
                <el-tag :type="detail.isOverdue ? 'danger' : 'success'">
                  {{ detail.isOverdue ? '是' : '否' }}
                </el-tag>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 状态信息卡片 -->
        <el-card class="info-card status-info-card">
          <div class="card-header">状态信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">督办任务状态：</span>
              <span class="info-value">
                <el-tag :type="detail.supvStatus === '督办中' ? 'primary' : 'success'">
                  {{ detail.supvStatus }}
                </el-tag>
              </span>
            </div>
            <div class="info-item">
              <span class="info-label">关联整改任务状态：</span>
              <span class="info-value">
                <el-tag :type="detail.rectifyStatus === '进行中' ? 'primary' : 'success'">
                  {{ detail.rectifyStatus }}
                </el-tag>
              </span>
            </div>
            <div class="info-item">
              <span class="info-label">最新督办进展类型：</span>
              <span class="info-value">{{ detail.latestSupvType || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 督办进展详情卡片 -->
        <el-card class="info-card progress-card" :span="2">
          <div class="card-header">督办进展详情</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">最新督办进展内容：</span>
              <div class="info-value block" v-html="detail.latestSupvContent || '-'"></div>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectSupvTrackViewVO } from '@/api/dataHub/supervisionInspection/keyTaskOversight/keyTaskOversightProgressTracking'
import { ref } from 'vue'

defineOptions({ name: 'InspectSupvTrackViewDetail' })

// 避免直接修改props，使用内部状态控制抽屉显示
const isDrawerOpen = ref(false)
const isFullscreen = ref(false)
const detail = ref<InspectSupvTrackViewVO>({} as InspectSupvTrackViewVO)
const emit = defineEmits(['close'])

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 获取督办状态文本
const getSupvStatusText = () => {
  switch (detail.value.supvStatus) {
    case '1':
      return '未开始'
    case '2':
      return '进行中'
    case '3':
      return '已完成'
    case '4':
      return '已逾期'
    default:
      return detail.value.supvStatus || '-'
  }
}

// 获取督办状态样式类
const getSupvStatusClass = () => {
  switch (detail.value.supvStatus) {
    case '1':
      return 'status-info'
    case '2':
      return 'status-warning'
    case '3':
      return 'status-success'
    case '4':
      return 'status-danger'
    default:
      return 'status-default'
  }
}

// 获取整改状态文本
const getRectifyStatusText = () => {
  switch (detail.value.rectifyStatus) {
    case '1':
      return '未开始'
    case '2':
      return '进行中'
    case '3':
      return '已完成'
    case '4':
      return '已逾期'
    default:
      return detail.value.rectifyStatus || '-'
  }
}

// 获取整改状态样式类
const getRectifyStatusClass = () => {
  switch (detail.value.rectifyStatus) {
    case '1':
      return 'status-info'
    case '2':
      return 'status-warning'
    case '3':
      return 'status-success'
    case '4':
      return 'status-danger'
    default:
      return 'status-default'
  }
}

// 打开抽屉
const open = (row: InspectSupvTrackViewVO) => {
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

.leader-info-card .card-header {
  background-color: #fff7e6;
}

.time-info-card .card-header {
  background-color: #f0f9ff;
}

.status-info-card .card-header {
  background-color: #f6ffed;
}

.progress-card .card-header {
  background-color: #f9f0ff;
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
  color: #1f2937;
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
  color: #fff !important;
  font-weight: 500;
}

.status-info {
  background-color: #4096ff !important;
}

.status-warning {
  background-color: #faad14 !important;
}

.status-success {
  background-color: #52c41a !important;
}

.status-danger {
  background-color: #ff4d4f !important;
}

.status-default {
  background-color: #8c8c8c !important;
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
