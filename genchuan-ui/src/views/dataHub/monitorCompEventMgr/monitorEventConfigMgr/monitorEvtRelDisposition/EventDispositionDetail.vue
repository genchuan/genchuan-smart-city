<template>
  <el-drawer
    v-model="drawerVisible"
    :title="title"
    :size="isFullscreen ? '100%' : '75%'"
    :before-close="handleClose"
    class="detail-drawer"
  >
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">事件关联处置详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <div class="detail-container">
      <div class="grid-layout">
        <!-- 基础信息卡片 -->
        <el-card class="info-card base-info-card">
          <div class="card-header">基础信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value">{{ data.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">事件类型ID：</span>
              <span class="value primary">{{ data.eventTypeId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">事件类型名称：</span>
              <span class="value">{{ data.eventTypeName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建时间：</span>
              <span class="value">{{ formatDate(data.createTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 行政区划信息卡片 -->
        <el-card class="info-card region-info-card">
          <div class="card-header">行政区划信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">行政区划编码：</span>
              <span class="value primary">{{ data.divisionCode || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">行政区划名称：</span>
              <span class="value">{{ data.divisionName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 流程模型信息卡片 -->
        <el-card class="info-card process-info-card">
          <div class="card-header">流程模型信息</div>
          <div class="card-content">
            <div class="info-item">
              <span class="label">流程模型ID：</span>
              <span class="value primary">{{ data.processModelId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">流程模型名称：</span>
              <span class="value">{{ data.processModelName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 扩展信息卡片 -->
        <el-card class="info-card ext-info-card">
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
import { EventDispositionVO } from '@/api/dataHub/monitorCompEventMgr/monitorEvtConfigMgr/monitorEvtRelDisposition'

defineOptions({ name: 'EventDispositionDetail' })

// 时间格式化
const formatDate = (val: any) => (val ? new Date(val).toLocaleDateString() : '-')

// Props
const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

// Emits
const emit = defineEmits(['close'])

// 状态
const isFullscreen = ref(false)
const data = ref<EventDispositionVO>({
  id: 0,
  eventTypeId: '',
  eventTypeName: '',
  divisionCode: '',
  divisionName: '',
  processModelId: '',
  processModelName: '',
  extCat1: '',
  extCat2: '',
  extCommon1: '',
  extCommon2: ''
})

// 设置详情数据
const setData = (row: EventDispositionVO) => {
  data.value = { ...row }
}

// 关闭抽屉
const handleClose = () => {
  emit('close')
  isFullscreen.value = false
}
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
// 暴露方法
defineExpose({ setData })
</script>

<style scoped>
.detail-drawer {
  background-color: #f5f7fa;
}

.detail-container {
  padding: 20px;
  height: calc(100% - 60px);
  overflow-y: auto;
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
}

.info-card:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08);
}

.card-header {
  padding: 12px 16px;
  font-weight: 500;
  color: #4b5563;
}

.base-info-card .card-header {
  background-color: #e6f7ff;
}

.region-info-card .card-header {
  background-color: #fff7e6;
}

.process-info-card .card-header {
  background-color: #f6ffed;
}

.ext-info-card .card-header {
  background-color: #fff0f0;
}

.card-content {
  padding: 16px;
}

.info-item {
  display: flex;
  margin-bottom: 12px;
  line-height: 1.5;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  flex: 0 0 120px;
  color: #6b7280;
  font-size: 14px;
}

.value {
  flex: 1;
  color: #374151;
  font-size: 14px;
  word-break: break-all;
}

.value.primary {
  color: #3b82f6;
  font-weight: 500;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .grid-layout {
    grid-template-columns: 1fr;
  }
}
</style>
