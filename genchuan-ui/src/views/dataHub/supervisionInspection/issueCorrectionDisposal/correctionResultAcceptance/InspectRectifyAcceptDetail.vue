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
        <span class="text-lg font-bold text-gray-800">整改结果验收详情</span>
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
              <span class="info-label">验收ID：</span>
              <span class="info-value font-bold text-blue-500">{{ detail.acceptId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">验收编码：</span>
              <span class="info-value font-bold text-blue-500">{{ detail.acceptCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联问题ID：</span>
              <span class="info-value">{{ detail.problemId || '-' }}</span>
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
            <div class="info-item">
              <span class="info-label">任务状态变更：</span>
              <span class="info-value">
                <span class="status-tag" :class="getStatusChangeClass()">
                  {{ detail.taskStatusChange || '-' }}
                </span>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 验收信息卡片 -->
        <el-card class="info-card accept-info-card">
          <div class="card-header">验收信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">验收结果：</span>
              <span class="info-value">
                <span class="status-tag" :class="getAcceptResultClass()">
                  {{ detail.acceptResult || '-' }}
                </span>
              </span>
            </div>
            <div class="info-item">
              <span class="info-label">验收人：</span>
              <span class="info-value">{{ detail.acceptUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">验收时间：</span>
              <span class="info-value">{{ formatDate(detail.acceptTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">验收意见：</span>
              <span class="info-value">{{ detail.acceptOpinion || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 整改提交信息卡片 -->
        <el-card class="info-card submit-info-card">
          <div class="card-header">整改提交信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">整改提交人：</span>
              <span class="info-value">{{ detail.rectifySubmitUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改提交时间：</span>
              <span class="info-value">{{ formatDate(detail.rectifySubmitTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改结果描述：</span>
              <span class="info-value">{{ detail.rectifyResultDesc || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 照片对比卡片 -->
        <el-card class="info-card photo-card" :span="2">
          <div class="card-header">照片对比</div>
          <div class="card-body">
            <div class="photo-group">
              <div class="photo-item">
                <h4 class="photo-title">整改前</h4>
                <div class="photo-container">
                  <div v-if="rectifyBeforePhotos.length > 0" class="photo-list">
                    <el-image
                      v-for="(photo, index) in rectifyBeforePhotos"
                      :key="index"
                      :src="photo"
                      :preview-src-list="rectifyBeforePhotos"
                      class="photo-img"
                      fallback="https://via.placeholder.com/200x150?text=暂无照片"
                    />
                  </div>
                  <div v-else class="no-photo">暂无照片</div>
                </div>
              </div>
              <div class="photo-item">
                <h4 class="photo-title">整改后</h4>
                <div class="photo-container">
                  <div v-if="rectifyAfterPhotos.length > 0" class="photo-list">
                    <el-image
                      v-for="(photo, index) in rectifyAfterPhotos"
                      :key="index"
                      :src="photo"
                      :preview-src-list="rectifyAfterPhotos"
                      class="photo-img"
                      fallback="https://via.placeholder.com/200x150?text=暂无照片"
                    />
                  </div>
                  <div v-else class="no-photo">暂无照片</div>
                </div>
              </div>
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
import { InspectRectifyAcceptVO } from '@/api/dataHub/supervisionInspection/issueCorrectionDisposal/correctionResultAcceptance'
import { ref, computed } from 'vue'

defineOptions({ name: 'InspectRectifyAcceptDetail' })

// 避免直接修改props，使用内部状态控制抽屉显示
const isDrawerOpen = ref(false)
const isFullscreen = ref(false)
const detail = ref<InspectRectifyAcceptVO>({} as InspectRectifyAcceptVO)
const emit = defineEmits(['close'])

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}

// 拆分照片URLs为数组
const rectifyBeforePhotos = computed(() => {
  return detail.value.rectifyBeforePhotoUrls
    ? detail.value.rectifyBeforePhotoUrls.split(',').map((url) => url.trim())
    : []
})

const rectifyAfterPhotos = computed(() => {
  return detail.value.rectifyAfterPhotoUrls
    ? detail.value.rectifyAfterPhotoUrls.split(',').map((url) => url.trim())
    : []
})

// 获取验收结果样式类
const getAcceptResultClass = () => {
  switch (detail.value.acceptResult) {
    case '合格':
      return 'status-success'
    case '待改进':
      return 'status-danger'
    case '待定':
      return 'status-warning'
    default:
      return 'status-default'
  }
}

// 获取状态变更样式类
const getStatusChangeClass = () => {
  const status = detail.value.taskStatusChange || ''
  if (status.includes('已完成')) return 'status-success'
  if (status.includes('待改进')) return 'status-danger'
  if (status.includes('进行中')) return 'status-warning'
  return 'status-default'
}

// 打开抽屉
const open = (row: InspectRectifyAcceptVO) => {
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

.accept-info-card .card-header {
  background-color: #f0f9ff;
}

.submit-info-card .card-header {
  background-color: #f6ffed;
}

.photo-card .card-header {
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

.status-tag {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  color: #fff;
  font-weight: 500;
}

.status-success {
  background-color: #52c41a;
}

.status-danger {
  background-color: #ff4d4f;
}

.status-warning {
  background-color: #faad14;
}

.status-default {
  background-color: #8c8c8c;
}

/* 照片对比样式 */
.photo-group {
  display: flex;
  gap: 20px;
}

.photo-item {
  flex: 1;
}

.photo-title {
  font-size: 14px;
  color: #4e5969;
  margin-bottom: 10px;
  font-weight: 500;
}

.photo-container {
  border: 1px solid #e5e7eb;
  border-radius: 4px;
  padding: 15px;
  min-height: 150px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.photo-list {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  justify-content: center;
}

.photo-img {
  width: 200px;
  height: 150px;
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

  .photo-group {
    flex-direction: column;
  }
}
</style>
