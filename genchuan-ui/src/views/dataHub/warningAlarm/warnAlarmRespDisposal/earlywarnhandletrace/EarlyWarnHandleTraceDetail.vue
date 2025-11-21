<template>
  <el-drawer v-model="drawerVisible" :size="isFullscreen ? '100%' : '75%'" @close="handleClose">
    <!-- 头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">预警告警处置跟踪详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>
    <div class="detail-container" :class="{ fullscreen: isFullscreen }">
      <!-- 内容区 -->
      <div class="detail-content">
        <div class="grid-layout">
          <!-- 基础信息卡片 -->
          <el-card class="info-card base-info-card">
            <div class="card-header">基础信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">主键：</span>
                <span class="value">{{ data?.id || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">跟踪ID：</span>
                <span class="value primary">{{ data?.handleTraceId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">派单ID：</span>
                <span class="value primary">{{ data?.dispatchId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">派单编号：</span>
                <span class="value">{{ data?.dispatchNo || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">预警ID：</span>
                <span class="value primary">{{ data?.earlyWarnId || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 处置信息卡片 -->
          <el-card class="info-card handle-info-card">
            <div class="card-header">处置信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">处置进度：</span>
                <span class="value">{{ data?.handleProgress || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">进度描述：</span>
                <span class="value">{{ data?.progressDesc || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">是否关键节点：</span>
                <span
                  class="value status"
                  :class="data?.isKeyNode === '1' ? 'status-success' : 'status-default'"
                >
                  {{ data?.isKeyNode === '1' ? '是' : '否' }}
                </span>
              </div>
              <div class="info-item">
                <span class="label">附件URL：</span>
                <span class="value">{{ data?.attachUrl || '-' }}</span>
              </div>
            </div>
          </el-card>

          <!-- 操作人信息卡片 -->
          <el-card class="info-card operator-info-card">
            <div class="card-header">操作人信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">操作人ID：</span>
                <span class="value">{{ data?.operUserId || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">操作人姓名：</span>
                <span class="value">{{ data?.operUserName || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">操作时间：</span>
                <span class="value">{{ formatDate(data?.operTime) }}</span>
              </div>
            </div>
          </el-card>

          <!-- 扩展信息卡片 -->
          <el-card class="info-card extend-info-card">
            <div class="card-header">扩展信息</div>
            <div class="card-body">
              <div class="info-item">
                <span class="label">分类扩展字段1：</span>
                <span class="value">{{ data?.extCat1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">分类扩展字段2：</span>
                <span class="value">{{ data?.extCat2 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">通用扩展字段1：</span>
                <span class="value">{{ data?.extCommon1 || '-' }}</span>
              </div>
              <div class="info-item">
                <span class="label">通用扩展字段2：</span>
                <span class="value">{{ data?.extCommon2 || '-' }}</span>
              </div>
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { EarlyWarnHandleTraceVO } from '@/api/dataHub/warningAlarm/warnAlarmRespDisposal/earlywarnhandletrace'

defineOptions({ name: 'EarlyWarnHandleTraceDetail' })

// 定义Props
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  },
  data: {
    type: Object as () => EarlyWarnHandleTraceVO | null,
    default: null
  }
})

// 定义Emits
const emit = defineEmits(['close'])
// 本地状态管理抽屉显示/隐藏
const drawerVisible = ref(props.visible)
watch(
  () => props.visible,
  (newVal) => {
    drawerVisible.value = newVal
  }
)
// 全屏状态
const isFullscreen = ref(false)

// 关闭抽屉
const handleClose = () => {
  emit('close')
  // 退出全屏状态
  isFullscreen.value = false
}

// 格式化日期
const formatDate = (val: any) => {
  if (!val) return '-'
  return new Date(val).toLocaleString()
}
</script>

<style scoped>
.detail-container {
  background-color: #f5f7fa;
  height: 100%;
  padding: 16px;
  box-sizing: border-box;
}

.detail-header {
  margin-bottom: 20px;
}

.detail-content {
  height: calc(100% - 50px);
  overflow-y: auto;
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
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  color: #4b5563;
  font-size: 14px;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
  border-left: 4px solid #1890ff;
}

.handle-info-card .card-header {
  background-color: #fff7e6;
  border-left: 4px solid #faad14;
}

.operator-info-card .card-header {
  background-color: #f6ffed;
  border-left: 4px solid #52c41a;
}

.extend-info-card .card-header {
  background-color: #fff0f6;
  border-left: 4px solid #eb2f96;
}

.card-body {
  padding: 16px;
  background-color: #fff;
}

.info-item {
  margin-bottom: 12px;
  line-height: 1.6;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  display: inline-block;
  width: 100px;
  color: #6b7280;
  font-size: 13px;
}

.value {
  color: #1f2937;
  font-size: 13px;
}

.value.primary {
  color: #1890ff;
  font-weight: 500;
}

.status {
  padding: 2px 6px;
  border-radius: 4px;
  font-size: 12px;
}

.status-success {
  background-color: #f0fff4;
  color: #10b981;
  border: 1px solid #a7f3d0;
}

.status-default {
  background-color: #f3f4f6;
  color: #6b7280;
  border: 1px solid #e5e7eb;
}

.fullscreen {
  padding: 0;
}

@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
