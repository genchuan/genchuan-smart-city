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
        <span class="text-lg font-bold text-gray-800">督办结果反馈详情</span>
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
              <span class="info-label">反馈ID：</span>
              <span class="info-value font-bold text-blue-500">{{ detail.feedbackId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">反馈编码：</span>
              <span class="info-value font-bold text-blue-500">{{
                detail.feedbackCode || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联问题名称：</span>
              <span class="info-value">{{ detail.problemName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 关联督办任务卡片 -->
        <el-card class="info-card task-info-card">
          <div class="card-header">关联督办任务信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">关联督办任务ID：</span>
              <span class="info-value">{{ detail.supvTaskId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联督办任务编码：</span>
              <span class="info-value font-bold text-blue-500">{{
                detail.supvTaskCode || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">督办任务状态变更：</span>
              <span class="info-value">
                <span class="status-tag" :class="getStatusChangeClass()">
                  {{ detail.supvStatusChange || '-' }}
                </span>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 反馈信息卡片 -->
        <el-card class="info-card feedback-info-card">
          <div class="card-header">反馈信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">反馈人：</span>
              <span class="info-value font-bold text-blue-500">{{
                detail.feedbackUser || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">反馈时间：</span>
              <span class="info-value">{{ formatDate(detail.feedbackTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">督办结果：</span>
              <span class="info-value">
                <el-tag :type="getSupvResultTagType(detail.supvResult)">
                  {{ detail.supvResult || '-' }}
                </el-tag>
              </span>
            </div>
            <div class="info-item">
              <span class="info-label">关联整改验收结果：</span>
              <span class="info-value">
                <el-tag :type="getAcceptResultTagType(detail.rectifyAcceptResult)">
                  {{ detail.rectifyAcceptResult || '-' }}
                </el-tag>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 督办结果详情卡片 -->
        <el-card class="info-card result-detail-card">
          <div class="card-header">督办结果详情</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">督办结果说明：</span>
              <span class="info-value">{{ detail.supvResultDesc || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">督办反馈意见：</span>
              <span class="info-value">{{ detail.supvFeedbackOpinion || '-' }}</span>
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
import { InspectSupvFeedbackVO } from '@/api/dataHub/supervisionInspection/keyTaskOversight/keyTaskOversightResultFeedback'
import { ref } from 'vue'

defineOptions({ name: 'InspectSupvFeedbackDetail' })

// 避免直接修改props，使用内部状态控制抽屉显示
const isDrawerOpen = ref(false)
const isFullscreen = ref(false)
const detail = ref<InspectSupvFeedbackVO>({} as InspectSupvFeedbackVO)
const emit = defineEmits(['close'])

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

/** 获取督办结果标签类型 */
const getSupvResultTagType = (result: string) => {
  switch (result) {
    case '已完成':
      return 'success'
    case '进行中':
      return 'warning'
    // case '未通过':
    //   return 'danger'
    // case '已逾期':
    //   return 'danger'
    case '部分完成':
      return 'danger'
    default:
      return 'info'
  }
}

/** 获取验收结果标签类型 */
const getAcceptResultTagType = (result: string) => {
  switch (result) {
    case '合格':
      return 'success'
    case '待改进':
      return 'danger'
    case '待定':
      return 'warning'
    default:
      return 'info'
  }
}

// 获取状态变更样式类
const getStatusChangeClass = () => {
  const status = detail.value.supvStatusChange || ''
  if (status.includes('已完成')) return 'status-success'
  if (status.includes('进行中')) return 'status-warning'
  if (status.includes('部分完成')) return 'status-danger'
  return 'status-default'
}

// 打开抽屉
const open = (row: InspectSupvFeedbackVO) => {
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

.feedback-info-card .card-header {
  background-color: #f0f9ff;
}

.result-detail-card .card-header {
  background-color: #f6ffed;
}

.ext-info-card .card-header {
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

.status-tag {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  color: #fff !important;
  font-weight: 500;
}

.status-success {
  background-color: #52c41a !important;
}

.status-warning {
  background-color: #faad14 !important;
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
}
</style>
