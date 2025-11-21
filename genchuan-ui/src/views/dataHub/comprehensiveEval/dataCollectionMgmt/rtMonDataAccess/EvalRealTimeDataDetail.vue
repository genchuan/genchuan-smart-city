<template>
  <el-drawer
    v-model="visible"
    :title="''"
    :size="isFullscreen ? '100%' : '75%'"
    direction="rtl"
    :append-to-body="true"
    class="detail-drawer"
    :before-close="handleClose"
  >
    <!-- 抽屉头部 -->
    <template #header>
      <div class="flex justify-between items-center w-full px-2">
        <span class="text-lg font-bold text-gray-800">实时监测数据详情</span>
        <el-button type="primary" link @click="isFullscreen = !isFullscreen">
          <Icon :icon="isFullscreen ? 'ep:zoom-out' : 'ep:zoom-in'" />
          {{ isFullscreen ? '退出全屏' : '全屏' }}
        </el-button>
      </div>
    </template>

    <!-- 抽屉内容 -->
    <div class="p-4 bg-gray-50 min-h-[calc(100vh-120px)]">
      <div class="grid grid-cols-2 gap-4">
        <!-- 基础信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-blue-50 text-blue-700">基础信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">主键ID：</span>
              <span class="value font-bold text-blue-600">{{ data.id || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">数据ID：</span>
              <span class="value font-bold text-blue-600">{{ data.realTimeDataId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联任务ID：</span>
              <span class="value font-bold text-blue-600">{{ data.evalTaskId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联任务名称：</span>
              <span class="value">{{ data.evalTaskName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联指标项ID：</span>
              <span class="value font-bold text-blue-600">{{ data.idxItemId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">关联指标项名称：</span>
              <span class="value">{{ data.idxItemName || '-' }}</span>
            </div>
          </div>
        </el-card>

        <!-- 设备信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-pink-50 text-pink-700">设备信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">设备ID：</span>
              <span class="value font-bold text-pink-600">{{ data.deviceId || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">设备名称：</span>
              <span class="value">{{ data.deviceName || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">同步频率：</span>
              <span class="value">{{ data.syncFrequency || '-' }} 秒</span>
            </div>
          </div>
        </el-card>

        <!-- 数据信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-green-50 text-green-700">数据信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">数据值：</span>
              <span class="value font-bold text-green-600">{{ data.dataValue || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">数据时间：</span>
              <span class="value">{{ formatDate(data.dataTime) }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建人(业务)：</span>
              <span class="value">{{ data.createUserBiz || '-' }}</span>
            </div>
            <div class="info-item">
              <span class="label">创建时间(业务)：</span>
              <span class="value">{{ formatDate(data.createTimeBiz) }}</span>
            </div>
            <div class="info-item">
              <span class="label">系统创建时间：</span>
              <span class="value">{{ formatDate(data.createTime) }}</span>
            </div>
          </div>
        </el-card>

        <!-- 状态信息卡片 -->
        <el-card class="card-beautify" :shadow="hoverShadow">
          <template #header>
            <div class="card-header bg-yellow-50 text-yellow-700">状态信息</div>
          </template>
          <div class="card-content">
            <div class="info-item">
              <span class="label">接入状态：</span>
              <el-tag :type="data.accessStatus === 'SUCCESS' ? 'success' : 'danger'">
                {{ data.accessStatus === 'SUCCESS' ? '成功' : '失败' }}
              </el-tag>
            </div>
            <div class="info-item">
              <span class="label">失败原因：</span>
              <span class="value">{{ data.failReason || '无' }}</span>
            </div>
          </div>
        </el-card>
      </div>
    </div>
  </el-drawer>
</template>
<script setup lang="ts">
import { PropType, ref, watch } from 'vue'
import { EvalRealTimeDataVO } from '@/api/dataHub/comprehensiveEval/dataCollectionMgmt/rtMonDataAccess'

/** 时间格式化函数 */
const formatDate = (val: any) => (val ? new Date(val).toLocaleString() : '-')

/** Props定义 */
const props = defineProps({
  modelValue: {
    type: Boolean,
    required: true
  },
  data: {
    type: Object as PropType<EvalRealTimeDataVO>,
    required: true,
    default: () => ({})
  }
})

/** Emits定义 */
const emit = defineEmits(['update:modelValue', 'close'])

/** 内部状态 */
const visible = ref(props.modelValue)
const isFullscreen = ref(false)
const hoverShadow = ref('hover') // 卡片hover阴影

/** 监听props变化同步内部状态 */
watch(
  () => props.modelValue,
  (newVal) => {
    visible.value = newVal
  },
  { immediate: true }
)

/** 同步内部状态到props */
watch(
  () => visible.value,
  (newVal) => {
    emit('update:modelValue', newVal)
  }
)

/** 关闭抽屉回调 */
const handleClose = () => {
  emit('close')
  isFullscreen.value = false // 退出全屏
  visible.value = false // 关键：直接更新visible状态
}

// 暴露方法给父组件（保持一致性）
defineExpose({ visible })
</script>
<style scoped>
.detail-drawer {
  --el-drawer-bg-color: #f8f9fa;
}

.card-beautify {
  border-radius: 8px;
  border: none;
  transition: all 0.3s ease;
}

.card-beautify:hover {
  transform: translateY(-3px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.08) !important;
}

.card-header {
  padding: 8px 16px;
  border-radius: 4px 4px 0 0;
  font-weight: 600;
}

.card-content {
  padding: 16px;
}

.info-item {
  display: flex;
  align-items: flex-start;
  margin-bottom: 12px;
  font-size: 14px;
}

.info-item:last-child {
  margin-bottom: 0;
}

.label {
  width: 130px;
  color: #666;
  font-weight: 500;
  flex-shrink: 0;
}

.value {
  color: #333;
  flex: 1;
  word-break: break-word;
}

/* 马克龙配色优化 */
.bg-blue-50 {
  background-color: #e6f7ff;
}

.text-blue-700 {
  color: #1890ff;
}

.text-blue-600 {
  color: #096dd9;
}

.bg-pink-50 {
  background-color: #fff0f6;
}

.text-pink-700 {
  color: #ff4d94;
}

.text-pink-600 {
  color: #e53e3e;
}

.bg-green-50 {
  background-color: #f0fff4;
}

.text-green-700 {
  color: #52c41a;
}

.text-green-600 {
  color: #389e0d;
}

.bg-yellow-50 {
  background-color: #fffbe6;
}

.text-yellow-700 {
  color: #faad14;
}

.text-yellow-600 {
  color: #fa8c16;
}

.el-drawer__body {
  padding: 0 !important;
  background-color: #f8fafc;
}

.el-drawer__header {
  padding: 16px 20px !important;
  border-bottom: 1px solid #e2e8f0;
}

@media (max-width: 1200px) {
  .grid {
    grid-template-columns: 1fr !important;
  }
}
</style>
