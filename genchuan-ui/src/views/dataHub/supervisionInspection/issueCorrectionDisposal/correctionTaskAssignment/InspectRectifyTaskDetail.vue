<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" @close="handleClose">
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">整改任务派发详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="detail-container" :class="{ fullscreen: isFullscreen }">
      <!-- 内容区使用网格布局确保卡片对齐 -->
      <div class="detail-content grid-layout">
        <!-- 基本信息卡片 -->
        <el-card class="info-card base-info-card grid-item">
          <div class="card-header">基本信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">主键：</span>
              <span class="info-value">{{ data?.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改任务ID：</span>
              <span class="info-value font-bold text-blue-500">{{
                data?.rectifyTaskId || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改任务编码：</span>
              <span class="info-value font-bold text-blue-500">{{
                data?.rectifyTaskCode || '-'
              }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改任务状态：</span>
              <span class="info-value">
                <span v-if="data?.rectifyStatus === '1'" class="status-tag status-not-started"
                  >未开始</span
                >
                <span v-if="data?.rectifyStatus === '2'" class="status-tag status-processing"
                  >进行中</span
                >
                <span v-if="data?.rectifyStatus === '3'" class="status-tag status-completed"
                  >已完成</span
                >
                <span v-if="!data?.rectifyStatus">-</span>
              </span>
            </div>
          </div>
        </el-card>

        <!-- 关联问题卡片 -->
        <el-card class="info-card problem-info-card grid-item">
          <div class="card-header">关联问题信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">关联问题ID：</span>
              <span class="info-value">{{ data?.problemId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联问题编码：</span>
              <span class="info-value">{{ data?.problemCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">关联问题名称：</span>
              <span class="info-value">{{ data?.problemName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 责任信息卡片 -->
        <el-card class="info-card responsibility-card grid-item">
          <div class="card-header">责任信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">责任部门代码：</span>
              <span class="info-value">{{ data?.deptCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">责任部门名称：</span>
              <span class="info-value">{{ data?.deptName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改负责人ID：</span>
              <span class="info-value">{{ data?.leaderUserId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改负责人姓名：</span>
              <span class="info-value">{{ data?.leaderUserName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 时间信息卡片 -->
        <el-card class="info-card time-info-card grid-item">
          <div class="card-header">时间信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">整改时限（小时）：</span>
              <span class="info-value">{{ data?.rectifyDeadlineHour || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">整改截止时间：</span>
              <span class="info-value">{{ formatDate(data?.rectifyDeadlineTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">派发时间：</span>
              <span class="info-value">{{ formatDate(data?.dispatchTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">创建时间：</span>
              <span class="info-value">{{ formatDate(data?.bizCreateTime) }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">更新时间：</span>
              <span class="info-value">{{ formatDate(data?.bizUpdateTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 派发信息卡片 -->
        <el-card class="info-card dispatch-info-card grid-item">
          <div class="card-header">派发信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">派发人：</span>
              <span class="info-value">{{ data?.dispatchUser || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">派发说明：</span>
              <span class="info-value">{{ data?.dispatchDesc || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card ext-info-card grid-item">
          <div class="card-header">扩展信息</div>
          <div class="card-body">
            <div class="info-item">
              <span class="info-label">分类扩展字段1：</span>
              <span class="info-value">{{ data?.extCat1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">分类扩展字段2：</span>
              <span class="info-value">{{ data?.extCat2 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">通用扩展字段1：</span>
              <span class="info-value">{{ data?.extCommon1 || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="info-label">通用扩展字段2：</span>
              <span class="info-value">{{ data?.extCommon2 || '-' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { InspectRectifyTaskVO } from '@/api/dataHub/supervisionInspection/issueCorrectionDisposal/correctionTaskAssignment'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => InspectRectifyTaskVO | null,
    default: null
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

// 添加对 drawerVisible 的监听 以解决drawerVisible值的同步问题
watch(
  () => drawerVisible.value,
  (newVal) => {
    if (!newVal) {
      emit('close') // 当抽屉关闭时，通知父组件重置 detailVisible
    }
  }
)
const isFullscreen = ref(false)

/** 格式化日期 */
const formatDate = (val: any) => {
  if (!val) return '-'
  const date = new Date(val)
  return date.toLocaleString()
}

/** 处理关闭事件 */
const handleClose = () => {
  emit('close')
  // 退出全屏状态
  isFullscreen.value = false
}
</script>

<style scoped>
.detail-container {
  height: 100%;
  padding: 16px;
  overflow-y: auto;
  box-sizing: border-box;
  /* 防止内容溢出导致横向滚动 */
  max-width: 100%;
}

.detail-container.fullscreen {
  padding: 24px;
}

.detail-content {
  height: 100%;
  overflow-y: auto;
}

/* 网格布局 - 确保卡片横纵对齐 */
.grid-layout {
  display: grid;
  grid-template-columns: repeat(2, 1fr); /* 两列布局 */
  gap: 20px; /* 卡片间距 */
}

.grid-item {
  width: 100%;
  box-sizing: border-box;
}

.info-card {
  margin: 0;
  border-radius: 8px;
  border: none;
  transition: all 0.3s ease;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  height: 100%; /* 卡片高度充满网格项 */
  display: flex;
  flex-direction: column;
}

.info-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  color: #4e5969;
  border-bottom: 1px solid #f0f2f5;
  border-radius: 8px 8px 0 0;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.problem-info-card .card-header {
  background-color: #fff7e6;
}

.responsibility-card .card-header {
  background-color: #f6ffed;
}

.time-info-card .card-header {
  background-color: #fff0f0;
}

.dispatch-info-card .card-header {
  background-color: #f0f5ff;
}

.ext-info-card .card-header {
  background-color: #f9f0ff;
}

.card-body {
  padding: 16px;
  flex: 1; /* 内容区域填充剩余空间 */
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.6;
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
  word-break: break-all; /* 长文本自动换行 */
}

.status-tag {
  display: inline-block;
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  color: #fff;
}

.status-not-started {
  background-color: #ff4d4f;
}

.status-processing {
  background-color: #faad14;
}

.status-completed {
  background-color: #52c41a;
}

::v-deep .el-drawer__body {
  padding: 0;
  overflow: hidden;
}

/* 响应式调整 - 在小屏幕下自动改为单列 */
@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
