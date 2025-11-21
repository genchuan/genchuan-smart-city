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
        <span class="text-lg font-bold text-gray-800">整改进度跟踪详情</span>
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
              <span class="info-label">整改任务ID：</span>
              <span class="info-value font-bold text-blue-500">{{
                detail.rectifyTaskId || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改任务编码：</span>
              <span class="info-value font-bold text-blue-500">{{
                detail.rectifyTaskCode || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联问题名称：</span>
              <span class="info-value">{{ detail.problemName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 责任信息卡片 -->
        <el-card class="info-card responsibility-card">
          <div class="card-header">责任信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">责任部门名称：</span>
              <span class="info-value">{{ detail.deptName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改负责人姓名：</span>
              <span class="info-value font-bold text-blue-500">{{
                detail.leaderUserName || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">派发时间：</span>
              <span class="info-value">{{ formatDate(detail.dispatchTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 时间限制卡片 -->
        <el-card class="info-card time-limit-card">
          <div class="card-header">时间限制信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">整改时限（小时）：</span>
              <span class="info-value">{{ detail.rectifyDeadlineHour || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改截止时间：</span>
              <span class="info-value">{{ formatDate(detail.rectifyDeadlineTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">剩余时间（分钟）：</span>
              <span class="info-value">{{ detail.remainTimeMin || '-' }}</span>
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
        <el-card class="info-card status-card">
          <div class="card-header">状态信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">整改任务状态：</span>
              <span class="info-value">
                <el-tag
                  :type="
                    detail.rectifyStatus === '待处理'
                      ? 'warning'
                      : detail.rectifyStatus === '已完成'
                        ? 'success'
                        : 'primary'
                  "
                >
                  {{ detail.rectifyStatus }}
                </el-tag>
              </span>
            </div>
            <div class="info-item">
              <span class="info-label">催办次数：</span>
              <span class="info-value">{{ detail.remindCount || 0 }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">最后催办时间：</span>
              <span class="info-value">{{ formatDate(detail.lastRemindTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 进展信息卡片 -->
        <el-card class="info-card progress-card" :span="2">
          <div class="card-header">最新进展信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">最新进展时间：</span>
              <span class="info-value">{{ formatDate(detail.latestProgressTime) }}</span>
            </div>
            <div class="info-item full-width">
              <span class="info-label">最新进展描述：</span>
              <span class="info-value block">{{ detail.latestProgressDesc || '-' }}</span>
            </div>
            <div class="info-item full-width">
              <span class="info-label">最新进展照片：</span>
              <div class="photo-container">
                <el-image
                  v-if="detail.latestProgressPhoto"
                  :src="detail.latestProgressPhoto"
                  :preview-src-list="[detail.latestProgressPhoto]"
                  class="progress-photo"
                  fallback="https://via.placeholder.com/150x100?text=暂无照片"
                />
                <span v-else class="no-photo">暂无照片</span>
              </div>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectRectifyTrackVO } from '@/api/dataHub/supervisionInspection/issueCorrectionDisposal/correctionProgressTracking'
import { ref, watch } from 'vue'

defineOptions({ name: 'InspectRectifyTrackDetail' })

// 避免直接修改props，使用内部状态控制抽屉显示
const isDrawerOpen = ref(false)
const isFullscreen = ref(false)
const detail = ref<InspectRectifyTrackVO>({} as InspectRectifyTrackVO)
const emit = defineEmits(['close'])

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 获取状态文本
// const getStatusText = (status: string) => {
//   switch (status) {
//     case '1':
//       return '未开始'
//     case '2':
//       return '进行中'
//     case '3':
//       return '已完成'
//     default:
//       return status || '-'
//   }
// }

// 打开抽屉
const open = (row: InspectRectifyTrackVO) => {
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

.responsibility-card .card-header {
  background-color: #fff7e6;
}

.time-limit-card .card-header {
  background-color: #f0f9ff;
}

.status-card .card-header {
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

.info-item.full-width {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
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
}

.photo-container {
  margin-left: 120px;
  margin-top: 4px;
}

.progress-photo {
  width: 150px;
  height: 100px;
  object-fit: cover;
  border-radius: 4px;
  cursor: pointer;
}

.no-photo {
  color: #9ca3af;
  font-size: 14px;
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

  .info-value.block,
  .photo-container {
    margin-left: 0;
  }
}
</style>
